package com.example.demo.enable.server_demo1;

import org.springframework.stereotype.Component;

@Component
public class FtpServer implements Server {
    @Override
    public void start() {
        System.out.println("FTP 服务器启动中...");
    }

    @Override
    public void stop() {
        System.out.println("FTP 服务器关闭中...");
    }
}
