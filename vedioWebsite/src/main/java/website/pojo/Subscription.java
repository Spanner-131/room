package website.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import website.common.entity.BaseEntity;

/**
 *  @title:Subscription实体
 *  @author:hb
 *  @content:订阅对应字段
 *  @createTime:2021/3/24
 *  @modifiedTime:2021/3/24
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription extends BaseEntity {

    private String userCode1;
    private String userCode2;
}
