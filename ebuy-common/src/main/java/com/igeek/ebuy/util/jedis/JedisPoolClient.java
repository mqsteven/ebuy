package com.igeek.ebuy.util.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月27日 下午2:29:19
 */
public class JedisPoolClient implements JedisClient {
	
	private JedisPool jedisPool;
	
	public JedisPool getJedisPool() {
		return jedisPool;
	}
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#get(java.lang.String)
	 */
	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public Boolean exists(String key) {
		Jedis jedis = jedisPool.getResource();
		Boolean result = jedis.exists(key);
		jedis.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#expire(java.lang.String, int)
	 */
	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key,seconds);
		jedis.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#ttl(java.lang.String)
	 */
	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#incr(java.lang.String)
	 */
	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#hset(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Long hset(String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(key,field,value);
		jedis.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#hget(java.lang.String, java.lang.String)
	 */
	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.hget(key,field);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String key, String... field) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(key,field);
		jedis.close();
		return result;
	}
	@Override
	public Long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

}
