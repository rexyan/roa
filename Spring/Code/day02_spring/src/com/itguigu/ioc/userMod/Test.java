package com.itguigu.ioc.userMod;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itguigu.ioc.userMod.controller.UserController;
import com.itguigu.ioc.userMod.dao.UserDao;
import com.itguigu.ioc.userMod.dao.UserDaoImpl;
import com.itguigu.ioc.userMod.service.UserService;
import com.itguigu.ioc.userMod.service.UserServiceImpl;


public class Test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("user.xml"); 
		// 因为默认 spring 管理的 bean 都是单例的，即加载的时候就会创建其对象，不是 getBean 的时候才创建对象
		// 所以运行后就能看到在构造器中的输出 UserDaoImpl  UserController UserServiceImpl
		
		// Spring 自动生成的 bean 的 id 是类名首字母的小写，即 userController, userServiceImpl, userDaoImpl
		// 这里可以进行 bean 的获取，看是否能获取到
		UserController userController = applicationContext.getBean("userController", UserController.class);
		System.out.println(userController); 
		
		UserService userService = applicationContext.getBean("userServiceImpl", UserServiceImpl.class);
		System.out.println(userService); 
		
//		UserDao userDao = applicationContext.getBean("userDaoImpl", UserDaoImpl.class);
//		System.out.println(userDao); 
		
		userController.addUser();
	}
}
