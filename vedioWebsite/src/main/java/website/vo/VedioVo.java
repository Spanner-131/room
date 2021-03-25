package website.vo;

import lombok.Data;
import website.pojo.Vedio;

/**
 *  @title:VedioVo
 *  @author:hb
 *  @content:主页视频Vedio Object
 *  @createTime:2021/3/24
 *  @modifiedTime:2021/3/24
 * */
@Data
public class VedioVo extends Vedio {

    private String userName;
    private String userCode;

}
