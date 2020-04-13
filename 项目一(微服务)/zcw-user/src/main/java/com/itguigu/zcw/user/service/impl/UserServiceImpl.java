package com.itguigu.zcw.user.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itguigu.zcw.user.bean.TMember;
import com.itguigu.zcw.user.bean.TMemberExample;
import com.itguigu.zcw.user.bean.TMemberExample.Criteria;
import com.itguigu.zcw.user.enums.UserExceptionEnum;
import com.itguigu.zcw.user.exception.UserException;
import com.itguigu.zcw.user.mapper.TMemberMapper;
import com.itguigu.zcw.user.service.UserService;
import com.itguigu.zcw.user.vo.req.UserRegistVo;
import com.itguigu.zcw.user.vo.resp.UserLoginRespVo;

import lombok.extern.slf4j.Slf4j;

@Transactional(readOnly = true)
@Slf4j
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	TMemberMapper memberMapper;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
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
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
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

	@Override
	public UserLoginRespVo userLogin(String loginacct, String password) {
		UserLoginRespVo userLoginRespVo = new UserLoginRespVo();
		
		TMemberExample tMemberExample = new TMemberExample();
		Criteria tmemberCriteria = tMemberExample.createCriteria();
		tmemberCriteria.andLoginacctEqualTo(loginacct);
		List<TMember> tmemberList = memberMapper.selectByExample(tMemberExample);
		if (tmemberList !=null && tmemberList.size() == 1) {
			for (TMember tMember : tmemberList) {
				// 判断密码是否相等
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				if (bCryptPasswordEncoder.matches(password, tMember.getUserpswd())) {
					// 账号密码正确
					// 随机生成 accessToken 
					String accessToken = UUID.randomUUID().toString().replace("-", "");
					// 使用 BeanUtils 将 Vo 和 实体类进行转换
					BeanUtils.copyProperties(tMember, userLoginRespVo);
					userLoginRespVo.setAccessToken(accessToken); 
					// 将 accessToken 写入 Redis中，key 为 accessToken，value 为数据 id
					stringRedisTemplate.opsForValue().set(accessToken, tMember.getId() + "");
					return userLoginRespVo;
				}else {
					log.info("账号:{}, 密码不正确, {}", loginacct, password);
					throw new UserException(UserExceptionEnum.LOGINACCT_OR_PASSWORD_ERROR);
				}
			}
		}else {
			log.info("账号不存在：{}", loginacct);
			throw new UserException(UserExceptionEnum.LOGINACCT_UNEXIST);
		}
		return null;
	}

}
