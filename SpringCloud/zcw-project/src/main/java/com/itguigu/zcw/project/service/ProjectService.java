package com.itguigu.zcw.project.service;

import java.util.List;

import com.itguigu.zcw.project.vo.req.CreateProjectBaseReqVo;
import com.itguigu.zcw.project.vo.req.CreateProjectBigReqVo;
import com.itguigu.zcw.project.vo.req.CreateProjectInfoReqVo;
import com.itguigu.zcw.project.vo.req.CreateProjectOriginatorReqVo;
import com.itguigu.zcw.project.vo.req.CreateProjectReturnReqVo;

public interface ProjectService {

	/**
	 * 项目初始化
	 * 
	 * @param accessToken
	 * @return 返回一个 Project ID
	 */
	CreateProjectBigReqVo initCreateroject(String accessToken);
	
	/**
	 * 添加项目及发起人信息
	 * 
	 * @param projectId 
	 * @param projectInfoReqVo
	 * @param projectOriginatorReqVo
	 * @return
	 */
	CreateProjectBaseReqVo addProjectOriginatorInfo(String projectId, CreateProjectInfoReqVo projectInfoReqVo, CreateProjectOriginatorReqVo projectOriginatorReqVo);
	
	/**
	 * 添加项目回报信息
	 * 
	 * @param projectId
	 * @param projectReturnListReqVo
	 * @return
	 */
	CreateProjectBaseReqVo addProjectRetuenInfo(String projectId, List<CreateProjectReturnReqVo> projectReturnListReqVo);
	
	/**
	 * 确认信息
	 * 
	 * @param projectId
	 * @return
	 */
	CreateProjectBaseReqVo confirmProjectInfo(String projectId);
}
