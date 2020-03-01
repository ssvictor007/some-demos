package com.ssvictor.im.dto.response;

import com.ssvictor.im.dto.BasePacket;
import com.ssvictor.im.protocol.command.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
@Data
@NoArgsConstructor
public class JoinGroupResponsePacket extends BasePacket {

    /**
     * 群聊id
     */
    private String groupId;

    /**
     * 操作是否成功
     */
    private Boolean success;

    /**
     * 操作失败原因
     */
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
