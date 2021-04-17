package website.vo;

import lombok.Data;
import website.pojo.Video;

import java.util.Date;
import java.util.List;

/**
 *  @title:VedioVo
 *  @author:hb
 *  @content:主页视频Vedio Object
 *  @createTime:2021/3/24
 *  @modifiedTime:2021/3/24
 * */
@Data
public class VideoVo extends Video {

    private Date createTime;
    private String createTimeStr;
    private String userName;
    private String userCode;
    private String headImg;
    private List comList;
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

    //是否关注
    private int isSubscribed;
    //是否点赞
    private int isLiked;
    //是否收藏
    private int isCollected;
}
