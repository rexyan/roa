package com.itguigu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 动态代理工具类
 * @author rex
 *
 */
public class ProxyUtil{
	// 目标对象（被代理的对象）
	private MathImpl mathImpl;
	
	// 创建有参构造为成员变量 mathImpl 赋值
	public ProxyUtil(MathImpl mathImpl) {
		super();
		this.mathImpl = mathImpl;
	}
	
	// 获取代理对象
	public Object getProxy() {
		/**
		 * 借助 Proxy.newProxyInstance 来返回一个代理类对象
		 * 第一个参数是类加载器，可以是被代理类 MathImpl 的，也可以是 当前类 ProxyUtil 的。只要是个类加载器就行
		 * 第二个参数是被代理内的接口们，只有知道被代理类有哪些接口，才能创建除代理类对象（中介得知道你的找房需求才能代替给你找房）
		 * 第三个参数是 InvocationHandler 对象，这里使用匿名内部类实现，实现 InvocationHandler 需要重写它的 invoke 方法
		 */
		ClassLoader classLoader = this.getClass().getClassLoader();
		Class<?>[] interfaces = mathImpl.getClass().getInterfaces();
		
		return Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				MyLogger.before(method.getName(), Arrays.toString(args));
				Object result = method.invoke(mathImpl, args);
				MyLogger.after(method.getName(), result);
				return result;
			}
		});
	}
}
