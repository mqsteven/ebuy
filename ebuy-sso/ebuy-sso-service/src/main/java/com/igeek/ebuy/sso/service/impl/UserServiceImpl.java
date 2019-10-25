package com.igeek.ebuy.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.igeek.ebuy.manager.mapper.TbUserMapper;
import com.igeek.ebuy.pojo.TbUser;
import com.igeek.ebuy.pojo.TbUserExample;
import com.igeek.ebuy.pojo.TbUserExample.Criteria;
import com.igeek.ebuy.sso.service.UserService;
import com.igeek.ebuy.util.jedis.JedisClient;
import com.igeek.ebuy.util.json.JsonUtils;
import com.igeek.ebuy.util.pojo.BuyResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 *              2019年4月8日 上午9:47:29
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private JedisClient client;

	@Autowired
	private TbUserMapper userMapper;

	public BuyResult checkData(String data, int type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		// 判断，设置什么条件
		if (type == 1) {
			// 校验用户名
			criteria.andUsernameEqualTo(data);
		} else {
			// 校验手机号码
			criteria.andPhoneEqualTo(data);
		}
		List<TbUser> users = userMapper.selectByExample(example);
		BuyResult result = BuyResult.ok();
		if (users != null && users.size() > 0) {// 存在
			result.setData(false);
		} else {
			result.setData(true);
		}
		return result;
	}

	@Override
	public BuyResult register(TbUser user) {
		// 再次校验数据
		if (StringUtils.isBlank(user.getUsername())) {
			return BuyResult.build(400, "用户名为空，注册失败!");
		}
		if (!(Boolean) checkData(user.getUsername(), 1).getData()) {
			return BuyResult.build(400, "用户重复，注册失败!");
		}
		if (StringUtils.isBlank(user.getPassword())) {
			return BuyResult.build(400, "密码为空，注册失败!");
		}
		if (StringUtils.isBlank(user.getPhone())) {
			return BuyResult.build(400, "手机号码为空，注册失败!");
		}
		if (!(Boolean) checkData(user.getPhone(), 2).getData()) {
			return BuyResult.build(400, "手机号重复，注册失败!");
		}
		// 补全数据
		user.setCreated(new Date());
		user.setUpdated(new Date());
		//密码需要MD5加密
		String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(password);
		//保存用户
		int result = userMapper.insert(user);
		if(result==1)
			return BuyResult.ok();
		else
			return BuyResult.build(400, "注册失败！不明原因!");
	}

	@Override
	public BuyResult login(String loginName, String password) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(loginName);
		List<TbUser> list = userMapper.selectByExample(example );
		if(list==null || list.size()==0) {
			criteria.andPhoneEqualTo(loginName);
			list = userMapper.selectByExample(example );
		}
		if(list==null || list.size()==0) {
			return BuyResult.build(400, "用户名或密码错误！");
		}
		TbUser user = list.get(0);
		//判断密码
		if(user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
			//登录用户信息写入session
			//使用redis模拟session
			//生成token  使用UUID生成   "ABC90-8AC08-AC00-A9C"
			String token = UUID.randomUUID().toString().replace("-", "");
			token = "SESSION:"+token;
			user.setPassword(null);
			//存入redis
			client.set(token, JsonUtils.objectToJson(user));
			//设置存活时间
			client.expire(token, 60*30);
			return BuyResult.build(200, token);
		}
		return BuyResult.build(400, "用户名或密码错误！");
	}

}
