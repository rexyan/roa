package com.atguigu.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.ssm.bean.Emp;
import com.atguigu.ssm.mapper.EmpMapper;
import com.atguigu.ssm.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpMapper mapper;

	@Override
	public List<Emp> getAllEmp() {
		return mapper.getAllEmp();
	}

	@Override
	public Emp getAllEmpByEid(String eid) {
		return mapper.getEmpByEid(eid);
	}

	@Override
	public void updateEmp(Emp emp) {
		mapper.updateEmp(emp);
	}

}
