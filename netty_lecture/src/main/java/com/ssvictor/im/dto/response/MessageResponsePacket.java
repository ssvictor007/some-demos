package com.ssvictor.im.dto.response;

import com.ssvictor.im.dto.BasePacket;
import com.ssvictor.im.protocol.command.Command;
import lombok.Data;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
@Data
public class MessageResponsePacket extends BasePacket {

    /**
     * fromUserId 表示那个用户id发送的
     */
    private String fromUserId;

    /**
     * fromUserName 表示那个用户名发送的
     */
    private String fromUserName;

    /**
     * message 表示具体内容
     */
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
