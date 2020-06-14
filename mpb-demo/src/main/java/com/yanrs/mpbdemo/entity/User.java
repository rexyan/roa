package com.yanrs.mpbdemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    // 插入的时候对值进行填充
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    // 插入或者更新的时候对值进行填充
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // 乐观锁 version 字段
    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;

    // 逻辑删除
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;
}
