package com.ametis.cms.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtils implements ApplicationContextAware {

	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext appContext)
			throws BeansException {
		// TODO Auto-generated method stub
		ctx = appContext;
	}
	
	public static ApplicationContext getApplicationContext(){
		return ctx;
	}
	
}
