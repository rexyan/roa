package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.Emp;

public interface EmpMapper {
	
	// 根据 eid 查询员工信息
	Emp getEmpByEid(String eid);
	
	// 获取所有的员工信息
	List<Emp> getAllEmp();
	
	// 添加员工信息
	void addEmp(Emp emp);
	
	// 修改员工信息
	void updateEmp(Emp emp);
	
	// 删除员工信息(返回 Integer)
	Boolean deleteEmp(String eid);
	
	// 根据部门 id 查询所属该部门的员工
	List<Emp> getEmpListByDid(String did);
}
