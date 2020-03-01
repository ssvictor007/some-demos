package com.ssvictor.netty.nio_chat;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Simple NIO ChatServer
 *
 * @author ssvictor
 * @date 2018-9-21 11:09:27
 */
public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<String, SocketChannel>();

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //set NonBlocking
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        // bind localhost:port
        serverSocket.bind(new InetSocketAddress(8888));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            try {
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey ->{

                    final SocketChannel client;

                    try {
                        if (selectionKey.isAcceptable()){
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);
                            // set clientKey,in order to find which socket send the message
                            String clientKey = UUID.randomUUID().toString();

                            clientMap.put(clientKey, client);
                        } else if (selectionKey.isReadable()){
                            client = (SocketChannel) selectionKey.channel();

                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                            int readCount = client.read(readBuffer);

                            if (readCount > 0){
                                //buffer => in to out
                                readBuffer.flip();

                                Charset charset = Charset.forName("utf-8");
                                String receivedMessage = String.valueOf(charset.decode(readBuffer).array());

                                System.out.println(client + ": " +receivedMessage);

                                String sendKey = null;

                                //get which socket send the message
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    if (client == entry.getValue()){
                                        sendKey = entry.getKey();
                                        break;
                                    }
                                }

                                //return the receivedMessage to all socket
                                for (Map.Entry<String, SocketChannel> entry: clientMap.entrySet()) {
                                    //entry value is socketChannel
                                    SocketChannel entryValue = entry.getValue();

                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                                    if (sendKey.equals(entry.getKey())){
                                        writeBuffer.put(("I Say: " + receivedMessage).getBytes());
                                    } else {
                                        writeBuffer.put((sendKey + " Says: " + receivedMessage).getBytes());
                                    }
                                    //buffer => in to out
                                    writeBuffer.flip();

                                    entryValue.write(writeBuffer);
                                }
                            }
                        }
                        // else if socket is disconnected
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                // clear selectionKey
                selectionKeys.clear();
            } catch (Exception e){
                e.printStackTrace();
            }


        }

    }
}
