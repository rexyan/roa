package com.itguigu.zcw.project.service;

import java.util.List;

import com.itguigu.zcw.commons.vo.resp.IndexRecommendRespVo;
import com.itguigu.zcw.project.bean.TAdvertisement;
import com.itguigu.zcw.project.bean.TProject;
import com.itguigu.zcw.project.bean.TProjectImages;
import com.itguigu.zcw.project.bean.TReturn;

public interface TProjectInfoService {
	
	/**
	 * 查询广告信息
	 * @return
	 */
	List<TAdvertisement> getIndexAdv();
	
	/**
	 * 查询推荐项目信息
	 * @return
	 */
	List<IndexRecommendRespVo> getIndexRecommendProject();
	
	/**
	 * 根据 ID 查询项目详细信息
	 * @param projectId
	 * @return
	 */
	TProject getProjectInfo(Integer projectId);
	
	/**
	 * 查询项目图片信息
	 * @param projectId
	 * @return
	 */
	List<TProjectImages> getProjectImages(Integer projectId);
	
	/**
	 * 获取项目回报信息
	 * @param projectId
	 * @return
	 */
	List<TReturn> getProjectReturnInfo(Integer projectId);
	
	/**
	 * 根据 returnId 获取回报信息
	 * @param returnId
	 * @return
	 */
	TReturn getProjectReturnInfoById(Integer returnId);

}
