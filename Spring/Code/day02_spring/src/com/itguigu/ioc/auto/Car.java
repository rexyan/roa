package com.itguigu.ioc.auto;

/**
 * 员工的车
 * @author rex
 *
 */
public class Car {
	private Integer cid;
	private String cname;
	
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Car [cid=" + cid + ", cname=" + cname + "]";
	}
}
