package com.igeek.ebuy.sso.service;

import com.igeek.ebuy.util.pojo.BuyResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年4月8日 下午2:19:10
 */
public interface TokenService {

	/**
	 * 根据token获取用户信息
	 * @param token
	 * @return  将用户信息包裹在buyResult对象中返回
	 */
	public BuyResult getUserByToken(String token);
}
