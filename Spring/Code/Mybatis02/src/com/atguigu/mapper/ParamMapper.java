package com.atguigu.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.Emp;

public interface ParamMapper {
	// 添加员工信息
	void insertEmp(Emp emp);
	
	// 根据 eid 获取员工信息
	Emp getEmpById(String eid);
	
	// 根据 eid ename 查询员工信息
	Emp getEmpByEidAndEname(String eid, String ename);
	
	// 根据 Map 查询员工信息
	Emp getEmpByMap(HashMap<String, Object> hashMap);
	
	// 根据 eid ename 查询员工信息
	Emp getEmpByEidAndEnameByParam(@Param("eid") String eid, @Param("ename") String ename);
}
