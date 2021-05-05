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
    VideoVo videoInfo(String userCode,String id);

    List<VideoVo> videoSubscribed(List<Subscription> list);

    // 查询 点赞
    List<VideoVo> queryLike(String userCode);

    // 查询 收藏
    List<VideoVo> queryColt(String userCode);

    // 查询 评论
    List<VideoVo> queryComt(String userCode);

    //查询 标签
    List<Video> queryTag(List list);

    //查询 符合6个标签的
    //List<VideoVo> queryByTag(List list);

    //区分开 不用videoInfo 已有方法
    List<VideoVo> queryAllVideoInfo();
}
