
package com.ametis.cms.service.impl;


import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.ExternalClaimService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.ExternalClaim;
import com.ametis.cms.datamodel.ExternalClaimDetail;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.ProviderService;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dao.ExternalClaimDao;

import java.awt.ItemSelectable;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 

/**
 * ExternalClaim is a servlet controller for external_claim Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class ExternalClaimServiceImpl implements ExternalClaimService

// extends+ 

// extends- 
{
	
	private ExternalClaimDao  externalClaimDao;
	private ClaimService claimService;
	private ClaimItemService claimItemService;
	private DiagnosisService diagnosisService;	
	private MemberProductService memberProductService;
	private MemberBenefitService memberBenefitService;
	private MemberService memberService;
	private ItemService itemService;
	private ItemCategoryService itemCategoryService;
	private com.ametis.cms.service.ProviderService providerService;
	
	
	
	

	
	public com.ametis.cms.service.ProviderService getProviderService() {
		return providerService;
	}
	public void setProviderService(
			com.ametis.cms.service.ProviderService providerService) {
		this.providerService = providerService;
	}
	public ItemService getItemService() {
		return itemService;
	}
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	public ItemCategoryService getItemCategoryService() {
		return itemCategoryService;
	}
	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public MemberProductService getMemberProductService() {
		return memberProductService;
	}
	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}
	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}
	public void setMemberBenefitService(MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}
	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}
	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
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
	public void setExternalClaimDao (ExternalClaimDao object){
		this.externalClaimDao = object;
	}
	public ExternalClaimDao getExternalClaimDao (){
		return this.externalClaimDao;
	}
	/*
	* Method create (ExternalClaim object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public ExternalClaim create (ExternalClaim object,ActionUser user) throws Exception {
		
		ExternalClaim result = null;
		object.setCreatedTime (new java.sql.Timestamp (System.currentTimeMillis()));
	
					
		if (user != null && user.getUser() != null){
			object.setCreatedBy(user.getUser().getUsername());
		}
	
		
		String[] eqParam = {"claimNumber","itemCode"};
        Object[] eqValue = {object.getClaimNumber(),object.getItemCode()};    
                
        String[] eqParamMember = {"deletedStatus","customerPolicyNumber"};
        Object[] eqValueMember = {Integer.valueOf(0),object.getMemberNumber()};
        
        Collection<Member> memberList = memberService.search(null,null,eqParamMember,eqValueMember,0,1);
        
        Member member = null;
        
        if (memberList != null){
        	Iterator<Member> iteratorMember = memberList.iterator();
        	
        	if (iteratorMember.hasNext()){
        		member = iteratorMember.next();
        		System.out.println("GETTING MEMBER = : " + member.getFirstName() + " CUSTOMER NUMBER = " + member.getCustomerPolicyNumber());
        	}
        }
        
        int total = getTotal(null,null,eqParam,eqValue);        
        
        if (total == 0){
        	
        	System.out.println("EXTERNAL CLAIM NOT FOUND ---> INSERTING NEW CLAIM");
        	result = externalClaimDao.create (object);
        	
        	if (result != null){
        		
        		String[] eqClaim = {"claimNumber","deletedStatus"};
        		Object[] eqClaimValue = {object.getClaimNumber(),Integer.valueOf(0)};
        		
        		int totalClaim = claimService.getTotal(null,null, eqClaim, eqClaimValue);
        		
        		Claim claim = null;
        		CaseCategory cc = new CaseCategory();
        		System.out.println("LOOKING FOR = " + object.getClaimNumber() + " --- > TOTAL = " + totalClaim);
        		if (totalClaim == 0){
        			
        			claim = new Claim();
        			claim.setClaimNumber(object.getClaimNumber());
        			claim.setAdmissionDate(object.getAdmissionDate());
        			claim.setDiagnosis1Code(object.getDiagnosis1Code());
        			claim.setDiagnosis2Code(object.getDiagnosis2Code());
        			claim.setDiagnosis3Code(object.getDiagnosis3Code());
        			claim.setProviderName(object.getProviderName());
        			
        			claim.setClaimChargeValue(object.getClaimChargeValue());
        			claim.setClaimApprovedValue(object.getClaimApprovedValue());
        			claim.setMemberId(member);
        			
        			String category = object.getClaimCategory();
        			
        		
        			
        			if (category != null){
        				if (category.trim().equalsIgnoreCase("OUTPATIENT")){
        					cc.setCaseCategoryId(CaseCategory.OUTPATIENT);
        				}
						if (category.trim().equalsIgnoreCase("INPATIENT")){
							cc.setCaseCategoryId(CaseCategory.INPATIENT);    					
						}
						if (category.trim().equalsIgnoreCase("DENTAL")){
							cc.setCaseCategoryId(CaseCategory.DENTAL);
						}
						if (category.trim().equalsIgnoreCase("MATERNITY")){
							cc.setCaseCategoryId(CaseCategory.MATERNITY);
						}
						if (category.trim().equalsIgnoreCase("OPTICAL")){
							cc.setCaseCategoryId(CaseCategory.OPTICAL);
        				}
        			}        			
        			claim.setCaseCategoryId(cc);        			
        			claim = claimService.create(claim, user);
        			System.out.println("CLAIM CREATED FOR ID = " + claim.getClaimId());
        		}
        		
        		
        		if (claim != null){
        			
        			ClaimItem claimItem = new ClaimItem();
        			
        			claimItem.setClaimId(claim);
        			claimItem.setItemCode(object.getItemCode());
        			claimItem.setItemName(object.getItemName());
        			
        			
        			claimItem.setClaimItemAmount(1.0);
        			claimItem.setClaimItemApprovedValue(object.getClaimApprovedValue());
        			claimItem.setClaimItemValue(object.getClaimChargeValue());
        			claimItem.setBenefitCheckRemarks(object.getRemarks());
        			claimItem.setDeletedStatus(0);
        			
        			CaseStatus status = new CaseStatus();
        			status.setCaseStatusId(Claim.CLAIM_CHECKED);
        			claimItem.setClaimItemStatus(status);
        			
        			String[] eqParamBenefitItem = {"memberId.memberId","deletedStatus","productId.caseCategory.caseCategoryId"};
        	        Object[] eqValueBenefitItem = {member.getMemberId(),Integer.valueOf(0),cc.getCaseCategoryId()};
        	        
        	        String[] eqParamMemberBen = {"memberId.memberId","deletedStatus","itemCategoryId.itemCategoryAlternateCode","caseCategoryId.caseCategoryId"};
        	        Object[] eqValueMemberBen = {member.getMemberId(),Integer.valueOf(0),object.getItemCode(),cc.getCaseCategoryId()};
        	        
        	        Collection<MemberProduct> memberProductList = memberProductService.search(null,null,eqParamBenefitItem,eqValueBenefitItem,0,1);        	        
        	        
        	        if (memberProductList != null){
        	        	Iterator<MemberProduct> iteratorProduct = memberProductList.iterator();
        	        	if (iteratorProduct.hasNext()){
        	        		System.out.println("MEMBER PRODUCT FOUND !");
        	        		MemberProduct currentProduct = iteratorProduct.next();
        	        		
        	        		if (currentProduct != null){
        	        			claim.setProductId(currentProduct);
        	        			
        	        			if (currentProduct.getActualBenefitLimit() != null && currentProduct.getActualBenefitLimit().doubleValue() > 0){
        	        				
        	        				double current = currentProduct.getActualBenefitLimit().doubleValue();
        	        				double approved = object.getClaimApprovedValue().doubleValue();
        	        				
        	        				System.out.println("CURRENT : " + current + " APPROVED = " + approved );
        	        				double toBeUpdated = current-approved;
        	        				double currentUsage = currentProduct.getBenefitUsage().doubleValue();
        	        			
        	        				claim.setRemainingMemberLimit(toBeUpdated);
        	        				
        	        				currentProduct.setBenefitUsage(currentUsage+approved);
        	        				currentProduct.setActualBenefitLimit(toBeUpdated);
        	        				double percentage = currentProduct.getAnnualBenefit()-toBeUpdated/currentProduct.getAnnualBenefit();
        	        				currentProduct.setBenefitUsagePercentage(percentage);
        	        				
        	        				System.out.println("TIDAK AS CHARGE ---> BENEFIT DI TAMBAH USAGE + KURANG BENEFIT NYA AJA");
        	        			}
        	        			else {
        	        	
        	        				System.out.println("AS CHARGE ---> PRODUCT DI TAMBAH USAGE NYA AJA");
        	        				double currentUsage = currentProduct.getBenefitUsage().doubleValue();
        	        				double approved = object.getClaimApprovedValue().doubleValue();
        	        				currentProduct.setBenefitUsage(currentUsage+approved);
        	        			}
        	        			
        	        			memberProductService.update(currentProduct, user);
    	        				claimService.update(claim, user);
        	        			
        	        		}
        	        	}
        	        }
        	        Item item = null;
        	        MemberBenefit memberBenefit = null;
        	        
        	        Collection<MemberBenefit> memberBenefitList = memberBenefitService.search(null,null,eqParamMemberBen,eqValueMemberBen,0,1);
        	        
        	        System.out.println("Member ID = " +member.getMemberId() + " item = " + object.getItemCode() + " case = " + cc.getCaseCategoryId());
        	        if (memberBenefitList != null){
        	        	Iterator<MemberBenefit> iteratorBenefit = memberBenefitList.iterator();
        	        	if (iteratorBenefit.hasNext()){
        	        		memberBenefit = iteratorBenefit.next();
        	        		
        	        		if (memberBenefit != null){
        	        
        	        			System.out.println("ITEM CODE = " + object.getItemCode());
        	        			String[] eqItemParam = {"itemCategoryId.itemCategoryId","deletedStatus","itemAlternateCode"};
        	        			Object[] eqItemValue = {memberBenefit.getItemCategoryId().getItemCategoryId(),Integer.valueOf(0),object.getItemCode()};
        	        			
        	        			Collection<Item> itemList = itemService.search(null,null,eqItemParam,eqItemValue,0,1);
        	        			if (itemList != null){
        	        				Iterator<Item> itemIterator = itemList.iterator();
        	        				
        	        				if (itemIterator.hasNext()){
        	        					item = itemIterator.next();
        	        				}
        	        			}
        	        			
        	        			if(item != null){
        	        				claimItem.setItemId(item);
        	        				claimItem.setMemberBenefitId(memberBenefit);
        	        				claimItem.setPreApproveRemainingBenefit(memberBenefit.getCurrentBenefitPosition());
        	        			}
        	        			else {
        	        				System.out.println("ITEM TIDAK DITEMUKAN !! ---> " + object.getItemCode());
        	        			}
        	        			
        	        		}
        	        	}
        	        }
        			claimItemService.create(claimItem, user);
        			
        			if (memberBenefit != null && memberBenefit.getCurrentBenefitPosition() != null && memberBenefit.getCurrentBenefitPosition().doubleValue() > 0){
        				
        				double currentUsage = memberBenefit.getBenefitUsage().doubleValue();
        				
        				double currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
        				double postapproved = currentBenefit - object.getClaimApprovedValue();
        				
        				currentUsage = currentUsage + object.getClaimApprovedValue();
        				
        				memberBenefit.setBenefitUsage(currentUsage);
        				memberBenefit.setCurrentBenefitPosition(postapproved);
        				claimItem.setPostApproveRemainingBenefit(postapproved);
        				
        				System.out.println("TIDAK AS CHARGE ---> BENEFIT DI TAMBAH USAGE + KURANG BENEFIT NYA AJA");
        				
        			}
        			else {
        			
        				if (memberBenefit != null){
	        				double currentUsage = 0;
	        				if (memberBenefit.getBenefitUsage() != null){
	        					currentUsage = memberBenefit.getBenefitUsage().doubleValue();
	        				}
	        				currentUsage = currentUsage + object.getClaimApprovedValue();
	        				System.out.println("AS CHARGE ---> BENEFIT DI TAMBAH USAGE NYA AJA");
	        				memberBenefit.setBenefitUsage(currentUsage);
        				}
        			}
        			
        			if (memberBenefit != null){
        				memberBenefitService.update(memberBenefit, user);        				
        				claimItemService.update(claimItem, user);
        			}
        		}
        	}
        }
		return result;
	}
	/*
	* Method update (ExternalClaim object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public ExternalClaim update (ExternalClaim object,ActionUser user) throws Exception{
		
			object.setModifiedTime (new java.sql.Timestamp (System.currentTimeMillis()));
			
			if (user != null && user.getUser() != null){
				object.setModifiedBy (user.getUser().getUsername());
			}
		
		
 		externalClaimDao.update (object);
      	return object;
	}


	/*
	* Method delete (Object pkey) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @return no return value karena objeknya sendiri sudah dihapus - just for consistency. Again,
	* exception if failure occured
	* WARNING ! Invalid value for the returned object, better not use it again in any
	* place
	*/
	public ExternalClaim trash (java.io.Serializable pkey) throws Exception {
		ExternalClaim object = externalClaimDao.get (pkey);
		externalClaimDao.delete (object);
		return object;
	}

	/*
	* Method delete (ExternalClaim object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public ExternalClaim delete (java.io.Serializable pkey,ActionUser user) throws Exception{
		ExternalClaim object = externalClaimDao.get (pkey);


		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		
		
		
		


		
		
		
		externalClaimDao.update (object);
		return object;
	}


	/*
	* Method delete (ExternalClaim object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public ExternalClaim delete (ExternalClaim object,ActionUser user) throws Exception{
		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		

		
		
		

		
		
		
		externalClaimDao.update (object);
		return object;
	}

// -- get section - carefull !


	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	public ExternalClaim get (java.io.Serializable pkey) throws Exception{
		ExternalClaim object = null;
		object = externalClaimDao.get(pkey);
		return object;
	}
	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @param required adalah array dari field-field yang dibutuhkan dari hibernate object
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/

	public ExternalClaim get (java.io.Serializable pkey, String[] required) throws Exception{
	    ExternalClaim object = null;
	    object = externalClaimDao.get(pkey);
		DaoSupportUtil.lazyInit(required,object);
	    return object;
	}
// -- get section end here


// SEARCH SECTION - PALING RUMIT !!
// * -> plain
// *b -> with columnOrder



// -- 1
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExternalClaim element = (ExternalClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;

	}
	//--req
		public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExternalClaim element = (ExternalClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExternalClaim element = (ExternalClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExternalClaim element = (ExternalClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;

	}
	// req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			 String[] required,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExternalClaim element = (ExternalClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;

	}
	// req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			 String[] required,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExternalClaim element = (ExternalClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}

	// req end



// -- 2 , between

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExternalClaim element = (ExternalClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			boolean asc, String columnOrder[],
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExternalClaim element = (ExternalClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExternalClaim element = (ExternalClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}

	// req end



 // -- 2b
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExternalClaim element = (ExternalClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	//req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			boolean asc, String columnOrder[],
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExternalClaim element = (ExternalClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExternalClaim element = (ExternalClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams,
		String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2
		) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams,
		String btwnColumns, Object btwnParams1,
			Object btwnParams2
		) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = externalClaimDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExternalClaim element = (ExternalClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = externalClaimDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public ExternalClaim searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		ExternalClaim obj = (ExternalClaim) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public ExternalClaim searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = externalClaimDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		ExternalClaim obj = (ExternalClaim) c.uniqueResult();
		return obj;
	}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = externalClaimDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = externalClaimDao.getDetachedCriteria();
		return dc;
	}




// class+ 

// class- 
}
