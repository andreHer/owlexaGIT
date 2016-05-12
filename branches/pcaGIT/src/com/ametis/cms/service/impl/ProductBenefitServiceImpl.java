
package com.ametis.cms.service.impl;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.dao.ProductBenefitDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BenefitUsageType;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductBenefit;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ProductBenefitService;
import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 


/**
 * ProductBenefit is a servlet controller for product_benefit Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class ProductBenefitServiceImpl implements ProductBenefitService

// extends+ 

// extends- 

{
	
	private ProductBenefitDao  productBenefitDao;
private ActivityLogService activityLogService;
	
	
	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}
	public void setProductBenefitDao (ProductBenefitDao object){
		this.productBenefitDao = object;
	}
	public ProductBenefitDao getProductBenefitDao (){
		return this.productBenefitDao;
	}
	/*
	* Method create (ProductBenefit object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public ProductBenefit create (ProductBenefit object,ActionUser actionUser) throws Exception {

			object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			object.setDeletedStatus(Integer.valueOf(0));
			
			Integer dependentSharingMethod = object.getDependentSharingMethod();
			
			if (dependentSharingMethod != null){
				if (dependentSharingMethod.intValue() == ProductBenefit.DEPENDENT_UNCOVERED){
					object.setDependentCoveredStatus(0);
				}
				else {
					object.setDependentCoveredStatus(1);
				}
			}
			
			if (actionUser != null){
				User user = actionUser.getUser();				
				if (user != null){
					object.setCreatedBy(user.getUsername());
				}
			}
			if (object.getBenefitLimit() != null && object.getBenefitLimit().doubleValue() == -1.0){
				object.setIsAsCharge(true);
				
			}
			else if (object.getBenefitLimit() != null && object.getBenefitLimit().doubleValue() > -1.0){
				object.setIsAsCharge(false);
			}
			if (object.getReimbursementBenefitLimit() != null && object.getReimbursementBenefitLimit().doubleValue() == -1.0){
				object.setReimburseAsCharge(true);
			}
			else  if (object.getReimbursementBenefitLimit() != null && object.getReimbursementBenefitLimit().doubleValue() > -1.0){
					object.setReimburseAsCharge(false);
			}
			
			if (object.getIsDeductPlanLimit() == null){
				object.setIsDeductPlanLimit(1);
			}
			
			

			

			ProductBenefit result = productBenefitDao.create (object);
			return result;
		}
	/*
	* Method update (ProductBenefit object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public ProductBenefit update (ProductBenefit object,ActionUser actionUser) throws Exception{
		
		
//		object.setUpdatedDate (new java.sql.Date (System.currentTimeMillis()));

				
				
		
					
				
				
		
			object.setModifiedTime (new java.sql.Timestamp (System.currentTimeMillis()));

			if (object.getBenefitLimit() != null && object.getBenefitLimit().doubleValue() == -1.0){
				object.setIsAsCharge(true);
				
			}
			else if (object.getBenefitLimit() != null && object.getBenefitLimit().doubleValue() > -1.0){
				object.setIsAsCharge(false);
			}
			if (object.getReimbursementBenefitLimit() != null && object.getReimbursementBenefitLimit().doubleValue() == -1.0){
				object.setReimburseAsCharge(true);
			}
			else  if (object.getReimbursementBenefitLimit() != null && object.getReimbursementBenefitLimit().doubleValue() > -1.0){
					object.setReimburseAsCharge(false);
			}

		
		
				
				
				
			/*
				Gue tambahin mekanisme NULL value checking 
				just in case user nya null
			*/
					if (actionUser != null){
						User user = actionUser.getUser();
			if (user != null){
				object.setModifiedBy (user.getUsername());
			}
					}
		
		
 		productBenefitDao.update (object);
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
	public ProductBenefit trash (java.io.Serializable pkey) throws Exception {
		ProductBenefit object = productBenefitDao.get (pkey);
		productBenefitDao.delete (object);
		return object;
	}

	/*
	* Method delete (ProductBenefit object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public ProductBenefit delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception{
		ProductBenefit object = productBenefitDao.get (pkey);


		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		
		
		
		object.setDeletedStatus(new Integer(1));

		


		if (actionUser != null){
			User user = actionUser.getUser();
			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
		}
		
		productBenefitDao.update (object);
		return object;
	}


	/*
	* Method delete (ProductBenefit object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public ProductBenefit delete (ProductBenefit object,ActionUser actionUser) throws Exception{
		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		

		
		
		object.setDeletedStatus(new Integer(1));

		

		if (actionUser != null){
			User user = actionUser.getUser();	
			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
		}

		
		productBenefitDao.update (object);
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
	public ProductBenefit get (java.io.Serializable pkey) throws Exception{
		ProductBenefit object = null;
		object = productBenefitDao.get(pkey);
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

	public ProductBenefit get (java.io.Serializable pkey, String[] required) throws Exception{
	    ProductBenefit object = null;
	    object = productBenefitDao.get(pkey);
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

		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ProductBenefit element = (ProductBenefit) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = productBenefitDao.getCriteria();
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
		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ProductBenefit element = (ProductBenefit) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = productBenefitDao.getCriteria();
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
		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ProductBenefit element = (ProductBenefit) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = productBenefitDao.getCriteria();
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
		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ProductBenefit element = (ProductBenefit) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = productBenefitDao.getCriteria();
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

		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ProductBenefit element = (ProductBenefit) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = productBenefitDao.getCriteria();
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

		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ProductBenefit element = (ProductBenefit) iter.next();
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

		Criteria c = productBenefitDao.getCriteria();
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

		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ProductBenefit element = (ProductBenefit) iter.next();
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

		Criteria c = productBenefitDao.getCriteria();
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

		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ProductBenefit element = (ProductBenefit) iter.next();
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

		Criteria c = productBenefitDao.getCriteria();
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

		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ProductBenefit element = (ProductBenefit) iter.next();
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

		Criteria c = productBenefitDao.getCriteria();
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

		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ProductBenefit element = (ProductBenefit) iter.next();
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

		Criteria c = productBenefitDao.getCriteria();
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

		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ProductBenefit element = (ProductBenefit) iter.next();
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

		Criteria c = productBenefitDao.getCriteria();
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

		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ProductBenefit element = (ProductBenefit) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = productBenefitDao.getCriteria();
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

		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = productBenefitDao.getCriteria();
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

		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = productBenefitDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = productBenefitDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ProductBenefit element = (ProductBenefit) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = productBenefitDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public ProductBenefit searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		ProductBenefit obj = (ProductBenefit) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public ProductBenefit searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = productBenefitDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		ProductBenefit obj = (ProductBenefit) c.uniqueResult();
		return obj;
	}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = productBenefitDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = productBenefitDao.getDetachedCriteria();
		return dc;
	}


	public boolean createProductBenefit (Collection<ProductBenefit> benefits,Product product, ActionUser actionUser) throws Exception {
		boolean res = false;
		
		
		if (benefits != null){
			Iterator<ProductBenefit> benefitIterator = benefits.iterator();
			
			if (benefitIterator != null){
				
				ProductBenefit benefit = null;
				ProductBenefit benefitFromDB = null;
				
				while (benefitIterator.hasNext()){
					
					benefit = benefitIterator.next();
					
					if (benefit != null){
						
						benefitFromDB = get (benefit.getProductBenefitId());
						
						if (benefitFromDB != null){
							
							ProductBenefit benefitToSave  = new ProductBenefit();
							
							ProductBenefit refBenefit = new ProductBenefit();
							refBenefit.setProductBenefitId(benefit.getProductBenefitId());
							
							benefitToSave.setReferenceBenefitId(refBenefit);
							benefitToSave.setProductId(product);
							benefitToSave.setBenefitLimit(benefit.getBenefitLimit());
							benefitToSave.setReimbursementBenefitLimit(benefit.getReimbursementBenefitLimit());
							benefitToSave.setMaxBenefitPerCase(benefit.getMaxBenefitPerCase());
							benefitToSave.setDeductibleLimit(benefit.getDeductibleLimit());
							
							if (benefit.getProductLayerId() != null){
								benefitToSave.setProductLayerId(benefit.getProductLayerId());	
							}
							
							benefitToSave.setBenefitUpgradable(benefitFromDB.isBenefitUpgradable());
							benefitToSave.setCostSharing(benefitFromDB.isCostSharing());
							
							
							benefitToSave.setBenefitUpgradeCoverageDaysInterval(benefitFromDB.getBenefitUpgradeCoverageDaysInterval());
							benefitToSave.setBenefitUpgradeLimit(benefitFromDB.getBenefitUpgradeLimit());
							benefitToSave.setBenefitUsageType(benefitFromDB.getBenefitUsageType());
							
							benefitToSave.setCaseCategoryId(benefitFromDB.getCaseCategoryId());
							
							benefitToSave.setCostSharingMethod(benefitFromDB.getCostSharingMethod());
							benefitToSave.setCostSharingPercentage(benefitFromDB.getCostSharingPercentage());
							
							benefitToSave.setCoverageDaysInterval(benefitFromDB.getCoverageDaysInterval());
							benefitToSave.setDependentCoveredStatus(benefitFromDB.getDependentCoveredStatus());
							benefitToSave.setDependentSharingMethod(benefitFromDB.getDependentSharingMethod());
							
							benefitToSave.setDiscount(benefitFromDB.getDiscount());
							benefitToSave.setDiscountUsageType(benefitFromDB.getDiscountUsageType());
							benefitToSave.setInformation(benefitFromDB.getInformation());
							benefitToSave.setItemCategoryId(benefitFromDB.getItemCategoryId());
							
													
							benefitToSave.setIsEdcEnabled(benefitFromDB.getIsEdcEnabled());
							benefitToSave.setBenefitShowedOnEdc(benefitFromDB.getBenefitShowedOnEdc());
							
							benefitToSave.setMaxOccurance(benefitFromDB.getMaxOccurance());
							benefitToSave.setMaxOccurancePerCase(benefitFromDB.getMaxOccurancePerCase());
							
							benefitToSave.setMeasurementUnit(benefitFromDB.getMeasurementUnit());
							benefitToSave.setProviderContract(benefitFromDB.getProviderContract());
							benefitToSave.setTreatmentLevel(benefitFromDB.getTreatmentLevel());
							benefitToSave.setTreatmentLocation(benefitFromDB.getTreatmentLocation());
							
							benefitToSave.setCashlessPercentage(benefitFromDB.getCashlessPercentage());
							benefitToSave.setReimbursementPercentage(benefitFromDB.getReimbursementPercentage());
							
							benefitToSave.setDependentCoSharePercentage(benefitFromDB.getDependentCoSharePercentage());
							benefitToSave.setDependentCoShareAmount(benefitFromDB.getDependentCoShareAmount());
							benefitToSave.setIsDeductPlanLimit(benefitFromDB.getIsDeductPlanLimit());
							
							
							
							benefitToSave.setDivertBenefit(false);
							
							if (benefitFromDB.getDivertBenefit() != null && benefitFromDB.getDivertBenefit()){
								benefitToSave.setDivertBenefit(benefitFromDB.getDivertBenefit());
							}
							else {
								if (benefitFromDB.getSharedBenefitId() != null){
									benefitToSave.setDivertBenefit(true);
								}
							}
							
							
							
							
							if (benefit.getBenefitLimit() != null && benefit.getBenefitLimit().doubleValue() == -1){
								benefitToSave.setIsAsCharge(true);
							}
							
							create (benefitToSave,actionUser);
						}
					}
				}
				
				
			}
			
			String[] eqParam = {"deletedStatus","productId.productId"};
			Object[] eqValue = {Integer.valueOf(0),product.getProductId()};
			String[] required = {"ProductBenefit.SharedBenefitId"};
			
			int total = getTotal(null,null,eqParam,eqValue);
			Collection<ProductBenefit> collections = search(null,null,eqParam,eqValue,0,total);
			
			for (Iterator iterator = collections.iterator(); iterator.hasNext();) {
				ProductBenefit productBenefit = (ProductBenefit) iterator.next();
				
				if (productBenefit != null){
					ProductBenefit refProductBenefit = productBenefit.getReferenceBenefitId();
					
					if (productBenefit.getDivertBenefit() != null && productBenefit.getDivertBenefit()){
						if (productBenefit.getSharedBenefitId() == null){
							
							if (refProductBenefit != null){
								refProductBenefit = get(refProductBenefit.getProductBenefitId(),required);
								
								ProductBenefit sharedBenefit = refProductBenefit.getSharedBenefitId();
								
								if (sharedBenefit != null){
									CaseCategory cc = sharedBenefit.getCaseCategoryId();
									ItemCategory itemCategory = sharedBenefit.getItemCategoryId();
									BenefitUsageType usageType = sharedBenefit.getBenefitUsageType();
									
									if (cc != null && itemCategory != null && usageType != null){
										String[] eqDivertParam = {"deletedStatus","caseCategoryId.caseCategoryId","itemCategoryId.itemCategoryId",
												"benefitUsageType.benefitUsageTypeId","productId.productId"};
										
										Object[] eqDivertValue = {0,cc.getCaseCategoryId(),itemCategory.getItemCategoryId(),
												usageType.getBenefitUsageTypeId(),product.getProductId()};
										
										Collection<ProductBenefit> sharedBenefitList = search(null,null,eqDivertParam,eqDivertValue,0,1);
										
										if (sharedBenefitList != null){
											Iterator<ProductBenefit> iteratorShared = sharedBenefitList.iterator();
											
											if (iteratorShared.hasNext()){
												ProductBenefit shared = iteratorShared.next();
												productBenefit.setSharedBenefitId(shared);
												
												update(productBenefit,actionUser);
											}
											
										}
									}
								}
							}
						}
					}
				}
				
			}
			
		}
		return res;
	}
	@Override
	public Collection<ProductBenefit> getBenefitLayer( Integer layerId) throws Exception {
		// TODO Auto-generated method stub
		Collection<ProductBenefit> result = null;
		
		String[] eqParam = {"productLayerId.productLayerLimitId","deletedStatus"};
		Object[] eqValue = {layerId,Integer.valueOf(0)};
		
		int total = getTotal(null,null,eqParam,eqValue);
		
		result = search(null,null,eqParam,eqValue,0,total);
		
		return result;
	}

// class+ 

// class- 

}
