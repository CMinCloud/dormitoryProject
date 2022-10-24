package com.dormitory.controller;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.dormitory.form.RuleForm;
import com.dormitory.service.DormitoryAdminService;
import com.dormitory.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dormitoryAdmin")
public class DormitoryAdminController {

    @Autowired
    private DormitoryAdminService dormitoryAdminService;

    @Cached(name = "admin_login",key="#ruleForm",expire = 1000,cacheType = CacheType.LOCAL)
    @GetMapping("/login")
    public ResultVO login(RuleForm ruleForm){
        ResultVO resultVO = this.dormitoryAdminService.Login(ruleForm);
        return resultVO;
    }
}
