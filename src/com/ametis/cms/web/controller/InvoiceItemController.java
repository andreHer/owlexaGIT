
package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
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
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClientContract;
import com.ametis.cms.datamodel.ClientContractItem;
import com.ametis.cms.datamodel.ContractType;
import com.ametis.cms.datamodel.Invoice;
import com.ametis.cms.datamodel.InvoiceItem;
import com.ametis.cms.datamodel.InvoiceItemMember;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberImport;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.InvoiceItemMemberService;
import com.ametis.cms.service.InvoiceItemService;
import com.ametis.cms.service.MemberImportService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.Converter;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * InvoiceItem is a servlet controller for invoice_item Table. All you have to
 * do is to convert necessary data field to the named parameter
 */
public class InvoiceItemController implements Controller {
	private ActivityLogService logService;

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	private InvoiceItemService invoiceItemService;
	private ClaimService claimService;
	private MemberImportService memberImportService;
	private InvoiceItemMemberService invoiceItemMemberService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;

	private SecurityService securityService;
	
	

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public MemberImportService getMemberImportService() {
		return memberImportService;
	}

	public void setMemberImportService(MemberImportService memberImportService) {
		this.memberImportService = memberImportService;
	}

	public InvoiceItemMemberService getInvoiceItemMemberService() {
		return invoiceItemMemberService;
	}

	public void setInvoiceItemMemberService(InvoiceItemMemberService invoiceItemMemberService) {
		this.invoiceItemMemberService = invoiceItemMemberService;
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

	public void setInvoiceItemService(InvoiceItemService invoiceItemService) {
		this.invoiceItemService = invoiceItemService;
	}

	public InvoiceItemService getInvoiceItemService() {
		return this.invoiceItemService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer invoiceItemId = WebUtil.getParameterInteger(request, "invoiceItemId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = invoiceItemId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEINVOICEITEM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEINVOICEITEM access");
				return errorResult;

			}
			InvoiceItem res = invoiceItemService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert",
						alertProperties.getMessage("success.delete.invoiceitem", null, "success", Locale.getDefault()));
			} else {
				request.setAttribute("alert",
						alertProperties.getMessage("fail.delete.invoiceitem", null, "fail", Locale.getDefault()));

			}
			result = searchPerformed(request, response, "searchInvoiceItem");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert",
					alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView detailPerformed(HttpServletRequest request, HttpServletResponse response, String location)
			throws Exception {
		ModelAndView result = null;

		try {
			Integer invoiceItemId = WebUtil.getParameterInteger(request, "invoiceItemId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request, "searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby", "");
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			result = new ModelAndView(location);
			java.io.Serializable pkey = invoiceItemId;
			InvoiceItem object = invoiceItemService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage("not.found.invoiceitem", null, "fail", Locale.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("invoiceItem", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert",
					alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView downloadAttachmentPerformed(HttpServletRequest request, HttpServletResponse response, String location)
			throws Exception {
		ModelAndView result = null;

		try {
			Integer invoiceItemId = WebUtil.getParameterInteger(request, "invoiceItemId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request, "searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby", "");
			
			result = new ModelAndView(location);
			java.io.Serializable pkey = invoiceItemId;
			InvoiceItem object = invoiceItemService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage("not.found.invoiceitem", null, "fail", Locale.getDefault()));
			}
			else {
				Invoice invoice = object.getInvoiceId();
				
				if (object.getInvoiceItemStatus() != null && object.getInvoiceItemStatus().getPaymentStatusId().intValue() == PaymentStatus.PAYMENT_DRAFT){
					if (object.getClientContractItemId().getContractUnitType().intValue() == ClientContractItem.PER_MEMBER){
						if (object.getClientContractItemId().getClientContractId().getMembershipPeriode().intValue() == ClientContract.ANNUALY){

							String[] eqParam = {"invoiceItemId.invoiceItemId","deletedStatus"};
							Object[] eqValue = {object.getInvoiceItemId(),0};
							
							int total = memberImportService.getTotal(null,null,eqParam,eqValue);
							Collection<MemberImport> memberImportList = memberImportService.search(null,null,eqParam,eqValue,0,total);
							
							String data = "No.|Member Name|Member Number|Card Number|Enrollment Date|Invoice Charge\r\n";
							
							int idx = 1;
							if (memberImportList != null && memberImportList.isEmpty()){
								for (Iterator iterator = memberImportList.iterator(); iterator.hasNext();) {
									MemberImport memberImport = (MemberImport) iterator.next();
									
									if (memberImport != null){
										data += idx+"|"+memberImport.getMemberName()+"|"+memberImport.getMemberNumber()+"|"+memberImport.getSwipeCardNumber()+"|"+memberImport.getCreatedTime()+"|"+Converter.getMoney(object.getClientContractItemId().getItemPrice()) +"\r\n" ;
												
										idx++;		
									}									
								}
							}
							
				            response.setContentType("application/x-csv");
				            response.setHeader("Pragma", "no-cache");
				            response.setHeader("Cache-Control", "no-cache");
				            response.setDateHeader("Expires", 0);

				            response.setHeader("Content-disposition", "inline; filename="
				                    + "InvoiceAttachment.csv");

				            ServletOutputStream sos = response.getOutputStream();

				            response.setHeader("Content-length", Integer.toString(data.length()));

				            sos.write(data.getBytes());
				            sos.close();
										
							
						}
						else {
							String[] eqParam = {"invoiceItemId.invoiceItemId","deletedStatus"};
							Object[] eqValue = {object.getInvoiceItemId(),0};
							
							int total = invoiceItemMemberService.getTotal(null,null,eqParam,eqValue);
							Collection<InvoiceItemMember> memberList = invoiceItemMemberService.search(null,null,eqParam,eqValue,0,total);

							String data = "No.|Member Name|Member Number|Card Number|Effective Date|Invoice Charge\r\n";
							int idx = 1;
							
							if (memberList != null && !memberList.isEmpty()){
								for (Iterator iterator = memberList.iterator(); iterator.hasNext();) {
									InvoiceItemMember invoiceItemMember = (InvoiceItemMember) iterator.next();
									
									if (invoiceItemMember != null){
										Member member = invoiceItemMember.getMemberId();
										
										data += idx+"|"+member.getFirstName()+"|"+member.getCustomerPolicyNumber()+"|"+member.getCurrentCardNumber()+"|"+member.getEffectiveDate()+"|"+ Converter.getMoney(invoiceItemMember.getBillingRate()) +"\r\n";
										
										idx ++;
									}
								}
							}
							
							
							response.setContentType("application/x-csv");
				            response.setHeader("Pragma", "no-cache");
				            response.setHeader("Cache-Control", "no-cache");
				            response.setDateHeader("Expires", 0);

				            response.setHeader("Content-disposition", "inline; filename="
				                    + "InvoiceAttachment.csv");

				            ServletOutputStream sos = response.getOutputStream();

				            response.setHeader("Content-length", Integer.toString(data.length()));

				            sos.write(data.getBytes());
				            sos.close();
										

						}
					}
					else if (object.getClientContractItemId().getContractUnitType().intValue() == ClientContractItem.PER_CLAIM){
						
						
							String[] eqParam = {"invoiceItemId.invoiceItemId","deletedStatus"};
							Object[] eqValue = {object.getInvoiceItemId(),0};
							
							int total = claimService.getTotal(null,null,eqParam,eqValue);
							Collection<Claim> claimList = claimService.search(null,null,eqParam,eqValue,0,total);
						
							String data = "No.|Claim Number|Member Name|Admission Date|Payment Request Date\r\n";
							
							int idx = 1;
							
							if (claimList != null && !claimList.isEmpty()){
								for (Iterator iterator = claimList.iterator(); iterator.hasNext();) {
									
									Claim claim = (Claim) iterator.next();
									
									if (claim != null){
									
										data += idx + "|" + claim.getClaimNumber() + "|" + claim.getMemberName() + "|" + claim.getAdmissionDate().toString() + "|" + claim.getPaymentGeneratedDate()+"\r\n";
										
										idx++;
									}
									
								}
							}
				            response.setContentType("application/x-csv");
				            response.setHeader("Pragma", "no-cache");
				            response.setHeader("Cache-Control", "no-cache");
				            response.setDateHeader("Expires", 0);

				            response.setHeader("Content-disposition", "inline; filename="
				                    + "InvoiceAttachment.csv");

				            ServletOutputStream sos = response.getOutputStream();

				            response.setHeader("Content-length", Integer.toString(data.length()));

				            sos.write(data.getBytes());
				            sos.close();
												
						
					}
				}
				else if (object.getInvoiceItemStatus() != null && 
						(object.getInvoiceItemStatus().getPaymentStatusId().intValue() == PaymentStatus.PAYMENT_OPEN || object.getInvoiceItemStatus().getPaymentStatusId().intValue() == PaymentStatus.PAYMENT_PAID )){

					if (object.getInvoiceItemAttachmentURL() != null){
						byte[] data = invoiceItemService.getAttachmentFile(invoiceItemId);
						
						if (data != null){
							
							String ext = "csv";
							
							StringTokenizer tokenizer = new StringTokenizer(object.getInvoiceItemAttachmentURL(),".");
							
							while(tokenizer.hasMoreTokens()){
								ext = tokenizer.nextToken();
							}
							
				            response.setContentType("application/x-csv");
				            response.setHeader("Pragma", "no-cache");
				            response.setHeader("Cache-Control", "no-cache");
				            response.setDateHeader("Expires", 0);

				            response.setHeader("Content-disposition", "inline; filename="
				                    + "InvoiceAttachment."+ext);

				            ServletOutputStream sos = response.getOutputStream();

				            response.setHeader("Content-length", Integer.toString(data.length));

				            sos.write(data);
				            sos.close();
						}
					}
				}
			}

			result.addObject("invoiceItem", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert",
					alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView searchPerformed(HttpServletRequest request, HttpServletResponse response, String location)
			throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request, "navigation", "");
			String searchtext = WebUtil.getParameterString(request, "searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby", "");
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
			if (navigation.equals("gosearch") || navigation.equals("golookup")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("createdBy")) {
						vLikeP.add("createdBy");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("deletedBy")) {
						vLikeP.add("deletedBy");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("modifiedBy")) {
						vLikeP.add("modifiedBy");
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

			count = invoiceItemService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
			else if (index.compareTo(new Integer(count / countSet.intValue() + 1)) == new Integer(1).intValue())
				index = new Integer(count / countSet.intValue() + 1);

			rowsetint = (new Integer((index.intValue() - 1) * countSet.intValue())).intValue();
			if (count % countSet.intValue() > 0) {
				result.addObject("halAkhir", new Integer(count / countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir", new Integer(count / countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
					// foreign affairs
					"InvoiceItem.ItemId", "InvoiceItem.InvoiceId",
					// foreign affairs end
			};
			collection = invoiceItemService.search(sLikeP, sLikeQ, sEqP, sEqQ, required, rowsetint,
					countSet.intValue());

			if (collection.size() <= 0) {
				index = new Integer(index.intValue() - 1);
				if (index.compareTo(new Integer(1)) == new Integer(-1).intValue())
					index = new Integer(1);
				else if (index.compareTo(new Integer(count / countSet.intValue() + 1)) == new Integer(1).intValue())
					index = new Integer(count / countSet.intValue() + 1);

				rowsetint = (new Integer((index.intValue() - 1) * countSet.intValue())).intValue();
				if (count % countSet.intValue() > 0) {
					result.addObject("halAkhir", new Integer(count / countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir", new Integer(count / countSet.intValue()));
				}
				collection = invoiceItemService.search(sLikeP, sLikeQ, sEqP, sEqQ, required, rowsetint,
						countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("InvoiceItems", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert",
					alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	/**
	 * Action Service
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get paramater navigation
		String navigation = request.getParameter("navigation") == null ? "welcome" : request.getParameter("navigation");

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
		 * 
		 * }
		 */
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response, "detailInvoiceItem");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search") || navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchInvoiceItem");
			} else if (navigation.equalsIgnoreCase("lookup") || navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupInvoiceItem");
			} else {
				result = searchPerformed(request, response, "searchInvoiceItem");
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
