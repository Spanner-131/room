package website.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import website.common.entity.BaseEntity;

/**
 *  @title:Collection实体
 *  @author:hb
 *  @content:收藏表对应字段
 *  @createTime:2021/3/24
 *  @modifiedTime:2021/3/24
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collection extends BaseEntity {

    private String userCode;
    private String videoId;
}
