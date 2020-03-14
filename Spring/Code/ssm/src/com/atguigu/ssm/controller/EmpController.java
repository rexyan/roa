package com.atguigu.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.ssm.bean.Dept;
import com.atguigu.ssm.bean.Emp;
import com.atguigu.ssm.service.DeptService;
import com.atguigu.ssm.service.EmpService;
import com.atguigu.ssm.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class EmpController {
	@Autowired
	private EmpService empService;
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping(value = "/emps/{pageNum}", method = {RequestMethod.GET})
	public String emps(Map<String, Object> map, @PathVariable("pageNum") Integer pageNum, HttpServletRequest request) {
		// 设置分页，第一个参数为当前页码，第二个参数为每页条数。
		PageHelper.startPage(pageNum, 5);
		List<Emp> allEmp = empService.getAllEmp();
		
		// 将数据传入获取分页信息
		PageInfo<Emp> pageInfo= new PageInfo<>(allEmp, 5);
		
		// 根据分页信息获取 html 页码链接信息
		String page = PageUtil.getPageInfo(pageInfo, request);
		
		map.put("empList", allEmp);
		map.put("page", page);
		return "list";
	}
	
	@RequestMapping(value = "/emp/{eid}", method = {RequestMethod.GET})
	public String toUpdate(@PathVariable("eid") String eid, Map<String, Object> map) {
		// 获取要修改的员工信息
		Emp emp = empService.getAllEmpByEid(eid);
		// 获取所有的部门信息
		List<Dept> deptList = deptService.getAllDept();
		// 获取存储性别的
		Map<String, String> sex = new HashMap<String, String>(); 
		sex.put("0", "女");
		sex.put("1", "男");
		
		map.put("emp", emp);
		map.put("deptList", deptList);
		map.put("sex", sex);
		return "update";
	}
	
	@RequestMapping(value = "/emp", method = {RequestMethod.PUT})
	public String updateEmp(Emp emp) {
		empService.updateEmp(emp);
		return "redirect:/emps/1";
	}
	
	@RequestMapping(value = "/emps", method = {RequestMethod.DELETE})
	public String deleteMore(String eid) {
		// TODO 获取批量删除 id
		System.out.println(eid);
		return "";
	}
}
