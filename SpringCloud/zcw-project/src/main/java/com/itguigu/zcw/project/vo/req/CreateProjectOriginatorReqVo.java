package com.itguigu.zcw.project.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 创建项目第二步。项目发起人 Vo
 * 
 * @author rex
 *
 */
@Data
@ToString
@ApiModel
public class CreateProjectOriginatorReqVo {
	@ApiModelProperty("自我介绍")
	private String selfintroduction; // 自我介绍

	@ApiModelProperty("详细自我介绍")
	private String detailselfintroduction; // 详细自我介绍

	@ApiModelProperty("联系电话")
	private String telphone; // 联系电话

	@ApiModelProperty("客服电话")
	private String hotline; // 客服电话
}
