package com.atguigu.ssm.mapper;

import java.util.List;

import com.atguigu.ssm.bean.Emp;

public interface EmpMapper {
	
	// 获取所有的员工信息
	List<Emp> getAllEmp();
	
	// 根据 eid 获取员工信息
	Emp getEmpByEid(String eid);
	
	// 修改员工信息
	void updateEmp(Emp emp);
}
