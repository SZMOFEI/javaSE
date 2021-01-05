package com.mofei.skyway.device.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author mofei
 * @version 2020/12/28 9:50
 */
@TableName("skyway_device")
public class SkywayDevice implements Serializable {
    @TableId
    private String id;
    private String sn;
    private String createUsername;

    public SkywayDevice(String id, String sn, String createUsername) {
        this.id = id;
        this.sn= sn;
        this.createUsername = createUsername;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }
}
