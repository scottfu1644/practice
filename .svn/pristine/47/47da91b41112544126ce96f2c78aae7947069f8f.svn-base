package com.randstad.activiti.service;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.randstad.spring.SpringContextUtil;
import com.randstad.spring.SpringUtil;

/**
 * 
 * Function: 流程图服务测试类. <br>
 * 
 * @author suzu
 */
public class ProcessImageServiceTest {

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
  public void testGenerateProcessImageByProcessInstanceId() {
    ProcessImageService processImageService = SpringContextUtil.getBean(ProcessImageService.class);
    InputStream in = processImageService.generateProcessImage("2512");
    byte[] data = new byte[1024000];
    try {
      FileOutputStream out = new FileOutputStream("/2512.png");
      in.read(data);
      out.write(data);
      out.flush();
      out.close();
      in.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
