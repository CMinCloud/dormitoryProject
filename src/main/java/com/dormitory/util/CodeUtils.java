package com.dormitory.util;

import org.springframework.stereotype.Component;


/**
 * 生成验证码
 */
@Component
public class CodeUtils {

    private String [] patch = {"000000","00000","0000","000","00","0",""};

    public String generator(String tele){
        int hash = tele.hashCode();     //获得的hash密钥为9位
        int encryption = 20206666;    //设置加密密钥
        long result = hash ^ encryption;
        long nowTime = System.currentTimeMillis();
        result = result ^ nowTime;      //保证了随机性(不重复性)
        long code = result % 1000000;   //除去前六位
        code = Math.abs(code);          //返回正整数
        String codeStr = code + "";
        int len = codeStr.length();
        return patch[len] + codeStr;
    }

/*//    该方法不能放在Service实现类中,否则spring容器的注解无法生效
    public String get(String tele){
        return null;    //根据缓存原理:如果没有值存入null,如果smsCode有值返回对应的tele
    }*/

//    public static void main(String[] args) {
//        System.out.println(new CodeUtils().generator("18866668888"));
//    }

}
