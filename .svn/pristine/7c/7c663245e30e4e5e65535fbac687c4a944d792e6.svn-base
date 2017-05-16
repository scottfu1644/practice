package com.randstad.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Set;

import com.randstad.common.utils.SerializeUtil;
import com.randstad.redis.RedisClientTemplate;
import com.randstad.spring.SpringContextUtil;

/**
 * 
 * 缓存获取Manager
 * 
 * <p>
 * *******
 * <p>
 * 
 * @author walker
 * @param <K>
 * @param <V>
 * 
 */
public class RedisShiroCache<K, V> implements Cache<K, V> {
  private static final Logger logger = LoggerFactory.getLogger(RedisShiroCache.class);
  /**
   * 为了不和其他的缓存混淆，采用追加前缀方式以作区分
   */
  private static final String REDIS_SHIRO_CACHE = "shiro-cache:";
  /**
   * Redis 分片(分区)，也可以在配置文件中配置
   */
  // private static final int DB_INDEX = 1;

  // static final RedisClientTemplate redisClient = SpringContextUtil.getBean("redisClientTemplate",
  // RedisClientTemplate.class);

  RedisClientTemplate redisClient = null;

  private String name;

  public RedisShiroCache(String name) {
    this.name = name;
  }

  /**
   * 自定义relm中的授权/认证的类名加上授权/认证英文名字
   */
  public String getName() {
    if (name == null) {
      return "";
    }
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @SuppressWarnings("unchecked")
  public V get(K key) throws CacheException {
    byte[] byteKey = SerializeUtil.serialize(buildCacheKey(key));
    byte[] byteValue = new byte[0];
    try {
      byteValue = redisClient.get(byteKey);
    } catch (Exception e) {
      logger.error("get value by cache throw exception", e);
      e.printStackTrace();
    }
    return (V) SerializeUtil.deserialize(byteValue);
  }

  @Override
  public V put(K key, V value) throws CacheException {
    V previos = get(key);
    try {
      byte[] skey = SerializeUtil.serialize(buildCacheKey(key));
      byte[] svalue = SerializeUtil.serialize(value);
      redisClient.set(skey, svalue);
      redisClient.expire(skey, -1);
    } catch (Exception e) {
      logger.error("put cache throw exception", e);
      e.printStackTrace();
    }
    return previos;
  }

  @Override
  public V remove(K key) throws CacheException {
    V previos = get(key);
    try {
      redisClient.del(SerializeUtil.serialize(buildCacheKey(key)));
    } catch (Exception e) {
      logger.error("remove cache  throw exception", e);
      e.printStackTrace();
    }
    return previos;
  }

  @Override
  public void clear() throws CacheException {}

  @Override
  public int size() {
    if (keys() == null) {
      return 0;
    }
    return keys().size();
  }

  public Set<K> keys() {
    // TODO
    return null;
  }

  public Collection<V> values() {
    // TODO
    return null;
  }

  private String buildCacheKey(Object key) {
    return REDIS_SHIRO_CACHE + getName() + ":" + key;
  }

}
