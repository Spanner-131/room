package vedioWebsite.Pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vedioWebsite.common.entity.BaseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    private String userName;
    private String password;
}
