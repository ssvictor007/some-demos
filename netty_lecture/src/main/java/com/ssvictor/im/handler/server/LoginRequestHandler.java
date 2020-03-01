package com.ssvictor.im.handler.server;

import com.ssvictor.im.dto.request.LoginRequestPacket;
import com.ssvictor.im.dto.response.LoginResponsePacket;
import com.ssvictor.im.session.Session;
import com.ssvictor.im.util.IDUtil;
import com.ssvictor.im.util.LoginUtil;
import com.ssvictor.im.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler SINGLETON = new LoginRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

        // 设置协议版本号
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        // 登录校验
        if (valid(loginRequestPacket)) {
            // 校验成功
            loginResponsePacket.setSuccess(true);
            // 生成userId
            String userId = IDUtil.randomId();
            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setUserName(loginRequestPacket.getUserName());
            // 将channel和session绑定
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录成功, 临时ID: " + IDUtil.randomId());

            LoginUtil.markAsLogin(ctx.channel());
        } else {
            // 校验失败
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
        }

        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    /**
     * 用户断线之后取消绑定
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        if (SessionUtil.getSession(ctx.channel()) != null){
            System.out.println(SessionUtil.getSession(ctx.channel()).getUserName() + " 下线了");
            SessionUtil.unBindSession(ctx.channel());
        }
    }

    /**
     * 假设用户信息
     *
     * @param loginRequestPacket
     * @return 登录是否成功
     */
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        if ("周星星".equals(loginRequestPacket.getUserName()) && "pwd".equals(loginRequestPacket.getPassword())) {
            return true;
        }
        if ("何金银".equals(loginRequestPacket.getUserName()) && "pwd".equals(loginRequestPacket.getPassword())) {
            return true;
        }
        if ("凌凌漆".equals(loginRequestPacket.getUserName()) && "pwd".equals(loginRequestPacket.getPassword())) {
            return true;
        }
        return false;
    }



}
