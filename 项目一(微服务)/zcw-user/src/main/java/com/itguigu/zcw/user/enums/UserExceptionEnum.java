package com.itguigu.zcw.user.enums;

public enum UserExceptionEnum {
	EMAIL_EXIST(11, "邮箱已经存在"),
	LOGINACCT_EXIST(12, "账号已经存在"),
	LOGINACCT_LOCKED(13, "账号已被锁定"),
	REGIST_ERROR(14, "注册账号错误"),
	LOGINACCT_OR_PASSWORD_ERROR(15, "账号或密码不正确"),
	LOGINACCT_UNEXIST(16, "账号不存在"),
	LOGIN_ERROR(17, "注册账号错误"),;

	private int code;
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private UserExceptionEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private UserExceptionEnum() {
	}
}
