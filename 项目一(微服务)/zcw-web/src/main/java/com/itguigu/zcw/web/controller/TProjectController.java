package com.itguigu.zcw.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.commons.vo.resp.ReturnPayConfirmRespVo;
import com.itguigu.zcw.web.service.TProjectServiceFeign;
import com.itguigu.zcw.web.vo.req.ProjectDetailRepVo;

@Controller
public class TProjectController {
	@Autowired
	TProjectServiceFeign projectServiceFeign;
	
	@RequestMapping("/project/projectInfo")
	public String projectInfo(@RequestParam("id") Integer id, Model model) {
		// 查询项目详情
		AppResponse<ProjectDetailRepVo> projectDetail = projectServiceFeign.detail(id);
		model.addAttribute("projectDetail", projectDetail.getData());
		return "project/index";
	}
	
	@RequestMapping("/project/support/{projectId}/{returnId}")
	public String supportProject(@PathVariable("projectId") Integer projectId, @PathVariable("returnId") Integer returnId, Model model) {
		AppResponse<ReturnPayConfirmRespVo> confirmReturn = projectServiceFeign.confirmReturn(projectId, returnId);
		model.addAttribute("confirmReturn", confirmReturn.getData());
		return "project/pay-step-1";
	}
}
