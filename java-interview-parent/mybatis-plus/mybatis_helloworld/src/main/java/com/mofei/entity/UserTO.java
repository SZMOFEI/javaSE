package com.mofei.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author com.mofei
 * @version 2020/6/7 0:54
 */
@Data
public class UserTO {
    private Long id;
    private String name;
    private Integer gender;
    private String email;
    private String nickName;
    private Date createTime;
    private Date updateTime;
}
