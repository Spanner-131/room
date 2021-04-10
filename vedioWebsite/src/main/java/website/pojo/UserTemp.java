package website.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import website.common.entity.BaseEntity;


/**
 *  @title:UserTemp实体
 *  @author:hb
 *  @content:注册管理表对应字段
 *  @createTime:2021/4/5
 *  @modifiedTime:2021/4/5
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTemp extends BaseEntity {

    // 网络昵称
    private String userName;
    private String password;
    // 学号
    private String userCode;
    private String phone;
    private String mail;
    // 头像URL
    private String headImg;
    //name
    private String realName;
    //关注数
    //private String subscribed;

    private Integer isAdmin;

}
