package com.itguigu.zcw.project.exception;

import org.springframework.stereotype.Component;

import com.itguigu.zcw.commons.bean.TMember;
import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.commons.vo.resp.UserLoginRespVo;
import com.itguigu.zcw.project.service.TMemberServiceFeign;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TMemberServiceFeignExceptionHandler implements TMemberServiceFeign {

	@Override
	public AppResponse<TMember> getMemberInfo(Integer memberId) {
		AppResponse<TMember> appResponse = AppResponse.fail(null);
		appResponse.setMsg("远程服务调用失败");
		log.error("远程服务: {} 调用失败", "SCW-USER");
		return appResponse;
	}

}
