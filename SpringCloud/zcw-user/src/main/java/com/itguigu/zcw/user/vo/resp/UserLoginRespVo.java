package com.itguigu.zcw.user.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel
@ToString
@Data
public class UserLoginRespVo {
	@ApiModelProperty("用户登录成功后不返回用户 id。生成一个 accessToken 返回")
	private String accessToken;
	
	private String loginacct;
	
	private String username;
	
	private String email;
	
	private String authstatus;
	
	private String usertype;
	
	private String realname;
	
	private String cardnum;
	
	private String accttype;
}
