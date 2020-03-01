package com.ssvictor.im.handler.client;

import com.ssvictor.im.dto.response.LoginResponsePacket;
import com.ssvictor.im.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {

        if (loginResponsePacket.getSuccess()) {
            LoginUtil.markAsLogin(ctx.channel());
            System.out.println("[" + loginResponsePacket.getUserName() + "]登录成功，userId 为: " + loginResponsePacket.getUserId());
            System.out.println(new Date() + ": 客户端登录成功");
        } else {
            System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
    }
}
