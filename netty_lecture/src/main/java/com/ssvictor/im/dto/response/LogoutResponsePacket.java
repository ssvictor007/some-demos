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
public class LogoutResponsePacket extends BasePacket {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
