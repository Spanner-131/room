package website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import website.mapper.SubscriptionMapper;
import website.pojo.Subscription;

import java.util.List;

public interface SubscriptionService extends IService<Subscription> {

    // 获取关注的用户学号列表
    List<Subscription> getUserCodeList(String userCode);

    // 有误关注，返回1/0
    int subOrNot(Subscription subscription);

}
