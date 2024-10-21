package com.cfg.base.dto;

import lombok.Data;

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
}
