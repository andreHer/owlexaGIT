package com.ametis.cms.service;

import javax.servlet.http.HttpServletRequest;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.User;

public interface SecurityService {
	public boolean isUserAuthorized (ActionUser user, String actionCode);
	public User getCurrentUser (HttpServletRequest request);
	public ActionUser getActionUser (HttpServletRequest request);
	public boolean isUsingIFrameSession(HttpServletRequest request);
	public String getTheSessionMemberId();
	public String getTheSessionMemberParentId();
	public String getTheIFrameLevelLogin();
	public boolean isRequestAllowed(HttpServletRequest request);
}
