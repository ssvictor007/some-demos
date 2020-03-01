package com.ssvictor.netty.nio_chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Simple NIO ChatClient
 *
 * @author ssvictor
 * @date 2018-9-21 12:35:35
 */
public class NioClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888));

            for (;;){
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {
                    try {
                        if (selectionKey.isConnectable()){
                            SocketChannel client = (SocketChannel) selectionKey.channel();

                            if (client.isConnectionPending()){
                                client.finishConnect();

                                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                                writeBuffer.put((LocalDateTime.now() + " connected successful").getBytes());
                                writeBuffer.flip();

                                client.write(writeBuffer);

                                ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                                executorService.submit(() -> {
                                   for (;;){
                                       writeBuffer.clear();

                                       InputStreamReader inputStream = new InputStreamReader(System.in);
                                       BufferedReader reader = new BufferedReader(inputStream);

                                       String message = reader.readLine();

                                       writeBuffer.put(message.getBytes());
                                       writeBuffer.flip();
                                       client.write(writeBuffer);
                                   }
                                });
                            }
                            // register read event
                            client.register(selector, SelectionKey.OP_READ);
                        } else if (selectionKey.isReadable()){
                            SocketChannel client  = (SocketChannel) selectionKey.channel();

                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                            int count = client.read(readBuffer);

                            if (count > 0){
                                String receivedMessage = new String(readBuffer.array(), 0, count);
                                System.out.println(receivedMessage);
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                selectionKeys.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
