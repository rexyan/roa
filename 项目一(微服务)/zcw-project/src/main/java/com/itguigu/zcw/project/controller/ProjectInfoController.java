package com.itguigu.zcw.project.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itguigu.zcw.commons.bean.TMember;
import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.commons.vo.resp.IndexRecommendRespVo;
import com.itguigu.zcw.commons.vo.resp.ReturnPayConfirmRespVo;
import com.itguigu.zcw.project.bean.TAdvertisement;
import com.itguigu.zcw.project.bean.TProject;
import com.itguigu.zcw.project.bean.TProjectImages;
import com.itguigu.zcw.project.bean.TReturn;
import com.itguigu.zcw.project.service.TMemberServiceFeign;
import com.itguigu.zcw.project.service.TProjectInfoService;
import com.itguigu.zcw.project.vo.req.ProjectDetailRepVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "项目信息模块")
@RequestMapping("/project")
@RestController
public class ProjectInfoController {
	@Autowired
	TProjectInfoService projectInfoService;
	
	@Autowired
	TMemberServiceFeign memberServiceFeign;
	
	@ApiOperation(value = "获取首页广告项目")
	@GetMapping("/adv")
	public AppResponse<List<TAdvertisement>> adv() {
		try {
			List<TAdvertisement> listAdvertisements = projectInfoService.getIndexAdv();
			log.info("获取首页广告项目:{}", listAdvertisements);
			return AppResponse.ok(listAdvertisements);
		} catch (Exception e) {
			log.error("获取首页广告项目错误:{}", e.getMessage());

			AppResponse<List<TAdvertisement>> appResponse = new AppResponse<>();
			appResponse.setData(null);
			appResponse.setMsg("获取首页广告项目错误");
			return appResponse;
		}
	}

	@ApiOperation(value = "获取首页热门推荐项目")
	@GetMapping("/recommend/index")
	public AppResponse<List<IndexRecommendRespVo>> indexRecommend() {
		try {
			List<IndexRecommendRespVo> indexRecommendList = projectInfoService.getIndexRecommendProject();
			log.info("获取首页热门推荐项目:{}", indexRecommendList);
			return AppResponse.ok(indexRecommendList);
		} catch (Exception e) {
			log.error("获取首页热门推荐项目:{}", e.getMessage());

			AppResponse<List<IndexRecommendRespVo>> appResponse = new AppResponse<>();
			appResponse.setData(null);
			appResponse.setMsg("获取首页热门推荐项目");
			return appResponse;
		}

	}

	@ApiOperation(value = "获取项目详情信息")
	@GetMapping("/info/detail/{projectId}")
	public AppResponse<ProjectDetailRepVo> detail(@PathVariable("projectId") Integer projectId) {
		ProjectDetailRepVo projectDetailRepVo = new ProjectDetailRepVo();
		// 查询项目信息
		TProject projectInfo = projectInfoService.getProjectInfo(projectId);
		BeanUtils.copyProperties(projectInfo, projectDetailRepVo);
		// 查询所有图片信息
		List<TProjectImages> projectImageList = projectInfoService.getProjectImages(projectId);
		for (TProjectImages tProjectImages : projectImageList) {
			ArrayList<Object> detailsImages = new ArrayList<>();
			if (0 == tProjectImages.getImgtype()) {
				// 头图
				projectDetailRepVo.setHeaderImage(tProjectImages.getImgurl());
			} else {
				// 详情图
				List<String> projectDetailsImageList = projectDetailRepVo.getDetailsImage();
				projectDetailsImageList.add(tProjectImages.getImgurl());
			}
		}
		// 查询回报信息
		List<TReturn> projectReturns = projectInfoService.getProjectReturnInfo(projectId);
		projectDetailRepVo.setProjectReturns(projectReturns);

		log.info("获取项目:{} 详情信息:{}", projectId, projectDetailRepVo);
		return AppResponse.ok(projectDetailRepVo);
	}
	
	@ApiOperation(value = "确认回报信息")
	@GetMapping("/confirm/return/{projectId}/{returnId}")
	public AppResponse<ReturnPayConfirmRespVo> confirmReturn(@PathVariable("projectId")Integer projectId,  @PathVariable("returnId")Integer returnId)  {
		log.info("获取请求参数 projectId:{} returnId:{}", projectId, returnId);
		
		ReturnPayConfirmRespVo returnPayConfirmRespVo = new ReturnPayConfirmRespVo();
		// 获取回报数据
		TReturn tReturn = projectInfoService.getProjectReturnInfoById(returnId);
		
		// 获取项目数据
		TProject tProject =  projectInfoService.getProjectInfo(projectId);
		
		// 获取发起人数据
		Integer memberid = tProject.getMemberid();
		AppResponse<TMember> memberInfo = memberServiceFeign.getMemberInfo(memberid);
		TMember tMember = memberInfo.getData();
		
		// Vo 赋值
		returnPayConfirmRespVo.setProjectId(projectId);
		returnPayConfirmRespVo.setProjectName(tProject.getName());
		returnPayConfirmRespVo.setProjectRemark(tProject.getRemark());
		returnPayConfirmRespVo.setMemberId(memberid);
		returnPayConfirmRespVo.setMemberName(tMember.getUsername());
		returnPayConfirmRespVo.setReturnId(returnId);
		returnPayConfirmRespVo.setReturnContent(tReturn.getContent());
		returnPayConfirmRespVo.setNum(1);
		returnPayConfirmRespVo.setPrice(tReturn.getSupportmoney());
		returnPayConfirmRespVo.setFreight(tReturn.getFreight());
		returnPayConfirmRespVo.setSignalpurchase(tReturn.getSignalpurchase());
		returnPayConfirmRespVo.setTotalPrice(new BigDecimal(returnPayConfirmRespVo.getNum() * returnPayConfirmRespVo.getPrice() + returnPayConfirmRespVo.getFreight()));
		
		log.info("获取项目回报确认信息:{}", returnPayConfirmRespVo);
		return AppResponse.ok(returnPayConfirmRespVo);
	}
	
	@ApiOperation(value = "根据回报 ID 查询回报详细信息")
	@GetMapping("/return/detail/{returnId}")
	public AppResponse<TReturn> getReturnDetailById(@PathVariable("returnId") Integer returnId) {
		TReturn tReturn = projectInfoService.getReturnDetailById(returnId);
		return AppResponse.ok(tReturn);
	}
	
	@ApiOperation(value = "获取首页分类推荐项目")
	@GetMapping("/recommend/type")
	public AppResponse<Object> type() {
		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "获取项目总览列表")
	@GetMapping("/all/index")
	public AppResponse<Object> all() {
		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "获取项目系统分类信息")
	@GetMapping("/sys/type")
	public AppResponse<Object> systype() {
		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "获取项目系统标签信息")
	@GetMapping("/sys/tags")
	public AppResponse<Object> systags() {
		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "获取项目回报档位信息")
	@GetMapping("/return/info")
	public AppResponse<Object> support() {
		return AppResponse.ok("ok");
	}

}
