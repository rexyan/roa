package com.itguigu.zcw.project.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.itguigu.zcw.commons.bean.TMember;
import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.project.exception.TMemberServiceFeignExceptionHandler;

@FeignClient(value = "SCW-USER", fallback = TMemberServiceFeignExceptionHandler.class)
public interface TMemberServiceFeign {
	
	@GetMapping("/user/info/{memberId}")
	public AppResponse<TMember> getMemberInfo(@PathVariable("memberId") Integer memberId);
}
