package com.ametis.cms.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
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
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseItem;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseItemService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * CaseItem is a servlet controller for case_item Table. All you have to do is
 * to convert necessary data field to the named parameter
 */
public class CaseItemController implements Controller

// extends+

// extends-

{

	private CaseItemService caseItemService;
	private SecurityService securityService;
	private CaseService caseService;
	
	private ClaimService claimService;
	
	private ItemService itemService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;

	private ActivityLogService logService;
	private MemberBenefitService memberBenefitService;

	private ClaimItemService claimItemService;
	
	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}

	public void setMemberBenefitService(MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
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

	public void setCaseItemService(CaseItemService caseItemService) {
		this.caseItemService = caseItemService;
	}

	public CaseItemService getCaseItemService() {
		return this.caseItemService;
	}

	
	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}

	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseItemId = WebUtil.getParameterInteger(request,"caseItemId");
			
			java.io.Serializable pkey = caseItemId;
			
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			Case theCase = caseService.get(caseId);

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETECASEITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETECASEITEM access");
				return errorResult;
				
			}
			CaseItem res = caseItemService.delete(pkey, user);

			if (res != null) {
				//Add 20151304 by FVO, penambahan untuk ikut otomatis update pada claim item saat update case item
				String[] requiredCase = {"Case.MemberId","Case.ClaimId","Case.CaseStatusId", "Case.CaseCategoryId", "Case.ClaimId.ClaimStatus"};
				
				java.io.Serializable pkeyCase = Integer.valueOf(caseId);
				Case object = caseService.get(pkeyCase,requiredCase);
				if(object.getClaimId()!=null){
					String[] eqParam = {"claimId.claimId","itemId.itemId", "claimId.caseId.caseId","deletedStatus", "claimId.caseCategoryId.caseCategoryId"};
    				Object[] eqValue = {object.getClaimId().getClaimId(), res.getItemId().getItemId(), object.getCaseId(), Integer.valueOf(0), 
    						object.getCaseCategoryId().getCaseCategoryId()};
    				ClaimItem claimItemDelete = claimItemService.searchUnique(eqParam, eqValue);
    				
    				if(claimItemDelete!=null){
    					//Edit 20150806 by FVO, ganti Operator != jadi ==
    					if(object.getClaimId().getClaimStatus().getCaseStatusId() == Claim.CLAIM_PRE_OPEN){
    						claimItemService.delete(claimItemDelete, user);
    						//Add 20150806 by FVO, untuk save Excess, Approved di Case
    		                Collection<ClaimItem> claimItemList;
    		                String[] required = {"Claim.MemberId", "Claim.DiagnosisId"};
    		                double totalExcess = 0;
    		    			double totalApproved = 0;
    		    			double claimCharge = 0;
    						Claim claim = claimService.get(object.getClaimId().getClaimId(),required);
    						claimItemList = claimItemService.getBenefitCheckCalculationForLetter(claim);
    						for(ClaimItem ci : claimItemList){
    							if(ci.getExcessValue()!=null){
    								totalExcess = totalExcess + ci.getExcessValue();
    							}
    							if(ci.getClaimItemCoveredValue() != null){
    								//totalApproved = totalApproved + ci.getClaimItemApprovedValue();
    								totalApproved = totalApproved + ci.getClaimItemCoveredValue();
    							}
    							if(ci.getClaimItemValue() != null){
    								claimCharge = claimCharge + ci.getClaimItemValue();
    							}
    						}
    						object.setCaseClaimValue(claimCharge);
    			            object.setCaseApprovedValue(totalApproved);
    			            object.setCaseExcessValue(totalExcess);
    						caseService.update(object, user);
    					}
    				}
				}
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.caseitem", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.caseitem", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchCaseItem");
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
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DETAILCASEITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DETAILCASEITEM access");
				return errorResult;
				
			}
			
			Integer caseItemId = WebUtil.getParameterInteger(request,
					"caseItemId");

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
			java.io.Serializable pkey = caseItemId;
			CaseItem object = caseItemService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties
						.getMessage("not.found.caseitem", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("caseItem", object);
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
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCASEITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCASEITEM access");
				return errorResult;
				
			}
			
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");
			
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
	
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Case theCase = caseService.get(caseId);
			
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
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */ 	
					if(searchby.equalsIgnoreCase("itemName")){
						vLikeP.add("itemId.itemName");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("itemCode")){
						vLikeP.add("itemId.itemCode");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("description")){
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					
				}

			}

				
				vEqP.add("caseId.caseId");
				vEqQ.add(caseId);
			
			result.addObject("caseId", caseId);
			result.addObject("theCase", theCase);
			
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

			count = caseItemService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
			"CaseItem.CaseEventId",
			// foreign affairs end
			};
			collection = caseItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = caseItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
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

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("CaseItems", collection);
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

	public ModelAndView addCaseItemPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			System.out.println("MASUK ADD BULK CASE ITEM");
			ActionUser user= securityService.getActionUser(request); 
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "ADDCASEITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for ADDCLAIMITEM access");
				return errorResult;
				
			}
			
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			Integer memberId = WebUtil.getParameterInteger(request,"memberId");

			result = new ModelAndView("addBulkCaseItem");

			Case theCase = caseService.get(caseId);

			int totalItemLeft = 0;

			if (theCase != null) {
				
				String[] param = {"caseId.caseId","deletedStatus"};
				Object[] value = {caseId, Integer.valueOf(0)};
				
				int total = caseItemService.getTotal(null, null, param, value);
				Collection claimItem = caseItemService.search(null, null,param, value, 0,total);
				
				
					
				Collection<Item> mostFrequentItems = itemService.getClaimableItem(theCase.getMemberId().getMemberId(),
						theCase.getCaseCategoryId().getCaseCategoryId());
				
				
				
				if (mostFrequentItems != null){
					
					Iterator<Item> iterator = mostFrequentItems.iterator();
					
					Collection colItemName = new Vector();
					Collection colItemCode = new Vector();
					Collection colItemId = new Vector(); 
					Collection blankCol = new Vector();
					Collection valueCol = new Vector();
					Collection claimItemCol = new Vector();
					Collection amounCollection = new Vector();
					Collection ruleCollection = new Vector();
					
					int totalItem = 0;
					while (iterator.hasNext()){
						
						Item item = iterator.next();
						
						
						if (item != null){
							System.out.println("ITEM CLAIMABLE : "+item.getItemId());
							colItemName.add(item.getItemName());
							colItemCode.add(item.getItemCode());
							colItemId.add(item.getItemId());
							
							item = itemService.get(item.getItemId());
							
							Collection<MemberBenefit> memberBenefitList = 
								memberBenefitService.getMemberBenefitByDate(memberId, item.getItemCategoryId().getItemCategoryId(), 
										theCase.getCaseCategoryId().getCaseCategoryId(),theCase.getMemberId().getEffectiveDate(),theCase.getMemberId().getExpireDate(),0);
							
							// TODO: Case Diatas tidak berlaku untuk MultiLayer scenario
							
							System.out.println("Member Benefit Limit Size : "+memberBenefitList.size());
							
							String ruleName = "";
							
							if (memberBenefitList != null ){
								Iterator<MemberBenefit> benIterator = memberBenefitList.iterator();
								
								if (benIterator.hasNext()){
									MemberBenefit memberBenefit = benIterator.next();
									
									if (memberBenefit!= null){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit().doubleValue() == -1.0){
												ruleName = "AS CHARGE";
											}
											else {
											
												if (memberBenefit.getBenefitLimit().doubleValue() > 0.0){
													BigDecimal bc = new BigDecimal(memberBenefit.getBenefitLimit());
													ruleName = bc.toPlainString();
												}												
											}
										}
										
										String rule = "";
										
										if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
											rule = "ANNUAL";
										}
										if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.PERDISABILITY){
											rule = "PER DISABILITY";
										}
										if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.MAX_DAILY_AND_OCCURANCE_METHOD){
											rule = "MAX DAILY";
										}
										ruleName += " / " +rule; 
									}
								}
							}
							ruleCollection.add(ruleName);
							//colItemId.add(item.getItemId()); -->bikin error... dua kali add item id dalam satu kali for :(
							
							Iterator<CaseItem> itemIterator = claimItem.iterator();
							
							if (itemIterator != null){
								
								boolean isAvailable = false;
								
								while (itemIterator.hasNext()){
									
									CaseItem ci = itemIterator.next();
									
									System.out.println("CI :  " + ci.getItemId().getItemId().intValue() + " Item Id : " +  item.getItemId().intValue());
									
									if (ci != null && (ci.getItemId().getItemId().intValue() == item.getItemId().intValue())){
										valueCol.add(ci.getUsageValue().toString());
										amounCollection.add(ci.getUsageAmount().toString());
										claimItemCol.add(ci.getCaseItemId().toString());
										blankCol.add(ci.getDescription());
										isAvailable = true;
									}
								}
								
								if (!isAvailable){
									valueCol.add("");
									amounCollection.add("");
									claimItemCol.add("");
									blankCol.add("");
									//ruleCollection.add("");
								}
							}
							//edit 23022015
							//blankCol.add("");
							totalItem+=1;
						}
					}					
					request.setAttribute("claimItemCodeVector", colItemCode);
					request.setAttribute("claimItemNameVector", colItemName);
					request.setAttribute("claimItemVector", colItemId);
					request.setAttribute("claimAmountVector", amounCollection);
					request.setAttribute("claimItemRuleVector", ruleCollection);
					request.setAttribute("claimValueVector", valueCol);
					request.setAttribute("claimDescVector", blankCol);
					request.setAttribute("claimItemIdVector", claimItemCol);
					
					request.setAttribute("totalItem", new Integer(totalItem));
					System.out.println("TOTAL ITEM : "+totalItem);

				}
			}

			result.addObject("caseId", caseId);
			//result.addObject("caseValue", claim.getClaimChargeValue());
			//result.addObject("pembayaranDimuka", claim.getPembayaranDimuka());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView saveCaseItemPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SAVECASEITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SAVECASEITEM access");
				return errorResult;
				
			}
			String navigation = WebUtil.getParameterString(request, "navigation", "");

			Integer caseId = WebUtil
					.getParameterInteger(request, "caseId");
			
			
			String[] claimItemId = request.getParameterValues("itemId");
			System.out.println("CLAIM ITEM ID LENGTH : "+claimItemId.length);
			String[] claimAmountId = request.getParameterValues("itemAmount");
			String[] measurementUnit = request
					.getParameterValues("measurementUnit");
			
			String[] claimItemIdRef = request.getParameterValues("claimItemId");
			
			String[] itemValue = request.getParameterValues("itemValue");
			String[] description = request.getParameterValues("description");
			Double pembulatan = WebUtil.getParameterDouble(request, "pembulatan");
			
			result = new ModelAndView(new RedirectView("caseitem?navigation=list&caseId=" + caseId));
			
			System.out.println("Item ID :"+claimItemId.length);

	
			boolean isError = false;
			if (isError) {
				
				result = addCaseItemPerformed(request, response,
						"addCaseItem");

				Vector vect = new Vector();
				Vector itemVect = new Vector();
				Vector valueVect = new Vector();
				Vector amountVect = new Vector();

				if (description != null) {
					for (int i = 0; i < description.length; i++) {
						vect.add(description[i]);
						itemVect.add(claimItemId[i]);
						System.out.println("ITEM VECT : "+i+"="+claimItemId[i]);
						valueVect.add(itemValue[i]);
						System.out.println("VALUE VECT : "+i+"="+itemValue[i]);
						amountVect.add(claimAmountId[i]);
						System.out.println("AMOUNT VECT : "+i+"="+claimAmountId[i]);
					}
				}
				
				Case caseObject = null;
				
				if(caseId != null){
					try
					{
					java.io.Serializable casepkey = caseId;
					System.out.println("case Id : "+caseId);
					String[] caseRequired = {"Case.MemberId","Case.MemberId.ParentMember","Case.MemberId.MemberGroupId","Case.ClaimId","Case.Diagnosis2Id","Case.Diagnosis3Id"};
					caseObject = caseService.get(casepkey, caseRequired);
					
					}
					catch (Exception ex)
					{
//						System.out.println("member group object : "+caseObject.getAddress());
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
				}
				result.addObject("claimDescVector", vect);
				result.addObject("claimItemVector", itemVect);
				result.addObject("claimValueVector", valueVect);
				result.addObject("claimAmountVector", amountVect);
				result.addObject("myCase", caseObject);

				
				result.addObject("navigation", "addclaimitem");
				result.addObject("caseId", caseId);

				System.out.println("TEST : " + vect.get(0));

			} else {
				
				Case theCase = caseService.get(caseId);

				if (theCase != null) {
					
					Collection collection = new Vector();
					
					CaseItem  caseItem = null;
				
					Item item = null;
					
					if (claimItemId != null) {
						
						for (int i = 0; i < claimItemId.length; i++) {
							System.out.println("i = "+i);
							System.out.println("ITEM ID CTRL : "+claimItemId[i]);
							String itemVal = itemValue[i].trim();
							String amountId = claimAmountId[i].trim();
							
							caseItem = new CaseItem ();
							caseItem.setCaseId(theCase);

							item = new Item();
							item.setItemId(Integer.valueOf(claimItemId[i]));

							caseItem.setItemId(item);
							if(amountId.equals(""))
								caseItem.setUsageAmount(Double.valueOf(0));
							else
								caseItem.setUsageAmount(Double.valueOf(claimAmountId[i]));
							
							
							if(itemVal.equals(""))
								caseItem.setUsageValue(Double.valueOf(0));
							else
								caseItem.setUsageValue(Double.valueOf(itemValue[i]));
															
							caseItem.setDescription(description[i]);
							
							collection.add(caseItem);
							
							//edit 23022015 Add Bulk Item, jika kolomnya dihapus
							/*if (!itemVal.equals("") && !amountId.equals("")){
								caseItem = new CaseItem ();
								caseItem.setCaseId(theCase);
	
								item = new Item();
								item.setItemId(Integer.valueOf(claimItemId[i]));
	
								caseItem.setItemId(item);
								caseItem.setUsageAmount(Double
										.valueOf(claimAmountId[i]));
								caseItem.setUsageValue(Double
										.valueOf(itemValue[i]));								
								caseItem.setDescription(description[i]);
	
								
								collection.add(caseItem);
							}*/
						}
					}
					caseService.createCaseItem(caseId, collection, pembulatan, user);
					
					double totalExcess = 0;
					double totalApproved = 0;
					double totalCharge = 0;
					Collection<ClaimItem> claimItemList;
					String[] required = {"Claim.MemberId", "Claim.DiagnosisId"};
					Claim claim = claimService.get(theCase.getClaimId().getClaimId(),required);
					claimItemList = claimItemService.getBenefitCheckCalculationForLetter(claim);
					for(ClaimItem ci : claimItemList){
						if(ci.getExcessValue()!=null){
							totalExcess = totalExcess + ci.getExcessValue();
						}
						if(ci.getClaimItemCoveredValue() != null){
							//totalApproved = totalApproved + ci.getClaimItemApprovedValue();
							totalApproved = totalApproved + ci.getClaimItemCoveredValue();
						}
						if(ci.getClaimItemValue() != null){
							totalCharge = totalCharge + ci.getClaimItemValue();
						}
					}
					theCase.setCaseApprovedValue(totalApproved);
					theCase.setCaseExcessValue(totalExcess);
					theCase.setCaseClaimValue(totalCharge);
					caseService.update(theCase, user);
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

		String caseId = request.getParameter("caseId");	
		String breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a>";
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response, "detailCaseItem");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchCaseItem");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupCaseItem");
			} 
			else if (navigation.equalsIgnoreCase("addcaseitem")) {
				result = addCaseItemPerformed(request, response, "addBulkCaseItem");
				breadcrumb += "&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;<a href=\"caseitem?navigation=list&caseId="+caseId+"\" class=\"linkbreadcrumb\">List Treatment Item</a>&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Treatment Item";
			}
			else if (navigation.equalsIgnoreCase("savecaseitem")) {
				result = saveCaseItemPerformed(request, response, "searchCaseItem");
				breadcrumb += "&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Treatment Item";
			}
			else if (navigation.equalsIgnoreCase("list"))
			{
				result = searchPerformed(request, response, "searchCaseItem");
				breadcrumb += "&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Treatment Item";
			}
			else {
				result = searchPerformed(request, response, "searchCaseItem");
				breadcrumb += "&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Treatment Item";
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
