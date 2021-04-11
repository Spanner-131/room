package website.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import website.mapper.*;
import website.pojo.Subscription;
import website.pojo.Video;
import website.service.VideoService;
import website.vo.CommentVo;
import website.vo.VideoVo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    SubscriptionMapper subscriptionMapper;

    @Autowired
    PointLikeMapper pointLikeMapper;

    @Autowired
    CollectionMapper collectionMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    BrowsingMapper browsingMapper;

    @Override
    public List<VideoVo> getRecdVideoInfo() {
        List<VideoVo> videoList = new ArrayList<>();
        return videoList;
    }

    @Override
    public List<VideoVo> getVideoSubscribed(List<Subscription> list) {
        List<VideoVo> vedioList = videoMapper.videoSubscribed(list);
        //分页查询即可
        return vedioList;
    }

    /**
     * operation:播放页面的初始化界面信息
     * param: id(video),videoId
     * */
    @Override
    public VideoVo getPlayInfo(String id) {
        QueryWrapper findUserCode = new QueryWrapper();
        findUserCode.eq("ID",id);
        Video video = videoMapper.selectOne(findUserCode);
        String userCode = video.getUserCode();
        //baseInfo
        VideoVo vv = videoMapper.videoInfo(userCode);
        Date createTime = vv.getCreateTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        String format = sdf.format(createTime);
        vv.setCreateTimeStr(format);
        //点赞 收藏 评论数
        QueryWrapper statics =  new QueryWrapper();
        statics.eq("VIDEO_ID",id);
        //comment列表返回评价信息,应该还需修改返回列表
        List<CommentVo> comList = commentMapper.comList(id);
        Integer likeAmount = pointLikeMapper.selectCount(statics);
        Integer comtAmount = commentMapper.selectCount(statics);
        Integer coltAmount = collectionMapper.selectCount(statics);
        Integer bowsAmount = browsingMapper.selectCount(statics);
        //订阅数
        QueryWrapper subStatics = new QueryWrapper();
        subStatics.eq("USER_CODE2",userCode);
        Integer subAmount = subscriptionMapper.selectCount(subStatics);
        //视频数量
        QueryWrapper uCodeWrapper = new QueryWrapper();
        uCodeWrapper.eq("USER_CODE",userCode);
        Integer vdoAmount = videoMapper.selectCount(uCodeWrapper);

        //把统计数据存入pojo
        vv.setUserCode(userCode);
        vv.setLikeAmount(likeAmount);
        vv.setComList(comList);
        vv.setCmtAmount(comtAmount);
        vv.setColtAmount(coltAmount);
        vv.setBowsAmount(bowsAmount);
        vv.setSubAmount(subAmount);
        vv.setVdoAmount(vdoAmount);
        return vv;
    }
}
