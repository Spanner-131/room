package website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import website.pojo.Subscription;

public interface SubscriptionMapper extends BaseMapper<Subscription> {

    int querySubscription(Subscription subscription);

    void reSubscribe(Subscription subscription);
}
