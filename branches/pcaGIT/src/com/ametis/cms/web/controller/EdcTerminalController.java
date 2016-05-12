
package com.ametis.cms.web.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.EdcTerminal;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.EdcTerminalService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.CaseReportGenerator;
import com.ametis.cms.util.EdcTerminalReportDownloader;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 


/**
 * EdcTerminal is a servlet controller for edc_terminal Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class EdcTerminalController implements Controller

// extends+ 

// extends- 

{
	
	private EdcTerminalService  edcTerminalService;
	private SecurityService securityService;
	private ProviderService providerService;
	
	
	private UserService  actionuserService;
	
	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;

	private ConfigurationService  configurationService;
	
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
	public void setEdcTerminalService	(EdcTerminalService edcTerminalService){
	this.edcTerminalService = edcTerminalService;
	}
	public EdcTerminalService getEdcTerminalService (){
	return this.edcTerminalService;
	}
	
	public ProviderService getProviderService() {
		return providerService;
	}
	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	public ModelAndView deletePerformed (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView result = null;

 	    try {
			
 	    	Integer id = WebUtil.getParameterInteger (request,"id");
				
			java.io.Serializable pkey = id;
						
			ActionUser user = securityService.getActionUser(request);
		
			EdcTerminal res = edcTerminalService.delete (pkey,user);

			if (res!=null){
				request.setAttribute ("alert", alertProperties.getMessage ("success.delete.edcterminal",null,"success",Locale.getDefault()));
			}
			else {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.delete.edcterminal",null,"fail",Locale.getDefault()));

			}
			result = new ModelAndView(new RedirectView("edcterminal?navigation=listprovider&providerId="+res.getProviderId().getProviderId()));
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
									Integer id = WebUtil.getParameterInteger (request,"id");
									
			Integer index = WebUtil.getParameterInteger(request,"index");
			String searchtext = WebUtil.getParameterString(request,"searchtext","");
			String searchby = WebUtil.getParameterString(request,"searchby","");
			String listnavigation = WebUtil.getParameterString(request,"listnavigation","");
			/*
				Pkey ini merupakan representasi dari primary key, misalkan
				hasil get dari attribute diatas ^ itu cuma ada 1 key saja
				berarti tinggal pkey = <nama var diatas>
				tapi kalau misalkan composite primary key berarti
				tinggal bikin representasi primary key nya saja .
			*/

			result = new ModelAndView (location);
						java.io.Serializable pkey = id;
				        EdcTerminal object = edcTerminalService.get (pkey);

			if (object == null){
				request.setAttribute ("alert", alertProperties.getMessage ("not.found.edcterminal",null,"fail",Locale.getDefault()));
			}
			/*
			convention .. kalo mau nampilkan sebuah object
			pake BeanClass aja ya .. ga pake 's'
			-adhit
			*/

		    result.addObject ("edcTerminal", object);
			result.addObject("index",index);
		    result.addObject("searchtext",searchtext);
		    result.addObject("searchby",searchby);
		    result.addObject("listnavigation",listnavigation);

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

		    Integer index = WebUtil.getParameterInteger (request, "index");

		    String navigation = WebUtil.getParameterString (request,"navigation","");
		    String searchtext = WebUtil.getParameterString (request,"searchtext","");
		    String searchby = WebUtil.getParameterString (request, "searchby","");
			String sortby = WebUtil.getParameterString (request, "sortby", "");
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			String listnavigation = WebUtil.getParameterString (request,"listnavigation","");
			
			//Provider provider = providerService.get(providerId);
			
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
  		        	if (searchby.equalsIgnoreCase("location")){
						vLikeP.add("location");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")){
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("deviceNumber")){
						vLikeP.add("deviceNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("authorizationKey")){
						vLikeP.add("authorizationKey");
						vLikeQ.add(searchtext);
					}
				}
		    }

		    if (providerId != null){
		    	vEqP.add("providerId.providerId");
		    	vEqQ.add(providerId);
		    }
		    vEqP.add("deletedStatus");
		    vEqQ.add(new Integer(0));

		    String sLikeP[] = new String[vLikeP.size()];
		    vLikeP.toArray(sLikeP);
		    Object sLikeQ[] = new Object[vLikeP.size()];
		    vLikeQ.toArray(sLikeQ);

		    String sEqP[] = new String[vEqP.size()];
		    vEqP.toArray(sEqP);
		    Object sEqQ[] = new Object[vEqP.size()];
		    vEqQ.toArray(sEqQ);

			count = edcTerminalService.getTotal (sLikeP,sLikeQ,sEqP,sEqQ);

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
		// foreign affairs end
	};
		    collection = edcTerminalService.search (sLikeP,sLikeQ,sEqP,sEqQ,
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
				collection = edcTerminalService.search (sLikeP,sLikeQ,sEqP,sEqQ,
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
		    
		    Provider providerObject = null;
			
			if(providerId != null){
				try
				{
				java.io.Serializable providerkey = providerId;
				System.out.println("provider id : "+providerId);
				String[] providerRequired = {"Provider.StatusId", "Provider.ProviderCategoryId", "Provider.ProviderGroupId","Provider.ProviderSpecId", "Provider.ProviderCurrencyId"};
				providerObject = providerService.get(providerkey, providerRequired);
				
				}
				catch (Exception ex)
				{
					//System.out.println("claim object : "+claimObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			//Add 20150821 by FVO, get Provider EDC Alert Default
			Configuration conf = configurationService.get(0);
			request.setAttribute("alertEdcDefault", conf.getEdcAlertGlobal());

		    result.addObject("EdcTerminals",collection);
		    result.addObject("provider", providerObject);
		    result.addObject("listnavigation",listnavigation);
		    
			request.setAttribute("countSet", countSet);
		    request.setAttribute("index", new Integer(index));
		    request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer (minIndex));
			request.setAttribute("maxIndex", new Integer (maxIndex));
			request.setAttribute("providerId", providerId);

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
    
    String listnavigation = request.getParameter("listnavigation") == null
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

	String providerId = request.getParameter("providerId");
	String breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider</a>";
	
	try {
	    if (navigation.equalsIgnoreCase ("detail")){
	    
	    	result = detailPerformed (request, response, "detailEdcTerminal" );
	    }
	    else if (navigation.equalsIgnoreCase ("delete")){
	    	result = deletePerformed (request, response );
	    }
	    else if (navigation.equalsIgnoreCase ("search") || navigation.equals("gosearch")){
	    	result = searchPerformed (request, response, "searchEdcTerminal" );
	    }
	    else if (navigation.equalsIgnoreCase ("lookup") || navigation.equals("golookup")){
	    	result = searchPerformed (request, response, "lookupEdcTerminal" );
	    }
	    else if (navigation.equalsIgnoreCase ("listprovider")){
	    	result = searchPerformed (request, response, "searchEdcTerminal" );
	    	if(listnavigation.equalsIgnoreCase("searchcapitation"))
			{
	    		breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"&listnavigation="+listnavigation+"\" class=\"linkbreadcrumb\">Detail Provider</a>  &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider EDC Terminal";
			}
	    	else
	    	{
	    		breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider</a>  &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider EDC Terminal";
	    	}
	    }
	    else if(navigation.equalsIgnoreCase("edcreport") || navigation.equalsIgnoreCase("golistprovideredcreport") || navigation.equals("downloadExcelProviderEDCReport")){
	    	result = searchProviderEDCPerformed(request, response, "searchProviderEDCReport");
	    	breadcrumb += "  &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Report EDC Terminal";
	    }
	    else if(navigation.equalsIgnoreCase("jsontotaledcalert")){
	    	result = jsonEdcTerminalPerformed(request, response);
	    }
	    else {
	    	result = searchPerformed (request, response, "searchEdcTerminal" );
	    	breadcrumb += "  &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List EDC Terminal";
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

  public ModelAndView searchProviderEDCPerformed (HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
	    ModelAndView result = null;
		try {
		    result = new ModelAndView (location);


			String rowset = WebUtil.getParameterString (request,"rowset","0");

		    Integer index = WebUtil.getParameterInteger (request, "index");

		    String navigation = WebUtil.getParameterString (request,"navigation","");
		    String searchtext = WebUtil.getParameterString (request,"searchtext","");
		    String searchby = WebUtil.getParameterString (request, "searchby","");
			String sortby = WebUtil.getParameterString (request, "sortby", "");
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			String listnavigation = WebUtil.getParameterString (request,"listnavigation","");
			String searchStatus = WebUtil.getParameterString(request,"status","");
			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			//String monthStatus = WebUtil.getParameterString(request,"monthStatus","");
			String dayStatus = WebUtil.getParameterString(request,"dayStatus","");
			
			//Provider provider = providerService.get(providerId);
			System.out.println("NAVI : "+navigation);
			System.out.println("Status : "+searchStatus);
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
		        	if (searchby.equalsIgnoreCase("location")){
						vLikeP.add("location");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")){
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("deviceNumber")){
						vLikeP.add("deviceNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("authorizationKey")){
						vLikeP.add("authorizationKey");
						vLikeQ.add(searchtext);
					}
				}
		    }

		    if (providerId != null){
		    	vEqP.add("providerId.providerId");
		    	vEqQ.add(providerId);
		    }
		    vEqP.add("deletedStatus");
		    vEqQ.add(new Integer(0));
		    
		    Object fault[] = {Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4)};
		    if (searchStatus != null && !searchStatus.equalsIgnoreCase("") ) {
		    	if(searchStatus.equals("1")||searchStatus.equals("2")||searchStatus.equals("3")||searchStatus.equals("4")){
		    		fault = new Object[1];
		    		fault[0] = Integer.valueOf(searchStatus);
		    		System.out.println("STATUS FAULT : "+fault[0]);
		    	}
		    }

		    String sLikeP[] = new String[vLikeP.size()];
		    vLikeP.toArray(sLikeP);
		    Object sLikeQ[] = new Object[vLikeP.size()];
		    vLikeQ.toArray(sLikeQ);

		    String sEqP[] = new String[vEqP.size()];
		    vEqP.toArray(sEqP);
		    Object sEqQ[] = new Object[vEqP.size()];
		    vEqQ.toArray(sEqQ);

		    /*if (minDate != null
					&& !minDate.toString().equalsIgnoreCase("1970-01-01")) {

				String lsthColumn ="lastTransaction";
				Object minColumn = minDate;
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -1);
				java.util.Date dateRes = cal.getTime();
				System.out.println("DATE RES : "+dateRes);
				minColumn = dateRes;
				count = edcTerminalService.getTotalProviderEDCFault(sLikeP, sLikeQ, sEqP, sEqQ,
						lsthColumn, minColumn, fault);
			}else{
				count = edcTerminalService.getTotalProviderEDCFault(sLikeP, sLikeQ, sEqP, sEqQ, fault);
			}*/
		    System.out.println("DAYSTASTUS : "+dayStatus);
		    if (dayStatus!=null && !dayStatus.equals("")) {

				String lsthColumn ="lastTransaction";
				Object minColumn = minDate;
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, Integer.valueOf(dayStatus));
				java.util.Date dateRes = cal.getTime();
				System.out.println("DATE RES : "+dateRes);
				minColumn = dateRes;
				count = edcTerminalService.getTotalProviderEDCFault(sLikeP, sLikeQ, sEqP, sEqQ,
						lsthColumn, minColumn, fault);
			}else{
				count = edcTerminalService.getTotalProviderEDCFault(sLikeP, sLikeQ, sEqP, sEqQ, fault);
			}

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
				// foreign affairs end
			};
			
			/*if (minDate != null
					&& !minDate.toString().equalsIgnoreCase("1970-01-01")) {

				String lsthColumn ="lastTransaction";
				Object minColumn = minDate;
				
				 collection = edcTerminalService.searchProviderEDCFault(sLikeP, sLikeQ, sEqP, sEqQ, lsthColumn, minColumn, fault, false, "id",
							required, rowsetint, countSet.intValue());
			}else{
				 collection = edcTerminalService.searchProviderEDCFault(sLikeP, sLikeQ, sEqP, sEqQ, fault, false, "id",
							required, rowsetint, countSet.intValue());
			}*/
			
			if(dayStatus!=null && !dayStatus.equals("")) {

				//String lsthColumn ="lastTransaction";
				//Object minColumn = minDate;
				
				String lsthColumn ="lastTransaction";
				Object minColumn = minDate;
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, Integer.valueOf(dayStatus));
				java.util.Date dateRes = cal.getTime();
				System.out.println("DATE RES : "+dateRes);
				minColumn = dateRes;
				
				collection = edcTerminalService.searchProviderEDCFault(sLikeP, sLikeQ, sEqP, sEqQ, lsthColumn, minColumn, fault, false, "id",
							required, rowsetint, countSet.intValue());
			}else{
				 collection = edcTerminalService.searchProviderEDCFault(sLikeP, sLikeQ, sEqP, sEqQ, fault, false, "id",
							required, rowsetint, countSet.intValue());
			}
		   
		   

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
				//collection = edcTerminalService.searchProviderEDCFault(sLikeP, sLikeQ, sEqP, sEqQ, fault, false, "id",
				//		required, rowsetint, countSet.intValue());
		    }
		    
		    String minimumDate = "";

			if (minDate != null
					&& !minDate.toString().equalsIgnoreCase("1970-01-01")) {
				minimumDate = minDate.toString();
			}
			
			
			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("status", searchStatus);
			request.setAttribute("dayStatus", dayStatus);
			
		    request.setAttribute("searchtext", searchtext);
		    request.setAttribute("searchby", searchby);
		    request.setAttribute("navigation", navigation);
			
		    /*
		    ini gue generate juga dari nama tablenya
		    convention --> collection = nama bean + 's'
		    -adhit
		    */
		    
		    Provider providerObject = null;
			
			if(providerId != null){
				try
				{
				java.io.Serializable providerkey = providerId;
				System.out.println("provider id : "+providerId);
				String[] providerRequired = {"Provider.StatusId", "Provider.ProviderCategoryId", "Provider.ProviderGroupId","Provider.ProviderSpecId", "Provider.ProviderCurrencyId"};
				providerObject = providerService.get(providerkey, providerRequired);
				
				}
				catch (Exception ex)
				{
					//System.out.println("claim object : "+claimObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

		    result.addObject("ProviderEdcs",collection);
		    result.addObject("listnavigation",listnavigation);
		    
			request.setAttribute("countSet", countSet);
		    request.setAttribute("index", new Integer(index));
		    request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer (minIndex));
			request.setAttribute("maxIndex", new Integer (maxIndex));
			request.setAttribute("providerId", providerId);

			if (navigation.equals("downloadExcelProviderEDCReport")) {
					
				String lsthColumn ="lastTransaction";
				Object minColumn = minDate;
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, Integer.valueOf(dayStatus));
				java.util.Date dateRes = cal.getTime();
				minColumn = dateRes;
				
				collection = edcTerminalService.searchProviderEDCFault(sLikeP, sLikeQ, sEqP, sEqQ, lsthColumn, minColumn, fault, false, "id",
								required, 0, countSet.intValue());

				HSSFWorkbook workbook = EdcTerminalReportDownloader.downloadCaseMonitoring(collection);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				String nowDate = df.format(new java.util.Date());
				
				response.setHeader("Content-disposition", "inline; filename="
						+ "providereedcreport"+nowDate+".xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				sos.close();
			}
		}
		catch (Exception e){
			e.printStackTrace();
	    		request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));

		    result = new ModelAndView ("error");
		}

		return result;
	}
  
  	public ModelAndView jsonEdcTerminalPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			
			result = new ModelAndView("jsonTotalEdcAlert");
			
			String navigation = request.getParameter("navigation");
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DETAILCASE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DETAILCASE access");
				return errorResult;
				
			}
			int total = 0;
			
			if (navigation.equalsIgnoreCase("jsontotaledcalert")){
				Configuration conf = configurationService.get(0); //Ambil Client 0
				if(conf.getEdcAlertGlobal() != null){
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -conf.getEdcAlertGlobal());
					java.util.Date dateAlert = cal.getTime();
					System.out.println("DATE dateAlert : "+dateAlert);
					//total = edcTerminalService.getTotalEdcTerminalAlert(dateAlert);
					total = providerService.getTotalProviderEdcTerminalAlert(dateAlert);
					
					System.out.println("TOTAL : "+total);
				}
			}
			
			result.addObject("result", total);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

// class+ 

// class- 

}
