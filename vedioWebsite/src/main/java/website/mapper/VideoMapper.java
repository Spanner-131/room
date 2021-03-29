package website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import website.pojo.Subscription;
import website.pojo.Video;
import website.vo.VideoVo;

import java.util.List;

public interface VideoMapper extends BaseMapper<Video> {

    //@Select("Select USER_NAME,HEAD_IMG,URL,TITLE,DESCRIPTION,COVER_URL from vedio,user where vedio.USER_CODE = user.USER_CODE")
    /**
    * issue:为何舍弃@Select的使用？
    * reason: 后续加入其他逻辑条件时，虽然@Select可以实现，但写在此处，页面不整洁，且修改不方便
    * anotherWay: 用回xml
    * operation: 在配置文件中通过mybatis-plus（mapper-location）定位xml文件路径
    * if: mapper.xml写在java类下，还需在pom下配置对java类中xml文件的扫描
    * */
    VideoVo videoInfo(String userCode);

    List<VideoVo> videoSubscribed(List<Subscription> list);


}
