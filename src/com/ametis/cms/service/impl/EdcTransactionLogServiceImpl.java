
package com.ametis.cms.service.impl;


import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.service.EdcTransactionLogService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.EdcTransactionLog;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.dao.EdcTransactionLogDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;


import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 

/**
 * EdcTransactionLog is a servlet controller for edc_transaction_log Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class EdcTransactionLogServiceImpl implements EdcTransactionLogService

// extends+ 

// extends- 
{
	
	private MemberGroupService memberGroupService;
	private MemberService memberService;
	
	private EdcTransactionLogDao  edcTransactionLogDao;

	public void setEdcTransactionLogDao (EdcTransactionLogDao object){
		this.edcTransactionLogDao = object;
	}
	public EdcTransactionLogDao getEdcTransactionLogDao (){
		return this.edcTransactionLogDao;
	}
	
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}
	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}
	/*
	* Method create (EdcTransactionLog object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public EdcTransactionLog create (EdcTransactionLog object,ActionUser user) throws Exception {

		if (object != null){
			if (object.getCardNumber() != null && !object.getCardNumber().equalsIgnoreCase("")){
				Member member = memberService.getMemberByCardNumber(object.getCardNumber());
				if (member != null){
					object.setGroupName(member.getMemberGroupId().getGroupName());					
				}
			}
		}
		EdcTransactionLog result = edcTransactionLogDao.create (object);
		return result;
	}
	/*
	* Method update (EdcTransactionLog object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public EdcTransactionLog update (EdcTransactionLog object,ActionUser user) throws Exception{
		
		
//		object.setUpdatedDate (new java.sql.Date (System.currentTimeMillis()));

				
				
		
					
				
				
		
					
		

		
		
				
				
				
		
 		edcTransactionLogDao.update (object);
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
	public EdcTransactionLog trash (java.io.Serializable pkey) throws Exception {
		EdcTransactionLog object = edcTransactionLogDao.get (pkey);
		edcTransactionLogDao.delete (object);
		return object;
	}

	/*
	* Method delete (EdcTransactionLog object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public EdcTransactionLog delete (java.io.Serializable pkey,ActionUser user) throws Exception{
		EdcTransactionLog object = edcTransactionLogDao.get (pkey);


		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		
		
		
		


		
		
		
		edcTransactionLogDao.update (object);
		return object;
	}


	/*
	* Method delete (EdcTransactionLog object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public EdcTransactionLog delete (EdcTransactionLog object,ActionUser user) throws Exception{
		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		

		
		
		

		
		
		
		edcTransactionLogDao.update (object);
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
	public EdcTransactionLog get (java.io.Serializable pkey) throws Exception{
		EdcTransactionLog object = null;
		object = edcTransactionLogDao.get(pkey);
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

	public EdcTransactionLog get (java.io.Serializable pkey, String[] required) throws Exception{
	    EdcTransactionLog object = null;
	    object = edcTransactionLogDao.get(pkey);
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

		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTransactionLog element = (EdcTransactionLog) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = edcTransactionLogDao.getCriteria();
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
		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTransactionLog element = (EdcTransactionLog) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = edcTransactionLogDao.getCriteria();
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
		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTransactionLog element = (EdcTransactionLog) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = edcTransactionLogDao.getCriteria();
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
		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTransactionLog element = (EdcTransactionLog) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = edcTransactionLogDao.getCriteria();
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

		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTransactionLog element = (EdcTransactionLog) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = edcTransactionLogDao.getCriteria();
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

		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTransactionLog element = (EdcTransactionLog) iter.next();
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

		Criteria c = edcTransactionLogDao.getCriteria();
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

		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTransactionLog element = (EdcTransactionLog) iter.next();
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

		Criteria c = edcTransactionLogDao.getCriteria();
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

		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTransactionLog element = (EdcTransactionLog) iter.next();
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

		Criteria c = edcTransactionLogDao.getCriteria();
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

		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTransactionLog element = (EdcTransactionLog) iter.next();
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

		Criteria c = edcTransactionLogDao.getCriteria();
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

		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTransactionLog element = (EdcTransactionLog) iter.next();
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

		Criteria c = edcTransactionLogDao.getCriteria();
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

		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTransactionLog element = (EdcTransactionLog) iter.next();
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

		Criteria c = edcTransactionLogDao.getCriteria();
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

		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTransactionLog element = (EdcTransactionLog) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total
	@SuppressWarnings("unchecked")
	public Collection<Object[]> searchEdcTransactionLogYearly(String[] actionCode, Date date, int index, int offset) throws Exception{
		Collection<Object[]> result = null;
		Session session = edcTransactionLogDao.getCurrentSession();
		
        if (session != null) {

        List actCode = new ArrayList();
     	 	 for (int h = 0 ; h < actionCode.length ; h++  ){
     	 		 actCode.add(actionCode[h]);
     	 		 
     	 	 } 	
        	
	 	StringBuffer sb = new StringBuffer();
	 	sb.append("SELECT a.actionCode, count(a.activityTime) as total, to_char(a.activityTime,  'YYYY') as activityTime "
	 			 +" FROM EdcTransactionLog a " +
	 			 " where to_char(a.activityTime,  'YYYY') = to_char(cast(:date as date),  'YYYY') ");
	 	//Query query = session.createQuery("SELECT a.actionCode , count(a.activityTime) as total FROM EdcTransactionLog a group by a.actionCode order by a.actionCode asc" );
        //String sqlQuery = sb.toString();
	 	
	 	if(actCode.isEmpty() == false){
	 		sb.append("and a.actionCode in (:actionCode)" );
	 	}
	 	
	 	sb.append(" group by a.actionCode , to_char(a.activityTime,  'YYYY') " +
		 			 " order by a.actionCode asc, to_char(a.activityTime,  'YYYY') asc" );
	 	
        Query query = session.createQuery(sb.toString());
	 		 	
	 	query.setDate("date", date);
	 	if(actCode.isEmpty() == false){
		 	query.setParameterList("actionCode", actCode);
		 }
        query.setFirstResult(index);
        query.setMaxResults(offset);

        //result = query.list();
        result = (Collection<Object[]>) query.list();
        }

        return result;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Object[]> searchEdcTransactionLogMonthly(String[] actionCode, Date date, int index, int offset) throws Exception{
		Collection<Object[]> result = null;
		Session session = edcTransactionLogDao.getCurrentSession();
		
        if (session != null) {

        List actCode = new ArrayList();
      	 	 for (int h = 0 ; h < actionCode.length ; h++  ){
      	 		 actCode.add(actionCode[h]);
      	 		 
      	 	 }

	 	StringBuffer sb = new StringBuffer();
	 	sb.append("SELECT a.actionCode, count(a.activityTime) as total, to_char(a.activityTime,  'MON-YYYY') as activityTime "
	 			 +" FROM EdcTransactionLog a " +
	 			 " where to_char(a.activityTime, 'MON-YYYY') = to_char(cast(:date as date), 'MON-yyyy') ");
	 	
	 	if(actCode.isEmpty() == false){
	 		sb.append("and a.actionCode in (:actionCode)" );
	 	}
	 	
	 	sb.append("group by a.actionCode , to_char(a.activityTime,  'MON-YYYY') " +
		 			 " order by a.actionCode asc, to_char(a.activityTime,  'MON-YYYY') asc");
//	 	Query query = session.createQuery("SELECT a.actionCode, count(a.activityTime) as total, to_char(a.activityTime,  'MON-YYYY') as activityTime "
//		 			 +" FROM EdcTransactionLog a " +
//		 			 " where to_char(a.activityTime, 'MON-YYYY') = to_char(cast(:date as date), 'MON-yyyy') and a.actionCode in (:actionCode) " +
//		 			 " group by a.actionCode , to_char(a.activityTime,  'MON-YYYY') " +
//		 			 " order by a.actionCode asc, to_char(a.activityTime,  'MON-YYYY') asc" );
	 	//Query query = session.createQuery("SELECT a.actionCode , count(a.activityTime) as total FROM EdcTransactionLog a group by a.actionCode order by a.actionCode asc" );
        //String sqlQuery = sb.toString();
        Query query = session.createQuery(sb.toString());
        
	 	query.setDate("date", date);
	 	if(actCode.isEmpty() == false){
		 	query.setParameterList("actionCode", actCode);
		 }
        query.setFirstResult(index);
        query.setMaxResults(offset);

        //result = query.list();
        result = (Collection<Object[]>) query.list();
        }

        return result;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Object[]> searchEdcTransactionLogDaily(String[] actionCode, Date date, int index, int offset) throws Exception{
		Collection<Object[]> result = null;
		Session session = edcTransactionLogDao.getCurrentSession();
		
        if (session != null) {

        List actCode = new ArrayList();
   	 	 for (int h = 0 ; h < actionCode.length ; h++  ){
   	 		 actCode.add(actionCode[h]);
   	 		 
   	 	 }

 	 	System.err.println("actcode = " + actCode);
 	 	
	 	StringBuffer sb = new StringBuffer();
	 	sb.append("SELECT a.actionCode, count(a.activityTime) as total, to_char(a.activityTime,  'dd-mm-yyyy') as activityTime "
	 			 +" FROM EdcTransactionLog a " +
	 			 " where to_char(a.activityTime, 'dd-mm-yyyy') = to_char(cast(:date as date), 'dd-mm-yyyy') " 
	 			  );
	 	
	 	if(actCode.isEmpty() == false){
	 		sb.append(" and a.actionCode in (:actionCode)" );
	 	}
	 	
	 	sb.append(" group by a.actionCode , to_char(a.activityTime,  'dd-mm-yyyy')" +
	 			 	" order by a.actionCode asc, to_char(a.activityTime,  'dd-mm-yyyy') asc");
//	 	Query query = session.createQuery("SELECT a.actionCode, count(a.activityTime) as total, to_char(a.activityTime,  'dd-mm-yyyy') as activityTime "
//		 			 +" FROM EdcTransactionLog a " +
//		 			 " where to_char(a.activityTime, 'dd-mm-yyyy') = to_char(cast(:date as date), 'dd-mm-yyyy') and a.actionCode in (:actionCode) " +
//		 			 " group by a.actionCode , to_char(a.activityTime,  'dd-mm-yyyy')" +
//		 			 " order by a.actionCode asc, to_char(a.activityTime,  'dd-mm-yyyy') asc" );
//	 	//Query query = session.createQuery("SELECT a.actionCode , count(a.activityTime) as total FROM EdcTransactionLog a group by a.actionCode order by a.actionCode asc" );
        //String sqlQuery = sb.toString();
       // Query query = session.createQuery(sqlQuery);
	 	
	 	Query query = session.createQuery(sb.toString());
	 	query.setDate("date", date);
	 	
	 	if(actCode.isEmpty() == false){
	 	query.setParameterList("actionCode", actCode);
	 	}
	 	
	 	query.setFirstResult(index);
        query.setMaxResults(offset);

        //result = query.list();
        result = (Collection<Object[]>) query.list();
        }

        return result;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Object[]> searchEdcTransactionLogAllTheTime(String[] actionCode, int index, int offset) throws Exception{
		Collection<Object[]> result = null;
		Session session = edcTransactionLogDao.getCurrentSession();
		
        if (session != null) {

        List actCode = new ArrayList();
      	 	 for (int h = 0 ; h < actionCode.length ; h++  ){
      	 		 actCode.add(actionCode[h]);
      	 	 }	
        	
	 	StringBuffer sb = new StringBuffer();
	 	sb.append("SELECT a.actionCode , count(a.activityTime) as total FROM EdcTransactionLog a ");
	 	//Query query = session.createQuery("SELECT a.actionCode, to_char(a.activityTime,  'MON-YYYY') as activityTime, count(a.activityTime) as total "
		 //			 +" FROM EdcTransactionLog a group by a.actionCode , to_char(a.activityTime,  'MON-YYYY') order by a.actionCode asc, to_char(a.activityTime,  'MON-YYYY') asc" );
	 	
	 	if(actCode.isEmpty() == false){
	 		sb.append(" where a.actionCode in (:actionCode) " );
	 	}
	 	
	 	sb.append(" group by a.actionCode order by a.actionCode asc");
	 	
	 	Query query = session.createQuery( sb.toString());
	 	
	 	if(actCode.isEmpty() == false){
		 	query.setParameterList("actionCode", actCode);
		 }
	 	
        query.setFirstResult(index);
        query.setMaxResults(offset);

        //result = query.list();
        result = (Collection<Object[]>) query.list();
        }

        return result;
	}
	
	public int getTotalAllTheTimeEdcTransactionLog(String[] actionCode) throws Exception{
		Session session = edcTransactionLogDao.getCurrentSession();
		
		List actCode = new ArrayList();

 	 	for (int h = 0 ; h < actionCode.length ; h++  ){
 	 		 actCode.add(actionCode[h]);
 	 		System.out.println("actCode" + actCode);
 	 		System.out.println("actionCode" + actionCode[h]);
 	 	 }	
		
	 	StringBuffer sb = new StringBuffer();
	 	//sb.append("SELECT count(a.activityTime) as total "
		 //			 +" FROM EdcTransactionLog a group by a.actionCode, to_char(a.activityTime,  'MON-YYYY') order by a.actionCode asc, to_char(a.activityTime,  'MON-YYYY') asc" );
	 	sb.append("SELECT count(a.activityTime) as total "
	 			 +" FROM EdcTransactionLog a " );
	 	
	 	if(actCode.isEmpty() == false){
	 		sb.append(" where a.actionCode in (:actionCode) " );
	 	}
	 	
	 	sb.append(" group by a.actionCode order by a.actionCode asc" );

        String sqlQuery = sb.toString();
        Query query = session.createQuery(sqlQuery);
        
        if(actCode.isEmpty() == false){
		 	query.setParameterList("actionCode", actCode);
		 }

        List list = query.list();
        int result = list.size();		
        return result;
	}
	
	public int getTotalDailyEdcTransactionLog(String[] actionCode, Date date) throws Exception{
		Session session = edcTransactionLogDao.getCurrentSession();
		
		List actCode = new ArrayList();
	 	 for (int h = 0 ; h < actionCode.length ; h++  ){
	 		 actCode.add(actionCode[h]);
	 		 System.out.println("actCode" + actCode);
	 	 }	
		
	 	StringBuffer sb = new StringBuffer();
	 	sb.append("SELECT count(a.activityTime) as total "
					 +" FROM EdcTransactionLog a " +
					 " where a.activityTime = :date" );
					 
		if(actCode.isEmpty() == false){
			sb.append(" and a.actionCode in (:actionCode) " );
		}
					 
		 sb.append( " group by a.actionCode, to_char(a.activityTime,  'dd-mm-yyyy') " +
					 " order by a.actionCode asc, to_char(a.activityTime,  'dd-mm-yyyy') asc" );

        String sqlQuery = sb.toString();
        Query query = session.createQuery(sqlQuery);
        query.setDate("date", date);
        if(actCode.isEmpty() == false){
		 	query.setParameterList("actionCode", actCode);
		 }

        List list = query.list();
        int result = list.size();		
        return result;
	}
	
	public int getTotalMonthlyEdcTransactionLog(String[] actionCode, Date date) throws Exception{
		Session session = edcTransactionLogDao.getCurrentSession();
		
		List actCode = new ArrayList();
	 	 for (int h = 0 ; h < actionCode.length ; h++  ){
	 		 actCode.add(actionCode[h]);
	 	 }	
		
	 	StringBuffer sb = new StringBuffer();
	 	sb.append("SELECT count(a.activityTime) as total "
					 +" FROM EdcTransactionLog a " +
					 " where to_char(a.activityTime, 'MON-YYYY') = to_char(cast(:date as date), 'MON-YYYY') " );
					 
		if(actCode.isEmpty() == false){
			sb.append(" and a.actionCode in (:actionCode) " );
		}
		
		
	 	sb.append(" group by a.actionCode, to_char(a.activityTime,  'MON-YYYY') " +
					 " order by a.actionCode asc, to_char(a.activityTime,  'MON-YYYY') asc" );

        String sqlQuery = sb.toString();
        Query query = session.createQuery(sqlQuery);
        query.setDate("date", date);
        if(actCode.isEmpty() == false){
		 	query.setParameterList("actionCode", actCode);
		 }

        List list = query.list();
        int result = list.size();		
        return result;
	}
	
	public int getTotalYearlyEdcTransactionLog(String[] actionCode, Date date) throws Exception{
		Session session = edcTransactionLogDao.getCurrentSession();
		
		List actCode = new ArrayList();
	 	 for (int h = 0 ; h < actionCode.length ; h++  ){
	 		 actCode.add(actionCode[h]);
	 	 }	
		
	 	StringBuffer sb = new StringBuffer();
	 	sb.append("SELECT count(a.activityTime) as total "
					 +" FROM EdcTransactionLog a " +
					 " where to_char(a.activityTime, 'YYYY') = to_char(cast(:date as date), 'YYYY') " );
					 
		if(actCode.isEmpty() == false){
			sb.append(" and a.actionCode in (:actionCode) " );
		}
		
	 	sb.append(" group by a.actionCode, to_char(a.activityTime,  'YYYY') " +
					 "order by a.actionCode asc, to_char(a.activityTime,  'YYYY') asc" );

        String sqlQuery = sb.toString();
        Query query = session.createQuery(sqlQuery);
        query.setDate("date", date);
        if(actCode.isEmpty() == false){
		 	query.setParameterList("actionCode", actCode);
		 }

        List list = query.list();
        int result = list.size();		
        return result;
	}

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = edcTransactionLogDao.getCriteria();
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

		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = edcTransactionLogDao.getCriteria();
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

		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = edcTransactionLogDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = edcTransactionLogDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTransactionLog element = (EdcTransactionLog) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = edcTransactionLogDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public EdcTransactionLog searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		EdcTransactionLog obj = (EdcTransactionLog) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public EdcTransactionLog searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = edcTransactionLogDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		EdcTransactionLog obj = (EdcTransactionLog) c.uniqueResult();
		return obj;
	}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = edcTransactionLogDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = edcTransactionLogDao.getDetachedCriteria();
		return dc;
	}




// class+ 

// class-

	public int getTotalTransaction(String merchantCode, String terminalCode) {
		Session session = edcTransactionLogDao.getCurrentSession();

		String queryStr = "select count(log.id) " +
				"from edc_transaction_log log " +
				"where log.merchant_code = :merchant_code " +
				"and log.terminal_code = :terminal_code ";

		SQLQuery q = session.createSQLQuery(queryStr);

		q.setString("merchant_code", merchantCode);
		q.setString("terminal_code", terminalCode);

		Number countResult = (Number) q.uniqueResult();

		return countResult.intValue();
	}
}
