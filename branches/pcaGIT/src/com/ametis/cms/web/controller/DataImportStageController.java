package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.DataImportStage;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.DataImportStageService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 
// imports- 
/**
 * DataImportStage is a servlet controller for data_import_stage Table. All you
 * have to do is to convert necessary data field to the named parameter
 */
public class DataImportStageController implements Controller // extends+ 
// extends- 
{

    private DataImportStageService dataImportStageService;
    private SecurityService securityService;
    private UserService userService;
    private ResourceBundleMessageSource alertProperties;
    private Integer countSet;
    private Integer maxPercountSet;
private ActivityLogService logService;

	
	
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

    public void setDataImportStageService(DataImportStageService dataImportStageService) {
        this.dataImportStageService = dataImportStageService;
    }

    public DataImportStageService getDataImportStageService() {
        return this.dataImportStageService;
    }

    public ModelAndView deletePerformed(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

            DataImportStage res = dataImportStageService.delete(pkey, user);

            if (res != null) {
                request.setAttribute("alert", alertProperties.getMessage("success.delete.dataimportstage", null, "success", Locale.getDefault()));
            } else {
                request.setAttribute("alert", alertProperties.getMessage("fail.delete.dataimportstage", null, "fail", Locale.getDefault()));

            }
            result = searchPerformed(request, response, "searchDataImportStage");
        } catch (Exception e) {
            // nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
            e.printStackTrace();
            request.setAttribute("alert", alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));
            result = new ModelAndView("error");
        }
        return result;
    }
    public ModelAndView markCardPrintedPerformed(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView result = null;

        try {
            Integer id = WebUtil.getParameterInteger(request, "id");

            java.io.Serializable pkey = id;

            ActionUser user = securityService.getActionUser(request);

            
            DataImportStage res = dataImportStageService.get(pkey);

            if (res != null) {
                
            	res.setStatus(4);
            	dataImportStageService.update(res, user);
            	
            } else {
                request.setAttribute("alert", alertProperties.getMessage("fail.delete.dataimportstage", null, "fail", Locale.getDefault()));

            }
            result = new ModelAndView(new RedirectView("dataimportstage?navigation=detail&id="+id));
        } catch (Exception e) {
            // nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
            e.printStackTrace();
            request.setAttribute("alert", alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));
            result = new ModelAndView("error");
        }
        return result;
    }
    public ModelAndView processFilePerformed(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView result = null;

        try {
            Integer id = WebUtil.getParameterInteger(request, "id");

            java.io.Serializable pkey = id;

            ActionUser user = securityService.getActionUser(request);

            
            boolean res = dataImportStageService.processUpload(pkey, user);

            if (res) {
                
            	request.setAttribute("alert", "<b>Success Process Uploaded File</b>");
            } else {
                request.setAttribute("alert", "<b>Failed To Process Uploaded File</b>");

            }
            result = new ModelAndView(new RedirectView("dataimportstage?navigation=detail&id="+id));
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
            Integer id = WebUtil.getParameterInteger(request, "id");

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
            java.io.Serializable pkey = id;
            DataImportStage object = dataImportStageService.get(pkey);

            if (object == null) {
                request.setAttribute("alert", alertProperties.getMessage("not.found.dataimportstage", null, "fail", Locale.getDefault()));
            }
            /*
             * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
             * ya .. ga pake 's' -adhit
             */

            result.addObject("dataImportStage", object);
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

    public ModelAndView rawMigratePerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
        ModelAndView result = null;

        try {
            Integer id = WebUtil.getParameterInteger(request, "id");

            Integer index = WebUtil.getParameterInteger(request, "index");
            String searchtext = WebUtil.getParameterString(request, "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby", "");


            result = new ModelAndView(location);
            java.io.Serializable pkey = id;


            boolean res = dataImportStageService.executeRawMigration(pkey);

            if (res) {
                result = new ModelAndView(new RedirectView("dataimportstage?navigation=detail&id=" + id + "&alert=success"));
            }



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

    public ModelAndView migratePerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
        ModelAndView result = null;

        try {
            Integer id = WebUtil.getParameterInteger(request, "id");

            Integer index = WebUtil.getParameterInteger(request, "index");
            String searchtext = WebUtil.getParameterString(request, "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby", "");


            result = new ModelAndView(location);
            java.io.Serializable pkey = id;

            boolean res = dataImportStageService.executeMigration(pkey);

            if (res) {
                result = new ModelAndView(new RedirectView("dataimportstage?navigation=detail&id=" + id + "&alert=success"));
            }



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

    public ModelAndView redirectPerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
        ModelAndView result = null;

        try {
            Integer id = WebUtil.getParameterInteger(request, "id");
            String navigation = WebUtil.getParameterString(request, "navigation", "");
            
            DataImportStage data = dataImportStageService.get(id);
            
            if (data != null){
                if (data.getTipe().intValue() == DataImportStage.TIPE_HEADER_CLAIM){
                    if (navigation.equalsIgnoreCase("list")){
                        location = "externalclaim?navigation=list&id="+id;
                    }
                    if (navigation.equalsIgnoreCase("listraw")){
                        location = "externalrawdata?navigation=list&id="+id;
                    }
                }
                if (data.getTipe().intValue() == DataImportStage.TIPE_DETAIL_CLAIM){
                    if (navigation.equalsIgnoreCase("list")){
                        location = "externalclaimdetail?navigation=list&id="+id;
                    }
                    if (navigation.equalsIgnoreCase("listraw")){
                        location = "externalrawdetaildata?navigation=list&id="+id;
                    }
                }
                if (data.getTipe().intValue() == DataImportStage.TIPE_MEMBER){
                	if (navigation.equalsIgnoreCase("list")){
                        location = "memberimport?navigation=list&id="+id;
                    }
                    if (navigation.equalsIgnoreCase("listraw")){
                        location = "externalrawdetaildata?navigation=list&id="+id;
                    }
                }
            }
            result = new ModelAndView(new RedirectView(location));

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("alert", alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));

            result = new ModelAndView("error");
        }

        return result;
    }

    public ModelAndView uploadPerformed(HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
        ModelAndView result = null;

        try {


            final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            Integer id = WebUtil.getParameterInteger(request, "id");

            Integer index = WebUtil.getParameterInteger(request, "index");
            String searchtext = WebUtil.getParameterString(request, "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby", "");


            byte[] fileContent = null;
            DataImportStage object = new DataImportStage();

            String navi = multiRequest.getParameter("navigation");
            String number = multiRequest.getParameter("importNumber");
            String date = multiRequest.getParameter("importDate");
            String template = multiRequest.getParameter("template");
            String description = multiRequest.getParameter("description");

            System.out.println("navigation : " + navi);
            System.out.println("number : " + number);
            System.out.println("date : " + date);
            System.out.println("description : " + description);

            Map maps = multiRequest.getFileMap();

            System.out.println("PRINT MAP SIZE : " + maps.size());


            Enumeration attribs = multiRequest.getAttributeNames();
            System.out.println("attribute list");
            while (attribs.hasMoreElements()) {
                System.out.println(attribs.nextElement().toString());
            }

            Enumeration params = multiRequest.getParameterNames();
            System.out.println("parameter list");
            while (params.hasMoreElements()) {
                System.out.println(params.nextElement().toString());
            }


            MultipartFile file = multiRequest.getFile("filenya");

            if (file != null) {
                System.out.println("ternyata file nya ga null chuy");
                fileContent = file.getBytes();

                System.out.println("isi file nya : ");
                for (int i = 0; i < fileContent.length; i++) {
                    System.out.print(fileContent[i]);
                }
            }


            DataImportStage resultObj = dataImportStageService.create(object, fileContent, null);

            if (resultObj != null) {
                request.setAttribute("alert", alertProperties.getMessage("not.found.dataimportstage", null, "fail", Locale.getDefault()));
                detailPerformed(request, response, "detailDataImportStage");
            }



            result.addObject("index", index);
            result.addObject("searchtext", searchtext);
            result.addObject("searchby", searchby);
            detailPerformed(request, response, location);

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
                    if (searchby.equalsIgnoreCase("importRawFile")) {
                        vLikeP.add("importRawFile");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("importReadyFile")) {
                        vLikeP.add("importReadyFile");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("importNumber")) {
                        vLikeP.add("importNumber");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("description")) {
                        vLikeP.add("description");
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

            //  vEqP.add("deletedStatus");
            //  vEqQ.add(new Integer(0));

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);

            count = dataImportStageService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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

            String required[] = new String[]{ // foreign affairs
            // foreign affairs end
            };
            collection = dataImportStageService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
                collection = dataImportStageService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        required, rowsetint, countSet.intValue());
            }

            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);

            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("DataImportStages", collection);

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
                result = detailPerformed(request, response, "detailDataImportStage");
                String id = request.getParameter("id");
				breadcrumb = "<a href=\"dataimportstage?navigation=detail&id="+id+"\" class=\"linkbreadcrumb\">Detail Data Import Session</a>";
            } else if (navigation.equalsIgnoreCase("delete")) {
                result = deletePerformed(request, response);
            } else if (navigation.equalsIgnoreCase("search") || navigation.equals("gosearch")) {
                result = searchPerformed(request, response, "searchDataImportStage");
                breadcrumb = "<a href=\"dataimportstage?navigation=search&searchtext=&searchby=&index=\" class=\"linkbreadcrumb\">Search Data Import Session</a>";
            } else if (navigation.equalsIgnoreCase("lookup") || navigation.equals("golookup")) {
                result = searchPerformed(request, response, "lookupDataImportStage");
            } else if (navigation.equalsIgnoreCase("migrate")) {
                result = migratePerformed(request, response, "detailDataImportStage");
            } else if (navigation.equalsIgnoreCase("preupload")) {
                result = new ModelAndView("editDataImportStage");
            } 
            
            else if (navigation.equalsIgnoreCase("markprint")) {
                result = markCardPrintedPerformed(request, response);
            }
            else if (navigation.equalsIgnoreCase("processfile")) {
                result = processFilePerformed(request, response);
            }
            else if (navigation.equalsIgnoreCase("upload")) {
                result = uploadPerformed(request, response, "detailDataImportStage");
            } else if (navigation.equalsIgnoreCase("rawmigrate")) {
                result = rawMigratePerformed(request, response, "detailDataImportStage");
            } else if (navigation.equalsIgnoreCase("migrate")) {
                result = migratePerformed(request, response, "detailDataImportStage");
            } else if (navigation.equalsIgnoreCase("list")) {
                result = redirectPerformed(request, response, "detailDataImportStage");
            } else if (navigation.equalsIgnoreCase("listraw")) {
                result = redirectPerformed(request, response, "detailDataImportStage");
            } else {
                result = searchPerformed(request, response, "searchDataImportStage");	
                breadcrumb = "<a href=\"dataimportstage?navigation=search&searchtext=&searchby=&index=\" class=\"linkbreadcrumb\">Search Data Import Session</a>";
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
