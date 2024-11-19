package com.cfg.base.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName : ProMakePrintDTO
 * @Description : 打菲DTO
 * @Author : chenfg
 * @Date: 2024-10-21 13:41
 */
@Data
public class ProMakePrintDTO {

    //生产id
    private Long proMakeId;
    //床次
    private String bedNo;
    //开始扎号
    private Long pkgStartNo;
    //结束扎号
    private Long pkgEndNo;
    //总扎数
    private Long pkgTotalNum;
    //生产总数量
    private Long mkTotalNum;
    //部位列表
    private List<String> partList;
    //单位名称
    private String empName;
    //单位ID
    private Long empId;
    //产品ID
    private Long proId;
    //产品名称
    private String proName;
}
