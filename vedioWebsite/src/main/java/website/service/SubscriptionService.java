package website.service;

import org.springframework.beans.factory.annotation.Autowired;
import website.mapper.SubscriptionMapper;
import website.pojo.Subscription;

import java.util.List;

public interface SubscriptionService {

    // 获取关注的用户学号列表
    List<Subscription> getUserCodeList(String userCode);

    // 关注订阅
    void addSubscription(Subscription subscription);
}
