package com.atguigu.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect  // 这个注解的作用是标示当前类为切面
@Order(1)
public class MyLoggerAspect {
	
	/**
	 * 创建可以重用的公共切入点
	 */
	@Pointcut(value = "execution(* com.atguigu.spring.aop.*.*(..))")
	public void publicJoinPoint() {}
	
	/**
	 * @Before 作用于方法执行之前
	 * 将方法指定为前置通知。
	 * 必须设置 value，值为切入点表达式(解析这个表达式，将通知作用于这个表达式代表的位置)
	 * 也就说将 befordMethod 这个方法作用到 add 方法执行之前。
	 */
	// @Before(value = "execution(public int com.atguigu.spring.aop.MathImpl.add(int, int))")
	// @Before(value = "execution(* com.atguigu.spring.aop.*.*(..))")
	@Before(value = "publicJoinPoint()")
	public void befordMethod(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();  // 获取方法参数
		String name = joinPoint.getSignature().getName();  // 获取方法名
		System.out.println("method: " + name + " ,args: " + Arrays.toString(args));
		System.out.println("前置通知");
	}
	
	/**
	 * @After 将方法标注为后置通知。相当于方法的 finally 语句块，相当于不管有没有异常都会执行。
	 */
	// @After(value = "execution (* com.atguigu.spring.aop.*.*(..))")
	@Before(value = "publicJoinPoint()")
	public void afterMethos() {
		System.out.println("后置通知");
	}
	

	/**
	 * @AfterReturning 将方法标注为返回通知。相当于方法正确执行（无异常）之后。
	 * 可以通过 returning 设置接收方法返回值的变量名。如果要在方法中使用，必须在方法的形参中设置一个参数，名字和 returning 设置的变量名一致。
	 */
	// @AfterReturning(value = "execution (* com.atguigu.spring.aop.*.*(..))", returning = "result")
	@AfterReturning(value = "publicJoinPoint()", returning = "result")
	public void afterReturningMethos(JoinPoint joinPoint, Object result) {
		System.out.println("method: " + joinPoint.getSignature().getName() + " ,result: " + result);
		System.out.println("返回通知");
	}
	
	/**
	 * @AfterThrowing 将方法标注为异常通知（例外通知）作用于方法抛出异常时
	 * 可以通过 throwing 设置接收方法异常的信息
	 * 在参数列表中可以通过具体的异常类型来对指定的异常信息进行操作。例如这里是 NullPointerException 异常
	 * 即当发生 NullPointerException 异常的时候，这个异常通知才起作用
	 */
	// @AfterThrowing(value = "execution (* com.atguigu.spring.aop.*.*(..))", throwing = "ex")
	@AfterThrowing(value = "publicJoinPoint()", throwing = "ex")
	public void afterThrowingMethod(NullPointerException ex) {
		System.out.println("出现异常: " + ex);
	}
	
	
//	/**
//	 * @Around 将方法标注为环绕通知，其实就是和动态代理一样，可以在方法执行的前后，加上自己想要处理的逻辑。
//	 * 不过参数类型为 ProceedingJoinPoint。proceed 代表执行方法。
//	 */
//	@Around(value = "execution (* com.atguigu.spring.aop.*.*(..))")
//	public Object aroundMethos(ProceedingJoinPoint proceedingJoinPoint) {  // ProceedingJoinPoint 是 JoinPoint 的子类
//		Object result;
//		
//		try {
//			// 前置通知
//			System.out.println("前置通知");
//			
//			// 执行方法
//			result = proceedingJoinPoint.proceed(); 
//			
//			// 返回通知
//			System.out.println("返回通知");
//			
//			return result;
//		} catch (Throwable e) {
//			e.printStackTrace();
//			
//			// 异常通知
//			System.out.println("异常通知");
//			
//			return -1;
//		} finally {
//			
//			// 后置通知
//			System.out.println("后置通知");
//		}
//	}
}
