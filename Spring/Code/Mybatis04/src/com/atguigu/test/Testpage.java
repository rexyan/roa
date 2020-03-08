package com.atguigu.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.bean.Emp;
import com.atguigu.mapper.EmpMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class Testpage {

	@Test
	public void testPage() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		EmpMapper mapper = openSession.getMapper(EmpMapper.class);
		
		// 设置分页，第一个参数为当前页码，第二个参数为每页条数。
		PageHelper.startPage(2, 2);
		
		// 查询所有数据
		List<Emp> allEmp = mapper.getAllEmp();
		
		// 获取分页信息
		PageInfo<Emp> pageInfo = new PageInfo<>(allEmp);
		
		System.out.println("pageInfo:" + pageInfo);
		for (Emp emp : allEmp) {
			System.out.println(emp);
		}
	}
	
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-conf.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		return sqlSessionFactory;
	}
}
