package com.ssvictor.im.protocol.command.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
