package com.ssvictor.im.handler.server;

import com.ssvictor.im.dto.request.HeartBeatRequestPacket;
import com.ssvictor.im.handler.client.CreateGroupResponseHandler;
import com.ssvictor.im.util.LoginUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static final AuthHandler SINGLETON = new AuthHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtil.hasLogin(ctx.channel())){
            System.out.println("无登录验证，强制关闭连接!");
            ctx.channel().close();
        } else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");

    }
}
