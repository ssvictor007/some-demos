package com.ssvictor.im.dto.response;

import com.ssvictor.im.dto.BasePacket;
import com.ssvictor.im.protocol.command.Command;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public class HeartBeatResponsePacket extends BasePacket {
    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
