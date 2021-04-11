package website.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import website.common.entity.AidData;
import website.mapper.SubscriptionMapper;
import website.pojo.Subscription;
import website.service.SubscriptionService;

import java.util.ArrayList;
import java.util.List;

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
        int result;
        try {
            if(subscriptionMapper.querySubscription(subscription) == 0){
                result = AidData.resultExist;
            }else{
                result = AidData.resultLgcDel;
            }
        }catch (Exception e){
            result = AidData.resultNull;
        }
        return result;
    }

    //因为牵扯逻辑删除，它会默认只对没有逻辑删除的数据进行操作
    @Override
    public void reSubscribe(Subscription subscription) {
        subscriptionMapper.reSubscribe(subscription);
    }
}
