package com.dormitory.service;


import com.dormitory.entity.DormitoryAdmin;
import com.dormitory.form.RuleForm;
import com.dormitory.vo.ResultVO;

public interface DormitoryAdminService {

    ResultVO<DormitoryAdmin> Login(RuleForm ruleForm);
}
