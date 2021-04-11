package website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import website.pojo.Subscription;

import java.util.List;

public interface SubscriptionService extends IService<Subscription> {

    // 获取关注的用户学号列表
    List<Subscription> getUserCodeList(String userCode);

    // 有误关注，返回1/0
    int subOrNot(Subscription subscription);

    //修改del
    void reSubscribe(Subscription subscription);
}
