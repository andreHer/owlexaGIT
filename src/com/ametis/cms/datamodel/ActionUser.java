package com.ametis.cms.datamodel;

public class ActionUser {

	private String ipAddress;
	private String loginSession;
	private User user;
	private String actionURL;
	private String actionQuery;
	public ActionUser(){}
	
	
	public String getActionQuery() {
		return actionQuery;
	}


	public void setActionQuery(String actionQuery) {
		this.actionQuery = actionQuery;
	}


	public String getActionURL() {
		return actionURL;
	}


	public void setActionURL(String actionURL) {
		this.actionURL = actionURL;
	}


	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getLoginSession() {
		return loginSession;
	}
	public void setLoginSession(String loginSession) {
		this.loginSession = loginSession;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
