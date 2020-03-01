package com.ssvictor.im.protocol.command.console;

import com.ssvictor.im.dto.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.print("请输入群组groupId: ");
        String toGroupId = scanner.next();
        System.out.print("请输入发送的消息: ");
        String message = scanner.next();
        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));

    }
}
