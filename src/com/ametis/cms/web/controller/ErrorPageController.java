package com.ametis.cms.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ErrorPageController implements Controller{

	private ResourceBundleMessageSource alertProperties;
	
	public ResourceBundleMessageSource getAlertProperties() {
		return alertProperties;
	}

	public void setAlertProperties(ResourceBundleMessageSource alertProperties) {
		this.alertProperties = alertProperties;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String errorType = request.getParameter("errorType");
		String referalURL = request.getParameter("ref-url");
		
		ModelAndView result = null;
		if (errorType != null){
			if (errorType.equalsIgnoreCase("accessdenied")){
		
				result = new ModelAndView("accessDenied");
			}
			//Add by aju on 20150320, add error page :)
			else{
				result = new ModelAndView("error");
			}
		}
		// TODO Auto-generated method stub
		return result;
	}

	
}
