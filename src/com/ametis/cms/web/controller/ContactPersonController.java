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
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.ContactPerson;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ContactPersonService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 
// imports- 
/**
 * ContactPerson is a servlet controller for contact_person Table. All you have
 * to do is to convert necessary data field to the named parameter
 */
public class ContactPersonController implements Controller // extends+ 
// extends- 
{

    private ContactPersonService contactPersonService;
    private SecurityService securityService;
    private MemberGroupService memberGroupService;
    private UserService userService;
    private ClientService clientService;
    private ProviderService providerService;
	private ResourceBundleMessageSource alertProperties;
    private Integer countSet;
    private Integer maxPercountSet;
    
    public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}
	
	private ActivityLogService logService;

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
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
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

    public void setContactPersonService(ContactPersonService contactPersonService) {
        this.contactPersonService = contactPersonService;
    }

    public ContactPersonService getContactPersonService() {
        return this.contactPersonService;
    }

    public ModelAndView deletePerformed(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView result = null;

        try {
            Integer contactPersonId = WebUtil.getParameterInteger(request, "contactPersonId");

          
            String location = "SearchContactPerson";
            java.io.Serializable pkey = contactPersonId;

            ActionUser user = securityService.getActionUser(request);

            ContactPerson res = contactPersonService.delete(pkey, user);

            if (res != null) {
                request.setAttribute("alert", alertProperties.getMessage("success.delete.contactperson", null, "success", Locale.getDefault()));
                
                if (res.getProviderId() != null){
                	location = "listProviderContactPerson";
                	request.setAttribute("providerId", res.getProviderId().getProviderId());

                	result = new ModelAndView(new RedirectView("contactperson?navigation=listprovider&providerId="+res.getProviderId().getProviderId()));                	
                	
                }
                if (res.getMemberGroupId() != null){
                	location = "listGroupContactPerson";
                	request.setAttribute("memberGroupId", res.getMemberGroupId().getMemberGroupId());

                	result = new ModelAndView(new RedirectView("contactperson?navigation=listgroup&memberGroupId="+res.getMemberGroupId().getMemberGroupId()));                	
                	
                }
                if (res.getClientId() != null){
                	location = "listClientContactPerson";
                	request.setAttribute("clientId", res.getClientId().getClientId());
                	result = new ModelAndView(new RedirectView("contactperson?navigation=listclient&clientId="+res.getClientId().getClientId()));                	
                	
                }
            } else {
                request.setAttribute("alert", alertProperties.getMessage("fail.delete.contactperson", null, "fail", Locale.getDefault()));
            }
            
        } catch (Exception e) {
            // nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
            e.printStackTrace();
            request.setAttribute("alert", alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));
            result = new ModelAndView("error");
        }
        return result;
    }

    public ModelAndView detailPerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
        ModelAndView result = null;

        try {
            Integer contactPersonId = WebUtil.getParameterInteger(request, "contactPersonId");

            Integer index = WebUtil.getParameterInteger(request, "index");
            String searchtext = WebUtil.getParameterString(request, "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby", "");
            String listnavigation = WebUtil.getParameterString(request, "listnavigation", "");
            /*
             * Pkey ini merupakan representasi dari primary key, misalkan hasil
             * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
             * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
             * primary key berarti tinggal bikin representasi primary key nya
             * saja .
             */

            result = new ModelAndView(location);
            java.io.Serializable pkey = contactPersonId;
            ContactPerson object = contactPersonService.get(pkey);

            if (object == null) {
                request.setAttribute("alert", alertProperties.getMessage("not.found.contactperson", null, "fail", Locale.getDefault()));
            }
            /*
             * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
             * ya .. ga pake 's' -adhit
             */

            result.addObject("contactPerson", object);
            result.addObject("index", index);
            result.addObject("searchtext", searchtext);
            result.addObject("searchby", searchby);
            result.addObject("listnavigation", listnavigation);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("alert", alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));

            result = new ModelAndView("error");
        }

        return result;
    }

    public ModelAndView searchPerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
        ModelAndView result = null;
        try {
            result = new ModelAndView(location);


            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");
            Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
            Integer providerId = WebUtil.getParameterInteger(request, "providerId");
            Integer clientId = WebUtil.getParameterInteger(request, "clientId");
            Integer brokerId = WebUtil.getParameterInteger(request, "brokerId");

            String navigation = WebUtil.getParameterString(request, "navigation", "");
            String listnavigation = WebUtil.getParameterString(request, "listnavigation", "");
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
            if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("listgroup") || navigation.equals("listclient")) {

                if (searchby != null) {
                    /**
                     * ini querynya disesuaikan dengan apa yang mau di search
                     * default nya gue bikin template search by semua field yang
                     * tipenya 'String' -adhit
                     */
                    if (searchby.equalsIgnoreCase("name")) {
                        vLikeP.add("name");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("telephone")) {
                        vLikeP.add("telephone");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("handphone")) {
                        vLikeP.add("handphone");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("email")) {
                        vLikeP.add("email");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("department")) {
                        vLikeP.add("department");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("jobPosition")) {
                        vLikeP.add("jobPosition");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("createdBy")) {
                        vLikeP.add("createdBy");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("modifiedBy")) {
                        vLikeP.add("modifiedBy");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("deletedBy")) {
                        vLikeP.add("deletedBy");
                        vLikeQ.add(searchtext);
                    }
                }

            }

            vEqP.add("deletedStatus");
            vEqQ.add(new Integer(0));
            
            if (memberGroupId != null){
                vEqP.add("memberGroupId.memberGroupId");
                vEqQ.add(memberGroupId);
            }
            
            if (brokerId != null){
                vEqP.add("brokerId.brokerId");
                vEqQ.add(brokerId);
            }
            
            if (clientId != null){
                vEqP.add("clientId.clientId");
                vEqQ.add(clientId);
            }
            if (providerId != null){
                vEqP.add("providerId.providerId");
                vEqQ.add(providerId);
            }
            

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);

            count = contactPersonService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

            String arah = WebUtil.getParameterString(request, "arah", "");

            if (index == null) {
                index = new Integer(1);
            }

            if (arah.equals("kanan")) {
                index = new Integer(index.intValue() + 1);
            } else if (arah.equals("kiri")) {
                index = new Integer(index.intValue() - 1);
            } else if (arah.equals("kiribgt")) {
                index = new Integer(1);
            } else if (arah.equals("kananbgt")) {
                index = new Integer(count / countSet.intValue() + 1);
            }

            if (index.compareTo(new Integer(1)) == new Integer(-1).intValue()) {
                index = new Integer(1);
            } else if (index.compareTo(new Integer(count / countSet.intValue() + 1)) == new Integer(1).intValue()) {
                index = new Integer(count / countSet.intValue() + 1);
            }

            rowsetint = (new Integer((index.intValue() - 1) * countSet.intValue())).intValue();
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

            String required[] = new String[]{
                // foreign affairs
                "ContactPerson.ClientId",
                "ContactPerson.MemberGroupId",
                "ContactPerson.ProviderId", // foreign affairs end
            };
            collection = contactPersonService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                    required, rowsetint, countSet.intValue());

            if (collection.size() <= 0) {
                index = new Integer(index.intValue() - 1);
                if (index.compareTo(new Integer(1)) == new Integer(-1).intValue()) {
                    index = new Integer(1);
                } else if (index.compareTo(new Integer(count / countSet.intValue() + 1)) == new Integer(1).intValue()) {
                    index = new Integer(count / countSet.intValue() + 1);
                }

                rowsetint = (new Integer((index.intValue() - 1) * countSet.intValue())).intValue();
                if (count % countSet.intValue() > 0) {
                    result.addObject("halAkhir", new Integer(count
                            / countSet.intValue() + 1));
                } else {
                    result.addObject("halAkhir", new Integer(count
                            / countSet.intValue()));
                }
                collection = contactPersonService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        required, rowsetint, countSet.intValue());
            }

            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("memberGroupId",memberGroupId);
            request.setAttribute("clientId", clientId);
            request.setAttribute("brokerId", brokerId);
            request.setAttribute("providerId", providerId);

            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */
            
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

            result.addObject("ContactPersons", collection);
            result.addObject("memberGroup", memberGroupObject);
            result.addObject("client", clientObject);
            result.addObject("provider", providerObject);
            result.addObject("listnavigation",listnavigation);

            request.setAttribute("countSet", countSet);
            request.setAttribute("index", new Integer(index));
            request.setAttribute("count", new Integer(count));
            request.setAttribute("alert", request.getParameter("alert"));
            request.setAttribute("minIndex", new Integer(minIndex));
            request.setAttribute("maxIndex", new Integer(maxIndex));

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("alert", alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));

            result = new ModelAndView("error");
        }

        return result;
    }

    /**
     * Action Service
     */
    public ModelAndView handleRequest(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        //Get paramater navigation
        String navigation = request.getParameter("navigation") == null
                ? "welcome"
                : request.getParameter("navigation");
        
        String listnavigation = request.getParameter("listnavigation") == null
                ? "welcome"
                : request.getParameter("listnavigation");
        
        Integer clientId = WebUtil.getParameterInteger(request, "clientId");


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
        String breadcrumb = "<a href=\"contactperson\" class=\"linkbreadcrumb\">Manage Contact Person</a>";
        try {
            if (navigation.equalsIgnoreCase("detail")) {
                /*
                 * disesuaikan dengan halaman targetnya nih
                 */
                result = detailPerformed(request, response, "detailContactPerson");
            } else if (navigation.equalsIgnoreCase("delete")) {
                result = deletePerformed(request, response);
            } else if (navigation.equalsIgnoreCase("search") || navigation.equals("gosearch")) {
                result = searchPerformed(request, response, "searchContactPerson");
            } else if (navigation.equalsIgnoreCase("listgroup")) {
                result = searchPerformed(request, response, "listGroupContactPerson");
                String groupId = request.getParameter("memberGroupId");
				breadcrumb = "<a href=\"membergroup?navigation=detail&memberGroupId="+groupId+"\" class=\"linkbreadcrumb\">Detail Member Group</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Group Contact Person";
            } else if (navigation.equalsIgnoreCase("listprovider")) {
                result = searchPerformed(request, response, "listProviderContactPerson");
                String providerId = request.getParameter("providerId");
                if(listnavigation.equalsIgnoreCase("searchcapitation"))
				{
                	breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"&listnavigation="+listnavigation+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Contact Person";
				}else{
					breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Contact Person";
				}
            } else if (navigation.equalsIgnoreCase("listclient")) {
                result = searchPerformed(request, response, "listClientContactPerson");
				breadcrumb = "<a href=\"client?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">Detail Client</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Client Contact Person";
            } else if (navigation.equalsIgnoreCase("listbroker")) {
                result = searchPerformed(request, response, "listBrokerContactPerson");
            } else if (navigation.equalsIgnoreCase("lookup") || navigation.equals("golookup")) {
                result = searchPerformed(request, response, "lookupContactPerson");
            } else {
                result = searchPerformed(request, response, "searchContactPerson");
            }
            result.addObject("breadcrumb", breadcrumb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("clientId", clientId);
        request.setAttribute("navigation", navigation);

        Map map = TableRenderingServlet.seti18N(request, response);
        result.addAllObjects(map);
        return result;
    }
// class+ 
// class- 
}
