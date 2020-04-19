package com.itguigu.zcw.project.components;

import java.io.InputStream;
import java.util.UUID;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@Data
public class AliOssTemplate {
	// 这里不使用 @Value 注解读取 properties 中的配置，而是使用配置的方式，详见 com.itguigu.zcw.config 下 AliOssConfig.java
	// 注意加上 @Data 注解，否则值不能注入进来
	// 上传地址
	private String endpoint;
	// accessKeyId
	private String accessKeyId;
	// accessKeySecret
	private String accessKeySecret;
	// bucketName
	private String bucketName;
	// 上传存储文件夹
	private String uploadFolderName;
	
	
	public String upload(InputStream inputStream, String uploadFileName) {
		log.info("endpoint:{}", endpoint);
		log.info("accessKeyId:{}", accessKeyId);
		log.info("accessKeySecret:{}", accessKeySecret);
		log.info("bucketName:{}", bucketName);
		log.info("uploadFolderName:{}", uploadFolderName);
		log.info("uploadFileName:{}", uploadFileName);
		
		String fileName;
		
		try {
			// 创建OSSClient实例。
			OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
			// 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
			String fileSuffix = UUID.randomUUID().toString().replace("-", "");
			fileName = fileSuffix + "_" + uploadFileName;
			ossClient.putObject(bucketName, uploadFolderName + "/" + fileName, inputStream);
			// 关闭OSSClient。
			ossClient.shutdown();
		} catch (Exception e) {
			return "";
		}
		// 返回上传后文件完整路径
		// https://osssssssss.oss-cn-shanghai.aliyuncs.com/zcw/2020-04-11_17-28-06.png
		return "https://" + bucketName + ".oss-cn-shanghai.aliyuncs.com/" + uploadFolderName + "/" + fileName;
	}
}
