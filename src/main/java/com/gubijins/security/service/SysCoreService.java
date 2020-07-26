package com.gubijins.security.service;

import com.google.common.collect.Lists;

import com.gubijins.security.config.RequestHolder;
import com.gubijins.security.mapper.SysAclMapper;
import com.gubijins.security.mapper.SysRoleAclMapper;
import com.gubijins.security.mapper.SysRoleUserMapper;
import com.gubijins.security.model.SysAcl;
import com.gubijins.security.model.SysUser;
import com.gubijins.security.util.JsonMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysCoreService {

    @Resource
    private SysAclMapper sysAclMapper;
    @Resource
    private SysRoleUserMapper sysRoleUserMapper;
    @Resource
    private SysRoleAclMapper sysRoleAclMapper;

    public List<SysAcl> getCurrentUserAclList() {
        int userId = RequestHolder.getCurrentUser().getId();
        return getUserAclList(userId);
    }

    public List<SysAcl> getRoleAclList(int roleId) {
        List<Integer> aclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(Lists.<Integer>newArrayList(roleId));
        if (CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclMapper.getByIdList(aclIdList);
    }

    public List<SysAcl> getUserAclList(int userId) {
        if (isSuperAdmin()) {
            return sysAclMapper.getAll();
        }
        List<Integer> userRoleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        List<Integer> userAclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(userRoleIdList);
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclMapper.getByIdList(userAclIdList);
    }

    public boolean isSuperAdmin() {
        //todo
//        // 这里是我自己定义了一个假的超级管理员规则，实际中要根据项目进行修改
//        // 可以是配置文件获取，可以指定某个用户，也可以指定某个角色
//        SysUser sysUser = RequestHolder.getCurrentUser();
//        return sysUser.getMail().contains("admin");
        return true;
    }
}
