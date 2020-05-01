package com.itguigu.zcw.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.web.bean.TOrder;
import com.itguigu.zcw.web.exception.TOrderServiceFeignExceptionHandler;
import com.itguigu.zcw.web.vo.req.AddOrderReqVo;

@FeignClient(value = "SCW-ORDER", fallback = TOrderServiceFeignExceptionHandler.class)
public interface TOrderServiceFeign {
	
	@PostMapping("/order/save")
	public AppResponse<TOrder> saveOrder(@RequestBody AddOrderReqVo addOrderReqVo);

}
