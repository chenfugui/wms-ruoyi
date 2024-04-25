package com.cfg.idgen.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @ClassName CommonIdDTO
 * @Description 公共统一ID DTO
 * @Author chenfg
 * @Date 2023/4/18 11:04
 */
@Data
public class CommonIdDTO  implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 唯一ID
	 */
	private Long id;

	/**
	 * 最大ID
	 */
	private Long maxId;

	/**
	 * 段大小
	 */
	private Integer step;

	/**
	 * 业务类型
	 */
	private String bizType;

	/**
	 * 版本号
	 */
	private Long version;

	/**
	 * id类型
	 */
	private String idType;

	/**
	 * 段标识：日期字符串
	 */
	private String segFlag;

	/**
	 * 创建时间
	 */
	private Date crteTime;

	/**
	 * 更新时间
	 */
	private Date updtTime;
}
