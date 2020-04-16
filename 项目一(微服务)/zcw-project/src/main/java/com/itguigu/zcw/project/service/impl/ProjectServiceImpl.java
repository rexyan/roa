package com.itguigu.zcw.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.mime.MimeWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.itguigu.zcw.commons.utils.AppDateUtils;
import com.itguigu.zcw.project.bean.TProject;
import com.itguigu.zcw.project.bean.TProjectImages;
import com.itguigu.zcw.project.bean.TReturn;
import com.itguigu.zcw.project.constant.ProjectConstant;
import com.itguigu.zcw.project.enums.ProjectExceptionEnum;
import com.itguigu.zcw.project.enums.ProjectImageTypeEnum;
import com.itguigu.zcw.project.enums.ProjectStatusEnum;
import com.itguigu.zcw.project.exception.ProjectException;
import com.itguigu.zcw.project.mapper.TProjectImagesMapper;
import com.itguigu.zcw.project.mapper.TProjectMapper;
import com.itguigu.zcw.project.service.ProjectService;
import com.itguigu.zcw.project.vo.req.CreateProjectBaseReqVo;
import com.itguigu.zcw.project.vo.req.CreateProjectBigReqVo;
import com.itguigu.zcw.project.vo.req.CreateProjectInfoReqVo;
import com.itguigu.zcw.project.vo.req.CreateProjectOriginatorReqVo;
import com.itguigu.zcw.project.vo.req.CreateProjectReturnReqVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	TProjectMapper projectMapper;
	
	@Autowired
	TProjectImagesMapper projectImagesMapper;

	@Override
	public CreateProjectBigReqVo initCreateroject(String accessToken) {
		// 判断当前用户是否已经登录
		String memberid = stringRedisTemplate.opsForValue().get(accessToken);
		if (StringUtils.isEmpty(memberid)) {
			log.error("项目初始化错误, 用户未登录：{}", accessToken);
			throw new ProjectException(ProjectExceptionEnum.USER_NOT_LOGIN);
		}
		// 生成一个 Project ID
		String projectId = UUID.randomUUID().toString().replace("-", "");

		// 创建大 Vo，将数据往里面塞
		CreateProjectBigReqVo projectBigReqVo = new CreateProjectBigReqVo();
		projectBigReqVo.setMemberid(Integer.parseInt(memberid));
		projectBigReqVo.setProjectToken(projectId);
		projectBigReqVo.setAccessToken(accessToken);

		// 将数据序列化为 json 后写入 redis 中
		String projectBigReqVoJsonStr = JSON.toJSONString(projectBigReqVo);
		stringRedisTemplate.opsForValue().set(ProjectConstant.PROJECT_TEMP_PREFIX + projectId, projectBigReqVoJsonStr,
				5, TimeUnit.MINUTES);

		log.error("项目初始化成功, projectId：{}", projectId);
		return projectBigReqVo;
	}

	@Override
	public CreateProjectBigReqVo addProjectOriginatorInfo(String projectId, CreateProjectInfoReqVo projectInfoReqVo,
			CreateProjectOriginatorReqVo projectOriginatorReqVo) {
		// 根据 projectId 取出 Redis 中的大 Vo 数据
		String projectInfoStr = stringRedisTemplate.opsForValue().get(projectId);
		if (StringUtils.isEmpty(projectInfoStr)) {
			throw new ProjectException(ProjectExceptionEnum.NOT_FOUNT_PROJECT_INFO);
		}
		// 将大 Vo 数据转换为对象
		CreateProjectBigReqVo projectInfoBaseVo = JSON.parseObject(projectInfoStr, CreateProjectBigReqVo.class);
		// 将 项目基本信息 塞入大 Vo 中（List数据部分不能使用 BeanUtils.copyProperties，需要手动设置值）
		projectInfoBaseVo.setTypeids(projectInfoReqVo.getTypeids());
		projectInfoBaseVo.setTagids(projectInfoReqVo.getTagids());
		projectInfoBaseVo.setDetailsImage(projectInfoReqVo.getDetailsImage());
		BeanUtils.copyProperties(projectInfoReqVo, projectInfoBaseVo);

		// 将 项目发起人信息 塞入大 Vo 中
		BeanUtils.copyProperties(projectOriginatorReqVo, projectInfoBaseVo);

		// 将大 Vo 重新序列化，存入 Redis 中
		String projectInfoBaseStr = JSON.toJSONString(projectInfoBaseVo);
		stringRedisTemplate.opsForValue().set(ProjectConstant.PROJECT_TEMP_PREFIX + projectId, projectInfoBaseStr, 5,
				TimeUnit.MINUTES);
		return projectInfoBaseVo;
	}

	@Override
	public CreateProjectBigReqVo addProjectRetuenInfo(String projectId,
			CreateProjectReturnReqVo[] projectReturnListReqVo) {
		// 根据 projectId 取出 Redis 中的大 Vo 数据
		String projectInfoStr = stringRedisTemplate.opsForValue().get(projectId);
		if (StringUtils.isEmpty(projectInfoStr)) {
			throw new ProjectException(ProjectExceptionEnum.NOT_FOUNT_PROJECT_INFO);
		}
		// 将大 Vo 数据转换为对象
		CreateProjectBigReqVo projectInfoBaseVo = JSON.parseObject(projectInfoStr, CreateProjectBigReqVo.class);

		// 将 projectReturnReqVo 信息塞入大 Vo 中
		ArrayList<TReturn> tReturnList = new ArrayList<>();
		for (CreateProjectReturnReqVo projectReturnReqVo : projectReturnListReqVo) {
			TReturn tReturn = new TReturn();
			BeanUtils.copyProperties(projectReturnReqVo, tReturn);
			tReturnList.add(tReturn);
		}
		projectInfoBaseVo.setProjectReturns(tReturnList);

		// 将大 Vo 重新序列化，存入 Redis 中
		String projectInfoBaseStr = JSON.toJSONString(projectInfoBaseVo);
		stringRedisTemplate.opsForValue().set(ProjectConstant.PROJECT_TEMP_PREFIX + projectId, projectInfoBaseStr, 5,
				TimeUnit.MINUTES);
		return projectInfoBaseVo;
	}

	@Transactional
	@Override
	public CreateProjectBaseReqVo confirmProjectInfo(String projectId) {
		// 根据 projectId 取出 Redis 中的大 Vo 数据
		String projectInfoStr = stringRedisTemplate.opsForValue().get(projectId);
		if (StringUtils.isEmpty(projectInfoStr)) {
			throw new ProjectException(ProjectExceptionEnum.NOT_FOUNT_PROJECT_INFO);
		}
		// 将大 Vo 数据转换为对象
		CreateProjectBigReqVo projectInfoBaseVo = JSON.parseObject(projectInfoStr, CreateProjectBigReqVo.class);

		// 保存 project 信息
		TProject tProject = new TProject();
		tProject.setName(projectInfoBaseVo.getName());
		tProject.setRemark(projectInfoBaseVo.getRemark());
		tProject.setMoney((long) projectInfoBaseVo.getMoney());
		tProject.setDay(projectInfoBaseVo.getDay());
		tProject.setStatus(ProjectStatusEnum.DRAFT.getStatus());
		tProject.setDeploydate(AppDateUtils.getFormatTime());
		tProject.setSupportmoney(0l);
		tProject.setSupporter(0);
		tProject.setCompletion(0);
		tProject.setMemberid(projectInfoBaseVo.getMemberid());
		tProject.setCreatedate(AppDateUtils.getFormatTime());
		tProject.setFollower(0);
		int insertProjectId = projectMapper.insertSelective(tProject); // 获取新增后返回的自增id

		// 保存项目图片信息
		// 项目头图
		TProjectImages tProjectImages = new TProjectImages();
		tProjectImages.setProjectid(insertProjectId);
		tProjectImages.setImgurl(projectInfoBaseVo.getHeaderImage());
		tProjectImages.setImgtype(ProjectImageTypeEnum.HEADER.getCode());
		projectImagesMapper.insertSelective(tProjectImages);
		// 项目详情
		List<String> detailsImage = projectInfoBaseVo.getDetailsImage();
		for (String detailImage : detailsImage) {
			TProjectImages tProjectDetailImage = new TProjectImages();
			tProjectDetailImage.setProjectid(insertProjectId);
			tProjectDetailImage.setImgurl(detailImage);
			tProjectDetailImage.setImgtype(ProjectImageTypeEnum.DETAILS.getCode());
			projectImagesMapper.insertSelective(tProjectDetailImage);
		}
		
		// 保存发起人信息
		
		
		
		return null;
	}

	private void TProjectImages() {
		// TODO Auto-generated method stub
		
	}
}
