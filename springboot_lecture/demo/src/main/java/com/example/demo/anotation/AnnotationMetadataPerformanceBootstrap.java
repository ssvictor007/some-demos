package com.example.demo.anotation;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

/**
 * 比较基于 反射API方式 和 基于ASM方式 的性能
 */
public class AnnotationMetadataPerformanceBootstrap {
    public static void main(String[] args) throws Exception {
        // 反射实现
        AnnotationMetadata standardAnnotaionMetadata = new StandardAnnotationMetadata(TransactionalService.class);

        //-----------------------------------------------------------------------------------------------------
        SimpleMetadataReaderFactory factory = new SimpleMetadataReaderFactory();

        MetadataReader metadataReader = factory.getMetadataReader(TransactionalService.class.getName());

        // ASM实现
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        // 10W次
        int times = 10 * 10000;
        testAnnotationMetadataPerformance(standardAnnotaionMetadata, times);
        testAnnotationMetadataPerformance(annotationMetadata, times);

        // 10W次
        times = 10000 * 10000;
        testAnnotationMetadataPerformance(standardAnnotaionMetadata, times);
        testAnnotationMetadataPerformance(annotationMetadata, times);

        /*
        100000 次 StandardAnnotationMetadata.getAnnotationTypes() 方法执行消耗 6 ms
        100000 次 SimpleAnnotationMetadata.getAnnotationTypes() 方法执行消耗 2 ms
        ------------------------------------------------------------------------------
        100000000 次 StandardAnnotationMetadata.getAnnotationTypes() 方法执行消耗 20 ms
        100000000 次 SimpleAnnotationMetadata.getAnnotationTypes() 方法执行消耗 0 ms
         */
    }

    private static void testAnnotationMetadataPerformance(AnnotationMetadata annotationMetadata, int times) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            annotationMetadata.getAnnotationTypes();
        }
        long costTime = System.currentTimeMillis() - startTime;
        System.out.printf("%d 次 %s.getAnnotationTypes() 方法执行消耗 %s ms\n", times,
                annotationMetadata.getClass().getSimpleName(), costTime);
    }
}
