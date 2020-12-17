package com.lb.springboot.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyTask.class);

    /**
     * initialDelay - 初始化时延迟5秒执行
     * fixedDelay - 每隔2秒执行一次
     */
    @Scheduled(initialDelay = 5000,fixedDelay = 1000)
    public void execute(){
        LOGGER.info("==============执行定时任务 initialDelay & fixedDelay ==============");
    }
}
