package website.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.common.entity.AidData;
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
        int result;
        try{
            if(pointLikeMapper.queryLike(pointLike) == 0){
                result = AidData.resultExist;
            }else{
                result = AidData.resultLgcDel;
            }
        }catch(Exception e){
            result = AidData.resultNull;
        }
        return result;
    }

    @Override
    public void reLike(PointLike pointLike) {
        pointLikeMapper.reLike(pointLike);
    }
}
