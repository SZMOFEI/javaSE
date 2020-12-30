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
}
