package com.itguigu.zcw.commons.vo.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel // swagger
@Data // lombok
@ToString
public class UserRegistVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("手机号")
	private String loginacct;

	@ApiModelProperty("密码")
	private String userpaswd;

	@ApiModelProperty("邮箱")
	private String email;

	@ApiModelProperty("验证码")
	private String code;
	
	@ApiModelProperty("用户类型 0-个人，1-企业")
	private String usertype;
}
