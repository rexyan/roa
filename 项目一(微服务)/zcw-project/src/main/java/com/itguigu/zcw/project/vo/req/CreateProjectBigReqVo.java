package com.itguigu.zcw.project.vo.req;

import java.util.ArrayList;
import java.util.List;

import com.itguigu.zcw.project.bean.TReturn;

import lombok.Data;
import lombok.ToString;

/**
 * 收集每一个步骤数据的大 Vo
 * @author rex
 *
 */
@Data
@ToString
public class CreateProjectBigReqVo extends CreateProjectBaseReqVo{
	// ********* 步骤一（同意协议） ********* 
	// 正在创建的项目ID（临时的项目ID）
	private String projectToken;
	
	// 会员ID（正在创建项目的人的ID）
	private Integer memberid;
	
	// ********* 步骤二（1. 项目及发起人信息） ********* 
	// 项目基本信息
	private List<Integer> typeids = new ArrayList<>(); //项目的分类id 
    private List<Integer> tagids = new ArrayList<>();  //项目的标签id 
    private String name;   //项目名称 
    private String remark; //项目简介 
    private Integer money; //筹资金额 
    private Integer day;   //筹资天数 
    private String headerImage;//项目头部图片 
    private List<String> detailsImage = new ArrayList<>();//项目详情图片 
    
    // 发起人信息：自我介绍，详细自我介绍，联系电话，客服电话
    private String selfintroduction; // 自我介绍
    private String detailselfintroduction; // 详细自我介绍
    private String telphone; // 联系电话
    private String hotline; // 客服电话
   
    // ********* 步骤三（2. 回报设置） ********* 
    private List<TReturn> projectReturns = new ArrayList<>();//项目回报 
    
    // ********* 步骤四（3. 确认信息） ********* 
 
}
