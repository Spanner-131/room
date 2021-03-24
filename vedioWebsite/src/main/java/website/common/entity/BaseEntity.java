package website.common.entity;


import lombok.Data;

import java.util.Date;

/**
 *  @title:BaseEntity
 *  @author:hb
 *  @content:公用基础字段id,锁，创建时间，修改时间，逻辑删除
 *  @createTime:2021/3/20
 *  @modifiedTime:2021/3/20
 * */
@Data
public class BaseEntity {
    private long id;
    private int version;
    private Date createTime;
    private Date modifiedTime;
    private int deleted;
}
