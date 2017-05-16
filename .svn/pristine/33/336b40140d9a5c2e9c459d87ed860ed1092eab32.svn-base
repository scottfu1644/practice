package com.randstad.activiti.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.randstad.spring.SpringContextUtil;
import com.randstad.spring.SpringUtil;

/**
 * 
 * Function: 流程服务测试类. <br>
 * 
 * @author suzu
 */
public class ProcessServiceTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  @Before
  public void setUp() throws Exception {
    SpringUtil.loadApplicationContext();
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testGetTaskActions() {
    ProcessService processService = SpringContextUtil.getBean(ProcessService.class);
    System.out.println(processService.getTaskActions("2517"));
  }

}
