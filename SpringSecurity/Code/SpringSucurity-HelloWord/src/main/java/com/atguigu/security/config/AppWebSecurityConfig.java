package com.atguigu.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

@Configuration // 声明当前类是一个配置类，相当于 xml 配置文件作用
@EnableWebSecurity // 启用 SpringSecurity 安全机制
public class AppWebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 默认规则
		// super.configure(auth);
		
		// 基于内存的认证方式，有 zhangsan 和 lisisi 两个用户。zhangsan 的角色是学徒，lisisi拥有的权限是 "罗汉拳" 和 "吸星大法"
 		// auth.inMemoryAuthentication().withUser("zhangsan").password("123456").roles("学徒")
		// .and()
		// .withUser("lisisi").password("123").authorities("罗汉拳", "吸星大法");
		
		//根据用户名查询出用户的详细信息
		// auth.userDetailsService(userDetailsService); 
		
		// 采用 md5 的方式进行比较
		// auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		
		// 采用 BCrypt 的方式进行比较
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 默认规则，所有请求都受到限制，并且会跳转到自动生成的login 页面
		// super.configure(http); 
		
		// 实验1，授权放行登录页面和静态规则（默认规则现在两者都不能访问）。其他的全部进行拦截
		// http.authorizeRequests().antMatchers("/layui/**", "/index.jsp").permitAll().anyRequest().authenticated();
		
		// 实验5，基于角色的访问控制
		http.authorizeRequests().antMatchers("/layui/**", "/index.jsp").permitAll()
		.antMatchers("/level1/**").hasRole("学徒")
		.antMatchers("/level2/**").hasRole("大师")
		.antMatchers("/level3/**").hasRole("宗师")
		.anyRequest().authenticated();
		
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
		// http.csrf().disable();
		
		// 实验4 - 注销
		// 默认注销规则，路径是 /logout，如果 csrf 开启，必须 post 方式的 /logout 请求，表单中需要增加 csrf token
		// http.logout();
		
		//  自定义注销。设置自定义路径是 /doLogout，注销成功后跳转到 /index.jsp
		http.logout().logoutUrl("/doLogout").logoutSuccessUrl("/index.jsp");
		
		// 实验6 - 自定义访问拒绝处理页面
		http.exceptionHandling().accessDeniedPage("/unauth.html");
		
		// 实验7 - 记住我 基于cookie
		// http.rememberMe();

		// 实验7 - 记住我 基于数据库
		JdbcTokenRepositoryImpl ptr = new JdbcTokenRepositoryImpl();
		ptr.setDataSource(dataSource);
		http.rememberMe().tokenRepository(ptr);

	}
}
