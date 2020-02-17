package cn.imooc.vo;

import lombok.Data;

/**
 * 前台传参
 */
@Data
public class Params {
    //mysql \ es
    private String type;
    private String keyword;
}
