package com.itguigu.zcw.project.enums;

public enum ProjectExceptionEnum {
	INIT_PROJECT_ERROR(21, "项目初始化错误"), 
	USER_NOT_LOGIN(22, "用户未登录"),
	NOT_FOUNT_PROJECT_INFO(23, "未找到项目信息");

	private Integer code;
	private String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private ProjectExceptionEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	private ProjectExceptionEnum() {
	}
}
