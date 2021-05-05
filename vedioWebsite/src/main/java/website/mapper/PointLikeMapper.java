package website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import website.pojo.PointLike;

import java.util.List;

public interface PointLikeMapper extends BaseMapper<PointLike> {

    int queryLike(PointLike pointLike);

    void reLike(PointLike pointLike);

    // 查询 点赞总数前十 视频号
    List<PointLike> queryTop10(String userCode);
}
