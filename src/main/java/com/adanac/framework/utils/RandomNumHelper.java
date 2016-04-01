package com.adanac.framework.utils;

import java.util.Random;

public class RandomNumHelper {
	/**  
	 * 产生一个随机的字符串  
	 *   
	 * @param 字符串长度  
	 * @return  
	 */
	public static String getRandomString(int length) {
		// Random random=new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			// int number=random.nextInt(2);
			// long result=0;
			// switch(number){
			// case 0://
			// result = Math.round(Math.random()*25+97);
			// sb.append(String.valueOf((char)result));
			// break;
			// case 1:
			// sb.append(String.valueOf(new Random().nextInt(10)));
			// break;
			// }
			sb.append(String.valueOf(new Random().nextInt(10)));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(getRandomString(6));
	}
}
