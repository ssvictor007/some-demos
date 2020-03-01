package com.ssvictor.im.dto.request;

import com.ssvictor.im.dto.BasePacket;
import com.ssvictor.im.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestPacket extends BasePacket {

    /**
     * toUserId 表示要发送给哪个用户
     */
    private String toUserId;

    /**
     * message 表示具体内容
     */
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
