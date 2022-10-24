package com.dormitory.service;

import com.dormitory.entity.Dormitory;
import com.dormitory.entity.DormitoryAdmin;
import com.dormitory.entity.Student;
import com.dormitory.entity.SystemAdmin;
import com.dormitory.form.RuleForm;
import com.dormitory.vo.ResultVO;

public interface SystemAdminService {

    ResultVO<SystemAdmin> login(RuleForm ruleForm);

}
