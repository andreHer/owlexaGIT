
package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ClaimMedicine;
import com.ametis.cms.datamodel.Medicine;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.service.ClaimMedicineService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * ClaimMedicine is a servlet controller for claim_medicine Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class ClaimMedicineController implements Controller

// extends+ 

// extends- 
{
	
	private ClaimMedicineService  claimMedicineService;
	private SecurityService securityService;
	
	
	private UserService  userService;
	
	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	private ClaimService claimService;

	
	
	public ClaimService getClaimService() {
		return claimService;
	}
	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public UserService getUserService() {
		return userService;
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
	public void setClaimMedicineService	(ClaimMedicineService claimMedicineService){
	this.claimMedicineService = claimMedicineService;
	}
	public ClaimMedicineService getClaimMedicineService (){
	return this.claimMedicineService;
	}
	public ModelAndView deletePerformed (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView result = null;

 	    try {
			Integer claimMedicineId = WebUtil.getParameterInteger (request,"claimMedicineId");
			
			java.io.Serializable pkey = claimMedicineId;
						
			ActionUser user = securityService.getActionUser(request);
		
			ClaimMedicine res = claimMedicineService.delete (pkey,user);

			if (res!=null){
				request.setAttribute ("alert", alertProperties.getMessage ("success.delete.claimmedicine",null,"success",Locale.getDefault()));
			}
			else {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.delete.claimmedicine",null,"fail",Locale.getDefault()));

			}
			result = searchPerformed (request, response, "searchClaimMedicine");
		}
		catch (Exception e){
		// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
		 e.printStackTrace();
		request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));
		result = new ModelAndView ("error");
		}	    return result;
	}
	public ModelAndView addBulkPerformed (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView result = null;

 	    try {
			
 	    	Integer claimId = WebUtil.getParameterInteger (request,"claimId");
 	    	Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
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

				Collection<ClaimMedicine> medicineList = new Vector<ClaimMedicine>();
				
				for (int i = 0; i < medicineId.length; i++){
					System.out.println("ID = " + medicineId[i] + " NAME = " + nameList[i] + " CHARGE = " + chargeList[i] + " DESC = " + descList[i] + " PRICE REF = " +priceList[i]);
				
					if (medicineId[i] != null && !medicineId[i].trim().equalsIgnoreCase("")){
						ClaimMedicine med = new ClaimMedicine();
						Medicine medicine = new Medicine();
						medicine.setMedicineId(Integer.valueOf(medicineId[i]));
						
						
						med.setMedicineId(medicine);
						if (priceList[i] != null && !priceList[i].trim().equalsIgnoreCase("")){
							med.setReferencePrice(Double.valueOf(priceList[i]));
						}
						else {
							med.setReferencePrice(-1.0);
						}
						if (usageList[i] != null && !usageList[i].trim().equalsIgnoreCase("")){
							med.setTotalUsage(Double.valueOf(usageList[i]));
						}
						else {
							med.setTotalUsage(-1.0);
						}
						if (chargeList[i] != null && !chargeList[i].trim().equalsIgnoreCase("")){
							med.setTotalCharge(Double.valueOf(chargeList[i]));
						}
						else {
							med.setTotalCharge(-1.0);
						}
						med.setDescription(descList[i]);
						
						medicineList.add(med);
						
					}
				}
				boolean res = claimMedicineService.addMedicine(claimId, medicineList, user);
				
				String alert = "";
				
				if (res){
					alert = "Success Add Medicine";
				}
				else {
					alert = "Failed Add Medicine";
				}
				result = new ModelAndView(new RedirectView("claimmedicine?navigation=list&claimId="+claimId+"&alert="+alert+"&memberId="+memberId));
			}
			request.setAttribute("claimId", claimId);
		
			
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
									Integer claimMedicineId = WebUtil.getParameterInteger (request,"claimMedicineId");
									
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
						java.io.Serializable pkey = claimMedicineId;
				        ClaimMedicine object = claimMedicineService.get (pkey);

			if (object == null){
				request.setAttribute ("alert", alertProperties.getMessage ("not.found.claimmedicine",null,"fail",Locale.getDefault()));
			}
			/*
			convention .. kalo mau nampilkan sebuah object
			pake BeanClass aja ya .. ga pake 's'
			-adhit
			*/

		    result.addObject ("claimMedicine", object);
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

		    String navigation = WebUtil.getParameterString (request,"navigation","");
		    String searchtext = WebUtil.getParameterString (request,"searchtext","");
		    String searchby = WebUtil.getParameterString (request, "searchby","");
			String sortby = WebUtil.getParameterString (request, "sortby", "");
			
			String memberId = WebUtil.getParameterString(request, "memberId", "");
			//String claimId = WebUtil.getParameterString(request, "claimId", "");
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			
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

			    							    							    							    							    							    							    								if (searchby.equalsIgnoreCase("description")){
					vLikeP.add("description");
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

		    vEqP.add("claimId.claimId");
		    vEqQ.add(Integer.valueOf(claimId));
		    
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

			count = claimMedicineService.getTotal (sLikeP,sLikeQ,sEqP,sEqQ);

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
		    collection = claimMedicineService.search (sLikeP,sLikeQ,sEqP,sEqQ,
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
				collection = claimMedicineService.search (sLikeP,sLikeQ,sEqP,sEqQ,
					required,rowsetint,countSet.intValue());
		    }
		    
		    Claim claimObject = null;
			
			if(claimId != null){
				try
				{
				java.io.Serializable claimkey = claimId;
				System.out.println("claim id : "+claimId);
				String[] claimRequired = {"Claim.BatchClaimId","Claim.MemberId",
						"Claim.CaseCategoryId","Claim.MemberId.MemberGroupId","Claim.CaseId",
						"Claim.DiagnosisId","Claim.Diagnosis2Id","Claim.Diagnosis3Id","Claim.CobClaimReferenceId","Claim.BatchClaimId.ClaimCurrency"};
				claimObject = claimService.get(claimkey, claimRequired);
				
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

		    result.addObject("ClaimMedicines",collection);
		    result.addObject("claim", claimObject);

			request.setAttribute("countSet", countSet);
		    request.setAttribute("index", new Integer(index));
		    request.setAttribute("count", new Integer(count));
		    request.setAttribute("claimId", claimId);
		    request.setAttribute("memberId", memberId);
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
    Integer memberId = WebUtil.getParameterInteger(request, "memberId");
    Integer claimId = WebUtil.getParameterInteger(request, "claimId");
    Integer index = WebUtil.getParameterInteger(request, "index");

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
	    	result = detailPerformed (request, response, "detailClaimMedicine" );
	    }
	    else if (navigation.equalsIgnoreCase ("delete")){
	    	result = deletePerformed (request, response );
	    }
	    else if (navigation.equalsIgnoreCase ("addbulk") || navigation.equalsIgnoreCase("savebulk")){
	    	result = addBulkPerformed(request, response );
	    	breadcrumb = "<a href=\"claimmedicine?navigation=addbulk&arah=&memberId="+memberId+"&claimId="+claimId+"&index="+index+"&claimMedicineId=\" class=\"linkbreadcrumb\">Add Medicine</a>";
	    }
	    else if (navigation.equalsIgnoreCase ("search") || navigation.equals("gosearch")){
	    	result = searchPerformed (request, response, "searchClaimMedicine" );
	    }
	    else if (navigation.equalsIgnoreCase ("lookup") || navigation.equals("golookup")){
	    	result = searchPerformed (request, response, "lookupClaimMedicine" );
	    }
	    else {
	    	result = searchPerformed (request, response, "searchClaimMedicine" );
			breadcrumb = "<a href=\"claim?navigation=detail&claimId="
					+ claimId
					+ "\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Claim Medicine";
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
