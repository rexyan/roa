package com.atguigu.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.bean.Emp;
import com.atguigu.mapper.EmpMapper;
import com.atguigu.mapper.EmpSelectMapper;

public class TestCRUD {
	@Test
	public void testCURD() throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis-conf.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		// SqlSession sqlSession = sqlSessionFactory.openSession();     需要手动处理事务
		SqlSession sqlSession = sqlSessionFactory.openSession(true); // 自动处理事务（默认为 false 不自动提交）
//		EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
	
//		// 测试 getEmpByEid
//		Emp emp1 = empMapper.getEmpByEid("1");
//		System.out.println(emp1);
//		
//		// 测试  getAllEmp
//		List<Emp> allEmp = empMapper.getAllEmp();
//		System.out.println(allEmp);
//		
//		// 测试 addEmp
//		empMapper.addEmp(new Emp(null, "223333",23, "nv"));
//		
//		// 测试 updateEmp
//		empMapper.updateEmp(new Emp(2, "zhangsansan", 33, "nv"));
//		
		// 测试 deleteEmp
//		Integer i = empMapper.deleteEmp("6");
//		System.out.println(i);
		
// 		测试 deleteEmp
// 		Boolean deleteEmp = empMapper.deleteEmp("6");

	}
}
