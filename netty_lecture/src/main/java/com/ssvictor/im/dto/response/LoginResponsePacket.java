package com.ssvictor.im.dto.response;

import com.ssvictor.im.dto.BasePacket;
import com.ssvictor.im.protocol.command.Command;
import lombok.Data;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
@Data
public class LoginResponsePacket extends BasePacket {

    /**
     * reason
     */
    private String reason;

    /**
     * success
     */
    private Boolean success;

    /**
     * userId
     */
    private String userId;

    /**
     * userName
     */
    private String userName;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
