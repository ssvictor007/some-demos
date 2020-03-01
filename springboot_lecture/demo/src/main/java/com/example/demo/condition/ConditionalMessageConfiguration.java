package com.example.demo.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalMessageConfiguration {

    @ConditionalOnSystemProperty(name = "language", value = "chinese")
    @Bean("message")
    public String chineseMessage() {
        return "世界,你好";
    }

    @ConditionalOnSystemProperty(name = "language", value = "english")
    @Bean("message")
    public String englishMessage() {
        return "Hello,World";
    }
}
