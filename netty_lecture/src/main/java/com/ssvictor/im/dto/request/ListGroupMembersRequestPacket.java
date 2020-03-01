package com.ssvictor.im.dto.request;

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
public class ListGroupMembersRequestPacket extends BasePacket {

    /**
     * 群聊id
     */
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
