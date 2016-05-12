
package com.ametis.cms.service.impl;


import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.dao.ItemDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 


/**
 * Item is a servlet controller for item Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class ItemServiceImpl implements ItemService

// extends+ 

// extends- 

{
	
	private ItemDao  itemDao;
	

	private ActivityLogService activityLogService;
	
	
	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}
	public void setItemDao (ItemDao object){
		this.itemDao = object;
	}
	public ItemDao getItemDao (){
		return this.itemDao;
	}
	/*
	* Method create (Item object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public Item create (Item object,ActionUser actionUser) throws Exception {
			
			
//			object.setCreatedDate (new java.sql.Date (System.currentTimeMillis()));

			// ----

			
			
			object.setDeletedStatus(Integer.valueOf(0));

			
			
			if (actionUser != null){
				User user = actionUser.getUser();
			if (user != null){
				object.setCreatedBy(user.getUsername());
			}
			}

			

			Item result = itemDao.create (object);
			return result;
		}
	/*
	* Method update (Item object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public Item update (Item object,ActionUser actionUser) throws Exception{
		
		
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
		
		
 		itemDao.update (object);
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
	public Item trash (java.io.Serializable pkey) throws Exception {
		Item object = itemDao.get (pkey);
		itemDao.delete (object);
		return object;
	}

	/*
	* Method delete (Item object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public Item delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception{
		Item object = itemDao.get (pkey);


		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		
		
		
		object.setDeletedStatus(new Integer(1));

		
		if (actionUser != null){
			User user = actionUser.getUser();

		
			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
		}
		
		itemDao.update (object);
		return object;
	}


	/*
	* Method delete (Item object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public Item delete (Item object,ActionUser actionUser) throws Exception{
		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		

		
		
		object.setDeletedStatus(new Integer(1));

		
		if (actionUser != null){
			User user = actionUser.getUser();
		
			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}

		}
		itemDao.update (object);
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
	public Item get (java.io.Serializable pkey) throws Exception{
		Item object = null;
		object = itemDao.get(pkey);
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

	public Item get (java.io.Serializable pkey, String[] required) throws Exception{
	    Item object = null;
	    object = itemDao.get(pkey);
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

		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Item element = (Item) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = itemDao.getCriteria();
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
		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Item element = (Item) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = itemDao.getCriteria();
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
		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Item element = (Item) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = itemDao.getCriteria();
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
		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Item element = (Item) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = itemDao.getCriteria();
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

		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Item element = (Item) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = itemDao.getCriteria();
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

		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Item element = (Item) iter.next();
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

		Criteria c = itemDao.getCriteria();
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

		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Item element = (Item) iter.next();
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

		Criteria c = itemDao.getCriteria();
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

		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Item element = (Item) iter.next();
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

		Criteria c = itemDao.getCriteria();
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

		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Item element = (Item) iter.next();
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

		Criteria c = itemDao.getCriteria();
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

		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Item element = (Item) iter.next();
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

		Criteria c = itemDao.getCriteria();
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

		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Item element = (Item) iter.next();
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

		Criteria c = itemDao.getCriteria();
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

		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Item element = (Item) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = itemDao.getCriteria();
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

		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = itemDao.getCriteria();
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

		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = itemDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = itemDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Item element = (Item) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = itemDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public Item searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		Item obj = (Item) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public Item searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = itemDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		Item obj = (Item) c.uniqueResult();
		return obj;
	}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = itemDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = itemDao.getDetachedCriteria();
		return dc;
	}
	public Collection<Item> getClaimableItem(Integer memberId,
			Integer caseCategoryId) throws Exception {
		// TODO Auto-generated method stub
		
		Session session = itemDao.getItemSession();
		
		Collection<Item> result = null;
		
		if (session != null){
			result = new Vector<Item>();
		    
			//Catatan by FVO 20151103: Member Benefit Status hanya mengambil status yang ACTIVE
			//Bermasalah bila Reimbursement, Item yang lama tidak akan muncul
			//Kenapa tidak pakai tanggal di Member Benefit
			//Concept REIMBURSEMENT, Item yang lama dan yang baru harus muncul. Yang lama dengan benefit yang lama, yang baru dengan benefit yang baru.
			String query = "SELECT DISTINCT i.itemName, i.itemCode, i.itemId FROM Item i, " +
					"MemberBenefit mb, ItemCategory ic WHERE mb.caseCategoryId.caseCategoryId = :cc AND" +
					" i.itemCategoryId.itemCategoryId = mb.itemCategoryId.itemCategoryId AND mb.memberBenefitStatus = 1 AND" +
					" ic.itemCategoryEDCCode = i.itemEDCCode AND i.deletedStatus = 0 AND" +
					" mb.memberId.memberId = :memberId AND i.itemEDCCode is not null ORDER BY i.itemCode ASC";
			
			Query itemQuery = session.createQuery(query);
			itemQuery.setInteger("cc", caseCategoryId);
			itemQuery.setInteger("memberId", memberId);
		
			Collection<Object[]> tmp = itemQuery.list();
			
			if (tmp != null){
				Iterator<Object[]> tmpIterator = tmp.iterator();
				
				while (tmpIterator.hasNext()){
					Object[] res = tmpIterator.next();
					
					if (res != null){

						Item item = new Item();
						if (res[0] != null){
							item.setItemName(res[0].toString());
						}
						else {
							item.setItemName("");
						}
						
						if (res[1] != null){
							item.setItemCode(res[1].toString());
						}
						else {
							item.setItemCode("");
						}
						if (res[2] != null){
							item.setItemId((Integer)res[2]);
						}
						
						
						result.add(item);
					}
				}
			}
		}
		
		return result;
	}
	
	public Collection<Item> getClaimableItem(Integer memberId,
			Integer caseCategoryId, Date admissionDate,
			Date dischargeDate) throws Exception {
		// TODO Auto-generated method stub
		
		Session session = itemDao.getItemSession();
		
		Collection<Item> result = null;
		
		if (session != null){
			result = new Vector<Item>();
		    
			//Catatan by FVO 20151103: Member Benefit Status hanya mengambil status yang ACTIVE
			//Bermasalah bila Reimbursement, Item yang lama tidak akan muncul
			//Kenapa tidak pakai tanggal di Member Benefit
			//Concept REIMBURSEMENT, Item yang lama dan yang baru harus muncul. Yang lama dengan benefit yang lama, yang baru dengan benefit yang baru.
			String query = "SELECT DISTINCT i.itemName, i.itemCode, i.itemId FROM Item i, " +
					"MemberBenefit mb, ItemCategory ic WHERE mb.caseCategoryId.caseCategoryId = :cc AND " +
					" i.itemCategoryId.itemCategoryId = mb.itemCategoryId.itemCategoryId AND" +
					" ic.itemCategoryEDCCode = i.itemEDCCode AND i.deletedStatus = 0 AND " +
					" mb.effectiveDate <= :effectiveDate AND mb.deletedStatus = 0 AND mb.expireDate >= :exp AND" +
					" mb.memberId.memberId = :memberId AND i.itemEDCCode is not null ORDER BY i.itemCode";
			
			//ini concept yang baru tidak kepake lagi
			/*String query = "SELECT DISTINCT i.itemName, i.itemCode, i.itemId FROM Item i, " +
					"MemberBenefit mb, ItemCategory ic WHERE mb.caseCategoryId.caseCategoryId = :cc AND " +
					" i.itemCategoryId.itemCategoryId = mb.itemCategoryId.itemCategoryId AND" +
					" ic.itemCategoryEDCCode = i.itemEDCCode AND i.deletedStatus = 0 AND " +
					" :effectiveDate BETWEEN mb.effectiveDate AND mb.expireDate-1 AND mb.deletedStatus = 0 AND " +
					" mb.memberId.memberId = :memberId AND i.itemEDCCode is not null ORDER BY i.itemCode";*/
			
			
			Query itemQuery = session.createQuery(query);
			itemQuery.setInteger("cc", caseCategoryId);
			itemQuery.setInteger("memberId", memberId);
			itemQuery.setDate("effectiveDate", admissionDate);
			itemQuery.setDate("exp", dischargeDate);
		
			Collection<Object[]> tmp = itemQuery.list();
			
			if (tmp != null){
				Iterator<Object[]> tmpIterator = tmp.iterator();
				
				while (tmpIterator.hasNext()){
					Object[] res = tmpIterator.next();
					
					if (res != null){

						Item item = new Item();
						if (res[0] != null){
							item.setItemName(res[0].toString());
						}
						else {
							item.setItemName("");
						}
						
						if (res[1] != null){
							item.setItemCode(res[1].toString());
						}
						else {
							item.setItemCode("");
						}
						if (res[2] != null){
							item.setItemId((Integer)res[2]);
						}
						
						
						result.add(item);
					}
				}
			}
		}
		
		return result;
	}
	
	public Item getItemByCode(String code) throws Exception {
		// TODO Auto-generated method stubCriteria c = itemDao.getCriteria();
		
		Item result = null;
		Criteria c = itemDao.getCriteria();
		
		DaoSupportUtil.setEqParam("itemCode",code,c);
		
		c.setMaxResults(1);
		
		
		List list = c.list();
		
		if (list != null && list.size() > 0){
			result = (Item)list.iterator().next();
		}
		
		return result;
	}
	
	public Item getItemByEdcCode(String code) throws Exception {
		// TODO Auto-generated method stub
		Item result = null;
		Criteria c = itemDao.getCriteria();
		
		DaoSupportUtil.setEqParam("itemEDCCode",code,c);
		
		c.setMaxResults(1);
		
		
		List list = c.list();
		
		if (list != null){
			Iterator<Item> iterator = list.iterator();
			
			if (iterator.hasNext()){
				result = (Item)list.iterator().next();
			}
		}
		
		return result;	
	}
	
	public Item getMemberItemByEdcCode(String code, Integer memberId)
			throws Exception {
		// TODO Auto-generated method stub
		
		Item result = null;
		try {
			Criteria c = itemDao.getCriteria();
			
			DaoSupportUtil.setEqParam("itemCode",code,c);
			DaoSupportUtil.setEqParam("itemCategoryId.itemCategoryCode", code, c);
			
			List<Item> list = c.list();
			
			if (list != null ){
				Iterator<Item> iterator = list.iterator();
				
				if (iterator.hasNext()){
					result = (Item) list.iterator().next();
				}
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Collection<Item> searchItem(String qText) throws Exception {
		Collection<Item> result = new Vector<Item>();
		
		try {
			Session session = itemDao.getItemSession();
			
			String query = "SELECT m FROM Item m WHERE (LOWER(m.itemName) LIKE :keyword OR LOWER(m.itemCode) LIKE :keyword) " +
					"AND m.deletedStatus = 0 ORDER BY m.itemName ASC LIMIT 15 OFFSET 0";
			
			
			Query q = session.createQuery(query);
			q.setString("keyword",  "%" + qText + "%");
			q.setMaxResults(10);
			
			result = q.list();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean updateItemBulk(Collection<Item> itemList, ActionUser user)
			throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		if (itemList != null){
			for (Iterator iterator = itemList.iterator(); iterator.hasNext();) {
				
				Item item = (Item) iterator.next();
				
				if (item != null){
				
					Item toBeUpdated = get(item.getItemId());
					
					if (toBeUpdated != null){
						
						toBeUpdated.setItemCode(item.getItemCode());
						toBeUpdated.setItemEDCCode(item.getItemEDCCode());
						toBeUpdated.setItemEDCName(item.getItemEDCName());
						
						update(toBeUpdated,user);
					}
				
				}
			}
			result = true;
		}
		
		return result;
	}
	@Override
	public Collection<Item> getClientInvoiceItemList() throws Exception {
		// TODO Auto-generated method stub
		Collection<Item> result = new Vector<Item>();
		
		String[] eqParam = {"itemCategoryId.isClientInvoiceItem","deletedStatus"};
		Object[] eqValue = {1,0};
		
		int total = getTotal(null,null,eqParam,eqValue);
		
		result = search(null,null,eqParam,eqValue,0,total);
		
		return result;
	}




// class+ 

// class- 

}
