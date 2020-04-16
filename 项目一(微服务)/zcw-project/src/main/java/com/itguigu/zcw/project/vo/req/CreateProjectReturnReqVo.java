package com.itguigu.zcw.project.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 创建项目第二步。设置项目回报Vo
 * @author rex
 *
 */
@ApiModel
@Data
@ToString
public class CreateProjectReturnReqVo {
	@ApiModelProperty("回报类型")
	private String type;

	@ApiModelProperty("支持金额（元）")
    private Integer supportmoney;
	
	@ApiModelProperty("回报内容")
    private String content;
	
	@ApiModelProperty("回报数量（名）")
    private Integer count;
	
	@ApiModelProperty("单笔限购")
    private Integer signalpurchase;

	@ApiModelProperty("运费(元)")
    private Integer purchase;
	
	@ApiModelProperty("发票")
    private Integer freight;

	@ApiModelProperty("说明图片")
    private String invoice;
	
	@ApiModelProperty("回报时间")
    private Integer rtndate;
}
