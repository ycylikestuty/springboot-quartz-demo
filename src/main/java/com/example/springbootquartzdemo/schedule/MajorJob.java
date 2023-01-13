package com.example.springbootquartzdemo.schedule;

import com.example.springbootquartzdemo.entity.Person;
import com.example.springbootquartzdemo.mapper.PersonMapper;
import com.example.springbootquartzdemo.utils.SpringContextJobUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author ycy
 */
public class MajorJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobName = dataMap.getString("jobName");

        //打印数据库的数据
        PersonMapper personMapper = (PersonMapper) SpringContextJobUtil.getBean("personMapper");
        List<Person> personList = personMapper.queryList();
        System.out.println(Thread.currentThread().getName() + "--"
                + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()) + "--"
                + jobName + "--" + personList);
    }
}
