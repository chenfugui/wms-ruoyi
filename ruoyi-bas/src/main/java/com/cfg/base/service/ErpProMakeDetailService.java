package com.cfg.base.service;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.idgen.service.IdGenService;
import com.cfg.idgen.util.OperatorUtils;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpProMakeDetailMapper;
import com.cfg.base.domain.ErpProMakeDetail;
import com.cfg.base.pojo.query.ErpProMakeDetailQuery;

/**
 * 服装生产明细Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProMakeDetailService {
    @Autowired
    private ErpProMakeDetailMapper erpProMakeDetailMapper;

    @Autowired
    private IdGenService idGenService;

    /**
     * 查询服装生产明细
     *
     * @param id 服装生产明细主键
     * @return 服装生产明细
     */
    public ErpProMakeDetail selectById(Long id) {
        return erpProMakeDetailMapper.selectById(id);
    }

    /**
     * 查询服装生产明细列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 服装生产明细
     */
    public List<ErpProMakeDetail> selectList(ErpProMakeDetailQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpProMakeDetail> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long proMakeId = query.getProMakeId();
        if (proMakeId != null) {
            qw.eq("pro_make_id", proMakeId);
        }
        String proMakeNo = query.getProMakeNo();
        if (!StringUtils.isEmpty(proMakeNo)) {
            qw.eq("pro_make_no", proMakeNo);
        }
        Long proId = query.getProId();
        if (proId != null) {
            qw.eq("pro_id", proId);
        }
        Long sizeId = query.getSizeId();
        if (sizeId != null) {
            qw.eq("size_id", sizeId);
        }
        Long colorId = query.getColorId();
        if (colorId != null) {
            qw.eq("color_id", colorId);
        }
        Long clothId = query.getClothId();
        if (clothId != null) {
            qw.eq("cloth_id", clothId);
        }
        String clothCode = query.getClothCode();
        if (!StringUtils.isEmpty(clothCode)) {
            qw.eq("cloth_code", clothCode);
        }
        String clothNameLike = query.getClothNameLike();
        if (!StringUtils.isEmpty(clothNameLike)) {
            qw.like("cloth_name", clothNameLike);
        }
        Long makeNum = query.getMakeNum();
        if (makeNum != null) {
            qw.eq("make_num", makeNum);
        }
        Integer delFlag = query.getDelFlag();
        if (delFlag != null) {
            qw.eq("del_flag", delFlag);
        }
        Long empId = query.getEmpId();
        if (empId != null) {
            qw.eq("emp_id", empId);
        }
        return erpProMakeDetailMapper.selectList(qw);
    }

    /**
     * 新增服装生产明细
     *
     * @param erpProMakeDetail 服装生产明细
     * @return 结果
     */
    public int insert(ErpProMakeDetail erpProMakeDetail) {
        erpProMakeDetail.setDelFlag(0);
        erpProMakeDetail.setCreateTime(LocalDateTime.now());
        erpProMakeDetail.setId(idGenService.getSeqId("detail_id"));
        erpProMakeDetail.setEmpId(SecurityUtils.getEmpId());
        OperatorUtils.setCreateInfo(erpProMakeDetail);
        return erpProMakeDetailMapper.insert(erpProMakeDetail);
    }

    /**
     * 修改服装生产明细
     *
     * @param erpProMakeDetail 服装生产明细
     * @return 结果
     */
    public int update(ErpProMakeDetail erpProMakeDetail) {
        OperatorUtils.setUpdateInfo(erpProMakeDetail);
        return erpProMakeDetailMapper.updateById(erpProMakeDetail);
    }

    /**
     * 批量删除服装生产明细
     *
     * @param ids 需要删除的服装生产明细主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpProMakeDetailMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除服装生产明细信息
     *
     * @param id 服装生产明细主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpProMakeDetailMapper.updateDelFlagByIds(ids);
    }

    /***
     * @author chenfg
     * @date: 2024/10/16 11:04
     * @description:  根据产品Id,生产id查询明细列表
     * @param proMakeId
     * @param proId
     * @return: java.util.List<com.cfg.base.domain.ErpProMakeDetail>
     */
    public List<ErpProMakeDetail> selectDetailList(Long proMakeId,Long proId) {
        Assert.isTrue(null!=proMakeId||null!=proId,"参数异常");
        QueryWrapper<ErpProMakeDetail> qw = new QueryWrapper<>();
        if (proMakeId != null) {
            qw.eq("pro_make_id", proMakeId);
        }
        if (proId != null) {
            qw.eq("pro_id", proId);
        }
        qw.orderByAsc("id");
        return erpProMakeDetailMapper.selectList(qw);
    }
    /***
     * @author chenfg
     * @date: 2024/10/21 14:07
     * @description:  查询生产明细
     * @param proMakeId 生产ID
     * @return: java.util.List<com.cfg.base.domain.ErpProMakeDetail>
     */
    public List<ErpProMakeDetail> selectDetailsByMakeId(Long proMakeId) {
        Assert.isTrue(null!=proMakeId,"参数异常");
        QueryWrapper<ErpProMakeDetail> qw = new QueryWrapper<>();
        if (proMakeId != null) {
            qw.eq("pro_make_id", proMakeId);
        }
        qw.orderByAsc("id");
        return erpProMakeDetailMapper.selectList(qw);
    }
    /***
     * @author chenfg
     * @date: 2024/10/16 11:08
     * @description:  根据生产id删除明细
     * @param proMakeId
     * @return: void
     */
    public void deleteByProMakeId(Long proMakeId) {
        QueryWrapper<ErpProMakeDetail> qw = new QueryWrapper<>();
        qw.eq("pro_make_id", proMakeId);
        erpProMakeDetailMapper.delete(qw);
    }

    /***
     * @author chenfg
     * @date: 2024/10/21 14:10
     * @description:  根据生产id批量删除明细
     * @param ids
     * @return: void
     */
    public void deleteByMakeIds(List<Long> ids) {
        erpProMakeDetailMapper.deleteByMakeIds(ids);
    }
}
