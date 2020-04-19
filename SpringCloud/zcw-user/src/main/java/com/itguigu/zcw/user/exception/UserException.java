package com.itguigu.zcw.user.exception;

import com.itguigu.zcw.user.enums.UserExceptionEnum;

/**
 * 自定义用户异常
 * @author rex
 *
 */
public class UserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserException(UserExceptionEnum userExceptionEnum) {
		super(userExceptionEnum.getMessage());
	}
	
}
