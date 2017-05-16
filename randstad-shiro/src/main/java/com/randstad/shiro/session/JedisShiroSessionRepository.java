package com.randstad.shiro.session;

import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.randstad.common.utils.SerializeUtil;
import com.randstad.redis.RedisClientTemplate;

// import com.randstad.spring.SpringContextUtil;

/**
 * Session 管理.
 * 
 * @author Walker
 * 
 */
@Repository
@SuppressWarnings("unchecked")
public class JedisShiroSessionRepository implements ShiroSessionRepository {
  private static final Logger logger = LoggerFactory.getLogger(JedisShiroSessionRepository.class);
  public static final String REDIS_SHIRO_SESSION = "randstad-shiro-session:";

  // 这里有个小BUG，因为Redis使用序列化后，Key反序列化回来发现前面有一段乱码，解决的办法是存储缓存不序列化
  public static final String REDIS_SHIRO_ALL = "*randstad-shiro-session:*";

  private static final int SESSION_VAL_TIME_SPAN = 18000;
  //
  // private static final int DB_INDEX = 1;

  @Autowired
  private RedisClientTemplate redisClient;

  // SpringContextUtil.getBean("redisClientTemplate", RedisClientTemplate.class);

  public void saveSession(Session session) {
    logger.info("saveSession({})", session);
    if (session == null || session.getId() == null) {
      throw new NullPointerException("session is empty");
    }
    try {
      byte[] key = SerializeUtil.serialize(buildRedisSessionKey(session.getId()));

      // 不存在才添加。
      // if (null == session.getAttribute(CustomSessionManager.SESSION_STATUS)) {
      // // Session 踢出自存存储。
      // // SessionStatus sessionStatus = new SessionStatus();
      // // session.setAttribute(CustomSessionManager.SESSION_STATUS, sessionStatus);
      // }

      byte[] value = SerializeUtil.serialize(session);
      long sessionTimeOut = session.getTimeout() / 1000;
      Long expireTime = sessionTimeOut + SESSION_VAL_TIME_SPAN + (5 * 60);
      redisClient.set(key, value);
      redisClient.expire(key, expireTime.intValue());
    } catch (Exception e) {
      logger.error(String.format("save session error，id:[%s]", session.getId()), e);
      e.printStackTrace();
    }
  }

  public void deleteSession(Serializable id) {
    logger.info("deleteSession({})", id);
    if (id == null) {
      throw new NullPointerException("session id is empty");
    }
    try {
      redisClient.del(SerializeUtil.serialize(buildRedisSessionKey(id)));
    } catch (Exception e) {
      logger.error(String.format("删除session出现异常，id:[%s]", id), e);
      e.printStackTrace();
    }
  }

  public Session getSession(Serializable id) {
    logger.info("getSession({})", id);
    if (id == null) {
      throw new NullPointerException("session id is empty");
    }
    Session session = null;
    try {
      byte[] value = redisClient.get(SerializeUtil.serialize(buildRedisSessionKey(id)));
      session = SerializeUtil.deserialize(value, Session.class);
    } catch (Exception e) {
      logger.error(String.format("获取session异常，id:[%s]", id), e);
    }
    return session;
  }

  public Collection<Session> getAllSessions() {
    logger.info("getAllSessions()");
    Collection<Session> sessions = new HashSet<Session>();
    try {
      Set<byte[]> byteKeys =
          redisClient.hkeys((JedisShiroSessionRepository.REDIS_SHIRO_ALL).getBytes("UTF-8"));
      if (byteKeys != null && byteKeys.size() > 0) {
        for (byte[] bs : byteKeys) {
          Session obj = SerializeUtil.deserialize(redisClient.get(bs), Session.class);
          if (obj instanceof Session) {
            sessions.add(obj);
          }
        }
      }
    } catch (Exception e) {
      logger.error("获取全部session异常", e);
      e.printStackTrace();
    }

    return sessions;
  }

  private String buildRedisSessionKey(Serializable sessionId) {
    return REDIS_SHIRO_SESSION + sessionId;
  }
}
