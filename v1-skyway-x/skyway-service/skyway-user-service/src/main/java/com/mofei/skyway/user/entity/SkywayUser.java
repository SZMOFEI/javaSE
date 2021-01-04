package com.mofei.skyway.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author mofei
 * @since 2020/12/30 22:38
 */
@TableName(value = "skyway_user_info")
public class SkywayUser {
    @TableId
    private String id ;

    private String username;

    private String password;

    public SkywayUser() {
    }

    public SkywayUser(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * 启用禁用状态 1是启用  0是禁用
     */
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
