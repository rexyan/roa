package com.atguigu.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.ssm.bean.Dept;
import com.atguigu.ssm.mapper.DeptMapper;
import com.atguigu.ssm.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptMapper mapper;
	
	@Override
	public List<Dept> getAllDept() {
		return mapper.getAllDept();
	}

}
