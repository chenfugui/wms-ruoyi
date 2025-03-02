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
import com.cfg.base.mapper.ErpProTemplateDetailMapper;
import com.cfg.base.domain.ErpProTemplateDetail;
import com.cfg.base.pojo.query.ErpProTemplateDetailQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 模板项目明细管理Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProTemplateDetailService {
    @Autowired
    private ErpProTemplateDetailMapper erpProTemplateDetailMapper;
    @Autowired
    private IdGenService idGenService;

    /**
     * 查询模板项目明细管理
     *
     * @param id 模板项目明细管理主键
     * @return 模板项目明细管理
     */
    public ErpProTemplateDetail selectById(Long id) {
        return erpProTemplateDetailMapper.selectById(id);
    }

    /**
     * 查询模板项目明细管理列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 模板项目明细管理
     */
    public List<ErpProTemplateDetail> selectList(ErpProTemplateDetailQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpProTemplateDetail> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long tmpId = query.getTmpId();
        if (tmpId != null) {
            qw.eq("tmp_id", tmpId);
        }
        String tmpCode = query.getTmpCode();
        if (!StringUtils.isEmpty(tmpCode)) {
            qw.eq("tmp_code", tmpCode);
        }
        String itemCode = query.getItemCode();
        if (!StringUtils.isEmpty(itemCode)) {
            qw.eq("item_code", itemCode);
        }
        String itemNameLike = query.getItemNameLike();
        if (!StringUtils.isEmpty(itemNameLike)) {
            qw.like("item_name", itemNameLike);
        }
        String itemMemo = query.getItemMemo();
        if (!StringUtils.isEmpty(itemMemo)) {
            qw.eq("item_memo", itemMemo);
        }
        return erpProTemplateDetailMapper.selectList(qw);
    }

    /**
     * 新增模板项目明细管理
     *
     * @param erpProTemplateDetail 模板项目明细管理
     * @return 结果
     */
    public int insert(ErpProTemplateDetail erpProTemplateDetail) {
        erpProTemplateDetail.setDelFlag(0);
        erpProTemplateDetail.setCreateTime(LocalDateTime.now());
        erpProTemplateDetail.setId(idGenService.getSeqId("tmp_id"));
        erpProTemplateDetail.setEmpId(SecurityUtils.getEmpId());
        OperatorUtils.setCreateInfo(erpProTemplateDetail);
        return erpProTemplateDetailMapper.insert(erpProTemplateDetail);
    }

    /**
     * 新增模板项目明细管理
     *
     * @param erpProTemplateDetails 模板项目明细管理
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertList(List<ErpProTemplateDetail> erpProTemplateDetails){
        Assert.notEmpty(erpProTemplateDetails, "模板项目明细管理为空");
        for (ErpProTemplateDetail erpProTemplateDetail : erpProTemplateDetails) {
            insert(erpProTemplateDetail);
        }
        return erpProTemplateDetails.size();
    }

    /**
     * 修改模板项目明细管理
     *
     * @param erpProTemplateDetail 模板项目明细管理
     * @return 结果
     */
    public int update(ErpProTemplateDetail erpProTemplateDetail) {
        OperatorUtils.setUpdateInfo(erpProTemplateDetail);
        return erpProTemplateDetailMapper.updateById(erpProTemplateDetail);
    }

    /**
     * 批量删除模板项目明细管理
     *
     * @param ids 需要删除的模板项目明细管理主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpProTemplateDetailMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除模板项目明细管理信息
     *
     * @param id 模板项目明细管理主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpProTemplateDetailMapper.updateDelFlagByIds(ids);
    }
}
