package com.cfg.base.pojo.query;

import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服装生产批次 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="服装生产批次 查询 对象")
@Data
public class ErpProMakeBatchQuery {
    @ApiModelProperty("产品生产id 精确匹配")
    private Long proMakeId;

    @ApiModelProperty("产品生产编码 精确匹配")
    private String proMakeNo;

    @ApiModelProperty("产品ID 精确匹配")
    private Long proId;

    @ApiModelProperty("尺码id 精确匹配")
    private Long sizeId;

    @ApiModelProperty("批次编码 精确匹配")
    private String batchNo;

    @ApiModelProperty("尺码名称 精确匹配")
    private String sizeNameLike;

    @ApiModelProperty("尺码编码 精确匹配")
    private String sizeCode;

    @ApiModelProperty("颜色id 精确匹配")
    private Long colorId;

    @ApiModelProperty("颜色编码 精确匹配")
    private String colorCode;

    @ApiModelProperty("颜色名称 精确匹配")
    private String colorNameLike;

    @ApiModelProperty("生产数量 精确匹配")
    private Long makeNum;

    @ApiModelProperty("床次 精确匹配")
    private String bedNo;

    @ApiModelProperty("开始扎号 精确匹配")
    private Long pkgStartNo;

    @ApiModelProperty("结束扎号 精确匹配")
    private Long pkgEndNo;

    @ApiModelProperty("单位id 精确匹配")
    private Long empid;

    @ApiModelProperty("创建人 精确匹配")
    private Long creater;

    @ApiModelProperty("更新人 精确匹配")
    private Long updater;

    @ApiModelProperty("dr 1 正常  2  删除 精确匹配")
    private Integer dr;

    @ApiModelProperty("顺序号 精确匹配")
    private Long seqno;

}
