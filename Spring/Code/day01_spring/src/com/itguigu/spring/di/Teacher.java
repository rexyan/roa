package com.itguigu.spring.di;

import java.util.List;
import java.util.Map;

public class Teacher {
	private String tname;  // 名称
	private Integer tage;  // 年龄
	private List<String> cls;  // 所教班级
	private List<Student> students;  // 所教学生
	private Map<String, String> bossMap;  // 领导
	
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
	
	public List<String> getCls() {
		return cls;
	}
	public void setCls(List<String> cls) {
		this.cls = cls;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public Map<String, String> getBossMap() {
		return bossMap;
	}
	public void setBossMap(Map<String, String> bossMap) {
		this.bossMap = bossMap;
	}
	@Override
	public String toString() {
		return "Teacher [tname=" + tname + ", tage=" + tage + ", cls=" + cls + ", students=" + students + ", bossMap="
				+ bossMap + "]";
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
