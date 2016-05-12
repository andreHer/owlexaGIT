package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
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
import com.ametis.cms.datamodel.Broker;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.ContactPerson;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ContactPersonService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ContactPersonForm;

// imports+ 
// imports- 
/**
 * ContactPerson is a mapping of contact_person Table.
 */
public class ContactPersonFormController extends SimpleFormController // extends+ 
// extends- 
{

    ContactPersonService contactPersonService;
    ResourceBundleMessageSource alertProperties;
    private SecurityService securityService;
    // foreign affairs
    ClientService clientService;
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
    public void setClientService(ClientService obj) {
        this.clientService = obj;
    }

    public ClientService getClientService() {
        return this.clientService;
    }
    MemberGroupService memberGroupService;

    public void setMemberGroupService(MemberGroupService obj) {
        this.memberGroupService = obj;
    }

    public MemberGroupService getMemberGroupService() {
        return this.memberGroupService;
    }
    com.ametis.cms.service.ProviderService providerService;

    public void setProviderService(com.ametis.cms.service.ProviderService obj) {
        this.providerService = obj;
    }

    public com.ametis.cms.service.ProviderService getProviderService() {
        return this.providerService;
    }

    // -- foreign affairs end
    public void setContactPersonService(ContactPersonService object) {
        this.contactPersonService = object;
    }

    public ContactPersonService getContactPersonService() {
        return this.contactPersonService;
    }
    // generate by default
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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

    public ContactPersonFormController() {
        setSessionForm(true);
        setValidateOnBinding(true);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        Object result = null;
        ContactPersonForm tmp = null;
        Integer index 	= WebUtil.getParameterInteger(request, "index");
        Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
        Integer contactPersonId = WebUtil.getParameterInteger(request, "contactPersonId");
        Integer providerId = WebUtil.getParameterInteger(request, "providerId");
        String refId = WebUtil.getParameterString(request, "refId", "");
        System.out.println("REFF ID : "+refId);
        Integer clientId = WebUtil.getParameterInteger(request, "clientId");
        String searchby = WebUtil.getParameterString(request, "searchby", "");
        String tipe = WebUtil.getParameterString(request, "tipe", "");
        String previousURL = WebUtil.getParameterString(request, "previousURL", "");
        String navigation = WebUtil.getParameterString(request, "navigation", "");

        
        /*
         * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
         * primary key nya
         */
        if (contactPersonId != null) {
            java.io.Serializable pkey = contactPersonId;
            ContactPerson object = contactPersonService.get(pkey);

            if (object != null) { // edit object

                tmp = new ContactPersonForm(object);
                // foreign affairs

                
                if (object.getClientId() != null){
                	tmp.setClientId("" + object.getClientId().getClientId());
                }
              
                if (object.getMemberGroupId() != null){
                	tmp.setMemberGroupId(""
                        + object.getMemberGroupId().getMemberGroupId());
                }


                if (object.getProviderId() != null){

                	tmp.setProviderId(""
                        + object.getProviderId().getProviderId());
                }

                tmp.setTipe(tipe);
                tmp.setPreviousURL(previousURL);
                tmp.setRefId(refId);

                // -- foreign affairs end
            } else {// object tidak ditemukan ?? anggap kita mau bikin baru !
                tmp = new ContactPersonForm();
                // foreign affairs

                tmp.setTipe(tipe);
                tmp.setPreviousURL(previousURL);
                tmp.setRefId(refId);

                
//                Integer clientId = WebUtil.getParameterInteger(request, "clientId");

                if (clientId != null) {
                    Client forClass = new Client();
                    forClass.setClientId(clientId);
                    tmp.setClientId("" + clientId);

                    Client client = this.clientService.get(clientId);
                    tmp.getContactPerson().setClientId(client);
                }

                if (memberGroupId != null) {
                    MemberGroup forClass = new MemberGroup();
                    forClass.setMemberGroupId(memberGroupId);
                    tmp.setMemberGroupId("" + memberGroupId);

                    MemberGroup memberGroup = this.memberGroupService.get(memberGroupId);
                    tmp.getContactPerson().setMemberGroupId(memberGroup);
                }

                if (providerId != null) {
                    Provider forClass = new Provider();
                    forClass.setProviderId(providerId);
                    tmp.setProviderId("" + providerId);

                    Provider provider = this.providerService.get(providerId);
                    tmp.getContactPerson().setProviderId(provider);
                 
//                    request.setAttribute("providerId", providerId);
                } else {
                  
                }


                // -- foreign affairs end
            }
        } // mau edit end
        else { // bikin baru
            tmp = new ContactPersonForm();
            // foreign affairs

            tmp.setTipe(tipe);
            tmp.setPreviousURL(previousURL);
            tmp.setRefId(refId);

            // -- foreign affairs end

        }
        
        String breadcrumb= "";
        
        if (navigation.equalsIgnoreCase("listprovider")) {
            breadcrumb = "<a href=\"contactperson?navigation="+navigation+"&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Contact Person</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Contact Person";
            request.setAttribute("breadcrumb", breadcrumb);
        }
        if (navigation.equalsIgnoreCase("listgroup")) {
            breadcrumb = "<a href=\"contactperson?navigation="+navigation+"&memberGroupId="+memberGroupId+"\" class=\"linkbreadcrumb\">List Group Contact Person</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Contact Person";
            request.setAttribute("breadcrumb", breadcrumb);
        }
        if (navigation.equalsIgnoreCase("listclient")) {
            breadcrumb = "<a href=\"contactperson?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Contact Person</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Contact Person";
        }
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("clientId", clientId);
		request.setAttribute("navigation", navigation);
		request.setAttribute("providerId", providerId);
		request.setAttribute("memberGroupId", memberGroupId);
		request.setAttribute("refId", refId);
		System.out.println("NAVI FORM : "+navigation);

        result = tmp;
        return result;
    }

    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
            throws Exception {

    	Integer index = WebUtil.getParameterInteger(request, "index");
        Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
        Integer refId = WebUtil.getParameterInteger(request, "refId");
        String searchby = WebUtil.getParameterString(request, "searchby", "");
        String previousURL = WebUtil.getParameterString(request, "previousURL", "");
        Integer providerId = WebUtil.getParameterInteger(request, "providerId");
        String navigation = WebUtil.getParameterString(request, "navigation", "");
        String navigation2 = WebUtil.getAttributeString(request, "navigation", "");
        Integer clientId = WebUtil.getParameterInteger(request, "clientId");

        System.out.println("NAVI 1 : "+navigation);
        System.out.println("NAVI 2 : "+navigation2);

        ContactPersonForm contactPersonForm = (ContactPersonForm) command;
        ContactPerson contactPerson = contactPersonForm.getContactPerson();

        String breadcrumb= "";
        
        if (navigation.equalsIgnoreCase("listprovider")) {
            breadcrumb = "<a href=\"contactperson?navigation="+navigation+"&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Contact Person</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Contact Person";
        }
        if (navigation.equalsIgnoreCase("listgroup")) {
            breadcrumb = "<a href=\"contactperson?navigation="+navigation+"&memberGroupId="+memberGroupId+"\" class=\"linkbreadcrumb\">List Group Contact Person</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Contact Person";
        }  
        if (navigation.equalsIgnoreCase("listclient")) {
            breadcrumb = "<a href=\"contactperson?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Contact Person</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Contact Person";
        }
        
        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("index", index);
        request.setAttribute("memberGroupId", memberGroupId);
        //request.setAttribute("refId", refId);
        request.setAttribute("searchby", searchby);
        request.setAttribute("previousURL", previousURL);
        request.setAttribute("providerId", providerId);
        request.setAttribute("navigation", navigation);
        request.setAttribute("clientId", clientId);
        
//		errors.setNestedPath("contactPerson");
//		getValidator().validate(contactPerson, errors);
//		errors.setNestedPath("");
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
        return model;
    }

    protected ModelAndView onSubmit(
            HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
            throws Exception {

        ContactPersonForm contactPersonForm = (ContactPersonForm) command;
        Integer index = WebUtil.getParameterInteger(request, "index");
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
		Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String searchby = WebUtil.getParameterString(request, "searchby", "");
		Integer clientId = WebUtil.getParameterInteger(request, "clientId");


        ContactPerson res = null;
        String alertMsg = "";
        try {
            // foreign affairs
        	System.out.println("REFF ID : "+contactPersonForm.getRefId());
            if (contactPersonForm.getTipe() != null) {
            	if(contactPersonForm.getRefId()!=null && !contactPersonForm.getRefId().equals("")){
	                if (contactPersonForm.getTipe().equalsIgnoreCase("group")) {
	                    MemberGroup memberGroup = new MemberGroup();
	                    memberGroup.setMemberGroupId(Integer.valueOf(contactPersonForm.getRefId()));
	                    contactPersonForm.getContactPerson().setMemberGroupId(memberGroup);
	                }
	                if (contactPersonForm.getTipe().equalsIgnoreCase("broker")) {
	                    Broker broker = new Broker();
	                    broker.setBrokerId(Integer.valueOf(contactPersonForm.getRefId()));
	                    contactPersonForm.getContactPerson().setBrokerId(broker);
	                }
	                if (contactPersonForm.getTipe().equalsIgnoreCase("client")) {
	                    Client client = new Client();
	                    client.setClientId(Integer.valueOf(contactPersonForm.getRefId()));
	                    contactPersonForm.getContactPerson().setClientId(client);
	                }
	                if (contactPersonForm.getTipe().equalsIgnoreCase("provider")) {
		        		 Provider provider = new Provider();
		                 provider.setProviderId(Integer.valueOf(contactPersonForm.getRefId()));
		                 contactPersonForm.getContactPerson().setProviderId(provider);
	                } 
            	}
            	
                String breadcrumb= "";
                    
                if (navigation.equalsIgnoreCase("listprovider")) {
                    breadcrumb = "<a href=\"contactperson?navigation="+navigation+"&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Contact Person</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Contact Person";
                }
                if (navigation.equalsIgnoreCase("listgroup")) {
                    breadcrumb = "<a href=\"contactperson?navigation="+navigation+"&memberGroupId="+memberGroupId+"\" class=\"linkbreadcrumb\">List Group Contact Person</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Contact Person";
                }
                if (navigation.equalsIgnoreCase("listclient")) {
                    breadcrumb = "<a href=\"contactperson?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Contact Person</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Contact Person";
                }
                request.setAttribute("breadcrumb", breadcrumb);
            }
            
            System.out.println("PAY OFF : "+contactPersonForm.getPaymentOfficer());

            // -- foreign affairs end
            String notyet = WebUtil.getParameterString(request, "notyet", "");
            if (notyet.equals("true")) {
                return showForm(request, response, errors);
            }


            ActionUser user = securityService.getActionUser(request);

            if (contactPersonForm.isNewContactPerson()) {
                res = contactPersonService.create(contactPersonForm.getContactPerson(), user);

                if (res != null) {
                    alertMsg = alertProperties.getMessage("success.create.contactperson", null, "success", Locale.getDefault());
                } else {
                    alertMsg = alertProperties.getMessage("fail.create.contactperson", null, "fail", Locale.getDefault());
                }
            } else {
                res = contactPersonService.update(contactPersonForm.getContactPerson(), user);

                if (res != null) {
                    alertMsg = alertProperties.getMessage("success.update.contactperson", null, "success", Locale.getDefault());
                } else {
                    alertMsg = alertProperties.getMessage("fail.update.contactperson", null, "fail", Locale.getDefault());
                }

            }
        } catch (Exception ex) {
            if (contactPersonForm.isNewContactPerson()) {
                request.setAttribute("alert", alertProperties.getMessage("fail.create.contactperson", null, "fail", Locale.getDefault()) + " " + ex.getMessage());
            } else {
                request.setAttribute("alert", alertProperties.getMessage("fail.update.contactperson", null, "fail", Locale.getDefault()) + " " + ex.getMessage());
            }
            ex.printStackTrace();
            return showForm(request, response, errors);
        }
        
        String breadcrumb ="";
        
        if (navigation.equalsIgnoreCase("listprovider")) {
            breadcrumb = "<a href=\"contactperson?navigation="+navigation+"&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Contact Person</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Contact Person";
        }
        if (navigation.equalsIgnoreCase("listgroup")) {
            breadcrumb = "<a href=\"contactperson?navigation="+navigation+"&memberGroupId="+memberGroupId+"\" class=\"linkbreadcrumb\">List Group Contact Person</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Contact Person";
        }
        if (navigation.equalsIgnoreCase("listclient")) {
            breadcrumb = "<a href=\"contactperson?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Group Contact Person</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Contact Person";
        }
        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("providerId", providerId);
        request.setAttribute("navigation", navigation);
        request.setAttribute("clientId", clientId);
        request.setAttribute("memberGroupId", memberGroupId);


        
        return new ModelAndView(new RedirectView(contactPersonForm.getPreviousURL() + "&alert=" + alertMsg));
//		return super.onSubmit(request, response, command, errors);
    }

    protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(req, binder);
        CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"), true);
        binder.registerCustomEditor(Date.class, cde);
        CustomNumberEditor num = new CustomNumberEditor(Number.class, true);
        binder.registerCustomEditor(Number.class, num);
    }
// class+ 
// class- 
}
