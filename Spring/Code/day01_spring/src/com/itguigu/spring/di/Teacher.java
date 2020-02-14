package com.itguigu.spring.di;

public class Teacher {
	private String tname;
	private Integer tage;
	
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Integer getTage() {
		return tage;
	}
	public void setTage(Integer tage) {
		this.tage = tage;
	}
	@Override
	public String toString() {
		return "Teacher [tname=" + tname + ", tage=" + tage + "]";
	}
	public Teacher(String tname, Integer tage) {
		super();
		this.tname = tname;
		this.tage = tage;
	}
	public Teacher() {
		super();
	}
	
}
