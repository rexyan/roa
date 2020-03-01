package com.atguigu.rest.crud.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.rest.crud.bean.Department;
import com.atguigu.rest.crud.bean.Employee;
import com.atguigu.rest.crud.dao.DepartmentDao;
import com.atguigu.rest.crud.dao.EmployeeDao;

@Controller
public class EmpController {
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * 获取所有员工信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/emps")
	public String getAllEmp(Map<String, Object> map) {
		Collection<Employee> emps = employeeDao.getAll();
		// 数组放入域中
		map.put("emps", emps);
		return "list";
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public String addEmpPage(Map<String, Object> map) {
		Collection<Department> departments = departmentDao.getDepartments();
		map.put("depts", departments);
		return "add";
	}
	
	/**
	 * 新增员工
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String addEmp(Employee employee) {
		// 保存
		employeeDao.save(employee);
		// 重定向到 list 页面
		return "redirect:/emps";
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public String updateEmpPage(@PathVariable("id") Integer id, Map<String, Object> map) {
		// 回显要修改的员工数据
		Employee employee = employeeDao.get(id);
		// 回显部门信息
		Collection<Department> departments = departmentDao.getDepartments();
		map.put("emp", employee);
		map.put("depts", departments);
		return "update";
	}
	
	/**
	 * 修改员工信息
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.PUT)
	public String updateEmp(Employee employee) {
		// 修改员工信息
		employeeDao.save(employee);
		// 重定向到 list 页面
		return "redirect:/emps";
	}
	
	/**
	 * 删除员工信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
	public String deleteEmp(@PathVariable("id") Integer id) {
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	
}
