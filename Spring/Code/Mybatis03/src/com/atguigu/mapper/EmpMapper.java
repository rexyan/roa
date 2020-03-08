package com.atguigu.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.atguigu.bean.Emp;

public interface EmpMapper {
	
	// 根据 eid, ename, age, sex 多条件查询员工信息
	List<Emp> getEmpListByMultipleCondition(Emp emp);
	
	List<Emp> getEmpListByMultipleCondition2(Emp emp);
	
	void updateEmpByMutipleCondition(Emp emp);
	
	// 根据  eid, ename, age, sex 中的其中一条信息查询员工信息
	List<Emp> getEmpListByChoose(Emp emp);
	
	// 添加员工信息，将传入的员工性别 0，转换为 女，1 转化为 男
	void insertEmp(Map<String, Object> emp);
	
	// 删除多个 Emp 信息,传入多个 eid，并使用 , 进行拼接
	void deleteMutipleEmp(String eids);
	
	// 删除多个 Emp 信息
	void deleteMutipleEmpByList(List<Integer> eids);
	
	// 批量添加多个 Emp
	void insertMutipleEmp(Emp[] emps);
	
	// 批量修改
	void updateMutipleEmp(Emp[] emps);
	
	// 根据 eid 查询员工信息
	Emp getEmpByEid(String eid);
}
