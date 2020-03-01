package com.ssvictor.im.dto.request;

import com.ssvictor.im.dto.BasePacket;
import com.ssvictor.im.protocol.command.Command;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public class HeartBeatRequestPacket extends BasePacket {
    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
