package com.itguigu.zcw.web.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.commons.vo.resp.UserLoginRespVo;
import com.itguigu.zcw.web.service.TMemberServiceFeign;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TMemberServiceFeignExceptionHandler implements TMemberServiceFeign{

	@Override
	@PostMapping("/user/login")
	public AppResponse<UserLoginRespVo> login(@RequestParam("loginacct") String loginacct, @RequestParam("password") String password){
		AppResponse<UserLoginRespVo> appResponse = AppResponse.fail(null);
		appResponse.setMsg("远程服务调用失败");
		log.error("远程服务: {} 调用失败", "SCW-USER");
		return appResponse;
	}

}
