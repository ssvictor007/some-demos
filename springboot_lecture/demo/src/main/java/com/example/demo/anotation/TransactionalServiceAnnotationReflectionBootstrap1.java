package com.example.demo.anotation;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.util.Set;

/**
 * 1. java中,class对象是类元信息的载体,包含了字段、方法、Annotation等,class是通过ClassLoader#loadClass()方法实现
 * 2. spring体系中,则通过ASM实现,读取的是类资源,直接操作其中字节码,获取相关元信息,同时便于spring相关的字节码提升
 */
// @TransactionalService(name = "test")
public class TransactionalServiceAnnotationReflectionBootstrap1 {
    public static void main(String[] args) throws Exception {
        String className = TransactionalServiceAnnotationReflectionBootstrap1.class.getName();
        // 构建 MetadataReaderFactory 实例
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        // 读取 @TransactionService MetadataReader 信息
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(className);
        // 读取 @TransactionService AnnotationMetadata 信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        annotationMetadata.getAnnotationTypes().forEach(annotationType -> {
            Set<String> metaAnnotationTypes = annotationMetadata.getMetaAnnotationTypes(annotationType);

            metaAnnotationTypes.forEach(metaAnnotationType -> {
                System.out.printf("注解 @%s 元标注 @%s\n", annotationType, metaAnnotationType);
            });
        });

    }
}
