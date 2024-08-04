package com.cfg.idgen.service.impl;

import com.cfg.idgen.bo.IdGenBO;
import com.cfg.idgen.service.IdGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName IdGenServiceImpl
 * @Description id生成服务
 * @Author chenfg
 * @Date 2023/4/18 11:16
 */
@Service
public class IdGenServiceImpl implements IdGenService {

	@Autowired
	private IdGenBO idGenBO;

	@Override
	public String getUuid() {
		return idGenBO.getUuid();
	}

	@Override
	public Long getSeqId(String bizType) {
		return idGenBO.getSeqId(bizType);
	}

	@Override
	public String getFixedLSegId(String bizType) {
		return idGenBO.getFixedLSegId(bizType);
	}

	@Override
	public String getSegmentId(String bizType) {
		return idGenBO.getSegmentId(bizType);
	}

	@Override
	public String getFixedLSegIdNoneBizType(String bizType) {
		return idGenBO.getFixedLSegIdNoneBizType(bizType);
	}

	@Override
	public String getSegIdNoneBizType(String bizType) {
		return idGenBO.getSegIdNoneBizType(bizType);
	}

	@Override
	public Long getSnowId() {
		return idGenBO.getSnowId();
	}

	@Override
	public Long getSeqId() {
		return idGenBO.getSeqId("globalId");
	}
}
