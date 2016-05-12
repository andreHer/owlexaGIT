package com.ametis.cms.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.Document;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.DocumentService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.StringUtil;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+

// imports-

/**
 * Document is a servlet controller for document Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class DocumentController implements Controller

// extends+

// extends-
{

	private DocumentService documentService;
	private SecurityService securityService;
	private CaseService caseService;

	private UserService actionuserService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	
	private ClaimService claimService;
	
	
	

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

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public DocumentService getDocumentService() {
		return this.documentService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer documentId = WebUtil.getParameterInteger(request,
					"documentId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = documentId;

			ActionUser user = securityService.getActionUser(request);

			Document res = documentService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.document", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.document", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchDocument");
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

	public ModelAndView previewPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer documentId = WebUtil.getParameterInteger(request,
					"documentId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = documentId;

			
			Document res = documentService.get(pkey);

			result = new ModelAndView("previewDocument");
			
			if (res != null) {
				StringTokenizer tokenizer = new StringTokenizer(res.getDocumentUrl(),".");
				String ext = "";
				
				while(tokenizer.hasMoreTokens()){
					ext = tokenizer.nextToken();
				}
				String cacheFile = StringUtil.hash(System.currentTimeMillis()+"")+System.currentTimeMillis()+"."+ext;
				String tipe = "image";
				
				if (ext != null){
					if (ext.equalsIgnoreCase("pdf")){
						tipe = "pdf";
					}
				}
				String path = request.getSession().getServletContext().getRealPath("/cache/");
				
				byte[] content = documentService.getDocumentContent(res);
				
				try {
					String separator = System.getProperty("file.separator");
					FileOutputStream outstream = new FileOutputStream(path+separator+cacheFile);
					outstream.write(content);
					outstream.flush();
					outstream.close();
					
					result.addObject("tipe", tipe);
					result.addObject("fileName", cacheFile);
				}
				catch (Exception e){
					e.printStackTrace();
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
			Integer documentId = WebUtil.getParameterInteger(request,
					"documentId");

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
			java.io.Serializable pkey = documentId;
			Document object = documentService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties
						.getMessage("not.found.document", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("document", object);
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
			

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			
			String batchClaimId  = WebUtil.getParameterString(request, "batchClaimId", "");
//			String claimId = WebUtil.getParameterString(request, "claimId", "");
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			String memberId = WebUtil.getParameterString(request, "memberId", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (navigation.equalsIgnoreCase("listcase") || navigation.equalsIgnoreCase("golistcase") ){
				location = "listCaseDocument";
			}

			result = new ModelAndView(location);
			
			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("list")) {

				if (searchby != null) {
					if (searchby.equalsIgnoreCase("documentNumber")) {
						vLikeP.add("documentNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("documentUrl")) {
						vLikeP.add("documentUrl");
						vLikeQ.add(searchtext);
					}
				}

			}

			if (claimId != null){
				vEqP.add("claimId.claimId");
				vEqQ.add(Integer.valueOf(claimId));
			}
			if (batchClaimId != null && !batchClaimId.equalsIgnoreCase("")){
				vEqP.add("claimId.batchClaimId.batchClaimId");
				vEqQ.add(Integer.valueOf(batchClaimId));
			}
			
			if (caseId != null){
				vEqP.add("caseId.caseId");
				vEqQ.add(Integer.valueOf(caseId));
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

			count = documentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"Document.ClaimId", "Document.DocumentCategoryId",
					"Document.MemberId",
			// foreign affairs end
			};
			collection = documentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = documentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
			}
			
			Claim claimObject = null;
			
			if(claimId != null){
				try
				{
				java.io.Serializable claimkey = claimId;
				System.out.println("claim id : "+claimId);
				String[] claimRequired = {"Claim.BatchClaimId","Claim.MemberId",
						"Claim.CaseCategoryId","Claim.MemberId.MemberGroupId","Claim.CaseId",
						"Claim.DiagnosisId","Claim.Diagnosis2Id","Claim.Diagnosis3Id","Claim.CobClaimReferenceId","Claim.BatchClaimId.ClaimCurrency"};
				claimObject = claimService.get(claimkey, claimRequired);
				
				}
				catch (Exception ex)
				{
					//System.out.println("claim object : "+claimObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
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

			result.addObject("Documents", collection);
			result.addObject("claim", claimObject);
			result.addObject("myCase", caseObject);

			request.setAttribute("batchClaimId", batchClaimId);
			request.setAttribute("claimId", claimId);
			request.setAttribute("caseId", caseId);
			request.setAttribute("memberId", memberId);
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
	public ModelAndView searchBatchDocumentPerformed(HttpServletRequest request,
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
			
			String batchClaimId  = WebUtil.getParameterString(request, "batchClaimId", "");
			String claimId = WebUtil.getParameterString(request, "claimId", "");
			String memberId = WebUtil.getParameterString(request, "memberId", "");
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
			if (navigation.equals("gosearch") || navigation.equals("golookup")) {

				if (searchby != null) {
					if (searchby.equalsIgnoreCase("documentNumber")) {
						vLikeP.add("documentNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("documentUrl")) {
						vLikeP.add("documentUrl");
						vLikeQ.add(searchtext);
					}
				}

			}

			if (claimId != null && !claimId.equalsIgnoreCase("")){
				vEqP.add("claimId.claimId");
				vEqQ.add(Integer.valueOf(claimId));
			}
			if (batchClaimId != null && !batchClaimId.equalsIgnoreCase("")){
				vEqP.add("claimId.batchClaimId.batchClaimId");
				vEqQ.add(Integer.valueOf(batchClaimId));
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

			count = documentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"Document.ClaimId", "Document.DocumentCategoryId",
					"Document.MemberId",
			// foreign affairs end
			};
			collection = documentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = documentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Documents", collection);

			request.setAttribute("batchClaimId", batchClaimId);
			request.setAttribute("claimId", claimId);
			request.setAttribute("memberId", memberId);
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
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response, "detailDocument");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchDocument");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupDocument");
			} 
			else if (navigation.equalsIgnoreCase("listbatch") || navigation.equalsIgnoreCase("golistbatch") ) {
				result = searchBatchDocumentPerformed(request, response, "listBatchDocument");
			} 
			else if (navigation.equalsIgnoreCase("preview")) {
					result = previewPerformed(request, response);
			}
			else {
				result = searchPerformed(request, response, "searchDocument");
				String claimId = request.getParameter("claimId");
				breadcrumb = "<a href=\"claim?navigation=detail&claimId="
						+ claimId
						+ "\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Claim Document";
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
