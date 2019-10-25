package com.igeek.ebuy.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年4月8日 上午9:26:07
 */
@Controller
public class PageController {

	@RequestMapping("/page/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}
