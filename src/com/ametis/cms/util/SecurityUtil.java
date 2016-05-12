/*
 * Name        : Security Util
 * Create By   : A.J.U
 * Create Date : 20150416
 * Description : use for encrypt /decrypt something :D
 * Usage       : handle with care :D
 */
package com.ametis.cms.util;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtil {
	
	private static String getKeyPerToday(){
		java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd");
	    //get current date time with Date()
	    java.util.Date dtToday = new java.util.Date();
	    //System.out.println(dateFormat.format(dtToday));
	    String strKey = dateFormat.format(dtToday).replace("/", "");
	    //Ga bisa dtambahin jam, karena salt 8 bit key only zzzz...
	    //strKey += "235959";
	    //System.out.println("Today Key : " + strKey);
		
		return strKey;
	}
	
	private static int getIterationPerToday(){
		String todayKey = getKeyPerToday();
		int totalIteration=0;
		
		for(int idx=0;idx<todayKey.length();idx++){
			if((idx+1)<todayKey.length()){
				totalIteration += Integer.parseInt(todayKey.substring(idx, idx+1));
			}
			else{
				totalIteration += Integer.parseInt(todayKey.substring(idx));
			}
		}
		
		//System.out.println("Today Key : " + todayKey+"\nToday Iteration : " +totalIteration);
		
		return totalIteration;
	}
	
	public static String saltEncrypt(String plainText){
		String res = null;
		
		byte[] SALT = getKeyPerToday().getBytes();
		int ITERATION_COUNT	= getIterationPerToday();
		
		if (plainText == null || plainText.length()<=0)
		{
			//throw new IllegalArgumentException();
			res = "";
		}
		else{
			try
			{
	 
				KeySpec keySpec = new PBEKeySpec(null, SALT, ITERATION_COUNT);
				AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATION_COUNT);
	 
				SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
	 
				Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
				ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
	 
				byte[] enc = ecipher.doFinal(plainText.getBytes());
	 
				res = new String(Base64.encodeBase64(enc));
				// escapes for url
				res = res.replace('+', '-').replace('/', '_').replace("%", "%25").replace("\n", "%0A");
	 
			}
			catch (Exception e)
			{
				e.printStackTrace();
				res=plainText;
			}
		}
		
		
		return res;
	}
	
	public static String saltDecrypt(String cipherText){
		String res = null;
		
		byte[] SALT = getKeyPerToday().getBytes();
		int ITERATION_COUNT	= getIterationPerToday();
		
		if (cipherText == null || cipherText.length()<=0)
		{
			res = "";
		}
		else{
			try
			{
	 
				String input = cipherText.replace("%0A", "\n").replace("%25", "%").replace('_', '/').replace('-', '+');
	 
				byte[] dec = Base64.decodeBase64(input.getBytes());
	 
				KeySpec keySpec = new PBEKeySpec(null, SALT, ITERATION_COUNT);
				AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATION_COUNT);
	 
				SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
	 
				Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
				dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
	 
				byte[] decoded = dcipher.doFinal(dec);
	 
				res = new String(decoded);
	 
			}
			catch (Exception e)
			{
	      // use logger in production code
				e.printStackTrace();
				res=cipherText;
			}
		}
		
		return res;
	}

}
