package website.common.entity;

import lombok.Data;

@Data
public class PageJson {
    private int code;
    private String msg;
    private int count;
    private Object data;
}
