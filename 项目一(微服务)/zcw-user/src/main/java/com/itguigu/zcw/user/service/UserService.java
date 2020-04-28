package com.itguigu.zcw.user.service;

import java.util.List;

import com.itguigu.zcw.commons.vo.req.UserRegistVo;
import com.itguigu.zcw.commons.vo.resp.UserLoginRespVo;
import com.itguigu.zcw.user.bean.TMember;
import com.itguigu.zcw.user.bean.TMemberAddress;

public interface UserService {
	
	/**
	 * 判断邮箱是否存在
	 * @param email
	 * @return
	 */
	Boolean emailExist(String email);
	
	/**
	 * 保存用户信息
	 * @param userRegistVo
	 * @return
	 */
	int saveMember(UserRegistVo userRegistVo);
	
	/**
	 * 判断账号是否存在
	 * @param userpaswd
	 * @return
	 */
	Boolean loginacctExist(String userpaswd);
	
	/**
	 * 用户登录
	 * @param loginacct
	 * @param password
	 * @return
	 */
	UserLoginRespVo userLogin(String loginacct, String password);
	
	/**
	 * 根据 ID 获取用户信息
	 * @param memberId
	 * @return
	 */
	TMember getMemberById(Integer memberId);
	
	/**
	 * 根据 accessToken 查询用户收获地址信息
	 * @param accessToken
	 * @return
	 */
	List<TMemberAddress> getMemberAddress(String accessToken);

}
