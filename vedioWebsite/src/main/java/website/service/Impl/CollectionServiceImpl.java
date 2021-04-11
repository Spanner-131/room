package website.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.common.entity.AidData;
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
        int result;
        try{
            if(collectionMapper.queryColt(collection) == 0){
                result = AidData.resultExist;
            }else{
                result = AidData.resultLgcDel;
            }
        }catch (Exception e){
            result = AidData.resultNull;
        }
        return result;
    }

    @Override
    public void reColt(Collection collection) {
        collectionMapper.reColt(collection);
    }
}
