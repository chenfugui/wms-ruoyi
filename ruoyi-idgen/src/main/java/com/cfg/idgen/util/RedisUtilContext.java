package com.cfg.idgen.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisLockContext
 * @Description 缓存上下文
 * @Author chenfg
 * @Date 2023/3/6 17:23
 */
@Data
@Slf4j
@Component
public class RedisUtilContext {

    @Autowired
    private  RedisTemplate<String, String> stringRedisTemplate;;

    @Autowired
    private  RedisTemplate<String, Long> longRedisTemplate;

    /**
     * 获取锁超时时间 单位秒
     */
    public static final int LOCK_TIMEOUT = 10;

    public static final String LOCK_FLAG = ":lock";

    /**
     * @param key      缓存主键
     * @param value    值
     * @param expire   过期时间
     * @param timeUnit 时间单位
     * @return boolean
     * @Description key不存在设置key
     * @author chenfg
     * @Date 2023/3/6 17:56
     **/
    public boolean setKeyIfAbsent(String key, String value, int expire, TimeUnit timeUnit) {
        boolean setFlag = stringRedisTemplate.opsForValue().setIfAbsent(key, value);
        if (setFlag) {
            stringRedisTemplate.expire(key, expire, timeUnit);
        }
        return setFlag;
    }

    public boolean existKey(String key) {
        boolean flag = stringRedisTemplate.hasKey(key);
        System.out.println(key + "==========" + flag);
        String value = stringRedisTemplate.opsForValue().get(key);
        System.out.println(key + "==========" + value);
        return Boolean.TRUE.equals(flag);
    }

    /**
     * @param key       idkey
     * @param initValue 自增初始值
     * @return java.lang.Long
     * @Description 设置段起始值
     * @author chenfg
     * @Date 2023/4/17 17:35
     **/
    public Long setIdInitValue(String key, Long initValue) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, stringRedisTemplate.getConnectionFactory());
        entityIdCounter.set(initValue);
        Long increment = entityIdCounter.incrementAndGet();
        System.out.println(increment);
        return initValue;
    }

    /**
     * @param key       idkey
     * @param initValue 自增初始值
     * @return java.lang.Long
     * @Description 设置段起始值
     * @author chenfg
     * @Date 2023/4/17 17:35
     **/
    public Long setIdInitValue(String key, Long initValue, Long expireMilliSeconds) {
        String requestId = CommonUtils.getUUID();
        String lock = key + LOCK_FLAG;
        lock(lock, requestId, expireMilliSeconds, LOCK_TIMEOUT);
        if (Boolean.FALSE.equals(longRedisTemplate.hasKey(key))) {
            longRedisTemplate.opsForValue().set(key, initValue, expireMilliSeconds, TimeUnit.MILLISECONDS);
        }
        unLock(lock, requestId);
        return initValue;
    }

    /**
     * @param key idkey
     * @return java.lang.Long
     * @Description key值自增1，如果key不存在返回null
     * @author chenfg
     * @Date 2023/4/17 17:35
     **/
    public Long getIncr(String key) {
        return (Long) longRedisTemplate.execute(getIncrScript(), Arrays.asList(key), null);
    }

    /**
     * @param key          idkey
     * @param conditionKey 条件Key
     * @return java.lang.Long
     * @Description key值自增1，如果key不存在返回null
     * @author chenfg
     * @Date 2023/4/17 17:35
     **/
    public Long getIncr(String key, String conditionKey) {
        return (Long) longRedisTemplate.execute(getIncrScriptWithCondition(), Arrays.asList(key), conditionKey);
    }

    /**
     * @param key redis缓存
     * @return boolean
     * @Description 删除redis缓存
     * @author chenfg
     * @Date 2023/3/6 17:55
     **/
    public boolean deleteKey(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * @param key
     * @return java.util.Set<java.lang.Integer>
     * @Description 获取Set数据
     * @author chenfg
     * @Date 2023/3/6 18:03
     **/
    public Set<String> getSet(String key) {
        Set<String> dataSet = stringRedisTemplate.opsForSet().members(key);
        return dataSet;
    }

    /**
     * @param key        键
     * @param requestId  请求id
     * @param expireTime 过期时间
     * @return boolean
     * @Description 获取锁
     * @author chenfg
     * @Date 2023/3/7 13:59
     **/
    public boolean tryLock(String key, String requestId, long expireTime) {
        int result = (int) longRedisTemplate.execute(getLockScript(), Arrays.asList(key), requestId, expireTime);
        if (1 == result) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param key        键
     * @param requestId  请求id
     * @param expireTime 过期时间秒
     * @param timeout    超时时间秒
     * @return boolean
     * @Description 获取锁
     * @author chenfg
     * @Date 2023/3/7 13:59
     **/
    public boolean lock(String key, String requestId, long expireTime, int timeout) {
        boolean lockFlag = true;
        Instant begin = Instant.now();
        Instant end = null;
        while (true) {
            Long result = (Long) longRedisTemplate.execute(getLockScript(), Arrays.asList(key), requestId, expireTime + "");
            if (null != result && 1 == result) {
                break;
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exception) {
                    log.error(exception.getMessage(),exception);
                    Thread.currentThread().interrupt();
                }
                end = Instant.now();
                if (Duration.between(begin, end).getSeconds() >= timeout) {
                    throw new RuntimeException("获取锁超时");
                }
            }
        }
        return lockFlag;
    }

    /**
     * @param key        key
     * @param requestId  请求ID
     * @param expireTime 过期时间
     * @return boolean
     * @Description 获取key锁
     * @author chenfg
     * @Date 2023/4/19 14:39
     **/
    public boolean lock(String key, String requestId, long expireTime) {
        return lock(key, requestId, expireTime, LOCK_TIMEOUT);
    }

    /**
     * @param key       lockKey
     * @param requestId 请求ID
     * @return boolean
     * @Description 释放锁
     * @author chenfg
     * @Date 2023/3/7 14:38
     **/
    public boolean unLock(String key, String requestId) {
        String lockReqId = stringRedisTemplate.opsForValue().get(key);
        if (requestId.equals(lockReqId)) {
            return Boolean.TRUE.equals(stringRedisTemplate.delete(key));
        }
        return false;
    }

    /**
     * @return org.springframework.data.redis.core.script.RedisScript
     * @Description redis锁脚本
     * @author chenfg
     * @Date 2023/3/7 14:46
     **/
    private RedisScript getLockScript() {
        String lockScript = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then " +
                " redis.call('expire',KEYS[1],tonumber(ARGV[2])) return 1 else return 0 end ";
        return new DefaultRedisScript(lockScript, Long.class);
    }

    private RedisScript getIncrScript() {
        String lockScript = "if redis.call('exists',KEYS[1]) == 1 then " +
                " return redis.call('incr',KEYS[1]) else return nil end ";
        return new DefaultRedisScript(lockScript, Long.class);
    }

    private RedisScript getIncrScriptWithCondition() {
        String lockScript = "if redis.call('exists',KEYS[1]) == 1 and redis.call('exists',ARGV[1]) == 1 then " +
                " return redis.call('incr',KEYS[1]) else return nil end ";
        return new DefaultRedisScript(lockScript, Long.class);
    }

    /**
     * @param prefix key前缀
     * @return java.util.Set<java.lang.String>
     * @Description 获取所有前缀key
     * @author chenfg
     * @Date 2023/4/18 16:04
     **/
    public Set<String> getKeysByPrefix(String prefix) {
        //return stringRedisTemplate.keys(prefix);
        return stringRedisTemplate.keys(prefix);
    }
}
