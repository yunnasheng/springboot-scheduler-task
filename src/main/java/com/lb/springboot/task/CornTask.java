package com.lb.springboot.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CornTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CornTask.class);
    /**
     * 5 秒执行一次
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void execute(){
        LOGGER.info("==============执行定时任务 CornTask  ==============");
    }
}
