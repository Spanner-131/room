package website;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *  @title:主程序类
 *  @author:hb
 *  @content:SpringBootApplication
 * */
@SpringBootApplication
//@MapperScan("website.mapper")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
