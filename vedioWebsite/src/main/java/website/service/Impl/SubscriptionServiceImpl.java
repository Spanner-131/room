package website.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import website.mapper.SubscriptionMapper;
import website.pojo.Subscription;
import website.service.SubscriptionService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    SubscriptionMapper subscriptionMapper;

    @Override
    public List<Subscription> getUserCodeList(String userCode) {
        // 获取关注用户学号
        QueryWrapper<Subscription> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_CODE1",userCode);
        List<Subscription> subscriptionList = subscriptionMapper.selectList(queryWrapper);
        List userCodelist = new ArrayList();
        for (Subscription subscription : subscriptionList) {
            if(!StringUtils.isEmpty(subscription.getUserCode2())){
                userCodelist.add(subscription.getUserCode2());
            }
        }
        return userCodelist;
    }

    @Override
    public void addSubscription(Subscription subscription) {
        subscriptionMapper.insert(subscription);
    }
}
