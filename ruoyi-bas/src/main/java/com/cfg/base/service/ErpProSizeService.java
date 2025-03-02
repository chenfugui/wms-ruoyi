package com.cfg.base.service;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.idgen.service.IdGenService;
import com.cfg.idgen.util.OperatorUtils;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpProSizeMapper;
import com.cfg.base.domain.ErpProSize;
import com.cfg.base.pojo.query.ErpProSizeQuery;

/**
 * 服装生产尺码Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProSizeService {
    @Autowired
    private ErpProSizeMapper erpProSizeMapper;

    @Autowired
    private IdGenService idGenService;

    /**
     * 查询服装生产尺码
     *
     * @param id 服装生产尺码主键
     * @return 服装生产尺码
     */
    public ErpProSize selectById(Long id) {
        return erpProSizeMapper.selectById(id);
    }

    /**
     * 查询服装生产尺码列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 服装生产尺码
     */
    public List<ErpProSize> selectList(ErpProSizeQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpProSize> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long proId = query.getProId();
        if (proId != null) {
            qw.eq("pro_id", proId);
        }
        Long empId = query.getEmpId();
        if (empId != null) {
            qw.eq("emp_id", empId);
        }
        Long sizeId = query.getSizeId();
        if (sizeId != null) {
            qw.eq("size_id", sizeId);
        }
        String sizeCode = query.getSizeCode();
        if (!StringUtils.isEmpty(sizeCode)) {
            qw.eq("size_code", sizeCode);
        }
        String sizeNameLike = query.getSizeNameLike();
        if (!StringUtils.isEmpty(sizeNameLike)) {
            qw.like("size_name", sizeNameLike);
        }
        Long seqNo = query.getSeqNo();
        if (seqNo != null) {
            qw.eq("seq_no", seqNo);
        }
        Integer delFlag = query.getDelFlag();
        if (delFlag != null) {
            qw.eq("del_flag", delFlag);
        }
        return erpProSizeMapper.selectList(qw);
    }

    /**
     * 新增服装生产尺码
     *
     * @param erpProSize 服装生产尺码
     * @return 结果
     */
    public int insert(ErpProSize erpProSize) {
        erpProSize.setDelFlag(0);
        erpProSize.setCreateTime(LocalDateTime.now());
        erpProSize.setId(idGenService.getSeqId("size_id"));
        erpProSize.setEmpId(SecurityUtils.getEmpId());
        OperatorUtils.setCreateInfo(erpProSize);
        return erpProSizeMapper.insert(erpProSize);
    }

    /**
     * 修改服装生产尺码
     *
     * @param erpProSize 服装生产尺码
     * @return 结果
     */
    public int update(ErpProSize erpProSize) {
        OperatorUtils.setUpdateInfo(erpProSize);
        return erpProSizeMapper.updateById(erpProSize);
    }

    /**
     * 批量删除服装生产尺码
     *
     * @param ids 需要删除的服装生产尺码主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpProSizeMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除服装生产尺码信息
     *
     * @param id 服装生产尺码主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpProSizeMapper.updateDelFlagByIds(ids);
    }

    public int deleteByProIds(List<Long> proIds) {
        return erpProSizeMapper.deleteByProIds(proIds);
    }
}
