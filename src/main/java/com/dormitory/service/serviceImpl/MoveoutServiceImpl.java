package com.dormitory.service.serviceImpl;

import com.dormitory.entity.Moveout;
import com.dormitory.entity.Student;
import com.dormitory.form.SearchForm;
import com.dormitory.mapper.MoveoutMapper;
import com.dormitory.service.MoveoutService;
import com.dormitory.util.CommonUtil;
import com.dormitory.vo.MoveoutVO;
import com.dormitory.vo.PageVO;
import com.dormitory.vo.StudentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoveoutServiceImpl implements MoveoutService {

    @Autowired
    private MoveoutMapper moveoutMapper;

    /**
     * 封装学生列表
     *
     * @param page
     * @param size
     * @return pageVO
     */
    public PageVO studentList(Integer page, Integer size) {
        page = CommonUtil.computePage(page, size);      //修改的当前页页码为数据库位置
        List<Student> studentList = moveoutMapper.listStudentByPage(page, size);
        Long total = moveoutMapper.getStudentTotal();

        List<StudentVO> studentVOList = new ArrayList<>();
//        将studentList 封装为studentVOList
        for (Student student : studentList) {
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(student, studentVO);
//            根据宿舍id获取宿舍名称
            String dorName = moveoutMapper.findDorNameByDorId(student.getDormitoryId());
            studentVO.setDormitoryName(dorName);
//            填入voList
            studentVOList.add(studentVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setData(studentVOList);
        pageVO.setTotal(total);
        return pageVO;
    }

    /**
     * 迁出学生列表
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageVO moveoutList(Integer page, Integer size) {
        page = CommonUtil.computePage(page, size);      //修改的当前页页码为数据库位置
        List<Moveout> moveoutList = moveoutMapper.moveoutList(page, size);
        Long total = moveoutMapper.getMoveoutListTotal();

        List<MoveoutVO> moveoutVOList = new ArrayList<>();
//        将studentList 封装为studentVOList
        for (Moveout moveout : moveoutList) {

            MoveoutVO moveoutVO = new MoveoutVO();
            BeanUtils.copyProperties(moveout, moveoutVO);
            String stuName = moveoutMapper.findStuNameByStuId(moveout.getStudentId());
            String dorName = moveoutMapper.findDorNameByDorId(moveout.getDormitoryId());
            //            根据学生id获取学生姓名
            moveoutVO.setStudentName(stuName);
            //            根据宿舍id获取宿舍名称
            moveoutVO.setDormitoryName(dorName);
            //            填入voList
            moveoutVOList.add(moveoutVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setData(moveoutVOList);
        pageVO.setTotal(total);
        return pageVO;
    }

    /**
     * 学生列表模糊查询
     *
     * @param searchForm
     * @return
     */
    @Override
    public PageVO studentSearch(SearchForm searchForm) {
//        重置page
        Integer page = (searchForm.getPage() - 1) * searchForm.getSize();
        searchForm.setPage(page);

        List<Student> studentList = moveoutMapper.studentSearch(searchForm);
        List<StudentVO> studentVOList = new ArrayList<>();
//        将studentList 封装为studentVOList
        for (Student student : studentList) {
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(student, studentVO);
//            根据宿舍id获取宿舍名称
            String dorName = moveoutMapper.findDorNameByDorId(student.getDormitoryId());
            studentVO.setDormitoryName(dorName);
//            填入voList
            studentVOList.add(studentVO);
        }
        Long total = moveoutMapper.getStuSearchTotal(searchForm);
        PageVO pageVO = new PageVO();
        pageVO.setData(studentVOList);
        pageVO.setTotal(total);
        return pageVO;
    }

    /**
     * 迁出列表模糊查询
     *
     * @param searchForm
     * @return
     */
    @Override
    public PageVO moveoutSearch(SearchForm searchForm) {
        //        重置page
        Integer page = (searchForm.getPage() - 1) * searchForm.getSize();
        searchForm.setPage(page);
        List<MoveoutVO> moveoutVOList = moveoutMapper.moveoutRecordSearch(searchForm);

//        获取查询总数
        Long total = moveoutMapper.getRecordSearchTotal(searchForm);
        PageVO pageVO = new PageVO();
        pageVO.setData(moveoutVOList);
        pageVO.setTotal(total);
        return pageVO;
    }

    /**
     * 迁出学生
     *
     * @param id
     * @param reason
     * @return
     */
    @Override
    public Boolean MoveOut(Integer id, String reason) {
        Moveout moveout = new Moveout();
        Integer dorId = moveoutMapper.findDormitoryIdByStutID(id);
//        填充迁出信息
        moveout.setStudentId(id);
        moveout.setDormitoryId(dorId);
        moveout.setReason(reason);
        moveout.setCreateDate(CommonUtil.createDate());

        Integer flag = moveoutMapper.moveout(moveout);
        if(flag ==1) {
//            迁出操作成功,修改学生信息的入住信息为迁出
            moveoutMapper.updateStateByStuId(id);
        }
        return flag == 1 ;
    }

    /**
     * 删除迁出表记录
     * @param stuId
     * @return
     */
    @Override
    public Boolean Delete(Integer stuId) {
       return moveoutMapper.deleteByStuId(stuId) == 1;
    }
}
