package com.atguigu.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.bean.Dept;
import com.atguigu.bean.Emp;
import com.atguigu.mapper.EmpDeptMapper;
import com.atguigu.mapper.EmpSelectMapper;
import com.atguigu.mapper.ParamMapper;

public class TestEmpDept {

	@Test
	public void test() throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis-conf.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession sqlSession = sqlSessionFactory.openSession(true); 
		
		EmpDeptMapper empDeptMapper = sqlSession.getMapper(EmpDeptMapper.class);
		// List<Emp> allEmp = empDeptMapper.getAllEmp();
		// System.out.println(allEmp);
		
		// 只去查询 emp 信息
		// Emp empStep = empDeptMapper.getEmpStep("2");
		// System.out.println(empStep.getEname());
		// 用到了 dept 才去执行 dept 的查询语句
		// System.out.println(empStep.getDept().getDname());
		
		// Dept deptEmpsByDid = empDeptMapper.getDeptEmpsByDid("3");
		// System.out.println(deptEmpsByDid);
		
		Dept onlyDeptByDid = empDeptMapper.getOnlyDeptByDid("3");
		System.out.println(onlyDeptByDid.getDname());
	}
}
