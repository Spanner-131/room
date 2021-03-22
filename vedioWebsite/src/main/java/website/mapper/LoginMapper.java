package website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import website.pojo.User;

@Repository
public interface LoginMapper extends BaseMapper<User> {
}
