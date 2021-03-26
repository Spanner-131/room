package website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import website.pojo.Subscription;
import website.pojo.Vedio;
import website.vo.VedioVo;
import java.util.List;

public interface VedioService extends IService<Vedio> {

    List<VedioVo> getVedioInfo();

    List<VedioVo> getVedioSubscribed(List<Subscription> list);

}
