package com.example.queue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadConfig {
    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(10);

//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(2);
//        executor.setMaxPoolSize(4);
//        executor.setThreadNamePrefix("my-thread-");
//        executor.initialize();
//        return executor;
    }
}
