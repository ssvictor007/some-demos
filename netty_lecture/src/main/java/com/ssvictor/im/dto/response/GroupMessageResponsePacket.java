package com.ssvictor.im.dto.response;

import com.ssvictor.im.dto.BasePacket;
import com.ssvictor.im.protocol.command.Command;
import com.ssvictor.im.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageResponsePacket extends BasePacket {

    /**
     * 源群组id
     */
    private String fromGroupId;

    /**
     * 源用户
     */
    private Session fromUser;

    /**
     * 消息体
     */
    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
