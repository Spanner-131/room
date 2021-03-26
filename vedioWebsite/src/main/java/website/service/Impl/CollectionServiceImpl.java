package website.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.mapper.CollectionMapper;
import website.pojo.Collection;
import website.service.CollectionService;

@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper,Collection> implements CollectionService {

    @Autowired
    CollectionMapper collectionMapper;

    @Override
    public void addCollection(Collection collection) {
        collectionMapper.insert(collection);
    }
}
