package com.cfg.idgen.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2022 YonYou. All Rights Reserved.
 * 转换工具类
 *
 * @author Jin.Li lijin1@yonyou.com Create on 2022/4/12 4:00 下午
 * @version 1.0
 */
public class ConvertUtils {

    private ConvertUtils() {
    }

    /**
     * 单体转换
     *
     * @param oldBean
     * @param newClazz
     * @param <T>
     * @return
     */
    public static <T> T convert(Object oldBean, Class<T> newClazz) {
        if (oldBean == null) {
            return null;
        }
        if (newClazz == null) {
            return null;
        }
        T newBean = null;
        try {
            newBean = newClazz.newInstance();
            BeanUtils.copyProperties(oldBean, newBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newBean;
    }

    /**
     * 集合转换
     *
     * @param oldList
     * @param newClazz
     * @param <T>
     * @return
     */
    public static <T> List<T> convert(List oldList, Class<T> newClazz) {
        if (oldList == null) {
            return null;
        }
        if (newClazz == null) {
            return null;
        }
        List<T> newList = new ArrayList<>();
        for (Object oldBean : oldList) {
            T newBean = convert(oldBean, newClazz);
            if (newBean != null) {
                newList.add(newBean);
            }
        }
        return newList;
    }

    /**
     * @param sourceBean
     * @param targetBean
     * @return T
     * @Description
     * @author chenfg
     * @Date 2022/8/4 15:52
     **/
    public static <T> T copyProperties(Object sourceBean, T targetBean) {
        if (sourceBean == null) {
            return null;
        }
        if (targetBean == null) {
            return null;
        }
        BeanUtils.copyProperties(sourceBean, targetBean);
        return targetBean;
    }

    /**
     * @Description 类型转换json
     * @author chenfg
     * @Date 2023/10/19 19:28
     * @param oldList
     * @param newClazz
     * @return java.util.List<T>
     **/
    public static <T> List<T> jsonConvert(List oldList, Class<T> newClazz) {
        if (oldList == null) {
            return null;
        }
        if (newClazz == null) {
            return null;
        }
        List<T> newList = new ArrayList<>();
        for (Object oldBean : oldList) {
            String jsonObj = JSON.toJSONString(oldBean);
            T newBean =JSON.parseObject(jsonObj,newClazz);
            if (newBean != null) {
                newList.add(newBean);
            }
        }
        return newList;
    }
}
