package com.ametis.cms.web.controller;

import java.io.IOException;
import java.sql.Date;
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
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberImport;
import com.ametis.cms.service.MemberImportService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.CardPrintDownloader;
import com.ametis.cms.util.MemberTrackingDownloader;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+

// imports-

/**
 * MemberImport is a servlet controller for member_import Table. All you have to
 * do is to convert necessary data field to the named parameter
 */
public class MemberImportController implements Controller

// extends+

// extends-
{

	private MemberImportService memberImportService;
	private SecurityService securityService;
	private MemberService memberService;

	private UserService actionuserService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	
	//Add by aju on 20150928, make iframe param public fufufu :D
	private String usingIFrame;
	private String iFrameClientMemberId;
	private String iFrameLevelLogin;
	private String sessionMemberId;
	private String sessionMemberParentId;
	private boolean isIFrameSession;
	
	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public void setActionUserService(UserService userService) {
		this.actionuserService = userService;
	}

	public UserService getActionUserService() {
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

	public void setMemberImportService(MemberImportService memberImportService) {
		this.memberImportService = memberImportService;
	}

	public MemberImportService getMemberImportService() {
		return this.memberImportService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Long id = WebUtil.getParameterLong(request, "id");

			java.io.Serializable pkey = id;

			ActionUser user = securityService.getActionUser(request);

			MemberImport res = memberImportService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.memberimport", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.memberimport", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchMemberImport");
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
	public ModelAndView markPrintedPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Long id = WebUtil.getParameterLong(request, "id");

			java.io.Serializable pkey = id;

			ActionUser user = securityService.getActionUser(request);

			MemberImport res = memberImportService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.memberimport", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.memberimport", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchMemberImport");
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

	public ModelAndView downloadPrintCardPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer id = WebUtil.getParameterInteger(request, "id");


			
			String[] eqParam = {"deletedStatus","printCard","importSessionId.id"};
			Object[] eqValue = {0,"Y",id};
			
			int total = memberImportService.getTotal(null,null,eqParam,eqValue);
			
			Collection<MemberImport> cardPrintList = memberImportService.search(null,null,eqParam,eqValue,0,total);
			
			HSSFWorkbook workbook = CardPrintDownloader.downloadExcel(cardPrintList);
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + System.currentTimeMillis()+".xls");

			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ServletOutputStream sos = response.getOutputStream();


			workbook.write(sos);

			sos.close();
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
	public ModelAndView downloadPrintMemberTrackingPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
			String actionType = WebUtil.getParameterString(request, "actionType", "");


			String[] eqParam = {"deletedStatus"};
			Object[] eqValue = {new Integer(0)};
			
			int total = memberImportService.getTotal(null,null,eqParam,eqValue);
			
			Collection<MemberImport> memberList = memberImportService.search(null,null,eqParam,eqValue,0,total);
			System.out.println("SIZE LIST : "+memberList.size());
			
			HSSFWorkbook workbook = MemberTrackingDownloader.downloadExcel(memberList);
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + System.currentTimeMillis()+".xls");

			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ServletOutputStream sos = response.getOutputStream();

			workbook.write(sos);

			sos.close();
			//request.setAttribute("navigation", "");
//			request.setAttribute("actionType", actionType);
//			request.setAttribute("countSet", countSet);
			request.setAttribute("index", 0);
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("navigation", "gosearch");
			//result = searchPerformed(request, response, "searchMemberImport");
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
	
	public ModelAndView printMemberTrackPerformed(HttpServletRequest request,
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
			String actionType = WebUtil.getParameterString(request, "actionType", "");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;
			
			if(navigation.equals("searchMemberImport")){
				navigation = "gosearch";
			}

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			if (navigation.equals("gosearch") || navigation.equals("golookup")) {
				if (searchby != null) {
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("memberName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("customerNumber")) {
						vLikeP.add("memberNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("swipeCardNumber")) {
						vLikeP.add("swipeCardNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("swipeCardNumberIndex")) {
						vLikeP.add("swipeCardNumberIndex");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("sex")) {
						vLikeP.add("sex");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("department")) {
						vLikeP.add("department");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("handphone")) {
						vLikeP.add("handphone");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("email")) {
						vLikeP.add("email");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("bankAccountName")) {
						vLikeP.add("bankAccountName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("accountNumber")) {
						vLikeP.add("accountNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("bank")) {
						vLikeP.add("bank");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("bankBranch")) {
						vLikeP.add("bankBranch");
						vLikeQ.add(searchtext);
					}
				}

			}
			System.out.println("ACION TYPE "+actionType);

			if (actionType != null){
				vLikeP.add("actionType");
				vLikeQ.add(actionType);
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

			count = memberImportService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			
			System.out.println("COUNT : "+count);

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
			
			collection = memberImportService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					false,"id",required, rowsetint, countSet.intValue());
			System.out.println("COLECTION SIZE : "+collection.size());

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
				collection = memberImportService.search(sLikeP, sLikeQ, sEqP,
						sEqQ,false,"id", required, rowsetint, countSet.intValue());
			}
			
			Collection<MemberImport> memberList = memberImportService.search(sLikeP, sLikeQ, sEqP,
					sEqQ,false,"id", required,0,count);
			
			HSSFWorkbook workbook = MemberTrackingDownloader.downloadExcel(memberList);
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + System.currentTimeMillis()+".xls");

			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ServletOutputStream sos = response.getOutputStream();

			workbook.write(sos);

			sos.close();

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("MemberImports", collection);

			request.setAttribute("actionType", actionType);
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
	
	public ModelAndView detailPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Long id = WebUtil.getParameterLong(request, "id");

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
			java.io.Serializable pkey = id;
			MemberImport object = memberImportService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.memberimport", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("memberImport", object);
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
			String actionType = WebUtil.getParameterString(request, "actionType", "");
			
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
								+ "<a href=\"memberimport?navigation=listmember&memberId="+memberId+"\">Go Back</a>");
						return errorResult;
					}
				}
			}


			
			String breadcrumb = "";

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
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("listmember")) {

				if (searchby != null) {
					
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("memberName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("customerNumber")) {
						vLikeP.add("memberNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("swipeCardNumber")) {
						vLikeP.add("swipeCardNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("swipeCardNumberIndex")) {
						vLikeP.add("swipeCardNumberIndex");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("sex")) {
						vLikeP.add("sex");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("department")) {
						vLikeP.add("department");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("handphone")) {
						vLikeP.add("handphone");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("email")) {
						vLikeP.add("email");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("bankAccountName")) {
						vLikeP.add("bankAccountName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("accountNumber")) {
						vLikeP.add("accountNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("bank")) {
						vLikeP.add("bank");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("bankBranch")) {
						vLikeP.add("bankBranch");
						vLikeQ.add(searchtext);
					}
					//Add 20150705 by FVO, for searching Member Movement
					if(searchby.equalsIgnoreCase("groupName")){
						vLikeP.add("groupName");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("clientName")){
						vLikeP.add("clientName");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("policyNumber")){
						vLikeP.add("policyNumber");
						vLikeQ.add(searchtext);
					}
				}

			}
			System.out.println("ACION TYPE "+actionType);

			if (actionType != null){
				vLikeP.add("actionType");
				vLikeQ.add(actionType);
			}
			
			if (navigation.equalsIgnoreCase("listmember")){
				result = new ModelAndView("listHistoryMemberImport");
				if (memberId != null){
					String[] requiredMember = {"Member.MemberGroupId","Member.ClientId",
							"Member.RelationshipId","Member.ParentMember","Member.CurrentPolicyId"};
					
					breadcrumb = "<a href=\"member?navigation="+navigation+"&memberId="+memberId+"\" class=\"linkbreadcrumb\">Detail Member</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Member Movement History";
					
					request.setAttribute("navigation", navigation);
					request.setAttribute("memberId", memberId);
					
					Member object = memberService.get(memberId,requiredMember);
					result.addObject("member", object);
					result.addObject("breadcrumb", breadcrumb);

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
					
					if (object != null){
						vEqP.add("memberNumber");
						vEqQ.add(object.getCustomerPolicyNumber());
						
						vEqP.add("policyNumber");
						vEqQ.add(object.getCurrentPolicyNumber());
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

			count = memberImportService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			
			System.out.println("COUNT : "+count);

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
			
			collection = memberImportService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					false,"id",required, rowsetint, countSet.intValue());
			System.out.println("COLECTION SIZE : "+collection.size());

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
				collection = memberImportService.search(sLikeP, sLikeQ, sEqP,
						sEqQ,false,"id", required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("MemberImports", collection);

			request.setAttribute("actionType", actionType);
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
	public ModelAndView searchPrintPerformed(HttpServletRequest request,
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
			String actionType = WebUtil.getParameterString(request, "actionType", "");
			
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;





			String required[] = new String[] {
			// foreign affairs
			// foreign affairs end
			};
			collection = memberImportService.getPrintingList(clientId);



			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("MemberImports", collection);

			request.setAttribute("actionType", actionType);
			request.setAttribute("countSet", countSet);


			request.setAttribute("alert", request.getParameter("alert"));

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
		System.out.println("NAVIGATION : "+navigation);
		try {
			
			//modified by aju on 20150929, new iFrame checker fufufu plus init
			isIFrameSession = securityService.isUsingIFrameSession(request);
			iFrameLevelLogin = securityService.getTheIFrameLevelLogin();
			System.out.println("securityService->SessionMemberId->" + securityService.getTheSessionMemberId());
			sessionMemberId = securityService.getTheSessionMemberId();
			System.out.println("securityService->SessionParentMemberId->" + securityService.getTheSessionMemberParentId());
			sessionMemberParentId = securityService.getTheSessionMemberParentId();

			
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response,
						"detailMemberImport");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchMemberImport");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupMemberImport");
			} 
			else if (navigation.equalsIgnoreCase("searchprint") || navigation.equalsIgnoreCase("gosearchprint") ){
				result = searchPrintPerformed(request, response,
						"searchPrintMemberImport");
			}
			else if (navigation.equalsIgnoreCase("downloadprintcard")){
				result = downloadPrintCardPerformed(request, response);
			}else if(navigation.equalsIgnoreCase("downloadMemberTracking")){
				result = printMemberTrackPerformed(request, response, "searchMemberImport");
				breadcrumb = "<a href=\"memberimport?navigation=gosearch\">Search Member Import</a>";
			}
			else {
				result = searchPerformed(request, response,
						"searchMemberImport");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	// class+

	// class-
}
