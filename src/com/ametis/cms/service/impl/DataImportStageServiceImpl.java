package com.ametis.cms.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;



import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.dao.DataImportStageDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.DataImportStage;
import com.ametis.cms.datamodel.ExportImportTemplate;
import com.ametis.cms.datamodel.ExternalClaim;
import com.ametis.cms.datamodel.ExternalClaimDetail;
import com.ametis.cms.datamodel.ExternalRawData;
import com.ametis.cms.datamodel.ExternalRawDetailData;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberImport;
import com.ametis.cms.datamodel.MemberProvider;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.Relationship;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.DataImportStageService;
import com.ametis.cms.service.ExportImportTemplateService;
import com.ametis.cms.service.ExternalClaimDetailService;
import com.ametis.cms.service.ExternalClaimService;
import com.ametis.cms.service.ExternalRawDataService;
import com.ametis.cms.service.ExternalRawDetailDataService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberImportService;
import com.ametis.cms.service.MemberProviderService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PaymentInstallmentService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.PolicyProviderService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderDiagnosisTreatmentService;
import com.ametis.cms.service.ProviderProcedureService;
import com.ametis.cms.service.ProviderTypeDiagnosisTreatmentService;
import com.ametis.cms.service.ProviderTypeProcedureService;
import com.ametis.cms.util.StringUtil;
import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 
// imports- 
/**
 * DataImportStage is a servlet controller for data_import_stage Table. All you
 * have to do is to convert necessary data field to the named parameter
 */
public class DataImportStageServiceImpl implements DataImportStageService // extends+ 
// extends- 
{

    private DataImportStageDao dataImportStageDao;
    private MemberImportService memberImportService;
    private ExternalRawDataService externalRawDataService;
    private ExternalRawDetailDataService externalRawDetailDataService;
    private ExternalClaimService externalClaimService;
    private ExternalClaimDetailService externalClaimDetailService;
    private ExportImportTemplateService exportImportTemplateService;
    private ConfigurationService configurationService;
    private com.ametis.cms.service.ProviderService providerService;
    private MemberProviderService memberProviderService;
    private PolicyProviderService policyProviderService;
    private ClientService clientService;
    private MemberGroupService memberGroupService;
    private PolicyService policyService;
    
    private ClaimService claimService;
    private ClaimItemService claimItemService;
    
    private ProviderTypeDiagnosisTreatmentService providerTypeDiagnosisTreatmentService;
    private ProviderTypeProcedureService providerTypeProcedureService;
    private ProviderDiagnosisTreatmentService providerDiagnosisTreatmentService;
    private ProviderProcedureService providerProcedureService;
    
    
    private PaymentService paymentService;
    private PaymentInstallmentService paymentInstallmentService;
    
    
    private MemberService memberService;
    
    
    
    
    
    
    public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public PaymentInstallmentService getPaymentInstallmentService() {
		return paymentInstallmentService;
	}

	public void setPaymentInstallmentService(
			PaymentInstallmentService paymentInstallmentService) {
		this.paymentInstallmentService = paymentInstallmentService;
	}

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

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}

	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public MemberProviderService getMemberProviderService() {
		return memberProviderService;
	}

	public void setMemberProviderService(MemberProviderService memberProviderService) {
		this.memberProviderService = memberProviderService;
	}

	public PolicyProviderService getPolicyProviderService() {
		return policyProviderService;
	}

	public void setPolicyProviderService(PolicyProviderService policyProviderService) {
		this.policyProviderService = policyProviderService;
	}

	public ProviderTypeDiagnosisTreatmentService getProviderTypeDiagnosisTreatmentService() {
		return providerTypeDiagnosisTreatmentService;
	}

	public void setProviderTypeDiagnosisTreatmentService(
			ProviderTypeDiagnosisTreatmentService providerTypeDiagnosisTreatmentService) {
		this.providerTypeDiagnosisTreatmentService = providerTypeDiagnosisTreatmentService;
	}

	public ProviderTypeProcedureService getProviderTypeProcedureService() {
		return providerTypeProcedureService;
	}

	public void setProviderTypeProcedureService(
			ProviderTypeProcedureService providerTypeProcedureService) {
		this.providerTypeProcedureService = providerTypeProcedureService;
	}

	public ProviderDiagnosisTreatmentService getProviderDiagnosisTreatmentService() {
		return providerDiagnosisTreatmentService;
	}

	public void setProviderDiagnosisTreatmentService(
			ProviderDiagnosisTreatmentService providerDiagnosisTreatmentService) {
		this.providerDiagnosisTreatmentService = providerDiagnosisTreatmentService;
	}

	public ProviderProcedureService getProviderProcedureService() {
		return providerProcedureService;
	}

	public void setProviderProcedureService(
			ProviderProcedureService providerProcedureService) {
		this.providerProcedureService = providerProcedureService;
	}

	public com.ametis.cms.service.ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(com.ametis.cms.service.ProviderService providerService) {
		this.providerService = providerService;
	}

	public MemberImportService getMemberImportService() {
		return memberImportService;
	}

	public void setMemberImportService(MemberImportService memberImportService) {
		this.memberImportService = memberImportService;
	}

	public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public ExternalClaimDetailService getExternalClaimDetailService() {
        return externalClaimDetailService;
    }

    public void setExternalClaimDetailService(ExternalClaimDetailService externalClaimDetailService) {
        this.externalClaimDetailService = externalClaimDetailService;
    }

    public ExternalClaimService getExternalClaimService() {
        return externalClaimService;
    }

    public void setExternalClaimService(ExternalClaimService externalClaimService) {
        this.externalClaimService = externalClaimService;
    }

    public ExternalRawDataService getExternalRawDataService() {
        return externalRawDataService;
    }

    public void setExternalRawDataService(ExternalRawDataService externalRawDataService) {
        this.externalRawDataService = externalRawDataService;
    }

    public ExternalRawDetailDataService getExternalRawDetailDataService() {
        return externalRawDetailDataService;
    }

    public void setExternalRawDetailDataService(ExternalRawDetailDataService externalRawDetailDataService) {
        this.externalRawDetailDataService = externalRawDetailDataService;
    }

    public ExportImportTemplateService getExportImportTemplateService() {
        return exportImportTemplateService;
    }

    public void setExportImportTemplateService(ExportImportTemplateService exportImportTemplateService) {
        this.exportImportTemplateService = exportImportTemplateService;
    }

    public void setDataImportStageDao(DataImportStageDao object) {
        this.dataImportStageDao = object;
    }

    public DataImportStageDao getDataImportStageDao() {
        return this.dataImportStageDao;
    }
    /*
     * Method create (DataImportStage object) berfungsi untuk melakukan
     * penambahan sebuah object kedalam database @param object adalah sebuah
     * object yang ingin diubah @return object hasil kreasi,lengkap dengan
     * assigned primary key, exception jika gagal
     */

    public DataImportStage create(DataImportStage object, byte[] fileContent, ActionUser user) throws Exception {
        object.setDeletedStatus(new Integer(0));
        object.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        object.setStatus(0);
        object.setImportDate(new java.sql.Date(System.currentTimeMillis()));

        String fileName = StringUtil.hash("" + System.currentTimeMillis() + object.getImportNumber());
        System.out.println("File Asli : " + fileName);
        String ext = "csv";
        if (object.getImportRawFile() != null){
        	StringTokenizer tokenizer = new StringTokenizer(object.getImportRawFile(),".");
        	
        	while(tokenizer.hasMoreTokens()){
        		ext = tokenizer.nextToken();
        	}       	
        }
        object.setImportReadyFile(fileName + "."+ext);

        if (user != null && user.getUser() != null) {
            object.setCreatedBy(user.getUser().getUsername());
        }
        if (object.getImportNumber() == null || !object.getImportNumber().equalsIgnoreCase("")){
        	if (object.getClientId() != null){
        		Client client = clientService.get(object.getClientId().getClientId());
        		
        		String importNumber = client.getClientCode() + "-" + System.currentTimeMillis();
        		object.setImportNumber(importNumber);
        	}
        }
        DataImportStage result = dataImportStageDao.create(object);

        if (result != null) {
            Configuration configuration = configurationService.getSystemConfiguration();
            if (configuration != null) {
                if (fileContent != null) {

                    try {
                        String fileDir = "";
                        
                        if (result.getTipe().intValue() == DataImportStage.TIPE_DETAIL_CLAIM || result.getTipe().intValue() == DataImportStage.TIPE_HEADER_CLAIM){
                        	fileDir = configuration.getReportPath();
                        }
                        else if (result.getTipe().intValue() == DataImportStage.TIPE_MEMBER){
                        	fileDir = configuration.getMemberStoragePath();
                        	
                        	if (object.getClientId() != null){
                        		Configuration clientConfig = configurationService.getClientConfiguration(object.getClientId().getClientId());
                        		if (clientConfig != null && clientConfig.getUploadStorageDirectory() != null){
                        			fileDir = clientConfig.getUploadStorageDirectory() + File.separator + "membermovement";
                        
                        			System.out.println("SETTING DIRECTORY TO : " + fileDir);
                        			File check = new File(fileDir);
                        			if (!check.exists()){
                        				check.mkdirs();
                        			}
                        		}
                        	}
                        }
                        else if (result.getTipe().intValue() == DataImportStage.TIPE_ITEM_MAPPING){
                        	fileDir = configuration.getUploadStorageDirectory();
                        	
                        	if (object.getClientId() != null){
                        		Configuration clientConfig = configurationService.getClientConfiguration(object.getClientId().getClientId());
                        		if (clientConfig != null && clientConfig.getUploadStorageDirectory() != null){
                        			fileDir = clientConfig.getUploadStorageDirectory() + File.separator + "iccm";
                        			
                        			File check = new File(fileDir);
                        			if (!check.exists()){
                        				check.mkdirs();
                        			}
                        		}
                        	}
                        }
                        else if (result.getTipe().intValue() == DataImportStage.TIPE_MEMBER_DIAGNOSIS){
                        	fileDir = configuration.getUploadStorageDirectory();
                        	
                        	if (object.getClientId() != null){
                        		Configuration clientConfig = configurationService.getClientConfiguration(object.getClientId().getClientId());
                        		if (clientConfig != null && clientConfig.getUploadStorageDirectory() != null){
                        			fileDir = clientConfig.getUploadStorageDirectory() + File.separator + "mdexclusion";
                        			
                        			File check = new File(fileDir);
                        			if (!check.exists()){
                        				check.mkdirs();
                        			}
                        		}
                        	}
                        }
                        else if (result.getTipe().intValue() == DataImportStage.TIPE_PROVIDER){
                        	fileDir = configuration.getUploadStorageDirectory() + File.separator + "provider";
                        	
                        	if (object.getClientId() != null){
                        		Configuration clientConfig = configurationService.getClientConfiguration(object.getClientId().getClientId());
                        		if (clientConfig != null && clientConfig.getUploadStorageDirectory() != null){
                        			fileDir = clientConfig.getUploadStorageDirectory() + File.separator + "provider";
                        			
                        			File check = new File(fileDir);
                        			if (!check.exists()){
                        				check.mkdirs();
                        			}
                        		}
                        	}
                        }
                        else if (result.getTipe().intValue() == DataImportStage.TIPE_PAYMENT){
                        	fileDir = configuration.getUploadStorageDirectory() + File.separator + "payment";
                        	
                        	if (object.getClientId() != null){
                        		Configuration clientConfig = configurationService.getClientConfiguration(object.getClientId().getClientId());
                        		if (clientConfig != null && clientConfig.getUploadStorageDirectory() != null){
                        			fileDir = clientConfig.getUploadStorageDirectory() + File.separator + "payment";
                        			
                        			File check = new File(fileDir);
                        			if (!check.exists()){
                        				check.mkdirs();
                        			}
                        		}
                        	}
                        }
                        else if (result.getTipe().intValue() == DataImportStage.TIPE_GROUP_SUSPEND){
                        	fileDir = configuration.getUploadStorageDirectory() + File.separator + "suspend";
                        	
                        	if (object.getClientId() != null){
                        		Configuration clientConfig = configurationService.getClientConfiguration(object.getClientId().getClientId());
                        		if (clientConfig != null && clientConfig.getUploadStorageDirectory() != null){
                        			fileDir = clientConfig.getUploadStorageDirectory() + File.separator + "suspend";
                        			
                        			File check = new File(fileDir);
                        			if (!check.exists()){
                        				check.mkdirs();
                        			}
                        		}
                        	}
                        }
                        else if (result.getTipe().intValue() == DataImportStage.TIPE_MEMBER_PROVIDER){
                        	fileDir = configuration.getUploadStorageDirectory() + File.separator + "memberprovider";
                        	
                        	if (object.getClientId() != null){
                        		Configuration clientConfig = configurationService.getClientConfiguration(object.getClientId().getClientId());
                        		if (clientConfig != null && clientConfig.getUploadStorageDirectory() != null){
                        			fileDir = clientConfig.getUploadStorageDirectory() + File.separator + "memberprovider";
                        			
                        			File check = new File(fileDir);
                        			if (!check.exists()){
                        				check.mkdirs();
                        			}
                        		}
                        	}
                        }
                        else if (result.getTipe().intValue() == DataImportStage.TIPE_MEMBER_DIAGNOSIS){
                        	fileDir = configuration.getUploadStorageDirectory() + File.separator + "memberdiagnosis";
                        	
                        	if (object.getClientId() != null){
                        		Configuration clientConfig = configurationService.getClientConfiguration(object.getClientId().getClientId());
                        		if (clientConfig != null && clientConfig.getUploadStorageDirectory() != null){
                        			fileDir = clientConfig.getUploadStorageDirectory() + File.separator + "memberdiagnosis";
                        			
                        			File check = new File(fileDir);
                        			if (!check.exists()){
                        				check.mkdirs();
                        			}
                        		}
                        	}
                        }
                        
                        object.setFileDirectory(fileDir);
                        update(object,user);
                        
                        FileOutputStream outstream = new FileOutputStream(fileDir + File.separator + object.getImportReadyFile());
                        outstream.write(fileContent);
                        outstream.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
    /*
     * Method update (DataImportStage object) berfungsi untuk melakukan
     * perubahan terhadap sebuah object yang terdapat didalam database @param
     * object adalah sebuah object yang ingin diubah @return object hasil
     * update, exception jika gagal
     */

    public DataImportStage update(DataImportStage object, ActionUser user) throws Exception {
        object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
        /*
         * Gue tambahin mekanisme NULL value checking just in case user nya null
         */
        if (user != null && user.getUser() != null) {
            object.setModifiedBy(user.getUser().getUsername());
        }


        dataImportStageDao.update(object);
        return object;
    }


    /*
     * Method delete (Object pkey) berfungsi untuk melakukan penghapusan
     * terhadap sebuah object yang terdapat didalam database @param pkey adalah
     * sebuah object yang merepresentasikan primary key dari tabel yang
     * bersangkutan. Object tersebut dapat dalam bentuk single ID maupun
     * composite ID @return no return value karena objeknya sendiri sudah
     * dihapus - just for consistency. Again, exception if failure occured
     * WARNING ! Invalid value for the returned object, better not use it again
     * in any place
     */
    public DataImportStage trash(java.io.Serializable pkey) throws Exception {
        DataImportStage object = dataImportStageDao.get(pkey);
        dataImportStageDao.delete(object);
        return object;
    }

    /*
     * Method delete (DataImportStage object) berfungsi untuk melakukan
     * penghapusan terhadap sebuah object yang terdapat didalam database @param
     * object adalah sebuah object yang ingin dihapus, isi dari object tersebut
     * cukup dengan mengisi field-field primary key @return updated object,
     * exception if failed
     */
    public DataImportStage delete(java.io.Serializable pkey, ActionUser user) throws Exception {
        DataImportStage object = dataImportStageDao.get(pkey);
        
        object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));
        object.setDeletedStatus(new Integer(1));
        
        if (user != null && user.getUser() != null) {
            object.setDeletedBy(user.getUser().getUsername());
        }


        dataImportStageDao.update(object);
        return object;
    }


    /*
     * Method delete (DataImportStage object) berfungsi untuk melakukan
     * penghapusan terhadap sebuah object yang terdapat didalam database @param
     * object adalah sebuah object yang ingin dihapus, isi dari object tersebut
     * cukup dengan mengisi field-field primary key @return updated object,
     * exception if failed
     */
    public DataImportStage delete(DataImportStage object, ActionUser user) throws Exception {

        object.setDeletedStatus(new Integer(1));
        object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));
        if (user != null && user.getUser() != null) {
            object.setDeletedBy(user.getUser().getUsername());
        }

        dataImportStageDao.update(object);
        return object;
    }

// -- get section - carefull !
    /*
     * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
     * sebuah object yang terdapat didalam database @param pkey adalah sebuah
     * object yang merepresentasikan primary key dari tabel yang bersangkutan.
     * Object tersebut dapat dalam bentuk single ID maupun composite ID @return
     * Object yang dihasilkan dari proses retrieval, apabila object tidak
     * ditemukan maka method akan mengembalikan nilai "NULL"
     */
    public DataImportStage get(java.io.Serializable pkey) throws Exception {
        DataImportStage object = null;
        object = dataImportStageDao.get(pkey);
        return object;
    }
    /*
     * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
     * sebuah object yang terdapat didalam database @param pkey adalah sebuah
     * object yang merepresentasikan primary key dari tabel yang bersangkutan.
     * Object tersebut dapat dalam bentuk single ID maupun composite ID @param
     * required adalah array dari field-field yang dibutuhkan dari hibernate
     * object @return Object yang dihasilkan dari proses retrieval, apabila
     * object tidak ditemukan maka method akan mengembalikan nilai "NULL"
     */

    public DataImportStage get(java.io.Serializable pkey, String[] required) throws Exception {
        DataImportStage object = null;
        object = dataImportStageDao.get(pkey);
        DaoSupportUtil.lazyInit(required, object);
        return object;
    }

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        List list = c.list();
        return list;

    }
    //--req

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, String[] required, int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DataImportStage element = (DataImportStage) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;

    }
    // --req end

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        return list;

    }
    //--req

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
            String[] required,
            int index, int offset) throws Exception {
        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DataImportStage element = (DataImportStage) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;

    }
    // --req end

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        return list;
    }
    //--req

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
            String[] required,
            int index, int offset) throws Exception {
        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DataImportStage element = (DataImportStage) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;

    }
    // --req end

// -- 1b
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams, int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        List list = c.list();
        return list;

    }
    //--req

    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams,
            String[] required,
            int index, int offset) throws Exception {
        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DataImportStage element = (DataImportStage) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;

    }
    // --req end

    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams,
            boolean asc, String columnOrder[],
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        return list;

    }
    // req

    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams,
            boolean asc, String columnOrder[],
            String[] required,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DataImportStage element = (DataImportStage) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;

    }
    // req end

    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams,
            boolean asc, String columnOrder,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        return list;

    }
    // req

    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams,
            boolean asc, String columnOrder,
            String[] required,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DataImportStage element = (DataImportStage) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;

    }

    // req end
// -- 2 , between
    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams,
            String[] btwnColumns, Object[] btwnParams1,
            Object[] btwnParams2,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        List list = c.list();
        return list;
    }
    // req

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams,
            String[] btwnColumns, Object[] btwnParams1,
            Object[] btwnParams2,
            String[] required,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DataImportStage element = (DataImportStage) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;
    }
    // req end

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams,
            String[] btwnColumns, Object[] btwnParams1,
            Object[] btwnParams2,
            boolean asc, String columnOrder[],
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        return list;
    }
    // req

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams,
            String[] btwnColumns, Object[] btwnParams1,
            Object[] btwnParams2,
            boolean asc, String columnOrder[],
            String[] required,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DataImportStage element = (DataImportStage) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;
    }
    // req end

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams,
            String[] btwnColumns, Object[] btwnParams1,
            Object[] btwnParams2,
            boolean asc, String columnOrder,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        return list;
    }
    // req

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams,
            String[] btwnColumns, Object[] btwnParams1,
            Object[] btwnParams2,
            boolean asc, String columnOrder,
            String[] required,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DataImportStage element = (DataImportStage) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;
    }

    // req end
    // -- 2b
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams,
            String btwnColumns, Object btwnParams1,
            Object btwnParams2,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        List list = c.list();
        return list;
    }
    // req

    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams,
            String btwnColumns, Object btwnParams1,
            Object btwnParams2,
            String[] required,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DataImportStage element = (DataImportStage) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;
    }
    // req end

    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams,
            String btwnColumns, Object btwnParams1,
            Object btwnParams2,
            boolean asc, String columnOrder[],
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        return list;
    }
    //req

    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams,
            String btwnColumns, Object btwnParams1,
            Object btwnParams2,
            boolean asc, String columnOrder[],
            String[] required,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DataImportStage element = (DataImportStage) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;
    }
    // req end

    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams,
            String btwnColumns, Object btwnParams1,
            Object btwnParams2,
            boolean asc, String columnOrder,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        return list;
    }
    // req

    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams,
            String btwnColumns, Object btwnParams1,
            Object btwnParams2,
            boolean asc, String columnOrder,
            String[] required,
            int index, int offset) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DataImportStage element = (DataImportStage) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;
    }
    // req end

// -- search end
//-- get total
    public int getTotal(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        int total = DaoSupportUtil.getTotal(c);
        return total;

    }

    public int getTotal(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams,
            String[] btwnColumns, Object[] btwnParams1,
            Object[] btwnParams2) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        int total = DaoSupportUtil.getTotal(c);
        return total;

    }

    public int getTotal(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        int total = DaoSupportUtil.getTotal(c);
        return total;

    }

    public int getTotal(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams,
            String btwnColumns, Object btwnParams1,
            Object btwnParams2) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        int total = DaoSupportUtil.getTotal(c);
        return total;

    }
//------------------------------------------------------------------

    public int getTotal() throws Exception {
        Criteria c = dataImportStageDao.getCriteria();
        int total = DaoSupportUtil.getTotal(c);
        return total;
    }
//-- get total end

//---------------------------------------------------
    public Collection getAll(String[] required) throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DataImportStage element = (DataImportStage) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;

    }

    public Collection getAll() throws Exception {

        Criteria c = dataImportStageDao.getCriteria();
        List list = c.list();
        return list;
    }
//-------------------------------------------------

// unique Result
    public DataImportStage searchUnique(String eqColumns, Object eqParams, String[] required)
            throws Exception {
        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DataImportStage obj = (DataImportStage) c.uniqueResult();
        DaoSupportUtil.lazyInit(required, obj);
        return obj;
    }

    public DataImportStage searchUnique(String eqColumns, Object eqParams)
            throws Exception {
        Criteria c = dataImportStageDao.getCriteria();
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DataImportStage obj = (DataImportStage) c.uniqueResult();
        return obj;
    }

// -----------------------------------------------

    /*
     * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
     * @return criteria
     */
    public Criteria getCriteria() throws Exception {
        Criteria c = dataImportStageDao.getCriteria();
        return c;
    }

    public DetachedCriteria getDetachedCriteria() throws Exception {
        DetachedCriteria dc = dataImportStageDao.getDetachedCriteria();
        return dc;
    }

// class+ 
// class- 
    @Override
    public boolean executeAsynchronusMigration(Serializable id) throws Exception {
        boolean result = false;

        return result;
    }

    public boolean executeRawMigrationXLS(DataImportStage data) throws Exception {
    	boolean result = false;    	
    	try {          
            
            Configuration config = configurationService.getSystemConfiguration();            
            if (data != null) {

            	int ver = 2003;
            	if (data.getClientId() != null){
            		config = configurationService.getClientConfiguration(data.getClientId().getClientId());
            	}
                FileInputStream reader = null;
                if (data.getTipe().intValue() == DataImportStage.TIPE_DETAIL_CLAIM || data.getTipe().intValue() == DataImportStage.TIPE_HEADER_CLAIM  ){
                	
                	reader = new FileInputStream(config.getReportPath() + File.separator + data.getImportReadyFile());
            	}
                else if (data.getTipe().intValue() == DataImportStage.TIPE_MEMBER ){
                	reader = new FileInputStream(config.getMemberStoragePath() + File.separator + data.getImportReadyFile());
                }
                else if (data.getTipe().intValue() == DataImportStage.TIPE_PROVIDER ){
                	reader = new FileInputStream(config.getMemberStoragePath() + File.separator + data.getImportReadyFile());
                }
                else if (data.getTipe().intValue() == DataImportStage.TIPE_MEMBER_PROVIDER){
                	reader = new FileInputStream(config.getUploadStorageDirectory() + "" + File.separator + data.getImportReadyFile());
                }
                else if (data.getTipe().intValue() == DataImportStage.TIPE_PAYMENT){
                	reader = new FileInputStream(config.getUploadStorageDirectory() + "" + File.separator +"payment" + File.separator + data.getImportReadyFile());
                }
                else if (data.getTipe().intValue() == DataImportStage.TIPE_GROUP_SUSPEND){
                	reader = new FileInputStream(config.getUploadStorageDirectory() + "" + File.separator +"suspend" + File.separator + data.getImportReadyFile());
                }

                String ext = "xls";
                StringTokenizer tokenizer = new StringTokenizer(data.getImportReadyFile(),".");
                
                while(tokenizer.hasMoreTokens()){
                	ext = tokenizer.nextToken();
                }
                if (ext != null){
                	if (ext.equalsIgnoreCase("xlsx")){
                		ver = 2007;
                	}
                	else if (ext.equalsIgnoreCase("xls")){
                		ver = 2003;
                	}
                }
                
    			ExportImportTemplate template = data.getExportImportTemplate();
    			String mapping = template.getTemplateMapping();
    			String[] mapArray = mapping.split(":");
    			String emptyLine = "";
    			
    			if (mapArray != null){
    				for(int i = 0; i < mapArray.length; i++){
    					if (i < mapArray.length - 1){
    						emptyLine += template.getDelimiter();    		
    					}
    				}
    			}
    			
    			Iterator<Row> rows = null;
    			
    			
    			if (ver == 2003){
    				HSSFWorkbook wb = new HSSFWorkbook(reader);
    				HSSFSheet sheet = wb.getSheetAt(0);
    				rows = sheet.rowIterator();
    			}
    			else if (ver == 2007){
    				XSSFWorkbook wb = new XSSFWorkbook(reader);
    				XSSFSheet sheet = wb.getSheetAt(0);
    				rows = sheet.iterator();
    			}
    			
    			
    			
    			
    			int i = 0;
    				
                

                ExternalRawData claimHeaderRaw = null;
                ExternalRawDetailData claimDetailRaw = null;
                ExternalRawData memberRawData = null;
                ExternalRawData providerRawData = null;
                ExternalRawData paymentRawData = null;
                ExternalRawData groupSuspendRawData = null;
                
                int isFirstLineHeader = data.getFirstLineHeader() == null ? 1 : data.getFirstLineHeader().intValue();
                
                while (rows.hasNext()) {
                	Row row = rows.next();
                	
                	if (isFirstLineHeader == 1 && i > 0){
	                	String line = "";
	    				
						int totalToken = 0;
						
						for (int x = 0; x < mapArray.length; x++){
							Cell cell = row.getCell(x);
							String cellVal = "";
							if (cell != null){
								int tipe = cell.getCellType();
								if (tipe == Cell.CELL_TYPE_NUMERIC){
									if (DateUtil.isCellDateFormatted(cell)){
										java.util.Date dateVal = cell.getDateCellValue();
										
										if (dateVal != null){
											Date sqlDate = new java.sql.Date(dateVal.getTime());
											cellVal = sqlDate.toString();
										}										
									}
									else {
										double num = cell.getNumericCellValue();
										java.math.BigDecimal bigDec = new java.math.BigDecimal(num);
										cellVal = bigDec.toPlainString();
									}								
								}
								if (tipe == Cell.CELL_TYPE_BOOLEAN){
									cellVal = cell.getBooleanCellValue()+"";
								}
								if (tipe == Cell.CELL_TYPE_STRING){
									cellVal = cell.getStringCellValue();
								}
							}
							if (x == 0){
								line += cellVal;
							}						
							else {
								line += template.getDelimiter() + cellVal;
							}
						}                    
	                	
	                    if (data.getTipe().intValue() == DataImportStage.TIPE_HEADER_CLAIM) {
	
	                    	if (!line.equalsIgnoreCase(emptyLine)){
		                        claimHeaderRaw = new ExternalRawData();
		                        claimHeaderRaw.setIsMigrated(0);
		                        claimHeaderRaw.setTemplateId(data.getExportImportTemplate());
		                        claimHeaderRaw.setRawData(line);
		                        claimHeaderRaw.setImportSessionId(data);
		                        claimHeaderRaw.setTotalToken(totalToken);
		
		                        claimHeaderRaw.setCreatedBy("system-daemon");
		                        claimHeaderRaw.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		                        claimHeaderRaw.setDeletedStatus(Integer.valueOf(0));
		
		                        
		                        
		                        externalRawDataService.create(claimHeaderRaw, null);
	                    	}
	                    }
	                    if (data.getTipe().intValue() == DataImportStage.TIPE_DETAIL_CLAIM) {
	
	                    	if (!line.equalsIgnoreCase(emptyLine)){
		                        claimDetailRaw = new ExternalRawDetailData();
		                        claimDetailRaw.setIsMigrated(0);
		                        claimDetailRaw.setTemplateId(data.getExportImportTemplate());
		                        claimDetailRaw.setData(line);
		                        claimDetailRaw.setImportSessionId((Integer) data.getId());
		                        
		
		
		                        externalRawDetailDataService.create(claimDetailRaw, null);
	                    	}
	                    }
	                    if (data.getTipe().intValue() == DataImportStage.TIPE_MEMBER) {
	                    	if (!line.equalsIgnoreCase(emptyLine)){
		                    	memberRawData = new ExternalRawData();
		                    	memberRawData.setIsMigrated(0);
		                    	memberRawData.setTemplateId(data.getExportImportTemplate());
		                    	memberRawData.setRawData(line);
		                    	memberRawData.setImportSessionId(data);
		                    	if (data.getMovementId() != null){
			                    	memberRawData.setMovementId(data.getMovementId());
			                    	memberRawData.setPolicyId(data.getMovementId().getPolicyId());
		                    	}
		                    	memberRawData.setCreatedBy("system-daemon");
		                    	memberRawData.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		                    	memberRawData.setDeletedStatus(Integer.valueOf(0));
		                    	memberRawData.setTotalToken(totalToken);
		                    	
		
		                        externalRawDataService.create(memberRawData, null);
	                    	}
	                    }
	                    if (data.getTipe().intValue() == DataImportStage.TIPE_PROVIDER){
	                    	if (!line.equalsIgnoreCase(emptyLine)){
		                    	providerRawData = new ExternalRawData();
		                    	providerRawData.setIsMigrated(0);
		                    	providerRawData.setTemplateId(data.getExportImportTemplate());
		                    	providerRawData.setRawData(line);
		                    	providerRawData.setImportSessionId(data);
		                    	providerRawData.setCreatedBy("system-daemon");
		                    	providerRawData.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		                    	providerRawData.setDeletedStatus(Integer.valueOf(0));
		                    	providerRawData.setTotalToken(totalToken);
		
		                        externalRawDataService.create(providerRawData, null);
	                    	}
	                    }
	                    if (data.getTipe().intValue() == DataImportStage.TIPE_PAYMENT){
	                    	System.out.println("EXTRACTING DATA PAYMENT = " + line);
	                    	paymentRawData = new ExternalRawData();
	                    	paymentRawData.setIsMigrated(0);
	                    	paymentRawData.setTemplateId(data.getExportImportTemplate());
	                    	paymentRawData.setRawData(line);
	                    	paymentRawData.setImportSessionId(data);
	                    	paymentRawData.setCreatedBy("system-daemon");
	                    	paymentRawData.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
	                    	paymentRawData.setDeletedStatus(Integer.valueOf(0));
	                    	paymentRawData.setTotalToken(totalToken);

	                        externalRawDataService.create(paymentRawData, null);
	                    }
	                    
	                    if (data.getTipe().intValue() == DataImportStage.TIPE_GROUP_SUSPEND){
	                    	System.out.println("EXTRACTING DATA SUSPEND = " + line);
	                    	groupSuspendRawData = new ExternalRawData();
	                    	groupSuspendRawData.setIsMigrated(0);
	                    	groupSuspendRawData.setTemplateId(data.getExportImportTemplate());
	                    	groupSuspendRawData.setRawData(line);
	                    	groupSuspendRawData.setImportSessionId(data);
	                    	groupSuspendRawData.setCreatedBy("system-daemon");
	                    	groupSuspendRawData.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
	                    	groupSuspendRawData.setDeletedStatus(Integer.valueOf(0));
	                    	groupSuspendRawData.setTotalToken(totalToken);

	                        externalRawDataService.create(groupSuspendRawData, null);
	                    }
                	}
                    i++;

                }
                result = true;
                
                data.setStatus(DataImportStage.STATUS_EXTRACT);
                dataImportStageDao.update(data);
            

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	return result;
    }
    public boolean executeRawMigration(Serializable id) throws Exception {
    	boolean result = false;
    	try {
    		DataImportStage data = dataImportStageDao.get(id);
    		if (data != null){
    			if (data.getFileFormat().equalsIgnoreCase("xls") || data.getFileFormat().equalsIgnoreCase("xlsx")){
    				System.out.println("EXECUTE RAW XLS MIGRATION");
    				result = executeRawMigrationXLS(data);
    			}
    			else if (data.getFileFormat().equalsIgnoreCase("csv")){
    				System.out.println("EXECUTE RAW CSV MIGRATION");
    				result = executeRawMigrationCSV(data);
    			}
    			
    		}
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    	return result;
    }
    
    public boolean executeRawMigrationCSV(DataImportStage data) throws Exception {
        boolean result = false;

        try {           
            
            Configuration config = configurationService.getClientConfiguration(data.getClientId().getClientId());

            
            if (data != null) {

            	if (data.getClientId() != null){
            		config = configurationService.getClientConfiguration(data.getClientId().getClientId());
            	}
                FileReader reader = null;
                if (data.getTipe().intValue() == DataImportStage.TIPE_DETAIL_CLAIM || data.getTipe().intValue() == DataImportStage.TIPE_HEADER_CLAIM  ){
                	
                	reader = new FileReader(config.getUploadStorageDirectory() + File.separator + "claim" + data.getImportReadyFile());
            	}
                else if (data.getTipe().intValue() == DataImportStage.TIPE_MEMBER ){
                	reader = new FileReader(config.getUploadStorageDirectory() + File.separator  + "membermovement" + File.separator+ data.getImportReadyFile());
                }
                else if (data.getTipe().intValue() == DataImportStage.TIPE_PROVIDER ){
                	reader = new FileReader(config.getUploadStorageDirectory() + File.separator  +"provider" + File.separator+ data.getImportReadyFile());
                }
                else if (data.getTipe().intValue() == DataImportStage.TIPE_MEMBER_PROVIDER){
                	reader = new FileReader(config.getUploadStorageDirectory() + "" + File.separator + data.getImportReadyFile());
                }
                else if (data.getTipe().intValue() == DataImportStage.TIPE_PAYMENT){
                	reader = new FileReader(config.getUploadStorageDirectory() + "" + File.separator +"payment" + File.separator + data.getImportReadyFile());
                }
                else if (data.getTipe().intValue() == DataImportStage.TIPE_GROUP_SUSPEND){
                	reader = new FileReader(config.getUploadStorageDirectory() + "" + File.separator +"suspend" + File.separator + data.getImportReadyFile());
                }
                
                
                BufferedReader buffReader = new BufferedReader(reader);
                String line = "";

                ExternalRawData claimHeaderRaw = null;
                ExternalRawDetailData claimDetailRaw = null;
                ExternalRawData memberRawData = null;
                ExternalRawData providerRawData = null;
                ExternalRawData paymentRawData = null;
                ExternalRawData groupSuspendRawData = null;

                while ((line = buffReader.readLine()) != null) {

                	String rawData = line;
                    while (rawData.contains("||")){
                       	rawData = rawData.replace("||", "| |");
                    }
                    StringTokenizer tokenizer = new StringTokenizer(rawData,"|");
                    int totalToken = tokenizer.countTokens();
                	
                    if (data.getTipe().intValue() == DataImportStage.TIPE_HEADER_CLAIM) {

                        claimHeaderRaw = new ExternalRawData();
                        claimHeaderRaw.setIsMigrated(0);
                        claimHeaderRaw.setTemplateId(data.getExportImportTemplate());
                        claimHeaderRaw.setRawData(line);
                        claimHeaderRaw.setImportSessionId(data);
                        claimHeaderRaw.setTotalToken(totalToken);

                        claimHeaderRaw.setCreatedBy("system-daemon");
                        claimHeaderRaw.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
                        claimHeaderRaw.setDeletedStatus(Integer.valueOf(0));

                        externalRawDataService.create(claimHeaderRaw, null);
                    }
                    if (data.getTipe().intValue() == DataImportStage.TIPE_DETAIL_CLAIM) {

                        claimDetailRaw = new ExternalRawDetailData();
                        claimDetailRaw.setIsMigrated(0);
                        claimDetailRaw.setTemplateId(data.getExportImportTemplate());
                        claimDetailRaw.setData(line);
                        claimDetailRaw.setImportSessionId((Integer) data.getId());
                        
                        externalRawDetailDataService.create(claimDetailRaw, null);
                    }
                    if (data.getTipe().intValue() == DataImportStage.TIPE_MEMBER) {
                    	memberRawData = new ExternalRawData();
                    	memberRawData.setIsMigrated(0);
                    	memberRawData.setTemplateId(data.getExportImportTemplate());
                    	memberRawData.setRawData(line);
                    	memberRawData.setImportSessionId(data);
                    	if (data.getMovementId() != null){
	                    	memberRawData.setMovementId(data.getMovementId());	                    	
	                    	memberRawData.setPolicyId(data.getMovementId().getPolicyId());
                    	}
                    	memberRawData.setCreatedBy("system-daemon");
                    	memberRawData.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
                    	memberRawData.setDeletedStatus(Integer.valueOf(0));
                    	memberRawData.setTotalToken(totalToken);
                    	

                        externalRawDataService.create(memberRawData, null);
                    }
                    if (data.getTipe().intValue() == DataImportStage.TIPE_PROVIDER){
                    	providerRawData = new ExternalRawData();
                    	providerRawData.setIsMigrated(0);
                    	providerRawData.setTemplateId(data.getExportImportTemplate());
                    	providerRawData.setRawData(line);
                    	providerRawData.setImportSessionId(data);
                    	providerRawData.setCreatedBy("system-daemon");
                    	providerRawData.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
                    	providerRawData.setDeletedStatus(Integer.valueOf(0));
                    	providerRawData.setTotalToken(totalToken);

                        externalRawDataService.create(providerRawData, null);
                    }
                    
                    if (data.getTipe().intValue() == DataImportStage.TIPE_PAYMENT){
                    	paymentRawData = new ExternalRawData();
                    	paymentRawData.setIsMigrated(0);
                    	paymentRawData.setTemplateId(data.getExportImportTemplate());
                    	paymentRawData.setRawData(line);
                    	paymentRawData.setImportSessionId(data);
                    	paymentRawData.setCreatedBy("system-daemon");
                    	paymentRawData.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
                    	paymentRawData.setDeletedStatus(Integer.valueOf(0));
                    	paymentRawData.setTotalToken(totalToken);

                        externalRawDataService.create(paymentRawData, null);
                    }
                    
                    if (data.getTipe().intValue() == DataImportStage.TIPE_GROUP_SUSPEND){
                    	groupSuspendRawData = new ExternalRawData();
                    	groupSuspendRawData.setIsMigrated(0);
                    	groupSuspendRawData.setTemplateId(data.getExportImportTemplate());
                    	groupSuspendRawData.setRawData(line);
                    	groupSuspendRawData.setImportSessionId(data);
                    	groupSuspendRawData.setCreatedBy("system-daemon");
                    	groupSuspendRawData.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
                    	groupSuspendRawData.setDeletedStatus(Integer.valueOf(0));
                    	groupSuspendRawData.setTotalToken(totalToken);

                        externalRawDataService.create(groupSuspendRawData, null);
                    }

                }
                result = true;
                
                data.setStatus(DataImportStage.STATUS_EXTRACT);
                dataImportStageDao.update(data);
            

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean executeMigration(Serializable id) throws Exception {
    	boolean result = false;
    	
    	try {
    		DataImportStage data = dataImportStageDao.get(id);
    		if (data != null){
    			if (data.getFileFormat().equalsIgnoreCase("csv")){
    				result = executeMigrationCSV(data);
    			}
    			else {
    				result = executeMigrationXLS(data);
    			}
    		}
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    	
    	return result;
    }
    public boolean executeMigrationCSV(DataImportStage data) throws Exception {
        boolean result = false;

        try {
        	
            System.out.println("Execute Data Migration");
            if (data != null) {

                if (data.getTipe().intValue() == DataImportStage.TIPE_HEADER_CLAIM) {
                    int total = externalRawDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawData> raws = externalRawDataService.search(null, null,"importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split(":");

                    for (Iterator<ExternalRawData> it = raws.iterator(); it.hasNext();) {
                        
                        ExternalRawData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Data : " + externalRawData.getRawData());
                            String rawData = externalRawData.getRawData().replace("\",\"",template.getDelimiter());
                            String[] dataArray = rawData.split(template.getDelimiter());

                            ExternalClaim claim = new ExternalClaim();
                            claim.setImportSessionId(data.getId());

                            for (int i = 0; i < mappingValue.length; i++) {
                                claim.setValue(mappingValue[i], dataArray[i]);
                            }
                            externalClaimService.create(claim, null);
                        }
                    }
                }
                if (data.getTipe().intValue() == DataImportStage.TIPE_DETAIL_CLAIM) {
                    int total = externalRawDetailDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawDetailData> raws = externalRawDetailDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split( ":");

                    for (Iterator<ExternalRawDetailData> it = raws.iterator(); it.hasNext();) {
                        ExternalRawDetailData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Detail Data : " + externalRawData.getData());
                            String rawData = externalRawData.getData().replace("\",\"", template.getDelimiter());
                            String[] dataArray = rawData.split(template.getDelimiter());

                            ExternalClaimDetail claim = new ExternalClaimDetail();
                            claim.setImportSessionId(data.getId());

                            for (int i = 0; i < mappingValue.length; i++) {
                                claim.setValue(mappingValue[i], dataArray[i]);
                            }

                            externalClaimDetailService.create(claim, null);
                        }
                    }
                }
                if (data.getTipe().intValue() == DataImportStage.TIPE_MEMBER){
                	
                	int total = externalRawDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawData> raws = externalRawDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split( ":");

                    for (Iterator<ExternalRawData> it = raws.iterator(); it.hasNext();) {
                        
                        ExternalRawData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Data : " + externalRawData.getRawData());
                            String rawData = externalRawData.getRawData();
                            while (rawData.contains("||")){
                            	rawData = rawData.replace("||", "| |");
                            }
                            StringTokenizer tokenizer = new StringTokenizer(rawData,"|");
                            int totalToken = tokenizer.countTokens();
                            
                            int idx = 0;
                            if (totalToken == mappingValue.length){

	                            MemberImport member = new MemberImport();
	                            member.setImportSessionId(data);
	                            member.setCreatedBy("system-daemon");
	                            member.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
	                            member.setDeletedStatus(Integer.valueOf(0));
	
	                            
	                            while(tokenizer.hasMoreElements()){
	                            	String nextToken = tokenizer.nextToken();
	                            	String currentHeader = mappingValue[idx];
	                            	System.out.println(currentHeader + " adalah " + nextToken);
	                            	member.setValue(currentHeader, nextToken);
	                            	
	                            	idx++;
	                            }
	
	                            memberImportService.create(member, null);
                            }
                        }
                    }                    
                }        
                if (data.getTipe().intValue() == DataImportStage.TIPE_PROVIDER){
                	
                	int total = externalRawDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawData> raws = externalRawDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split( ":");

                    for (Iterator<ExternalRawData> it = raws.iterator(); it.hasNext();) {
                        
                        ExternalRawData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Data : " + externalRawData.getRawData());
                            String rawData = externalRawData.getRawData();
                            while (rawData.contains("||")){
                            	rawData = rawData.replace("||", "| |");
                            }
                            
                            StringTokenizer tokenizer = new StringTokenizer(rawData,"|");
                            int totalToken = tokenizer.countTokens();
                            
                            int idx = 0;
                            if (totalToken == mappingValue.length){
                            	
	                            Provider provider = new Provider();	                            
	                            provider.setCreatedBy("system-daemon");
	                            provider.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
	                            provider.setDeletedStatus(Integer.valueOf(0));
	
	                            
	                            while(tokenizer.hasMoreElements()){
	                            	String nextToken = tokenizer.nextToken();
	                            	String currentHeader = mappingValue[idx];
	                            	System.out.println(currentHeader + " adalah " + nextToken.trim());
	                            	provider.setValue(currentHeader, nextToken.trim());
	                            	
	                            	idx++;
	                            }
	
	                            providerService.create(provider, null);
                            }
                        }
                    }                    
                }
                if (data.getTipe().intValue() == DataImportStage.TIPE_PAYMENT){                	
                	int total = externalRawDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawData> raws = externalRawDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split( ":");

                    for (Iterator<ExternalRawData> it = raws.iterator(); it.hasNext();) {
                        
                        ExternalRawData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Data : " + externalRawData.getRawData());
                            String rawData = externalRawData.getRawData();
                            while (rawData.contains("||")){
                            	rawData = rawData.replace("||", "| |");
                            }
                            
                            StringTokenizer tokenizer = new StringTokenizer(rawData,"|");
                            int totalToken = tokenizer.countTokens();
                            
                            int idx = 0;
                            if (totalToken == mappingValue.length){
                            	
	                            
                            	
                            	ExternalClaim claim = new ExternalClaim();
                                claim.setImportSessionId(data.getId());

	                            while(tokenizer.hasMoreElements()){
	                            	String nextToken = tokenizer.nextToken();
	                            	String currentHeader = mappingValue[idx];
	                            	System.out.println(currentHeader + " adalah " + nextToken.trim());
	                            	claim.setValue(currentHeader, nextToken.trim());
	                            	
	                            	idx++;
	                            }
	                            
                                externalClaimService.create(claim, null);	
                                
                                if (claim != null){
                                	Client client = clientService.getClient(claim.getClientCode());
                                	
                                	if (client != null){
                                		String[] eqParam = {"claimNumber","deletedStatus","clientId"};
                                		Object[] eqValue = {claim.getClaimNumber(),0,client.getClientId()};
                                		
                                		Collection<Claim> claimList = claimService.search(null,null,eqParam,eqValue,0,1);
                                		
                                		if (claimList != null && claimList.size() > 0){
                                			Iterator<Claim> iterator = claimList.iterator();
                                			
                                			if (iterator.hasNext()){
                                				Claim realClaim = iterator.next();
                                				
                                				if (realClaim != null){
                                					realClaim.setBankCodeFromClient(claim.getBankCode());
                                					realClaim.setBankNameFromClient(claim.getBankName());
                                					realClaim.setAccountHolderNameFromClient(claim.getBankAccount());
                                					realClaim.setAccountNumberFromClient(claim.getAccountNo());
                                					realClaim.setClientPaymentVoucherNumber(claim.getPaymentVoucherNo());
                                					realClaim.setClientPaymentVoucherDate(claim.getPaymentVoucherDate());
                                					realClaim.setClientPaidApprovalAmount(claim.getAmountTotal().toString());
                                					
                                					claimService.update(realClaim, null);
                                				}
                                			}
                                		}
                                	}
                                }	                            
                            }
                        }
                    }                    
                }
                if (data.getTipe().intValue() == DataImportStage.TIPE_GROUP_SUSPEND){                	
                	int total = externalRawDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawData> raws = externalRawDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split( ":");

                    for (Iterator<ExternalRawData> it = raws.iterator(); it.hasNext();) {
                        
                        ExternalRawData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Data : " + externalRawData.getRawData());
                            String rawData = externalRawData.getRawData();
                            while (rawData.contains("||")){
                            	rawData = rawData.replace("||", "| |");
                            }
                            
                            StringTokenizer tokenizer = new StringTokenizer(rawData,"|");
                            int totalToken = tokenizer.countTokens();
                            
                            int idx = 0;
                            if (totalToken == mappingValue.length){
                            	
                            	MemberGroup group = new MemberGroup();                                

	                            while(tokenizer.hasMoreElements()){
	                            	String nextToken = tokenizer.nextToken();
	                            	String currentHeader = mappingValue[idx];
	                            	System.out.println(currentHeader + " adalah " + nextToken.trim());
	                            	group.setValue(currentHeader, nextToken.trim());
	                            	
	                            	idx++;
	                            }


                            	ActionUser user = new ActionUser();
                            	User theUser = new User();
                            	theUser.setUsername("system-daemon");
                            	user.setUser(theUser);
	                            
	                            MemberGroup toSuspend = memberGroupService.getMemberGroupByCode(group.getMemberGroupCode());	
                                String policyNumber = group.getPolicyNumber();
                                String clientCode = group.getClientCode();
                                String actionType = group.getActionType();
                                
                                Client client = clientService.getClient(clientCode);
                                
                                if (actionType != null && actionType.equalsIgnoreCase("SUSPEND")){
                                	if (policyNumber == null || policyNumber.equalsIgnoreCase("")){
		                                if (toSuspend != null ){                    			                                	
		                                	memberGroupService.block(toSuspend.getMemberGroupId(), group.getSuspendReason(),group.getSuspendDate(),group.getSuspendEndDate(), user);		                                	
		                                }	                      
                                	}
                                	else if (policyNumber != null && !policyNumber.equalsIgnoreCase("")){
                                		Policy policy = policyService.getPolicyByNumber(policyNumber, client.getClientId());                                		
                                		if (policy != null){
                                			policyService.block(policy.getPolicyId(), group.getSuspendDate(),group.getSuspendEndDate(), group.getSuspendReason(),user);
                                		}
                                	}
                                }
                                else {
                                	if (policyNumber == null || policyNumber.equalsIgnoreCase("")){
		                                if (toSuspend != null ){
		                                	memberGroupService.unblock(toSuspend.getMemberGroupId(), group.getSuspendReason(), user);		                                	
		                                }	                      
                                	}
                                	else if (policyNumber != null && !policyNumber.equalsIgnoreCase("")){
                                		Policy policy = policyService.getPolicyByNumber(policyNumber, client.getClientId());
                                		
                                		if (policy != null){
                                			policyService.unblock(policy.getPolicyId(), group.getSuspendReason(), user);
                                		}
                                	}
                                }                        
                            }
                        }
                    }                    
                }
                if (data.getTipe().intValue() == DataImportStage.TIPE_MEMBER_PROVIDER){
                	
                	int total = externalRawDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawData> raws = externalRawDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split( ":");

                    for (Iterator<ExternalRawData> it = raws.iterator(); it.hasNext();) {
                        
                        ExternalRawData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Data : " + externalRawData.getRawData());
                            String rawData = externalRawData.getRawData();
                            while (rawData.contains("||")){
                            	rawData = rawData.replace("||", "| |");
                            }
                            
                            StringTokenizer tokenizer = new StringTokenizer(rawData,"|");
                            int totalToken = tokenizer.countTokens();
                            
                            int idx = 0;
                            if (totalToken == mappingValue.length){
                            	
	                            MemberProvider provider = new MemberProvider();	                            
	                            provider.setCreatedBy("system-daemon");
	                            provider.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
	                            provider.setDeletedStatus(Integer.valueOf(0));
	
	                            
	                            while(tokenizer.hasMoreElements()){
	                            	String nextToken = tokenizer.nextToken();
	                            	String currentHeader = mappingValue[idx];
	                            	System.out.println(currentHeader + " adalah " + nextToken.trim());
	                            	//provider.setValue(currentHeader, nextToken.trim());
	                            	
	                            	idx++;
	                            }	
	                            //providerService.create(provider, null);
                            }
                        }
                    }                    
                }
                result = true;
                data.setStatus(DataImportStage.STATUS_MIGRATE);
                dataImportStageDao.update(data);


            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean executeMigrationXLS(DataImportStage data) throws Exception {
        boolean result = false;

        try {
            

            System.out.println("Execute Data Migration");
            
            
            if (data != null) {

                if (data.getTipe().intValue() == DataImportStage.TIPE_HEADER_CLAIM) {

                    int total = externalRawDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawData> raws = externalRawDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split(":");

                    for (Iterator<ExternalRawData> it = raws.iterator(); it.hasNext();) {
                        
                        ExternalRawData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Data : " + externalRawData.getRawData());
                            String rawData = externalRawData.getRawData();
                            
                            String[] dataArray = rawData.split(template.getDelimiter());
                            	
                            while (rawData.contains("||")){
                            	rawData = rawData.replace("||", "| |");
                            }
                            
                            StringTokenizer tokenizer = new StringTokenizer(rawData,template.getDelimiter());
                            
                            ExternalClaim claim = new ExternalClaim();
                            claim.setImportSessionId(data.getId());

                            for (int i = 0; i < mappingValue.length; i++) {
                            	String token = tokenizer.nextToken();
                            	claim.setValue(mappingValue[i], token);
                                //claim.setValue(mappingValue[i], dataArray[i]);
                            }

                            externalClaimService.create(claim, null);
                        }

                    }
                }
                if (data.getTipe().intValue() == DataImportStage.TIPE_DETAIL_CLAIM) {
                    int total = externalRawDetailDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawDetailData> raws = externalRawDetailDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split( ":");

                    for (Iterator<ExternalRawDetailData> it = raws.iterator(); it.hasNext();) {
                        ExternalRawDetailData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Detail Data : " + externalRawData.getData());
                            String rawData = externalRawData.getData();
                            String[] dataArray = rawData.split(template.getDelimiter());

                            ExternalClaimDetail claim = new ExternalClaimDetail();
                            claim.setImportSessionId(data.getId());

                            for (int i = 0; i < mappingValue.length; i++) {
                                claim.setValue(mappingValue[i], dataArray[i]);
                            }

                            externalClaimDetailService.create(claim, null);
                        }

                    }
                }
                if (data.getTipe().intValue() == DataImportStage.TIPE_MEMBER){
                	
                	int total = externalRawDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawData> raws = externalRawDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split( ":");

                    for (Iterator<ExternalRawData> it = raws.iterator(); it.hasNext();) {
                        
                        ExternalRawData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Data : " + externalRawData.getRawData());
                            String rawData = externalRawData.getRawData();
                            while (rawData.contains("||")){
                            	rawData = rawData.replace("||", "| |");
                            }
                            StringTokenizer tokenizer = new StringTokenizer(rawData,"|");
                            int totalToken = tokenizer.countTokens();
                            
                            int idx = 0;
                            System.out.println("TOTAL TOKEN = " + totalToken + " MAPPING LENGTH = " + mappingValue.length);
                            if (totalToken == mappingValue.length){

	                            MemberImport member = new MemberImport();
	                            member.setImportSessionId(data);
	                            member.setCreatedBy("system-daemon");
	                            member.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
	                            member.setDeletedStatus(Integer.valueOf(0));
	
	                            
	                            while(tokenizer.hasMoreElements()){
	                            	String nextToken = tokenizer.nextToken();
	                            	String currentHeader = mappingValue[idx];
	                            	System.out.println(currentHeader + " adalah " + nextToken);
	                            	member.setValue(currentHeader, nextToken);
	                            	
	                            	idx++;
	                            }
	
	                            memberImportService.create(member, null);
                            }
                        }
                    }                    
                }        
                if (data.getTipe().intValue() == DataImportStage.TIPE_PROVIDER){
                	
                	int total = externalRawDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawData> raws = externalRawDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split( ":");

                    for (Iterator<ExternalRawData> it = raws.iterator(); it.hasNext();) {
                        
                        ExternalRawData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Data : " + externalRawData.getRawData());
                            String rawData = externalRawData.getRawData();
                            while (rawData.contains("||")){
                            	rawData = rawData.replace("||", "| |");
                            }
                            
                            StringTokenizer tokenizer = new StringTokenizer(rawData,"|");
                            int totalToken = tokenizer.countTokens();
                            
                            int idx = 0;
                            if (totalToken == mappingValue.length){
                            	
	                            Provider provider = new Provider();	                            
	                            provider.setCreatedBy("system-daemon");
	                            provider.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
	                            provider.setDeletedStatus(Integer.valueOf(0));
	
	                            
	                            while(tokenizer.hasMoreElements()){
	                            	String nextToken = tokenizer.nextToken();
	                            	String currentHeader = mappingValue[idx];
	                            	System.out.println(currentHeader + " adalah " + nextToken.trim());
	                            	provider.setValue(currentHeader, nextToken.trim());
	                            	
	                            	idx++;
	                            }
	
	                            providerService.create(provider, null);
                            }
                        }

                    }
                    
                }
                if (data.getTipe().intValue() == DataImportStage.TIPE_PAYMENT){                	
                	int total = externalRawDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawData> raws = externalRawDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split( ":");

                    for (Iterator<ExternalRawData> it = raws.iterator(); it.hasNext();) {
                        
                        ExternalRawData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Data : " + externalRawData.getRawData());
                            String rawData = externalRawData.getRawData();
                            while (rawData.contains("||")){
                            	rawData = rawData.replace("||", "| |");
                            }
                            
                            StringTokenizer tokenizer = new StringTokenizer(rawData,"|");
                            int totalToken = tokenizer.countTokens();
                            
                            int idx = 0;
                            if (totalToken == mappingValue.length){
                            	
                            	ExternalClaim claim = new ExternalClaim();
                                claim.setImportSessionId(data.getId());

	                            while(tokenizer.hasMoreElements()){
	                            	String nextToken = tokenizer.nextToken();
	                            	String currentHeader = mappingValue[idx];
	                            	System.out.println(currentHeader + " adalah " + nextToken.trim());
	                            	claim.setValue(currentHeader, nextToken.trim());
	                            	
	                            	idx++;
	                            }
	                            
                                externalClaimService.create(claim, null);	
                                
                                if (claim != null){
                                	Client client = data.getClientId();
                                	
                                	if (client != null){
                                		String[] eqParam = {"claimNumber","deletedStatus","clientId"};
                                		Object[] eqValue = {claim.getClaimNumber(),0,client.getClientId()};
                                		
                                		System.out.println("GETTING REAL CLAIM TO PAYMENT UPDATE");
                                		Collection<Claim> claimList = claimService.search(null,null,eqParam,eqValue,0,1);
                                		String[] required ={"Claim.DiagnosisId","Claim.Diagnosis2Id","Claim.Diagnosis3Id","Claim.MemberId",
                                				"Claim.MemberId.ParentMember","Claim.MemberId.ClientId","Claim.MemberId.MemberGroupId",
                                				"Claim.ClaimTypeId","Claim.PaymentId","Claim.BatchClaimId"};
                                		
                                		if (claimList != null && claimList.size() > 0){
                                			Iterator<Claim> iterator = claimList.iterator();
                                			
                                			if (iterator.hasNext()){
                                				Claim realClaim = iterator.next();
                                				
                                				if (realClaim != null){
                                					realClaim = claimService.get(realClaim.getClaimId(),required);
                                					
                                					System.out.println("REAL CLAIM TO UPDATE = " + realClaim.getClaimNumber());
                                					realClaim.setBankCodeFromClient(claim.getBankCode());
                                					realClaim.setBankNameFromClient(claim.getBankName());
                                					realClaim.setAccountHolderNameFromClient(claim.getBankAccount());
                                					realClaim.setAccountNumberFromClient(claim.getAccountNo());
                                					realClaim.setClientPaymentVoucherNumber(claim.getPaymentVoucherNo());
                                					realClaim.setClientPaymentVoucherDate(claim.getPaymentVoucherDate());
                                					realClaim.setClientPaidApprovalAmount(claim.getAmountTotal().toString());
                                					
                                					claimService.update(realClaim, null);
                                				}
                                			}
                                		}
                                	}
                                }	                            
                            }
                        }
                    }                    
                }
                if (data.getTipe().intValue() == DataImportStage.TIPE_PRODUCT_UPLOAD){                	
                	int total = externalRawDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawData> raws = externalRawDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split( ":");

                    for (Iterator<ExternalRawData> it = raws.iterator(); it.hasNext();) {
                        
                        ExternalRawData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Data : " + externalRawData.getRawData());
                            String rawData = externalRawData.getRawData();
                            while (rawData.contains("||")){
                            	rawData = rawData.replace("||", "| |");
                            }
                            
                            StringTokenizer tokenizer = new StringTokenizer(rawData,"|");
                            int totalToken = tokenizer.countTokens();
                            
                            int idx = 0;
                            if (totalToken == mappingValue.length){
                            	
                            	ExternalClaim claim = new ExternalClaim();
                                claim.setImportSessionId(data.getId());

	                            while(tokenizer.hasMoreElements()){
	                            	String nextToken = tokenizer.nextToken();
	                            	String currentHeader = mappingValue[idx];
	                            	System.out.println(currentHeader + " adalah " + nextToken.trim());
	                            	claim.setValue(currentHeader, nextToken.trim());
	                            	
	                            	idx++;
	                            }
	                            
                                externalClaimService.create(claim, null);	
                                
                                if (claim != null){
                                	Client client = data.getClientId();
                                	
                                	if (client != null){
                                		String[] eqParam = {"claimNumber","deletedStatus","clientId"};
                                		Object[] eqValue = {claim.getClaimNumber(),0,client.getClientId()};
                                		
                                		System.out.println("GETTING REAL CLAIM TO PAYMENT UPDATE");
                                		Collection<Claim> claimList = claimService.search(null,null,eqParam,eqValue,0,1);
                                		String[] required ={"Claim.DiagnosisId","Claim.Diagnosis2Id","Claim.Diagnosis3Id","Claim.MemberId",
                                				"Claim.MemberId.ParentMember","Claim.MemberId.ClientId","Claim.MemberId.MemberGroupId",
                                				"Claim.ClaimTypeId","Claim.PaymentId","Claim.BatchClaimId"};
                                		
                                		if (claimList != null && claimList.size() > 0){
                                			Iterator<Claim> iterator = claimList.iterator();
                                			
                                			if (iterator.hasNext()){
                                				Claim realClaim = iterator.next();
                                				
                                				if (realClaim != null){
                                					realClaim = claimService.get(realClaim.getClaimId(),required);
                                					
                                					System.out.println("REAL CLAIM TO UPDATE = " + realClaim.getClaimNumber());
                                					realClaim.setBankCodeFromClient(claim.getBankCode());
                                					realClaim.setBankNameFromClient(claim.getBankName());
                                					realClaim.setAccountHolderNameFromClient(claim.getBankAccount());
                                					realClaim.setAccountNumberFromClient(claim.getAccountNo());
                                					realClaim.setClientPaymentVoucherNumber(claim.getPaymentVoucherNo());
                                					realClaim.setClientPaymentVoucherDate(claim.getPaymentVoucherDate());
                                					realClaim.setClientPaidApprovalAmount(claim.getAmountTotal().toString());
                                					
                                					claimService.update(realClaim, null);
                                				}
                                			}
                                		}
                                	}
                                }	                            
                            }
                        }
                    }                    
                }

                if (data.getTipe().intValue() == DataImportStage.TIPE_GROUP_SUSPEND){                	
                	int total = externalRawDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawData> raws = externalRawDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split( ":");

                    for (Iterator<ExternalRawData> it = raws.iterator(); it.hasNext();) {
                        
                        ExternalRawData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Data : " + externalRawData.getRawData());
                            String rawData = externalRawData.getRawData();
                            while (rawData.contains("||")){
                            	rawData = rawData.replace("||", "| |");
                            }
                            
                            StringTokenizer tokenizer = new StringTokenizer(rawData,"|");
                            int totalToken = tokenizer.countTokens();
                            
                            int idx = 0;
                            if (totalToken == mappingValue.length){
                            	
                            	MemberGroup group = new MemberGroup();                                

	                            while(tokenizer.hasMoreElements()){
	                            	String nextToken = tokenizer.nextToken();
	                            	String currentHeader = mappingValue[idx];
	                            	System.out.println(currentHeader + " adalah " + nextToken.trim());
	                            	group.setValue(currentHeader, nextToken.trim());
	                            	
	                            	idx++;
	                            }
	                          	
                            	ActionUser user = new ActionUser();
                            	User theUser = new User();
                            	theUser.setUsername("system-daemon");
                            	user.setUser(theUser);
	                            
                                MemberGroup toSuspend = memberGroupService.getMemberGroupByCode(group.getMemberGroupCode());	
                                String policyNumber = group.getPolicyNumber();
                                String clientCode = group.getClientCode();
                                String actionType = group.getActionType();
                                
                                Client client = clientService.getClient(clientCode);
                                
                                if (actionType != null && actionType.equalsIgnoreCase("SUSPEND")){
                                	if (policyNumber == null || policyNumber.equalsIgnoreCase("")){
		                                if (toSuspend != null ){                    			                                	
		                                	memberGroupService.block(toSuspend.getMemberGroupId(), group.getSuspendReason(),group.getSuspendDate(),group.getSuspendEndDate(), user);		                                	
		                                }	                      
                                	}
                                	else if (policyNumber != null && !policyNumber.equalsIgnoreCase("")){
                                		Policy policy = policyService.getPolicyByNumber(policyNumber, client.getClientId());
                                		
                                		if (policy != null){
                                			policyService.block(policy.getPolicyId(), group.getSuspendDate(),group.getSuspendEndDate(), group.getSuspendReason(),user);
                                		}
                                	}
                                }
                                else {
                                	if (policyNumber == null || policyNumber.equalsIgnoreCase("")){
		                                if (toSuspend != null ){
		                                	memberGroupService.unblock(toSuspend.getMemberGroupId(), group.getSuspendReason(), user);		                                	
		                                }	                      
                                	}
                                	else if (policyNumber != null && !policyNumber.equalsIgnoreCase("")){
                                		Policy policy = policyService.getPolicyByNumber(policyNumber, client.getClientId());
                                		
                                		if (policy != null){
                                			policyService.unblock(policy.getPolicyId(), group.getSuspendReason(), user);
                                		}
                                	}
                                }
                            }
                        }
                    }
                }
                if (data.getTipe().intValue() == DataImportStage.TIPE_MEMBER_PROVIDER){
                	
                	int total = externalRawDataService.getTotal(null, null, "importSessionId.id", data.getId());
                    Collection<ExternalRawData> raws = externalRawDataService.search(null, null,
                            "importSessionId.id", data.getId(), 0, total);

                    ExportImportTemplate template = data.getExportImportTemplate();
                    String mapping = template.getTemplateMapping();
                    String[] mappingValue = mapping.split( ":");

                    for (Iterator<ExternalRawData> it = raws.iterator(); it.hasNext();) {
                        
                        ExternalRawData externalRawData = it.next();

                        if (externalRawData != null) {
                            System.out.println("Execute Raw Data : " + externalRawData.getRawData());
                            String rawData = externalRawData.getRawData();
                            while (rawData.contains("||")){
                            	rawData = rawData.replace("||", "| |");
                            }
                            
                            StringTokenizer tokenizer = new StringTokenizer(rawData,"|");
                            int totalToken = tokenizer.countTokens();
                            
                            int idx = 0;
                            if (totalToken == mappingValue.length){
                            	
	                            MemberProvider provider = new MemberProvider();	                            
	                            provider.setCreatedBy("system-daemon");
	                            provider.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
	                            provider.setDeletedStatus(Integer.valueOf(0));
	
	                            
	                            while(tokenizer.hasMoreElements()){
	                            	String nextToken = tokenizer.nextToken();
	                            	String currentHeader = mappingValue[idx];
	                            	System.out.println(currentHeader + " adalah " + nextToken.trim());
	                            	//provider.setValue(currentHeader, nextToken.trim());
	                            	
	                            	idx++;
	                            }
	
	                            //providerService.create(provider, null);
                            }
                        }

                    }
                    
                }
                result = true;
                data.setStatus(DataImportStage.STATUS_MIGRATE);
                dataImportStageDao.update(data);


            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public Integer activateMigration(java.io.Serializable id, ActionUser user) throws Exception {
    	Integer result = 0;
    	
    	try {
    		DataImportStage importStage = get(id);
    		
    		if (importStage != null){
    			String[] eqParamImport = {"importSessionId.id"};
	    		Object[] eqValueImport = {importStage.getId()};
	    		int total = memberImportService.getTotal(null,null,eqParamImport,eqValueImport);
	    		Collection<MemberImport> memberImports = memberImportService.search(null,null,eqParamImport,eqValueImport,0,total);
	    		
	    		if (memberImports != null){
	    			Iterator<MemberImport> importIterator = memberImports.iterator();
	    			
	    			while (importIterator.hasNext()){
	    				MemberImport memberImport = importIterator.next();
	    				
	    				if (memberImport != null){
	    					Member member = new Member();
	    					
	    					if (memberImport.getActionType() != null){
	    						if (memberImport.getActionType().equalsIgnoreCase("ADDITION")){	    							
	    							member.setStatus(SubscriptionStatus.PENDING);	    							
	    							actionTypeAddition(memberImport, member);	    								    	    					
	    	    					memberService.create(member, user);
	    						}
	    						else {
	    							if (memberImport.getActionType().equalsIgnoreCase(MemberImport.ACTION_TYPE_CHANGEPLAN) || memberImport.getActionType().equalsIgnoreCase(MemberImport.ACTION_TYPE_RENEWAL)){
	    			
	    								String policyNumber = memberImport.getPolicyNumber();
		    							String memberNumber = memberImport.getMemberNumber();
		    							member = memberService.getMemberByPolicyNumber(memberNumber,policyNumber);
		    							
		    							if (memberImport.getActionType().equalsIgnoreCase(MemberImport.ACTION_TYPE_RENEWAL)){
		    								
		    								if (member != null){
			    								member.setCurrentProductCode(memberImport.getProduct());
				    							member.setProductUpgradeEffectiveDate(memberImport.getEffectiveDate());
				    							member.setParentName(memberImport.getParentName());
				    							member.setParentNumber(memberImport.getParentNumber());
				    							member.setNextExpireDate(memberImport.getExpireDate());
				    							member.setNextEffectiveDate(memberImport.getEffectiveDate());
				    							member.setNextCardNumber(memberImport.getSwipeCardNumber());			    							
				    							
			    								member.setStatus(SubscriptionStatus.PENDING_RENEWAL);
			    								memberService.update(member, user);
		    								}
		    								
		    							}
		    							else if (memberImport.getActionType().equalsIgnoreCase(MemberImport.ACTION_TYPE_CHANGEPLAN)){
		    								
		    								if (member != null){
				    							member.setCurrentProductCode(memberImport.getProduct());
				    							member.setProductUpgradeEffectiveDate(memberImport.getEffectiveDate());
				    							member.setStatus(SubscriptionStatus.PENDING_UPGRADE);
				    							memberService.update(member, user);
		    								}
			    						}
		    							else if (memberImport.getActionType().equalsIgnoreCase(MemberImport.ACTION_TYPE_UPDATE)){		    			
			    	    					actionTypeUpdate(memberImport,member);
			    	    					memberService.update(member, user);
		    							}
	    							}
	    						}
	    					}
	    					else {
	    						member.setStatus(SubscriptionStatus.PENDING);
	    						actionTypeAddition(memberImport, member);    	    					
    	    					memberService.create(member, user);
	    					}
	    				}
	    			}
	    		}

	    		importStage.setStatus(DataImportStage.STATUS_ACTIVATED);
	    		dataImportStageDao.update(importStage);
    		}
    		
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    	
    	return result;
    }

    private void actionTypeAddition (MemberImport memberImport, Member member) throws Exception{
    	member.setCustomerNumber(memberImport.getMemberNumber());
		member.setCustomerPolicyNumber(memberImport.getMemberNumber());
		member.setFirstName(memberImport.getMemberName());
		member.setDepartment(memberImport.getDepartment());
		member.setJoinDate(memberImport.getJoinDate());
		member.setEffectiveDate(memberImport.getEffectiveDate());
		member.setResignedDate(memberImport.getExpireDate());
		member.setExpireDate(memberImport.getExpireDate());
		member.setCurrentProductCode(memberImport.getProduct());
		member.setEmployeeIDNumber(memberImport.getNik());
		member.setParentNumber(memberImport.getParentNumber());
		member.setCurrentPolicyNumber(memberImport.getPolicyNumber());
		
		System.out.println("SALARY = " + memberImport.getSalary() + "|"); 
		
		if (memberImport.getSalary() != null && !memberImport.getSalary().trim().equalsIgnoreCase("")){
			member.setCurrentSalary(Double.valueOf(memberImport.getSalary()));
		}	    	    					
		if (memberImport.getIsVIP() != null && memberImport.getIsVIP().equalsIgnoreCase("Y")){
			member.setIsVIP(1);
		}
		else {
			member.setIsVIP(0);
		}
		if (memberImport.getRelationship() != null){
			member.setRelationship(memberImport.getRelationship());
			
			Relationship relationship = new Relationship();
			
			if (memberImport.getRelationship().equalsIgnoreCase("EMPLOYEE")){
				relationship.setRelationshipId(Relationship.EMPLOYEE);
			}
			if (memberImport.getRelationship().equalsIgnoreCase("SPOUSE")){
				relationship.setRelationshipId(Relationship.SPOUSE);
			}
			if (memberImport.getRelationship().equalsIgnoreCase("CHILDREN")){
				relationship.setRelationshipId(Relationship.CHILD);
			}	    	    						
			member.setRelationshipId(relationship);	    	    						
		}	    	    					
		member.setGender(memberImport.getSex());
		member.setJobPosition(memberImport.getJobFunction());
		member.setBank(memberImport.getBankName());
		member.setBankBranch(memberImport.getBankBranch());
		member.setBankAccountName(memberImport.getBankAccount());
		member.setBankAccount(memberImport.getAccountNo());
		member.setSwiftCode(memberImport.getSwiftCode());
		
		Client client = clientService.getClient(memberImport.getClientCode());
		MemberGroup memberGroup = memberGroupService.getMemberGroupByCode(memberImport.getGroupCode());
		
		
		member.setClientId(client);
		member.setMemberGroupId(memberGroup);
		member.setEmail(memberImport.getEmail());
		member.setMobilePhone(memberImport.getHandphone());
		
		java.sql.Date birthDate = null;
		try {
			if (memberImport.getBirthdate() != null && !memberImport.getBirthdate().equalsIgnoreCase("")){
				birthDate = Date.valueOf(memberImport.getBirthdate());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		if (birthDate != null){
			member.setBirthday(birthDate);
		}
		
		member.setCurrentCardNumber("");
		
		String swipeCard = memberImport.getSwipeCardNumber();
		if (swipeCard != null && !swipeCard.equals("")){
			member.setCurrentCardNumber(swipeCard);
		}
    }
	private void actionTypeUpdate(MemberImport memberImport, Member member) {
		member.setFirstName(memberImport.getMemberName());
		member.setDepartment(memberImport.getDepartment());
		member.setJoinDate(memberImport.getJoinDate());
		member.setEffectiveDate(memberImport.getEffectiveDate());
		member.setResignedDate(memberImport.getExpireDate());
		member.setExpireDate(memberImport.getExpireDate());			    	    					
		member.setParentNumber(memberImport.getParentNumber());		
		member.setEmployeeIDNumber(memberImport.getNik());
		member.setJobPosition(memberImport.getJobFunction());
		
		if (memberImport.getSalary() != null && !memberImport.getSalary().trim().equalsIgnoreCase("")){
			member.setCurrentSalary(Double.valueOf(memberImport.getSalary()));
		}	    	    					
		if (memberImport.getIsVIP() != null && memberImport.getIsVIP().equalsIgnoreCase("Y")){
			member.setIsVIP(1);
		}
		else {
			member.setIsVIP(0);
		}
		if (memberImport.getRelationship() != null){
			member.setRelationship(memberImport.getRelationship());			    	    						
			Relationship relationship = new Relationship();			    	    						
			if (memberImport.getRelationship().equalsIgnoreCase("EMPLOYEE")){
				relationship.setRelationshipId(Relationship.EMPLOYEE);
			}
			if (memberImport.getRelationship().equalsIgnoreCase("SPOUSE")){
				relationship.setRelationshipId(Relationship.SPOUSE);
			}
			if (memberImport.getRelationship().equalsIgnoreCase("CHILDREN")){
				relationship.setRelationshipId(Relationship.CHILD);
			}	    	    						
			member.setRelationshipId(relationship);	    	    						
		}	    	    					
		member.setGender(memberImport.getSex());			    	    					
		member.setBank(memberImport.getBankName());
		member.setBankBranch(memberImport.getBankBranch());
		member.setBankAccountName(memberImport.getBankAccount());
		member.setBankAccount(memberImport.getAccountNo());
		member.setSwiftCode(memberImport.getSwiftCode());
		member.setEmail(memberImport.getEmail());
		member.setMobilePhone(memberImport.getHandphone());
		
		java.sql.Date birthDate = null;
		try {
			birthDate = Date.valueOf(memberImport.getBirthdate());
		}
		catch (Exception e){
			e.printStackTrace();
		}
		if (birthDate != null){
			member.setBirthday(birthDate);
		}
	}

	@Override
	public boolean processUpload(Serializable id, ActionUser user)
			throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		try {
			DataImportStage importStage = get(id);
			
			if (importStage != null){
				executeRawMigration(id);
				
				executeMigration(id);
				
				activateMigration(id, user);
				
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

}
