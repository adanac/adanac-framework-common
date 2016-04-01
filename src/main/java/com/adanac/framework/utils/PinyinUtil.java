package com.adanac.framework.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音工具类
 */
public class PinyinUtil {
	/**
	 * format
	 */
	private static volatile HanyuPinyinOutputFormat format;

	/**
	 * PinyinUtil
	 */
	private PinyinUtil() {
	}

	/**
	 * 创建format<br>
	 * 
	 * @return
	 */
	private static HanyuPinyinOutputFormat createFormat() {
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		return outputFormat;
	}

	/**
	 * 获取format<br>
	 * 
	 * @return
	 */
	private static HanyuPinyinOutputFormat getFormat() {
		if (format == null) {
			synchronized (PinyinUtil.class) {
				if (format == null) {
					format = createFormat();
				}
			}
		}
		return format;
	}

	/**
	 * 单个字符转换为拼音<br>
	 * 
	 * @param c
	 * @return
	 */
	public static String toPinyin(char c) {
		String[] pinyin = null;

		try {
			pinyin = PinyinHelper.toHanyuPinyinStringArray(c, getFormat());
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}

		// 如果c不是汉字，toHanyuPinyinStringArray会返回null
		if (pinyin == null) {
			return null;
		}

		// 只取一个发音，如果是多音字，仅取第一个发音
		return pinyin[0];
	}

	/**
	 * 字符串转换为拼音<br>
	 * 
	 * @param str
	 * @return
	 */
	public static String toPinyin(String str) {

		StringBuilder sb = new StringBuilder();
		String tempPinyin = null;

		for (int i = 0; i < str.length(); ++i) {

			tempPinyin = toPinyin(str.charAt(i));
			if (tempPinyin == null) {

				// 如果非汉字，则保持原样
				sb.append(str.charAt(i));

			} else {
				sb.append(tempPinyin);
			}
		}
		return sb.toString();
	}
}
