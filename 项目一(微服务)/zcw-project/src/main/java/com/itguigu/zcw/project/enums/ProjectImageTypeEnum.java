package com.itguigu.zcw.project.enums;

public enum ProjectImageTypeEnum {
	HEADER((byte)0, "头图"), 
	DETAILS((byte)1, "详细图");
	
	private byte code;
	private String type;
	public byte getCode() {
		return code;
	}
	public void setCode(byte code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private ProjectImageTypeEnum(byte code, String type) {
		this.code = code;
		this.type = type;
	}
	private ProjectImageTypeEnum() {
	}
}
