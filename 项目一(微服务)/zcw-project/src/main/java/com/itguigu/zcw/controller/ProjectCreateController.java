package com.itguigu.zcw.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.bouncycastle.cert.ocsp.RespData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.components.AliOssTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "项目发起模块")
@RequestMapping("/project/create")
@RestController
public class ProjectCreateController {

	@Autowired
	AliOssTemplate aliOssTemplate;

	@ApiOperation(value = "项目初始创建")
	@PostMapping("/init")
	public AppResponse<Object> init() {
		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "添加项目回报档位")
	@PostMapping("/return")
	public AppResponse<Object> returnDetail() {
		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "删除项目回报档位")
	@DeleteMapping("/return")
	public AppResponse<Object> deleteReturnDetail() {
		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "上传图片")
	@PostMapping("/upload")
	public AppResponse<Object> upload(@RequestParam("files") MultipartFile[] files) {
		ArrayList<String> fileUrlList = new ArrayList<>();
		for (MultipartFile file : files) {
			try {
				String fileUrl = aliOssTemplate.upload(file.getInputStream(), file.getOriginalFilename());
				fileUrlList.add(fileUrl);
			} catch (IOException e) {
				log.error("文件上传错误:{}", file.getOriginalFilename());
				e.printStackTrace();
				AppResponse<Object> resp = AppResponse.fail("");
				resp.setMsg("文件上传失败");
			}
		}
		return AppResponse.ok(fileUrlList);
	}

	@ApiOperation(value = "确认项目法人信息")
	@PostMapping("/confirm/legal")
	public AppResponse<Object> legal() {
		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "项目草稿保存")
	@PostMapping("/tempsave")
	public AppResponse<Object> tempsave() {
		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "项目提交审核申请")
	@PostMapping("/submit")
	public AppResponse<Object> submit() {
		return AppResponse.ok("ok");
	}

}
