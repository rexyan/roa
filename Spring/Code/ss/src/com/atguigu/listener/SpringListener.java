package com.atguigu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 创建 Spring IOC 容器对象
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		// 可以将创建的 applicationContext 对象放入 ServletContext 中，以便在后续中
		// 可以随时获取 Spring 管理的对象
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("applicationContext", applicationContext);
	}
}
