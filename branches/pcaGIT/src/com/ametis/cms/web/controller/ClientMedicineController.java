
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
import java.util.*;

// imports+ 

// imports- 

/**
 * ClientMedicine is a servlet controller for client_medicine Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class ClientMedicineController implements Controller

// extends+ 

// extends- 
{
	
	private ClientMedicineService  clientMedicineService;
	private SecurityService securityService;
	private ClientService clientService;
	
	
	private UserService  actionuserService;
	
	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	

	public ClientService getClientService() {
		return clientService;
	}
	
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	
	public void setActionUserService(UserService userService) {
		this.actionuserService = userService;
	}
	public UserService getActionUserService() {
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
	public void setClientMedicineService	(ClientMedicineService clientMedicineService){
	this.clientMedicineService = clientMedicineService;
	}
	public ClientMedicineService getClientMedicineService (){
	return this.clientMedicineService;
	}
	public ModelAndView deletePerformed (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView result = null;

 	    try {
									Integer clientMedicineId = WebUtil.getParameterInteger (request,"clientMedicineId");
						
			/*
			Pkey ini merupakan representasi dari primary key, misalkan
			hasil get dari attribute diatas ^ itu cuma ada 1 key saja
			berarti tinggal pkey = <nama var diatas>
			tapi kalau misalkan composite primary key berarti
			tinggal bikin representasi primary key nya saja .
			*/
						java.io.Serializable pkey = clientMedicineId;
						
			ActionUser user = securityService.getActionUser(request);
		
			ClientMedicine res = clientMedicineService.delete (pkey,user);

			if (res!=null){
				request.setAttribute ("alert", alertProperties.getMessage ("success.delete.clientmedicine",null,"success",Locale.getDefault()));
			}
			else {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.delete.clientmedicine",null,"fail",Locale.getDefault()));

			}
			result = searchPerformed (request, response, "searchClientMedicine");
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
									Integer clientMedicineId = WebUtil.getParameterInteger (request,"clientMedicineId");
									
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
						java.io.Serializable pkey = clientMedicineId;
				        ClientMedicine object = clientMedicineService.get (pkey);

			if (object == null){
				request.setAttribute ("alert", alertProperties.getMessage ("not.found.clientmedicine",null,"fail",Locale.getDefault()));
			}
			/*
			convention .. kalo mau nampilkan sebuah object
			pake BeanClass aja ya .. ga pake 's'
			-adhit
			*/

		    result.addObject ("clientMedicine", object);
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

		    Integer index = WebUtil.getParameterInteger (request, "index");
		    Integer clientId = WebUtil.getParameterInteger(request, "clientId");

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
		    if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("listclient")){

  		        if (searchby != null){
			    /**
			    * ini querynya disesuaikan dengan apa yang mau di search
			    * default nya gue bikin template search by semua field yang tipenya 'String'
			    * -adhit
			    */

    			}

		    }

		    vEqP.add("deletedStatus");
		    vEqQ.add(new Integer(0));
		    
		    vEqP.add("clientId.clientId");
		    vEqQ.add(clientId);

		    String sLikeP[] = new String[vLikeP.size()];
		    vLikeP.toArray(sLikeP);
		    Object sLikeQ[] = new Object[vLikeP.size()];
		    vLikeQ.toArray(sLikeQ);

		    String sEqP[] = new String[vEqP.size()];
		    vEqP.toArray(sEqP);
		    Object sEqQ[] = new Object[vEqP.size()];
		    vEqQ.toArray(sEqQ);

			count = clientMedicineService.getTotal (sLikeP,sLikeQ,sEqP,sEqQ);

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
				"ClientMedicine.MedicineId",
				"ClientMedicine.ClientId",
		// foreign affairs end
	};
		    collection = clientMedicineService.search (sLikeP,sLikeQ,sEqP,sEqQ,
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
				collection = clientMedicineService.search (sLikeP,sLikeQ,sEqP,sEqQ,
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
		    
		    Client clientObject = null;
			
			if(clientId != null){
				try
				{
				java.io.Serializable clientpkey = clientId;
				System.out.println("member client id : "+clientId);
				String[] clientRequired = {"Client.FundCurrency","Client.PaymentCurrency","Client.StatusId"};
				clientObject = clientService.get(clientpkey, clientRequired);
				
				}
				catch (Exception ex)
				{
					System.out.println("member group object : "+clientObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

		    result.addObject("ClientMedicines",collection);
		    result.addObject("client", clientObject);

		    request.setAttribute("clientId", clientId);
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
	public ModelAndView addBulkPerformed (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView result = null;

 	    try {
			
 	    	Integer clientId = WebUtil.getParameterInteger (request,"clientId");	    	
			
 	    	String navigation = WebUtil.getParameterString(request, "navigation", "");
						
			ActionUser user = securityService.getActionUser(request);
			
			if (navigation.equalsIgnoreCase("addbulk")){	
				
				Vector<String> itemValueList = new Vector<String>();
				Vector<String> itemAmountList = new Vector<String>();
				Vector<String> medicineList = new Vector<String>();
				Vector<String> medicineIdList = new Vector<String>();
				Vector<String> descList = new Vector<String>();
				Vector<String> priceList = new Vector<String>();
				
				
				for(int i = 0; i < 10; i++){
					
					itemValueList.add("");
					medicineList.add("");
					medicineIdList.add("");
					itemAmountList.add("");
					descList.add("");
					priceList.add("");
					
				}
				result = new ModelAndView("addBulkMedicine");
				
				result.addObject("valueVector", itemValueList);
				result.addObject("nameVector", medicineList);
				result.addObject("medIdVector", medicineIdList);
				result.addObject("amountVector", itemAmountList);
				result.addObject("descVector", descList);
				result.addObject("medPriceVector", priceList);
				
			}
			else if (navigation.equalsIgnoreCase("savebulk")){
				
				String[] medicineId = request.getParameterValues("medicineId");
				String[] usageList = request.getParameterValues("itemAmount");
				String[] nameList = request.getParameterValues("medicineName");
				String[] chargeList = request.getParameterValues("itemValue");
				String[] priceList = request.getParameterValues("itemPrice");
				String[] descList = request.getParameterValues("description");

				Collection<ClientMedicine> medicineList = new Vector<ClientMedicine>();
				
				for (int i = 0; i < medicineId.length; i++){
					System.out.println("ID = " + medicineId[i] + " NAME = " + nameList[i] + " CHARGE = " + chargeList[i] + " DESC = " + descList[i] + " PRICE REF = " +priceList[i]);
				
					if (medicineId[i] != null && !medicineId[i].trim().equalsIgnoreCase("")){
						ClientMedicine med = new ClientMedicine();
						Medicine medicine = new Medicine();
						medicine.setMedicineId(Integer.valueOf(medicineId[i]));
						
						
						med.setMedicineId(medicine);
						if (priceList[i] != null && !priceList[i].trim().equalsIgnoreCase("")){
							med.setReferencePrice(Double.valueOf(priceList[i]));
							
						}
						else {
							med.setReferencePrice(-1.0);
						}
						
						med.setItemValue(Double.valueOf(chargeList[i]));
						med.setDescription(descList[i]);
						
						medicineList.add(med);
						
					}
				}
				boolean res = clientMedicineService.addMedicine(clientId, medicineList, user);
				
				String alert = "";
				
				if (res){
					alert = "Success Add Medicine";
				}
				else {
					alert = "Failed Add Medicine";
				}
				result = new ModelAndView(new RedirectView("clientmedicine?navigation=list&clientId="+clientId+"&alert="+alert));
			}
			
			request.setAttribute("clientId", clientId);
		
			
		}
		catch (Exception e){
		// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
		 e.printStackTrace();
		request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));
		result = new ModelAndView ("error");
		}	    return result;
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
    String clientId = request.getParameter("clientId");

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
	    	result = detailPerformed (request, response, "detailClientMedicine" );
	    }
	    else if (navigation.equalsIgnoreCase ("delete")){
	    	result = deletePerformed (request, response );
	    }
	    else if (navigation.equalsIgnoreCase("addbulk") || navigation.equalsIgnoreCase("savebulk")){
	    	result = addBulkPerformed(request, response);
        	breadcrumb = "<a href=\"clientmedicine?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Medicine Formularium</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Medicine";
	    }
	    else if (navigation.equalsIgnoreCase ("search") || navigation.equals("gosearch")){
	    	result = searchPerformed (request, response, "searchClientMedicine" );
	    }
	    else if (navigation.equalsIgnoreCase ("lookup") || navigation.equals("golookup")){
	    	result = searchPerformed (request, response, "lookupClientMedicine" );
	    }
	    else {
	    	result = searchPerformed (request, response, "searchClientMedicine" );
//	    	String clientId = request.getParameter("clientId");
			breadcrumb = "<a href=\"client?navigation=detail&clientId="+clientId+"\" class=\"linkbreadcrumb\">Detail Client</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Client Medicine Formularium";
	    }
    }
    catch (Exception e){
    	e.printStackTrace();
    }
	request.setAttribute("clientId", clientId);
	result.addObject("breadcrumb", breadcrumb);
	Map map = TableRenderingServlet.seti18N(request,response);
	result.addAllObjects(map);
    return result;
  }


// class+ 

// class- 
}
