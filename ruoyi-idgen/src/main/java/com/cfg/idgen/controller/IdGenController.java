package com.cfg.idgen.controller;

import com.cfg.idgen.bo.IdGenBO;
import com.cfg.idgen.service.IdGenService;
import com.cfg.idgen.util.WrapperResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IdGenController
 * @Description id获取接口
 * @Author chenfg
 * @Date 2023/4/18 10:30
 */
@RestController
@RequestMapping("/api/id")
public class IdGenController {

	private static final Logger log = LoggerFactory.getLogger(IdGenController.class);

	@Autowired
	private IdGenService idGenService;
	@Autowired
	private IdGenBO idGenBO;


	@GetMapping("/uuid")
	public WrapperResponse<String> getUuid(){
		try {
			return WrapperResponse.success(idGenService.getUuid());
		} catch (Exception e) {
			e.printStackTrace();
			return WrapperResponse.fail(e.getMessage(),null);
		}
	}

	@GetMapping("/seq")
	public WrapperResponse<Long> getSeqId(@RequestParam String bizType){
		try {
			return WrapperResponse.success(idGenService.getSeqId(bizType));
		} catch (Exception e) {
			e.printStackTrace();
			return WrapperResponse.fail(e.getMessage(),null);
		}
	}

	@GetMapping("/segId/fixed")
	public WrapperResponse<String> getFixedLSegId(@RequestParam String bizType){
		try {
			return WrapperResponse.success(idGenService.getFixedLSegId(bizType));
		} catch (Exception e) {
			e.printStackTrace();
			return WrapperResponse.fail(e.getMessage(),null);
		}
	}

	@GetMapping("/segId")
	public WrapperResponse<String> getSegmentId(@RequestParam String bizType){
		try {
			return WrapperResponse.success(idGenService.getSegmentId(bizType));
		} catch (Exception e) {
			e.printStackTrace();
			return WrapperResponse.fail(e.getMessage(),null);
		}
	}

	@GetMapping("/segId/fixed/noprefix")
	public WrapperResponse<String> getFixedLSegIdNoneBizType(@RequestParam String bizType){
		try {
			return WrapperResponse.success(idGenService.getFixedLSegIdNoneBizType(bizType));
		} catch (Exception e) {
			e.printStackTrace();
			return WrapperResponse.fail(e.getMessage(),null);
		}
	}

	@GetMapping("/segId/noprefix")
	public WrapperResponse<String> getSegIdNoneBizType(@RequestParam String bizType){
		try {
			return WrapperResponse.success(idGenService.getSegIdNoneBizType(bizType));
		} catch (Exception e) {
			e.printStackTrace();
			return WrapperResponse.fail(e.getMessage(),null);
		}
	}

	@GetMapping("/snowId")
	public WrapperResponse<Long> getSnowId(){
		try {
			return WrapperResponse.success(idGenService.getSnowId());
		} catch (Exception e) {
			e.printStackTrace();
			return WrapperResponse.fail(e.getMessage(),null);
		}
	}

	/**
	 * @Description 删除ID信息
	 * @author chenfg
	 * @Date 2023/4/21 9:53
	 * @param bizType 业务类型
	 * @return boolean
	 **/
	@GetMapping("/del")
	public WrapperResponse<Boolean> deleteIdSequence(@RequestParam String bizType){
		try {
			return WrapperResponse.success(idGenBO.deleteId(bizType));
		} catch (Exception e) {
			e.printStackTrace();
			return WrapperResponse.fail(e.getMessage(),null);
		}
	}
}

