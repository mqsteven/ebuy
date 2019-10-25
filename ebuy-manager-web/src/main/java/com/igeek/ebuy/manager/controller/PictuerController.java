package com.igeek.ebuy.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.igeek.ebuy.util.fdfs.FastDFSClient;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月25日 上午10:44:58
 */
@Controller
public class PictuerController {
	
	@Value("${FASTDFS_SERVER}")
	private String FASTDFS_SERVER;
	

	@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE+";charSet=utf-8")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) throws JsonProcessingException {
		Map map = new HashMap();
		
		FastDFSClient client = new FastDFSClient("classpath:conf/client.conf");
		//区文件后缀
		String fileName = uploadFile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		try {
			String path = client.uploadFile(uploadFile.getBytes(), ext);
			map.put("error", 0);
			map.put("url", FASTDFS_SERVER+path);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", 1);
			map.put("message", "出错啦!");
		}
		return new ObjectMapper().writeValueAsString(map);
	}
}
