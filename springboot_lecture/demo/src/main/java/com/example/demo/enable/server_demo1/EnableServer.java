package com.example.demo.enable.server_demo1;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerImportSelector1.class)
public @interface EnableServer {
    /**
     * 设置服务器类型
     * @return non-null
     */
    Server.Type type();
}
