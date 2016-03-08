package com.adanac.framework.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 功能描述：把指定路径中的图片转成相应的二进制码
	 */
	public static String getBase64file(String picUrl) {
		File sf = new File(picUrl);
		try {
			FileInputStream in = new FileInputStream(sf);

			BufferedInputStream bis = new BufferedInputStream(in);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			int c = bis.read();// 读取bis流中的下一个字节

			while (c != -1) {

				baos.write(c);

				c = bis.read();

			}
			bis.close();
			byte retArr[] = baos.toByteArray();
			String str = new String(Base64.encode(retArr, Base64.DEFAULT));
			return str;

		} catch (FileNotFoundException e) {
			logger.error("getBase64file 未找到" + picUrl + "中文件");
		} catch (IOException e) {
			logger.error("getBase64file 输入输出流出错:" + e.getMessage());
		}
		return null;
	}
}
