package com.ssvictor.im.protocol.command.console;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public class ConsoleCommandManager implements ConsoleCommand {

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("sendToGroup", new SendToGroupConsoleCommand());

        consoleCommandMap.put("logout", new LogoutConsoleCommand());

        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
        consoleCommandMap.put("listGroupNumbers", new ListGroupMembersConsoleCommand());

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("指令集: [");
        consoleCommandMap.forEach((k, v) -> {
            System.out.print(k + " ");
        });
        System.out.println("]");
        System.out.print("请输入指令: ");
        //  获取第一个指令
        String command = scanner.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.err.println("无法识别[" + command + "]指令，请重新输入!");
        }
    }
}
