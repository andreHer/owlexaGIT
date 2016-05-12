
package com.ametis.cms.service.impl;


import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.ametis.cms.dao.DiagnosisDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 


/**
 * Diagnosis is a servlet controller for diagnosis Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class DiagnosisServiceImpl implements DiagnosisService

// extends+ 

// extends- 

{
	
	private DiagnosisDao  diagnosisDao;
private ActivityLogService activityLogService;
	
	
	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}
	public void setDiagnosisDao (DiagnosisDao object){
		this.diagnosisDao = object;
	}
	public DiagnosisDao getDiagnosisDao (){
		return this.diagnosisDao;
	}
	/*
	* Method create (Diagnosis object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public Diagnosis create (Diagnosis object,ActionUser actionUser) throws Exception {
		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(Integer.valueOf(0));
		if (actionUser != null){
			User user = actionUser.getUser();				
			if (user != null){
				object.setCreatedBy(user.getUsername());
			}
		}
		Diagnosis result = diagnosisDao.create (object);
		return result;
	}
	/*
	* Method update (Diagnosis object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public Diagnosis update (Diagnosis object,ActionUser actionUser) throws Exception{
		object.setModifiedTime (new java.sql.Timestamp (System.currentTimeMillis()));
		if (actionUser != null){
			User user = actionUser.getUser();			
			if (user != null){
				object.setModifiedBy (user.getUsername());
			}
		}
		
 		diagnosisDao.update (object);
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
	public Diagnosis trash (java.io.Serializable pkey) throws Exception {
		Diagnosis object = diagnosisDao.get (pkey);
		diagnosisDao.delete (object);
		return object;
	}

	/*
	* Method delete (Diagnosis object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public Diagnosis delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception{
		Diagnosis object = diagnosisDao.get (pkey);
		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));		
		object.setDeletedStatus(new Integer(1));		

		if (actionUser != null){
			User user = actionUser.getUser();
		
			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
		}
		
		diagnosisDao.update (object);
		return object;
	}


	/*
	* Method delete (Diagnosis object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public Diagnosis delete (Diagnosis object,ActionUser actionUser) throws Exception{
	
		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(new Integer(1));

		if (actionUser != null){
			User user = actionUser.getUser();
			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
		}

		
		diagnosisDao.update (object);
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
	public Diagnosis get (java.io.Serializable pkey) throws Exception{
		Diagnosis object = null;
		object = diagnosisDao.get(pkey);
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

	public Diagnosis get (java.io.Serializable pkey, String[] required) throws Exception{
	    Diagnosis object = null;
	    object = diagnosisDao.get(pkey);
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

		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Diagnosis element = (Diagnosis) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end
	
	//--req
		public Collection searchOr (String[] likeColumns, Object[] likeParams,
				String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

			Criteria c = diagnosisDao.getCriteria();
			DaoSupportUtil.setLikeParamOr(likeColumns,likeParams,c);
			DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
			DaoSupportUtil.setLimit(index, offset, c);
			List list = c.list();
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Diagnosis element = (Diagnosis) iter.next();
	   			DaoSupportUtil.lazyInit(required,element);
			}

			return list;

		}

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = diagnosisDao.getCriteria();
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
		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Diagnosis element = (Diagnosis) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = diagnosisDao.getCriteria();
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
		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Diagnosis element = (Diagnosis) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	//--req
		public Collection searchOr (String[] likeColumns, Object[] likeParams,
				String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
				String[] required,
				int index, int offset) throws Exception{
			Criteria c = diagnosisDao.getCriteria();
			DaoSupportUtil.setLikeParamOr(likeColumns,likeParams,c);
			DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
			DaoSupportUtil.setLimit(index, offset, c);
			DaoSupportUtil.setOrderBy(asc,columnOrder,c);
			List list = c.list();
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Diagnosis element = (Diagnosis) iter.next();
	   			DaoSupportUtil.lazyInit(required,element);
			}

			return list;

		}
		// --req end

// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = diagnosisDao.getCriteria();
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
		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Diagnosis element = (Diagnosis) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = diagnosisDao.getCriteria();
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

		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Diagnosis element = (Diagnosis) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = diagnosisDao.getCriteria();
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

		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Diagnosis element = (Diagnosis) iter.next();
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

		Criteria c = diagnosisDao.getCriteria();
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

		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Diagnosis element = (Diagnosis) iter.next();
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

		Criteria c = diagnosisDao.getCriteria();
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

		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Diagnosis element = (Diagnosis) iter.next();
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

		Criteria c = diagnosisDao.getCriteria();
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

		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Diagnosis element = (Diagnosis) iter.next();
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

		Criteria c = diagnosisDao.getCriteria();
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

		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Diagnosis element = (Diagnosis) iter.next();
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

		Criteria c = diagnosisDao.getCriteria();
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

		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Diagnosis element = (Diagnosis) iter.next();
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

		Criteria c = diagnosisDao.getCriteria();
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

		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Diagnosis element = (Diagnosis) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	
	public int getTotalOr (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams) throws Exception{

			Criteria c = diagnosisDao.getCriteria();
			DaoSupportUtil.setLikeParamOr(likeColumns,likeParams,c);
			DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
			int total = DaoSupportUtil.getTotal(c);
			return total;

		}
	
	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams,
		String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2
		) throws Exception{

		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = diagnosisDao.getCriteria();
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

		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = diagnosisDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = diagnosisDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Diagnosis element = (Diagnosis) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = diagnosisDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public Diagnosis searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		Diagnosis obj = (Diagnosis) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public Diagnosis searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		Diagnosis obj = (Diagnosis) c.uniqueResult();
		return obj;
	}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = diagnosisDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = diagnosisDao.getDetachedCriteria();
		return dc;
	}
	public Collection<Diagnosis> searchDiagnosis(String queryLike, int index,
			int offset) throws Exception {
		// TODO Auto-generated method stub
		Collection<Diagnosis> result = new Vector<Diagnosis>();
		
		try {
			Criteria c = diagnosisDao.getCriteria();
			c.add(Restrictions.or(Restrictions.ilike("diagnosisCode",queryLike,MatchMode.ANYWHERE),
					Restrictions.ilike("description",queryLike,MatchMode.ANYWHERE)) );
			
			
			c.add(Property.forName("deletedStatus").eq(0));
			c.addOrder(Order.asc("diagnosisName"));
			
			c.setMaxResults(offset);
			c.setFirstResult(index*offset);
			
			
			result = c.list();
			
			
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	public int getTotalDiagnosis(String queryLike) throws Exception {
		// TODO Auto-generated method stub
		
		int result = 0;
		try {
			Session session = diagnosisDao.getDiagnosisSession();
			
			String sqlQuery = "SELECT count(diag.diagnosisCode) FROM " +
					"Diagnosis diag WHERE (diag.diagnosisCode LIKE :keyword OR diag.diagnosisName LIKE :keyword" +
					" OR diag.description LIKE :keyword ) AND diag.deletedStatus = 0 ";

			Query query = session.createQuery(sqlQuery);
			query.setString("keyword", "%"+queryLike+"%");
			
			Collection res = query.list();
			Iterator<Object> resultIterator = res.iterator();
			
			result = Integer.valueOf(resultIterator.next().toString());
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public Diagnosis getDiagnosisByCode(String diagnosisCode) throws Exception {
		// TODO Auto-generated method stub
		Diagnosis diagnosis = null;
		
		try {
			String param[] = {"diagnosisCode"};
			Object value[] = {diagnosisCode};
			
			int total = getTotal (null,null,param,value);
			Collection<Diagnosis> diagnosisCollection = search(null,null,param,value,0,total);
			
			if (diagnosisCollection != null){
				Iterator<Diagnosis> iterator = diagnosisCollection.iterator();
				
				if (iterator.hasNext()){
					diagnosis = iterator.next();
				}
			}
			if (diagnosis == null){
			
				String param1[] = {"alternateCode"};			
				
				total = getTotal (null,null,param,value);
				diagnosisCollection = search(null,null,param1,value,0,total);
				
				if (diagnosisCollection != null){
					Iterator<Diagnosis> iterator = diagnosisCollection.iterator();
					
					if (iterator.hasNext()){
						diagnosis = iterator.next();
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return diagnosis;
	}
	@Override
	public Diagnosis getDiagnosisByAlternateCode(String diagnosisCode)
			throws Exception {
		Diagnosis diagnosis = null;
		
		try {
			if (diagnosisCode != null){
				
				System.out.println ("BEFORE DIAGNOSIS TRIM = |"+diagnosisCode+"| AFTER TRIM |"+diagnosisCode.trim()+"|");
				String param[] = {"alternateCode"};
				Object value[] = {diagnosisCode.trim()};
				
				int total = getTotal (null,null,param,value);
				Collection<Diagnosis> diagnosisCollection = search(null,null,param,value,0,total);
				
				if (diagnosisCollection != null){
					Iterator<Diagnosis> iterator = diagnosisCollection.iterator();
					
					if (iterator.hasNext()){
						diagnosis = iterator.next();
					}
				}
				
				if (diagnosis == null){
					System.out.println("ICD 9 CODE IS NOT FOUND -- TRY TO FIND ICD 10 MAPPING");
					String param1[] = {"icd10AlternateCode"};
					
					total = getTotal (null,null,param1,value);
					diagnosisCollection = search(null,null,param1,value,0,total);
					
					if (diagnosisCollection != null){
						Iterator<Diagnosis> iterator = diagnosisCollection.iterator();
						
						if (iterator.hasNext()){
							diagnosis = iterator.next();
						}
					}
					
					
				}
				if (diagnosis != null){
					System.out.println("DIAGNOSIS MAPPING FOUND ! --> " + diagnosis.getDiagnosisName());
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return diagnosis;
	}
	
	public Collection<Diagnosis> getDiagnosisList(String[] eqColumns, Object[] eqParams) throws Exception{
		Criteria c = diagnosisDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		List list = c.list();
		return list;
}
	
	public Collection<Diagnosis> searchDiagnosisByDiagnosisCode(String[] eqColumns, String[] eqDiagnosis,  Object[] oDiagnosis, Object[] eqParams) throws Exception{
			Criteria c = diagnosisDao.getCriteria();
			DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
			DaoSupportUtil.setLikeParam(eqDiagnosis, oDiagnosis, c);
			List list = c.list();
			return list;
	}


	public Collection<Object[]> generatePotentialHealthProblemReport(Integer clientId, Date startDate, Date endDate) throws Exception{
   	 Collection<Object[]> result = null;
   	 
   	 Session session = diagnosisDao.getDiagnosisSession();
        if (session != null) {
       	 
       	 Query query = session.createQuery("SELECT " +
          		"c.diagnosisId.diagnosisGroupDescription, " +
          		"(SELECT COUNT(cl.claimId) FROM Claim cl INNER JOIN cl.diagnosisId WHERE cl.claimStatus != 4 AND cl.claimStatus != -1 AND cl.claimApprovedValue > 0 " +
          		"AND cl.diagnosisId.diagnosisGroupDescription = c.diagnosisId.diagnosisGroupDescription) AS totalClaim, " +
          		"(SELECT COUNT(cl.claimId) FROM Claim cl INNER JOIN cl.diagnosisId WHERE cl.deletedStatus = 0 AND cl.claimStatus !=4 AND cl.claimStatus !=-1 " +
          		"AND cl.diagnosisId.diagnosisGroupDescription = c.diagnosisId.diagnosisGroupDescription) AS totalClaimant " +
          		"FROM Claim c JOIN c.diagnosisId JOIN c.client WHERE c.deletedStatus = 0 AND c.client.clientId = :clientId " +
          		"AND (c.admissionDate BETWEEN :startDate AND :endDate)" +
          		" GROUP BY c.diagnosisId.diagnosisGroupDescription, c.diagnosisId.diagnosisGroupCode " +
          		"ORDER BY c.diagnosisId.diagnosisGroupDescription");

       	query.setInteger("clientId", clientId);
       	query.setDate("startDate", startDate);
       	query.setDate("endDate", endDate);
         //query.setInteger("claimStatus", Claim.CLAIM_PAID);
            //EXCESSPAYMENTTYPE KOSONG
           // Collection res = query.list();
            
            result = (Collection<Object[]>) query.list();
        }
   	 return result;
   }
	
	
	public Collection<Object[]> generatePotentialHealthProblemReport(Integer clientId, Integer memberGroupId, Date startDate, Date endDate) throws Exception{
	   	 Collection<Object[]> result = null;
	   	 
	   	 Session session = diagnosisDao.getDiagnosisSession();
	        if (session != null) {
	       	 
	       	 Query query = session.createQuery("SELECT " +
	          		"c.diagnosisId.diagnosisGroupDescription, " +
	          		"(SELECT COUNT(cl.claimId) FROM Claim cl INNER JOIN cl.diagnosisId WHERE cl.claimStatus != 4 AND cl.claimStatus != -1 AND cl.claimApprovedValue > 0 " +
	          		"AND cl.diagnosisId.diagnosisGroupDescription = c.diagnosisId.diagnosisGroupDescription) AS totalClaim, " +
	          		"(SELECT COUNT(cl.claimId) FROM Claim cl INNER JOIN cl.diagnosisId WHERE cl.deletedStatus = 0 AND cl.claimStatus !=4 AND cl.claimStatus !=-1 " +
	          		"AND cl.diagnosisId.diagnosisGroupDescription = c.diagnosisId.diagnosisGroupDescription) AS totalClaimant " +
	          		"FROM Claim c JOIN c.diagnosisId JOIN c.client WHERE c.deletedStatus = 0 AND c.client.clientId = :clientId AND c.memberGroupId = :memberGroupId " +
	          		"AND (c.admissionDate BETWEEN :startDate AND :endDate)" +
	          		" GROUP BY c.diagnosisId.diagnosisGroupDescription, c.diagnosisId.diagnosisGroupCode " +
	          		"ORDER BY c.diagnosisId.diagnosisGroupDescription");

	       	query.setInteger("clientId", clientId);
	       	query.setInteger("memberGroupId", memberGroupId);
	       	query.setDate("startDate", startDate);
	       	query.setDate("endDate", endDate);
	         //query.setInteger("claimStatus", Claim.CLAIM_PAID);
	            //EXCESSPAYMENTTYPE KOSONG
	           // Collection res = query.list();
	            
	            result = (Collection<Object[]>) query.list();
	        }
	   	 return result;
	   }



// class+ 

// class- 

}
