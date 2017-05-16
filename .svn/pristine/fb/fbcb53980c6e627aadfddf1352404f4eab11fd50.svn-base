package com.randstad.shiro.cache;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

/**
 * 
 * 
 * shiro cache manager 接口Redis 实现
 * 
 * <p>
 * 
 * @author walker
 * 
 */
public class RedisCacheManager extends AbstractCacheManager {

  /**
   * 
   */
  @Override
  protected Cache<Object, Object> createCache(String name) throws CacheException {
    return new RedisShiroCache<Object, Object>(name);
  }

}
