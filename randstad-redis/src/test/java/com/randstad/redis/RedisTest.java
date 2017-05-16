/**
 * Copy right.
 * 
 * Project Name: randstad-system <br>
 * Date: 2017年4月7日 下午9:59:01 <br/>
 * Function: TODO(suzu) ADD FUNCTION. <br>
 * History : 1. [2017-04-07] Create by suzu
 */
package com.randstad.redis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
// import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.randstad.spring.SpringUtil;

/**
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
public class RedisTest {

  @Test
  public void test() {
    ApplicationContext ac = SpringUtil.loadApplicationContext();
    ShardedJedisPool jedisPool = ac.getBean(ShardedJedisPool.class);
    RedisTemplate<Serializable, Object> redisClient =
        (RedisTemplate<Serializable, Object>) ac.getBean("redisTemplate");
    ShardedJedis jedis = jedisPool.getResource();
    jedis.set("a", "abc");
    System.out.println("a : " + jedis.get("a"));
    jedisPool.close();
    // fail("Not yet implemented");
  }

}
