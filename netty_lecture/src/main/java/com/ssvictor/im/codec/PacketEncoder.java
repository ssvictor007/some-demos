package com.ssvictor.im.codec;

import com.ssvictor.im.dto.BasePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public class PacketEncoder extends MessageToByteEncoder<BasePacket> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, BasePacket basePacket, ByteBuf byteBuf) throws Exception {

        PacketCodeC.INSTANCE.encode(byteBuf, basePacket);
    }
}
