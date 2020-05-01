package com.itguigu.zcw.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itguigu.zcw.commons.vo.resp.IndexRecommendRespVo;
import com.itguigu.zcw.project.bean.TAdvertisement;
import com.itguigu.zcw.project.bean.TAdvertisementExample;
import com.itguigu.zcw.project.bean.TProject;
import com.itguigu.zcw.project.bean.TProjectImages;
import com.itguigu.zcw.project.bean.TProjectImagesExample;
import com.itguigu.zcw.project.bean.TProjectImagesExample.Criteria;
import com.itguigu.zcw.project.bean.TReturn;
import com.itguigu.zcw.project.bean.TReturnExample;
import com.itguigu.zcw.project.mapper.TAdvertisementMapper;
import com.itguigu.zcw.project.mapper.TProjectImagesMapper;
import com.itguigu.zcw.project.mapper.TProjectMapper;
import com.itguigu.zcw.project.mapper.TReturnMapper;
import com.itguigu.zcw.project.service.TProjectInfoService;

@Service
public class TProjectInfoServiceImpl implements TProjectInfoService {
	@Autowired
	TAdvertisementMapper advertisementMapper;

	@Autowired
	TProjectMapper projectMapper;

	@Autowired
	TProjectImagesMapper projectImagesMapper;

	@Autowired
	TReturnMapper returnMapper;

	@Override
	public List<TAdvertisement> getIndexAdv() {
		TAdvertisementExample tAdvertisementExample = new TAdvertisementExample();
		List<TAdvertisement> listAdvertisements = advertisementMapper.selectByExample(tAdvertisementExample);
		return listAdvertisements;
	}

	@Override
	public List<IndexRecommendRespVo> getIndexRecommendProject() {
		ArrayList<IndexRecommendRespVo> indexRecommendProjectList = new ArrayList<>();
		// 查询推荐项目
		List<TProject> indexRecommendProject = projectMapper.getIndexRecommendProject();

		// 查询推荐项目图片
		for (TProject tProject : indexRecommendProject) {
			String imgUrl = projectImagesMapper.getImgUrl(tProject.getId());
			IndexRecommendRespVo indexRecommendRespVo = new IndexRecommendRespVo();
			BeanUtils.copyProperties(tProject, indexRecommendRespVo);
			indexRecommendRespVo.setImgurl(imgUrl);
			indexRecommendProjectList.add(indexRecommendRespVo);
		}
		return indexRecommendProjectList;
	}

	@Override
	public TProject getProjectInfo(Integer projectId) {
		TProject projectInfo = projectMapper.selectByPrimaryKey(projectId);
		return projectInfo;
	}

	@Override
	public List<TProjectImages> getProjectImages(Integer projectId) {
		TProjectImagesExample tProjectImagesExample = new TProjectImagesExample();
		Criteria projectimagesCriteria = tProjectImagesExample.createCriteria();
		projectimagesCriteria.andProjectidEqualTo(projectId);
		List<TProjectImages> projectImagesList = projectImagesMapper.selectByExample(tProjectImagesExample);
		return projectImagesList;
	}

	@Override
	public List<TReturn> getProjectReturnInfo(Integer projectId) {
		TReturnExample tReturnExample = new TReturnExample();
		tReturnExample.createCriteria().andProjectidEqualTo(projectId);
		List<TReturn> projectReturnList = returnMapper.selectByExample(tReturnExample);
		return projectReturnList;
	}

	@Override
	public TReturn getProjectReturnInfoById(Integer returnId) {
		return returnMapper.selectByPrimaryKey(returnId);
	}

	@Override
	public TReturn getReturnDetailById(Integer returnId) {
		return returnMapper.selectByPrimaryKey(returnId);
	}

}
