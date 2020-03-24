package com.atguigu.security.component;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		// rawPassword 为密码的原文
		return MD5Util.digest(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// 比较密码是否一致
		// rawPassword 是传入的密码的原文， encodedPassword 是数据库中查询的来的密码信息
		return encodedPassword.equals(encode(rawPassword));
	}

}
