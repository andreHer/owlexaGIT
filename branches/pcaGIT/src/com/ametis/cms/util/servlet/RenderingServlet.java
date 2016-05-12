
package com.ametis.cms.util.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ametis.cms.service.RoleService;
import com.ametis.cms.service.UserService;

public class RenderingServlet implements Controller {
	private RoleService roleService;
	private UserService userService;



	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ModelAndView actionPerformed(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		String path = request.getContextPath();
		String servlet = request.getServletPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+
			""+servlet;
		try {
			String str = (basePath+" user and role"+userService.getTotal()+" "+roleService.getTotal());
//			ModelAndView view = new ModelAndView("../template/directory");
			ModelAndView view = new ModelAndView("content");
			view.addObject("basePath",str);
			return view;
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}

	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView view = actionPerformed(request,response);
		return view;
	}
}
