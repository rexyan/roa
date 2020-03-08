package com.atguigu.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.bean.Emp;
import com.atguigu.mapper.EmpSelectMapper;

public class TestSerlectMapper {

	@Test
	public void test() throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis-conf.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

		SqlSession sqlSession = sqlSessionFactory.openSession(true); 
		EmpSelectMapper empSelectMapper = sqlSession.getMapper(EmpSelectMapper.class);
		
		Emp emp = empSelectMapper.getEmpByEid("1");
		System.out.println(emp);
		
		Integer count = empSelectMapper.getCount();
		System.out.println(count);
		
		Map<String, Object> empMapByEid = empSelectMapper.getEmpMapByEid("1");
		System.out.println(empMapByEid);
		
		Map<String, Object> allEmpMap = empSelectMapper.getAllEmpMap();
		System.out.println();
	}
}
