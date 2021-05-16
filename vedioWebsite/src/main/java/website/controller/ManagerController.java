package website.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import website.common.entity.AjaxJson;
import website.common.entity.PageJson;
import website.pojo.Comment;
import website.pojo.User;
import website.service.CommentService;
import website.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/campus")
public class ManagerController {

    @Autowired
    LoginService loginService;

    @Autowired
    CommentService commentService;

    @RequestMapping("/manager")
    public ModelAndView index(){
        return new ModelAndView("manager/manager");
    }

    @RequestMapping("/helloPage")
    public ModelAndView hello(){return new ModelAndView("manager/hello");}

    @RequestMapping("/tempRegis")
    public ModelAndView tempRegis(){return new ModelAndView("manager/registerTemp");}

    @RequestMapping("/tempComt")
    public ModelAndView tempComt(){return new ModelAndView("manager/commentTemp");}

    @RequestMapping("/userMng")
    public ModelAndView userMng(){return new ModelAndView("manager/user"); }

    @RequestMapping("/videoMng")
    public ModelAndView videoMng(){return new ModelAndView("manager/video");}

    @RequestMapping("/videoUploadMng")
    public ModelAndView videoUploadMng(){return new ModelAndView("manager/videoUploadMng");}

    @RequestMapping("/setRole/{id}")
    public ModelAndView setRole(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView("manager/setRole");
        mv.addObject("id",id);
        return mv;
    }

    @RequestMapping("/updateRole")
    public AjaxJson updateRole(HttpServletRequest request , HttpServletResponse response) {
        AjaxJson result = new AjaxJson();
        try {
            UpdateWrapper<User> uw = new UpdateWrapper<>();
            String role = request.getParameter("role");
            String id = request.getParameter("id");
            uw.eq("ID", id);
            QueryWrapper qw = new QueryWrapper();
            qw.eq("ID", id);
            User user = loginService.getOne(qw);
            user.setIsAdmin(Integer.parseInt(role));
            System.out.println(user);
            loginService.update(user, uw);
            result.setSuccess(true);
            result.setMessage("修改成功");
        }catch(Exception e){
            result.setSuccess(false);
            result.setMessage("修改失败");
        }
        return result;
    }

    @RequestMapping("/registerBox")
    public PageJson registerBox(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("userCode")String userCode,Integer page,Integer limit){
        PageJson result = new PageJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)){
            queryWrapper.between("CREATE_TIME",startTime,endTime);
        }
        if(!StringUtils.isEmpty(userCode)){
            queryWrapper.eq("USER_CODE",userCode);
        }
        queryWrapper.eq("IS_TEMP","1");
        IPage<User> userTempPage = new Page<>(page,limit);
        IPage resultPage = loginService.page(userTempPage, queryWrapper);
        List<User> list = resultPage.getRecords();
        result.setCode(0);
        result.setCount(loginService.count(queryWrapper));
        result.setData(list);
        return result;
    }

    @RequestMapping("/commentBox")
    public PageJson commentBox(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("userCode")String userCode,Integer page,Integer limit){
        PageJson result = new PageJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)){
            queryWrapper.between("CREATE_TIME",startTime,endTime);
        }
        if(!StringUtils.isEmpty(userCode)){
            queryWrapper.eq("USER_CODE",userCode);
        }
        queryWrapper.eq("IS_TEMP","1");
        IPage<Comment> comtTempPage = new Page<>();
        IPage resultPage = commentService.page(comtTempPage, queryWrapper);
        List<Comment> list = resultPage.getRecords();
        result.setCode(0);
        result.setCount(commentService.count(queryWrapper));
        result.setData(list);
        return result;
    }

    @RequestMapping("/userBox")
    public PageJson userBox(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("userCode")String userCode,@Param("userName")String userName,Integer page,Integer limit){
        PageJson result = new PageJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)){
            queryWrapper.between("CREATE_TIME",startTime,endTime);
        }
        if(!StringUtils.isEmpty(userCode)){
            queryWrapper.eq("USER_CODE",userCode);
        }
        if(!StringUtils.isEmpty(userName)){
            queryWrapper.eq("USER_Name",userName);
        }
        queryWrapper.eq("IS_TEMP","0");
        IPage<User> userPage = new Page<>();
        IPage resultPage = loginService.page(userPage, queryWrapper);
        List<User> list = resultPage.getRecords();
        result.setCode(0);
        result.setCount(loginService.count(queryWrapper));
        result.setData(list);
        return result;
    }

    @RequestMapping("/agreeRegister")
    public AjaxJson agreeRegister(User user){
        AjaxJson result = new AjaxJson();
        try{
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("ID",user.getId());
            user.setIsTemp(0);
            loginService.update(user,updateWrapper);
            result.setSuccess(true);
            result.setMessage("用户注册通过！");
        }catch(Exception e){
            result.setSuccess(false);
            result.setMessage("通过失败，请重新操作");
        }
        return result;
    }

    @RequestMapping("/delRegister")
    public AjaxJson delRegister(@Param("id")String id){
        AjaxJson result = new AjaxJson();
        try{
            long uid = Long.parseLong(id);
            loginService.removeById(uid);
            result.setSuccess(true);
            result.setMessage("删除成功");
        }catch(Exception e){
            result.setSuccess(false);
            result.setMessage("删除失败");
        }
        return result;
    }

    @RequestMapping("/agreeComment")
    public AjaxJson agreeComment(Comment comment){
        AjaxJson result = new AjaxJson();
        try{
            UpdateWrapper<Comment> updateWrapper = new UpdateWrapper();
            updateWrapper.eq("ID",comment.getId());
//            System.out.println(comment.getId());
            comment.setIsTemp(0);
            commentService.update(comment,updateWrapper);
            result.setSuccess(true);
            result.setMessage("评论通过！");
        }catch(Exception e){
            result.setSuccess(false);
            result.setMessage("评论通过失败，请重新操作");
        }
        return result;
    }

    //play界面也有个delComment接口，故此处修改
    @RequestMapping("/delComt")
    public AjaxJson delComment(@Param("id")String id){
        AjaxJson result = new AjaxJson();
        try{
            long uid = Long.parseLong(id);
            commentService.removeById(uid);
            result.setSuccess(true);
            result.setMessage("删除成功");
        }catch(Exception e){
            result.setSuccess(false);
            result.setMessage("删除失败");
        }
        return result;
    }
}
