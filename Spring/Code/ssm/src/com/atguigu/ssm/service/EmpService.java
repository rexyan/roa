package com.atguigu.ssm.service;

import java.util.List;

import com.atguigu.ssm.bean.Emp;

public interface EmpService {
	List<Emp> getAllEmp();
	
	Emp getAllEmpByEid(String eid);
	
	void updateEmp(Emp emp);
}
