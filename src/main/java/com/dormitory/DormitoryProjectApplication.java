package com.dormitory;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "com.dormitory")  //需要与上面的开启注解结合使用
@SpringBootApplication
public class DormitoryProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DormitoryProjectApplication.class, args);
    }

}
