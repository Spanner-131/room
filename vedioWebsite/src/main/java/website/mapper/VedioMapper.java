package website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import website.pojo.Vedio;
import website.vo.VedioVo;

import java.util.List;

public interface VedioMapper extends BaseMapper<Vedio> {

    @Select("Select USER_NAME,HEAD_IMG,URL,TITLE,DESCRIPTION,COVER_URL from vedio,user where vedio.USER_CODE = user.USER_CODE")
    List<VedioVo> vedioInfo();
}
