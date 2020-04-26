package com.itguigu.zcw.commons.vo.resp;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel
public class IndexRecommendRespVo implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("项目ID")
	private Integer id;
	
	@ApiModelProperty("项目名称")
	private String name;
	
	@ApiModelProperty("项目描述")
	private String remark;
	
	@ApiModelProperty("项目图片地址")
	private String imgurl;
}
