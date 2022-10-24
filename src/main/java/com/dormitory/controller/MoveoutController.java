package com.dormitory.controller;


import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.dormitory.form.SearchForm;
import com.dormitory.service.MoveoutService;
import com.dormitory.util.ResultVoUtil;
import com.dormitory.vo.ResultVO;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/moveout")
public class MoveoutController {

    @Autowired
    private MoveoutService moveoutService;

    // TODO: 2022/10/15 测试完成
    //学生列表迁出登记查询这个不需要缓存，可能刚刚迁出了再次查询需要刷新数据
//    @Cached(name = "studentList",key="#page",expire = 1000,cacheType = CacheType.LOCAL)
    @GetMapping ("/list/{page}/{size}")
    public ResultVO studentList(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return ResultVoUtil.success(moveoutService.studentList(page, size));
    }

    // TODO: 2022/10/15 测试完成
    //迁出列表
//    @Cached(name = "moveoutList",key="#page",expire = 1000,cacheType = CacheType.LOCAL)
    @GetMapping("/moveoutList/{page}/{size}")
    public ResultVO moveoutList(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return ResultVoUtil.success(this.moveoutService.moveoutList(page, size));
    }

    // TODO: 2022/10/15 测试完成
    //模糊查询学生信息
    @GetMapping("/search")
    public ResultVO studentSearch(SearchForm searchForm){
        return ResultVoUtil.success(this.moveoutService.studentSearch(searchForm));
    }


    // TODO: 2022/10/15 测试成功 
    //模糊查询迁出记录
    @GetMapping("/moveoutSearch")
    public ResultVO moveoutSearch(SearchForm searchForm){
        return ResultVoUtil.success(moveoutService.moveoutSearch(searchForm));
    }

    // TODO: 2022/10/15 测试成功 
    //迁出学生
    @PutMapping("/moveout/{id}/{reason}")
    public ResultVO moveout(@PathVariable("id") Integer id,@PathVariable("reason") String reason){
        Boolean moveout = this.moveoutService.MoveOut(id, reason);
        if(!moveout) return ResultVoUtil.fail();
        return ResultVoUtil.success(null);
    }

    @DeleteMapping("/delete/{id}")
    public ResultVO delete(@PathVariable("id") Integer id){
        return moveoutService.Delete(id) ? ResultVoUtil.success(null) : ResultVoUtil.fail();
    }
}
