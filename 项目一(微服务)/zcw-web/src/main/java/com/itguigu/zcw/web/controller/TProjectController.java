package com.itguigu.zcw.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.commons.vo.resp.ProjectDetailRepVo;
import com.itguigu.zcw.web.service.TProjectServiceFeign;

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
}
