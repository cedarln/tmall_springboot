package com.linan.tmall.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.swing.*;

//为了触发aop，因为springboot缓存机制是通过aop实现的
@Component
public class SpringContextUtil implements ApplicationContextAware{
    private static ApplicationContext applicationContext;
    private SpringContextUtil(){
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
