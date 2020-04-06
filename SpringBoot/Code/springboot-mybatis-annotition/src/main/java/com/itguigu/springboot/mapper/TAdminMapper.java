package com.itguigu.springboot.mapper;

import org.apache.ibatis.annotations.Select;

import com.itguigu.springboot.bean.TAdmin;

public interface TAdminMapper {
	// 去除 mapper.xml 后，直接使用注解方式添加对应操作。还支持 @Insert，@Delete，@Update 等注解
	@Select("select * from t_admin where id=#{id}")
	TAdmin getTAdminById(Integer id);
}
