package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.Role;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.SecurityUtil;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * User is a servlet controller for user Table. All you have to do is to convert
 * necessary data field to the named parameter
 */
public class UserController implements Controller

// extends+

// extends-

{

	private UserService userService;

	private SecurityService securityService;

	private ConfigurationService configurationService;
	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;

private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setCountSet(Integer countSet) {
		this.countSet = countSet;
	}

	public Integer getCountSet() {
		return this.countSet;
	}

	public void setMaxPercountSet(Integer maxCount) {
		this.maxPercountSet = maxCount;
	}

	public Integer getMaxPercountSet() {
		return this.maxPercountSet;
	}

	public void setAlertProperties(ResourceBundleMessageSource alert) {
		this.alertProperties = alert;
	}

	public ResourceBundleMessageSource getAlertProperties() {
		return alertProperties;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return this.userService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer userId = WebUtil.getParameterInteger(request, "userId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = userId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEUSER");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEUSER access");
				return errorResult;
				
			}
			User res = userService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.user", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.user", null, "fail", Locale.getDefault()));

			}
			result = searchPerformed(request, response, "searchUser");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView detailPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DETAILUSER");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DETAILUSER access");
				return errorResult;
				
			}
			Integer userId = WebUtil.getParameterInteger(request, "userId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			result = new ModelAndView(location);
			java.io.Serializable pkey = userId;
			User object = userService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.user", null, "fail", Locale.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("user", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView updateStatusPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer userId = WebUtil.getParameterInteger(request, "userId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATESTATUSUSER");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATESTATUSUSER access");
				return errorResult;
				
			}
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			result = new ModelAndView(location);
			java.io.Serializable pkey = userId;

			if (navigation.equals("activate")) {
				userService.activate(userId, user);
			} else if (navigation.equals("block")) {
				userService.block(userId, user);
			}

			User object = userService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.user", null, "fail", Locale.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("user", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	/* Add by aju on 20150724, for mobile application :D */
	public ModelAndView updateStatusMobilePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer userId = WebUtil.getParameterInteger(request, "userId");
			
			//Add by aju on 20150724, read the token :D
			String mobileToken = WebUtil.getParameterString(request, "mobiletoken", "");
			if(mobileToken.length()>0)
			{
				System.out.println("MobileToken detected on request...");
				String decodedToken = SecurityUtil.saltDecrypt(mobileToken);
				System.out.println("Decoded Token : " + decodedToken);
				if(decodedToken!=mobileToken){
					String[] splitToken = decodedToken.split("&");
					if(splitToken.length>0 && splitToken.length<5){
						userId = Integer.parseInt(splitToken[0].replace("userId=", "") );
						System.out.println("splitDecode=>userId="+userId);
					}
					else{
						//jez make it go to error page if mobile token hacked/wrong :D
						System.out.println("Mobile user activation failed!");
						ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
						errorResult.addObject("errorType","activatemobileaccount");			
						errorResult.addObject("errorMessage", "Activation Token Unrecognized");
						return errorResult;
					}
				}
			}
			else{
				//jez make it go to error page if no mobile token :D	
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "Activation Token Unrecognized");
				return errorResult;
			}
			
			String navigation = WebUtil.getParameterString(request,	"navigation", "");
			
			ActionUser actionUser = null;
		
			User theUser = userService.getUser("admin");
			
			String ipAddress = request.getRemoteAddr();
			String sessionCode = request.getSession().getId();
			
			actionUser = new ActionUser();
			actionUser.setIpAddress(ipAddress);
			actionUser.setLoginSession(sessionCode);
			actionUser.setUser(theUser);
			actionUser.setActionURL(request.getRequestURL().toString());

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATESTATUSUSER");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for Activating User");
				return errorResult;
				
			}

			result = new ModelAndView(location);
			java.io.Serializable pkey = userId;

			if (navigation.equals("activatemobileaccount")) {
				System.out.println("Mobile Application -> Activating userId : " + userId);
				userService.activate(userId, user);
				System.out.println("Mobile Application -> Activating userId : " + userId + ", success!!");
			} else if (navigation.equals("blockmobileaccount")) {
				System.out.println("Mobile Application -> Deactivating userId : " + userId);
				userService.block(userId, user);
				System.out.println("Mobile Application -> Deactivating userId : " + userId + ", success!!");
			}

			User object = userService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.user", null, "fail", Locale.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("user", object);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			System.out.println("Mobile user activation failed!");
			ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
			errorResult.addObject("errorType","activatemobileaccount");			
			errorResult.addObject("errorMessage", "Activation Token Unrecognized");
			return errorResult;
		}

		return result;
	}
	
	public ModelAndView changePasswordPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer userId = WebUtil.getParameterInteger(request, "userId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CHANGEPASSWORDUSER");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CHANGEPASSWORDUSER access");
				return errorResult;
				
			}
		

			result = new ModelAndView(location);
			java.io.Serializable pkey = userId;

			if (navigation.equalsIgnoreCase("savechpass")) {

				String password = WebUtil.getParameterString(request,
						"password", "");
				
				String confirmPassword = WebUtil.getParameterString(request,
						"confirmPassword", "");

				if (password.equals(confirmPassword)) {
					boolean res = userService.changePassword(userId,
							password, user);

					result = detailPerformed(request, response, "detailUser");
					
					if (res) {
						result.addObject("alert", alertProperties.getMessage(
								"success.change.password", null, "success",
								request.getLocale()));
					} else {
						result.addObject("alert", alertProperties.getMessage(
								"fail.change.password", null, "fail", request
										.getLocale()));
					}
					request.setAttribute("userId", pkey);
					
					return result;
				} else {
					result.addObject("alert", alertProperties.getMessage(
							"mismatch.change.password", null, "fail", request
									.getLocale()));
				}

			} else if (navigation.equalsIgnoreCase("chpass")) {

				result.addObject("userId", userId);
			}

			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView changeMyPasswordPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer index = WebUtil.getParameterInteger(request, "index");
			
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CHANGEPASSWORDUSER");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CHANGEPASSWORDUSER access");
				return errorResult;
				
			}
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			result = new ModelAndView(location);
			

			if (navigation.equalsIgnoreCase("savemypass")) {

				String password = WebUtil.getParameterString(request,
						"password", "");
				String oldPassword = WebUtil.getParameterString(request,
						"oldPassword", "");
				String confirmPassword = WebUtil.getParameterString(request,
						"confirmPassword", "");

				if (password.equals(confirmPassword)) {
					boolean res = userService.changePassword(user.getUser().getUsername(),
							oldPassword, password, user);

					
					if (res) {
						result.addObject("alert", alertProperties.getMessage(
								"success.change.password", null, "success",
								request.getLocale()));
					} else {
						result.addObject("alert", alertProperties.getMessage(
								"fail.change.password", null, "fail", request
										.getLocale()));
					}
					//request.setAttribute("userId", user.getUser().getUserId());
					//result = detailPerformed(request, response, "detailUser");
					
					
					return result;
				} else {
					result.addObject("alert", alertProperties.getMessage(
							"mismatch.change.password", null, "fail", request
									.getLocale()));
				}

			} else if (navigation.equalsIgnoreCase("chmypass")) {

			}

			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView logoutPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
				
			User user= securityService.getCurrentUser(request); 
			
			HttpSession session = request.getSession(false);
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			result = new ModelAndView(location);
		

			if (user != null ){
				
				boolean res = userService.logout(user.getUsername(), session.getId());
				
				if (res){
					session.invalidate();
					session = null;
					result = new ModelAndView(new RedirectView("user?navigation=login"));
				}
			}
			else {
				session.invalidate();
				session = null;
				result = new ModelAndView(new RedirectView("user?navigation=login"));
			}
			
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

		

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	
	

	public ModelAndView loginPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		
		try {
			
			
			Integer userId = WebUtil.getParameterInteger(request, "userId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			String username = WebUtil.getParameterString(request, "username",
					"");
			String password = WebUtil.getParameterString(request, "pwd",
					"");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			result = new ModelAndView(location);
			java.io.Serializable pkey = userId;

			HttpSession session = request.getSession(false);
			String sessId = session == null ? "" : session.getId();

			if (session != null){
				Configuration config = (Configuration)session.getAttribute("systemConfig");
				if (config == null){
					config = configurationService.getSystemConfiguration();
					
					if (config != null){
						session.setAttribute("systemConfig", config);
					}
				}
			}
			
			if (navigation.equalsIgnoreCase("authorize")) {

				boolean res = userService.login(username, password, sessId,
						request.getRemoteAddr());

				if (res) {
					
					Vector vEqP = new Vector();
					Vector vEqQ = new Vector();
					
					vEqP.add("deletedStatus");
					vEqQ.add(Integer.valueOf(0));
					
					
					vEqP.add("username");
					vEqQ.add(username);

					String sEqP[] = new String[vEqP.size()];
					vEqP.toArray(sEqP);
					Object sEqQ[] = new Object[vEqP.size()];
					vEqQ.toArray(sEqQ);

					//modified by aju on 20151001, add link user -> client, user -> memberGroup, user -> member 
					String[] required = {
							"User.ProviderId","User.ProviderId.ProviderCategoryId",
							"User.ClientId",
							"User.MemberGroupId", "User.MemberGroupId.ClientId",
							"User.MemberId", "User.MemberId.ParentMember","User.MemberId.ParentMember.ClientId", "User.MemberId.ParentMember.MemberGroupId", 
							"User.MemberId.MemberGroupId", "User.MemberId.ClientId"							
					};
					
					User user = userService.getUserOnlyOne(username, sEqP, sEqQ,required);
					
					if (user != null){
						user.setPassword("");
					}
					
					session.setAttribute("theUser", user);
					
					//Add by aju on 20150413, remove all the session about iFrame on relogin :D
					if(session.getAttribute("iframe")!=null){
						session.removeAttribute("iframe");
						session.removeAttribute("levelLogin");
						session.removeAttribute("clientMemberId");
						session.removeAttribute("theMember");
						
					}
					
					
					if (user.getUserType().intValue() == User.TPA){
						if (user.getRoleId().getRoleId().intValue() == Role.CASE_MANAGEMENT_HEAD ||
								user.getRoleId().getRoleId().intValue() == Role.CASE_MANAGEMENT_STAFF	){
							result = new ModelAndView(new RedirectView("case"));
								
						}
						else if (user.getRoleId().getRoleId().intValue() == Role.CLAIM_HEAD || 
								user.getRoleId().getRoleId().intValue() == Role.CLAIM_STAFF){
							result = new ModelAndView(new RedirectView("claim"));
						}
						else if (user.getRoleId().getRoleId().intValue() == Role.FINANCE_HEAD ||
								user.getRoleId().getRoleId().intValue() == Role.FINANCE_STAFF){
							result = new ModelAndView(new RedirectView("payment"));
						}
						else if (user.getRoleId().getRoleId().intValue() == Role.EXCESS_HEAD ||
								user.getRoleId().getRoleId().intValue() == Role.EXCESS_STAFF){
							result = new ModelAndView(new RedirectView("excesscharge"));
						}
						else if (user.getRoleId().getRoleId().intValue() == Role.CUSTOMER_SERVICE){
							result = new ModelAndView(new RedirectView("member"));
						}
						else if (user.getRoleId().getRoleId().intValue() == Role.ADMINISTRATOR){
							result = new ModelAndView(new RedirectView("member"));
						}
						else if (user.getRoleId().getRoleId().intValue() == Role.ADMINISTRATION_STAFF){
							result = new ModelAndView(new RedirectView("batchclaim?status=1&navigation=gosearch"));
						}
						else if (user.getRoleId().getRoleId().intValue() == Role.CLAIM_CHECKER){
							result = new ModelAndView(new RedirectView("batchclaim?status=12&navigation=gosearch"));
						}
						else if (user.getRoleId().getRoleId().intValue() == Role.EDC_HELPDESK){
							result = new ModelAndView(new RedirectView("edctransactionlog"));
						}
						else if (user.getRoleId().getRoleId().intValue() == Role.MEMBERSHIP){
							result = new ModelAndView(new RedirectView("member"));
						}
					}
					else if (user.getUserType().intValue() == User.CLIENT){
						result = new ModelAndView(new RedirectView("dashboard?navigation=dashboard&tipe=claimgrowth"));
					}
					else if (user.getUserType().intValue() == User.MEMBER_GROUP){
						result = new ModelAndView(new RedirectView("claim"));
					}
					else if (user.getUserType().intValue() == User.PROVIDER){
						result = new ModelAndView(new RedirectView("batchclaim"));
					}
					else if (user.getUserType().intValue() == User.BROKER){
						result = new ModelAndView(new RedirectView("groupclaimutilreport"));
					}
					else if (user.getUserType().intValue() == User.MANAGEMENT){
						result = new ModelAndView(new RedirectView("groupclaimutilreport"));
					}
					else if (user.getUserType().intValue() == User.MEMBER){
						result = new ModelAndView(new RedirectView("claim"));
					}
						
				} else {
					result = new ModelAndView(new RedirectView(
							"user?navigation=login&alert=error"));
				///	result.addObject("alert", alertProperties.getMessage(
				//			"failed.login", null, "fail", request.getLocale()));
//					result.addObject("alert", "<b>Gagal melakukan proses login</b>");
//					request.setAttribute("alert","<b>Gagal melakukan proses login</b>");
					
					//Component frame = null;
					//JOptionPane.showMessageDialog(frame, "Gagal melakukan proses login, silahkan Anda masukkan Username / Password dengan benar");
					
//					request.setAttribute("alertLogin", "Gagal melakukan proses login, silahkan Anda masukkan Username / Password dengan benar");
//					System.out.println("masuk ke else");
				}
			} else if (navigation.equalsIgnoreCase("login")) {

				result.addObject("userId", userId);
			}

			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHUSER");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHUSER access");
				return errorResult;
				
			}
			
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;
			
			String arah = WebUtil.getParameterString(request, "arah", "");
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			boolean sortUserName = true, sortName = true, sortType = true, sortRole = true,
					sortEmail = true, sortStatus = true;
			boolean order = true;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			
			//CHECKING SORTING COLUMN
			if((!navigation.equalsIgnoreCase("gosearchbysort") && arah.isEmpty() && arah.equals("")) ||
					navigation.equals("gosearch")){
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}
			
			if (navigation.equals("gosearch") || navigation.equals("golookup") ||
					navigation.equals("gosearchbysort") || (navigation.equals("") && !arah.isEmpty())) {
				if (searchby != null && searchtext!=null && !searchtext.equals("")) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("username")) {
						vLikeP.add("username");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("firstName")) {
						vLikeP.add("firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("lastName")) {
						vLikeP.add("lastName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("email")) {
						vLikeP.add("email");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("telephone")) {
						vLikeP.add("telephone");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("mobilePhone")) {
						vLikeP.add("mobilePhone");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
				}
				if (searchStatus != null && searchStatus.intValue() > 0) {
					vEqP.add("status.statusId");
					vEqQ.add(searchStatus);
				}
			}

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = userService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			if (index == null)
				index = new Integer(1);

			if (arah.equals("kanan"))
				index = new Integer(index.intValue() + 1);
			else if (arah.equals("kiri"))
				index = new Integer(index.intValue() - 1);
			else if (arah.equals("kiribgt"))
				index = new Integer(1);
			else if (arah.equals("kananbgt"))
				index = new Integer(count / countSet.intValue() + 1);

			if (index.compareTo(new Integer(1)) == new Integer(-1).intValue())
				index = new Integer(1);
			else if (index.compareTo(new Integer(count / countSet.intValue()
					+ 1)) == new Integer(1).intValue())
				index = new Integer(count / countSet.intValue() + 1);

			rowsetint = (new Integer((index.intValue() - 1)
					* countSet.intValue())).intValue();
			if (count % countSet.intValue() > 0) {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
			// foreign affairs
			"User.RoleId",
			// foreign affairs end
			};
			
			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equalsIgnoreCase("username")){
						sortByColumn = "username";
						Boolean sortByOrderUserName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderUserName", ""));
						sortUserName = !sortByOrderUserName;
						order = sortUserName;
					}else if(sortcolumn.equalsIgnoreCase("user")){
						sortByColumn = "firstName";
						Boolean sortByOrderName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderName", ""));
						sortName = !sortByOrderName;
						order = sortName;
					}else if(sortcolumn.equalsIgnoreCase("usertype")){
						sortByColumn = "userType";
						Boolean sortByOrderType = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderType", ""));
						sortType = !sortByOrderType;
						order = sortType;
					}else if(sortcolumn.equalsIgnoreCase("userrole")){
						sortByColumn = "roleId.roleName";
						Boolean sortByOrderRole = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderRole", ""));
						sortRole = !sortByOrderRole;
						order = sortRole;
					}else if(sortcolumn.equalsIgnoreCase("email")){
						sortByColumn = "email";
						Boolean sortByOrderEmail = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderEmail", ""));
						sortEmail = !sortByOrderEmail;
						order = sortEmail;
					}else{
						sortByColumn = "status.status";
						Boolean sortByOrderStatus = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderStatus", ""));
						sortStatus = !sortByOrderStatus;
						order = sortStatus;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equalsIgnoreCase("username")){
						sortUserName = order;
					}else if(sortcolumn.equalsIgnoreCase("user")){
						sortName = order;
					}else if(sortcolumn.equalsIgnoreCase("usertype")){
						sortType = order;
					}else if(sortcolumn.equalsIgnoreCase("userrole")){
						sortRole = order;
					}else if(sortcolumn.equalsIgnoreCase("email")){
						sortEmail = order;
					}else{
						sortStatus = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				collection = userService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						order, sortByColumn, required, rowsetint, countSet.intValue());
			}else{
				collection = userService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
			}
			

			if (collection.size() <= 0) {
				index = new Integer(index.intValue() - 1);
				if (index.compareTo(new Integer(1)) == new Integer(-1)
						.intValue())
					index = new Integer(1);
				else if (index.compareTo(new Integer(count
						/ countSet.intValue() + 1)) == new Integer(1)
						.intValue())
					index = new Integer(count / countSet.intValue() + 1);

				rowsetint = (new Integer((index.intValue() - 1)
						* countSet.intValue())).intValue();
				if (count % countSet.intValue() > 0) {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue()));
				}
				collection = userService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("sortcolumn", sortcolumn);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Users", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			
			request.setAttribute("sortEmail", sortEmail);
			request.setAttribute("sortName", sortName);
			request.setAttribute("sortRole", sortRole);
			request.setAttribute("sortStatus", sortStatus);
			request.setAttribute("sortType", sortType);
			request.setAttribute("sortUserName", sortUserName);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	/**
	 * Action Service
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Get paramater navigation
		String navigation = request.getParameter("navigation") == null ? "welcome"
				: request.getParameter("navigation");

		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);

		String breadcrumb = "<a href=\"user\" class=\"linkbreadcrumb\">Search User</a>";
		
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				
				result = detailPerformed(request, response, "detailUser");
				String userId = request.getParameter("userId");
				
				breadcrumb = "<a href=\"user?navigation=detail&userId="+userId+"\" class=\"linkbreadcrumb\">Detail User</a>";
				
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("block")
					|| navigation.equalsIgnoreCase("activate")) {
				result = updateStatusPerformed(request, response, "detailUser");
				String userId = request.getParameter("userId");
				breadcrumb = "<a href=\"user?navigation=detail&userId="+userId+"\" class=\"linkbreadcrumb\">Detail User</a>";
			}
			/* Add by aju on 20150724, for mobile application :D */
			else if (navigation.equalsIgnoreCase("activatemobileaccount") 
					|| navigation.equalsIgnoreCase("blockmobileaccount")) {
				result = updateStatusMobilePerformed(request, response, "activationMobileUser");
			} 
			else if (navigation.equalsIgnoreCase("changePassword")
					|| navigation.equalsIgnoreCase("saveChangePassword")) {

				result = changePasswordPerformed(request, response,
						"changePassword");
				
				String userId = request.getParameter("userId");
				breadcrumb = "<a href=\"user?navigation=detail&userId="+userId+"\" class=\"linkbreadcrumb\">Detail User</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Change Password";
				
			} else if (navigation.equalsIgnoreCase("chpass")
					|| navigation.equalsIgnoreCase("savechpass")) {
				result = changePasswordPerformed(request, response,
						"changePass");

				String userId = request.getParameter("userId");
				breadcrumb = "<a href=\"user?navigation=detail&userId="+userId+"\" class=\"linkbreadcrumb\">Detail User</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Change Password";

			} 
			else if (navigation.equalsIgnoreCase("chmypass") ||navigation.equalsIgnoreCase("savemypass") ){
				result = changeMyPasswordPerformed(request, response, "changePassword");
				
			}
			else if (navigation.equalsIgnoreCase("login")
					|| navigation.equalsIgnoreCase("authorize")) {
				String alert = WebUtil.getParameterString(request, "alert", "");
				System.out.println("ALERT "+alert);
				result = loginPerformed(request, response,
						"login");
				if(alert.equals("error")){
					request.setAttribute("alertLogin", "Gagal melakukan proses login, silahkan Anda masukkan Username / Password dengan benar     ");
				}
				return result;

			} else if (navigation.equalsIgnoreCase("logout")) {
				result = logoutPerformed(request, response,
						"changePassword");

			}

			else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchUser");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupUser");
			} else {
				//result = searchPerformed(request, response, "searchUser");
				ActionUser theUser= securityService.getActionUser(request);
				System.out.println("NoNavigation->UserType(" + theUser.getUser().getUserType().intValue() + ")");
				if (theUser.getUser().getUserType().intValue() != User.TPA){
					result = new ModelAndView(new RedirectView("errorpage"));	
					result.addObject("errorType","NoNavigationAccessDenied");			
					result.addObject("errorMessage", "Sorry, you cannot access this section freely :)");
				}
				else{
					result = searchPerformed(request, response, "searchUser");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.addObject("breadcrumb", breadcrumb);
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	// class+

	// class-

}
