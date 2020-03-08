package com.atguigu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import com.atguigu.bean.Emp;

public interface EmpSelectMapper {
	
	// 根据 eid 查询员工信息
	Emp getEmpByEid(String eid);
	
	// 获取所有的员工数量
	Integer getCount();
	
	// 以 map 集合获取一个员工信息
	Map<String, Object> getEmpMapByEid(String eid);
	
	// 以 map 集合获取所有员工信息
	@MapKey(value = "eid") // 设置每个 map 的键为 eid
	Map<String, Object> getAllEmpMap();
}
