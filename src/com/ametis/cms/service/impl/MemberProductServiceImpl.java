
package com.ametis.cms.service.impl;


import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.joda.time.DateTime;

import com.ametis.cms.dao.MemberProductDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 


/**
 * MemberProduct is a servlet controller for member_product Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class MemberProductServiceImpl implements MemberProductService

// extends+ 

// extends- 

{
	
	private MemberProductDao  memberProductDao;
	private ActivityLogService activityLogService;
	private PolicyService policyService;
	
	
	
	
	public PolicyService getPolicyService() {
		return policyService;
	}
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}
	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}
	public void setMemberProductDao (MemberProductDao object){
		this.memberProductDao = object;
	}
	public MemberProductDao getMemberProductDao (){
		return this.memberProductDao;
	}
	/*
	* Method create (MemberProduct object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public MemberProduct create (MemberProduct object,ActionUser actionUser) throws Exception {
			
			
			object.setDeletedStatus(Integer.valueOf(0));
			object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));

			
			
			if (actionUser != null){
				User user = actionUser.getUser();				
				if (user != null){
					object.setCreatedBy(user.getUsername());
					object.setModifiedBy(user.getUsername());
				}
			}

			

			MemberProduct result = memberProductDao.create (object);
			return result;
		}
	/*
	* Method update (MemberProduct object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public MemberProduct update (MemberProduct object,ActionUser actionUser) throws Exception{
		
					object.setModifiedTime (new java.sql.Timestamp (System.currentTimeMillis()));
		
					if (actionUser != null){
						User user = actionUser.getUser();			
			if (user != null){
				object.setModifiedBy (user.getUsername());
			}
					}
		
		
 		memberProductDao.update (object);
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
	public MemberProduct trash (java.io.Serializable pkey) throws Exception {
		MemberProduct object = memberProductDao.get (pkey);
		memberProductDao.delete (object);
		return object;
	}

	/*
	* Method delete (MemberProduct object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public MemberProduct delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception{
		MemberProduct object = memberProductDao.get (pkey);


		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(new Integer(1));

		


		
		if (actionUser != null){
			User user = actionUser.getUser();		
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
		}

		
		memberProductDao.update (object);
		return object;
	}


	/*
	* Method delete (MemberProduct object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public MemberProduct delete (MemberProduct object,ActionUser actionUser) throws Exception{
		
		

		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(new Integer(1));

		

		if (actionUser != null){
			User user = actionUser.getUser();	
			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
		}

		
		memberProductDao.update (object);
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
	public MemberProduct get (java.io.Serializable pkey) throws Exception{
		MemberProduct object = null;
		object = memberProductDao.get(pkey);
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

	public MemberProduct get (java.io.Serializable pkey, String[] required) throws Exception{
	    MemberProduct object = null;
	    object = memberProductDao.get(pkey);
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

		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberProduct element = (MemberProduct) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = memberProductDao.getCriteria();
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
		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberProduct element = (MemberProduct) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = memberProductDao.getCriteria();
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
		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberProduct element = (MemberProduct) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = memberProductDao.getCriteria();
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
		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberProduct element = (MemberProduct) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = memberProductDao.getCriteria();
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

		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberProduct element = (MemberProduct) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = memberProductDao.getCriteria();
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

		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberProduct element = (MemberProduct) iter.next();
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

		Criteria c = memberProductDao.getCriteria();
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

		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberProduct element = (MemberProduct) iter.next();
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

		Criteria c = memberProductDao.getCriteria();
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

		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberProduct element = (MemberProduct) iter.next();
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

		Criteria c = memberProductDao.getCriteria();
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

		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberProduct element = (MemberProduct) iter.next();
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

		Criteria c = memberProductDao.getCriteria();
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

		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberProduct element = (MemberProduct) iter.next();
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

		Criteria c = memberProductDao.getCriteria();
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

		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberProduct element = (MemberProduct) iter.next();
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

		Criteria c = memberProductDao.getCriteria();
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

		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberProduct element = (MemberProduct) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = memberProductDao.getCriteria();
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

		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = memberProductDao.getCriteria();
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

		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = memberProductDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = memberProductDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberProduct element = (MemberProduct) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = memberProductDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public MemberProduct searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		MemberProduct obj = (MemberProduct) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public MemberProduct searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = memberProductDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		MemberProduct obj = (MemberProduct) c.uniqueResult();
		return obj;
	}

	public MemberProduct searchUnique (String[] eqColumns, Object[] eqParams)
	throws Exception{
		MemberProduct obj = null;	
	Criteria c = memberProductDao.getCriteria();
	DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
	
	try {
	 obj = (MemberProduct) c.uniqueResult();
	}
	catch (Exception e){
		for (int i = 0 ; i < eqColumns.length; i++){
			System.out.println(eqColumns[i] + "   = " + eqParams[i] );
		}
	}
	return obj;
}
// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = memberProductDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = memberProductDao.getDetachedCriteria();
		return dc;
	}
	public MemberProduct getMemberActiveProduct(Integer memberId,
			Integer caseCategoryId) throws Exception {
		// TODO Auto-generated method stub
		String[] required = {"ProductId"};
		String[] eqColumns = {"memberId.memberId","productId.caseCategory.caseCategoryId","memberProductStatus.statusId"};
		Object[] eqParams = {memberId,caseCategoryId,SubscriptionStatus.ACTIVE};
		
		/**
		 * BUGFIX 0000034 [Benefit] Product Synchronize Duplicates
		 * Patch : Sort By memberProductId DESC
		 */
		
		Collection<MemberProduct> productList = search(null,null,eqColumns,eqParams,false,"memberProductId",required,0,1); 
		MemberProduct result = null;
		if (productList != null && productList.size() > 0){
			Iterator<MemberProduct> iterator = productList.iterator();
			
			if (iterator.hasNext()){
				result = iterator.next();
			}
		}
		
		return result;
	}
	
	public MemberProduct getMemberStatusProduct(Integer memberId,
			Integer caseCategoryId, Integer status) throws Exception {
		// TODO Auto-generated method stub
		String[] required = {"ProductId"};
		String[] eqColumns = {"memberId.memberId","productId.caseCategory.caseCategoryId",
				"memberProductStatus.statusId"};
		Object[] eqParams = {memberId,caseCategoryId,status};
		
		Collection<MemberProduct> productList = search(null,null,eqColumns,eqParams,required,0,1); 
		MemberProduct result = null;
		if (productList != null && productList.size() > 0){
			Iterator<MemberProduct> iterator = productList.iterator();
			
			if (iterator.hasNext()){
				result = iterator.next();
			}
		}
		
		return result;
	}
	
	
	public MemberProduct getMemberActiveProduct(Integer memberId,
			Integer caseCategoryId, Date currentDate) throws Exception {
		// TODO Auto-generated method stub
		String[] eqColumns = {"memberId.memberId","productId.caseCategory.caseCategoryId","memberProductStatus.statusId"};
		Object[] eqParams = {memberId,caseCategoryId,SubscriptionStatus.ACTIVE};
		
		MemberProduct result = searchUnique(eqColumns, eqParams);
		return result;
	}
	public MemberProduct getMemberActiveProduct(Integer memberId,
			Integer caseCategoryId, Date effectiveDate, Date expireDate) throws Exception {
		// TODO Auto-generated method stub
		
		MemberProduct result = null;
		
		try {
		
			Session session =  memberProductDao.getProductSession();
			
			if (session != null){
				String q = "SELECT p FROM MemberProduct p WHERE p.expireDate >= :exp AND p.memberId.memberId = :memberId " +
						" AND p.productId.caseCategory.caseCategoryId = :ccId" +
						" AND p.registerDate <= :effectiveDate AND p.expireDate <= : AND p.deletedStatus = 0 AND " +
						"( p.memberProductStatus.statusId = " + SubscriptionStatus.ACTIVE
						 +" OR p.memberProductStatus.statusId = " + SubscriptionStatus.EXPIRED +") ORDER BY p.memberProductId DESC";
				
				org.hibernate.Query query = session.createQuery(q);
				query.setDate("exp", expireDate);
				query.setInteger("memberId", memberId);
				query.setInteger("ccId", caseCategoryId);
				query.setDate("effectiveDate", effectiveDate);
				
				if (query != null){
					List list = query.list();
					
					if (list != null){
						Iterator<MemberProduct> iterator = list.iterator();
						
						if (iterator.hasNext()){
							result = iterator.next();
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
	
	public Collection<MemberProduct> getMemberActiveProduct(Integer memberId)
			throws Exception {
		
		String[] required = {"MemberProduct.ProductId.CaseCategory"};
		
		System.out.println("LAZY INIT = " + "MemberProduct.ProductId.CaseCategory");
		String[] eqColumns = {"memberId.memberId","memberProductStatus.statusId"};
		Object[] eqParams = {memberId,SubscriptionStatus.ACTIVE};
		
		Collection<MemberProduct> result = search(null,null,eqColumns, eqParams,required,0,100);
		
		return result;
	}
	@Override
	public MemberProduct getMemberProductByDate(Integer memberId,
			Integer caseCategoryId, Date admissionDate, Date dischargeDate)
			throws Exception {
		// TODO Auto-generated method stub
		
		MemberProduct result = null;
		
		try {
		
			Session session =  memberProductDao.getProductSession();
			
			String[] required = {"MemberProduct.FamilyProductId","MemberProduct.SecondaryProductId"};
			
			if (session != null){
				String q = "SELECT p FROM MemberProduct p WHERE p.expireDate >= :exp AND p.memberId.memberId = :memberId " +
						" AND p.productId.caseCategory.caseCategoryId = :ccId" +
						" AND p.registerDate <= :effectiveDate AND p.deletedStatus = 0 AND " +
						"( p.memberProductStatus.statusId = " + SubscriptionStatus.ACTIVE
						 +" OR p.memberProductStatus.statusId = " + SubscriptionStatus.UPGRADED + " OR " +
						 		"p.memberProductStatus.statusId = " + SubscriptionStatus.REVISION +" OR "
						 				+ "p.memberProductStatus.statusId = " + SubscriptionStatus.EXPIRED + " ) "
						 						+ "ORDER BY p.memberProductId DESC";
				
				org.hibernate.Query query = session.createQuery(q);
				query.setDate("exp", dischargeDate);
				query.setInteger("memberId", memberId);
				query.setInteger("ccId", caseCategoryId);
				query.setDate("effectiveDate", admissionDate);
				
				
				if (query != null){
					List list = query.list();
					
					if (list != null){
						Iterator<MemberProduct> iterator = list.iterator();
						
						if (iterator.hasNext()){
							result = iterator.next();
							
							DaoSupportUtil.lazyInit(required, result);
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
	@Override
	public double getRemainingBenefit(Integer memberId, Integer caseCategoryId,
			Date currentDate) throws Exception {
		// TODO Auto-generated method stub
		double result = 0;
		
		
		
		try {
		
			Session session =  memberProductDao.getProductSession();
			
			if (session != null){
				String q = "SELECT p FROM MemberProduct p WHERE p.expireDate >= :exp AND p.memberId.memberId = :memberId " +
						" AND p.productId.caseCategory.caseCategoryId = :ccId" +
						" AND p.registerDate <= :effectiveDate AND p.deletedStatus = 0 AND " +
						"( p.memberProductStatus.statusId = " + SubscriptionStatus.ACTIVE
						 +" OR p.memberProductStatus.statusId = " + SubscriptionStatus.UPGRADED + " OR p.memberProductStatus.statusId = " + SubscriptionStatus.REVISION + ")";
				
				org.hibernate.Query query = session.createQuery(q);
				query.setDate("exp", currentDate);
				query.setInteger("memberId", memberId);
				query.setInteger("ccId", caseCategoryId);
				query.setDate("effectiveDate", currentDate);
				
				if (query != null){
					List list = query.list();
					
					if (list != null && list.size() > 0){
						Iterator<MemberProduct> iterator = list.iterator();
						
						if (iterator.hasNext()){
							MemberProduct resultProduct = iterator.next();
							
							if (resultProduct != null){
								if (resultProduct.getSecondaryCoverageId() != null && resultProduct.getSecondaryCoverageId().getActualBenefitLimit() != null){
									result = resultProduct.getSecondaryCoverageId().getActualBenefitLimit();
								}
								else if (resultProduct.getFamilyProductId() != null && resultProduct.getFamilyProductId().getActualBenefitLimit() != null){
									result = resultProduct.getFamilyProductId().getActualBenefitLimit();
								}
								else if (resultProduct.getActualBenefitLimit() != null){
									result = resultProduct.getActualBenefitLimit();
								}
							}
						}
					}
					else {
						// check divert
					}
				}				
			}		
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		
		return result;
	}
	@Override
	public void updateMemberExpiredProduct(ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub
		Session session =  memberProductDao.getProductSession();
		
		if (session != null){
			String q = "UPDATE MemberProduct mp SET mp.memberProductStatus.statusId = :newStatus " +
					"WHERE mp.expireDate < :current AND mp.memberProductStatus.statusId = :stat AND mp.deletedStatus = 0";
			
			org.hibernate.Query query = session.createQuery(q);
			query.setInteger("newStatus", SubscriptionStatus.EXPIRED);
			query.setInteger("stat", SubscriptionStatus.ACTIVE);
			
			query.setDate("current", new java.sql.Date(System.currentTimeMillis()));
			
			query.executeUpdate();
			
		}
	}

	public void updateInconsistentFamilyPlan(ActionUser user){
		try {
			
			Session session =  memberProductDao.getProductSession();
			
			if (session != null){
				String q = "SELECT p FROM MemberProduct p WHERE p.familyProductId IS null AND ( p.productId.productType.productTypeId = :family " +
						" OR p.productId.productType.productTypeId = :groupFamily ) and p.deletedStatus = 0" +
						" and (p.memberProductStatus = :active or p.memberProductStatus = :upgrade)";
				
				org.hibernate.Query query = session.createQuery(q);
				query.setInteger("family", ProductType.FAMILY);
				query.setInteger("groupFamily", ProductType.GROUP_FAMILY);
				query.setInteger("active", SubscriptionStatus.ACTIVE);
				query.setInteger("upgrade", SubscriptionStatus.UPGRADED);
				
				if (query != null){
					List list = query.list();
					
					if (list != null){
						Iterator<MemberProduct> iterator = list.iterator();
						
						MemberProduct result = null;
						
						while (iterator.hasNext()){
							result = iterator.next();
							System.out.println("MP ID = " + result.getMemberProductId() + " MEMBER ID = " + result.getMemberId().getMemberId() +
									" --> INCONSISTENT FAMILY PLAN");
							
							if (result != null){
								if (result.getMemberId().getParentMember() != null){
									if (result.getMemberId().getMemberId().intValue() == result.getMemberId().getParentMember().getMemberId().intValue()){
										result.setFamilyProductId(result);
										
										memberProductDao.update(result);
									}
									else {
										Integer parentId = result.getMemberId().getParentMember().getMemberId();
										
										String[] eqParam = {"deletedStatus","memberId.memberId","productId.productId"};
										Object[] eqValue = {0,parentId,result.getProductId().getProductId()};
										
										Collection<MemberProduct> familyProductList = search(null,null,eqParam,eqValue,0,1);
										
										if (familyProductList != null && familyProductList.size() > 0){
											Iterator<MemberProduct> iteratorProduct = familyProductList.iterator();
											
											if (iteratorProduct.hasNext()){
												MemberProduct familyProduct = iteratorProduct.next();
												
												if (familyProduct != null){
													result.setFamilyProductId(familyProduct);
													
													memberProductDao.update(result);
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
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
	}
	@Override
	public double getTotalFamilyLimitUsage(Integer memberProductId) throws Exception {
		// TODO Auto-generated method stub
		
		double result = 0.0;
		try {
			
			MemberProduct familyProduct = get(memberProductId);
			
			Session session =  memberProductDao.getProductSession();
			
			if (session != null){
				String q = "SELECT sum(p.benefitUsage) FROM MemberProduct p WHERE p.familyProductId.memberProductId = :parentId "
						+ "AND ( p.productId.productType.productTypeId = :family " +
						" OR p.productId.productType.productTypeId = :groupFamily )";
				
				org.hibernate.Query query = session.createQuery(q);
				query.setInteger("parentId", memberProductId);
				query.setInteger("family", ProductType.FAMILY);
				query.setInteger("groupFamily", ProductType.GROUP_FAMILY);
				
				if (query != null){
					List list = query.list();
					
					if (list != null){
						Iterator<Object> iterator = list.iterator();
						
						Object tmp = null;
						
						if (iterator.hasNext()){
							tmp = iterator.next();
							
							if (tmp instanceof Double){
								result = (Double) tmp;
							}
						}
					}
				}				
			}		
			
			if (familyProduct != null && familyProduct.getFamilyProductId() == null){
				double parentUsage = familyProduct.getBenefitUsage() == null ? 0.0 : familyProduct.getBenefitUsage();
				result = result + parentUsage;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	@Override
	public boolean updateProductGracePeriode() throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		
		Session session = memberProductDao.getProductSession();
		
		String q = "SELECT mp FROM MemberProduct mp WHERE mp.gracePeriodeDate is NULL ";
		Query query = session.createQuery(q);
		query.setMaxResults(500);
		
		List<MemberProduct> list = query.list();
		
		if (list != null && list.size() > 0){
			
			
			
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				MemberProduct memberProduct = (MemberProduct) iterator.next();
			
				if (memberProduct != null){
					Policy policy = policyService.get(memberProduct.getCurrentPolicyId().getPolicyId());
					
					if (policy != null){
						if (policy.getReimburseMaxReceiveDay() != null){
							DateTime dateTime = new DateTime(memberProduct.getExpireDate().getTime());
							
							DateTime gracePeriodeTime = dateTime.plusDays(policy.getReimburseMaxReceiveDay());
							
							memberProduct.setGracePeriodeDate(new java.sql.Date(gracePeriodeTime.getMillis()));
							
							
							memberProductDao.update(memberProduct);
						}
					}
				}
				
			}
			
			result = true;
		}
		
		return result;
	}
	@Override
	public boolean updateProductPolicy() throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		
		Session session = memberProductDao.getProductSession();
		String q = "SELECT mp.memberProductId FROM MemberProduct mp WHERE mp.currentPolicyId is NULL";
		Query query = session.createQuery(q);
		query.setMaxResults(500);
		
		List<Object> list = query.list();
		String[] required = {"MemberProduct.MemberId"};
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object id = (Object) iterator.next();
		
			if (id instanceof Integer){
				Integer memberProductId = (Integer) id;
				
				MemberProduct memberProduct = get(memberProductId,required);
				if (memberProduct != null){
					Member member = memberProduct.getMemberId();
					
					if(member != null && member.getCurrentPolicyId() != null){
						memberProduct.setCurrentPolicyId(member.getCurrentPolicyId());
					}
					
				}
				
				memberProductDao.update(memberProduct);
			}
			
		}
		
		return result;
	}
	


// class+ 

// class- 

}
