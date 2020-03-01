package com.ssvictor.netty.netty_protobuf;

import com.ssvictor.netty.netty_protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestClientHandler extends SimpleChannelInboundHandler<DataInfo.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfo.MyMessage message = DataInfo.MyMessage.newBuilder()
                                            .setDataType(DataInfo.MyMessage.DataType.TeacherType)
                                            .setTeacher(
                                                    DataInfo.Teacher.newBuilder()
                                                    .setName("asdasd")
                                                    .setAge(20)
                                                    .build()
                                            ).build();

        ctx.channel().writeAndFlush(message);

    }
}
