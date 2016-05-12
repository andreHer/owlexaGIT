package com.ametis.cms.util.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ametis.cms.datamodel.User;
import com.ametis.cms.util.WebUtil;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class SecurityFilterServlet implements Filter {

    private String excludePatterns;

    /**
     * Constructor of the object.
     */
    public SecurityFilterServlet() {
        super();
    }

    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        
        String contentType = request.getContentType();    
        if (contentType != null && contentType.startsWith("multipart/form-data")) {
        //    System.out.println("BY PASSING");            
            chain.doFilter(request, response);
        } else {
          
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;

            HttpSession session = req.getSession(false);
            
            boolean isAuthorized = true;
            boolean loginAction = false;

            User user = null;

            String requestedPath = ((HttpServletRequest) request).getServletPath();
            String navigation = WebUtil.getParameterString(req, "navigation", "");
            
//            System.out.println("[SecurityFilterServlet] ");
//            System.out.println("Session ID : " + (session != null ? session.getId().toString() : null));
//            System.out.println("Requested Path : " + requestedPath);

            if (session != null) {
                user = (User) session.getAttribute("theUser");

                if (user == null) {
                    isAuthorized = false;
                    if (navigation.equalsIgnoreCase("authorize") || navigation.equalsIgnoreCase("login")) {
                        loginAction = true;
                    }
                } else {
                }
            } else {

                isAuthorized = false;


                if (navigation.equalsIgnoreCase("authorize") || navigation.equalsIgnoreCase("login")) {
                    loginAction = true;
                }
            }

            if (requestedPath != null) {
                String toBeCompared = requestedPath + "?navigation=" + navigation;
                if (toBeCompared.equalsIgnoreCase("/member-form?navigation=register")) {
                    isAuthorized = true;
                }
                //Modified by aju on 20150326, we won't touch the navigation :D
                //Add by aju on 20150320, for client iFrame Support :D
                //if (toBeCompared.contains("navigation=iframe")) {
                if(req.getQueryString() != null){
                	//System.out.println("req query : " + req.getQueryString());
                    if (req.getQueryString().contains("iframe=yes")) {
                    	System.out.println("[SecurityFilterServlet] ");
                        System.out.println("Session ID : " + (session != null ? session.getId().toString() : null));
                        System.out.println("Requested Path : " + requestedPath);
                    	System.out.println("IFrame ByPass goes here :D");
                        isAuthorized = true;
                    }
                }
                //Add by aju on 20150724, for mobile application account activation :D
                if (toBeCompared.startsWith("/user?navigation=activatemobileaccount")) {
                    isAuthorized = true;
                }
                
              //Add by aju on 20150820, for provider list json
                if (toBeCompared.startsWith("/provider?navigation=getproviderlistjson")) {
                    isAuthorized = true;
                }
                
                if (toBeCompared.startsWith("/services")) {
                    isAuthorized = true;
                }
                if (toBeCompared.contains("wsdl")){
                	isAuthorized = true;
                }
                if (toBeCompared.startsWith("/ClaimWebService")){
                	isAuthorized = true;
                }
                if (toBeCompared.startsWith("/MemberWebService")){
                	isAuthorized = true;
                }
                if (toBeCompared.startsWith("/member?navigation=freeactivate")) {
                    isAuthorized = true;
                }
                if (toBeCompared.startsWith("/claim?navigation=testregister")) {
                    isAuthorized = true;
                }
                if (toBeCompared.startsWith("/member?navigation=testcheckin")) {
                    isAuthorized = true;
                }
                if (toBeCompared.startsWith("/sms")) {
                    isAuthorized = true;
                }
                if (toBeCompared.startsWith("/runreport")) {
                    isAuthorized = true;
                }

                if (toBeCompared.startsWith("/run")) {
                    isAuthorized = true;
                }
                if (toBeCompared.startsWith("/mobile")) {
                    isAuthorized = true;
                }
                if (toBeCompared.equalsIgnoreCase("/user?navigation=lostpassword")) {
                    isAuthorized = true;
                }

                if (toBeCompared.startsWith("/register-user")) {
                    isAuthorized = true;
                }
                if (toBeCompared.startsWith("/member-form")) {
                    isAuthorized = true;
                }
                if (toBeCompared.startsWith("/css/")) {
                    isAuthorized = true;
                    // CSS URL
                }
                if (toBeCompared.startsWith("/errorpage")) {
                    isAuthorized = true;
                }
                if (toBeCompared.startsWith("/include/pngbehavior.htc")) {
                    isAuthorized = true;
                }
                if (toBeCompared.startsWith("/member?navigation=registerResult")) {
                    isAuthorized = true;
                }
                if (toBeCompared.startsWith("/scripts/")) {
                    isAuthorized = true;

                    //Javascript URL
                }
                if (toBeCompared.startsWith("/images/")) {
                    isAuthorized = true;
                    // Images URL
                }
                if (toBeCompared.startsWith("/dwr")) {
                    isAuthorized = true;
                    // DWR AJAX URL
                }
                if (toBeCompared.startsWith("/services/")) {
                    isAuthorized = true;
                    // Web Service URL
                }
                if (toBeCompared.startsWith("/openflashchart/")) {
                    isAuthorized = true;
                }
                if (toBeCompared.startsWith("/dataimportstage-form")) {
                    isAuthorized = true;
                }
            }

            if (isAuthorized || loginAction) {
                chain.doFilter(request, response);

            } else {

                String contextPath = ((HttpServletRequest) request).getContextPath();
                resp.sendRedirect(contextPath + "/user?navigation=login");

                //System.out.println("CONTEXT PATH : " + contextPath);
                //chain.doFilter(request, response);

            }
        }


    }

//	public void forward(String page, ServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException 
//			{
//		
//		getServletContext().getRequestDispatcher(page).forward(request,
//				response);
//	}
    public void init(FilterConfig cfg) throws ServletException {
        // TODO Auto-generated method stub
        this.excludePatterns = cfg.getInitParameter("excludePatterns");
    }
    /**
     * Destruction of the servlet. <br>
     */
    /**
     * The doGet method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
}
