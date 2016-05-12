
package com.ametis.cms.service.impl;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.dao.ClaimTypeDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.service.ClaimTypeService;
import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 


/**
 * ClaimType is a servlet controller for claim_type Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class ClaimTypeServiceImpl implements ClaimTypeService

// extends+ 

// extends- 

{
	
	private ClaimTypeDao  claimTypeDao;

	public void setClaimTypeDao (ClaimTypeDao object){
		this.claimTypeDao = object;
	}
	public ClaimTypeDao getClaimTypeDao (){
		return this.claimTypeDao;
	}
	/*
	* Method create (ClaimType object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public ClaimType create (ClaimType object,ActionUser actionUser) throws Exception {
			
			
//			object.setCreatedDate (new java.sql.Date (System.currentTimeMillis()));

			// ----

			
			
			
			
			
			

			ClaimType result = claimTypeDao.create (object);
			return result;
		}
	/*
	* Method update (ClaimType object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public ClaimType update (ClaimType object,ActionUser actionUser) throws Exception{
		
		
//		object.setUpdatedDate (new java.sql.Date (System.currentTimeMillis()));

				
				
		
					
				
				
		
					
		

		
		
				
				
				
		
 		claimTypeDao.update (object);
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
	public ClaimType trash (java.io.Serializable pkey) throws Exception {
		ClaimType object = claimTypeDao.get (pkey);
		claimTypeDao.delete (object);
		return object;
	}

	/*
	* Method delete (ClaimType object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public ClaimType delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception{
		ClaimType object = claimTypeDao.get (pkey);


		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		
		
		
		


		
		
		
		claimTypeDao.update (object);
		return object;
	}


	/*
	* Method delete (ClaimType object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public ClaimType delete (ClaimType object,ActionUser actionUser) throws Exception{
		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		

		
		
		

		
		
		
		claimTypeDao.update (object);
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
	public ClaimType get (java.io.Serializable pkey) throws Exception{
		ClaimType object = null;
		object = claimTypeDao.get(pkey);
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

	public ClaimType get (java.io.Serializable pkey, String[] required) throws Exception{
	    ClaimType object = null;
	    object = claimTypeDao.get(pkey);
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

		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClaimType element = (ClaimType) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = claimTypeDao.getCriteria();
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
		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClaimType element = (ClaimType) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = claimTypeDao.getCriteria();
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
		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClaimType element = (ClaimType) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = claimTypeDao.getCriteria();
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
		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClaimType element = (ClaimType) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = claimTypeDao.getCriteria();
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

		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClaimType element = (ClaimType) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = claimTypeDao.getCriteria();
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

		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClaimType element = (ClaimType) iter.next();
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

		Criteria c = claimTypeDao.getCriteria();
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

		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClaimType element = (ClaimType) iter.next();
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

		Criteria c = claimTypeDao.getCriteria();
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

		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClaimType element = (ClaimType) iter.next();
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

		Criteria c = claimTypeDao.getCriteria();
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

		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClaimType element = (ClaimType) iter.next();
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

		Criteria c = claimTypeDao.getCriteria();
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

		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClaimType element = (ClaimType) iter.next();
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

		Criteria c = claimTypeDao.getCriteria();
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

		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClaimType element = (ClaimType) iter.next();
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

		Criteria c = claimTypeDao.getCriteria();
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

		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClaimType element = (ClaimType) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = claimTypeDao.getCriteria();
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

		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = claimTypeDao.getCriteria();
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

		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = claimTypeDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = claimTypeDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			ClaimType element = (ClaimType) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = claimTypeDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public ClaimType searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		ClaimType obj = (ClaimType) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public ClaimType searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = claimTypeDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		ClaimType obj = (ClaimType) c.uniqueResult();
		return obj;
	}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = claimTypeDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = claimTypeDao.getDetachedCriteria();
		return dc;
	}




// class+ 

// class- 

}
