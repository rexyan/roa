package com.itguigu.zcw.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itguigu.zcw.components.AliOssTemplate;

// 使用 @Configuration 或者 @SpringBootConfiguration 让其成为一个配置类
@Configuration
public class AliOssConfig {
	
	// 从 Properties 中读取前缀为 oss 的配置值，并且注入到 AliOssTemplate 属性中去
	@ConfigurationProperties(prefix = "oss")
	@Bean
	public AliOssTemplate aliOssTemplate() {
		return new AliOssTemplate();
	}
}
