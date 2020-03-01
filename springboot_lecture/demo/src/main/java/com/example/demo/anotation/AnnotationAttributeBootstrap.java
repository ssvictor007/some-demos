package com.example.demo.anotation;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.AnnotatedElement;

public class AnnotationAttributeBootstrap {
    public static void main(String[] args) {
        AnnotatedElement annotatedElement = TransactionalServiceBean.class;

        // 获取@Service 注解独享属性
        AnnotationAttributes serviceAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Service.class);

        // 获取@Transactional 注解独享属性
        AnnotationAttributes transactionalAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Transactional.class);

        System.out.println(serviceAttributes);
        System.out.println(transactionalAttributes);
    }
}
