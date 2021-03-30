package website.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import website.mapper.CollectionMapper;
import website.pojo.Collection;
import website.service.CollectionService;

@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper,Collection> implements CollectionService {

    @Autowired
    CollectionMapper collectionMapper;

//    @Override
//    public void addCollection(Collection collection) {
//        collectionMapper.insert(collection);
//    }


    @Override
    public int collectOrNot(Collection collection) {
        QueryWrapper collectWrapper = new QueryWrapper();
        collectWrapper.eq("USER_CODE",collection.getUserCode());
        collectWrapper.eq("VIDEO_ID",collection.getVideoId());
        Collection checkColt = collectionMapper.selectOne(collectWrapper);
        if(StringUtils.isEmpty(checkColt.getUserCode()) || StringUtils.isEmpty(checkColt.getVideoId())){
            return 0;
        }else{
            return 1;
        }
    }
}
