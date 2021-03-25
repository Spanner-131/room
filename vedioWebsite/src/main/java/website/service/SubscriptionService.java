package website.service;

import org.springframework.beans.factory.annotation.Autowired;
import website.mapper.SubscriptionMapper;
import website.pojo.Subscription;

import java.util.List;

public interface SubscriptionService {

    List<Subscription> getUserCodeList(String userCode);
}
