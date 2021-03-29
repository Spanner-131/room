package website.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  @title:Vedio实体
 *  @author:hb
 *  @content:视频表对应字段
 *  @createTime:2021/3/22
 *  @modifiedTime:2021/3/24
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {


    //连接user表
    private String userCode;

    //vedioPath
    private String url;

    private String title;
    private String description;

    //coverPath
    private String coverUrl;

    //tag1
    private String tag1;
    private String tag2;
    private String tag3;
    private String tag4;
    private String tag5;
    private String tag6;

}
