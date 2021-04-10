package website.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import website.common.entity.BaseEntity;

/**
 *  @title:CommentTemp实体
 *  @author:hb
 *  @content:评论管理表对应字段
 *  @createTime:2021/4/5
 *  @modifiedTime:2021/4/5
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentTemp extends BaseEntity {

    //内容
    private String content;
    //关联用
    private String videoId;
    private String userCode;
//
}
