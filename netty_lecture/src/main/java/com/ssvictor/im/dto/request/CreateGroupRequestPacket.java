package com.ssvictor.im.dto.request;

import com.ssvictor.im.dto.BasePacket;
import com.ssvictor.im.protocol.command.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
@Data
@NoArgsConstructor
public class CreateGroupRequestPacket extends BasePacket {
    /**
     * 群聊中用户列表
     */
    private List<String> userIdList;

    @Override
    public Byte getCommand() {

        return Command.CREATE_GROUP_REQUEST;
    }
}
