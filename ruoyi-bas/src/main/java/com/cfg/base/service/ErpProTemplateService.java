package com.cfg.base.service;

import java.util.List;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.base.domain.ErpProTemplateDetail;
import com.cfg.base.mapper.ErpProTemplateDetailMapper;
import com.cfg.base.pojo.dto.ErpProTemplateDTO;
import com.cfg.base.pojo.dto.ErpProTemplateDetailDTO;
import com.cfg.enums.DrFlag;
import com.cfg.idgen.service.IdGenService;
import com.cfg.idgen.util.ConvertUtils;
import com.cfg.idgen.util.OperatorUtils;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpProTemplateMapper;
import com.cfg.base.domain.ErpProTemplate;
import com.cfg.base.pojo.query.ErpProTemplateQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 模板表Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProTemplateService {
    @Autowired
    private ErpProTemplateMapper templateMapper;
    @Autowired
    private ErpProTemplateDetailMapper templateDetailMapper;
    @Autowired
    private IdGenService idGenService;

    /**
     * 查询模板表
     *
     * @param id 模板表主键
     * @return 模板表
     */
    public ErpProTemplate selectById(Long id) {
        return templateMapper.selectById(id);
    }

    /**
     * 查询模板表列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 模板表
     */
    public List<ErpProTemplate> selectList(ErpProTemplateQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpProTemplate> qw = new QueryWrapper<>();
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if(!loginUser.getUser().isAdmin()){
            qw.eq("emp_id",loginUser.getUser().getEmpId());
        }
        qw.eq("del_flag",0);
        String tmpCode = query.getTmpCode();
        if (!StringUtils.isEmpty(tmpCode)) {
            qw.eq("tmp_code", tmpCode);
        }
        String tmpNameLike = query.getTmpNameLike();
        if (!StringUtils.isEmpty(tmpNameLike)) {
            qw.like("tmp_name", tmpNameLike);
        }
        String tmpType = query.getTmpType();
        if (!StringUtils.isEmpty(tmpType)) {
            qw.eq("tmp_type", tmpType);
        }
        String tmpMemo = query.getTmpMemo();
        if (!StringUtils.isEmpty(tmpMemo)) {
            qw.eq("tmp_memo", tmpMemo);
        }
        return templateMapper.selectList(qw);
    }

    /**
     * 新增模板表
     *
     * @param erpProTemplateDtO 模板表
     * @return 结果
     */
    @Transactional
    public int insert(ErpProTemplateDTO erpProTemplateDtO) {
        ErpProTemplate erpProTemplate = ConvertUtils.convert(erpProTemplateDtO, ErpProTemplate.class);
        erpProTemplate.setDelFlag(0);
        erpProTemplate.setCreateTime(LocalDateTime.now());
        erpProTemplate.setId(idGenService.getSeqId("tmp_id"));
        erpProTemplate.setEmpId(SecurityUtils.getEmpId());
        OperatorUtils.setCreateInfo(erpProTemplate);
        int rows = templateMapper.insert(erpProTemplate);
        erpProTemplateDtO.setId(erpProTemplate.getId());
        insertErpProTemplateDetail(erpProTemplateDtO);
        return rows;
    }

    /**
     * 修改模板表
     *
     * @param erpProTemplateDTO 模板表
     * @return 结果
     */
    @Transactional
    public int update(ErpProTemplateDTO erpProTemplateDTO) {
        ErpProTemplate erpProTemplate = ConvertUtils.convert(erpProTemplateDTO, ErpProTemplate.class);
        templateDetailMapper.deleteErpProTemplateDetailByTmpId(erpProTemplate.getId());
        OperatorUtils.setUpdateInfo(erpProTemplate);
        erpProTemplate.setDelFlag(DrFlag.NORMAL.getCode());
        if(!CollectionUtils.isEmpty(erpProTemplateDTO.getErpProTemplateDetailList())) {
            insertErpProTemplateDetail(erpProTemplateDTO);
        }
        return templateMapper.updateById(erpProTemplate);
    }

    /**
     * 批量删除模板表
     *
     * @param ids 需要删除的模板表主键
     * @return 结果
     */
    @Transactional
    public int deleteByIds(Long[] ids) {
       // return templateMapper.updateDelFlagByIds(ids);
        templateDetailMapper.deleteByTmpIds(ids);
        templateMapper.deleteByIds(ids);
        return ids.length;
    }

    /**
     * 删除模板表信息
     *
     * @param id 模板表主键
     * @return 结果
     */
    @Transactional
    public int deleteById(Long id) {
        Long[] ids = {id};
        return templateMapper.updateDelFlagByIds(ids);
    }



    @Transactional
   public void insertErpProTemplateDetail(ErpProTemplateDTO  erpProTemplate){
        if(null!=erpProTemplate&&null!=erpProTemplate.getErpProTemplateDetailList()){
            for(ErpProTemplateDetailDTO erpProTemplateDetail:erpProTemplate.getErpProTemplateDetailList()){
                erpProTemplateDetail.setTmpId(erpProTemplate.getId());
                ErpProTemplateDetail tmpDetail = ConvertUtils.convert(erpProTemplateDetail,ErpProTemplateDetail.class);
                tmpDetail.setDelFlag(DrFlag.NORMAL.getCode());
                OperatorUtils.setCreateInfo(tmpDetail);
                OperatorUtils.setUpdateInfo(tmpDetail);
                templateDetailMapper.insert(tmpDetail);
            }
        }
    }
}
