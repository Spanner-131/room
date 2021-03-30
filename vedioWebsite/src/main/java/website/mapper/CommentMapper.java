package website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import website.pojo.Comment;
import website.vo.CommentVo;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    List<CommentVo> comList(String videoId);
}
