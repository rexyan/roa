package com.itguigu.zcw.user.controller;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.support.logging.Log;
import com.itguigu.zcw.enums.AccttypeEnume;
import com.itguigu.zcw.enums.AuthEnume;
import com.itguigu.zcw.enums.UserTypeEnume;
import com.itguigu.zcw.user.components.SmsTemplate;
import com.itguigu.zcw.user.service.UserService;
import com.itguigu.zcw.user.vo.req.UserRegistVo;
import com.itguigu.zcw.validation.ValidationEmail;
import com.itguigu.zcw.vo.resp.AppResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;

@Slf4j
@Api(tags = "用户登陆注册模块")
@RequestMapping("/user")
@RestController
public class UserLoginRegistController {
	
	@Autowired
	SmsTemplate smsTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	UserService userService;
	
//	@ApiOperation(value="用户登陆") 
//	@ApiImplicitParams(value={
//			@ApiImplicitParam(value="登陆账号",name="loginacct"),
//			@ApiImplicitParam(value="用户密码",name="password")
//	})
//	@PostMapping("/login")
//	public AppResponse<LoginRespVo> login(String loginacct,String password){
//		LoginRespVo respVo = new LoginRespVo();
//		TMember member = new TMember();
//		member.setLoginacct("15353475896");
//		member.setRealname("张三");
//		member.setUsertype(UserTypeEnume.USER_VIP2.getCode());
//		member.setAccttype(AccttypeEnume.BUSINESS_MAN.getCode());
//		member.setAuthstatus(AuthEnume.AUTHING.getCode());
//		member.setCardnum("145645893212365646");
//		member.setUsername("zhangsan");
//		
//		respVo.setAccessToken(UUID.randomUUID().toString().replace("-", ""));
//		respVo.setMember(member);
//		return AppResponse.ok(respVo);
//	} 

	@ApiOperation(value = "用户注册")
	@PostMapping("/register")
	public AppResponse<Object> register(UserRegistVo userRegistVo) {
		log.info("用户注册，接收到请求参数：{}", userRegistVo);
		
		// 获取注册手机号码
		String loginacct = userRegistVo.getLoginacct();
		if (!StringUtils.isEmpty(loginacct)) {
			// 校验验证码
			String code = stringRedisTemplate.opsForValue().get(loginacct);
			if (!StringUtils.isEmpty(code) && code.equals(userRegistVo.getCode())) {
				// 校验邮箱格式是否正确
				boolean isEmail = ValidationEmail.isEmail(userRegistVo.getEmail());
				if(!isEmail) {
					AppResponse<Object> resp = AppResponse.fail(null);
					resp.setMsg("邮箱格式不正确");
					return resp;
				}
				try {
					// 校验邮箱是否已经存在
					Boolean emailExist = userService.emailExist(userRegistVo.getEmail());
					if (emailExist) {
						AppResponse<Object> resp = AppResponse.fail(null);
						resp.setMsg("邮箱已存在");
						return resp;
					}
					// 校验账户是否已经存在
					Boolean loginExist = userService.loginacctExist(userRegistVo.getUserpaswd());
					if (emailExist) {
						AppResponse<Object> resp = AppResponse.fail(null);
						resp.setMsg("账号已被注册");
						return resp;
					}
					
					// 注册
					int i = userService.saveMember(userRegistVo);
					if (i==1) {
						// 删除验证码信息
						stringRedisTemplate.delete(loginacct);
						AppResponse<Object> resp = AppResponse.ok("ok");
						resp.setMsg("注册成功");
						return resp;
					}else {
						AppResponse<Object> resp = AppResponse.fail(null);
						resp.setMsg("注册失败");
						return resp;
					}
				} catch (Exception e) {
					log.error("注册失败， 系统错误:{}", e.getMessage());
					AppResponse<Object> resp = AppResponse.fail(null);
					resp.setMsg("系统错误");
					return resp;
				}
			}else {
				AppResponse<Object> resp = AppResponse.fail(null);
				resp.setMsg("验证码已经过期, 请重新获取");
				return resp;
			}
		}else {
			AppResponse<Object> resp = AppResponse.fail(null);
			resp.setMsg("请输入注册手机号");
			return resp;
		}
		
	}

	@ApiOperation(value = "发送短信验证码")
	@PostMapping("/sendsms")
	public AppResponse<Object> sendsms(String loginacct) {
		StringBuffer code = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			code.append(random.nextInt(10));
		}
		HashMap<String, String> querys = new HashMap<>();
		querys.put("mobile", loginacct);
		querys.put("param", "code:" + code.toString());
		querys.put("tpl_id", "TP1711063");
		
		AppResponse<String> status = smsTemplate.sendCode(querys);
		if (0 == status.getCode()) {
			// 存入 redis，并设置过期时间
			stringRedisTemplate.opsForValue().set(loginacct, code.toString(), 5, TimeUnit.MINUTES);
			log.info("发送短信成功！");
			return AppResponse.ok(status.getMsg());
		}else {
			log.info("发送短信失败！");
			return AppResponse.fail(status.getMsg());
		}
	}

	@ApiOperation(value = "验证短信验证码")
	@PostMapping("/valide")
	public AppResponse<Object> valide() {
		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "重置密码")
	@PostMapping("/reset")
	public AppResponse<Object> reset() {
		return AppResponse.ok("ok");
	}

}
