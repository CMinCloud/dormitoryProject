package com.dormitory.service.serviceImpl;

import com.dormitory.entity.DormitoryAdmin;
import com.dormitory.form.RuleForm;
import com.dormitory.mapper.DormitoryAdminMapper;
import com.dormitory.service.DormitoryAdminService;
import com.dormitory.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DormitoryAdminServiceImpl implements DormitoryAdminService {

    @Autowired
    private DormitoryAdminMapper dormitoryAdminMapper;

    @Override
    public ResultVO<DormitoryAdmin> Login(RuleForm ruleForm) {
        String username = ruleForm.getUsername();
        String password = ruleForm.getPassword();

        ResultVO<DormitoryAdmin> resultVO = new ResultVO<>();  // 创建结果值对象
        DormitoryAdmin dormitoryAdmin = dormitoryAdminMapper.selectDorAdminByUsername(username);
        // 输入值判空
        if(dormitoryAdmin == null){
            resultVO.setCode(-1);    //设置响应值为-1 ，代表用户名不正确无法查找到用户
        }
        else {
            if(password.equals(dormitoryAdmin.getPassword())){
//            对密码进行判定
                resultVO.setCode(0);     // 密码判定正确
                resultVO.setData(dormitoryAdmin);    //  存入登录用户数据
            }else {
                resultVO.setCode(-2);    // 密码错误
            }
        }

        return resultVO;
    }
}
