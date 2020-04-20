package com.itguigu.zcw.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.commons.vo.resp.UserLoginRespVo;
import com.itguigu.zcw.web.service.TMemberServiceFeign;

@Controller
public class DispatcherController {
	
	@Autowired
	TMemberServiceFeign memberServiceFeign;
	
	/**
	 * 首页
	 * @return
	 */
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	/**
	 * 登录页
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	/**
	 * 注册页
	 * @return
	 */
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	/**
	 * 登录
	 * @param loginacct
	 * @param password
	 * @param session
	 * @param model
	 * @return
	 */
	@PostMapping("/doLogin")
	public String doLogin(@RequestParam("loginacct") String loginacct, @RequestParam("password") String password, HttpSession session, Model model) {
		AppResponse<UserLoginRespVo> response = memberServiceFeign.login(loginacct, password);
		UserLoginRespVo loginRespVo = response.getData();
		if (loginRespVo==null) {
			model.addAttribute("message", "账号或密码不正确！");
			return "login";
		}
		// 登录成功，保存 session 信息
		session.setAttribute("memeber", loginRespVo);
		return "redirect:/index";
	}
	
	/**
	 * 退出
	 * @return
	 */
	@GetMapping("/doLogout")
	public String doLogout(HttpSession session) {
		// 清除用户信息
		if(session!=null) {
			session.removeAttribute("memeber");
			session.invalidate();
		}
		return "redirect:/index";
	}
}
