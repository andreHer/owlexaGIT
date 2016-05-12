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
import org.springframework.context.support.ResourceBundleMessageSource; /*
 import org.acegisecurity.Authentication;
 import org.acegisecurity.context.SecurityContextHolder;
 import org.acegisecurity.userdetails.UserDetails;
 */
import com.ametis.cms.datamodel.*;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.ProviderTypeDiagnosisTreatmentService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.service.ProviderDoctorService;
import com.ametis.cms.service.ProviderPoliklinikService;
import java.util.*;

// imports+

// imports-

/**
 * ProviderDoctor is a servlet controller for provider_doctor Table. All you
 * have to do is to convert necessary data field to the named parameter
 */
public class ProviderDoctorController implements Controller

// extends+

// extends-
{

	private ProviderDoctorService providerDoctorService;
	private SecurityService securityService;
	private ProviderPoliklinikService providerPoliklinikService;
	private ProviderService providerService;

	private UserService actionuserService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	

	
	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public ProviderPoliklinikService getProviderPoliklinikService() {
		return providerPoliklinikService;
	}

	public void setProviderPoliklinikService(
			ProviderPoliklinikService providerPoliklinikService) {
		this.providerPoliklinikService = providerPoliklinikService;
	}

	public void setUserService(UserService userService) {
		this.actionuserService = userService;
	}

	public UserService getUserService() {
		return actionuserService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public SecurityService getSecurityService() {
		return this.securityService;
	}

	public void setCountSet(Integer countSet) {
		this.countSet = countSet;
	}

	public Integer getCountSet() {
		return this.countSet;
	}

	public void setMaxPercountSet(Integer maxCount) {
		this.maxPercountSet = maxCount;
	}

	public Integer getMaxPercountSet() {
		return this.maxPercountSet;
	}

	public void setAlertProperties(ResourceBundleMessageSource alert) {
		this.alertProperties = alert;
	}

	public ResourceBundleMessageSource getAlertProperties() {
		return alertProperties;
	}

	public void setProviderDoctorService(
			ProviderDoctorService providerDoctorService) {
		this.providerDoctorService = providerDoctorService;
	}

	public ProviderDoctorService getProviderDoctorService() {
		return this.providerDoctorService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer providerDoctor = WebUtil.getParameterInteger(request,
					"providerDoctor");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = providerDoctor;

			ActionUser user = securityService.getActionUser(request);

			ProviderDoctor res = providerDoctorService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.providerdoctor", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.providerdoctor", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchProviderDoctor");
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

	public ModelAndView detailPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer providerDoctor = WebUtil.getParameterInteger(request,
					"providerDoctor");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String listnavigation = WebUtil.getParameterString(request, "listnavigation",
					"");
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			result = new ModelAndView(location);
			java.io.Serializable pkey = providerDoctor;
			ProviderDoctor object = providerDoctorService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.providerdoctor", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("providerDoctor", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("listnavigation",listnavigation);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String listnavigation = WebUtil.getParameterString(request, "listnavigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

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
			if (navigation.equals("gosearch") || navigation.equals("golookup") || providerId != null) {

				if (searchby != null) {
					

					if (searchby.equalsIgnoreCase("doctorName")) {
						vLikeP.add("doctorName");
						vLikeQ.add(searchtext);
					}
					
				
					if (searchby.equalsIgnoreCase("poliklinikName")) {
						vLikeP.add("providerPoliklinikId.poliklinikName");
						vLikeQ.add(searchtext);
					}
				}

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

			count = providerDoctorService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			String arah = WebUtil.getParameterString(request, "arah", "");

			if (index == null)
				index = new Integer(1);

			if (arah.equals("kanan"))
				index = new Integer(index.intValue() + 1);
			else if (arah.equals("kiri"))
				index = new Integer(index.intValue() - 1);
			else if (arah.equals("kiribgt"))
				index = new Integer(1);
			else if (arah.equals("kananbgt"))
				index = new Integer(count / countSet.intValue() + 1);

			if (index.compareTo(new Integer(1)) == new Integer(-1).intValue())
				index = new Integer(1);
			else if (index.compareTo(new Integer(count / countSet.intValue()
					+ 1)) == new Integer(1).intValue())
				index = new Integer(count / countSet.intValue() + 1);

			rowsetint = (new Integer((index.intValue() - 1)
					* countSet.intValue())).intValue();
			if (count % countSet.intValue() > 0) {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
					// foreign affairs
					"ProviderDoctor.ProviderId",
					"ProviderDoctor.ProviderItemId",
			// foreign affairs end
			};
			collection = providerDoctorService.search(sLikeP, sLikeQ, sEqP,
					sEqQ, required, rowsetint, countSet.intValue());

			if (collection.size() <= 0) {
				index = new Integer(index.intValue() - 1);
				if (index.compareTo(new Integer(1)) == new Integer(-1)
						.intValue())
					index = new Integer(1);
				else if (index.compareTo(new Integer(count
						/ countSet.intValue() + 1)) == new Integer(1)
						.intValue())
					index = new Integer(count / countSet.intValue() + 1);

				rowsetint = (new Integer((index.intValue() - 1)
						* countSet.intValue())).intValue();
				if (count % countSet.intValue() > 0) {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue()));
				}
				collection = providerDoctorService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
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
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("ProviderDoctors", collection);
			result.addObject("provider", providerObject);
			result.addObject("listnavigation", listnavigation);

			request.setAttribute("providerId", providerId);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView addBulkPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			ActionUser user = securityService.getActionUser(request);

			if (navigation.equalsIgnoreCase("addbulk")) {

				String[] eqParamPoli = {"deletedStatus","providerId.providerId"};
				Object[] eqValuePoli = {0,providerId};
				
				int totalPoli = providerPoliklinikService.getTotal(null,null,eqParamPoli,eqValuePoli);
				Collection<ProviderPoliklinik> poliklinikList = providerPoliklinikService.search(null,null,eqParamPoli,eqValuePoli,0,totalPoli);

				Vector<String> sunday = new Vector<String>();
				Vector<String> monday = new Vector<String>();
				Vector<String> tuesday = new Vector<String>();
				Vector<String> wednesday = new Vector<String>();
				Vector<String> thursday = new Vector<String>();
				Vector<String> friday = new Vector<String>();
				Vector<String> saturday = new Vector<String>();
				
				Vector<String> nameList = new Vector<String>();
				Vector<String> poliIdList = new Vector<String>();


				for (int i = 0; i < 15; i++) {

					sunday.add("");
					monday.add("");
					tuesday.add("");
					wednesday.add("");
					thursday.add("");
					friday.add("");
					saturday.add("");
					
					nameList.add("");
					poliIdList.add("");


				}
				result = new ModelAndView("addBulkDoctor");
				result.addObject("sundayVector", sunday);
				result.addObject("mondayVector", monday);
				result.addObject("tuesdayVector", tuesday);
				result.addObject("wednesdayVector", wednesday);
				result.addObject("thursdayVector", thursday);
				result.addObject("fridayVector", friday);
				result.addObject("saturdayVector", saturday);
				
				result.addObject("nameVector", nameList);
				result.addObject("poliIdVector", poliIdList);
				result.addObject("polikliniks", poliklinikList);

			} else if (navigation.equalsIgnoreCase("savebulk")) {

				String[] poliId = request.getParameterValues("poliId");
				String[] doctorName = request.getParameterValues("doctorName");
				String[] monday = request.getParameterValues("monday");
				String[] tuesday = request.getParameterValues("tuesday");
				String[] thursday = request.getParameterValues("thursday");
				String[] wednesday = request.getParameterValues("wednesday");
				String[] friday = request.getParameterValues("friday");
				String[] saturday = request.getParameterValues("saturday");
				String[] sunday = request.getParameterValues("sunday");

				Collection<ProviderDoctor> poliList = new Vector<ProviderDoctor>();

				for (int i = 0; i < poliId.length; i++) {

					if (poliId[i] != null
							&& !poliId[i].trim().equalsIgnoreCase("")) {

						ProviderDoctor doctor = new ProviderDoctor();
						Poliklinik poli = new Poliklinik();
						poli.setPoliklinikId(Integer.valueOf(poliId[i]));
						
						
						doctor.setProviderPoliklinikId(poli);
						doctor.setDoctorName(doctorName[i]);
						doctor.setMonday(monday[i]);
						doctor.setTuesday(tuesday[i]);
						doctor.setWednesday(wednesday[i]);
						doctor.setThursday(thursday[i]);
						doctor.setFriday(friday[i]);
						doctor.setSaturday(saturday[i]);
						doctor.setSunday(sunday[i]);



						poliList.add(doctor);

					}
				}
				boolean res = providerDoctorService.addDoctor(
						providerId, poliList, user);

				String alert = "";

				if (res) {
					alert = "Success Add Doctor(s)";
				} else {
					alert = "Failed Add Doctor(s)";
				}
				result = new ModelAndView(new RedirectView(
						"providerdoctor?navigation=list&providerId="
								+ providerId + "&alert=" + alert));
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
	public ModelAndView lookupJsonPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			
			System.out.println("lookup json provider doctor dipanggil");
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			
			String q = WebUtil.getParameterString(request, "q", "");
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");

			String[] likeParam = { "doctorName" };
			String[] eqParam = { "providerId.providerId" };
			

			Object[] likeValue = { q };
			Object[] eqValue = { providerId };
			
			System.out.println("providerId.providerId " + providerId);
			
//			String[] required = {"ProviderMedicine.MedicineId", "ProviderMedicine.ClientId", "ProviderMedicine.ProviderId" };

				Collection<Provider> provider = providerDoctorService.search(likeParam,
						likeValue, eqParam, eqValue, 0, 10);
	
				result.addObject("Providers", provider);
			

		} catch (Exception e) {
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
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Get paramater navigation
		String navigation = request.getParameter("navigation") == null ? "welcome"
				: request.getParameter("navigation");
		String listnavigation = request.getParameter("listnavigation") == null ? "welcome"
				: request.getParameter("listnavigation");
		Integer index = WebUtil.getParameterInteger(request, "index");
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
		String searchby = WebUtil.getParameterString(request, "searchby","");



		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);

		/*
		 * if (session == null) {
		 * 
		 * request.setAttribute("alert", "<b>" +
		 * alertProperties.getValue("not.login") + "</b>");
		 * request.setAttribute("content", "/jsp/login.jsp");
		 * forward("/main.jsp", request, response);
		 * 
		 * 
		 * result = new ModelAndView ("login"); } else {
		 *  }
		 */
		String breadcrumb = "";
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response,
						"detailProviderDoctor");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchProviderDoctor");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupProviderDoctor");
			} else if (navigation.equalsIgnoreCase("lookupjson")) {
			result = lookupJsonPerformed(request, response,
					"lookupProviderDoctorJson");
			}
			else if (navigation.equalsIgnoreCase("addbulk")
					|| navigation.equals("savebulk")) {
				result = addBulkPerformed(request, response);
				breadcrumb = "<a href=\"providerdoctor?providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Doctor </a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Doctor";
			} 
			else {
				result = searchPerformed(request, response,
						"searchProviderDoctor");
				if(listnavigation.equalsIgnoreCase("searchcapitation"))
				{
					breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"&listnavigation="+listnavigation+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Doctor";
				}
				else
				{
					breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Doctor";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.addObject("breadcrumb", breadcrumb);
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	// class+

	// class-
}
