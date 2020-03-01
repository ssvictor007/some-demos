package com.example.demo.failure;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class UnknowErrorSpringbootBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Object.class)
                .initializers(applicationContext -> {
                    throw new UnknownError("故意抛出异常");
                })
                .web(WebApplicationType.NONE)
                .run(args)
                .close();
    }
}
