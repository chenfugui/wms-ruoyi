package com.cfg.idgen.bo.impl;

import com.alibaba.fastjson.JSON;
import com.cfg.base.task.ApplicationStatusTask;
import com.cfg.idgen.bo.IdGenBO;
import com.cfg.idgen.dao.BasCommonIdDAO;
import com.cfg.idgen.entity.BasCommonIdDO;
import com.cfg.idgen.enums.IdTypeEnum;
import com.cfg.idgen.util.CommonUtils;
import com.cfg.idgen.util.RedisUtilContext;
import com.cfg.idgen.util.SnowflakeIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName IdGenBOImpl
 * @Description id生成BO实现
 * @Author chenfg
 * @Date 2023/4/18 11:14
 */
@Slf4j
@Component
public class IdGenBOImpl implements IdGenBO, InitializingBean {

	public static final String ID_KEY_PRIFEX = "idgen:id";
	public static final String ID_SEG_PRIFEX = "idgen:seg";
	public static final String SPLIT_CHAR = ":";

	public static final int FIX_LENGTH = 6;
	/**
	 * 过期时间5秒
	 */
	public static final int LOCK_EXPIRE_TIME = 5;
	public static final String  LOCK_SUFFIX = "lock";
	/**
	 段id过期时间，单位小时
	 **/
	public static final int SEG_KEY_EXPIRE = 25;
	public static final Integer STEP = 1000;
	@Autowired
	private BasCommonIdDAO basCommonIdDAO;
	@Autowired
	private ApplicationStatusTask applicationStatusTask;
	@Autowired
	private RedisUtilContext redisUtilContext;
	@Value("${wms.application.path:wms}")
	private String appkeyPrefix;

	@Override
	public String getUuid() {
		return CommonUtils.getUUID();
	}

	@Override
	public Long getSeqId(String bizType) {
		//查询redis是否存在idkey
		String idType = IdTypeEnum.SEQUENCE.getCode();
		return getNextSquentId(bizType, idType, null);
	}

	/**
	 * @param bizType 业务类型
	 * @param idType  id类型
	 * @return java.lang.Long
	 * @Description 获取Id序列数值
	 * @author chenfg
	 * @Date 2023/4/19 16:45
	 **/
	public Long getNextSquentId(String bizType, String idType, String segFlag) {
		String idKey = getIdKey(bizType, idType, segFlag);
		String requestId = CommonUtils.getUUID();
		//获取段地址
		String segmentKey = getSegmentKey(bizType, idType);
		Long idValue = null;
		//获取ID值
		idValue = redisUtilContext.getIncr(idKey,segmentKey);
		//处理段key和idkey都存在
		if (Objects.isNull(idValue)) {
			updateSyncSegmentInfo(segmentKey, idKey, bizType, idType, segFlag, requestId);
			idValue = redisUtilContext.getIncr(idKey,segmentKey);
		}
		if(idValue%STEP==0) {
			//获取段信息
			BasCommonIdDO ficsCommonIdDO = getSegmentInfo(segmentKey, idKey, bizType, idType, segFlag);
			//判断是否到达段最大值
			if (updateSegmentFlag(idValue, ficsCommonIdDO.getMaxId())) {
				ficsCommonIdDO=updateApplyNextSegMent(segmentKey, idKey, bizType, idType, segFlag, requestId, idValue, ficsCommonIdDO);
			}
		}
		return idValue;
	}

	/**
	 * @param segmentKey     段key
	 * @param idKey          idKey
	 * @param requestId      请求ID
	 * @param idValue        当前ID值
	 * @param commonIdDO 段对象
	 * @return void
	 * @Description 申请下一段ID
	 * @author chenfg
	 * @Date 2023/4/19 16:16
	 **/
	public BasCommonIdDO updateApplyNextSegMent(String segmentKey, String idKey,String bizType,String idType,String segFlag, String requestId, Long idValue, BasCommonIdDO commonIdDO) {
		redisUtilContext.lock(segmentKey+LOCK_SUFFIX, requestId, LOCK_EXPIRE_TIME);
		BasCommonIdDO ficsCommonIdDO = getSegmentInfo(segmentKey,idKey,bizType,idType,segFlag);
		//更新段信息
		if (updateSegmentFlag(idValue, ficsCommonIdDO.getMaxId())) {
			if(idValue-ficsCommonIdDO.getMaxId() > STEP){
				basCommonIdDAO.updateMaxIdWithMaxId(ficsCommonIdDO.getId(), idValue,ficsCommonIdDO.getVersion(), Timestamp.valueOf(LocalDateTime.now()));
			}else{
				basCommonIdDAO.updateMaxId(ficsCommonIdDO.getId(), ficsCommonIdDO.getVersion(), Timestamp.valueOf(LocalDateTime.now()));
			}
			//获取最新段信息
			ficsCommonIdDO = basCommonIdDAO.selectByPrimaryKey(ficsCommonIdDO.getId());
			Assert.notNull(ficsCommonIdDO,"序列段信息不存在,缓存数据异常");
			if(IdTypeEnum.SEQUENCE.getCode().equals(idType)) {
				redisUtilContext.getStringRedisTemplate().opsForValue().set(segmentKey, JSON.toJSONString(ficsCommonIdDO));
			}else{
				redisUtilContext.getStringRedisTemplate().opsForValue().set(segmentKey, JSON.toJSONString(ficsCommonIdDO),SEG_KEY_EXPIRE, TimeUnit.HOURS);
			}
		}
		redisUtilContext.unLock(segmentKey+LOCK_SUFFIX, requestId);
		return ficsCommonIdDO;
	}

	/**
	 * @param segmentKey 段Key
	 * @param idKey      idkey
	 * @param bizType    业务类型
	 * @param idType     id类型
	 * @param segFlag    段标识
	 * @param requestId  请求id
	 * @return void
	 * @Description 同步段信息
	 * @author chenfg
	 * @Date 2023/4/19 16:10
	 **/
	public void updateSyncSegmentInfo(String segmentKey, String idKey, String bizType, String idType, String segFlag, String requestId) {
		redisUtilContext.lock(segmentKey+LOCK_SUFFIX, requestId, LOCK_EXPIRE_TIME);
		if (!redisUtilContext.existKey(segmentKey)||!redisUtilContext.existKey(idKey)) {
			BasCommonIdDO ficsCommonIdDO = basCommonIdDAO.selectSegment(bizType, idType, segFlag);
			Long beginValue = 0L;
			//段存在
			if (null != ficsCommonIdDO) {
				beginValue=ficsCommonIdDO.getMaxId();
				//更新段信息
				basCommonIdDAO.updateMaxId(ficsCommonIdDO.getId(), ficsCommonIdDO.getVersion(), Timestamp.valueOf(LocalDateTime.now()));
				//获取最新段信息
				ficsCommonIdDO = basCommonIdDAO.selectByPrimaryKey(ficsCommonIdDO.getId());
			} else {
				ficsCommonIdDO = new BasCommonIdDO();
				ficsCommonIdDO.setId(getSnowId());
				ficsCommonIdDO.setStep(STEP);
				ficsCommonIdDO.setMaxId((long) STEP);
				ficsCommonIdDO.setVersion(1L);
				ficsCommonIdDO.setBizType(bizType);
				ficsCommonIdDO.setIdType(idType);
				if(StringUtils.isNotBlank(segFlag)){
					ficsCommonIdDO.setSegFlag(segFlag);
				}
				Date date = new Date();
				ficsCommonIdDO.setCrteTime(date);
				ficsCommonIdDO.setUpdtTime(date);
				basCommonIdDAO.insert(ficsCommonIdDO);
			}
			//设置段信息
			if(IdTypeEnum.SEQUENCE.getCode().equals(idType)){
				redisUtilContext.getStringRedisTemplate().opsForValue().set(segmentKey, JSON.toJSONString(ficsCommonIdDO));
				redisUtilContext.getLongRedisTemplate().opsForValue().set(idKey, beginValue);
				redisUtilContext.unLock(segmentKey+LOCK_SUFFIX, requestId);
			}else{
				redisUtilContext.getStringRedisTemplate().opsForValue().set(segmentKey, JSON.toJSONString(ficsCommonIdDO),SEG_KEY_EXPIRE,TimeUnit.HOURS);
				redisUtilContext.getLongRedisTemplate().opsForValue().set(idKey, beginValue,SEG_KEY_EXPIRE,TimeUnit.HOURS);
				redisUtilContext.unLock(segmentKey+LOCK_SUFFIX, requestId);
			}

		}
	}

	/**
	 * @param segmentKey 段信息key
	 * @return cn.hsa.ics.ext.idgen.entity.FicsCommonIdDO
	 * @Description 获取短信息
	 * @author chenfg
	 * @Date 2023/4/19 14:51
	 **/
	public BasCommonIdDO getSegmentInfo(String segmentKey,String idKey,String bizType,String idType,String segFlag) {
		String segmentInfo = redisUtilContext.getStringRedisTemplate().opsForValue().get(segmentKey);
		if(Objects.isNull(segmentInfo)){
			BasCommonIdDO	commonIdDO = basCommonIdDAO.selectSegment(bizType,idType,segFlag);
			if(Objects.isNull(commonIdDO)){
				log.warn("业务类型{} id类型{} 序列段信息不存在,初始化序列",bizType,idKey);
				updateSyncSegmentInfo(segmentKey, idKey, bizType, idType, segFlag, CommonUtils.getUUID());
			}
		}
		return JSON.parseObject(segmentInfo, BasCommonIdDO.class);
	}

	/**
	 * @param id    id值
	 * @param maxId 段最大值
	 * @return boolean
	 * @Description 判断是否更新段标识
	 * @author chenfg
	 * @Date 2023/4/19 14:53
	 **/
	public boolean updateSegmentFlag(Long id, Long maxId) {
		if (id >= maxId) {
			return true;
		}
		return false;
	}

	/**
	 * @param bizType 业务类型
	 * @param idType  id类型
	 * @return java.lang.String
	 * @Description 获取idkey
	 * @author chenfg
	 * @Date 2023/4/19 11:26
	 **/
	public String getIdKey(String bizType, String idType, String segFlag) {
		if (idType.equals(IdTypeEnum.SEG_FIX_LENGTH.getCode())
				|| idType.equals(IdTypeEnum.SEG_NO_FIX.getCode())
				|| idType.equals(IdTypeEnum.SEG_FIX_LENGTH_NO_BIZTYPE.getCode())
				|| idType.equals(IdTypeEnum.SEG_NO_FIX_BIZTYPE.getCode())) {
			return ID_KEY_PRIFEX + SPLIT_CHAR + bizType + SPLIT_CHAR + idType + SPLIT_CHAR + segFlag;
		} else {
			return ID_KEY_PRIFEX + SPLIT_CHAR + bizType + SPLIT_CHAR + idType;
		}
	}

	/**
	 * @param bizType 业务类型
	 * @param idType  id类型
	 * @return java.lang.String
	 * @Description 获取段字符串
	 * @author chenfg
	 * @Date 2023/4/19 14:19
	 **/
	public String getSegmentKey(String bizType, String idType) {
		return ID_SEG_PRIFEX + SPLIT_CHAR + bizType + SPLIT_CHAR + idType;
	}

	/**
	 * @return java.lang.String
	 * @Description 获取当前日期
	 * @author chenfg
	 * @Date 2023/4/19 11:33
	 **/
	public String getCurrentDay() {
		return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}

	@Override
	public String getFixedLSegId(String bizType) {
		//查询redis是否存在idkey
		String idType = IdTypeEnum.SEG_FIX_LENGTH.getCode();
		String segFlag = getCurrentDay();
		Long idValue = getNextSquentId(bizType, idType, segFlag);
		return bizType + "_" + segFlag + "_" + StringUtils.leftPad(String.valueOf(idValue), FIX_LENGTH, "0");
	}

	@Override
	public String getSegmentId(String bizType) {
		//查询redis是否存在idkey
		String idType = IdTypeEnum.SEG_NO_FIX.getCode();
		String segFlag = getCurrentDay();
		Long idValue = getNextSquentId(bizType, idType, segFlag);
		return bizType + "_" + segFlag + "_" + idValue;
	}

	@Override
	public String getFixedLSegIdNoneBizType(String bizType) {
		//查询redis是否存在idkey
		String idType = IdTypeEnum.SEG_FIX_LENGTH_NO_BIZTYPE.getCode();
		String segFlag = getCurrentDay();
		Long idValue = getNextSquentId(bizType, idType, segFlag);
		return segFlag + "_" + StringUtils.leftPad(String.valueOf(idValue), FIX_LENGTH, "0");
	}

	@Override
	public String getSegIdNoneBizType(String bizType) {
		//查询redis是否存在idkey
		String idType = IdTypeEnum.SEG_NO_FIX_BIZTYPE.getCode();
		String segFlag = getCurrentDay();
		Long idValue = getNextSquentId(bizType, idType, segFlag);
		return segFlag + "_" + idValue;
	}

	@Override
	public Long getSnowId() {
		SnowflakeIdGenerator snowIdGenerator = new SnowflakeIdGenerator(1L, 0L);
		//snowIdGenerator.setWorkerId(getWorkerId());
		snowIdGenerator.setWorkerId(1L);
		return snowIdGenerator.nextId();
	}

	/**
	 * @Description 根据业务类型删除ID序列
	 * @author chenfg
	 * @Date 2023/4/20 19:51
	 * @param bizType 业务类型
	 * @return boolean
	 **/
	@Override
	public boolean deleteId(String bizType) {
		if(StringUtils.isNotBlank(bizType)) {
			List<BasCommonIdDO> idDOList = basCommonIdDAO.selectSegSelective(bizType, null, null);
			if(null!=idDOList&&idDOList.size()>0){
				for (BasCommonIdDO ficsCommonIdDO : idDOList) {
					basCommonIdDAO.deleteByPrimaryKey(ficsCommonIdDO.getId());
					redisUtilContext.deleteKey(getSegmentKey(ficsCommonIdDO));
					redisUtilContext.deleteKey(getIdKey(ficsCommonIdDO));
				}
			}
		}
		return true;
	}

	public long getWorkerId() {
		long workerId = 0L;
		if (StringUtils.isBlank(appkeyPrefix)) {
			appkeyPrefix = ApplicationStatusTask.APPLICATION_KEY + SPLIT_CHAR + applicationStatusTask.getApplicationName() + SPLIT_CHAR + "*";
		}
		Set<String> keys = redisUtilContext.getKeysByPrefix(appkeyPrefix);
		if (!Objects.isNull(keys) && keys.size() > 0) {
			List<Long> timeList = keys.stream().map(key -> {
				String[] strArray = key.split(SPLIT_CHAR);
				return Long.parseLong(strArray[strArray.length - 1]);
			}).sorted().collect(Collectors.toList());
			for (int i = 0; i < timeList.size(); i++) {
				if (ApplicationStatusTask.startTime.equals(timeList.get(i))) {
					workerId = (long) i;
				}
			}
		}
		return workerId;
	}


	/**
	 * @Description 校验段信息
	 * @author chenfg
	 * @Date 2023/4/20 14:52
	 **/
	public void checkSegDataInfo(){
		//查询段信息
		Set<String> segkeys = redisUtilContext.getKeysByPrefix(ID_SEG_PRIFEX+SPLIT_CHAR+"*");
		List<Long> idList = new ArrayList<>();
		//获取所有段信息
		if(null!=segkeys&&segkeys.size()>0){
			List<String> segmentList = redisUtilContext.getStringRedisTemplate().opsForValue().multiGet(segkeys);
			for (String segJson : segmentList) {
				if(StringUtils.isNotBlank(segJson)) {
					BasCommonIdDO ficsCommonIdDO = JSON.parseObject(segJson, BasCommonIdDO.class);
					idList.add(ficsCommonIdDO.getId());
				}
			}
		}
		//db segkeys
		List<String> dbSetKeys = new ArrayList<>();
		if(idList.size()>0) {
			//根据ID列表查询段信息
			List<BasCommonIdDO> dbSeglist =  basCommonIdDAO.selectSegmentByIdList(idList);
			for (BasCommonIdDO ficsCommonIdDO : dbSeglist) {
				dbSetKeys.add(getSegmentKey(ficsCommonIdDO));
			}
		}
		//去除不存在的seg
		for (String segkey : segkeys) {
			if(!dbSetKeys.contains(segkey)){
				redisUtilContext.deleteKey(segkey);
			}
		}
	}

	/**
	 * @Description 获取idKey
	 * @param ficsCommonIdDO  id段信息对象
	 * @return java.lang.String
	 * @author chenfg
	 * @Date 2023/4/20 11:26
	 **/
	public String getIdKey(BasCommonIdDO ficsCommonIdDO) {
		String idType = ficsCommonIdDO.getIdType();
		if (idType.equals(IdTypeEnum.SEG_FIX_LENGTH.getCode())
				|| idType.equals(IdTypeEnum.SEG_NO_FIX.getCode())
				|| idType.equals(IdTypeEnum.SEG_FIX_LENGTH_NO_BIZTYPE.getCode())
				|| idType.equals(IdTypeEnum.SEG_NO_FIX_BIZTYPE.getCode())) {
			return ID_KEY_PRIFEX + SPLIT_CHAR + ficsCommonIdDO.getBizType() + SPLIT_CHAR + idType + SPLIT_CHAR + ficsCommonIdDO.getSegFlag();
		} else {
			return ID_KEY_PRIFEX + SPLIT_CHAR + ficsCommonIdDO.getBizType() + SPLIT_CHAR + idType;
		}
	}

	/**
	 * @Description 获取segKey
	 * @param ficsCommonIdDO  id段信息对象
	 * @return java.lang.String
	 * @author chenfg
	 * @Date 2023/4/20 11:26
	 **/
	public String getSegmentKey(BasCommonIdDO ficsCommonIdDO) {
		return ID_SEG_PRIFEX + SPLIT_CHAR + ficsCommonIdDO.getBizType() + SPLIT_CHAR + ficsCommonIdDO.getIdType();
	}

	/**
	 * @Description 清除redis无效段信息
	 * @author chenfg
	 * @Date 2023/4/20 16:30
	 * @return void
	 **/
	@Override
	public void afterPropertiesSet() throws Exception {
		//检查redis段信息
		checkSegDataInfo();
	}
}
