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
import com.ametis.cms.datamodel.Bank;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BankService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.LogUtil;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 
// imports- 
/**
 * Bank is a servlet controller for bank Table. All you have to do is to convert
 * necessary data field to the named parameter
 */
public class BankController implements Controller // extends+ 
// extends- 
{

    private BankService bankService;
    private SecurityService securityService;
    private UserService userService;
    private ResourceBundleMessageSource alertProperties;
    private Integer countSet;
    private Integer maxPercountSet;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
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

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    public BankService getBankService() {
        return this.bankService;
    }

    public ModelAndView deletePerformed(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView result = null;

        try {
            Integer bankId = WebUtil.getParameterInteger(request, "bankId");

            /*
             * Pkey ini merupakan representasi dari primary key, misalkan hasil
             * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
             * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
             * primary key berarti tinggal bikin representasi primary key nya
             * saja .
             */
            java.io.Serializable pkey = bankId;

            ActionUser user = securityService.getActionUser(request);

            Bank res = bankService.delete(pkey, user);

            if (res != null) {
                request.setAttribute("alert", alertProperties.getMessage("success.delete.bank", null, "success", Locale.getDefault()));
            } else {
                request.setAttribute("alert", alertProperties.getMessage("fail.delete.bank", null, "fail", Locale.getDefault()));

            }
            LogUtil.log(logService, user, "Delete Bank - " + request.getAttribute("alert"), res.toString());
            result = searchPerformed(request, response, "searchBank");
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
            Integer bankId = WebUtil.getParameterInteger(request, "bankId");

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
	ActionUser user = securityService.getActionUser(request);
	
	boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DETAILBANK");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DETAILBANK access");
				return errorResult;

			}
            
            
            result = new ModelAndView(location);
            java.io.Serializable pkey = bankId;
            Bank object = bankService.get(pkey);

            if (object == null) {
                request.setAttribute("alert", alertProperties.getMessage("not.found.bank", null, "fail", Locale.getDefault()));
            }
            /*
             * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
             * ya .. ga pake 's' -adhit
             */

            LogUtil.log(logService, user, "View Detail Bank - " + request.getAttribute("alert"), object.toString());
            result.addObject("bank", object);
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

        	ActionUser user = securityService.getActionUser(request);

        	boolean isUserAuthorized = securityService.isUserAuthorized(user,
				"SEARCHBANK");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHBANK access");
				return errorResult;

			}


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
            
            String arah = WebUtil.getParameterString(request, "arah", "");
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			boolean sortBankName = true, sortSwiftCode = true, sortDescription = true, sortBankCode = true;
			boolean order = true;

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
            
            if (navigation.equals("gosearch") || navigation.equals("golookup")||
					navigation.equals("gosearchbysort") || (navigation.equals("") && !arah.isEmpty())) {
                if (searchby != null && searchtext!=null && !searchtext.equals("")) {
                    /**
                     * ini querynya disesuaikan dengan apa yang mau di search
                     * default nya gue bikin template search by semua field yang
                     * tipenya 'String' -adhit
                     */
                    if (searchby.equalsIgnoreCase("bankName")) {
                        vLikeP.add("bankName");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("swiftCode")) {
                        vLikeP.add("swiftCode");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("description")) {
                        vLikeP.add("description");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("bankCode")) {
                        vLikeP.add("bankCode");
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

            count = bankService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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

            String required[] = new String[]{ // foreign affairs
            // foreign affairs end
            };
            
          //SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equalsIgnoreCase("bankname")){
						sortByColumn = "bankName";
						Boolean sortByOrderBankName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderBankName", ""));
						sortBankName = !sortByOrderBankName;
						order = sortBankName;
					}else if(sortcolumn.equalsIgnoreCase("swiftcode")){
						sortByColumn = "swiftCode";
						Boolean sortByOrderSwiftCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderSwiftCode", ""));
						sortSwiftCode = !sortByOrderSwiftCode;
						order = sortSwiftCode;
					}else if(sortcolumn.equalsIgnoreCase("description")){
						sortByColumn = "description";
						Boolean sortByOrderDescription = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderDescription", ""));
						sortDescription = !sortByOrderDescription;
						order = sortDescription;
					}else{
						sortByColumn = "bankCode";
						Boolean sortByOrderBankCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderBankCode", ""));
						sortBankCode = !sortByOrderBankCode;
						order = sortBankCode;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equalsIgnoreCase("bankname")){
						sortBankName = order;
					}else if(sortcolumn.equalsIgnoreCase("swiftcode")){
						sortSwiftCode = order;
					}else if(sortcolumn.equalsIgnoreCase("description")){
						sortDescription = order;
					}else{
						sortBankCode = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				collection = bankService.search(sLikeP, sLikeQ, sEqP, sEqQ, order, sortByColumn,
	                    required, rowsetint, countSet.intValue());
			}else{
				collection = bankService.search(sLikeP, sLikeQ, sEqP, sEqQ,
	                    required, rowsetint, countSet.intValue());
			}
            

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
                collection = bankService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        required, rowsetint, countSet.intValue());
            }

            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("sortcolumn", sortcolumn);

            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */
            LogUtil.log(logService, user, "Search Bank - ", "SearchText : " + searchtext + "  SearchBy : " + searchby);
            result.addObject("Banks", collection);

            request.setAttribute("countSet", countSet);
            request.setAttribute("index", new Integer(index));
            request.setAttribute("count", new Integer(count));
            request.setAttribute("alert", request.getParameter("alert"));
            request.setAttribute("minIndex", new Integer(minIndex));
            request.setAttribute("maxIndex", new Integer(maxIndex));
            request.setAttribute("sortBankCode", sortBankCode);
            request.setAttribute("sortBankName", sortBankName);
            request.setAttribute("sortDescription", sortDescription);
            request.setAttribute("sortSwiftCode", sortSwiftCode);

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

    public ModelAndView lookupJsonPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String q = WebUtil.getParameterString(request, "q", "");
			
			Collection<Bank> banks = bankService.searchBank(q);
			
			result.addObject("Banks", banks);


		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
    public ModelAndView handleRequest(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        //Get paramater navigation
        String navigation = request.getParameter("navigation") == null
                ? "welcome"
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
         *
         * }
         */
        String breadcrumb = "";
        try {
            if (navigation.equalsIgnoreCase("detail")) {
                /*
                 * disesuaikan dengan halaman targetnya nih
                 */
                result = detailPerformed(request, response, "detailBank");
            } else if (navigation.equalsIgnoreCase("delete")) {
                result = deletePerformed(request, response);
            } else if (navigation.equalsIgnoreCase("search") || navigation.equals("gosearch")) {
                result = searchPerformed(request, response, "searchBank");
                breadcrumb = "<a href=\"bank\" class=\"linkbreadcrumb\">Search Bank</a>";
            } else if (navigation.equalsIgnoreCase("lookup") || navigation.equals("golookup")) {
                result = searchPerformed(request, response, "lookupBank");
            } else if (navigation.equalsIgnoreCase("lookupjson")) {
                System.out.println("lookup json");
                System.out.println("q : " + request.getParameter("q"));
                result = lookupJsonPerformed(request, response, "lookupBankJson");
            } else {
                result = searchPerformed(request, response, "searchBank");
                breadcrumb = "<a href=\"bank\" class=\"linkbreadcrumb\">Search Bank</a>";
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
