
package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Broker;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.Role;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.RoleService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.service.UserTypeService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.UserForm;


public class UserFormController extends SimpleFormController
{
	UserService userService ;
	ResourceBundleMessageSource alertProperties ;
	SecurityService securityService;
	
	// foreign affairs
	
	RoleService roleService;
	
	UserTypeService userTypeService;
	
	public UserTypeService getUserTypeService() {
		return userTypeService;
	}

	public void setUserTypeService(UserTypeService userTypeService) {
		this.userTypeService = userTypeService;
	}

	private ActivityLogService logService;

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
			
	public void setRoleService(RoleService obj){
		this.roleService = obj;
	}

	public RoleService getRoleService(){
		return this.roleService;
	}
	
	public void setUserService (UserService object){
	    this.userService = object;
	}
	public UserService getUserService (){
	    return this.userService;
	}
	
	
	public void setPropertiesUtil (ResourceBundleMessageSource object){
	    this.alertProperties = object;
	}
	public ResourceBundleMessageSource getPropertiesUtil (){
	    return this.alertProperties;
	}


	public UserFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		UserForm tmp = null;
		Integer userId = WebUtil.getParameterInteger (request,"userId");
		String navigation  = WebUtil.getParameterString(request, "navigation", "");
		
		request.setAttribute("navigation", navigation);
		
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		
		if (userId != null	) {
			
			java.io.Serializable pkey = userId;
			User object = userService.get (pkey );
//			Role object2 = roleService.get (pkey);
				
			if (object != null){ // edit object
				tmp = new UserForm(object);	
				tmp.setRoleId(object.getRoleId());
				tmp.setStatus(object.getStatus());	
				
//				UserType userType = object2.getUserTypeId(); 
//				
//				if (userType != null){
//					tmp.setUserType(userType.getUserTypeId().toString());
//				}
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new UserForm();
				
				Integer roleId = WebUtil.getParameterInteger (request,"roleId");
		
				if(roleId!=null){
					Role forClass = new Role();
					forClass.setRoleId(roleId);
					tmp.setRoleId(forClass);
	
					Role role = this.roleService.get(roleId);
					tmp.getUser().setRoleId(role);
					tmp.setStatus(new SubscriptionStatus());
				}else{
					Role forClass = new Role();
	//				tmp.setRoleId("");
					tmp.getUser().setRoleId(forClass);
					tmp.setStatus(new SubscriptionStatus());
				}

			}
				
			String breadcrumb = "<a href=\"user?navigation=detail&userId="+userId+"\" class=\"linkbreadcrumb\">Detail User</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update User";
			request.setAttribute("breadcrumb", breadcrumb);

		} // mau edit end
		else { // bikin baru
			tmp = new UserForm();
				Integer roleId = WebUtil.getParameterInteger (request,"roleId");
			if(roleId!=null){
				Role forClass = new Role();
				forClass.setRoleId(roleId);
				tmp.setRoleId(forClass);

				Role role = this.roleService.get(roleId);
				tmp.getUser().setRoleId(role);
				tmp.setStatus(new SubscriptionStatus());
			}else{
				Role forClass = new Role();
//				tmp.setRoleId("");
				tmp.getUser().setRoleId(forClass);
				tmp.setStatus(new SubscriptionStatus());
			}

			String breadcrumb = "<a href=\"user-form\" class=\"linkbreadcrumb\">Register User</a>";
			request.setAttribute("breadcrumb", breadcrumb);

		}
																																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		UserForm userForm = ( UserForm ) command;
		User user = userForm.getUser();

//		errors.setNestedPath("user");
//		getValidator().validate(user, errors);
//		errors.setNestedPath("");
		
		System.out.println (errors);
 	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		/**
			ini dipake buat populate data - data yang dibutuhkan
			contoh : Problem membutuhkan ProblemCategory
			nah fungsi method ini yaitu untuk populate list problem category ke jsp
			nanti biar bisa ditangkep sama jspnya

			contoh : Collection pc = pcontroller.searchPC();

			model.addObject("pcbeans", pc);

		*/
		
		//Collection<SubscriptionStatus> status = subsc
//		Collection role = roleService.getAll("roleName", true);
		
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();
		
		vEqP.add("deletedStatus");
		vEqQ.add(Integer.valueOf(0));

		String sEqP[] = new String[vEqP.size()];
		vEqP.toArray(sEqP);
		Object sEqQ[] = new Object[vEqP.size()];
		vEqQ.toArray(sEqQ);
		
		Collection role = roleService.search(null, null, sEqP, sEqQ, true, "roleName", 0, -1);
		Collection userType = userTypeService.search(null, null, sEqP, sEqQ, true, "userTypeName", 0, -1);
//		Collection currency = currencyService.getAll();
		

		model.put("roleBeans", role);
		model.put("userBeans", userType);
		

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		UserForm userForm = ( UserForm ) command;

		User res = null;
		String alertMsg="";
		String userId = "";
		int totalUser = 0;
		Vector vLikeP = new Vector();
		Vector vLikeQ = new Vector();
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();
		try {
		// foreign affairs
			
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			if (userForm.getUserType() != null){
				if (userForm.getUserType().equalsIgnoreCase("1")){
					MemberGroup group = new MemberGroup();
					group.setMemberGroupId(userForm.getInstitutionId());
					userForm.getUser().setMemberGroupId(group);
				}
				
				if (userForm.getUserType().equalsIgnoreCase("3")){
					Client client = new Client();
					client.setClientId(userForm.getInstitutionId());
					userForm.getUser().setClientId(client);
				}
				if (userForm.getUserType().equalsIgnoreCase("4")){
					Provider provider = new Provider();
					provider.setProviderId(userForm.getInstitutionId());
					userForm.getUser().setProviderId(provider);
				}
				if (userForm.getUserType().equalsIgnoreCase("5")){
					Broker broker = new Broker();
					broker.setBrokerId(userForm.getInstitutionId());
					userForm.getUser().setBrokerId(broker);
//					System.out.println("Sesuatu :"+userForm.getInstitutionId());
				}
				if (userForm.getUserType().equalsIgnoreCase("7")){
					Member member= new Member();
					member.setMemberId(userForm.getInstitutionId());
					userForm.getUser().setMemberId(member);
				}
			
			}
			
			

			if (userForm.isNewUser ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEUSER");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEUSER access");
				return errorResult;
				
			}
				//res = userService.create (userForm.getUser(),user);
				
//				User user2 = userForm.getUser();

				
				if (userForm.getUser().getUsername()!= null){
					vEqP.add("deletedStatus");
					vEqQ.add(Integer.valueOf(0));
					vEqP.add("username");
					vEqQ.add(userForm.getUser().getUsername());
					String sLikeP[] = new String[vLikeP.size()];
					vLikeP.toArray(sLikeP);
					Object sLikeQ[] = new Object[vLikeP.size()];
					vLikeQ.toArray(sLikeQ);
					
					String sEqP[] = new String[vEqP.size()];
					vEqP.toArray(sEqP);
					Object sEqQ[] = new Object[vEqP.size()];
					vEqQ.toArray(sEqQ);
					
					totalUser = userService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
//					System.out.println("total user "+ totalUser);
					if(totalUser==0){
						res = userService.create (userForm.getUser(),user);
						if (res!=null){
							userId = res.getUserId().toString();
							alertMsg = alertProperties.getMessage ("success.create.user",null,"success",Locale.getDefault());
						}
//						System.out.println("masuk ke if");
					}
					else{	
						User checkUser = userService.searchUnique(sEqP, sEqQ, 0, 1);
						request.setAttribute("usernameWarning", 
								"Username Already Used by \n"+checkUser.getUsername());
//						System.out.println("masuk ke else");
						return showForm(request, response, errors);
					}
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.user",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEUSER");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEUSER access");
					return errorResult;
					
				}
				
				String username = "";
				if(userForm.getUser().getUsername()!= null){
					vEqP.add("username");
			    	vEqQ.add(userForm.getUser().getUsername());
			    	vEqP.add("deletedStatus");
					vEqQ.add(new Integer(0));
					String sLikeP[] = new String[vLikeP.size()];
					vLikeP.toArray(sLikeP);
					Object sLikeQ[] = new Object[vLikeP.size()];
					vLikeQ.toArray(sLikeQ);
					
					String sEqP[] = new String[vEqP.size()];
				    vEqP.toArray(sEqP);
				    Object sEqQ[] = new Object[vEqP.size()];
				    vEqQ.toArray(sEqQ);
					try{
						username = userForm.getUser().getUsername();
//						System.out.println("user1 :"+ username);
						User checkUser = userService.get(userForm.getUser().getUserId());
//						System.out.println("user2 :"+  user.getUser().getUsername());
						totalUser = 0;
						if(checkUser == null)
						{
							res = userService.update(userForm
							.getUser(), user);
						}//true
						else if(checkUser.getUsername().equals(userForm.getUser().getUsername())){
							res = userService.update(userForm
									.getUser(), user);
//							System.out.println("checkUser : "+ checkUser.getUsername());
						}//true
						else{
							totalUser = userService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
//							System.out.println("checkUser2 : "+ checkUser.getUsername());
//							System.out.println("totalUser :"+ totalUser);
							if(totalUser <= 0){
								res = userService.update(userForm
										.getUser(), user);
							}else{
								String breadcrumb = "";
								String navigation = request.getParameter("navigation");
								userId = request.getParameter("userId");
								breadcrumb = "<a href=\"user-form?navigation=update&userId"+userId+"\" class=\"linkbreadcrumb\">Detail User</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update User";
								request.setAttribute("breadcrumb", breadcrumb);
								request.setAttribute("navigation", "update");
								
								checkUser = userService.searchUnique(sEqP, sEqQ, 0, 1);
								request.setAttribute("usernameWarning", 
										"Username Already Used by \n"+
												checkUser.getUsername());
								request.setAttribute("usernameWarning",  "Username Already Used");
								return showForm(request, response, errors);
								
								
							}
						}
					}catch (Exception ex) {
						ex.printStackTrace();
						User checkUser = userService.searchUnique(sEqP, sEqQ, 0, 1);
						request.setAttribute("usernameWarning", 
								"Username Already Used by \n"+
										checkUser.getUsername());
						request.setAttribute("usernameWarning", alertProperties.getMessage(
									"fail.create.user", null, "Username Already Used", Locale.getDefault()));
						return showForm(request, response, errors);
					}
				}else{
					res = userService.update(userForm
							.getUser(), user);
				}
//				res = userService.update (userForm.getUser(),user);

				if (res!=null){
					userId = res.getUserId().toString();
					alertMsg = alertProperties.getMessage ("success.update.user",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.user",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (userForm.isNewUser ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.user",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.user",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
		String breadcrumb = "<a href=\"user-form\" class=\"linkbreadcrumb\">Register User</a>";
		request.setAttribute("breadcrumb", breadcrumb);
		
		return new ModelAndView(new RedirectView("user?navigation=detail&userId="+userId+"&alert="+alertMsg));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Integer.class,true);
		binder.registerCustomEditor(Integer.class,num);
	}
// class+ 

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

// class- 

}
