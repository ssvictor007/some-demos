package com.example.demo.enable.server_demo2;

import com.example.demo.enable.server_demo2.EnableServer;
import com.example.demo.enable.server_demo2.FtpServer;
import com.example.demo.enable.server_demo2.HttpServer;
import com.example.demo.enable.server_demo2.Server;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class ServerImportSelector2 implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 读取 EnableServer 中所有的属性方法,本例中仅有 type() 方法
        // 其中 key 为属性方法的名称, value 为属性方法的返回对象
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableServer.class.getName());
        // 获取名为 type 的属性方法, 并且强制转化成 Server.Type 类型
        Server.Type type = (Server.Type) annotationAttributes.get("type");
        // 导入的类名称数组
        String[] importClassNames = new String[0];
        switch (type) {
            case HTTP:
                importClassNames = new String[]{HttpServer.class.getName()};
                break;
            case FTP:
                importClassNames = new String[]{FtpServer.class.getName()};
                break;

        }
        return importClassNames;
    }
}
