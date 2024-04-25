package com.cfg.idgen.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 常用工具方法
 */
public class CommonUtils {

    /**
     *@Description: 返回时间戳+位随机数
     *@Author: huawd
     *@Date: 2022/9/14
     */
    public static String getRandomNo(){
        String times = String.valueOf(System.currentTimeMillis());
        // cfg修改random安全漏洞
        SecureRandom secureRandom=new SecureRandom();
        String randNo = times+(int)( secureRandom.nextDouble()*10);
        return randNo;
    }


    /**
     * 获取唯一uuid
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @Description 首字母大写
     * @author chenfg
     * @Date 2022/8/24 18:04
     * @param str
     * @return java.lang.String
     **/
    public static String firsToUpper(String str){
        return  str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * @Description 首字母转小写
     * @author chenfg
     * @Date 2022/8/24 18:04
     * @param str
     * @return java.lang.String
     **/
    public static String firstToLower(String str){
        return  str.substring(0, 1).toLowerCase() + str.substring(1);
    }


    /**
     * 首字母转大写
     * @author：zhangwbh
     * @date：2022/10/28 16:20
     * @param：
     * @return：
    */
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}