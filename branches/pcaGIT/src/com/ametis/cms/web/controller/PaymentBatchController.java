package com.ametis.cms.web.controller;

import java.io.IOException;
import java.sql.Date;
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
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentBatch;
import com.ametis.cms.datamodel.PaymentInstallment;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BankService;
import com.ametis.cms.service.PaymentBatchService;
import com.ametis.cms.service.PaymentInstallmentService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.BankTransferDocumentConverter;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+

// imports-

/**
 * PaymentBatch is a servlet controller for payment_batch Table. All you have to
 * do is to convert necessary data field to the named parameter
 */
public class PaymentBatchController implements Controller

// extends+

// extends-
{

	private PaymentBatchService paymentBatchService;
	private SecurityService securityService;
	private PaymentService paymentService;
	private BankService bankService;
	private PaymentInstallmentService paymentInstallmentService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
private ActivityLogService logService;

		
	
	public PaymentInstallmentService getPaymentInstallmentService() {
	return paymentInstallmentService;
}

public void setPaymentInstallmentService(
		PaymentInstallmentService paymentInstallmentService) {
	this.paymentInstallmentService = paymentInstallmentService;
}

	public BankService getBankService() {
	return bankService;
}

public void setBankService(BankService bankService) {
	this.bankService = bankService;
}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
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

	public void setPaymentBatchService(PaymentBatchService paymentBatchService) {
		this.paymentBatchService = paymentBatchService;
	}

	public PaymentBatchService getPaymentBatchService() {
		return this.paymentBatchService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer id = WebUtil.getParameterInteger(request, "id");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = id;

			ActionUser user = securityService.getActionUser(request);

			PaymentBatch res = paymentBatchService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.paymentbatch", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.paymentbatch", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchPaymentBatch");
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
	public ModelAndView confirmPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer id = WebUtil.getParameterInteger(request, "id");

			String navigation = WebUtil.getParameterString(request, "navigation", "");
			
			
			java.io.Serializable pkey = id;

			ActionUser user = securityService.getActionUser(request);

			PaymentBatch res = paymentBatchService.get(id);
			
			if (navigation.equalsIgnoreCase("preconfirm")){
				result = new ModelAndView("confirmPaymentBatch");
				result.addObject("paymentBatch", res);
				
				String[] eqParam = { "paymentBatchId.id" };
				Object[] eqValue = { id };

				int total = paymentInstallmentService.getTotal(null, null, eqParam, eqValue);
				Collection<PaymentInstallment> paymentCollection = paymentInstallmentService.search(null,
						null, eqParam, eqValue, 0, total);

				result.addObject("paymentCollection", paymentCollection);
			}
			else if (navigation.equalsIgnoreCase("confirm")){
				Date confirmDate = WebUtil.getParameterDate(request, "confirmDate");
				String approvalNote = WebUtil.getParameterString(request, "approvalNote", "");
				
				boolean status = paymentBatchService.confirmPayment(id, approvalNote, confirmDate, user);
				
				String alert = "";
				
				if (status){
					alert = "<b>Success Confirm Payment</b>";
				}
				else {
					alert = "<b>Failed Confirm Payment</b>";
				}
				
				result = new ModelAndView(new RedirectView("paymentbatch?navigation=detail&id="+id+"&alert="+alert));
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

			Integer id = WebUtil.getParameterInteger(request, "id");

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
			PaymentBatch object = paymentBatchService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.paymentbatch", null, "fail", Locale
								.getDefault()));
			}

			String[] eqParam = { "paymentBatchId.id" };
			Object[] eqValue = { id };

			int total = paymentInstallmentService.getTotal(null, null, eqParam, eqValue);
			Collection<PaymentInstallment> paymentCollection = paymentInstallmentService.search(null,
					null, eqParam, eqValue, 0, total);

			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("paymentCollection", paymentCollection);
			result.addObject("paymentBatch", object);
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
			
			Integer status = WebUtil.getParameterInteger(request,"searchstatus");
			

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

					if (searchby.equalsIgnoreCase("paymentBatchNumber")) {
						vLikeP.add("paymentBatchNumber");
						vLikeQ.add(searchtext);
					}
			
				}

			}

			if (status != null){
				if (status.intValue() >= 0){
					vEqP.add("batchStatus");
					vEqQ.add(status);
				}
			}
			// vEqP.add("deletedStatus");
			// vEqQ.add(new Integer(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = paymentBatchService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
			collection = paymentBatchService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = paymentBatchService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("PaymentBatchs", collection);

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
	public ModelAndView exportBankPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			String bankType = WebUtil.getParameterString(request, "bankType",
					"");

			String downloadDate = WebUtil.getParameterString(request,
					"downloadDate", "");

			Integer paymentBatchId = WebUtil.getParameterInteger(request,
					"paymentBatchId");

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

			/*
			 * if (bankType.equalsIgnoreCase("bca")){ vLikeP.add("bankName");
			 * vLikeQ.add("BCA"); } else if
			 * (bankType.equalsIgnoreCase("mandiri")){ vLikeP.add("bankName");
			 * vLikeQ.add("Mandiri"); } else { }
			 */

			// vEqP.add("paymentStatus.paymentStatusId");
			// vEqQ.add(new Integer(PaymentStatus.PAYMENT_DISPOSITION));
			if (paymentBatchId != null) {
				vEqP.add("paymentBatchId.id");
				vEqQ.add(paymentBatchId);

			}

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			String required[] = new String[] {
					// foreign affairs
					"Payment.OutstandingId", "Payment.MemberId",
					"Payment.ProviderId",
			// foreign affairs end
			};

			int idx = 1;
			String data = "";

			int total = paymentInstallmentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			collection = paymentInstallmentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required, 0, total);

			if (bankType.equalsIgnoreCase("bca")) {

				if (collection != null) {

					// data =
					// BankTransferDocumentConverter.convertToBCA(collection);

					java.sql.Date dlDate = java.sql.Date.valueOf(downloadDate);

					HSSFWorkbook workbook = BankTransferDocumentConverter
							.downloadBCAInstallment(collection, dlDate);

					java.sql.Date date = new java.sql.Date(System
							.currentTimeMillis());

					String filename = date.toString().replace("-", "")
							+ "-bca.xls";
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition",
							"attachment; filename=" + filename);

					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);

					ServletOutputStream sos = response.getOutputStream();

					workbook.write(sos);
					
					sos.close();
				}
			} else if (bankType.equalsIgnoreCase("mandiri")) {

				if (collection != null) {

					String fromAccount = "";
					String emailContact = "";
					if (paymentBatchId != null) {
						PaymentBatch paymentBatch = paymentBatchService
								.get(paymentBatchId);
						fromAccount = paymentBatch.getPaymentAccountSource()
								.getBankAccount();
					}
					data = BankTransferDocumentConverter.convertToMandiriInstallment(
							collection, fromAccount, "IDR", downloadDate,
							bankService,emailContact);

					System.out.println("DATA : " + data);

					response.setContentType("application/x-csv");
					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);

					response.setHeader("Content-disposition",
							"inline; filename=" + fromAccount + ".csv");

					ServletOutputStream sos = response.getOutputStream();

					response.setHeader("Content-length", Integer.toString(data
							.length()));

					sos.write(data.getBytes());
					sos.close();
				}
			} else {

			}

			/*
			 * 
			 * System.out.println("DATA : " + data);
			 * 
			 * response.setContentType("application/x-csv");
			 * response.setHeader("Pragma", "no-cache");
			 * response.setHeader("Cache-Control", "no-cache");
			 * response.setDateHeader("Expires", 0);
			 * 
			 * response.setHeader("Content-disposition", "inline; filename=" +
			 * "cdvDisposition.csv");
			 * 
			 * ServletOutputStream sos = response.getOutputStream();
			 * 
			 * response.setHeader("Content-length", Integer
			 * .toString(data.length()));
			 * 
			 * sos.write(data.getBytes()); sos.close();
			 */

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Payments", collection);

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
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				result = detailPerformed(request, response,	"detailPaymentBatch");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} 
			else if (navigation.equalsIgnoreCase("preconfirm") || navigation.equalsIgnoreCase("confirm")) {
				result = confirmPerformed(request, response);
			} 
			
			else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchPaymentBatch");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupPaymentBatch");
			} 
			else if (navigation.equalsIgnoreCase("exportbank")){
				result = exportBankPerformed(request, response,
				"detailPaymentBatch");
			}
			else {
				result = searchPerformed(request, response,
						"searchPaymentBatch");
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
