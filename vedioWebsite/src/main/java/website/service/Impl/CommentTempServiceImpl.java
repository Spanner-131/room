package website.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import website.mapper.CommentTempMapper;
import website.pojo.CommentTemp;
import website.service.CommentTempService;

@Service
public class CommentTempServiceImpl extends ServiceImpl<CommentTempMapper, CommentTemp> implements CommentTempService {

}
