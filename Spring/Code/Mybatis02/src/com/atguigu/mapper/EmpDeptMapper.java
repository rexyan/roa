package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.Dept;
import com.atguigu.bean.Emp;

public interface EmpDeptMapper {
	
	// 获取所有的员工对象
	List<Emp> getAllEmp();
	
	// 分步查询。先查询 Emp 信息，然后在通过 Emp 信息查询 Dept 信息。
	Emp getEmpStep(String eid);
	
	// 获取 dept 的信息，包括里面的员工信息
	Dept getDeptEmpsByDid(String did);
	
	// 分步查询。先查询 Dept 信息，然后在通过 Dept 信息查询 Emp 信息。
	Dept getOnlyDeptByDid(String did);
}
