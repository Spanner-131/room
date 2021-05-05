package website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import website.pojo.Subscription;
import website.pojo.Video;
import website.vo.CommentVo;
import website.vo.VideoVo;

import java.util.List;

public interface VideoService extends IService<Video> {

    List<VideoVo> getRecdVideoInfo();

    List<VideoVo> getVideoSubscribed(List<Subscription> list);

    VideoVo getPlayInfo(String id,String currentUserCode);

    //transfer
    List<VideoVo> transTime(List<VideoVo> videoList);

    //transfer 播放界面 不合并
    List<CommentVo> transComtTime(List<CommentVo> comtList);

    //获取 点赞 收藏 评论 三项前五十去重后的视频
    List<VideoVo> getTopDistinct(String userCode);

    //获取 top+num 随机的视频
    List<VideoVo> commonRecdVideo();

    /**
    * 个人推荐
    * */
    //查询 （点赞，收藏，评论）视频的标签
    //统计 次数最多的 前5个 标签
    //通过标签 进行查询
    //符合 5个的 优先推荐
    //符合 4个的 其次 以此类推
    //ps：推荐前 公用和个人无重复
    //@Param("userCode")String userCode)
    List<String> getTagList(String userCode);

    void countTag(String tag,List<String> tagRank,List<Integer> tagCount);

    List<VideoVo> getRecdVideoByTag(String userCode);

    int  ifExisted(String tag,List tagList);
}
