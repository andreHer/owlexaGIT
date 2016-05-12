
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
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.context.support.ResourceBundleMessageSource;
/*
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
*/
import com.ametis.cms.datamodel.*;
import com.ametis.cms.service.*;
import com.ametis.cms.service.ProviderService;

import java.util.*;

// imports+ 

// imports- 


/**
 * ProviderPoliklinik is a servlet controller for provider_poliklinik Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class ProviderPoliklinikController implements Controller

// extends+ 

// extends- 

{
	
	private ProviderPoliklinikService  providerPoliklinikService;
	private SecurityService securityService;
	
	
	private UserService  actionuserService;
	
	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;

	private PoliklinikService poliklinikService;
	
	private ProviderService providerService;

	public ProviderService getProviderService() {
		return providerService;
	}
	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}
	public PoliklinikService getPoliklinikService() {
		return poliklinikService;
	}
	public void setPoliklinikService(PoliklinikService poliklinikService) {
		this.poliklinikService = poliklinikService;
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
	public void setProviderPoliklinikService	(ProviderPoliklinikService providerPoliklinikService){
	this.providerPoliklinikService = providerPoliklinikService;
	}
	public ProviderPoliklinikService getProviderPoliklinikService (){
	return this.providerPoliklinikService;
	}
	public ModelAndView deletePerformed (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView result = null;

 	    try {
									Integer providerPoliklinikId = WebUtil.getParameterInteger (request,"providerPoliklinikId");
						
			/*
			Pkey ini merupakan representasi dari primary key, misalkan
			hasil get dari attribute diatas ^ itu cuma ada 1 key saja
			berarti tinggal pkey = <nama var diatas>
			tapi kalau misalkan composite primary key berarti
			tinggal bikin representasi primary key nya saja .
			*/
						java.io.Serializable pkey = providerPoliklinikId;
						
			ActionUser user = securityService.getActionUser(request);
		
			ProviderPoliklinik res = providerPoliklinikService.delete (pkey,user);

			if (res!=null){
				request.setAttribute ("alert", alertProperties.getMessage ("success.delete.providerpoliklinik",null,"success",Locale.getDefault()));
			}
			else {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.delete.providerpoliklinik",null,"fail",Locale.getDefault()));

			}
			result = searchPerformed (request, response, "searchProviderPoliklinik");
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
									Integer providerPoliklinikId = WebUtil.getParameterInteger (request,"providerPoliklinikId");
									
									String listnavigation = WebUtil.getParameterString(request,
											"listnavigation", "");
									
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
						java.io.Serializable pkey = providerPoliklinikId;
				        ProviderPoliklinik object = providerPoliklinikService.get (pkey);

			if (object == null){
				request.setAttribute ("alert", alertProperties.getMessage ("not.found.providerpoliklinik",null,"fail",Locale.getDefault()));
			}
			/*
			convention .. kalo mau nampilkan sebuah object
			pake BeanClass aja ya .. ga pake 's'
			-adhit
			*/

		    result.addObject ("providerPoliklinik", object);
			result.addObject("index",index);
		    result.addObject("searchtext",searchtext);
		    result.addObject("searchby",searchby);
		    result.addObject("listnavigation", listnavigation);

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

		    Integer providerId = WebUtil.getParameterInteger(request, "providerId");
		    String listnavigation = WebUtil.getParameterString(request,
					"listnavigation", "");
		    String navigation = WebUtil.getParameterString (request,"navigation","");
		    String searchtext = WebUtil.getParameterString (request,"searchtext","");
		    String searchby = WebUtil.getParameterString (request, "searchby","");
			String sortby = WebUtil.getParameterString (request, "sortby", "");
			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
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

		    
//		    if (searchtext != null){
//		    	vLikeP.add("poliklinikId.poliklinikName");
//		    	vLikeQ.add(searchtext);
//		    }
		    
		    if (navigation.equals("gosearch") || navigation.equals("golookup") || providerId != null) {

				if (searchby != null ) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("poliklinikName")) {
						vLikeP.add("poliklinikId.poliklinikName");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("poliklinikCode")) {
						vLikeP.add("poliklinikId.poliklinikCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("location")) {
						vLikeP.add("location");
						vLikeQ.add(searchtext);
					}


					}
				}
				if (searchStatus != null && searchStatus.intValue() > 0) {
					vEqP.add("statusId.statusId");
					vEqQ.add(searchStatus);
				}

			


		    vEqP.add("deletedStatus");
		    vEqQ.add(new Integer(0));
		    
		    vEqP.add("providerId.providerId");
		    vEqQ.add(providerId);

		    String sLikeP[] = new String[vLikeP.size()];
		    vLikeP.toArray(sLikeP);
		    Object sLikeQ[] = new Object[vLikeP.size()];
		    vLikeQ.toArray(sLikeQ);

		    String sEqP[] = new String[vEqP.size()];
		    vEqP.toArray(sEqP);
		    Object sEqQ[] = new Object[vEqP.size()];
		    vEqQ.toArray(sEqQ);

			count = providerPoliklinikService.getTotal (sLikeP,sLikeQ,sEqP,sEqQ);

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
				"ProviderPoliklinik.PoliklinikId",
				"ProviderPoliklinik.ProviderId",
		// foreign affairs end
	};
		    collection = providerPoliklinikService.search (sLikeP,sLikeQ,sEqP,sEqQ,
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
				collection = providerPoliklinikService.search (sLikeP,sLikeQ,sEqP,sEqQ,
					required,rowsetint,countSet.intValue());
		    }
		    
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
			
		    request.setAttribute("searchtext", searchtext);
		    request.setAttribute("searchby", searchby);
		    request.setAttribute("navigation", navigation);
			
		    /*
		    ini gue generate juga dari nama tablenya
		    convention --> collection = nama bean + 's'
		    -adhit
		    */

		    result.addObject("ProviderPolikliniks",collection);
		    result.addObject("provider", providerObject);
		    result.addObject("listnavigation", listnavigation);

		    request.setAttribute("providerId", providerId);
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



	public ModelAndView addBulkPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			
			

			ActionUser user = securityService.getActionUser(request);

			if (navigation.equalsIgnoreCase("addbulk")) {
				
				Collection<Poliklinik> poliklinikList = poliklinikService.getAll();

				Vector<String> totalRoomList = new Vector<String>();
				Vector<String> totalDoctorList = new Vector<String>();
				Vector<String> poliList = new Vector<String>();
				Vector<String> poliIdList = new Vector<String>();
			
				Vector<String> locationList = new Vector<String>();

				for (int i = 0; i < 15; i++) {

					totalRoomList.add("");
					poliList.add("");
					poliIdList.add("");
					totalDoctorList.add("");
			
					locationList.add("");

				}
				result = new ModelAndView("addBulkPoliklinik");
				result.addObject("totalRoomVector", totalRoomList);
				result.addObject("nameVector", poliList);
				result.addObject("poliIdVector", poliIdList);
				result.addObject("totalDoctorVector", totalDoctorList);
				result.addObject("locationVector", locationList);
				result.addObject("polikliniks", poliklinikList);

			} else if (navigation.equalsIgnoreCase("savebulk")) {

				String[] poliId = request.getParameterValues("poliId");
				String[] locationList = request.getParameterValues("location");
				String[] roomList = request.getParameterValues("totalRoom");
				String[] totalDoctorList = request.getParameterValues("totalDoctor");
			

				Collection<ProviderPoliklinik> poliList = new Vector<ProviderPoliklinik>();

				for (int i = 0; i < poliId.length; i++) {
					

					if (poliId[i] != null
							&& !poliId[i].trim().equalsIgnoreCase("")) {
						
						ProviderPoliklinik poliProvider = new ProviderPoliklinik();
						Poliklinik poli = new Poliklinik();
						poli.setPoliklinikId(Integer.valueOf(poliId[i]));

						poliProvider.setPoliklinikId(poli);
						
						poliProvider.setLocation(locationList[i]);
						
						if (roomList[i] != null
								&& !roomList[i].trim().equalsIgnoreCase("")) {
							poliProvider.setTotalRoom(Integer.valueOf(roomList[i]));
						} else {
							poliProvider.setTotalRoom(0);
						}
						if (totalDoctorList[i] != null
								&& !totalDoctorList[i].trim().equalsIgnoreCase("")) {
							poliProvider.setTotalDoctor(Integer.valueOf(totalDoctorList[i]));
						} else {
							poliProvider.setTotalDoctor(0);
						}


						poliList.add(poliProvider);

					}
				}
				boolean res = providerPoliklinikService.addPoliklinik(providerId,
						poliList, user);

				String alert = "";

				if (res) {
					alert = "Success Add Poliklinik";
				} else {
					alert = "Failed Add Poliklinik";
				}
				result = new ModelAndView(new RedirectView(
						"providerpoliklinik?navigation=list&providerId=" + providerId
								+ "&alert=" + alert ));
			}
			request.setAttribute("providerId", providerId);

		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
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
            : request.getParameter("listnavigation");
    
	Integer index = WebUtil.getParameterInteger(request, "index");
	Integer providerId = WebUtil.getParameterInteger(request, "providerId");
	String searchby = WebUtil.getParameterString(request, "searchby", "");

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
	    	result = detailPerformed (request, response, "detailProviderPoliklinik" );
	    }
	    else if (navigation.equalsIgnoreCase ("delete")){
	    	result = deletePerformed (request, response );
	    }
	    else if (navigation.equalsIgnoreCase ("search") || navigation.equals("gosearch")){
	    	result = searchPerformed (request, response, "searchProviderPoliklinik" );
	    }
	    else if (navigation.equalsIgnoreCase ("lookup") || navigation.equals("golookup")){
	    	result = searchPerformed (request, response, "lookupProviderPoliklinik" );
	    }
	    else if (navigation.equalsIgnoreCase ("addbulk") || navigation.equals("savebulk")){
	    	result = addBulkPerformed(request, response);
			breadcrumb = "<a href=\"providerpoliklinik?providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Poliklinik </a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Poliklinik";
	    }
	    else {
	    	result = searchPerformed (request, response, "searchProviderPoliklinik" );
	    	if(listnavigation.equalsIgnoreCase("searchcapitation"))
			{
	    		breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"&listnavigation="+listnavigation+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Poliklinik";
			}
	    	else
	    	{
	    		breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Poliklinik";
	    	}
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
