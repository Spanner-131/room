package website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import website.pojo.Collection;

import java.util.List;

public interface CollectionMapper extends BaseMapper<Collection> {

    int queryColt(Collection collection);

    void reColt(Collection collection);

    // 查询 收藏总数前十 视频号
    List<Collection> queryTop10(String userCode);
}
