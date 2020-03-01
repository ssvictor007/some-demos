package com.example.demo.enable;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@EnableHelloWorld
@Configuration
public class EnableHelloWorldBootstrap {
    public static void main(String[] args) {
        // 构建 Annotation 配置驱动 Spring 上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册引导当前类(@Configuration标注)到 spring 上下文
        context.register(EnableHelloWorldBootstrap.class);
        // 启动上下文
        context.refresh();
        // 获取名为 helloWorld 的 Bean
        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println(helloWorld);

        // 关闭上下文
        context.close();
    }
}
