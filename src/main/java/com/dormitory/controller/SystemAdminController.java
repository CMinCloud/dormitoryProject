package com.dormitory.controller;


import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.dormitory.form.RuleForm;
import com.dormitory.service.SystemAdminService;
import com.dormitory.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/systemAdmin")
public class SystemAdminController {

    @Autowired
    private SystemAdminService service;

    @Cached(name = "admin_login",key="#ruleForm",expire = 1000,cacheType = CacheType.LOCAL)
    @GetMapping("/login")
    public ResultVO login(RuleForm ruleForm){
        return service.login(ruleForm);
    }
}
