package com.cfg.base.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.base.dto.ErpEmpDicDTO;
import com.cfg.enums.CrudTypeEnum;
import com.cfg.enums.DicTypeEnum;
import com.cfg.idgen.service.IdGenService;
import com.cfg.idgen.util.ConvertUtils;
import com.cfg.idgen.util.OperatorUtils;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpEmpDicMapper;
import com.cfg.base.domain.ErpEmpDic;
import com.cfg.base.pojo.query.ErpEmpDicQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 常用字典管理Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpEmpDicService {
    @Autowired
    private ErpEmpDicMapper erpEmpDicMapper;
    @Autowired
    private IdGenService idGenService;

    private final String ID_BIZ_TYPE ="dic_id";

    /**
     * 查询常用字典管理
     *
     * @param id 常用字典管理主键
     * @return 常用字典管理
     */
    public ErpEmpDic selectById(Integer id) {
        return erpEmpDicMapper.selectById(id);
    }

    /**
     * 查询常用字典管理列表
     *
     * @param query 查询条件
     * @return 常用字典管理
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ErpEmpDic> insertCopyDefaultList(ErpEmpDicQuery query) {
            List<ErpEmpDic> empDics = insertDefaultDic(query.getTypeCode());
            if(!CollectionUtils.isEmpty(empDics)){
                for (ErpEmpDic empDic : empDics) {
                    ErpEmpDic erpEmpDic = ConvertUtils.convert(empDic,ErpEmpDic.class);
                    erpEmpDic.setId(idGenService.getSeqId(ID_BIZ_TYPE));
                    erpEmpDic.setEmpId(query.getEmpId());
                    OperatorUtils.setCreateInfo(erpEmpDic);
                    OperatorUtils.setUpdateInfo(erpEmpDic);
                    erpEmpDicMapper.insert(erpEmpDic);
                }
            }
        return empDics;
    }
    @Transactional(rollbackFor = Exception.class)
    public List<ErpEmpDic> selectList(ErpEmpDicQuery query, Pageable page){
        List<ErpEmpDic> dics = selectEmpList(query,page);
        if(CollectionUtils.isEmpty(dics)){
            insertCopyDefaultList(query);
            return selectEmpList(query,page);
        }else{
            return dics;
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public List<ErpEmpDic> selectEmpList(ErpEmpDicQuery query, Pageable page) {
        query.setEmpId(SecurityUtils.getEmpId());
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpEmpDic> qw = new QueryWrapper<>();
        String typeCode = query.getTypeCode();
        if (!StringUtils.isEmpty(typeCode)) {
            qw.eq("type_code", typeCode);
        }
        String typeNameLike = query.getTypeNameLike();
        if (!StringUtils.isEmpty(typeNameLike)) {
            qw.like("type_name", typeNameLike);
        }
        String itemCode = query.getItemCode();
        if (!StringUtils.isEmpty(itemCode)) {
            qw.eq("item_code", itemCode);
        }
        String itemNameLike = query.getItemNameLike();
        if (!StringUtils.isEmpty(itemNameLike)) {
            qw.like("item_name", itemNameLike);
        }
        String itemValue = query.getItemValue();
        if (!StringUtils.isEmpty(itemValue)) {
            qw.eq("item_value", itemValue);
        }
        Long empId = query.getEmpId();
        if (empId != null) {
            qw.eq("emp_id", empId);
        }
        return  erpEmpDicMapper.selectList(qw);
    }

    /**
     * 查询常用字典管理列表
     * @param query 查询条件
     * @param page 分页条件
     * @return 常用字典管理
     */
    public List<ErpEmpDic> selectSysDefaultList(ErpEmpDicQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpEmpDic> qw = new QueryWrapper<>();
        String typeCode = query.getTypeCode();
        if (!StringUtils.isEmpty(typeCode)) {
            qw.eq("type_code", typeCode);
        }
        String typeNameLike = query.getTypeNameLike();
        if (!StringUtils.isEmpty(typeNameLike)) {
            qw.like("type_name", typeNameLike);
        }
        String itemCode = query.getItemCode();
        if (!StringUtils.isEmpty(itemCode)) {
            qw.eq("item_code", itemCode);
        }
        String itemNameLike = query.getItemNameLike();
        if (!StringUtils.isEmpty(itemNameLike)) {
            qw.like("item_name", itemNameLike);
        }
        String itemValue = query.getItemValue();
        if (!StringUtils.isEmpty(itemValue)) {
            qw.eq("item_value", itemValue);
        }
        qw.isNull("emp_id");
        return erpEmpDicMapper.selectList(qw);
    }

    /**
     * 新增常用字典管理
     *
     * @param erpEmpDic 常用字典管理
     * @return 结果
     */
    public int insert(ErpEmpDic erpEmpDic) {
        erpEmpDic.setCreateTime(LocalDateTime.now());
        erpEmpDic.setEmpId(SecurityUtils.getEmpId());
        erpEmpDic.setId(idGenService.getSeqId(ID_BIZ_TYPE));
        OperatorUtils.setCreateInfo(erpEmpDic);
        OperatorUtils.setUpdateInfo(erpEmpDic);
        return erpEmpDicMapper.insert(erpEmpDic);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(ErpEmpDicDTO erpEmpDicDTO) {
        List<String> itemValues = erpEmpDicDTO.getItemValues();
        if(StringUtils.isNotBlank(erpEmpDicDTO.getEditType())&& CollectionUtil.isNotEmpty(itemValues)) {
            if (CrudTypeEnum.ADD.getCode().equals(erpEmpDicDTO.getEditType())) {
                if(!CollectionUtils.isEmpty(itemValues)){
                    for (String itemValue : itemValues) {
                        ErpEmpDic erpEmpDic = new ErpEmpDic();
                        erpEmpDic.setCreateTime(LocalDateTime.now());
                        erpEmpDic.setEmpId(SecurityUtils.getEmpId());
                        erpEmpDic.setId(getDicId());
                        erpEmpDic.setItemCode(String.valueOf(erpEmpDic.getId()));
                        erpEmpDic.setItemName(itemValue);
                        erpEmpDic.setItemValue(itemValue);
                        erpEmpDic.setTypeCode(DicTypeEnum.CLOSH_PART.getCode());
                        erpEmpDic.setTypeName(DicTypeEnum.CLOSH_PART.getName());
                        OperatorUtils.setCreateInfo(erpEmpDic);
                        OperatorUtils.setUpdateInfo(erpEmpDic);
                        erpEmpDicMapper.insert(erpEmpDic);
                    }
                }
            }else if(CrudTypeEnum.DELETE.getCode().equals(erpEmpDicDTO.getEditType())){
                erpEmpDicMapper.deleteByItemNameList(itemValues,erpEmpDicDTO.getTypeCode(),SecurityUtils.getEmpId().intValue());
            }
        }else{
            ErpEmpDic erpEmpDic = ConvertUtils.convert(erpEmpDicDTO,ErpEmpDic.class);
            if(StringUtils.isNotBlank(erpEmpDic.getItemName())&& StringUtils.isNotBlank(erpEmpDic.getTypeCode())){
                erpEmpDic.setId(getDicId());
                erpEmpDic.setItemCode(String.valueOf(erpEmpDic.getId()));
                erpEmpDic.setItemValue(erpEmpDicDTO.getItemName());
                erpEmpDic.setTypeCode(erpEmpDic.getTypeCode());
                DicTypeEnum dicTypeEnum =DicTypeEnum.of(erpEmpDic.getTypeCode());
                if(dicTypeEnum!=null){
                    erpEmpDic.setTypeName(dicTypeEnum.getName());
                    erpEmpDic.setEmpId(SecurityUtils.getEmpId());
                    OperatorUtils.setCreateInfo(erpEmpDic);
                    OperatorUtils.setUpdateInfo(erpEmpDic);
                    erpEmpDicMapper.insert(erpEmpDic);
                }else{
                    throw new RuntimeException("数据项类型不存在");
                }
            }
        }
        return 1;
    }

    /***
     * @author chenfg
     * @date: 2024/11/15 14:57
     * @description:  获取字典项id
     * @return: java.lang.Integer
     */
    Long getDicId(){
        return idGenService.getSeqId(ID_BIZ_TYPE);
    }


    /**
     * 修改常用字典管理
     *
     * @param erpEmpDic 常用字典管理
     * @return 结果
     */
    public int update(ErpEmpDic erpEmpDic) {
        return erpEmpDicMapper.updateById(erpEmpDic);
    }

    /**
     * 批量删除常用字典管理
     *
     * @param ids 需要删除的常用字典管理主键
     * @return 结果
     */
    public int deleteByIds(Integer[] ids) {
        return erpEmpDicMapper.updateDelFlagByIds(ids);
    }

    /***
     * @author chenfg
     * @date: 2024/11/15 14:51
     * @description:  按字典项名称删除
     * @param dicNameList
     * @param dicTypeCode
     * @param empId
     * @return: int
     */
    public int deleteByTypeAndNames(List<String> dicNameList,String dicTypeCode,Integer empId) {
        return erpEmpDicMapper.deleteByItemNameList(dicNameList,dicTypeCode,empId);
    }

    /**
     * 删除常用字典管理信息
     *
     * @param id 常用字典管理主键
     * @return 结果
     */
    public int deleteById(Integer id) {
        Integer[] ids = {id};
        return erpEmpDicMapper.deleteById(ids);
    }

   /**
     * 批量新增常用字典管理
     * @param dicTypeCode 常用字典管理集合
     * @return 结果
     */
    public List<ErpEmpDic> insertDefaultDic(String dicTypeCode){
        ErpEmpDicQuery query = new ErpEmpDicQuery();
        query.setTypeCode(dicTypeCode);
        return selectSysDefaultList(query,null);
    }
}
