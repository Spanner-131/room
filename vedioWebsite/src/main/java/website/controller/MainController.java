package website.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import website.common.entity.AjaxJson;
import website.mapper.LoginMapper;
import website.pojo.Subscription;
import website.pojo.User;
import website.service.SubscriptionService;
import website.service.VideoService;
import website.vo.VideoVo;

import java.util.ArrayList;
import java.util.List;

/**
 *  @title:MainController
 *  @author:hb
 *  @content:主页接口
 *  @createTime:2021/3/24
 *  @modifiedTime:2021/3/24
 * */
@RestController
@RequestMapping("/campus")
public class MainController {
    @Autowired
    LoginMapper loginMapper;

    @Autowired
    VideoService videoService;

    @Autowired
    SubscriptionService subscriptionService;

    @RequestMapping("/homepage")
    public ModelAndView homepage(){
        return new ModelAndView("/main/main");
    }

    @RequestMapping("/getRecdVideo")
    public AjaxJson getRecdVideo(@Param("userCode")String userCode){
        AjaxJson result = new AjaxJson();
        try{
            List<VideoVo> list = new ArrayList();
            List<VideoVo> recdVideoByTag = videoService.getRecdVideoByTag(userCode);
            List<VideoVo> comRec = videoService.commonRecdVideo();
            for (VideoVo videoVo : recdVideoByTag) {
                list.add(videoVo);
            }
            for (VideoVo vv : list){
                for(VideoVo videoVo : comRec){
                    if(!vv.getId().equals(videoVo.getId())){
                        list.add(videoVo);
                    }
                }
            }
            result.setSuccess(true);
            result.setData(list);
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/getVideoBySpt")
    public AjaxJson getVideoBySpt(@Param("userCode")String userCode){
        AjaxJson result = new AjaxJson();
        List<Subscription> subscriptionList = subscriptionService.getUserCodeList(userCode);
        List<VideoVo> videoList = videoService.getVideoSubscribed(subscriptionList);
        List<VideoVo> videoListPro = videoService.transTime(videoList);
        result.setSuccess(true);
        result.setData(videoListPro);
        return result;
    }

    @RequestMapping("/getCurrentUserInfo")
    public AjaxJson getCurrentUserInfo(@Param("userCode")String userCode) {
        AjaxJson result = new AjaxJson();
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("USER_CODE",userCode);
            User user = loginMapper.selectOne(queryWrapper);
            result.setData(user);
            result.setSuccess(true);
        }catch(Exception e) {
            result.setSuccess(false);
        }
        return result;
    }
}
