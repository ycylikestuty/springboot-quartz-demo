package com.example.springbootquartzdemo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author ycy
 */
@Component
public class SpringContextJobUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    @SuppressWarnings("static-access")
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }
    /**
     * 根据name获取bean
     * @param beanName name
     * @return bean对象
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}
