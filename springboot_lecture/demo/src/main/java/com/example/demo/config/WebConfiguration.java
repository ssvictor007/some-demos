package com.example.demo.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

//@Configuration
@SpringBootApplication  //@SpringBootApplication可标注在为引导类上
public class WebConfiguration {

    @Bean
    public ApplicationRunner runner(BeanFactory beanFactory) {
        return args -> {
            System.out.println("当前类 hello bean 实现类为 : "
                    + beanFactory.getBean("helloworld").getClass().getName());

            System.out.println("当前类 WebConfiguration bean 实现类为 : "
                    + beanFactory.getBean(WebConfiguration.class).getClass().getName());
        };
    }

    /**
     * webflux
     * curl http://localhost:8080/helloworld
     *
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> helloworld() {
        return route(GET("/helloworld"),
                serverRequest -> ok().body(Mono.just("Hello, World"), String.class));
    }

    /**
     * 监听WebServerInitializedEvent事件,此实现更具有健壮性,即使在非web应用中运行,
     * 也不至于注入WebServerApplicationContext失败
     *
     * @param event WebServerInitializedEvent
     */
    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event) {
        System.out.println("当前WebServer实现类为: " + event.getWebServer().getClass().getName());
    }

    /**
     * {@link ApplicationRunner#run(ApplicationArguments)} 方法在
     * spring Boot 启动后回调
     * @param context WebServerApplicationContext
     * @return ApplicationRunner Bean
     */
//	@Bean
//	public ApplicationRunner runner(WebServerApplicationContext context) {
//		return args -> {
//			System.out.println("当前WebServer实现类为: " + context.getWebServer().getClass().getName());
//		};
//	}
}
