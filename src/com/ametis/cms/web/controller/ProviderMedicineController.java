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
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.ClaimMedicine;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Medicine;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.ProviderMedicine;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderMedicineService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+

// imports-

/**
 * ProviderMedicine is a servlet controller for provider_medicine Table. All you
 * have to do is to convert necessary data field to the named parameter
 */
public class ProviderMedicineController implements Controller

// extends+

// extends-
{

	private ProviderMedicineService providerMedicineService;
	private SecurityService securityService;
	private MemberService memberService;
	private CaseService caseService;
	private ClientService clientService;
	private MemberGroupService memberGroupService;
	private PolicyService policyService;

	private UserService actionuserService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	
	private ProviderService providerService;

	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}
	
	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
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

	public void setProviderMedicineService(
			ProviderMedicineService providerMedicineService) {
		this.providerMedicineService = providerMedicineService;
	}

	public ProviderMedicineService getProviderMedicineService() {
		return this.providerMedicineService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer providerMedicineId = WebUtil.getParameterInteger(request,
					"providerMedicineId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = providerMedicineId;

			ActionUser user = securityService.getActionUser(request);

			ProviderMedicine res = providerMedicineService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.providermedicine", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.providermedicine", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response,
					"searchProviderMedicine");
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
			Integer providerMedicineId = WebUtil.getParameterInteger(request,
					"providerMedicineId");

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
			java.io.Serializable pkey = providerMedicineId;
			ProviderMedicine object = providerMedicineService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.providermedicine", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("providerMedicine", object);
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

	public ModelAndView searchPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);
			
			

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String listnavigation = WebUtil.getParameterString(request,
					"listnavigation", "");
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
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("listprovider")) {

				if (searchby != null && searchtext!=null && !searchtext.equals("")) {
					if (searchby.equalsIgnoreCase("medicineName")) {
						vLikeP.add("medicineId.medicineName");
						vLikeQ.add(searchtext);
					}
					//Add Search Option by User Request 06032015
					if(searchby.equalsIgnoreCase("medicineFactory")){
						vLikeP.add("medicineId.medicineFactory");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("medicineType")){
						vLikeP.add("medicineId.medicineType");
						vLikeQ.add(searchtext);
					}
				}

			}

			vEqP.add("providerId.providerId");
			vEqQ.add(providerId);
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

			count = providerMedicineService
					.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"ProviderMedicine.ProviderId",
					"ProviderMedicine.MedicineId",
			// foreign affairs end
			};
			collection = providerMedicineService.search(sLikeP, sLikeQ, sEqP,
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
				collection = providerMedicineService.search(sLikeP, sLikeQ,
						sEqP, sEqQ, required, rowsetint, countSet.intValue());
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

			result.addObject("ProviderMedicines", collection);
			result.addObject("provider", providerObject);
			result.addObject("listnavigation",listnavigation);
			
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

	public ModelAndView addBulkPerformed (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView result = null;

 	    try {
			
 	    	Integer providerId = WebUtil.getParameterInteger (request,"providerId");	    	
			
 	    	String navigation = WebUtil.getParameterString(request, "navigation", "");
						
			ActionUser user = securityService.getActionUser(request);
			
			if (navigation.equalsIgnoreCase("addbulk")){	
				
				Vector<String> itemSVIPValueList = new Vector<String>();
				Vector<String> itemMarginSVIPValueList = new Vector<String>();
				Vector<String> itemDiskonSVIPValueList = new Vector<String>();
				
				Vector<String> itemVIPValueList = new Vector<String>();
				Vector<String> itemMarginVIPValueList = new Vector<String>();
				Vector<String> itemDiskonVIPValueList = new Vector<String>();
				
				Vector<String> itemC1ValueList = new Vector<String>();
				Vector<String> itemMarginC1ValueList = new Vector<String>();
				Vector<String> itemDiskonC1ValueList = new Vector<String>();
				
				Vector<String> itemC2ValueList = new Vector<String>();
				Vector<String> itemMarginC2ValueList = new Vector<String>();
				Vector<String> itemDiskonC2ValueList = new Vector<String>();
				
				Vector<String> itemC3ValueList = new Vector<String>();
				Vector<String> itemMarginC3ValueList = new Vector<String>();
				Vector<String> itemDiskonC3ValueList = new Vector<String>();
				
				Vector<String> itemRJValueList = new Vector<String>();
				Vector<String> itemMarginRJValueList = new Vector<String>();
				Vector<String> itemDiskonRJValueList = new Vector<String>();
				
				Vector<String> clientCodeValueList = new Vector<String>();
				Vector<String> memberGroupCodeValueList = new Vector<String>();
				Vector<String> policyNumberValueList = new Vector<String>();
				
				Vector<String> itemAmountList = new Vector<String>();
				Vector<String> medicineList = new Vector<String>();
				Vector<String> medicineIdList = new Vector<String>();
				Vector<String> descList = new Vector<String>();
				Vector<String> priceList = new Vector<String>();
				
				
				for(int i = 0; i < 10; i++){
					
					itemSVIPValueList.add("");
					itemMarginSVIPValueList.add("");
					itemDiskonSVIPValueList.add("");
					
					itemVIPValueList.add("");
					itemMarginVIPValueList.add("");
					itemDiskonVIPValueList.add("");
					
					itemC1ValueList.add("");
					itemMarginC1ValueList.add("");
					itemDiskonC1ValueList.add("");
					
					itemC2ValueList.add("");
					itemMarginC2ValueList.add("");
					itemDiskonC2ValueList.add("");
					
					itemC3ValueList.add("");
					itemMarginC3ValueList.add("");
					itemDiskonC3ValueList.add("");
					
					itemRJValueList.add("");
					itemMarginRJValueList.add("");
					itemDiskonRJValueList.add("");
					
					clientCodeValueList.add("");
					memberGroupCodeValueList.add("");
					policyNumberValueList.add("");
					
					medicineList.add("");
					medicineIdList.add("");
					itemAmountList.add("");
					descList.add("");
					priceList.add("");
					
				}
				result = new ModelAndView("addBulkMedicine");
				
				result.addObject("valueSVIPVector", itemSVIPValueList);
				result.addObject("valueMarginSVIPVector", itemMarginSVIPValueList);
				result.addObject("valueDiskonSVIPVector", itemDiskonSVIPValueList);
				
				result.addObject("valueVIPVector", itemVIPValueList);
				result.addObject("valueMarginVIPVector", itemMarginVIPValueList);
				result.addObject("valueDiskonVIPVector", itemDiskonVIPValueList);
				
				result.addObject("valueC1Vector", itemC1ValueList);
				result.addObject("valueMarginC1Vector", itemMarginC1ValueList);
				result.addObject("valueDiskonC1Vector", itemDiskonC1ValueList);
				
				result.addObject("valueC2Vector", itemC2ValueList);
				result.addObject("valueMarginC2Vector", itemMarginC2ValueList);
				result.addObject("valueDiskonC2Vector", itemDiskonC2ValueList);
				
				result.addObject("valueC3Vector", itemC3ValueList);
				result.addObject("valueMarginC3Vector", itemMarginC3ValueList);
				result.addObject("valueDiskonC3Vector", itemDiskonC3ValueList);
				
				result.addObject("valueRJVector", itemRJValueList);
				result.addObject("valueMarginRJVector", itemMarginRJValueList);
				result.addObject("valueDiskonRJVector", itemDiskonRJValueList);
				
				result.addObject("medClientCodeVector", clientCodeValueList);
				
				result.addObject("medMemberGroupCodeVector", memberGroupCodeValueList);
				
				result.addObject("medPolicyNumberVector", policyNumberValueList);
				
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
				String[] svipChargeList = request.getParameterValues("svipValue");
				String[] marginSvipChargeList = request.getParameterValues("marginSvipValue");
				String[] diskonSvipChargeList = request.getParameterValues("diskonSvipValue");
				String[] vipChargeList = request.getParameterValues("vipValue");
				String[] marginVipChargeList = request.getParameterValues("marginVipValue");
				String[] diskonVipChargeList = request.getParameterValues("diskonVipValue");
				String[] c1ChargeList = request.getParameterValues("kelas1Value");
				String[] marginC1ChargeList = request.getParameterValues("marginKelas1Value");
				String[] diskonC1ChargeList = request.getParameterValues("diskonKelas1Value");
				String[] c2ChargeList = request.getParameterValues("kelas2Value");
				String[] marginC2ChargeList = request.getParameterValues("marginKelas2Value");
				String[] diskonC2ChargeList = request.getParameterValues("diskonKelas2Value");
				String[] c3ChargeList = request.getParameterValues("kelas3Value");
				String[] marginC3ChargeList = request.getParameterValues("marginKelas3Value");
				String[] diskonC3ChargeList = request.getParameterValues("diskonKelas3Value");
				String[] rjChargeList = request.getParameterValues("rjValue");
				String[] marginRjChargeList = request.getParameterValues("marginRjValue");
				String[] diskonRjChargeList = request.getParameterValues("diskonRjValue");
				
				String[] priceList = request.getParameterValues("itemPrice");
				
				String[] clientCodeList = request.getParameterValues("clientCode");
				String[] memberGroupCodeList = request.getParameterValues("memberGroupCode");
				String[] policyNumberList = request.getParameterValues("policyNumber");
				
				String[] descList = request.getParameterValues("description");

				Collection<ProviderMedicine> medicineList = new Vector<ProviderMedicine>();
				
				for (int i = 0; i < medicineId.length; i++){
					
				
					if (medicineId[i] != null && !medicineId[i].trim().equalsIgnoreCase("")){
						ProviderMedicine med = new ProviderMedicine();
						Medicine medicine = new Medicine();
						medicine.setMedicineId(Integer.valueOf(medicineId[i]));
						
						
						med.setMedicineId(medicine);
						if (priceList[i] != null && !priceList[i].trim().equalsIgnoreCase("")){
							med.setReferencePrice(Double.valueOf(priceList[i]));
							
						}
						else {
							med.setReferencePrice(-1.0);
						}
						
						//SVIP
						if(!svipChargeList[i].trim().equalsIgnoreCase("")){
							double dblSVIP = Double.valueOf(svipChargeList[i]);
							double dblMarginSVIP = 0.0;
							if(!marginSvipChargeList[i].trim().equalsIgnoreCase("")){
								dblMarginSVIP = Double.valueOf(marginSvipChargeList[i]);
							}
							double dblDiskonSVIP = 0.0;
							if(!diskonSvipChargeList[i].trim().equalsIgnoreCase("")){
								dblDiskonSVIP = Double.valueOf(diskonSvipChargeList[i]);
							}
													
							if(marginSvipChargeList[i].trim().equalsIgnoreCase("") 
									&& !diskonSvipChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblSVIP -= (dblSVIP * dblDiskonSVIP/100);
					          }else if( diskonSvipChargeList[i].trim().equalsIgnoreCase("") 
					        		  && !marginSvipChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblSVIP += (dblSVIP * dblMarginSVIP/100) ;
					          }else if(!diskonSvipChargeList[i].trim().equalsIgnoreCase("") 
					        		  && !marginSvipChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblSVIP += ((dblSVIP * dblMarginSVIP/100) - ((dblSVIP * dblMarginSVIP/100) * dblDiskonSVIP/100));
					        }
							
							if (svipChargeList[i] != null && !svipChargeList[i].trim().equalsIgnoreCase("")){
	//						med.setSvip(Double.valueOf(svipChargeList[i]));
								med.setSvip(dblSVIP);
							}
							if (!marginSvipChargeList[i].trim().equalsIgnoreCase("")){
								med.setMarginSVIP(Double.valueOf(marginSvipChargeList[i]));
							}
							if (!diskonSvipChargeList[i].trim().equalsIgnoreCase("")){
								med.setDiskonSVIP(Double.valueOf(diskonSvipChargeList[i]));
							}
						}
						
						//VIP
						if(!vipChargeList[i].trim().equalsIgnoreCase("")){
							double dblVIP = Double.valueOf(vipChargeList[i]);
							double dblMarginVIP = 0.0;
							if(!marginVipChargeList[i].trim().equalsIgnoreCase("")){
								dblMarginVIP = Double.valueOf(marginVipChargeList[i]);
							}
							double dblDiskonVIP = 0.0;
							if(!diskonVipChargeList[i].trim().equalsIgnoreCase("")){
								dblDiskonVIP = Double.valueOf(diskonVipChargeList[i]);
							}
													
							if(marginVipChargeList[i].trim().equalsIgnoreCase("") 
									&& !diskonVipChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblVIP -= (dblVIP * dblDiskonVIP/100);
					          }else if( diskonVipChargeList[i].trim().equalsIgnoreCase("") 
					        		  && !marginVipChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblVIP += (dblVIP * dblMarginVIP/100) ;
					          }else if(!diskonVipChargeList[i].trim().equalsIgnoreCase("") 
					        		  && !marginVipChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblVIP += ((dblVIP * dblMarginVIP/100) - ((dblVIP * dblMarginVIP/100) * dblDiskonVIP/100));
					        }
							
							if (vipChargeList[i] != null && !vipChargeList[i].trim().equalsIgnoreCase("")){
	//						med.setVip(Double.valueOf(vipChargeList[i]));
								med.setVip(dblVIP);
							}
							if (!marginVipChargeList[i].trim().equalsIgnoreCase("")){
								med.setMarginVIP(Double.valueOf(marginVipChargeList[i]));
							}
							if (!diskonVipChargeList[i].trim().equalsIgnoreCase("")){
								med.setDiskonVIP(Double.valueOf(diskonVipChargeList[i]));
							}
						}
									
						//Kelas 1
						if(!c1ChargeList[i].trim().equalsIgnoreCase("")){
							double dblC1 = Double.valueOf(c1ChargeList[i]);
							double dblMarginC1 = 0.0;
							if(!marginC1ChargeList[i].trim().equalsIgnoreCase("")){
								dblMarginC1 = Double.valueOf(marginC1ChargeList[i]);
							}
							double dblDiskonC1 = 0.0;
							if(!diskonC1ChargeList[i].trim().equalsIgnoreCase("")){
								dblDiskonC1 = Double.valueOf(diskonC1ChargeList[i]);
							}
													
							if(marginC1ChargeList[i].trim().equalsIgnoreCase("") 
									&& !diskonC1ChargeList[i].trim().equalsIgnoreCase("")){
								dblC1 -= (dblC1 * dblDiskonC1/100);
					          }else if( diskonC1ChargeList[i].trim().equalsIgnoreCase("") 
					        		  && !marginC1ChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblC1 += (dblC1 * dblMarginC1/100) ;
					          }else if(!diskonC1ChargeList[i].trim().equalsIgnoreCase("") 
					        		  && !marginC1ChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblC1 += ((dblC1 * dblMarginC1/100) - ((dblC1 * dblMarginC1/100) * dblDiskonC1/100));
					        }
													
							if (c1ChargeList[i] != null && !c1ChargeList[i].trim().equalsIgnoreCase("")){
	//						med.setKelas1(Double.valueOf(c1ChargeList[i]));
								med.setKelas1(dblC1);
							}
							if (!marginC1ChargeList[i].trim().equalsIgnoreCase("")){
								med.setMarginKelas1(Double.valueOf(marginC1ChargeList[i]));
							}
							if (!diskonC1ChargeList[i].trim().equalsIgnoreCase("")){
								med.setDiskonKelas1(Double.valueOf(diskonC1ChargeList[i]));
							}	
						}
								
						//Kelas 2
						if(!c2ChargeList[i].trim().equalsIgnoreCase("")){
							double dblC2 = Double.valueOf(c2ChargeList[i]);
							double dblMarginC2 = 0.0;
							if(!marginC2ChargeList[i].trim().equalsIgnoreCase("")){
								dblMarginC2 = Double.valueOf(marginC2ChargeList[i]);
							}
							double dblDiskonC2 = 0.0;
							if(!diskonC2ChargeList[i].trim().equalsIgnoreCase("")){
								dblDiskonC2 = Double.valueOf(diskonC2ChargeList[i]);
							}
							
							if(marginC2ChargeList[i].trim().equalsIgnoreCase("") 
									&& !diskonC2ChargeList[i].trim().equalsIgnoreCase("")){
								dblC2 -= (dblC2 * dblDiskonC2/100);
					          }else if( diskonC2ChargeList[i].trim().equalsIgnoreCase("") 
					        		  && !marginC2ChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblC2 += (dblC2 * dblMarginC2/100) ;
					          }else if(!diskonC2ChargeList[i].trim().equalsIgnoreCase("") 
					        		  && !marginC2ChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblC2 += ((dblC2 * dblMarginC2/100) - ((dblC2 * dblMarginC2/100) * dblDiskonC2/100));
					        }
							
							
							if (c2ChargeList[i] != null && !c2ChargeList[i].trim().equalsIgnoreCase("")){
	//						med.setKelas2(Double.valueOf(c2ChargeList[i]));
								med.setKelas2(dblC2);
							}
							if (!marginC2ChargeList[i].trim().equalsIgnoreCase("")){
								med.setMarginKelas2(Double.valueOf(marginC2ChargeList[i]));
							}
							if (!diskonC2ChargeList[i].trim().equalsIgnoreCase("")){
								med.setDiskonKelas2(Double.valueOf(diskonC2ChargeList[i]));
							}
						}
						
						//Kelas 3
						if(!c3ChargeList[i].trim().equalsIgnoreCase("")){
							double dblC3 = Double.valueOf(c3ChargeList[i]);
							double dblMarginC3 = 0.0;
							if(!marginC3ChargeList[i].trim().equalsIgnoreCase("")){
								dblMarginC3 = Double.valueOf(marginC3ChargeList[i]);
							}
							double dblDiskonC3 = 0.0;
							if(!diskonC3ChargeList[i].trim().equalsIgnoreCase("")){
								dblDiskonC3 = Double.valueOf(diskonC3ChargeList[i]);
							}
							
							if(marginC3ChargeList[i].trim().equalsIgnoreCase("") 
									&& !diskonC3ChargeList[i].trim().equalsIgnoreCase("")){
								dblC3 -= (dblC3 * dblDiskonC3/100);
					          }else if( diskonC3ChargeList[i].trim().equalsIgnoreCase("") 
					        		  && !marginC3ChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblC3 += (dblC3 * dblMarginC3/100) ;
					          }else if(!diskonC3ChargeList[i].trim().equalsIgnoreCase("") 
					        		  &&  !marginC3ChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblC3 += ((dblC3 * dblMarginC3/100) - ((dblC3 * dblMarginC3/100) * dblDiskonC3/100));
					        }
							
							if (c3ChargeList[i] != null && !c3ChargeList[i].trim().equalsIgnoreCase("")){
//							med.setKelas3(Double.valueOf(c3ChargeList[i]));
								med.setKelas3(dblC3);
							}
							if (!marginC3ChargeList[i].trim().equalsIgnoreCase("")){
								med.setMarginKelas3(Double.valueOf(marginC3ChargeList[i]));
							}
							if (!diskonC3ChargeList[i].trim().equalsIgnoreCase("")){
								med.setDiskonKelas3(Double.valueOf(diskonC3ChargeList[i]));
							}
						}
						
						
						//RJ
						if(!rjChargeList[i].trim().equalsIgnoreCase("")){
							double dblRJ = Double.valueOf(rjChargeList[i]);
							double dblMarginRJ = 0.0;
							if(!marginRjChargeList[i].trim().equalsIgnoreCase("")){
								dblMarginRJ = Double.valueOf(marginRjChargeList[i]);
							}
							double dblDiskonRJ = 0.0;
							if(!diskonRjChargeList[i].trim().equalsIgnoreCase("")){
								dblDiskonRJ = Double.valueOf(diskonRjChargeList[i]);
							}
							
							if(marginRjChargeList[i].trim().equalsIgnoreCase("")
									&& !diskonRjChargeList[i].trim().equalsIgnoreCase("")){
								dblRJ -= (dblRJ * dblDiskonRJ/100);
					          }else if( diskonRjChargeList[i].trim().equalsIgnoreCase("") 
					        		  && !marginRjChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblRJ += (dblRJ * dblMarginRJ/100) ;
					          }else if(!diskonRjChargeList[i].trim().equalsIgnoreCase("") 
					        		  && !marginRjChargeList[i].trim().equalsIgnoreCase("")){
					        	  dblRJ += ((dblRJ * dblMarginRJ/100) - ((dblRJ * dblMarginRJ/100) * dblDiskonRJ/100));
					        }
							if (rjChargeList[i] != null && !rjChargeList[i].trim().equalsIgnoreCase("")){
	//							med.setRj(Double.valueOf(rjChargeList[i]));
								med.setRj(dblRJ);
							}
							if (!marginRjChargeList[i].trim().equalsIgnoreCase("")){
								med.setMarginRJ(Double.valueOf(marginRjChargeList[i]));
							}
							if (!diskonRjChargeList[i].trim().equalsIgnoreCase("")){
								med.setDiskonRJ(Double.valueOf(diskonRjChargeList[i]));
							}
						}
						
						med.setDescription(descList[i]);
						
						if (!clientCodeList[i].trim().equalsIgnoreCase("")){
							Client client = clientService.getClient(clientCodeList[i]);
							med.setClientId(client);
						}
						
						if (!memberGroupCodeList[i].trim().equalsIgnoreCase("")){
							MemberGroup memberGroup = memberGroupService.getMemberGroupByCode(memberGroupCodeList[i]);
							med.setMemberGroupId(memberGroup);
						}
						
						if (!policyNumberList[i].trim().equalsIgnoreCase("") && !clientCodeList[i].trim().equalsIgnoreCase("")){
							Client client = clientService.getClient(clientCodeList[i]);
							Policy policy = policyService.getPolicyByNumber(policyNumberList[i], client.getClientId());
							med.setPolicyId(policy);
						}
						
						medicineList.add(med);
						
					}
				}
				boolean res = providerMedicineService.addMedicine(providerId, medicineList, user);
				
				String alert = "";
				
				if (res){
					alert = "Success Add Medicine";
				}
				else {
					alert = "Failed Add Medicine";
				}
				result = new ModelAndView(new RedirectView("providermedicine?navigation=list&providerId="+providerId+"&alert="+alert));
			}
			result.addObject("providerId", providerId);
			request.setAttribute("providerId", providerId);
		
			
		}
		catch (Exception e){
		// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
		 e.printStackTrace();
		request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));
		result = new ModelAndView ("error");
		}	    return result;
	}
	
	//PENAMBAHAN LOOKUP JSON PROVIDER MEDICINE CLIENT, MEMBER GROUP, POLICY, BY AULIA R.
	public ModelAndView lookupJsonPerformedClient(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			String q = WebUtil.getParameterString(request, "q", "");
			
			Integer memberId = WebUtil.getParameterInteger(request, "memberIdauto");
			System.out.println("member id " + memberId);
			Member member = memberService.get(memberId);
			
			Integer caseId = WebUtil.getParameterInteger(request, "caseIdauto");
			System.out.println("caseId " + caseId);
			Case theCase = caseService.get(caseId);
			
			String[] likeParam = { "medicineId.medicineName" };
			String[] eqParam = { "deletedStatus" , "clientId.clientId", "providerId.providerId"};
			

			Object[] likeValue = { q };
			Object[] eqValue = { Integer.valueOf(0), member.getClientId().getClientId() , theCase.getProviderId().getProviderId()};
			
			System.out.println("member.getClientId().getClientId() " + member.getClientId().getClientId());
			System.out.println("theCase.getProviderId().getProviderId() " + theCase.getProviderId().getProviderId());
			
			String[] required = {"ProviderMedicine.MedicineId", "ProviderMedicine.ClientId", "ProviderMedicine.ProviderId" };

			
			Collection<ProviderMedicine> medList = providerMedicineService.search(likeParam,
					likeValue, eqParam, eqValue, required, 0, 10);

			result = new ModelAndView("lookupProviderMedicineJson");
			
			result.addObject("ProviderMedicines", medList);
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

	public ModelAndView lookupJsonPerformedMemberGroup(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			String q = WebUtil.getParameterString(request, "q", "");
			
			Integer memberId = WebUtil.getParameterInteger(request, "memberIdauto");
			System.out.println("member id " + memberId);
			Member member = memberService.get(memberId);
			
			Integer caseId = WebUtil.getParameterInteger(request, "caseIdauto");
			System.out.println("caseId " + caseId);
			Case theCase = caseService.get(caseId);
			
			String[] likeParam = { "medicineId.medicineName" };
			String[] eqParam = { "deletedStatus" , "memberGroupId.memberGroupId", "providerId.providerId"};
			

			Object[] likeValue = { q };
			Object[] eqValue = { Integer.valueOf(0), member.getMemberGroupId().getMemberGroupId() , theCase.getProviderId().getProviderId()};
			
			String[] required = {"ProviderMedicine.MedicineId", "ProviderMedicine.MemberGroupId", "ProviderMedicine.ProviderId" };

			Collection<ProviderMedicine> medList = providerMedicineService.search(likeParam,
					likeValue, eqParam, eqValue, required, 0, 10);

			result = new ModelAndView("lookupProviderMedicineJson");
			
			
			result.addObject("ProviderMedicines", medList);
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

	public ModelAndView lookupJsonPerformedPolicy(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			String q = WebUtil.getParameterString(request, "q", "");
			Integer memberId = WebUtil.getParameterInteger(request, "memberIdauto");
			System.out.println("member id " + memberId);
			Member member = memberService.get(memberId);
			
			Integer caseId = WebUtil.getParameterInteger(request, "caseIdauto");
			System.out.println("caseId " + caseId);
			Case theCase = caseService.get(caseId);
			
			String[] likeParam = { "medicineId.medicineName" };
			String[] eqParam = { "deletedStatus" , "policyId.policyId", "providerId.providerId"};
			

			Object[] likeValue = { q };
			Object[] eqValue = { Integer.valueOf(0), member.getCurrentPolicyId().getPolicyId() , theCase.getProviderId().getProviderId()};
			
			String[] required = {"ProviderMedicine.MedicineId", "ProviderMedicine.PolicyId", "ProviderMedicine.ProviderId" };

			Collection<ProviderMedicine> medList = providerMedicineService.search(likeParam,
					likeValue, eqParam, eqValue, required, 0, 10);

			result = new ModelAndView("lookupProviderMedicineJson");
						
			
			result.addObject("ProviderMedicines", medList);
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
		
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
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
				result = detailPerformed(request, response,	"detailProviderMedicine");
			} 
			else if (navigation.equalsIgnoreCase("addbulk") || navigation.equalsIgnoreCase("savebulk")) {
				result= addBulkPerformed(request, response);
				breadcrumb = "<a href=\"providermedicine?navigation=listprovider&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Medicine</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Medicine";
			}
			else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchProviderMedicine");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupProviderMedicine");
			} else if (navigation.equalsIgnoreCase("lookupjsonClient")) {
					result = lookupJsonPerformedClient(request, response);
			} else if (navigation.equalsIgnoreCase("lookupjsonMemberGroup")) {
				result = lookupJsonPerformedMemberGroup(request, response);
			} else if (navigation.equalsIgnoreCase("lookupjsonPolicy")) {
				result = lookupJsonPerformedPolicy(request, response);
			} else if (navigation.equalsIgnoreCase("listprovider")) {
				result = searchPerformed(request, response,
						"searchProviderMedicine");
//				String providerId = request.getParameter("providerId");
				if(listnavigation.equalsIgnoreCase("searchcapitation"))
				{
					breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"&listnavigation="+listnavigation+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Medicine";
				}
				else
				{
					breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Medicine";
				}
			}else {
				result = searchPerformed(request, response,
						"searchProviderMedicine");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.addObject("breadcrumb", breadcrumb);
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
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
