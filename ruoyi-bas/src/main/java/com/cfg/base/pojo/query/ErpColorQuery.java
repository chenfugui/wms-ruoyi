package com.cfg.base.pojo.query;

import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服装颜色管理 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="服装颜色管理 查询 对象")
@Data
public class ErpColorQuery {
    @ApiModelProperty("颜色编码 精确匹配")
    private String colorCode;

    @ApiModelProperty("颜色名称 精确匹配")
    private String colorNameLike;

    @ApiModelProperty("单位id 精确匹配")
    private Long empId;

    @ApiModelProperty("顺序号 精确匹配")
    private Long seqno;

    @ApiModelProperty("创建人 精确匹配")
    private Long creater;

    @ApiModelProperty("更新人 精确匹配")
    private Long updater;

    @ApiModelProperty("dr 1 正常  2  删除 精确匹配")
    private Integer dr;

}
