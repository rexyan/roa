package com.itguigu.proxy;

public class Test {
	public static void main(String[] args) {
//		MathI mathI = new MathImpl();
//		int add = mathI.add(1, 1);
//		System.out.println(add);
		
		ProxyUtil paProxyUtil = new ProxyUtil(new MathImpl());
		
		// 这里不能将 getProxy 获取到的动态代理对象强制转换为目标对象。得把它转换为相对应的接口类型。
		// 因为动态代理是根据接口动态的创建出一个代理类对象，这里生成的代理类对象和目标对象（被代理的对象）其实相当于是兄弟关系，所以不能相互转换。
		// 或者这样理解，如果这里强转成了 目标对象，那么无法保证后续调用的方法还是接口里面需要代理的（可能不调用加减乘除，调用 xxx 方法，这样的话代理就没用）
		// 所以这里只能强转为接口对象，因为接口里面就只能调用需要被代理的方法（加减乘除）
		MathI mathI = (MathI) paProxyUtil.getProxy();
		mathI.add(12, 12);
		/**
		 *  方法 - add args:[12, 12]
			方法 - add result:24
		 */
	}
}
