package com.itguigu.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class myFactory implements FactoryBean<Car>{

	@Override
	public Car getObject() throws Exception {
		Car car = new Car();
		car.setBrand("奥迪");
		car.setPrice(99999.0);
		return car;
	}

	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}
