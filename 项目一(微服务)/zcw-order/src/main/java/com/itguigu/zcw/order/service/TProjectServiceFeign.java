package com.itguigu.zcw.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.itguigu.zcw.commons.bean.TReturn;
import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.order.execption.TProjectServiceFeignExceptionHandler;

@FeignClient(value = "SCW-PROJECT", fallback = TProjectServiceFeignExceptionHandler.class)
public interface TProjectServiceFeign {

	@GetMapping("/project/return/detail/{returnId}")
	public AppResponse<TReturn> getReturnDetailById(@PathVariable("returnId") Integer returnId);
}
