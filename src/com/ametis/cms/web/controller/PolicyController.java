package com.ametis.cms.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
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
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * Policy is a servlet controller for policy Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class PolicyController implements Controller

// extends+

// extends-
{

	private PolicyService policyService;
	private MemberGroupService memberGroupService;
	private SecurityService securityService;
	private UserService userService;
	private ClientService clientService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	
	private ActivityLogService logService;


	
	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}


	public ClientService getClientService() {
		return clientService;
	}
	
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
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

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public PolicyService getPolicyService() {
		return this.policyService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Long policyId = WebUtil.getParameterLong(request, "policyId");

			
			java.io.Serializable pkey = policyId;

			ActionUser user = securityService.getActionUser(request);

			Policy res = policyService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.policy", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties
						.getMessage("fail.delete.policy", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchPolicy");
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
	public ModelAndView renewPolicyPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer policyId = WebUtil.getParameterInteger(request, "policyId");

			Serializable pkey = policyId;
			ActionUser user = securityService.getActionUser(request);
			String navigation = WebUtil.getParameterString(request, "navigation", "");

			if (navigation.equalsIgnoreCase("renewal")){
				Date renewalDate = WebUtil.getParameterDate(request, "renewalDate");
				Date expireDate = WebUtil.getParameterDate(request, "expireDate");
				String notes = WebUtil.getParameterString(request, "notes", "");

				String[] required = {"Policy.ClientId"};
				
				Policy policy = policyService.get(pkey,required);
				
				boolean res = policyService.renewal(policy, renewalDate, expireDate, notes, user);

				String alert = "";
				if (res) {
					alert = "<b>Success Extend Policy</b>";
				} else {
					alert = "<b>Failed Extend Policy</b>";
	
				}
				result = new ModelAndView(new RedirectView("policy?navigation=detail&policyId="+policyId+"&alert="+alert));
			}
			else if (navigation.equalsIgnoreCase("prerenewal")){
				

				String[] required = {"Policy.ClientId"};
				
				Policy policy = policyService.get(pkey,required);
				
				result = new ModelAndView("renewalPolicy");
				result.addObject("policy", policy);
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
	public ModelAndView extendPolicyPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer policyId = WebUtil.getParameterInteger(request, "policyId");

			Serializable pkey = policyId;
			ActionUser user = securityService.getActionUser(request);
			String navigation = WebUtil.getParameterString(request, "navigation", "");

			if (navigation.equalsIgnoreCase("extend")){
				Date extendDate = WebUtil.getParameterDate(request, "extendDate");
				Date expireDate = WebUtil.getParameterDate(request, "expireDate");
				String notes = WebUtil.getParameterString(request, "notes", "");
				

				String[] required = {"Policy.ClientId"};
				
				Policy policy = policyService.get(pkey,required);
				
				String alert = "";
				boolean res = policyService.extend(policy, extendDate, expireDate, notes, user);
	
				if (res) {
					alert = "<b>Success Extend Policy</b>";
				} else {
					alert = "<b>Failed Extend Policy</b>";
	
				}
				result = new ModelAndView(new RedirectView("policy?navigation=detail&policyId="+policyId+"&alert="+alert));
			}
			else if (navigation.equalsIgnoreCase("preextend")){
				
				String[] required = {"Policy.ClientId"};
				
				Policy policy = policyService.get(pkey,required);
				
				result = new ModelAndView("extendPolicy");
				result.addObject("policy", policy);
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
	public ModelAndView terminatePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Long policyId = WebUtil.getParameterLong(request, "policyId");

			ActionUser user = securityService.getActionUser(request);

			boolean res = policyService.terminatePolicy(policyId, new java.sql.Date(System.currentTimeMillis()), user);

			if (res) {
				request.setAttribute("alert", "<b>Success Terminate Policy</b>");
			} else {
				request.setAttribute("alert", "<b>Failed Terminate Policy</b>");

			}
			result = detailPerformed(request, response, "detailPolicy");
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
	public ModelAndView downloadTCPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			
			Integer policyId = WebUtil.getParameterInteger(request, "policyId");

			ActionUser user = securityService.getActionUser(request);

			Policy policy = policyService.get(policyId);
			
			if (navigation.equalsIgnoreCase("downloadtc1")){
				if (policy.getPolicyTcFile1()!= null){
					
					String fileName = policy.getPolicyTcFile1();
					
					StringTokenizer tokenizer = new StringTokenizer(fileName,",");
					
					String ext = "";
					
					while(tokenizer.hasMoreTokens()){
						ext = tokenizer.nextToken();
					}
					
					byte[] content = policyService.getPolicyFile(policyId, policy.getPolicyTcFile1());
					
					String mimeType = "application/";
					
					if (ext.equalsIgnoreCase("pdf")){
						mimeType = "application/pdf";
					}
					else if (ext.equalsIgnoreCase("txt")){
						mimeType = "text/plain";
					}
					else if (ext.equalsIgnoreCase("doc")){
						mimeType = "application/msword";
					}
					else if (ext.equalsIgnoreCase("docx")){
						mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
					}
					else if (ext.equalsIgnoreCase("xls")){
						mimeType = "application/vnd.ms-excel";
					}
					else if (ext.equalsIgnoreCase("xlsx")){
						mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
					}
					else if (ext.equalsIgnoreCase("png")){
						mimeType = "image/png";
					}
					else if (ext.equalsIgnoreCase("jpg")){
						mimeType = "image/jpeg";
					}
					
					response.setContentType(mimeType);
					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);

					response.setHeader("Content-disposition", "inline; filename="
							+ fileName);

					ServletOutputStream sos = response.getOutputStream();

					response.setHeader("Content-length", Integer
							.toString(content.length));

					sos.write(content);
					sos.close();
				}
			}
			else if (navigation.equalsIgnoreCase("downloadtc2")){
				if (policy.getPolicyTcFile2()!= null){
					String fileName = policy.getPolicyTcFile2();
					
					StringTokenizer tokenizer = new StringTokenizer(fileName,",");
					
					String ext = "";
					
					while(tokenizer.hasMoreTokens()){
						ext = tokenizer.nextToken();
					}
					
					byte[] content = policyService.getPolicyFile(policyId, policy.getPolicyTcFile2());
					
					String mimeType = "application/";
					
					if (ext.equalsIgnoreCase("pdf")){
						mimeType = "application/pdf";
					}
					else if (ext.equalsIgnoreCase("txt")){
						mimeType = "text/plain";
					}
					else if (ext.equalsIgnoreCase("doc")){
						mimeType = "application/msword";
					}
					else if (ext.equalsIgnoreCase("docx")){
						mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
					}
					else if (ext.equalsIgnoreCase("xls")){
						mimeType = "application/vnd.ms-excel";
					}
					else if (ext.equalsIgnoreCase("xlsx")){
						mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
					}
					else if (ext.equalsIgnoreCase("png")){
						mimeType = "image/png";
					}
					else if (ext.equalsIgnoreCase("jpg")){
						mimeType = "image/jpeg";
					}
					
					response.setContentType(mimeType);
					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);

					response.setHeader("Content-disposition", "inline; filename="
							+ fileName);

					ServletOutputStream sos = response.getOutputStream();

					response.setHeader("Content-length", Integer
							.toString(content.length));

					sos.write(content);
					sos.close();
					
				}
			}
			else if (navigation.equalsIgnoreCase("downloadtc3")){
				if (policy.getPolicyTcFile3()!= null){

					String fileName = policy.getPolicyTcFile3();
					
					StringTokenizer tokenizer = new StringTokenizer(fileName,",");
					
					String ext = "";
					
					while(tokenizer.hasMoreTokens()){
						ext = tokenizer.nextToken();
					}
					
					byte[] content = policyService.getPolicyFile(policyId, policy.getPolicyTcFile3());
					
					String mimeType = "application/";
					
					if (ext.equalsIgnoreCase("pdf")){
						mimeType = "application/pdf";
					}
					else if (ext.equalsIgnoreCase("txt")){
						mimeType = "text/plain";
					}
					else if (ext.equalsIgnoreCase("doc")){
						mimeType = "application/msword";
					}
					else if (ext.equalsIgnoreCase("docx")){
						mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
					}
					else if (ext.equalsIgnoreCase("xls")){
						mimeType = "application/vnd.ms-excel";
					}
					else if (ext.equalsIgnoreCase("xlsx")){
						mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
					}
					else if (ext.equalsIgnoreCase("png")){
						mimeType = "image/png";
					}
					else if (ext.equalsIgnoreCase("jpg")){
						mimeType = "image/jpeg";
					}
					
					response.setContentType(mimeType);
					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);

					response.setHeader("Content-disposition", "inline; filename="
							+ fileName);

					ServletOutputStream sos = response.getOutputStream();

					response.setHeader("Content-length", Integer
							.toString(content.length));

					sos.write(content);
					sos.close();

				}
			}


			
			result = detailPerformed(request, response, "detailPolicy");
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
	public ModelAndView activatePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Long policyId = WebUtil.getParameterLong(request, "policyId");

			ActionUser user = securityService.getActionUser(request);

			boolean res = policyService.activatePolicy(policyId, user);

			if (res) {
				request.setAttribute("alert", "<b>Success Activate Policy</b>");
			} else {
				request.setAttribute("alert", "<b>Failed Activate Policy</b>");

			}
			result = detailPerformed(request, response, "detailPolicy");
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
	public ModelAndView synchronizePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer policyId = WebUtil.getParameterInteger(request, "policyId");

			ActionUser user = securityService.getActionUser(request);

			Policy policy = policyService.get(policyId);
			
			if (policy != null){
				policy.setDoSynchronize(1);
				policy = policyService.update(policy, user);
	
				if (policy != null) {
					request.setAttribute("alert", "<b>Success Synchronize Policy</b>");
				} else {
					request.setAttribute("alert", "<b>Failed Synchronize Policy</b>");
	
				}
			}
			// andre redirect ke halaman search
			response.sendRedirect(""+request.getContextPath()+"/policy");
			result = searchPerformed(request, response, "searchPolicy");
//			result = detailPerformed(request, response, "detailPolicy");
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
			
			Integer policyId = WebUtil.getParameterInteger(request, "policyId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			

			result = new ModelAndView(location);
			java.io.Serializable pkey = policyId;
			String[] required = {"Policy.BrokerId","Policy.ClientId","Policy.ProductType","Policy.CardTypeId"};
			Policy object = policyService.get(pkey,required);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.policy", null, "fail", Locale.getDefault()));
			}
			

			result.addObject("policy", object);
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

			/*String memberGroupId = WebUtil.getParameterString(request,
					"memberGroupId", "");*/
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
                        
            Integer clientId = WebUtil.getParameterInteger(request, "clientId");
            String searchStatus = WebUtil.getParameterString(request, "searchstatus","");
            
            String arah = WebUtil.getParameterString(request, "arah", "");
            String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
            
            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil.getParameterDate(request, "maximum_date");
			String dateBy = WebUtil.getParameterString(request, "dateby", "");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;
			
			boolean sortPolicyNumber = true, sortGroupName = true, sortClientName = true, sortEffectiveDate = true,
					sortExpireDate = true, sortStatus = true, sortRenewalDate = true, sortModifiedTime = true;
			boolean order = true;
			
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
			
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equalsIgnoreCase("golistgroup") || navigation.equalsIgnoreCase("golistclient")||
					navigation.equals("gosearchbysort") || navigation.equals("listgroup") || (navigation.equals("") && !arah.isEmpty())) {
				if (searchby != null && searchtext!=null && !searchtext.equals("")) {
					if (searchby.equalsIgnoreCase("policyNumber")) {
						vLikeP.add("policyNumber");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("groupName")){
						vLikeP.add("memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("clientName")){
						vLikeP.add("clientId.clientName");
						vLikeQ.add(searchtext);
					}
				}
				if (searchStatus != null && !searchStatus.equalsIgnoreCase("")){
					vEqP.add("policyStatus.statusId");
					vEqQ.add(Integer.valueOf(searchStatus));	
				}
			}
			
			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));
                        
            /*if (memberGroupId != null && !memberGroupId.equalsIgnoreCase("")){
                vEqP.add("memberGroupId.memberGroupId");
                vEqQ.add(Integer.valueOf(memberGroupId));
            }*/
			
			if (memberGroupId != null){
                vEqP.add("memberGroupId.memberGroupId");
                vEqQ.add(Integer.valueOf(memberGroupId));
            }
            if (clientId != null){
                vEqP.add("clientId.clientId");
                vEqQ.add(clientId);
            }

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
			String[] betweenBy = null;
			Object[] minDate = null;
			Object[] maxDate = null;
			
			if(!dateBy.equalsIgnoreCase("")){
				
				if (minimumDate != null && maximumDate != null){
					
					betweenBy = new String[1];
					betweenBy[0] = dateBy;
					minDate = new Date[1];
					minDate[0] = minimumDate;
					
					maxDate = new Date[1];
					maxDate[0] = maximumDate;
					
					if (dateBy.equalsIgnoreCase("createdTime")){
						minDate = new Timestamp[1];
						maxDate = new Timestamp[1];
						
						minDate[0] = new java.sql.Timestamp(minimumDate.getTime());
						maxDate[0] = new java.sql.Timestamp(maximumDate.getTime());
					}
				}
			}
			
			count = policyService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate);			


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
					"Policy.ClientId", "Policy.MemberGroupId",
					"Policy.QuotationId","Policy.BrokerId"
			// foreign affairs end
			};
			
			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equals("policynumber")){
						sortByColumn = "policyNumber";
						Boolean sortByOrderPolicyNo = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderPolicyNo", ""));
						sortPolicyNumber = !sortByOrderPolicyNo;
						order = sortPolicyNumber;
					}else if(sortcolumn.equals("groupname")){
						sortByColumn = "memberGroupId.groupName";
						Boolean sortByOrderGroup = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderGroup", ""));
						sortGroupName = !sortByOrderGroup;
						order = sortGroupName;
					}else if(sortcolumn.equals("clientname")){
						sortByColumn = "clientId.clientName";
						Boolean sortByOrderClient = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderClient", ""));
						sortClientName = !sortByOrderClient;
						order = sortClientName;
					}else if(sortcolumn.equals("effectivedate")){
						sortByColumn = "effectiveDate";
						Boolean sortByOrderEffectiveDate = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderEffectiveDate", ""));
						sortEffectiveDate = !sortByOrderEffectiveDate;
						order = sortEffectiveDate;
					}else if(sortcolumn.equals("expiredate")){
						sortByColumn = "expireDate";
						Boolean sortByOrderExpireDate = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderExpireDate", ""));
						sortExpireDate = !sortByOrderExpireDate;
						order = sortExpireDate;
					}else if(sortcolumn.equals("status")){
						sortByColumn = "policyStatus.status";
						Boolean sortByOrderStatus = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderStatus", ""));
						sortStatus = !sortByOrderStatus;
						order = sortStatus;
					}else if(sortcolumn.equals("renewaldate")){
						sortByColumn = "renewalDate";
						Boolean sortByOrderRenewalDate = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderRenewalDate", ""));
						sortRenewalDate = !sortByOrderRenewalDate;
						order = sortRenewalDate;
					}else{
						sortByColumn = "modifiedTime";
						Boolean sortByOrderModified = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderModified", ""));
						sortModifiedTime = !sortByOrderModified;
						order = sortModifiedTime;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equals("policynumber")){
						sortPolicyNumber = order;
					}else if(sortcolumn.equals("groupname")){
						sortGroupName = order;
					}else if(sortcolumn.equals("clientname")){
						sortClientName = order;
					}else if(sortcolumn.equals("effectivedate")){
						sortEffectiveDate = order;
					}else if(sortcolumn.equals("expiredate")){
						sortExpireDate = order;
					}else if(sortcolumn.equals("status")){
						sortStatus = order;
					}else if(sortcolumn.equals("renewaldate")){
						sortRenewalDate = order;
					}else{
						sortModifiedTime = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				
				collection = policyService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate,order,sortByColumn,
						required, rowsetint, countSet.intValue());
			}else{
				collection = policyService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, betweenBy, minDate, maxDate, required, rowsetint, countSet.intValue());
			}
			
//			if(navigation.equalsIgnoreCase("search") || (navigation.isEmpty() && checkOrder == true)|| 
//					((navigation.equals("gosearch") || navigation.equals("")) && !arah.isEmpty() && 
//							((searchTextTmp!=null && searchByTmp!=null) || searchStatusTmp!=null))){
//				String sortByColumn = new String();
//				if((arah.isEmpty()||arah.equals("")) && checkOrder==true){
//					if(sortby.equals("policy_number") || sortby.equals("policy_numberdsc")){
//						sortByColumn = "policyNumber";
//						if(sortby.equals("policy_number"))
//							sortPolicyNumber = false;
//						else
//							sortPolicyNumber = true;
//						order = sortPolicyNumber;
//					}else if(sortby.equals("group_name") || sortby.equals("group_namedsc")){
//						sortByColumn = "memberGroupId";
//						if(sortby.equals("group_name"))
//							sortGroupName = false;
//						else
//							sortGroupName = true;
//						order = sortGroupName;
//					}else if(sortby.equals("client_name") || sortby.equals("client_namedsc")){
//						sortByColumn = "clientId";
//						if(sortby.equals("client_name"))
//							sortClientName = false;
//						else
//							sortClientName = true;
//						order = sortClientName;
//					}else if(sortby.equals("effective_date") || sortby.equals("effective_datedsc")){
//						sortByColumn = "effectiveDate";
//						if(sortby.equals("effective_date"))
//							sortEffectiveDate = false;
//						else
//							sortEffectiveDate = true;
//						order = sortEffectiveDate;
//					}else if(sortby.equals("expire_date") || sortby.equals("expire_datedsc")){
//						sortByColumn = "expireDate";
//						if(sortby.equals("expire_date"))
//							sortExpireDate = false;
//						else
//							sortExpireDate = true;
//						order = sortExpireDate;
//					}else if(sortby.equals("status") || sortby.equals("statusdsc")){
//						sortByColumn = "policyStatus";
//						if(sortby.equals("status"))
//							sortStatus = false;
//						else
//							sortStatus = true;
//						order = sortStatus;
//					}else if(sortby.equals("renewal_date")|| sortby.equals("renewal_datedsc")){
//						sortByColumn = "renewalDate";
//						if(sortby.equals("renewal_date"))
//							sortRenewalDate = false;
//						else
//							sortRenewalDate = true;
//						order = sortRenewalDate;
//					}else{
//						sortByColumn = "modifiedTime";
//						if(sortby.equals("modified_time"))
//							sortModifiedTime = false;
//						else
//							sortModifiedTime = true;
//						order = sortModifiedTime;
//					}
//					//Add 20150216, for handling if no sortByColumn(i.e click Return To List)
//					if(!sortByColumn.isEmpty()){
//						collection = policyService.search(sLikeP, sLikeQ, sEqP, sEqQ,
//								required, sortByColumn, order, rowsetint, countSet.intValue());
//						chkOrder = order;
//						checkOrderColumn = sortByColumn;
//					}else{
//						collection = policyService.search(sLikeP, sLikeQ, sEqP, sEqQ,
//								required, rowsetint, countSet.intValue());
//						searchByTmp = null;
//						searchTextTmp = null;
//						searchStatusTmp = null;
//						checkOrder = false;
//					}
//				}else if((!arah.isEmpty()||!arah.equals("")) && checkOrder == true && !checkOrderColumn.isEmpty()){
//					collection = policyService.search(sLikeP, sLikeQ, sEqP, sEqQ,
//							required, checkOrderColumn, chkOrder, rowsetint, countSet.intValue());
//				}else{
//					searchByTmp = null;
//					searchTextTmp = null;
//					searchStatusTmp = null;
//					checkOrder = false;
//					collection = policyService.search(sLikeP, sLikeQ, sEqP, sEqQ,
//							required, rowsetint, countSet.intValue());
//				}
//			}else{
//				//checkOrder = false;
//				//default collection
//				collection = policyService.search(sLikeP, sLikeQ, sEqP, sEqQ,
//						required, rowsetint, countSet.intValue());
//			}
			

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
				//collection = policyService.search(sLikeP, sLikeQ, sEqP, sEqQ,
				//		required, rowsetint, countSet.intValue());
				collection = policyService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, betweenBy, minDate, maxDate, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("memberGroupId", memberGroupId);
            request.setAttribute("clientId", clientId);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("sortcolumn", sortcolumn);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */
			
			
			if (maximumDate != null
					&& maximumDate.toString().equals("1970-01-01")) {
				request.setAttribute("maximumDate", "");
			} else {
				request.setAttribute("maximumDate", maximumDate);
			}
			if (maximumDate != null
					&& maximumDate.toString().equals("1970-01-01")) {
				request.setAttribute("minimumDate", "");
			} else {
				request.setAttribute("minimumDate", minimumDate);
			}
			
			MemberGroup memberGroupObject = null;
			
			if(memberGroupId != null){
				try
				{
				java.io.Serializable memberGrouppkey = memberGroupId;
				System.out.println("member group id : "+memberGroupId);
				String[] memberGroupRequired = {"MemberGroup.ClientId","MemberGroup.BusinessCategoryId"};
				memberGroupObject = memberGroupService.get(memberGrouppkey, memberGroupRequired);
				
				}
				catch (Exception ex)
				{
					System.out.println("member group object : "+memberGroupObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			Client clientObject = null;
			
			if(clientId != null){
				try
				{
				java.io.Serializable clientpkey = clientId;
				System.out.println("member client id : "+clientId);
				String[] clientRequired = {"Client.FundCurrency","Client.PaymentCurrency","Client.StatusId"};
				clientObject = clientService.get(clientpkey, clientRequired);
				
				}
				catch (Exception ex)
				{
					System.out.println("member group object : "+clientObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			

			result.addObject("Policys", collection);
			result.addObject("client", clientObject);
			result.addObject("memberGroup", memberGroupObject);
			
			request.setAttribute("searchstatus", searchStatus);
			request.setAttribute("dateby", dateBy);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("sortPolicyNumber", sortPolicyNumber);
			request.setAttribute("sortGroupName", sortGroupName);
			request.setAttribute("sortClientName", sortClientName);
			request.setAttribute("sortEffectiveDate", sortEffectiveDate);
			request.setAttribute("sortExpireDate", sortExpireDate);
			request.setAttribute("sortStatus", sortStatus);
			request.setAttribute("sortRenewalDate", sortRenewalDate);
			request.setAttribute("sortModifiedTime", sortModifiedTime);

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
			result = new ModelAndView("lookupPolicyJson");

			String q= WebUtil.getParameterString(request, "q", "");
			
			String[] eqParam = {"deletedStatus"};
			Object[] eqValue = {0};
			String[] likeParam = {"policyNumber"};
			Object[] likeValue= {q};
			
			String[] required = {"Policy.ClientId"};
             
			Collection<Policy> policyList = policyService.search(likeParam,likeValue,eqParam,eqValue,required,0,15);
			
			result.addObject("PolicyList", policyList);
			
			

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
		String breadcrumb = "<a href=\"policy\" class=\"linkbreadcrumb\">Detail Policy</a>";
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				result = detailPerformed(request, response, "detailPolicy");
				String policyId = request.getParameter("policyId");
				breadcrumb = "<a href=\"policy?navigation=detail&policyId="+policyId+"\" class=\"linkbreadcrumb\">Detail Policy</a>";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} 
			else if (navigation.equalsIgnoreCase("synchronize")) {
					result = synchronizePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("activate")) {
				result = activatePerformed(request, response);
		}
			else if (navigation.equalsIgnoreCase("terminate")) {
					result = terminatePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("extend") || navigation.equalsIgnoreCase("preextend")) {
				result = extendPolicyPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("renewal") || navigation.equalsIgnoreCase("prerenewal")) {
				result = renewPolicyPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("downloadtc1") || navigation.equalsIgnoreCase("downloadtc2") ||
					navigation.equalsIgnoreCase("downloadtc3")) {
				result = downloadTCPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("lookupjson")) {
				result = lookupJsonPerformed(request, response, "lookupPolicyJson");
			}
			else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchPolicy");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupPolicy");
			} else if (navigation.equalsIgnoreCase("listgroup") || navigation.equalsIgnoreCase("golistgroup") ) {
				result = searchPerformed(request, response, "listGroupPolicy");
				String groupId = request.getParameter("memberGroupId");
				breadcrumb = "<a href=\"membergroup?navigation=detail&memberGroupId="+groupId+"\" class=\"linkbreadcrumb\">Detail Member Group</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Group Policy";
			} 
            else if (navigation.equalsIgnoreCase("listclient") || navigation.equalsIgnoreCase("golistclient")) {
				result = searchPerformed(request, response, "listClientPolicy");
				String clientId = request.getParameter("clientId");
				breadcrumb = "<a href=\"client?navigation=detail&clientId="+clientId+"\" class=\"linkbreadcrumb\">Detail Client</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Client Policy";
				
			}
            else {
				result = searchPerformed(request, response, "searchPolicy");
				breadcrumb = "<a href=\"policy\" class=\"linkbreadcrumb\">Search Policy</a>";
			}
			result.addObject("breadcrumb", breadcrumb);
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
