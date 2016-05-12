package com.ametis.cms.web.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import com.ametis.cms.util.*;
import com.ametis.cms.util.servlet.TableRenderingServlet;
import java.io.IOException;
import java.io.Serializable;

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
import com.ametis.cms.service.*;
import java.util.*;

public class CaseMedicineController implements Controller {

	private CaseMedicineService caseMedicineService;
	private SecurityService securityService;
	private CaseService caseService;
	private PolicyService policyService;
	private MemberService memberService;
	private ClaimMedicineService claimMedicineService;

	private UserService actionuserService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	
	private CaseEventService caseEventService;
	
	
	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}

	public CaseEventService getCaseEventService() {
		return caseEventService;
	}

	public void setCaseEventService(CaseEventService caseEventService) {
		this.caseEventService = caseEventService;
	}

	public void setUserService(UserService userService) {
		this.actionuserService = userService;
	}
	
	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
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

	public void setCaseMedicineService(CaseMedicineService caseMedicineService) {
		this.caseMedicineService = caseMedicineService;
	}

	public CaseMedicineService getCaseMedicineService() {
		return this.caseMedicineService;
	}
	
	public ClaimMedicineService getClaimMedicineService() {
		return claimMedicineService;
	}

	public void setClaimMedicineService(ClaimMedicineService claimMedicineService) {
		this.claimMedicineService = claimMedicineService;
	}
	
	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseMedicineId = WebUtil.getParameterInteger(request,
					"caseMedicineId");
			java.io.Serializable pkey = caseMedicineId;

			ActionUser user = securityService.getActionUser(request);

			CaseMedicine res = caseMedicineService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.casemedicine", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.casemedicine", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchCaseMedicine");
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
	public ModelAndView approvePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseMedicineId = WebUtil.getParameterInteger(request,"caseMedicineId");
			java.io.Serializable pkey = caseMedicineId;
			String reason = WebUtil.getParameterString(request, "reason", "");

			ActionUser user = securityService.getActionUser(request);

			String navigation = WebUtil.getParameterString(request, "navigation", "");
			
			boolean hasil = false;
			
			if (navigation.equalsIgnoreCase("approve")){
			
				CaseMedicine med = caseMedicineService.get(pkey);
				if (med != null){
					med.setStatus(1);
					med.setApprovalReason(reason);
					caseMedicineService.update(med, user);
					hasil = true;
				}
			}
			else if (navigation.equalsIgnoreCase("reject")){
				CaseMedicine med = caseMedicineService.get(pkey);
				if (med != null){
					med.setStatus(-1);
					med.setApprovalReason(reason);
					caseMedicineService.update(med, user);
					hasil = true;
				}
			}

			if (hasil) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.approve.casemedicine", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.casemedicine", null, "fail", Locale
								.getDefault()));

			}
			result = searchApprovePerformed(request, response, "searchApproveMedicine");
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
	public ModelAndView addBulkPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			Case theCase = caseService.get(caseId);
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			ActionUser user = securityService.getActionUser(request);

			if (navigation.equalsIgnoreCase("addbulk")) {

				Vector<String> itemValueList = new Vector<String>();
				Vector<String> itemAmountList = new Vector<String>();
				Vector<String> medicineList = new Vector<String>();
				Vector<String> medicineIdList = new Vector<String>();
				Vector<String> descList = new Vector<String>();
				Vector<String> priceList = new Vector<String>();

				for (int i = 0; i < 10; i++) {

					itemValueList.add("");
					medicineList.add("");
					medicineIdList.add("");
					itemAmountList.add("");
					descList.add("");
					priceList.add("");

				}
				
				//PENAMBAHAN TARIF TYPE DI ADD MADICINE , BY AULIA R.
				System.out.println("member " + theCase.getMemberId().getMemberId());
				System.out.println("case " + caseId);
//				Member member = memberService.get(theCase.getMemberId());
				Integer policyId = theCase.getMemberId().getCurrentPolicyId().getPolicyId();
				Policy policy = policyService.get(policyId);
				
				String location = "";
				
				if(policy.getTarifTypeId() == null || policy.getTarifTypeId().getTarifTypeId() == TarifType.GENERAL ){
					System.out.println("masuk addBulkMedicine " );
					location = "addBulkMedicine";
				}else if(policy.getTarifTypeId().getTarifTypeId() == TarifType.GENERAL_CLIENT){
//					belum digunakan
//					location = "addBulkMedicineClient";
					location = "addBulkMedicine";
				}else if(policy.getTarifTypeId().getTarifTypeId() == TarifType.GENERAL_POLICY){
//					belum digunakan
//					location = "addBulkMedicinePolicy";
					location = "addBulkMedicine";
				}else if(policy.getTarifTypeId().getTarifTypeId() == TarifType.GENERAL_MEMBER_GROUP){
//					belum digunakan
//					location = "addBulkMedicineMemberGroup";
					location = "addBulkMedicine";
				}else if(policy.getTarifTypeId().getTarifTypeId() == TarifType.PROVIDER_CLIENT){
					System.out.println("masuk addBulkProviderMedicineClient " );
					location = "addBulkProviderMedicineClient";
				}else if(policy.getTarifTypeId().getTarifTypeId() == TarifType.PROVIDER_POLICY){
					System.out.println("masuk addBulkProviderMedicinePolicy " );
					location = "addBulkProviderMedicinePolicy";
				}else if(policy.getTarifTypeId().getTarifTypeId() == TarifType.PROVIDER_MEMBER_GROUP){
					System.out.println("masuk addBulkProviderMedicineMemberGroup " );
					location = "addBulkProviderMedicineMemberGroup";
				}
				

				System.out.println(location);
				result = new ModelAndView(location);
				result.addObject("valueVector", itemValueList);
				result.addObject("nameVector", medicineList);
				result.addObject("medIdVector", medicineIdList);
				result.addObject("amountVector", itemAmountList);
				result.addObject("descVector", descList);
				result.addObject("medPriceVector", priceList);
				
				result.addObject("memberId", theCase.getMemberId().getMemberId());
				result.addObject("roomAndBoard", theCase.getRoomAndBoard());
				result.addObject("caseCategory", theCase.getCaseCategoryId().getCaseCategoryId());
				
				

			} else if (navigation.equalsIgnoreCase("savebulk")) {

				String[] medicineId = request.getParameterValues("medicineId");
				String[] usageList = request.getParameterValues("itemAmount");
				String[] nameList = request.getParameterValues("medicineName");
				String[] chargeList = request.getParameterValues("itemValue");
				String[] priceList = request.getParameterValues("itemPrice");
				String[] descList = request.getParameterValues("description");

				Collection<CaseMedicine> medicineList = new Vector<CaseMedicine>();
				Collection<ClaimMedicine> claimMedicineList = new Vector<ClaimMedicine>();

				for (int i = 0; i < medicineId.length; i++) {
					System.out.println("ID = " + medicineId[i] + " NAME = "
							+ nameList[i] + " CHARGE = " + chargeList[i]
							+ " DESC = " + descList[i] + " PRICE REF = "
							+ priceList[i]);

					if (medicineId[i] != null
							&& !medicineId[i].trim().equalsIgnoreCase("")) {
						CaseMedicine med = new CaseMedicine();
						//input ke claim medicine
						ClaimMedicine claimMed = new ClaimMedicine();
						Medicine medicine = new Medicine();
						medicine.setMedicineId(Integer.valueOf(medicineId[i]));

						med.setMedicineId(medicine);
						if (priceList[i] != null
								&& !priceList[i].trim().equalsIgnoreCase("")) {
							med.setReferencePrice(Double.valueOf(priceList[i]));
							claimMed.setReferencePrice(Double.valueOf(priceList[i]));
						} else {
							med.setReferencePrice(-1.0);
							claimMed.setReferencePrice(-1.0);
						}
						if (usageList[i] != null
								&& !usageList[i].trim().equalsIgnoreCase("")) {
							med.setTotalUsage(Double.valueOf(usageList[i]));
							claimMed.setTotalUsage(Double.valueOf(usageList[i]));
						} else {
							med.setTotalUsage(-1.0);
							claimMed.setTotalUsage(-1.0);
						}
						if (chargeList[i] != null
								&& !chargeList[i].trim().equalsIgnoreCase("")) {
							if(Double.valueOf(chargeList[i]) > Double.valueOf(priceList[i])){
								med.setTotalCharge(Double.valueOf(chargeList[i]));
								claimMed.setTotalCharge(Double.valueOf(chargeList[i]));
							}else{
								med.setTotalCharge(Double.valueOf(chargeList[i]));
								claimMed.setTotalCharge(Double.valueOf(chargeList[i]));
							}
						} else {
							med.setTotalCharge(-1.0);
							claimMed.setTotalCharge(-1.0);
						}
						med.setDescription(descList[i]);
						claimMed.setDescription(descList[i]);

						medicineList.add(med);
						claimMedicineList.add(claimMed);
					}
				}
				boolean res = caseMedicineService.addMedicine(caseId,
						medicineList, user);
				
				boolean resClaim = claimMedicineService.addMedicine(theCase.getClaimId().getClaimId(), claimMedicineList, user);

				String alert = "";

				if (res && resClaim) {
					alert = "Success Add Medicine";
				} else {
					alert = "Failed Add Medicine";
				}
				result = new ModelAndView(new RedirectView(
						"casemedicine?navigation=list&caseId=" + caseId
								+ "&alert=" + alert + "&memberId=" + memberId));
			}
			request.setAttribute("caseId", caseId);

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
			Integer caseMedicineId = WebUtil.getParameterInteger(request,
					"caseMedicineId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,	"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby","");

			result = new ModelAndView(location);
			java.io.Serializable pkey = caseMedicineId;
			CaseMedicine object = caseMedicineService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.casemedicine", null, "fail", Locale
								.getDefault()));
			}
			else {
				String[] eqParam = {"caseId.caseId","deletedStatus"};
				Object[] eqValue = {object.getCaseId().getCaseId(),0};
				
				int total = caseEventService.getTotal(null,null,eqParam,eqValue);
				Collection<CaseEvent> eventList = caseEventService.search(null,null,eqParam,eqValue,0,total);
				result.addObject("CaseEvents", eventList);
				
			}
			result.addObject("caseMedicine", object);
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
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

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
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("list")) {

				if (searchby != null && !searchtext.equals("") && searchtext != null) {

					if (searchby.equalsIgnoreCase("medicineName")) {
						vLikeP.add("medicineId.medicineName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}

				}

			}

			vEqP.add("caseId.caseId");
			vEqQ.add(caseId);
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

			count = caseMedicineService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
			collection = caseMedicineService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = caseMedicineService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}
			
			Case caseObject = null;
			
			if(caseId != null){
				try
				{
				java.io.Serializable casepkey = caseId;
				System.out.println("case Id : "+caseId);
				//modified by aju on 20150423, Add link Case->CaseStatus
				String[] caseRequired = {"Case.MemberId","Case.MemberId.ParentMember","Case.MemberId.MemberGroupId","Case.ClaimId","Case.Diagnosis2Id","Case.Diagnosis3Id","Case.CaseStatusId"};
				caseObject = caseService.get(casepkey, caseRequired);
				
				}
				catch (Exception ex)
				{
//					System.out.println("member group object : "+caseObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			Case theCase = caseService.get(caseId);
			
			result.addObject("theCase", theCase);
			
			result.addObject("CaseMedicines", collection);
			result.addObject("myCase", caseObject);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("caseId", caseId);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView jsonTotalPendingPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			result = new ModelAndView("jsonTotalPending");

			String navigation = request.getParameter("navigation");
			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DETAILCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DETAILCASE access");
				return errorResult;

			}
			String[] eqParam = { "status", "deletedStatus" };
			Object[] eqValue = { Integer.valueOf(0), Integer.valueOf(0) };
			int total = caseMedicineService.getTotal(null, null, eqParam,
					eqValue);

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

	public ModelAndView searchApprovePerformed(HttpServletRequest request,
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
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

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

					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("medicine")) {
						vLikeP.add("medicineId.medicineName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("caseNumber")) {
						vLikeP.add("caseId.caseNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("caseId.memberId.firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("diagnosis")) {
						vLikeP.add("caseId.diagnosis2Id.description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("createdBy")) {
						vLikeP.add("createdBy");
						vLikeQ.add(searchtext);
					}

				}

			}

			vEqP.add("status");
			vEqQ.add(new Integer(0));
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

			count = caseMedicineService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
			collection = caseMedicineService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = caseMedicineService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			//Add by 20150518 by Feiby Veronika, to save previous navigation
			request.setAttribute("subnavigation", "searchapprove");

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("CaseMedicines", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("caseId", caseId);

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

		String subnavigation = WebUtil.getParameterString(request,
				"subnavigation", "");
		
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
		String caseId = request.getParameter("caseId");	
		System.out.println("NAV : "+navigation);
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				result = detailPerformed(request, response,
						"detailCaseMedicine");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("searchapprove") || subnavigation.equalsIgnoreCase("searchapprove")) {
				result = searchApprovePerformed(request, response,
						"searchApproveMedicine");
			} else if (navigation.equalsIgnoreCase("jsontotalapprove")) {
				result = jsonTotalPendingPerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchCaseMedicine");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupCaseMedicine");
			}
			else if (navigation.equalsIgnoreCase("approve")
					|| navigation.equals("reject")) {
				result = approvePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("addbulk")
					|| navigation.equals("savebulk")) {
				result = addBulkPerformed(request, response);
			} else if (navigation.equalsIgnoreCase("list")) {
				result = searchPerformed(request, response,"searchCaseMedicine");
				breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Medicine";
			}else {
				result = searchPerformed(request, response,
						"searchCaseMedicine");
				breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Medicine";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.addObject("breadcrumb",breadcrumb);
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	

	// class+

	// class-
}
