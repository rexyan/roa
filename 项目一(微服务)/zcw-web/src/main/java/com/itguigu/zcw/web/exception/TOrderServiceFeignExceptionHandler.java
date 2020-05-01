package com.itguigu.zcw.web.exception;

import org.springframework.stereotype.Component;

import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.web.bean.TOrder;
import com.itguigu.zcw.web.service.TOrderServiceFeign;
import com.itguigu.zcw.web.vo.req.AddOrderReqVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TOrderServiceFeignExceptionHandler implements TOrderServiceFeign {

	@Override
	public AppResponse<TOrder> saveOrder(AddOrderReqVo addOrderReqVo) {
		AppResponse<TOrder> appResponse = AppResponse.fail(null);
		appResponse.setMsg("远程服务调用失败");
		log.error("远程服务: {} 调用失败", "SCW-ORDER");
		return appResponse;
	}

}
