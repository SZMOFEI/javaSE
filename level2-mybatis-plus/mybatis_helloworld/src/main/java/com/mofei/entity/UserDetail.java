package com.mofei.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author com.mofei
 * @version 2020/6/6 23:48
 */
@Data
public class UserDetail implements Serializable {
    private Integer id;
    private String name;

}
