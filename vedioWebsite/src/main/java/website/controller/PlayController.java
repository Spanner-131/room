package website.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import website.common.entity.AjaxJson;
import website.pojo.*;
import website.service.*;
import website.vo.VideoVo;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/campus")
public class PlayController {

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    PointLikeService pointLikeService;

    @Autowired
    CollectionService collectionService;

    @Autowired
    CommentService commentService;

    @Autowired
    CommentTempService commentTempService;

    @Autowired
    VideoService videoService;

    /**
     * operation:跳转播放页面
     * param: 通过pathVariable传递videoId返回前台
     * */
    @RequestMapping("/play/{videoId}")
    public ModelAndView index(@PathVariable("videoId")String videoId){
        ModelAndView mv = new ModelAndView("play/play");
        mv.addObject("videoId",videoId);
        return mv;
    }

    /**
     * operation:获取视频信息
     * param: 返回信息中评价信息须检验
     * */
    //界面信息通过homepage页面modelandview跳转并返回
    @RequestMapping("/getVideo")
    public AjaxJson getVideo(@Param("id")String id,@Param("currentUserCode")String currentUserCode){
        AjaxJson result = new AjaxJson();
        System.out.println("当前用户id" + currentUserCode);
        try {
            VideoVo vv = videoService.getPlayInfo(id,currentUserCode);
            result.setSuccess(true);
            result.setData(vv);
        }catch(Exception e){
            result.setSuccess(false);
        }
        return result;
    }

    /**
    * operation:关注or取消关注
    * param: 前台session.getItem获取用户userCode1,userCode2
    * ps: 此处表内新增的数据deleted应该是1
    * */
    @RequestMapping("/subOrNot")
    public AjaxJson subscribeUser(Subscription subscription){
        AjaxJson result = new AjaxJson();
        //test使用
        //subscription.setUserCode1("20191111120");
        int queryResult = subscriptionService.subOrNot(subscription);
        if(queryResult == 1 || queryResult == -1){
            try{
                if(queryResult == 1) {
                    subscriptionService.reSubscribe(subscription);
                }else{
                    subscriptionService.save(subscription);
                }
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
                //Q:用继承的remove方法，需重新声明wrapper，不知道是假删除还是真删，测试过后决定是否修改
                //A:2021.04.04 结果为逻辑删除，已修改逻辑
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
     * param: 前台session.getItem获取用户userCode,videoId
     * */
    @RequestMapping("/pointLikeOrNot")
        public AjaxJson pointlike(PointLike pointLike){
            AjaxJson result = new AjaxJson();
            int checkExisted = pointLikeService.likeOrNot(pointLike);
            if(checkExisted == 1 || checkExisted == -1){
                try{
                    if(checkExisted == 1){
                        pointLikeService.reLike(pointLike);
                    }else{
                        pointLikeService.save(pointLike);
                    }
                    result.setSuccess(true);
                    result.setMessage("点赞成功");
                }catch(Exception e){
                    result.setSuccess(false);
                    result.setMessage("点赞失败");
                }
            }else{
                try{
                    Map removeMap = new HashMap();
                    removeMap.put("USER_CODE",pointLike.getUserCode());
                    removeMap.put("VIDEO_ID",pointLike.getVideoId());
                    pointLikeService.removeByMap(removeMap);
                    result.setSuccess(true);
                    result.setMessage("取消点赞成功");
                }catch(Exception e){
                    result.setSuccess(false);
                    result.setMessage("取消点赞失败");
                }
            }
            return result;
        }

    /**
     * operation:收藏or取消收藏
     * param: 前台session.getItem获取用户userCode,videoId
     * */
    @RequestMapping("/collectOrNot")
    public AjaxJson addCollection(Collection collection){
        AjaxJson result = new AjaxJson();
        int checkColt = collectionService.collectOrNot(collection);
        if(checkColt == 1 || checkColt == -1){
            try{
                if(checkColt == 1){
                    collectionService.reColt(collection);
                }else{
                    collectionService.save(collection);
                }
                result.setSuccess(true);
                result.setMessage("收藏成功");
            }catch(Exception e){
                result.setSuccess(false);
                result.setMessage("收藏失败");
            }
        }else{
            try{
                Map removeMap = new HashMap();
                removeMap.put("USER_CODE",collection.getUserCode());
                removeMap.put("VIDEO_ID",collection.getVideoId());
                collectionService.removeByMap(removeMap);
                result.setSuccess(true);
                result.setMessage("取消收藏成功");
            }catch(Exception e){
                result.setSuccess(false);
                result.setMessage("取消收藏失败");
            }
        }
        return result;
    }


    /**
     * operation:评价
     * param: 前台session.getItem获取用户headImg,userName,content,videoId
     * */
    @RequestMapping("/addComt")
    public AjaxJson addCommentTemp(CommentTemp commentTemp){
        AjaxJson result = new AjaxJson();
        try{
            commentTempService.save(commentTemp);
            result.setSuccess(true);
            result.setMessage("提交成功，请耐心等待审核！");
        }catch(Exception e){
            result.setSuccess(false);
            result.setMessage("提交失败，请重新提交！");
        }
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
