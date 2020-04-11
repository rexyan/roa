package com.itguigu.zcw.user.enums;

public enum UserExceptionEnum {
	EMAIL_EXIST(1, "邮箱已经存在"),
	LOGINACCT_EXIST(2, "账号已经存在"),
	LOGINACCT_LOCKED(3, "账号已被锁定"),
	REGIST_ERROR(4, "注册账号错误");

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
