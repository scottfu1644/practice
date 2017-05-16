package com.randstad.common.codec;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * Function: md5. <br>
 * 
 * @author suzu
 */
public class Md5 {
  /**
   * 默认的密码字符串组合，apache校验下载的文件的正确性用的就是默认的这个组合
   */
  protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
      'c', 'd', 'e', 'f'};

  public static void main(String[] args) throws IOException {

    System.out.println(Md5.getMD5String("admin"));

    long begin = System.currentTimeMillis();

    File big = new File("E:/oracle_10201_client_win32.zip");

    String md5 = getFileMD5String(big);

    long end = System.currentTimeMillis();
    System.out.println("md5:" + md5 + " time:" + ((end - begin) / 1000) + "s");
    md5 = getMD5(big);
    end = System.currentTimeMillis();
    System.out.println("md5:" + md5 + " time:" + ((end - begin) / 1000) + "s");
  }

  /**
   * 对文件全文生成MD5摘要
   * 
   * @param file 要加密的文件
   * @return MD5摘要码
   */
  public static String getFileMD5String(File file) throws IOException {
    FileInputStream in = null;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      long s = System.currentTimeMillis();
      System.out.println("全文生成MD5摘要开始：" + s / 1000);
      in = new FileInputStream(file);
      FileChannel ch = in.getChannel();
      MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length()); // 文件太大会造成内存溢出
      md.update(byteBuffer);

      // // 创建缓冲区
      // ByteBuffer buffer = ByteBuffer.allocate(4096);
      // while (true) {
      // // clear方法重设缓冲区，使它可以接受读入的数据
      // buffer.clear();
      // // 从输入通道中将数据读到缓冲区
      // int r = ch.read(buffer);
      // // read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1
      // if (r == -1) {
      // break;
      // }
      // md.update(buffer);// 此方法处理后生成的md5和md.update(buffer.array(), 0,
      // r);方法处理的结果不同，但好像后一种方法是正确的。需要确认一下原因
      // }

      System.out.println("全文生成MD5摘要耗时：" + (System.currentTimeMillis() - s) / 1000);
      return byteToHexString(md.digest());
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    } finally {
      try {
        in.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * 对文件全文生成MD5摘要
   * 
   * @param file 要加密的文件
   * @return MD5摘要码
   */
  public static String getMD5(File file) {
    FileInputStream fis = null;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      fis = new FileInputStream(file);
      byte[] buffer = new byte[2048];
      int length = -1;
      long s = System.currentTimeMillis();
      System.out.println("全文生成MD5摘要开始：" + s / 1000);
      while ((length = fis.read(buffer)) != -1) {
        md.update(buffer, 0, length);
      }
      byte[] b = md.digest();
      System.out.println("全文生成MD5摘要耗时：" + (System.currentTimeMillis() - s) / 1000);
      return byteToHexString(b);
      // 16位加密
      // return buf.toString().substring(8, 24);
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    } finally {
      try {
        fis.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  public static String getMD5String(String s) {
    try {
      return getMD5String(s.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String getMD5String(byte[] bytes) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(bytes);
      return byteToHexString(md.digest());
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }

  }

  /**
   * 把byte[]数组转换成十六进制字符串表示形式
   * 
   * @param tmp 要转换的byte[]
   * @return 十六进制字符串表示形式
   */
  private static String byteToHexString(byte[] tmp) {
    String s;
    // 用字节表示就是 16 个字节
    char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
    // 所以表示成 16 进制需要 32 个字符
    int k = 0; // 表示转换结果中对应的字符位置
    for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
      // 转换成 16 进制字符的转换
      byte byte0 = tmp[i]; // 取第 i 个字节
      str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
      // >>> 为逻辑右移，将符号位一起右移
      str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
    }
    s = new String(str); // 换后的结果转换为字符串
    return s;
  }

  public static boolean checkPassword(String password, String md5PwdStr) {
    String s = getMD5String(password);
    return s.equals(md5PwdStr);
  }
}
