package com.example.demo.enable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {
    /**
     * 创建名为 helloWorld 的 bean
     * @return
     */
    @Bean
    public String helloWorld() {
        return "Hello World";
    }
}
