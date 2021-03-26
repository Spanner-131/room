package website.common.entity;


import com.baomidou.mybatisplus.annotation.*;
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

    @TableId(type = IdType.AUTO)
    private long id;
    @Version
    private Integer version;
    //自动填充配置
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //自动填充配置
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;
    @TableLogic //逻辑删除
    private Integer deleted;
}
