
package com.ametis.cms.service.impl;


import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.dao.ExcessChargeItemDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ExcessChargeItem;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ExcessChargeItemService;
import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 


/**
 * ExcessChargeItem is a servlet controller for excess_charge_item Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class ExcessChargeItemServiceImpl implements ExcessChargeItemService

// extends+ 

// extends- 

{
	
	private ExcessChargeItemDao  excessChargeItemDao;
private ActivityLogService activityLogService;
	
	
	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}
	public void setExcessChargeItemDao (ExcessChargeItemDao object){
		this.excessChargeItemDao = object;
	}
	public ExcessChargeItemDao getExcessChargeItemDao (){
		return this.excessChargeItemDao;
	}
	/*
	* Method create (ExcessChargeItem object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public ExcessChargeItem create (ExcessChargeItem object,ActionUser actionUser) throws Exception {
			
			
//			object.setCreatedDate (new java.sql.Date (System.currentTimeMillis()));

			// ----

			
			
			object.setDeletedStatus(Integer.valueOf(0));

			
			if (actionUser != null){
				User user = actionUser.getUser();
						
			if (user != null){
				object.setCreatedBy(user.getUsername());
			}
			}

			

			ExcessChargeItem result = excessChargeItemDao.create (object);
			return result;
		}
	/*
	* Method update (ExcessChargeItem object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public ExcessChargeItem update (ExcessChargeItem object,ActionUser actionUser) throws Exception{
		
		
//		object.setUpdatedDate (new java.sql.Date (System.currentTimeMillis()));

				
				
		
					
				
				
		
					object.setModifiedTime (new java.sql.Timestamp (System.currentTimeMillis()));
		

		
		
				
				
				
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
		
		
 		excessChargeItemDao.update (object);
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
	public ExcessChargeItem trash (java.io.Serializable pkey) throws Exception {
		ExcessChargeItem object = excessChargeItemDao.get (pkey);
		excessChargeItemDao.delete (object);
		return object;
	}

	/*
	* Method delete (ExcessChargeItem object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public ExcessChargeItem delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception{
		ExcessChargeItem object = excessChargeItemDao.get (pkey);


		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		
		
		
		object.setDeletedStatus(new Integer(1));

		


		if (actionUser != null){
			User user = actionUser.getUser();
			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
		
		}

		
		excessChargeItemDao.update (object);
		return object;
	}


	/*
	* Method delete (ExcessChargeItem object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public ExcessChargeItem delete (ExcessChargeItem object,ActionUser actionUser) throws Exception{
		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		

		
		
		object.setDeletedStatus(new Integer(1));

		

		
		if (actionUser != null){
			User user = actionUser.getUser();
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
		}

		
		excessChargeItemDao.update (object);
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
	public ExcessChargeItem get (java.io.Serializable pkey) throws Exception{
		ExcessChargeItem object = null;
		object = excessChargeItemDao.get(pkey);
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

	public ExcessChargeItem get (java.io.Serializable pkey, String[] required) throws Exception{
	    ExcessChargeItem object = null;
	    object = excessChargeItemDao.get(pkey);
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

		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExcessChargeItem element = (ExcessChargeItem) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = excessChargeItemDao.getCriteria();
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
		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExcessChargeItem element = (ExcessChargeItem) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = excessChargeItemDao.getCriteria();
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
		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExcessChargeItem element = (ExcessChargeItem) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = excessChargeItemDao.getCriteria();
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
		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExcessChargeItem element = (ExcessChargeItem) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = excessChargeItemDao.getCriteria();
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

		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExcessChargeItem element = (ExcessChargeItem) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = excessChargeItemDao.getCriteria();
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

		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExcessChargeItem element = (ExcessChargeItem) iter.next();
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

		Criteria c = excessChargeItemDao.getCriteria();
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

		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExcessChargeItem element = (ExcessChargeItem) iter.next();
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

		Criteria c = excessChargeItemDao.getCriteria();
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

		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExcessChargeItem element = (ExcessChargeItem) iter.next();
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

		Criteria c = excessChargeItemDao.getCriteria();
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

		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExcessChargeItem element = (ExcessChargeItem) iter.next();
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

		Criteria c = excessChargeItemDao.getCriteria();
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

		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExcessChargeItem element = (ExcessChargeItem) iter.next();
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

		Criteria c = excessChargeItemDao.getCriteria();
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

		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExcessChargeItem element = (ExcessChargeItem) iter.next();
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

		Criteria c = excessChargeItemDao.getCriteria();
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

		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExcessChargeItem element = (ExcessChargeItem) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = excessChargeItemDao.getCriteria();
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

		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = excessChargeItemDao.getCriteria();
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

		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = excessChargeItemDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = excessChargeItemDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ExcessChargeItem element = (ExcessChargeItem) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = excessChargeItemDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public ExcessChargeItem searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		ExcessChargeItem obj = (ExcessChargeItem) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public ExcessChargeItem searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = excessChargeItemDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		ExcessChargeItem obj = (ExcessChargeItem) c.uniqueResult();
		return obj;
	}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = excessChargeItemDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = excessChargeItemDao.getDetachedCriteria();
		return dc;
	}
	public Collection<ExcessChargeItem> getExcessItem(
			Serializable excessChargeId) throws Exception {
		// 
		String[] param = {"excessChargeId.excessChargeId","deletedStatus"};
		Object[] value = {excessChargeId,Integer.valueOf(0)};
		
		int total = getTotal (null,null,param,value);
		Collection<ExcessChargeItem> result = search(null,null,param,value,0,total);
		
		return result;
	}




// class+ 

// class- 

}
