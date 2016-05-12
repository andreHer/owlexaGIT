
package com.ametis.cms.util;

import java.security.MessageDigest;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

public class StringUtil {
	public static String getStringValue (Object paramName){
		return getStringValue(paramName,"");
	}
	public static String getPolicyHolderCode (String dependantNumber){
		String code = "";
		
		return code;
	}
	public static String padding (String value,int total){
		String result = value+"";
		
		int totalLength = result.length();
		
		while (totalLength<total){
			result = result + " ";
			totalLength = result.length();
		}
		
		return result;
	}
	public static String paddingCardNumber (String index,int totalDigit){
		String result = index+"";
		
		
		int totalLength = result.length();
		
		while (totalLength<totalDigit){
			result = "0"+result;
			totalLength = result.length();
		}
		return result;
	}
	public static String convertEDCNumber (double value,int totalDigit){
		String result = Converter.getMoney(value);
		
		result = result.replace(",", "");
		//System.out.println("TOTAL DIGIT ("+result+") : " + result.length() + " YANG DIBUTUHKAN : " + totalDigit);
		
		String decimal = "00";
		if (result != null){
			StringTokenizer tokenizer = new StringTokenizer(result,".");
			if (tokenizer.hasMoreTokens()){
				result = tokenizer.nextToken();
			}
			if (tokenizer.hasMoreTokens()){
				decimal = tokenizer.nextToken();
				
				
			}
		}
		
		int totalLength = result.length();
		
		while (totalLength<totalDigit){
			result = "0"+result;
			totalLength = result.length();
		}
		return result;
	}
	public static String convertEDCNumberWithDecimal (double value,int totalDigit){
String result = Converter.getMoney(value);
		
		result = result.replace(",", "");
		//System.out.println("TOTAL DIGIT ("+result+") : " + result.length() + " YANG DIBUTUHKAN : " + totalDigit);
		
		String decimal = "00";
		if (result != null){
			StringTokenizer tokenizer = new StringTokenizer(result,".");
			if (tokenizer.hasMoreTokens()){
				result = tokenizer.nextToken();
			}
			
			if (tokenizer.hasMoreTokens()){
				decimal = tokenizer.nextToken();
				if (decimal != null && decimal.equalsIgnoreCase("")){
					decimal = "00";
				}
			}	
		}
		
		int totalLength = result.length();
		
		while (totalLength<totalDigit){
			result = "0"+result;
			totalLength = result.length();
		}
		result = result+decimal;
		
		return result;
	}
	public static String generateFullMemberCode (String currentCode){
		String code = "";
		
		if (currentCode != null){
			int length = currentCode.length();
			
			if (length == 9){
				String randomCode = RandomStringUtils.randomAlphabetic(1).toUpperCase();
				String groupNo = currentCode.substring(0,3);
				String memberNo = currentCode.substring(3,9);
				
				code = groupNo+randomCode+memberNo;
			}
		}
		return code;
	}
	public static char getMemberLastCode (String memberNumber){
		char result = ' ';
		
		if (memberNumber != null){
			int length = memberNumber.length();
			result = memberNumber.charAt(length-1);
		}
		
		return result;
	}
	public static String hash (String toHash){
		String result = null;
		
		if (toHash != null){
			
			try {
			
				MessageDigest mdAlgorithm = MessageDigest.getInstance("MD5");
				mdAlgorithm.update(toHash.getBytes());
	
				byte[] digest = mdAlgorithm.digest();
				StringBuffer hexString = new StringBuffer();
	
				String plainText = "";
				for (int i = 0; i < digest.length; i++) {
				    plainText = Integer.toHexString(0xFF & digest[i]);
	
				    if (plainText.length() < 2) {
				        plainText = "0" + plainText;
				    }
	
				    hexString.append(plainText);
				}
				result = hexString.toString();
			}
			catch (Exception e){
				
			}
			//out.print(hexString.toString());		
		}
		
		return result;
	}
	public static String getStringValue (Object paramName, String defaultVal){
		String result = "";
//		if (paramName instanceof java.sql.Date){
//			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//			String tmp = df.format(paramName);
//			
//			if (tmp != null){
//				result = tmp;
//			}
//			
//		}
//		else {
			result = paramName == null ? defaultVal : paramName.toString();
		//}
		return result;
	}
	public static Integer getIntegerValue (String paramName, int defaultVal){
		String res = getStringValue(paramName,""+defaultVal);

		if (StringUtils.isNumeric(res)&&!res.trim().equals("")){
			return new Integer(res);
		}else{
			return Integer.valueOf(0);
		}
	}
	public static Long getLongValue (String paramName, int defaultVal){
		String res = getStringValue(paramName,""+defaultVal);

		if (StringUtils.isNumeric(res)&&!res.trim().equals("")){
			return new Long(res);
		}else{
			return new Long(0);
		}
	}

	public static Double getDoubleValue (String paramName, double defaultVal){
		String res = getStringValue(paramName,""+defaultVal);
		
		try {
			return Double.valueOf(res);
		}
		catch (Exception e){
			return new Double(0);
		}
		
//		if (StringUtils.isNumeric(res)&&!res.trim().equals("")){
//			return new Double(res);
//		}else{
//			return new Double(0);
//		}
	}
	public static Float getFloatValue (String paramName, float defaultVal){
		String res = getStringValue(paramName,""+defaultVal);
		if (StringUtils.isNumeric(res)&&!res.trim().equals("")){
			return new Float(res);
		}else{
			return new Float(0);
		}
	}


	public static Timestamp getTimeStampValue(String dateName,String timeName){
		Timestamp result = null;
		String date = getStringValue(dateName, "1970-01-01");
		String time = getStringValue(timeName, "00:00");
		result = TimeUtils.parseTimestamp(date,time);
		return result;
	}
	public static Date getDateValue(String dateName){
		Date result = null;
		String date = getStringValue(dateName, "1970-01-01");
		result = TimeUtils.parseDate(date);
		return result;
	}
	public static Date getDateValue(String dateName, String pattern){
		Date result = null;
		String date = getStringValue(dateName, "1970-01-01");
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			java.util.Date res = df.parse(dateName);
			if (res != null){
				result = new Date(res.getTime());
				System.out.println(result);
			}
		}
		catch (Exception e){
			
		}
		result = TimeUtils.parseDate(date);
		return result;
	}
}