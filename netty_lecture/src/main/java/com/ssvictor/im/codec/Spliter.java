package com.ssvictor.im.codec;

import com.ssvictor.im.codec.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author ssvictor007
 * @date 2019-10-8
 *
 * +--------------+-----------------+----------------------+---------+----------------+----------------+
 * | Magic Number | Message Version | Serializer Algorithm | Command | Message Length | Actual Content |
 * | 0x12345678   |        0x01     |        0x01          |   0x01  |     0x00000017 | "HELLO, WORLD" |
 * |     4 bytes  |       1 bytes   |         1 bytes      | 1 bytes |      4 bytes   |    12 bytes    |
 * +--------------+-----------------+----------------------+---------+----------------+----------------+
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 屏蔽非本协议的客户端
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
