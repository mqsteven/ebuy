package com.igeek.ebuy.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igeek.ebuy.pojo.TbUser;
import com.igeek.ebuy.sso.service.TokenService;
import com.igeek.ebuy.util.jedis.JedisClient;
import com.igeek.ebuy.util.json.JsonUtils;
import com.igeek.ebuy.util.pojo.BuyResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年4月8日 下午2:20:15
 */
@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private JedisClient client;
	
	@Override
	public BuyResult getUserByToken(String token) {
		if(!client.exists(token)) {
			return BuyResult.build(404, "用户登录过期");
		}
		String json = client.get(token);
		if(StringUtils.isNoneBlank(json)) {
			TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
			//验证通过
			client.expire(token, 60*30);
			return new BuyResult(200, user);
		}
		return BuyResult.build(404, "用户没登录");
	}

}
