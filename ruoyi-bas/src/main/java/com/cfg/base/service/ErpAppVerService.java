package com.cfg.base.service;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.idgen.util.OperatorUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpAppVerMapper;
import com.cfg.base.domain.ErpAppVer;
import com.cfg.base.pojo.query.ErpAppVerQuery;

/**
 * App版本Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpAppVerService {
    @Autowired
    private ErpAppVerMapper erpAppVerMapper;

    /**
     * 查询App版本
     *
     * @param id App版本主键
     * @return App版本
     */
    public ErpAppVer selectById(Long id) {
        return erpAppVerMapper.selectById(id);
    }

    /**
     * 查询App版本列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return App版本
     */
    public List<ErpAppVer> selectList(ErpAppVerQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpAppVer> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String appVersion = query.getAppVersion();
        if (!StringUtils.isEmpty(appVersion)) {
            qw.eq("app_version", appVersion);
        }
        Long releaseNum = query.getReleaseNum();
        if (releaseNum != null) {
            qw.eq("release_num", releaseNum);
        }
        String appInfo = query.getAppInfo();
        if (!StringUtils.isEmpty(appInfo)) {
            qw.eq("app_info", appInfo);
        }
        String iosUrl = query.getIosUrl();
        if (!StringUtils.isEmpty(iosUrl)) {
            qw.eq("ios_url", iosUrl);
        }
        String androidUrl = query.getAndroidUrl();
        if (!StringUtils.isEmpty(androidUrl)) {
            qw.eq("android_url", androidUrl);
        }
        return erpAppVerMapper.selectList(qw);
    }

    /**
     * 新增App版本
     *
     * @param erpAppVer App版本
     * @return 结果
     */
    public int insert(ErpAppVer erpAppVer) {
        erpAppVer.setDelFlag(0);
        erpAppVer.setCreateTime(LocalDateTime.now());
        //设置创建信息
        erpAppVer.setCreateTime(LocalDateTime.now());
        OperatorUtils.setCreateInfo(erpAppVer);
        return erpAppVerMapper.insert(erpAppVer);
    }

    /**
     * 修改App版本
     *
     * @param erpAppVer App版本
     * @return 结果
     */
    public int update(ErpAppVer erpAppVer) {
        return erpAppVerMapper.updateById(erpAppVer);
    }

    /**
     * 批量删除App版本
     *
     * @param ids 需要删除的App版本主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpAppVerMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除App版本信息
     *
     * @param id App版本主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpAppVerMapper.updateDelFlagByIds(ids);
    }

    /**
     * 根据发布编号查询最新版本
     * @return 结果
     */
    public ErpAppVer selectByMaxReleaseNum() {
        return erpAppVerMapper.selectByMaxReleaseNum();
    }
}
