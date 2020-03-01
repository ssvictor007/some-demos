package com.ssvictor.netty.codec3tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++){
            String messageToBeSent = "sent from client: ";
            int responseLength = messageToBeSent.getBytes(Charset.forName("utf-8")).length;
            byte[] responseContent = messageToBeSent.getBytes(Charset.forName("utf-8"));
            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setLength(responseLength);
            personProtocol.setContent(responseContent);

            ctx.writeAndFlush(personProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("client accept:");
        System.out.println("length: " + length);
        System.out.println("content: " + new String(content));
        System.out.println("client accept count : " + (++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
