package com.dormitory.mapper;

import com.dormitory.entity.DormitoryAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DormitoryAdminMapper {

    @Select("select * from dormitory_admin where username = #{username}")
    DormitoryAdmin selectDorAdminByUsername(String username);
}
