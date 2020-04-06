package com.itguigu.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itguigu.springboot.bean.TAdmin;
import com.itguigu.springboot.mapper.TAdminMapper;
import com.itguigu.springboot.service.TAdminService;

// 如果不想单独设置还可以将注解设置在类上，这样类中的方法都会有这个注解（生效的时候采用就近原则，方法上有就使用方法上的，方法上没有就使用类上的）
@Transactional(readOnly = true)
@Service
public class TAdminServiceImpl implements TAdminService {
	@Autowired
	TAdminMapper tadminMapper;
	
	// 如果是非查询，还可以设置事务隔离级别，传播行为，rollbackFor 等
	// @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	
	// 加上事务，因为是查询，所有只设置 readOnly = true 即可
	// @Transactional(readOnly = true)
	@Override
	public TAdmin getTAdminById(Integer id) {
		return tadminMapper.getTAdminById(id);
	}
}
