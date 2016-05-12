
package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CardType;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.service.CardTypeService;
import com.ametis.cms.service.MemberElectronicCardService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.MemberElectronicCardForm;



// imports+ 

// imports- 


/**
 * MemberElectronicCard is a mapping of member_electronic_card Table.
*/
public class MemberElectronicCardFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	MemberElectronicCardService memberElectronicCardService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	private MemberService memberService;
	private CardTypeService cardTypeService;

	
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public CardTypeService getCardTypeService() {
		return cardTypeService;
	}
	public void setCardTypeService(CardTypeService cardTypeService) {
		this.cardTypeService = cardTypeService;
	}
	public void setMemberElectronicCardService (MemberElectronicCardService object){
	    this.memberElectronicCardService = object;
	}
	public MemberElectronicCardService getMemberElectronicCardService (){
	    return this.memberElectronicCardService;
	}
		// generate by default
		private UserService  actionuserService;
	public UserService getActionUserService() {
		return actionuserService;
	}
	public void setActionUserService(UserService userService) {
		this.actionuserService = userService;
	}
	
	
	public void setPropertiesUtil (ResourceBundleMessageSource object){
	    this.alertProperties = object;
	}
	public ResourceBundleMessageSource getPropertiesUtil (){
	    return this.alertProperties;
	}
	
	public void setSecurityService (SecurityService object){
	    this.securityService = object;
	}
	public SecurityService getSecurityService (){
	    return this.securityService;
	}

	
	public MemberElectronicCardFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		MemberElectronicCardForm tmp = null;
		Integer id = WebUtil.getParameterInteger (request,"id");
		Integer index = WebUtil.getParameterInteger (request,"index");
		Integer memberId = WebUtil.getParameterInteger(request, "memberId");
		String navigation = request.getParameter("navigation");

				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								id != null
				) {
						java.io.Serializable pkey = id;
						MemberElectronicCard object = memberElectronicCardService.get (pkey );

			 if (object != null){ // edit object

				tmp = new MemberElectronicCardForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new MemberElectronicCardForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new MemberElectronicCardForm();
					 // foreign affairs
			// -- foreign affairs end



		}
		if (memberId != null){
			Member member = memberService.get(memberId);
			if (member != null){
				tmp.setMemberId(member.getMemberId().toString());
				tmp.setMemberName(member.getFirstName());
				tmp.setMemberNumber(member.getCustomerPolicyNumber());
			}
		}
		
		request.setAttribute("navigation", navigation);
		request.setAttribute("index", index);
		request.setAttribute("id", id);
		request.setAttribute("memberId", memberId);
		
		String breadcrumb = "<a href=\"memberelectroniccard?navigation="+navigation+"&memberId="+memberId+"\" class=\"linkbreadcrumb\">List Member Electronic Card</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Member Card";
		request.setAttribute("breadcrumb", breadcrumb);
																																				
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {
		
		Integer id = WebUtil.getParameterInteger (request,"id");
		Integer index = WebUtil.getParameterInteger (request,"index");
		Integer memberId = WebUtil.getParameterInteger(request, "memberId");
		String navigation = request.getParameter("navigation");


		MemberElectronicCardForm memberElectronicCardForm = ( MemberElectronicCardForm ) command;
		MemberElectronicCard memberElectronicCard = memberElectronicCardForm.getMemberElectronicCard();
		
		request.setAttribute("navigation", navigation);
		request.setAttribute("id", id);
		request.setAttribute("index", index);
		request.setAttribute("memberId", memberId);
		
		String breadcrumb = "<a href=\"memberelectroniccard?navigation="+navigation+"&memberId="+memberId+"\" class=\"linkbreadcrumb\">List Member Electronic Card</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Member Card";
		request.setAttribute("breadcrumb", breadcrumb);

//		errors.setNestedPath("memberElectronicCard");
//		getValidator().validate(memberElectronicCard, errors);
//		errors.setNestedPath("");
 	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		try {
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			if (memberId != null){
				String[] cardEqParam = {"deletedStatus","memberId.memberId","cardStatus"};
				Object[] cardEqValue = {Integer.valueOf(0),memberId,Integer.valueOf(1)};
				
				int totalCard = memberElectronicCardService.getTotal(null,null,cardEqParam,cardEqValue);
				
				Collection<MemberElectronicCard> cardList = memberElectronicCardService.search(null,null,cardEqParam,cardEqValue,0,totalCard);
				
				model.put("cardList", cardList);
				
				Collection<CardType> cardTypeList = cardTypeService.getAll();
				model.put("cardTypeList", cardTypeList);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		MemberElectronicCardForm memberElectronicCardForm = ( MemberElectronicCardForm ) command;
		
		Integer memberId2 = WebUtil.getParameterInteger(request, "memberId");
		String navigation = request.getParameter("navigation");
		
		MemberElectronicCard res = null;
		String alertMsg="";
		String url = "";
		try {

			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			
			ActionUser user = securityService.getActionUser(request);
			Integer memberId = null;
			
			memberId = Integer.valueOf(memberElectronicCardForm.getMemberId());
			
			if (memberElectronicCardForm.isNewMemberElectronicCard ()) {
				res = memberElectronicCardService.create (memberElectronicCardForm.getMemberElectronicCard(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.memberelectroniccard",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.memberelectroniccard",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = memberElectronicCardService.update (memberElectronicCardForm.getMemberElectronicCard(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.memberelectroniccard",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.memberelectroniccard",null,"fail",Locale.getDefault());
				}

			}
			url = "memberelectroniccard"+"?alert="+alertMsg+"&navigation=list&memberId="+memberId;
		}catch (Exception ex) {
			if (memberElectronicCardForm.isNewMemberElectronicCard ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.memberelectroniccard",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.memberelectroniccard",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		String breadcrumb = "<a href=\"memberelectroniccard?navigation="+navigation+"&memberId="+memberId2+"\" class=\"linkbreadcrumb\">List Member Electronic Card</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Member Card";
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("memberId2", memberId2);

		return new ModelAndView(new RedirectView(url));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class,true);
		binder.registerCustomEditor(Number.class,num);
	}
// class+ 

// class- 

}
