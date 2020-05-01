package com.itguigu.zcw.order.execption;

import org.springframework.stereotype.Component;

import com.itguigu.zcw.commons.bean.TReturn;
import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.order.service.TProjectServiceFeign;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TProjectServiceFeignExceptionHandler implements TProjectServiceFeign {
	@Override
	public AppResponse<TReturn> getReturnDetailById(Integer returnId) {
		AppResponse<TReturn> appResponse = AppResponse.fail(null);
		appResponse.setMsg("远程服务调用失败");
		log.error("远程服务: {} 调用失败", "SCW-PROJECT");
		return appResponse;
	}

}
