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
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.datamodel.MemberGroupProvider;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.MemberGroupProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 
// imports- 
/**
 * MemberGroupProvider is a servlet controller for member_group_provider Table.
 * All you have to do is to convert necessary data field to the named parameter
 */
public class MemberGroupProviderController implements Controller // extends+ 
// extends- 
{

    private MemberGroupProviderService memberGroupProviderService;
    private UserService userService;
    private MemberGroupService memberGroupService;
    private ResourceBundleMessageSource alertProperties;
    private Integer countSet;
    private Integer maxPercountSet;
    private SecurityService securityService;
private ActivityLogService logService;

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

    public void setMemberGroupProviderService(MemberGroupProviderService memberGroupProviderService) {
        this.memberGroupProviderService = memberGroupProviderService;
    }

    public MemberGroupProviderService getMemberGroupProviderService() {
        return this.memberGroupProviderService;
    }

    public ModelAndView deletePerformed(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView result = null;

        try {
            Integer memberGroupProviderId = WebUtil.getParameterInteger(request, "memberGroupProviderId");

            /*
             * Pkey ini merupakan representasi dari primary key, misalkan hasil
             * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
             * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
             * primary key berarti tinggal bikin representasi primary key nya
             * saja .
             */
            java.io.Serializable pkey = memberGroupProviderId;

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEGROUPPROVIDER");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEGROUPPROVIDER access");
                return errorResult;

            }
            MemberGroupProvider res = memberGroupProviderService.delete(pkey, user);

            if (res != null) {
                request.setAttribute("alert", alertProperties.getMessage("success.delete.membergroupprovider", null, "success", Locale.getDefault()));
            } else {
                request.setAttribute("alert", alertProperties.getMessage("fail.delete.membergroupprovider", null, "fail", Locale.getDefault()));

            }
            result = searchPerformed(request, response, "searchMemberGroupProvider");
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
            Integer memberGroupProviderId = WebUtil.getParameterInteger(request, "memberGroupProviderId");

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
            java.io.Serializable pkey = memberGroupProviderId;
            MemberGroupProvider object = memberGroupProviderService.get(pkey);

            if (object == null) {
                request.setAttribute("alert", alertProperties.getMessage("not.found.membergroupprovider", null, "fail", Locale.getDefault()));
            }
            /*
             * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
             * ya .. ga pake 's' -adhit
             */

            result.addObject("memberGroupProvider", object);
            result.addObject("index", index);
            result.addObject("searchtext", searchtext);
            result.addObject("searchby", searchby);

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

            String navigation = WebUtil.getParameterString(request, "navigation", "");
            String searchtext = WebUtil.getParameterString(request, "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby", "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");


            String currentNavigation = WebUtil.getParameterString(request, "currentnavigation", "");


            Integer clientId = WebUtil.getParameterInteger(request, "clientId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");

            String subnavigation = WebUtil.getParameterString(request, "subnavigation", "");


            if (currentNavigation.equalsIgnoreCase("listmember")) {
                location = "listMemberProvider";
            } else if (currentNavigation.equalsIgnoreCase("listclient")) {
                location = "listClientProvider";
            } else if (currentNavigation.equalsIgnoreCase("listgroup")) {
                location = "listMemberGroupProvider";
            }


            result.addObject("subnavigation", subnavigation);
            result.addObject("currentnavigation", currentNavigation);

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
            if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equalsIgnoreCase("golistgroup")
            		|| navigation.equalsIgnoreCase("listgroup")) {

                if (searchby != null) {
                    /**
                     * ini querynya disesuaikan dengan apa yang mau di search
                     * default nya gue bikin template search by semua field yang
                     * tipenya 'String' -adhit
                     */
                    if (searchby.equalsIgnoreCase("providerName")) {
                        vLikeP.add("providerId.providerName");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("province")) {
                        vLikeP.add("providerId.province");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("country")) {
                        vLikeP.add("providerId.country");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("city")) {
                        vLikeP.add("providerId.city");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("providerCategoryName")) {
                        vLikeP.add("providerId.providerCategoryId.providerCategoryName");
                        vLikeQ.add(searchtext);
                    }
                }

            }
            if (navigation.equalsIgnoreCase("listgroup") || navigation.equalsIgnoreCase("golistgroup")) {


                vEqP.add("memberGroupId.memberGroupId");
                vEqQ.add(memberGroupId);


            }
            //result.addObject("memberGroupId", memberGroupId);
            //
            	
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

            result.addObject("clientId", clientId);
            result.addObject("memberId", memberId);
            result.addObject("memberGroupId", memberGroupId);
            result.addObject("memberGroup", memberGroupObject);

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

            count = memberGroupProviderService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
                "MemberGroupProvider.MemberGroupId",
                "MemberGroupProvider.ProviderId", // foreign affairs end
            };
            collection = memberGroupProviderService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
                collection = memberGroupProviderService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        required, rowsetint, countSet.intValue());
            }

            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);

            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("MemberGroupProviders", collection);

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


        String subnavigation = WebUtil.getParameterString(request, "subnavigation", "");
        
        

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
        String breadcrumb = "<a href=\"membergroupprovider\" class=\"linkbreadcrumb\">Search Member Group Provider</a>";
        try {        	
            if (navigation.equalsIgnoreCase("detail")) {
                /*
                 * disesuaikan dengan halaman targetnya nih
                 */
                result = detailPerformed(request, response, "detailMemberGroupProvider");
            } else if (navigation.equalsIgnoreCase("delete")) {
                result = deletePerformed(request, response);
            } else if (navigation.equalsIgnoreCase("search") || navigation.equals("gosearch")) {
                result = searchPerformed(request, response, "searchMemberGroupProvider");
            } else if (navigation.equalsIgnoreCase("lookup") || navigation.equals("golookup")) {
                result = searchPerformed(request, response, "lookupMemberGroupProvider");
            } else if (navigation.equalsIgnoreCase("listgroup") || subnavigation.equalsIgnoreCase("listgroup") || navigation.equalsIgnoreCase("golistgroup")) {
                result = searchPerformed(request, response,
                        "listMemberGroupProvider");
                String groupId = request.getParameter("memberGroupId");
				breadcrumb = "<a href=\"membergroup?navigation=detail&memberGroupId="+groupId+"\" class=\"linkbreadcrumb\">Detail Member Group</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Group Provider";
            } else {
                result = searchPerformed(request, response, "listMemberGroupProvider");
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
