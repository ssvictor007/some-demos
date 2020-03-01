package com.ssvictor.im.handler.server;

import com.ssvictor.im.dto.BasePacket;
import com.ssvictor.im.protocol.command.Command;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 合并并行的handler，这些handler一次read只会调用其中的一个handler
 *
 * @author ssvictor007
 * @date 2019-10-8
 */
@ChannelHandler.Sharable
public class IMServerParallelHandler extends SimpleChannelInboundHandler<BasePacket> {

    public static final IMServerParallelHandler SINGLETON = new IMServerParallelHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends BasePacket>> parallelHandlerMap;

    private IMServerParallelHandler() {
        parallelHandlerMap = new HashMap<>();

        parallelHandlerMap.put(Command.MESSAGE_REQUEST, MessageRequestHandler.SINGLETON);
        parallelHandlerMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.SINGLETON);
        parallelHandlerMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestHandler.SINGLETON);
        parallelHandlerMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestHandler.SINGLETON);
        parallelHandlerMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.SINGLETON);
        parallelHandlerMap.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.SINGLETON);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BasePacket basePacket) throws Exception {
        // 判断packet有对应的handler处理,否则交给下一个handler
        if (parallelHandlerMap.keySet().contains(basePacket.getCommand())) {

            parallelHandlerMap.get(basePacket.getCommand()).channelRead(ctx, basePacket);
        }
        ctx.fireChannelRead(basePacket);
    }
}
