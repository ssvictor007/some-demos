package com.ssvictor.im.handler.server;

import com.ssvictor.im.dto.request.HeartBeatRequestPacket;
import com.ssvictor.im.dto.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket msg) throws Exception {
        System.out.println("收到客户端心跳包,链接有效");
        ctx.channel().writeAndFlush(new HeartBeatResponsePacket());
    }
}
