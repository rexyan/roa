package com.atguigu.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.bean.Emp;
import com.atguigu.mapper.EmpMapper;

public class TestCache {

	@Test
	public void testFirstCache() throws IOException {
		/**
		 * Mybatis 中的一级缓存默认是开启的，是 SqlSession 级别的
		 * 即同一个 SqlSession 对于一个 sql 语句，执行之后就会存储在缓存中，下次执行相同的 SQL 的时候直接从缓存中取
		 */
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		EmpMapper mapper = openSession.getMapper(EmpMapper.class);
		
		System.out.println("第一次执行会显示出 SQL 语句");
		Emp empByEid1 = mapper.getEmpByEid("10");
		System.out.println(empByEid1);
		
		System.out.println("-------------------------------");
		
		System.out.println("执行相同的查询，不会再次出现 SQL 语句，因为被缓存了所以直接从缓存中读取");
		Emp empByEid2 = mapper.getEmpByEid("10");
		System.out.println(empByEid2);
	}
	
	@Test
	public void testSecondCache() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		EmpMapper mapper = openSession.getMapper(EmpMapper.class);
		Emp empByEid1 = mapper.getEmpByEid("10");
		System.out.println(empByEid1);
		
		openSession.commit();  // 提交 Sqlsession，这样二级缓存才能生效
		System.out.println("-------------------------------");
		
		EmpMapper mapper2 = openSession.getMapper(EmpMapper.class);
		Emp empByEid2 = mapper2.getEmpByEid("10");
		System.out.println(empByEid2);
	}
	
	
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-conf.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		return sqlSessionFactory;
	}
}
