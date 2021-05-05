package website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import website.pojo.Comment;
import website.vo.CommentVo;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    List<CommentVo> comList(String videoId);

    // 查询 评论总数前十 视频号
    List<Comment> queryTop10(String userCode);
}
