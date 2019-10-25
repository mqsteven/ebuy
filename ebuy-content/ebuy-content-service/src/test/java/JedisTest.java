import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月27日 下午2:19:36
 */
public class JedisTest {
	
	//@Test
	public void testSpringJedisCluster() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisCluster cluster = context.getBean(JedisCluster.class);
		cluster.set("spring","spring");
		cluster.close();
	}
	
	
	
	//@Test
	public void testJedisCluster() {
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.128.152", 7001));
		nodes.add(new HostAndPort("192.168.128.152", 7002));
		nodes.add(new HostAndPort("192.168.128.152", 7003));
		nodes.add(new HostAndPort("192.168.128.152", 7004));
		nodes.add(new HostAndPort("192.168.128.152", 7005));
		nodes.add(new HostAndPort("192.168.128.152", 7006));
		JedisCluster cluster = new JedisCluster(nodes );
		
		cluster.set("clusterTest", "xixi");
		cluster.close();
	}
	
	
	
	//@Test
	public void testJedisPool1() {
		JedisPool jedisPool = new JedisPool("192.168.128.152",6379);
		Jedis jedis = jedisPool.getResource();
		String v = jedis.get("jedisTest1");
		System.out.println(v);
		jedis.close();
	}
	
	
	//@Test
	public void testJedisPool() {
		JedisPool jedisPool = new JedisPool("192.168.128.152",6379);
		Jedis jedis = jedisPool.getResource();
		jedis.set("jedisTest1", "heihei");
		jedis.close();
	}
	
	//@Test
	public void testJedis1() {
		Jedis jedis = new Jedis("192.168.128.152",6379);
		String v = jedis.get("jedisTest");
		System.out.println(v);
		jedis.close();
	}
	
	
	//@Test
	public void testJedis() {
		Jedis jedis = new Jedis("192.168.128.152",6379);
		jedis.set("jedisTest", "hehe");
		jedis.close();
	}
}
