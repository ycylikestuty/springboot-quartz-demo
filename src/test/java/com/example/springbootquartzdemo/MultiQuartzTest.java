package com.example.springbootquartzdemo;

import com.example.springbootquartzdemo.schedule.SimpleJob;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 多触发器的定时任务
 */
public class MultiQuartzTest {
    @Test
    public void multiJobTest() throws SchedulerException,InterruptedException{
        //1.创建调度器
        SchedulerFactory schedulerFactory=new StdSchedulerFactory();
        Scheduler scheduler=schedulerFactory.getScheduler();
        //2.创建JobDetail实例，与SimpleJob绑定，注意要设置storeDurably()，否则报错
        //storeDurably()方法将JobDetail设置为 孤立后 保存存储（没有触发器指向该作业的情况）
        JobDetail jobDetail= JobBuilder.newJob(SimpleJob.class)
                .withIdentity("job1","job-group")
                .storeDurably()
                .build();
        //3.分别构建触发器实例
        Trigger trigger1=TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "trigger-group")
                .startNow()
                .forJob(jobDetail)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();
        Trigger trigger2=TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "trigger-group")
                .startNow()
                .forJob(jobDetail)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();
        //4.调度器中添加job
        scheduler.addJob(jobDetail,false);
        scheduler.scheduleJob(trigger1);
        scheduler.scheduleJob(trigger2);
        //启动调度器
        scheduler.start();
        // 休眠任务执行时长
        TimeUnit.SECONDS.sleep(20);
        scheduler.shutdown();
    }
}
