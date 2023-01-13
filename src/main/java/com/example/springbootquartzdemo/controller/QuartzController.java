package com.example.springbootquartzdemo.controller;

import com.example.springbootquartzdemo.schedule.MajorJob;
import com.example.springbootquartzdemo.utils.QuartzUtils;
import org.quartz.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author ycy
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController {
    private static String jobName = "jobName-1";
    private static String triggerName = "triggerName-1";
    private static String jobGroupName = "job-group";
    private static String triggerGroupName = "trigger-group";

    /**
     * 启动触发器
     *
     * @return string
     */
    @GetMapping(path = "/trigger/start")
    public String startJob() {
        // 构建定时任务
        JobDetail jobDetail = JobBuilder.newJob(MajorJob.class)
                .withIdentity(jobName, jobGroupName)
                .usingJobData("jobName", "QuartzDemo")
                .build();

        Date start = new Date();
        start.setTime(start.getTime() + 10000);
        Date end = new Date();
        end.setTime(end.getTime() + 90000);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, triggerGroupName)
                .startNow()
                //结束时间
                .endAt(end)
                .withSchedule(SimpleScheduleBuilder.
                        repeatSecondlyForTotalCount(10, 10))
                .build();
        QuartzUtils.startJob(jobDetail, trigger);
        return "触发器启动成功";
    }

    /**
     * 暂停触发器
     *
     * @return string
     */
    @GetMapping(path = "/trigger/pause")
    public String pauseTrigger() {
        QuartzUtils.pauseTrigger(triggerName, triggerGroupName);
        return "触发器暂停成功";
    }

    /**
     * 重启触发器
     *
     * @return string
     */
    @GetMapping(path = "/trigger/resume")
    public String resumeTrigger() {
        QuartzUtils.resumeTrigger(triggerName, triggerGroupName);
        return "触发器重启成功";
    }

    /**
     * 停止触发器
     *
     * @return string
     */
    @GetMapping(path = "/trigger/shutdown")
    public String shutdown() {
        QuartzUtils.removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
        return "触发器停止成功";
    }


    /**
     * 查询触发器是否存在
     *
     * @return boolean
     * @throws SchedulerException e
     */
    @GetMapping(path = "/trigger/exists")
    public boolean checkTriggerExists() throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        return QuartzUtils.checkExists(triggerKey);
    }

    /**
     * 暂停job
     *
     * @return string
     */
    @GetMapping(path = "/job/pause")
    public String pauseJob() {
        QuartzUtils.pauseJob(jobName, jobGroupName);
        return "Job暂停成功";
    }

    /**
     * 重启job
     *
     * @return string
     */
    @GetMapping(path = "/job/resume")
    public String resumeJob() {
        QuartzUtils.resumeJob(jobName, jobGroupName);
        return "Job重启成功";
    }

    /**
     * 查询job是否存在
     *
     * @return boolean
     * @throws SchedulerException e
     */
    @GetMapping(path = "/job/exists")
    public boolean checkJobExists() throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        return QuartzUtils.checkExists(jobKey);
    }
}
