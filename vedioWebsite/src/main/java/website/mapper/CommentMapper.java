package website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import website.pojo.Comment;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}
