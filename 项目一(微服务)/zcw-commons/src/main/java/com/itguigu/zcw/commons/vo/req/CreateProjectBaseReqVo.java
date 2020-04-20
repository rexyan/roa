package com.itguigu.zcw.commons.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 创建项目第一步，基础 ReqVo
 * @author rex
 *
 */
@Data
@ToString
@ApiModel
public class CreateProjectBaseReqVo {
	// 用户 accessToken 
	@ApiModelProperty("用户登录 Token")
	private String accessToken;
}
