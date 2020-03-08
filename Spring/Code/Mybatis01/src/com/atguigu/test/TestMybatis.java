package com.atguigu.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.atguigu.bean.User;
import com.atguigu.mapper.UserMapper;

class TestMybatis {

	@Test
	void test() throws IOException {
		// 获取操作数据库的会话对象
		InputStream is = Resources.getResourceAsStream("mybatis-conf.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession openSession = sqlSessionFactory.openSession();
		
		// getMapper 会通过动态代理动态的生成 UserMapper 实现类，所以后续可以调用 getUserByUid 方法（不然抽象类中方法不能直接调用）
		UserMapper userMapper = openSession.getMapper(UserMapper.class);
		User user = userMapper.getUserByUid("1");
		System.out.println(user);
	}
}
