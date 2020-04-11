package com.itguigu.zcw.user.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.itguigu.zcw.user.bean.TMember;
import com.itguigu.zcw.user.bean.TMemberExample;
import com.itguigu.zcw.user.bean.TMemberExample.Criteria;
import com.itguigu.zcw.user.enums.UserExceptionEnum;
import com.itguigu.zcw.user.exception.UserException;
import com.itguigu.zcw.user.mapper.TMemberMapper;
import com.itguigu.zcw.user.service.UserService;
import com.itguigu.zcw.user.vo.req.UserRegistVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	TMemberMapper memberMapper;
	
	@Override
	public Boolean emailExist(String email) {
		try {
			TMemberExample tMemberExample = new TMemberExample();
			Criteria tmemCriteria = tMemberExample.createCriteria();
			tmemCriteria.andEmailEqualTo(email);
			List<TMember> memberList = memberMapper.selectByExample(tMemberExample);
			// 判断邮箱是否存在
			return memberList.size()>0 ? true : false;
		} catch (Exception e) {
			throw new UserException(UserExceptionEnum.EMAIL_EXIST);
		}
		
	}

	@Override
	public int saveMember(UserRegistVo userRegistVo) {
		try {
			// 将 Vo 转换为 TMember 实体对象 (名称匹配的就转, 不匹配就不转，但是也不报错)
			TMember tMember = new TMember();
			BeanUtils.copyProperties(userRegistVo, tMember);
			// 设置其他前段没有传递，但是数据库不能为空的参数
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String bCryptPassword = bCryptPasswordEncoder.encode(userRegistVo.getUserpaswd());
			// userpswd
			tMember.setUserpswd(bCryptPassword);
			// 设置 username
			tMember.setUsername(userRegistVo.getLoginacct());
			// 新增操作
			return memberMapper.insertSelective(tMember);
		} catch (Exception e) {
			log.error("新增用户失败：{}", e.getMessage());
			throw new UserException(UserExceptionEnum.REGIST_ERROR);
		}
	}

	@Override
	public Boolean loginacctExist(String userpaswd) {
		try {
			TMemberExample tMemberExample = new TMemberExample();
			Criteria tmemCriteria = tMemberExample.createCriteria();
			tmemCriteria.andUserpswdEqualTo(userpaswd);
			List<TMember> memberList = memberMapper.selectByExample(tMemberExample);
			// 判断邮箱是否存在
			return memberList.size()>0 ? true : false;
		} catch (Exception e) {
			throw new UserException(UserExceptionEnum.LOGINACCT_EXIST);
		}
	}

}
