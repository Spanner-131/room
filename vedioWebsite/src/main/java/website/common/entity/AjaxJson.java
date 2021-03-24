package website.common.entity;


import lombok.Data;

/**
 *  @title:AjaxJson / ReturnData
 *  @author:hb
 *  @content:基础公用模块
 *  @createTime:2021/3/21
 *  @modifiedTime:2021/3/21
 * */
@Data
public class AjaxJson {
    public String message;
    public boolean success;
    public Object data;
}
