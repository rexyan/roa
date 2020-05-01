package com.itguigu.zcw.web.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.commons.vo.resp.ReturnPayConfirmRespVo;
import com.itguigu.zcw.commons.vo.resp.UserLoginRespVo;
import com.itguigu.zcw.web.bean.TMemberAddress;
import com.itguigu.zcw.web.service.TMemberServiceFeign;
import com.itguigu.zcw.web.service.TProjectServiceFeign;
import com.itguigu.zcw.web.vo.req.ProjectDetailRepVo;

@Controller
public class TProjectController {
	@Autowired
	TProjectServiceFeign projectServiceFeign;
	
	@Autowired
	TMemberServiceFeign memberServiceFeign;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@RequestMapping("/project/projectInfo")
	public String projectInfo(@RequestParam("id") Integer id, Model model) {
		// 查询项目详情
		AppResponse<ProjectDetailRepVo> projectDetail = projectServiceFeign.detail(id);
		model.addAttribute("projectDetail", projectDetail.getData());
		return "project/index";
	}
	
	@RequestMapping("/project/support/{projectId}/{returnId}")
	public String supportProject(@PathVariable("projectId") Integer projectId, @PathVariable("returnId") Integer returnId, Model model, HttpSession session) {
		AppResponse<ReturnPayConfirmRespVo> confirmReturn = projectServiceFeign.confirmReturn(projectId, returnId);
		model.addAttribute("confirmReturn", confirmReturn.getData());
		// 信息放入 session 中，方便下一步取用
		session.setAttribute("projectConfirmInfo", confirmReturn.getData());
		return "project/pay-step-1";
	}
	
	@RequestMapping("/project/confirm/order/{buyNum}")
	public String confirmOrder(Model model, HttpSession session, @PathVariable("buyNum")Integer buyNum) {
		UserLoginRespVo userSession = (UserLoginRespVo)session.getAttribute("memeber");
		if(userSession == null) {
			session.setAttribute("redirectUrl", "/project/confirm/order/" + buyNum);
			return "redirect:/login";
		}
		// 显示地址信息
		String accessToken = userSession.getAccessToken();
		//远程调用 user 服务，根据 accessToken 查询用户收获地址信息
		AppResponse<List<TMemberAddress>> memberAddress = memberServiceFeign.getMemberAddress(accessToken);
		model.addAttribute("memberAddress", memberAddress.getData());
		
		// 显示回报信息（从 session 中取）
		// 更新数量信息
		// 需要重新计算价格
		ReturnPayConfirmRespVo projectConfirmInfo = (ReturnPayConfirmRespVo)session.getAttribute("projectConfirmInfo");
		projectConfirmInfo.setNum(buyNum);
		projectConfirmInfo.setTotalPrice(new BigDecimal(buyNum * projectConfirmInfo.getPrice() + projectConfirmInfo.getFreight()));
		// 最后需要更新 Redis 缓存中的数据信息
		session.setAttribute("projectConfirmInfo", projectConfirmInfo);
		
		return "project/pay-step-2";
	}
}
