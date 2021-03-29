package videoWebsite;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import website.mapper.LoginMapper;

@SpringBootTest
public class MybatisPlusApplicationTests {

    @Autowired
    LoginMapper loginMapper;

    @Test
    public void contextLoads(){
    }
}
