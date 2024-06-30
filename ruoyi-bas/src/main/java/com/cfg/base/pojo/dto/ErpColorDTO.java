package com.cfg.base.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;
/**
 * 服装颜色管理 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpColorDTO {
    private Long id;
    private String colorCode;
    private String colorName;
    private Long empId;
    private Long seqno;
    private Long creater;
    private LocalDateTime createTime;
    private Long updater;
    private LocalDateTime updateTime;
    private Integer dr;
}
