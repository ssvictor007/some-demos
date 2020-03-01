package com.ssvictor.im.dto.request;

import com.ssvictor.im.dto.BasePacket;
import com.ssvictor.im.protocol.command.Command;
import lombok.Data;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
@Data
public class LoginRequestPacket extends BasePacket {

    /**
     * username
     */
    private String userName;

    /**
     * password
     */
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
