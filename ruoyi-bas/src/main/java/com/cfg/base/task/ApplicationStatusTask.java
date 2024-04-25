package com.cfg.base.task;

import com.cfg.idgen.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ApplicationStatusTask
 * @Description 应用存活状态注册任务
 * @Author chenfg
 * @Date 2023/4/18 14:25
 */
@Slf4j
@Component
public class ApplicationStatusTask implements Runnable {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    public static final String APPLICATION_KEY = "wms:application";

    @Value("${spring.application.name}")
    private String applicationName;

    public static String appId = CommonUtils.getUUID();

    public static String appKey;
    public static Long startTime;

    public static Long expireTimeSeconds = 3L;

    private static boolean closeFlag = false;

    @Override
    public void run() {
        setTaskInfo(applicationName);
        while (!closeFlag || Thread.interrupted()) {
            stringRedisTemplate.opsForValue().set(appKey, appId, expireTimeSeconds, TimeUnit.SECONDS);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error("Interrupted!", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void closeTask() {
        closeFlag = true;
    }

    public String getApplicationKey() {
        return APPLICATION_KEY;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public static String getAppId() {
        return appId;
    }

    public static String getAppKey() {
        return appKey;
    }


    public static Long getStartTime() {
        return startTime;
    }

    public static void setTaskInfo(String applicationName){
        if (StringUtils.isBlank(appKey)) {
            ApplicationStatusTask.startTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            ApplicationStatusTask.appKey = APPLICATION_KEY + ":" + applicationName + ":" + startTime;
        }
    }

}
