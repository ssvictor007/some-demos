package com.example.demo.anotation;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// @TransactionalService(name = "test")
public class TransactionalServiceAnnotationReflectionBootstrap2 {

    /**
     * java 反射 API StandardAnnotationMetadata 实现
     *
     * 从 springframework4.0 开始, AnnotationMetadata#getMetaAnnotationTypes(String) 方法能够获取所有元注解类型集合,
     * 再结合 getAnnotationAttributes(String) 方法返回注解所关联的属性信息,以 Map 结构存储,
     * @param args
     */
    public static void main(String[] args) {
        // 读取 @TransactionService AnnotationMetadata 信息
        AnnotationMetadata annotationMetadata = AnnotationMetadata.introspect(TransactionalServiceAnnotationReflectionBootstrap2.class);
        // 获取所有元注解类型(全类名)集合
        Set<String> metaAnnotationTypes = annotationMetadata.getAnnotationTypes()
                // To Stream
                .stream()
                //读取单注解的元注解类型集合
                .map(annotationMetadata::getMetaAnnotationTypes)
                // 合并元注解(全类名)集合
                .collect(LinkedHashSet::new, Set::addAll, Set::addAll);

        metaAnnotationTypes.forEach(metaAnnotation -> { // 读取所有元注解类型
            // 读取元注解信息
            Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(metaAnnotation);
            if (!CollectionUtils.isEmpty(annotationAttributes)) {
                annotationAttributes.forEach((name, value) -> {
                    System.out.printf("注解 @%s 属性 %s = %s\n", ClassUtils.getShortName(metaAnnotation), name, value);
                });
            }
        });
    }

    /**
     * 基于 Java 反射API 获取元(嵌套)注解及属性信息的实现,需递归地获取元注解
     * @param args
     */
    public static void main1(String[] args) {
        // Class 实现了AnnotatedElement接口
        AnnotatedElement annotatedElement = TransactionalServiceAnnotationReflectionBootstrap2.class;
        // 从 AnnotatedElement 获取 TransactionService
        TransactionalService transactionalService = annotatedElement.getAnnotation(TransactionalService.class);

        /**
         * 热身
        1. 显式的调用属性方法 获取属性
        String nameAttribute = transactionService.name();
        System.out.println("TransactionService.name() = " + nameAttribute);

        2.1 完全 java 反射实现, 所有的注解均实现了 Annotation 接口
        ReflectionUtils.doWithMethods(TransactionalService.class,
                method -> System.out.printf("TransactionService.%s() = %s\n", method.getName(),
                                            ReflectionUtils.invokeMethod(method, transactionService)),
                                            // 选择其中的无参方法
                                            method -> method.getParameterCount() == 0);

        2.2 完全 java 反射实现, 所有的注解均实现了 Annotation 接口
        ReflectionUtils.doWithMethods(TransactionalService.class,
                method -> System.out.printf("TransactionService.%s() = %s\n", method.getName(),
                        ReflectionUtils.invokeMethod(method, transactionService)),
                // 选择非Annotation方法
                method -> !method.getDeclaringClass().equals(Annotation.class));
        */
        // 获取 transactionService 的所有元注解
        Set<Annotation> metaAnnotations = getAllMetaAnnotations(transactionalService);
        // 输出结果
        metaAnnotations.forEach(TransactionalServiceAnnotationReflectionBootstrap2 :: printAnnotationAttribute);
    }

    private static Set<Annotation> getAllMetaAnnotations(Annotation annotation) {
        Annotation[] metaAnnotations = annotation.annotationType().getAnnotations();

        if (ObjectUtils.isEmpty(metaAnnotations)) {
            return Collections.emptySet();
        }

        // 获取所有非 Java 标准元注解集合
        Set<Annotation> metaAnnotationsSet = Stream.of(metaAnnotations)
                // 排除Java标准注解,如@Target @Documented等，他们相互依赖,将导致无限递归
                // 通过 java.lang.annotation 报名排除
                .filter(metaAnnotation -> !Target.class.getPackage().equals(metaAnnotation.annotationType().getPackage()))
                .collect(Collectors.toSet());

        // 递归 查找元注解的元注解集合
        Set<Annotation> metasMetaAnnotationsSet = metaAnnotationsSet.stream()
                .map(TransactionalServiceAnnotationReflectionBootstrap2::getAllMetaAnnotations)
                .collect(HashSet::new, Set::addAll, Set::addAll);

        // 添加递归结果
        metaAnnotationsSet.addAll(metasMetaAnnotationsSet);

        return metaAnnotationsSet;
    }

    private static void printAnnotationAttribute(Annotation annotation) {
        Class<?> annotationType = annotation.annotationType();
        // Java反射调用
        ReflectionUtils.doWithMethods(annotationType,
                    method -> System.out.printf("@%s.%s() = %s\n",
                                annotationType.getSimpleName(),
                                method.getName(),
                                ReflectionUtils.invokeMethod(method, annotation)
                            ),
                    method -> method.getParameterCount() == 0 && !method.getDeclaringClass().equals(Annotation.class)
                );
    }
}
