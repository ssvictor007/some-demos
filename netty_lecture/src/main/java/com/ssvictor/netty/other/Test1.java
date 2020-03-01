package com.ssvictor.netty.other;

import io.netty.util.NettyRuntime;

import java.net.URL;

public class Test1 {
    public static void main(String[] args) {
        // Get the configured number of available processors.
         System.out.println(NettyRuntime.availableProcessors() * 2);

    }
}
