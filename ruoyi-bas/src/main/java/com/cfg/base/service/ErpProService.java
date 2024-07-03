package com.cfg.base.service;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpProMapper;
import com.cfg.base.domain.ErpPro;
import com.cfg.base.pojo.query.ErpProQuery;

/**
 * 服装产品管理Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProService {
    @Autowired
    private ErpProMapper erpProMapper;

    /**
     * 查询服装产品管理
     *
     * @param proId 服装产品管理主键
     * @return 服装产品管理
     */
    public ErpPro selectByProId(Long proId) {
        return erpProMapper.selectById(proId);
    }

    /**
     * 查询服装产品管理列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 服装产品管理
     */
    public List<ErpPro> selectList(ErpProQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpPro> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String proCode = query.getProCode();
        if (!StringUtils.isEmpty(proCode)) {
            qw.eq("pro_code", proCode);
        }
        String proNameLike = query.getProNameLike();
        if (!StringUtils.isEmpty(proNameLike)) {
            qw.like("pro_name", proNameLike);
        }
        String proDesc = query.getProDesc();
        if (!StringUtils.isEmpty(proDesc)) {
            qw.eq("pro_desc", proDesc);
        }
        String proType = query.getProType();
        if (!StringUtils.isEmpty(proType)) {
            qw.eq("pro_type", proType);
        }
        String proMemo = query.getProMemo();
        if (!StringUtils.isEmpty(proMemo)) {
            qw.eq("pro_memo", proMemo);
        }
        String status = query.getStatus();
        if (!StringUtils.isEmpty(status)) {
            qw.eq("status", status);
        }
        Long empid = query.getEmpid();
        if (empid != null) {
            qw.eq("empId", empid);
        }
        Long seqNo = query.getSeqNo();
        if (seqNo != null) {
            qw.eq("seq_no", seqNo);
        }
        Integer dr = query.getDr();
        if (dr != null) {
            qw.eq("dr", dr);
        }
        return erpProMapper.selectList(qw);
    }

    /**
     * 新增服装产品管理
     *
     * @param erpPro 服装产品管理
     * @return 结果
     */
    public int insert(ErpPro erpPro) {
        erpPro.setDelFlag(0);
        erpPro.setCreateTime(LocalDateTime.now());
        return erpProMapper.insert(erpPro);
    }

    /**
     * 修改服装产品管理
     *
     * @param erpPro 服装产品管理
     * @return 结果
     */
    public int update(ErpPro erpPro) {
        return erpProMapper.updateById(erpPro);
    }

    /**
     * 批量删除服装产品管理
     *
     * @param proIds 需要删除的服装产品管理主键
     * @return 结果
     */
    public int deleteByProIds(Long[] proIds) {
        return erpProMapper.updateDelFlagByIds(proIds);
    }

    /**
     * 删除服装产品管理信息
     *
     * @param proId 服装产品管理主键
     * @return 结果
     */
    public int deleteByProId(Long proId) {
        Long[] proIds = {proId};
        return erpProMapper.updateDelFlagByIds(proIds);
    }
}
