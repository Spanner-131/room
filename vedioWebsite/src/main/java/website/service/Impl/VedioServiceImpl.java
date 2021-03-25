package website.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.mapper.VedioMapper;
import website.service.VedioService;
import website.vo.VedioVo;

import java.util.List;

@Service
public class VedioServiceImpl implements VedioService {

    @Autowired
    VedioMapper vedioMapper;

    @Override
    public List<VedioVo> getVedioInfo() {
        List<VedioVo> vedioList = vedioMapper.vedioInfo();
        return vedioList;
    }
}