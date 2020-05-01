package com.itguigu.zcw.web.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.itguigu.zcw.commons.bean.TAdvertisement;
import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.commons.vo.resp.IndexRecommendRespVo;
import com.itguigu.zcw.commons.vo.resp.UserLoginRespVo;
import com.itguigu.zcw.web.service.TMemberServiceFeign;
import com.itguigu.zcw.web.service.TProjectServiceFeign;

@Controller
public class DispatcherController {
	
	@Autowired
	TMemberServiceFeign memberServiceFeign;
	
	@Autowired
	TProjectServiceFeign projectServiceFeign;
	
	/**
	 * 首页
	 * @return
	 */
	@GetMapping(value = {"/index", "/"})
	public String index(Model model) {
		// 查询首页广告信息
		AppResponse<List<TAdvertisement>> adv = projectServiceFeign.adv();
		model.addAttribute("adv", adv.getData());
		
		// 查询推荐项目
		AppResponse<List<IndexRecommendRespVo>> indexRecommend = projectServiceFeign.indexRecommend();
		model.addAttribute("indexRecommend", indexRecommend.getData());
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
		String redirectUrl = (String) session.getAttribute("redirectUrl");
		if(!StringUtils.isEmpty(redirectUrl)) {
			return "redirect:" + redirectUrl;
		}else {
			return "redirect:/index";
		}
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
