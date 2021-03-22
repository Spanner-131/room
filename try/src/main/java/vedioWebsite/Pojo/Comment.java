package vedioWebsite.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    //内容
    private String content;
    //关联用
    private String vedioId;
    private String userCode;
}
