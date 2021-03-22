package website.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import website.common.entity.BaseEntity;

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
}
