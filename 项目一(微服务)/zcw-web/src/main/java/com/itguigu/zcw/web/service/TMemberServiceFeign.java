package com.itguigu.zcw.web.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.commons.vo.resp.UserLoginRespVo;
import com.itguigu.zcw.web.bean.TMemberAddress;
import com.itguigu.zcw.web.exception.TMemberServiceFeignExceptionHandler;


// 远程调用服务，并且处理异常信息
@FeignClient(value = "SCW-USER", fallback = TMemberServiceFeignExceptionHandler.class)
public interface TMemberServiceFeign {
	
	/**
	 * 用户登录
	 * 注意前面有 /user 前缀
	 * @param loginacct
	 * @param password
	 * @return
	 */
	@PostMapping("/user/login")
	public AppResponse<UserLoginRespVo> login(@RequestParam("loginacct") String loginacct, @RequestParam("password") String password);
	
	/**
	 * 根据 accessToken 查询用户收获地址信息
	 * @param accessToken
	 * @return
	 */
	@GetMapping("/user/info/address/{accessToken}")
	public AppResponse<List<TMemberAddress>> getMemberAddress(@PathVariable("accessToken") String accessToken);
}
