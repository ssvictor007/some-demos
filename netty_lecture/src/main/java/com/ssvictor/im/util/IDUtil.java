package com.ssvictor.im.util;

import java.util.UUID;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
