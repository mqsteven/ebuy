package com.igeek.ebuy.portal.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.igeek.ebuy.util.exceptions.MySqlException;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月30日 下午2:00:06
 */
public class GlobalExceptionReslver implements HandlerExceptionResolver {
	
	Logger logger = Logger.getLogger(GlobalExceptionReslver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//判断异常类型
		if(ex instanceof MySqlException) {
			
		}
		//处理异常   ex中包裹着异常信息    request和response可以转发或者重定向
		//输出：通过log4j输出
		logger.debug(ex.getMessage());
		//发邮件，发短信，。。。各种通知。各种记录。
		
		//响应客户端。
		//您的网络有问题，请稍后再试。
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("e", ex.getMessage());
		return modelAndView; 
	}

}
