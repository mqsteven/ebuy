package com.igeek.ebuy.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igeek.ebuy.sso.service.TokenService;
import com.igeek.ebuy.util.json.JsonUtils;
import com.igeek.ebuy.util.pojo.BuyResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年4月8日 下午2:40:09
 */
@Controller
public class TokenController {

	@Autowired
	private TokenService tokenService;
	
	@RequestMapping("/user/token/{token}")
	@ResponseBody
	public String getUserByToken(@PathVariable String token,String callback) {
		BuyResult result = tokenService.getUserByToken(token);
		//拼接一个函数调用的js字符串
		String res = callback+"("+JsonUtils.objectToJson(result)+")";
		System.out.println(res);
		return res;
	};
}
