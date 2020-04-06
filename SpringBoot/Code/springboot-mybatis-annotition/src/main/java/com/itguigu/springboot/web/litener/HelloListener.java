package com.itguigu.springboot.web.litener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class HelloListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("应用销毁了....HelloListener");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("应用销毁了....HelloListener");
	}
}
