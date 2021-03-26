package website.service;


import com.baomidou.mybatisplus.extension.service.IService;
import website.pojo.Collection;

public interface CollectionService extends IService<Collection> {

    void addCollection(Collection collection);
}
