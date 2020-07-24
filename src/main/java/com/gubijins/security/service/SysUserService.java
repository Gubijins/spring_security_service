package com.gubijins.security.service;

import com.google.common.base.Preconditions;
import com.gubijins.security.beans.PageQuery;
import com.gubijins.security.beans.PageResult;
import com.gubijins.security.exception.ParamException;
import com.gubijins.security.mapper.SysUserMapper;
import com.gubijins.security.model.SysUser;
import com.gubijins.security.param.UserParam;
import com.gubijins.security.util.BeanValidator;
import com.gubijins.security.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    public void save(UserParam param) {
        BeanValidator.check(param);
        if(checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if(checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        String password="12345678";
        String encryptedPassword = MD5Util.encrypt(password);
        SysUser user = SysUser.builder().username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail())
                .password(encryptedPassword).deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        user.setOperator("System");//TODO
        user.setOperateIp("127.0.0.1");//TODO
        user.setOperateTime(new Date());
        sysUserMapper.insertSelective(user);
        //sysLogService.saveUserLog(null, user);
    }

    public void update(UserParam param) {
        BeanValidator.check(param);
        if(checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if(checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在");
        SysUser after = SysUser.builder().id(param.getId()).username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail())
                .deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        after.setOperator("System");//TODO
        after.setOperateIp("127.0.0.1");//TODO
        after.setOperateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(after);
        //sysLogService.saveUserLog(before, after);
    }

    public boolean checkEmailExist(String mail, Integer userId) {
        //return sysUserMapper.countByMail(mail, userId) > 0;
        return false;
    }

    public boolean checkTelephoneExist(String telephone, Integer userId) {
        //return sysUserMapper.countByTelephone(telephone, userId) > 0;
        return false;
    }

    public SysUser findByKeyword(String keyword) {
        return sysUserMapper.findByKeyword(keyword);
    }

    public PageResult<SysUser> getPageByDeptId(int deptId, PageQuery page) {
        BeanValidator.check(page);
        int count = sysUserMapper.countByDeptId(deptId);
        if (count > 0) {
            List<SysUser> list = sysUserMapper.getPageByDeptId(deptId, page);
            return PageResult.<SysUser>builder().total(count).data(list).build();
        }
        return PageResult.<SysUser>builder().build();


    }

//    public List<SysUser> getAll() {
//        return sysUserMapper.getAll();
//    }
}
