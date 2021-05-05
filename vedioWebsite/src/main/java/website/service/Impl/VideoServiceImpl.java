package website.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import website.common.entity.AidData;
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
        List<VideoVo> videoList = videoMapper.videoSubscribed(list);
        //分页查询即可
        return videoList;
    }

    /**
     * operation:播放页面的初始化界面信息
     * param: id(video),videoId
     * */
    @Override
    public VideoVo getPlayInfo(String id,String currentUserCode) {
        QueryWrapper findUserCode = new QueryWrapper();
        findUserCode.eq("ID",id);
        Video video = videoMapper.selectOne(findUserCode);
        String userCode = video.getUserCode();
        //baseInfo
        VideoVo vv = videoMapper.videoInfo(userCode,id);
        Date createTime = vv.getCreateTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(createTime);
        vv.setCreateTimeStr(format);
        //点赞 收藏 评论数
        QueryWrapper statics =  new QueryWrapper();
        statics.eq("VIDEO_ID",id);
        //comment列表返回评价信息,应该还需修改返回列表
        List<CommentVo> comList = commentMapper.comList(id);
        //格式化 评论表时间
        List<CommentVo> comtList = transComtTime(comList);

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

        //是否关注、点赞、收藏
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("USER_CODE1",currentUserCode);
        queryWrapper1.eq("USER_CODE2",userCode);
        List isSubcribed = subscriptionMapper.selectList(queryWrapper1);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("USER_CODE",currentUserCode);
        queryWrapper.eq("VIDEO_ID",id);
        List isLiked = pointLikeMapper.selectList(queryWrapper);
        List isCollected = collectionMapper.selectList(queryWrapper);

        //把统计数据存入pojo
        if(isSubcribed.size() != 0){
            vv.setIsSubscribed(AidData.isExist);
        }else{
            vv.setIsSubscribed(AidData.notExist);
        }
        if(isLiked.size() != 0){
            vv.setIsLiked(AidData.isExist);
        }else{
            vv.setIsLiked(AidData.notExist);
        }
        if(isCollected.size() != 0){
            vv.setIsCollected(AidData.isExist);
        }else{
            vv.setIsCollected(AidData.notExist);
        }
        vv.setUserCode(userCode);
        vv.setLikeAmount(likeAmount);
        vv.setComList(comtList);
        vv.setCmtAmount(comtAmount);
        vv.setColtAmount(coltAmount);
        vv.setBowsAmount(bowsAmount);
        vv.setSubAmount(subAmount);
        vv.setVdoAmount(vdoAmount);
        return vv;
    }

    @Override
    public List<VideoVo> transTime(List<VideoVo> videoList) {
        Date date = new Date();
        long current = date.getTime();
        SimpleDateFormat sdfHM = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (VideoVo videoVo : videoList) {
            Date createTime = videoVo.getCreateTime();
            long create = createTime.getTime();
            double gap = (current - create) / 24 / 3600 / 1000;
            //today
            if(gap <= 1  && date.getDate() == createTime.getDate()){
                String format = sdfHM.format(createTime);
                videoVo.setCreateTimeStr("今天 " + format);
            }else if(gap <= 1 && (date.getDate() - createTime.getDate()) == 1){
                String format = sdfHM.format(createTime);
                videoVo.setCreateTimeStr("昨天 " + format);
            }else if(gap > 1){
                String format = sdf.format(createTime);
                videoVo.setCreateTimeStr(format);
            }
        }
        return videoList;
    }

    @Override
    public List<CommentVo> transComtTime(List<CommentVo> list) {
        Date date = new Date();
        long current = date.getTime();
        SimpleDateFormat sdfHM = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (CommentVo cv : list) {
            Date createTime = cv.getCreateTime();
            long create = createTime.getTime();
            double gap = (current - create) / 24 / 3600 / 1000;
            //today
            if(gap <= 1  && date.getDate() == createTime.getDate()){
                String format = sdfHM.format(createTime);
                cv.setCreateTimeStr("今天 " + format);
            }else if(gap <= 1 && (date.getDate() - createTime.getDate()) == 1){
                String format = sdfHM.format(createTime);
                cv.setCreateTimeStr("昨天 " + format);
            }else if(gap > 1){
                String format = sdf.format(createTime);
                cv.setCreateTimeStr(format);
            }
        }
        return list;
    }

    @Override
    public List<VideoVo> getTopDistinct(String userCode) {
        List<VideoVo> top150All = new ArrayList();
        List<VideoVo> topDistinct = new ArrayList();

        List<VideoVo> topLike = videoMapper.queryLike(userCode);
        List<VideoVo> topColt = videoMapper.queryColt(userCode);
        List<VideoVo> topComt = videoMapper.queryComt(userCode);

        //添加至top150All
        if(topLike.size() >= 50) {
            for(int i = 0;i < 50;i++){
                VideoVo videoVo = topLike.get(i);
                top150All.add(videoVo);
            }
        }else{
            for (VideoVo videoVo : topLike) {
                top150All.add(videoVo);
            }
        }
        if(topColt.size() >= 50){
            for(int i = 0;i < 50;i++){
                VideoVo videoVo = topColt.get(i);
                top150All.add(videoVo);
            }
        }else{
            for (VideoVo videoVo : topColt) {
                top150All.add(videoVo);
            }
        }
        if(topComt.size() >= 50){
            for(int i = 0;i < 50;i++){
                VideoVo videoVo = topComt.get(i);
                top150All.add(videoVo);
            }
        }else{
            for (VideoVo videoVo : topComt) {
                top150All.add(videoVo);
            }
        }

        // 全部过滤插入topDistinct
        scan:for (VideoVo videoVo : top150All) {
            for (VideoVo vo : topDistinct) {
                if(videoVo.getId().equals(vo.getId())){
                    continue scan;
                }
            }
            topDistinct.add(videoVo);
        }
        return topDistinct;
    }

    @Override
    public List<VideoVo> commonRecdVideo() {
        List<VideoVo> topDistinct = getTopDistinct("");
        List<VideoVo> topRandom10 = new ArrayList();

        // 随机topDistinct中的视频
        if(topDistinct.size() >= 10) {
            while (topRandom10.size() != 10) {
                //Random
                double num = Math.random();
                int randNum = (int) (Math.floor(num * topDistinct.size()));
                VideoVo vv = topDistinct.get(randNum);
                int i = 0;
                int flag = 1;
                while (i < topRandom10.size()) {
                    if (topRandom10.get(i).getId().equals(vv.getId())) {
                        flag = 0;
                    }
                    i++;
                }
                if (flag == 1) {
                    topRandom10.add(vv);
                }
            }
        }else{
            for (VideoVo videoVo : topDistinct) {
                topRandom10.add(videoVo);
            }
        }
        return topRandom10;
    }

    @Override
    public List<String> getTagList(String userCode) {
        List<VideoVo> topDistinct = getTopDistinct(userCode);
        List<Video> tagList = videoMapper.queryTag(topDistinct);

        List<String> tagRank = new ArrayList();
        List<Integer> tagCount = new ArrayList();
        List<String> top10Tag = new ArrayList();

        for (Video video : tagList) {
            String tag1 = video.getTag1();
            String tag2 = video.getTag2();
            String tag3 = video.getTag3();
            String tag4 = video.getTag4();
            String tag5 = video.getTag5();
            String tag6 = video.getTag6();

            countTag(tag1,tagRank,tagCount);
            countTag(tag2,tagRank,tagCount);
            countTag(tag3,tagRank,tagCount);
            countTag(tag4,tagRank,tagCount);
            countTag(tag5,tagRank,tagCount);
            countTag(tag6,tagRank,tagCount);
        }
        Integer temp = 0;
        String tempTag = "";
        for(int i = 0;i < tagCount.size() - 1;i++){
            for(int j = 0;j < tagCount.size() - 1 -i;j++){
                if(tagCount.get(j) < tagCount.get(j + 1)){
                    //冒泡排序 统计数量
                    temp = tagCount.get(j + 1);
                    tagCount.set(j + 1,tagCount.get(j));
                    tagCount.set(j,temp);
                    //冒泡排序 标签 从大到小
                    tempTag = tagRank.get(j + 1);
                    tagRank.set(j + 1,tagRank.get(j));
                    tagRank.set(j,tempTag);
                }
            }
        }
        if(tagRank.size() >= 10) {
            for (int i = 0; i < 10; i++) {
                top10Tag.add(tagRank.get(i));
            }
        }else{
            top10Tag = tagRank;
        }
        return top10Tag;
    }

    @Override
    public void countTag(String tag,List<String> tagRank,List<Integer> tagCount) {
        int flag = 1;
        int i = 0;
        Integer num = 0;
        if(!StringUtils.isEmpty(tag)){
            while(i < tagRank.size()){
                if(tag == tagRank.get(i)){
                    flag = 0;
                    num = tagCount.get(i);
                }
                i++;
            }
            if(flag == 0){
                tagCount.set(i,++num);
            }else{
                tagRank.add(tag);
                tagCount.add(1);
            }
        }
    }

    @Override
    public List<VideoVo> getRecdVideoByTag(String userCode) {
        List<String> tagList = getTagList(userCode);
        List<VideoVo> videoAll = videoMapper.queryAllVideoInfo();
        List<VideoVo> recdVideoList = new ArrayList();
        List<VideoVo> rand10Video = new ArrayList();
        for (VideoVo videoVo : videoAll) {
            int similarity = 0;
            String tag1 = videoVo.getTag1();
            int result1 = ifExisted(tag1, tagList);
            String tag2 = videoVo.getTag2();
            int result2 = ifExisted(tag2, tagList);
            String tag3 = videoVo.getTag3();
            int result3 = ifExisted(tag3, tagList);
            String tag4 = videoVo.getTag4();
            int result4 = ifExisted(tag4, tagList);
            String tag5 = videoVo.getTag5();
            int result5 = ifExisted(tag5, tagList);
            String tag6 = videoVo.getTag6();
            int result6 = ifExisted(tag6, tagList);
            similarity = result1 + result2 + result3 + result4 + result5 + result6;

            //简单推荐即可
            if(similarity != 0){
                recdVideoList.add(videoVo);
            }
        }
        if(recdVideoList.size() >= 10) {
            for (int i = 0; i < 10; i++) {
                double num = Math.random();
                int randNum = (int) (Math.floor(num * recdVideoList.size()));
                VideoVo vo = recdVideoList.get(randNum);
                int flag = 1;
                for (VideoVo vv : rand10Video) {
                    if(vo.getId().equals(vv.getId())){
                        flag = 0;
                    }
                }
                if(flag == 1){
                    rand10Video.add(vo);
                }
            }
        }else{
            rand10Video = recdVideoList;
        }
        return rand10Video;
    }

    @Override
    public int ifExisted(String tag,List tagList) {
        boolean flag = false;
        for (Object o : tagList) {
            if(tag != null && tag!= "" && tag.equals(o)){
                flag = true;
            }
        }
        if(flag){
            return AidData.isExist;
        }else{
            return AidData.notExist;
        }
    }
}
