package com.itguigu.zcw;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class ZcwUserApplicationTests {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Test
	void testDatasource() throws SQLException {
		Connection connection = dataSource.getConnection();
		System.out.println(connection); // com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@421ead7e
		connection.close(); // 放回链接池
	}
	
	@Test
	void testRedis() {
		// 设置值
		stringRedisTemplate.opsForValue().set("test", "test value");
		// 获取值
		String string = stringRedisTemplate.opsForValue().get("test");
		System.out.println(string); // test value
		
		// 设置值
		redisTemplate.opsForValue().set("zhangsan", "zhangsan");
		// 获取值
		Object object = redisTemplate.opsForValue().get("zhangsan");
		System.out.println(object); // zhangsan
	}
}
