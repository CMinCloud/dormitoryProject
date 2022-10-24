package com.dormitory.service.serviceImpl;

import com.dormitory.entity.SystemAdmin;
import com.dormitory.form.RuleForm;
import com.dormitory.mapper.SystemAdminMapper;
import com.dormitory.service.SystemAdminService;
import com.dormitory.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SystemAdminServiceImpl implements SystemAdminService {

    @Autowired
    private SystemAdminMapper systemAdminMapper;

    @Override
    public ResultVO<SystemAdmin> login(RuleForm ruleForm) {
        String username = ruleForm.getUsername();
        String password = ruleForm.getPassword();

        ResultVO<SystemAdmin> resultVO = new ResultVO<>();  // 创建结果值对象

        SystemAdmin systemAdmin = systemAdminMapper.selectSysAdminByUsername(username);
        // 输入值判空
        if(systemAdmin == null){
            resultVO.setCode(-1);    //设置响应值为-1 ，代表用户名不正确无法查找到用户
        }
        else {
            if(password.equals(systemAdmin.getPassword())){
//            对密码进行判定
                resultVO.setCode(0);     // 密码判定正确
                resultVO.setData(systemAdmin);    //  存入登录用户数据
            }else {
                resultVO.setCode(-2);    // 密码错误
            }
        }
        return resultVO;
    }

}
