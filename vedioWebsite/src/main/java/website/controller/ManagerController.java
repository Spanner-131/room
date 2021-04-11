package website.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import website.common.entity.AjaxJson;
import website.common.entity.PageJson;
import website.pojo.Comment;
import website.pojo.CommentTemp;
import website.pojo.User;
import website.pojo.UserTemp;
import website.service.CommentService;
import website.service.CommentTempService;
import website.service.LoginService;
import website.service.UserTempService;

import java.util.List;

@RestController
@RequestMapping("/campus")
public class ManagerController {

    @Autowired
    UserTempService userTempService;

    @Autowired
    CommentTempService commentTempService;

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
        IPage<UserTemp> userTempPage = new Page<>(page,limit);
        IPage resultPage = userTempService.page(userTempPage, queryWrapper);
        List<UserTemp> list = resultPage.getRecords();
        result.setCode(0);
        result.setCount(userTempService.count(queryWrapper));
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
        IPage<CommentTemp> comtTempPage = new Page<>();
        IPage resultPage = commentTempService.page(comtTempPage, queryWrapper);
        List<CommentTemp> list = resultPage.getRecords();
        result.setCode(0);
        result.setCount(commentTempService.count(queryWrapper));
        result.setData(list);
        return result;
    }

    @RequestMapping("/agreeRegister")
    public AjaxJson agreeRegister(User user){
        AjaxJson result = new AjaxJson();
        try{
            loginService.save(user);
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
            userTempService.removeById(uid);
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
            commentService.save(comment);
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
            commentTempService.removeById(uid);
            result.setSuccess(true);
            result.setMessage("删除成功");
        }catch(Exception e){
            result.setSuccess(false);
            result.setMessage("删除失败");
        }
        return result;
    }
}
