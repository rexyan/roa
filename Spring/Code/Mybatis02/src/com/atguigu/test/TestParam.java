package com.atguigu.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.bean.Emp;
import com.atguigu.mapper.EmpSelectMapper;
import com.atguigu.mapper.ParamMapper;

public class TestParam {

	@Test
	public void test() throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis-conf.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession sqlSession = sqlSessionFactory.openSession(true); 
		
		ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);
//		Emp emp = new Emp(null, "1111", 20, "nana");
//		paramMapper.insertEmp(emp);
//		System.out.println("新插入数据的 eid" + emp.getEid());
		
//		Emp emp = paramMapper.getEmpById("1");
//		System.out.println(emp);
		
//		Emp empByEidAndEname = paramMapper.getEmpByEidAndEname("2", "zhangsansan");
//		System.out.println(empByEidAndEname) ;
		
//		HashMap<String,Object> hashMap = new HashMap<>();
//		hashMap.put("eid", 2);
//		hashMap.put("ename", "zhangsansan");
//		Emp empByMap = paramMapper.getEmpByMap(hashMap);
//		System.out.println(empByMap);
		
		Emp empByEidAndEname = paramMapper.getEmpByEidAndEname("2", "zhangsansan");
		System.out.println(empByEidAndEname);
	}
}
