package com.cfg.idgen.service;

/**
 * Copyright © 2023 YonYou. All Rights Reserved.
 *
 * @author Jin.Li lijin1@yonyou.com Create on 2023/5/25 15:51
 * @version 1.0
 */
public interface IdGenService {
    /**
     * @return
     */
    String getUuid();

    /**
     * @param bizType
     * @return
     */
    Long getSeqId(String bizType);

    /**
     * @param bizType
     * @return
     */
    String getFixedLSegId(String bizType);

    /**
     * @param bizType
     * @return
     */
    String getSegmentId(String bizType);

    /**
     * @param bizType
     * @return
     */
    String getFixedLSegIdNoneBizType(String bizType);

    /**
     * @param bizType
     * @return
     */
    String getSegIdNoneBizType(String bizType);

    /**
     * @return
     */
    Long getSnowId();
}
