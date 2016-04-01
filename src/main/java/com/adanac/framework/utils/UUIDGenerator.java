package com.adanac.framework.utils;

import java.util.Stack;
import java.util.UUID;

/**
 * 生成UUID工具类
 * @author adanac
 * @version 1.0
 */
public class UUIDGenerator {
	private static UUIDGenerator instance = new UUIDGenerator();
	private Stack<String> uuidStack = new Stack<String>();
	private Integer setpSequence = 1000;

	// private static String hexStr = "0123456789ABCDEF";
	final public static char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
			'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z', '_', '$' };

	public static UUIDGenerator getInstance() {
		return instance;
	}

	public synchronized String getDefaultUUID() throws Exception {

		if (uuidStack.empty()) {
			for (Integer i = setpSequence; i > 0; i--) {
				String sid = UUID.randomUUID().toString();
				sid = sid.replace("-", "");
				// sid =
				// sid.substring(0,8)+sid.substring(9,13)+sid.substring(14,18)+sid.substring(19,23)+sid.substring(24);
				uuidStack.push(sid);
			}
		}
		String id = uuidStack.pop();
		return id;
	}

	public synchronized String get64BitUUID() throws Exception {

		String sid = getDefaultUUID();
		sid = change16to64(sid);
		return sid;
	}

	private static String change16to64(String str) {
		StringBuffer shortBuffer = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			String str4 = str.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str4, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}

	public static void main(String[] args) {
		UUIDGenerator uuid = new UUIDGenerator();
		try {
			String str = uuid.get64BitUUID();
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** 
	*  
	* @param hexString 
	* @return 将十六进制转换为字节数组 
	*/
	/*
	 * public static byte[] HexStringToBinary(String hexString){
	 * //hexString的长度对2取整，作为bytes的长度 int len = hexString.length()/2; byte[]
	 * bytes = new byte[len]; byte high = 0;//字节高四位 byte low = 0;//字节低四位
	 * 
	 * for(int i=0;i<len;i++){ //右移四位得到高位 high =
	 * (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4); low =
	 * (byte)hexStr.indexOf(hexString.charAt(2*i+1)); bytes[i] = (byte)
	 * (high|low);//高地位做或运算 } return bytes; }
	 */
	/** 
	 * 把10进制的数字转换成64进制 
	 * @param number 
	 * @param shift 
	 * @return 
	 */
	/*
	 * private static String CompressNumber10to64(long number, int shift) {
	 * char[] buf = new char[64]; int charPos = 64; int radix = 1 << shift; long
	 * mask = radix - 1; do { buf[--charPos] = chars[(int)(number & mask)];
	 * number >>>= shift; } while (number != 0); return new String(buf, charPos,
	 * (64 - charPos)); }
	 */
	/** 
	 * 把64进制的字符串转换成10进制 
	 * @param decompStr 
	 * @return 
	 */
	/*
	 * private static long UnCompressNumber64to10(String decompStr) { long
	 * result=0; for (int i = decompStr.length()-1; i >=0; i--) {
	 * if(i==decompStr.length()-1) {
	 * result+=getCharIndexNum(decompStr.charAt(i)); continue; } for (int j = 0;
	 * j < chars.length; j++) { if(decompStr.charAt(i)==chars[j]) {
	 * result+=((long)j)<<6*(decompStr.length()-1-i); } } } return result; }
	 */

	/*
	 * private static long getCharIndexNum(char ch) { int num=((int)ch);
	 * if(num>=48&&num<=57) { return num-48; } else if(num>=97&&num<=122) {
	 * return num-87; }else if(num>=65&&num<=90) { return num-29; }else
	 * if(num==43) { return 62; } else if (num == 47) { return 63; } return 0; }
	 */
}
