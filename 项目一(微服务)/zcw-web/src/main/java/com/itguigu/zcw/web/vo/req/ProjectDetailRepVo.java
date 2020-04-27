package com.itguigu.zcw.web.vo.req;

import java.util.ArrayList;
import java.util.List;

import com.itguigu.zcw.commons.bean.TReturn;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProjectDetailRepVo {

	private Integer id;

	private String name;

	private String remark;

	private Long money;

	private Integer day;

	private String status;

	private String deploydate;

	private Long supportmoney;

	private Integer supporter;

	private Integer completion;

	private Integer memberid;

	private String createdate;

	private Integer follower;
	
	private String headerImage;// 项目头部图片 
	
	private List<String> detailsImage = new ArrayList<>();// 项目详情图片 
	
	
	private List<TReturn> projectReturns = new ArrayList<>();// 项目回报 
}
