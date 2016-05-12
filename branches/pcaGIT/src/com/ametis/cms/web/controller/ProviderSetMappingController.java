
package com.ametis.cms.web.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import com.ametis.cms.util.*;
import com.ametis.cms.util.servlet.TableRenderingServlet;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.context.support.ResourceBundleMessageSource;
/*
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
*/
import com.ametis.cms.datamodel.*;
import com.ametis.cms.service.*;
import java.util.*;

// imports+ 

// imports- 

/**
 * ProviderSetMapping is a servlet controller for provider_set_mapping Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class ProviderSetMappingController implements Controller

// extends+ 

// extends- 
{
	
	private ProviderSetMappingService  providerSetMappingService;
	private SecurityService securityService;
	
	
	private UserService  actionuserService;
	
	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;

	private ProviderSetService providerSetService;
	
	
	public ProviderSetService getProviderSetService() {
		return providerSetService;
	}
	public void setProviderSetService(ProviderSetService providerSetService) {
		this.providerSetService = providerSetService;
	}
	
	public void setUserService(UserService userService) {
		this.actionuserService = userService;
	}
	public UserService getUserService() {
		return actionuserService;
	}
	
	public void setSecurityService (SecurityService securityService){
		this.securityService = securityService;
	}
	public SecurityService getSecurityService (){
		return this.securityService;
	}
	public void setCountSet (Integer countSet){
		this.countSet = countSet;
	}
	public Integer getCountSet (){
		return this.countSet;
	}
	public void setMaxPercountSet (Integer maxCount){
		this.maxPercountSet = maxCount;
	}
	public Integer getMaxPercountSet (){
		return this.maxPercountSet;
	}
	public void setAlertProperties (ResourceBundleMessageSource alert){
		this.alertProperties = alert;
	}
	public ResourceBundleMessageSource getAlertProperties (){
		return alertProperties;
	}
	public void setProviderSetMappingService	(ProviderSetMappingService providerSetMappingService){
	this.providerSetMappingService = providerSetMappingService;
	}
	public ProviderSetMappingService getProviderSetMappingService (){
	return this.providerSetMappingService;
	}
	public ModelAndView deletePerformed (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView result = null;

 	    try {
									Integer providerSetMappingId = WebUtil.getParameterInteger (request,"providerSetMappingId");
						
			/*
			Pkey ini merupakan representasi dari primary key, misalkan
			hasil get dari attribute diatas ^ itu cuma ada 1 key saja
			berarti tinggal pkey = <nama var diatas>
			tapi kalau misalkan composite primary key berarti
			tinggal bikin representasi primary key nya saja .
			*/
						java.io.Serializable pkey = providerSetMappingId;
						
			ActionUser user = securityService.getActionUser(request);
		
			ProviderSetMapping res = providerSetMappingService.delete (pkey,user);

			if (res!=null){
				request.setAttribute ("alert", alertProperties.getMessage ("success.delete.providersetmapping",null,"success",Locale.getDefault()));
			}
			else {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.delete.providersetmapping",null,"fail",Locale.getDefault()));

			}
			result = searchPerformed (request, response, "searchProviderSetMapping");
		}
		catch (Exception e){
		// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
		 e.printStackTrace();
		request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));
		result = new ModelAndView ("error");
		}	    return result;
	}

	public ModelAndView detailPerformed (HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
	    ModelAndView result = null;

	    try {
									Integer providerSetMappingId = WebUtil.getParameterInteger (request,"providerSetMappingId");
									
			Integer index = WebUtil.getParameterInteger(request,"index");
			String searchtext = WebUtil.getParameterString(request,"searchtext","");
			String searchby = WebUtil.getParameterString(request,"searchby","");
			/*
				Pkey ini merupakan representasi dari primary key, misalkan
				hasil get dari attribute diatas ^ itu cuma ada 1 key saja
				berarti tinggal pkey = <nama var diatas>
				tapi kalau misalkan composite primary key berarti
				tinggal bikin representasi primary key nya saja .
			*/

			result = new ModelAndView (location);
						java.io.Serializable pkey = providerSetMappingId;
				        ProviderSetMapping object = providerSetMappingService.get (pkey);

			if (object == null){
				request.setAttribute ("alert", alertProperties.getMessage ("not.found.providersetmapping",null,"fail",Locale.getDefault()));
			}
			/*
			convention .. kalo mau nampilkan sebuah object
			pake BeanClass aja ya .. ga pake 's'
			-adhit
			*/

		    result.addObject ("providerSetMapping", object);
			result.addObject("index",index);
		    result.addObject("searchtext",searchtext);
		    result.addObject("searchby",searchby);

		}
		catch (Exception e){
		 e.printStackTrace();
		 request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));

         result = new ModelAndView ("error");
		}

	    return result;
	}



	public ModelAndView searchPerformed (HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
	    ModelAndView result = null;
		try {
		    result = new ModelAndView (location);


			String rowset = WebUtil.getParameterString (request,"rowset","0");


		    Integer providerSetId = WebUtil.getParameterInteger(request, "providerSetId");
		    Integer providerSetMappingId = WebUtil.getParameterInteger(request, "providerSetMappingId");
		    Integer index = WebUtil.getParameterInteger (request, "index");

		    String navigation = WebUtil.getParameterString (request,"navigation","");
		    String searchtext = WebUtil.getParameterString (request,"searchtext","");
		    String searchby = WebUtil.getParameterString (request, "searchby","");
			String sortby = WebUtil.getParameterString (request, "sortby", "");
			
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

		    Collection collection = null;

		    int rowsetint = 0;
		    int count = 0;


		    if (StringUtils.isNumeric(rowset)) {
		      rowsetint = Integer.parseInt(rowset);
		    }
		    Vector vLikeP = new Vector();
		    Vector vLikeQ = new Vector();
		    Vector vEqP = new Vector();
		    Vector vEqQ = new Vector();
		    if (navigation.equals("gosearch") || navigation.equals("golookup")){

  		        if (searchby != null){
			    /**
			    * ini querynya disesuaikan dengan apa yang mau di search
			    * default nya gue bikin template search by semua field yang tipenya 'String'
			    * -adhit
			    */

			    							    							    							    								if (searchby.equalsIgnoreCase("providerName")){
					vLikeP.add("providerName");
					vLikeQ.add(searchtext);
				}
							    								if (searchby.equalsIgnoreCase("providerCode")){
					vLikeP.add("providerCode");
					vLikeQ.add(searchtext);
				}
							    								if (searchby.equalsIgnoreCase("city")){
					vLikeP.add("city");
					vLikeQ.add(searchtext);
				}
							    								if (searchby.equalsIgnoreCase("province")){
					vLikeP.add("province");
					vLikeQ.add(searchtext);
				}
							    								if (searchby.equalsIgnoreCase("address")){
					vLikeP.add("address");
					vLikeQ.add(searchtext);
				}
							    								if (searchby.equalsIgnoreCase("countery")){
					vLikeP.add("countery");
					vLikeQ.add(searchtext);
				}
							    							    							    								if (searchby.equalsIgnoreCase("createdBy")){
					vLikeP.add("createdBy");
					vLikeQ.add(searchtext);
				}
							    							    								if (searchby.equalsIgnoreCase("modifiedBy")){
					vLikeP.add("modifiedBy");
					vLikeQ.add(searchtext);
				}
							    							    								if (searchby.equalsIgnoreCase("deletedBy")){
					vLikeP.add("deletedBy");
					vLikeQ.add(searchtext);
				}
							    							    			}

		    }

		    vEqP.add("deletedStatus");
		    vEqQ.add(new Integer(0));


		    vEqP.add("providerSetId.providerSetId");
		    vEqQ.add(providerSetId);
		    
		    String sLikeP[] = new String[vLikeP.size()];
		    vLikeP.toArray(sLikeP);
		    Object sLikeQ[] = new Object[vLikeP.size()];
		    vLikeQ.toArray(sLikeQ);

		    String sEqP[] = new String[vEqP.size()];
		    vEqP.toArray(sEqP);
		    Object sEqQ[] = new Object[vEqP.size()];
		    vEqQ.toArray(sEqQ);

			count = providerSetMappingService.getTotal (sLikeP,sLikeQ,sEqP,sEqQ);

			String arah = WebUtil.getParameterString(request, "arah","");

			if(index==null)
				index = new Integer(1);

			if(arah.equals("kanan"))
				index = new Integer(index.intValue()+1);
			else if(arah.equals("kiri"))
				index = new Integer(index.intValue()-1);
			else if(arah.equals("kiribgt"))
				index=new Integer(1);
			else if(arah.equals("kananbgt"))
				index = new Integer(count/countSet.intValue()+1);

			if(index.compareTo(new Integer(1))==new Integer(-1).intValue())
				index = new Integer(1);
			else if(index.compareTo(new Integer(count/countSet.intValue()+1))==new Integer(1).intValue())
				index = new Integer(count/countSet.intValue()+1);

			rowsetint = (new Integer((index.intValue()-1)*countSet.intValue())).intValue();
			if(count%countSet.intValue()>0){
				result.addObject("halAkhir", new Integer(count
					/ countSet.intValue() + 1));
			}else{
				result.addObject("halAkhir", new Integer(count
					/ countSet.intValue() ));
			}
			
			minIndex = (index - 1) * countSet;
			maxIndex = index  * countSet;

			if (maxIndex > count){
				maxIndex = count;
			}

	String required[] = new String[]{
	// foreign affairs
				"ProviderSetMapping.ProviderSetId",
		// foreign affairs end
	};
		    collection = providerSetMappingService.search (sLikeP,sLikeQ,sEqP,sEqQ,
			required,rowsetint,countSet.intValue());

		    if(collection.size()<=0){
		    	index = new Integer(index.intValue()-1);
		    	if(index.compareTo(new Integer(1))==new Integer(-1).intValue())
					index = new Integer(1);
				else if(index.compareTo(new Integer(count/countSet.intValue()+1))==new Integer(1).intValue())
					index = new Integer(count/countSet.intValue()+1);

				rowsetint = (new Integer((index.intValue()-1)*countSet.intValue())).intValue();
				if(count%countSet.intValue()>0){
					result.addObject("halAkhir", new Integer(count
						/ countSet.intValue() + 1));
				}else{
					result.addObject("halAkhir", new Integer(count
						/ countSet.intValue() ));
				}
				collection = providerSetMappingService.search (sLikeP,sLikeQ,sEqP,sEqQ,
					required,rowsetint,countSet.intValue());
		    }
			
		    request.setAttribute("searchtext", searchtext);
		    request.setAttribute("searchby", searchby);
		    request.setAttribute("navigation", navigation);
			
		    /*
		    ini gue generate juga dari nama tablenya
		    convention --> collection = nama bean + 's'
		    -adhit
		    */
		    
		    ProviderSet providerSetObject = null;
			
			if(providerSetId != null){
				try
				{
				java.io.Serializable providerSetkey = providerSetId;
				System.out.println("provider set id : "+providerSetId);
				String[] providerSetRequired = {"ProviderSet.MemberGroupId", "ProviderSet.ClientId"};
				providerSetObject = providerSetService.get(providerSetkey, providerSetRequired);
				
				}
				catch (Exception ex)
				{
					//System.out.println("claim object : "+claimObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			ProviderSetMapping providerSetMappingObject = null;
			
			if(providerSetMappingId != null){
				try
				{
				java.io.Serializable providerSetMappingkey = providerSetMappingId;
				System.out.println("provider set mapping id : "+providerSetMappingId);
				String[] providerSetMappingRequired = {"ProviderSetMapping.ProviderSetId"};
				providerSetMappingObject = providerSetMappingService.get(providerSetMappingkey, providerSetMappingRequired);
				
				}
				catch (Exception ex)
				{
					//System.out.println("claim object : "+claimObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

		    result.addObject("ProviderSetMappings",collection);
		    result.addObject("providerSet", providerSetObject);
		    result.addObject("providerSetMapping",providerSetMappingObject);

		    request.setAttribute("providerSetId", providerSetId);
			request.setAttribute("countSet", countSet);
		    request.setAttribute("index", new Integer(index));
		    request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer (minIndex));
			request.setAttribute("maxIndex", new Integer (maxIndex));

		}
		catch (Exception e){
			e.printStackTrace();
	    		request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));

		    result = new ModelAndView ("error");
		}

		return result;
	}




  /**
   * Action Service
   */
  public ModelAndView handleRequest(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {

    //Get paramater navigation
    String navigation = request.getParameter("navigation") == null
        ? "welcome"
        : request.getParameter("navigation");
    

    Object user = null;

	ModelAndView result = null;
    HttpSession session = request.getSession(false);

/*
    if (session == null) {

      request.setAttribute("alert", "<b>" + alertProperties.getValue("not.login") + "</b>");
      request.setAttribute("content", "/jsp/login.jsp");
      forward("/main.jsp", request, response);


	  result = new ModelAndView ("login");
    }
    else {

    }
*/
    String breadcrumb = "";
	try {
	    if (navigation.equalsIgnoreCase ("detail")){
	    /*
	    	disesuaikan dengan halaman targetnya nih
	    */
	    	result = detailPerformed (request, response, "detailProviderSetMapping" );
	    }
	    else if (navigation.equalsIgnoreCase ("delete")){
	    	result = deletePerformed (request, response );
	    }
	    else if (navigation.equalsIgnoreCase ("search") || navigation.equals("gosearch")){
	    	result = searchPerformed (request, response, "searchProviderSetMapping" );
	    	String providerSetId = request.getParameter("providerSetId");
	    	breadcrumb = "<a href=\"providerset?navigation=detail&index=1&providerSetId="+providerSetId+"\" class=\"linkbreadcrumb\">Detail Provider Profile</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider";
	    }
	    else if (navigation.equalsIgnoreCase ("lookup") || navigation.equals("golookup")){
	    	result = searchPerformed (request, response, "lookupProviderSetMapping" );
	    }
	    else {
	    	result = searchPerformed (request, response, "searchProviderSetMapping" );
	    	String providerSetId = request.getParameter("providerSetId");
	    	breadcrumb = "<a href=\"providerset?navigation=detail&index=1&providerSetId="+providerSetId+"\" class=\"linkbreadcrumb\">Detail Provider Profile</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider";
	    }
    }
    catch (Exception e){
    	e.printStackTrace();
    }
	result.addObject("breadcrumb", breadcrumb);
	Map map = TableRenderingServlet.seti18N(request,response);
	result.addAllObjects(map);
    return result;
  }


// class+ 

// class- 
}
