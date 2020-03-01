package com.example.demo.exit;

import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
public class ExitCodeGeneratorBootstrap {

    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        System.out.println("exitCodeGenerator bean创建");
        return () -> {
            System.out.println("执行退出码生成");
            return 888;
        };
    }

    public static void main(String[] args) {
        int exitCode = SpringApplication.exit(
            new SpringApplicationBuilder(ExitCodeGeneratorBootstrap.class)
                .listeners((ApplicationListener<ExitCodeEvent>) event -> {
                    System.out.println("监听到退出码: " +event.getExitCode());
                })
                .web(WebApplicationType.NONE)
                .run(args)

        );
        System.exit(exitCode);
    }
}
