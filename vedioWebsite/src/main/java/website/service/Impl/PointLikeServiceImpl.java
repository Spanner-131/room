package website.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.mapper.PointLikeMapper;
import website.pojo.PointLike;
import website.service.PointLikeService;

@Service
public class PointLikeServiceImpl extends ServiceImpl<PointLikeMapper,PointLike> implements PointLikeService {

    @Autowired
    PointLikeMapper pointLikeMapper;

    @Override
    public void addPointLike(PointLike pointLike) {
        pointLikeMapper.insert(pointLike);
    }
}
