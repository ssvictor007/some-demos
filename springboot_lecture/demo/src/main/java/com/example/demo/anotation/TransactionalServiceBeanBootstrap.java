package com.example.demo.anotation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.SimpleTransactionStatus;

import java.util.Map;

@Configuration
@ComponentScan(basePackageClasses = TransactionalServiceBean.class)
@EnableTransactionManagement
public class TransactionalServiceBeanBootstrap {
    public static void main(String[] args) {
        // 注册当前引导类作为Configuration Class
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(TransactionalServiceBeanBootstrap.class);
        // 获取所有TransactionalServiceBean类型的 Bean, 其中Key为Bean名称
        Map<String, TransactionalServiceBean> beanMap = context.getBeansOfType(TransactionalServiceBean.class);

        beanMap.forEach((beanName, bean) -> {
            System.out.printf("Bean 名称 : %s, 对象 : %s\n", beanName, bean);
            bean.save();
        });

        context.close();
    }

    @Bean("txManager")
    public PlatformTransactionManager txManager() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus status) throws TransactionException {
                System.out.println("txManager 事务提交...");
            }

            @Override
            public void rollback(TransactionStatus status) throws TransactionException {

            }
        };
    }

    @Bean("txManager2")
    public PlatformTransactionManager txManager2() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus status) throws TransactionException {
                System.out.println("txManager2 事务提交...");
            }

            @Override
            public void rollback(TransactionStatus status) throws TransactionException {

            }
        };
    }
}

@TransactionalService(name = "transactionalServiceBean")
class TransactionalServiceBean {
    public void save() {
        System.out.println("保存操作...");
    }
}
