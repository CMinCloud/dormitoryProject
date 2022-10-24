package com.dormitory.mapper;

import com.dormitory.entity.DormitoryAdmin;
import com.dormitory.entity.SystemAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SystemAdminMapper {

    @Select("select * from system_admin where username = #{username}")
    SystemAdmin selectSysAdminByUsername(String username);
}
