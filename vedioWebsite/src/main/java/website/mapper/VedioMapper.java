package website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import website.pojo.Vedio;
import website.vo.VedioVo;

import java.util.List;

@Repository
public interface VedioMapper extends BaseMapper<Vedio> {

    @Select("Select USER_NAME,HEADIMG,URL,TITLE,DESCRIPTION,COVER_URL from vedio v,user u where v.userCode = u.userCode")
    List<VedioVo> vedioInfo();
}
