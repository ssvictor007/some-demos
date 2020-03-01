package com.example.demo.failure;

import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalyzer;

/**
 * spring.factories 配置
 */
public class UnknowErrorFailureAnalyzer implements FailureAnalyzer {
    @Override
    public FailureAnalysis analyze(Throwable failure) {
        if (failure instanceof UnknownError) {
            return new FailureAnalysis("未知错误, 请重启尝试", null, failure);
        }
        return null;
    }
}
