package com.itguigu.zcw.user.service;

import com.itguigu.zcw.user.vo.req.UserRegistVo;

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

}
