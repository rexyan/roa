package com.atguigu.jdbctemplate;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;

public class TestJdbcTemplate {
	
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc.xml");
	JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
	
	@Test
	public void test() {
		// String sqlString = "insert into emp values (null, 'zhangsan', 23, 'nan')";
		// jdbcTemplate.update(sqlString);
		
		// String sqlString2 = "insert into emp values (null, ?, ?, ?)";
		// jdbcTemplate.update(sqlString2, "lisi", 23, "nv");
		
		// String sqlString3 = "delete from emp where eid = ?";
		// jdbcTemplate.update(sqlString3, 1);
		
		String sqlString4 = "update emp set ename = ? where eid = ?";
		jdbcTemplate.update(sqlString4, "wangwu", 2);
	}
	
	@Test
	public void testBatchUpdate() {
		String sqlString = "insert into emp values (null, ?, ?, ?)";
		List<Object[]> list = new ArrayList<>();
		list.add(new Object[] {"a1", 1, "nan"});
		list.add(new Object[] {"a2", 2, "nan"});
		list.add(new Object[] {"a3", 3, "nan"});
		jdbcTemplate.batchUpdate(sqlString, list);
	}
	
	@Test
	public void testQueryForObject() {
		// jdbcTemplate.queryForObject(sql, rowMapper)    用来获取单条数据
		// jdbcTemplate.queryForObject(sql, requiredType) 用来获取单个的值，例如 count，sum...
		
		String sql = "select eid, ename, age, sex from emp where eid=?";
		// 将查询出的结果列名与实体类中的属性进行映射
		RowMapper<Emp> rowMapper = new BeanPropertyRowMapper<>(Emp.class); 
		Emp emp = jdbcTemplate.queryForObject(sql, new Object[] {5}, rowMapper);
		System.out.println(emp);
		
		String sql2 = "select count(1) from emp";
		Integer count = jdbcTemplate.queryForObject(sql2, Integer.class);
		System.out.println(count);
	}
	
	@Test
	public void testQuery() {
		String sql = "select eid, ename, age, sex from emp";
		RowMapper<Emp> rowMapper = new BeanPropertyRowMapper<>(Emp.class); 
		List<Emp> query = jdbcTemplate.query(sql, rowMapper);
		for (Emp emp : query) {
			System.out.println(emp);
		}
	}
}
