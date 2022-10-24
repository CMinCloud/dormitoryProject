package com.dormitory.mapper;

import com.dormitory.entity.Moveout;
import com.dormitory.entity.Student;
import com.dormitory.form.SearchForm;
import com.dormitory.vo.MoveoutVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MoveoutMapper {

    @Select("select count(*) from student where state = '入住'")
    Long getStudentTotal();     //查询学生总数

    List<Student> listStudentByPage(int page, int size);   //分页展示学生列表


    List<Student> studentSearch(SearchForm searchForm);    // 分页模糊查询学生列表
    Long getStuSearchTotal(SearchForm searchForm);          //获取模糊查询的总数

    List<MoveoutVO> moveoutRecordSearch(SearchForm searchForm);    // 分页模糊查询迁出记录

    Long getRecordSearchTotal(SearchForm searchForm);  //获取模糊查询的总数

    @Select("select dormitory.id from dormitory,student where student.dormitory_id = dormitory.id\n" +
            "and student.id = #{stuId}")
    Integer findDormitoryIdByStutID(Integer stuId);   // 根据学生id查找宿舍id

    @Select("select dormitory.name from dormitory where dormitory.id = #{dorId}")
    String findDorNameByDorId(Integer dorId);

    //学生迁出，新增迁出表
    @Insert("insert into moveout(student_id, dormitory_id, reason, create_date) values (#{studentId},#{dormitoryId},#{reason},#{createDate})")
    Integer moveout(Moveout moveout);

//    查询moveout表总数
    @Select("select count(*) from moveout")
    Long getMoveoutListTotal();

//    查询moveoutList
    @Select("select * from moveout limit #{page},#{size} ")
    List<Moveout> moveoutList(int page,int size);

//    根据id查询学生姓名
    @Select("select name from student where id = #{stuId}")
    String findStuNameByStuId(Integer stuId);

//    根据学生id查询宿舍id
    @Select("select dormitory_id from student where id = #{stuId} ")
     Integer getStuNameByStuId(Integer stuId);

//    修改迁出学生的入住状态
    @Update("update student set state = '迁出' where id = #{stuId} ")
    Integer updateStateByStuId(Integer stuId);

//    删除迁出表的学生记录
    @Delete("delete from moveout where student_id = #{stuId}")
    Integer deleteByStuId(Integer stuId);

}
