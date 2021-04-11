package website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import website.pojo.Collection;

public interface CollectionMapper extends BaseMapper<Collection> {

    int queryColt(Collection collection);

    void reColt(Collection collection);
}
