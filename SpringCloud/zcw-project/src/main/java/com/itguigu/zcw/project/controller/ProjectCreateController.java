package com.itguigu.zcw.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.project.components.AliOssTemplate;
import com.itguigu.zcw.project.service.ProjectService;
import com.itguigu.zcw.project.vo.req.CreateProjectBaseReqVo;
import com.itguigu.zcw.project.vo.req.CreateProjectInfoReqVo;
import com.itguigu.zcw.project.vo.req.CreateProjectOriginatorReqVo;
import com.itguigu.zcw.project.vo.req.CreateProjectReturnReqVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "项目发起模块")
@RequestMapping("/project/create")
@RestController
public class ProjectCreateController {

	@Autowired
	AliOssTemplate aliOssTemplate;
	
	@Autowired
	ProjectService projectService;
	
	/**
	 * 
	 * @param baseReqVo 用户登录 accessToken 信息
	 * @return
	 */
	@ApiOperation(value = "1. 同意项目初始化协议")
	@PostMapping("/init")
	public AppResponse<Object> init(CreateProjectBaseReqVo baseReqVo) {
		// 获取传递的参数
		String accessToken = baseReqVo.getAccessToken();
		log.error("项目初始化接收到 accessToken：{}", accessToken);
		
		try {
			// 判断参数是否为空
			if (!StringUtils.isEmpty(accessToken)) {
				CreateProjectBaseReqVo projectInfo = projectService.initCreateroject(accessToken);
				AppResponse<Object> appResponse = AppResponse.ok(projectInfo);
				appResponse.setMsg("项目初始化成功");
				return appResponse;
			} else {
				AppResponse<Object> appResponse = AppResponse.fail(null);
				appResponse.setMsg("参数不正确");
				return appResponse;
			} 
		} catch (Exception e) {
			log.error("项目初始化错误：{}", e.getMessage());
			AppResponse<Object> appResponse = AppResponse.fail(null);
			appResponse.setMsg(e.getMessage());
			return appResponse;
		}
	}
	
	/**
	 * 
	 * @param baseReqVo 用户登录 accessToken 信息
	 * @param projectInfoReqVo 项目基本信息
	 * @param projectOriginatorReqVo 项目发起人信息
	 * @return
	 */
	@ApiOperation(value = "2. 添加项目及发起人信息")
	@PostMapping("/basicInfo/{projectId}")
	public AppResponse<Object> basicInfo(@PathVariable("projectId") String projectId, CreateProjectBaseReqVo baseReqVo, CreateProjectInfoReqVo projectInfoReqVo, CreateProjectOriginatorReqVo projectOriginatorReqVo) {
		// 获取传递的参数
		String accessToken = baseReqVo.getAccessToken();
		log.error("添加项目及发起人信息 accessToken：{}, projectInfoReqVo:{}, projectOriginatorReqVo:{}", accessToken, projectInfoReqVo, projectOriginatorReqVo);
		
		try {
			// 判断参数是否为空
			if (!StringUtils.isEmpty(accessToken)) {
				CreateProjectBaseReqVo projectInfo = projectService.addProjectOriginatorInfo(projectId, projectInfoReqVo, projectOriginatorReqVo);
				AppResponse<Object> appResponse = AppResponse.ok(projectInfo);
				appResponse.setMsg("添加项目及发起人信息成功");
				return appResponse;
			} else {
				AppResponse<Object> appResponse = AppResponse.fail(null);
				appResponse.setMsg("参数不正确");
				return appResponse;
			} 
		} catch (Exception e) {
			log.error("添加项目及发起人信息错误：{}", e.getMessage());
			AppResponse<Object> appResponse = AppResponse.fail(null);
			appResponse.setMsg(e.getMessage());
			return appResponse;
		}
	}

	/**
	 * 
	 * @param baseReqVo 用户登录 accessToken 信息
	 * @param projectReturnReqVo 项目回报信息
	 * @return
	 */
	@ApiOperation(value = "3. 添加项目回报")
	@PostMapping("/return/{projectId}")
	public AppResponse<Object> returnDetail(@PathVariable("projectId") String projectId, CreateProjectBaseReqVo baseReqVo, @RequestBody() List<CreateProjectReturnReqVo> projectReturnListReqVo) {
		// 获取传递的参数
		String accessToken = baseReqVo.getAccessToken();
		log.error("添加项目回报 accessToken：{}, baseReqVo:{}, projectReturnReqVo:{}", accessToken, baseReqVo, projectReturnListReqVo);
		
		try {
			// 判断参数是否为空
			if (!StringUtils.isEmpty(accessToken)) {
				CreateProjectBaseReqVo projectInfo = projectService.addProjectRetuenInfo(projectId, projectReturnListReqVo);
				AppResponse<Object> appResponse = AppResponse.ok(projectInfo);
				appResponse.setMsg("添加项目回报信息成功");
				return appResponse;
			} else {
				AppResponse<Object> appResponse = AppResponse.fail(null);
				appResponse.setMsg("参数不正确");
				return appResponse;
			} 
		} catch (Exception e) {
			log.error("添加项目回报信息错误：{}", e.getMessage());
			AppResponse<Object> appResponse = AppResponse.fail(null);
			appResponse.setMsg(e.getMessage());
			return appResponse;
		}
	}
	
	/**
	 * 
	 * @param baseReqVo 用户登录 accessToken 信息
	 * @param projectReturnReqVo 项目回报信息
	 * @return
	 */
	@ApiOperation(value = "4. 确认信息")
	@PostMapping("/confirmInfo/{projectId}")
	public AppResponse<Object> confirmInfo(@PathVariable("projectId") String projectId, CreateProjectBaseReqVo baseReqVo) {
	
		// 获取传递的参数
		String accessToken = baseReqVo.getAccessToken();
		log.error("确认信息 accessToken：{}, baseReqVo:{}", accessToken, baseReqVo);
		
		try {
			// 判断参数是否为空
			if (!StringUtils.isEmpty(accessToken)) {
				CreateProjectBaseReqVo projectInfo = projectService.confirmProjectInfo(projectId);
				AppResponse<Object> appResponse = AppResponse.ok(projectInfo);
				appResponse.setMsg("确认信息成功");
				return appResponse;
			} else {
				AppResponse<Object> appResponse = AppResponse.fail(null);
				appResponse.setMsg("参数不正确");
				return appResponse;
			} 
		} catch (Exception e) {
			log.error("添加确认信息错误：{}", e.getMessage());
			AppResponse<Object> appResponse = AppResponse.fail(null);
			appResponse.setMsg(e.getMessage());
			return appResponse;
		}
	}

	@ApiOperation(value = "删除项目回报档位")
	@DeleteMapping("/return")
	public AppResponse<Object> deleteReturnDetail() {
		return AppResponse.ok("ok");
	}
	
	/**
	 * 文件上传
	 * @param files 一个或者多个图片
	 * @return
	 */
	@ApiOperation(value = "上传图片")
	@PostMapping("/upload")
	public AppResponse<Object> upload(@RequestParam("files") MultipartFile[] files) {
		ArrayList<String> fileUrlList = new ArrayList<>();
		for (MultipartFile file : files) {
			try {
				String fileUrl = aliOssTemplate.upload(file.getInputStream(), file.getOriginalFilename());
				fileUrlList.add(fileUrl);
			} catch (IOException e) {
				log.error("文件上传错误:{}", file.getOriginalFilename());
				e.printStackTrace();
				AppResponse<Object> resp = AppResponse.fail("");
				resp.setMsg("文件上传失败");
			}
		}
		return AppResponse.ok(fileUrlList);
	}

	@ApiOperation(value = "确认项目法人信息")
	@PostMapping("/confirm/legal")
	public AppResponse<Object> legal() {
		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "项目草稿保存")
	@PostMapping("/tempsave")
	public AppResponse<Object> tempsave() {
		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "项目提交审核申请")
	@PostMapping("/submit")
	public AppResponse<Object> submit() {
		return AppResponse.ok("ok");
	}

}
