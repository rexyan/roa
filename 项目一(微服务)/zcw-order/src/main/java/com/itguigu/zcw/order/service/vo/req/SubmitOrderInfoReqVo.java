package com.itguigu.zcw.order.service.vo.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel
public class SubmitOrderInfoReqVo implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("收货地址")
	private String address;
	
	@ApiModelProperty("是否需要发票：0-不需,1-需要")
	private byte invoice;
	
	@ApiModelProperty("发票抬头")
	private String invoictitle;
	
	@ApiModelProperty("订单备注")
	private String remark;
}
