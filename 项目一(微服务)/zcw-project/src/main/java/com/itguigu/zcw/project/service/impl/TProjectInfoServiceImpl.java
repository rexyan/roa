package com.itguigu.zcw.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itguigu.zcw.project.bean.TAdvertisement;
import com.itguigu.zcw.project.bean.TAdvertisementExample;
import com.itguigu.zcw.project.mapper.TAdvertisementMapper;
import com.itguigu.zcw.project.service.TProjectInfoService;

@Service
public class TProjectInfoServiceImpl implements TProjectInfoService {
	@Autowired
	TAdvertisementMapper advertisementMapper;
	
	@Override
	public List<TAdvertisement> getIndexAdv() {
		TAdvertisementExample tAdvertisementExample = new TAdvertisementExample();
		List<TAdvertisement> listAdvertisements = advertisementMapper.selectByExample(tAdvertisementExample);
		return listAdvertisements;
	}

}
