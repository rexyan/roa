package com.itguigu.zcw.web.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class AppWebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 添加拦截器
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 如果 controller 中路由只是起到跳转的作用，那么我们可以直接写一个映射
		// 下面写法的意思是当访问 /index 的时候，直接跳转到 resource/template/index 页面。
		// 这种用法不多，当作了解即可
		registry.addViewController("/index").setViewName("index");
	}
}
