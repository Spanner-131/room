package website.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import website.common.entity.BaseEntity;

/**
 *  @title:Comment实体
 *  @author:hb
 *  @content:评论表对应字段
 *  @createTime:2021/3/24
 *  @modifiedTime:2021/3/24
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {

    //内容
    private String content;
    //关联用
    private String videoId;
    private String userCode;
//
}
