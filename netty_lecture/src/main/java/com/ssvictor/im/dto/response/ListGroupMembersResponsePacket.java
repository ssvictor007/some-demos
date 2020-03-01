package com.ssvictor.im.dto.response;

import com.ssvictor.im.dto.BasePacket;
import com.ssvictor.im.protocol.command.Command;
import com.ssvictor.im.session.Session;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
@Data
@NoArgsConstructor
public class ListGroupMembersResponsePacket extends BasePacket {
    /**
     * 群聊id
     */
    private String groupId;

    /**
     * 群组用户session list
     */
    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
