package website.service;


import website.pojo.User;

public interface LoginService {

    public int checkUser(String userName,String userCode,String password);

    public int saveUser(User user);

    public User queryRequiredMsg(User user);

    public void updateUser(User user);
}
