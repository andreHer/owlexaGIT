package com.ametis.cms.webservice;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.ametis.cms.dto.UserDto;
import com.ametis.cms.dto.MemberDto;

import com.ametis.cms.datamodel.ActionResult;

@WebService (targetNamespace="http://ametis.co.id/services/MobileApplicationWebService")
public interface IMobileApplicationWebService {

	//@WebMethod
	//public UserDto isValidLogin(String loginBy, String password);
	
	@WebMethod
	public UserDto doLogin(String loginBy, String password);
	
	//@WebMethod
	//public UserDto isValidRegister(String fullName, String dob, String cardNumber, String username, String password, String passwordRetype, String email, String mobilePhone);
	
	@WebMethod
	public UserDto doRegister(String fullName, String dob, String cardNumber, String username, String password, String passwordRetype, String email, String mobilePhone);
	
	/*
	 * BEGIN
	 * Add by aju on 20151012, some supporting ge ce em
	 */
	//@WebMethod
	//public UserDto isValidLoginGcmSupport(String loginBy, String password, String mobileDeviceId);
	
	@WebMethod
	public UserDto doLoginGcmSupport(String loginBy, String password, String mobileDeviceId);
	
	//@WebMethod
	//public UserDto isValidRegisterGcmSupport(String fullName, String dob, String cardNumber, String username, String password, String passwordRetype, String email, String mobilePhone, String mobileDeviceId);
	
	@WebMethod
	public UserDto doRegisterGcmSupport(String fullName, String dob, String cardNumber, String username, String password, String passwordRetype, String email, String mobilePhone, String mobileDeviceId);
	/*
	 * END
	 * Add by aju on 20151012, some supporting ge ce em
	 */
	
	@WebMethod
	public MemberDto getMemberById(String memberId);
	
	@WebMethod
	public UserDto sentResetPasswordEmail(String emailAddress);
	
	@WebMethod
	public UserDto doChangePassword(String userId, String oldPassword, String newPassword, String newPasswordRetype);
	
	@WebMethod
	public Collection<ActionResult> doMobileAnnouncementByDeviceId(String mobileDeviceId, String messageAnnouncement);
	
}
