package com.example.demo.anotation;


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * @AliasFor 提供了属性覆盖和别名
 *
 * @author ssvictor
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Transactional
@Service(value = "transactionalService")
public @interface TransactionalService {

    /**
     *
     * @return bean名称
     */
    @AliasFor("value")
    String name() default "txManager";

    // String transactionManager() default "txManager";

    /**
     * 覆盖 {@link Transactional#value()} 默认值
     * @return {@link org.springframework.transaction.PlatformTransactionManager} Bean 名称,默认关联 "txManager" Bean
     */
    @AliasFor("name")
    String value() default "txManager";

    /**
     * 建立{@link Transactional#transactionManager()}别名
     * @return
     */
    @AliasFor(attribute = "transactionManager", annotation = Transactional.class)
    String manager() default "txManager";
}
