package com.example.demo.failure;

import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalysisReporter;

public class ConsoleFailureAnalysisReporter implements FailureAnalysisReporter {
    @Override
    public void report(FailureAnalysis analysis) {
        System.out.printf("故障描述 : %s\n 执行动作: %s\n 异常堆栈: %s\n",
                analysis.getDescription(),
                analysis.getAction(),
                analysis.getCause());
    }
}
