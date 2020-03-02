package com.auguigu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class FirstInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)throws Exception {
		System.out.println("FirstInterceptor: afterCompletion, 相当于在 finally 里面，无论失败成功都会执行");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)throws Exception {
		System.out.println("FirstInterceptor: postHandle， 正确的返回之后之后才会执行，位置相当于在 ModelAndView 之后");	
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("FirstInterceptor: preHandle，Handler 开始处理请求之前");	
		
		//返回 true 表示放行，返回 false 表示进行拦截。
		return true;
	}
}
