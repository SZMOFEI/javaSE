package com.mofei.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mofei
 * @date 2020/6/6 23:48
 */
@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer gender;
    private String email;
    private String nickName;
    private Date createTime;
    private Date updateTime;
    private UserDetail userDetail ;
}
