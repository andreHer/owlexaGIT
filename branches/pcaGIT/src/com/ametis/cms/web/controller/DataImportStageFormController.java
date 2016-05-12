package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.DataImportStage;
import com.ametis.cms.datamodel.ExportImportTemplate;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.DataImportStageService;
import com.ametis.cms.service.ExportImportTemplateService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.DataImportStageForm;

// imports+ 
// imports- 
/**
 * DataImportStage is a mapping of data_import_stage Table.
 */
public class DataImportStageFormController extends SimpleFormController // extends+ 
// extends- 
{

    DataImportStageService dataImportStageService;
    ResourceBundleMessageSource alertProperties;
    private SecurityService securityService;
    private ExportImportTemplateService templateService;
    // foreign affairs
    private ActivityLogService logService;
    private ConfigurationService configurationService;

	
	
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
    public ExportImportTemplateService getTemplateService() {
        return templateService;
    }

    public void setTemplateService(ExportImportTemplateService templateService) {
        this.templateService = templateService;
    }

    // -- foreign affairs end
    public void setDataImportStageService(DataImportStageService object) {
        this.dataImportStageService = object;
    }

    public DataImportStageService getDataImportStageService() {
        return this.dataImportStageService;
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

    public DataImportStageFormController() {
        setSessionForm(true);
        setValidateOnBinding(true);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        Object result = null;
        DataImportStageForm tmp = null;
        Integer id = WebUtil.getParameterInteger(request, "id");
        String tipe = WebUtil.getParameterString(request, "tipe","");

        /*
         * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
         * primary key nya
         */
        if (id != null) {
            java.io.Serializable pkey = id;
            DataImportStage object = dataImportStageService.get(pkey);

            if (object != null) { // edit object

                tmp = new DataImportStageForm(object);
                // foreign affairs
                // -- foreign affairs end
            } else {// object tidak ditemukan ?? anggap kita mau bikin baru !
                tmp = new DataImportStageForm();
                tmp.setTipe(tipe);
                // foreign affairs
                // -- foreign affairs end
            }
        } // mau edit end
        else { // bikin baru
            tmp = new DataImportStageForm();
            tmp.setTipe(tipe);
            // foreign affairs
            // -- foreign affairs end



        }
        
        String breadcrumb = "";
        if (tipe.equals("1"))
        {
        breadcrumb = "<a href=\"dataimportstage-form?tipe="+tipe+"\" class=\"linkbreadcrumb\">Register Upload Claim</a>";
		request.setAttribute("breadcrumb", breadcrumb);
        }
        else if (tipe.equals("2"))
        {
        breadcrumb = "<a href=\"dataimportstage-form?tipe="+tipe+"\" class=\"linkbreadcrumb\">Register Upload Claim Detail</a>";
		request.setAttribute("breadcrumb", breadcrumb);
        }
        else if (tipe.equals("3"))
        {
        breadcrumb = "<a href=\"dataimportstage-form?tipe="+tipe+"\" class=\"linkbreadcrumb\">Register Upload Member</a>";
		request.setAttribute("breadcrumb", breadcrumb);
        }
        else
        {
        breadcrumb = "<a href=\"dataimportstage-form?tipe="+tipe+"\" class=\"linkbreadcrumb\">Register Upload Provider</a>";
		request.setAttribute("breadcrumb", breadcrumb);
        }

        result = tmp;
        return result;
    }

    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
            throws Exception {



        DataImportStageForm dataImportStageForm = (DataImportStageForm) command;
        DataImportStage dataImportStage = dataImportStageForm.getDataImportStage();
        errors.printStackTrace();

//		errors.setNestedPath("dataImportStage");
//		getValidator().validate(dataImportStage, errors);
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
        try {
        	boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        	if (isMultipart){
        		FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                try 
                {
                    List items = upload.parseRequest(request);
                    Iterator iterator = items.iterator();
                    while (iterator.hasNext()) 
                    {
                        FileItem item = (FileItem) iterator.next();

                        if (item.isFormField()) //your code for getting form fields
                        {
                            String name = item.getFieldName();
                            String value = item.getString();
                            System.out.println(name+value);
                        }

                        if (!item.isFormField()) 
                        {
                           //your code for getting multipart 
                        }
                    }
                }
                catch (Exception e){
                	e.printStackTrace();
                }
        	}
        	else {
	        	String type = request.getParameter("tipe");
	        	
				String[] eqParam = {"tipe","deletedStatus"};
				Object[] eqValue = {Integer.valueOf(type),Integer.valueOf(0)};
			
				int total = templateService.getTotal(null,null,eqParam,eqValue);
				
				Collection<ExportImportTemplate> col = templateService.search(null,null,eqParam,eqValue,0,total);
				
				model.put("templates", col);
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

        DataImportStageForm dataImportStageForm = (DataImportStageForm) command;

        DataImportStage res = null;
        String alertMsg = "";
        String id = "";
        try {
            // foreign affairs
            // -- foreign affairs end
            String notyet = WebUtil.getParameterString(request, "notyet", "");
            if (notyet.equals("true")) {
                return showForm(request, response, errors);
            }

            byte[] theData = null;
            // String fileName = "";

            Configuration systemConfig = configurationService.getSystemConfiguration();
            
            if (dataImportStageForm.getDataImportStage().getTipe().intValue() == DataImportStage.TIPE_MEMBER){
            	if (systemConfig.getUploadMemberTemplateId() != null){
            		ExportImportTemplate template = new ExportImportTemplate();
            		template.setId(Integer.valueOf(systemConfig.getUploadMemberTemplateId()));
            		
            		dataImportStageForm.getDataImportStage().setExportImportTemplate(template);
            	}
            }
            if (dataImportStageForm.getDataImportStage().getTipe().intValue() == DataImportStage.TIPE_PROVIDER){
            	if (systemConfig.getUploadProviderTemplateId() != null){
            		ExportImportTemplate template = new ExportImportTemplate();
            		template.setId(Integer.valueOf(systemConfig.getUploadProviderTemplateId()));
            		
            		dataImportStageForm.getDataImportStage().setExportImportTemplate(template);
            	}
            }
            if (dataImportStageForm.getDataImportStage().getTipe().intValue() == DataImportStage.TIPE_HEADER_CLAIM){
            	if (systemConfig.getUploadClaimHeaderTemplateId() != null){
            		ExportImportTemplate template = new ExportImportTemplate();
            		template.setId(Integer.valueOf(systemConfig.getUploadClaimHeaderTemplateId()));
            		
            		dataImportStageForm.getDataImportStage().setExportImportTemplate(template);
            	}
            }
            if (dataImportStageForm.getDataImportStage().getTipe().intValue() == DataImportStage.TIPE_DETAIL_CLAIM){
            	if (systemConfig.getUploadClaimDetailTemplateId() != null){
            		ExportImportTemplate template = new ExportImportTemplate();
            		template.setId(Integer.valueOf(systemConfig.getUploadClaimDetailTemplateId()));
            		
            		dataImportStageForm.getDataImportStage().setExportImportTemplate(template);
            	}
            }
            if (dataImportStageForm.getDataImportStage().getTipe().intValue() == DataImportStage.TIPE_PAYMENT){
            	if (systemConfig.getUploadPaymentTemplateId() != null){
            		ExportImportTemplate template = new ExportImportTemplate();
            		template.setId(Integer.valueOf(systemConfig.getUploadPaymentTemplateId()));
            		
            		dataImportStageForm.getDataImportStage().setExportImportTemplate(template);
            	}
            }
            if (dataImportStageForm.getDataImportStage().getTipe().intValue() == DataImportStage.TIPE_GROUP_SUSPEND){
            	if (systemConfig.getUploadPaymentTemplateId() != null){
            		ExportImportTemplate template = new ExportImportTemplate();
            		template.setId(Integer.valueOf(systemConfig.getUploadGroupSuspendTemplateId()));
            		
            		dataImportStageForm.getDataImportStage().setExportImportTemplate(template);
            	}
            }
            if (dataImportStageForm.getDataImportStage().getTipe().intValue() == DataImportStage.TIPE_PRODUCT_UPLOAD){
            	if (systemConfig.getUploadProductImportTemplateId() != null){
            		ExportImportTemplate template = new ExportImportTemplate();
            		template.setId(Integer.valueOf(systemConfig.getUploadProductImportTemplateId()));
            		
            		dataImportStageForm.getDataImportStage().setExportImportTemplate(template);
            	}
            }
            
            MultipartFile multipartFile = dataImportStageForm.getFile();
            System.out.println("Original File Name : " + multipartFile.getOriginalFilename());
            System.out.println("File Name : " + multipartFile.getName());
            String fileFormat = "csv";
            
            if (multipartFile.getOriginalFilename() != null){
            	if (multipartFile.getOriginalFilename().contains("xls")){
            		fileFormat = "xls";
            	}
            	if (multipartFile.getOriginalFilename().contains("xlsx")){
            		fileFormat = "xlsx";
            	}
            	if (multipartFile.getOriginalFilename().contains("csv")){
            		fileFormat = "csv";
            	}
            }
            
            dataImportStageForm.getDataImportStage().setImportRawFile(multipartFile.getOriginalFilename());
            dataImportStageForm.getDataImportStage().setFileFormat(fileFormat);

            System.out.println("CONTENT : " + theData + " content 2 : " + dataImportStageForm.getFile());


            ActionUser user = securityService.getActionUser(request);

            if (dataImportStageForm.isNewDataImportStage()) {
                res = dataImportStageService.create(dataImportStageForm.getDataImportStage(), multipartFile.getBytes(), user);

                id = res.getId()+"";
                if (res != null) {
                    alertMsg = alertProperties.getMessage("success.create.dataimportstage", null, "success", Locale.getDefault());
                } else {
                    alertMsg = alertProperties.getMessage("fail.create.dataimportstage", null, "fail", Locale.getDefault());
                }
            } else {
                res = dataImportStageService.update(dataImportStageForm.getDataImportStage(), user);

                id = res.getId()+"";
                if (res != null) {
                    alertMsg = alertProperties.getMessage("success.update.dataimportstage", null, "success", Locale.getDefault());
                } else {
                    alertMsg = alertProperties.getMessage("fail.update.dataimportstage", null, "fail", Locale.getDefault());
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (dataImportStageForm.isNewDataImportStage()) {
                request.setAttribute("alert", alertProperties.getMessage("fail.create.dataimportstage", null, "fail", Locale.getDefault()) + " " + ex.getMessage());
            } else {
                request.setAttribute("alert", alertProperties.getMessage("fail.update.dataimportstage", null, "fail", Locale.getDefault()) + " " + ex.getMessage());
            }
            ex.printStackTrace();
            return showForm(request, response, errors);
        }
        return new ModelAndView(new RedirectView("dataimportstage" + "?navigation=detail&id="+id+"&alert=" + alertMsg));
//		return super.onSubmit(request, response, command, errors);
    }

    protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(req, binder);
        CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"), true);
        binder.registerCustomEditor(Date.class, cde);
        CustomNumberEditor num = new CustomNumberEditor(Number.class, true);
        binder.registerCustomEditor(Number.class, num);
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }
// class+ 
// class- 
}
