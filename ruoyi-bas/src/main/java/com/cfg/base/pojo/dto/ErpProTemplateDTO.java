package com.cfg.base.pojo.dto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 模板表 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProTemplateDTO extends BaseAudit {
    private Long id;
    private String tmpCode;
    private String tmpName;
    private String tmpType;
    private String tmpMemo;
    private Long empId;
    private List<ErpProTemplateDetailDTO> erpProTemplateDetailList;

    public static void main(String[] args) {
        ErpProTemplateDTO tmpDTO = new ErpProTemplateDTO();
        tmpDTO.setId(1L);
        tmpDTO.setTmpCode("tmpCode");
        tmpDTO.setTmpName("tmpName");
        tmpDTO.setTmpType("tmpType");
        tmpDTO.setTmpMemo("tmpMemo");
        tmpDTO.setEmpId(1L);
        tmpDTO.setCreateBy(1L);
        tmpDTO.setCreateTime(LocalDateTime.now());
        tmpDTO.setUpdateBy(1L);
        tmpDTO.setUpdateTime(LocalDateTime.now());
        ErpProTemplateDetailDTO tmpDetailDTO = new ErpProTemplateDetailDTO();
        tmpDetailDTO.setId(1L);
        tmpDetailDTO.setTmpId(1L);
        tmpDetailDTO.setItemCode("itemCode");
        tmpDetailDTO.setItemName("itemName");
        tmpDetailDTO.setItemMemo("itemMemo");
        tmpDetailDTO.setSeqNo(1L);
        tmpDetailDTO.setEmpId(1L);
        tmpDetailDTO.setCreateBy(1L);
        tmpDetailDTO.setCreateTime(LocalDateTime.now());
        tmpDetailDTO.setUpdateBy(1L);
        tmpDetailDTO.setUpdateTime(LocalDateTime.now());
        List<ErpProTemplateDetailDTO> detail = Arrays.asList(tmpDetailDTO);
        tmpDTO.setErpProTemplateDetailList(detail);
        System.out.println(JSON.toJSONString(tmpDTO, SerializerFeature.WriteDateUseDateFormat));
    }
}
