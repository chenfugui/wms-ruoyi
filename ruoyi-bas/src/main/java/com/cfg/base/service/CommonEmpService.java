package com.cfg.base.service;

import java.util.List;
import java.time.LocalDateTime;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.base.dto.EmpUserDTO;
import com.cfg.enums.UserRegType;
import com.cfg.idgen.util.OperatorUtils;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.CommonEmpMapper;
import com.ruoyi.common.core.domain.entity.CommonEmp;
import com.cfg.base.pojo.query.CommonEmpQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单位信息Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class CommonEmpService {
    @Autowired
    private CommonEmpMapper commonEmpMapper;

    @Autowired
    private SysDeptMapper deptMapper;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询单位信息
     *
     * @param empId 单位信息主键
     * @return 单位信息
     */
    public CommonEmp selectByEmpId(Long empId) {
        return commonEmpMapper.selectById(empId);
    }

    /**
     * 查询单位信息列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 单位信息
     */
    public List<CommonEmp> selectList(CommonEmpQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<CommonEmp> qw = new QueryWrapper<>();
        qw.eq("del_flag","0");
        String empId = query.getEmpId();
        if (!StringUtils.isEmpty(empId)) {
            qw.eq("emp_id", empId);
        }
        String empCode = query.getEmpCode();
        if (!StringUtils.isEmpty(empCode)) {
            qw.eq("emp_code", empCode);
        }
        String empNameLike = query.getEmpNameLike();
        if (!StringUtils.isEmpty(empNameLike)) {
            qw.like("emp_name", empNameLike);
        }
        Long parentId = query.getParentId();
        if (parentId != null) {
            qw.eq("parent_id", parentId);
        }
        String ancestors = query.getAncestors();
        if (!StringUtils.isEmpty(ancestors)) {
            qw.eq("ancestors", ancestors);
        }
        Long orderNum = query.getOrderNum();
        if (orderNum != null) {
            qw.eq("order_num", orderNum);
        }
        return commonEmpMapper.selectList(qw);
    }

    /**
     * 新增单位信息
     *
     * @param commonEmp 单位信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insert(CommonEmp commonEmp) {
        commonEmp.setDelFlag("0");
        commonEmp.setCreateTime(LocalDateTime.now());
        //return commonEmpMapper.insert(commonEmp);
        OperatorUtils.setCreateInfo(commonEmp);
        int i = commonEmpMapper.insertEmp(commonEmp);
        if(null==commonEmp.getParentId()||0==commonEmp.getParentId()){
            SysDept dept = new SysDept();
            dept.setEmpId(commonEmp.getEmpId());
            dept.setDeptName(commonEmp.getEmpName());
            dept.setOrderNum("0");
            dept.setAncestors("0");
            dept.setDelFlag(commonEmp.getDelFlag());
            OperatorUtils.setCreateInfo(dept);
            deptMapper.insertDept(dept);
        }

        /*SysPost sysPost = new SysPost();
        sysPost.setEmpId(commonEmp.getEmpId());
        sysPost.setPostCode(StringUtils.leftPad(String.valueOf(commonEmp.getEmpId()),6,"0"));
        sysPost.setPostName(commonEmp.getEmpName());*/
         return i;
    }

    /**
     * 修改单位信息
     *
     * @param commonEmp 单位信息
     * @return 结果
     */
    public int update(CommonEmp commonEmp) {
        OperatorUtils.setUpdateInfo(commonEmp);
        return commonEmpMapper.updateById(commonEmp);
    }

    /**
     * 批量删除单位信息
     *
     * @param empIds 需要删除的单位信息主键
     * @return 结果
     */
    public int deleteByEmpIds(Long[] empIds) {
        return commonEmpMapper.updateDelFlagByIds(empIds);
    }

    /**
     * 删除单位信息信息
     *
     * @param empId 单位信息主键
     * @return 结果
     */
    public int deleteByEmpId(Long empId) {
        Long[] empIds = {empId};
        return commonEmpMapper.updateDelFlagByIds(empIds);
    }

    //根据EmpUserDTO创建企业及用户
    @Transactional(rollbackFor = Exception.class)
    public int insertRegUser(EmpUserDTO empUserDTO) {
        Assert.notNull(empUserDTO, "注册信息不能为空");
        SysUser sysUser = new SysUser();
        sysUser.setUserName(empUserDTO.getUserName());
        sysUser.setNickName(empUserDTO.getNickName());
        sysUser.setPhonenumber(empUserDTO.getPhonenumber());
        //加密密码
        sysUser.setPassword(SecurityUtils.encryptPassword(empUserDTO.getPassword()));
        sysUser.setStatus("0");
        sysUser.setDelFlag("0");
        sysUser.setCreateTime(LocalDateTime.now());
        if(UserRegType.EMP_BOSS.getCode().equals(empUserDTO.getRegType())){
            //创建企业
            CommonEmp commonEmp = new CommonEmp();
            commonEmp.setEmpName(empUserDTO.getEmpName());
            commonEmp.setXzqhCode(empUserDTO.buildXzqhCode());
            commonEmp.setAddr(empUserDTO.getAddress());
            commonEmp.setInviteCode(empUserDTO.getInviteCode());
            commonEmp.setCreateTime(LocalDateTime.now());
            commonEmp.setDelFlag("0");
            commonEmp.setCreateTime(LocalDateTime.now());
            commonEmpMapper.insertEmp(commonEmp);
            sysUser.setEmpId(commonEmp.getEmpId());
            if(null==commonEmp.getParentId()||0==commonEmp.getParentId()){
                SysDept dept = new SysDept();
                dept.setEmpId(commonEmp.getEmpId());
                dept.setDeptName(commonEmp.getEmpName());
                dept.setOrderNum("0");
                dept.setAncestors("0");
                dept.setDelFlag(commonEmp.getDelFlag());
                dept.setCreateTime(LocalDateTime.now());
                deptMapper.insertDept(dept);
            }
            sysUser.setRoleIds(new Long[]{102L});
        }
        if(UserRegType.EMP_WORKER.getCode().equals(empUserDTO.getRegType())){
           sysUser.setEmpId(Long.valueOf(empUserDTO.getEmpId()));
           CommonEmp commonEmp = commonEmpMapper.selectById(sysUser.getEmpId());
           if(null==commonEmp){
               throw new RuntimeException("企业码不存在");
           }
            sysUser.setRoleIds(new Long[]{100L});
        }

        return sysUserService.insertUser(sysUser);
    }
}
