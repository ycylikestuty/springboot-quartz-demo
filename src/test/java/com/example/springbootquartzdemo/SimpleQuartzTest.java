package com.example.springbootquartzdemo;

import com.example.springbootquartzdemo.schedule.SimpleJob;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

public class SimpleQuartzTest {

    /**
     * 基于时间间隔的定时任务
     */
    @Test
    public void simpleTest() throws SchedulerException,InterruptedException{
        //1.创建Scheduler即调度器
        SchedulerFactory schedulerFactory=new StdSchedulerFactory();
        Scheduler scheduler=schedulerFactory.getScheduler();
        //2.创建JobDetail实例，并与SimpleJob（要执行的任务）绑定Job要执行的内容
        JobDetail jobDetail= JobBuilder.newJob(SimpleJob.class)
                .withIdentity("job1","group1")
                .build();
        //3.构建Trigger即触发器，定义任务执行的频率和时长
        Trigger trigger=TriggerBuilder.newTrigger()
                //指定group和name，这些是唯一身份标识
                .withIdentity("trigger-1","trigger-group")
                //立即生效
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        //每隔2s执行一次
                        .withIntervalInSeconds(2)
                        //永久执行
                        .repeatForever())
                .build();
        //4.将job和trigger交给scheduler调度
        scheduler.scheduleJob(jobDetail,trigger);
        //5.启动scheduler
        scheduler.start();
        //休眠，决定调度器执行时间，这里为30s
        TimeUnit.SECONDS.sleep(30);
        //关闭scheduler
        scheduler.shutdown();
    }

    /**
     * 基于cron表达式的定时任务
     */
    @Test
    public void cronTest() throws SchedulerException,InterruptedException{
        //1.创建调度器
        SchedulerFactory schedulerFactory=new StdSchedulerFactory();
        Scheduler scheduler=schedulerFactory.getScheduler();
        //2.创建JobDetail实例，并与SimpleJob绑定
        JobDetail jobDetail=JobBuilder.newJob(SimpleJob.class)
                .withIdentity("job-1","job-group")
                .build();
        //3.构建触发器
        CronTrigger cronTrigger=TriggerBuilder.newTrigger()
                .withIdentity("trigger-1","trigger-group")
                .startNow()
                //cron表达式
                //每天13点到14点每5分钟运行一次，开始于13:00，结束于13:55
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 13 * * ?"))
                .build();
        //4.执行
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();
        //休眠，决定调度器执行时间
        TimeUnit.SECONDS.sleep(30);
        scheduler.shutdown();
    }
}
