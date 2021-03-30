package website.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import website.mapper.PointLikeMapper;
import website.pojo.PointLike;
import website.service.PointLikeService;

@Service
public class PointLikeServiceImpl extends ServiceImpl<PointLikeMapper,PointLike> implements PointLikeService {

    @Autowired
    PointLikeMapper pointLikeMapper;

//    @Override
//    public void addPointLike(PointLike pointLike) {
//        pointLikeMapper.insert(pointLike);
//    }


    @Override
    public int likeOrNot(PointLike pointLike) {
        QueryWrapper likeWrapper = new QueryWrapper();
        likeWrapper.eq("USER_CODE",pointLike.getUserCode());
        likeWrapper.eq("VIDEO_ID",pointLike.getVideoId());
        PointLike checkLike = pointLikeMapper.selectOne(likeWrapper);
        if(StringUtils.isEmpty(checkLike.getUserCode()) || StringUtils.isEmpty(checkLike.getVideoId())){
            return 0;
        }else {
            return 1;
        }
    }
}
