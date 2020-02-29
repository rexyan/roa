package com.atguigu.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.User;
import com.sun.org.apache.regexp.internal.recompile;

@Controller
public class ParamController {
	/**
	 * 保证形参参数名和传递的数据的参数名保持一致，就可以自动赋值 （下面的 password，age 就是）。
	 * 如果形参参数名和传递的数据的参数名不一致，那么可以使用 @RequestParam 进行设置（下面的 username 就是）。
	 * @RequestParam 可以设置 required 属性。表示该参数是否必须。
	 * @RequestParam 可以设置 defaultValue 属性。表示该参数没传递时（注意是没传递，也就是 null 的时候，并不是为空字符串的时候)的默认值。
	 */
	@RequestMapping(value = "/param", method = {RequestMethod.POST})
	public String param(@RequestParam(value = "name", required = false, defaultValue = "admin") String username, String password, String age) {
		System.out.println(" username:" +username + " password:" + password + " age:" + age);
		return "success";
	}
	
	/**
	 * 使用 @RequestHeader 获取请求头信息
	 * @RequestHeader 和 @RequestParam 一样，也有 required 属性和 defaultValue 属性
	 */
	@RequestMapping(value = "/param2", method = {RequestMethod.POST})
	public String param2(@RequestHeader(value = "User-Agent") String userAgent) {
		System.out.println("User-Agent: " + userAgent);
		return "success";
	}
	
	/**
	 * 使用 @CookieValue 获取 cookie 信息
	 * @CookieValue 和 @RequestHeader 和 @RequestParam 一样，也有 required 属性和 defaultValue 属性
	 */
	@RequestMapping(value = "/param3", method = {RequestMethod.POST})
	public String param3(@CookieValue(value = "JSESSIONID") String sessionId) {
		System.out.println("sessionId: " + sessionId);
		return "success";
	}
	
	/**
	 * 可以通过形参的方式获取 Servlet API
	 */
	@RequestMapping(value = "/param4", method = {RequestMethod.POST})
	public String param4(User user, HttpServletRequest request) {
		System.out.println(request.getParameter("username"));
		return "success";
	}
	
	/**
	 * 往作用域传值方式1
	 */
	@RequestMapping(value = "/param5", method = {RequestMethod.POST})
	public ModelAndView param5(User user) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("username", user.getUsername());  // 往 request 作用域中赋值
		modelAndView.setViewName("success");  // 设置 view 名称
		return modelAndView;
	}
	
	/**
	 * 往作用域传值方式2
	 */
	@RequestMapping(value = "/param6", method = {RequestMethod.POST})
	public String param6(Map<String, Object> map) {
		map.put("username", "admin");  // 往 request 作用域中赋值
		return "success"; // 返回视图名称
	}
	
	/**
	 * 往作用域传值方式3
	 */
	@RequestMapping(value = "/param7", method = {RequestMethod.POST})
	public String param7(Model model) {
		model.addAttribute("username", "admin");  // 往 request 作用域中赋值
		return "success"; // 返回视图名称
	}
}
