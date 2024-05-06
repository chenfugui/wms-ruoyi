package com.cfg.base.service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.idgen.service.IdGenService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.MakeMapper;
import com.cfg.base.domain.Make;
import com.cfg.base.pojo.query.MakeQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 产品生产表Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class MakeService {
    @Autowired
    private IdGenService idGen;

    @Autowired
    private MakeMapper makeMapper;

    /**
     * 查询产品生产表
     *
     * @param makeId 产品生产表主键
     * @return 产品生产表
     */
    public Make selectByMakeId(Long makeId) {
        return makeMapper.selectById(makeId);
    }

    /**
     * 查询产品生产表列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 产品生产表
     */
    public List<Make> selectList(MakeQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Make> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String makeCode = query.getMakeCode();
        if (!StringUtils.isEmpty(makeCode)) {
            qw.eq("make_code", makeCode);
        }
        Long makeNum = query.getMakeNum();
        if (makeNum != null) {
            qw.eq("make_num", makeNum);
        }
        Long empId = query.getEmpId();
        if (empId != null) {
            qw.eq("emp_id", empId);
        }
        String status = query.getStatus();
        if (!StringUtils.isEmpty(status)) {
            qw.eq("status", status);
        }
        Long proId = query.getProId();
        if (proId != null) {
            qw.eq("pro_id", proId);
        }
        return makeMapper.selectList(qw);
    }

    /**
     * 新增产品生产表
     *
     * @param make 产品生产表
     * @return 结果
     */
    public int insert(Make make) {
        make.setDelFlag("0");
        make.setCreateTime(LocalDateTime.now());
        return makeMapper.insert(make);
    }

    /**
     * 修改产品生产表
     *
     * @param make 产品生产表
     * @return 结果
     */
    public int update(Make make) {
        return makeMapper.updateById(make);
    }

    /**
     * 批量删除产品生产表
     *
     * @param makeIds 需要删除的产品生产表主键
     * @return 结果
     */
    public int deleteByMakeIds(Long[] makeIds) {
        return makeMapper.updateDelFlagByIds(makeIds);
    }

    /**
     * 删除产品生产表信息
     *
     * @param makeId 产品生产表主键
     * @return 结果
     */
    public int deleteByMakeId(Long makeId) {
        Long[] makeIds = {makeId};
        return makeMapper.updateDelFlagByIds(makeIds);
    }

    /**
     * 新增产品生产表
     *
     * @param query 产品生产表
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public List<Make> insertBatch(MakeQuery query) {
        List<Make> makeLst = new ArrayList<Make>();
        if(null!=query.getProId()&&null!=query.getProId()) {
            for(int i=0;i<query.getProId().intValue();i++) {
                Make make = new Make();
                make.setMakeCode(idGen.getSeqId("pro"+query.getProId()).toString());
                make.setMakeNum(10L);
                make.setStatus("0");
                make.setEmpId(query.getEmpId());
                make.setDelFlag("0");
                make.setCreateTime(LocalDateTime.now());
                make.setProName(query.getProName());
                makeMapper.insert(make);
                makeLst.add(make);
            }
        }
        return makeLst;
    }

}
