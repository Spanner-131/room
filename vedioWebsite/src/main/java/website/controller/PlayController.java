package website.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import website.common.entity.AjaxJson;
import website.mapper.SubscriptionMapper;
import website.pojo.Subscription;
import website.service.SubscriptionService;

@RestController
@RequestMapping("/play")
public class PlayController {

    @Autowired
    SubscriptionService subscriptionService;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("play/play");
    }

    //界面信息通过homepage页面modelandview跳转并返回

    /**
    * operation:关注
    * param: 前台session.getItem获取用户userCode
    * */
    @RequestMapping("/subscribe")
    public AjaxJson subscribeUser(Subscription subscription){
        AjaxJson result = new AjaxJson();
        try{
            subscriptionService.addSubscription(subscription);
            result.setSuccess(true);
            result.setMessage("关注成功！");
        }catch(Exception e){
            result.setSuccess(false);
            result.setMessage("关注失败！");
        }
        return result;
    }
}
