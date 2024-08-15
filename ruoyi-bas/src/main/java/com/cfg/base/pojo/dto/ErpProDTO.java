package com.cfg.base.pojo.dto;

import com.alibaba.fastjson.JSON;
import com.cfg.base.domain.ErpProColor;
import com.cfg.base.domain.ErpProPrice;
import com.cfg.base.domain.ErpProProcess;
import com.cfg.base.domain.ErpProSize;
import com.ruoyi.common.core.domain.BaseAudit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 服装产品管理 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProDTO extends BaseAudit {
    private Long id;
    private String proCode;
    private String proName;
    private String proDesc;
    private String proType;
    private String proMemo;
    private String status;
    private Long empId;
    private Long seqNo;
    private Integer delFlag;
    @ApiModelProperty("颜色列表")
    private List<ErpProColor> colorList;
    @ApiModelProperty("尺码列表")
    private List<ErpProSize> sizeList;
    @ApiModelProperty("工序列表")
    private List<ErpProProcess> procList;
    @ApiModelProperty("工价列表")
    private List<ErpProPrice> priceList;


    public static String getProductJson(){
        ErpProDTO erpProDTO = new ErpProDTO();
        erpProDTO.setId(1L);
        erpProDTO.setId(1L);
        erpProDTO.setProCode("123");
        erpProDTO.setProName("儿童校服");
        erpProDTO.setProDesc("冰丝，通气，舒适");
        erpProDTO.setProType("1");
        erpProDTO.setEmpId(1L);
        erpProDTO.setSeqNo(1L);
        erpProDTO.setDelFlag(1);
        erpProDTO.setProMemo("备注信息");
        erpProDTO.setStatus("1");
        erpProDTO.setCreateBy(3L);
        erpProDTO.setCreateTime(LocalDateTime.now());
        erpProDTO.setUpdateBy(4L);
        erpProDTO.setUpdateTime(LocalDateTime.now());

        List<ErpProColor> colors = new ArrayList<>();
        List<ErpProSize> sizes = new ArrayList<>();
        List<ErpProProcess> proProcesses = new ArrayList<>();
        List<ErpProPrice> prices = new ArrayList<>();
        ErpProColor erpProColor = new ErpProColor();
        erpProColor.setId(1L);
        erpProColor.setColorCode("yellow");
        erpProColor.setColorName("黄");
        erpProColor.setProId(1L);
        erpProColor.setSeqno(1L);
        erpProColor.setDelFlag(1);
        erpProColor.setEmpId(1L);
        erpProColor.setCreateBy(3L);
        erpProColor.setCreateTime(LocalDateTime.now());
        erpProColor.setUpdateBy(4L);
        erpProColor.setUpdateTime(LocalDateTime.now());
        colors.add(erpProColor);
        erpProDTO.setColorList(colors);
        //尺码
        ErpProSize erpSize = new ErpProSize();
        erpSize.setId(1L);
        erpSize.setSizeCode("x");
        erpSize.setSizeName("小");
        erpSize.setProId(1L);
        erpSize.setSeqNo(1L);
        erpSize.setDelFlag(1);
        erpSize.setEmpId(1L);
        erpSize.setCreateBy(3L);
        erpSize.setCreateTime(LocalDateTime.now());
        erpSize.setUpdateBy(4L);
        erpSize.setUpdateTime(LocalDateTime.now());
        sizes.add(erpSize);
        erpProDTO.setSizeList(sizes);
        //工序
        ErpProProcess erpProProcess = new ErpProProcess();
        erpProProcess.setId(1L);
        erpProProcess.setStepName("拉链");
        erpProProcess.setStepCode("01");
        erpProProcess.setPrice(new BigDecimal("0.005"));
        erpProProcess.setProId(1L);
        erpProProcess.setSeqNo(1L);
        erpProProcess.setDelFlag(1);
        erpProProcess.setEmpId(1L);
        erpProProcess.setCreateBy(3L);
        erpProProcess.setCreateTime(LocalDateTime.now());
        erpProProcess.setUpdateBy(4L);
        erpProProcess.setUpdateTime(LocalDateTime.now());
        proProcesses.add(erpProProcess);
        erpProDTO.setProcList(proProcesses);
        //ErpProPrice
        ErpProPrice erpPrice = new ErpProPrice();
        erpPrice.setId(1L);
        erpPrice.setSizeId(1L);
        erpPrice.setStepId(1L);
        erpPrice.setPrice(new BigDecimal("0.005"));
        erpPrice.setProId(1L);
        erpPrice.setDelFlag(1);
        erpPrice.setEmpId(1L);
        erpPrice.setCreateBy(3L);
        erpPrice.setCreateTime(LocalDateTime.now());
        erpPrice.setUpdateBy(4L);
        erpPrice.setUpdateTime(LocalDateTime.now());
        prices.add(erpPrice);
        erpProDTO.setPriceList(prices);
        String proJson = JSON.toJSONString(erpProDTO);
        return proJson;
    }

    public static void main(String[] args) {
          String proJson =  getProductJson();
        System.out.println(proJson);
    }
}
