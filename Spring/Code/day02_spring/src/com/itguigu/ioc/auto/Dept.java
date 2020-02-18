package com.itguigu.ioc.auto;

/**
 * 员工的部门
 * @author rex
 *
 */
public class Dept {
	private Integer did;
	private String dname;
	
	
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	@Override
	public String toString() {
		return "Dept [did=" + did + ", dname=" + dname + "]";
	}
	
}
