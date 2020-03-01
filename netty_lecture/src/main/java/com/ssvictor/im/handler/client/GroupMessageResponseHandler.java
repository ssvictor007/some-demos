package com.ssvictor.im.handler.client;

import com.ssvictor.im.dto.response.GroupMessageResponsePacket;
import com.ssvictor.im.session.Session;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author ssvictor007
 * @date 2019-10-9
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket responsePacket) throws Exception {
        String fromGroupId = responsePacket.getFromGroupId();
        Session fromUser = responsePacket.getFromUser();
        System.out.println("群号[" + fromGroupId + "]中[" + fromUser + "]说: " + responsePacket.getMessage());
    }
}
