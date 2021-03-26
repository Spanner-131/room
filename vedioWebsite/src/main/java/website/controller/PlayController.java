package website.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import website.common.entity.AjaxJson;
import website.mapper.CommentMapper;
import website.pojo.*;
import website.service.CollectionService;
import website.service.CommentService;
import website.service.PointLikeService;
import website.service.SubscriptionService;

@RestController
@RequestMapping("/play")
public class PlayController {

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    PointLikeService pointLikeService;

    @Autowired
    CollectionService collectionService;

    @Autowired
    CommentService commentService;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("play/play");
    }

    //界面信息通过homepage页面modelandview跳转并返回

    /**
    * operation:关注or取消关注
    * param: 前台session.getItem获取用户userCode1,userCode2
    * */
    @RequestMapping("/subOrNot")
    public AjaxJson subscribeUser(Subscription subscription){
        AjaxJson result = new AjaxJson();
        int queryResult = subscriptionService.subOrNot(subscription);
        if(queryResult == 0){
            try{
                subscriptionService.save(subscription);
                result.setSuccess(true);
                result.setMessage("关注成功！");
            }catch(Exception e){
                result.setSuccess(false);
                result.setMessage("关注失败！");
            }
        }else{
            try{
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("USER_CODE1",subscription.getUserCode1());
                queryWrapper.eq("USER_CODE2",subscription.getUserCode2());
                //用继承的remove方法，需重新声明wrapper，不知道是假删除还是真删，测试过后决定是否修改
                subscriptionService.remove(queryWrapper);
                result.setSuccess(true);
                result.setMessage("取消关注");
            }catch(Exception e){
                result.setSuccess(false);
                result.setMessage("取消关注失败");
            }
        }
        return result;
    }

    /**
     * operation:点赞or取消点赞
     * param: 前台session.getItem获取用户userCode,vedioId
     * */
    @RequestMapping("/pointLikeOrNot")
    public AjaxJson pointlike(PointLike pointLike){
        AjaxJson result = new AjaxJson();
        try{
            pointLikeService.addPointLike(pointLike);
            result.setSuccess(true);
        }catch(Exception e){
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * operation:收藏or取消收藏
     * param: 前台session.getItem获取用户userCode,vedioId
     * */
    @RequestMapping("/collectOrNot")
    public AjaxJson addCollection(Collection collection){
        AjaxJson result = new AjaxJson();
        try{
            collectionService.addCollection(collection);
            result.setSuccess(true);
        }catch(Exception e){
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * operation:评价
     * param: 前台session.getItem获取用户headImg,userName,content,vedioId
     * */
    @RequestMapping("/comment")
    public AjaxJson addComment(Comment comment){
        AjaxJson result = new AjaxJson();
        try{
            commentService.addComment(comment);
            result.setSuccess(true);
        }catch(Exception e){
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * operation:展示评价
     * param: vedioId
     * */
    @RequestMapping("/showComment")
    public AjaxJson showComment(Vedio vedio){
        AjaxJson result = new AjaxJson();
        //三表联表查头像，时间，内容，降序排序
        return result;
    }

    /**
     * operation:删除通过的评价
     * param: commentId
     * */
    @RequestMapping("/delComment")
    public AjaxJson delComment(Comment comment){
        AjaxJson result = new AjaxJson();
        //逻辑删除与否
        return result;
    }
}
