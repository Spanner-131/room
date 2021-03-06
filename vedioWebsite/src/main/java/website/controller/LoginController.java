package website.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import website.common.entity.AjaxJson;
import website.pojo.User;
import website.pojo.UserTemp;
import website.service.LoginService;
import website.service.UserTempService;

/**
 *  @title:loginController
 *  @author:hb
 *  @content:登录，修改信息页接口
 *  @createTime:2021/3/20
 *  @modifiedTime:2021/3/22
 * */
@RestController
@RequestMapping("/campus")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserTempService userTempService;

    //test interface
    @RequestMapping("/hi")
    public String hi(){
        return "helloWorld";
    }

    @RequestMapping("/login")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/login/login");
        return mv;
    }

    @RequestMapping("/enter")
    public int enter(@Param("userName")String userName,@Param("userCode")String userCode, @Param("password")String password){
         int result = loginService.checkUser(userName,userCode,password);
         return  result;
    }

    @RequestMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("/login/register");
    }

    //2021.4.4 modify
    @RequestMapping("/addUser")
    public AjaxJson addUser(UserTemp userTemp){
        AjaxJson result = new AjaxJson();
        try{
            userTempService.save(userTemp);
            result.setSuccess(true);
            result.setMessage("信息已录入，等待管理员审核通过");
        }catch(Exception e){
            result.setSuccess(false);
            result.setMessage("未录入成功，请重新操作");
        }
        return result;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(){
        return new ModelAndView("/login/edit");
    }

    @RequestMapping("/init")
    public AjaxJson queryUserMsg(User user){
        AjaxJson result = new AjaxJson();
        User userMsg = loginService.queryRequiredMsg(user);
        result.setData(userMsg);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/updateUser")
    public AjaxJson updateUser(User user){
        AjaxJson result = new AjaxJson();
        try{
            loginService.updateUser(user);
            result.setSuccess(true);
            result.setMessage("修改成功！");
        }catch(Exception e){
            result.setSuccess(false);
            result.setMessage("修改失败");
        }
        return result;
    }

}

