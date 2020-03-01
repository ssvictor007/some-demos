package com.example.demo.enable.server_demo2;

public interface Server {
    /**
     * 启动服务器
     */
    void start();

    /**
     * 关闭服务器
     */
    void stop();

    enum Type {
        // 服务器类型
        HTTP,
        FTP
    }
}
