package com.auguigu.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.OS;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TestUploadAndDownController {
	
	@RequestMapping(value = "/down")
	public ResponseEntity<byte []> down(HttpSession session) throws IOException {
		String fileName = "1.jpg";
		
		// 获取下载文件的路径
		String path = session.getServletContext().getRealPath("img");
		String finalPath = path + File.separator + fileName;
		
		// 读取文件
		InputStream inputStream = new FileInputStream(finalPath);
		
		// available 获取输入流读取的文件的最大字节
		byte[] body = new byte[inputStream.available()]; // 创建一个字节数组
		inputStream.read(body); // 将输入流放入字节数组中
		
		// 设置响应头
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Disposition", "attachment;filename=" + fileName);
		
		// 设置响应状态
		HttpStatus statusCode = HttpStatus.OK;
		
		// 返回文件流信息
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte []>(body, httpHeaders, statusCode);
		
		return responseEntity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/upload1", method = {RequestMethod.POST})
	// SpringMVC 将客户端接口到的文件对象转换为 MultipartFile 对象
	public String upload1(String desc, MultipartFile uploadFile, HttpSession session) throws IOException{ 
		// 获取上传的文件的名称
		String originalFilename = uploadFile.getOriginalFilename();
		
		// 设置文件上传路径
		String path = session.getServletContext().getRealPath("photo") + File.separator + originalFilename;
		
		// 获取输入流
		InputStream inputStream = uploadFile.getInputStream();
		
		// 获取输出流
		File file = new File(path);
		OutputStream outputStream = new FileOutputStream(file);
		
		// 边读边写文件
		int i =0;
		byte[] b = new byte[1024];
		while ((i = inputStream.read(b))!=-1) {
			outputStream.write(b, 0, i);
		}
		
		// 关闭数据流
		outputStream.close();
		inputStream.close();
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/upload2", method = {RequestMethod.POST})
	// SpringMVC 将客户端接口到的文件对象转换为 MultipartFile 对象
	public String upload2(String desc, MultipartFile uploadFile, HttpSession session) throws IOException{ 
		// 获取上传的文件的名称
		String originalFilename = uploadFile.getOriginalFilename();
		
		// 重命令
		String finalFileName = UUID.randomUUID().toString().replace("-", "") + originalFilename.substring(originalFilename.lastIndexOf("."));
		
		// 设置文件上传路径
		String path = session.getServletContext().getRealPath("photo") + File.separator + finalFileName;
		
		// 使用 uploadFile 的上传
		File file = new File(path);
		uploadFile.transferTo(file);
		return "success";
	}
}
