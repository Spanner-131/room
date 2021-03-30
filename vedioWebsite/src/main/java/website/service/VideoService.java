package website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import website.pojo.Subscription;
import website.pojo.Video;
import website.vo.VideoVo;

import java.util.List;

public interface VideoService extends IService<Video> {

    List<VideoVo> getRecdVideoInfo();

    List<VideoVo> getVideoSubscribed(List<Subscription> list);

    VideoVo getPlayInfo(String id);
}
