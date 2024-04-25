package com.cfg.idgen.bo;

/**
 * @ClassName IdGenBO
 * @Description id生成BO
 * @Author chenfg
 * @Date 2023/4/18 11:11
 */
public interface IdGenBO {

	/**
	 * @return java.lang.String
	 * @Description 获取UUID
	 * @author chenfg
	 * @Date 2023/4/18 10:52
	 **/
	String getUuid();

	/**
	 * @param bizType 业务类型
	 * @return java.lang.Long
	 * @Description 获取序列ID
	 * @author chenfg
	 * @Date 2023/4/18 10:53
	 **/
	Long getSeqId(String bizType);

	/**
	 * @param bizType 业务类型
	 * @return java.lang.String
	 * @Description 获取分段固定长度ID
	 * @author chenfg
	 * @Date 2023/4/18 10:53
	 **/
	String getFixedLSegId(String bizType);

	/**
	 * @param bizType 业务类型
	 * @return java.lang.String
	 * @Description 获取分段不固定长度ID
	 * @author chenfg
	 * @Date 2023/4/18 10:54
	 **/
	String getSegmentId(String bizType);

	/**
	 * @param bizType 业务类型
	 * @return java.lang.String
	 * @Description 获取分段固定长度ID无前缀
	 * @author chenfg
	 * @Date 2023/4/18 10:57
	 **/
	String getFixedLSegIdNoneBizType(String bizType);

	/**
	 * @param bizType
	 * @return java.lang.String
	 * @Description 获取分段ID无前缀
	 * @author chenfg
	 * @Date 2023/4/18 11:01
	 **/
	String getSegIdNoneBizType(String bizType);

	/**
	 * @return java.lang.Long
	 * @Description 获取雪花ID
	 * @author chenfg
	 * @Date 2023/4/18 11:02
	 **/
	Long getSnowId();

	/**
	 * @Description 删除序列ID
	 * @author chenfg
	 * @Date 2023/4/20 19:44
	 * @param bizType 业务类型
	 * @return boolean
	 **/
	boolean deleteId(String bizType);
}
