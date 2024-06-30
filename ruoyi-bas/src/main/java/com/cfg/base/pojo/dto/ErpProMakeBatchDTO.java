package com.cfg.base.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;
/**
 * 服装生产批次 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProMakeBatchDTO {
    private Long id;
    private Long proMakeId;
    private String proMakeNo;
    private Long proId;
    private Long sizeId;
    private String batchNo;
    private String sizeName;
    private String sizeCode;
    private Long colorId;
    private String colorCode;
    private String colorName;
    private Long makeNum;
    private String bedNo;
    private Long pkgStartNo;
    private Long pkgEndNo;
    private Long empid;
    private Long creater;
    private LocalDateTime createTime;
    private Long updater;
    private LocalDateTime updateTime;
    private Integer dr;
    private Long seqno;
}
