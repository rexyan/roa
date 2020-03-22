package com.atguigu.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration // 声明当前类是一个配置类，相当于 xml 配置文件作用
@EnableWebSecurity // 启用 SpringSecurity 安全机制
public class AppWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 默认规则，所有请求都受到限制，并且会跳转到自动生成的login 页面
		super.configure(http); 
		
		// 实验1，授权放行登录页面和静态规则（默认规则现在两者都不能访问）。其他的全部进行拦截
		http.authorizeRequests().antMatchers("/layui/**", "/index.jsp").permitAll().anyRequest().authenticated();
		
		// 实验2，默认及自定义登录页面
		// 对于无权限访问（403）的地址，那么跳转默认的登录页面。404 资源不存在是不会跳转的（404数据有权限，只是资源不存在）
		// http.formLogin();
		
		// 自定义登录页。页面地址是 /index.jsp，账号参数名称是 loginacct，密码参数名称是 userpswd，处理请求的地址是 /doLogin。
		// 认证成功后跳转到 /main.html 这个地址。（注意需要加上 _csrf）
		http.formLogin()
		.loginPage("/index.jsp")
		.usernameParameter("loginacct")
		.passwordParameter("userpswd")
		.loginProcessingUrl("/doLogin")
		.defaultSuccessUrl("/main.html");
		
		// 关闭 csrf
		http.csrf().disable();
		
	}
}
