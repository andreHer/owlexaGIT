package com.ametis.cms.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseEvent;
import com.ametis.cms.datamodel.CaseInvestigation;
import com.ametis.cms.datamodel.CaseItem;
import com.ametis.cms.datamodel.CaseMedicine;
import com.ametis.cms.datamodel.CaseProcedure;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.EventCategory;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.Medicine;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Procedure;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderCategory;
import com.ametis.cms.datamodel.ProviderDoctor;
import com.ametis.cms.datamodel.ProviderPoliklinik;
import com.ametis.cms.datamodel.RefCity;
import com.ametis.cms.datamodel.RefProvince;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.CaseEventService;
import com.ametis.cms.service.CaseInvestigationService;
import com.ametis.cms.service.CaseItemService;
import com.ametis.cms.service.CaseProcedureService;
import com.ametis.cms.service.CaseMedicineService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.EventCategoryService;
import com.ametis.cms.service.ExcessChargeService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.MedicineCategoryService;
import com.ametis.cms.service.MedicineFactoryService;
import com.ametis.cms.service.MedicineService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PoliklinikService;
import com.ametis.cms.service.ProcedureService;
import com.ametis.cms.service.ProviderCategoryService;
import com.ametis.cms.service.ProviderDoctorService;
import com.ametis.cms.service.ProviderPoliklinikService;
import com.ametis.cms.service.RefCityService;
import com.ametis.cms.service.RefCountryService;
import com.ametis.cms.service.RefProvinceService;
import com.ametis.cms.service.RefRegionService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.SubscriptionStatusService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

public class MobileController implements Controller{

	private CaseService caseService;
	private ClaimService claimService;
	
	private MedicineCategoryService medicineCategoryService;
	private MedicineService medicineService;
	private MedicineFactoryService medicineFactoryService;
	
	private ProviderService providerService;
	private UserService userService;
	private DiagnosisService diagnosisService;
	private ClaimItemService claimItemService;
	private CaseItemService caseItemService;
	private CaseEventService caseEventService;
	private CaseInvestigationService caseInvestigationService;
	private CaseMedicineService caseMedicineService;
	private CaseProcedureService caseProcedureService;
	private ProviderDoctorService providerDoctorService;
	private ProcedureService procedureService;
	private SubscriptionStatusService statusService;
	
	private ExcessChargeService excessChargeService;
	
	private RefCityService refCityService;
	private RefProvinceService refProvinceService;
	private RefRegionService refRegionService;
	private RefCountryService refCountryService;
	
	private SecurityService securityService;
	private MemberService memberService;
	private MemberBenefitService memberBenefitService;
	private PoliklinikService poliklinikService;
	private ProviderPoliklinikService providerPoliklinikService;
	private EventCategoryService eventCategoryService;
	private ItemService itemService;
	private ProviderCategoryService providerCategoryService;
	
	private Integer countSet = 10;
	private Integer maxPercountSet = 10;
	private ResourceBundleMessageSource alertProperties;
	
	
	
	public ExcessChargeService getExcessChargeService() {
		return excessChargeService;
	}
	public void setExcessChargeService(ExcessChargeService excessChargeService) {
		this.excessChargeService = excessChargeService;
	}
	public MedicineFactoryService getMedicineFactoryService() {
		return medicineFactoryService;
	}
	public void setMedicineFactoryService(
			MedicineFactoryService medicineFactoryService) {
		this.medicineFactoryService = medicineFactoryService;
	}
	public ProviderCategoryService getProviderCategoryService() {
		return providerCategoryService;
	}
	public void setProviderCategoryService(
			ProviderCategoryService providerCategoryService) {
		this.providerCategoryService = providerCategoryService;
	}
	public RefCityService getRefCityService() {
		return refCityService;
	}
	public void setRefCityService(RefCityService refCityService) {
		this.refCityService = refCityService;
	}
	public RefProvinceService getRefProvinceService() {
		return refProvinceService;
	}
	public void setRefProvinceService(RefProvinceService refProvinceService) {
		this.refProvinceService = refProvinceService;
	}
	public RefRegionService getRefRegionService() {
		return refRegionService;
	}
	public void setRefRegionService(RefRegionService refRegionService) {
		this.refRegionService = refRegionService;
	}
	public RefCountryService getRefCountryService() {
		return refCountryService;
	}
	public void setRefCountryService(RefCountryService refCountryService) {
		this.refCountryService = refCountryService;
	}
	public SubscriptionStatusService getStatusService() {
		return statusService;
	}
	public void setStatusService(SubscriptionStatusService statusService) {
		this.statusService = statusService;
	}
	public ProcedureService getProcedureService() {
		return procedureService;
	}
	public void setProcedureService(ProcedureService procedureService) {
		this.procedureService = procedureService;
	}
	public ItemService getItemService() {
		return itemService;
	}
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	public EventCategoryService getEventCategoryService() {
		return eventCategoryService;
	}
	public void setEventCategoryService(EventCategoryService eventCategoryService) {
		this.eventCategoryService = eventCategoryService;
	}
	public CaseEventService getCaseEventService() {
		return caseEventService;
	}
	public void setCaseEventService(CaseEventService caseEventService) {
		this.caseEventService = caseEventService;
	}
	public CaseInvestigationService getCaseInvestigationService() {
		return caseInvestigationService;
	}
	public void setCaseInvestigationService(
			CaseInvestigationService caseInvestigationService) {
		this.caseInvestigationService = caseInvestigationService;
	}
	public CaseMedicineService getCaseMedicineService() {
		return caseMedicineService;
	}
	public void setCaseMedicineService(CaseMedicineService caseMedicineService) {
		this.caseMedicineService = caseMedicineService;
	}
	public CaseProcedureService getCaseProcedureService() {
		return caseProcedureService;
	}
	public void setCaseProcedureService(CaseProcedureService caseProcedureService) {
		this.caseProcedureService = caseProcedureService;
	}
	public PoliklinikService getPoliklinikService() {
		return poliklinikService;
	}
	public void setPoliklinikService(PoliklinikService poliklinikService) {
		this.poliklinikService = poliklinikService;
	}
	public ProviderPoliklinikService getProviderPoliklinikService() {
		return providerPoliklinikService;
	}
	public void setProviderPoliklinikService(
			ProviderPoliklinikService providerPoliklinikService) {
		this.providerPoliklinikService = providerPoliklinikService;
	}
	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}
	public void setMemberBenefitService(MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}
	public Integer getCountSet() {
		return countSet;
	}
	public void setCountSet(Integer countSet) {
		this.countSet = countSet;
	}
	public Integer getMaxPercountSet() {
		return maxPercountSet;
	}
	public void setMaxPercountSet(Integer maxPercountSet) {
		this.maxPercountSet = maxPercountSet;
	}
	public ResourceBundleMessageSource getAlertProperties() {
		return alertProperties;
	}
	public void setAlertProperties(ResourceBundleMessageSource alertProperties) {
		this.alertProperties = alertProperties;
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
	public CaseService getCaseService() {
		return caseService;
	}
	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}
	public ClaimService getClaimService() {
		return claimService;
	}
	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}
	public MedicineCategoryService getMedicineCategoryService() {
		return medicineCategoryService;
	}
	public void setMedicineCategoryService(
			MedicineCategoryService medicineCategoryService) {
		this.medicineCategoryService = medicineCategoryService;
	}
	public MedicineService getMedicineService() {
		return medicineService;
	}
	public void setMedicineService(MedicineService medicineService) {
		this.medicineService = medicineService;
	}
	public ProviderService getProviderService() {
		return providerService;
	}
	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}
	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}
	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}
	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}
	public CaseItemService getCaseItemService() {
		return caseItemService;
	}
	public void setCaseItemService(CaseItemService caseItemService) {
		this.caseItemService = caseItemService;
	}
	public ProviderDoctorService getProviderDoctorService() {
		return providerDoctorService;
	}
	public void setProviderDoctorService(ProviderDoctorService providerDoctorService) {
		this.providerDoctorService = providerDoctorService;
	}
	public ModelAndView searchCaseSettlementPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			boolean isAuthorized = isAuthorized(request);
			
			if(!isAuthorized){
				// redirect to somewhere
				return notAuthorized();
			}
			
			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			String searchtext = WebUtil.getParameterString(request,"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby","");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			ActionUser user = securityService.getActionUser(request);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));

			vEqP.add("caseStatusId.caseStatusId");
			vEqQ.add(new Integer(Case.CASE_APPROVED));
			
			if (user != null){
				if (user.getUser().getUserType().intValue() == User.PROVIDER){
					Provider provider = user.getUser().getProviderId();
					
					if (provider != null){
						vEqP.add("providerId.providerId");
						vEqQ.add(provider.getProviderId());
					}
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

			count = caseService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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

			String required[] = new String[] {};

			collection = caseService.search(sLikeP, sLikeQ, sEqP, sEqQ,required, 0, count);

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

				collection = caseService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, 0, count);
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			result.addObject("caseList", collection);

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
	public ModelAndView searchClaimPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			boolean isAuthorized = isAuthorized(request);
			
			if(!isAuthorized){
				// redirect to somewhere
				return notAuthorized();
			}
			
			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			String searchtext = WebUtil.getParameterString(request,"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby","");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			String cardNumber = WebUtil.getParameterString(request, "cardnum", "");

			ActionUser user = securityService.getActionUser(request);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));

			if (user != null){
				if (user.getUser().getUserType().intValue() == User.PROVIDER){
					Provider provider = user.getUser().getProviderId();
					if (provider != null){
						vEqP.add("providerId.providerId");
						vEqQ.add(provider.getProviderId());
					}
				}
				else if (user.getUser().getUserType().intValue() == User.MEMBER){
					Member member = user.getUser().getMemberId();
					if (member != null){
						vEqP.add("memberId.memberId");
						vEqQ.add(member.getMemberId());
						

						vEqP.add("claimTypeId.claimTypeId");
						vEqQ.add(ClaimType.REIMBURESEMENT);

					}
				}
				else if (user.getUser().getUserType().intValue() == User.TPA){
					Member member = memberService.getMemberByCardNumber(cardNumber);
					
					if (member != null){

						vEqP.add("memberId.memberId");
						vEqQ.add(member.getMemberId());
					}
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

			count = claimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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

			String required[] = new String[] {};

			collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,required, 0, count);

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

				collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, 0, count);
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			result.addObject("Claims", collection);

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
	public ModelAndView searchProviderPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			boolean isAuthorized = isAuthorized(request);
			
			if(!isAuthorized){
				// redirect to somewhere
				return notAuthorized();
			}
			
			
			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			Integer countryId = WebUtil.getParameterInteger(request, "countryId");
			Integer provinceId = WebUtil.getParameterInteger(request, "provinceId");
			Integer cityId = WebUtil.getParameterInteger(request, "cityId");
			
			Integer providerCategoryId = WebUtil.getParameterInteger(request, "providerCategoryId");

			ActionUser user = securityService.getActionUser(request);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection<Provider> collection = null;

			int rowsetint = 0;
			int count = 10;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));

			if (providerCategoryId != null){
				vEqP.add("providerCategoryId.providerCategoryId");
				vEqQ.add(providerCategoryId);
			}
			if (cityId != null){
				vEqP.add("cityId.id");
				vEqQ.add(cityId);
			}
			if (provinceId != null){
				vEqP.add("provinceId.id");
				vEqQ.add(provinceId);
			}

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

			String required[] = new String[] {};

			collection = providerService.search(sLikeP, sLikeQ, sEqP, sEqQ,required, 0, countSet);

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
						required, 0, countSet);
			}

			request.setAttribute("countryId", countryId);
			request.setAttribute("provinceId", provinceId);
			request.setAttribute("cityId", cityId);
			request.setAttribute("providerCategoryId", providerCategoryId);
			request.setAttribute("navigation", navigation);

			result.addObject("providerList", collection);
			
			Collection<RefProvince> provinceList = refProvinceService.getAll();
			result.addObject("provinceList", provinceList);
			
			Collection<ProviderCategory> provCategoryList = providerCategoryService.getAll();
			result.addObject("providerCategoryList", provCategoryList);
			
			if (provinceId != null){
				String[] eqParamCity = {"provinceId.id","deletedStatus"};
				Object[] eqValueCity = {provinceId,0};
				
				int totalCity = refCityService.getTotal(null,null,eqParamCity,eqValueCity);
				Collection<RefCity> cityList = refCityService.search(null,null,eqParamCity,eqValueCity,0,totalCity);
				
				result.addObject("cityList", cityList);

			}

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
	public ModelAndView searchMedicinePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			boolean isAuthorized = isAuthorized(request);
			
			if(!isAuthorized){
				// redirect to somewhere
				return notAuthorized();
			}
			
			
			Integer rowset = WebUtil.getParameterInteger(request, "rowset");
			Integer index = WebUtil.getParameterInteger(request, "index");
			String navigation = WebUtil.getParameterString(request,"navigation", "");
			Integer categoryId = WebUtil.getParameterInteger(request, "categoryId");
			
			ActionUser user = securityService.getActionUser(request);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection<Provider> collection = null;

			int rowsetint = 0;
			int count = 10;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

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

			String required[] = new String[] {};

			collection = providerService.search(sLikeP, sLikeQ, sEqP, sEqQ,required, 0, countSet);

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
						required, 0, countSet);
			}

			request.setAttribute("categoryId", categoryId);
			request.setAttribute("navigation", navigation);

			result.addObject("providerList", collection);
			
			


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

	public ModelAndView registerCasePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user = null;
			
			if (!isAuthorized(request)){
				return notAuthorized();
			}
			else {
				user = securityService.getActionUser(request);	
			}
			

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			Member member = memberService.get(memberId);

			result = new ModelAndView("registerCase");
			result.addObject("memberId", memberId);
			
			if (user.getUser().getUserType().intValue() == User.PROVIDER){
				Provider provider = providerService.get(user.getUser().getProviderId().getProviderId());
				ProviderCategory provCategory = provider.getProviderCategoryId();
				
				if (member != null){
				
					result.addObject("member", member);					
					SubscriptionStatus status = statusService.get(member.getStatus());
					
					result.addObject("status", status.getStatus());
					
					DateFormat df = new SimpleDateFormat("dd MMM yyyy");
					String expDate = df.format(member.getExpireDate());
					result.addObject("expireDate", expDate);
				
					if (provCategory.getProviderCategoryId().intValue() == ProviderCategory.PERSONAL_DOCTOR 
							|| provCategory.getProviderCategoryId().intValue() == ProviderCategory.PPK1_UMUM){
						
						String[] eqBenefitParam = {"deletedStatus","memberId.memberId","caseCategoryId.caseCategoryId","memberBenefitStatus"};
						Object[] eqBenefitValue = {0,member.getMemberId(),CaseCategory.GP_OUTPATIENT,SubscriptionStatus.ACTIVE};
						
						int totalBenefit = memberBenefitService.getTotal(null,null,eqBenefitParam,eqBenefitValue);
						Collection<MemberBenefit> benefitList = memberBenefitService.search(null,null,eqBenefitParam,eqBenefitValue,0,totalBenefit);
						
						result.addObject("benefitList", benefitList);
					}
					
				}
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
	public ModelAndView listMedicineCategoryPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user = null;
			
			if (!isAuthorized(request)){
				return notAuthorized();
			}
			else {
				user = securityService.getActionUser(request);	
			}
			

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			Member member = memberService.get(memberId);

			result = new ModelAndView("registerCase");
			result.addObject("memberId", memberId);
			

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView inquiryMemberPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user = null;
			
			if (!isAuthorized(request)){
				return notAuthorized();
			}
			else {
				user = securityService.getActionUser(request);	
			}
			

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			String login = WebUtil.getParameterString(request, "login", "");
			String password = WebUtil.getParameterString(request, "password", "");
			
			

			if (navigation.equalsIgnoreCase("inquiry")){
				result = new ModelAndView("inquiryMember");
			}
			else if (navigation.equalsIgnoreCase("doinquiry")){
				String memberNumber = WebUtil.getParameterString(request, "memberNumber", "");
				
				result = new ModelAndView("detailMember");
				
				Member member = memberService.getMember(memberNumber);
				
				result.addObject("member", member);
				
				DateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
				String expireDate = sdf.format(member.getExpireDate());
				result.addObject("expireDate", expireDate);
				
				SubscriptionStatus status = statusService.get(member.getStatus());
				result.addObject("status", status.getStatus());
				
			}

			result.addObject("login", login);
			result.addObject("password", password);
			
			



		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView referCasePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user = null;
			
			if (!isAuthorized(request)){
				return notAuthorized();
			}
			else {
				user = securityService.getActionUser(request);	
			}		

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			result = new ModelAndView("referCase");
			
			Case theCase = caseService.get(caseId);
			
			if (navigation.equalsIgnoreCase("saverefer")){
				
				
				result = detailCasePerformed(request, response);
			}
			else {
				
				Collection<Poliklinik> poliklinikList = poliklinikService.getAll();
				
				if (theCase != null){
					result.addObject("case", theCase);
					result.addObject("poliklinikList", poliklinikList);
				}
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
	public ModelAndView closeCasePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user = null;
			
			if (!isAuthorized(request)){
				return notAuthorized();
			}
			else {
				user = securityService.getActionUser(request);	
			}		

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			result = new ModelAndView("referCase");
			
			Case theCase = caseService.get(caseId);
			

			request.setAttribute("caseId", caseId);
			
			if (theCase != null){
				boolean res = caseService.closeCase(theCase, user);
				
				if (res){
					request.setAttribute("alert", "<b>Success Close Case</b>");
				}
				else {
					request.setAttribute("alert", "<b>Failed Close Case</b>");
				}
			}
			

			result = detailCasePerformed(request, response);


		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView detailCasePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user = null;
			
			if (!isAuthorized(request)){
				return notAuthorized();
			}
			else {
				user = securityService.getActionUser(request);	
			}
			

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			

			String[] required = {"Case.MemberId.MemberGroupId"};
			Case theCase = caseService.get(caseId,required);
			

			result = new ModelAndView("detailCase");
			result.addObject("caseId", caseId);
			
			if (theCase != null){
				String[] eqParam = {"caseId.caseId","deletedStatus"};
				Object[] eqValue = {caseId,Integer.valueOf(0)};
				
				int totalCaseItem = caseItemService.getTotal(null,null,eqParam,eqValue);
				Collection<CaseItem> caseItemList = caseItemService.search(null,null,eqParam,eqValue,0,totalCaseItem);
				
				
				result.addObject("myCase", theCase);
				result.addObject("caseItemList", caseItemList);
				
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
	public ModelAndView detailProviderPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user = null;
			
			if (!isAuthorized(request)){
				return notAuthorized();
			}
			else {
				user = securityService.getActionUser(request);	
			}
			

			Integer providerId = WebUtil.getParameterInteger(request, "providerId");


			result = new ModelAndView("detailProvider");

			Provider provider = providerService.get(providerId);
			
			if (provider != null){
				String[] eqParam = {"providerId.providerId","deletedStatus"};
				Object[] eqValue = {providerId,Integer.valueOf(0)};
				
				int totalPoli = providerPoliklinikService.getTotal(null,null,eqParam,eqValue);
				Collection<ProviderPoliklinik> poliklinikList = providerPoliklinikService.search(null,null,eqParam,eqValue,0,totalPoli);
				
				int totalDoctor = providerDoctorService.getTotal(null,null,eqParam,eqValue);
				Collection<ProviderDoctor> doctorList = providerDoctorService.search(null,null,eqParam,eqValue,0,totalDoctor);
				
	
				
				result.addObject("provider", provider);
				result.addObject("poliklinikList", poliklinikList);
				result.addObject("doctorList", doctorList);
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

	public ModelAndView searchExcessPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			boolean isAuthorized = isAuthorized(request);
			
			if(!isAuthorized){
				// redirect to somewhere
				return notAuthorized();
			}
			
			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			String searchtext = WebUtil.getParameterString(request,"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby","");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			String cardNumber = WebUtil.getParameterString(request, "cardnum", "");

			ActionUser user = securityService.getActionUser(request);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));

			if (user != null){
				if (user.getUser().getUserType().intValue() == User.MEMBER){
					Member member = user.getUser().getMemberId();
					if (member != null){
						vEqP.add("memberId.memberId");
						vEqQ.add(member.getMemberId());
					}
				}
				else if (user.getUser().getUserType().intValue() == User.TPA){
					Member member = memberService.getMemberByCardNumber(cardNumber);
					
					if (member != null){

						vEqP.add("memberId.memberId");
						vEqQ.add(member.getMemberId());
					}
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

			count = claimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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

			String required[] = new String[] {};

			collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,required, 0, count);

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

				collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, 0, count);
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			result.addObject("Claims", collection);

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
	public ModelAndView addMedicinePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user = null;
			
			if (!isAuthorized(request)){
				return notAuthorized();
			}
			else {
				user = securityService.getActionUser(request);	
			}		

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");			

			result = new ModelAndView("addMedicine");
			result.addObject("caseId", caseId);

			request.setAttribute("caseId", caseId);
			
			if (navigation.equalsIgnoreCase("savemedicine")){
				Integer medicineId = WebUtil.getParameterInteger(request, "medicineId");
				Double amount = WebUtil.getParameterDouble(request, "itemAmount");
				Double totalValue = WebUtil.getParameterDouble(request, "itemValue");
				
				Medicine medicine = medicineService.get(medicineId);
				
				if (medicine != null){
					Case theCase = caseService.get(caseId);
					CaseMedicine med = new CaseMedicine();
					med.setCaseId(theCase);
					med.setTotalUsage(amount);
					med.setTotalCharge(totalValue);
					med.setMedicineId(medicine);
					med.setStatus(1);
					med.setReferencePrice(medicine.getMedicinePrice());
					
					caseMedicineService.create(med, user);
				}
				
				
				result = detailCasePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("addmedicine")){
				
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
	public ModelAndView addDiagnosticPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user = null;
			
			if (!isAuthorized(request)){
				return notAuthorized();
			}
			else {
				user = securityService.getActionUser(request);	
			}		

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");			

			result = new ModelAndView("addDiagnostic");
			
			if (navigation.equalsIgnoreCase("savediagnostic")){
				request.setAttribute("caseId", caseId);
				
				result = detailCasePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("adddiagnostic")){
				String[] eqParam = {"deletedStatus"};
				Object[] eqValue = {};
			}
			
			result.addObject("caseId", caseId);


		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView addProcedurePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {			
			ActionUser user = null;
			
			if (!isAuthorized(request)){
				return notAuthorized();
			}
			else {
				user = securityService.getActionUser(request);	
			}			

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");		

			result = new ModelAndView("addCaseProcedure");
			result.addObject("caseId", caseId);
			
			if (navigation.equalsIgnoreCase("saveprocedure")){
				request.setAttribute("caseId", caseId);
				
				Integer procedureId = WebUtil.getParameterInteger(request, "caseProcedureId");
				Double charge  = WebUtil.getParameterDouble(request, "itemValue");
				
				Case theCase = caseService.get(caseId);
				
				if (theCase != null){
					
					Procedure procedure = procedureService.get(procedureId);
					
					CaseProcedure caseProcedure = new CaseProcedure();
					caseProcedure.setCaseId(theCase);
					caseProcedure.setDeletedStatus(0);
					caseProcedure.setTotalCharge(charge);
					caseProcedure.setProcedureId(procedure);
					caseProcedure.setReferencePrice(procedure.getProcedurePrice());
					
					caseProcedureService.create(caseProcedure, user);
					
					
				}
				
				result = detailCasePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("addprocedure")){
				
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
	
	public ModelAndView addItemPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {			
			ActionUser user = null;
			
			if (!isAuthorized(request)){
				return notAuthorized();
			}
			else {
				user = securityService.getActionUser(request);	
			}			

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");		

			result = new ModelAndView("addCaseItem");
			result.addObject("caseId", caseId);
			
			if (navigation.equalsIgnoreCase("saveitem")){
				request.setAttribute("caseId", caseId);
				
				Case theCase = caseService.get(caseId);
				
				Integer caseItemId = WebUtil.getParameterInteger(request, "caseItemId");
				Double amount = WebUtil.getParameterDouble(request, "itemAmount");
				Double itemValue = WebUtil.getParameterDouble(request, "itemValue");
				
				Item item = itemService.get(caseItemId);
				if (item != null && theCase != null){
					CaseItem caseItem = new CaseItem();
					caseItem.setCaseId(theCase);
					caseItem.setItemId(item);
					caseItem.setUsageAmount(amount);
					caseItem.setUsageValue(itemValue);
					caseItem.setDeletedStatus(0);
					
					caseItemService.create(caseItem, user);
				}
				
				result = detailCasePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("additem")){
				
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
	public ModelAndView requestGLPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {			
			ActionUser user = null;
			
			if (!isAuthorized(request)){
				return notAuthorized();
			}
			else {
				user = securityService.getActionUser(request);	
			}			

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");		

			result = new ModelAndView("requestGL");
			
			if (navigation.equalsIgnoreCase("savegl")){
				request.setAttribute("caseId", caseId);				
				result = detailCasePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("requestgl")){
				
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
	public ModelAndView caseMonitoringPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {			
			ActionUser user = null;
			
			if (!isAuthorized(request)){
				return notAuthorized();
			}
			else {
				user = securityService.getActionUser(request);	
			}			

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");		

			result = new ModelAndView("addCaseMonitoring");
			
			if (navigation.equalsIgnoreCase("savecasemonitoring")){
				request.setAttribute("caseId", caseId);				
				String anamnesa = WebUtil.getParameterString(request, "anamnesa", "");
				String remarks = WebUtil.getParameterString(request, "remarks", "");
				String therapy = WebUtil.getParameterString(request, "therapy", "");
				Integer categoryId = WebUtil.getParameterInteger(request, "categoryId");
				Integer procedureId = WebUtil.getParameterInteger(request, "procedureId");
				
				CaseEvent caseEvent = new CaseEvent();
				
				if (categoryId != null){
					EventCategory eventCategory = new EventCategory();
					eventCategory.setEventCategoryId(categoryId);
					caseEvent.setEventCategoryId(eventCategory);					
				}				
				if (procedureId != null){
					Procedure procedure = new Procedure();
					procedure.setProcedureId(procedureId);
					
					CaseInvestigation caseInvestigation = new CaseInvestigation();
				}
				
				caseEventService.create(caseEvent, user);
				result = detailCasePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("casemonitoring")){
				Collection<EventCategory> eventList = eventCategoryService.getAll();
				result.addObject("eventList", eventList);
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
	private boolean isAuthorized(HttpServletRequest request){
		boolean result = false;
		
		try {
			String username = WebUtil.getParameterString(request, "login", "");
			String password = WebUtil.getParameterString(request, "password", "");
			
			User user = securityService.getCurrentUser(request);
			
			System.out.println("USER NYA = " + user + " login = " + username + " password = " + password);
			if (user != null){
				result = true;
			}
			else {
				String ipAddress = request.getRemoteAddr();
				String session = request.getSession().getId();
				HttpSession httpSession = request.getSession(true);
				
				user= userService.loginMobile(username, password, session, ipAddress);
				if (user != null){
					System.out.println("LOGIN USERNAME = " + user.getUsername() + " UserType = " + user.getUserType());
				}
				httpSession.setAttribute("theUser", user);
				result = true;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public ModelAndView loginPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView result = null;
		try {
			result = new ModelAndView("login");			
			User user = null;
			HttpSession session = request.getSession();
			session.invalidate();
			session = null;
			if (isAuthorized(request)){
				user = securityService.getCurrentUser(request);
				if (user != null){
					String userType = "";
					
					if (user.getUserType().intValue() == User.PROVIDER){
						userType = "PROVIDER";
					}
					else if (user.getUserType().intValue() == User.MEMBER){
						userType = "MEMBER";
					}
					else if (user.getUserType().intValue() ==  User.MEMBER_GROUP){
						userType = "GROUP";
					}
					result.addObject("result", "OK|" + userType);	
				}
			}
			else {
				result.addObject("result", "NOK");	
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
	private ModelAndView notAuthorized(){
		ModelAndView result = new ModelAndView("notAuthorized");
		
		return result;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String navigation = request.getParameter("navigation") == null ? "welcome"
				: request.getParameter("navigation");

		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);

		try {
			String username = WebUtil.getParameterString(request, "login", "");
			String password = WebUtil.getParameterString(request, "password", "");
			
			if (navigation.equalsIgnoreCase("listsettlement")){
				result = searchCaseSettlementPerformed(request, response, "searchCaseSettlement");
			}
			else if (navigation.equalsIgnoreCase("detailcase")){
				result = detailCasePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("detailprovider")){
				result = detailProviderPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("refercase") || navigation.equalsIgnoreCase("saverefer") ){
				result = referCasePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("closecase") ){
				result = closeCasePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("searchprovider") || navigation.equalsIgnoreCase("gosearchprovider")){
				result = searchProviderPerformed(request, response, "searchProvider");
			}
			else if ( navigation.equalsIgnoreCase("searchmedicine")){
				result = searchMedicinePerformed(request, response, "searchMedicine");
			}
			else if (navigation.equalsIgnoreCase("listmedicine") ){
				result = listMedicineCategoryPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("listmemberclaim")){
				result = searchClaimPerformed(request, response, "myClaimList");
			}
			else if (navigation.equalsIgnoreCase("listclaim")){
				result = searchClaimPerformed(request, response, "myClaimList");
			}
			else if (navigation.equalsIgnoreCase("listexcess")){
				result = searchExcessPerformed(request, response, "myClaimList");
			}
			else if (navigation.equalsIgnoreCase("addmedicine") || navigation.equalsIgnoreCase("savemedicine")){
				result = addMedicinePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("additem") || navigation.equalsIgnoreCase("saveitem")){
				result = addItemPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("requestgl") || navigation.equalsIgnoreCase("savegl")){
				result = requestGLPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("casemonitoring") || navigation.equalsIgnoreCase("savecasemonitoring")){
				result = caseMonitoringPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("addprocedure") || navigation.equalsIgnoreCase("saveprocedure")){
				result = addProcedurePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("adddiagnostic") || navigation.equalsIgnoreCase("savediagnostic")){
				result = addDiagnosticPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("registercase") || navigation.equalsIgnoreCase("doregistercase") || navigation.equalsIgnoreCase("registerreference")){
				result = registerCasePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("inquiry") || navigation.equalsIgnoreCase("doinquiry")){
				result = inquiryMemberPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("login")){
				result = loginPerformed(request, response);
			}
			else {
				result = registerCasePerformed(request, response);	
			}
			result.addObject("login", username);
			result.addObject("password",password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

}
