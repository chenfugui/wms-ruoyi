package com.cfg.base.dto;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 服装生产批次对象 erp_pro_make_batch
 * 
 * @author chenfg
 */
@ApiModel(description="服装生产批次对象")
@Data
public class ProMakeBatchDTO extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("产品生产id")
    @Excel(name = "产品生产id")
    private Long proMakeId;

    @ApiModelProperty("产品生产编码")
    @Excel(name = "产品生产编码")
    private String proMakeNo;

    @ApiModelProperty("产品ID")
    @Excel(name = "产品ID")
    private Long proId;

    @ApiModelProperty("尺码id")
    @Excel(name = "尺码id")
    private Long sizeId;

    @ApiModelProperty("批次编码(同一个产品生产编码，批次号唯一)")
    @Excel(name = "批次编码(同一个产品生产编码，批次号唯一)")
    private String batchNo;

    @ApiModelProperty("尺码名称")
    @Excel(name = "尺码名称")
    private String sizeName;

    @ApiModelProperty("尺码编码")
    @Excel(name = "尺码编码")
    private String sizeCode;

    @ApiModelProperty("颜色id")
    @Excel(name = "颜色id")
    private Long colorId;

    @ApiModelProperty("颜色编码")
    @Excel(name = "颜色编码")
    private String colorCode;

    @ApiModelProperty("颜色名称")
    @Excel(name = "颜色名称")
    private String colorName;

    @ApiModelProperty("生产数量")
    @Excel(name = "生产数量")
    private Long makeNum=0L;

    @ApiModelProperty("床次")
    @Excel(name = "床次")
    private String bedNo;

    @ApiModelProperty("开始扎号")
    @Excel(name = "开始扎号")
    private Long pkgStartNo;

    @ApiModelProperty("结束扎号")
    @Excel(name = "结束扎号")
    private Long pkgEndNo;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long empId;

    @ApiModelProperty("delFlag 0 正常  1  删除")
    @Excel(name = "delFlag 0 正常  1  删除")
    private Integer delFlag=0;

    @ApiModelProperty("顺序号")
    @Excel(name = "顺序号")
    private Long seqNo;

    public static void main(String[] args) {
        ProMakeBatchDTO proMakeBatch = new ProMakeBatchDTO();
        proMakeBatch.setId(1L);
        proMakeBatch.setProMakeId(1L);
        proMakeBatch.setProMakeNo("1");
        proMakeBatch.setProId(1L);
        proMakeBatch.setSizeId(1L);
        proMakeBatch.setBatchNo("1");
        proMakeBatch.setBatchNo("1");
        proMakeBatch.setSizeName("1");
        proMakeBatch.setSizeCode("1");
        proMakeBatch.setColorId(1L);
        proMakeBatch.setColorCode("1");
        proMakeBatch.setColorName("1");
        proMakeBatch.setMakeNum(1L);
        proMakeBatch.setBedNo("1");
        proMakeBatch.setPkgStartNo(1L);
        proMakeBatch.setPkgEndNo(1L);
        proMakeBatch.setEmpId(1L);
        proMakeBatch.setSeqNo(1L);
        System.out.println(JSON.toJSONString(proMakeBatch));
    }
}
