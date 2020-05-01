package com.itguigu.zcw.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.order.bean.TOrder;
import com.itguigu.zcw.order.service.TOrderService;
import com.itguigu.zcw.order.service.vo.req.AddOrderReqVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/order")
@Api(tags = "订单模块")
public class OrderController {
	@Autowired
	TOrderService orderService;

	@ApiOperation("保存订单")
	@PostMapping("/save")
	public AppResponse<TOrder> saveOrder(@RequestBody AddOrderReqVo addOrderReqVo) {
		log.info("订单模块-保存订单接口接收到参数: ", addOrderReqVo);
		
		TOrder order = orderService.saveOrder(addOrderReqVo);
		AppResponse<TOrder> appResponse = AppResponse.ok(order);
		appResponse.setMsg("下单成功");
		
		log.info("下单成功");
		return appResponse;
	}
}
