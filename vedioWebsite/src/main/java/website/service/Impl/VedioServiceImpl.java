package website.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.mapper.VedioMapper;
import website.pojo.Subscription;
import website.pojo.Vedio;
import website.service.VedioService;
import website.vo.VedioVo;

import java.util.List;

@Service
public class VedioServiceImpl extends ServiceImpl<VedioMapper,Vedio> implements VedioService {

    @Autowired
    VedioMapper vedioMapper;

    @Override
    public List<VedioVo> getVedioInfo() {
        List<VedioVo> vedioList = vedioMapper.vedioInfo();
        return vedioList;
    }

    @Override
    public List<VedioVo> getVedioSubscribed(List<Subscription> list) {
        List<VedioVo> vedioList = vedioMapper.vedioSubscribed(list);
        return vedioList;
    }
}
