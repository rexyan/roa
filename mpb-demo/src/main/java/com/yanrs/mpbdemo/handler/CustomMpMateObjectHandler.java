package com.yanrs.mpbdemo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomMpMateObjectHandler implements MetaObjectHandler {
    // 在执行 insert 操作的时候运行
    @Override
    public void insertFill(MetaObject metaObject) {
        // 在进行 insert 操作的时候为 createTime updateTime 设置新的值
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);

        // 为乐观锁 version 字段设置默认值
        this.setFieldValByName("version", 1, metaObject);

        // 逻辑删除
        this.setFieldValByName("deleted", 1, metaObject);
    }

    // 在执行 update 操作的时候运行
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
