package website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import website.pojo.Comment;

public interface CommentService extends IService<Comment> {

    void addComment(Comment comment);

}
