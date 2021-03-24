package website.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import website.common.entity.BaseEntity;

/**
 *  @title:Browsing实体
 *  @author:hb
 *  @content:浏览表对应字段
 *  @createTime:2021/3/24
 *  @modifiedTime:2021/3/24
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Browsing extends BaseEntity {

    private String userCode;
    private String vedioId;
    private String costTime;
}
