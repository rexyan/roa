package com.itguigu.ioc.life;

public class Person {
	private Integer id;
	private String sex;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		System.out.println("bean的生命周期-2: 为bean的属性设置值");
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person(Integer id, String sex, String name) {
		super();
		this.id = id;
		this.sex = sex;
		this.name = name;
	}
	public Person() {
		super();
		System.out.println("bean的生命周期-1: 通过构造器或工厂方法创建 bean 实例");
	}
	
	@Override
	public String toString() {
		System.out.println("bean的生命周期-4: 使用 bean");
		return "Person [id=" + id + ", sex=" + sex + ", name=" + name + "]";
	}
	
	public void init() {
		System.out.println("bean的生命周期-3: 初始化方法");
	}
	
	public void destory() {
		System.out.println("bean的生命周期-5: 调用bean的销毁方法");
	}
	
	
}
