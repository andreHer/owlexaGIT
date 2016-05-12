package com.ametis.cms.web.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
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
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.EdcTerminal;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderDoctor;
import com.ametis.cms.datamodel.ProviderItem;
import com.ametis.cms.datamodel.ProviderPoliklinik;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.EdcTerminalService;
import com.ametis.cms.service.EdcTransactionLogService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.ProviderDoctorService;
import com.ametis.cms.service.ProviderItemService;
import com.ametis.cms.service.ProviderPoliklinikService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.ProviderReportDownloader;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * Provider is a servlet controller for provider Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class ProviderController implements Controller

// extends+

// extends-

{

	private ProviderService providerService;
	private ProviderDoctorService providerDoctorService;
	private ProviderPoliklinikService providerPoliklinikService;
	private ProviderItemService providerItemService;
	private MemberService memberService;
	private EdcTerminalService edcTerminalService;
	private EdcTransactionLogService edcTransactionLogService;

	private UserService userService;
	
	private CaseService caseService;

	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;

	SecurityService securityService;
	private ActivityLogService logService;

	private ConfigurationService  configurationService;
	
	//Add by aju on 20150928, make iframe param public fufufu :D
	private String usingIFrame;
	private String iFrameClientMemberId;
	private String iFrameLevelLogin;
	private String sessionMemberId;
	private String sessionMemberParentId;
	private boolean isIFrameSession;
	
	public ProviderDoctorService getProviderDoctorService() {
		return providerDoctorService;
	}

	public void setProviderDoctorService(ProviderDoctorService providerDoctorService) {
		this.providerDoctorService = providerDoctorService;
	}

	public ProviderPoliklinikService getProviderPoliklinikService() {
		return providerPoliklinikService;
	}

	public void setProviderPoliklinikService(
			ProviderPoliklinikService providerPoliklinikService) {
		this.providerPoliklinikService = providerPoliklinikService;
	}

	public ProviderItemService getProviderItemService() {
		return providerItemService;
	}

	public void setProviderItemService(ProviderItemService providerItemService) {
		this.providerItemService = providerItemService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public EdcTerminalService getEdcTerminalService() {
		return edcTerminalService;
	}

	public void setEdcTerminalService(EdcTerminalService edcTerminalService) {
		this.edcTerminalService = edcTerminalService;
	}

	public EdcTransactionLogService getEdcTransactionLogService() {
		return edcTransactionLogService;
	}

	public void setEdcTransactionLogService(EdcTransactionLogService edcTransactionLogService) {
		this.edcTransactionLogService = edcTransactionLogService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
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

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public ProviderService getProviderService() {
		return this.providerService;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer providerId = WebUtil.getParameterInteger(request,"providerId");
			
			java.io.Serializable pkey = providerId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DELETEPROVIDER");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DELETEPROVIDER access");
				return errorResult;

			}
			Provider res = providerService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.provider", null, "success", Locale
								.getDefault()));
//				result = new ModelAndView();
				result = searchPerformed(request, response, "searchProvider");
			} else {
				request.setAttribute("fail", "y");
				/*request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.provider", null, "fail", Locale
								.getDefault()));*/
				result = detailPerformed(request, response, "detailProvider");
			}
			
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
	public ModelAndView unblockPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			Integer providerId = WebUtil.getParameterInteger(request,"providerId");
			String reason = WebUtil.getParameterString(request, "reason","");
			
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			Provider provider = providerService.get(providerId);
			
			if (navigation.equalsIgnoreCase("confirmunblock")){
				ActionUser user = securityService.getActionUser(request);
	
				boolean isUserAuthorized = securityService.isUserAuthorized(user,
						"UNBLOCKPROVIDER");
	
				if (!isUserAuthorized) {
	
					ModelAndView errorResult = new ModelAndView(new RedirectView(
							"errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for UNBLOCKPROVIDER access");
					return errorResult;
	
				}
				boolean res = providerService.unBlockProvider(providerId,reason, user);
				String alert = "";
	
				if (res) {
					alert = "Success UnBlock Provider";
				} else {
					alert = "Failed UnBlock Provider";
	
				}
				result = new ModelAndView(new RedirectView("provider?navigation=detail&providerId="+providerId+"&alert="+alert));
			}
			else if (navigation.equalsIgnoreCase("unblock")){
				result = new ModelAndView("unblockProvider");
				result.addObject("provider", provider);
			}
			
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
	public ModelAndView blockPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer providerId = WebUtil.getParameterInteger(request,"providerId");
			String reason = WebUtil.getParameterString(request, "reason","");
			String navigation  = WebUtil.getParameterString(request, "navigation", "");
			
			Provider provider = providerService.get(providerId);
			
			if (provider != null){
			
				if (navigation.equalsIgnoreCase("confirmblock")){
					ActionUser user = securityService.getActionUser(request);
		
					boolean isUserAuthorized = securityService.isUserAuthorized(user,
							"DELETEPROVIDER");
		
					if (!isUserAuthorized) {
		
						ModelAndView errorResult = new ModelAndView(new RedirectView(
								"errorpage"));
						errorResult.addObject("errorType", "accessDenied");
						errorResult.addObject("errorMessage",
								"You Are Not Authorized for BLOCKPROVIDER access");
						return errorResult;
		
					}
					
					boolean res = providerService.blockProvider(providerId,reason, user);
					String alert = "";
		
					if (res) {
						alert = "Success Block Provider";
					} else {
						alert = "Failed Block Provider";
		
					}
					result = new ModelAndView(new RedirectView("provider?navigation=detail&providerId="+providerId+"&alert="+alert));
				}
				else if (navigation.equalsIgnoreCase("block")){
					
					result = new ModelAndView("blockProvider");
					
					result.addObject("provider", provider);
				}
			}
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
			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			
			String listnavigation = WebUtil.getParameterString(request,
					"listnavigation", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			result = new ModelAndView(location);
			java.io.Serializable pkey = providerId;
			String[] required = {"Provider.ProviderSpecId","Provider.ProviderGroupId","Provider.ProviderCurrencyId","Provider.StatusId"};
			Provider object = providerService.get(pkey,required);

			if (object == null) {
				request.setAttribute("alert", alertProperties
						.getMessage("not.found.provider", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

		//	Provider prov = providerService.getByProviderCode("703549102360001");
			
			result.addObject("provider", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("listnavigation", listnavigation);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView showProviderMapPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			Integer index = WebUtil.getParameterInteger(request, "index");

			result = new ModelAndView(location);
			java.io.Serializable pkey = providerId;
			Provider object = providerService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties
						.getMessage("not.found.provider", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("provider", object);
			result.addObject("index", index);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView searchProviderMapPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */


			result = new ModelAndView("lookupProviderMapJson");
			
			Collection<Provider> providers = providerService.searchPlotableProvider();
			result.addObject("providers", providers);
			

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView updateStatusPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");

			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			result = new ModelAndView(location);
			java.io.Serializable pkey = providerId;

			Provider object = null;

			if (navigation.equalsIgnoreCase("terminate")) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "TERMINATEPROVIDER");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for TERMINATEPROVIDER access");
					return errorResult;

				}
				object = providerService.terminate(pkey, user);

			} else if (navigation.equalsIgnoreCase("activate")) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "ACTIVATEPROVIDER");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for ACTIVATEPROVIDER access");
					return errorResult;

				}
				object = providerService.activate(pkey, user);

			} else if (navigation.equalsIgnoreCase("renewal")) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "RENEWPROVIDER");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for RENEWPROVIDER access");
					return errorResult;

				}
				Date contractStart = WebUtil.getParameterDate(request,
						"contractStart");
				Date contractEnd = WebUtil.getParameterDate(request,
						"contractEnd");

				object = providerService.renewal(pkey, contractStart,
						contractEnd, user);

			}

			if (object == null) {
				request.setAttribute("alert", alertProperties
						.getMessage("not.found.provider", null, "fail", Locale
								.getDefault()));
			}
			else {
				return detailPerformed(request, response, location);
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("provider", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

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

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;
			
			String arah = WebUtil.getParameterString(request, "arah", "");
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			boolean sortProviderName = true, sortProviderCat = true, sortCity = true, sortProvince = true,
					sortCountry = true, sortMID = true, sortEdc = true, sortStatus = true, sortProviderCode = true, sortStatusProspek = true;
			boolean order = true;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			
			//CHECKING SORTING COLUMN
			if((!navigation.equalsIgnoreCase("gosearchbysort") && arah.isEmpty() && arah.equals("")) ||
					navigation.equals("gosearch")){
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}
			
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("listpolicy") ||
					navigation.equals("gosearchbysort") || (navigation.equals("") && !arah.isEmpty())) {

				if (searchby != null && searchtext!=null && !searchtext.equals("")) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerName");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("address")) {
						vLikeP.add("address");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("bank")) {
						vLikeP.add("bank");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
//					if (searchby.equalsIgnoreCase("description")) {
//						vLikeP.add("description");
//						vLikeQ.add(searchtext);
//					}
					if (searchby.equalsIgnoreCase("province")) {
						vLikeP.add("province");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("country")) {
						vLikeP.add("country");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("postalCode")) {
						vLikeP.add("postalCode");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("confirmation")) {
						vLikeP.add("confirmation");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerCategoryName")) {
						vLikeP.add("providerCategoryId.providerCategoryName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("contactPerson")) {
						vLikeP.add("contactPerson");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("expireDate")) {
						vLikeP.add("contractEndDate");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("startDate")) {
						vLikeP.add("contractStartDate");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerEdcCode")) {
						vLikeP.add("edcCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerCode")) {
						vLikeP.add("providerCode");
						vLikeQ.add(searchtext);
					}
				}
				if (searchStatus != null && searchStatus.intValue() > 0) {
					vEqP.add("statusId.statusId");
					vEqQ.add(searchStatus);
				}

			}

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));
			
			//Add 20150820 by FVO, for searching edc alert default
			//-==-Start-==-
			Vector vLeP = new Vector();
			Vector vLeQ = new Vector();
			Collection providerIds = null;
			Object ids[] = null;
			
			if(navigation.equalsIgnoreCase("searchedcalertdefault")){				
				//Coba bikin ambil query untuk provider id trus dibikin setIn
				Configuration conf = configurationService.get(0); //Ambil Client 0
				if(conf.getEdcAlertGlobal() != null){
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -conf.getEdcAlertGlobal());
					java.util.Date dateAlert = cal.getTime();
					providerIds = providerService.getProviderIdsEdcTerminalAlert(dateAlert);
					if(providerIds != null){
						if(providerIds.size() > 0){
							ids = new Object[providerIds.size()];
							providerIds.toArray(ids);
						}
					}
				}
			}else if(navigation.equalsIgnoreCase("searchedcalertcustom")){
				providerIds = providerService.getProviderIdsEdcTerminalAlertCustom(new java.util.Date());
				if(providerIds != null){
					if(providerIds.size()>0){
						ids = new Object[providerIds.size()];
						providerIds.toArray(ids);
					}
				}
			}else if(navigation.equalsIgnoreCase("searchrollpaperalertdefault")){
				Configuration conf = configurationService.get(0);
				if(conf != null){
					if(conf.getProviderRollPaperLimit() != null){
						providerIds = providerService.getProviderIdsRollPaperAlertDefault(conf.getProviderRollPaperLimit());
						if(providerIds != null){
							if(providerIds.size()>0){
								ids = new Object[providerIds.size()];
								providerIds.toArray(ids);
							}
						}
					}
				}
			}else if(navigation.equalsIgnoreCase("searchrollpaperalertcustom")){
				providerIds = providerService.getProviderIdsRollPaperAlertCustomize();
				if(providerIds != null){
					if(providerIds.size()>0){
						ids = new Object[providerIds.size()];
						providerIds.toArray(ids);
					}
				}
			}
			
			//-==-End-==-
			
			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			//Edit 20150821 by FVO for provider edc alert
			if(navigation.equalsIgnoreCase("searchedcalertdefault") || navigation.equalsIgnoreCase("searchedcalertcustom") ||
					navigation.equalsIgnoreCase("searchrollpaperalertcustom") || navigation.equalsIgnoreCase("searchrollpaperalertdefault")){
				if(ids == null){
					ids = new Object[1];
					ids[0] = Integer.valueOf(0);
				}
				count = providerService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ, ids);
			}else{
				count = providerService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			}


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
					"Provider.ProviderCategoryId", "Provider.StatusId",
			// foreign affairs end
			};
			
			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equalsIgnoreCase("providername")){
						sortByColumn = "providerName";
						Boolean sortByOrderProvName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderProvName", ""));
						sortProviderName = !sortByOrderProvName;
						order = sortProviderName;
					}else if(sortcolumn.equalsIgnoreCase("providercategory")){
						sortByColumn = "providerCategoryId.providerCategoryName";
						Boolean sortByOrderProvCat = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderProvCat", ""));
						sortProviderCat = !sortByOrderProvCat;
						order = sortProviderCat;
					}else if(sortcolumn.equalsIgnoreCase("providercity")){
						sortByColumn = "city";
						Boolean sortByOrderCity = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCity", ""));
						sortCity = !sortByOrderCity;
						order = sortCity;
					}else if(sortcolumn.equalsIgnoreCase("providerprovince")){
						sortByColumn = "province";
						Boolean sortByOrderProvince = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderProvince", ""));
						sortProvince = !sortByOrderProvince;
						order = sortProvince;
					}else if(sortcolumn.equalsIgnoreCase("providercountry")){
						sortByColumn = "country";
						Boolean sortByOrderCountry = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCountry", ""));
						sortCountry = !sortByOrderCountry;
						order = sortCountry;
					}else if(sortcolumn.equalsIgnoreCase("providermid")){
						sortByColumn = "edcCode";
						Boolean sortByOrderMID = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderMID", ""));
						sortMID = !sortByOrderMID;
						order = sortMID;
					}else if(sortcolumn.equalsIgnoreCase("providerstatus")){
						sortByColumn = "statusId.status";
						Boolean sortByOrderStatus = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderStatus", ""));
						sortStatus = !sortByOrderStatus;
						order = sortStatus;
					}else if(sortcolumn.equalsIgnoreCase("providercode")){
						sortByColumn = "providerCode";
						Boolean sortByOrderCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCode", ""));
						sortProviderCode = !sortByOrderCode;
						order = sortProviderCode;
					}else if(sortcolumn.equalsIgnoreCase("provideredc")){
						sortByColumn = "edcCode";
						Boolean sortByOrderEdc = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderEdc", ""));
						sortEdc = !sortByOrderEdc;
						order = sortEdc;
					}else{
						sortByColumn = "statusProspek";
						Boolean sortByOrderStatusProspek = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderStatusProspek", ""));
						sortStatusProspek = !sortByOrderStatusProspek;
						order = sortStatusProspek;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equalsIgnoreCase("providername")){
						sortProviderName = order;
					}else if(sortcolumn.equalsIgnoreCase("providercategory")){
						sortProviderCat = order;
					}else if(sortcolumn.equalsIgnoreCase("providercity")){
						sortCity = order;
					}else if(sortcolumn.equalsIgnoreCase("providerprovince")){
						sortProvince = order;
					}else if(sortcolumn.equalsIgnoreCase("providercountry")){
						sortCountry = order;
					}else if(sortcolumn.equalsIgnoreCase("providermid")){
						sortMID = order;
					}else if(sortcolumn.equalsIgnoreCase("providerstatus")){
						sortStatus = order;
					}else if(sortcolumn.equalsIgnoreCase("providercode")){
						sortProviderCode = order;
					}else{
						sortEdc = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				if(navigation.equalsIgnoreCase("searchedcalertdefault") || navigation.equalsIgnoreCase("searchedcalertcustom") ||
						navigation.equalsIgnoreCase("searchrollpaperalertdefault") || navigation.equalsIgnoreCase("searchrollpaperalertcustom")){
					collection = providerService.search(sLikeP, sLikeQ, sEqP, sEqQ, order, sortByColumn,
							required, ids, rowsetint, countSet.intValue());
				}else{
					collection = providerService.search(sLikeP, sLikeQ, sEqP, sEqQ, order, sortByColumn,
							required, rowsetint, countSet.intValue());
				}
			}else{
				if(navigation.equalsIgnoreCase("searchedcalertdefault") || navigation.equalsIgnoreCase("searchedcalertcustom") || 
						navigation.equalsIgnoreCase("searchrollpaperalertdefault") || navigation.equalsIgnoreCase("searchrollpaperalertcustom")){
					collection = providerService.search(sLikeP, sLikeQ, sEqP, sEqQ,
							required, ids, rowsetint, countSet.intValue());
				}else{
					collection = providerService.search(sLikeP, sLikeQ, sEqP, sEqQ,
							required, rowsetint, countSet.intValue());
				}
			}
			

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
				if(navigation.equalsIgnoreCase("searchedcalertdefault") || navigation.equalsIgnoreCase("searchedcalertcustom") ||
						navigation.equalsIgnoreCase("searchrollpaperalertdefault") || navigation.equalsIgnoreCase("searchrollpaperalertcustom")){
					collection = providerService.search(sLikeP, sLikeQ, sEqP, sEqQ,
							required, ids, rowsetint, countSet.intValue());
				}else{
					collection = providerService.search(sLikeP, sLikeQ, sEqP, sEqQ,
							required, rowsetint, countSet.intValue());
				}
			}

			request.setAttribute("status", searchStatus);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("sortcolumn", sortcolumn);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Providers", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("sortCity", sortCity);
			request.setAttribute("sortCountry", sortCountry);
			request.setAttribute("sortEdc", sortEdc);
			request.setAttribute("sortMID", sortMID);
			request.setAttribute("sortProviderCat", sortProviderCat);
			request.setAttribute("sortProviderName", sortProviderName);
			request.setAttribute("sortProvince", sortProvince);
			request.setAttribute("sortStatus", sortStatus);
			request.setAttribute("sortProviderCode", sortProviderCode);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView report(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView result = new ModelAndView("reportProvider");

		try {
			Integer pageNo = WebUtil.getParameterInteger(request, "pageNo");
			if(pageNo == null || (pageNo < 1)) {
				pageNo = 1;
			}

			String navigation = WebUtil.getParameterString(request, "navigation", "");
			String searchText = WebUtil.getParameterString(request, "searchText", "");
			String searchBy = WebUtil.getParameterString(request, "searchBy", "");

			String arah = WebUtil.getParameterString(request, "arah", "");

			boolean isSearchReport = "searchReport".equals(navigation);
			boolean isGenerateReport = "generateReport".equals(navigation);

			boolean hasSearchText = (searchBy != null && searchText!=null && !searchText.equals(""));

			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			if (hasSearchText) {

				if ("providerId.providerName".equals(searchBy)) {
					vLikeP.add("providerId.providerName");
					vLikeQ.add(searchText);
				}

				if ("deviceNumber".equals(searchBy)) {
					vLikeP.add("deviceNumber");
					vLikeQ.add(searchText);
				}

				if ("providerId.providerCode".equals(searchBy)) {
					//vLikeP.add("providerId.providerCode");
					vLikeP.add("providerId.edcCode");
					vLikeQ.add(searchText);
				}

				if ("providerId.address".equals(searchBy)) {
					vLikeP.add("providerId.address");
					vLikeQ.add(searchText);
				}

				//lama EDC dipakai

				//jumlah transaksi selama EDC dipakai

				if ("providerId.contactPerson".equals(searchBy)) {
					vLikeP.add("providerId.contactPerson");
					vLikeQ.add(searchText);
				}

				if ("providerId.telephone".equals(searchBy)) {
					vLikeP.add("providerId.telephone");
					vLikeQ.add(searchText);
				}
			}

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			int totalRow = edcTerminalService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			int limitRow = countSet.intValue();
			int maxPage = (totalRow % limitRow) == 0 ? (totalRow/limitRow) : (totalRow/limitRow) + 1;

			if (totalRow < limitRow) maxPage = 1;

			if (pageNo > maxPage) pageNo = maxPage;

			if (arah.equals("kanan")) pageNo++;
			else if (arah.equals("kiri")) pageNo--;
			else if (arah.equals("kiribgt")) pageNo = 1;
			else if (arah.equals("kananbgt")) pageNo = maxPage;

			int startRow = (pageNo * limitRow) - limitRow + 1;
			int endRow = (pageNo * limitRow);

			String required[] = new String[] {
					// foreign affairs
					"EdcTerminal.ProviderId"
					// foreign affairs end
			};


			Collection edcTerminals = edcTerminalService.search (sLikeP,sLikeQ,sEqP,sEqQ,required,
					isGenerateReport ? 0 : startRow - 1,
					isGenerateReport ? totalRow : limitRow);

			Map<Integer, Integer> totalTrxPerTerminalMap = new HashMap<Integer, Integer>();

			for (Object object : edcTerminals) {
				EdcTerminal edcTerminal = (EdcTerminal) object;

				String merchantCode = edcTerminal.getProviderId().getEdcCode();
				String terminalCode = edcTerminal.getDeviceNumber();

				//int totalTrx = edcTransactionLogService.getTotalTransaction(merchantCode, terminalCode);
				int totalTrx = caseService.getTotalProviderCaseManualRegistration(edcTerminal.getProviderId().getProviderId(), edcTerminal.getId());
				
				//System.out.println("TOTAL TRX : "+totalTrx);

				totalTrxPerTerminalMap.put(edcTerminal.getId(), totalTrx);
			}

			if(isGenerateReport) {
				HSSFWorkbook workbook = ProviderReportDownloader.generateReportEdcTerminal(edcTerminals, totalTrxPerTerminalMap);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "providerReport.xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				// sos.write(workbook.getBytes());
				sos.close();
			}

			result.addObject("edcTerminals", edcTerminals);
			result.addObject("totalTrxPerTerminalMap", totalTrxPerTerminalMap);

			request.setAttribute("searchText", searchText);
			request.setAttribute("searchBy", searchBy);

			request.setAttribute("navigation", navigation);

			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalRow", totalRow);
			request.setAttribute("limitRow", limitRow);
			request.setAttribute("maxPage", maxPage);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchExclusionPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = 0;

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer searchStatus = WebUtil.getParameterInteger(request,"status");
			
			Integer policyId = WebUtil.getParameterInteger(request,"policyId");
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			
			String memberGroupName = WebUtil.getParameterString(request, "memberGroupName", "");
			String clientName = WebUtil.getParameterString(request, "clientName", "");
			String policyNumber = WebUtil.getParameterString(request, "policyNumber", "");
			
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;
			

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;
			
			String arah = WebUtil.getParameterString(request, "arah", "");
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			boolean sortProviderName = true, sortProviderCat = true, sortCity = true, sortProvince = true,
					sortCountry = true, sortMID = true, sortEdc = true, sortStatus = true, sortProviderCode = true;
			boolean order = true;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			
			//CHECKING SORTING COLUMN
			if((!navigation.equalsIgnoreCase("gosearchexclusionbysort") && arah.isEmpty() && arah.equals("")) ||
					navigation.equals("gosearch")){
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}
			
			String subject = "";
			String type = "";
			
			if (policyId != null){
				subject = policyNumber;
				type = "Policy Number ";
				collection = providerService.searchProviderReport(null, policyId, null);
			}
			if (memberGroupId != null){
				subject = memberGroupName;
				type = "Group Name ";
				collection = providerService.searchProviderReport(null,null,memberGroupId);
			}
			if (clientId != null){
				subject = clientName;
				type = "Client Name ";
				collection = providerService.searchProviderReport(clientId,null,null);
			}
			
			
			
			

			
			request.setAttribute("status", searchStatus);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("sortcolumn", sortcolumn);
			
			request.setAttribute("memberGroupName", memberGroupName);
			request.setAttribute("clientName", clientName);
			request.setAttribute("policyNumber", policyNumber);
			request.setAttribute("memberGroupId", memberGroupId);
			request.setAttribute("clientId", clientId);
			request.setAttribute("policyId", policyId);
			

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */
			
			if (policyId == null && memberGroupId == null && clientId == null){
				collection = new Vector<Provider>();
			}
			
			if (navigation.equalsIgnoreCase("downloadexclusion")){
				HSSFWorkbook workbook = ProviderReportDownloader.downloadExclusionReport(collection,subject,type);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "provider-Exclusion.xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				sos.close();

			}

			result.addObject("Providers", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("sortCity", sortCity);
			request.setAttribute("sortCountry", sortCountry);
			request.setAttribute("sortEdc", sortEdc);
			request.setAttribute("sortMID", sortMID);
			request.setAttribute("sortProviderCat", sortProviderCat);
			request.setAttribute("sortProviderName", sortProviderName);
			request.setAttribute("sortProvince", sortProvince);
			request.setAttribute("sortStatus", sortStatus);
			request.setAttribute("sortProviderCode", sortProviderCode);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView searchFacilitiesPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"providerName", "");
			
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			

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
			
			//CHECKING SORTING COLUMN

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));
			
			if (providerId != null){
				String[] provReq = {"Provider.StatusId","Provider.ProviderCurrencyId"};
				Provider provider = providerService.get(providerId,provReq);
				
				result.addObject("provider", provider);
				
				String[] eqParam = {"providerId.providerId","deletedStatus"};
				Object[] eqValue = {providerId,Integer.valueOf(0)};
				
				int totalPoliklinik = providerPoliklinikService.getTotal(null,null,eqParam,eqValue);
				Collection<ProviderPoliklinik> poliList = providerPoliklinikService.search(null,null,eqParam,eqValue,0,totalPoliklinik);
				
				int totalDoctor = providerDoctorService.getTotal(null,null,eqParam,eqValue);
				Collection<ProviderDoctor> doctorList = providerDoctorService.search(null,null,eqParam,eqValue,0,totalDoctor);
				
				int totalItem = providerItemService.getTotal(null,null,eqParam,eqValue);
				Collection<ProviderItem> itemList = providerItemService.search(null,null,eqParam,eqValue,0,totalItem);
				
				result.addObject("PoliklinikList", poliList);
				result.addObject("doctorList", doctorList);
				result.addObject("ProviderItemList", itemList);
				
				if (navigation.equalsIgnoreCase("downloadfacilities")){
					HSSFWorkbook workbook = ProviderReportDownloader.generateFacilitiesReport(provider, poliList, doctorList, itemList);

					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);

					response.setHeader("Content-disposition", "inline; filename="
							+ "provider-Facilities.xls");

					ServletOutputStream sos = response.getOutputStream();

					workbook.write(sos);
					sos.close();
				}				
			}
			
			




			String required[] = new String[] {
			// foreign affairs
					"Provider.ProviderCategoryId", "Provider.StatusId",
			// foreign affairs end
			};
			
			

			request.setAttribute("status", searchStatus);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			
			

			request.setAttribute("countSet", countSet);

			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			
			
			request.setAttribute("providerId", providerId);
			request.setAttribute("providerName", searchtext);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchMemberPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			//Add by aju on 20150929, for handle iFrame things fufufu
			if(isIFrameSession && iFrameLevelLogin.equalsIgnoreCase("member")){
				System.out.println("it\'s still on member(" + sessionMemberId + ") login session fufufu...");
				memberId = (memberId==null?Integer.parseInt(sessionMemberId):memberId);
				Member theMember = memberService.get(memberId);
				if(theMember!=null){
					System.out.println("checking->requestParentMemberId(" + theMember.getParentMember().getMemberId().toString() + ") vs sessionParentMemberId(" + sessionMemberParentId + ")");
					if(!theMember.getParentMember().getMemberId().toString().equals(sessionMemberParentId)){
						memberId=Integer.parseInt(sessionMemberId);
						ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
						errorResult.addObject("errorType","OtherFamilyAccessDenied");			
						errorResult.addObject("errorMessage", "Hey member("+ sessionMemberId + "), are you missing your way? :P<br/>" 
								+ "<a href=\"provider?navigation=listmember&memberId="+memberId+"\">Go Back</a>");
						return errorResult;
					}
				}
			}

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
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equalsIgnoreCase("golistmember")) {

				if (searchby != null) {
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerName");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("address")) {
						vLikeP.add("address");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("bank")) {
						vLikeP.add("bank");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("providerCategoryId.providerCategoryName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("province")) {
						vLikeP.add("province");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("country")) {
						vLikeP.add("country");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("postalCode")) {
						vLikeP.add("postalCode");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("confirmation")) {
						vLikeP.add("confirmation");
						vLikeQ.add(searchtext);
					}
//					if (searchby.equalsIgnoreCase("providerCategoryName")) {
//						vLikeP.add("providerCategoryId.providerCategoryName");
//						vLikeQ.add(searchtext);
//					}
					if (searchby.equalsIgnoreCase("contactPerson")) {
						vLikeP.add("contactPerson");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("expireDate")) {
						vLikeP.add("contractEndDate");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("startDate")) {
						vLikeP.add("contractStartDate");
						vLikeQ.add(searchtext);
					}
				}
				if (searchStatus != null && searchStatus.intValue() > 0) {
					vEqP.add("statusId.statusId");
					vEqQ.add(searchStatus);
				}

			}

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = providerService.getTotalMemberProvider(sLikeP, sLikeQ, sEqP, sEqQ,memberId);

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

			String required[] = new String[] {"Provider.ProviderCategoryId", "Provider.StatusId"};
			collection = providerService.searchMemberProvider(sLikeP, sLikeQ, sEqP, sEqQ,
					memberId,required, rowsetint, countSet.intValue());

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
				collection = providerService.searchMemberProvider(sLikeP, sLikeQ, sEqP, sEqQ,
						memberId,required, rowsetint, countSet.intValue());
			}

			if (memberId != null){

				String[] requiredMember = {"Member.MemberGroupId","Member.ClientId",
						"Member.RelationshipId","Member.ParentMember","Member.CurrentPolicyId"};
				
				Member object = memberService.get(memberId,requiredMember);
				result.addObject("member", object);
				
				//hitung umur
				Date umur =  object.getBirthday();
				Calendar dob = Calendar.getInstance();  
				dob.setTime(umur);  
				Calendar today = Calendar.getInstance();  
				int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR); 
				if ((today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
				    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) ||
				    today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
					age--;  
				}
				result.addObject("age", age);
				//end
			}
			request.setAttribute("status", searchStatus);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("memberId", memberId);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Providers", collection);

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
	public ModelAndView searchCapitationPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

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
			if (navigation.equals("gosearchcapitation")) {

				if (searchby != null) {					

					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerName");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("address")) {
						vLikeP.add("address");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
//					if (searchby.equalsIgnoreCase("description")) {
//						vLikeP.add("description");
//						vLikeQ.add(searchtext);
//					}
					if (searchby.equalsIgnoreCase("province")) {
						vLikeP.add("province");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("country")) {
						vLikeP.add("country");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("postalCode")) {
						vLikeP.add("postalCode");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("confirmation")) {
						vLikeP.add("confirmation");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerCategoryName")) {
						vLikeP.add("providerCategoryId.providerCategoryName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("contactPerson")) {
						vLikeP.add("contactPerson");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("expireDate")) {
						vLikeP.add("contractEndDate");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("startDate")) {
						vLikeP.add("contractStartDate");
						vLikeQ.add(searchtext);
					}
				}
				if (searchStatus != null && searchStatus.intValue() > 0) {
					vEqP.add("statusId.statusId");
					vEqQ.add(searchStatus);
				}

			}

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));
			
			vEqP.add("isUsingCapitation");
			vEqQ.add(1);

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = providerService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"Provider.ProviderCategoryId", "Provider.StatusId",
			// foreign affairs end
			};
			collection = providerService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required, rowsetint, countSet.intValue());

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
				collection = providerService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
			}

			request.setAttribute("status", searchStatus);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Providers", collection);

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


	public ModelAndView searchUnAssignedPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			String currentNavigation = WebUtil.getParameterString(request,
					"currentnavigation", "");
			String subNavigation = WebUtil.getParameterString(request,
					"subnavigation", "");

			if (currentNavigation.equalsIgnoreCase("addclientprovider")) {
				location = "addClientProvider";
			}

			result = new ModelAndView(location);

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
			if (navigation.equals("gosearchaddclientprovider")
					|| navigation.equals("golookup")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerName");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("address")) {
						vLikeP.add("address");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
//					if (searchby.equalsIgnoreCase("description")) {
//						vLikeP.add("description");
//						vLikeQ.add(searchtext);
//					}
					
					if (searchby.equalsIgnoreCase("province")) {
						vLikeP.add("province");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("country")) {
						vLikeP.add("country");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("postalCode")) {
						vLikeP.add("postalCode");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("confirmation")) {
						vLikeP.add("confirmation");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerCategoryName")) {
						vLikeP.add("providerCategoryId.providerCategoryName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("contactPerson")) {
						vLikeP.add("contactPerson");
						vLikeQ.add(searchtext);
					}
				}
				if (searchStatus != null && searchStatus.intValue() > 0) {
					vEqP.add("statusId.statusId");
					vEqQ.add(searchStatus);
				}

			}

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			// count = providerService.getTotalUnassignedProvider(sLikeP,
			// sLikeQ, sEqP, sEqQ,clientId);

			count = providerService.getTotalUnassignedProvider(clientId,
					searchtext, searchby);

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
					"Provider.ProviderCategoryId", "Provider.StatusId",
			// foreign affairs end
			};
			// collection = providerService.searchUnassignedProvider(sLikeP,
			// sLikeQ, sEqP, sEqQ,
			// required, rowsetint, clientId,countSet.intValue());
			collection = providerService.getUnassignedProvider(clientId,
					searchtext, searchby, rowsetint, countSet.intValue());

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
				// collection = providerService.search(sLikeP, sLikeQ, sEqP,
				// sEqQ,
				// required, rowsetint, countSet.intValue());

				collection = providerService.getUnassignedProvider(clientId,
						searchtext, searchby, rowsetint, countSet.intValue());

			}

			request.setAttribute("status", searchStatus);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Providers", collection);
			request.setAttribute("clientId", clientId);
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

	public ModelAndView lookupJsonPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			
			System.out.println("lookup json provider dipanggil");
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			
			//Add by aju on 20150821, for order by fufufu :D
			String orderResult = WebUtil.getParameterString(request, "orderResult", "false");
			String orderAsc = WebUtil.getParameterString(request, "orderAsc", "false");
			String orderBy = WebUtil.getParameterString(request, "orderBy", "");
			
			String q = WebUtil.getParameterString(request, "q", "");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			int total = 0;

			
			if (navigation.equalsIgnoreCase("lookupjson")){
				
				Collection<Provider> provider = null;
				
				if(memberId != null){
					
					Member member = null;
					String requiredMember[] = new String[] {
							// foreign affairs
									"Member.CurrentPolicyId",
							// foreign affairs end
							};
					
					member = memberService.get(memberId, requiredMember);
					
					System.out.println("member = " + member.getMemberId() );
				
					if(member.getCurrentPolicyId().getProviderAllocationType() == 1){
						Integer clientId = member.getClientId().getClientId();
						
						System.out.println("clientId = " +clientId );
						provider = providerService.searchClientProvider(q, clientId);
					}else if(member.getCurrentPolicyId().getProviderAllocationType() == 2){
						Integer groupId = member.getMemberGroupId().getMemberGroupId();
						provider = providerService.searchMemberGroupProvider(q, groupId);
					}else if(member.getCurrentPolicyId().getProviderAllocationType() == 3){
						provider = providerService.searchMemberProvider(q, memberId);
					}else if(member.getCurrentPolicyId().getProviderAllocationType() == 4){
						Integer policyId = member.getCurrentPolicyId().getPolicyId();
						provider = providerService.searchPolicyProvider2(q, policyId);
					}else if(member.getCurrentPolicyId().getProviderAllocationType() == 0 ){
						provider = providerService.searchProvider(q);
					}
				}else{
					provider = providerService.searchProvider(q);
				}
					
				result.addObject("Providers", provider);
			}
			else if (navigation.equalsIgnoreCase("lookupjsonpolicy")){
				Integer policyId = WebUtil.getParameterInteger(request, "policyId");
				Collection<Provider> provider = providerService.searchPolicyProvider(q, policyId);
				
				result.addObject("Providers", provider);
			}else if(navigation.equalsIgnoreCase("jsontotalprovideredcalertcustom")){
				result = new ModelAndView("jsonTotalProviderEdcAlert");
				java.util.Date alertDate = new java.util.Date();
				total = providerService.getTotalProviderEdcTerminalAlertCustom(alertDate);
				result.addObject("result", total);
			}
			//Add by aju on 20150821
			else if (navigation.equalsIgnoreCase("getproviderlistjson")){
				String required[] = new String[] {
						// foreign affairs
								"Provider.ProviderCategoryId", "Provider.StatusId",
						// foreign affairs end
						};
				//Collection<Provider> provider = providerService.getAll("deletedStatus", 0, required);
				Collection<Provider> provider = null;
				if(Boolean.parseBoolean(orderResult)){
					boolean isAscending = Boolean.parseBoolean(orderAsc);
					provider = providerService.getAll("deletedStatus", 0, required, true, isAscending, orderBy);
				}else{
					provider = providerService.getAll("deletedStatus", 0, required, false, false, null);
				}
	
				result.addObject("Providers", provider);
			}//Add 20150901 by FVO
			else if(navigation.equalsIgnoreCase("jsontotalrollpaperalertdefault")){
				result = new ModelAndView("jsonTotalProviderEdcAlert");
				Configuration conf = configurationService.get(0);
				if(conf!=null){
					if(conf.getProviderRollPaperLimit()!=null)
						total = providerService.getTotalProviderRollPaperAlertDefault(conf.getProviderRollPaperLimit());
				}
				result.addObject("result", total);
			}else if(navigation.equalsIgnoreCase("jsontotalrollpaperalertcustom")){
				result = new ModelAndView("jsonTotalProviderEdcAlert");
				Configuration conf = configurationService.get(0);
				if(conf!=null){
					total = providerService.getTotalProviderRollPaperAlertCustom();
				}
				result.addObject("result", total);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	
	public ModelAndView lookupJsonPerformedWithAllocation(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			
			System.out.println("lookup json provider with allocation dipanggil");
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			
			//Add by aju on 20150821, for order by fufufu :D
			String orderResult = WebUtil.getParameterString(request, "orderResult", "false");
			String orderAsc = WebUtil.getParameterString(request, "orderAsc", "false");
			String orderBy = WebUtil.getParameterString(request, "orderBy", "");
			
			String q = WebUtil.getParameterString(request, "q", "");
			
			String memberId = WebUtil.getParameterString(request, "memberId", "");
			int total = 0;
			
			if (navigation.equalsIgnoreCase("lookupjson")){
				Collection<Provider> provider = providerService.searchProvider(q);
	
				result.addObject("Providers", provider);
			}
			else if (navigation.equalsIgnoreCase("lookupjsonpolicy")){
				Integer policyId = WebUtil.getParameterInteger(request, "policyId");
				Collection<Provider> provider = providerService.searchPolicyProvider(q, policyId);
				
				result.addObject("Providers", provider);
			}else if(navigation.equalsIgnoreCase("jsontotalprovideredcalertcustom")){
				result = new ModelAndView("jsonTotalProviderEdcAlert");
				java.util.Date alertDate = new java.util.Date();
				total = providerService.getTotalProviderEdcTerminalAlertCustom(alertDate);
				result.addObject("result", total);
			}
			//Add by aju on 20150821
			else if (navigation.equalsIgnoreCase("getproviderlistjson")){
				String required[] = new String[] {
						// foreign affairs
								"Provider.ProviderCategoryId", "Provider.StatusId",
						// foreign affairs end
						};
				//Collection<Provider> provider = providerService.getAll("deletedStatus", 0, required);
				Collection<Provider> provider = null;
				if(Boolean.parseBoolean(orderResult)){
					boolean isAscending = Boolean.parseBoolean(orderAsc);
					provider = providerService.getAll("deletedStatus", 0, required, true, isAscending, orderBy);
				}else{
					provider = providerService.getAll("deletedStatus", 0, required, false, false, null);
				}
	
				result.addObject("Providers", provider);
			}//Add 20150901 by FVO
			else if(navigation.equalsIgnoreCase("jsontotalrollpaperalertdefault")){
				result = new ModelAndView("jsonTotalProviderEdcAlert");
				Configuration conf = configurationService.get(0);
				if(conf!=null){
					if(conf.getProviderRollPaperLimit()!=null)
						total = providerService.getTotalProviderRollPaperAlertDefault(conf.getProviderRollPaperLimit());
				}
				result.addObject("result", total);
			}else if(navigation.equalsIgnoreCase("jsontotalrollpaperalertcustom")){
				result = new ModelAndView("jsonTotalProviderEdcAlert");
				Configuration conf = configurationService.get(0);
				if(conf!=null){
					total = providerService.getTotalProviderRollPaperAlertCustom();
				}
				result.addObject("result", total);
			}

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
		

		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);

		String breadcrumb = "";
		try {
			
			//add aju on 20150929, new iFrame checker fufufu plus init
//			System.out.println("ProviderController::handleRequest()...");
			isIFrameSession = securityService.isUsingIFrameSession(request);
			iFrameLevelLogin = securityService.getTheIFrameLevelLogin();
//			System.out.println("securityService->SessionMemberId->" + securityService.getTheSessionMemberId());
			sessionMemberId = securityService.getTheSessionMemberId();
//			System.out.println("securityService->SessionParentMemberId->" + securityService.getTheSessionMemberParentId());
			sessionMemberParentId = securityService.getTheSessionMemberParentId();
			
			
			if (navigation.equalsIgnoreCase("detail")) {
				
				result = detailPerformed(request, response, "detailProvider");
				String providerId = request.getParameter("providerId");
				if(listnavigation.equalsIgnoreCase("searchcapitation"))
				{
					breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"&listnavigation="+listnavigation+"\" class=\"linkbreadcrumb\">Detail Provider</a>";
				}
				else
				{
					breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider</a>";
				}
				
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
				
			}
			else if (navigation.equalsIgnoreCase("block") || navigation.equalsIgnoreCase("confirmblock")){
				result = blockPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("unblock") || navigation.equalsIgnoreCase("confirmunblock")){
				result = unblockPerformed(request, response);
			}
			
			else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchProvider");breadcrumb = "<a href=\"provider?navigation=search&searchtext=&searchby=&index=1\" class=\"linkbreadcrumb\">Search Provider</a>";
				
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupProvider");
			} else if (navigation.equalsIgnoreCase("lookupjson") || navigation.equalsIgnoreCase("lookupjsonpolicy") ||
					navigation.equalsIgnoreCase("jsontotalprovideredcalertcustom") || navigation.equalsIgnoreCase("jsontotalrollpaperalertdefault") ||
					navigation.equalsIgnoreCase("jsontotalrollpaperalertcustom")) { //Edit 20150821 by FVO, for get Provider edc alert
				result = lookupJsonPerformed(request, response,
						"lookupProviderJson");
			//Add by aju on 20150820, for creating json all provider list :D
			} else if (navigation.equalsIgnoreCase("getproviderlistjson")) {
				result = lookupJsonPerformed(request, response,
						"providerListJson");
			} else if (navigation.equalsIgnoreCase("addclientprovider")
					|| navigation.equalsIgnoreCase("goearchaddclientprovider")) {
				
				result = searchUnAssignedPerformed(request, response,
						"addClientProvider");
				
			} else if (navigation.equalsIgnoreCase("terminate")
					|| navigation.equals("activate")
					|| navigation.equalsIgnoreCase("renewal")) {
				
				result = updateStatusPerformed(request, response,
						"detailProvider");
				
				String providerId = request.getParameter("providerId");
				breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider</a>";
			}
			else if(navigation.equalsIgnoreCase("searchexclusion") || navigation.equalsIgnoreCase("gosearchexclusion") || navigation.equalsIgnoreCase("downloadexclusion") || navigation.equalsIgnoreCase("gosearchexclusionbysort")){
				result = searchExclusionPerformed(request, response, "searchProviderExclusion");
				String memberId = request.getParameter("memberId");
				breadcrumb = "<a href=\"member?navigation=detail&memberId="+memberId+"\" class=\"linkbreadcrumb\">Detail Member</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Member Provider";
			}
			else if(navigation.equalsIgnoreCase("searchfacilities") || navigation.equalsIgnoreCase("gosearchfacilities") || navigation.equalsIgnoreCase("downloadfacilities")){
				result = searchFacilitiesPerformed(request, response, "searchProviderFacilities");
				String memberId = request.getParameter("memberId");
				breadcrumb = "<a href=\"member?navigation=detail&memberId="+memberId+"\" class=\"linkbreadcrumb\">Detail Member</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Member Provider";
			}
			else if (navigation.equalsIgnoreCase("showprovidermap")){
				result = showProviderMapPerformed(request, response, "showProviderMap");
			}
			else if (navigation.equalsIgnoreCase("loadprovidermapjson")){
				result = searchProviderMapPerformed(request, response, "lookupProviderMapJson");
			}
			else if (navigation.equalsIgnoreCase("showmap")){
				result = searchPerformed(request, response, "searchProviderMap");
			}
			else if (navigation.equalsIgnoreCase("searchcapitation") || navigation.equalsIgnoreCase("gosearchcapitation")){
				result = searchCapitationPerformed(request, response, "searchProviderFund");
				breadcrumb = "<a href=\"provider?navigation=searchcapitation\" class=\"linkbreadcrumb\">Search Capitation</a>";
			}
			else if(navigation.equalsIgnoreCase("listmember") || navigation.equalsIgnoreCase("golistmember")){
				result = searchMemberPerformed(request, response, "listMemberProvider");
				String memberId = request.getParameter("memberId");
				breadcrumb = "<a href=\"member?navigation=detail&memberId="+memberId+"\" class=\"linkbreadcrumb\">Detail Member</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Member Provider";
			}
			else if(navigation.equalsIgnoreCase("searchedcalertdefault") || navigation.equalsIgnoreCase("searchedcalertcustom")){
				result = searchPerformed(request, response, "searchProvider");
				breadcrumb = "<a href=\"provider?navigation=search&searchtext=&searchby=&index=1&listnavigation=searchcapitation\" class=\"linkbreadcrumb\">Search Provider</a>";
			}
			else if("searchReport".equalsIgnoreCase(navigation) || "generateReport".equals(navigation)) {
				result = report(request, response);
				breadcrumb = "<a href=\"provider?navigation=searchReport\" class=\"linkbreadcrumb\">Provider Report</a>";
			}
			else {
				result = searchPerformed(request, response, "searchProvider");
				if(navigation.equalsIgnoreCase("searchcapitation"))
				{
					breadcrumb = "<a href=\"provider?navigation=search&searchtext=&searchby=&index=1&listnavigation=searchcapitation\" class=\"linkbreadcrumb\">Search Provider</a>";
				}else 
				{
					breadcrumb = "<a href=\"provider?navigation=search&searchtext=&searchby=&index=1\" class=\"linkbreadcrumb\">Search Provider</a>";
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
