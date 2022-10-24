package com.dormitory.service;

import com.dormitory.form.SearchForm;
import com.dormitory.vo.PageVO;
import org.apache.ibatis.annotations.Mapper;

public interface MoveoutService {

     PageVO studentList(Integer page, Integer size);

     PageVO moveoutList(Integer page, Integer size);

     PageVO studentSearch(SearchForm searchForm);

     PageVO moveoutSearch(SearchForm searchForm);

     Boolean MoveOut(Integer id, String reason);

     Boolean Delete(Integer stuId);


}
