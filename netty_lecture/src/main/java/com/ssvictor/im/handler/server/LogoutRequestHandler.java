package com.ssvictor.im.handler.server;

import com.ssvictor.im.dto.request.LogoutRequestPacket;
import com.ssvictor.im.dto.response.LogoutResponsePacket;
import com.ssvictor.im.util.LoginUtil;
import com.ssvictor.im.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler SINGLETON = new LogoutRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        if (SessionUtil.getSession(ctx.channel()) != null){
            System.out.println(SessionUtil.getSession(ctx.channel()).getUserName() + " 下线了");
            SessionUtil.unBindSession(ctx.channel());
        }
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        // 使用channel的writeAndFlush保证从pipeline的尾端流动
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
