package vedioWebsite.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vedioWebsite.common.entity.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vedio extends BaseEntity {

    private String url;
    private String title;
    private String userCode;
    //用户加关注数的字段

    //点赞数
    private int likeAmount;
    //评论数
    //private int cmtAmount;
	
	//
}
