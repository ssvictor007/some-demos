package com.ssvictor.netty.netty_protobuf;

import com.ssvictor.netty.netty_protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<DataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.MyMessage msg) throws Exception {
        DataInfo.MyMessage.DataType dataType = msg.getDataType();
        if (DataInfo.MyMessage.DataType.TeacherType == dataType){
            DataInfo.Teacher teacher = msg.getTeacher();
            System.out.println(teacher.getName());
            System.out.println(teacher.getAge());
        }
    }
}
