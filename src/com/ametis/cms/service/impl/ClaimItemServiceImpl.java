package com.ametis.cms.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.joda.time.DateTime;

import com.ametis.cms.dao.ClaimDao;
import com.ametis.cms.dao.ClaimItemDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Benefit;
import com.ametis.cms.datamodel.BenefitUsageType;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.CostContainment;
import com.ametis.cms.datamodel.CostContainmentType;
import com.ametis.cms.datamodel.CoverageStatus;
import com.ametis.cms.datamodel.ExcessChargeItem;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberClausul;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberLimitLayer;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyClausul;
import com.ametis.cms.datamodel.PolicyCoverageFund;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.TreatmentUpgradeType;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.ClaimItemDto;
import com.ametis.cms.dto.PaymentReportSummary;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BenefitService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.CostContainmentService;
import com.ametis.cms.service.DiagnosisSetDetailService;
import com.ametis.cms.service.DiagnosisSetService;
import com.ametis.cms.service.ExchangeRateService;
import com.ametis.cms.service.GroupBenefitService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberClausulService;
import com.ametis.cms.service.MemberLimitLayerService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.MostFrequentItemService;
import com.ametis.cms.service.PolicyBenefitService;
import com.ametis.cms.service.PolicyClausulService;
import com.ametis.cms.service.PolicyCoverageFundService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProductBenefitService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.util.Converter;
import com.ametis.cms.util.dao.DaoSupportUtil;


public class ClaimItemServiceImpl implements ClaimItemService // extends+
// extends-
{

    private ClaimItemDao claimItemDao;
    private ClaimDao claimDao;
    private MemberLimitLayerService memberLimitLayerService;
    private MemberBenefitService memberBenefitService;
    private MemberClausulService memberClausulService;
    private CostContainmentService costContainmentService;
    private BenefitService benefitService;
    private MemberService memberService;
    private MemberProductService memberProductService;
    private MostFrequentItemService mostFrequentItemService;
    private ProductService productService;
    
    private ActivityLogService activityLogService;
    private ProductBenefitService productBenefitService;
    private PolicyService policyService;
    private DiagnosisSetService diagnosisSetService;
    private DiagnosisSetDetailService diagnosisSetDetailService;
    
    private ExchangeRateService exchangeRateService;
    private PolicyClausulService policyClausulService;
    private PolicyBenefitService policyBenefitService;
    
    private GroupBenefitService groupBenefitService;
    
    private PolicyCoverageFundService policyCoverageFundService;

    
    
    
    public GroupBenefitService getGroupBenefitService() {
		return groupBenefitService;
	}

	public void setGroupBenefitService(GroupBenefitService groupBenefitService) {
		this.groupBenefitService = groupBenefitService;
	}

	public ClaimDao getClaimDao() {
		return claimDao;
	}

	public void setClaimDao(ClaimDao claimDao) {
		this.claimDao = claimDao;
	}

	public DiagnosisSetDetailService getDiagnosisSetDetailService() {
		return diagnosisSetDetailService;
	}

	public void setDiagnosisSetDetailService(
			DiagnosisSetDetailService diagnosisSetDetailService) {
		this.diagnosisSetDetailService = diagnosisSetDetailService;
	}

	public DiagnosisSetService getDiagnosisSetService() {
		return diagnosisSetService;
	}

	public void setDiagnosisSetService(DiagnosisSetService diagnosisSetService) {
		this.diagnosisSetService = diagnosisSetService;
	}

	public MemberLimitLayerService getMemberLimitLayerService() {
		return memberLimitLayerService;
	}

	public void setMemberLimitLayerService(
			MemberLimitLayerService memberLimitLayerService) {
		this.memberLimitLayerService = memberLimitLayerService;
	}

	public PolicyCoverageFundService getPolicyCoverageFundService() {
		return policyCoverageFundService;
	}

	public void setPolicyCoverageFundService(
			PolicyCoverageFundService policyCoverageFundService) {
		this.policyCoverageFundService = policyCoverageFundService;
	}

	public PolicyClausulService getPolicyClausulService() {
		return policyClausulService;
	}

	public void setPolicyClausulService(PolicyClausulService policyClausulService) {
		this.policyClausulService = policyClausulService;
	}

	public PolicyBenefitService getPolicyBenefitService() {
		return policyBenefitService;
	}

	public void setPolicyBenefitService(PolicyBenefitService policyBenefitService) {
		this.policyBenefitService = policyBenefitService;
	}

	public ExchangeRateService getExchangeRateService() {
		return exchangeRateService;
	}

	public void setExchangeRateService(ExchangeRateService exchangeRateService) {
		this.exchangeRateService = exchangeRateService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public MemberClausulService getMemberClausulService() {
		return memberClausulService;
	}

	public void setMemberClausulService(MemberClausulService memberClausulService) {
		this.memberClausulService = memberClausulService;
	}

	public ProductBenefitService getProductBenefitService() {
        return productBenefitService;
    }

    public void setProductBenefitService(ProductBenefitService productBenefitService) {
        this.productBenefitService = productBenefitService;
    }

    public MemberProductService getMemberProductService() {
        return memberProductService;
    }

    public void setMemberProductService(
            MemberProductService memberProductService) {
        this.memberProductService = memberProductService;
    }

    public MostFrequentItemService getMostFrequentItemService() {
        return mostFrequentItemService;
    }

    public void setMostFrequentItemService(
            MostFrequentItemService mostFrequentItemService) {
        this.mostFrequentItemService = mostFrequentItemService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public ActivityLogService getActivityLogService() {
        return activityLogService;
    }

    public void setActivityLogService(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    public BenefitService getBenefitService() {
        return benefitService;
    }

    public void setBenefitService(BenefitService benefitService) {
        this.benefitService = benefitService;
    }

    public CostContainmentService getCostContainmentService() {
        return costContainmentService;
    }

    public void setCostContainmentService(
            CostContainmentService costContainmentService) {
        this.costContainmentService = costContainmentService;
    }

    public MemberBenefitService getMemberBenefitService() {
        return memberBenefitService;
    }

    public void setMemberBenefitService(
            MemberBenefitService memberBenefitService) {
        this.memberBenefitService = memberBenefitService;
    }

    public void setClaimItemDao(ClaimItemDao object) {
        this.claimItemDao = object;
    }

    public ClaimItemDao getClaimItemDao() {
        return this.claimItemDao;
    }

    /*
     * Method create (ClaimItem object) berfungsi untuk melakukan penambahan
     * sebuah object kedalam database @param object adalah sebuah object yang
     * ingin diubah @return object hasil kreasi,lengkap dengan assigned primary
     * key, exception jika gagal
     */
    public ClaimItem create(ClaimItem object, ActionUser actionUser)
            throws Exception {

        // object.setCreatedDate (new java.sql.Date
        // (System.currentTimeMillis()));

        // ----
        ClaimItem result = null;
        object.setDeletedStatus(Integer.valueOf(0));
        object.setIsReconciled(0);


        if (actionUser != null) {
            User user = actionUser.getUser();
            if (user != null) {
                object.setCreatedBy(user.getUsername());
            }
        }
        //Edit 20150414 by FVO, penambahan flek dengan deleted status = 0
        String[] eqColumn = {"claimItemValue", "itemId.itemId",
            "claimId.claimId", "deletedStatus"};

        if (object.getItemId() != null){
	        Object[] eqValue = {object.getClaimItemValue(),
	            object.getItemId().getItemId(),
	            object.getClaimId().getClaimId(), Integer.valueOf(0)};
	        
	        int totalItem = getTotal(null,null,eqColumn,eqValue);
	        
	        if (totalItem == 0) {
	            result = claimItemDao.create(object);
	        }
        }
        else {
        	result = claimItemDao.create(object);
        }

        return result;
    }

    /*
     * Method update (ClaimItem object) berfungsi untuk melakukan perubahan
     * terhadap sebuah object yang terdapat didalam database @param object
     * adalah sebuah object yang ingin diubah @return object hasil update,
     * exception jika gagal
     */
    public ClaimItem update(ClaimItem object, ActionUser actionUser)
            throws Exception {


        object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
        if (actionUser != null) {
            User user = actionUser.getUser();
            if (user != null) {
                object.setModifiedBy(user.getUsername());
            }
        }

        claimItemDao.update(object);
        return object;
    }

    public ClaimItem reject(ClaimItem object, ActionUser actionUser)
            throws Exception {
        object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));

        /*
         * Gue tambahin mekanisme NULL value checking just in case user nya null
         */
        if (actionUser != null) {
            User user = actionUser.getUser();
            if (user != null) {
                object.setModifiedBy(user.getUsername());
            }
        }

        CaseStatus status = new CaseStatus();
        status.setCaseStatusId(ClaimItem.CLAIM_ITEM_REJECTED);

        object.setClaimItemStatus(status);
        // object.
        if (actionUser != null) {
            User user = actionUser.getUser();
            if (user != null) {
                object.setVerifiedBy(user.getUsername());
            }
        }
        object.setVerifiedDate(new Date(System.currentTimeMillis()));

        claimItemDao.update(object);

        return object;
    }

    public ClaimItem approve(ClaimItem object, ActionUser actionUser)
            throws Exception {
        object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));

        /*
         * Gue tambahin mekanisme NULL value checking just in case user nya null
         */
        if (actionUser != null) {
            User user = actionUser.getUser();
            if (user != null) {
                object.setModifiedBy(user.getUsername());
            }
        }

        CaseStatus status = new CaseStatus();
        status.setCaseStatusId(ClaimItem.CLAIM_ITEM_APPROVED);
        object.setClaimItemStatus(status);
        if (actionUser != null) {
            User user = actionUser.getUser();
            if (user != null) {
                object.setVerifiedBy(user.getUsername());
            }
        }
        object.setVerifiedDate(new java.sql.Date(System.currentTimeMillis()));

        claimItemDao.update(object);

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
    public ClaimItem trash(java.io.Serializable pkey) throws Exception {
        ClaimItem object = claimItemDao.get(pkey);
        claimItemDao.delete(object);
        return object;
    }

    /*
     * Method delete (ClaimItem object) berfungsi untuk melakukan penghapusan
     * terhadap sebuah object yang terdapat didalam database @param object
     * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
     * dengan mengisi field-field primary key @return updated object, exception
     * if failed
     */
    public ClaimItem delete(java.io.Serializable pkey, ActionUser actionUser)
            throws Exception {

        return trash(pkey);

        /*
         * ClaimItem object = claimItemDao.get(pkey);
         *
         * // object.setDeletedDate (new java.sql.Date //
         * (System.currentTimeMillis()));
         *
         * object.setDeletedStatus(new Integer(1)); if (actionUser != null) {
         * User user = actionUser.getUser(); if (user != null) {
         * object.setDeletedBy(user.getUsername()); } }
         *
         * object = claimItemDao.update(object);
         *
         * return object;
         */
    }

    /*
     * Method delete (ClaimItem object) berfungsi untuk melakukan penghapusan
     * terhadap sebuah object yang terdapat didalam database @param object
     * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
     * dengan mengisi field-field primary key @return updated object, exception
     * if failed
     */
    public ClaimItem delete(ClaimItem object, ActionUser actionUser)
            throws Exception {

        // object.setDeletedDate (new java.sql.Date
        // (System.currentTimeMillis()));

        object.setDeletedStatus(new Integer(1));
        if (actionUser != null) {
            User user = actionUser.getUser();
            if (user != null) {
                object.setDeletedBy(user.getUsername());
            }
        }

        claimItemDao.update(object);
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
    public ClaimItem get(java.io.Serializable pkey) throws Exception {
        ClaimItem object = null;
        object = claimItemDao.get(pkey);
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
    public ClaimItem get(java.io.Serializable pkey, String[] required)
            throws Exception {
        ClaimItem object = null;
        object = claimItemDao.get(pkey);
        DaoSupportUtil.lazyInit(required, object);
        return object;
    }

    // -- get section end here
    // SEARCH SECTION - PALING RUMIT !!
    // * -> plain
    // *b -> with columnOrder
    // -- 1
    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, int index, int offset)
            throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        List list = c.list();
        return list;

    }

    // --req
    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, String[] required,
            int index, int offset) throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;

    }

    // --req end
    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, boolean asc,
            String columnOrder[], int index, int offset) throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        return list;

    }

    // --req
    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, boolean asc,
            String columnOrder[], String[] required, int index, int offset)
            throws Exception {
        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;

    }

    // --req end
    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, boolean asc,
            String columnOrder, int index, int offset) throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        return list;
    }

    // --req
    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, boolean asc,
            String columnOrder, String[] required, int index, int offset)
            throws Exception {
        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;

    }

    // --req end
    // -- 1b
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams, int index, int offset)
            throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        List list = c.list();
        return list;

    }

    // --req
    
    //Add 20150825 by FVO, add search for active claim
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams, Object[] inParamsValue, int index, int offset)
            throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        if(inParamsValue.length >= 1)
			DaoSupportUtil.setIn("claimId.claimStatus.caseStatusId", inParamsValue,c);
        List list = c.list();
        return list;

    }
    
    
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams, String[] required, int index,
            int offset) throws Exception {
        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;

    }

    // --req end
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams, boolean asc,
            String columnOrder[], int index, int offset) throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        return list;

    }

    // req
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams, boolean asc,
            String columnOrder[], String[] required, int index, int offset)
            throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;

    }

    // req end
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams, boolean asc, String columnOrder,
            int index, int offset) throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        return list;

    }

    // req
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams, boolean asc, String columnOrder,
            String[] required, int index, int offset) throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;

    }

    // req end
    // -- 2 , between
    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, String[] btwnColumns,
            Object[] btwnParams1, Object[] btwnParams2, int index, int offset)
            throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        List list = c.list();
        return list;
    }

    // req
    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, String[] btwnColumns,
            Object[] btwnParams1, Object[] btwnParams2, String[] required,
            int index, int offset) throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;
    }

    // req end
    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, String[] btwnColumns,
            Object[] btwnParams1, Object[] btwnParams2, boolean asc,
            String columnOrder[], int index, int offset) throws Exception {

        Criteria c = claimItemDao.getCriteria();
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
            String[] eqColumns, Object[] eqParams, String[] btwnColumns,
            Object[] btwnParams1, Object[] btwnParams2, boolean asc,
            String columnOrder[], String[] required, int index, int offset)
            throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;
    }

    // req end
    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, String[] btwnColumns,
            Object[] btwnParams1, Object[] btwnParams2, boolean asc,
            String columnOrder, int index, int offset) throws Exception {

        Criteria c = claimItemDao.getCriteria();
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
            String[] eqColumns, Object[] eqParams, String[] btwnColumns,
            Object[] btwnParams1, Object[] btwnParams2, boolean asc,
            String columnOrder, String[] required, int index, int offset)
            throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;
    }

    // req end
    // -- 2b
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams, String btwnColumns,
            Object btwnParams1, Object btwnParams2, int index, int offset)
            throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        List list = c.list();
        return list;
    }

    // req
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams, String btwnColumns,
            Object btwnParams1, Object btwnParams2, String[] required,
            int index, int offset) throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;
    }

    // req end
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams, String btwnColumns,
            Object btwnParams1, Object btwnParams2, boolean asc,
            String columnOrder[], int index, int offset) throws Exception {

        Criteria c = claimItemDao.getCriteria();
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
            String eqColumns, Object eqParams, String btwnColumns,
            Object btwnParams1, Object btwnParams2, boolean asc,
            String columnOrder[], String[] required, int index, int offset)
            throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;
    }

    // req end
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams, String btwnColumns,
            Object btwnParams1, Object btwnParams2, boolean asc,
            String columnOrder, int index, int offset) throws Exception {

        Criteria c = claimItemDao.getCriteria();
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
            String eqColumns, Object eqParams, String btwnColumns,
            Object btwnParams1, Object btwnParams2, boolean asc,
            String columnOrder, String[] required, int index, int offset)
            throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;
    }

    // req end
    // -- search end
    // -- get total
    public int getTotal(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams) throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        int total = DaoSupportUtil.getTotal(c);
        return total;

    }

    public int getTotal(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, String[] btwnColumns,
            Object[] btwnParams1, Object[] btwnParams2) throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        int total = DaoSupportUtil.getTotal(c);
        return total;

    }

    public int getTotal(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams) throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        int total = DaoSupportUtil.getTotal(c);
        return total;

    }

    public int getTotal(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, String btwnColumns,
            Object btwnParams1, Object btwnParams2) throws Exception {

        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        int total = DaoSupportUtil.getTotal(c);
        return total;

    }

    // ------------------------------------------------------------------
    public int getTotal() throws Exception {
        Criteria c = claimItemDao.getCriteria();
        int total = DaoSupportUtil.getTotal(c);
        return total;
    }

    // -- get total end
    // ---------------------------------------------------
    public Collection getAll(String[] required) throws Exception {

        Criteria c = claimItemDao.getCriteria();
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;

    }

    public Collection getAll() throws Exception {

        Criteria c = claimItemDao.getCriteria();
        List list = c.list();
        return list;
    }

    // -------------------------------------------------
    // unique Result
    public ClaimItem searchUnique(String eqColumns, Object eqParams,
            String[] required) throws Exception {
        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        ClaimItem obj = (ClaimItem) c.uniqueResult();
        DaoSupportUtil.lazyInit(required, obj);
        return obj;
    }

    public ClaimItem searchUnique(String eqColumns, Object eqParams)
            throws Exception {
        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        ClaimItem obj = (ClaimItem) c.uniqueResult();
        return obj;
    }
    
    public ClaimItem searchUnique(String[] eqColumns, Object[] eqParams)
            throws Exception {
        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(0, 1, c);
        ClaimItem obj = (ClaimItem) c.uniqueResult();
        return obj;
    }

    // -----------------------------------------------

    /*
     * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
     * @return criteria
     */
    public Criteria getCriteria() throws Exception {
        Criteria c = claimItemDao.getCriteria();
        return c;
    }

    public DetachedCriteria getDetachedCriteria() throws Exception {
        DetachedCriteria dc = claimItemDao.getDetachedCriteria();
        return dc;
    }

    public ClaimItem getNextToVerifyItem(Claim claim) throws Exception {
        // TODO Auto-generated method stub

        ClaimItem result = null;

        if (claim != null) {

            Criteria c = claimItemDao.getCriteria();
            String[] eqColumns = {"claimId.claimId",
                "claimItemStatus.caseStatusId", "deletedStatus"};

            Object[] eqParams = {claim.getClaimId(),
                Integer.valueOf(ClaimItem.CLAIM_ITEM_OPEN),
                Integer.valueOf(0)};

            DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
            DaoSupportUtil.setLimit(0, 1, c);

            List list = c.list();

            if (list != null && !list.isEmpty()) {
                result = (ClaimItem) list.get(0);
            }

        }
        return result;
    }

    public boolean approveVerifyItem(ClaimItem claimItem, ActionUser actionUser)
            throws Exception {
        // TODO Auto-generated method stub
        boolean result = false;

        if (claimItem != null) {

            if (actionUser != null) {
                User user = actionUser.getUser();
                if (user != null) {
                    claimItem.setVerifiedBy(user.getUsername());
                    claimItem.setModifiedBy(user.getUsername());
                }
            }

            claimItem.setVerifiedDate(new java.sql.Date(System.currentTimeMillis()));
            claimItem.setModifiedTime(new Timestamp(System.currentTimeMillis()));
            CaseStatus itemStatus = new CaseStatus();
            itemStatus.setCaseStatusId(Integer.valueOf(ClaimItem.CLAIM_ITEM_APPROVED));
            claimItem.setClaimItemStatus(itemStatus);

            claimItemDao.update(claimItem);

            result = true;
        }

        return result;
    }

    public boolean rejectVerifyItem(ClaimItem claimItem, ActionUser actionUser)
            throws Exception {
        boolean result = false;

        if (claimItem != null) {
            claimItem.setVerifiedDate(new java.sql.Date(System.currentTimeMillis()));
            claimItem.setModifiedTime(new Timestamp(System.currentTimeMillis()));

            if (actionUser != null) {
                User user = actionUser.getUser();
                if (user != null) {
                    claimItem.setVerifiedBy(user.getUsername());
                    claimItem.setModifiedBy(user.getUsername());
                }
            }

            CaseStatus itemStatus = new CaseStatus();
            itemStatus.setCaseStatusId(Integer.valueOf(ClaimItem.CLAIM_ITEM_REJECTED));

            claimItem.setClaimItemStatus(itemStatus);
            // claimItem.setMedicalRemarks(claimItem.get)

            CostContainment costContainment = new CostContainment();
            costContainment.setDescription(claimItem.getItemId().getItemName());
            costContainment.setCostContainmentValue(claimItem.getClaimItemValue());

            CostContainmentType costContainmentType = new CostContainmentType();
            costContainmentType.setCostContainmentTypeId(Integer.valueOf(CostContainmentType.CLAIM_ITEM_REJECTION));

            costContainment.setCostContainmentType(costContainmentType);
            costContainment.setClaimId(claimItem.getClaimId());

            // gue yakin ini error :P
            costContainment.setClientId(claimItem.getClaimId().getMemberId().getClientId());

            claimItemDao.update(claimItem);

            costContainmentService.create(costContainment, actionUser);

            result = true;
        }

        return result;
    }

    public ClaimItem getNextToCheckItem(Claim claim) throws Exception {
        // TODO Auto-generated method stub

        ClaimItem result = null;

        if (claim != null) {

            Criteria c = claimItemDao.getCriteria();

            String[] eqColumns = {"claimId.claimId",
                "claimItemStatus.caseStatusId"};
            Object[] eqParams = {claim.getClaimId(),
                Integer.valueOf(ClaimItem.CLAIM_ITEM_APPROVED)};

            DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
            DaoSupportUtil.setLimit(0, 1, c);

            List list = c.list();

            if (list != null && !list.isEmpty()) {
                result = (ClaimItem) list.get(0);
            }

            // do the benefit calculation

            if (result != null) {

                CaseCategory caseCategoryId = claim.getCaseCategoryId();

                Item item = result.getItemId();
                ItemCategory itemCategory = item.getItemCategoryId();

                MemberBenefit memberBenefit = null;

                Benefit benefit = null;

                if (item != null) {

                    // Object res = memberBenefitService.search()

                    String[] params = {"caseCategoryId.caseCategoryId",
                        "itemCategoryId.itemCategoryId"};
                    Object[] paramValue = {caseCategoryId.getCaseCategoryId(),
                        itemCategory.getItemCategoryId()};

                    String[] required = {""};

                    // Collection collections =
                    // benefitService.search(null,null,params,paramValue
                    // ,required ,0,1);
                    Collection<MemberBenefit> collections = memberBenefitService.search(null, null, params, paramValue, required,
                            0, 1);

                    if (collections != null && collections.size() > 0) {

                        Iterator iterator = collections.iterator();

                        if (iterator != null && iterator.hasNext()) {

                            memberBenefit = (MemberBenefit) iterator.next();

                            if (memberBenefit != null) {
                                // benefit = memberBenefit.getBenefitId();
                            }

                            CoverageStatus coverage = new CoverageStatus();
                            coverage.setCoverageStatusId(CoverageStatus.COVERED);

                            result.setCoverageStatus(coverage);

                            if (benefit != null) {
                                // double maxOccurance =
                                // memberBenefit.getBenefitId().getBenefitMaxOccurance();
                                // double currentOccurance =
                                // memberBenefit.getBenefitId().get
                                // double
                            }
                        } else {
                            CoverageStatus coverage = new CoverageStatus();
                            coverage.setCoverageStatusId(CoverageStatus.UNCOVERED);

                            result.setCoverageStatus(coverage);
                            result.setClaimItemCoveredValue(Double.valueOf(0));

                        }
                    }
                }
            }
        }
        return result;
    }

    public boolean approveBulkItemVerification(
            Collection<ClaimItem> claimItems, ActionUser actionUser)
            throws Exception {
        boolean result = true;

        if (claimItems != null) {

            Iterator<ClaimItem> iterator = claimItems.iterator();

            if (iterator != null) {
                ClaimItem item = null;

                while (iterator.hasNext()) {
                    item = iterator.next();

                    if (item != null) {

                        if (item.getClaimItemStatus().getCaseStatusId().intValue() == ClaimItem.CLAIM_ITEM_APPROVED) {
                            approveVerifyItem(item, actionUser);
                        } else if (item.getClaimItemStatus().getCaseStatusId().intValue() == ClaimItem.CLAIM_ITEM_REJECTED) {
                            rejectVerifyItem(item, actionUser);
                        }
                    }

                }
            }
        }

        return result;
    }

    public double[] approveCheckItem(ClaimItem claimItem, ActionUser actionUser)
            throws Exception {

        double[] result = new double[2];

        if (claimItem != null) {

            double excess = 0.0;

            boolean isCurrencyConversion = false;
            double currencyMultiplier = claimItem.getExchangeRate() == null ? 1.0 : claimItem.getExchangeRate();
            double productCurrencyApprovedValue = 0.0;
            double productCurrencyInitialValue = 0.0;
            double productCurrencyCoveredValue = 0.0;
            double productCurrencyExcessValue = 0.0;
            
            
            if (currencyMultiplier > 1.0 || currencyMultiplier < 1.0){
            	isCurrencyConversion = true;
            }
            
            double initialValue = 0.0;
            if (claimItem.getClaimItemValue() != null){
            	initialValue = claimItem.getClaimItemValue().doubleValue();
            	productCurrencyInitialValue = initialValue * currencyMultiplier;
            }
            
            double coveredValue = 0.0;
            
            if (claimItem.getClaimItemCoveredValue() != null){
            	coveredValue = claimItem.getClaimItemCoveredValue().doubleValue();
            	productCurrencyCoveredValue = coveredValue * currencyMultiplier;
            }
            
            double checkedValue = 0.0;
            
            if (claimItem.getClaimItemApprovedValue() != null && claimItem.getClaimItemApprovedValue().doubleValue() <= initialValue ){
            	checkedValue = claimItem.getClaimItemApprovedValue().doubleValue();
            	productCurrencyApprovedValue = checkedValue * currencyMultiplier;
            }
            else {
            	claimItem.setClaimItemApprovedValue(coveredValue);
            	checkedValue = coveredValue;
            	productCurrencyCoveredValue = coveredValue * currencyMultiplier;
            }

            claimItem.setProductCurrencyApprovedValue(productCurrencyApprovedValue);
            claimItem.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
            claimItem.setBenefitCheckedDate(new java.sql.Date(System.currentTimeMillis()));

            if (actionUser != null) {
                User user = actionUser.getUser();

                if (user != null) {
                    claimItem.setBenefitCheckedBy(user.getUsername());
                    claimItem.setModifiedBy(user.getUsername());
                }
            }

            claimItem.setExcessValue(Double.valueOf(0));
            CaseStatus itemStatus = new CaseStatus();

            itemStatus.setCaseStatusId(Integer.valueOf(ClaimItem.CLAIM_ITEM_CHECKED));

            claimItem.setClaimItemStatus(itemStatus);

            System.out.println("APPROVE START = " + claimItem.getClaimId().getAdmissionDate() + " END = " + claimItem.getClaimId().getDischargeDate());
            
            Collection<MemberBenefit> collections =  memberBenefitService.getMemberBenefitByDate( claimItem.getClaimId().getMemberId().getMemberId(), 
    				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
    				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
    				 claimItem.getClaimId().getAdmissionDate(), claimItem.getClaimId().getDischargeDate(),0);

            MemberBenefit memberBenefit = null;

            if (collections != null && collections.size() > 0) {
                Iterator<MemberBenefit> memberBenefitIterator = collections.iterator();

                if (memberBenefitIterator != null) {
                    memberBenefit = memberBenefitIterator.next();

                    if (memberBenefit != null) {
                        claimItem.setMemberBenefitId(memberBenefit);
                        System.out.println("GETTING BENEFIT ID = " + memberBenefit.getMemberBenefitId() + " EFFECTIVE = " + memberBenefit.getEffectiveDate() + 
                        		" END = " + memberBenefit.getExpireDate() + " PRODUCT ID = "  + memberBenefit.getMemberProductId().getMemberProductId() + " START = " + memberBenefit.getMemberProductId().getRegisterDate() + " EXPIRE = " + memberBenefit.getMemberProductId().getExpireDate());
                         
                    }
                }
            }
            else {
            	System.out.println("TIDAK MENEMUKAN BENEFIT");
            }

            result[0] = checkedValue;
            // MemberBenefit memberBenefit = memberBenefitService.sear
            if (checkedValue < initialValue) {
                // terdapat cost containment

            	excess = initialValue - checkedValue;
            	productCurrencyExcessValue = excess * currencyMultiplier;
            	
                claimItem.setExcessValue(excess);
                claimItem.setProductCurrencyExcessValue(productCurrencyExcessValue);
                
                CostContainment costContainment = new CostContainment();
                costContainment.setDescription(claimItem.getItemId().getItemName());
                costContainment.setCostContainmentValue(claimItem.getClaimItemValue());

                CostContainmentType costContainmentType = new CostContainmentType();
                costContainmentType.setCostContainmentTypeId(Integer.valueOf(CostContainmentType.BENEFIT_CHECK));

                String[] reqMember = {"Member.ClientId"};
                Member member = memberService.get(claimItem.getClaimId().getMemberId().getMemberId(),reqMember);
                
                costContainment.setClientId(member.getClientId());

                
                costContainment.setCostContainmentType(costContainmentType);
                costContainment.setClaimId(claimItem.getClaimId());
                costContainment.setDeletedStatus(0);

                costContainment.setMemberId(member);

                costContainmentService.create(costContainment, actionUser);

                Claim claim = claimItem.getClaimId();

                if (claim != null) {


                        Integer claimType = claim.getClaimTypeId().getClaimTypeId();

                        if (claimType != null
                                && (claimType.intValue() == ClaimType.CASHLESS || claimType.intValue() == ClaimType.REIMBURSEMENT_KHUSUS)) {

                            excess = initialValue - checkedValue;

                            ExcessChargeItem excessItem = new ExcessChargeItem();
                            excessItem.setItemId(claimItem.getItemId());

                            claimItem.setExcessValue(excess);

                            excessItem.setValue(excess);
                            excessItem.setDeletedStatus(0);

                            result[1] = excess;

                            if (actionUser != null) {
                                User user = actionUser.getUser();

                                if (user != null) {
                                    excessItem.setCreatedBy(user.getUsername());
                                }
                            }
                            // excessItem.setCreatedBy(actionUser.getU)
                        }
                }
            }
            claimItemDao.update(claimItem);        
        }

        return result;

    }

    public Collection<ClaimItem> getVerifiableItem(Claim claim)
            throws Exception {
        // TODO Auto-generated method stub

        Collection<ClaimItem> result = null;

        if (claim != null) {

            Criteria c = claimItemDao.getCriteria();

            String[] eqParam = {"claimId.claimId", "deletedStatus"};
            Object[] eqValue = {claim.getClaimId(), Integer.valueOf(0)};
            DaoSupportUtil.setEqParam(eqParam, eqValue, c);
            result = c.list();

        }

        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ametis.cms.service.ClaimItemService#getBenefitCheckItem(java.lang.Integer)
     *
     *
     *
     *
     * Algoritma untuk melakukan pemeriksaan benefit claim berdasarkan claim
     * item Langkah 1 : Proses Claim Item dari Value terkecil Langkah 2 :
     * Crosscheck dengan tabel benefit sesuai dengan kategori layanan Langkah 3
     * : Check metode penghitungan benefit Langkah 4 : Apabila metode
     * perhitungan menggunakan INNER + OUTER maka selalu melakukan crosscheck
     * dengan ACTUAL_CUSTOMER_LIMIT
     *
     *
     */
    public Collection<ClaimItem> getBenefitCheckItem(Claim claimId)
            throws Exception {
        Collection<ClaimItem> result = null;

        boolean isSurgery = false;
        boolean isComplication = false;
        boolean isRoomUpgrade = false;
        
        Policy policy = null;
        double multiplier = 1;
        int roomUpgradeCalculationSubject = -1;
        int multiplierCalculation = TreatmentUpgradeType.MULTIPLIER_WITH_BENEFIT_CHECK;

        if (claimId != null) {

        	Member member = memberService.get(claimId.getMemberId().getMemberId());
        	
        	MemberGroup memberGroup = member.getMemberGroupId();
        	
        	
        	
        	if (claimId.getPolicy() != null){
        		policy = policyService.get(claimId.getPolicy().getPolicyId());
        	}
        	else {
	        	if (memberGroup != null){
	        		
	        		policy = policyService.getGroupActivePolicy(memberGroup.getMemberGroupId());
	        	}
        	}
        	
            if (claimId.getIsSurgery() != null) {
                if (claimId.getIsSurgery().intValue() == 1) {
                    isSurgery = true;
                }
            }

            if (claimId.getHasComplication() != null) {
                if (claimId.getHasComplication().intValue() == 1) {
                    isComplication = true;
                }
            }
            if (claimId.getRoomUpgradeType() != null){
            	
            	
            	int roomUpgradeType = claimId.getRoomUpgradeType().intValue();
            	
            	if(roomUpgradeType == TreatmentUpgradeType.KAMAR_PENUH || roomUpgradeType == TreatmentUpgradeType.KAMAR_TIDAK_TERSEDIA ||
            		roomUpgradeType == TreatmentUpgradeType.NAIK_ATAS_PERMINTAAN_SENDIRI || roomUpgradeType == TreatmentUpgradeType.REKOMENDASI_DOKTER){
            		
            		isRoomUpgrade = true;	
            	}
            	
            	
            	String[] eqParamPolicy = {"policyId.policyId","deletedStatus","treatmentUpgradeType.treatmentUpgradeTypeId",
            			"caseCategoryId.caseCategoryId"};
            	Object[] eqValuePolicy = {claimId.getPolicyId(),0,claimId.getRoomUpgradeType(),claimId.getCaseCategoryId().getCaseCategoryId()};
            	
            	
            	int totalPolicy = policyClausulService.getTotal(null,null,eqParamPolicy,eqValuePolicy);
            	
            	if (totalPolicy > 0){
            		Collection<PolicyClausul> clausulList = policyClausulService.search(null,null,eqParamPolicy,eqValuePolicy,0,totalPolicy);
            		
            		Iterator<PolicyClausul> iterator = clausulList.iterator();
            		
            		if (iterator.hasNext()){
            			PolicyClausul policyClausul = iterator.next();
            			
            			if (policyClausul != null){
            				
            				if (policyClausul.getBenefitReductionType().intValue() == TreatmentUpgradeType.PRO_RATE_UPGRADE_CALC){
            					if (claimId.getCurrentRoomRate() != null){
            						
            						if (policyClausul.getMultiplierCalculationType() != null && policyClausul.getMultiplierCalculationType().intValue() == TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK){
            							multiplierCalculation = TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK;
            						}
            						MemberProduct product = claimId.getProductId();
            						
            						String[] requiredProduct = {};
            						
            						if (product != null){
            							product = memberProductService.get(product.getMemberProductId(),requiredProduct);
            						}
            						
            						if (product != null && (product.getProductClassRate() != null && product.getProductClassRate().doubleValue() != 0.0)){
            							double current = claimId.getCurrentRoomRate().doubleValue();
            							double benefitRoom = product.getProductClassRate().doubleValue();
            							multiplier = benefitRoom / current;
            						}
            						else if ((product.getProductClassRate() != null && product.getProductClassRate().doubleValue() == 0.0) || claimId.getProductRoomRate() != null){
            							double current = claimId.getCurrentRoomRate().doubleValue();
            							double benefitRoom = claimId.getProductRoomRate().doubleValue();
            							multiplier = benefitRoom / current;
            						}
            					}
            				}
            				else if (policyClausul.getBenefitReductionType().intValue() == TreatmentUpgradeType.PERCENTAGE_UPGRADE_CALC){
            					if (policyClausul.getBenefitReductionPercentage() != null){
            						double reductionPercentage = policyClausul.getBenefitReductionPercentage().doubleValue();
            						if (reductionPercentage > 1){
            							// percentage belum dibagi dengan 100
            							reductionPercentage = reductionPercentage / 100;
            							multiplier = multiplier - reductionPercentage;
            						}
            						else if (reductionPercentage <= 1){
            							multiplier = multiplier - reductionPercentage;
            						}
            						
            						if (policyClausul.getMultiplierCalculationType() != null && policyClausul.getMultiplierCalculationType().intValue() == TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK){
            							multiplierCalculation = TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK;
            						}
            					}
            				}
            				else if (policyClausul.getBenefitReductionType().intValue() == TreatmentUpgradeType.FIXED_POINT_CALC){
            					
            				}
            			}
            			
            		}
            		
            		
            	}
            	else {
            		String[] eqParam = {"memberId.memberId","deletedStatus","treatmentUpgradeType.treatmentUpgradeTypeId",
        				"caseCategoryId.caseCategoryId"};
        	
            		Object[] eqValue = {claimId.getMemberId().getMemberId(),Integer.valueOf(0),claimId.getRoomUpgradeType(),
            				claimId.getCaseCategoryId().getCaseCategoryId()};
            		
            		int total = memberClausulService.getTotal(null,null,eqParam,eqValue);
        	
	            	if (total > 0){
	            		Collection<MemberClausul> clausulList = memberClausulService.search(null,null,eqParam,eqValue,0,1);
	            		
	            		Iterator<MemberClausul> iterator = clausulList.iterator();
	            		
	            		if (iterator.hasNext()){
	            			MemberClausul clausul = iterator.next();
	            			if (clausul != null){
	            				if (clausul.getReductionType().intValue() == TreatmentUpgradeType.PRO_RATE_UPGRADE_CALC){
	            					if (claimId.getCurrentRoomRate() != null){
	            						
	            						if (clausul.getMultiplierCalculationType() != null && clausul.getMultiplierCalculationType().intValue() == TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK){
	            							multiplierCalculation = TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK;
	            						}
	            						MemberProduct product = claimId.getProductId();
	            						
	            						String[] requiredProduct = {};
	            						
	            						if (product != null){
	            							product = memberProductService.get(product.getMemberProductId(),requiredProduct);
	            						}
	            						
	            						if (product != null && (product.getProductClassRate() != null && product.getProductClassRate().doubleValue() != 0.0)){
	            							double current = claimId.getCurrentRoomRate().doubleValue();
	            							double benefitRoom = product.getProductClassRate().doubleValue();
	            							multiplier = benefitRoom / current;
	            						}
	            						else if ((product.getProductClassRate() != null && product.getProductClassRate().doubleValue() == 0.0) || claimId.getProductRoomRate() != null){
	            							double current = claimId.getCurrentRoomRate().doubleValue();
	            							double benefitRoom = claimId.getProductRoomRate().doubleValue();
	            							multiplier = benefitRoom / current;
	            						}
	            					}
	            				}
	            				else if (clausul.getReductionType().intValue() == TreatmentUpgradeType.PERCENTAGE_UPGRADE_CALC){
	            					if (clausul.getBenefitReductionPercentage() != null){
	            						double reductionPercentage = clausul.getBenefitReductionPercentage().doubleValue();
	            						if (reductionPercentage > 1){
	            							// percentage belum dibagi dengan 100
	            							reductionPercentage = reductionPercentage / 100;
	            							multiplier = multiplier - reductionPercentage;
	            						}
	            						else if (reductionPercentage <= 1){
	            							multiplier = multiplier - reductionPercentage;
	            						}
	            						
	            						if (clausul.getMultiplierCalculationType() != null && clausul.getMultiplierCalculationType().intValue() == TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK){
	            							multiplierCalculation = TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK;
	            						}
	            					}
	            				}
	            				else if (clausul.getReductionType().intValue() == TreatmentUpgradeType.FIXED_POINT_CALC){
	            					
	            				}
	            			}
	            		}
	            	}            	
            	}
            }

            /*
             * - Checking Clausul
             */

            Collection<ClaimItem> retrieved = null;

            Criteria c = claimItemDao.getCriteria();
            
            String[] requiredItem = {"ClaimItem.MemberBenefitId","ClaimItem.ClaimId",
            		"ClaimItem.ClaimId.ClaimTypeId","ClaimItem.ClaimId.MemberId"};
            
            String[] eqParam = {"claimId.claimId",  "claimItemStatus.caseStatusId","deletedStatus"};

            Object[] eqValue = {claimId.getClaimId(),  ClaimItem.CLAIM_ITEM_APPROVED,Integer.valueOf(0)};

            DaoSupportUtil.setEqParam(eqParam, eqValue, c);
            DaoSupportUtil.setOrderBy(true, "claimItemValue", c);
            

            retrieved = c.list();
            
            for (Iterator iterator = retrieved.iterator(); iterator.hasNext();) {
				ClaimItem claimItem = (ClaimItem) iterator.next();
				
				DaoSupportUtil.lazyInit(requiredItem, claimItem);
			}
            
            if (retrieved == null){
            	c = null;
            	c = claimItemDao.getCriteria();
                Object[] eqValueVoid = {claimId.getClaimId(),  Claim.CLAIM_VOID,Integer.valueOf(0)};
                DaoSupportUtil.setEqParam(eqParam, eqValueVoid, c);
                DaoSupportUtil.setOrderBy(true, "claimItemValue", c);

                retrieved = c.list();
            }
            else if (retrieved != null && retrieved.size() == 0){
            	c = null;
            	c = claimItemDao.getCriteria();
                Object[] eqValueVoid = {claimId.getClaimId(),  Claim.CLAIM_VOID,Integer.valueOf(0)};
                DaoSupportUtil.setEqParam(eqParam, eqValueVoid, c);
                DaoSupportUtil.setOrderBy(true, "claimItemValue", c);

                retrieved = c.list();            	
            }

            double currentBenefitLimit = 0.0;
            double outerLimitBenefit = 0.0;
            double additionalLayerBenefit = 0.0;
            boolean isMemberActive = true;


            double deductibleSMM = -1.0;
            
            MemberLimitLayer smmLimit = null;
            
            

            
            if (claimId.getDiagnosisId() != null){
            	smmLimit = memberLimitLayerService.getSMMLayer(member.getMemberId(), claimId.getDiagnosisId().getDiagnosisId(), 
            			claimId.getCaseCategoryId().getCaseCategoryId());
            }
            if (claimId.getDiagnosis2Id() != null){
            	smmLimit = memberLimitLayerService.getSMMLayer(member.getMemberId(), claimId.getDiagnosis2Id().getDiagnosisId(),
            			claimId.getCaseCategoryId().getCaseCategoryId());
            }
            if (claimId.getDiagnosis3Id() != null){
            	smmLimit = memberLimitLayerService.getSMMLayer(member.getMemberId(), claimId.getDiagnosis3Id().getDiagnosisId(),
            			claimId.getCaseCategoryId().getCaseCategoryId());
            }
        	

            

            if (smmLimit != null){
	            if (smmLimit.getDeductible() != null){
	            	deductibleSMM = smmLimit.getDeductible();
	            }
            }
        	

            if (retrieved != null && retrieved.size() > 0 && isMemberActive) {

                result = new Vector<ClaimItem>();

                Iterator<ClaimItem> iterator = retrieved.iterator();

                MemberBenefit memberBenefit = null;

                MemberProduct memberProduct = memberProductService.getMemberProductByDate(member.getMemberId(), 
						claimId.getCaseCategoryId().getCaseCategoryId(),
						 claimId.getAdmissionDate(),
							claimId.getDischargeDate());
				
				if (memberProduct == null){
					memberProduct = memberProductService.getMemberActiveProduct(claimId.getMemberId().getMemberId(), 
							claimId.getCaseCategoryId().getCaseCategoryId());
				}
				else {
					if (memberProduct.getFamilyProductId() != null){
						memberProduct = memberProduct.getFamilyProductId();
					}
				}
				
				MemberLimitLayer additionalLimitLayer = null;
				
					if (memberProduct != null){
						additionalLimitLayer = memberLimitLayerService.getAvailableLayer(memberProduct.getMemberId().getMemberId(), 
						  claimId.getCaseCategoryId().getCaseCategoryId(), 	memberProduct.getMemberProductId());
					}
	            
                
                if (memberProduct == null){
                	// potentially divert benefit
                	
                	Collection<MemberBenefit> benefitList = memberBenefitService.getMemberBenefitByDate(member.getMemberId(), claimId.getCaseCategoryId().getCaseCategoryId(), claimId.getAdmissionDate(), claimId.getDischargeDate());
                	
                	if (benefitList != null && benefitList.size() > 0){
                		for (Iterator iterator2 = benefitList.iterator(); iterator2
								.hasNext();) {
							MemberBenefit memberBenefit2 = (MemberBenefit) iterator2
									.next();
							
							if (memberBenefit2 != null && memberBenefit2.getMemberProductId() != null){
								memberProduct = memberBenefit2.getMemberProductId();
								
								if (memberProduct != null){
									if (memberProduct.getMemberProductStatus().getStatusId().intValue() != SubscriptionStatus.ACTIVE && 
											member.getStatus().intValue() == SubscriptionStatus.ACTIVE){
										// update ambil yang terbaru
										
										memberProduct = memberProductService.getMemberActiveProduct(member.getMemberId(), memberBenefit2.getProductCaseCategoryId().getCaseCategoryId());
										
										claimId.setProductId(memberProduct);
									}									
									else if (memberProduct.getMemberProductStatus().getStatusId().intValue() != SubscriptionStatus.ACTIVE && 
											member.getStatus().intValue() == SubscriptionStatus.EXPIRED){
										

										memberProduct = memberProductService.getMemberActiveProduct(member.getMemberId(), memberBenefit2.getProductCaseCategoryId().getCaseCategoryId());
										
										claimId.setProductId(memberProduct);
										
									}
								}
								
								break;
							}
							
						}
                	}
                }

                
                if (memberProduct != null && smmLimit == null) {
                	// check apakah family plan atau shared plan atau personal limit
                	String[] reqMP = {"MemberProduct.ProductId","MemberProduct.ProductId.ProductType"};
                	memberProduct = memberProductService.get(memberProduct.getMemberProductId(),reqMP);
                	
                	if (memberProduct.getSecondaryCoverageId() == null && memberProduct.getFamilyProductId() == null){
                	
                		if (memberProduct.getProductId().getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY || 
                				memberProduct.getProductId().getProductType().getProductTypeId().intValue() == ProductType.FAMILY ){
                			
                			if (member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())){
                				outerLimitBenefit = memberProduct.getActualBenefitLimit();
                			}
                			else {
                				if (memberProduct.getFamilyProductId() != null){
                					MemberProduct familyProduct = memberProductService.get(memberProduct.getFamilyProductId().getMemberProductId());
                					
                					if (familyProduct != null){
                        				outerLimitBenefit = familyProduct.getActualBenefitLimit();

                					}
                				}
                				else {
                					outerLimitBenefit = memberProduct.getActualBenefitLimit();
                				}                				
                			}
                		}
                		else {                		
		                    outerLimitBenefit = memberProduct.getAnnualBenefit();
		                    double benefitUsage = memberProduct.getBenefitUsage() == null ? 0 : memberProduct.getBenefitUsage();
		
		                    if (outerLimitBenefit >= 0) {
		                        outerLimitBenefit = outerLimitBenefit  - benefitUsage;
		                    }
                		}
                	}
                	else if (memberProduct.getSecondaryCoverageId() != null && memberProduct.getFamilyProductId() == null){
                		MemberProduct secondaryPlan = memberProductService.get(memberProduct.getSecondaryCoverageId().getMemberProductId());
                		
                        outerLimitBenefit = secondaryPlan.getActualBenefitLimit() == null ? 0 : secondaryPlan.getActualBenefitLimit();
                    	
                	}
                	else if (memberProduct.getFamilyProductId() != null && memberProduct.getSecondaryCoverageId() == null){
                		MemberProduct familyPlan = memberProductService.get(memberProduct.getFamilyProductId().getMemberProductId());
                		
                        outerLimitBenefit = familyPlan.getActualBenefitLimit() == null ? 0 : familyPlan.getActualBenefitLimit();
                	}
                	
                	
                }
                else if (memberProduct != null && smmLimit != null){
                	
                	if (smmLimit != null && smmLimit.getActualBenefitLimit() != null && smmLimit.getActualBenefitLimit() > 0){
                		
                		claimId.setIsUsingSMM(1);
                		claimId.setSmmLayerId(smmLimit);
                		
                		if (smmLimit.getFamilyLimitLayerId() != null){
                			outerLimitBenefit = smmLimit.getFamilyLimitLayerId().getActualBenefitLimit();
                		}
                		else {
                			outerLimitBenefit = smmLimit.getActualBenefitLimit();
                		}
                	}
                }

                // check ASO Limit
                // added by AP 20150319
                if (policy.getIsUsingFloatingFund() != null){
                	if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_POLICY){
                		double currentFund = policy.getCurrentPolicyFund() == null ? 0.0 : policy.getCurrentPolicyFund();
                		double minimumFund = policy.getFundBlockingLimit() == null ? 0.0 : policy.getFundBlockingLimit();
                		
                		
                		double asoLimitBenefit = currentFund - minimumFund;
                		
                		if (asoLimitBenefit < outerLimitBenefit){
                			outerLimitBenefit = asoLimitBenefit;
                		}
                	}
                	if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_COVERAGE){
                		String[] eqParamFund = {"deletedStatus","policyId.policyId","caseCategoryId.caseCategoryId"};
                		Object[] eqValueFund = {0,policy.getPolicyId(),claimId.getCaseCategoryId().getCaseCategoryId()};
                		
                		Collection<PolicyCoverageFund> fundList = policyCoverageFundService.search(null,null,eqParamFund,eqValueFund,0,1);
                		
                		if (fundList != null){
                			Iterator<PolicyCoverageFund> fundIterator = fundList.iterator();
                			
                			if (fundIterator.hasNext()){
                				PolicyCoverageFund fundAsoLimit = fundIterator.next();
                				
                				if (fundAsoLimit != null){
                					double currentFund = fundAsoLimit.getCurrentFundValue() == null ? 0.0 : fundAsoLimit.getCurrentFundValue();
                            		double minimumFund = fundAsoLimit.getMinimumAsoValue() == null ? 0.0 : fundAsoLimit.getMinimumAsoValue();
                            		
                            		double asoLimitBenefit = currentFund - minimumFund;
                            		
                            		if (asoLimitBenefit < outerLimitBenefit){
                            			outerLimitBenefit = asoLimitBenefit;
                            		}
                				}
                			}
                		}
                	}
                }
                
                if (outerLimitBenefit == 0){
                	if (additionalLimitLayer != null){
                		claimId.setMultiLayerId(additionalLimitLayer);
                		claimId.setIsUsingMultiLayerLimit(1);
                		outerLimitBenefit = additionalLimitLayer.getActualBenefitLimit();
                	}
                }
                int i = 0;
                while (iterator.hasNext()) {
                    ClaimItem claimItem = iterator.next();

                    if (claimItem != null) {

                    	claimItem.setIsSMMBenefit(0);
                    	claimItem.setClaimId(claimId);
                    	
                        if (member != null) {
                            currentBenefitLimit = member.getActualCustomerLimit() == null ? 0
                                    : member.getActualCustomerLimit().doubleValue();
                        }
                        
                        if (claimId.getIsUsingMultiLayerLimit() != null && claimId.getIsUsingMultiLayerLimit().intValue() == 1){
                        	claimItem.setIsMultiLayerBenefit(1);
                        	
                        }

                        Collection<MemberBenefit> collections = null;

                        /**
                         * Kasus Khusus yaitu tindakan operasi
                         */

                        
                        if (claimItem.getItemId().getItemCategoryId().getIsSurgeryItem() != null && 
                        		claimItem.getItemId().getItemCategoryId().getIsSurgeryItem().intValue() == 1){
                        	
	                        if (claimItem.getClaimId().getIsSurgery() != null && claimItem.getClaimId().getIsSurgery().intValue() == 1){
	                            
	                        	if (memberProduct != null){
	
	                        		/**
	                        		String[] params = {"caseCategoryId.caseCategoryId",
		                                    "itemCategoryId.itemCategoryId",
		                                    "memberId.memberId", "memberProductId.memberProductId"};
		
		                                Object[] paramValue = {
		                                    claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),
		                                    claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
		                                    claimId.getMemberId().getMemberId(), memberProduct.getMemberProductId() };
		                                
	
	                                String[] required = {""};
	                                collections = memberBenefitService.search(null, null, params, paramValue,
	                                        required, 0, 1);
	                                
	                                if (collections == null || collections.size() == 0){
	                                	*/
	                                	if (additionalLimitLayer == null){
	                                		collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
	                            				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
	                            				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
	                            				claimId.getAdmissionDate(), claimId.getDischargeDate(),0);
	                                	}
	                                	else {
	                                		if (memberProduct != null && memberProduct.getActualBenefitLimit() > 0.0){

		                                		collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
			                            				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
			                            				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
			                            				claimId.getAdmissionDate(), claimId.getDischargeDate(),0);	
	                                		}
	                                		else if (memberProduct != null && memberProduct.getActualBenefitLimit() == -1.0){

		                                		collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
			                            				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
			                            				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
			                            				claimId.getAdmissionDate(), claimId.getDischargeDate(),0);
	                                		}
	                                		else {

		                                		collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
			                            				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
			                            				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
			                            				claimId.getAdmissionDate(), claimId.getDischargeDate(),additionalLimitLayer.getLayer());
	                                		}
	                                	}
	                                //}
	                        	}
	                        	else {
	                        		if (additionalLimitLayer == null){
	                        			collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
	                        				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
	                        				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
	                        				claimId.getAdmissionDate(), claimId.getDischargeDate(),0);
	                        		}
	                        		else {
	                        			collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
		                        				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
		                        				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
		                        				claimId.getAdmissionDate(), claimId.getDischargeDate(),additionalLimitLayer.getLayer());
	                        		}
	                        	}
	                        } 
                        }
                        else {
                        	
                        	if (memberProduct != null && smmLimit == null){
                        		
                        			/**
	                        		String[] params = {"caseCategoryId.caseCategoryId",
	                                    "itemCategoryId.itemCategoryId",
	                                    "memberId.memberId", "memberProductId.memberProductId"};
	
	                        		System.out.println("Member Id = " + claimId.getMemberId().getMemberId() );
	                        	
	                                Object[] paramValue = {
	                                    claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),
	                                    claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
	                                    claimId.getMemberId().getMemberId(), memberProduct.getMemberProductId()};
	
	                                
	                                collections = memberBenefitService.search(null, null, params, paramValue,
	                                        required, 0, 1);
	                                */
                        			
                        			String[] required = {""};
	                            	
                        			if (additionalLimitLayer == null){
                                		collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
                            				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
                            				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
                            				claimId.getAdmissionDate(), claimId.getDischargeDate(),0);
                                	}
                                	else {
                                   		if (memberProduct != null && memberProduct.getActualBenefitLimit() > 0.0){

	                                		collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
		                            				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
		                            				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
		                            				claimId.getAdmissionDate(), claimId.getDischargeDate(),0);	
                                		}
                                		else if (memberProduct != null && memberProduct.getActualBenefitLimit() == -1.0){

	                                		collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
		                            				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
		                            				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
		                            				claimId.getAdmissionDate(), claimId.getDischargeDate(),0);
                                		}
                                		else {

	                                		collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
		                            				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
		                            				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
		                            				claimId.getAdmissionDate(), claimId.getDischargeDate(),additionalLimitLayer.getLayer());
                                		}
                                	}
	                                
	                                if (collections != null && collections.size() == 0){
	                                	// Potensial Divert Benefit yang menggunakan item category yang sama dengan 
	                                	// benefit 'seharusnya' .. contoh :  Dokter Gigi (OP) di mix dengan Perawatan Gusi (Dental)
	                                	// Dokter Gigi tidak akan terdeteksi apabila claim utama nya adalah "Dental"
	                                	
	                                	
	                                	String[] paramsDivert = {"caseCategoryId.caseCategoryId",
	    	                                    "itemCategoryId.itemCategoryId",
	    	                                    "memberId.memberId"};
	    	
	    	                        	System.out.println("Member Id = " + claimId.getMemberId().getMemberId() );
	    	                        	
	    	                                Object[] paramDivertValue = {
	    	                                    claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),
	    	                                    claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
	    	                                    claimId.getMemberId().getMemberId()};
	    	
	    	                                collections = memberBenefitService.search(null, null, paramsDivert, paramDivertValue,
	    	                                        required, 0, 1);
	                                	
	                                }
                        	}
                        	else if (memberProduct != null && smmLimit != null){
    
    
	                        	System.out.println("Member Id = " + claimId.getMemberId().getMemberId() );
	                        	
	                        	String[] checkSMMParam = {"caseCategoryId.caseCategoryId",	                                    
	                                    "memberId.memberId", "memberProductId.memberProductId","memberLimitLayerId.memberLimitLayerId"};
	                        	
	                        	Object[] checkSMMValue = {
	                                    claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),	                                    
	                                    claimId.getMemberId().getMemberId(), memberProduct.getMemberProductId(),smmLimit.getMemberLimitLayerId()};
	                        	
	                        	int totalSMMBenefit = memberBenefitService.getTotal(null,null,checkSMMParam,checkSMMValue);
	                        	
	                        	if (totalSMMBenefit > 0){
	                        		
	                        		String[] params = {"caseCategoryId.caseCategoryId",
		                                    "itemCategoryId.itemCategoryId",
		                                    "memberId.memberId", "memberProductId.memberProductId","memberLimitLayerId.memberLimitLayerId"};
		
	                        		Object[] paramValue = {
		                                    claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),
		                                    claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
		                                    claimId.getMemberId().getMemberId(), memberProduct.getMemberProductId(),smmLimit.getMemberLimitLayerId()};
		
	                                String[] required = {""};
	                                collections = memberBenefitService.search(null, null, params, paramValue,
	                                        required, 0, 1);
	                                
	                                if (collections != null && collections.size() == 0){
	                                	// Potensial Divert Benefit yang menggunakan item category yang sama dengan 
	                                	// benefit 'seharusnya' .. contoh :  Dokter Gigi (OP) di mix dengan Perawatan Gusi (Dental)
	                                	// Dokter Gigi tidak akan terdeteksi apabila claim utama nya adalah "Dental"
	                                	
	                                	
	                                	String[] paramsDivert = {"caseCategoryId.caseCategoryId",
	    	                                    "itemCategoryId.itemCategoryId",
	    	                                    "memberId.memberId"};
	    	
	    	                        	System.out.println("Member Id = " + claimId.getMemberId().getMemberId() );
	    	                        	
	    	                                Object[] paramDivertValue = {
	    	                                    claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),
	    	                                    claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
	    	                                    claimId.getMemberId().getMemberId()};
	    	
	    	                                collections = memberBenefitService.search(null, null, paramsDivert, paramDivertValue,
	    	                                        required, 0, 1);
	                                	
	                                }
	                                claimItem.setIsSMMBenefit(1);
	                        	}	
	                        	else if (totalSMMBenefit == 0){
	                            	String[] paramsNonSMM = {"caseCategoryId.caseCategoryId",
		                                    "itemCategoryId.itemCategoryId",
		                                    "memberId.memberId", "memberProductId.memberProductId"};
		
		                        	System.out.println("Member Id = " + claimId.getMemberId().getMemberId() );
		                        	
	                                Object[] paramNonSMMValue = {
	                                    claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),
	                                    claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
	                                    claimId.getMemberId().getMemberId(), memberProduct.getMemberProductId()};
	
	                                String[] required = {""};
	                                collections = memberBenefitService.search(null, null, paramsNonSMM, paramNonSMMValue,
	                                        required, 0, 1);
	                                
	                                if (collections != null && collections.size() == 0){
	                                	// Potensial Divert Benefit yang menggunakan item category yang sama dengan 
	                                	// benefit 'seharusnya' .. contoh :  Dokter Gigi (OP) di mix dengan Perawatan Gusi (Dental)
	                                	// Dokter Gigi tidak akan terdeteksi apabila claim utama nya adalah "Dental"
	                                	
	                                	
	                                	String[] paramsDivert = {"caseCategoryId.caseCategoryId",
	    	                                    "itemCategoryId.itemCategoryId",
	    	                                    "memberId.memberId"};
	    	
	    	                        	System.out.println("Member Id = " + claimId.getMemberId().getMemberId() );
	    	                        	
    	                                Object[] paramDivertValue = {
    	                                    claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),
    	                                    claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
    	                                    claimId.getMemberId().getMemberId()};
    	
    	                                collections = memberBenefitService.search(null, null, paramsDivert, paramDivertValue,
    	                                        required, 0, 1);
	                                	
	                                }	    	
	                                claimItem.setIsSMMBenefit(0);
	                        	}   
                        	}
                        	else if (memberProduct == null && smmLimit == null) {
                        		// Divert Benefit
                        		
                        		System.out.println("DIVERT BENEFIT --> IS FOUND ");
                        		if (additionalLimitLayer == null){
                        			collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
                        				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
                        				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
                        				claimId.getAdmissionDate(), claimId.getDischargeDate(),0);
                        		}
                        		else {
                        			collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
                            				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
                            				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
                            				claimId.getAdmissionDate(), claimId.getDischargeDate(),additionalLimitLayer.getLayer());
                        		}
                        		
                        		System.out.println("HASIL Collection --> " + collections);
                        	}
                        }

                        if (collections != null && collections.size() > 0) {

                            Iterator iteratorBenefit = collections.iterator();

                            if (iteratorBenefit != null
                                    && iteratorBenefit.hasNext()) {

                                memberBenefit = (MemberBenefit) iteratorBenefit.next();

                                if (memberBenefit != null) {
                                    // benefit = memberBenefit.getBenefitId();

                                    CoverageStatus coverage = new CoverageStatus();
                                    coverage.setCoverageStatusId(CoverageStatus.COVERED);

                                    claimItem.setCoverageStatus(coverage);
                                    claimItem.setMemberBenefitId(memberBenefit);

                                    Double currentUsageValue = memberBenefit.getBenefitUsage();

                                    BenefitUsageType calculationMethod = memberBenefit.getBenefitCalculationMethod();


                                    boolean upgradable = memberBenefit.isBenefitUpgradable() == null ? false   : true;
                                    Boolean providerContract = memberBenefit.isProviderContract();

                                    if (calculationMethod != null
                                            && calculationMethod.getBenefitUsageTypeId() == MemberBenefit.MAX_DAILY_AND_OCCURANCE_METHOD) {

                                        outerLimitBenefit = maxDailyAndOccuranceCalculationMethod(
                                                memberBenefit, claimItem,
                                                outerLimitBenefit,multiplier,multiplierCalculation);

                                    } else if (calculationMethod != null
                                            && calculationMethod.getBenefitUsageTypeId() == MemberBenefit.ACCUMULATIVE_VALUE) {

                                        outerLimitBenefit = accumulativeCalculateMethod(
                                                memberBenefit, claimItem,
                                                outerLimitBenefit,multiplier,multiplierCalculation);

                                    }  else if (calculationMethod != null
                                            && calculationMethod.getBenefitUsageTypeId() == MemberBenefit.PERDISABILITY) {

                                        outerLimitBenefit = perDisabilityCalculationMethod(
                                                memberBenefit, claimItem,
                                                upgradable, outerLimitBenefit,multiplier,multiplierCalculation);
                                    }
                                }

                            } else {
                                CoverageStatus coverage = new CoverageStatus();
                                coverage.setCoverageStatusId(CoverageStatus.UNCOVERED);
                                claimItem.setCoverageStatus(coverage);
                                claimItem.setClaimItemCoveredValue(Double.valueOf(0));
                                claimItem.setBenefitCheckRemarks(ClaimItem.TIDAK_AMBIL_BENEFIT);
                                claimItem.setExcessValue(claimItem.getClaimItemValue());                            
                            }                            
                        }
                        else {
                            CoverageStatus coverage = new CoverageStatus();
                            coverage.setCoverageStatusId(CoverageStatus.UNCOVERED);

                            claimItem.setCoverageStatus(coverage);
                            claimItem.setClaimItemCoveredValue(Double.valueOf(0));
                            claimItem.setBenefitCheckRemarks(ClaimItem.TIDAK_AMBIL_BENEFIT);
                            claimItem.setExcessValue(claimItem.getClaimItemValue());
                        	
                        }

                    }
                    result.add(claimItem);
                }
                
        //        claimDao.update(claimId);
                
            }            
        }

        return result;
    }
    
    //Add 20150414 by FVO, penambahan benefit claim item calculation dengan status Claim Item Open
    //Digunakan untuk informasi saat print Benefit Letter Calculation
    public Collection<ClaimItem> getBenefitCheckCalculationForLetter(Claim claimId)
            throws Exception {
        Collection<ClaimItem> result = null;

        boolean isSurgery = false;
        boolean isComplication = false;
        boolean isRoomUpgrade = false;
        
        Policy policy = null;
        double multiplier = 1;
        int roomUpgradeCalculationSubject = -1;
        int multiplierCalculation = TreatmentUpgradeType.MULTIPLIER_WITH_BENEFIT_CHECK;

        if (claimId != null) {

        	Member member = memberService.get(claimId.getMemberId().getMemberId());
        	
        	MemberGroup memberGroup = member.getMemberGroupId();
        	
        	
        	
        	if (memberGroup != null){
        		policy = policyService.getGroupActivePolicy(memberGroup.getMemberGroupId());
        	}
        	
            if (claimId.getIsSurgery() != null) {
                if (claimId.getIsSurgery().intValue() == 1) {
                    isSurgery = true;
                }
            }

            if (claimId.getHasComplication() != null) {
                if (claimId.getHasComplication().intValue() == 1) {
                    isComplication = true;
                }
            }
            if (claimId.getRoomUpgradeType() != null){
            	
            	
            	int roomUpgradeType = claimId.getRoomUpgradeType().intValue();
            	
            	if(roomUpgradeType == TreatmentUpgradeType.KAMAR_PENUH || roomUpgradeType == TreatmentUpgradeType.KAMAR_TIDAK_TERSEDIA ||
            		roomUpgradeType == TreatmentUpgradeType.NAIK_ATAS_PERMINTAAN_SENDIRI || roomUpgradeType == TreatmentUpgradeType.REKOMENDASI_DOKTER){
            		
            		isRoomUpgrade = true;	
            	}
            	
            	String[] eqParam = {"memberId.memberId","deletedStatus","treatmentUpgradeType.treatmentUpgradeTypeId",
            			"caseCategoryId.caseCategoryId"};
            	
            	Object[] eqValue = {claimId.getMemberId().getMemberId(),Integer.valueOf(0),claimId.getRoomUpgradeType(),
            			claimId.getCaseCategoryId().getCaseCategoryId()};
            	
            	int total = memberClausulService.getTotal(null,null,eqParam,eqValue);
            	
            	if (total > 0){
            		Collection<MemberClausul> clausulList = memberClausulService.search(null,null,eqParam,eqValue,0,1);
            		
            		Iterator<MemberClausul> iterator = clausulList.iterator();
            		
            		if (iterator.hasNext()){
            			MemberClausul clausul = iterator.next();
            			if (clausul != null){
            				if (clausul.getReductionType().intValue() == TreatmentUpgradeType.PRO_RATE_UPGRADE_CALC){
            					if (claimId.getCurrentRoomRate() != null){
            						
            						if (clausul.getMultiplierCalculationType() != null && clausul.getMultiplierCalculationType().intValue() == TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK){
            							multiplierCalculation = TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK;
            						}
            						MemberProduct product = claimId.getProductId();
            						
            						String[] requiredProduct = {};
            						
            						if (product != null){
            							product = memberProductService.get(product.getMemberProductId(),requiredProduct);
            						}
            						
            						if (product != null && (product.getProductClassRate() != null && product.getProductClassRate().doubleValue() != 0.0)){
            							double current = claimId.getCurrentRoomRate().doubleValue();
            							double benefitRoom = product.getProductClassRate().doubleValue();
            							multiplier = benefitRoom / current;
            						}
            						else if ((product.getProductClassRate() != null && product.getProductClassRate().doubleValue() == 0.0) || claimId.getProductRoomRate() != null){
            							double current = claimId.getCurrentRoomRate().doubleValue();
            							double benefitRoom = claimId.getProductRoomRate().doubleValue();
            							multiplier = benefitRoom / current;
            						}
            					}
            				}
            				else if (clausul.getReductionType().intValue() == TreatmentUpgradeType.PERCENTAGE_UPGRADE_CALC){
            					if (clausul.getBenefitReductionPercentage() != null){
            						double reductionPercentage = clausul.getBenefitReductionPercentage().doubleValue();
            						if (reductionPercentage > 1){
            							// percentage belum dibagi dengan 100
            							reductionPercentage = reductionPercentage / 100;
            							multiplier = multiplier - reductionPercentage;
            						}
            						else if (reductionPercentage <= 1){
            							multiplier = multiplier - reductionPercentage;
            						}
            						
            						if (clausul.getMultiplierCalculationType() != null && clausul.getMultiplierCalculationType().intValue() == TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK){
            							multiplierCalculation = TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK;
            						}
            					}
            				}
            				else if (clausul.getReductionType().intValue() == TreatmentUpgradeType.FIXED_POINT_CALC){
            					
            				}
            			}
            		}
            	}            	
            }

            /*
             * - Checking Clausul
             */

            Collection<ClaimItem> retrieved = null;

            Criteria c = claimItemDao.getCriteria();
            String[] requiredItem = {"ClaimItem.MemberBenefitId","ClaimItem.ClaimId","ClaimItem.ClaimId.ClaimTypeId"};
            
            String[] eqParam = {"claimId.claimId",  "claimItemStatus.caseStatusId", "deletedStatus"};

            Object[] eqValue = {claimId.getClaimId(), 
            		claimId.getClaimStatus().getCaseStatusId() == Claim.CLAIM_PRE_OPEN ? ClaimItem.CLAIM_ITEM_PRE_OPEN : 
            			claimId.getClaimStatus().getCaseStatusId() == Claim.CLAIM_VERIFIED? ClaimItem.CLAIM_ITEM_APPROVED : ClaimItem.CLAIM_ITEM_OPEN, Integer.valueOf(0)};

            DaoSupportUtil.setEqParam(eqParam, eqValue, c);
            DaoSupportUtil.setOrderBy(true, "claimItemValue", c);
            

            retrieved = c.list();
            
            System.out.println("RETRIEVE SIZE : "+retrieved.size());
            for (Iterator iterator = retrieved.iterator(); iterator.hasNext();) {
				ClaimItem claimItem = (ClaimItem) iterator.next();
				
				DaoSupportUtil.lazyInit(requiredItem, claimItem);
			}
            
            if (retrieved == null){          
            	String[] eqP = {"claimId.claimId",  "claimItemStatus.caseStatusId"};
            	c = null;
            	c = claimItemDao.getCriteria();
                Object[] eqValueVoid = {claimId.getClaimId(),  Claim.CLAIM_VOID};
                DaoSupportUtil.setEqParam(eqP, eqValueVoid, c);
                DaoSupportUtil.setOrderBy(true, "claimItemValue", c);

                retrieved = c.list();
            }
            else if (retrieved != null && retrieved.size() == 0){
            	String[] eqP = {"claimId.claimId",  "claimItemStatus.caseStatusId"};
            	c = null;
            	c = claimItemDao.getCriteria();
                Object[] eqValueVoid = {claimId.getClaimId(),  Claim.CLAIM_VOID};
                DaoSupportUtil.setEqParam(eqP, eqValueVoid, c);
                DaoSupportUtil.setOrderBy(true, "claimItemValue", c);

                retrieved = c.list();            	
            }

            double currentBenefitLimit = 0.0;
            double outerLimitBenefit = -1;
            boolean isMemberActive = true;
            
            
            
            MemberLimitLayer smmLimit = null;
            
            MemberLimitLayer additionalLimitLayer = memberLimitLayerService.getAvailableLayer(member.getMemberId(), 
            		claimId.getCaseCategoryId().getCaseCategoryId());
            

            
            if (claimId.getDiagnosisId() != null){
            	smmLimit = memberLimitLayerService.getSMMLayer(member.getMemberId(), claimId.getDiagnosisId().getDiagnosisId(), 
            			claimId.getCaseCategoryId().getCaseCategoryId());
            }
            if (claimId.getDiagnosis2Id() != null){
            	smmLimit = memberLimitLayerService.getSMMLayer(member.getMemberId(), claimId.getDiagnosis2Id().getDiagnosisId(),
            			claimId.getCaseCategoryId().getCaseCategoryId());
            }
            if (claimId.getDiagnosis3Id() != null){
            	smmLimit = memberLimitLayerService.getSMMLayer(member.getMemberId(), claimId.getDiagnosis3Id().getDiagnosisId(),
            			claimId.getCaseCategoryId().getCaseCategoryId());
            }

        	
        	if (smmLimit != null && smmLimit.getActualBenefitLimit() != null && smmLimit.getActualBenefitLimit() > 0){
        		claimId.setIsUsingSMM(1);
        		
        		if (smmLimit.getFamilyLimitLayerId() != null){
        			outerLimitBenefit = smmLimit.getFamilyLimitLayerId().getActualBenefitLimit();
        		}
        		else {
        			outerLimitBenefit = smmLimit.getActualBenefitLimit();
        		}
        	}

            if (retrieved != null && retrieved.size() > 0 && isMemberActive) {

                result = new Vector<ClaimItem>();

                Iterator<ClaimItem> iterator = retrieved.iterator();

                MemberBenefit memberBenefit = null;

                //MemberProduct memberProduct = memberProductService.getMemberActiveProduct(member.getMemberId(), claimId.getCaseCategoryId().getCaseCategoryId());
                MemberProduct memberProduct = claimId.getProductId();
                
                if (memberProduct == null){
                	// potentially divert benefit
                	
                	Collection<MemberBenefit> benefitList = memberBenefitService.getMemberBenefitByDate(member.getMemberId(), claimId.getCaseCategoryId().getCaseCategoryId(), claimId.getAdmissionDate(), claimId.getDischargeDate());
                	
                	if (benefitList != null && benefitList.size() > 0){
                		for (Iterator iterator2 = benefitList.iterator(); iterator2
								.hasNext();) {
							MemberBenefit memberBenefit2 = (MemberBenefit) iterator2
									.next();
							
							if (memberBenefit2 != null && memberBenefit2.getMemberProductId() != null){
								memberProduct = memberBenefit2.getMemberProductId();
								break;
							}
							
						}
                	}
                }

                
                if (memberProduct != null) {
                	// check apakah family plan atau shared plan atau personal limit
                	String[] reqMP = {"MemberProduct.ProductId","MemberProduct.ProductId.ProductType"};
                	memberProduct = memberProductService.get(memberProduct.getMemberProductId(),reqMP);
                	
                	if (memberProduct.getSecondaryCoverageId() == null && memberProduct.getFamilyProductId() == null){
                	
                		if (memberProduct.getProductId().getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY || 
                				memberProduct.getProductId().getProductType().getProductTypeId().intValue() == ProductType.FAMILY ){
                			
                			if (member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())){
                				outerLimitBenefit = memberProduct.getActualBenefitLimit();
                			}
                		}
                		else {
                		
		                    outerLimitBenefit = memberProduct.getAnnualBenefit();
		
		                    if (outerLimitBenefit >= 0) {
		                        outerLimitBenefit = outerLimitBenefit  - memberProduct.getBenefitUsage();
		                    }
                		}
                	}
                	else if (memberProduct.getSecondaryCoverageId() != null && memberProduct.getFamilyProductId() == null){
                		MemberProduct secondaryPlan = memberProductService.get(memberProduct.getSecondaryCoverageId().getMemberProductId());
                		
                        outerLimitBenefit = secondaryPlan.getActualBenefitLimit() == null ? 0 : secondaryPlan.getActualBenefitLimit();
                    	
                	}
                	else if (memberProduct.getFamilyProductId() != null && memberProduct.getSecondaryCoverageId() == null){
                		MemberProduct familyPlan = memberProductService.get(memberProduct.getFamilyProductId().getMemberProductId());
                		
                        outerLimitBenefit = familyPlan.getActualBenefitLimit() == null ? 0 : familyPlan.getActualBenefitLimit();
                	}
                }
                else {                	
                }

                // check ASO Limit
                // added by AP 20150319
                if (policy.getIsUsingFloatingFund() != null){
                	if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_POLICY){
                		double currentFund = policy.getCurrentPolicyFund() == null ? 0.0 : policy.getCurrentPolicyFund();
                		double minimumFund = policy.getFundBlockingLimit() == null ? 0.0 : policy.getFundBlockingLimit();
                		
                		
                		double asoLimitBenefit = currentFund - minimumFund;
                		
                		if (asoLimitBenefit < outerLimitBenefit){
                			outerLimitBenefit = asoLimitBenefit;
                		}
                	}
                	if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_COVERAGE){
                		String[] eqParamFund = {"deletedStatus","policyId.policyId","caseCategoryId.caseCategoryId"};
                		Object[] eqValueFund = {0,policy.getPolicyId(),claimId.getCaseCategoryId().getCaseCategoryId()};
                		
                		Collection<PolicyCoverageFund> fundList = policyCoverageFundService.search(null,null,eqParamFund,eqValueFund,0,1);
                		
                		if (fundList != null){
                			Iterator<PolicyCoverageFund> fundIterator = fundList.iterator();
                			
                			if (fundIterator.hasNext()){
                				PolicyCoverageFund fundAsoLimit = fundIterator.next();
                				
                				if (fundAsoLimit != null){
                					double currentFund = fundAsoLimit.getCurrentFundValue() == null ? 0.0 : fundAsoLimit.getCurrentFundValue();
                            		double minimumFund = fundAsoLimit.getMinimumAsoValue() == null ? 0.0 : fundAsoLimit.getMinimumAsoValue();
                            		
                            		double asoLimitBenefit = currentFund - minimumFund;
                            		
                            		if (asoLimitBenefit < outerLimitBenefit){
                            			outerLimitBenefit = asoLimitBenefit;
                            		}
                				}
                			}
                		}
                	}
                }
                int i = 0;
                while (iterator.hasNext()) {
                    ClaimItem claimItem = iterator.next();

                    if (claimItem != null) {

                    	
                        if (member != null) {
                            currentBenefitLimit = member.getActualCustomerLimit() == null ? 0
                                    : member.getActualCustomerLimit().doubleValue();
                        }

                        Collection<MemberBenefit> collections = null;

                        /** 
                         * 
                         * Kasus Khusus yaitu tindakan operasi
                         */

                        
                        if (claimItem.getItemId().getItemCategoryId().getIsSurgeryItem() != null && 
                        		claimItem.getItemId().getItemCategoryId().getIsSurgeryItem().intValue() == 1){
                        	
	                        if (claimItem.getClaimId().getIsSurgery() != null && claimItem.getClaimId().getIsSurgery().intValue() == 1){
	                            
	                        	if (memberProduct != null){
	                        		/**
	                        		String[] params = {"caseCategoryId.caseCategoryId",
	                                    "itemCategoryId.itemCategoryId",
	                                    "memberId.memberId", "memberProductId.memberProductId","treatmentLevel"};
	
	                                Object[] paramValue = {
	                                    claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),
	                                    claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
	                                    claimId.getMemberId().getMemberId(), memberProduct.getMemberProductId(),
	                                    Integer.valueOf(claimItem.getClaimId().getSurgeryLevel()) };
	                                    
	                                    */
	                        		
	                        		String[] params = {"caseCategoryId.caseCategoryId",
		                                    "itemCategoryId.itemCategoryId",
		                                    "memberId.memberId", "memberProductId.memberProductId"};
		
		                                Object[] paramValue = {
		                                    claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),
		                                    claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
		                                    claimId.getMemberId().getMemberId(), memberProduct.getMemberProductId() };
		                                
	
	                                String[] required = {""};
	                                collections = memberBenefitService.search(null, null, params, paramValue,
	                                        required, 0, 1);
	                                
	                                if (collections == null || collections.size() == 0){
	                                	if (additionalLimitLayer == null){
	                                		collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
	                            				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
	                            				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
	                            				claimId.getAdmissionDate(), claimId.getDischargeDate(),0);
	                                	}
	                                	else {
	                                		collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
		                            				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
		                            				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
		                            				claimId.getAdmissionDate(), claimId.getDischargeDate(),additionalLimitLayer.getLayer());
	                                	}
	                                }
	                        	}
	                        	else {
	                        		if (additionalLimitLayer == null){
	                        			collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
	                        				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
	                        				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
	                        				claimId.getAdmissionDate(), claimId.getDischargeDate(),0);
	                        		}
	                        		else {
	                        			collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
		                        				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
		                        				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
		                        				claimId.getAdmissionDate(), claimId.getDischargeDate(),additionalLimitLayer.getLayer());
	                        		}
	                        	}
	                        } 
                        }
                        else {
                        	
                        	if (memberProduct != null){
	                        	String[] params = {"caseCategoryId.caseCategoryId",
	                                    "itemCategoryId.itemCategoryId",
	                                    "memberId.memberId", "memberProductId.memberProductId"};
	
	                        	System.out.println("Member Id = " + claimId.getMemberId().getMemberId() );
	                        	
	                                Object[] paramValue = {
	                                    claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),
	                                    claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
	                                    claimId.getMemberId().getMemberId(), memberProduct.getMemberProductId()};
	
	                                String[] required = {""};
	                                collections = memberBenefitService.search(null, null, params, paramValue,
	                                        required, 0, 1);
	                                
	                                if (collections != null && collections.size() == 0){
	                                	// Potensial Divert Benefit yang menggunakan item category yang sama dengan 
	                                	// benefit 'seharusnya' .. contoh :  Dokter Gigi (OP) di mix dengan Perawatan Gusi (Dental)
	                                	// Dokter Gigi tidak akan terdeteksi apabila claim utama nya adalah "Dental"
	                                	
	                                	
	                                	String[] paramsDivert = {"caseCategoryId.caseCategoryId",
	    	                                    "itemCategoryId.itemCategoryId",
	    	                                    "memberId.memberId"};
	    	
	    	                        	System.out.println("Member Id = " + claimId.getMemberId().getMemberId() );
	    	                        	
	    	                                Object[] paramDivertValue = {
	    	                                    claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),
	    	                                    claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
	    	                                    claimId.getMemberId().getMemberId()};
	    	
	    	                                collections = memberBenefitService.search(null, null, paramsDivert, paramDivertValue,
	    	                                        required, 0, 1);
	                                	
	                                }
                        	}
                        	else {
                        		// Divert Benefit
                        		
                        		System.out.println("DIVERT BENEFIT --> IS FOUND ");
                        		if (additionalLimitLayer == null){
                        			collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
                        				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
                        				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
                        				claimId.getAdmissionDate(), claimId.getDischargeDate(),0);
                        		}
                        		else {
                        			collections = memberBenefitService.getMemberBenefitByDate(claimId.getMemberId().getMemberId(), 
                            				claimItem.getItemId().getItemCategoryId().getItemCategoryId(), 
                            				claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), 
                            				claimId.getAdmissionDate(), claimId.getDischargeDate(),additionalLimitLayer.getLayer());
                        		}
                        		
                        		System.out.println("HASIL Collection --> " + collections);
                        	}
                        }
                        
                        

                        if (collections != null && collections.size() > 0) {

                            Iterator iteratorBenefit = collections.iterator();

                            if (iteratorBenefit != null
                                    && iteratorBenefit.hasNext()) {

                                memberBenefit = (MemberBenefit) iteratorBenefit.next();

                                if (memberBenefit != null) {
                                    // benefit = memberBenefit.getBenefitId();

                                    CoverageStatus coverage = new CoverageStatus();
                                    coverage.setCoverageStatusId(CoverageStatus.COVERED);

                                    claimItem.setCoverageStatus(coverage);
                                    claimItem.setMemberBenefitId(memberBenefit);

                                    Double currentUsageValue = memberBenefit.getBenefitUsage();

                                    BenefitUsageType calculationMethod = memberBenefit.getBenefitCalculationMethod();


                                    boolean upgradable = memberBenefit.isBenefitUpgradable() == null ? false   : true;
                                    Boolean providerContract = memberBenefit.isProviderContract();

                                    if (calculationMethod != null
                                            && calculationMethod.getBenefitUsageTypeId() == MemberBenefit.MAX_DAILY_AND_OCCURANCE_METHOD) {

                                        outerLimitBenefit = maxDailyAndOccuranceCalculationMethod(
                                                memberBenefit, claimItem,
                                                outerLimitBenefit,multiplier,multiplierCalculation);

                                    } else if (calculationMethod != null
                                            && calculationMethod.getBenefitUsageTypeId() == MemberBenefit.ACCUMULATIVE_VALUE) {

                                        outerLimitBenefit = accumulativeCalculateMethod(
                                                memberBenefit, claimItem,
                                                outerLimitBenefit,multiplier,multiplierCalculation);

                                    }  else if (calculationMethod != null
                                            && calculationMethod.getBenefitUsageTypeId() == MemberBenefit.PERDISABILITY) {

                                        outerLimitBenefit = perDisabilityCalculationMethod(
                                                memberBenefit, claimItem,
                                                upgradable, outerLimitBenefit,multiplier,multiplierCalculation);
                                    }
                                }

                            } else {
                                CoverageStatus coverage = new CoverageStatus();
                                coverage.setCoverageStatusId(CoverageStatus.UNCOVERED);
                                claimItem.setCoverageStatus(coverage);
                                claimItem.setClaimItemCoveredValue(Double.valueOf(0));
                                claimItem.setBenefitCheckRemarks(ClaimItem.TIDAK_AMBIL_BENEFIT);
                                claimItem.setExcessValue(claimItem.getClaimItemValue());                            
                            }                            
                        }
                        else {
                            CoverageStatus coverage = new CoverageStatus();
                            coverage.setCoverageStatusId(CoverageStatus.UNCOVERED);

                            claimItem.setCoverageStatus(coverage);
                            claimItem.setClaimItemCoveredValue(Double.valueOf(0));
                            claimItem.setBenefitCheckRemarks(ClaimItem.TIDAK_AMBIL_BENEFIT);
                            claimItem.setExcessValue(claimItem.getClaimItemValue());
                        	
                        }

                    }
                    result.add(claimItem);
                }
            }
        }

        return result;
    }

    private void addBenefitCheckRemarks(ClaimItem claimItem, String remarks) {
        if (claimItem != null) {
            String current = claimItem.getBenefitCheckRemarks() == null ? ""
                    : claimItem.getBenefitCheckRemarks();

            if (!current.equalsIgnoreCase("")){
            	current += "\n" + remarks;
            }
            else {
            	current += remarks;
            }

            claimItem.setClaimItemRemarks(current);
            claimItem.setBenefitCheckRemarks(current);
        }
    }

    private double perDisabilityCalculationMethod(MemberBenefit memberBenefit,
            ClaimItem claimItem, boolean upgradable, double annualBenefitLimit,double multiplier, int multiplierCalculation) throws Exception {

        double outerLimitRemaining = -1;
        double coveredValue = claimItem.getClaimItemValue();
        double maxOccurance = -1.0;

        DecimalFormat df = new DecimalFormat("#.00");
        boolean isFamilyPlan = false;
        
        if (memberBenefit.getMemberProductId().getProductId() != null){
        	if (memberBenefit.getMemberProductId().getProductId().getProductType().getProductTypeId().intValue() == ProductType.FAMILY ||
        			memberBenefit.getMemberProductId().getProductId().getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY ){
        		isFamilyPlan = true;
        	}
        		
        }
        
        if (isFamilyPlan){
        	if (memberBenefit.getFamilyBenefitId() != null){
        		memberBenefit = memberBenefitService.get(memberBenefit.getFamilyBenefitId().getMemberBenefitId());
        	}
        }
        
        double sharingVariabel = 1;
        double paymentPercentage = 1;
        
        if (memberBenefit != null && claimItem != null) {
        	

            boolean isUsingCurrencyConversion = false;
            boolean isDivertBenefit = false;
            
            double currencyMultiplier = claimItem.getExchangeRate() == null ? 1.0 : claimItem.getExchangeRate();
            
            if (currencyMultiplier > 1.0 || currencyMultiplier < 1.0){
            	isUsingCurrencyConversion = true;
            }
            
            MemberBenefit sharedBenefit = memberBenefit.getSharedBenefitId();
            double sharedBenefitValue = 0.0;
            double sharedBenefitUsage = 0.0;
            boolean isSharedBenefit = false;
            
            
            claimItem.setBenefitCheckRemarks("");
            claimItem.setClaimItemRemarks("");
            
            if (claimItem.getIsSMMBenefit() != null){
            	if (claimItem.getIsSMMBenefit().intValue() == 1){
            		claimItem.setBenefitCheckRemarks("[SMM]");
            	}
            }
            if (claimItem.getIsMultiLayerBenefit() != null){
            	if (claimItem.getIsMultiLayerBenefit().intValue() == 1){
            		claimItem.setBenefitCheckRemarks("[MultiLayer]");
            	}
            }
         
            if (claimItem.getClaimId().getSmmLayerId() != null){
            	if (claimItem.getClaimId().getSmmLayerId().getDeductible() != null){
            		if (claimItem.getClaimId().getClaimChargeValue() < claimItem.getClaimId().getSmmLayerId().getDeductible()){
            			
            			
            			claimItem.setExcessValue(claimItem.getClaimItemValue());
                    	claimItem.setClaimItemApprovedValue(0.0);
                    	claimItem.setClaimItemCoveredValue(0.0);
                    	claimItem.setRefund(0.0);
                    	
                    	addBenefitCheckRemarks(claimItem, " ;Deductible = " + claimItem.getClaimId().getSmmLayerId().getDeductible());
                    	
                    	return outerLimitRemaining;
            		}
            	}
            	if (claimItem.getClaimId().getSmmLayerId().getCoSharePercentage() != null){
            		if (claimItem.getClaimId().getSmmLayerId().getCoSharePercentage().doubleValue() > 0){
            			
            		}
            	}
            }
            
            if (claimItem.getClaimId().getMultiLayerId() != null){
            	if (claimItem.getClaimId().getMultiLayerId().getDeductible() != null){
            		if (claimItem.getClaimId().getClaimChargeValue() < claimItem.getClaimId().getMultiLayerId().getDeductible()){
            			
            			
            			claimItem.setExcessValue(claimItem.getClaimItemValue());
                    	claimItem.setClaimItemApprovedValue(0.0);
                    	claimItem.setClaimItemCoveredValue(0.0);
                    	claimItem.setRefund(0.0);
                    	
                    	addBenefitCheckRemarks(claimItem, " ;Deductible = " + claimItem.getClaimId().getMultiLayerId().getDeductible());
                    	
                    	return outerLimitRemaining;
            		}
            	}
            	
            }
            
            if (memberBenefit.getMemberProductId().getProductId().getCaseCategory().getCaseCategoryId().intValue() 
            		!= memberBenefit.getCaseCategoryId().getCaseCategoryId().intValue()){
            	isDivertBenefit = true;
            }
            
            if (sharedBenefit != null){
            	isSharedBenefit = true;
            	sharedBenefitValue = sharedBenefit.getBenefitLimit().doubleValue();
            	
            	try {
            		Collection<ClaimItem> allClaimItem = getClaimItem(claimItem.getClaimId().getClaimId());
            		
            		for (Iterator iterator = allClaimItem.iterator(); iterator
    						.hasNext();) {
            			
    					ClaimItem claimItem2 = (ClaimItem) iterator.next();
    					
    					if (claimItem2.getMemberBenefitId() != null){
    						if (claimItem2.getMemberBenefitId().getMemberBenefitId().intValue() == sharedBenefit.getMemberBenefitId().intValue()){
    							
    							if (claimItem2.getClaimItemCoveredValue() != null){
    								sharedBenefitUsage += claimItem2.getClaimItemCoveredValue().doubleValue();
    							}
    						}
    					}    					
    				}
            	}
            	catch (Exception e){
            		e.printStackTrace();
            	}            	
            }
        	
            
            double costSharingAmount = 0.0;
            
            outerLimitRemaining = annualBenefitLimit;
            double deductible = 0.0;

            Double maxOccurancePerCase = memberBenefit.getMaxOccurancePerCase() == null ? 0.0 : memberBenefit.getMaxOccurancePerCase();
            maxOccurance = memberBenefit.getMaxBenefitOccurance() == null ? 0.0 : memberBenefit.getMaxBenefitOccurance();
            
            DateTime disabilityDateRange = new DateTime(claimItem.getClaimId().getAdmissionDate().getTime());
            
            int disabilityLength = memberBenefit.getMemberProductId().getDisabilityLength() == null ? 0 : memberBenefit.getMemberProductId().getDisabilityLength();
            disabilityDateRange = disabilityDateRange.minusDays(disabilityLength);
            
            
            double occurancePerCase = getBenefitTotalOccured(memberBenefit.getMemberId().getMemberId(),
            		new java.sql.Date(disabilityDateRange.getMillis()) , claimItem.getClaimId().getDischargeDate(), 
            		claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
            		claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), claimItem.getClaimId().getDiagnosisId().getDiagnosisId());
            
            double benefitUsagePerCase = getBenefitUsagePerCase(memberBenefit.getMemberId().getMemberId(),
            		new java.sql.Date(disabilityDateRange.getMillis()) , claimItem.getClaimId().getDischargeDate(), 
            		claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
            		claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(), claimItem.getClaimId().getDiagnosisId().getDiagnosisId());
            
            

            if (maxOccurance >= 0){
            	double benefitOccured = getBenefitTotalOccured(claimItem.getClaimId().getMemberId().getMemberId(),
                		claimItem.getClaimId().getMemberId().getEffectiveDate(), claimItem.getClaimId().getMemberId().getExpireDate(),
                		claimItem.getItemId().getItemCategoryId().getItemCategoryId(), claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId());
                
            	if (benefitOccured >= maxOccurance){
            		addBenefitCheckRemarks(claimItem, " ;Annual Occurance Max Usage Reached");

            		claimItem.setExcessValue(claimItem.getClaimItemValue());
                	claimItem.setClaimItemApprovedValue(0.0);
                	claimItem.setClaimItemCoveredValue(0.0);
                	
            		return outerLimitRemaining;
            	}
            }
            
            if (maxOccurancePerCase >= 0 && maxOccurancePerCase <= occurancePerCase){
        		addBenefitCheckRemarks(claimItem, " ;Max Occurance Per Disability Usage Reached");

        		claimItem.setExcessValue(claimItem.getClaimItemValue());
            	claimItem.setClaimItemApprovedValue(0.0);
            	claimItem.setClaimItemCoveredValue(0.0);
            	
        		return outerLimitRemaining;
            }
            

            boolean isUnlimited = outerLimitRemaining == -1.0 ? true : false;

            double maxBenefitPerCase = memberBenefit.getMaxBenefitPerCase() == null ? 0.0 : memberBenefit.getMaxBenefitPerCase();
            double benefitLimit = memberBenefit.getBenefitLimit() == null ? 0.0 : memberBenefit.getBenefitLimit();
            
            if (benefitLimit == 0.0){
            	if (memberBenefit.getReimbursementBenefitLimit() != null && memberBenefit.getReimbursementBenefitLimit().doubleValue() > 0.0){
            		if (claimItem.getClaimId().getClaimTypeId().getClaimTypeId().intValue() == ClaimType.REIMBURESEMENT){
            			benefitLimit = memberBenefit.getReimbursementBenefitLimit();
            		}
            	}
            }
            
            boolean asCharge = false;
            
            if (benefitLimit == -1.0){
            	asCharge = true;
            }

            

            Product product = memberBenefit.getProductId();

            if (claimItem.getClaimId().getIsExGratia() != null && claimItem.getClaimId().getIsExGratia().intValue() == 1){
            	addBenefitCheckRemarks(claimItem, " ;EX-GRATIA");
            }
            if (memberBenefit.getDeductibleLimit() != null ){
            	deductible = memberBenefit.getDeductibleLimit().doubleValue();
            }
            
            if (deductible > 0.0 && claimItem.getClaimItemValue().doubleValue() < deductible){
            	
            	claimItem.setExcessValue(claimItem.getClaimItemValue());
            	claimItem.setClaimItemApprovedValue(0.0);
            	claimItem.setClaimItemCoveredValue(0.0);
            	
            	addBenefitCheckRemarks(claimItem, " ;Deductible = " + deductible);
            	return outerLimitRemaining;
            }
            if (claimItem.getItemId().getItemCategoryId().getIsSurgeryItem() != null && claimItem.getItemId().getItemCategoryId().getIsSurgeryItem().intValue() == 1){
	            if (claimItem.getClaimId().getIsSurgery() != null && claimItem.getClaimId().getIsSurgery().intValue() == 1){
	            	if (claimItem.getClaimId().getSurgeryLevel() != null){
	            		int level = Integer.parseInt(claimItem.getClaimId().getSurgeryLevel());
	            		if (level == 0){
	            			addBenefitCheckRemarks(claimItem, " ;SURGERY KOMPLEKS");
	            		}
	            		if (level == 1){
	            			addBenefitCheckRemarks(claimItem, " ;SURGERY BESAR");
	            		}
	            		if (level == 2){
	            			addBenefitCheckRemarks(claimItem, " ;SURGERY SEDANG");
	            		}
	            		if (level == 3){
	            			addBenefitCheckRemarks(claimItem, " ;SURGERY KECIL");
	            		}
	            		if (level == 4){
	            			addBenefitCheckRemarks(claimItem, " ;SURGERY MINOR");
	            		}
	            	}
	            }
            }
            
            if (product != null) {
                if (claimItem.getClaimId().getClaimTypeId().getClaimTypeId().intValue() == ClaimType.CASHLESS) {
                    if (memberBenefit.getCashlessPercentage() != null) {
                        paymentPercentage = memberBenefit.getCashlessPercentage().doubleValue() / 100;
                        
                        if (memberBenefit.getCashlessPercentage().doubleValue() < 100){
                        	addBenefitCheckRemarks(claimItem, " ;Cashless Percentage " + memberBenefit.getCashlessPercentage().doubleValue() + " %");
                        }
                    }
                }

                if (claimItem.getClaimId().getClaimTypeId().getClaimTypeId().intValue() == ClaimType.REIMBURESEMENT) {
                    if (memberBenefit.getReimbursementPercentage() != null) {
                        paymentPercentage = memberBenefit.getReimbursementPercentage().doubleValue() / 100;
                        
                        if (memberBenefit.getReimbursementPercentage().doubleValue() < 100){
                        	addBenefitCheckRemarks(claimItem, " ;Reimbursement Percentage " + memberBenefit.getReimbursementPercentage().doubleValue() + " %");
                        }
                    }
                }
                if (memberBenefit.getCostSharingPercentage() != null){
                	paymentPercentage = paymentPercentage * (memberBenefit.getCostSharingPercentage().doubleValue() / 100);
                	
                	if (memberBenefit.getCostSharingPercentage().doubleValue() < 100){
                		addBenefitCheckRemarks(claimItem, " ;Cost Sharing " + memberBenefit.getCostSharingPercentage().doubleValue() + " %");	
                	}
                }
                if (claimItem.getClaimId().getSmmLayerId() != null){
                	if (claimItem.getClaimId().getSmmLayerId().getCoSharePercentage() != null){
                		paymentPercentage = paymentPercentage * claimItem.getClaimId().getSmmLayerId().getCoSharePercentage()/100.0;
                		addBenefitCheckRemarks(claimItem, " ;Cost Sharing SMM " + claimItem.getClaimId().getSmmLayerId().getCoSharePercentage().doubleValue() + " %");
                	}
                }
                if (claimItem.getClaimId().getMultiLayerId() != null){
                	if (claimItem.getClaimId().getMultiLayerId().getCoSharePercentage() != null){
                		paymentPercentage = paymentPercentage * claimItem.getClaimId().getMultiLayerId().getCoSharePercentage()/100.0;
                		addBenefitCheckRemarks(claimItem, " ;Cost Sharing MultiLayer " + claimItem.getClaimId().getMultiLayerId().getCoSharePercentage().doubleValue() + " %");
                	}
                }
                if (memberBenefit.getCostSharingAmount() != null){
                	costSharingAmount = memberBenefit.getCostSharingAmount();
                	BigDecimal bc = new BigDecimal(costSharingAmount);
                	String costShareAmt = bc.toPlainString() ;
                	addBenefitCheckRemarks(claimItem, " ;Cost Sharing = " + costShareAmt);
                }
            }
            
            double charge = paymentPercentage * (claimItem.getClaimItemValue()-costSharingAmount);
            double initialCharge = claimItem.getClaimItemValue();
            
            
            
            if (claimItem.getProductCurrencyChargeValue() != null && !claimItem.getProductCurrencyChargeValue().equals(initialCharge)){
            	charge = paymentPercentage * claimItem.getProductCurrencyChargeValue();
            	initialCharge = claimItem.getProductCurrencyChargeValue();
            	
            	if (costSharingAmount > 0.0){
                	charge = paymentPercentage * (claimItem.getProductCurrencyChargeValue()-costSharingAmount);

            	}
            }

            /*
             * benefitLimit --> if -1 maka as charge --> bisa diasosiasikan
             * dengan isAsCharge if -1 --> inner limit
             *
             */
            if (asCharge) {
                if (!isUnlimited) {
                    if (outerLimitRemaining <= charge) {
                        coveredValue = outerLimitRemaining;                        
                    }
                    else {
                    	coveredValue = charge;
                    }
                }
            } else {
                if (isUnlimited) {
                    if (charge >= benefitLimit) {
                        coveredValue = benefitLimit;
                    }
                } else {
                    if (outerLimitRemaining <= benefitLimit) {
                        if (charge >= outerLimitRemaining) {
                            coveredValue = outerLimitRemaining;
                        }
                    } else {
                        if (charge >= benefitLimit) {
                            coveredValue = benefitLimit;
                        }
                        else {
                        	if (benefitUsagePerCase > 0){
                        		if (benefitUsagePerCase+charge <= maxBenefitPerCase){
                        			coveredValue = charge;
                        		}
                        		else {
                        			coveredValue = maxBenefitPerCase - benefitUsagePerCase;
                        			addBenefitCheckRemarks(claimItem, " ;Max Benefit Per Case = " + Converter.getMoney(coveredValue));
                        		}
                        	}
                        	else {
                        		coveredValue = charge;
                        	}
                        }
                    }
                }
            }

            if (maxOccurancePerCase != null
                    && maxOccurancePerCase.intValue() > 0) {

                Double occurance = claimItem.getClaimItemAmount();
                int itemAmount = 0;

                if (occurance != null) {

                    itemAmount = occurance.intValue();

                    if (itemAmount > maxOccurancePerCase.intValue()) {

                        if (claimItem.getClaimItemValue() != null) {

                            if (asCharge) {

                                /*
                                 * Jika As Charge maka gunakan charge value pro
                                 * rate sebanyak jumlah maksimum per case yang
                                 * diperbolehkan.
                                 */

                                double perServiceValue = claimItem.getClaimItemValue().doubleValue()
                                        / itemAmount;

                                double approvedValue = (perServiceValue * maxOccurancePerCase.intValue())
                                        * sharingVariabel;

                                claimItem.setClaimItemCoveredValue(approvedValue);

                                // check dengan annualLimit apakah sudah
                                // melebihi atau belum..

                            } else {
                                /*
                                 * Jika tidak as charge maka by default
                                 * menggunakan member_benefit_limit dan
                                 * dikalikan dengan jumlah maksimum per case
                                 * yang diperbolehkan.
                                 */


                                double approvedValue = memberBenefit.getBenefitLimit().doubleValue()
                                        * itemAmount * sharingVariabel;

                                claimItem.setClaimItemCoveredValue(approvedValue);

                            }
                        }
                        //
                    } else {
                        // ada 3 kasus yang mungkin terjadi
						/*
                         * 1. AS CHARGE 2. MAX per Day 3. MAX Total Per Case
                         */

                        if (asCharge) {
                            claimItem.setClaimItemCoveredValue(charge);
                        } else {
                        }
                    }
                }
            }


            System.out.println("[post] ITEM CHARGE = " + charge + " MAX BENEFIT COV: " + coveredValue + " ITEM = " + claimItem.getItemId().getItemName() + " MULTIPLIER = " + multiplier);            
            
            if (claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.INPATIENT){
            	if (claimItem.getClaimId().getRoomUpgradeType() != null){
            		if (claimItem.getClaimId().getRoomUpgradeType().intValue() == TreatmentUpgradeType.NAIK_ATAS_PERMINTAAN_SENDIRI){
            			addBenefitCheckRemarks(claimItem, " ;NAIK KELAS APS");
            		}
            		if (claimItem.getClaimId().getRoomUpgradeType().intValue() == TreatmentUpgradeType.KAMAR_PENUH){
            			addBenefitCheckRemarks(claimItem, " ;NAIK KELAS - KAMAR PENUH");
            		}
            		if (claimItem.getClaimId().getRoomUpgradeType().intValue() == TreatmentUpgradeType.KAMAR_TIDAK_TERSEDIA){
            			addBenefitCheckRemarks(claimItem, " ;NAIK KELAS - KAMAR TIDAK TERSEDIA");
            		}
            		//Add 20150421 by FVO, handle Treatment Upgrade with Doctor Recomendation
        			if (claimItem.getClaimId().getRoomUpgradeType().intValue() == TreatmentUpgradeType.REKOMENDASI_DOKTER){
        				addBenefitCheckRemarks(claimItem, "NAIK KAMAR REKOMENDASI DOKTER");
        			}
            		
            		if (multiplierCalculation == TreatmentUpgradeType.MULTIPLIER_WITH_BENEFIT_CHECK){
            			coveredValue = coveredValue * multiplier;
            		}
            		else if (multiplierCalculation == TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK){
            			coveredValue = multiplier * charge;
            		}
            		
					claimItem.setClaimItemCoveredValue(coveredValue);
					claimItem.setClaimItemApprovedValue(coveredValue);
					claimItem.setExcessValue(claimItem.getClaimItemValue() - coveredValue);
					
					if (coveredValue < claimItem.getClaimItemValue()) {
					    addBenefitCheckRemarks(claimItem, " ;limit benefit = " + df.format(coveredValue));
					
					}
					if (outerLimitRemaining > 0) {
					    outerLimitRemaining = outerLimitRemaining - coveredValue;
					}
            	}
            	else {
	               	 claimItem.setClaimItemCoveredValue(coveredValue);
	                 claimItem.setClaimItemApprovedValue(coveredValue);
	                 claimItem.setExcessValue(initialCharge - coveredValue);
	
	                 if (coveredValue < claimItem.getClaimItemValue()) {
	                     addBenefitCheckRemarks(claimItem, " ;limit benefit = " + df.format(coveredValue));
	
	                 }
	                 if (outerLimitRemaining > 0) {
	                     outerLimitRemaining = outerLimitRemaining - coveredValue;
	                 }
            		
            	}
            }
            else {
            	 claimItem.setClaimItemCoveredValue(coveredValue);
                 claimItem.setClaimItemApprovedValue(coveredValue);
                 claimItem.setExcessValue(initialCharge - coveredValue);

                 if (coveredValue < initialCharge) {
                     addBenefitCheckRemarks(claimItem, "limit benefit = " + df.format(coveredValue));

                 }
                 if (outerLimitRemaining > 0) {
                     outerLimitRemaining = outerLimitRemaining - coveredValue;
                 }
            }
        }
        
        if (memberBenefit.getMemberLimitLayerId() != null && memberBenefit.getDiagnosisSetId() != null){
        	String remarks = "[SMM] " + claimItem.getBenefitCheckRemarks();
        	claimItem.setBenefitCheckRemarks(remarks);
        }

        claimItemDao.update(claimItem);

        return outerLimitRemaining;
    }

    
    /*
     * Method MaxDailyAndOccuranceCalculation berfungsi untuk melakukan
     * perhitungan benefit terhadap item yang mempunyai batasan pada setiap
     * perhitungan claim. Sebagai contoh adalah perhitungan terhadap benefit
     * kamar perawatan.
     *
     * Kamar perawatan PASTI mempunyai limit daily contoh: 1.000.000 / hari,
     * 500.000 / hari dan lain sebagainya. Nah fungsi dari method ini adalah
     * melakukan kalkulasi batasan maksimum yang dapat diberikan kepada
     * tertanggung.
     *
     * Langkah 1 : Identifikasi Jumlah Service yang berikan (berapa hari)
     * Langkah 2 : Identifikasi apakah terdapat clausul Treatment Location
     * Langkah 3 : Identifikasi apakah terdapat clausul provider/non provider
     * Langkah 4 : Identifikasi Jumlah maksimum service yang diperbolehkan dalam
     * 1 case Langkah 5 : Identifikasi Jumlah charge yang diberikan (nilai
     * claim) Langkah 6 : Identifikasi nilai rata - rata charge harian (jumlah
     * claim/jumlah service) Langkah 7 : Identifikasi benefit limit (hitung
     * apabila ada maksimum occurance dan annual limit) Langkah 8 : Tetapkan
     * jumlah benefit yang ditanggung : benefit limit * jumlah service yang
     * ditanggung Langkah 9 : Lakukan perhitungan excess charges (untuk
     * provider)
     *
     *
     * Clausul 1 : Buatlah case jika tertanggung melakukan upgrade kamar (harus
     * ada trigger)
     *
     */
    private double maxDailyAndOccuranceCalculationMethod(
            MemberBenefit memberBenefit, ClaimItem claimItem, double outerLimit,double multiplier,int multiplierCalculation) throws Exception {

        double outerLimitRemaining = outerLimit;

        double maxBenefitPerOccuranceUsage = 0.0;
        double maxBenefitPerCase = 0.0;

        boolean isFamilyPlan = false;
        
        if (memberBenefit.getMemberProductId().getProductId() != null){
        	if (memberBenefit.getMemberProductId().getProductId().getProductType().getProductTypeId().intValue() == ProductType.FAMILY ||
        			memberBenefit.getMemberProductId().getProductId().getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY ){
        		isFamilyPlan = true;
        	}
        		
        }
        
        if (isFamilyPlan){
        	if (memberBenefit.getFamilyBenefitId() != null){
        		memberBenefit = memberBenefitService.get(memberBenefit.getFamilyBenefitId().getMemberBenefitId());
        	}
        }
        
        double maxOccurancePerCase = memberBenefit.getMaxOccurancePerCase() == null ? 0.0 : memberBenefit.getMaxOccurancePerCase();
        double maxOccurance = memberBenefit.getMaxBenefitOccurance() == null ? 0.0 : memberBenefit.getMaxBenefitOccurance();
        
        boolean isUsingCurrencyConversion = false;
        double currencyMultiplier = claimItem.getExchangeRate() == null ? 1.0 : claimItem.getExchangeRate();

        
        
        String desc = "" ;
        double deductible = 0.0;
        
        boolean isDivertBenefit = false;
        MemberBenefit sharedBenefit = memberBenefit.getSharedBenefitId();
        double sharedBenefitValue = 0.0;
        double sharedBenefitUsage = 0.0;
        boolean isSharedBenefit = false;
        
        
        
        
        claimItem.setBenefitCheckRemarks("");
        claimItem.setClaimItemRemarks("");
      
        if (claimItem.getIsSMMBenefit() != null){
        	if (claimItem.getIsSMMBenefit().intValue() == 1){
        		claimItem.setBenefitCheckRemarks("[SMM]");
        		//check apakah ada clausul co-share di SMM?
        	}
        }
        
        if (claimItem.getIsMultiLayerBenefit() != null){
        	if (claimItem.getIsMultiLayerBenefit().intValue() == 1){
        		claimItem.setBenefitCheckRemarks("[MultiLayer]");
        		// check apakah ada clausul co-share di multi layer?
        	}
        }
        
        if (memberBenefit.getMaxBenefitPerCase() != null) {
            maxBenefitPerCase = memberBenefit.getMaxBenefitPerCase().doubleValue();
        }
                
           
        if (memberBenefit.getMemberProductId().getProductId().getCaseCategory().getCaseCategoryId().intValue() 
        		!= memberBenefit.getCaseCategoryId().getCaseCategoryId().intValue()){
        	isDivertBenefit = true;
        }
        
        if (claimItem.getClaimId().getSmmLayerId() != null){
        	if (claimItem.getClaimId().getSmmLayerId().getDeductible() != null){
        		if (claimItem.getClaimId().getClaimChargeValue() < claimItem.getClaimId().getSmmLayerId().getDeductible()){
        			
        			
        			claimItem.setExcessValue(claimItem.getClaimItemValue());
                	claimItem.setClaimItemApprovedValue(0.0);
                	claimItem.setClaimItemCoveredValue(0.0);
                	claimItem.setRefund(0.0);
                	
                	addBenefitCheckRemarks(claimItem, " ;Deductible = " + claimItem.getClaimId().getSmmLayerId().getDeductible());
                	
                	return outerLimitRemaining;
        		}
        	}
        }
        
        if (claimItem.getClaimId().getMultiLayerId() != null){
        	if (claimItem.getClaimId().getMultiLayerId().getDeductible() != null){
        		if (claimItem.getClaimId().getClaimChargeValue() < claimItem.getClaimId().getMultiLayerId().getDeductible()){
        			
        			
        			claimItem.setExcessValue(claimItem.getClaimItemValue());
                	claimItem.setClaimItemApprovedValue(0.0);
                	claimItem.setClaimItemCoveredValue(0.0);
                	claimItem.setRefund(0.0);
                	
                	addBenefitCheckRemarks(claimItem, " ;Deductible = " + claimItem.getClaimId().getMultiLayerId().getDeductible());
                	
                	return outerLimitRemaining;
        		}
        	}
        }
        
        if (maxOccurance >= 0){
        	double benefitOccured = getBenefitTotalOccured(claimItem.getClaimId().getMemberId().getMemberId(),
            		claimItem.getClaimId().getMemberId().getEffectiveDate(), claimItem.getClaimId().getMemberId().getExpireDate(),
            		claimItem.getItemId().getItemCategoryId().getItemCategoryId(), claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId());
            
        	
        	if (benefitOccured >= maxOccurance){
        		addBenefitCheckRemarks(claimItem, " ;Annual Occurance Max Usage Reached");
        		
        		claimItem.setExcessValue(claimItem.getClaimItemValue());
            	claimItem.setClaimItemApprovedValue(0.0);
            	claimItem.setClaimItemCoveredValue(0.0);
        		
        		return outerLimitRemaining;
        	}
        }
        
        double occurancePerCase = getBenefitTotalOccured(memberBenefit.getMemberId().getMemberId(),
        		claimItem.getClaimId().getAdmissionDate(), claimItem.getClaimId().getDischargeDate(), 
        		claimItem.getItemId().getItemCategoryId().getItemCategoryId(), claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),
        		claimItem.getClaimId().getDiagnosisId().getDiagnosisId());
        
        double benefitUsagePerCase = getBenefitUsagePerCase(memberBenefit.getMemberId().getMemberId(),
        		claimItem.getClaimId().getAdmissionDate(), claimItem.getClaimId().getDischargeDate(), 
        		claimItem.getItemId().getItemCategoryId().getItemCategoryId(), claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId()
        		,claimItem.getClaimId().getDiagnosisId().getDiagnosisId());
        
        
        /**
         * 
         */
        
        if (claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
        	 occurancePerCase = getBenefitTotalOccured(memberBenefit.getMemberId().getMemberId(),
             		claimItem.getClaimId().getAdmissionDate(), claimItem.getClaimId().getDischargeDate(), 
             		claimItem.getItemId().getItemCategoryId().getItemCategoryId(), claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId());
             
             benefitUsagePerCase = getBenefitUsagePerCase(memberBenefit.getMemberId().getMemberId(),
             		claimItem.getClaimId().getAdmissionDate(), claimItem.getClaimId().getDischargeDate(), 
             		claimItem.getItemId().getItemCategoryId().getItemCategoryId(), claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId());
             
        }
        
        if (maxOccurancePerCase > 0 ){
	        if (occurancePerCase >= maxOccurancePerCase ){
	        	addBenefitCheckRemarks(claimItem, " ;Max Occurance Per Case Usage Reached");
	    		
	    		claimItem.setExcessValue(claimItem.getClaimItemValue());
	        	claimItem.setClaimItemApprovedValue(0.0);
	        	claimItem.setClaimItemCoveredValue(0.0);
	        	claimItem.setRefund(0.0);
	    		
	    		return outerLimitRemaining;
	        }
	        
        }
        
        if (sharedBenefit != null){
        	isSharedBenefit = true;
        	sharedBenefitValue = sharedBenefit.getBenefitLimit().doubleValue();
        	
        	try {
        		Collection<ClaimItem> allClaimItem = getClaimItem(claimItem.getClaimId().getClaimId());
        		
        		for (Iterator iterator = allClaimItem.iterator(); iterator
						.hasNext();) {
        			
					ClaimItem claimItem2 = (ClaimItem) iterator.next();
					
					if (claimItem2.getMemberBenefitId() != null){
						if (claimItem2.getMemberBenefitId().getMemberBenefitId().intValue() == sharedBenefit.getMemberBenefitId().intValue()){
							
							if (claimItem2.getClaimItemCoveredValue() != null){
								sharedBenefitUsage += claimItem2.getClaimItemCoveredValue().doubleValue();
							}
						}
					}					
				}
        	}
        	catch (Exception e){
        		e.printStackTrace();
        	}        	
        }
        
        
        double costSharingAmount = 0.0;
        
        DecimalFormat df = new DecimalFormat("#.00");        
        
        if (claimItem.getClaimId().getIsExGratia() != null && claimItem.getClaimId().getIsExGratia().intValue() == 1){
        	addBenefitCheckRemarks(claimItem, " ;EX-GRATIA");
        }
        if ( claimItem.getBenefitCheckRemarks() != null ){
        	desc = claimItem.getBenefitCheckRemarks();
        }
        if (currencyMultiplier > 1.0 || currencyMultiplier < 1.0){
        	isUsingCurrencyConversion = true;
        	addBenefitCheckRemarks(claimItem, " ;Exchange Rate = " + currencyMultiplier + " DATE = " + claimItem.getExchangeRateDate());
        }
        if (memberBenefit.getDeductibleLimit() != null ){
        	deductible = memberBenefit.getDeductibleLimit().doubleValue();
        }
        
        if (deductible > 0.0 && claimItem.getClaimItemValue().doubleValue() < deductible){
        	
        	claimItem.setExcessValue(claimItem.getClaimItemValue());
        	claimItem.setClaimItemApprovedValue(0.0);
        	claimItem.setClaimItemCoveredValue(0.0);
        	
        	addBenefitCheckRemarks(claimItem, " ;Deductible = " + deductible);
        	return outerLimitRemaining;
        }
        if (claimItem.getItemId().getItemCategoryId().getIsSurgeryItem() != null && claimItem.getItemId().getItemCategoryId().getIsSurgeryItem().intValue() == 1){
	        if (claimItem.getClaimId().getIsSurgery() != null && claimItem.getClaimId().getIsSurgery().intValue() == 1){
	        	if (claimItem.getClaimId().getSurgeryLevel() != null){
	        		int level = Integer.parseInt(claimItem.getClaimId().getSurgeryLevel());
	        		if (level == 0){
	        			addBenefitCheckRemarks(claimItem, "SURGERY KOMPLEKS");
	        		}
	        		if (level == 1){
	        			addBenefitCheckRemarks(claimItem, "SURGERY BESAR");
	        		}
	        		if (level == 2){
	        			addBenefitCheckRemarks(claimItem, "SURGERY SEDANG");
	        		}
	        		if (level == 3){
	        			addBenefitCheckRemarks(claimItem, "SURGERY KECIL");
	        		}
	        		if (level == 4){
	        			addBenefitCheckRemarks(claimItem, "SURGERY MINOR");
	        		}
	        	}
	        }
        }
        double paymentPercentage = 1;

        Product product = memberBenefit.getProductId();
        
        /**
         * check additional co-share percentage dari multi-layer maupun SMM
         */

        if (product != null) {
            if (claimItem.getClaimId().getClaimTypeId().getClaimTypeId().intValue() == ClaimType.CASHLESS) {
                if (memberBenefit.getCashlessPercentage() != null) {
                    paymentPercentage = memberBenefit.getCashlessPercentage().doubleValue() / 100;
                    
                    if (memberBenefit.getCashlessPercentage().doubleValue() < 100){
                    	addBenefitCheckRemarks(claimItem, " ;Cashless Percentage " + memberBenefit.getCashlessPercentage().doubleValue() + " %");
                    }
                }
            }

            if (claimItem.getClaimId().getClaimTypeId().getClaimTypeId().intValue() == ClaimType.REIMBURESEMENT) {
                if (memberBenefit.getReimbursementPercentage() != null) {
                    paymentPercentage = memberBenefit.getReimbursementPercentage().doubleValue() / 100;
                    
                    if (memberBenefit.getReimbursementPercentage().doubleValue() < 100){
                    	addBenefitCheckRemarks(claimItem, " ;Reimbursement Percentage " + memberBenefit.getReimbursementPercentage().doubleValue() + " %");
                    }
                }
            }
            if (memberBenefit.getCostSharingPercentage() != null){
            	paymentPercentage = paymentPercentage * (memberBenefit.getCostSharingPercentage().doubleValue() / 100);
            	
            	if (memberBenefit.getCostSharingPercentage().doubleValue() < 100){
            		addBenefitCheckRemarks(claimItem, " ;Cost Sharing " + memberBenefit.getCostSharingPercentage().doubleValue() + " %");	
            	}
            }
            if (memberBenefit.getCostSharingAmount() != null){
            	costSharingAmount = memberBenefit.getCostSharingAmount();
            	BigDecimal bc = new BigDecimal(costSharingAmount);
            	String costShareAmt = bc.toPlainString() ;
            	addBenefitCheckRemarks(claimItem, " ;Cost Sharing = " + costShareAmt);
            }
            if (claimItem.getClaimId().getSmmLayerId() != null ){
            	if (claimItem.getClaimId().getSmmLayerId().getCoSharePercentage() != null){
            		paymentPercentage = paymentPercentage * claimItem.getClaimId().getSmmLayerId().getCoSharePercentage()/100.0;
            		addBenefitCheckRemarks(claimItem, " ;Cost Sharing SMM " + claimItem.getClaimId().getSmmLayerId().getCoSharePercentage().doubleValue() + " %");
            	}
            }
            if (claimItem.getClaimId().getMultiLayerId() != null ){
            	if (claimItem.getClaimId().getMultiLayerId().getCoSharePercentage() != null){
            		paymentPercentage = paymentPercentage * claimItem.getClaimId().getMultiLayerId().getCoSharePercentage()/100.0;
            		addBenefitCheckRemarks(claimItem, " ;Cost Sharing MultiLayer " + claimItem.getClaimId().getMultiLayerId().getCoSharePercentage().doubleValue() + " %");
            	}
            }
        }
        

        if (memberBenefit.getBenefitLimit() != null) {
            maxBenefitPerOccuranceUsage = memberBenefit.getBenefitLimit().doubleValue();
            
            if (maxBenefitPerOccuranceUsage == 0.0){
            	if (maxBenefitPerOccuranceUsage == 0.0){
                	if (memberBenefit.getReimbursementBenefitLimit() != null && memberBenefit.getReimbursementBenefitLimit().doubleValue() > 0.0){
                		if (claimItem.getClaimId().getClaimTypeId().getClaimTypeId().intValue() == ClaimType.REIMBURESEMENT){
                			maxBenefitPerOccuranceUsage = memberBenefit.getReimbursementBenefitLimit();
                		}
                	}
                }
            }
        }

        double numberOfService = 0.0;

        if (claimItem.getClaimItemAmount() != null) {
            numberOfService = claimItem.getClaimItemAmount().doubleValue();

            if (maxOccurancePerCase > 0 && numberOfService > maxOccurancePerCase) {
                numberOfService = maxOccurancePerCase;
                
                if (desc == null) {
                    desc = "" ;
                } else {
                    desc += "\n;maksimum layanan " + maxOccurancePerCase
                            + " kali per kasus" ;
                }

                addBenefitCheckRemarks(claimItem, desc);
            }
        }
        
        

        double charge = paymentPercentage * (claimItem.getClaimItemValue()-costSharingAmount);
        double initialCharge = claimItem.getClaimItemValue();
        
        
        
        if (claimItem.getProductCurrencyChargeValue() != null && !claimItem.getProductCurrencyChargeValue().equals(initialCharge)){
        	charge = paymentPercentage * claimItem.getProductCurrencyChargeValue();
        	initialCharge = claimItem.getProductCurrencyChargeValue()-costSharingAmount;
        	
        	if (costSharingAmount > 0.0){
            	charge = paymentPercentage * (claimItem.getProductCurrencyChargeValue()-costSharingAmount);

        	}
        }
        
        /**
         * perlu informasi, apabila terdapat parameter cost sharing amount dan cost sharing percentage digunakan
         * Apakah (initial charge - cost sharing) * payment percentage
         * 
         * atau
         * 
         * initial charge * payment percentage - cost sharing
         * 
         */
        
        
        
        double perItemCharge = charge / claimItem.getClaimItemAmount().doubleValue();
        
        if (maxBenefitPerOccuranceUsage == -1.0){
        	maxBenefitPerOccuranceUsage = perItemCharge;
        }
        
        double maxBenefit = numberOfService * maxBenefitPerOccuranceUsage;
        
        if (maxOccurancePerCase < claimItem.getClaimItemAmount()){
        	if (maxBenefitPerOccuranceUsage == -1.0){
        		if (maxOccurancePerCase > 0){
        			maxBenefit = perItemCharge*(maxOccurancePerCase-occurancePerCase);
        		}
        		else {
        			maxBenefit = perItemCharge*claimItem.getClaimItemAmount();
        		}
        	}
        	else {
        		if (maxOccurancePerCase < 0){
	        		if (perItemCharge > memberBenefit.getBenefitLimit()){
	        			if (memberBenefit.getBenefitLimit() > 0){
	        				maxBenefit = memberBenefit.getBenefitLimit()*claimItem.getClaimItemAmount();
	        			}
	        			else {
	        				maxBenefit = perItemCharge * claimItem.getClaimItemAmount();
	        			}
	        		}
	        		else {
	        			maxBenefit = perItemCharge*claimItem.getClaimItemAmount();
	        		}
        		}
        		else {
        			if (perItemCharge > memberBenefit.getBenefitLimit()){
	        			maxBenefit = memberBenefit.getBenefitLimit()*(maxOccurancePerCase-occurancePerCase);
	        		}
	        		else {
	        			maxBenefit = perItemCharge*(maxOccurancePerCase-occurancePerCase);
	        		}
        		}
        	}
        }
        
        System.out.println("PER ITEM CHARGE = " + perItemCharge + " MAX BENEFIT : " + maxBenefit + " ITEM = " + claimItem.getItemId().getItemName());

        double coveredValue = 0.0;

        if ( outerLimitRemaining >= 0){
        	/** this is check unlimited atau masih ada sisa benefit */
        	if (outerLimitRemaining >= maxBenefit){
        		if (charge >= maxBenefit) {
                    coveredValue = maxBenefit;
                } else {
                    coveredValue = charge;
                }        		
        	}
        	else {
        		coveredValue = outerLimitRemaining;
        	}
        }
        else {
        	if (charge >= maxBenefit) {
                coveredValue = maxBenefit;
            } else {
                coveredValue = charge;
            }
        }
        

        if (memberBenefit.getIsAsCharge() != null  && memberBenefit.getIsAsCharge().booleanValue()) {

            if ( outerLimitRemaining >= 0){
            	if (outerLimitRemaining >= coveredValue && coveredValue > 0.0){    
            		if (outerLimitRemaining >= maxBenefit){
            			coveredValue = maxBenefit; // dibutuhkan untuk multi currency conversion
            		}
            		
            	}
            	else {
            		coveredValue = outerLimitRemaining * paymentPercentage;
            	}
            }
            else {            	
            	coveredValue = maxBenefit; // multi currency conversion
            }
            
        }
        
        System.out.println("[post] PER ITEM CHARGE = " + perItemCharge + " MAX BENEFIT COV: " + coveredValue + " ITEM = " + claimItem.getItemId().getItemName() + " MULTIPLIER = " + multiplier);
        
        if (claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.INPATIENT){
        	if (claimItem.getClaimId().getRoomUpgradeType() != null){
        		if (claimItem.getClaimId().getRoomUpgradeType().intValue() == TreatmentUpgradeType.NAIK_ATAS_PERMINTAAN_SENDIRI){        			
        			addBenefitCheckRemarks(claimItem, "NAIK KELAS APS");
        		}
        		if (claimItem.getClaimId().getRoomUpgradeType().intValue() == TreatmentUpgradeType.KAMAR_PENUH){
        			addBenefitCheckRemarks(claimItem, "NAIK KELAS KAMAR PENUH");
        		}
        		if (claimItem.getClaimId().getRoomUpgradeType().intValue() == TreatmentUpgradeType.KAMAR_TIDAK_TERSEDIA){
        			addBenefitCheckRemarks(claimItem, "NAIK KELAS KAMAR TIDAK TERSEDIA");
        		}
        		//Add 20150421 by FVO, handle Treatment Upgrade with Doctor Recomendation
    			if (claimItem.getClaimId().getRoomUpgradeType().intValue() == TreatmentUpgradeType.REKOMENDASI_DOKTER){
    				addBenefitCheckRemarks(claimItem, "NAIK KAMAR REKOMENDASI DOKTER");
    			}
        	}
        	if (multiplierCalculation == TreatmentUpgradeType.MULTIPLIER_WITH_BENEFIT_CHECK){
        		coveredValue = coveredValue * multiplier;
        	}
        	else if (multiplierCalculation == TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK) {
        		coveredValue = charge * multiplier;
        	}
        }

        double excessValue = 0.0;
        claimItem.setClaimItemRemarks(desc);
        
        if (isSharedBenefit){
        	if (sharedBenefitUsage+coveredValue > sharedBenefitValue){
        		coveredValue = sharedBenefitValue - sharedBenefitUsage;
        		addBenefitCheckRemarks(claimItem, " ;sisa limit shared benefit = " + coveredValue);
        	}
        }
        
        if (maxBenefitPerCase > 0 && coveredValue > 0){        	
        		
        	double coveredAfterMaxValue = (maxBenefitPerCase - benefitUsagePerCase) < 0 ? 0 : (maxBenefitPerCase - benefitUsagePerCase);
        	
        	if (coveredAfterMaxValue < coveredValue){
        		addBenefitCheckRemarks(claimItem, " ;max benefit per case = " + maxBenefitPerCase);
        		
        		coveredValue = coveredAfterMaxValue;
        	}
        	        	
        }

        if (coveredValue <= initialCharge) {

            if (memberBenefit.getIsAsCharge() != null && !memberBenefit.getIsAsCharge().booleanValue()) {
            	if (coveredValue == 0){
        			addBenefitCheckRemarks(claimItem, " ;sisa limit benefit habis");
            	}
            	else {
            		if (!isUsingCurrencyConversion){
            			addBenefitCheckRemarks(claimItem, " ;limit benefit = " + Converter.getMoney(coveredValue));
            		}
            		else {
            			addBenefitCheckRemarks(claimItem, " ;limit benefit = " + Converter.getMoney(coveredValue) + " ("+ df.format(coveredValue/currencyMultiplier)+")");
            		}
            	}
            } 
            if (memberBenefit.getIsAsCharge() != null && memberBenefit.getIsAsCharge().booleanValue()) {
            	if (coveredValue > outerLimitRemaining && outerLimitRemaining > 0){
	            	if (!isUsingCurrencyConversion){
	            		addBenefitCheckRemarks(claimItem, " ;limit benefit = " + Converter.getMoney(outerLimitRemaining));
	            	}
	            	else {
	            		addBenefitCheckRemarks(claimItem, " ;limit benefit = " + Converter.getMoney(outerLimitRemaining) + " ("+ df.format(outerLimitRemaining/currencyMultiplier)+")");
	            	}
            	}
            	else {
            		if (coveredValue < charge){
            			if (!isUsingCurrencyConversion){
    	            		addBenefitCheckRemarks(claimItem, " ;limit benefit = " + Converter.getMoney(coveredValue));
    	            	}
    	            	else {
    	            		addBenefitCheckRemarks(claimItem, " ;limit benefit = " + Converter.getMoney(coveredValue) + " ("+ df.format(coveredValue/currencyMultiplier)+")");
    	            	}
            		}
            	}
                
            }
            else if (memberBenefit.getIsAsCharge() == null) {
                
            	if ( outerLimitRemaining >= 0){
            		if (outerLimitRemaining == 0){
            			addBenefitCheckRemarks(claimItem, " ;sisa limit benefit habis");
            		}
            		else {
            			if (coveredValue > outerLimitRemaining){
            				if (!isUsingCurrencyConversion){
            					addBenefitCheckRemarks(claimItem, " ;limit benefit = " + Converter.getMoney(outerLimitRemaining) );
            				}
            				else {
            					addBenefitCheckRemarks(claimItem, " ;limit benefit = " + Converter.getMoney(outerLimitRemaining)  + " ("+ df.format(outerLimitRemaining/currencyMultiplier)+")" );
            				}
            			}
            			else {
            				if (coveredValue < claimItem.getClaimItemValue().doubleValue()){
            					if (!isUsingCurrencyConversion){
            						addBenefitCheckRemarks(claimItem, " ;limit benefit = " + Converter.getMoney(coveredValue));
            					}
            					else {
            						addBenefitCheckRemarks(claimItem, " ;limit benefit = " + Converter.getMoney(coveredValue)  + " ("+ df.format(coveredValue/currencyMultiplier)+")" );
            					}
            				}
            			}
            		}
            	}
            	else {
	            	if (coveredValue < initialCharge){	            		
	            		if (!isUsingCurrencyConversion){
    						addBenefitCheckRemarks(claimItem, " ;limit benefit = " + Converter.getMoney(coveredValue));
    					}
    					else {
    						addBenefitCheckRemarks(claimItem, " ;limit benefit = " + Converter.getMoney(coveredValue)  + " ("+ df.format(coveredValue/currencyMultiplier)+")" );
    					}
	            	}
            	}            	
            }
           
            
        } else {        	
            coveredValue = charge;
            excessValue = 0.0;            
        }  
        excessValue = initialCharge - coveredValue;

        
        
        if (outerLimit >= 0){
        	outerLimitRemaining = outerLimit - coveredValue;
        }

        if (memberBenefit.getMemberLimitLayerId() != null && memberBenefit.getDiagnosisSetId() != null){
        	String remarks = "[SMM] " + claimItem.getBenefitCheckRemarks();
        	claimItem.setBenefitCheckRemarks(remarks);
        }
        claimItem.setExcessValue(excessValue);
        claimItem.setProductCurrencyExcessValue(excessValue);
        claimItem.setClaimItemCoveredValue(coveredValue);
        
        if (isUsingCurrencyConversion){
        	claimItem.setClaimItemCoveredValue(coveredValue/currencyMultiplier);
        	claimItem.setExcessValue(excessValue/currencyMultiplier);
        }
        claimItemDao.update(claimItem);

        return outerLimitRemaining;
    }

    
    /*
     * apakah ini paling bagus untuk as charge? check global nya juga Pre -
     * Check : Apakah tidak violate Clausul? Langkah 1: check is as charge
     * Langkah 2: check total benefit limit left Langkah 3:
     *
     */
    private double accumulativeCalculateMethod(MemberBenefit memberBenefit,
            ClaimItem claimItem, double outerLimit,double multiplier, int multiplierCalculation) throws Exception {

        double outerLimitRemaining = -1;
        
        boolean isFamilyPlan = false;
        
        if (memberBenefit.getMemberProductId().getProductId() != null){
        	if (memberBenefit.getMemberProductId().getProductId().getProductType().getProductTypeId().intValue() == ProductType.FAMILY ||
        			memberBenefit.getMemberProductId().getProductId().getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY ){
        		isFamilyPlan = true;
        	}
        		
        }
        
        if (isFamilyPlan){
        	if (memberBenefit.getFamilyBenefitId() != null){
        		memberBenefit = memberBenefitService.get(memberBenefit.getFamilyBenefitId().getMemberBenefitId());
        	}
        }

        if (memberBenefit.getIsAsCharge() != null && !memberBenefit.getIsAsCharge().booleanValue()){
	        if (outerLimit > outerLimitRemaining) {
	            outerLimitRemaining = outerLimit;
	        }
        }
        
        double deductible = memberBenefit.getDeductibleLimit() == null ? 0.0 : memberBenefit.getDeductibleLimit();
        Date effectiveDate = memberBenefit.getEffectiveDate() == null ? claimItem.getClaimId().getMemberId().getEffectiveDate() : memberBenefit.getEffectiveDate();
        Date expireDate = memberBenefit.getExpireDate() == null ? claimItem.getClaimId().getMemberId().getExpireDate() : memberBenefit.getExpireDate();
        
        
        if (claimItem.getClaimId().getSmmLayerId() != null){
        	if (claimItem.getClaimId().getSmmLayerId().getDeductible() != null){
        		if (claimItem.getClaimId().getClaimChargeValue() < claimItem.getClaimId().getSmmLayerId().getDeductible()){
        			
        			
        			claimItem.setExcessValue(claimItem.getClaimItemValue());
                	claimItem.setClaimItemApprovedValue(0.0);
                	claimItem.setClaimItemCoveredValue(0.0);
                	claimItem.setRefund(0.0);
                	
                	addBenefitCheckRemarks(claimItem, " ;Deductible = " + claimItem.getClaimId().getSmmLayerId().getDeductible());
                	
                	return outerLimitRemaining;
        		}
        	}
        }
        
        if (claimItem.getClaimId().getMultiLayerId() != null){
        	if (claimItem.getClaimId().getMultiLayerId().getDeductible() != null){
        		if (claimItem.getClaimId().getClaimChargeValue() < claimItem.getClaimId().getMultiLayerId().getDeductible()){
        			
        			
        			claimItem.setExcessValue(claimItem.getClaimItemValue());
                	claimItem.setClaimItemApprovedValue(0.0);
                	claimItem.setClaimItemCoveredValue(0.0);
                	claimItem.setRefund(0.0);
                	
                	addBenefitCheckRemarks(claimItem, " ;Deductible = " + claimItem.getClaimId().getMultiLayerId().getDeductible());
                	
                	return outerLimitRemaining;
        		}
        	}
        }
        double costSharingAmount = 0.0;
        double sharedBenefitValue = 0.0;
        double sharedBenefitUsage = 0.0;
        boolean isSharedBenefit = false;
        boolean isDivertBenefit = false;
        double maxBenefitPerCase = memberBenefit.getMaxBenefitPerCase() == null ? 0.0 : memberBenefit.getMaxBenefitPerCase();
        
        
        MemberBenefit sharedBenefit = memberBenefit.getSharedBenefitId();
        
        double maxOccurance = memberBenefit.getMaxBenefitOccurance() == null ? 0.0 : memberBenefit.getMaxBenefitOccurance();
        
        double maxOccurancePerCase = memberBenefit.getMaxOccurancePerCase() == null ? 0.0 : memberBenefit.getMaxOccurancePerCase();
        //double benefitOccured = memberBenefit.getBenefitOccuranceUsage() == null ? 0.0 : memberBenefit.getBenefitOccuranceUsage().doubleValue();
        /**
        double benefitOccured = getBenefitTotalOccured(claimItem.getClaimId().getMemberId().getMemberId(),
        		claimItem.getClaimId().getMemberId().getEffectiveDate(), claimItem.getClaimId().getMemberId().getExpireDate(),
        		claimItem.getItemId().getItemCategoryId().getItemCategoryId(), claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId());
        */
        
        double benefitOccured = getBenefitTotalOccured(claimItem.getClaimId().getMemberId().getMemberId(),
        		effectiveDate, expireDate,
        		claimItem.getItemId().getItemCategoryId().getItemCategoryId(), claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId());
        

        double benefitOccuredPerCase = getBenefitTotalOccured(claimItem.getClaimId().getMemberId().getMemberId(),
        		claimItem.getClaimId().getAdmissionDate(), claimItem.getClaimId().getDischargeDate(),
        		claimItem.getItemId().getItemCategoryId().getItemCategoryId(), claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId());
        
        claimItem.setBenefitCheckRemarks("");
        claimItem.setClaimItemRemarks("");
        
        if (claimItem.getIsSMMBenefit() != null){
        	if (claimItem.getIsSMMBenefit().intValue() == 1){
        		claimItem.setBenefitCheckRemarks("[SMM]");
        	}
        }
        

        if (claimItem.getIsMultiLayerBenefit() != null){
        	if (claimItem.getIsMultiLayerBenefit().intValue() == 1){
        		claimItem.setBenefitCheckRemarks("[MultiLayer]");
        	}
        }
        
        if (maxOccurance >= 0){
        	System.out.println("CHECK OCCURANCE ANNUAL = " + maxOccurance  + " BENEFIT USAGE = " + benefitOccured);
        	if (benefitOccured == maxOccurance){
        		addBenefitCheckRemarks(claimItem, " ;Annual Occurance Max Usage Reached");
        		

            	
        		claimItem.setExcessValue(claimItem.getClaimItemValue());
            	claimItem.setClaimItemApprovedValue(0.0);
            	claimItem.setClaimItemCoveredValue(0.0);
            	claimItem.setRefund(0.0);
        		
        		return outerLimitRemaining;
        	}
        }
        
        if (maxOccurancePerCase >= 0){
        	System.out.println("CHECK OCCURANCE PER CASE = " + maxOccurancePerCase  + " BENEFIT USAGE = " + benefitOccuredPerCase);
        	if (benefitOccuredPerCase == maxOccurancePerCase){
        		addBenefitCheckRemarks(claimItem, " ;Max Occurance Per Case Reached");
        		

            	
        		claimItem.setExcessValue(claimItem.getClaimItemValue());
            	claimItem.setClaimItemApprovedValue(0.0);
            	claimItem.setClaimItemCoveredValue(0.0);
            	claimItem.setRefund(0.0);
        		
        		return outerLimitRemaining;
        	}
        }
        
                
        if (memberBenefit.getMemberProductId().getProductId().getCaseCategory().getCaseCategoryId().intValue() 
        		!= memberBenefit.getCaseCategoryId().getCaseCategoryId().intValue()){
        	isDivertBenefit = true;
        }
        
        if (sharedBenefit != null){
        	isSharedBenefit = true;
        	sharedBenefitValue = sharedBenefit.getBenefitLimit().doubleValue();
        	
        	try {
        		Collection<ClaimItem> allClaimItem = getClaimItem(claimItem.getClaimId().getClaimId());
        		
        		for (Iterator iterator = allClaimItem.iterator(); iterator.hasNext();) {        			
					ClaimItem claimItem2 = (ClaimItem) iterator.next();					
					if (claimItem2.getMemberBenefitId() != null){
						if (claimItem2.getMemberBenefitId().getMemberBenefitId().intValue() == sharedBenefit.getMemberBenefitId().intValue()){							
							if (claimItem2.getClaimItemCoveredValue() != null){
								sharedBenefitUsage += claimItem2.getClaimItemCoveredValue().doubleValue();
							}
						}
					}					
				}
        	}
        	catch (Exception e){
        		e.printStackTrace();
        	}
        	
        }
        
        if (claimItem.getClaimId().getIsExGratia() != null && claimItem.getClaimId().getIsExGratia().intValue() == 1){
        	addBenefitCheckRemarks(claimItem, "EX-GRATIA");
        }
        if (claimItem.getClaimId().getIsSurgery() != null && claimItem.getClaimId().getIsSurgery().intValue() == 1){
        	if (claimItem.getClaimId().getSurgeryLevel() != null){
        		int level = Integer.parseInt(claimItem.getClaimId().getSurgeryLevel());
        		if (level == 0){
        			addBenefitCheckRemarks(claimItem, "SURGERY KOMPLEKS");
        		}
        		if (level == 1){
        			addBenefitCheckRemarks(claimItem, "SURGERY BESAR");
        		}
        		if (level == 2){
        			addBenefitCheckRemarks(claimItem, "SURGERY SEDANG");
        		}
        		if (level == 3){
        			addBenefitCheckRemarks(claimItem, "SURGERY KECIL");
        		}
        		if (level == 4){
        			addBenefitCheckRemarks(claimItem, "SURGERY MINOR");
        		}
        	}
        }        
        
        

        double benefitLimit = 0.0;
        
        if (sharedBenefit != null){
        	benefitLimit = sharedBenefit.getBenefitLimit() == null ? 0.0 : sharedBenefit.getBenefitLimit().doubleValue();
        	
        	if (benefitLimit == 0.0){
            	if (sharedBenefit.getReimbursementBenefitLimit() != null && sharedBenefit.getReimbursementBenefitLimit().doubleValue() > 0.0){
            		if (claimItem.getClaimId().getClaimTypeId().getClaimTypeId().intValue() == ClaimType.REIMBURESEMENT){
            			benefitLimit = sharedBenefit.getReimbursementBenefitLimit();
            		}
            	}
            }
        }
        else {
        	benefitLimit = memberBenefit.getBenefitLimit() == null ? 0.0 : memberBenefit.getBenefitLimit().doubleValue();
        	
        	if (benefitLimit == 0.0){
            	if (memberBenefit.getReimbursementBenefitLimit() != null && memberBenefit.getReimbursementBenefitLimit().doubleValue() > 0.0){
            		if (claimItem.getClaimId().getClaimTypeId().getClaimTypeId().intValue() == ClaimType.REIMBURESEMENT){
            			benefitLimit = memberBenefit.getReimbursementBenefitLimit();
            		}
            	}
            }
        }
        
        double currencyMultiplier = claimItem.getExchangeRate() == null ? 1.0 : claimItem.getExchangeRate();
        boolean isUsingCurrencyConversion = false;
        
        Date effectiveDateBenefitUsage = memberBenefit.getEffectiveDate() == null ? memberBenefit.getMemberId().getEffectiveDate() : memberBenefit.getEffectiveDate();
        Date expireDateBenefitUsage = memberBenefit.getExpireDate() == null ? memberBenefit.getMemberId().getExpireDate() : memberBenefit.getExpireDate();

        // check apakah utilization menggunakan level main atau layer
        double benefitUsage = 0.0;
        
        if(memberBenefit.getMemberLimitLayerId() == null){
        	benefitUsage = getClaimItemUtilization(memberBenefit.getMemberId().getMemberId(),
        		effectiveDateBenefitUsage, expireDateBenefitUsage,
                claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
                claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId());
        }
        else {
        	benefitUsage = getClaimItemUtilization(memberBenefit.getMemberId().getMemberId(),
            		effectiveDateBenefitUsage, expireDateBenefitUsage,
                    claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
                    claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),memberBenefit.getMemberLimitLayerId().getMemberLimitLayerId());
        }

        
        if (currencyMultiplier > 1.0 || currencyMultiplier < 1.0){
        	isUsingCurrencyConversion = true;
        	addBenefitCheckRemarks(claimItem, " ;Exchange Rate = " + currencyMultiplier + " DATE = " + claimItem.getExchangeRateDate());
        }
        
        if (deductible > 0.0 && claimItem.getClaimItemValue().doubleValue() < deductible){
        	
        	claimItem.setExcessValue(claimItem.getClaimItemValue());
        	claimItem.setClaimItemApprovedValue(0.0);
        	claimItem.setClaimItemCoveredValue(0.0);
        	claimItem.setRefund(0.0);
        	
        	addBenefitCheckRemarks(claimItem, " ;Deductible = " + deductible);
        	return outerLimitRemaining;
        }

        double coveredValue = 0;
        double paymentPercentage = 1;

        Product product = memberBenefit.getProductId();

        if (product != null) {
            if (claimItem.getClaimId().getClaimTypeId().getClaimTypeId().intValue() == ClaimType.CASHLESS) {
                if (memberBenefit.getCashlessPercentage() != null) {
                    paymentPercentage = memberBenefit.getCashlessPercentage().doubleValue() / 100;
                    
                    if (memberBenefit.getCashlessPercentage().doubleValue() < 100){
                    	addBenefitCheckRemarks(claimItem, " ;Cashless Percentage " + memberBenefit.getCashlessPercentage().doubleValue() + " %");
                    }
                }
            }

            if (claimItem.getClaimId().getClaimTypeId().getClaimTypeId().intValue() == ClaimType.REIMBURESEMENT) {
                if (memberBenefit.getReimbursementPercentage() != null) {
                    paymentPercentage = memberBenefit.getReimbursementPercentage().doubleValue() / 100;
                    
                    if (memberBenefit.getReimbursementPercentage().doubleValue() < 100){
                    	addBenefitCheckRemarks(claimItem, " ;Reimbursement Percentage " + memberBenefit.getReimbursementPercentage().doubleValue() + " %");
                    }
                }
            }
            if (memberBenefit.getCostSharingPercentage() != null){
            	paymentPercentage = paymentPercentage * (memberBenefit.getCostSharingPercentage().doubleValue() / 100);
            	
            	if (memberBenefit.getCostSharingPercentage().doubleValue() < 100){
            		addBenefitCheckRemarks(claimItem, " ;Cost Sharing " + memberBenefit.getCostSharingPercentage().doubleValue() + " %");	
            	}
            }
            if (claimItem.getClaimId().getSmmLayerId() != null){
            	if (claimItem.getClaimId().getSmmLayerId().getCoSharePercentage() != null){
            		paymentPercentage = paymentPercentage * claimItem.getClaimId().getSmmLayerId().getCoSharePercentage()/100.0;
            		addBenefitCheckRemarks(claimItem, " ;Cost Sharing SMM " + claimItem.getClaimId().getSmmLayerId().getCoSharePercentage().doubleValue() + " %");
            	}
            }
            if (claimItem.getClaimId().getMultiLayerId() != null){
            	if (claimItem.getClaimId().getMultiLayerId().getCoSharePercentage() != null){
            		paymentPercentage = paymentPercentage * claimItem.getClaimId().getMultiLayerId().getCoSharePercentage()/100.0;
            		addBenefitCheckRemarks(claimItem, " ;Cost Sharing MultiLayer " + claimItem.getClaimId().getMultiLayerId().getCoSharePercentage().doubleValue() + " %");
            	}
            }
            if (memberBenefit.getCostSharingAmount() != null){
            	costSharingAmount = memberBenefit.getCostSharingAmount();
            	BigDecimal bc = new BigDecimal(costSharingAmount);
            	String costShareAmt = bc.toPlainString() ;
            	addBenefitCheckRemarks(claimItem, " ;Cost Sharing = " + costShareAmt);
            }
        }
        
        double charge = paymentPercentage * (claimItem.getClaimItemValue()-costSharingAmount);
        double initialCharge = claimItem.getClaimItemValue() - costSharingAmount;
        
        
        
        if (claimItem.getProductCurrencyChargeValue() != null && !claimItem.getProductCurrencyChargeValue().equals(initialCharge)){
        	charge = paymentPercentage * claimItem.getProductCurrencyChargeValue();
        	initialCharge = claimItem.getProductCurrencyChargeValue()-costSharingAmount;
        	
        	if (costSharingAmount > 0.0){
            	charge = paymentPercentage * (claimItem.getProductCurrencyChargeValue()-costSharingAmount);

        	}
        }

        
        if (benefitLimit == -1.0) {

            if (charge <= outerLimit) {
                coveredValue = charge;
            } else {
            	if (outerLimit >= 0){
            		coveredValue = outerLimit;
            	}            	
            	else {
            		coveredValue = charge;
            	}
            }
        
            if (outerLimit > 0){
            	outerLimitRemaining = outerLimit - coveredValue;
            }
            
        } else {
        	
            double availableLimit = benefitLimit - benefitUsage;

            if (availableLimit <= outerLimit) {
                System.out.println("Benefit Lebih kecil dari outerlimit jadi aman");

                if (availableLimit >= charge) {
                    coveredValue = charge;
                }                    
                else {
                	coveredValue = availableLimit;
                }
            } else {
            	if (outerLimit >= 0){
                    if (outerLimit >= charge) {
                        coveredValue = charge;
                    } else {                            
                        coveredValue =  outerLimit;
                    }
            	}
            	else if (outerLimit == -1.0){
            		if (benefitLimit == -1.0){
            			coveredValue = charge;    
            		}
            		else {
            			if (availableLimit >= charge){
            				coveredValue = charge;
            			}
            			else {
            				coveredValue = availableLimit;
            			}
            		}
            	}
            }            
        }
        

        if (coveredValue > 0) {

        	
        	if (claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.INPATIENT){
        		if (claimItem.getClaimId().getRoomUpgradeType() != null){
        			if (claimItem.getClaimId().getRoomUpgradeType().intValue() == TreatmentUpgradeType.NAIK_ATAS_PERMINTAAN_SENDIRI){
        				addBenefitCheckRemarks(claimItem, "NAIK KAMAR APS");
        			}
        			if (claimItem.getClaimId().getRoomUpgradeType().intValue() == TreatmentUpgradeType.KAMAR_PENUH){
        				addBenefitCheckRemarks(claimItem, "NAIK KAMAR PENUH");
        			}
        			if (claimItem.getClaimId().getRoomUpgradeType().intValue() == TreatmentUpgradeType.KAMAR_TIDAK_TERSEDIA){
        				addBenefitCheckRemarks(claimItem, "NAIK KAMAR TIDAK TERSEDIA");
        			}
        			//Add 20150421 by FVO, handle Treatment Upgrade with Doctor Recomendation
        			if (claimItem.getClaimId().getRoomUpgradeType().intValue() == TreatmentUpgradeType.REKOMENDASI_DOKTER){
        				addBenefitCheckRemarks(claimItem, "NAIK KAMAR REKOMENDASI DOKTER");
        			}

        			if (multiplierCalculation == TreatmentUpgradeType.MULTIPLIER_WITH_BENEFIT_CHECK){
        				coveredValue = coveredValue * paymentPercentage * multiplier;
        			}
        			else if (multiplierCalculation == TreatmentUpgradeType.MULTIPLIER_WITHOUT_BENEFIT_CHECK){
        				coveredValue = claimItem.getClaimItemValue().doubleValue() * multiplier;
        			}
        			
    	            claimItem.setClaimItemCoveredValue(coveredValue);
    	
    	            claimItem.setExcessValue(Double.valueOf(0));
    	
    	            if (coveredValue < claimItem.getClaimItemValue() ) {
    	                String desc = claimItem.getClaimItemRemarks();
    	
    	                String descCheck = claimItem.getBenefitCheckRemarks();
    	            	
    	                if (desc != null) {
    	                    desc += " \n;sisa benefit " + claimItem.getItemId().getItemName() + " = " + Converter.getMoney(coveredValue);
    	                    descCheck += " \n;sisa benefit " + claimItem.getItemId().getItemName() + " = " + Converter.getMoney(coveredValue);
    	                } else {
    	                    desc = " ;sisa benefit " + claimItem.getItemId().getItemName() + " = " + Converter.getMoney(coveredValue);
    	                    descCheck = " ;sisa benefit " + claimItem.getItemId().getItemName() + " = " + Converter.getMoney(coveredValue);
    	                }
    	                claimItem.setExcessValue(claimItem.getClaimItemValue() - coveredValue);
    	                claimItem.setClaimItemRemarks(desc);
    	                claimItem.setBenefitCheckRemarks(descCheck);
    	            }
        		}
        		else {        			
    	            claimItem.setClaimItemCoveredValue(coveredValue);    	
    	            claimItem.setExcessValue(Double.valueOf(0));
    	
    	            if (coveredValue < claimItem.getClaimItemValue() ) {
    	                String desc = claimItem.getClaimItemRemarks();
    	
    	                String descCheck = claimItem.getBenefitCheckRemarks();
    	            	
    	                if (desc != null) {
    	                    desc += " ;\nsisa benefit " + claimItem.getItemId().getItemName() + " = " + Converter.getMoney(coveredValue);
    	                    descCheck += " ;\nsisa benefit " + claimItem.getItemId().getItemName() + " = " + Converter.getMoney(coveredValue);
    	                } else {
    	                    desc = " ;sisa benefit " + claimItem.getItemId().getItemName() + " = " + Converter.getMoney(coveredValue);
    	                    descCheck = " ;sisa benefit " + claimItem.getItemId().getItemName() + " = " + Converter.getMoney(coveredValue);
    	                }
    	                claimItem.setExcessValue(claimItem.getClaimItemValue() - coveredValue);
    	                claimItem.setClaimItemRemarks(desc);
    	                claimItem.setBenefitCheckRemarks(descCheck);
    	            }
        		}
        	}
        	else {        	
	            claimItem.setClaimItemCoveredValue(coveredValue);	
	            claimItem.setExcessValue(Double.valueOf(0));
	            
	
	            if (coveredValue < claimItem.getClaimItemValue() ) {
	                String desc = claimItem.getClaimItemRemarks();
	                String descCheck = claimItem.getBenefitCheckRemarks();
	
	                if (desc != null) {
	                    desc += " \nsisa benefit " + claimItem.getItemId().getItemName() + " = " + Converter.getMoney(coveredValue);
	                    descCheck += " \nsisa benefit " + claimItem.getItemId().getItemName() + " = " + Converter.getMoney(coveredValue);
	                } else {
	                    desc = " sisa benefit " + claimItem.getItemId().getItemName() + " = " + Converter.getMoney(coveredValue);
	                    descCheck = " sisa benefit " + claimItem.getItemId().getItemName() + " = " + Converter.getMoney(coveredValue);
	                }
	                claimItem.setExcessValue(claimItem.getClaimItemValue() - coveredValue);
	                claimItem.setClaimItemRemarks(desc);
	                claimItem.setBenefitCheckRemarks(descCheck);
	            }
        	}
        } else {
            String desc = claimItem.getClaimItemRemarks();

            if (desc != null) {
                desc += " \nSisa benefit " + claimItem.getItemId().getItemName() + " habis" ;
            } else {
                desc = "Sisa benefit " + claimItem.getItemId().getItemName() + " habis" ;
            }
            claimItem.setClaimItemRemarks(desc);
            claimItem.setBenefitCheckRemarks(desc);
            claimItem.setClaimItemCoveredValue(Double.valueOf(0));
            claimItem.setExcessValue(claimItem.getClaimItemValue());


        }

        if (outerLimit > 0){
        	outerLimitRemaining = outerLimit - claimItem.getClaimItemCoveredValue().doubleValue();
        }
        
        if (memberBenefit.getMemberLimitLayerId() != null && memberBenefit.getDiagnosisSetId() != null){
        	String remarks = "[SMM] " + claimItem.getBenefitCheckRemarks();
        	claimItem.setBenefitCheckRemarks(remarks);
        }
        
        claimItem.setRefund(0.0);
        claimItemDao.update(claimItem);
        
        return outerLimitRemaining;

    }

    private boolean checkClausul() {
        boolean result = false;

        return result;
    }

    private boolean checkDiagnosisClausul() {

        boolean result = false;

        return result;
    }

    private boolean checkLayananClausul() {
        boolean result = false;

        return result;
    }

    private boolean checkUsiaClausul(Member member) {
        boolean result = false;

        return result;
    }

    private boolean checkAdministrationClausul() {
        boolean result = false;

        return result;
    }

    private boolean checkObatClausul() {
        boolean result = false;

        return result;
    }

    private double checkPerServiceValue(ClaimItem claimItem) {
        double perItemServiceValue = 0.0;

        if (claimItem.getClaimItemAmount().doubleValue() > 0) {
            perItemServiceValue = claimItem.getClaimItemValue().doubleValue()
                    / claimItem.getClaimItemAmount().doubleValue();
        }

        return perItemServiceValue;
    }

    public Collection<ClaimItem> getVerifiableItem(Integer claimId)
            throws Exception {
        // TODO Auto-generated method stub
        Collection<ClaimItem> result = null;

        if (claimId != null) {

            Criteria c = claimItemDao.getCriteria();
            String[] eqParam = {"claimId.claimId",
                "claimItemStatus.caseStatusId", "deletedStatus"};
            Object[] eqValue = {claimId, ClaimItem.CLAIM_ITEM_OPEN,
                Integer.valueOf(0)};
            DaoSupportUtil.setEqParam(eqParam, eqValue, c);

            result = c.list();

        }

        return result;
    }

    public double[] approveBulkItemCheck(Collection<ClaimItem> claimItems,
            ActionUser actionUser) throws Exception {
        // TODO Auto-generated method stub

        double[] result = new double[2];
        double approvedValue = 0.0;
        double excess = 0.0;
        double refund = 0.0;
        double prePaidExcess = 0.0;

        if (claimItems != null) {
            Iterator<ClaimItem> iterator = claimItems.iterator();

            ClaimItem claimItem = null;

            while (iterator.hasNext()) {
                claimItem = iterator.next();
                if (claimItem != null) {
                	System.out.println("APPROVE + CHECK CLAIM ITEM : " + claimItem.getItemId().getItemEDCCode() + " Name : " + claimItem.getItemId().getItemEDCName() + " Name Real : " + claimItem.getItemId().getItemName() + " ITEM CATEGORY : " + 
                			claimItem.getItemId().getItemCategoryId().getItemCategoryName() + " ITEM CAT CODE : " + claimItem.getItemId().getItemCategoryId().getItemCategoryEDCCode());

                    double[] res = approveCheckItem(claimItem, actionUser);

                    if (res != null) {
                        excess += res[1];
                        approvedValue += res[0];
                    }
                }
            }

            result[0] = approvedValue;
            result[1] = excess;
        }

        return result;
    }

    public PaymentReportSummary getPaymentReportSummary(Integer batchClaimId)
            throws Exception {
        // TODO Auto-generated method stub

        Session session = claimItemDao.getClaimItemSession();

        PaymentReportSummary reportSummary = null;

        String sqlQuery = "SELECT sum(claimItem.claimItemValue) as chargeValue, sum(claimItem.claimItemApprovedValue) as approvedValue,"
                + "(sum(claimItem.claimItemValue) - sum(claimItem.claimItemApprovedValue)) as rejectedValue"
                + ", sum(claimItem.claimExGratia) as exGratia, sum(claimItem.excessValue) as excessValue FROM ClaimItem claimItem "
                + "WHERE claimItem.claimId.claimId IN (SELECT c.claimId FROM Claim c WHERE c.batchClaimId.batchClaimId = :batchId) AND"
                + " claimItem.deletedStatus = 0" ;

        Query query = session.createQuery(sqlQuery);
        query.setInteger("batchId", batchClaimId);

        Object[] result = (Object[]) query.uniqueResult();

        if (result != null) {
            reportSummary = new PaymentReportSummary();
            Object chargeValue = result[0];
            Object approvedValue = result[1];
            Object rejectedValue = result[2];
            Object exGratia = result[3];
            Object excessValue = result[4];

            if (chargeValue != null) {
                reportSummary.setClaimChargeValue(Double.valueOf(chargeValue.toString()));
            }
            if (approvedValue != null) {
                reportSummary.setClaimApprovedValue(Double.valueOf(approvedValue.toString()));
            }
            if (rejectedValue != null) {
                reportSummary.setClaimRejectedValue(Double.valueOf(rejectedValue.toString()));
            }
            if (exGratia != null) {
                reportSummary.setClaimExGratiaValue(Double.valueOf(exGratia.toString()));
            }
            if (excessValue != null) {
                reportSummary.setClaimExcessValue(Double.valueOf(excessValue.toString()));
            }

        }
        return reportSummary;
    }

    public boolean voidClaimItem(ClaimItem claimItem, ActionUser actionUser)
            throws Exception {
        // TODO Auto-generated method stub

        boolean result = false;
        if (claimItem != null) {

            CaseStatus caseStatus = new CaseStatus();
            caseStatus.setCaseStatusId(CaseStatus.VOID);

            MemberBenefit memberBenefit = memberBenefitService.getMemberBenefit(claimItem);

            if (memberBenefit != null && claimItem.getClaimItemStatus().getCaseStatusId().intValue() == Claim.CLAIM_CHECKED) {

                double approvedValue = claimItem.getClaimItemApprovedValue() == null ? 0.0 : claimItem.getClaimItemApprovedValue();
                double excessedValue = claimItem.getExcessValue() == null ? 0.0 : claimItem.getExcessValue();
                double exGratiaValue = claimItem.getClaimExGratia() == null ? 0.0 : claimItem.getClaimExGratia();
                double occurance = claimItem.getClaimItemAmount() == null ? 0.0 : claimItem.getClaimItemAmount();

                double memberBenefitApprovedValue = memberBenefit.getBenefitUsage() == null ? 0 : memberBenefit.getBenefitUsage();
                double memberBenefitExcessedValue = memberBenefit.getBenefitExcessed() == null ? 0 : memberBenefit.getBenefitExcessed();
                double memberBenefitExGratia = memberBenefit.getBenefitExGratia() == null ? 0 : memberBenefit.getBenefitExGratia();
                double memberBenefitOccurance = memberBenefit.getBenefitOccuranceUsage() == null ? 0 : memberBenefit.getBenefitOccuranceUsage();

                memberBenefit.setBenefitExcessed(memberBenefitExcessedValue - excessedValue);
                memberBenefit.setBenefitExGratia(memberBenefitExGratia - exGratiaValue);
                memberBenefit.setBenefitUsage(memberBenefitApprovedValue - approvedValue);
                memberBenefit.setBenefitOccuranceUsage(memberBenefitOccurance - occurance);

                memberBenefitService.update(memberBenefit, actionUser);

            }

            claimItem.setClaimItemStatus(caseStatus);
            
            claimItem.setClaimItemRemarks("voided");

            update(claimItem, actionUser);

            result = true;
        }
        return result;
    }

    public boolean voidClaimItem(Integer claimItemId, ActionUser actionUser)
            throws Exception {
        // TODO Auto-generated method stub

        ClaimItem claimItem = get(claimItemId);


        return voidClaimItem(claimItem, actionUser);
    }

    public Collection<ClaimItem> getClaimItem(Integer claimId)
            throws Exception {
        // TODO Auto-generated method stub
        Collection<ClaimItem> result = null;

        if (claimId != null) {

            Criteria c = claimItemDao.getCriteria();
            String[] eqParam = {"claimId.claimId", "deletedStatus"};
            Object[] eqValue = {claimId,
                Integer.valueOf(0)};
            DaoSupportUtil.setEqParam(eqParam, eqValue, c);


            String[] required = {"ClaimItem.ItemId","ClaimItem.ItemId.ItemCategoryId"};

            result = c.list();
            
            if (result != null){
            	for (Iterator iterator = result.iterator(); iterator.hasNext();) {
					ClaimItem claimItem = (ClaimItem) iterator.next();
					
					DaoSupportUtil.lazyInit(required, claimItem);
				}
            }


            
            

        }

        return result;
    }
    
    public Collection<ClaimItem> getClaimItem(Integer claimId, String[] required)
            throws Exception {
        // TODO Auto-generated method stub
        Collection<ClaimItem> result = null;

        if (claimId != null) {

            Criteria c = claimItemDao.getCriteria();
            String[] eqParam = {"claimId.claimId", "deletedStatus"};
            Object[] eqValue = {claimId,
                Integer.valueOf(0)};
            DaoSupportUtil.setEqParam(eqParam, eqValue, c);
            //String[] required = {"ClaimItem.ItemId","ClaimItem.ItemId.ItemCategoryId"};

            result = c.list();
            
            if (result != null){
            	for (Iterator iterator = result.iterator(); iterator.hasNext();) {
					ClaimItem claimItem = (ClaimItem) iterator.next();
					
					DaoSupportUtil.lazyInit(required, claimItem);
				}
            }


            
            

        }

        return result;
    }
    
    //Add 08042015, For Report Benefit Calculation
    //Gak tau kenapa pake yang atas ditambahin ClaimItem.MemberBenefitId jadi error2
    public Collection<ClaimItem> getClaimItemsForBenefitLetter(Integer claimId)
            throws Exception {
        // TODO Auto-generated method stub
        Collection<ClaimItem> result = null;

        if (claimId != null) {

            Criteria c = claimItemDao.getCriteria();
            String[] eqParam = {"claimId.claimId", "deletedStatus"};
            Object[] eqValue = {claimId,
                Integer.valueOf(0)};
            DaoSupportUtil.setEqParam(eqParam, eqValue, c);


            String[] required = {"ClaimItem.ItemId","ClaimItem.ItemId.ItemCategoryId", "ClaimItem.MemberBenefitId"};

            result = c.list();
            
            if (result != null){
            	for (Iterator iterator = result.iterator(); iterator.hasNext();) {
					ClaimItem claimItem = (ClaimItem) iterator.next();
					
					DaoSupportUtil.lazyInit(required, claimItem);
				}
            }
        }

        return result;
    }


    public Collection<ClaimItem> getExcessClaimItem(Integer claimId)
            throws Exception {
        // TODO Auto-generated method stub

        Collection<ClaimItem> result = null;

        if (claimId != null) {

            Criteria c = claimItemDao.getCriteria();

            String[] param = {"claimId.claimId", "deletedStatus"};
            Object[] value = {claimId, Integer.valueOf(0)};

            DaoSupportUtil.setEqParam(param, value, c);
            DaoSupportUtil.setGreaterThan(Double.valueOf(0), "excessValue", c);
            
            String[] required = {"ClaimItem.ItemId"};

            result = c.list();
            
            if (result != null){
            	for (Iterator iterator = result.iterator(); iterator.hasNext();) {
					ClaimItem claimItem = (ClaimItem) iterator.next();
					
					DaoSupportUtil.lazyInit(required, claimItem);
				}
            }

        }

        return result;
    }

    public int verifyBulk(Serializable batchClaimId, ActionUser actionUser)
            throws Exception {
        // TODO Auto-generated method stub

        Session session = claimItemDao.getClaimItemSession();


        String sqlQuery = "UPDATE ClaimItem ci SET ci.claimItemStatus = :claimItemStatus "
                + ", ci.verifiedBy = :userId, ci.verifiedDate = :time WHERE ci.claimId.claimId IN (SELECT c.claimId FROM Claim c WHERE c.batchClaimId.batchClaimId = :batchId) AND"
                + " ci.deletedStatus = 0" ;

        Query query = session.createQuery(sqlQuery);
        query.setInteger("batchId", (Integer) batchClaimId);
        query.setInteger("claimItemStatus", ClaimItem.CLAIM_ITEM_APPROVED);
        query.setString("userId", actionUser.getUser().getUsername());
        query.setDate("time", new java.sql.Date(System.currentTimeMillis()));

        int result = query.executeUpdate();

        return result;
    }

    public boolean synchronizeClaimItemBenefit(ClaimItem claimItem,
            ActionUser actionUser) throws Exception {
        // TODO Auto-generated method stub

        boolean result = false;

        if (claimItem != null) {
            MemberBenefit benefit = memberBenefitService.getMemberBenefit(claimItem);

            if (benefit != null) {
                claimItem.setMemberBenefitId(benefit);
                update(claimItem, actionUser);
                result = true;
            }
        }
        return false;
    }

    
    public ClaimItem getClaimItem(Serializable claimId, Serializable itemId)
            throws Exception {
        // TODO Auto-generated method stub

        ClaimItem result = null;

        String[] param = {"claimId.claimId", "itemId.itemId", "deletedStatus"};
        Object[] value = {claimId, itemId, Integer.valueOf(0)};

        Collection<ClaimItem> claimItems = search(null, null, param, value, 0, 1);

        if (claimItems != null) {
            Iterator<ClaimItem> iterator = claimItems.iterator();

            if (iterator != null && iterator.hasNext()) {
                result = iterator.next();
            }
        }

        return result;
    }

    public double getClaimItemUtilization(Integer memberId, Date effectiveDate, Date expireDate, Integer itemCategoryId, Integer caseCategoryId) {

        double result = 0.0;


        try {
            Session session = claimItemDao.getClaimItemSession();

            Query query = session.createQuery("SELECT sum(ci.claimItemApprovedValue) As total "
                    + "FROM ClaimItem ci WHERE ci.claimId.memberId.memberId = :memberId "
                    + "AND (ci.claimId.claimStatus.caseStatusId = :claimStatus"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimStatusIssued"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimChecked)"
                    + " AND ci.claimId.dischargeDate >= :startDate AND "
                    + " ci.claimId.caseCategoryId.caseCategoryId = :caseCategory "
                    + " AND ci.claimId.dischargeDate <= :endDate "
                    + " AND ci.itemId.itemCategoryId = :itemCategoryId AND ci.deletedStatus = 0");

            query.setInteger("memberId", memberId);
            query.setInteger("claimStatus", Claim.CLAIM_PAID);
            query.setInteger("claimStatusIssued", Claim.CLAIM_PAYMENT_ISSUED);
            query.setInteger("claimChecked", Claim.CLAIM_CHECKED);

            query.setDate("startDate", effectiveDate);
            query.setDate("endDate", expireDate);

            query.setInteger("caseCategory", caseCategoryId);
            query.setInteger("itemCategoryId", itemCategoryId);

            Object res = (Object) query.uniqueResult();

            if (res != null) {
                result = (Double) res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public double getClaimItemUtilization(Integer memberId, Date effectiveDate, Date expireDate, Integer itemCategoryId, Integer caseCategoryId, Integer memberLimitLayerId) {

        double result = 0.0;


        try {
            Session session = claimItemDao.getClaimItemSession();

            Query query = session.createQuery("SELECT sum(ci.claimItemApprovedValue) As total "
                    + "FROM ClaimItem ci WHERE ci.claimId.memberId.memberId = :memberId "
                    + "AND (ci.claimId.claimStatus.caseStatusId = :claimStatus"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimStatusIssued"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimChecked)"
                    + " AND ci.claimId.dischargeDate >= :startDate AND "
                    + " ci.claimId.caseCategoryId.caseCategoryId = :caseCategory "
                    + " AND ci.claimId.dischargeDate <= :endDate "
                    + " AND ci.itemId.itemCategoryId = :itemCategoryId AND ci.deletedStatus = 0 "
                    + " AND ci.memberBenefitId.memberLimitLayerId.memberLimitLayerId = :layerId");

            query.setInteger("memberId", memberId);
            query.setInteger("claimStatus", Claim.CLAIM_PAID);
            query.setInteger("claimStatusIssued", Claim.CLAIM_PAYMENT_ISSUED);
            query.setInteger("claimChecked", Claim.CLAIM_CHECKED);

            query.setDate("startDate", effectiveDate);
            query.setDate("endDate", expireDate);

            query.setInteger("caseCategory", caseCategoryId);
            query.setInteger("itemCategoryId", itemCategoryId);
            query.setInteger("layerId", memberLimitLayerId);

            Object res = (Object) query.uniqueResult();

            if (res != null) {
                result = (Double) res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // class+

    public Collection<Object[]> getClaimItem() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public double getBenefitTotalOccured(Integer memberId, Date effectiveDate, Date expireDate, Integer itemCategoryId, Integer caseCategoryId) {


        double result = 0.0;


        try {
        	Session session = claimItemDao.getClaimItemSession();
            Query query = session.createQuery("SELECT count(ci.claimItemId) As total "
                    + "FROM ClaimItem ci WHERE ci.claimId.memberId.memberId = :memberId "
                    + "AND (ci.claimId.claimStatus.caseStatusId = :claimStatus"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimStatusIssued"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimChecked)"
                    + " AND ci.claimId.dischargeDate >= :startDate AND "
                    + " ci.claimId.caseCategoryId.caseCategoryId = :caseCategory "
                    + " AND ci.claimId.dischargeDate <= :endDate "
                    + " AND ci.itemId.itemCategoryId = :itemCategoryId " +
                    " AND ci.claimItemApprovedValue > 0 AND ci.deletedStatus = 0");

            query.setInteger("memberId", memberId);
            query.setInteger("claimStatus", Claim.CLAIM_PAID);
            query.setInteger("claimStatusIssued", Claim.CLAIM_PAYMENT_ISSUED);
            query.setInteger("claimChecked", Claim.CLAIM_CHECKED);

            query.setDate("startDate", effectiveDate);
            query.setDate("endDate", expireDate);

            query.setInteger("caseCategory", caseCategoryId);
            query.setInteger("itemCategoryId", itemCategoryId);

            Object res = (Object) query.uniqueResult();

            if (res != null) {
                result = ((Long) res).doubleValue();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;


    }
    public Collection<ClaimItem> getPreviousClaim(Integer memberId,Date start,Date end,Integer itemCategoryId,
    		Integer caseCategoryId, Integer diagnosisId){
    	
    	Collection<ClaimItem> result = new Vector<ClaimItem>();
    	

        try {
        	Session session = claimItemDao.getClaimItemSession();
            Query query = session.createQuery("SELECT ci "
                    + "FROM ClaimItem ci WHERE ci.claimId.memberId.memberId = :memberId "
                    + "AND (ci.claimId.claimStatus.caseStatusId = :claimStatus"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimStatusIssued"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimChecked)"
                    + " AND ci.claimId.dischargeDate >= :startDate AND "
                    + " ci.claimId.caseCategoryId.caseCategoryId = :caseCategory "
                    + " AND ci.claimId.dischargeDate <= :endDate "
                    + " AND ci.itemId.itemCategoryId = :itemCategoryId AND ci.deletedStatus = 0" +
                    " AND ci.claimId.diagnosisId.diagnosisId = :diagId");

            query.setInteger("memberId", memberId);
            query.setInteger("claimStatus", Claim.CLAIM_PAID);
            query.setInteger("claimStatusIssued", Claim.CLAIM_PAYMENT_ISSUED);
            query.setInteger("claimChecked", Claim.CLAIM_CHECKED);

            query.setDate("startDate", start);
            query.setDate("endDate", end);

            query.setInteger("caseCategory", caseCategoryId);
            query.setInteger("itemCategoryId", itemCategoryId);
            query.setInteger("diagId", diagnosisId);

            result = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    	
    	
    }
    public double getBenefitTotalOccured(Integer memberId, Date effectiveDate, Date expireDate, Integer itemCategoryId, Integer caseCategoryId,
    		Integer diagnosisId) {


        double result = 0.0;


        try {
        	Session session = claimItemDao.getClaimItemSession();
            Query query = session.createQuery("SELECT count(ci.claimItemId) As total "
                    + "FROM ClaimItem ci WHERE ci.claimId.memberId.memberId = :memberId "
                    + "AND (ci.claimId.claimStatus.caseStatusId = :claimStatus"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimStatusIssued"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimChecked)"
                    + " AND ci.claimId.dischargeDate >= :startDate AND "
                    + " ci.claimId.caseCategoryId.caseCategoryId = :caseCategory "
                    + " AND ci.claimId.dischargeDate <= :endDate "
                    + " AND ci.itemId.itemCategoryId = :itemCategoryId AND ci.deletedStatus = 0" +
                    " AND ci.claimItemApprovedValue > 0 AND ci.claimId.diagnosisId.diagnosisId = :diagId");

            query.setInteger("memberId", memberId);
            query.setInteger("claimStatus", Claim.CLAIM_PAID);
            query.setInteger("claimStatusIssued", Claim.CLAIM_PAYMENT_ISSUED);
            query.setInteger("claimChecked", Claim.CLAIM_CHECKED);

            query.setDate("startDate", effectiveDate);
            query.setDate("endDate", expireDate);

            query.setInteger("caseCategory", caseCategoryId);
            query.setInteger("itemCategoryId", itemCategoryId);
            query.setInteger("diagId", diagnosisId);

            Object res = (Object) query.uniqueResult();

            if (res != null) {
                result = ((Long) res).doubleValue();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;


    }

    public double getBenefitUsagePerCase(Integer memberId, Date admission, Date discharge, Integer itemCategoryId, Integer caseCategoryId) {


        double result = 0.0;


        try {
        	Session session = claimItemDao.getClaimItemSession();
            Query query = session.createQuery("SELECT sum(ci.claimItemApprovedValue) As total "
                    + "FROM ClaimItem ci WHERE ci.claimId.memberId.memberId = :memberId "
                    + "AND (ci.claimId.claimStatus.caseStatusId = :claimStatus"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimStatusIssued"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimChecked)"
                    + " AND ci.claimId.dischargeDate >= :startDate AND "
                    + " ci.claimId.caseCategoryId.caseCategoryId = :caseCategory "
                    + " AND ci.claimId.dischargeDate <= :endDate "
                    + " AND ci.itemId.itemCategoryId = :itemCategoryId AND ci.deletedStatus = 0");

            query.setInteger("memberId", memberId);
            query.setInteger("claimStatus", Claim.CLAIM_PAID);
            query.setInteger("claimStatusIssued", Claim.CLAIM_PAYMENT_ISSUED);
            query.setInteger("claimChecked", Claim.CLAIM_CHECKED);

            query.setDate("startDate", admission);
            query.setDate("endDate", discharge);

            query.setInteger("caseCategory", caseCategoryId);
            query.setInteger("itemCategoryId", itemCategoryId);

            Object res = (Object) query.uniqueResult();

            if (res != null) {
                result = ((Double) res).doubleValue();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;


    }

    public double getBenefitUsagePerCase (Integer memberId, Date effectiveDate, Date expireDate, Integer itemCategoryId, 
    		Integer caseCategoryId, Integer diagnosisId)  {
        double result = 0.0;


        try {
        	Session session = claimItemDao.getClaimItemSession();
            Query query = session.createQuery("SELECT sum(ci.claimItemApprovedValue) As total "
                    + "FROM ClaimItem ci WHERE ci.claimId.memberId.memberId = :memberId "
                    + "AND (ci.claimId.claimStatus.caseStatusId = :claimStatus"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimStatusIssued"
                    + " OR ci.claimId.claimStatus.caseStatusId = :claimChecked)"
                    + " AND ci.claimId.dischargeDate >= :startDate AND "
                    + " ci.claimId.caseCategoryId.caseCategoryId = :caseCategory "
                    + " AND ci.claimId.dischargeDate <= :endDate "
                    + " AND ci.itemId.itemCategoryId = :itemCategoryId AND ci.deletedStatus = 0" +
                    		" AND ci.claimId.diagnosisId.diagnosisId = :diagId");

            query.setInteger("memberId", memberId);
            query.setInteger("claimStatus", Claim.CLAIM_PAID);
            query.setInteger("claimStatusIssued", Claim.CLAIM_PAYMENT_ISSUED);
            query.setInteger("claimChecked", Claim.CLAIM_CHECKED);

            query.setDate("startDate", effectiveDate);
            query.setDate("endDate", expireDate);

            query.setInteger("caseCategory", caseCategoryId);
            query.setInteger("itemCategoryId", itemCategoryId);
            query.setInteger("diagId", diagnosisId);

            Object res = (Object) query.uniqueResult();

            if (res != null) {
                result = (Double) res;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public Collection<ClaimItem> getMemberClaimItem(Integer memberId,
            Date startDate, Date endDate) throws Exception {
        Collection<ClaimItem> result = new Vector<ClaimItem>();

        try {
            /*
             * String[] eqParam = {"claimId.memberId.memberId"}; Object[]
             * eqValue = {memberId}; String[] required = {};
             *
             * Object[] start = {startDate}; Object[] end = {endDate}; String[]
             * between = {"claimId.claimDate"}; int total =
             * claimItemService.getTotal(null,null,eqParam,eqValue,"claimId.claimDate",startDate,endDate);
             * Collection<ClaimItem> list =
             * claimItemService.search(null,null,eqParam,eqValue,between,start,end,required,0,total);
             *
             * if (list != null){ for (ClaimItem claimItem : list) {
             * ClaimItemDto dto = claimItem.export();
             *
             * result.add(dto); } }
             */

            Session session = claimItemDao.getClaimItemSession();

            if (session != null) {
                String query = "SELECT ci FROM ClaimItem ci WHERE ci.claimId.memberId.memberId = ? AND ci.claimId.claimDate >= ? AND ci.claimId.claimDate <= ?" ;

                Query q = session.createQuery(query);
                q.setInteger(0, memberId);
                q.setDate(1, startDate);
                q.setDate(2, endDate);

                result = q.list();


            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Collection<ClaimItemDto> getClaimItemDto(Integer claimId)
            throws Exception {

        Collection<ClaimItemDto> result = null;


        if (claimId != null) {

            Criteria c = claimItemDao.getCriteria();
            String[] eqParam = {"claimId.claimId", "deletedStatus"};
            Object[] eqValue = {claimId,
                Integer.valueOf(0)};
            DaoSupportUtil.setEqParam(eqParam, eqValue, c);

            Collection<ClaimItem> tmp = c.list();

            if (tmp != null && tmp.size() > 0) {
                result = new Vector<ClaimItemDto>();
                for (ClaimItem claimItem : tmp) {
                    ClaimItemDto dto = claimItem.export();

                    result.add(dto);
                }
            }

        }

        return result;
    }

    public Collection<ClaimItem> getBatchClaimItem(Integer batchClaimId)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    // class-

    
    @Override
    public Collection search(String[] likeColumns, Object[] likeParams,
    String[] eqColumns, Object[] eqParams, String betweenParam, Object minBetween, 
    Object maxBetween, String[] required, Double claimChargeValue) throws Exception {
        
        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        
        
        
        DaoSupportUtil.setBetweenParam(betweenParam, minBetween, maxBetween, c);

        if (claimChargeValue != null) {
            DaoSupportUtil.setGreaterThanEqual(claimChargeValue, "claimId.claimChargeValue", c);
        }
        List list = c.list();


        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;
    }
    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, String[] required, Double minimumClaim)
            throws Exception {
        Criteria c = claimItemDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);

        if (minimumClaim != null) {
            DaoSupportUtil.setGreaterThanEqual(minimumClaim, "claimId.claimChargeValue", c);
        }

        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ClaimItem element = (ClaimItem) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;
    }

	@Override
	public boolean getIsClaimItemExist(Integer claimId, Integer itemId)
			throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		
		try {
			String[] eqParam = {"claimId.claimId","itemId.itemId","deletedStatus"};
			Object[] eqValue = {claimId, itemId,0};
			
			int total = getTotal(null,null,eqParam,eqValue);
			
			if (total> 0){
				result = true;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public PaymentReportSummary getReportSummaryByPayment(Integer paymentId)
			throws Exception {

		Session session = claimItemDao.getClaimItemSession();

        PaymentReportSummary reportSummary = null;

        String sqlQuery = "SELECT sum(claimItem.claimItemValue) as chargeValue, sum(claimItem.claimItemApprovedValue) as approvedValue,"
                + "(sum(claimItem.claimItemValue) - sum(claimItem.claimItemApprovedValue)) as rejectedValue"
                + ", sum(claimItem.claimExGratia) as exGratia, sum(claimItem.excessValue) as excessValue FROM ClaimItem claimItem "
                + "WHERE claimItem.claimId.claimId IN (SELECT c.claimId FROM Claim c WHERE c.paymentId.paymentId = :paymentId) AND"
                + " claimItem.deletedStatus = 0" ;

        Query query = session.createQuery(sqlQuery);
        query.setInteger("paymentId", paymentId);

        Object[] result = (Object[]) query.uniqueResult();

        if (result != null) {
            reportSummary = new PaymentReportSummary();
            Object chargeValue = result[0];
            Object approvedValue = result[1];
            Object rejectedValue = result[2];
            Object exGratia = result[3];
            Object excessValue = result[4];

            if (chargeValue != null) {
                reportSummary.setClaimChargeValue(Double.valueOf(chargeValue.toString()));
            }
            if (approvedValue != null) {
                reportSummary.setClaimApprovedValue(Double.valueOf(approvedValue.toString()));
            }
            if (rejectedValue != null) {
                reportSummary.setClaimRejectedValue(Double.valueOf(rejectedValue.toString()));
            }
            if (exGratia != null) {
                reportSummary.setClaimExGratiaValue(Double.valueOf(exGratia.toString()));
            }
            if (excessValue != null) {
                reportSummary.setClaimExcessValue(Double.valueOf(excessValue.toString()));
            }

        }
        return reportSummary;

	}




}
