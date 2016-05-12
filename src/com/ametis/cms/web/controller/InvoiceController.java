package com.ametis.cms.web.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.ExcessCharge;
import com.ametis.cms.datamodel.Invoice;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.dto.ExcessDto;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.ExcessChargeService;
import com.ametis.cms.service.InvoiceItemService;
import com.ametis.cms.service.InvoiceService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.Converter;
import com.ametis.cms.util.MoneyParser;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * Invoice is a servlet controller for invoice Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class InvoiceController implements Controller

// extends+

// extends-

{

	private InvoiceService invoiceService;
	private InvoiceItemService invoiceItemService;
	private ClaimItemService claimItemService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	
	private MemberGroupService memberGroupService;
	private MemberService memberService;
	private ConfigurationService configurationService;
	
	private ExcessChargeService excessChargeService;

	private SecurityService securityService;


private ActivityLogService logService;

	
	
	public InvoiceItemService getInvoiceItemService() {
	return invoiceItemService;
}

public void setInvoiceItemService(InvoiceItemService invoiceItemService) {
	this.invoiceItemService = invoiceItemService;
}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}

	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}

	public ExcessChargeService getExcessChargeService() {
		return excessChargeService;
	}

	public void setExcessChargeService(ExcessChargeService excessChargeService) {
		this.excessChargeService = excessChargeService;
	}

	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
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

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	public InvoiceService getInvoiceService() {
		return this.invoiceService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer invoiceId = WebUtil.getParameterInteger(request,
					"invoiceId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = invoiceId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DELETEINVOICE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DELETEINVOICE access");
				return errorResult;

			}
			Invoice res = invoiceService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.invoice", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.invoice", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchInvoice");
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
			Integer invoiceId = WebUtil.getParameterInteger(request,
					"invoiceId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			result = new ModelAndView(location);
			java.io.Serializable pkey = invoiceId;
			Invoice object = invoiceService.get(pkey);

			if (object == null) {
				request
						.setAttribute("alert", alertProperties.getMessage(
								"not.found.invoice", null, "fail", Locale
										.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("invoice", object);
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
	public ModelAndView lookupJsonTotalPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			String navigation = request.getParameter("navigation");
			result = new ModelAndView("jsonTotalPendingInvoice");
			
			int total = 0;
			
			if (navigation.equalsIgnoreCase("jsontotalopen")){
				total = invoiceService.getTotalPendingInvoice();
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
	public ModelAndView printPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer invoiceId = WebUtil.getParameterInteger(request,
					"invoiceId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
	

			result = new ModelAndView(location);
			java.io.Serializable pkey = invoiceId;
			Invoice object = invoiceService.get(pkey);
			
			Configuration configuration = configurationService.getSystemConfiguration();

			MemberGroup memberGroup = object.getMemberGroupId();
			

			String reportFile = "ExcessReport"+"_"+configuration.getCompanyCode().toLowerCase()+".rptdesign";
			result = new ModelAndView(new RedirectView("run"));
		
			System.out.println("REPORT FILE : " + reportFile);
			result.addObject("__report", "reports/"+reportFile);
			result.addObject("__format", "pdf");
			
			result.addObject("GroupId", memberGroup.getMemberGroupId());
			
			
			
			
			result.addObject("invoice_date",object.getInvoiceDate());
			result.addObject("invoice_due_date",object.getInvoiceDueDate());
			

			result.addObject("contactPerson",memberGroup.getContactPerson());
			result.addObject("halaman1Body", "");
			result.addObject("groupName", memberGroup.getGroupName());
			result.addObject("totalInvoiceValue", object.getOutstanding());
			result.addObject("totalValueString", MoneyParser.convert(object.getOutstanding().longValue()));
			result.addObject("nomorPolis", memberGroup.getPolicyNumber());
			result.addObject("invoiceId", invoiceId);
			result.addObject("coveragePeriode", "");
			result.addObject("currency", "IDR");
			result.addObject("groupAddress", memberGroup.getAddress());
			
			result.addObject("excessPeriode", "");
			
			
			
			
			result.addObject("invoice", object);
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
	public ModelAndView printReceiptPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer invoiceId = WebUtil.getParameterInteger(request,
					"invoiceId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
	

			java.io.Serializable pkey = invoiceId;
			Invoice object = invoiceService.get(pkey);
			
			
			
			Configuration configuration = configurationService.getSystemConfiguration();
			
			location = location + "_" + configuration.getCompanyCode();
			

			result = new ModelAndView(location);
			result.addObject("configuration", configuration);

			MemberGroup memberGroup = object.getMemberGroupId();
			
			result.addObject("GroupId", memberGroup.getMemberGroupId());
			
			
			
			
			result.addObject("invoice_date",object.getInvoiceDate());
			result.addObject("invoice_due_date",object.getInvoiceDueDate());
			

			result.addObject("contactPerson",memberGroup.getContactPerson());
			result.addObject("halaman1Body", "");
			result.addObject("groupName", memberGroup.getGroupName());
			result.addObject("totalInvoiceValue", object.getOutstanding());
			result.addObject("totalValueString", MoneyParser.convert(object.getOutstanding().longValue()));
			result.addObject("nomorPolis", memberGroup.getPolicyNumber());
			result.addObject("invoiceId", invoiceId);
			result.addObject("coveragePeriode", "");
			result.addObject("currency", "IDR");
			result.addObject("groupAddress", memberGroup.getAddress());
			
			result.addObject("excessPeriode", "");
			
			
			
			
			result.addObject("invoice", object);
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
	public ModelAndView printRecapitulationPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer invoiceId = WebUtil.getParameterInteger(request,
					"invoiceId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
	

			java.io.Serializable pkey = invoiceId;
			Invoice object = invoiceService.get(pkey);
			
			
			
			Configuration configuration = configurationService.getSystemConfiguration();
			
			location = location + "_" + configuration.getCompanyCode();


			result = new ModelAndView(location);
			MemberGroup memberGroup = object.getMemberGroupId();
			
			if (memberGroup != null){
				result.addObject("GroupId", memberGroup.getMemberGroupId());
				result.addObject("nomorPolis", memberGroup.getPolicyNumber());
				result.addObject("contactPerson",memberGroup.getContactPerson());			
				result.addObject("groupName", memberGroup.getGroupName());
				result.addObject("groupAddress", memberGroup.getAddress());
			}
			

			if (object.getInvoiceType() != null && object.getInvoiceType().intValue() == Invoice.EXCESS_INVOICE){
				String[] eqParam = {"invoiceId.invoiceId","deletedStatus"};
				Object[] eqValue = {pkey, Integer.valueOf(0)};
				
				int total = excessChargeService.getTotal(null,null,eqParam,eqValue);
				Collection<ExcessCharge> excessList = excessChargeService.search(null,null,eqParam,eqValue,0,total);
				result.addObject("excessCollection", excessList);
			}
			else if (object.getInvoiceType() != null && object.getInvoiceType().intValue() == Invoice.FEE_INVOICE){
				String[] eqParam = {"invoiceId.invoiceId","deletedStatus"};
				Object[] eqValue = {pkey, Integer.valueOf(0)};
				
				int total = excessChargeService.getTotal(null,null,eqParam,eqValue);
				Collection<ExcessCharge> excessList = excessChargeService.search(null,null,eqParam,eqValue,0,total);
				result.addObject("excessCollection", excessList);
			}
			result.addObject("invoice_date",object.getInvoiceDate());
			result.addObject("invoice_due_date",object.getInvoiceDueDate());
			
			
			result.addObject("halaman1Body", "");

			
			
			result.addObject("totalInvoiceValue", object.getOutstanding());
			result.addObject("totalValueString", MoneyParser.convert(object.getOutstanding().longValue()));
			
			result.addObject("invoiceId", invoiceId);
			result.addObject("coveragePeriode", "");
			result.addObject("currency", "IDR");
			
			
			result.addObject("excessPeriode", "");
			result.addObject("configuration", configuration);
			
			
			
			
			result.addObject("invoice", object);
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
	public ModelAndView printHal4Performed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer invoiceId = WebUtil.getParameterInteger(request,
					"invoiceId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
	

			result = new ModelAndView(location);
			java.io.Serializable pkey = invoiceId;
			Invoice object = invoiceService.get(pkey);
			
			Configuration configuration = configurationService.getSystemConfiguration();

			MemberGroup memberGroup = object.getMemberGroupId();
			

			String reportFile = "ExcessReport"+"_"+configuration.getCompanyCode().toLowerCase()+".rptdesign";
			result = new ModelAndView(location);
		
			String[] eqParam = {"invoiceId.invoiceId"};
			Object[] eqValue = {invoiceId};
			
			int total = excessChargeService.getTotal(null,null,eqParam,eqValue);
			Collection<ExcessCharge> excessList = excessChargeService.search(null,null,eqParam,eqValue,0,total);
			
			Collection<ExcessDto> excessCollection = new Vector<ExcessDto>();
			
			
			Iterator<ExcessCharge> iterator = excessList.iterator();
			
			while (iterator.hasNext()){
				ExcessDto excessDto = new ExcessDto();
				
				ExcessCharge tmp = iterator.next();
				excessDto.setExcessCharge(tmp);
				
				Collection<ClaimItem> claimItems = claimItemService.getExcessClaimItem(tmp.getClaimId().getClaimId());
				excessDto.setClaimItemList(claimItems);
				excessCollection.add(excessDto);
			}
			
			
			result.addObject("invoice_date",object.getInvoiceDate());
			result.addObject("invoice_due_date",object.getInvoiceDueDate());
			

			result.addObject("excessCollection", excessCollection);

			result.addObject("contactPerson",memberGroup.getContactPerson());
			result.addObject("halaman1Body", "");
			result.addObject("groupName", memberGroup.getGroupName());
			result.addObject("totalInvoiceValue", object.getOutstanding());
			result.addObject("totalValueString", MoneyParser.convert(object.getOutstanding().longValue()));
			result.addObject("nomorPolis", memberGroup.getPolicyNumber());
			result.addObject("invoiceId", invoiceId);
			result.addObject("coveragePeriode", "");
			result.addObject("currency", "IDR");
			result.addObject("groupAddress", memberGroup.getAddress());
			
			result.addObject("excessPeriode", "");
			
			
			
			
			result.addObject("invoice", object);
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

	public ModelAndView printHal3Performed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer invoiceId = WebUtil.getParameterInteger(request,
					"invoiceId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
	

			result = new ModelAndView(location);
			java.io.Serializable pkey = invoiceId;
			Invoice object = invoiceService.get(pkey);
			
			Configuration configuration = configurationService.getSystemConfiguration();

			MemberGroup memberGroup = object.getMemberGroupId();
			

			String reportFile = "ExcessReport"+"_"+configuration.getCompanyCode().toLowerCase()+".rptdesign";
			result = new ModelAndView(location);
		
			
			String[] eqParam = {"invoiceId.invoiceId"};
			Object[] eqValue = {invoiceId};
			
			int total = excessChargeService.getTotal(null,null,eqParam,eqValue);
			Collection<ExcessCharge> excessCollection = excessChargeService.search(null,null,eqParam,eqValue,0,total);
			
			
			result.addObject("invoice_date",object.getInvoiceDate());
			result.addObject("invoice_due_date",object.getInvoiceDueDate());
			
			result.addObject("excessCollection", excessCollection);

			result.addObject("contactPerson",memberGroup.getContactPerson());
			result.addObject("halaman1Body", "");
			result.addObject("groupName", memberGroup.getGroupName());
			result.addObject("totalInvoiceValue", object.getOutstanding());
			result.addObject("totalValueString", MoneyParser.convert(object.getOutstanding().longValue()));
			result.addObject("nomorPolis", memberGroup.getPolicyNumber());
			result.addObject("invoiceId", invoiceId);
			result.addObject("coveragePeriode", "");
			result.addObject("currency", "IDR");
			result.addObject("groupAddress", memberGroup.getAddress());
			
			result.addObject("excessPeriode", "");
			
			
			
			
			result.addObject("invoice", object);
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

	public ModelAndView printHal2Performed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer invoiceId = WebUtil.getParameterInteger(request,
					"invoiceId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
	

			result = new ModelAndView(location);
			java.io.Serializable pkey = invoiceId;
			Invoice object = invoiceService.get(pkey);
			
			Configuration configuration = configurationService.getSystemConfiguration();

			MemberGroup memberGroup = object.getMemberGroupId();
			

			String reportFile = "ExcessReport"+"_"+configuration.getCompanyCode().toLowerCase()+".rptdesign";
			result = new ModelAndView(location);
		
			
			result.addObject("invoice_date",object.getInvoiceDate());
			result.addObject("invoice_due_date",object.getInvoiceDueDate());
			

			result.addObject("contactPerson",memberGroup.getContactPerson());
			result.addObject("halaman1Body", "");
			result.addObject("groupName", memberGroup.getGroupName());
			result.addObject("totalInvoiceValue", object.getOutstanding());
			result.addObject("totalValueString", MoneyParser.convert(object.getOutstanding().longValue()));
			result.addObject("nomorPolis", memberGroup.getPolicyNumber());
			result.addObject("invoiceId", invoiceId);
			result.addObject("coveragePeriode", "");
			result.addObject("currency", "IDR");
			result.addObject("groupAddress", memberGroup.getAddress());
			
			result.addObject("excessPeriode", "");
			
			
			
			
			result.addObject("invoice", object);
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

	public ModelAndView printHal1Performed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer invoiceId = WebUtil.getParameterInteger(request,
					"invoiceId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
	

			result = new ModelAndView(location);
			java.io.Serializable pkey = invoiceId;
			Invoice object = invoiceService.get(pkey);
			
			Configuration configuration = configurationService.getSystemConfiguration();

			MemberGroup memberGroup = object.getMemberGroupId();
			
			location = location + "_" + configuration.getCompanyCode();

			//String reportFile = "ExcessReport"+"_"+configuration.getCompanyCode().toLowerCase()+".rptdesign";
			
			result = new ModelAndView(location);
			result.addObject("configuration", configuration);
		
			if (memberGroup != null){
				result.addObject("GroupId", memberGroup.getMemberGroupId());
				result.addObject("nomorPolis", memberGroup.getPolicyNumber());
				result.addObject("groupAddress", memberGroup.getAddress());
				result.addObject("contactPerson",memberGroup.getContactPerson());
				result.addObject("groupName", memberGroup.getGroupName());
			}
			
			
			
			
			result.addObject("invoice_date",object.getInvoiceDate());
			result.addObject("invoice_due_date",object.getInvoiceDueDate());
			

			
			result.addObject("halaman1Body", "");
			
			result.addObject("totalInvoiceValue", object.getOutstanding());
			result.addObject("totalValueString", MoneyParser.convert(object.getOutstanding().longValue()));
			
			result.addObject("invoiceId", invoiceId);
			result.addObject("coveragePeriode", "");
			result.addObject("currency", "IDR");
			
			
			result.addObject("excessPeriode", "");
			
			
			
			
			result.addObject("invoice", object);
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

			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
	
			
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
			if (navigation.equals("gosearch") || navigation.equals("golookup")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					

					if (searchby.equalsIgnoreCase("memberGroupName")) {
						vLikeP.add("memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("invoiceNumber")) {
						vLikeP.add("invoiceNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}

				}

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

			count = invoiceService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
			// foreign affairs end
			};
			collection = invoiceService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = invoiceService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Invoices", collection);

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

	public ModelAndView exportPerformed(HttpServletRequest request,
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
			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
	

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

			if (navigation.equals("export") ) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("memberGroupName")) {
						vLikeP.add("memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("invoiceNumber")) {
						vLikeP.add("invoiceNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
				}

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

			count = invoiceService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);


			String required[] = new String[] {
			// foreign affairs
			// foreign affairs end
			};
			collection = invoiceService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required, rowsetint, countSet.intValue());
			
			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
					&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
			
				String[] betweenColumn = {"excessChargeTime"};
				Object[] minColumn = {minDate};
				Object[] maxColumn = {maxDate};
				
				count = invoiceService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn);
				
				collection = invoiceService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn,
					null, 0, count);
			}
			else {
				count = invoiceService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
				
				collection = invoiceService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					null, 0, count);
			}



			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */
			
			String data = "";
			
			data += "Invoice No, Company Name, Outstanding, Due Date, Payment,Payment Date, Total \n\n";
			
			if (collection != null){
				Iterator<Invoice> invoiceIterator  = collection.iterator();
				
				Invoice invoice = null;
				
				String reason = "";
				
				
				while (invoiceIterator.hasNext()){
					String invoiceNo = "-";
					String paymentDate = "-";
					invoice = invoiceIterator.next();
					
					double total = invoice.getOutstanding();
					
					if (invoice.getPaid()!= null ){
						total = total - invoice.getPaid().doubleValue();
					}
					if (invoice.getLastPaymentDate() != null){
						paymentDate = invoice.getLastPaymentDate().toString();
					}
					
										
					data += invoice.getInvoiceNumber()+","+invoice.getMemberGroupId().getGroupName()+",\""+ Converter.getMoney(invoice.getOutstanding()) ;
					data += "\","+invoice.getInvoiceDueDate()+",\""+invoice.getPaid()+"\","+paymentDate+",\"" + Converter.getMoney(total);
					
					data += "\"\n";
					
					reason = "";
				}
			}
			System.out.println("DATA : " + data);
			
			response.setContentType("application/x-csv");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			response.setHeader("Content-disposition",
					"inline; filename="  
							+ "StatementofAccount.csv");

			ServletOutputStream sos = response.getOutputStream();

			response.setHeader("Content-length", Integer
					.toString(data.length()));
			
			
			sos.write(data.getBytes());
			sos.close();

			result.addObject("Invoices", collection);

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

	/**
	 * Action Service
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Get paramater navigation
		String navigation = request.getParameter("navigation") == null ? "welcome"
				: request.getParameter("navigation");

		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);

		String breadcrumb = "<a href=\"invoice\" class=\"linkbreadcrumb\">Manage Invoice</a>";
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response, "detailInvoice");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchInvoice");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupInvoice");
			} else if (navigation.equalsIgnoreCase("export")) {
				result = exportPerformed(request, response, "searchInvoice");
			}
			else if (navigation.equalsIgnoreCase("print")){
				result = printPerformed(request, response, "");
			}	
			else if (navigation.equalsIgnoreCase("jsontotalopen") ){
				result = lookupJsonTotalPerformed(request,response);
			}	
			else if (navigation.equalsIgnoreCase("printReceipt")){
				result = printReceiptPerformed(request, response, "printInvoiceReceipt");
			}
			else if (navigation.equalsIgnoreCase("printRecap")){
				result = printRecapitulationPerformed(request, response, "printInvoiceRekap");
			}
			else if (navigation.equalsIgnoreCase("printhal1")){
				result = printHal1Performed(request, response, "printInvoice");
			}
			else if (navigation.equalsIgnoreCase("printhal2")){
				result = printHal2Performed(request, response, "printHal2Invoice");
			}
			else if (navigation.equalsIgnoreCase("printhal3")){
				result = printHal3Performed(request, response, "printHal3Invoice");	
			}
			else if (navigation.equalsIgnoreCase("printhal4")){
				result = printHal4Performed(request, response, "printHal4Invoice");	
			}
			else {
				result = searchPerformed(request, response, "searchInvoice");
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
