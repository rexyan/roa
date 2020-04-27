package com.itguigu.zcw.web.exception;

import java.util.List;

import org.springframework.stereotype.Component;

import com.itguigu.zcw.commons.bean.TAdvertisement;
import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.commons.vo.resp.IndexRecommendRespVo;
import com.itguigu.zcw.commons.vo.resp.ReturnPayConfirmRespVo;
import com.itguigu.zcw.web.service.TProjectServiceFeign;
import com.itguigu.zcw.web.vo.req.ProjectDetailRepVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TProjectServiceFeignExceptionHandler implements TProjectServiceFeign{

	@Override
	public AppResponse<List<IndexRecommendRespVo>> indexRecommend(){
		AppResponse<List<IndexRecommendRespVo>> appResponse = new AppResponse<>();
		appResponse.setData(null);
		appResponse.setMsg("远程服务调用失败");
		log.error("远程服务: {} 调用失败: {}", "SCW-PROJECT", "indexRecommend");
		return appResponse;
	}

	@Override
	public AppResponse<List<TAdvertisement>> adv() {
		AppResponse<List<TAdvertisement>> appResponse = new AppResponse<>();
		appResponse.setData(null);
		appResponse.setMsg("远程服务调用失败");
		log.error("远程服务: {} 调用失败: {}", "SCW-PROJECT", "adv");
		return appResponse;
	}

	@Override
	public AppResponse<ProjectDetailRepVo> detail(Integer projectId) {
		AppResponse<ProjectDetailRepVo> appResponse = new AppResponse<>();
		appResponse.setData(null);
		appResponse.setMsg("远程服务调用失败");
		log.error("远程服务: {} 调用失败: {}", "SCW-PROJECT", "detail");
		return appResponse;
	}

	@Override
	public AppResponse<ReturnPayConfirmRespVo> confirmReturn(Integer projectId, Integer returnId) {
		AppResponse<ReturnPayConfirmRespVo> appResponse = new AppResponse<>();
		appResponse.setData(null);
		appResponse.setMsg("远程服务调用失败");
		log.error("远程服务: {} 调用失败: {}", "SCW-PROJECT", "confirmReturn");
		return appResponse;
	}
}
