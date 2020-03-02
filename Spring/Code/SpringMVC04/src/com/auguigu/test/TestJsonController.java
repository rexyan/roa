package com.auguigu.test;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auguigu.bean.Employee;
import com.auguigu.dao.EmployeeDao;

@Controller
public class TestJsonController {
	@Autowired
	private EmployeeDao employeeDao;
	
	@RequestMapping("/testJson")
	@ResponseBody 
	public String testJson() {
		return "success";
	}
	
	@RequestMapping("/testJson2")
	@ResponseBody 
	public Collection<Employee> testJson2() {
		Collection<Employee> allEmployee = employeeDao.getAll();
		return allEmployee;
	}
	
	@RequestMapping(value = "/testJson3", method = RequestMethod.POST)
	@ResponseBody 
	public Collection<Employee> testJson3() {
		Collection<Employee> allEmployee = employeeDao.getAll();
		return allEmployee;
	}
}
