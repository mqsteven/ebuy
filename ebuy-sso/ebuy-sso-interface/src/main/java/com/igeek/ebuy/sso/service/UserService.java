package com.igeek.ebuy.sso.service;

import com.igeek.ebuy.pojo.TbUser;
import com.igeek.ebuy.util.pojo.BuyResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年4月8日 上午9:46:54
 */
public interface UserService {
	/**
	 * 
	 * @param data
	 * @param type
	 * @return
	 */
	public BuyResult checkData(String data,int type);
	/**
	 * 
	 * @param user
	 * @return
	 */
	public BuyResult register(TbUser user);
	/**
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public BuyResult login(String loginName,String password);
}
