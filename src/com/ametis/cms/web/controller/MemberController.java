package com.ametis.cms.web.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
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
import org.joda.time.DateTime;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyClausul;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.datamodel.ProductTypePoliklinik;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderCategory;
import com.ametis.cms.datamodel.ProviderPoliklinik;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberElectronicCardService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyClausulService;
import com.ametis.cms.service.PolicyMemberMovementService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderPoliklinikService;
import com.ametis.cms.service.ProductTypePoliklinikService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.StringUtil;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * Member is a servlet controller for member Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class MemberController implements Controller

// extends+

// extends-

{

	private MemberProductService memberProductService;
	private MemberGroupService memberGroupService;
	private MemberBenefitService memberBenefitService;
	private com.ametis.cms.service.ProviderService providerService;
	private PolicyClausulService policyClausulService;
	
	private MemberService memberService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;
	
	private ConfigurationService configurationService;
	
	private SecurityService securityService;
	private ActivityLogService logService;
	private PolicyMemberMovementService policyMemberMovementService;
	private PolicyService policyService;
	private CaseCategoryService caseCategoryService;
	private CaseService caseService;
	private DiagnosisService diagnosisService;
	
	private ProductTypePoliklinikService productTypePoliklinikService;
	private ProviderPoliklinikService providerPoliklinikService;
	
	private MemberElectronicCardService memberElectronicCardService;
	
	//Add by aju on 20150928, make iframe param public fufufu :D
	private String usingIFrame;
	private String iFrameClientMemberId;
	private String iFrameLevelLogin;
	private String sessionMemberId;
	private String sessionMemberParentId;
	private boolean isIFrameSession;
	
	
	public ProductTypePoliklinikService getProductTypePoliklinikService() {
		return productTypePoliklinikService;
	}

	public void setProductTypePoliklinikService(
			ProductTypePoliklinikService productTypePoliklinikService) {
		this.productTypePoliklinikService = productTypePoliklinikService;
	}

	public ProviderPoliklinikService getProviderPoliklinikService() {
		return providerPoliklinikService;
	}

	public void setProviderPoliklinikService(
			ProviderPoliklinikService providerPoliklinikService) {
		this.providerPoliklinikService = providerPoliklinikService;
	}

	public PolicyClausulService getPolicyClausulService() {
		return policyClausulService;
	}

	public void setPolicyClausulService(PolicyClausulService policyClausulService) {
		this.policyClausulService = policyClausulService;
	}

	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}
	
	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}

	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}

	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}

	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}

	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
	}

	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}

	public void setMemberBenefitService(MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}

	public com.ametis.cms.service.ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(com.ametis.cms.service.ProviderService providerService) {
		this.providerService = providerService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public PolicyMemberMovementService getPolicyMemberMovementService() {
		return policyMemberMovementService;
	}

	public void setPolicyMemberMovementService(
			PolicyMemberMovementService policyMemberMovementService) {
		this.policyMemberMovementService = policyMemberMovementService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
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

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public MemberService getMemberService() {
		return this.memberService;
	}

	public MemberElectronicCardService getMemberElectronicCardService() {
		return memberElectronicCardService;
	}

	public void setMemberElectronicCardService(
			MemberElectronicCardService memberElectronicCardService) {
		this.memberElectronicCardService = memberElectronicCardService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = memberId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEMEMBER");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEMEMBER access");
				return errorResult;
				
			}
			Member res = memberService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.member", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties
						.getMessage("fail.delete.member", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchMember");
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
	public ModelAndView registerRKIPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			String cardNumber = WebUtil.getParameterString(request, "cardNumber", "");
			Integer caseCategoryId = WebUtil.getParameterInteger(request, "caseCategoryId");
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			String providerName = WebUtil.getParameterString(request, "providerName", "");
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "OPENCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for OPENCLAIM access");
				return errorResult;
				
			}	
			
			if (navigation.equalsIgnoreCase("registerrki")){
							
				result = new ModelAndView("registerRKIClaim");
			
			}
			else if (navigation.equalsIgnoreCase("saveregisterrki")){
				
				boolean proses = true;
				
				
				if (proses){
					Member member = memberService.getMemberByCardNumber(cardNumber);
					Provider provider = null;
					if (providerId != null){
						provider = providerService.get(providerId);
					}
					
					result = new ModelAndView("detailRegisterRKI");
					
					if (member != null){
						result.addObject("providerId", providerId);
						result.addObject("caseCategoryId", caseCategoryId);
						result.addObject("memberId", member.getMemberId());
						result.addObject("registerStatus", Integer.valueOf(1));
						
						Collection<MemberBenefit> memberBenefitList = memberBenefitService.getMemberBenefitList(member.getMemberId(), caseCategoryId);
						
						result.addObject("benefitList", memberBenefitList);
					
						result.addObject("member",member);
						result.addObject("provider", provider);
						result.addObject("registerDate", new java.sql.Date(System.currentTimeMillis()));
					}
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
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			//add by aju on 20150928, sorry, no url param changes for member access fufufu :D
			if(isIFrameSession && sessionMemberParentId != null){
				System.out.println("it\'s still on member(" + sessionMemberId + ") login session fufufu...");
				//check first if the memberId passed from the url is on the same parentId fufufu
				memberId=(memberId==null?Integer.parseInt(sessionMemberId):memberId);
				java.io.Serializable theMemberIdParam = memberId;
				Member checkMember = memberService.get(theMemberIdParam);
				
				if(checkMember!=null){
					//make it back to default if the memberId from param is injected wekawekaweka :))))
					if(checkMember.getParentMember().getMemberId() != Integer.parseInt( sessionMemberParentId)){
						System.out.println("aha! gotcha! sorry, you cannot see others member data fufufu :D");
						memberId = Integer.parseInt(sessionMemberId);
						
						ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
						errorResult.addObject("errorType","OtherFamilyAccessDenied");			
						errorResult.addObject("errorMessage", "Hey member("+ sessionMemberId + "), are you missing your way? :P<br/>" 
								+ "<a href=\"member?navigation=detail&memberId="+memberId+"\">Go Back</a>");
						return errorResult;
						
					}
				}
			}

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby","");			

			result = new ModelAndView(location);
			java.io.Serializable pkey = memberId;
			
			String[] required = {"Member.MemberGroupId","Member.ClientId",
					"Member.RelationshipId","Member.ParentMember","Member.CurrentPolicyId"};
			Member object = memberService.get(pkey,required);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.member", null, "fail", Locale.getDefault()));
			
			}
			else {
				String[] reqProduct = new String[] {
						// foreign affairs
						"MemberProduct.MemberProductStatus",
						"MemberProduct.MemberId", "MemberProduct.ProductId",
						"MemberProduct.ProductId.CaseCategory","MemberProduct.MemberId.ClientId",
						"MemberProduct.MemberProductStatus",
						"MemberProduct.ProductId.ProductType","MemberProduct.FamilyProductId","MemberProduct.SecondaryCoverageId"
				// foreign affairs end
				};
				String[] eqParam = {"memberId.memberId","deletedStatus","memberProductStatus.statusId"};
				Object[] eqValue = {object.getMemberId(),0,SubscriptionStatus.ACTIVE};
				
				int total = memberProductService.getTotal(null,null,eqParam,eqValue);
				Collection<MemberProduct> productList = memberProductService.search(null,null,eqParam,eqValue,reqProduct,0,total);
				result.addObject("MemberProducts", productList);
				
				//Add by aju on 20150803, for linked card COB :D
				if(object.getLinkedCardNumber() != null && !object.getLinkedCardNumber().equalsIgnoreCase("")){
					MemberElectronicCard mec = memberElectronicCardService.searchUnique("cardNumber", object.getLinkedCardNumber());
					System.out.println("MemberElectronicCard : " + mec);
					
					if(mec == null){
						System.out.println("Can't find MemberElectronicCard for " + object.getLinkedCardNumber());
					}
					
					result.addObject("MemberLinkedCard", mec);
				}
			}

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
						
			result.addObject("member", object);
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
	public ModelAndView printRKIPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			Integer caseCategoryId = WebUtil.getParameterInteger(request, "caseCategoryId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",	"");

			result = new ModelAndView(location);
			java.io.Serializable pkey = memberId;
			String[] required = {"Member.MemberGroupId"};
			Member object = memberService.get(pkey,required);
			
			Provider provider = null;
			CaseCategory caseCategory = null;
			DateFormat dtFormat = new SimpleDateFormat("dd MMMM yyyy, HH:mm");
			String dateTime = dtFormat.format(new java.util.Date(System.currentTimeMillis()));
			
			String[] eqClausul = {"policyId.policyId","caseCategoryId.caseCategoryId","deletedStatus","isEdc"};
			Object[] eqClausulVal = {object.getCurrentPolicyId().getPolicyId(),caseCategoryId,0,1};
			
			int totalEdcClausul = policyClausulService.getTotal(null,null,eqClausul,eqClausulVal);
			Collection<PolicyClausul> edcClausul = policyClausulService.search(null,null,eqClausul,eqClausulVal,0,totalEdcClausul);
			
			if (providerId != null){
				provider = providerService.get(providerId);
			}
			if (caseCategoryId != null){
				caseCategory = caseCategoryService.get(caseCategoryId);
			}

			if (object != null && caseCategory != null){
				Collection<MemberBenefit> memberBenefitList = memberBenefitService.getMemberBenefitList(memberId, caseCategoryId);
				result.addObject("memberBenefitList", memberBenefitList);
				
				DateFormat format = new SimpleDateFormat("dd MMM yyyy");
				String expireDate = format.format(object.getExpireDate());
				result.addObject("expireDate", expireDate);
			}
			result.addObject("dateTime", dateTime);
			
			
			result.addObject("caseCategory", caseCategory);
			result.addObject("provider", provider);
			result.addObject("member", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("policyClausulList", edcClausul);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView testAuthorizeMember(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView result = null;
		
		try {

			result = new ModelAndView("testAuthorizeResult");

			String cardNumber = WebUtil.getParameterString(request, "cardNumber", "");
			String merchantId = WebUtil.getParameterString(request, "merchantId", "");
				Member member = memberService.getMemberByCardNumber(cardNumber);

				Provider provider = providerService.getByProviderCode(merchantId);
				CaseCategory caseCategory = caseCategoryService
						.getCaseCategory("OP");
				
				
				if (member != null && member.getStatus().intValue() == SubscriptionStatus.ACTIVE) {

					Collection<MemberBenefit> memberBenefitList = memberBenefitService.getMemberBenefitList(member.getMemberId(),
							caseCategory.getCaseCategoryId());
					
					boolean isManagedCare = false;
					boolean isIndemnity = false;
					boolean isRefered = false;
					
					Policy policy = policyService.getGroupActivePolicy(member.getMemberGroupId().getMemberGroupId(), member.getClientId().getClientId());
					
					if (policy != null){
						if (policy.getPolicyType().intValue() == 1){
							isIndemnity = true;
						}
						else if (policy.getPolicyType().intValue() == 2){
							isManagedCare = true;
						}
					}
					
					
					
					MemberProduct product = memberProductService.getMemberActiveProduct(member.getMemberId(), caseCategory.getCaseCategoryId());

					String responseLine = "";
					int index = 0;
					

					if (product != null && product.getActualBenefitLimit() != null && product.getActualBenefitLimit().doubleValue() > 0){

						if (memberBenefitList != null) {
							Vector<String> sendItemList = new Vector<String>();
							
							for (Iterator iterator = memberBenefitList.iterator(); iterator
									.hasNext();) {
		
								MemberBenefit memberBenefit = (MemberBenefit) iterator
										.next();
		
								if (memberBenefit != null
										&& memberBenefit.getIsEDCEnabled() != null && memberBenefit.getIsEDCEnabled().booleanValue()) {
									String itemCategoryName = "";
									
									if (memberBenefit.getItemCategoryId().getItemCategoryEDCName() != null){
										itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryEDCName();
									}
									else {
										itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryName();
									}
									if (index == 0) {
										responseLine += itemCategoryName
												+ ","
												+ memberBenefit.getItemCategoryId()
												.getItemCategoryCode();
										
										if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {											
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getCurrentBenefitPosition().doubleValue(), 9);											
													responseLine += "," + limitSend;
												}
											}
										}
										else {
											if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
												if (memberBenefit.getBenefitLimit() != null){
													if (memberBenefit.getBenefitLimit() == -1){
														responseLine += ",AS CHARGE"; 
													}
													else {												
														
														String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
														responseLine += "," + limitSend;
														
													}
												}
											}
											else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
												double currentBenefit = 0;
												double totalBenefit = -1;
												double benefitUsage = 0;
												
												if (memberBenefit.getBenefitLimit() != null){
													totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
												}
												if (memberBenefit.getCurrentBenefitPosition() != null){
													currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
												}
												if (memberBenefit.getBenefitUsage() != null){
													benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
												}
												
												if (totalBenefit == -1){
													responseLine += ",DIJAMIN";
												}
												else {
													currentBenefit = totalBenefit - benefitUsage;
													double percentage = currentBenefit/totalBenefit*100;
													
													
													if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
														if (percentage < 10){
															responseLine += ",LMT <10%";
														}
														else if (percentage >= 10 && percentage < 30){
															responseLine += ",LMT <30%";
														}
														else if (percentage >= 30 && percentage < 50){
															responseLine += ",LMT <50%";
														}
														else {
															responseLine += ",DIJAMIN";
														}		
													}
													else {
														responseLine += ",DIJAMIN";
													}
												}
											}
										}
									} else {
										responseLine += "|" + itemCategoryName
										+ ","
										+ memberBenefit.getItemCategoryId()
										.getItemCategoryCode();
										
										if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {											
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);											
													responseLine += "," + limitSend;
												}
											}
										}
										else {
											if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
												if (memberBenefit.getBenefitLimit() != null){
													if (memberBenefit.getBenefitLimit() == -1){
														responseLine += ",AS CHARGE"; 
													}
													else {												
														String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
														responseLine += "," + limitSend;
													}
												}
											}
											else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
												double currentBenefit = 0;
												double totalBenefit = -1;
												double benefitUsage = 0;
												
												if (memberBenefit.getBenefitLimit() != null){
													totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
												}
												if (memberBenefit.getCurrentBenefitPosition() != null){
													currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
												}					
												if (memberBenefit.getBenefitUsage() != null){
													benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
												}
												if (totalBenefit == -1){
													responseLine += ",DIJAMIN";
												}
												else {
													currentBenefit = totalBenefit - benefitUsage;
		
													double percentage = currentBenefit/totalBenefit*100;
													
														
													if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
														if (percentage < 10){
															responseLine += ",LMT <10%";
														}
														else if (percentage >= 10 && percentage < 30){
															responseLine += ",LMT <30%";
														}
														else if (percentage >= 30 && percentage < 50){
															responseLine += ",LMT <50%";
														}
														else {
															responseLine += ",DIJAMIN";
														}		
													}
													else {
														responseLine += ",DIJAMIN";
													}
												}
											}
										}
									}
								}
								index++;
							}
						}
		
						Case theCase = new Case();
						theCase.setCaseCategoryId(caseCategory);
						theCase.setMemberId(member);
						theCase.setCaseStartTime(new java.sql.Date(System.currentTimeMillis()));
						
						
						if (caseCategory.getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
							theCase.setCaseEndTime(new java.sql.Date(System.currentTimeMillis()));
						}
						else {
							DateTime dt = new DateTime();
							DateTime endTime = dt.plusDays(4);
							
							theCase.setCaseEndTime(new java.sql.Date(endTime.getMillis()));
						}
						
						if (provider != null){
							theCase.setProviderId(provider);
						}
					
						ActionUser actionUser = new ActionUser();
						User user = new User();
						user.setUsername(merchantId);
						actionUser.setUser(user);
						CaseStatus status = new CaseStatus();
						status.setCaseStatusId(CaseStatus.CASE_OPEN);
						theCase.setCaseType(Case.CASE_EDC);
						theCase.setCaseStatusId(status);
						
						Case newCase = caseService.create(theCase, actionUser);
		
						result.addObject("result",newCase.getCaseNumber());
						
					}
					else {
						if (product != null && product.getActualBenefitLimit() != null && product.getActualBenefitLimit().doubleValue() == 0){
							if (product.getExcessPaymentType() != null && product.getExcessPaymentType().intValue() == 1){

								if (memberBenefitList != null) {
									Vector<String> sendItemList = new Vector<String>();
									
									for (Iterator iterator = memberBenefitList.iterator(); iterator
											.hasNext();) {
				
										MemberBenefit memberBenefit = (MemberBenefit) iterator
												.next();
				
										if (memberBenefit != null
												&& memberBenefit.getIsEDCEnabled() != null && memberBenefit.getIsEDCEnabled().booleanValue()) {
											String itemCategoryName = "";
											
											if (memberBenefit.getItemCategoryId().getItemCategoryEDCName() != null){
												itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryEDCName();
											}
											else {
												itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryName();
											}
											if (index == 0) {
												responseLine += itemCategoryName
														+ ","
														+ memberBenefit.getItemCategoryId()
														.getItemCategoryCode();
												
												if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
													if (memberBenefit.getBenefitLimit() != null){
														if (memberBenefit.getBenefitLimit() == -1){
															responseLine += ",AS CHARGE"; 
														}
														else {											
															String limitSend = StringUtil.convertEDCNumber(memberBenefit.getCurrentBenefitPosition().doubleValue(), 9);											
															responseLine += "," + limitSend;
														}
													}
												}
												else {
													if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
														if (memberBenefit.getBenefitLimit() != null){
															if (memberBenefit.getBenefitLimit() == -1){
																responseLine += ",AS CHARGE"; 
															}
															else {												
																
																String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
																responseLine += "," + limitSend;
																
															}
														}
													}
													else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
														double currentBenefit = 0;
														double totalBenefit = -1;
														double benefitUsage = 0;
														
														if (memberBenefit.getBenefitLimit() != null){
															totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
														}
														if (memberBenefit.getCurrentBenefitPosition() != null){
															currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
														}
														if (memberBenefit.getBenefitUsage() != null){
															benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
														}
														
														if (totalBenefit == -1){
															responseLine += ",DIJAMIN";
														}
														else {
															currentBenefit = totalBenefit - benefitUsage;
															double percentage = currentBenefit/totalBenefit*100;
															
															
															if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
																if (percentage < 10){
																	responseLine += ",LMT <10%";
																}
																else if (percentage >= 10 && percentage < 30){
																	responseLine += ",LMT <30%";
																}
																else if (percentage >= 30 && percentage < 50){
																	responseLine += ",LMT <50%";
																}
																else {
																	responseLine += ",DIJAMIN";
																}		
															}
															else {
																responseLine += ",DIJAMIN";
															}
														}
													}
												}
											} else {
												responseLine += "|" + itemCategoryName
												+ ","
												+ memberBenefit.getItemCategoryId()
												.getItemCategoryCode();
												
												if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
													if (memberBenefit.getBenefitLimit() != null){
														if (memberBenefit.getBenefitLimit() == -1){
															responseLine += ",AS CHARGE"; 
														}
														else {											
															String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);											
															responseLine += "," + limitSend;
														}
													}
												}
												else {
													if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
														if (memberBenefit.getBenefitLimit() != null){
															if (memberBenefit.getBenefitLimit() == -1){
																responseLine += ",AS CHARGE"; 
															}
															else {												
																String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
																responseLine += "," + limitSend;
															}
														}
													}
													else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
														double currentBenefit = 0;
														double totalBenefit = -1;
														double benefitUsage = 0;
														
														if (memberBenefit.getBenefitLimit() != null){
															totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
														}
														if (memberBenefit.getCurrentBenefitPosition() != null){
															currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
														}					
														if (memberBenefit.getBenefitUsage() != null){
															benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
														}
														if (totalBenefit == -1){
															responseLine += ",DIJAMIN";
														}
														else {
															currentBenefit = totalBenefit - benefitUsage;
				
															double percentage = currentBenefit/totalBenefit*100;
															
																
															if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
																if (percentage < 10){
																	responseLine += ",LMT <10%";
																}
																else if (percentage >= 10 && percentage < 30){
																	responseLine += ",LMT <30%";
																}
																else if (percentage >= 30 && percentage < 50){
																	responseLine += ",LMT <50%";
																}
																else {
																	responseLine += ",DIJAMIN";
																}		
															}
															else {
																responseLine += ",DIJAMIN";
															}
														}
													}
												}
											}
										}
										index++;
									}
								}
				
								Case theCase = new Case();
								theCase.setCaseCategoryId(caseCategory);
								theCase.setMemberId(member);
								theCase.setCaseStartTime(new java.sql.Date(System.currentTimeMillis()));
								
								
								
								if (caseCategory.getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
									theCase.setCaseEndTime(new java.sql.Date(System.currentTimeMillis()));
								}
								else {
									DateTime dt = new DateTime();
									DateTime endTime = dt.plusDays(4);
									
									theCase.setCaseEndTime(new java.sql.Date(endTime.getMillis()));
								}
								
								if (provider != null){
									theCase.setProviderId(provider);
								}
							
								ActionUser actionUser = new ActionUser();
								User user = new User();
								user.setUsername(merchantId);
								actionUser.setUser(user);
								CaseStatus status = new CaseStatus();
								status.setCaseStatusId(CaseStatus.CASE_OPEN);
								theCase.setCaseType(Case.CASE_EDC);
								theCase.setCaseStatusId(status);
								
								Case newCase = caseService.create(theCase, actionUser);
				
								result.addObject("result",responseLine);
							}
							else if (product.getExcessPaymentType() != null && product.getExcessPaymentType().intValue() == 0){
								result.addObject("result","BLOCKED");
							}
						}
					}
				}
				else {
					int status = member.getStatus().intValue();
					if (status == SubscriptionStatus.RESIGNED || status == -2){
						result.addObject("result","RESIGNED");
					}
					else if (status == SubscriptionStatus.TERMINATED ){
						result.addObject("result","TERMINATED");
					}
					else if (status == SubscriptionStatus.BLOCKED || status == SubscriptionStatus.INACTIVE){
						result.addObject("result","BLOCKED");
					}
					
				}


		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public ModelAndView	activatePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			result = new ModelAndView(location);
			
			java.io.Serializable pkey = memberId;
			
			ActionUser actionUser = securityService.getActionUser(request); 
			
			System.out.println("PRE ACTIVATING MEMBER");

			
			Member object = memberService.get(pkey);
			if (object != null){
				ActionResult activate = null;
				
				if (object.getStatus().intValue() == SubscriptionStatus.PENDING_UPGRADE){
					activate = memberService.activateUpgrade(memberId, actionUser);
				}
				else if (object.getStatus().intValue() == SubscriptionStatus.PENDING){
					activate = memberService.activate(memberId, actionUser);
				}
				String[] required = {"Member.MemberGroupId","Member.ClientId",
						"Member.RelationshipId","Member.ParentMember","Member.CurrentPolicyId"};
				object = memberService.get(pkey,required);
				
				System.out.println("POST ACTIVATING MEMBER");
	
				
				if (activate != null){
				
					if (!activate.isResult()) {
						result.addObject("alert","<b>Failed Activate Member - " + activate.getAdditionalMessage() + " </b>");
					}
					else {
						result.addObject("alert","<b>Success Activate Member</b>");
					}
				}
			}
			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.member", null, "fail", Locale.getDefault()));
			}
			
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("navigation", "detail");
			result.addObject("member", object);
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
	public ModelAndView	freeActivatePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			result = new ModelAndView(location);
			
			ActionUser actionUser = new ActionUser();
			actionUser.setActionQuery("ACTIVATE MEMBER");
			User theUser = new User();
			theUser.setUsername("system-daemon");
			
			actionUser.setUser(theUser);
			
			ActionResult activate = memberService.activate(memberId, actionUser);
			if (activate.isResult()){
				result.addObject("result", "OK|"+memberId);
			}
			else {
				result.addObject("result", "NOK|"+memberId);
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

	public ModelAndView	activateAllMemberPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");

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
			
			java.io.Serializable pkey = memberGroupId;
			
			ActionUser actionUser = securityService.getActionUser(request); 
			
			System.out.println("PRE ACTIVATING MEMBER");

			Collection<Object> objects = memberService.activateAllPendingMember(memberGroupId, actionUser);
			
			if (objects != null){
			
		
				for (Iterator iterator = objects.iterator(); iterator.hasNext();) {
					Object object = (Object) iterator.next();
					
					if (object instanceof Integer){
						memberService.activate((Integer)object, actionUser);
					}
				}
				
				result = new ModelAndView(new RedirectView("membergroup?navigation=detail&memberGroupId="+memberGroupId));
				result.addObject("alert","<b>Success Activate Member</b>");
					
				
			} 
			

			result.addObject("navigation", "detail");
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
	public ModelAndView	terminatePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			Date terminationDate = WebUtil.getParameterDate(request, "terminationDate");
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
			
			java.io.Serializable pkey = memberId;
			
			ActionUser actionUser = securityService.getActionUser(request); 
			
			System.out.println("PRE TERMINATION MEMBER");
			ActionResult terminate = memberService.terminate(memberId,terminationDate,  actionUser);
			System.out.println("POST TERMINATION MEMBER");
			Member object = (Member) terminate.getResultObject();

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.member", null, "fail", Locale.getDefault()));
			}
			if (terminate != null){
			
				if (!terminate.isResult()) {
					result.addObject("alert","<b>Failed Terminate Member - " + terminate.getAdditionalMessage() + " </b>");
				}
				else {
					result.addObject("alert","<b>Success Terminate Member</b>");
				}
			}
			
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("navigation", "detail");
			result.addObject("member", object);
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
	public ModelAndView	unblockPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String reason = WebUtil.getParameterString(request, "reason","");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			

			result = new ModelAndView(location);
			
			
			
			ActionUser actionUser = securityService.getActionUser(request); 
			
			System.out.println("PRE UNBLOCKING MEMBER");
			ActionResult terminate = memberService.unblock(memberId,reason,  actionUser);
			System.out.println("POST UNBLOCKING MEMBER");
			Member object = (Member) terminate.getResultObject();

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.member", null, "fail", Locale.getDefault()));
			}
			if (terminate != null){
			
				if (!terminate.isResult()) {
					result.addObject("alert","<b>Failed Unblock Member - " + terminate.getAdditionalMessage() + " </b>");
				}
				else {
					result.addObject("alert","<b>Success Unblock Member</b>");
				}
			}
			
			result.addObject("navigation", "detail");
			result.addObject("member", object);
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
	public ModelAndView	blockPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String reason = WebUtil.getParameterString(request, "reason","");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			
			Date startDate = WebUtil.getParameterDate(request, "suspendStartDate");
			Date endDate = WebUtil.getParameterDate(request, "suspendEndDate");
			

			result = new ModelAndView(location);
			
			java.io.Serializable pkey = memberId;
			
			ActionUser actionUser = securityService.getActionUser(request); 
			
			System.out.println("PRE BLOCK MEMBER");
			ActionResult terminate = memberService.block(memberId,reason,startDate,endDate,  actionUser);
			System.out.println("POST BLOCK MEMBER");
			Member object = (Member) terminate.getResultObject();

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.member", null, "fail", Locale.getDefault()));
			}
			if (terminate != null){
			
				if (!terminate.isResult()) {
					result.addObject("alert","<b>Failed Block Member - " + terminate.getAdditionalMessage() + " </b>");
				}
				else {
					result.addObject("alert","<b>Success Block Member</b>");
				}
			}
			
			
			result.addObject("navigation", "detail");
			result.addObject("member", object);
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
	public ModelAndView	authorizePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

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
			java.io.Serializable pkey = memberId;
			Member object = memberService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.member", null, "fail", Locale.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("member", object);
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
	public ModelAndView	upgradePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby","");
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			String product = WebUtil.getParameterString(request, "currentProductCode", "");
			Date effectiveDate = WebUtil.getParameterDate(request, "effectiveDate");

			result = new ModelAndView(location);
			java.io.Serializable pkey = memberId;
			
			ActionUser actionUser = securityService.getActionUser(request);
			String[] required = {"Member.ClientId","Member.MemberGroupId"};
			Member object = memberService.get(pkey,required);
			
			if (navigation.equalsIgnoreCase("preupgrade")){
				
			}
			else if (navigation.equalsIgnoreCase("saveupgrade")){
				
				object.setStatus(SubscriptionStatus.PENDING_UPGRADE);
				
				object.setProductUpgradeEffectiveDate(effectiveDate);
				object.setCurrentProductCode(product);
				object = memberService.update(object, actionUser);
				
				if (object == null) {
					request.setAttribute("alert", alertProperties.getMessage(
							"not.found.member", null, "fail", Locale.getDefault()));
				}			
				else {
					return detailPerformed(request, response, "detailMember");
				}
			}
			

			

			result.addObject("currentProductCode", product);
			result.addObject("member", object);
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
	public ModelAndView	exportPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			
			Integer status = WebUtil.getParameterInteger(request, "searchStatus");
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			
			result = new ModelAndView(location);
			
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			
			
			if (searchby != null) {
				/**
				 * ini querynya disesuaikan dengan apa yang mau di search
				 * default nya gue bikin template search by semua field yang
				 * tipenya 'String' -adhit
				 */

				if (searchby.equalsIgnoreCase("firstName")) {
					vLikeP.add("firstName");
					vLikeQ.add(searchtext);
				}
				if (searchby.equalsIgnoreCase("lastName")) {
					vLikeP.add("lastName");
					vLikeQ.add(searchtext);
				}
				if (searchby.equalsIgnoreCase("mobilePhone")) {
					vLikeP.add("mobilePhone");
					vLikeQ.add(searchtext);
				}
				if (searchby.equalsIgnoreCase("faximile")) {
					vLikeP.add("faximile");
					vLikeQ.add(searchtext);
				}
				if (searchby.equalsIgnoreCase("customerNumber")) {
					vLikeP.add("customerNumber");
					vLikeQ.add(searchtext);
				}
				
				if (searchby.equalsIgnoreCase("customerPolicyNumber")) {
					vLikeP.add("customerPolicyNumber");
					vLikeQ.add(searchtext);
				}
				
				if (searchby.equalsIgnoreCase("province")) {
					vLikeP.add("province");
					vLikeQ.add(searchtext);
				}
				if (searchby.equalsIgnoreCase("city")) {
					vLikeP.add("city");
					vLikeQ.add(searchtext);
				}
				
				if (searchby.equalsIgnoreCase("country")) {
					vLikeP.add("country");
					vLikeQ.add(searchtext);
				}
				if (searchby.equalsIgnoreCase("clientName")) {
					vLikeP.add("clientId.clientName");
					vLikeQ.add(searchtext);
				}
				if (searchby.equalsIgnoreCase("groupName")){
					vLikeP.add("memberGroupId.groupName");
					vLikeQ.add(searchtext);
				}
				if (searchby.equalsIgnoreCase("cardNumber")){
					vLikeP.add("currentCardNumber");
					vLikeQ.add(searchtext);
				}
				if (status != null && status.intValue() >= -1){
					vEqP.add("status.statusId");
					vEqQ.add(status);
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

			int count = memberService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);




			String required[] = new String[] {
					"Member.MemberType", "Member.ClientId",
					"Member.MemberGroupId", "Member.RelationshipId",
			};
			Collection<Member> collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ, required, 0, count);
			
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */
			
			int idx = 1;
			String data = "No,Member Number,Member Name,BirthDate,Gender,Effective Date, Expire Date, Group Name, Product Code";
			data += "\n\n";
			
			if (collection != null){
				Iterator<Member> iterator = collection.iterator();
				
				while (iterator.hasNext()){
					
					Member member = iterator.next();
					
					data += idx + ",'" + member.getCustomerPolicyNumber() + ",\"" + member.getFirstName() + "\","+member.getBirthday()+","+member.getGender()+","+member.getEffectiveDate()+","+member.getExpireDate()+","+member.getMemberGroupId().getGroupName()+","+member.getCurrentProductCode()+"\n";
					
					idx += 1;
					
					
				}
			}
			
			

			System.out.println("DATA : " + data);

			response.setContentType("application/x-csv");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			response.setHeader("Content-disposition", "inline; filename="
					+ "memberList.csv");

			ServletOutputStream sos = response.getOutputStream();

			response.setHeader("Content-length", Integer
					.toString(data.length()));

			sos.write(data.getBytes());
			sos.close();
			
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

	private ModelAndView memberBankAccountReport (HttpServletRequest request, HttpServletResponse response, String location){
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
			
			String useNumber = WebUtil.getParameterString(request, "usenumber","");

			Integer searchStatus = WebUtil.getParameterInteger(request,"status");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			String url = WebUtil.getParameterString(request, "url", "");
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			
			
			
			Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");
			
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			request.setAttribute("memberGroupId", memberGroupId);
			request.setAttribute("memberId", memberId);
			request.setAttribute("clientId",clientId);
			request.setAttribute("url", url);
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
			
			if (navigation.equals("gosearchaccount") ) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("firstName")) {
						vLikeP.add("firstName");
						vLikeQ.add(searchtext);
					}
			
					if (searchby.equalsIgnoreCase("customerNumber")) {
						vLikeP.add("customerNumber");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("customerPolicyNumber")) {
						vLikeP.add("customerPolicyNumber");
						vLikeQ.add(searchtext);
					}
					
					
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("clientId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")){
						vLikeP.add("memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchStatus != null && searchStatus.intValue() >= -1){
						vEqP.add("status.statusId");
						vEqQ.add(searchStatus);
					}
					
				}

			}
			
					
			if (clientId != null){
				vEqP.add("clientId.clientId");
				vEqQ.add(clientId);
			}

			if (memberGroupId != null){
				vEqP.add("memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);
			}
			
			if (memberId != null){
				vEqP.add("parentMember.memberId");
				vEqQ.add(memberId);
				
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

			count = memberService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			String arah = WebUtil.getParameterString(request, "arah", "");


			String required[] = new String[] {
			// foreign affairs
					"Member.MemberType", "Member.ClientId",
					"Member.MemberGroupId", "Member.RelationshipId",
			// foreign affairs end
			};
			collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required,0,count);


			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			String data = "No,Nama,Nomor Member, Group, Bank Account, Bank Account Name, Bank";
			
			data += "\n\n";
			
			int idx = 1;
			
			Iterator<Member> iterator = collection.iterator();
			
			while (iterator.hasNext()){
				
				Member member = iterator.next();
				
				data += idx + ","+ member.getFirstName() +","+member.getCustomerPolicyNumber()+","+ member.getMemberGroupId().getGroupName();
				data += "," + member.getBankAccount() + "," + member.getBankAccountName() + "," + member.getBank();
				data += "\n";
				
				idx += 1;
			}
			//System.out.println("DATA : " + data);
			
			response.setContentType("application/x-csv");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			response.setHeader("Content-disposition",
					"inline; filename="  
							+ "memberAccount.csv");

			ServletOutputStream sos = response.getOutputStream();

			response.setHeader("Content-length", Integer
					.toString(data.length()));
			
			
			sos.write(data.getBytes());
			sos.close();

			result.addObject("Members", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("usenumber", useNumber);

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
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			String useNumber = WebUtil.getParameterString(request, "usenumber","");

			String searchStatus = WebUtil.getParameterString(request,"status","");
			
			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			String dateBy = WebUtil.getParameterString(request, "dateBy", "");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			String url = WebUtil.getParameterString(request, "url", "");
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			if(isIFrameSession){				
				if (iFrameLevelLogin.equalsIgnoreCase("member")){
					memberId = Integer.parseInt(sessionMemberId);
				}
			}
			
            Integer policyId = WebUtil.getParameterInteger(request, "policyId");
			
			ActionUser user = securityService.getActionUser(request);
			
			Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");
			
			String arah = WebUtil.getParameterString(request, "arah", "");
			
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;
			
			
			boolean sortCardNumber = true, sortNameMember = true, sortBirthday = true,
					sortCustomerNo = true, sortClientName = true, sortClientId = true,
					sortGroupId = true, sortGroupName = true, sortEffectiveDate = true,
					sortExpireDate = true, sortPolicyNo = true, sortStatus = true;
			boolean order = true;

			request.setAttribute("memberGroupId", memberGroupId);
			request.setAttribute("memberId", memberId);
			request.setAttribute("clientId",clientId);
            request.setAttribute("policyId",policyId);
			request.setAttribute("url", url);
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
			if((!navigation.equalsIgnoreCase("gosearchbysort") && arah.isEmpty() && arah.equals("")) ||
					navigation.equals("gosearch")){
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}
			
			
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("golistgroup")  || navigation.equalsIgnoreCase("lookupjson") || navigation.equalsIgnoreCase("golistpolicy") ||
					navigation.equals("gosearchbysort") || (navigation.equals("") && !arah.isEmpty() || navigation.equalsIgnoreCase("listpolicy"))) {
				if (searchby != null) {
					

					if (searchby.equalsIgnoreCase("firstName")) {
						vLikeP.add("firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("lastName")) {
						vLikeP.add("lastName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("mobilePhone")) {
						vLikeP.add("mobilePhone");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("faximile")) {
						vLikeP.add("faximile");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("customerNumber")) {
						vLikeP.add("customerNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("policyNumber")) {
						vLikeP.add("currentPolicyNumber");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("customerPolicyNumber")) {
						vLikeP.add("customerPolicyNumber");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("province")) {
						vLikeP.add("province");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("country")) {
						vLikeP.add("country");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("clientId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")){
						vLikeP.add("memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("cardNumber")){
						vLikeP.add("currentCardNumber");
						vLikeQ.add(searchtext);
					}
					if (searchStatus != null && !searchStatus.equals("")){
						vEqP.add("status");
						vEqQ.add(Integer.valueOf(searchStatus));
					}
				}
			}
			
			if (url != null){
				if (url.equalsIgnoreCase("batchclaim-form") || url.equalsIgnoreCase("claim-form")){
					//vEqP.add("status.statusId");
					//vEqQ.add(SubscriptionStatus.ACTIVE);
				}
			}
			
			if (clientId != null){
				vEqP.add("clientId.clientId");
				vEqQ.add(clientId);
			}

			if (memberGroupId != null){
				vEqP.add("memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);
			}
			
			if (memberId != null){
				vEqP.add("parentMember.memberId");
				vEqQ.add(memberId);
				
			}
            if (policyId != null){
            	vEqP.add("currentPolicyId.policyId");
        		vEqQ.add(policyId);
            }
			
			if (user != null && user.getUser().getUserType() != null){
				if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
					vEqP.add("memberGroupId.memberGroupId");
					vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
				}
				else if (user.getUser().getUserType().intValue() == User.CLIENT){
					vEqP.add("clientId.clientId");
					vEqQ.add(user.getUser().getClientId().getClientId());
				}	
				else if (user.getUser().getUserType().intValue() == User.BROKER){
					//vEqP.add("providerId.providerId");
					//vEqQ.add(user.getUser().getProviderId().getProviderId());
					
					vEqP.add("brokerId.brokerId");
					vEqQ.add(user.getUser().getBrokerId().getBrokerId());
				}
								
			}

			
			//vEqP.add("tipe");
			//vEqQ.add("show");

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

			if (minDate != null
					&& !minDate.toString().equalsIgnoreCase("1970-01-01")
					&& maxDate != null
					&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

				String[] betweenColumn = { dateBy };
				Object[] minColumn = { minDate };
				Object[] maxColumn = { maxDate };
				
				count = memberService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,
						betweenColumn, minColumn, maxColumn);
			}else{
				count = memberService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
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
			

			String required[] = new String[] {"Member.ClientId","Member.MemberGroupId", "Member.ParentMember", "Member.Status"};
			
			//SORTING CHECKING START
			//Revision 05032015
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equals("cardnumber")){
						sortByColumn = "currentCardNumber";
						String sortbycard = WebUtil.getParameterString(request, "sortbycard", "");
						if(sortOrder!=null){
							Boolean sortByOrderCard = Boolean.valueOf(WebUtil.
									getParameterString(request, "sortByOrderCard", ""));
							sortCardNumber = !sortByOrderCard;
						}else{
							sortCardNumber = !sortOrder;
						}
						order = sortCardNumber;
					}else if(sortcolumn.equals("membername")){
						sortByColumn = "firstName";
						Boolean sortByOrderMemberName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderMemberName", ""));
						sortNameMember = !sortByOrderMemberName;
						order = sortNameMember;
					}else if(sortcolumn.equals("dateofbirth")){
						sortByColumn = "birthday";
						Boolean sortByOrderDOB = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderDOB", ""));
						sortBirthday = !sortByOrderDOB;
						order = sortBirthday;
					}else if(sortcolumn.equals("customernumber")){
						sortByColumn = "customerNumber";
						Boolean sortByOrderCusNo = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCusNo", ""));
						sortCustomerNo = !sortByOrderCusNo;
						order = sortCustomerNo;
					}else if(sortcolumn.equals("clientname")){
						sortByColumn = "clientName";
						Boolean sortByOrderClientName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderClientName", ""));
						sortClientName = !sortByOrderClientName;
						order = sortClientName;
					}else if(sortcolumn.equals("clientcode")){
						sortByColumn = "clientId";
						Boolean sortByOrderCCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCCode", ""));
						sortClientId = !sortByOrderCCode;
						order = sortClientId;
						
					}else if(sortcolumn.equals("group")){
						sortByColumn = "groupName";
						Boolean sortByOrderGroup = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderGroup", ""));
						sortGroupName = !sortByOrderGroup;
						order = sortGroupName;
					}else if(sortcolumn.equals("groupcode")){
						sortByColumn = "memberGroupId";
						Boolean sortByOrderGroupCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderGroupCode", ""));
						sortGroupId = !sortByOrderGroupCode;
						order = sortGroupId;
					}else if(sortcolumn.equals("effectivedate")){
						sortByColumn = "effectiveDate";
						Boolean sortByOrderEffective = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderEffective", ""));
						sortEffectiveDate = !sortByOrderEffective;
						order = sortEffectiveDate;
					}else if(sortcolumn.equals("expiredate")){
						sortByColumn = "expireDate";
						Boolean sortByOrderExpire = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderExpire", ""));
						sortExpireDate = !sortByOrderExpire;
						order = sortExpireDate;
					}else if(sortcolumn.equals("policynumber")){
						sortByColumn = "currentPolicyNumber";
						Boolean sortByOrderExpire = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderPolicy", ""));
						sortPolicyNo = !sortByOrderExpire;
						order = sortPolicyNo;
					}else{
						sortByColumn = "status";
						Boolean sortByOrderStatus = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderStatus", ""));
						sortStatus = !sortByOrderStatus;
						order = sortStatus;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equals("cardnumber")){
						sortCardNumber = order;
					}else if(sortcolumn.equals("membername")){
						sortNameMember = order;
					}else if(sortcolumn.equals("dateofbirth")){
						sortBirthday = order;
					}else if(sortcolumn.equals("customernumber")){
						sortCustomerNo = order;
					}else if(sortcolumn.equals("clientname")){
						sortClientName = order;
					}else if(sortcolumn.equals("clientcode")){
						sortClientId = order;
					}else if(sortcolumn.equals("group")){
						sortGroupName = order;
					}else if(sortcolumn.equals("groupcode")){
						order = sortGroupId;
					}else if(sortcolumn.equals("effectivedate")){
						sortEffectiveDate = order;
					}else if(sortcolumn.equals("expiredate")){
						sortExpireDate = order;
					}else if(sortcolumn.equals("policynumber")){
						sortPolicyNo = order;
					}else{
						sortStatus = order;
					}
				}
				if(minDate != null
						&& !minDate.toString().equalsIgnoreCase("1970-01-01")
						&& maxDate != null
						&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

					String[] betweenColumn = { dateBy };
					Object[] minColumn = { minDate };
					Object[] maxColumn = { maxDate };
					collection = memberService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, betweenColumn, minColumn, maxColumn, order, sortByColumn, required,
							rowsetint, countSet.intValue());
				}else{
					collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ,
							required, sortByColumn, order, rowsetint, countSet.intValue());
				}
				
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
			}else{
				//DEFAULT COLLECTION WITHOUT SORTING
				if (minDate != null
						&& !minDate.toString().equalsIgnoreCase("1970-01-01")
						&& maxDate != null
						&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

					String[] betweenColumn = { dateBy };
					Object[] minColumn = { minDate };
					Object[] maxColumn = { maxDate };

					collection = memberService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, betweenColumn, minColumn, maxColumn, required,
							rowsetint, countSet.intValue());
				}else{
					//Edit 31032015, Penambahan Sorting dengan Customer Number agar terurut
					if(navigation.equalsIgnoreCase("listgroup") || navigation.equalsIgnoreCase("golistgroup")){
						collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ, true, "customerNumber",
								required, rowsetint, countSet.intValue());	
					}else{
					collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ,
							required, rowsetint, countSet.intValue());
					}
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
				
				if (minDate != null
						&& !minDate.toString().equalsIgnoreCase("1970-01-01")
						&& maxDate != null
						&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

					String[] betweenColumn = { dateBy };
					Object[] minColumn = { minDate };
					Object[] maxColumn = { maxDate };
					System.out.println("ngambil ini det");
					collection = memberService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, betweenColumn, minColumn, maxColumn, required,
							rowsetint, countSet.intValue());
				}else{
					System.out.println("ngambil ini");
					collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ,
							required, rowsetint, countSet.intValue());
				}
			}
			
			String minimumDate = "";
			String maximumDate = "";

			if (minDate != null
					&& !minDate.toString().equalsIgnoreCase("1970-01-01")) {
				minimumDate = minDate.toString();
			}
			if (maxDate != null
					&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {
				maximumDate = maxDate.toString();
			}
			
			
			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("maximum_date", maximumDate);
			request.setAttribute("dateBy", dateBy);

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("status", searchStatus);
			request.setAttribute("sortcolumn", sortcolumn);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */
			
			Policy policyObject = null;
			
			if(policyId != null){
				try
				{
				java.io.Serializable policypkey = policyId;
				System.out.println("member policy id : "+policyId);
				String[] policyRequired = {"Policy.BrokerId","Policy.ClientId","Policy.ProductType","Policy.CardTypeId"};
				policyObject = policyService.get(policypkey, policyRequired);
				
				}
				catch (Exception ex)
				{
					//System.out.println("member policy object : "+policyObject.getad());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			MemberGroup memberGroup = null;
			
			if (memberGroupId != null){
				try
				{
				java.io.Serializable groupKey = memberGroupId;
				String[] requiredGroup = {"MemberGroup.BusinessCategoryId","MemberGroup.ClientId"};
				memberGroup = memberGroupService.get(groupKey,requiredGroup);
				
				}
				catch (Exception ex)
				{
					//System.out.println("member policy object : "+policyObject.getad());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			result.addObject("policy", policyObject);
			result.addObject("memberGroup", memberGroup);
			result.addObject("Members", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("usenumber", useNumber);
			request.setAttribute("sortCardNumber", sortCardNumber);
			request.setAttribute("sortNameMember", sortNameMember);
			request.setAttribute("sortBirthday", sortBirthday);
			request.setAttribute("sortCustomerNo", sortCustomerNo);
			request.setAttribute("sortClientName", sortClientName);
			request.setAttribute("sortClientId", sortClientId);
			request.setAttribute("sortGroupId", sortGroupId);
			request.setAttribute("sortGroupName", sortGroupName);
			request.setAttribute("sortEffectiveDate", sortEffectiveDate);
			request.setAttribute("sortExpireDate", sortExpireDate);
			request.setAttribute("sortPolicyNo", sortPolicyNo);
			request.setAttribute("sortStatus", sortStatus);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView lookupEmployeePerformed(HttpServletRequest request,
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
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			String useNumber = WebUtil.getParameterString(request, "usenumber","");

			String searchStatus = WebUtil.getParameterString(request,"status","");
			
			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			String dateBy = WebUtil.getParameterString(request, "dateBy", "");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			String url = WebUtil.getParameterString(request, "url", "");
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
                        Integer policyId = WebUtil.getParameterInteger(request, "policyId");
			
			ActionUser user = securityService.getActionUser(request);
			
			Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");
			
			String arah = WebUtil.getParameterString(request, "arah", "");
			
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;
			
			
			boolean sortCardNumber = true, sortNameMember = true, sortBirthday = true,
					sortCustomerNo = true, sortClientName = true, sortClientId = true,
					sortGroupId = true, sortGroupName = true, sortEffectiveDate = true,
					sortExpireDate = true, sortPolicyNo = true, sortStatus = true;
			boolean order = true;

			request.setAttribute("memberGroupId", memberGroupId);
			request.setAttribute("memberId", memberId);
			request.setAttribute("clientId",clientId);
            request.setAttribute("policyId",policyId);
			request.setAttribute("url", url);
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
			if((!navigation.equalsIgnoreCase("gosearchbysort") && arah.isEmpty() && arah.equals("")) ||
					navigation.equals("gosearch")){
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}
			
			
			if (navigation.equals("golookupemployee") || navigation.equals("golookup") || navigation.equals("golistgroup")  || navigation.equalsIgnoreCase("lookupjson") || navigation.equalsIgnoreCase("golistpolicy") ||
					navigation.equals("gosearchbysort") || (navigation.equals("") && !arah.isEmpty())) {
				if (searchby != null) {
					

					if (searchby.equalsIgnoreCase("firstName")) {
						vLikeP.add("firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("lastName")) {
						vLikeP.add("lastName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("mobilePhone")) {
						vLikeP.add("mobilePhone");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("faximile")) {
						vLikeP.add("faximile");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("customerNumber")) {
						vLikeP.add("customerNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("policyNumber")) {
						vLikeP.add("currentPolicyNumber");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("customerPolicyNumber")) {
						vLikeP.add("customerPolicyNumber");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("province")) {
						vLikeP.add("province");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("country")) {
						vLikeP.add("country");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("clientId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")){
						vLikeP.add("memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("cardNumber")){
						vLikeP.add("currentCardNumber");
						vLikeQ.add(searchtext);
					}
					if (searchStatus != null && !searchStatus.equals("")){
						vEqP.add("status");
						vEqQ.add(Integer.valueOf(searchStatus));
					}
				}
			}		
			

			
			//vEqP.add("tipe");
			//vEqQ.add("show");

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

			if (minDate != null
					&& !minDate.toString().equalsIgnoreCase("1970-01-01")
					&& maxDate != null
					&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

				String[] betweenColumn = { dateBy };
				Object[] minColumn = { minDate };
				Object[] maxColumn = { maxDate };
				
				count = memberService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,
						betweenColumn, minColumn, maxColumn);
			}else{
				count = memberService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
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
			

			String required[] = new String[] {"Member.ClientId","Member.MemberGroupId"};
			
			//SORTING CHECKING START
			//Revision 05032015
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equals("cardnumber")){
						sortByColumn = "currentCardNumber";
						String sortbycard = WebUtil.getParameterString(request, "sortbycard", "");
						if(sortOrder!=null){
							Boolean sortByOrderCard = Boolean.valueOf(WebUtil.
									getParameterString(request, "sortByOrderCard", ""));
							sortCardNumber = !sortByOrderCard;
						}else{
							sortCardNumber = !sortOrder;
						}
						order = sortCardNumber;
					}else if(sortcolumn.equals("membername")){
						sortByColumn = "firstName";
						Boolean sortByOrderMemberName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderMemberName", ""));
						sortNameMember = !sortByOrderMemberName;
						order = sortNameMember;
					}else if(sortcolumn.equals("dateofbirth")){
						sortByColumn = "birthday";
						Boolean sortByOrderDOB = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderDOB", ""));
						sortBirthday = !sortByOrderDOB;
						order = sortBirthday;
					}else if(sortcolumn.equals("customernumber")){
						sortByColumn = "customerNumber";
						Boolean sortByOrderCusNo = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCusNo", ""));
						sortCustomerNo = !sortByOrderCusNo;
						order = sortCustomerNo;
					}else if(sortcolumn.equals("clientname")){
						sortByColumn = "clientName";
						Boolean sortByOrderClientName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderClientName", ""));
						sortClientName = !sortByOrderClientName;
						order = sortClientName;
					}else if(sortcolumn.equals("clientcode")){
						sortByColumn = "clientId";
						Boolean sortByOrderCCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCCode", ""));
						sortClientId = !sortByOrderCCode;
						order = sortClientId;
						
					}else if(sortcolumn.equals("group")){
						sortByColumn = "groupName";
						Boolean sortByOrderGroup = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderGroup", ""));
						sortGroupName = !sortByOrderGroup;
						order = sortGroupName;
					}else if(sortcolumn.equals("groupcode")){
						sortByColumn = "memberGroupId";
						Boolean sortByOrderGroupCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderGroupCode", ""));
						sortGroupId = !sortByOrderGroupCode;
						order = sortGroupId;
					}else if(sortcolumn.equals("effectivedate")){
						sortByColumn = "effectiveDate";
						Boolean sortByOrderEffective = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderEffective", ""));
						sortEffectiveDate = !sortByOrderEffective;
						order = sortEffectiveDate;
					}else if(sortcolumn.equals("expiredate")){
						sortByColumn = "expireDate";
						Boolean sortByOrderExpire = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderExpire", ""));
						sortExpireDate = !sortByOrderExpire;
						order = sortExpireDate;
					}else if(sortcolumn.equals("policynumber")){
						sortByColumn = "currentPolicyNumber";
						Boolean sortByOrderExpire = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderPolicy", ""));
						sortPolicyNo = !sortByOrderExpire;
						order = sortPolicyNo;
					}else{
						sortByColumn = "status";
						Boolean sortByOrderStatus = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderStatus", ""));
						sortStatus = !sortByOrderStatus;
						order = sortStatus;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equals("cardnumber")){
						sortCardNumber = order;
					}else if(sortcolumn.equals("membername")){
						sortNameMember = order;
					}else if(sortcolumn.equals("dateofbirth")){
						sortBirthday = order;
					}else if(sortcolumn.equals("customernumber")){
						sortCustomerNo = order;
					}else if(sortcolumn.equals("clientname")){
						sortClientName = order;
					}else if(sortcolumn.equals("clientcode")){
						sortClientId = order;
					}else if(sortcolumn.equals("group")){
						sortGroupName = order;
					}else if(sortcolumn.equals("groupcode")){
						order = sortGroupId;
					}else if(sortcolumn.equals("effectivedate")){
						sortEffectiveDate = order;
					}else if(sortcolumn.equals("expiredate")){
						sortExpireDate = order;
					}else if(sortcolumn.equals("policynumber")){
						sortPolicyNo = order;
					}else{
						sortStatus = order;
					}
				}
				if(minDate != null
						&& !minDate.toString().equalsIgnoreCase("1970-01-01")
						&& maxDate != null
						&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

					String[] betweenColumn = { dateBy };
					Object[] minColumn = { minDate };
					Object[] maxColumn = { maxDate };
					collection = memberService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, betweenColumn, minColumn, maxColumn, order, sortByColumn, required,
							rowsetint, countSet.intValue());
				}else{
					System.out.println("ngambil ini kitu");
					collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ,
							required, sortByColumn, order, rowsetint, countSet.intValue());
				}
				
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
			}else{
				//DEFAULT COLLECTION WITHOUT SORTING
				if (minDate != null
						&& !minDate.toString().equalsIgnoreCase("1970-01-01")
						&& maxDate != null
						&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

					String[] betweenColumn = { dateBy };
					Object[] minColumn = { minDate };
					Object[] maxColumn = { maxDate };

					System.out.println("ngambil ini mreun");
					collection = memberService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, betweenColumn, minColumn, maxColumn, required,
							rowsetint, countSet.intValue());
				}else{

					System.out.println(" mungkin ngambil ini");
					
					if(navigation.equalsIgnoreCase("listpolicy") || navigation.equalsIgnoreCase("golistpolicy")){
						collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ, true, "customerNumber",
								required, rowsetint, countSet.intValue());	
					}else{
					collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ,
							required, rowsetint, countSet.intValue());
					}
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
				
				if (minDate != null
						&& !minDate.toString().equalsIgnoreCase("1970-01-01")
						&& maxDate != null
						&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

					String[] betweenColumn = { dateBy };
					Object[] minColumn = { minDate };
					Object[] maxColumn = { maxDate };
					System.out.println("ngambil ini det");
					collection = memberService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, betweenColumn, minColumn, maxColumn, required,
							rowsetint, countSet.intValue());
				}else{
					System.out.println("ngambil ini");
					collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ,
							required, rowsetint, countSet.intValue());
				}
			}
			
			String minimumDate = "";
			String maximumDate = "";

			if (minDate != null
					&& !minDate.toString().equalsIgnoreCase("1970-01-01")) {
				minimumDate = minDate.toString();
			}
			if (maxDate != null
					&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {
				maximumDate = maxDate.toString();
			}
			
			
			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("maximum_date", maximumDate);
			request.setAttribute("dateBy", dateBy);

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("status", searchStatus);
			request.setAttribute("sortcolumn", sortcolumn);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */
			
			Policy policyObject = null;
			
			if(policyId != null){
				try
				{
				java.io.Serializable policypkey = policyId;
				System.out.println("member policy id : "+policyId);
				String[] policyRequired = {"Policy.BrokerId","Policy.ClientId","Policy.ProductType","Policy.CardTypeId"};
				policyObject = policyService.get(policypkey, policyRequired);
				
				}
				catch (Exception ex)
				{
					//System.out.println("member policy object : "+policyObject.getad());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			MemberGroup memberGroup = null;
			
			if (memberGroupId != null){
				try
				{
				java.io.Serializable groupKey = memberGroupId;
				String[] requiredGroup = {"MemberGroup.BusinessCategoryId","MemberGroup.ClientId"};
				memberGroup = memberGroupService.get(groupKey,requiredGroup);
				
				}
				catch (Exception ex)
				{
					//System.out.println("member policy object : "+policyObject.getad());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			result.addObject("policy", policyObject);
			result.addObject("memberGroup", memberGroup);
			result.addObject("Members", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("usenumber", useNumber);
			request.setAttribute("sortCardNumber", sortCardNumber);
			request.setAttribute("sortNameMember", sortNameMember);
			request.setAttribute("sortBirthday", sortBirthday);
			request.setAttribute("sortCustomerNo", sortCustomerNo);
			request.setAttribute("sortClientName", sortClientName);
			request.setAttribute("sortClientId", sortClientId);
			request.setAttribute("sortGroupId", sortGroupId);
			request.setAttribute("sortGroupName", sortGroupName);
			request.setAttribute("sortEffectiveDate", sortEffectiveDate);
			request.setAttribute("sortExpireDate", sortExpireDate);
			request.setAttribute("sortPolicyNo", sortPolicyNo);
			request.setAttribute("sortStatus", sortStatus);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView searchDependentPerformed(HttpServletRequest request,
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
			
			String useNumber = WebUtil.getParameterString(request, "usenumber","");

			Integer searchStatus = WebUtil.getParameterInteger(request,"status");

			
			
			String url = WebUtil.getParameterString(request, "url", "");
			
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			if(isIFrameSession){
				//check if member session
				if(iFrameLevelLogin.equalsIgnoreCase("member")){
					memberId=(memberId==null?Integer.parseInt(sessionMemberId):memberId);
					Member theMember = memberService.get(memberId);
					if(theMember!=null){
						System.out.println("checking->requestParentMemberId(" + theMember.getParentMember().getMemberId().toString() + ") vs sessionParentMemberId(" + sessionMemberParentId + ")");
						if(!theMember.getParentMember().getMemberId().toString().equals(sessionMemberParentId)){
							memberId = Integer.parseInt(sessionMemberId);
							
							ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
							errorResult.addObject("errorType","OtherFamilyAccessDenied");			
							errorResult.addObject("errorMessage", "Hey member("+ sessionMemberId + "), are you missing your way? :P<br/>" 
									+ "<a href=\"member?navigation=listdependant&memberId="+memberId+"\">Go Back</a>");
							return errorResult;
						}
					}
				}
				
			}
			
			ActionUser user = securityService.getActionUser(request);
			
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			if (user != null && user.getUser().getUserType().intValue() == User.MEMBER){
				memberId = user.getUser().getMemberId().getMemberId();
			}
			
			request.setAttribute("url", url);
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
			
			if (navigation.equals("golistdependant") ) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("firstName")) {
						vLikeP.add("firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("lastName")) {
						vLikeP.add("lastName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("mobilePhone")) {
						vLikeP.add("mobilePhone");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("faximile")) {
						vLikeP.add("faximile");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("customerNumber")) {
						vLikeP.add("customerNumber");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("customerPolicyNumber")) {
						vLikeP.add("customerPolicyNumber");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("province")) {
						vLikeP.add("province");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("country")) {
						vLikeP.add("country");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("clientId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")){
						vLikeP.add("memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("cardNumber")){
						vLikeP.add("currentCardNumber");
						vLikeQ.add(searchtext);
					}
					if (searchStatus != null && searchStatus.intValue() >= -1){
						vEqP.add("status");
						vEqQ.add(searchStatus);
					}					
				}
			}			
			
			if (memberId != null){
				Member member= memberService.get(memberId);
				
				Integer parentId = memberId;
				if (member != null && member.getParentMember() != null){
					parentId = member.getParentMember().getMemberId();
				}
				vEqP.add("parentMember.memberId");
				vEqQ.add(parentId);
				
			}
            
			//vEqP.add("tipe");
			//vEqQ.add("show");

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

			count = memberService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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

			String required[] = new String[] {"Member.MemberGroupId","Member.ClientId"};
			collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
			}
			

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

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
			

			result.addObject("Members", collection);

			request.setAttribute("memberId", memberId);
			
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("usenumber", useNumber);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchCriticalBenefitPerformed(HttpServletRequest request,
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
			
			String useNumber = WebUtil.getParameterString(request, "usenumber","");

			Integer searchStatus = WebUtil.getParameterInteger(request,"status");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			String url = WebUtil.getParameterString(request, "url", "");
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			
			
			Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");
			
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			request.setAttribute("memberGroupId", memberGroupId);
			request.setAttribute("memberId", memberId);
			request.setAttribute("clientId",clientId);
			request.setAttribute("url", url);
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

					if (searchby.equalsIgnoreCase("firstName")) {
						vLikeP.add("firstName");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("customerNumber")) {
						vLikeP.add("customerNumber");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("customerPolicyNumber")) {
						vLikeP.add("customerPolicyNumber");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("province")) {
						vLikeP.add("province");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("country")) {
						vLikeP.add("country");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("clientId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")){
						vLikeP.add("memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchStatus != null && searchStatus.intValue() >= -1){
						vEqP.add("status.statusId");
						vEqQ.add(searchStatus);
					}
					
				}

			}
			
			if (url != null){
				if (url.equalsIgnoreCase("batchclaim-form") || url.equalsIgnoreCase("claim-form")){
					//vEqP.add("status.statusId");
					//vEqQ.add(SubscriptionStatus.ACTIVE);
				}
			}
			
			if (clientId != null){
				vEqP.add("clientId.clientId");
				vEqQ.add(clientId);
			}

			if (memberGroupId != null){
				vEqP.add("memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);
			}
			
			if (memberId != null){
				vEqP.add("parentMember.memberId");
				vEqQ.add(memberId);
				
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

			count = memberService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"Member.MemberType", "Member.ClientId",
					"Member.MemberGroupId", "Member.RelationshipId",
			// foreign affairs end
			};
			collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Members", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("usenumber", useNumber);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchMemberBenefitPerformed(HttpServletRequest request,
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
			
			String useNumber = WebUtil.getParameterString(request, "usenumber","");

			Integer searchStatus = WebUtil.getParameterInteger(request,"status");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			String url = WebUtil.getParameterString(request, "url", "");
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			
			
			Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");
			
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			request.setAttribute("memberGroupId", memberGroupId);
			request.setAttribute("memberId", memberId);
			request.setAttribute("clientId",clientId);
			request.setAttribute("url", url);
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
			
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equalsIgnoreCase("gosearchbenefit")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("firstName")) {
						vLikeP.add("firstName");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("customerNumber")) {
						vLikeP.add("customerNumber");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("customerPolicyNumber")) {
						vLikeP.add("customerPolicyNumber");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("province")) {
						vLikeP.add("province");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("country")) {
						vLikeP.add("country");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("clientId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")){
						vLikeP.add("memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchStatus != null && searchStatus.intValue() >= -1){
						vEqP.add("status.statusId");
						vEqQ.add(searchStatus);
					}
					
				}

			}
			
			
			
			if (clientId != null){
				vEqP.add("clientId.clientId");
				vEqQ.add(clientId);
			}

			if (memberGroupId != null){
				vEqP.add("memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);
			}
			
			if (memberId != null){
				vEqP.add("parentMember.memberId");
				vEqQ.add(memberId);
				
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

			count = memberService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"Member.MemberType", "Member.ClientId",
					"Member.MemberGroupId", "Member.RelationshipId",
			// foreign affairs end
			};
			collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = memberService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Members", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("usenumber", useNumber);

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
			String memberGroupId = WebUtil.getParameterString(request, "memberGroupId", "");
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			String q = WebUtil.getParameterString(request, "q", "");
			
			Collection<Member> members = null;
			
//			if (memberGroupId != null && !memberGroupId.equalsIgnoreCase("")){
//				members = memberService.searchMemberAndGroup(q,Integer.parseInt(memberGroupId));
//			}else if (clientId != null && memberGroupId.equalsIgnoreCase("")){
//				members = memberService.searchMemberAndClient(q, clientId);
				
			//373
			Integer policyId = WebUtil.getParameterInteger(request, "policyId");
			String claimNumber = WebUtil.getParameterString(request, "claimNumber", "");
			
			if(clientId!= null && memberGroupId != null && !memberGroupId.equalsIgnoreCase("") && policyId != null){
				members = memberService.searchMemberClientPolicy(q, clientId, Integer.valueOf(memberGroupId), policyId);
			}else {
				members = memberService.searchMember(q);
			}		
			
			result.addObject("Members", members);


		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	
	//Add by aju on 20150702, for edc simulator
	public ModelAndView lookupJsonEdcService(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);
			
			//get the providerId and cardNumber to getting the available service
			String providerId = WebUtil.getParameterString(request, "providerId", "");
			System.out.println("jsonEdcService -> ProviderId : " + providerId);
			Provider provider = providerService.get(Integer.parseInt(providerId));
			System.out.println("provider -> edc code : " + provider.getEdcCode());
			System.out.println("provider -> edc code : " + provider.getProviderCode());
			
			String cardNumber = WebUtil.getParameterString(request, "cardNumber", "");
			System.out.println("jsonEdcService -> CardNumber : " + cardNumber);
			Member member = memberService.getMemberByCardNumber(cardNumber);

			ActionResult actRes = new ActionResult();
			
			if(provider!=null && member != null){
				
				boolean isManagedCare = false;
				
				//get the policy
				Policy policy = policyService.getGroupActivePolicy(member.getMemberGroupId().getMemberGroupId(), member.getClientId().getClientId());
				if (policy != null){
					if (policy.getPolicyType().intValue() == 2){
						isManagedCare = true;
					}
				}
				
				//if is INDEMNITY
				if(!isManagedCare){
					System.out.println("INDEMNITY");
					actRes.setReason("INDEMNITY");
					
					String responseLine = "";
					int index = 0;
					
					Collection<MemberBenefit> benefitList = memberBenefitService.getMemberBenefitList(member.getMemberId());
					
					Vector<MemberBenefit> responseList = new Vector<MemberBenefit>(); 
					
					if (benefitList != null && benefitList.size() > 0){
						for (Iterator iterator = benefitList.iterator(); iterator.hasNext();) {
							
							MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
							Product product = memberBenefit.getProductId();
							
							if (memberBenefit != null && product != null && product.getEdcEnabled() != null && product.getEdcEnabled().intValue() == 1){
								boolean isExist = isMemberBenefitCategoryExist(responseList, memberBenefit);
								
								if (!isExist){
									responseList.add(memberBenefit);
									
									
									if (index == 0) {
										responseLine += memberBenefit.getCaseCategoryId()
												.getCaseCategoryName()
												+ "#"
												+ memberBenefit.getCaseCategoryId().getCaseCategoryEdcCode(); // getCaseCategoryId();
		
									} else {
										responseLine += "|"
												+ memberBenefit.getCaseCategoryId()
														.getCaseCategoryName()
												+ "#"
												+ memberBenefit.getCaseCategoryId().getCaseCategoryEdcCode(); // getCaseCategoryId();
									}
									
									index++;
								}
							}
							
						}
					}
					
					System.out.println(responseLine);
					actRes.setAdditionalMessage(responseLine);
					actRes.setResult(true);
					
				}
				//if is MANAGED CARE
				else{
					actRes.setReason("MANAGEDCARE");
					if (provider != null){
						if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.PPK1_UMUM){
							System.out.println("MANAGED CARE -> PPK1 UMUM");
							
							String responseLine = "POLI UMUM"+ "#"+ "1010";
							//String responseLine = "POLI UMUM"+ "#"+ "14"; //by CaseCategoryId
							
							System.out.println(responseLine);
							actRes.setAdditionalMessage(responseLine);
							actRes.setResult(true);
											
						}
						else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.PPK1_GIGI){
							System.out.println("MANAGED CARE -> PPK1 GIGI");
							
							String responseLine = "POLI GIGI"+ "#"+ "4010";
							//String responseLine = "POLI GIGI"+ "#"+ "15"; //by CaseCategoryId
							
							System.out.println(responseLine);
							actRes.setAdditionalMessage(responseLine);
							actRes.setResult(true);
						}
						else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.PPK2){
							System.out.println("MANAGED CARE -> PPK2");
							
							String[] eqParam = {"deletedStatus","productId.caseCategory.caseCategoryId","memberId.memberId"};
							Object[] eqValue = {0,CaseCategory.SPECIALIST,member.getMemberId()};
							
							Collection<MemberProduct> memberProductList = memberProductService.search(null,null,eqParam,eqValue,0,1);
							if (memberProductList != null){
								
								Iterator<MemberProduct> iterator = memberProductList.iterator();
								
								if (iterator.hasNext()){
									MemberProduct product = iterator.next();
									
									if (product != null){
										System.out.println("memberProductId:"+ product.getMemberProductId());
										
										ProductType type = product.getProductId().getProductType();
										
										String[] eqProdType = {"deletedStatus","productTypeId.productTypeId","status"};
										Object[] eqProdTypeVal = {0,type.getProductTypeId(),1};
										
										int totalProd = productTypePoliklinikService.getTotal(null,null,eqProdType,eqProdTypeVal);
										Collection<ProductTypePoliklinik> poliList = productTypePoliklinikService.search(null,null,
												eqProdType,eqProdTypeVal,0,totalProd);
										
										if (poliList != null && poliList.size() > 0){
											Iterator<ProductTypePoliklinik> iteratorPoli = poliList.iterator();
											String responseLine = "";
											
											int index = 0;
											while (iteratorPoli.hasNext()){
												ProductTypePoliklinik poli = iteratorPoli.next();
												
												if (poli != null){
													if (index == 0) {
														responseLine += poli.getPoliklinikId().getPoliklinikCode()
																+ "#"
																+ poli.getPoliklinikId().getPoliklinikEdcCode();
						
													} else {
														responseLine += "|"
																+ poli.getPoliklinikId().getPoliklinikCode()
																+ "#"
																+ poli.getPoliklinikId().getPoliklinikEdcCode();
													}
													index++;
												}
											}
											
											System.out.println(responseLine);
											actRes.setAdditionalMessage(responseLine);
											actRes.setResult(true);
										}
										else if (poliList == null || poliList.size() == 0){
											String[] eqParamPoli = {"deletedStatus","providerId.providerId"};
											Object[] eqValuePoli = {Integer.valueOf(0),provider.getProviderId()};
											
											String responseLine = "";
											
											int totalPoli = providerPoliklinikService.getTotal(null,null,eqParamPoli,eqValuePoli);
											Collection<ProviderPoliklinik> providerPoliList = providerPoliklinikService.search(null,null,eqParamPoli,eqValuePoli,0,totalPoli);
											
											if (providerPoliList != null){
											
												Iterator<ProviderPoliklinik> iteratorPoli = providerPoliList.iterator();
												
												int index = 0;
												
												while (iteratorPoli.hasNext()){
													ProviderPoliklinik poli = iteratorPoli.next();
													
													if (poli != null){
														if (index == 0) {
															responseLine += poli.getPoliklinikId().getPoliklinikCode()
																	+ "#"
																	+ poli.getPoliklinikId().getPoliklinikEdcCode();
							
														} else {
															responseLine += "|"
																	+ poli.getPoliklinikId().getPoliklinikCode()
																	+ "#"
																	+ poli.getPoliklinikId().getPoliklinikEdcCode();
														}
														index++;
													}
												}
												
												System.out.println(responseLine);
												actRes.setAdditionalMessage(responseLine);
												actRes.setResult(true);
											}
											
											
										}
										
										
									}
								}
							}
						}
						else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.RUMAH_SAKIT){
							// PPK3 dan atau PPK2 (Poliklinik)
							System.out.println("MANAGED CARE -> PPK3 / PPK2 (poli RS)");
							
							String[] eqParam = {"deletedStatus","productId.caseCategory.caseCategoryId","memberId.memberId"};
							Object[] eqValue = {0,CaseCategory.SPECIALIST,member.getMemberId()};
							
							Collection<MemberProduct> memberProductList = memberProductService.search(null,null,eqParam,eqValue,0,1);
							if (memberProductList != null){
								System.out.println("memberProduct exist");
								
								Iterator<MemberProduct> iterator = memberProductList.iterator();
								
								if (iterator.hasNext()){
									MemberProduct product = iterator.next();
									
									if (product != null){
										System.out.println("memberProductId : " + product.getMemberProductId());
										
										ProductType type = product.getProductId().getProductType();
										System.out.println("ProductTypeId : " + type.getProductTypeId());
										
										String[] eqProdType = {"deletedStatus","productTypeId.productTypeId","status"};
										Object[] eqProdTypeVal = {0,type.getProductTypeId(),1};
										System.out.println("eqParam : " + Arrays.toString(eqProdType) + "\neqValue : " + Arrays.toString(eqProdTypeVal));
										
										int totalProd = productTypePoliklinikService.getTotal(null,null,eqProdType,eqProdTypeVal);
										System.out.println("total ProductType : " + totalProd);
										
										Collection<ProductTypePoliklinik> poliList = productTypePoliklinikService.search(null,null,
												eqProdType,eqProdTypeVal,0,totalProd);
										
										if (poliList != null && poliList.size() > 0){
											Iterator<ProductTypePoliklinik> iteratorPoli = poliList.iterator();
											String responseLine = "";
											
											int index = 0;
											while (iteratorPoli.hasNext()){
												ProductTypePoliklinik poli = iteratorPoli.next();
												
												if (poli != null){
													if (index == 0) {
														responseLine += poli.getPoliklinikId().getPoliklinikCode()
																+ "#"
																+ poli.getPoliklinikId().getPoliklinikEdcCode();
						
													} else {
														responseLine += "|"
																+ poli.getPoliklinikId().getPoliklinikCode()
																+ "#"
																+ poli.getPoliklinikId().getPoliklinikEdcCode();
													}
													index++;
												}
											}
											
											System.out.println(responseLine);
											actRes.setAdditionalMessage(responseLine);
											actRes.setResult(true);
										}
										else if (poliList == null || poliList.size() == 0){
											String[] eqParamPoli = {"deletedStatus","providerId.providerId"};
											Object[] eqValuePoli = {Integer.valueOf(0),provider.getProviderId()};
											
											String responseLine = "";
											
											int totalPoli = providerPoliklinikService.getTotal(null,null,eqParamPoli,eqValuePoli);
											Collection<ProviderPoliklinik> providerPoliList = providerPoliklinikService.search(null,null,eqParamPoli,eqValuePoli,0,totalPoli);
											
											if (providerPoliList != null){
											
												Iterator<ProviderPoliklinik> iteratorPoli = providerPoliList.iterator();
												
												int index = 0;
												
												while (iteratorPoli.hasNext()){
													ProviderPoliklinik poli = iteratorPoli.next();
													
													if (poli != null){
														if (index == 0) {
															responseLine += poli.getPoliklinikId().getPoliklinikCode()
																	+ "#"
																	+ poli.getPoliklinikId().getPoliklinikEdcCode();
							
														} else {
															responseLine += "|"
																	+ poli.getPoliklinikId().getPoliklinikCode()
																	+ "#"
																	+ poli.getPoliklinikId().getPoliklinikEdcCode();
														}
														index++;
													}
												}
												
												System.out.println(responseLine);
												actRes.setAdditionalMessage(responseLine);
												actRes.setResult(true);
											}
										}
									}
								}
							}
							
						}
						else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.APOTEK){
							
						}
						else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.LAB){
							
						}
					}
					
				}
				
				
			}
			
			result.addObject("EDCServices", actRes);


		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	//Add by aju on 20150702, for edc simulator
	private boolean isMemberBenefitCategoryExist (Vector<MemberBenefit> benefitList, MemberBenefit mb){
		boolean result = false;
		
		for (Iterator iterator = benefitList.iterator(); iterator.hasNext();) {
			MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
			
			if (memberBenefit != null && mb != null){
				if (memberBenefit.getCaseCategoryId().getCaseCategoryId().intValue() == mb.getCaseCategoryId().getCaseCategoryId().intValue()){
					return true;
				}
			}
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
		System.out.println("Masuk Mmeber Controller");
		String breadcrumb = "";
		try {
			//modified by aju on 20150929, new iFrame checker fufufu plus init
			isIFrameSession = securityService.isUsingIFrameSession(request);
			iFrameLevelLogin = securityService.getTheIFrameLevelLogin();
			System.out.println("securityService->SessionMemberId->" + securityService.getTheSessionMemberId());
			sessionMemberId = securityService.getTheSessionMemberId();
			System.out.println("securityService->SessionParentMemberId->" + securityService.getTheSessionMemberParentId());
			sessionMemberParentId = securityService.getTheSessionMemberParentId();
			
			//Add by aju on 20151001, check the user login priviledges
			boolean isAllowed = securityService.isRequestAllowed(request);
			if(!isAllowed){
				User theUser = securityService.getCurrentUser(request);
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
				errorResult.addObject("errorType","OtherFamilyAccessDenied");			
				errorResult.addObject("errorMessage", "Hey "+ theUser.getUsername() + "(" + theUser.getUserId() + "), are you missing your way? :P<br/>" 
						+ "<a href=\"member\">Go Back</a>");
				return errorResult;
			}
			
			
			if (navigation.equalsIgnoreCase("detail")) {
				result = detailPerformed(request, response, "detailMember");
				//modified by aju on 20150928, no url param changes for member access fufufu :D
				String memberId = request.getParameter("memberId");
				//String memberId = (securityService.getTheSessionMemberId()==null ? request.getParameter("memberId") : securityService.getTheSessionMemberId());
				if(isIFrameSession && iFrameLevelLogin.equalsIgnoreCase("member")){
					memberId = (memberId==null?securityService.getTheSessionMemberId():memberId);
					Member theMember = memberService.get(Integer.parseInt(memberId));
					if(theMember!=null){
						if(!theMember.getParentMember().getMemberId().toString().equals(sessionMemberParentId)){
							memberId = securityService.getTheSessionMemberId();
						}
					}
				}
				
				
				breadcrumb = "<a href=\"member?navigation=detail&memberId="+memberId+"\" class=\"linkbreadcrumb\">Detail Member</a>";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchMember");
				breadcrumb = "<a href=\"member\" class=\"linkbreadcrumb\">Search Member</a>";
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupMember");
			}
			 else if (navigation.equalsIgnoreCase("lookupemployee")
						|| navigation.equals("golookupemployee")) {
					result = searchPerformed(request, response, "lookupMember");
				}
			else if (navigation.equalsIgnoreCase("auth") || navigation.equalsIgnoreCase("doauth")){
				result = authorizePerformed(request,response,"authorizeMember");
			}	
			else if (navigation.equalsIgnoreCase("export") ){
				result = exportPerformed(request,response,"searchMember");
			}	
			else if (navigation.equalsIgnoreCase("activate")){
				result = activatePerformed(request, response, "detailMember");
			}
			else if (navigation.equalsIgnoreCase("listdependant") || navigation.equalsIgnoreCase("golistdependant")){
				String memberId = request.getParameter("memberId");		
				
				//modified by aju on 20150410, add level login filtering :D
				if(isIFrameSession && securityService.getTheIFrameLevelLogin().equalsIgnoreCase("member")){
					memberId = (memberId==null?securityService.getTheSessionMemberId():memberId);
					Member theMember = memberService.get(Integer.parseInt(memberId));
					if(theMember!=null){
						if(!theMember.getParentMember().getMemberId().toString().equals(sessionMemberParentId)){
							memberId = securityService.getTheSessionMemberId();
						}
					}
				}
				
				result = searchDependentPerformed(request, response, "listMemberDependent");
				breadcrumb = "<a href=\"member?navigation=detail&memberId="+memberId+"\" class=\"linkbreadcrumb\">Detail Member</a>&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Member Dependant";
			}
            else if (navigation.equalsIgnoreCase("listpolicy") || navigation.equalsIgnoreCase("golistpolicy")){
				result = searchPerformed(request, response, "listMemberPolicy");
				System.out.println("Masuk list policy");
				String policyId = request.getParameter("policyId");
				breadcrumb = "<a href=\"policy?navigation=detail&policyId="+policyId+"\" class=\"linkbreadcrumb\">Detail Policy</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Policy Member";
			}
            else if (navigation.equalsIgnoreCase("listgroup") || navigation.equalsIgnoreCase("golistgroup")){
				result = searchPerformed(request, response, "listGroupMember");
				String memberGroupId = request.getParameter("memberGroupId");
				breadcrumb = "<a href=\"membergroup?navigation=listgroup&memberGroupId="+memberGroupId+"\" class=\"linkbreadcrumb\">Detail Member Group</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Group Member";
			}
			else if (navigation.equalsIgnoreCase("terminate")){
				result = terminatePerformed(request, response, "detailMember");
			}
			else if (navigation.equalsIgnoreCase("preterminate")){
				result = detailPerformed(request, response, "terminateMember");
			}
			else if (navigation.equalsIgnoreCase("preupgrade") || navigation.equalsIgnoreCase("saveupgrade")){
				result = upgradePerformed(request, response, "upgradeMember");
			}
			else if (navigation.equalsIgnoreCase("block")){
				result = blockPerformed(request, response, "detailMember");
			}
			else if (navigation.equalsIgnoreCase("preblock")){
				result = detailPerformed(request, response, "blockMember");
			}
			else if (navigation.equalsIgnoreCase("unblock")){
				result = unblockPerformed(request, response, "detailMember");
			}
			else if (navigation.equalsIgnoreCase("preunblock")){
				result = detailPerformed(request, response, "unblockMember");
			}
			else if (navigation.equalsIgnoreCase("account") || navigation.equalsIgnoreCase("gosearchaccount")){
				result = searchPerformed(request, response, "searchMemberBankAccount");
			} 
			else if (navigation.equalsIgnoreCase("criticalbenefit")){
				result = searchCriticalBenefitPerformed(request, response, "searchCriticalBenefitMember");
			}
			else if (navigation.equalsIgnoreCase("downloadbenefit") || navigation.equalsIgnoreCase("gosearchbenefit")){
				result = searchMemberBenefitPerformed(request, response, "searchCriticalBenefitMember");
			}
			else if (navigation.equalsIgnoreCase("downloadaccount")){
				 result = memberBankAccountReport(request, response, "searchMemberBankAccount");
			}
			else if (navigation.equalsIgnoreCase("lookupjson")){
				 result = lookupJsonPerformed(request, response, "lookupMemberJson");
			}
			//Add by aju on 20150707, for edc emulator
			else if (navigation.equalsIgnoreCase("lookupjsonedcservice")){
				 result = lookupJsonEdcService(request, response, "lookupJsonMemberEDCServcice");
			}
			else if (navigation.equalsIgnoreCase("activateall")){
				 result = activateAllMemberPerformed(request, response, "lookupMemberJson");
			}
			else if (navigation.equalsIgnoreCase("testcheckin")){
				result = testAuthorizeMember(request, response);
			}
			else if (navigation.equalsIgnoreCase("registerrki") || navigation.equalsIgnoreCase("saveregisterrki")){
				result = registerRKIPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("printrki") ){
				result = printRKIPerformed(request, response,"printMemberRKI");
			}
			else if (navigation.equalsIgnoreCase("freeactivate") ){
				result = freeActivatePerformed(request, response,"activateResult");
			}
			else {
				result = searchPerformed(request, response, "searchMember");
				breadcrumb = "<a href=\"member\" class=\"linkbreadcrumb\">Search Member</a>";
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
