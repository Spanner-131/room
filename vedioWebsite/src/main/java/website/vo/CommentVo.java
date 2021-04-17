package website.vo;

import lombok.Data;
import website.pojo.Comment;

/**
 *  @title:CommentVo
 *  @author:hb
 *  @content:Comment View Object
 *  @createTime:2021/3/30
 *  @modifiedTime:2021/3/30
 * */

@Data
public class CommentVo extends Comment {

    private String userName; //名字
    private String headImg;  //头像
    private String createTimeStr;
}
