package com.cfg.base.dto;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : ErpEmpDicDTO
 * @Description : 字典信息DTO
 * @Author : chenfg
 * @Date: 2024-11-15 13:34
 */
@Data
public class ErpEmpDicDTO implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("类型编码")
    @Excel(name = "类型编码")
    private String typeCode;

    @ApiModelProperty("类型名称")
    @Excel(name = "类型名称")
    private String typeName;

    @ApiModelProperty("字典项编码")
    @Excel(name = "字典项编码")
    private String itemCode;

    @ApiModelProperty("字典项名称")
    @Excel(name = "字典项名称")
    private String itemName;

    @ApiModelProperty("字典项值")
    @Excel(name = "字典项值")
    private String itemValue;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long empId;
    /**
     * @author chenfg
     * @date: 2024/11/15 13:37
     * @description:  字典项值集合
     */
    private List<String> itemValues;
    /**
     * @author chenfg
     * @date: 2024/11/15 13:37
     * @description:  编辑类型: 1新增 2修改 3删除
     */
    private String editType;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;

    public static void main(String[] args) {
        ErpEmpDicDTO dto = new ErpEmpDicDTO();
        List<String> itemValues = new ArrayList<>();
        itemValues.add("1");
        itemValues.add("2");
        dto.setId(1L);
        dto.setTypeCode("1");
        dto.setTypeName("1");
        dto.setItemCode("1");
        dto.setItemName("1");
        dto.setItemValue("1");
        dto.setEmpId(1L);
        dto.setItemValues(itemValues);
        dto.setEditType("1");
        dto.setCreateBy(1L);
        dto.setCreateTime(LocalDateTime.now());
        dto.setUpdateBy(1L);
        dto.setUpdateTime(LocalDateTime.now());
        System.out.println(JSON.toJSONString(dto));
    }
}
