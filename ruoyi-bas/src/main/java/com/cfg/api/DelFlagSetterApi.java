package com.cfg.api;

/**
 * @ClassName : DelFlagSetterApi
 * @Description : 删除标志
 * @Author : chenfg
 * @Date: 2024-07-03 10:21
 */
public interface DelFlagSetterApi {

    /***
     * @author chenfg
     * @date: 2024/7/3 10:23
     * @description:  设置删除标志
     * @param flag
     * @return: void
     */
    void setDelFlag(Integer flag);
    /***
     * @author chenfg
     * @date: 2024/7/3 10:23
     * @description:  获取删除标志
     * @param
     * @return: int
     */
    int getDelFlag();
}
