package website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import website.pojo.PointLike;

public interface PointLikeService extends IService<PointLike> {

//    void addPointLike(PointLike pointLike);

    int likeOrNot(PointLike pointLike);
}
