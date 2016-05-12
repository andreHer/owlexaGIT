package com.ametis.cms.util.auth;

import com.ametis.cms.datamodel.Action;

public class AuthUtil {
	
	public static Action generateAction (String actionCode, String actionName, 
                String url, String actionGroup){
		Action result = new Action();
		
		result.setActionCode(actionCode);
		result.setActionName(actionName);
		result.setActionUrl(url);
		result.setActionGroupCode(actionGroup);
		
		return result;
	}

}
