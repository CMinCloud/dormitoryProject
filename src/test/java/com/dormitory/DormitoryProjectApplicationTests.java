package com.dormitory;

import com.dormitory.entity.Moveout;
import com.dormitory.entity.Student;
import com.dormitory.form.SearchForm;
import com.dormitory.mapper.MoveoutMapper;
import com.dormitory.util.CommonUtil;
import com.dormitory.vo.MoveoutVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class DormitoryProjectApplicationTests {


    @Autowired
    private MoveoutMapper moveoutMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void listStudentByPage(){
        int page = (1-1)*3;   //要查第二页的
        List<Student> students = moveoutMapper.listStudentByPage(page, 3);
        System.out.println(students);
    }


    @Test
    void searchBySearchForm(){
        int page = (1-1)*3;
        SearchForm searchForm = new SearchForm("studentName","李",page,3);
        List<Student> students = moveoutMapper.studentSearch(searchForm);
        System.out.println(students);
    }


    @Test
    void moveoutRecordSearch(){
        int page = (1-1)*3;
        SearchForm searchForm = new SearchForm("dormitoryName","",page,3);
        List<MoveoutVO> moveoutVOList = moveoutMapper.moveoutRecordSearch(searchForm);
        System.out.println(moveoutVOList);
    }


    @Test
    void moveout(){
        Moveout moveoutForm = new Moveout(-1,25,3,"无理由", CommonUtil.createDate());
        moveoutMapper.moveout(moveoutForm);
    }

    @Test
    void moveoutList(){
        int page = (2-1)*3;
        List<Moveout> moveouts = moveoutMapper.moveoutList(page,3);
        System.out.println(moveouts);
    }

    @Test
    void moveoutRecordTotal(){
        int page = (1-1)*3;
        SearchForm searchForm = new SearchForm("dormitoryName","",page,3);
        Long total = moveoutMapper.getRecordSearchTotal(searchForm);
        System.out.println(total);
    }
}
