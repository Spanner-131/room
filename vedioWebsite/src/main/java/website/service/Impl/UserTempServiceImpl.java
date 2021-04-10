package website.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import website.mapper.UserTempMapper;
import website.pojo.UserTemp;
import website.service.UserTempService;

@Service
public class UserTempServiceImpl extends ServiceImpl<UserTempMapper, UserTemp> implements UserTempService {
}
