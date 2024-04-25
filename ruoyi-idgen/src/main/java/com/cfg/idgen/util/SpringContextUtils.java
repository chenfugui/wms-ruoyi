package com.cfg.idgen.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Copyright © 2023 YonYou. All Rights Reserved.
 *
 * @author Jin.Li lijin1@yonyou.com Create on 2023/9/13 10:53
 * @version 1.0
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtils.applicationContext == null) {
            SpringContextUtils.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取Bean
     * @param name
     * @return
     */
    public static <T> T getBean(String name) {
        return (T) getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name，以及Clazz返回指定的Bean
     * @param name
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * Get a property from the current environment.
     *
     * @param key Property key
     * @return the property value or null if not found
     */
    public static String getProperty(String key) {
        Environment environment = getApplicationContext().getEnvironment();
        return environment.getProperty(key);
    }

    /**
     * Get a property from the current environment with a default value.
     *
     * @param key Property key
     * @param defaultValue default value to return if key is not present
     * @return the property value or defaultValue if not found
     */
    public static String getProperty(String key, String defaultValue) {
        Environment environment = getApplicationContext().getEnvironment();
        return environment.getProperty(key, defaultValue);
    }
}
