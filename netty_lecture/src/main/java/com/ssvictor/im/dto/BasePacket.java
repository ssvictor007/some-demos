package com.ssvictor.im.dto;

import lombok.Data;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
@Data
public abstract class BasePacket {
    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     * @return 指令标识
     */
    public abstract Byte getCommand();
}