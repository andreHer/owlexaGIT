
package com.ametis.cms.service.impl;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.dao.UserDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ActivityLog;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.StringUtil;
import com.ametis.cms.util.dao.DaoSupportUtil;



// imports+ 

// imports- 


/**
 * User is a servlet controller for user Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class UserServiceImpl implements UserService

// extends+ 

// extends- 

{
	
	private UserDao  userDao;
	private ActivityLogService activityLogService;
	
	

	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}
	public void setUserDao (UserDao object){
		this.userDao = object;
	}
	public UserDao getUserDao (){
		return this.userDao;
	}
	/*
	* Method create (User object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public User create (User object,ActionUser actionUser) throws Exception {
			
			
//			

			// ----

			
			
			object.setDeletedStatus(Integer.valueOf(0));
			SubscriptionStatus status = new SubscriptionStatus();
			status.setStatusId(SubscriptionStatus.ACTIVE);
			
			
			
			if (object != null){
				String password = object.getPassword();				
				object.setPassword(StringUtil.hash(password));
				object.setStatus(status);
			}
			if (actionUser != null){
				
				User user = actionUser.getUser();						
			if (user != null){
				object.setCreatedBy(user.getUsername());
			}
			}
			object.setCreatedTime(new java.sql.Timestamp (System.currentTimeMillis()));
			

			User result = userDao.create (object);
			return result;
		}
	/*
	* Method update (User object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public User update (User object,ActionUser actionUser) throws Exception{
		
		
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
		
		
 		userDao.update (object);
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
	public User trash (java.io.Serializable pkey) throws Exception {
		User object = userDao.get (pkey);
		userDao.delete (object);
		return object;
	}

	/*
	* Method delete (User object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public User delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception{
		User object = userDao.get (pkey);


		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		
		
		
		object.setDeletedStatus(new Integer(1));

		


		
if (actionUser != null){
			
			User user = actionUser.getUser();		
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
}
		
		userDao.update (object);
		return object;
	}


	/*
	* Method delete (User object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public User delete (User object,ActionUser actionUser) throws Exception{
		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		

		
		
		object.setDeletedStatus(new Integer(1));

		

		
if (actionUser != null){
			
			User user = actionUser.getUser();			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
}
		
		userDao.update (object);
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
	public User get (java.io.Serializable pkey) throws Exception{
		User object = null;
		object = userDao.get(pkey);
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

	public User get (java.io.Serializable pkey, String[] required) throws Exception{
	    User object = null;
	    object = userDao.get(pkey);
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

		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = userDao.getCriteria();
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
		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = userDao.getCriteria();
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
		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = userDao.getCriteria();
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
		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = userDao.getCriteria();
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

		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = userDao.getCriteria();
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

		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
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

		Criteria c = userDao.getCriteria();
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

		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
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

		Criteria c = userDao.getCriteria();
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

		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
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

		Criteria c = userDao.getCriteria();
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

		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
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

		Criteria c = userDao.getCriteria();
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

		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
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

		Criteria c = userDao.getCriteria();
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

		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
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

		Criteria c = userDao.getCriteria();
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

		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = userDao.getCriteria();
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

		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = userDao.getCriteria();
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

		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = userDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = userDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = userDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public User searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		User obj = (User) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public User searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = userDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		User obj = (User) c.uniqueResult();
		return obj;
	}
	
	public User searchUnique (String[] eqColumns, Object[] eqParams, int index, int offset)
			throws Exception{
			Criteria c = userDao.getCriteria();
			DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
			DaoSupportUtil.setLimit(index, offset, c);
			User obj = (User) c.uniqueResult();
			return obj;
		}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = userDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = userDao.getDetachedCriteria();
		return dc;
	}
	public User getUser (String username) throws Exception {
		User result = null;
		
		if (username != null){
			Criteria c = userDao.getCriteria();
			String[] exQ = {"username"};
			String[] exP = {username};
			
			DaoSupportUtil.setEqParam(exQ,exP,c);
			
			List collection = c.list();
			
			if (collection != null){
				Iterator iterator = collection.iterator();
				
				if (iterator != null && iterator.hasNext()){
					result = (User) iterator.next();
				}
				
			}
			
		}
		
		return result;
	}
	
	public User getUserOnlyOne (String username, String[] eqColumns, Object[] eqParams,String[] required) throws Exception {
		User result = null;
		
		if (username != null){
			Criteria c = userDao.getCriteria();
//			String[] exQ = {"username", "deletedStatus"};
//			String[] exP = {username, Integer.valueOf(0).toString()};
			
			DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
			DaoSupportUtil.setLimit(0, 1, c);
			
			
			List collection = c.list();
			
			if (collection != null){
				Iterator iterator = collection.iterator();
				
				if (iterator != null && iterator.hasNext()){
					result = (User) iterator.next();
					
					DaoSupportUtil.lazyInit(required,result);
				}
				
			}
			
		}
		
		return result;
	}
	
	public boolean changePassword(String username, String password, String newPassword, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		User object = getUser(username);
		password = StringUtil.hash(password);
		
		if (object != null){
			if (password != null && password.equals(object.getPassword())){
				newPassword = StringUtil.hash(newPassword);
				
				object.setPassword(newPassword);
				
				update(object,actionUser);
				
				result = true;
			}
		}
		
		return result;
	}
	public boolean block(String username, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		User object = getUser(username);
		
		if (object != null){
			SubscriptionStatus status = new SubscriptionStatus();
			status.setStatusId(Integer.valueOf(User.BLOCKED));
			
			object.setStatus(status);
			
			update (object, actionUser);
			result = true;
		}
		
		return result;
	}
	public boolean activate(String username, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		
		User object = getUser(username);
		
		if (object != null){
			SubscriptionStatus status = new SubscriptionStatus();
			status.setStatusId(Integer.valueOf(User.ACTIVE));
			
			object.setStatus(status);
			
			update (object, actionUser);
			result = true;
		}
		
		return result;
	}
	
	public boolean login(String username, String password, String session, String ipAddress) throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		
		User object = getUser(username);
		
		String hashedPassword = StringUtil.hash(password);
		
		com.ametis.cms.datamodel.ActivityLog log = new ActivityLog();
		log.setAction("LOGIN");
		log.setActionUrl("user?navigation=login");
		log.setDescription("Failed");
		log.setUsername(username);
		log.setIpAddress(ipAddress);
		log.setSessionId(session);
		log.setActivityLogTime(new Timestamp(System.currentTimeMillis()));
		
		if (object != null && hashedPassword != null){
			
			
			
			if (hashedPassword.equals(object.getPassword()) && object.getStatus().getStatusId() == SubscriptionStatus.ACTIVE){
				
				
				result = true;
				
				object.setLastLoginIp(ipAddress);
				object.setLastLoginSession(session);
				object.setLastLoginTime(new java.sql.Timestamp(System.currentTimeMillis()));
				
				
				
				log.setDescription("Success");
			//	activityLogService.create(log, object);
				
				activityLogService.create(log);
				userDao.update(object);
			}
			else {
				activityLogService.create(log);
				
			}
			
			
		}
		
	
		
		return result;
	}
	
	public User loginMobile(String username, String hashedPassword, String session, String ipAddress) throws Exception {
		
		User object = getUser(username);		
		
		com.ametis.cms.datamodel.ActivityLog log = new ActivityLog();
		log.setAction("LOGIN");
		log.setActionUrl("user?navigation=login");
		log.setDescription("Failed");
		log.setUsername(username);
		log.setIpAddress(ipAddress);
		log.setSessionId(session);
		log.setActivityLogTime(new Timestamp(System.currentTimeMillis()));
		
		if (object != null && hashedPassword != null){	
			System.out.println("HASHED PASS = |" + hashedPassword +  "| VS |" + object.getPassword()+"|" + " Status = " + object.getStatus().getStatusId());
			
			
			if (hashedPassword.equals(object.getPassword()) && object.getStatus().getStatusId() == SubscriptionStatus.ACTIVE){	
				object.setLastLoginIp(ipAddress);
				object.setLastLoginSession(session);
				object.setLastLoginTime(new java.sql.Timestamp(System.currentTimeMillis()));				
				log.setDescription("Success");
			
				activityLogService.create(log);
				userDao.update(object);
			}
			else {
				object = null;
				activityLogService.create(log);				
			}
		}
		
		return object;
	}

	public boolean loginMobileApplication(String username, String password, String session, String ipAddress) throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		
		User object = getUser(username);
		
		String hashedPassword = StringUtil.hash(password);
		
		com.ametis.cms.datamodel.ActivityLog log = new ActivityLog();
		log.setAction("LOGIN MOBILE APPLICATION");
		log.setActionUrl("user?navigation=login");
		log.setDescription("Failed");
		log.setUsername(username);
		log.setIpAddress(ipAddress);
		log.setSessionId(session);
		log.setActivityLogTime(new Timestamp(System.currentTimeMillis()));
		
		if (object != null && hashedPassword != null){
			
			
			
			if (hashedPassword.equals(object.getPassword()) && object.getStatus().getStatusId() == SubscriptionStatus.ACTIVE){
				
				
				result = true;
				
				object.setLastLoginIp(ipAddress);
				object.setLastLoginSession(session);
				object.setLastLoginTime(new java.sql.Timestamp(System.currentTimeMillis()));
				
				
				
				log.setDescription("Success");
			//	activityLogService.create(log, object);
				
				activityLogService.create(log);
				userDao.update(object);
			}
			else {
				activityLogService.create(log);
				
			}
			
			
		}
		
	
		
		return result;
	}
	
	//Add by aju on 20151012, to support ge ce em
	public boolean loginMobileApplicationGcmSupport(String username, String password, String session, String ipAddress, String mobileDeviceId) throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		
		User object = getUser(username);
		
		String hashedPassword = StringUtil.hash(password);
		
		com.ametis.cms.datamodel.ActivityLog log = new ActivityLog();
		log.setAction("LOGIN MOBILE APPLICATION");
		log.setActionUrl("user?navigation=login");
		log.setDescription("Failed");
		log.setUsername(username);
		log.setIpAddress(ipAddress);
		log.setSessionId(session);
		log.setActivityLogTime(new Timestamp(System.currentTimeMillis()));
		
		if (object != null && hashedPassword != null){
			
			
			
			if (hashedPassword.equals(object.getPassword()) && object.getStatus().getStatusId() == SubscriptionStatus.ACTIVE){
				
				
				result = true;
				
				object.setLastLoginIp(ipAddress);
				object.setLastLoginSession(session);
				object.setLastLoginTime(new java.sql.Timestamp(System.currentTimeMillis()));
				object.setMobileDeviceId(mobileDeviceId);
				
				
				log.setDescription("Success");
			//	activityLogService.create(log, object);
				
				activityLogService.create(log);
				userDao.update(object);
			}
			else {
				activityLogService.create(log);
				
			}
			
			
		}
		
	
		
		return result;
	}
	
	public boolean logout(String username, String session) throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = true;
		
		ActivityLog log = new ActivityLog();
		log.setUsername(username);
		log.setSessionId(session);
		log.setDescription("success");
		log.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
		log.setAction("LOGOUT");
		log.setActionUrl("user?navigation=logout");
		
		activityLogService.create(log);
		return result;
	}
	public boolean block(Integer userId, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
	boolean result = false;
		
		User object = get(userId);
		
		if (object != null){
			SubscriptionStatus status = new SubscriptionStatus();
			status.setStatusId(Integer.valueOf(User.BLOCKED));
			
			object.setStatus(status);
			
			update (object, actionUser);
			result = true;
		}
		
		return result;
	}
	public boolean activate(Integer userId, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
boolean result = false;
		
		User object = get(userId);
		
		if (object != null){
			SubscriptionStatus status = new SubscriptionStatus();
			status.setStatusId(Integer.valueOf(User.ACTIVE));
			
			object.setStatus(status);
			
			update (object, actionUser);
			result = true;
		}
		
		return result;
	}
	
	public boolean changePassword(Integer userId, String password, String newPassword, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		User object = get(userId);
		System.out.println ("PASSWORD : " + password);
		System.out.println ("NEW PASSWORD : " + newPassword);
		
		password = StringUtil.hash(password);
		System.out.println ("HASHED PASSWORD : " + password);
		
		if (object != null){
			if (password != null && password.equals(object.getPassword())){
				
				newPassword = StringUtil.hash(newPassword);
				
				object.setPassword(newPassword);
				
				update(object,actionUser);
				
				result = true;
			}
		}
		
		return result;
	}


	public boolean changePassword(Integer userId, String newPassword, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		User object = get(userId);

		
		if (object != null){
			
				newPassword = StringUtil.hash(newPassword);
				
				object.setPassword(newPassword);
				
				update(object,actionUser);
				
				result = true;
			
		}
		
		return result;
	}
	@Override
	public Collection<User> getUserByRole(Integer roleId) {
		// TODO Auto-generated method stub
		Collection<User> result = null;
		
		try {
			String[] eqParam = {"deletedStatus","roleId.roleId"};
			Object[] eqValue = {0,roleId};
			
			int total = getTotal(null,null,eqParam,eqValue);
			
			result = search(null,null,eqParam,eqValue,0,total);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	

// class+ 

// class- 

}
