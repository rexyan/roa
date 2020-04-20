package com.itguigu.zcw.commons.vo.req;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreateProjectTReturnReqVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer projectid;

	private String type;

	private Integer supportmoney;

	private String content;

	private Integer count;

	private Integer signalpurchase;

	private Integer purchase;

	private Integer freight;

	private String invoice;

	private Integer rtndate;
}
