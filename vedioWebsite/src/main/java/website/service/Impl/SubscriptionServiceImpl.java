package website.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import website.mapper.SubscriptionMapper;
import website.pojo.Subscription;
import website.service.SubscriptionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubscriptionServiceImpl extends ServiceImpl<SubscriptionMapper,Subscription> implements SubscriptionService {

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

    // 0--不存在;1--存在
    @Override
    public int subOrNot(Subscription subscription) {

        int result = 0;
        QueryWrapper<Subscription> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_CODE1",subscription.getUserCode1());
        queryWrapper.eq("USER_CODE2",subscription.getUserCode2());
        Subscription one = subscriptionMapper.selectOne(queryWrapper);
        if(StringUtils.isEmpty(one.getUserCode1()) || StringUtils.isEmpty(one.getUserCode2())){
            result = 0;
        }else{
            result = 1;
        }
        return result;
    }
}
