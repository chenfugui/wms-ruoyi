package com.cfg.base.dto;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cfg.base.pojo.dto.ErpProMakeBatchDTO;
import com.cfg.base.pojo.dto.ErpProMakeDetailDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 服装生产管理对象 erp_pro_make
 * 
 * @author chenfg
 */
@ApiModel(description="服装生产管理对象")
@Data
public class ProMakeDTO extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("产品id")
    @Excel(name = "产品id")
    private Long proId;

    @ApiModelProperty("产品生产编码")
    @Excel(name = "产品生产编码")
    private String proMakeNo;

    @ApiModelProperty("生产开始时间")
    @Excel(name = "生产开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime makeStartTime;

    @ApiModelProperty("生产结束时间")
    @Excel(name = "生产结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime makeEndTime;

    @ApiModelProperty("交付时间")
    @Excel(name = "交付时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime deliverTime;

    @ApiModelProperty("生产状态: 1 正常  2 停止 3 作废")
    @Excel(name = "生产状态: 1 正常  2 停止 3 作废")
    private String status="1";

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long empId;

    @ApiModelProperty("顺序号")
    @Excel(name = "顺序号")
    private Long seqNo;

    @ApiModelProperty("delFlag 0 正常  1  删除")
    @Excel(name = "delFlag 0 正常  1  删除")
    private Integer delFlag=0;

    @ApiModelProperty("打菲记录")
    private List<ProMakeBatchDTO> makeBatchList;

    @ApiModelProperty("生产明细")
    private List<ProMakeDetailDTO> makeDetailList;


    public static void main(String[] args) {
        ProMakeDTO proMakeDTO = new ProMakeDTO();
        proMakeDTO.setProId(1L);
        proMakeDTO.setId(1L);
        proMakeDTO.setProMakeNo("1");
        proMakeDTO.setMakeStartTime(LocalDateTime.now());
        proMakeDTO.setMakeEndTime(LocalDateTime.now());
        proMakeDTO.setDeliverTime(LocalDateTime.now());
        proMakeDTO.setStatus("1");
        proMakeDTO.setEmpId(1L);
        proMakeDTO.setSeqNo(1L);
        proMakeDTO.setCreateBy(1L);
        proMakeDTO.setCreateTime(LocalDateTime.now());
        proMakeDTO.setUpdateBy(1L);
        proMakeDTO.setUpdateTime(LocalDateTime.now());
        proMakeDTO.setDelFlag(0);

        //生产明细
        List<ProMakeDetailDTO> detail=new ArrayList<>();
        ProMakeDetailDTO makeDetailDTO =new ProMakeDetailDTO();
        makeDetailDTO.setId(1L);
        makeDetailDTO.setProMakeId(proMakeDTO.getId());
        makeDetailDTO.setProMakeNo(proMakeDTO.getProMakeNo());
        makeDetailDTO.setProId(1L);
        makeDetailDTO.setMakeNum(1L);
        makeDetailDTO.setClothId(1L);
        makeDetailDTO.setClothCode("1");
        makeDetailDTO.setClothName("棉");
        makeDetailDTO.setEmpId(1L);
        makeDetailDTO.setColorId(1L);
        makeDetailDTO.setSizeId(1L);
        makeDetailDTO.setDelFlag(0);
        makeDetailDTO.setCreateBy(1L);
        makeDetailDTO.setCreateTime(LocalDateTime.now());
        makeDetailDTO.setUpdateBy(1L);
        makeDetailDTO.setUpdateTime(LocalDateTime.now());
        detail.add(makeDetailDTO);

        //打菲记录
        List<ProMakeBatchDTO> batch=new ArrayList<>();
        ProMakeBatchDTO batchDTO =new ProMakeBatchDTO();
        batchDTO.setId(1L);
        batchDTO.setProMakeId(proMakeDTO.getId());
        batchDTO.setProMakeNo(proMakeDTO.getProMakeNo());
        batchDTO.setProId(1L);
        batchDTO.setMakeNum(1L);
        batchDTO.setBatchNo("123123");
        batchDTO.setBedNo("1");
        batchDTO.setColorCode("黄");
        batchDTO.setColorName("黄");
        batchDTO.setSizeCode("x");
        batchDTO.setSizeName("x");
        batchDTO.setPkgStartNo(1L);
        batchDTO.setPkgEndNo(9L);
        batchDTO.setSeqNo(1L);
        batchDTO.setEmpId(1L);
        batchDTO.setColorId(1L);
        batchDTO.setSizeId(1L);
        batchDTO.setDelFlag(0);
        batchDTO.setCreateBy(1L);
        batchDTO.setCreateTime(LocalDateTime.now());
        batchDTO.setUpdateBy(1L);
        batchDTO.setUpdateTime(LocalDateTime.now());
        batch.add(batchDTO);

        proMakeDTO.setMakeBatchList(batch);
        proMakeDTO.setMakeDetailList(detail);

        String makeJson = JSONObject.toJSONString(proMakeDTO, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(makeJson);


    }

}
