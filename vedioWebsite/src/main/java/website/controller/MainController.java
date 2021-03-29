package website.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import website.common.entity.AjaxJson;
import website.pojo.Subscription;
import website.service.SubscriptionService;
import website.service.VideoService;
import website.vo.VideoVo;

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
    VideoService videoService;

    @Autowired
    SubscriptionService subscriptionService;

    @RequestMapping("/homepage")
    public ModelAndView homePage(){
        return new ModelAndView("/main/main");
    }

    @RequestMapping("/getVedio")
    public AjaxJson getVedio(){
        AjaxJson result = new AjaxJson();
        List<VideoVo> vedioList = videoService.getVideoInfo();
        result.setSuccess(true);
        result.setData(vedioList);
        return result;
    }

    @RequestMapping("/getVedioBySpt")
    public AjaxJson getVedioBySpt(@Param("userCode")String userCode){
        AjaxJson result = new AjaxJson();
        List<Subscription> subscriptionList = subscriptionService.getUserCodeList(userCode);
        List<VideoVo> vedioList = videoService.getVideoSubscribed(subscriptionList);
        result.setSuccess(true);
        result.setData(vedioList);
        return result;
    }

}
