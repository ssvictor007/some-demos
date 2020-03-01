package com.ssvictor.im;

import com.ssvictor.im.codec.PacketDecoder;
import com.ssvictor.im.codec.PacketEncoder;
import com.ssvictor.im.codec.Spliter;
import com.ssvictor.im.handler.client.*;
import com.ssvictor.im.handler.server.IMIdleStateHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new IMIdleStateHandler())
                .addLast(new Spliter())
                .addLast(new PacketDecoder())
                .addLast(new HeartBeatTimeHandler())
                .addLast(new LoginResponseHandler())
                .addLast(new CreateGroupResponseHandler())
                .addLast(new JoinGroupResponseHandler())
                .addLast(new QuitGroupResponseHandler())
                .addLast(new ListGroupMembersResponseHandler())
                .addLast(new GroupMessageResponseHandler())
                .addLast(new MessageResponseHandler())
                .addLast(new LogoutResponseHandler())
                .addLast(new PacketEncoder());
    }
}
