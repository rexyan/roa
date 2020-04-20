package com.itguigu.zcw.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.commons.vo.resp.UserLoginRespVo;
import com.itguigu.zcw.web.exception.TMemberServiceFeignExceptionHandler;


// 远程调用服务，并且处理异常信息
@FeignClient(value = "SCW-USER", fallback = TMemberServiceFeignExceptionHandler.class)
public interface TMemberServiceFeign {
	
	/**
	 * 注意前面有 /user 前缀
	 * @param loginacct
	 * @param password
	 * @return
	 */
	@PostMapping("/user/login")
	public AppResponse<UserLoginRespVo> login(@RequestParam("loginacct") String loginacct, @RequestParam("password") String password);
}
