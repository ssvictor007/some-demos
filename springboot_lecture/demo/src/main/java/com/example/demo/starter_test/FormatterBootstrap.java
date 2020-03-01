//package com.example.demo.starter_test;
//
//import com.ssvictor.samples.autoconfigure.formatter.Formatter;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableAutoConfiguration
//public class FormatterBootstrap {
//    public static void main(String[] args) {
//        ConfigurableApplicationContext context = new SpringApplicationBuilder(FormatterBootstrap.class)
//                // 非web应用
//                .web(WebApplicationType.NONE)
//                // .properties("formatter.enabled=true")
//                // run
//                .run(args);
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("name", "胜傻victor");
//
//        Formatter formatter = context.getBean(Formatter.class);
//
//        System.out.printf("data : %s\n", formatter.format(data));
//
//        context.close();
//    }
//}
