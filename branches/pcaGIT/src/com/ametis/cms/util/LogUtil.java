package com.ametis.cms.util;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ActivityLog;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.SecurityService;

public class LogUtil {

	public static void log (ActivityLogService logService, ActionUser user, String action,String desc){
		try {
			
			ActivityLog log = new ActivityLog();
			log.setAction(action);
			log.setDescription(desc);
			log.setActivityLogTime(new Timestamp(System.currentTimeMillis()));
			log.setSessionId(user.getLoginSession());
			log.setUsername(user.getUser().getUsername());
			log.setActionUrl(user.getActionURL());
			log.setActionQuery(user.getActionQuery());
			
			logService.create(log);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
