package com.ssvictor.netty.codec2tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;


/**
 * MyClientHandler
 *
 * @author ssvictor
 */
public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf s) throws Exception {
        // 目标数组长度不能大于原数组长度
        byte[] buffer = new byte[s.readableBytes()];
        s.readBytes(buffer);

        String message = new String(buffer, Charset.forName("utf-8"));
        System.out.println("client accept message: " + message);
        System.out.println("client accept message count: " + (++this.count));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ByteBuf buf = Unpooled.copiedBuffer("sent from client-" + i + ".", Charset.forName("utf-8"));
            ctx.writeAndFlush(buf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
