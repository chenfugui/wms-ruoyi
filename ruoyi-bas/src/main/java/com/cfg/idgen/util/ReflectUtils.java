package com.cfg.idgen.util;

import org.springframework.util.ReflectionUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenfg
 * @Description 反射工具类
 * @Date 2022/8/4 16:01
 **/
public class ReflectUtils {

    /**
     * 查找class字段
     *
     * @param targetClass 目标class
     * @param fieldName   字段名称
     * @return field
     */
    public static Field getClassField(Class<?> targetClass, String fieldName) {
        Field field = ReflectionUtils.findField(targetClass, fieldName);
        if (null != field) {
            ReflectionUtils.makeAccessible(field);
        }
        return field;
    }

    /**
     * 获取字段Value
     *
     * @param field        字段
     * @param targetObject 目标对象
     * @return Object
     */
    public static Object getFieldValue(Field field, Object targetObject) {
        return ReflectionUtils.getField(field, targetObject);
    }

    /**
     * @param targetObject 对象
     * @param fieldName    属性名
     * @return Object
     */
    public static Object getFieldValue(@NotNull Object targetObject, @NotNull String fieldName) {
        Field field = getClassField(targetObject.getClass(), fieldName);
        return getFieldValue(field, targetObject);
    }

    /**
     * 获取class所有field字段
     *
     * @param targetClass class
     * @return Field[]
     */
    public static Field[] getClassField(Class<?> targetClass) {
        Field[] fields = targetClass.getDeclaredFields();
        for (Field field : fields) {
            ReflectionUtils.makeAccessible(field);
        }
        return fields;
    }

    /**
     * 获取对象属性map
     *
     * @param obj 对象
     * @return Map<String, Object>
     */
    public static Map<String, Object> getObjectPropertyMap(@NotNull Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, Object> propertyMap = new HashMap<>();
        if (fields.length > 0) {
            for (Field field : fields) {
                ReflectionUtils.makeAccessible(field);
                Object value = ReflectionUtils.getField(field, obj);
                propertyMap.put(field.getName(), value);
            }
        }
        return propertyMap;
    }

    /**
     * 获取对象属性map
     *
     * @param obj 对象
     * @return Map<String, Object>
     */
    public static Map<String, Object> getPropertyMap(@NotNull Object obj) {
        Method[] methods = obj.getClass().getDeclaredMethods();
        Map<String, Object> propertyMap = new HashMap<>();
        if (methods.length > 0) {
            for (Method method : methods) {
                if (method.getName().startsWith("get") && method.getParameterTypes().length < 1) {
                    String key = firstToLowerCase(method.getName().substring(3));
                    try {
                        Object value = method.invoke(obj);
                        propertyMap.put(key, value);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return propertyMap;
    }

    /**
     * 设置对象属性
     *
     * @param obj 对象
     * @return T
     */
    public static <T> T setObjectProperty(@NotNull T obj, @NotNull Map<String, Object> propertyMap, boolean includeNull) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, Object> tmpMap = new HashMap<>();
        for (Map.Entry<String, Object> stringObjectEntry : propertyMap.entrySet()) {
            tmpMap.put(stringObjectEntry.getKey().toLowerCase(), stringObjectEntry.getValue());
        }
        if (fields.length > 0) {
            for (Field field : fields) {
                ReflectionUtils.makeAccessible(field);
                String fieldName = field.getName().toLowerCase();
                if (tmpMap.containsKey(fieldName)) {
                    if (!includeNull) {
                        if (null != tmpMap.get(fieldName)) {
                            ReflectionUtils.setField(field, obj, tmpMap.get(fieldName));
                        }
                    } else {
                        ReflectionUtils.setField(field, obj, tmpMap.get(fieldName));
                    }
                }
            }
        }
        return obj;
    }

    /**
     * copy对象相同属性
     *
     * @param sourceObj   源对象
     * @param targetObj   目标对象
     * @param includeNull 是否包含null值的属性
     * @return T
     */
    public static <T> T copyPropertyValue(@NotNull Object sourceObj, @NotNull T targetObj, boolean includeNull) {
        Map<String, Object> sourcePropertyMap = getObjectPropertyMap(sourceObj);
        sourcePropertyMap.remove("serialVersionUID");
        setObjectProperty(targetObj, sourcePropertyMap, includeNull);
        return targetObj;
    }


    /**
     * @param str
     * @return java.lang.String
     * @Description 首字母转大写
     * @author chenfg
     * @Date 2022/8/4 17:05
     **/
    public static String firstToUpper(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * @param str
     * @return java.lang.String
     * @Description 首字母大写
     * @author chenfg
     * @Date 2022/4/8 10:58
     **/
    public static String firstToUpperCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * @param str
     * @return java.lang.String
     * @Description 首字母小写
     * @author chenfg
     * @Date 2022/4/8 10:58
     **/
    public static String firstToLowerCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

}
