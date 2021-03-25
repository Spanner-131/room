package website.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import website.common.entity.AjaxJson;
import website.pojo.Subscription;
import website.pojo.Vedio;
import website.service.SubscriptionService;
import website.service.VedioService;
import website.vo.VedioVo;

import java.util.List;

/**
 *  @title:MainController
 *  @author:hb
 *  @content:主页接口
 *  @createTime:2021/3/24
 *  @modifiedTime:2021/3/24
 * */
@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    VedioService vedioService;

    @Autowired
    SubscriptionService subscriptionService;

    @RequestMapping("/homePage")
    public ModelAndView homePage(){
        return new ModelAndView("/main/main");
    }

    @RequestMapping("/getVedio")
    public AjaxJson getVedio(){
        AjaxJson result = new AjaxJson();
        List<VedioVo> vedioList = vedioService.getVedioInfo();
        result.setSuccess(true);
        result.setData(vedioList);
        return result;
    }

    @RequestMapping("/getVedioBySpt")
    public AjaxJson getVedioBySpt(@Param("userCode")String userCode){
        AjaxJson result = new AjaxJson();
        List<Subscription> subscriptionList = subscriptionService.getUserCodeList(userCode);
        List<VedioVo> vedioList = vedioService.getVedioSubscribed(subscriptionList);
        result.setSuccess(true);
        result.setData(vedioList);
        return result;
    }

}
