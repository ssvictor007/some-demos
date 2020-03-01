package com.ssvictor.im.handler.client;

import com.ssvictor.im.dto.response.LogoutResponsePacket;
import com.ssvictor.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
        System.out.println("退出登录! ");
        ctx.channel().close();
    }
}
