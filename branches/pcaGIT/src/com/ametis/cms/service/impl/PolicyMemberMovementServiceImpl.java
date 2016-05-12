package com.ametis.cms.service.impl;

import com.ametis.cms.service.DataImportStageService;
import com.ametis.cms.service.ExternalRawDataService;
import com.ametis.cms.service.MemberImportService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyMemberMovementService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.DataImportStage;
import com.ametis.cms.datamodel.ExportImportTemplate;
import com.ametis.cms.datamodel.ExternalRawData;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberImport;
import com.ametis.cms.datamodel.Relationship;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.datamodel.PolicyMemberMovement;
import com.ametis.cms.dao.PolicyMemberMovementDao;
import com.ametis.cms.datamodel.ActionUser;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.util.StringUtil;
import com.ametis.cms.util.dao.DaoSupportUtil;

// imports+ 
// imports- 
/**
 * PolicyMemberMovement is a servlet controller for policy_member_movement
 * Table. All you have to do is to convert necessary data field to the named
 * parameter
 */
public class PolicyMemberMovementServiceImpl implements PolicyMemberMovementService // extends+ 
// extends- 
{

    private PolicyMemberMovementDao policyMemberMovementDao;
    private DataImportStageService dataImportService;
    private ExternalRawDataService externalRawDataService;
    private MemberService memberService;
    private MemberImportService memberImportService;
    private PolicyService policyService;
    
    

    public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public ExternalRawDataService getExternalRawDataService() {
		return externalRawDataService;
	}

	public void setExternalRawDataService(
			ExternalRawDataService externalRawDataService) {
		this.externalRawDataService = externalRawDataService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public MemberImportService getMemberImportService() {
		return memberImportService;
	}

	public void setMemberImportService(MemberImportService memberImportService) {
		this.memberImportService = memberImportService;
	}

	public DataImportStageService getDataImportService() {
		return dataImportService;
	}

	public void setDataImportService(DataImportStageService dataImportService) {
		this.dataImportService = dataImportService;
	}

	public void setPolicyMemberMovementDao(PolicyMemberMovementDao object) {
        this.policyMemberMovementDao = object;
    }

    public PolicyMemberMovementDao getPolicyMemberMovementDao() {
        return this.policyMemberMovementDao;
    }
    /*
     * Method create (PolicyMemberMovement object) berfungsi untuk melakukan
     * penambahan sebuah object kedalam database @param object adalah sebuah
     * object yang ingin diubah @return object hasil kreasi,lengkap dengan
     * assigned primary key, exception jika gagal
     */

    public PolicyMemberMovement create(PolicyMemberMovement object,byte[] content, ActionUser user) throws Exception {

    	object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
        object.setDeletedStatus(new Integer(0));



        object.setStatus(Integer.valueOf(0));

        if (user != null && user.getUser() != null) {
            object.setCreatedBy(user.getUser().getUsername());
        }



        PolicyMemberMovement result = policyMemberMovementDao.create(object);
        
        if (result != null){
        	
        	DataImportStage dataImport = new DataImportStage();
        	dataImport.setCreatedBy(result.getCreatedBy());
        	dataImport.setCreatedTime(result.getCreatedTime());
        	dataImport.setDeletedStatus(Integer.valueOf(0));
        	dataImport.setDescription("Policy Member Movement : " + result.getMovementNumber());
        	dataImport.setExportImportTemplate(result.getTemplateId());
        	dataImport.setImportNumber(result.getMovementNumber());
        	dataImport.setImportReadyFile(result.getFormatedMovementFile());
        	dataImport.setTipe(DataImportStage.TIPE_MEMBER);
        	dataImport.setFileFormat(result.getFileFormat());
        	dataImport.setDatePattern(result.getDatePattern());
        	dataImport.setFirstLineHeader(result.getFirstLineHeader());
        	dataImport.setStatus(0);
        	dataImport.setMovementId(result);
        	
        	dataImportService.create(dataImport, content, user);
        }
        return result;
    }
    /*
     * Method update (PolicyMemberMovement object) berfungsi untuk melakukan
     * perubahan terhadap sebuah object yang terdapat didalam database @param
     * object adalah sebuah object yang ingin diubah @return object hasil
     * update, exception jika gagal
     */

    public PolicyMemberMovement activate (Integer id,ActionUser user) throws Exception {
    	PolicyMemberMovement result = null;
    	
    	try {
    		PolicyMemberMovement object = get(id);
    		
    		String[] eqParam = {"movementId.id"};
    		Object[] eqValue = {object.getId()};
    		
    		Collection<DataImportStage> stages = dataImportService.search(null,null,eqParam,eqValue,0,1);
    		
    		if (stages != null){
    			
    			String swipeCardPrefix = object.getPolicyId().getSwipeCardPrefix();
    			Integer isUsingSwipe = object.getPolicyId().getUsingSwipeCard();
    			MemberGroup memberGroup = object.getPolicyId().getMemberGroupId();
    			Client client = object.getPolicyId().getClientId();
    			
    			Iterator<DataImportStage> iterator = stages.iterator();
    			if (iterator != null && iterator.hasNext()){
    				DataImportStage importStage = iterator.next();
    				
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
    		    					member.setCustomerNumber(memberImport.getMemberNumber());
    		    					member.setCustomerPolicyNumber(memberImport.getMemberNumber());
    		    					member.setFirstName(memberImport.getMemberName());
    		    					member.setDepartment(memberImport.getDepartment());
    		    					member.setJoinDate(memberImport.getJoinDate());
    		    					member.setEffectiveDate(memberImport.getEffectiveDate());
    		    					member.setResignedDate(memberImport.getExpireDate());
    		    					member.setExpireDate(memberImport.getExpireDate());
    		    					member.setCurrentProductCode(memberImport.getProduct());
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
    		    					
    		    					member.setBank(memberImport.getBankName());
    		    					member.setBankBranch(memberImport.getBankBranch());
    		    					member.setBankAccountName(memberImport.getBankAccount());
    		    					member.setBankAccount(memberImport.getAccountNo());
    		    					
    		    					member.setClientId(client);
    		    					member.setMemberGroupId(memberGroup);
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
    		    					
    		    					
    		    					member.setStatus(SubscriptionStatus.PENDING);
    		    					
    		    					member.setCurrentCardNumber("");
    		    					
    		    					String swipeCard = memberImport.getSwipeCardNumber();
    		    					if (swipeCard != null && !swipeCard.equals("")){
    		    						member.setCurrentCardNumber(swipeCard);
    		    					}
    		    					else {
    		    						String swipeCardIndex = memberImport.getSwipeCardNumberIndex();    		    						
    		    						if (swipeCardIndex != null && !swipeCardIndex.equals("")){
    		    							swipeCard = swipeCardPrefix+StringUtil.paddingCardNumber(swipeCardIndex, 7);
    		    							member.setCurrentCardNumber(swipeCard);
    		    						}    		    						
    		    					}
    		    					
    		    					Member hasil = memberService.create(member, user);
    		    					
    		    					System.out.println("Created Member  : " + hasil.getCustomerPolicyNumber());
    		    				}
    		    			}
    		    		}
    				}
    			}
    		}
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    	return result;
    }
    public boolean extract (Integer id, ActionUser user) throws Exception {
    	boolean result = false;
    	
    	try{
    		PolicyMemberMovement movement = get(id);
    		
    		if (movement != null){
	    		String[] eqParam = {"movementId.id"};
	    		Object[] eqValue = {movement.getId()};
	    		
	    		Collection<DataImportStage> stages = dataImportService.search(null,null,eqParam,eqValue,0,1);
	    		
	    		if (stages != null){
	    			Iterator<DataImportStage> iterator = stages.iterator();
	    			if (iterator != null && iterator.hasNext()){
	    				DataImportStage importStage = iterator.next();
	    				
	    				if (importStage != null){
	    					
	    					boolean res = dataImportService.executeRawMigration(importStage.getId());
	    					
	    					if (res){
	    						movement.setStatus(Integer.valueOf(1));
	    						policyMemberMovementDao.update(movement);
	    			    		result = true;	    			    		
	    					}
	    				}
	    			}
	    		}	    		
    		}
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    	
    	return result;
    }
	public boolean migrate (Integer id, ActionUser user) throws Exception {
		boolean result = false;
		try{
			PolicyMemberMovement movement = get(id);
    		
    		if (movement != null){
	    		String[] eqParam = {"movementId.id"};
	    		Object[] eqValue = {movement.getId()};
	    		
	    		Collection<DataImportStage> stages = dataImportService.search(null,null,eqParam,eqValue,0,1);
	    		
	    		if (stages != null){
	    			Iterator<DataImportStage> iterator = stages.iterator();
	    			if (iterator != null && iterator.hasNext()){
	    				DataImportStage importStage = iterator.next();
	    				
	    				if (importStage != null){
	    					
	    					boolean res = dataImportService.executeMigration(importStage.getId());
	    					
	    					if (res){

	    						movement.setStatus(Integer.valueOf(2));
	    						policyMemberMovementDao.update(movement);
	    			    		result = true;
	    			    		
	    					}
	    				}
	    			}
	    		}	    		
    		}
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    	
		return result;
	}
    public PolicyMemberMovement update(PolicyMemberMovement object, ActionUser user) throws Exception {
        object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
        if (user != null && user.getUser() != null) {
            object.setModifiedBy(user.getUser().getUsername());
        }


        policyMemberMovementDao.update(object);
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
    public PolicyMemberMovement trash(java.io.Serializable pkey) throws Exception {
        PolicyMemberMovement object = policyMemberMovementDao.get(pkey);
        policyMemberMovementDao.delete(object);
        return object;
    }

    /*
     * Method delete (PolicyMemberMovement object) berfungsi untuk melakukan
     * penghapusan terhadap sebuah object yang terdapat didalam database @param
     * object adalah sebuah object yang ingin dihapus, isi dari object tersebut
     * cukup dengan mengisi field-field primary key @return updated object,
     * exception if failed
     */
    public PolicyMemberMovement delete(java.io.Serializable pkey, ActionUser user) throws Exception {
        PolicyMemberMovement object = policyMemberMovementDao.get(pkey);
        object.setDeletedStatus(new Integer(1));
        if (user != null && user.getUser() != null) {
            object.setDeletedBy(user.getUser().getUsername());
        }


        policyMemberMovementDao.update(object);
        return object;
    }


    /*
     * Method delete (PolicyMemberMovement object) berfungsi untuk melakukan
     * penghapusan terhadap sebuah object yang terdapat didalam database @param
     * object adalah sebuah object yang ingin dihapus, isi dari object tersebut
     * cukup dengan mengisi field-field primary key @return updated object,
     * exception if failed
     */
    public PolicyMemberMovement delete(PolicyMemberMovement object, ActionUser user) throws Exception {
        object.setDeletedStatus(new Integer(1));
        if (user != null && user.getUser() != null) {
            object.setDeletedBy(user.getUser().getUsername());
        }


        policyMemberMovementDao.update(object);
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
    public PolicyMemberMovement get(java.io.Serializable pkey) throws Exception {
        PolicyMemberMovement object = null;
        object = policyMemberMovementDao.get(pkey);
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

    public PolicyMemberMovement get(java.io.Serializable pkey, String[] required) throws Exception {
        PolicyMemberMovement object = null;
        object = policyMemberMovementDao.get(pkey);
        DaoSupportUtil.lazyInit(required, object);
        return object;
    }
// -- get section end here

// SEARCH SECTION - PALING RUMIT !!
// * -> plain
// *b -> with columnOrder
// -- 1
    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, int index, int offset) throws Exception {

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        List list = c.list();
        return list;

    }
    //--req

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, String[] required, int index, int offset) throws Exception {

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            PolicyMemberMovement element = (PolicyMemberMovement) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;

    }
    // --req end

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
            int index, int offset) throws Exception {

        Criteria c = policyMemberMovementDao.getCriteria();
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
        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            PolicyMemberMovement element = (PolicyMemberMovement) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;

    }
    // --req end

    public Collection search(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
            int index, int offset) throws Exception {

        Criteria c = policyMemberMovementDao.getCriteria();
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
        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            PolicyMemberMovement element = (PolicyMemberMovement) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;

    }
    // --req end

// -- 1b
    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams, int index, int offset) throws Exception {

        Criteria c = policyMemberMovementDao.getCriteria();
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
        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            PolicyMemberMovement element = (PolicyMemberMovement) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }

        return list;

    }
    // --req end

    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams,
            boolean asc, String columnOrder[],
            int index, int offset) throws Exception {

        Criteria c = policyMemberMovementDao.getCriteria();
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

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            PolicyMemberMovement element = (PolicyMemberMovement) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;

    }
    // req end

    public Collection search(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams,
            boolean asc, String columnOrder,
            int index, int offset) throws Exception {

        Criteria c = policyMemberMovementDao.getCriteria();
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

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            PolicyMemberMovement element = (PolicyMemberMovement) iter.next();
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

        Criteria c = policyMemberMovementDao.getCriteria();
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

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            PolicyMemberMovement element = (PolicyMemberMovement) iter.next();
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

        Criteria c = policyMemberMovementDao.getCriteria();
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

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            PolicyMemberMovement element = (PolicyMemberMovement) iter.next();
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

        Criteria c = policyMemberMovementDao.getCriteria();
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

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            PolicyMemberMovement element = (PolicyMemberMovement) iter.next();
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

        Criteria c = policyMemberMovementDao.getCriteria();
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

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            PolicyMemberMovement element = (PolicyMemberMovement) iter.next();
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

        Criteria c = policyMemberMovementDao.getCriteria();
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

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            PolicyMemberMovement element = (PolicyMemberMovement) iter.next();
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

        Criteria c = policyMemberMovementDao.getCriteria();
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

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setLimit(index, offset, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        DaoSupportUtil.setOrderBy(asc, columnOrder, c);
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            PolicyMemberMovement element = (PolicyMemberMovement) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;
    }
    // req end

// -- search end
//-- get total
    public int getTotal(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams) throws Exception {

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        int total = DaoSupportUtil.getTotal(c);
        return total;

    }

    public int getTotal(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams,
            String[] btwnColumns, Object[] btwnParams1,
            Object[] btwnParams2) throws Exception {

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        int total = DaoSupportUtil.getTotal(c);
        return total;

    }

    public int getTotal(String likeColumns, Object likeParams,
            String eqColumns, Object eqParams) throws Exception {

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        int total = DaoSupportUtil.getTotal(c);
        return total;

    }

    public int getTotal(String[] likeColumns, Object[] likeParams,
            String[] eqColumns, Object[] eqParams,
            String btwnColumns, Object btwnParams1,
            Object btwnParams2) throws Exception {

        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
        int total = DaoSupportUtil.getTotal(c);
        return total;

    }
//------------------------------------------------------------------

    public int getTotal() throws Exception {
        Criteria c = policyMemberMovementDao.getCriteria();
        int total = DaoSupportUtil.getTotal(c);
        return total;
    }
//-- get total end

//---------------------------------------------------
    public Collection getAll(String[] required) throws Exception {

        Criteria c = policyMemberMovementDao.getCriteria();
        List list = c.list();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            PolicyMemberMovement element = (PolicyMemberMovement) iter.next();
            DaoSupportUtil.lazyInit(required, element);
        }
        return list;

    }

    public Collection getAll() throws Exception {

        Criteria c = policyMemberMovementDao.getCriteria();
        List list = c.list();
        return list;
    }
//-------------------------------------------------

// unique Result
    public PolicyMemberMovement searchUnique(String eqColumns, Object eqParams, String[] required)
            throws Exception {
        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        PolicyMemberMovement obj = (PolicyMemberMovement) c.uniqueResult();
        DaoSupportUtil.lazyInit(required, obj);
        return obj;
    }

    public PolicyMemberMovement searchUnique(String eqColumns, Object eqParams)
            throws Exception {
        Criteria c = policyMemberMovementDao.getCriteria();
        DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
        PolicyMemberMovement obj = (PolicyMemberMovement) c.uniqueResult();
        return obj;
    }

// -----------------------------------------------

    /*
     * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
     * @return criteria
     */
    public Criteria getCriteria() throws Exception {
        Criteria c = policyMemberMovementDao.getCriteria();
        return c;
    }

    public DetachedCriteria getDetachedCriteria() throws Exception {
        DetachedCriteria dc = policyMemberMovementDao.getDetachedCriteria();
        return dc;
    }
// class+ 
// class- 
}
