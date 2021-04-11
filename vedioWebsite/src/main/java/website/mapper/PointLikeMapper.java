package website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import website.pojo.PointLike;

public interface PointLikeMapper extends BaseMapper<PointLike> {

    int queryLike(PointLike pointLike);

    void reLike(PointLike pointLike);
}
