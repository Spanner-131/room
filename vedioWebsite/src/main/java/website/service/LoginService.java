package website.service;


import website.pojo.User;

/**
 *  @title:LoginService
 *  @author:hb
 *  @content:服务接口
 *  @createTime:2021/3/18
 *  @modifiedTime:2021/3/21
 * */
public interface LoginService {

    public int checkUser(String userName,String userCode,String password);

    public int saveUser(User user);

    public User queryRequiredMsg(User user);

    public void updateUser(User user);
}
