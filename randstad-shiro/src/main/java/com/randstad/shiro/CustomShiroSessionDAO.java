package com.randstad.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;

import com.randstad.shiro.session.ShiroSessionRepository;

/**
 * Session 操作.
 * 
 * <p>
 * 
 * @author walker
 * 
 */
public class CustomShiroSessionDAO extends AbstractSessionDAO {
  private static final Logger logger = LoggerFactory.getLogger(CustomShiroSessionDAO.class);

  @Autowired
  private ShiroSessionRepository shiroSessionRepository;

  public ShiroSessionRepository getShiroSessionRepository() {
    return shiroSessionRepository;
  }

  public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
    this.shiroSessionRepository = shiroSessionRepository;
  }

  public void update(Session session) throws UnknownSessionException {
    getShiroSessionRepository().saveSession(session);
  }

  @Override
  public void delete(Session session) {
    if (session == null) {
      logger.error("Session 不能为null");
      return;
    }
    Serializable id = session.getId();
    if (id != null) {
      getShiroSessionRepository().deleteSession(id);
    }
  }

  public Collection<Session> getActiveSessions() {
    return getShiroSessionRepository().getAllSessions();
  }

  protected Serializable doCreate(Session session) {
    Serializable sessionId = this.generateSessionId(session);
    this.assignSessionId(session, sessionId);
    getShiroSessionRepository().saveSession(session);
    return sessionId;
  }

  protected Session doReadSession(Serializable sessionId) {
    return getShiroSessionRepository().getSession(sessionId);
  }
}
