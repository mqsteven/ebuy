package com.igeek.ebuy.sso.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igeek.ebuy.pojo.TbUser;
import com.igeek.ebuy.sso.service.UserService;
import com.igeek.ebuy.util.cookie.CookieUtils;
import com.igeek.ebuy.util.pojo.BuyResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年4月8日 上午9:44:30
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/login")
	@ResponseBody
	public BuyResult login(String username,String password,HttpServletRequest request,HttpServletResponse response) {
		BuyResult result = userService.login(username, password);
		//写cookie
		String token = result.getMsg();//取出token
		if(StringUtils.isNoneBlank(token)) {
//			Cookie cookie = new Cookie("EBUY-TOKEN", token);
//			cookie.setDomain(".ebuy.com");//修改域   不设置，默认就是当前的域名
//			//修改路径
//			cookie.setPath("/");   //默认是当前路径
//			response.addCookie(cookie);
			//使用工具类写cookie
			CookieUtils.setCookie(request, response, "EBUY-TOKEN", token);
		}
		return result;
	}
	
	@RequestMapping("/user/register")
	@ResponseBody
	public BuyResult register(TbUser user) {
		return userService.register(user);
	}

	@RequestMapping("/user/check/{data}/{type}")
	@ResponseBody
	public BuyResult checkData(@PathVariable String data,@PathVariable int type) {
		return userService.checkData(data, type);
	}
}
