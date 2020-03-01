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
public class GroupMessageRequestPacket extends BasePacket {

    /**
     * 目标群组id
     */
    private String toGroupId;
    /**
     * 消息
     */
    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }
}
