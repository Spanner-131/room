package website.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import website.common.entity.BaseEntity;


/**
 *  @title:User实体
 *  @author:hb
 *  @content:用户表对应字段
 *  @createTime:2021/3/20
 *  @modifiedTime:2021/3/24
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

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

    private boolean isAdmin;
    //管理员要管理视频
}
