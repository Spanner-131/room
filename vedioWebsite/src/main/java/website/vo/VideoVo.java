package website.vo;

import lombok.Data;
import website.pojo.Video;

/**
 *  @title:VedioVo
 *  @author:hb
 *  @content:主页视频Vedio Object
 *  @createTime:2021/3/24
 *  @modifiedTime:2021/3/24
 * */
@Data
public class VideoVo extends Video {

    private String userName;
    private String userCode;
    private String headImg;
    //点赞数
    private int likeAmount;
    //评论数
    private int cmtAmount;
    //收藏数
    private int coltAmount;
    //播放数
    private int bowsAmount;
    //关注数
    private int subAmount;
    //视频数
    private int vdoAmount;
}
