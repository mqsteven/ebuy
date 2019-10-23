package com.igeek.ebuy.util.jedis;

import redis.clients.jedis.JedisCluster;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月27日 下午2:29:34
 */
public class JedisClusterClient implements JedisClient {
	
	private JedisCluster cluster;
	
	public JedisCluster getCluster() {
		return cluster;
	}

	public void setCluster(JedisCluster cluster) {
		this.cluster = cluster;
	}

	@Override
	public String set(String key, String value) {
		String result = cluster.set(key,value);
		//cluster.close();
		return result;
	}

	@Override
	public String get(String key) {
		String result = cluster.get(key);
		//cluster.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#exists(java.lang.String)
	 */
	@Override
	public Boolean exists(String key) {
		Boolean result = cluster.exists(key);
		//cluster.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#expire(java.lang.String, int)
	 */
	@Override
	public Long expire(String key, int seconds) {
		Long result = cluster.expire(key,seconds);
		//cluster.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#ttl(java.lang.String)
	 */
	@Override
	public Long ttl(String key) {
		Long result = cluster.ttl(key);
		//cluster.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#incr(java.lang.String)
	 */
	@Override
	public Long incr(String key) {
		Long result = cluster.incr(key);
		//cluster.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#hset(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Long hset(String key, String field, String value) {
		Long result = cluster.hset(key,field,value);
		//cluster.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#hget(java.lang.String, java.lang.String)
	 */
	@Override
	public String hget(String key, String field) {
		String result = cluster.hget(key,field);
		//cluster.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#hdel(java.lang.String, java.lang.String[])
	 */
	@Override
	public Long hdel(String key, String... field) {
		Long result = cluster.hdel(key,field);
		//cluster.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.igeek.ebuy.util.jedis.JedisClient#del(java.lang.String)
	 */
	@Override
	public Long del(String key) {
		// TODO Auto-generated method stub
		return cluster.del(key);
	}

}
