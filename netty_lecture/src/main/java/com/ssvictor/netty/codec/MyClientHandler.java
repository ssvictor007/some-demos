package com.ssvictor.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * MyClientHandler
 *
 * @author ssvictor
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Long s) throws Exception {
        System.out.println(channelHandlerContext.channel().remoteAddress() + ", " + s);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush(123456L);
    }
}
