package website.common.entity;


import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    private long id;
    private int version;
    private Date createTime;
    private Date modifiedTime;
    private int deleted;
}
