/*
 * Name        : Security Web Service 
 * Create By   : A.J.U
 * Create Date : 20150416
 * Description : use for encrypt something :D
 * Usage       : Just give the client access to encrypt methods :D no need to give the decrypt :D
 */
package com.ametis.cms.webservice;

import javax.jws.WebService;

//for security API
import com.ametis.cms.util.SecurityUtil;

@WebService(name = "SecurityWebService", 
		endpointInterface = "com.ametis.cms.webservice.ISecurityWebService",
		serviceName="SecurityWebService")

public class SecurityWebServiceImpl implements ISecurityWebService{
	
	public String getToken(String plainURL){	
		System.out.println("Original URL : "+SecurityUtil.saltDecrypt( SecurityUtil.saltEncrypt(plainURL) ));
		return SecurityUtil.saltEncrypt(plainURL);
	}
	
	public String decryptURL(String cipherURL){		
		return SecurityUtil.saltDecrypt(cipherURL);
	}
	
}
