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
}
