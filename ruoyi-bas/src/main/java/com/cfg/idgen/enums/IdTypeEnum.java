package com.cfg.idgen.enums;

/**
 * @ClassName IdTypeEnum
 * @Description TODO
 * @Author chenfg
 * @Date 2023/4/18 20:01
 */
public enum IdTypeEnum {

	SEQUENCE("01","自增序列"),
	SEG_FIX_LENGTH("02","分段固长"),
	SEG_NO_FIX("03","分段不固长"),
	SEG_FIX_LENGTH_NO_BIZTYPE("04","分段固长无前缀"),
	SEG_NO_FIX_BIZTYPE("05","分段无前缀");

	private String code;
	private String name;

	IdTypeEnum(String code,String name){
		this.code=code;
		this.name=name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
