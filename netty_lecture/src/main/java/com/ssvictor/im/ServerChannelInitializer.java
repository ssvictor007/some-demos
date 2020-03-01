package com.ssvictor.im;

import com.ssvictor.im.codec.PacketDecoder;
import com.ssvictor.im.codec.PacketEncoder;
import com.ssvictor.im.codec.Spliter;
import com.ssvictor.im.handler.server.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
                //心跳监测
        pipeline.addLast(new IMIdleStateHandler())
                .addLast(new Spliter())
                .addLast(new PacketDecoder())
                .addLast(LoginRequestHandler.SINGLETON)
                .addLast(new HeartBeatRequestHandler())
                .addLast(AuthHandler.SINGLETON)
                .addLast(LogoutRequestHandler.SINGLETON)

                // parallel handler
                .addLast(IMServerParallelHandler.SINGLETON)

                .addLast(new PacketEncoder());
    }
}
