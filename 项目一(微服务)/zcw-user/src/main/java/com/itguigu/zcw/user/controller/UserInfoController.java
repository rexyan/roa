package com.itguigu.zcw.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.user.bean.TMember;
import com.itguigu.zcw.user.bean.TMemberAddress;
import com.itguigu.zcw.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "用户信息查询模块")
@RequestMapping("/user")
@RestController
public class UserInfoController {
	@Autowired
	UserService userService;

	@ApiOperation(value = "获取用户信息")
	@GetMapping("/info/{memberId}")
	public AppResponse<TMember> getMemberInfo(@PathVariable("memberId") Integer memberId) {
		TMember tMember = userService.getMemberById(memberId);
		return AppResponse.ok(tMember);
	}

	@ApiOperation(value = "获取用户收获地址信息")
	@GetMapping("/info/address{accessToken}")
	public AppResponse<List<TMemberAddress>> getMemberAddress(@PathVariable("accessToken") String accessToken) {
		List<TMemberAddress> tMemberAddressList = userService.getMemberAddress(accessToken);
		return AppResponse.ok(tMemberAddressList);
	}

}
