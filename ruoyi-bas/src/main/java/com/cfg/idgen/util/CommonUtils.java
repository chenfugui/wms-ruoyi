package com.cfg.idgen.util;

import java.math.BigDecimal;
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

    public static BigDecimal getFinalScore(BigDecimal evalScore){
        if(null==evalScore){
            return evalScore;
        }
        if(evalScore.compareTo(BigDecimal.ZERO)<0){
            return new BigDecimal(0);
        }
        if(evalScore.compareTo(new BigDecimal(100))>0){
            return new BigDecimal(100);
        }
        return evalScore;
    }

    public static void main(String[] args) {
        BigDecimal score1 = new BigDecimal("1.00");
        BigDecimal score2 = new BigDecimal("-3.00");
        BigDecimal score3 = new BigDecimal("3.00");
        BigDecimal score4 = new BigDecimal("103.00");
        BigDecimal resData = score1.add(score2);
        System.out.println(score1+" + "+score2+"="+resData);
        resData = score1.subtract(score2);
        System.out.println(score1+" - "+score2+"="+resData);
        int scale = score2.divide(score1).intValue();
        resData = new BigDecimal(scale).multiply(score3);
        System.out.println("("+score2+" / "+score1+")*"+score3+"="+resData);
        System.out.println(score2+"> getFinal="+getFinalScore(score2));
        System.out.println(score1+"> getFinal="+getFinalScore(score1));
        System.out.println(score4+"> getFinal="+getFinalScore(score4));
    }
}