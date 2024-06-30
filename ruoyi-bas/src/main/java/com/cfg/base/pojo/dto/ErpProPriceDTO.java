package com.cfg.base.pojo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
/**
 * 服装工价信息 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProPriceDTO {
    private Long id;
    private Long proid;
    private Long stepid;
    private Long sizeid;
    private Long empId;
    private BigDecimal price;
    private Long creater;
    private LocalDateTime createTime;
    private Long updater;
    private LocalDateTime updateTime;
    private Integer dr;
}
