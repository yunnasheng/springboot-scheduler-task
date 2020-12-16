package com.lb.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableScheduling
public class SchedulerTaskConfig implements SchedulingConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerTaskConfig.class);

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(schedulerTheadPool());
    }

    /**
     * 线程池
     * <p>
     *  @code {@Bean(destroyMethod = "shutdown")}当线程执行完毕，自动关闭线程池
     * </p>
     * @return
     */
    @Bean(destroyMethod = "shutdown")
    public Executor schedulerTheadPool() {
        // 核心线程数等于运行时可用线程
        int coreSize = Runtime.getRuntime().availableProcessors();
        return new ScheduledThreadPoolExecutor(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "scheduler-task");
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
                LOGGER.error("执行任务失败 task : {}", runnable);
            }
        });
    }

}
