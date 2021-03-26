package website.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.mapper.CommentMapper;
import website.pojo.Comment;
import website.service.CommentService;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper,Comment> implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }
}
