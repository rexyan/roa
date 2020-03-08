package com.atguigu.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.bean.Dept;
import com.atguigu.bean.Emp;
import com.atguigu.mapper.EmpMapper;

public class TestSQL {

	@Test
	public void testIf() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		EmpMapper mapper = openSession.getMapper(EmpMapper.class);
		
		Emp emp = new Emp();
		emp.setAge(90);
		emp.setSex("0");
		emp.setEname("aa");
		
		// List<Emp> emps = mapper.getEmpListByMultipleCondition(emp);
		// System.out.println(emps);
		
		// List<Emp> emps = mapper.getEmpListByMultipleCondition2(emp);
		// System.out.println(emps);
		
		// mapper.updateEmpByMutipleCondition(emp);
		
		// List<Emp> empListByChoose = mapper.getEmpListByChoose(emp);
		// System.out.println(empListByChoose);
		
		// HashMap<String,Object> hashMap = new HashMap<>();
		// hashMap.put("age", 90);
		// hashMap.put("sex", "0");
		// hashMap.put("ename", "aa");
		// hashMap.put("did", 9);
		// mapper.insertEmp(hashMap);
		
		// String eids = "1,3,4";
		// mapper.deleteMutipleEmp(eids);
		
		// ArrayList<Integer> arrayList = new ArrayList<>();
		// arrayList.add(2);
		// arrayList.add(7);
		// arrayList.add(8);
		// mapper.deleteMutipleEmpByList(arrayList);
		
		 Emp emp2 = new Emp(null, "tiantian", 22, "1");
		 Emp emp3 = new Emp(null, "lanlan", 45, "0");
		 Emp emp4 = new Emp(null, "wangwang", 34, "1");
		 Emp emp5 = new Emp(null, "liiu", 65, "0");
		 Emp[] emps = {emp2, emp3, emp4, emp5};
		 mapper.insertMutipleEmp(emps);
		
		// Emp emp2 = new Emp(15, "zhenzhen1", 22, "1");
		// Emp emp3 = new Emp(16, "zhenzhen2", 23, "0");
		// Emp emp4 = new Emp(17, "zhenzhen3", 24, "1");
		// Emp emp5 = new Emp(18, "zhenzhen4", 25, "0");
		// Emp[] emps2 = {emp2, emp3, emp4, emp5};
		// mapper.updateMutipleEmp(emps2);
	}
	
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-conf.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		return sqlSessionFactory;
	}
}
