package com.dormitory.util;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 工具类，创建当前时间
 */
public class CommonUtil {
    public static String createDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    public static Integer computePage(Integer page,Integer size){
        return (page-1)*size;
    }

}
