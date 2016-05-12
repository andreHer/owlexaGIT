package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Document;
import com.ametis.cms.datamodel.DocumentCategory;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.DocumentCategoryService;
import com.ametis.cms.service.DocumentService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.StringUtil;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.DocumentForm;

// imports+

// imports-

/**
 * Document is a mapping of document Table.
 */
public class DocumentFormController extends SimpleFormController
// extends+

// extends-
{

	DocumentService documentService;
	ResourceBundleMessageSource alertProperties;
	private SecurityService securityService;
	private CaseService caseService;
	// foreign affairs

	ClaimService claimService;

	
	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}

	public void setClaimService(ClaimService obj) {
		this.claimService = obj;
	}

	public ClaimService getClaimService() {
		return this.claimService;
	}

	DocumentCategoryService documentCategoryService;

	public void setDocumentCategoryService(DocumentCategoryService obj) {
		this.documentCategoryService = obj;
	}

	public DocumentCategoryService getDocumentCategoryService() {
		return this.documentCategoryService;
	}

	MemberService memberService;

	public void setMemberService(MemberService obj) {
		this.memberService = obj;
	}

	public MemberService getMemberService() {
		return this.memberService;
	}

	// -- foreign affairs end

	public void setDocumentService(DocumentService object) {
		this.documentService = object;
	}

	public DocumentService getDocumentService() {
		return this.documentService;
	}

	// generate by default
	private UserService actionuserService;

	public UserService getActionUserService() {
		return actionuserService;
	}

	public void setActionUserService(UserService userService) {
		this.actionuserService = userService;
	}

	public void setPropertiesUtil(ResourceBundleMessageSource object) {
		this.alertProperties = object;
	}

	public ResourceBundleMessageSource getPropertiesUtil() {
		return this.alertProperties;
	}

	public void setSecurityService(SecurityService object) {
		this.securityService = object;
	}

	public SecurityService getSecurityService() {
		return this.securityService;
	}

	public DocumentFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		DocumentForm tmp = null;
		Integer documentId = WebUtil.getParameterInteger(request, "documentId");
		Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");
		Integer claimId = WebUtil.getParameterInteger(request, "claimId");
		Integer index = WebUtil.getParameterInteger(request, "index");
		Integer caseId = WebUtil.getParameterInteger(request, "caseId");
		String searchby = WebUtil.getParameterString(request, "searchby", "");
		String navigation = WebUtil.getParameterString(request, "navigation", "");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (documentId != null) {
			java.io.Serializable pkey = documentId;
			Document object = documentService.get(pkey);

			if (object != null) { // edit object

				tmp = new DocumentForm(object);
				// foreign affairs

				tmp.setClaimId("" + object.getClaimId().getClaimId());

				tmp.setDocumentCategoryId(""
						+ object.getDocumentCategoryId()
								.getDocumentCategoryId());

				tmp.setMemberId("" + object.getMemberId().getMemberId());

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new DocumentForm();
				// foreign affairs

				if (claimId != null) {
					Claim forClass = new Claim();
					forClass.setClaimId(claimId);
					

					Claim claim = this.claimService.get(claimId);
					
					if (claim != null){
						tmp.getDocument().setClaimId(claim);
						tmp.setClaimId("" + claimId);
					}
				} else {
					
				}

				Integer documentCategoryId = WebUtil.getParameterInteger(
						request, "documentCategoryId");

				if (documentCategoryId != null) {
					DocumentCategory forClass = new DocumentCategory();
					forClass.setDocumentCategoryId(documentCategoryId);
					

					DocumentCategory documentCategory = this.documentCategoryService
							.get(documentCategoryId);
					if (documentCategory != null){
						tmp.getDocument().setDocumentCategoryId(documentCategory);
						tmp.setDocumentCategoryId("" + documentCategoryId);
					}
				} else {
					
				}

				Integer memberId = WebUtil.getParameterInteger(request,
						"memberId");

				if (memberId != null) {
					Member forClass = new Member();
					forClass.setMemberId(memberId);
					

					Member member = this.memberService.get(memberId);
					if (member != null){
						tmp.getDocument().setMemberId(member);
						tmp.setMemberId("" + memberId);
					}
				} else {
					
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new DocumentForm();
			// foreign affairs

//			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
//			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			if (claimId != null) {
				Claim forClass = new Claim();
				forClass.setClaimId(claimId);
				tmp.setClaimId("" + claimId);

				Claim claim = this.claimService.get(claimId);
				tmp.getDocument().setClaimId(claim);
				
				if (claim != null){
					tmp.setClaimNumber(claim.getClaimNumber());
				}
			}
			
			if (caseId != null) {
				Case forClass = new Case();
				forClass.setCaseId(claimId);
				tmp.setCaseId("" + claimId);

				Case theCase = this.caseService.get(caseId);
				tmp.getDocument().setCaseId(theCase);
				
				if (theCase != null){
					tmp.setClaimNumber(theCase.getCaseNumber());
				}
			}

			Integer documentCategoryId = WebUtil.getParameterInteger(request,
					"documentCategoryId");

			if (documentCategoryId != null) {
				DocumentCategory forClass = new DocumentCategory();
				forClass.setDocumentCategoryId(documentCategoryId);
				tmp.setDocumentCategoryId("" + documentCategoryId);

				DocumentCategory documentCategory = this.documentCategoryService
						.get(documentCategoryId);
				tmp.getDocument().setDocumentCategoryId(documentCategory);
			} else {
				
			}

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			if (memberId != null) {
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId("" + memberId);

				Member member = this.memberService.get(memberId);
				tmp.getDocument().setMemberId(member);
			} else {
				
			}

			// -- foreign affairs end

		}
		
		String breadcrumb = "<a href=\"document-form?navigation=tambah&arah=&batchClaimId="+batchClaimId+"&tipe=&claimId="+claimId+"&index="+index+"&documentId=&searchtext=&searchby="+searchby+"\" class=\"linkbreadcrumb\">Upload Document</a>";
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("caseId", caseId);
		request.setAttribute("navigation", navigation);
		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		DocumentForm documentForm = (DocumentForm) command;
		Document document = documentForm.getDocument();
		
		Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");
		Integer claimId = WebUtil.getParameterInteger(request, "claimId");
		Integer index = WebUtil.getParameterInteger(request, "index");
		Integer caseId = WebUtil.getParameterInteger(request, "caseId");
		String searchby = WebUtil.getParameterString(request, "searchby", "");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		
		String breadcrumb="";
		breadcrumb = "<a href=\"document-form?navigation=tambah&arah=&batchClaimId="+batchClaimId+"&tipe=&claimId="+claimId+"&index="+index+"&documentId=&searchtext=&searchby="+searchby+"\" class=\"linkbreadcrumb\">Upload Document</a>";
		
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("batchClaimId", batchClaimId);
		request.setAttribute("claimId", claimId);
		request.setAttribute("index", index);
		request.setAttribute("searchby", searchby);
		request.setAttribute("caseId", caseId);
		request.setAttribute("navigation", navigation);

		// errors.setNestedPath("document");
		// getValidator().validate(document, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		/**
		 * ini dipake buat populate data - data yang dibutuhkan contoh : Problem
		 * membutuhkan ProblemCategory nah fungsi method ini yaitu untuk
		 * populate list problem category ke jsp nanti biar bisa ditangkep sama
		 * jspnya
		 * 
		 * contoh : Collection pc = pcontroller.searchPC();
		 * 
		 * model.addObject("pcbeans", pc);
		 * 
		 */

		try {
			String batchClaimId = WebUtil.getParameterString(request,
					"batchClaimId", "");
			String claimId = WebUtil.getParameterString(request, "claimId", "");
			Collection<DocumentCategory> docList = documentCategoryService
					.getAll();
			model.put("documentCategories", docList);
			model.put("batchClaimId", batchClaimId);
			model.put("claimId", claimId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		DocumentForm documentForm = (DocumentForm) command;
		
		Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");
//		Integer claimId = WebUtil.getParameterInteger(request, "claimId");
		Integer index = WebUtil.getParameterInteger(request, "index");
//		Integer caseId = WebUtil.getParameterInteger(request, "caseId");
		String searchby = WebUtil.getParameterString(request, "searchby", "");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		
		Document res = null;
		String alertMsg = "";
		String claimId = "";
		String memberId = "";
		String caseId = "";
		String nav = "list";
		try {
			// foreign affairs


			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
//			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}



			ActionUser user = securityService.getActionUser(request);

			//System.out.println("Creating document for claim = " + documentForm.getDocument().getClaimId().getClaimId());
			if (documentForm.isNewDocument()) {
				
				Collection<Document> documentList = new Vector<Document>();
				Collection<byte[]> contentList = new Vector<byte[]>();
				
				if (documentForm.getFile1() != null && documentForm.getFile1().getSize() > 0){
					Document doc = new Document();
					if (documentForm.getDocument().getClaimId() != null){
						doc.setClaimId(documentForm.getDocument().getClaimId());						
					}
					if (documentForm.getDocument().getCaseId() != null){
						doc.setCaseId(documentForm.getDocument().getCaseId());						
					}
					if (documentForm.getDocument().getMemberId() != null){
						doc.setMemberId(documentForm.getDocument().getMemberId());						
					}
					if (documentForm.getDocumentCategoryId1() != null){
						DocumentCategory category = new DocumentCategory();
						category.setDocumentCategoryId(Integer.valueOf(documentForm.getDocumentCategoryId1()));
						doc.setDocumentCategoryId(category);
					}
					StringTokenizer tokenizer = new StringTokenizer(documentForm.getFile1().getOriginalFilename(),".");
					String ext = "";
					
					while(tokenizer.hasMoreTokens()){
						ext = tokenizer.nextToken();
					}
					
					String url = StringUtil.hash(System.currentTimeMillis()+"") + "." + ext;
					doc.setDocumentUrl(url);
					doc.setDeletedStatus(0);
					
					
					documentList.add(doc);
					contentList.add(documentForm.getFile1().getBytes());
				}
				if (documentForm.getFile2() != null && documentForm.getFile2().getSize() > 0){
					Document doc = new Document();
					if (documentForm.getDocument().getClaimId() != null){
						doc.setClaimId(documentForm.getDocument().getClaimId());						
					}
					if (documentForm.getDocument().getCaseId() != null){
						doc.setCaseId(documentForm.getDocument().getCaseId());						
					}
					if (documentForm.getDocument().getMemberId() != null){
						doc.setMemberId(documentForm.getDocument().getMemberId());						
					}
					if (documentForm.getDocumentCategoryId2() != null){
						DocumentCategory category = new DocumentCategory();
						category.setDocumentCategoryId(Integer.valueOf(documentForm.getDocumentCategoryId2()));
						doc.setDocumentCategoryId(category);
					}
					StringTokenizer tokenizer = new StringTokenizer(documentForm.getFile2().getOriginalFilename(),".");
					String ext = "";
					
					while(tokenizer.hasMoreTokens()){
						ext = tokenizer.nextToken();
					}
					
					
					String url = StringUtil.hash(System.currentTimeMillis()+"") + "." + ext;
					doc.setDocumentUrl(url);
					doc.setDeletedStatus(0);
					
					
					documentList.add(doc);
					contentList.add(documentForm.getFile2().getBytes());	
				}
				if (documentForm.getFile3() != null && documentForm.getFile3().getSize() > 0){
					Document doc = new Document();
					if (documentForm.getDocument().getClaimId() != null){
						doc.setClaimId(documentForm.getDocument().getClaimId());						
					}
					if (documentForm.getDocument().getCaseId() != null){
						doc.setCaseId(documentForm.getDocument().getCaseId());						
					}
					if (documentForm.getDocument().getMemberId() != null){
						doc.setMemberId(documentForm.getDocument().getMemberId());						
					}
					if (documentForm.getDocumentCategoryId3() != null){
						DocumentCategory category = new DocumentCategory();
						category.setDocumentCategoryId(Integer.valueOf(documentForm.getDocumentCategoryId3()));
						doc.setDocumentCategoryId(category);
					}
					StringTokenizer tokenizer = new StringTokenizer(documentForm.getFile3().getOriginalFilename(),".");
					String ext = "";
					
					while(tokenizer.hasMoreTokens()){
						ext = tokenizer.nextToken();
					}
					
					String url = StringUtil.hash(System.currentTimeMillis()+"") + "." + ext;
					doc.setDocumentUrl(url);
					doc.setDeletedStatus(0);
					
					
					documentList.add(doc);
					contentList.add(documentForm.getFile3().getBytes());
				}
				if (documentForm.getFile4() != null && documentForm.getFile4().getSize() > 0){
					Document doc = new Document();
					if (documentForm.getDocument().getClaimId() != null){
						doc.setClaimId(documentForm.getDocument().getClaimId());						
					}
					if (documentForm.getDocument().getCaseId() != null){
						doc.setCaseId(documentForm.getDocument().getCaseId());						
					}
					if (documentForm.getDocument().getMemberId() != null){
						doc.setMemberId(documentForm.getDocument().getMemberId());						
					}
					if (documentForm.getDocumentCategoryId4() != null){
						DocumentCategory category = new DocumentCategory();
						category.setDocumentCategoryId(Integer.valueOf(documentForm.getDocumentCategoryId4()));
						doc.setDocumentCategoryId(category);
					}
					StringTokenizer tokenizer = new StringTokenizer(documentForm.getFile4().getOriginalFilename(),".");
					String ext = "";
					
					while(tokenizer.hasMoreTokens()){
						ext = tokenizer.nextToken();
					}
					
					String url = StringUtil.hash(System.currentTimeMillis()+"") + "." + ext;
					doc.setDocumentUrl(url);
					doc.setDeletedStatus(0);
					
					
					documentList.add(doc);
					contentList.add(documentForm.getFile4().getBytes());
				}
				if (documentForm.getFile5() != null && documentForm.getFile5().getSize() > 0){
					Document doc = new Document();
					if (documentForm.getDocument().getClaimId() != null){
						doc.setClaimId(documentForm.getDocument().getClaimId());
						
					}
					if (documentForm.getDocument().getCaseId() != null){
						doc.setCaseId(documentForm.getDocument().getCaseId());
						nav = "listcase";
					}
					if (documentForm.getDocument().getMemberId() != null){
						doc.setMemberId(documentForm.getDocument().getMemberId());						
					}
					if (documentForm.getDocumentCategoryId5() != null){
						DocumentCategory category = new DocumentCategory();
						category.setDocumentCategoryId(Integer.valueOf(documentForm.getDocumentCategoryId5()));
						doc.setDocumentCategoryId(category);
					}
					StringTokenizer tokenizer = new StringTokenizer(documentForm.getFile5().getOriginalFilename(),".");
					String ext = "";
					
					while(tokenizer.hasMoreTokens()){
						ext = tokenizer.nextToken();
					}
					
					String url = StringUtil.hash(System.currentTimeMillis()+"") + "." + ext;
					doc.setDocumentUrl(url);
					doc.setDeletedStatus(0);
					
					
					documentList.add(doc);
					contentList.add(documentForm.getFile5().getBytes());
				}

				
				ActionResult resu = documentService.create(documentList,contentList, user);				
				

				if (resu != null) {
					Document resDoc = (Document)resu.getResultObject();
					if (resDoc != null){
						if (resDoc.getClaimId() != null){
							claimId = resDoc.getClaimId().getClaimId().toString();							
						}
						if (resDoc.getMemberId() != null){
							memberId = resDoc.getMemberId().getMemberId().toString();
						}
						if (resDoc.getCaseId() != null){
							caseId = resDoc.getCaseId().getCaseId().toString();
							nav = "listcase";
						}
					}
					alertMsg = alertProperties.getMessage(
							"success.create.document", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.document", null, "fail", Locale
									.getDefault());
				}
			} else {
				res = documentService.update(documentForm.getDocument(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.document", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.document", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			if (documentForm.isNewDocument()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.document", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.document", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
		String breadcrumb="";
		breadcrumb = "<a href=\"document-form?navigation=tambah&arah=&batchClaimId="+batchClaimId+"&tipe=&claimId="+claimId+"&index="+index+"&documentId=&searchtext=&searchby="+searchby+"\" class=\"linkbreadcrumb\">Upload Document</a>";
		
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("batchClaimId", batchClaimId);
		request.setAttribute("claimId", claimId);
		request.setAttribute("index", index);
		request.setAttribute("searchby", searchby);
		request.setAttribute("caseId", caseId);
		request.setAttribute("navigation", navigation);
		
		return new ModelAndView(new RedirectView("document" + "?navigation="+nav+"&claimId="+claimId+"&caseId="+caseId+"&memberId="+memberId+"&alert="+ alertMsg));
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"yyyy/MM/dd"), true);
		binder.registerCustomEditor(Date.class, cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class, true);
		binder.registerCustomEditor(Number.class, num);
	}
	// class+

	// class-
}
