package com.cfg.base.dto;

import com.cfg.config.CommonCfg;
import com.ruoyi.common.utils.StringUtils;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Desc : 企业用户DTO
 * @Author : chenfugui
 * @Date : 2025/4/7
 */
@Data
public class EmpUserDTO {

    // 单位编码
    @NotBlank(message = "企业码不能为空",groups = {Worker.class})
    private  String empId;
    // 单位名称
    @NotBlank(message = "企业名称不能为空",groups = {Boss.class})
    private  String empName;
    // 单位类型
    private  String empType;
    // 用户名
    @NotBlank(message = "用户名不能为空")
    private  String userName;
    // 昵称
    @NotBlank(message = "昵称不能为空")
    private String nickName;
    // 密码
    @NotBlank(message = "密码不能为空")
    private  String password;
    //手机号
    @NotBlank(message = "手机号不能为空")
    private  String phonenumber;
    //邮箱
    private  String email;
    //邀请码
    private  String inviteCode;
    //省编码
    @NotBlank(message = "区域不能为空",groups = {Boss.class})
    private  String provinceCode;
    //市编码
    @NotBlank(message = "区域不能为空",groups = {Boss.class})
    private  String cityCode;
    //县编码
    @NotBlank(message = "区域不能为空",groups = {Boss.class})
    private  String countyCode;
    //详细地址
    @NotBlank(message = "区域不能为空",groups = {Boss.class})
    private  String address;
    //用户类型  0 企业员工  1 企业老板
    @NotBlank(message = "类型不能为空")
    private  String regType;
    //省市区编码
    private String xzqhCode;
    
    
    public String buildXzqhCode() {
        String ssxCode = provinceCode+ CommonCfg.SPLIT_CHAR+cityCode;
        if(StringUtils.isNoneBlank(countyCode)){
            ssxCode = ssxCode+CommonCfg.SPLIT_CHAR+countyCode;
        }
        xzqhCode =ssxCode;
        return ssxCode;
    }

    public void splitXzqhCode(String xzqhCode) {
      String[] ssxCodes = xzqhCode.split(CommonCfg.SPLIT_CHAR);
      if(ssxCodes.length==3){
          this.provinceCode = ssxCodes[0];
          this.cityCode = ssxCodes[1];
          this.countyCode = ssxCodes[2];
      }else if(ssxCodes.length==2){
          this.provinceCode = ssxCodes[0];
          this.cityCode = ssxCodes[1];
      }
    }


    public interface Boss{};
    public interface Worker{};

}
