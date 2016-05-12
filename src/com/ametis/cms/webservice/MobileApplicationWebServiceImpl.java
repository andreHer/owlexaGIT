/*
 * Name        : Mobile Application Web Service 
 * Create By   : A.J.U
 * Create Date : 20150722
 * Description : use for mobile application gateway :D
 * Usage       : anything about mobile application :D
 */
package com.ametis.cms.webservice;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.WebService;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.xfire.transport.http.XFireServletController;
import org.springframework.context.ApplicationContext;

import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Role;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.PolicyDto;
import com.ametis.cms.dto.UserDto;
import com.ametis.cms.dto.MemberDto;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.RoleService;
import com.ametis.cms.service.SubscriptionStatusService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.service.MemberElectronicCardService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.util.ApplicationContextUtils;
import com.ametis.cms.util.ApplicationEnvironment;
import com.ametis.cms.util.SecurityUtil;
import com.ametis.cms.util.StringUtil;
/*
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
*/

@WebService(name = "MobileApplicationWebService", 
		endpointInterface = "com.ametis.cms.webservice.IMobileApplicationWebService",
		serviceName="MobileApplicationWebService")

public class MobileApplicationWebServiceImpl implements IMobileApplicationWebService{
	private UserService userService;
	private MemberService memberService;
	private RoleService roleService;
	private SubscriptionStatusService subscriptionStatusService;
	private ApplicationContext appContext;
	private PolicyService policyService;
	private MemberElectronicCardService memberCardService;
	
	//username can contain alphabets, numeric, dot, underscore, dash, minimal 6 char, maximal 25 char
	private static final String USERNAME_PATTERN = "^[a-z0-9._-]{6,25}$";
	private static final String FULLNAME_PATTERN = "[a-zA-Z\\s]{3,}";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
			   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
	private static final String ID_PATTERN = "[0-9]+";  
	private static final String STRING_PATTERN = "[a-zA-Z]+";  
	private static final String MOBILE_PATTERN = "[0-9]{8,}";  
	
	private static final String EMAIL_FROM_REGISTRATION = "mobile-activation@owlexa.com";
	private static final String EMAIL_FROM_RESET = "mobile-reset@owlexa.com";
	private static final String EMAIL_FROM_CHANGE = "mobile-changes@owlexa.com";
	
	private Pattern pattern;  
	private Matcher matcher;  
	
	//add by aju on 20151012, for ge ce em
	private static final String GOOGLE_SERVER_KEY = "AIzaSyD6e3cb5IC0qF7dBSnr5hzueAFbOcOsbxE";
	static final String MESSAGE_KEY = "message";
	
	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public MemberService getMemberService() {
		return memberService;
	}


	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}


	public RoleService getRoleService() {
		return roleService;
	}


	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


	public SubscriptionStatusService getSubscriptionStatusService() {
		return subscriptionStatusService;
	}


	public void setSubscriptionStatusService(
			SubscriptionStatusService subscriptionStatusService) {
		this.subscriptionStatusService = subscriptionStatusService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}


	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}


	public MemberElectronicCardService getMemberCardService() {
		return memberCardService;
	}


	public void setMemberCardService(MemberElectronicCardService memberCardService) {
		this.memberCardService = memberCardService;
	}


	public UserDto isValidLogin(String loginBy, String password) {
		UserDto result = new UserDto();
		
		try{
			result.setIsSuccess(true);
			
			System.out.println(".::MobileApplicationWebService.login.isValid()");
			System.out.println("loginBy    : " + loginBy);
			System.out.println("password   : " + password);
			
			//check if credential sent is null
			//loginBy, can be username, email or cardNumber		
			if(loginBy == null || loginBy.equalsIgnoreCase("")){
				System.out.println("Credential checker failed -> loginBy == null");
				result.setIsSuccess(false);
				result.setLoginReason("loginBy is null");
				
				return result;
			}
			
			//password
			if(password == null || password.equalsIgnoreCase("")){
				System.out.println("Credential checker failed -> password == null");
				result.setIsSuccess(false);
				result.setLoginReason("password is null");
				
				return result;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		result.setLoginReason("login credential check passed");
		return result;
	}
	
	//Add by aju on 20151012, to support ge ce em
	public UserDto isValidLoginGcmSupport(String loginBy, String password, String mobileDeviceId) {
		UserDto result = new UserDto();
		
		try{
			result.setIsSuccess(true);
			
			System.out.println(".::MobileApplicationWebService.login.isValid()");
			System.out.println("loginBy        : " + loginBy);
			System.out.println("password       : " + password);
			System.out.println("mobileDeviceId : " + mobileDeviceId);
			
			//check if credential sent is null
			//loginBy, can be username, email or cardNumber		
			if(loginBy == null || loginBy.equalsIgnoreCase("")){
				System.out.println("Credential checker failed -> loginBy == null");
				result.setIsSuccess(false);
				result.setLoginReason("loginBy is null");
				
				return result;
			}
			
			//password
			if(password == null || password.equalsIgnoreCase("")){
				System.out.println("Credential checker failed -> password == null");
				result.setIsSuccess(false);
				result.setLoginReason("password is null");
				
				return result;
			}
			
			//mobileDeviceId
			if(mobileDeviceId == null || mobileDeviceId.equalsIgnoreCase("")){
				System.out.println("Credential checker failed -> mobileDeviceId == null");
				result.setIsSuccess(false);
				result.setLoginReason("mobileDeviceId is null");
				
				return result;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		result.setLoginReason("login credential check passed");
		return result;
	}


	public UserDto doLogin(String loginBy, String password) {
		UserDto result=null;
		UserDto validationResult = null;
		
		try {
			result = new UserDto();
			validationResult = isValidLogin(loginBy, password);
			
			
			//check if credential valid
			if(validationResult.getIsSuccess()){
				HttpServletRequest request =  XFireServletController.getRequest();
				HttpSession session = request.getSession(false);
				System.out.println("ipAddress : " + request.getRemoteAddr());
				if(session == null){
					session = request.getSession();
				}
				System.out.println("sessionId : " + session);
				
				User user = null;
				Member member = null;
				Boolean successLogged = false;
				String hashedPassword = StringUtil.hash(password);
				//check if login by username
				if(!successLogged){
					String[] eqColumns = {"username", "password", "deletedStatus", "roleId.roleId", "userType"};
					Object[] eqParams = {loginBy, hashedPassword,  0, 17, 7};
					
					user = userService.searchUnique(eqColumns, eqParams, 0, 1);
					
					if(user != null){
						System.out.println("username  : " + user.getUsername());
						System.out.println("password  : " + password);
						System.out.println("sessionId : " + session.getId());
						System.out.println("ipAddress : " + request.getRemoteAddr());
						
						successLogged = userService.loginMobileApplication(user.getUsername(), password, session.getId(), request.getRemoteAddr());
					}
					System.out.println("user : " + user + ", loginByUsername success : " + successLogged);
				}
				
				//if login failed, check if it's login by email
				if(!successLogged){
					String[] eqColumns = {"email", "password", "deletedStatus", "roleId.roleId", "userType"};
					Object[] eqParams = {loginBy, hashedPassword,  0, 17, 7};
					
					user = userService.searchUnique(eqColumns, eqParams, 0, 1);
					
					if(user != null){
						successLogged = userService.loginMobileApplication(user.getUsername(), password, session.getId(), request.getRemoteAddr());
					}
					System.out.println("user : " + user + ", loginByEmail success : " + successLogged);
				}
				
				//if login failed, check if it's login by cardNumber
				if(!successLogged){
					member = memberService.getMemberByCardNumber(loginBy);
					
					if(member != null){
						String[] eqColumns = {"memberId.memberId", "password", "deletedStatus", "roleId.roleId", "userType"};
						Object[] eqParams = {member.getMemberId(), hashedPassword,  0, 17, 7};
						
						user = userService.searchUnique(eqColumns, eqParams, 0, 1);
						
						if(user != null){
							successLogged = userService.loginMobileApplication(user.getUsername(), password, session.getId(), request.getRemoteAddr());
						}
					}
					System.out.println("user : " + user + ", loginByCardNumber success : " + successLogged);
				}
				
				//if success on login, make the userDto :D 
				if(successLogged){
					result.setIsSuccess(true);
					result.setLoginReason("LOGINSUCCESS");
					result.setUserId(user.getUserId());
					result.setUsername(user.getUsername());
					result.setEmail(user.getEmail());
					result.setMemberId(user.getMemberId().getMemberId().toString());
				}
				else{
					result.setIsSuccess(false);
					result.setLoginReason("LOGINFAILED");
				}
				
				
			}
			else{
				result.setIsSuccess(false);
				result.setLoginReason("INVALIDCREDENTIAL");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//Add by aju on 20151012, to support ge ce em
	public UserDto doLoginGcmSupport(String loginBy, String password, String mobileDeviceId) {
		UserDto result=null;
		UserDto validationResult = null;
		
		try {
			result = new UserDto();
			validationResult = isValidLoginGcmSupport(loginBy, password, mobileDeviceId);
			
			
			//check if credential valid
			if(validationResult.getIsSuccess()){
				HttpServletRequest request =  XFireServletController.getRequest();
				HttpSession session = request.getSession(false);
				System.out.println("ipAddress : " + request.getRemoteAddr());
				if(session == null){
					session = request.getSession();
				}
				System.out.println("sessionId : " + session);
				
				User user = null;
				Member member = null;
				Boolean successLogged = false;
				String hashedPassword = StringUtil.hash(password);
				//check if login by username
				if(!successLogged){
					String[] eqColumns = {"username", "password", "deletedStatus", "roleId.roleId", "userType"};
					Object[] eqParams = {loginBy, hashedPassword,  0, 17, 7};
					
					user = userService.searchUnique(eqColumns, eqParams, 0, 1);
					
					if(user != null){
						System.out.println("username       : " + user.getUsername());
						System.out.println("password       : " + password);
						System.out.println("sessionId      : " + session.getId());
						System.out.println("ipAddress      : " + request.getRemoteAddr());
						System.out.println("mobileDeviceId : " + mobileDeviceId);
						
						successLogged = userService.loginMobileApplicationGcmSupport(user.getUsername(), password, session.getId(), request.getRemoteAddr(), mobileDeviceId);
					}
					System.out.println("user : " + user + ", loginByUsername success : " + successLogged);
				}
				
				//if login failed, check if it's login by email
				if(!successLogged){
					String[] eqColumns = {"email", "password", "deletedStatus", "roleId.roleId", "userType"};
					Object[] eqParams = {loginBy, hashedPassword,  0, 17, 7};
					
					user = userService.searchUnique(eqColumns, eqParams, 0, 1);
					
					if(user != null){
						successLogged = userService.loginMobileApplicationGcmSupport(user.getUsername(), password, session.getId(), request.getRemoteAddr(), mobileDeviceId);
					}
					System.out.println("user : " + user + ", loginByEmail success : " + successLogged);
				}
				
				//if login failed, check if it's login by cardNumber
				if(!successLogged){
					member = memberService.getMemberByCardNumber(loginBy);
					
					if(member != null){
						String[] eqColumns = {"memberId.memberId", "password", "deletedStatus", "roleId.roleId", "userType"};
						Object[] eqParams = {member.getMemberId(), hashedPassword,  0, 17, 7};
						
						user = userService.searchUnique(eqColumns, eqParams, 0, 1);
						
						if(user != null){
							successLogged = userService.loginMobileApplicationGcmSupport(user.getUsername(), password, session.getId(), request.getRemoteAddr(), mobileDeviceId);
						}
					}
					System.out.println("user : " + user + ", loginByCardNumber success : " + successLogged);
				}
				
				//if success on login, make the userDto :D 
				if(successLogged){
					result.setIsSuccess(true);
					result.setLoginReason("LOGINSUCCESS");
					result.setUserId(user.getUserId());
					result.setUsername(user.getUsername());
					result.setEmail(user.getEmail());
					result.setMemberId(user.getMemberId().getMemberId().toString());
					result.setMobileDeviceId(mobileDeviceId);
				}
				else{
					result.setIsSuccess(false);
					result.setLoginReason("LOGINFAILED");
				}
				
				
			}
			else{
				result.setIsSuccess(false);
				result.setLoginReason("INVALIDCREDENTIAL");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
	public UserDto isValidRegister(String fullName, String dob,
			String cardNumber, String username, String password,
			String passwordRetype, String email, String mobilePhone) {
				
		UserDto result = new UserDto();
		try{
			
			result.setIsSuccess(true);
			
			System.out.println(".::MobileApplicationWebService.register.isValid()");
			System.out.println("fullName       : " + fullName);
			System.out.println("dob            : " + dob);
			System.out.println("cardNumber     : " + cardNumber);
			System.out.println("username       : " + username);
			System.out.println("password       : " + password);
			System.out.println("passwordRetype : " + passwordRetype);
			System.out.println("email          : " + email);
			System.out.println("mobilePhone    : " + mobilePhone);
			
			//check  user fullName
			if(fullName == null || fullName.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> fullName is null");
				result.setIsSuccess(false);
				result.setRegisterReason("fullName is null");
				return result;
			}
			/*pattern = Pattern.compile(FULLNAME_PATTERN);  
			matcher = pattern.matcher(fullName);  
			if (!matcher.matches()) {  
				System.out.println("Register field checker failed -> full name not valid format");
				result.setIsSuccess(false);
				result.setRegisterReason("full name not valid format");
				return result;
			}  */
			
			//check  user cardNumber
			if(cardNumber == null || cardNumber.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> cardNumber is null");
				result.setIsSuccess(false);
				result.setRegisterReason("cardNumber is null");
				return result;
			}
			Member member = memberService.getMemberByCardNumber(cardNumber);
			if(member == null){
				System.out.println("Register field checker failed -> cardNumber not found");
				result.setIsSuccess(false);
				result.setRegisterReason("cardNumber not found");
				return result;
			}
			
			//check  user dob
			if(dob == null || dob.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> dob is null");
				result.setIsSuccess(false);
				result.setRegisterReason("dob is null");
				return result;
			}
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date memberDob = null;
			try {
				memberDob = formatter.parse(dob);
			} 
			catch (ParseException e) {
				System.out.println("Register field checker failed -> dob not valid format (yyyy-mm-dd)");
				result.setIsSuccess(false);
				result.setRegisterReason("dob not valid format (yyyy-mm-dd)");
				return result;
			}
			java.sql.Date memberBd = new java.sql.Date(memberDob.getTime());
			if(!member.getBirthday().equals(memberBd)){
				System.out.println("Register field checker failed -> dob different from member dob");
				System.out.println("Member.Birthday   : " + member.getBirthday());
				System.out.println("Register Birthday : " + memberBd);
				result.setIsSuccess(false);
				result.setRegisterReason("dob different from member dob");
				return result;
			}
			
			//check  user username
			if(username == null || username.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> username is null");
				result.setIsSuccess(false);
				result.setRegisterReason("username is null");
				return result;
			}
			pattern = Pattern.compile(USERNAME_PATTERN);  
			matcher = pattern.matcher(username);  
			if (!matcher.matches()) {  
				System.out.println("Register field checker failed -> username not valid format");
				result.setIsSuccess(false);
				result.setRegisterReason("username not valid format");
				return result;
			} 
			
			
			//check  user password
			if(password == null || password.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> password is null");
				result.setIsSuccess(false);
				result.setRegisterReason("password is null");
				return result;
			}

			//check  user passwordRetype
			if(passwordRetype == null || passwordRetype.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> passwordRetype is null");
				result.setIsSuccess(false);
				result.setRegisterReason("passwordRetype is null");
				return result;
			}
			
			//check if password and passwordRetype are same
			if(password.compareTo(passwordRetype) != 0){
				System.out.println("Register field checker failed -> password and passwordRetype is different");
				result.setIsSuccess(false);
				result.setRegisterReason("password and passwordRetype is different");
				return result;
			}

			//check  user email
			if(email == null || email.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> email is null");
				result.setIsSuccess(false);
				result.setRegisterReason("email is null");
				return result;
			}
			pattern = Pattern.compile(EMAIL_PATTERN);  
			matcher = pattern.matcher(email);  
			if (!matcher.matches()) {  
				System.out.println("Register field checker failed -> email address not valid format");
				result.setIsSuccess(false);
				result.setRegisterReason("email address not valid format");
				return result;
			}  

			//check  user mobilePhone
			if(mobilePhone == null || mobilePhone.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> mobilePhone is null");
				result.setIsSuccess(false);
				result.setRegisterReason("mobilePhone is null");
				return result;
			}
			pattern = Pattern.compile(MOBILE_PATTERN);    
			matcher = pattern.matcher(mobilePhone);  
			if (!matcher.matches()) {  
				System.out.println("Register field checker failed -> mobilePhone not valid format");
				result.setIsSuccess(false);
				result.setRegisterReason("mobilePhone not valid format");
				return result;
			}  

			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
				
		result.setRegisterReason("register validation passed");
		return result;
	}
	
	//Add by aju on 20151012, to support ge ce em
	public UserDto isValidRegisterGcmSupport(String fullName, String dob,
			String cardNumber, String username, String password,
			String passwordRetype, String email, String mobilePhone, String mobileDeviceId) {
				
		UserDto result = new UserDto();
		try{
			
			result.setIsSuccess(true);
			
			System.out.println(".::MobileApplicationWebService.register.isValid()");
			System.out.println("fullName       : " + fullName);
			System.out.println("dob            : " + dob);
			System.out.println("cardNumber     : " + cardNumber);
			System.out.println("username       : " + username);
			System.out.println("password       : " + password);
			System.out.println("passwordRetype : " + passwordRetype);
			System.out.println("email          : " + email);
			System.out.println("mobilePhone    : " + mobilePhone);
			System.out.println("mobileDeviceId : " + mobileDeviceId);
			
			//check  user fullName
			if(fullName == null || fullName.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> fullName is null");
				result.setIsSuccess(false);
				result.setRegisterReason("fullName is null");
				return result;
			}
			/*pattern = Pattern.compile(FULLNAME_PATTERN);  
			matcher = pattern.matcher(fullName);  
			if (!matcher.matches()) {  
				System.out.println("Register field checker failed -> full name not valid format");
				result.setIsSuccess(false);
				result.setRegisterReason("full name not valid format");
				return result;
			}  */
			
			//check  user cardNumber
			if(cardNumber == null || cardNumber.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> cardNumber is null");
				result.setIsSuccess(false);
				result.setRegisterReason("cardNumber is null");
				return result;
			}
			Member member = memberService.getMemberByCardNumber(cardNumber);
			if(member == null){
				System.out.println("Register field checker failed -> cardNumber not found");
				result.setIsSuccess(false);
				result.setRegisterReason("cardNumber not found");
				return result;
			}
			
			//check  user dob
			if(dob == null || dob.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> dob is null");
				result.setIsSuccess(false);
				result.setRegisterReason("dob is null");
				return result;
			}
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date memberDob = null;
			try {
				memberDob = formatter.parse(dob);
			} 
			catch (ParseException e) {
				System.out.println("Register field checker failed -> dob not valid format (yyyy-mm-dd)");
				result.setIsSuccess(false);
				result.setRegisterReason("dob not valid format (yyyy-mm-dd)");
				return result;
			}
			java.sql.Date memberBd = new java.sql.Date(memberDob.getTime());
			if(!member.getBirthday().equals(memberBd)){
				System.out.println("Register field checker failed -> dob different from member dob");
				System.out.println("Member.Birthday   : " + member.getBirthday());
				System.out.println("Register Birthday : " + memberBd);
				result.setIsSuccess(false);
				result.setRegisterReason("dob different from member dob");
				return result;
			}
			
			//check  user username
			if(username == null || username.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> username is null");
				result.setIsSuccess(false);
				result.setRegisterReason("username is null");
				return result;
			}
			pattern = Pattern.compile(USERNAME_PATTERN);  
			matcher = pattern.matcher(username);  
			if (!matcher.matches()) {  
				System.out.println("Register field checker failed -> username not valid format");
				result.setIsSuccess(false);
				result.setRegisterReason("username not valid format");
				return result;
			} 
			
			
			//check  user password
			if(password == null || password.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> password is null");
				result.setIsSuccess(false);
				result.setRegisterReason("password is null");
				return result;
			}

			//check  user passwordRetype
			if(passwordRetype == null || passwordRetype.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> passwordRetype is null");
				result.setIsSuccess(false);
				result.setRegisterReason("passwordRetype is null");
				return result;
			}
			
			//check if password and passwordRetype are same
			if(password.compareTo(passwordRetype) != 0){
				System.out.println("Register field checker failed -> password and passwordRetype is different");
				result.setIsSuccess(false);
				result.setRegisterReason("password and passwordRetype is different");
				return result;
			}

			//check  user email
			if(email == null || email.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> email is null");
				result.setIsSuccess(false);
				result.setRegisterReason("email is null");
				return result;
			}
			pattern = Pattern.compile(EMAIL_PATTERN);  
			matcher = pattern.matcher(email);  
			if (!matcher.matches()) {  
				System.out.println("Register field checker failed -> email address not valid format");
				result.setIsSuccess(false);
				result.setRegisterReason("email address not valid format");
				return result;
			}  

			//check  user mobilePhone
			if(mobilePhone == null || mobilePhone.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> mobilePhone is null");
				result.setIsSuccess(false);
				result.setRegisterReason("mobilePhone is null");
				return result;
			}
			pattern = Pattern.compile(MOBILE_PATTERN);    
			matcher = pattern.matcher(mobilePhone);  
			if (!matcher.matches()) {  
				System.out.println("Register field checker failed -> mobilePhone not valid format");
				result.setIsSuccess(false);
				result.setRegisterReason("mobilePhone not valid format");
				return result;
			}  

			//check  user mobileDeviceId
			if(mobileDeviceId == null || mobileDeviceId.equalsIgnoreCase("")){
				System.out.println("Register field checker failed -> mobileDeviceId is null");
				result.setIsSuccess(false);
				result.setRegisterReason("mobileDeviceId is null");
				return result;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
				
		result.setRegisterReason("register validation passed");
		return result;
	}


	public UserDto doRegister(String fullName, String dob, String cardNumber,
			String username, String password, String passwordRetype,
			String email, String mobilePhone) {
		UserDto result = null;
		UserDto validationResult = null;
		Boolean alreadyRegistered = false;
		
		try{
			result = new UserDto();
			result.setIsSuccess(true);
			validationResult = isValidRegister(fullName, dob, cardNumber, username, password, passwordRetype, email, mobilePhone);
			
			if(validationResult.getIsSuccess()){
				//check if user already registered depends on some data inputted, i.e: cardNumber, email, username
				//check by cardNumber
				User user = null;
				Member member = memberService.getMemberByCardNumber(cardNumber);
				if(member == null){
					result.setIsSuccess(false);
					result.setRegisterReason("CardNo is not found");
					return result;
				}
				
				String[] checkByCardColumns = {"memberId.memberId", "deletedStatus", "roleId.roleId", "userType"};
				Object[] checkByCardParams = {member.getMemberId() ,0, 17, 7};
				
				user = userService.searchUnique(checkByCardColumns, checkByCardParams, 0, 1);
				if(user != null){
					result.setIsSuccess(false);
					result.setRegisterReason("CardNo already registered");
					return result;
				}
				
				//check by email
				String[] checkByEmailColumns = {"email", "deletedStatus", "roleId.roleId", "userType"};
				Object[] checkByEmailParams = {email ,0, 17, 7};
				
				user = userService.searchUnique(checkByEmailColumns, checkByEmailParams, 0, 1);
				if(user != null){
					result.setIsSuccess(false);
					result.setRegisterReason("Email already registered");
					return result;
				}
				
				//check by username
				String[] checkByUsernameColumns = {"username", "deletedStatus", "roleId.roleId", "userType"};
				Object[] checkByUsernameParams = {username ,0, 17, 7};
				
				user = userService.searchUnique(checkByUsernameColumns, checkByUsernameParams, 0, 1);
				if(user != null){
					result.setIsSuccess(false);
					result.setRegisterReason("Username already registered");
					return result;
				}
				
				//Create the user for mobile application :D
				ActionUser adminUser = new ActionUser();
				User theAdminUser = userService.getUser("admin");
				adminUser.setUser(theAdminUser);
				
				User newUser = new User();
				newUser.setUsername(username);
				newUser.setFirstName(fullName);
				newUser.setEmail(email);
				newUser.setMobilePhone(mobilePhone);
				
				/* no need to hashed the password, because the userService.create will hash it :D
				String hashedPassword = StringUtil.hash(password);
				System.out.println("ori : " + password);
				System.out.println("mod : " + hashedPassword);
				newUser.setPassword(hashedPassword);
				*/
				newUser.setPassword(password);
				
				Role theRole = new Role();
				theRole.setRoleId(Role.MEMBER);
				newUser.setRoleId(theRole);
				
				newUser.setUserType(7);
				
				newUser.setClientId(member.getClientId());
				newUser.setMemberGroupId(member.getMemberGroupId());
				newUser.setMemberId(member);
				
				System.out.println("Trying to create new mobile application user : " + newUser.getUsername());
				User createdNew = userService.create(newUser, adminUser);
				System.out.println("New User : " + newUser);
				if(createdNew != null){
					//if user successfully created, make it inactive first muahahaha :D
					//make it inactive first after created :D
					System.out.println("Block the new user : " + createdNew.getUsername() + "\nUserID : "+ createdNew.getUserId());
					userService.block(createdNew.getUserId(), adminUser);
					
					sentActivationEmail(createdNew, password, cardNumber);
					
					result.setRegisterReason("REGISTRATIONSUCCESS");
				}
				else{
					result.setIsSuccess(false);
					result.setRegisterReason("REGISTRATIONFAILED");
					return result;
				}
				
			}
			else{
				result.setIsSuccess(false);
				result.setRegisterReason("INVALIDREGISTER");
				return result;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//Add by aju on 20151012, to support ge ce em
	public UserDto doRegisterGcmSupport(String fullName, String dob, String cardNumber,
			String username, String password, String passwordRetype,
			String email, String mobilePhone, String mobileDeviceId) {
		UserDto result = null;
		UserDto validationResult = null;
		Boolean alreadyRegistered = false;
		
		try{
			result = new UserDto();
			result.setIsSuccess(true);
			validationResult = isValidRegisterGcmSupport(fullName, dob, cardNumber, username, password, passwordRetype, email, mobilePhone, mobileDeviceId);
			
			if(validationResult.getIsSuccess()){
				//check if user already registered depends on some data inputted, i.e: cardNumber, email, username
				//check by cardNumber
				User user = null;
				Member member = memberService.getMemberByCardNumber(cardNumber);
				if(member == null){
					result.setIsSuccess(false);
					result.setRegisterReason("CardNo is not found");
					return result;
				}
				
				String[] checkByCardColumns = {"memberId.memberId", "deletedStatus", "roleId.roleId", "userType"};
				Object[] checkByCardParams = {member.getMemberId() ,0, 17, 7};
				
				user = userService.searchUnique(checkByCardColumns, checkByCardParams, 0, 1);
				if(user != null){
					result.setIsSuccess(false);
					result.setRegisterReason("CardNo already registered");
					return result;
				}
				
				//check by email
				String[] checkByEmailColumns = {"email", "deletedStatus", "roleId.roleId", "userType"};
				Object[] checkByEmailParams = {email ,0, 17, 7};
				
				user = userService.searchUnique(checkByEmailColumns, checkByEmailParams, 0, 1);
				if(user != null){
					result.setIsSuccess(false);
					result.setRegisterReason("Email already registered");
					return result;
				}
				
				//check by username
				String[] checkByUsernameColumns = {"username", "deletedStatus", "roleId.roleId", "userType"};
				Object[] checkByUsernameParams = {username ,0, 17, 7};
				
				user = userService.searchUnique(checkByUsernameColumns, checkByUsernameParams, 0, 1);
				if(user != null){
					result.setIsSuccess(false);
					result.setRegisterReason("Username already registered");
					return result;
				}
				
				//Create the user for mobile application :D
				ActionUser adminUser = new ActionUser();
				User theAdminUser = userService.getUser("admin");
				adminUser.setUser(theAdminUser);
				
				User newUser = new User();
				newUser.setUsername(username);
				newUser.setFirstName(fullName);
				newUser.setEmail(email);
				newUser.setMobilePhone(mobilePhone);
				
				/* no need to hashed the password, because the userService.create will hash it :D
				String hashedPassword = StringUtil.hash(password);
				System.out.println("ori : " + password);
				System.out.println("mod : " + hashedPassword);
				newUser.setPassword(hashedPassword);
				*/
				newUser.setPassword(password);
				
				Role theRole = new Role();
				theRole.setRoleId(Role.MEMBER);
				newUser.setRoleId(theRole);
				
				newUser.setUserType(7);
				
				newUser.setClientId(member.getClientId());
				newUser.setMemberGroupId(member.getMemberGroupId());
				newUser.setMemberId(member);

				newUser.setMobileDeviceId(mobileDeviceId);
				
				System.out.println("Trying to create new mobile application user : " + newUser.getUsername());
				User createdNew = userService.create(newUser, adminUser);
				System.out.println("New User : " + newUser);
				if(createdNew != null){
					//if user successfully created, make it inactive first muahahaha :D
					//make it inactive first after created :D
					System.out.println("Block the new user : " + createdNew.getUsername() + "\nUserID : "+ createdNew.getUserId());
					userService.block(createdNew.getUserId(), adminUser);
					
					sentActivationEmail(createdNew, password, cardNumber);
					
					result.setRegisterReason("REGISTRATIONSUCCESS");
				}
				else{
					result.setIsSuccess(false);
					result.setRegisterReason("REGISTRATIONFAILED");
					return result;
				}
				
			}
			else{
				result.setIsSuccess(false);
				result.setRegisterReason("INVALIDREGISTER");
				return result;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private void sentActivationEmail(User user, String password, String cardNumber){
		try{
			
			Properties prop = System.getProperties();
			prop.setProperty("mail.smtp.host", "localhost");
//			
			ApplicationEnvironment env = (ApplicationEnvironment) ApplicationContextUtils.getApplicationContext().getBean("applicationEnvironment", ApplicationEnvironment.class);
			System.out.println("Web Environment : " + env.getEnvironmentServer());
			System.out.println("Web Url : " + env.getEnvironmentUrl());
			
			String fromAddress=null;
			fromAddress= EMAIL_FROM_REGISTRATION;
			//fromAddress= "mobile-activation@owlexa.com";
			//fromAddress= "noreply@localhost";
			
			String toAddress = null;
			toAddress = user.getEmail();
			
			/*
			if(toAddress == null){
				toAddress = "member@localhost";
			}
			*/
			
			String emailSubject="[NO-REPLY] Mobile account activation";
			
			Member member = memberService.searchUnique("memberId", user.getMemberId().getMemberId());
			
			String emailBody="Kepada " + (user.getMemberId().getGender().equalsIgnoreCase("P") ? "Ibu":"Bapak") + " " + member.getFirstName() + ",";
			emailBody += "\nTerima kasih telah menggunakan fasilitas Owlexa Healthcare.";
			emailBody += "\n\nMelalui email ini kami ingin menginformasikan bahwa akun aplikasi mobile Owlexa Healthcare : ";
			emailBody += "\n\tLogin\t: " + cardNumber + " / " + user.getEmail() + " / " + user.getUsername();
			emailBody += "\n\tPassword\t: " + password;
			emailBody += "\nTelah terdaftar di system kami, untuk mengaktifkan akun tersebut mohon klik link berikut";
			
			//emailBody += "\n\thttp://182.23.65.111/user?navigation=activatemobileaccount&token=";
			//emailBody += "\n\thttp://localhost:8181/owlexa/user?navigation=activatemobileaccount&mobiletoken=" + SecurityUtil.saltEncrypt("userId=" + user.getUserId());
			emailBody += "\n\t\"http://" +env.getEnvironmentUrl() + "/user?navigation=activatemobileaccount&mobiletoken=" + SecurityUtil.saltEncrypt("userId=" + user.getUserId()) + "\"";
			emailBody += "\n\nSemoga informasi ini berguna untuk Anda.";
			emailBody += "\n\nHormat kami,";
			emailBody += "\nOwlexa Healthcare";
			
			Session emailSession = Session.getDefaultInstance(prop);
			MimeMessage emailMessage = new MimeMessage(emailSession);
			
			//set email sender
			if(fromAddress != null){
				emailMessage.setFrom(new InternetAddress(fromAddress));
			}
			
			//set email receiver
			if(toAddress != null){
				InternetAddress[] emailAddress = InternetAddress.parse(toAddress);
				emailMessage.addRecipients(Message.RecipientType.TO, emailAddress);
			}
			System.out.println("Sent activation email to : " + toAddress);
			
			//set email subject
			emailMessage.setSubject(emailSubject);
			
			//set email body
			BodyPart emailBodyPart = new MimeBodyPart();
			emailBodyPart.setText(emailBody);
			
			//construct the email
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(emailBodyPart);
			
			emailMessage.setContent(multipart);
			
			//sent it
			Transport.send(emailMessage);
			System.out.println("Sent activation email success!!!");
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public PolicyDto getActivePolicy(String memberGroupId, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub
		PolicyDto result = null;
		
		try {
			Policy policy = policyService.getGroupActivePolicy(Integer.valueOf(memberGroupId));
			
			if (policy != null){
				result = policy.exportDto();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public MemberDto getMemberById(String memberId) {
		MemberDto result = null;
		try {
			System.out.println("MobileApplicationWebService::getMemberById()");
			java.io.Serializable mid = Integer.valueOf(memberId);
			String[] required = {"Member.CurrentPolicyId","Member.ClientId", "Member.MemberGroupId"};
			Member member = memberService.get(mid,required);
			System.out.println("Member : " + member);
			
			if (member != null){
				
				result = new MemberDto();
				result.setCustomerPolicyNumber(member.getCustomerPolicyNumber());
				result.setCustomerNumber(member.getCustomerNumber());
				result.setMemberId(member.getMemberId());
				if (member.getParentMember() != null){
					result.setParentId(member.getParentMember().getMemberId().toString());
					result.setParentName(member.getParentMember().getFirstName());
				}
				result.setCardNumber(member.getCurrentCardNumber() == null ? "" : member.getCurrentCardNumber());				
				
				if (member.getCurrentPolicyId() != null){;
					result.setPolicyNumber(member.getCurrentPolicyId().getPolicyNumber() == null ? "" : member.getCurrentPolicyId().getPolicyNumber());
				}

				
				result.setFirstName(member.getFirstName());
				result.setBirthday(member.getBirthday());
				result.setExpireDate(member.getExpireDate());
				result.setEffectiveDate(member.getEffectiveDate());
				result.setResignedDate(member.getResignedDate());
				
				result.setClientName(member.getClientId().getClientName());
				result.setClientCode(member.getClientId().getClientCode());
				
				result.setGender(member.getGender());
				result.setClientId(member.getClientId().getClientId().toString());
				
				if (member.getMemberGroupId() != null){
					result.setMemberGroupCode(member.getMemberGroupId().getMemberGroupCode() == null ? "" : member.getMemberGroupId().getMemberGroupCode());
					result.setMemberGroupId(member.getMemberGroupId().getMemberGroupId().toString());
					
					
					result.setMemberGroupName(member.getMemberGroupId().getGroupName());
					
					PolicyDto activePolicy = getActivePolicy(member.getMemberGroupId().getMemberGroupId().toString(), null);
					
					if (activePolicy != null){
						result.setPolicyType(activePolicy.getPolicyType());
					}
					
				}
				
				
				if (member.getMemberGroupId() != null){
					if (member.getMemberGroupId().getStatus().getStatusId().intValue() == SubscriptionStatus.ACTIVE){
						result.setStatus(member.getStatus().toString());
					}
					else {
						result.setStatus(member.getMemberGroupId().getStatus().getStatusId().toString());
					}
				}
				
				String[] eqCardParam = {"memberId.memberId"};
				Object[] eqCardValue = {member.getMemberId()};
				
				Collection<MemberElectronicCard> cardList = memberCardService.search(null,null,eqCardParam,eqCardValue,0,1);
				
				if (cardList != null){
					Iterator<MemberElectronicCard> iterator = cardList.iterator();
					
					if (iterator.hasNext()){
						MemberElectronicCard card = iterator.next();
						
						if (card != null ){
							result.setCardStatus(card.getCardStatus().toString());
						}
						else {
							result.setCardStatus("0");
						}
					}
				}
				else {
					result.setCardStatus("UNKNOWN");
				}
				
			}
			else {
				String[] eqCardParam = {"deletedStatus","memberId.memberId"};
				Object[] eqCardValue = {Integer.valueOf(0),memberId};
				
				Collection<MemberElectronicCard> cardList = memberCardService.search(null,null,eqCardParam,eqCardValue,0,1);
				if (cardList != null){
					Iterator<MemberElectronicCard> iterator = cardList.iterator();
					if (iterator.hasNext()){
				
						MemberElectronicCard memberCard = iterator.next();
						
						String[] reqMember = {"Member.MemberGroupId","Member.MemberGroupId.ClientId","Member.ClientId"};
						member = memberCard.getMemberId();
						
						result = new MemberDto();
						result.setCustomerPolicyNumber(member.getCustomerPolicyNumber());
						result.setCustomerNumber(member.getCustomerNumber());
						result.setMemberId(member.getMemberId());
						
						
						result.setFirstName(member.getFirstName());
						result.setBirthday(member.getBirthday());
						result.setExpireDate(member.getExpireDate());
						result.setEffectiveDate(member.getEffectiveDate());
						result.setResignedDate(member.getResignedDate());
						result.setClientName(member.getClientId().getClientName());
						result.setClientCode(member.getClientId().getClientCode());
						
						
						if (member.getMemberGroupId() != null){
							result.setMemberGroupId(member.getMemberGroupId().getMemberGroupId().toString());
							result.setPolicyNumber(member.getCurrentPolicyId().getPolicyNumber());
							result.setMemberGroupName(member.getMemberGroupId().getGroupName());
							
							if (member.getMemberGroupId().getStatus().getStatusId().intValue() == SubscriptionStatus.ACTIVE){
								result.setStatus(member.getStatus().toString());
							}
							else {
								result.setStatus(member.getMemberGroupId().getStatus().getStatusId().toString());
							}
						}
						
						result.setCardStatus(memberCard.getCardStatus().toString());
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public UserDto sentResetPasswordEmail(String emailAddress) {
		UserDto result = null;
		User user = null;
		Member member = null;
		
		try{
			System.out.println("MobileApplicationWebServiceImpl::sentResetPasswordEmail()");
			result = new UserDto();
			result.setIsSuccess(true);
			
			//check email address
			if(emailAddress == null || emailAddress.equalsIgnoreCase("")){
				System.out.println("sentResetPasswordEmail() failed -> email address is null");
				result.setIsSuccess(false);
				result.setRegisterReason("email address is null");
				return result;
			}
			pattern = Pattern.compile(EMAIL_PATTERN);  
			matcher = pattern.matcher(emailAddress);  
			if (!matcher.matches()) {  
				System.out.println("sentResetPasswordEmail() failed -> email address invalid format");
				result.setIsSuccess(false);
				result.setRegisterReason("email address invalid format");
				return result;
			}  
			
			//get user by email
			String[] checkByCardColumns = {"email", "deletedStatus", "roleId.roleId", "userType"};
			Object[] checkByCardParams = {emailAddress ,0, 17, 7};
			
			user = userService.searchUnique(checkByCardColumns, checkByCardParams, 0, 1);
			if(user == null){
				result.setIsSuccess(false);
				result.setRegisterReason("user email address not registered");
				return result;
			}
			
			//get the member from user
			member = memberService.searchUnique("memberId", user.getMemberId().getMemberId());
			if(member == null){
				result.setIsSuccess(false);
				result.setRegisterReason("user member data not found");
				return result;
			}
			
			//reset the password
			//to be simple, the new password are the md5 of the owlexa salted encrypt username
			String newPassword = SecurityUtil.saltEncrypt(user.getUsername());
			if(!doResetPassword(user, newPassword)){
				//if not success reset password
				result.setIsSuccess(false);
				result.setRegisterReason("failed to reset password");
				return result;
			}
			else{
				//if success reset password, sent the email
				//create the reset password email
				Properties prop = System.getProperties();
				prop.setProperty("mail.smtp.host", "localhost");
				
				ApplicationEnvironment env = (ApplicationEnvironment) ApplicationContextUtils.getApplicationContext().getBean("applicationEnvironment", ApplicationEnvironment.class);
				System.out.println("Web Environment : " + env.getEnvironmentServer());
				System.out.println("Web Url : " + env.getEnvironmentUrl());
				
				String fromAddress=null;
				fromAddress= EMAIL_FROM_RESET;
				
				String toAddress = null;
				toAddress = user.getEmail();
				
				String emailSubject="[NO-REPLY] Mobile account reset password";

				String emailBody="Kepada " + (member.getGender().equalsIgnoreCase("P") ? "Ibu":"Bapak") + " " + member.getFirstName() + ",";
				emailBody += "\nTerima kasih telah menggunakan fasilitas Owlexa Healthcare.";
				emailBody += "\n\nMelalui email ini kami ingin menginformasikan bahwa reset password akun aplikasi mobile Owlexa Healthcare telah berhasil dilakukan: ";
				emailBody += "\n\tLogin\t: " + member.getCurrentCardNumber() + " / " + user.getEmail() + " / " + user.getUsername();
				emailBody += "\n\tPassword\t: " + newPassword;

				emailBody += "\n\nSemoga informasi ini berguna untuk Anda.";
				emailBody += "\n\nHormat kami,";
				emailBody += "\nOwlexa Healthcare";
				
				Session emailSession = Session.getDefaultInstance(prop);
				MimeMessage emailMessage = new MimeMessage(emailSession);

				//set email sender
				if(fromAddress != null){
					emailMessage.setFrom(new InternetAddress(fromAddress));
				}

				//set email receiver
				if(toAddress != null){
					InternetAddress[] toEmailAddress = InternetAddress.parse(toAddress);
					emailMessage.addRecipients(Message.RecipientType.TO, toEmailAddress);
				}
				System.out.println("Sent reset password email to : " + toAddress);
				
				//set email subject
				emailMessage.setSubject(emailSubject);

				//set email body
				BodyPart emailBodyPart = new MimeBodyPart();
				emailBodyPart.setText(emailBody);

				//construct the email
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(emailBodyPart);

				emailMessage.setContent(multipart);

				//sent it
				Transport.send(emailMessage);
				System.out.println("Sent reset password email success!!!");
				
				result.setRegisterReason("RESETPASSWORDSUCCESS");
				
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean doResetPassword(User user, String newPassword){
		boolean result = true;
		
		try{
			HttpServletRequest request =  XFireServletController.getRequest();
			HttpSession session = request.getSession(false);
			System.out.println("ipAddress : " + request.getRemoteAddr());
			if(session == null){
				session = request.getSession();
			}
			System.out.println("sessionId : " + session);
		
			ActionUser actionUser = null;
			
			User theUser = userService.getUser(user.getUsername());
			
			String ipAddress = request.getRemoteAddr();
			String sessionCode = request.getSession().getId();
			
			actionUser = new ActionUser();
			actionUser.setIpAddress(ipAddress);
			actionUser.setLoginSession(sessionCode);
			actionUser.setUser(theUser);
			actionUser.setActionURL(request.getRequestURL().toString());
			
			//create the new password from owlexa salted username, the do changePassword, no need to hash, the method already do it :D
			System.out.println("Owlexa Salted newPassword : " + newPassword);
			result = userService.changePassword(user.getUserId(), newPassword, actionUser);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public UserDto doChangePassword(String userId, String oldPassword,
			String newPassword, String newPasswordRetype) {
		UserDto  result = null;
		
		try {
			
			result = new UserDto();
			result.setIsSuccess(true);
			
			//check userId
			if(userId == null || userId.equalsIgnoreCase("")){
				result.setIsSuccess(false);
				result.setRegisterReason("userId is null");
				return result;
			}
			
			Integer uid = null;
			
			try {
				uid = Integer.parseInt(userId);
			} catch (NumberFormatException e) {
				// TODO: handle exception
				result.setIsSuccess(false);
				result.setRegisterReason("userId invalid format");
				return result;
			}
			
			java.io.Serializable theUserId = uid;
			
			User user = userService.searchUnique("userId", theUserId);
			if(user == null){
				result.setIsSuccess(false);
				result.setRegisterReason("userId not found");
				return result;
			}
			
			//check the old password
			if(oldPassword == null || oldPassword.equalsIgnoreCase("")){
				result.setIsSuccess(false);
				result.setRegisterReason("old password cannot be empty");
				return result;
			}
			
			String oldPasswordHashed = StringUtil.hash(oldPassword);
			if(!oldPasswordHashed.equals(user.getPassword())){
				result.setIsSuccess(false);
				result.setRegisterReason("invalid old password");
				return result;
			}
			
			//check the new password
			if(newPassword == null || newPassword.equalsIgnoreCase("")){
				result.setIsSuccess(false);
				result.setRegisterReason("new password cannot be empty");
				return result;
			}
			
			//check the new password retype
			if(newPasswordRetype == null || newPasswordRetype.equalsIgnoreCase("")){
				result.setIsSuccess(false);
				result.setRegisterReason("retyped new password cannot be empty");
				return result;
			}
			
			//check if the new password and the retyped is equals 
			if(!newPasswordRetype.equals(newPassword)){
				result.setIsSuccess(false);
				result.setRegisterReason("new password mismatch, please retype");
				return result;
			}
			
			//done checking, let's change the password fufufu :D
			HttpServletRequest request =  XFireServletController.getRequest();
			HttpSession session = request.getSession(false);
			System.out.println("ipAddress : " + request.getRemoteAddr());
			if(session == null){
				session = request.getSession();
			}
			System.out.println("sessionId : " + session);
		
			ActionUser actionUser = null;
			
			User theUser = userService.getUser(user.getUsername());
			
			String ipAddress = request.getRemoteAddr();
			String sessionCode = request.getSession().getId();
			
			actionUser = new ActionUser();
			actionUser.setIpAddress(ipAddress);
			actionUser.setLoginSession(sessionCode);
			actionUser.setUser(theUser);
			actionUser.setActionURL(request.getRequestURL().toString());
			
			if(!userService.changePassword(user.getUserId(), oldPassword, newPassword, actionUser)){
				//failed changing password
				result.setIsSuccess(false);
				result.setRegisterReason("CHANGEPASSWORDFAILED");
				return result;
			}
			else{
				//success changing password, sent email to inform user for the changes
				//create the change password email
				Properties prop = System.getProperties();
				prop.setProperty("mail.smtp.host", "localhost");

				ApplicationEnvironment env = (ApplicationEnvironment) ApplicationContextUtils.getApplicationContext().getBean("applicationEnvironment", ApplicationEnvironment.class);
				System.out.println("Web Environment : " + env.getEnvironmentServer());
				System.out.println("Web Url : " + env.getEnvironmentUrl());

				String fromAddress=null;
				fromAddress= EMAIL_FROM_CHANGE;

				String toAddress = null;
				toAddress = user.getEmail();

				String emailSubject="[NO-REPLY] Mobile account change password";
				
				Member member = memberService.searchUnique("memberId", user.getMemberId().getMemberId());
				
				String emailBody="Kepada " + (member.getGender().equalsIgnoreCase("P") ? "Ibu":"Bapak") + " " + member.getFirstName() + ",";
				emailBody += "\nTerima kasih telah menggunakan fasilitas Owlexa Healthcare.";
				emailBody += "\n\nMelalui email ini kami ingin menginformasikan bahwa password akun aplikasi mobile Owlexa Healthcare telah berhasil diubah : ";
				emailBody += "\n\tLogin\t: " + member.getCurrentCardNumber() + " / " + user.getEmail() + " / " + user.getUsername();
				emailBody += "\n\tPassword\t: " + newPassword;

				emailBody += "\n\nSemoga informasi ini berguna untuk Anda.";
				emailBody += "\n\nHormat kami,";
				emailBody += "\nOwlexa Healthcare";

				Session emailSession = Session.getDefaultInstance(prop);
				MimeMessage emailMessage = new MimeMessage(emailSession);

				//set email sender
				if(fromAddress != null){
					emailMessage.setFrom(new InternetAddress(fromAddress));
				}

				//set email receiver
				if(toAddress != null){
					InternetAddress[] toEmailAddress = InternetAddress.parse(toAddress);
					emailMessage.addRecipients(Message.RecipientType.TO, toEmailAddress);
				}
				System.out.println("Sent change password email to : " + toAddress);

				//set email subject
				emailMessage.setSubject(emailSubject);

				//set email body
				BodyPart emailBodyPart = new MimeBodyPart();
				emailBodyPart.setText(emailBody);

				//construct the email
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(emailBodyPart);

				emailMessage.setContent(multipart);

				//sent it
				Transport.send(emailMessage);
				System.out.println("Sent reset password email success!!!");

				result.setRegisterReason("CHANGEPASSWORDSUCCESS");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	@Override
	public Collection<ActionResult> doMobileAnnouncementByDeviceId(
			String mobileDeviceId, String messageAnnouncement) {

		Collection<ActionResult> result = null;
		result = new Vector();
		Collection<User> theUser;
		/*
		Result sentAnnouncementResult = null;
		
		try {
			theUser = userService.search(null, null, "mobileDeviceId", mobileDeviceId, 0, 10);
			
			if(theUser != null){
				System.out.println("Found " + theUser.size() + " user with mobileDeviceId : " + mobileDeviceId);
				Iterator<User> userIterator = theUser.iterator();
				while(userIterator.hasNext()){
					User user = userIterator.next();
					
					Sender sender = new Sender(GOOGLE_SERVER_KEY);
					com.google.android.gcm.server.Message message = new com.google.android.gcm.server.Message.Builder().timeToLive(30)
							.delayWhileIdle(true).addData(MESSAGE_KEY, messageAnnouncement).build();
					
					sentAnnouncementResult = sender.send(message, mobileDeviceId, 1);
					System.out.println("Mobile Announcement Result : " + sentAnnouncementResult.toString());
					
					ActionResult actionResult = new ActionResult();
					actionResult.setResult(true);
					actionResult.setReason("Sent announcement to " + user.getUsername() + " with deviceId : " + user.getMobileDeviceId());
					System.out.println("Sent announcement to " + user.getUsername() + " with deviceId : " + user.getMobileDeviceId());
					
					result.add(actionResult);
				}
			}
			else{
				ActionResult res = new ActionResult();
				res.setResult(false);
				res.setReason("Can\'t find device with ID : " + mobileDeviceId);
				
				result.add(res);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		*/
		return result;
		
	}
	
}
