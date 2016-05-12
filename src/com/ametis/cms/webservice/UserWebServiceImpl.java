package com.ametis.cms.webservice;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.xfire.transport.http.XFireServletController;

import com.ametis.cms.service.UserService;

@WebService(name = "UserWebService", endpointInterface = "com.ametis.cms.webservice.IUserWebService",
		serviceName="UserWebService")
public class UserWebServiceImpl implements IUserWebService {

	private UserService userService;
	
	
	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public boolean login(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HttpServletRequest request =  XFireServletController.getRequest();
			HttpSession session = request.getSession(false);
			
			result = userService.login(username, password, session.getId(), request.getRemoteAddr());
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
