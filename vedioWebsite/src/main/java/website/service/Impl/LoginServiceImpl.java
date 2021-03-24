package website.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import website.mapper.LoginMapper;
import website.pojo.User;
import website.service.LoginService;

/**
 *  @title：LoginServiceImpl
 *  @author:hb
 *  @content:服务接口实现
 *  @createTime:2021/3/18
 *  @modifiedTime:2021/3/21
 * */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    /**
    *  方法：检验用户
    *  return：1/0
    * */
    @Override
    public int checkUser(String userName,String userCode,String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        if(StringUtils.isEmpty(userName)){
            queryWrapper.eq("USER_CODE",userCode);
        }else{
            queryWrapper.eq("USER_NAME",userName);
        }
        try {
            User checkUser = loginMapper.selectOne(queryWrapper);
            if (checkUser.getPassword().equals(password)) {
                return 1;
            } else {
                return 0;
            }
        }catch(Exception err){
            return 0;
        }
    }

    //此处须改成 管理员导入学生信息，初始化学生用户
    @Override
    public int saveUser(User user) {
        int result =  loginMapper.insert(user);
        return result;
    }

    @Override
    public User queryRequiredMsg(User user) {
        String userName = user.getUserName();
        String userCode = user.getUserCode();
        User userSelected = new User();
        if(userName != null && userName != ""){
            QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
            queryWrapper.eq("USER_NAME",userName);
            userSelected = loginMapper.selectOne(queryWrapper);
        }
        if (userCode != null && userName != ""){
            QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
            queryWrapper.eq("USER_CODE",userCode);
            userSelected = loginMapper.selectOne(queryWrapper);
        }
        return userSelected;
    }

    @Override
    public void updateUser(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
        updateWrapper.eq("USER_CODE", user.getUserCode());
        int rows = loginMapper.update(user, updateWrapper);
        log.info("成功改动" + rows + "行");
    }
}
