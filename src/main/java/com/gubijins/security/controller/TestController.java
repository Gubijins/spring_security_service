package com.gubijins.security.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gubijins.security.common.ApplicationContextHelper;
import com.gubijins.security.common.JsonData;
import com.gubijins.security.exception.ParamException;
import com.gubijins.security.exception.PermissionException;
import com.gubijins.security.mapper.SysAclModuleMapper;
import com.gubijins.security.model.SysAclModule;
import com.gubijins.security.param.TestVo;
import com.gubijins.security.util.BeanValidator;
import com.gubijins.security.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：gubijins
 * @date ：Created in 2020/6/26 18:57
 */

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
        //throw new PermissionException("test exception");
        //throw new RuntimeException("test exception");
        return JsonData.success("hello, permission");//这是正常
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamException {
        log.info("validate");
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule module = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(module));
        BeanValidator.check(vo);
        return JsonData.success("test validate");
    }
}
