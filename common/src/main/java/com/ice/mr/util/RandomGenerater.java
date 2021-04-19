package com.ice.mr.util;

import java.util.Random;

public class RandomGenerater {


	public static String getsaltLongNumber(String  prdfix) {
		return prdfix+String.valueOf(System.currentTimeMillis())+generateValidationCodeRandom();
	}

	public static  String generateValidationCodeRandom(){
	    StringBuilder randomCode=new StringBuilder();
	    Random random=new Random();
		int length=4;
	    String base="0123456789";
	    int size=base.length();
		for (int i = 0; i < length; i++) {
			int start=random.nextInt(size);
			String strRand=base.substring(start,start+1);
		    randomCode.append(strRand);
		}
		return randomCode.toString();
	}

	public static String randomUserName(){
		Integer [] items = new Integer[]{2,3,4,5};
		int a= (int) Math.floor(Math.random()*items.length);
		Integer randomNumber = items[a];
		StringBuilder sb = new StringBuilder();
		char[] englishCodeArray = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
				'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		char[] numCodeArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		Random random = new Random();
		sb.append("TR");
		for (int i = 0; i <randomNumber; i++){
			char num = englishCodeArray[random.nextInt(englishCodeArray.length)];
			sb.append(num);
		}
		sb.append("_");
		for (int i = 0; i <randomNumber; i++){
			char num = numCodeArray[random.nextInt(numCodeArray.length)];
			sb.append(num);
		}
		return sb.toString();
	}
}
