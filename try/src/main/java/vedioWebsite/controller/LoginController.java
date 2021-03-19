package vedioWebsite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vedioWebsite.Pojo.User;
import vedioWebsite.mapper.LoginMapper;


@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    LoginMapper loginMapper;

    @RequestMapping("/hi")
    public String sayHi(){
        return "helloWorld";
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login/login");
    }

    @PostMapping("/check")
    public int checkUser(@Param("userName")String userName,@Param("password")String password){
//        User user = new User();
//        user.setUserName(userName);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_name",userName);
        User checkUser = loginMapper.selectOne(queryWrapper);
        if(checkUser.getPassword().equals(password)){
            return 1;
        }else{
            return 0;
        }
    }
}
