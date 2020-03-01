package com.example.demo.enable.server_demo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 基于 ImportSelector 接口实现
 */
@Configuration
@EnableServer(type = Server.Type.HTTP)
public class EnableServerBootstrap1 {
    public static void main(String[] args) {
        // 构建 Annotation 配置驱动 Spring 上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册引导当前类(@Configuration标注)到 spring 上下文
        context.register(EnableServerBootstrap1.class);
        // 启动上下文
        context.refresh();
        // 获取名实际服务器 Bean
        Server server = context.getBean(Server.class);

        server.start();
        server.stop();

        // 关闭上下文
        context.close();
    }
}
