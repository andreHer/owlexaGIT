package com.ametis.cms.util;

public class StringTest {

	public static void main(String[] args){
		String total = StringUtil.convertEDCNumber(10000, 9);
		System.out.println("dari  : " + 10000 + " menjadi : " + total);
	}
}
