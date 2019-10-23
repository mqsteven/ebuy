package com.igeek.ebuy.util.jedis;
/**
 * 
* @ClassName: JedisClient  
* @Description: JedisClient接口
* @date 2017年10月18日 上午11:32:25    
* Company www.igeekhome.com
*
 */
public interface JedisClient {

	String set(String key, String value);
	String get(String key);
	Boolean exists(String key);
	Long expire(String key, int seconds);
	Long ttl(String key);
	Long incr(String key);
	Long hset(String key, String field, String value);
	String hget(String key, String field);
	Long hdel(String key, String... field);
	Long del(String key);
}
