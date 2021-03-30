package website.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import website.common.entity.BaseEntity;

/**
 *  @title:PointLike实体
 *  @author:hb
 *  @content:点赞表对应字段
 *  @createTime:2021/3/24
 *  @modifiedTime:2021/3/24
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointLike extends BaseEntity {

    private String userCode;
    private String videoId;
}
