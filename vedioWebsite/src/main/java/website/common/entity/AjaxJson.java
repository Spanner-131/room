package website.common.entity;


import lombok.Data;

/**
*  返回模块
* */
@Data
public class AjaxJson {
    public String message;
    public boolean success;
    public Object data;
}
