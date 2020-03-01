package com.example.demo.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConditionalOnSystemPropertyBootstrap {
    public static void main(String[] args) {
        // 设置 System Property language = chinese
        System.setProperty("language", "chinese");
        // 构建 Annotation 配置驱动 Spring 上下文
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        // 注册配置Bean ConditionalMessageConfiguration 到 Spring 上下文
        configApplicationContext.register(ConditionalMessageConfiguration.class);

        configApplicationContext.refresh();
        configApplicationContext.start();

        String message = configApplicationContext.getBean("message", String.class);

        // 输出
        System.out.printf("\"message\" Bean 对象 : %s\n", message);
    }
}
