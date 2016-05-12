
package com.ametis.cms.service.impl;


import java.io.Serializable;
import java.sql.Array;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.ametis.cms.dao.ProviderDao;
import com.ametis.cms.datamodel.Action;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ActivityLog;
import com.ametis.cms.datamodel.ClientProvider;
import com.ametis.cms.datamodel.ContactPerson;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.datamodel.MemberGroupProvider;
import com.ametis.cms.datamodel.MemberProvider;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyProvider;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderHistory;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClientProviderService;
import com.ametis.cms.service.MemberGroupProviderService;
import com.ametis.cms.service.MemberProviderService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyProviderService;
import com.ametis.cms.service.ProviderHistoryService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.util.dao.DaoSupportUtil;



// imports+ 

// imports- 


/**
 * Provider is a servlet controller for provider Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class ProviderServiceImpl implements ProviderService

// extends+ 

// extends- 

{
	
	private ProviderDao  providerDao;
	private ActivityLogService activityLogService;
	private MemberService memberService;
	private MemberProviderService memberProviderService;
	private MemberGroupProviderService memberGroupProviderService;
	private PolicyProviderService policyProviderService;
	private ClientProviderService clientProviderService;
	private ProviderHistoryService providerHistoryService;
	

	
	
	
	public ProviderHistoryService getProviderHistoryService() {
		return providerHistoryService;
	}
	public void setProviderHistoryService(
			ProviderHistoryService providerHistoryService) {
		this.providerHistoryService = providerHistoryService;
	}
	public MemberProviderService getMemberProviderService() {
		return memberProviderService;
	}
	public void setMemberProviderService(MemberProviderService memberProviderService) {
		this.memberProviderService = memberProviderService;
	}
	public MemberGroupProviderService getMemberGroupProviderService() {
		return memberGroupProviderService;
	}
	public void setMemberGroupProviderService(
			MemberGroupProviderService memberGroupProviderService) {
		this.memberGroupProviderService = memberGroupProviderService;
	}
	public PolicyProviderService getPolicyProviderService() {
		return policyProviderService;
	}
	public void setPolicyProviderService(PolicyProviderService policyProviderService) {
		this.policyProviderService = policyProviderService;
	}
	public ClientProviderService getClientProviderService() {
		return clientProviderService;
	}
	public void setClientProviderService(ClientProviderService clientProviderService) {
		this.clientProviderService = clientProviderService;
	}
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}
	public void setProviderDao (ProviderDao object){
		this.providerDao = object;
	}
	public ProviderDao getProviderDao (){
		return this.providerDao;
	}
	/*
	* Method create (Provider object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public Provider create (Provider object,ActionUser actionUser) throws Exception {
		
		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(Integer.valueOf(0));

			
			
			if (actionUser != null){
				
				User user = actionUser.getUser();						
				if (user != null){
					object.setCreatedBy(user.getUsername());
				}
				
				if(user.getRoleId().getRoleId() == 6){
					object.setStatusProspek(2);
				}
				
				if(user.getRoleId().getRoleId() == 4 || user.getRoleId().getRoleId() == 3){
					object.setStatusProspek(1);
				}
				
			}
			
			java.sql.Date startDate = object.getContractStartDate();
			java.sql.Date endDate = object.getContractEndDate();
			
			if (startDate != null && endDate != null){
				if (startDate.before(new java.util.Date(System.currentTimeMillis())) && endDate.after(new java.util.Date(System.currentTimeMillis())) ){
					SubscriptionStatus status = new SubscriptionStatus();
					status.setStatusId(SubscriptionStatus.ACTIVE);
					object.setStatusId(status);
					
				}
			}
			else {
				SubscriptionStatus status = new SubscriptionStatus();
				status.setStatusId(SubscriptionStatus.TERMINATED);
				object.setStatusId(status);
			}
			

			Provider result = providerDao.create (object);
			return result;
		}
	/*
	* Method update (Provider object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public Provider update (Provider object,ActionUser actionUser) throws Exception{
		
		object.setModifiedTime (new java.sql.Timestamp (System.currentTimeMillis()));
		if (actionUser != null){					
			User user = actionUser.getUser();			
			if (user != null){
				object.setModifiedBy (user.getUsername());
			}
		}
	
		java.sql.Date startDate = object.getContractStartDate();
		java.sql.Date endDate = object.getContractEndDate();
		
		if (object.getStatusId().getStatusId().intValue() != SubscriptionStatus.BLOCKED){
			if (startDate != null && endDate != null){
				if (startDate.before(new java.util.Date(System.currentTimeMillis())) && endDate.after(new java.util.Date(System.currentTimeMillis())) ){
					SubscriptionStatus status = new SubscriptionStatus();
					status.setStatusId(SubscriptionStatus.ACTIVE);
					object.setStatusId(status);				
				}
				else {
					SubscriptionStatus status = new SubscriptionStatus();
					status.setStatusId(SubscriptionStatus.TERMINATED);
					object.setStatusId(status);
				}
			}
			else {
				SubscriptionStatus status = new SubscriptionStatus();
				status.setStatusId(SubscriptionStatus.TERMINATED);
				object.setStatusId(status);
			}
		}
		
		providerDao.update (object);
      	
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
	public Provider trash (java.io.Serializable pkey) throws Exception {
		Provider object = providerDao.get (pkey);
		providerDao.delete (object);
		return object;
	}

	/*
	* Method delete (Provider object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public Provider delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception{
		Provider object = providerDao.get (pkey);
		// created by andre //
		// buat validasi delete provider//
		Session session = providerDao.getProviderSession();
			
			String sqlQuery="";
			sqlQuery += " select * from member_provider where provider_id = "+pkey+" ";
			SQLQuery q = session.createSQLQuery(sqlQuery);	
			List sql = q.list();
			String mp = sql.toString();
					
			String sqlQuery2 ="";
			sqlQuery2 += " select * from client_provider where provider_id = "+pkey+" ";
			SQLQuery q2 = session.createSQLQuery(sqlQuery2);	
			List sql2 = q2.list();
			String cp = sql2.toString();
			
			String sqlQuery3 ="";
			sqlQuery3 += " select * from policy_provider where provider_id = "+pkey+" ";
			SQLQuery q3 = session.createSQLQuery(sqlQuery3);
			List sql3 = q3.list();
			String pp = sql3.toString();
			
			if (mp.equalsIgnoreCase("[]") && cp.equalsIgnoreCase("[]") && pp.equalsIgnoreCase("[]")) {
				object.setDeletedStatus(new Integer(1));
				object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			
					if (actionUser != null){
						
						User user = actionUser.getUser();
					
						if (user != null){
							object.setDeletedBy(user.getUsername());
						}
					}
			
			providerDao.update (object);
			}else {
				object = null;
			}
			
		// created by andre//
		return object;
	}


	/*
	* Method delete (Provider object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public Provider delete (Provider object,ActionUser actionUser) throws Exception{
		
		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(new Integer(1));
		if (actionUser != null){
			
		User user = actionUser.getUser();
			if (user != null){
				object.setDeletedBy(user.getUsername());
			}
		}
		
		providerDao.update (object);
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
	public Provider get (java.io.Serializable pkey) throws Exception{
		Provider object = null;
		object = providerDao.get(pkey);
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

	public Provider get (java.io.Serializable pkey, String[] required) throws Exception{
	    Provider object = null;
	    object = providerDao.get(pkey);
		DaoSupportUtil.lazyInit(required,object);
	    return object;
	}
// -- get section end here


// SEARCH SECTION - PALING RUMIT !!
// * -> plain
// *b -> with columnOrder



// -- 1
	public Collection<Provider> getProviderList(String[] sStatus,  Object[] oStatus, 
			String[] required) throws Exception {
		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setEqParam(sStatus, oStatus, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}
	
	//Add by aju on 20151028, nirbai nirbai fufufu :P
	public Collection<Provider> getNearbyProviderByMaxKilometer(double myLatitude, double myLongitude, double maxDistance,
			String[] required) throws Exception {
		
		Collection<Provider> providerList = new Vector<Provider>();
		
		try{
			
			
			Session session = providerDao.getProviderSession();
			if (session != null){
				String sqlQuery="";
				sqlQuery += " SELECT p.* ";
				sqlQuery += "        , distance_in_kilometers(:latitude, :longitude, CAST(p.latitude AS FLOAT), CAST(p.longitude AS FLOAT)) theDistance ";
				sqlQuery += "   FROM Provider p ";
				sqlQuery += "  WHERE (p.latitude IS NOT NULL AND LENGTH(p.latitude)>0) ";
				sqlQuery += "    AND (p.longitude IS NOT NULL AND LENGTH(p.longitude)>0) ";
				sqlQuery += "    AND  distance_in_kilometers(:latitude, :longitude, CAST(p.latitude AS FLOAT), CAST(p.longitude AS FLOAT)) < :maxdistance ";
				//sqlQuery += "    AND sqrt((69.1 * (cast(p.latitude as float) - cast(:latitude as float))) * (69.1 * (cast(p.latitude as float) - cast(:latitude as float))) + (69.1 * (cast(p.longitude as float) - cast(:longitude as float)) * cos(cast(:latitude as float) / 57.3)) * (69.1 * (cast(p.longitude as float) - cast(:longitude as float)) * cos(cast(:latitude as float) / 57.3))) < :maxdistance ";
				sqlQuery += " ORDER BY theDistance ASC ";
				
				SQLQuery q = session.createSQLQuery(sqlQuery);
				//Query q = session.createQuery(sqlQuery);
				q.addEntity(Provider.class);
				q.setDouble("latitude", myLatitude);
				q.setDouble("longitude", myLongitude);
				q.setDouble("maxdistance", maxDistance);
				
				providerList = q.list();
				System.out.println("getNearbyProviderByMaxKilometer("+myLatitude+", "+myLongitude+", "+maxDistance+")");
				System.out.println("Total Nearby Provider : " + providerList.size());
				
				for (Iterator iter = providerList.iterator(); iter.hasNext();) {
					Provider element = (Provider) iter.next();
					DaoSupportUtil.lazyInit(required, element);
				}
				
				
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		/*
		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setEqParam(sStatus, oStatus, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		*/
		return providerList;

	}
	
	public Collection<Provider> searchProvider( String[] eqProvider,  Object[] oProvider, String[] sStatus,  Object[] oStatus, 
			String[] required) throws Exception {
		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(eqProvider, oProvider, c);
		DaoSupportUtil.setEqParam(sStatus, oStatus, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, int index, int offset) throws Exception{

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		System.out.println("DI DALAM SEARCH ");
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end
	
	//Add 20150821 by FVO, for provider edc alert
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required,Object[] inParamsValue, int index, int offset) throws Exception{

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		if(inParamsValue.length >= 1)
			DaoSupportUtil.setIn("providerId",inParamsValue,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = providerDao.getCriteria();
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
		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = providerDao.getCriteria();
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
		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	
	//Add 20150821 by FVO, searching Provider for EDC Alert
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			String[] required, Object[] inParamsValue,
			int index, int offset) throws Exception{
		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		if(inParamsValue.length >= 1)
			DaoSupportUtil.setIn("providerId", inParamsValue,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	
	
	public Collection<Provider> searchProviderByProviderStatus (String[] likeColumns, Object[] likeParams, String[] required) throws Exception{

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setEqParam(likeColumns, likeParams, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = providerDao.getCriteria();
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
		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = providerDao.getCriteria();
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

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = providerDao.getCriteria();
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

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
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

		Criteria c = providerDao.getCriteria();
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

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
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

		Criteria c = providerDao.getCriteria();
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

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
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

		Criteria c = providerDao.getCriteria();
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

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
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

		Criteria c = providerDao.getCriteria();
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

		
		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
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

		Criteria c = providerDao.getCriteria();
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

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
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

		Criteria c = providerDao.getCriteria();
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

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total
	//Add 20150821 by FVO, for provider edc alert
	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams, Object[] inParamsValue) throws Exception{

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		if(inParamsValue.length >= 1)
			DaoSupportUtil.setIn("providerId",inParamsValue,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = providerDao.getCriteria();
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

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = providerDao.getCriteria();
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

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	
	//Add 20150821 by FVO, for total Provider edc alert
	public int getTotalProviderRollPaperAlertDefault(Integer rollPaperAmount){
		int total = 0;
		try{
			Session session = providerDao.getProviderSession();
			if(session != null){
				Query query = session.createQuery("SELECT COUNT(DISTINCT prov.providerId) FROM Provider prov WHERE prov.rollPaperLimitCustomize <= 0 AND " +
			   			"prov.rollPaperPaymentCustomize <= 0 AND " +
			   			"prov.rollPaperRegisterCustomize <= 0 AND " +
			   			"prov.rollPaperAmount <= :rollPaperAmount AND " +
			   			"prov.deletedStatus = 0");
				
				query.setInteger("rollPaperAmount", rollPaperAmount);
				Number num = (Number) query.uniqueResult();
				total = num.intValue();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return total;
	}
	public int getTotalProviderRollPaperAlertCustom(){
		int total = 0;
		try{
			Session session = providerDao.getProviderSession();
			if(session != null){
				Query query = session.createQuery("SELECT COUNT(DISTINCT prov.providerId) FROM Provider prov WHERE prov.rollPaperLimitCustomize > 0 AND " +
			   			"prov.rollPaperPaymentCustomize > 0 AND " +
			   			"prov.rollPaperRegisterCustomize > 0 AND " +
			   			"prov.rollPaperAmount <= prov.rollPaperLimitCustomize AND " +
			   			"prov.deletedStatus = 0");
				Number num = (Number) query.uniqueResult();
				total = num.intValue();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return total;
	}
	//Add 20150901 by FVO for Roll Paper Alert
	public int getTotalProviderEdcTerminalAlert(java.util.Date alertDate){
		int total = 0;
		try{
			Session session = providerDao.getProviderSession();
			if(session != null){
				/*Query query = session.createQuery("SELECT COUNT(DISTINCT edc.providerId.providerId) FROM EdcTerminal edc INNER JOIN edc.providerId WHERE " +
						"edc.deletedStatus = 0 AND edc.lastTransaction <= :alertDate " +
						"AND edc.providerId.edcAlert IS NOT NULL " +
						"AND edc.providerId.deletedStatus = 0 ");
				*/
				Query query = session.createQuery("SELECT COUNT(DISTINCT edc.providerId.providerId) FROM EdcTerminal edc INNER JOIN edc.providerId WHERE " +
						"edc.deletedStatus = 0 AND edc.lastTransaction <= :alertDate " +
						"AND edc.providerId.edcAlert <= 0 " +
						"AND edc.providerId.deletedStatus = 0 ");
				
				query.setDate("alertDate", alertDate);
				Number num = (Number) query.uniqueResult();
				total = num.intValue();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return total;
	}
	public int getTotalProviderEdcTerminalAlertCustom(java.util.Date alertDate){
		int total = 0;
		try{
			Session session = providerDao.getProviderSession();
			if(session != null){
				Query query = session.createQuery("SELECT COUNT(DISTINCT edc.providerId.providerId) FROM EdcTerminal edc INNER JOIN edc.providerId WHERE " +
						"edc.deletedStatus = 0 AND " +
						"edc.providerId.deletedStatus = 0 AND " +
						"edc.providerId.edcAlert > 0 AND " +
						"edc.lastTransaction + edc.providerId.edcAlert < :alertDate ");
				query.setDate("alertDate", alertDate);
				
				Number num = (Number) query.uniqueResult();
				total = num.intValue();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return total;
	}
	
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = providerDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = providerDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	
	//Add by aju on 20150820, for getting all active provider list :D
	public Collection getAll (String eqColumns, Object eqParams, String[] required, boolean orderResult, boolean orderAsc, String orderBy) throws Exception{

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		if(orderResult){
			DaoSupportUtil.setOrderBy(orderAsc, orderBy, c);
		}
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Provider element = (Provider) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	
	public Collection getAll () throws Exception{

		Criteria c = providerDao.getCriteria();
		List list = c.list();
		return list;
	}
	
	//Add 20150821 by FVO, ambil provider ID untuk alert
	public Collection getProviderIdsEdcTerminalAlert(java.util.Date alertDate) throws Exception{
		Collection result = null;
	   	 
	   	 Session session = providerDao.getProviderSession();
	        if (session != null) {
	       	 Query query = 	session.createQuery("SELECT DISTINCT edc.providerId.providerId FROM EdcTerminal edc INNER JOIN edc.providerId WHERE " +
						"edc.deletedStatus = 0 AND edc.lastTransaction <= :alertDate " +
						"AND edc.providerId.edcAlert <= 0 " +
						"AND edc.providerId.deletedStatus = 0 ");
	       	 	query.setDate("alertDate", alertDate);
	            result = (Collection) query.list();
	        } 
	   	 return result;
	}
	public Collection getProviderIdsEdcTerminalAlertCustom(java.util.Date alertDate) throws Exception{
		Collection result = null;
	   	 
	   	 Session session = providerDao.getProviderSession();
	        if (session != null) {
	       	 Query query = 	session.createQuery("SELECT DISTINCT edc.providerId.providerId FROM EdcTerminal edc INNER JOIN edc.providerId WHERE " +
					"edc.deletedStatus = 0 AND " +
					"edc.providerId.deletedStatus = 0 AND " +
					"edc.providerId.edcAlert > 0 AND " +
					"edc.lastTransaction + edc.providerId.edcAlert < :alertDate ");
       	 	query.setDate("alertDate", alertDate);
            result = (Collection) query.list();
	        } 
	   	 return result;
	}
	
	//Add 20150901 by FVO, for alert roll paper provider
	public Collection getProviderIdsRollPaperAlertDefault(Integer rollPaperAmount) throws Exception{
		Collection result = null;
	   	 
	   	 Session session = providerDao.getProviderSession();
	        if (session != null) {
	       	 Query query = 	session.createQuery("SELECT DISTINCT prov.providerId FROM Provider prov WHERE prov.rollPaperLimitCustomize <= 0 AND " +
	       			"prov.rollPaperPaymentCustomize <= 0 AND " +
	       			"prov.rollPaperRegisterCustomize <= 0 AND " +
	       			"prov.rollPaperAmount <= :rollPaperAmount AND " +
	       			"prov.deletedStatus = 0");
	       	 	query.setInteger("rollPaperAmount", rollPaperAmount);
	            result = (Collection) query.list();
	        } 
	   	 return result;
	}
	public Collection getProviderIdsRollPaperAlertCustomize() throws Exception{
		Collection result = null;
	   	 
	   	 Session session = providerDao.getProviderSession();
	        if (session != null) {
	       	 Query query = 	session.createQuery("SELECT DISTINCT prov.providerId FROM Provider prov WHERE prov.rollPaperLimitCustomize > 0 AND " +
	       			"prov.rollPaperPaymentCustomize > 0 AND " +
	       			"prov.rollPaperRegisterCustomize > 0 AND " +
	       			"prov.rollPaperAmount <= prov.rollPaperLimitCustomize AND " +
	       			"prov.deletedStatus = 0");
	            result = (Collection) query.list();
	        } 
	   	 return result;
	}
//-------------------------------------------------

// unique Result

	public Provider searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		Provider obj = (Provider) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public Provider searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		Provider obj = (Provider) c.uniqueResult();
		return obj;
	}
	public Provider searchUnique (String[] eqColumns, Object[] eqParams, int index, int offset)
			throws Exception{
			Criteria c = providerDao.getCriteria();
			DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
			DaoSupportUtil.setLimit(index, offset, c);
			Provider obj = (Provider) c.uniqueResult();
			return obj;
		}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = providerDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = providerDao.getDetachedCriteria();
		return dc;
	}
	public Provider terminate(Serializable object, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		
		Provider provider = null;
		
		if (object != null){
			provider = providerDao.get(object);
			
			if (provider != null){
				SubscriptionStatus status = new SubscriptionStatus();
				status.setStatusId(SubscriptionStatus.TERMINATED);
				provider.setStatusId(status);
				provider.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
				String username = "";
				if (actionUser != null){
					if (actionUser.getUser() != null){
						provider.setModifiedBy(actionUser.getUser().getUsername());
						username = actionUser.getUser().getUsername();
					}
				}
				
				provider = providerDao.update(provider);
				
				String statusProv = "";
				
				if (provider.getStatusId() != null){
					if (provider.getStatusId().getStatusId().intValue() == 1){
						statusProv = "ACTIVE";
					}
					if (provider.getStatusId().getStatusId().intValue() == 2){
						statusProv = "TERMINATE";
					}
				}
				
				ActivityLog log = new ActivityLog();
				log.setProviderId(provider);
				log.setAction("TERMINATE PROVIDER");
				log.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
				log.setUsername(username);
				log.setSessionId(actionUser.getLoginSession());
				log.setIpAddress(actionUser.getIpAddress());
				
				log.setDescription("PROVIDER NAME = " + provider.getProviderName() + " STATUS = " + statusProv);
				
				activityLogService.create(log);
			}
		}
		
		
		return provider;
	}
	public Provider activate(Serializable object, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		Provider provider = null;
		
		if (object != null){
			provider = providerDao.get(object);
			
			if (provider != null){
				SubscriptionStatus status = new SubscriptionStatus();
				status.setStatusId(SubscriptionStatus.ACTIVE);
				
				provider.setStatusId(status);
				provider.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
				String username = "";
				if (actionUser != null){
					if (actionUser.getUser() != null){
						provider.setModifiedBy(actionUser.getUser().getUsername());
						username = actionUser.getUser().getUsername();
					}
				}
				
				provider = providerDao.update(provider);
				String statusProv = "";
				
				if (provider.getStatusId() != null){
					if (provider.getStatusId().getStatusId().intValue() == 1){
						statusProv = "ACTIVE";
					}
					if (provider.getStatusId().getStatusId().intValue() == 2){
						statusProv = "TERMINATE";
					}
				}
				
				ActivityLog log = new ActivityLog();
				log.setProviderId(provider);
				log.setAction("ACTIVATE PROVIDER");
				log.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
				log.setUsername(username);
				log.setSessionId(actionUser.getLoginSession());
				log.setIpAddress(actionUser.getIpAddress());
				log.setDescription("PROVIDER NAME = " + provider.getProviderName() + " STATUS = " + statusProv);
				
				activityLogService.create(log);
			}
		}
		
		
		return provider;
	}
	public Provider renewal(Serializable object, Date contractStart, Date contractEnd, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		Provider provider = null;
		
		if (object != null){
			provider = providerDao.get(object);
			
			if (provider != null){
		
				provider.setContractStartDate(contractStart);
				provider.setContractEndDate(contractEnd);
				
				SubscriptionStatus status = new SubscriptionStatus();
				status.setStatusId(SubscriptionStatus.ACTIVE);
				provider.setStatusId(status);
				
				provider = update(provider,actionUser);
				
			}
		}
		
		
		return provider;
	}
	public int getTotalUnassignedProvider(String[] likeColumns,
			Object[] likeParams, String[] eqColumns, Object[] eqParams,
			Integer clientId) throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria dc = DetachedCriteria.forClass(ClientProvider.class);
		dc.setProjection(Property.forName("providerId"));
		dc.add(Property.forName("clientId.clientId").eq(clientId));
		Criteria c = providerDao.getCriteria();
		
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setNotIn("providerId", dc, c);
		
		List<Provider> providers = c.list();
		
		int result = 0;
		
		if (providers != null){
			result = providers.size();
		}
		
		return result;
	}
	public Collection<Provider> searchUnassignedProvider(String[] likeColumns,
			Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] required, Integer clientId, int index, int offset)
			throws Exception {
		// TODO Auto-generated method stub
	

		DetachedCriteria dc = DetachedCriteria.forClass(ClientProvider.class);
		dc.setProjection(Property.forName("providerId"));
		dc.add(Property.forName("clientId.clientId").eq(clientId));
		Criteria c = providerDao.getCriteria();
		
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setNotIn("providerId", dc, c);
		DaoSupportUtil.setLimit(index,offset, c);
		
			
		return c.list();
	}
	
	public Collection<Action> getUnassignedProvider (Integer clientId,String searchKeyword,String category, 
			int index, int offset) throws Exception{

	
	Session session = providerDao.getProviderSession();
		
		String searchCriteria = "";
		searchKeyword = "%"+searchKeyword+"%";
		
		if (category != null && category.equalsIgnoreCase("providerName")){
			searchCriteria = " provider.providerName LIKE :providerName and ";
		}
		else if (category != null && category.equalsIgnoreCase("city")){
			searchCriteria = " provider.city LIKE :city and ";
		}
		else if (category != null && category.equalsIgnoreCase("province")){
			searchCriteria = " provider.province LIKE :province and ";
		}
		else if (category != null && category.equalsIgnoreCase("country")){
			searchCriteria = " provider.country LIKE :country and ";
		}
		
		Query query =  session.createQuery("select provider from Provider provider where "+searchCriteria+ " provider.providerId not in " +
				"(select cp.providerId FROM ClientProvider cp WHERE cp.clientId = :clientId)");
		query.setInteger("clientId", clientId);
		
		if (category != null && category.equalsIgnoreCase("providerName")){
			query.setString("providerName", searchKeyword);
		}
		else if (category != null && category.equalsIgnoreCase("city")){
			query.setString("city", searchKeyword);
		}
		else if (category != null && category.equalsIgnoreCase("province")){
			query.setString("province", searchKeyword);
		}
		else if (category != null && category.equalsIgnoreCase("country")){
			query.setString("country", searchKeyword);
		}
		
			
		query.setMaxResults(offset);
		query.setFirstResult(index);
		
		List<Action> list = query.list();
		return list;

	}
	public int getTotalUnassignedProvider (Integer clientId,String searchKeyword,String category	) throws Exception{

		
		Session session = providerDao.getProviderSession();
		
		String searchCriteria = "";
		searchKeyword = "%"+searchKeyword+"%";
		
		if (category != null && category.equalsIgnoreCase("providerName")){
			searchCriteria = " provider.providerName LIKE :providerName and ";
		}
		else if (category != null && category.equalsIgnoreCase("city")){
			searchCriteria = " provider.city LIKE :city and ";
		}
		else if (category != null && category.equalsIgnoreCase("province")){
			searchCriteria = " provider.province LIKE :province and ";
		}
		else if (category != null && category.equalsIgnoreCase("country")){
			searchCriteria = " provider.country LIKE :country and ";
		}
		
		Query query =  session.createQuery("select provider from Provider provider where "+searchCriteria+ " provider.providerId not in " +
				"(select cp.providerId FROM ClientProvider cp WHERE cp.clientId = :clientId)");
		query.setInteger("clientId", clientId);
		
		if (category != null && category.equalsIgnoreCase("providerName")){
			query.setString("providerName", searchKeyword);
		}
		else if (category != null && category.equalsIgnoreCase("city")){
			query.setString("city", searchKeyword);
		}
		else if (category != null && category.equalsIgnoreCase("province")){
			query.setString("province", searchKeyword);
		}
		else if (category != null && category.equalsIgnoreCase("country")){
			query.setString("country", searchKeyword);
		}
		
		
		List<Action> list = query.list();
		
		int total = 0;
		
		if (list != null){
			
			if (list.size() > 0){
				total = list.size();
			}
		}
		
		return total;
	}
	public Provider getByProviderCode(String providerCode) throws Exception {
		// TODO Auto-generated method stub

		Provider provider = null;
		try {
			
						
			Session session = providerDao.getProviderSession();
			String sqlQuery = "SELECT p FROM "
				+ "Provider p WHERE p.edcCode = :code AND p.deletedStatus = 0";
	
			Query query = session.createQuery(sqlQuery);
			query.setString("code", providerCode);
			query.setMaxResults(1);
			
			
			Collection<Object> list = query.list();
			
			if (list != null){
				Iterator<Object> iterartor = list.iterator();
				
				if (iterartor.hasNext()){
					Object obj = iterartor.next();
					
					if (obj instanceof Provider){
						provider = (Provider) obj;
					}
				}
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return provider;
	}
	
	public Collection<Provider> searchMemberGroupProvider(String q, Integer groupId) throws Exception {
		
		Collection<Provider> result = new Vector<Provider>();
		
		try {

			Session session = providerDao.getProviderSession();
			String sqlQuery = "SELECT p FROM "
				+ "Provider p WHERE lower(p.providerName) LIKE :keyword AND p.deletedStatus = 0 " +
						"AND p.statusId.statusId = :statusId " +
						"AND p.providerId IN (SELECT mgp.providerId FROM MemberGroupProvider mgp" +
						" WHERE mgp.memberGroupId.memberGroupId = :groupId ) ORDER BY p.providerName ASC";

			Query query = session.createQuery(sqlQuery);
			query.setString("keyword", "%" + q + "%");
			query.setInteger("statusId", SubscriptionStatus.ACTIVE);
			query.setInteger("groupId", groupId);
			query.setMaxResults(10);
			System.out.println("query = " + query.getQueryString());
			
			System.out.println("result = " + query.list());
			result = query.list();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Collection<Provider> searchClientProvider(String q, Integer clientId) throws Exception {
		
		Collection<Provider> result = new Vector<Provider>();
		
		try {

			Session session = providerDao.getProviderSession();
			String sqlQuery = "SELECT p FROM "
				+ "Provider p WHERE lower(p.providerName) LIKE :keyword AND p.deletedStatus = 0 " +
						"AND p.statusId.statusId = :statusId  " +
						"AND p.providerId IN (SELECT cp.providerId FROM ClientProvider cp" +
						" WHERE cp.clientId.clientId = :clientId ) ORDER BY p.providerName ASC";

			Query query = session.createQuery(sqlQuery);
			query.setString("keyword", "%" + q + "%");
			query.setInteger("statusId", SubscriptionStatus.ACTIVE);
			query.setInteger("clientId", clientId);
			query.setMaxResults(10);
			System.out.println("query = " + query.getQueryString());
			
			System.out.println("result = " + query.list());
			result = query.list();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Collection<Provider> searchMemberProvider(String q, Integer memberId) throws Exception {
	
		Collection<Provider> result = new Vector<Provider>();
		
		try {
	
			Session session = providerDao.getProviderSession();
			String sqlQuery = "SELECT p FROM "
				+ "Provider p WHERE lower(p.providerName) LIKE :keyword AND p.deletedStatus = 0 " +
						"AND p.statusId.statusId = :statusId  " +
						"AND p.providerId IN (SELECT mp.providerId FROM MemberProvider mp" +
						" WHERE mp.memberId.memberId = :memberId ) ORDER BY p.providerName ASC";
	
			Query query = session.createQuery(sqlQuery);
			query.setString("keyword", "%" + q + "%");
			query.setInteger("statusId", SubscriptionStatus.ACTIVE);
			query.setInteger("memberId", memberId);
			query.setMaxResults(10);
			System.out.println("query = " + query.getQueryString());
			
			System.out.println("result = " + query.list());
			result = query.list();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Collection<Provider> searchPolicyProvider2(String q, Integer policyId) throws Exception {
		
		Collection<Provider> result = new Vector<Provider>();
		
		try {

			Session session = providerDao.getProviderSession();
			String sqlQuery = "SELECT p FROM "
				+ "Provider p WHERE lower(p.providerName) LIKE :keyword AND p.deletedStatus = 0 " +
						"AND p.statusId.statusId = :statusId " +
						"AND p.providerId IN (SELECT pp.providerId FROM PolicyProvider pp" +
						" WHERE pp.policyId.policyId = :policyId ) ORDER BY p.providerName ASC";

			Query query = session.createQuery(sqlQuery);
			query.setString("keyword", "%" + q + "%");
			query.setInteger("statusId", SubscriptionStatus.ACTIVE);
			query.setInteger("policyId", policyId);
			query.setMaxResults(10);
			System.out.println("query = " + query.getQueryString());
			
			System.out.println("result = " + query.list());
			result = query.list();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public Collection<Provider> searchPolicyProvider(String q, Integer policyId) throws Exception {
		
		Collection<Provider> result = new Vector<Provider>();
		
		try {

			Session session = providerDao.getProviderSession();
			String sqlQuery = "SELECT p FROM "
				+ "Provider p WHERE lower(p.providerName) LIKE :keyword AND p.deletedStatus = 0 " +
						"AND p.statusId.statusId = :statusId AND p.statusProspek = 1 " +
						"AND p.providerId NOT IN (SELECT pp.providerId FROM PolicyProvider pp" +
						" WHERE pp.policyId.policyId = :policyId ) ORDER BY p.providerName ASC";

			Query query = session.createQuery(sqlQuery);
			query.setString("keyword", "%" + q + "%");
			query.setInteger("statusId", SubscriptionStatus.ACTIVE);
			query.setInteger("policyId", policyId);
			query.setMaxResults(10);
			System.out.println("query = " + query.getQueryString());
			
			System.out.println("result = " + query.list());
			result = query.list();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	public Collection<Provider> searchProvider(String q) throws Exception {
		// TODO Auto-generated method stub
		Collection<Provider> result = new Vector<Provider>();
		
		try {

			String[] likeParam = {"providerName"};
			Object[] likeValue = {q};
			
			String[] eqParam = {"deletedStatus"};
			Object[] eqValue = {0};
			
			result = search(likeParam,likeValue,eqParam,eqValue,0,20);
			
				}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Collection<Provider> searchAuthorizedProvider(String q) throws Exception {
		// TODO Auto-generated method stub
		Collection<Provider> result = new Vector<Provider>();
		
		try {
			
			String[] likeParam = {"providerName"};
			Object[] likeValue = {q};
			
			String[] eqParam = {"deletedStatus","statusId.statusId"};
			Object[] eqValue = {0,SubscriptionStatus.ACTIVE};
			
			result = search(likeParam,likeValue,eqParam,eqValue,0,10);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Collection<Provider> searchPlotableProvider() throws Exception {
		// TODO Auto-generated method stub
			Collection<Provider> result = new Vector<Provider>();
		
		try {

			Session session = providerDao.getProviderSession();
			String sqlQuery = "SELECT p FROM "
				+ "Provider p WHERE p.longitude IS NOT NULL";
	
			Query query = session.createQuery(sqlQuery);
			query.setMaxResults(10);
			
			
			result = query.list();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public Collection<Provider> searchProviderReport(Integer clientId, Integer policyId, Integer memberGroupId) throws Exception {
			Collection<Provider> result = new Vector<Provider>();
		
		try {
			
		
								
			
			if (memberGroupId != null){
					
					DetachedCriteria dc = DetachedCriteria.forClass(MemberGroupProvider.class);
					dc.setProjection(Property.forName("providerId"));
					dc.add(Property.forName("memberGroupId.memberGroupId").eq(memberGroupId));
					
					Criteria c = providerDao.getCriteria();					
					DaoSupportUtil.setIn("providerId", dc, c);
					
					
					result = c.list();
					
					for (Iterator iter = result.iterator(); iter.hasNext();) {
						Provider element = (Provider) iter.next();
			   			
			   			
			   			String[] eqParamMember = {"memberGroupId.memberGroupId","deletedStatus","providerId.providerId"};
			   			Object[] eqValueMember = {memberGroupId,0,element.getProviderId()};
			   			
			   			Collection<MemberGroupProvider> mp = memberGroupProviderService.search(null,null,eqParamMember,eqValueMember,0,1);
			   			
			   			if (mp != null){
			   				Iterator<MemberGroupProvider> mpIterator = mp.iterator();
			   				
			   				if (mpIterator.hasNext()){
			   					MemberGroupProvider memberProvider = mpIterator.next();
			   					if (memberProvider != null){
			   						
			   						if (memberProvider.getInpatient() != null && memberProvider.getInpatient().intValue() == 1){
			   							element.setInpatient("Y");
			   						}
			   						else {
			   							element.setInpatient("N");
			   						}
			   						
			   						if (memberProvider.getOutpatient() != null && memberProvider.getOutpatient().intValue() == 1){
			   							element.setOutpatient("Y");
			   						}
			   						else {
			   							element.setOutpatient("N");
			   						}
			   						
			   						if (memberProvider.getDental() != null && memberProvider.getDental().intValue() == 1){
			   							element.setDental("Y");
			   						}
			   						else {
			   							element.setDental("N");
			   						}
			   						if (memberProvider.getMaternity() != null && memberProvider.getMaternity().intValue() == 1){
			   							element.setMaternity("Y");
			   						}
			   						else {
			   							element.setMaternity("N");
			   						}
			   						if (memberProvider.getOptical() != null && memberProvider.getOptical().intValue() == 1){
			   							element.setOptical("Y");
			   						}
			   						else {
			   							element.setOptical("N");
			   						}
			   						if (memberProvider.getMcuLab() != null && memberProvider.getMcuLab().intValue() == 1){
			   							element.setLab("Y");
			   						}
			   						else {
			   							element.setLab("N");
			   						}
			   						if (memberProvider.getPpk1() != null && memberProvider.getPpk1().intValue() == 1){
			   							element.setPpk1("Y");
			   						}
			   						else {
			   							element.setPpk1("N");
			   						}
			   						if (memberProvider.getPpk2() != null && memberProvider.getPpk2().intValue() == 1){
			   							element.setPpk2("Y");
			   						}
			   						else {
			   							element.setPpk2("N");
			   						}
			   						if (memberProvider.getPpk3() != null && memberProvider.getPpk3().intValue() == 1){
			   							element.setPpk3("Y");
			   						}
			   						else {
			   							element.setPpk3("N");
			   						}
			   					}
			   				}
			   			}
					}
					
				}
				else if (policyId != null){
					
					DetachedCriteria dc = DetachedCriteria.forClass(PolicyProvider.class);
					dc.setProjection(Property.forName("providerId"));
					dc.add(Property.forName("policyId.policyId").eq(policyId));
					Criteria c = providerDao.getCriteria();
					
					DaoSupportUtil.setIn("providerId", dc, c);
					
					
					result = c.list();
					
					for (Iterator iter = result.iterator(); iter.hasNext();) {
						Provider element = (Provider) iter.next();
			   			
			   			
			   			String[] eqParamMember = {"policyId.policyId","deletedStatus","providerId.providerId"};
			   			Object[] eqValueMember = {policyId,0,element.getProviderId()};
			   			
			   			Collection<PolicyProvider> mp = policyProviderService.search(null,null,eqParamMember,eqValueMember,0,1);
			   			
			   			if (mp != null){
			   				Iterator<PolicyProvider> mpIterator = mp.iterator();
			   				
			   				if (mpIterator.hasNext()){
			   					PolicyProvider memberProvider = mpIterator.next();
			   					if (memberProvider != null){
			   						
			   						if (memberProvider.getInpatient() != null && memberProvider.getInpatient().intValue() == 1){
			   							element.setInpatient("Y");
			   						}
			   						else {
			   							element.setInpatient("N");
			   						}
			   						
			   						if (memberProvider.getOutpatient() != null && memberProvider.getOutpatient().intValue() == 1){
			   							element.setOutpatient("Y");
			   						}
			   						else {
			   							element.setOutpatient("N");
			   						}
			   						
			   						if (memberProvider.getDental() != null && memberProvider.getDental().intValue() == 1){
			   							element.setDental("Y");
			   						}
			   						else {
			   							element.setDental("N");
			   						}
			   						if (memberProvider.getMaternity() != null && memberProvider.getMaternity().intValue() == 1){
			   							element.setMaternity("Y");
			   						}
			   						else {
			   							element.setMaternity("N");
			   						}
			   						if (memberProvider.getOptical() != null && memberProvider.getOptical().intValue() == 1){
			   							element.setOptical("Y");
			   						}
			   						else {
			   							element.setOptical("N");
			   						}
			   						if (memberProvider.getMcuLab() != null && memberProvider.getMcuLab().intValue() == 1){
			   							element.setLab("Y");
			   						}
			   						else {
			   							element.setLab("N");
			   						}
			   						if (memberProvider.getPpk1() != null && memberProvider.getPpk1().intValue() == 1){
			   							element.setPpk1("Y");
			   						}
			   						else {
			   							element.setPpk1("N");
			   						}
			   						if (memberProvider.getPpk2() != null && memberProvider.getPpk2().intValue() == 1){
			   							element.setPpk2("Y");
			   						}
			   						else {
			   							element.setPpk2("N");
			   						}
			   						if (memberProvider.getPpk3() != null && memberProvider.getPpk3().intValue() == 1){
			   							element.setPpk3("Y");
			   						}
			   						else {
			   							element.setPpk3("N");
			   						}
			   					}
			   				}
			   			}
					}
					
				}
				else if (clientId != null){
					DetachedCriteria dc = DetachedCriteria.forClass(ClientProvider.class);
					dc.setProjection(Property.forName("providerId"));
					dc.add(Property.forName("clientId.clientId").eq(clientId));
					Criteria c = providerDao.getCriteria();
					
					
					DaoSupportUtil.setIn("providerId", dc, c);
					
					
					result = c.list();
					
					for (Iterator iter = result.iterator(); iter.hasNext();) {
						Provider element = (Provider) iter.next();
			   			
			   			
			   			String[] eqParamMember = {"clientId.clientId","deletedStatus","providerId.providerId"};
			   			Object[] eqValueMember = {clientId,0,element.getProviderId()};
			   			
			   			Collection<ClientProvider> mp = clientProviderService.search(null,null,eqParamMember,eqValueMember,0,1);
			   			
			   			if (mp != null){
			   				Iterator<ClientProvider> mpIterator = mp.iterator();
			   				
			   				if (mpIterator.hasNext()){
			   					ClientProvider memberProvider = mpIterator.next();
			   					if (memberProvider != null){
			   						
			   						if (memberProvider.getInpatient() != null && memberProvider.getInpatient().intValue() == 1){
			   							element.setInpatient("Y");
			   						}
			   						else {
			   							element.setInpatient("N");
			   						}
			   						
			   						if (memberProvider.getOutpatient() != null && memberProvider.getOutpatient().intValue() == 1){
			   							element.setOutpatient("Y");
			   						}
			   						else {
			   							element.setOutpatient("N");
			   						}
			   						
			   						if (memberProvider.getDental() != null && memberProvider.getDental().intValue() == 1){
			   							element.setDental("Y");
			   						}
			   						else {
			   							element.setDental("N");
			   						}
			   						if (memberProvider.getMaternity() != null && memberProvider.getMaternity().intValue() == 1){
			   							element.setMaternity("Y");
			   						}
			   						else {
			   							element.setMaternity("N");
			   						}
			   						if (memberProvider.getOptical() != null && memberProvider.getOptical().intValue() == 1){
			   							element.setOptical("Y");
			   						}
			   						else {
			   							element.setOptical("N");
			   						}
			   						if (memberProvider.getMcuLab() != null && memberProvider.getMcuLab().intValue() == 1){
			   							element.setLab("Y");
			   						}
			   						else {
			   							element.setLab("N");
			   						}
			   						if (memberProvider.getPpk1() != null && memberProvider.getPpk1().intValue() == 1){
			   							element.setPpk1("Y");
			   						}
			   						else {
			   							element.setPpk1("N");
			   						}
			   						if (memberProvider.getPpk2() != null && memberProvider.getPpk2().intValue() == 1){
			   							element.setPpk2("Y");
			   						}
			   						else {
			   							element.setPpk2("N");
			   						}
			   						if (memberProvider.getPpk3() != null && memberProvider.getPpk3().intValue() == 1){
			   							element.setPpk3("Y");
			   						}
			   						else {
			   							element.setPpk3("N");
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
	public Collection<Provider> searchMemberProvider(String[] likeParam, Object[] likeValue,String[] eqParam, Object[] eqValue,
			Integer memberId,String[] required, Integer index, Integer offset) throws Exception {
			Collection<Provider> result = new Vector<Provider>();
		
		try {
			
			String[] requiredMember = {"Member.CurrentPolicyId"};
			Member member = memberService.get(memberId,requiredMember);			
			
			if (member != null){			
			
				Policy policy = member.getCurrentPolicyId();
				int allocation  = 0;
				if (policy.getProviderAllocationType() != null){
					allocation = policy.getProviderAllocationType();
				}
								
			
				if (allocation == Policy.PROVIDER_MEMBER_USAGE_TYPE){

					DetachedCriteria dc = DetachedCriteria.forClass(MemberProvider.class);
					dc.setProjection(Property.forName("providerId"));
					dc.add(Property.forName("memberId.memberId").eq(memberId));
					Criteria c = providerDao.getCriteria();
					
					DaoSupportUtil.setLikeParam(likeParam, likeValue, c);
					DaoSupportUtil.setEqParam(eqParam, eqValue, c);
					DaoSupportUtil.setIn("providerId", dc, c);
					DaoSupportUtil.setLimit(index,offset, c);
					
					result = c.list();
					
					for (Iterator iter = result.iterator(); iter.hasNext();) {
						Provider element = (Provider) iter.next();
			   			DaoSupportUtil.lazyInit(required,element);
			   			
			   			String[] eqParamMember = {"memberId.memberId","deletedStatus","providerId.providerId"};
			   			Object[] eqValueMember = {member.getMemberId(),0,element.getProviderId()};
			   			
			   			Collection<MemberProvider> mp = memberProviderService.search(null,null,eqParamMember,eqValueMember,0,1);
			   			
			   			if (mp != null){
			   				Iterator<MemberProvider> mpIterator = mp.iterator();
			   				
			   				if (mpIterator.hasNext()){
			   					MemberProvider memberProvider = mpIterator.next();
			   					if (memberProvider != null){
			   						
			   						if (memberProvider.getInpatient() != null && memberProvider.getInpatient().intValue() == 1){
			   							element.setInpatient("Y");
			   						}
			   						else {
			   							element.setInpatient("N");
			   						}
			   						
			   						if (memberProvider.getOutpatient() != null && memberProvider.getOutpatient().intValue() == 1){
			   							element.setOutpatient("Y");
			   						}
			   						else {
			   							element.setOutpatient("N");
			   						}
			   						
			   						if (memberProvider.getDental() != null && memberProvider.getDental().intValue() == 1){
			   							element.setDental("Y");
			   						}
			   						else {
			   							element.setDental("N");
			   						}
			   						if (memberProvider.getMaternity() != null && memberProvider.getMaternity().intValue() == 1){
			   							element.setMaternity("Y");
			   						}
			   						else {
			   							element.setMaternity("N");
			   						}
			   						if (memberProvider.getOptical() != null && memberProvider.getOptical().intValue() == 1){
			   							element.setOptical("Y");
			   						}
			   						else {
			   							element.setOptical("N");
			   						}
			   						if (memberProvider.getMcuLab() != null && memberProvider.getMcuLab().intValue() == 1){
			   							element.setLab("Y");
			   						}
			   						else {
			   							element.setLab("N");
			   						}
			   						if (memberProvider.getPpk1() != null && memberProvider.getPpk1().intValue() == 1){
			   							element.setPpk1("Y");
			   						}
			   						else {
			   							element.setPpk1("N");
			   						}
			   						if (memberProvider.getPpk2() != null && memberProvider.getPpk2().intValue() == 1){
			   							element.setPpk2("Y");
			   						}
			   						else {
			   							element.setPpk2("N");
			   						}
			   						if (memberProvider.getPpk3() != null && memberProvider.getPpk3().intValue() == 1){
			   							element.setPpk3("Y");
			   						}
			   						else {
			   							element.setPpk3("N");
			   						}
			   					}
			   				}
			   			}
			   			
					}
				}
				else if (allocation == Policy.PROVIDER_GROUP_USAGE_TYPE){
					
					DetachedCriteria dc = DetachedCriteria.forClass(MemberGroupProvider.class);
					dc.setProjection(Property.forName("providerId"));
					dc.add(Property.forName("memberGroupId.memberGroupId").eq(member.getMemberGroupId().getMemberGroupId()));
					Criteria c = providerDao.getCriteria();
					
					DaoSupportUtil.setLikeParam(likeParam, likeValue, c);
					DaoSupportUtil.setEqParam(eqParam, eqValue, c);
					DaoSupportUtil.setIn("providerId", dc, c);
					DaoSupportUtil.setLimit(index,offset, c);
					
					result = c.list();
					
					for (Iterator iter = result.iterator(); iter.hasNext();) {
						Provider element = (Provider) iter.next();
			   			DaoSupportUtil.lazyInit(required,element);
			   			
			   			String[] eqParamMember = {"memberGroupId.memberGroupId","deletedStatus","providerId.providerId"};
			   			Object[] eqValueMember = {member.getMemberGroupId().getMemberGroupId(),0,element.getProviderId()};
			   			
			   			Collection<MemberGroupProvider> mp = memberGroupProviderService.search(null,null,eqParamMember,eqValueMember,0,1);
			   			
			   			if (mp != null){
			   				Iterator<MemberGroupProvider> mpIterator = mp.iterator();
			   				
			   				if (mpIterator.hasNext()){
			   					MemberGroupProvider memberProvider = mpIterator.next();
			   					if (memberProvider != null){
			   						
			   						if (memberProvider.getInpatient() != null && memberProvider.getInpatient().intValue() == 1){
			   							element.setInpatient("Y");
			   						}
			   						else {
			   							element.setInpatient("N");
			   						}
			   						
			   						if (memberProvider.getOutpatient() != null && memberProvider.getOutpatient().intValue() == 1){
			   							element.setOutpatient("Y");
			   						}
			   						else {
			   							element.setOutpatient("N");
			   						}
			   						
			   						if (memberProvider.getDental() != null && memberProvider.getDental().intValue() == 1){
			   							element.setDental("Y");
			   						}
			   						else {
			   							element.setDental("N");
			   						}
			   						if (memberProvider.getMaternity() != null && memberProvider.getMaternity().intValue() == 1){
			   							element.setMaternity("Y");
			   						}
			   						else {
			   							element.setMaternity("N");
			   						}
			   						if (memberProvider.getOptical() != null && memberProvider.getOptical().intValue() == 1){
			   							element.setOptical("Y");
			   						}
			   						else {
			   							element.setOptical("N");
			   						}
			   						if (memberProvider.getMcuLab() != null && memberProvider.getMcuLab().intValue() == 1){
			   							element.setLab("Y");
			   						}
			   						else {
			   							element.setLab("N");
			   						}
			   						if (memberProvider.getPpk1() != null && memberProvider.getPpk1().intValue() == 1){
			   							element.setPpk1("Y");
			   						}
			   						else {
			   							element.setPpk1("N");
			   						}
			   						if (memberProvider.getPpk2() != null && memberProvider.getPpk2().intValue() == 1){
			   							element.setPpk2("Y");
			   						}
			   						else {
			   							element.setPpk2("N");
			   						}
			   						if (memberProvider.getPpk3() != null && memberProvider.getPpk3().intValue() == 1){
			   							element.setPpk3("Y");
			   						}
			   						else {
			   							element.setPpk3("N");
			   						}
			   					}
			   				}
			   			}
					}
					
				}
				else if (allocation == Policy.PROVIDER_POLICY_USAGE_TYPE){
					
					DetachedCriteria dc = DetachedCriteria.forClass(PolicyProvider.class);
					dc.setProjection(Property.forName("providerId"));
					dc.add(Property.forName("policyId.policyId").eq(policy.getPolicyId()));
					Criteria c = providerDao.getCriteria();
					
					DaoSupportUtil.setLikeParam(likeParam, likeValue, c);
					DaoSupportUtil.setEqParam(eqParam, eqValue, c);
					DaoSupportUtil.setIn("providerId", dc, c);
					DaoSupportUtil.setLimit(index,offset, c);
					
					result = c.list();
					
					for (Iterator iter = result.iterator(); iter.hasNext();) {
						Provider element = (Provider) iter.next();
			   			DaoSupportUtil.lazyInit(required,element);
			   			
			   			String[] eqParamMember = {"policyId.policyId","deletedStatus","providerId.providerId"};
			   			Object[] eqValueMember = {policy.getPolicyId(),0,element.getProviderId()};
			   			
			   			Collection<PolicyProvider> mp = policyProviderService.search(null,null,eqParamMember,eqValueMember,0,1);
			   			
			   			if (mp != null){
			   				Iterator<PolicyProvider> mpIterator = mp.iterator();
			   				
			   				if (mpIterator.hasNext()){
			   					PolicyProvider memberProvider = mpIterator.next();
			   					if (memberProvider != null){
			   						
			   						if (memberProvider.getInpatient() != null && memberProvider.getInpatient().intValue() == 1){
			   							element.setInpatient("Y");
			   						}
			   						else {
			   							element.setInpatient("N");
			   						}
			   						
			   						if (memberProvider.getOutpatient() != null && memberProvider.getOutpatient().intValue() == 1){
			   							element.setOutpatient("Y");
			   						}
			   						else {
			   							element.setOutpatient("N");
			   						}
			   						
			   						if (memberProvider.getDental() != null && memberProvider.getDental().intValue() == 1){
			   							element.setDental("Y");
			   						}
			   						else {
			   							element.setDental("N");
			   						}
			   						if (memberProvider.getMaternity() != null && memberProvider.getMaternity().intValue() == 1){
			   							element.setMaternity("Y");
			   						}
			   						else {
			   							element.setMaternity("N");
			   						}
			   						if (memberProvider.getOptical() != null && memberProvider.getOptical().intValue() == 1){
			   							element.setOptical("Y");
			   						}
			   						else {
			   							element.setOptical("N");
			   						}
			   						if (memberProvider.getMcuLab() != null && memberProvider.getMcuLab().intValue() == 1){
			   							element.setLab("Y");
			   						}
			   						else {
			   							element.setLab("N");
			   						}
			   						if (memberProvider.getPpk1() != null && memberProvider.getPpk1().intValue() == 1){
			   							element.setPpk1("Y");
			   						}
			   						else {
			   							element.setPpk1("N");
			   						}
			   						if (memberProvider.getPpk2() != null && memberProvider.getPpk2().intValue() == 1){
			   							element.setPpk2("Y");
			   						}
			   						else {
			   							element.setPpk2("N");
			   						}
			   						if (memberProvider.getPpk3() != null && memberProvider.getPpk3().intValue() == 1){
			   							element.setPpk3("Y");
			   						}
			   						else {
			   							element.setPpk3("N");
			   						}
			   					}
			   				}
			   			}
					}
					
				}
				else if (allocation == Policy.PROVIDER_CLIENT_USAGE_TYPE){
					DetachedCriteria dc = DetachedCriteria.forClass(ClientProvider.class);
					dc.setProjection(Property.forName("providerId"));
					dc.add(Property.forName("clientId.clientId").eq(policy.getClientId().getClientId()));
					Criteria c = providerDao.getCriteria();
					
					DaoSupportUtil.setLikeParam(likeParam, likeValue, c);
					DaoSupportUtil.setEqParam(eqParam, eqValue, c);
					DaoSupportUtil.setIn("providerId", dc, c);
					DaoSupportUtil.setLimit(index,offset, c);
					
					result = c.list();
					
					for (Iterator iter = result.iterator(); iter.hasNext();) {
						Provider element = (Provider) iter.next();
			   			DaoSupportUtil.lazyInit(required,element);
			   			
			   			String[] eqParamMember = {"clientId.clientId","deletedStatus","providerId.providerId"};
			   			Object[] eqValueMember = {policy.getClientId().getClientId(),0,element.getProviderId()};
			   			
			   			Collection<ClientProvider> mp = clientProviderService.search(null,null,eqParamMember,eqValueMember,0,1);
			   			
			   			if (mp != null){
			   				Iterator<ClientProvider> mpIterator = mp.iterator();
			   				
			   				if (mpIterator.hasNext()){
			   					ClientProvider memberProvider = mpIterator.next();
			   					if (memberProvider != null){
			   						
			   						if (memberProvider.getInpatient() != null && memberProvider.getInpatient().intValue() == 1){
			   							element.setInpatient("Y");
			   						}
			   						else {
			   							element.setInpatient("N");
			   						}
			   						
			   						if (memberProvider.getOutpatient() != null && memberProvider.getOutpatient().intValue() == 1){
			   							element.setOutpatient("Y");
			   						}
			   						else {
			   							element.setOutpatient("N");
			   						}
			   						
			   						if (memberProvider.getDental() != null && memberProvider.getDental().intValue() == 1){
			   							element.setDental("Y");
			   						}
			   						else {
			   							element.setDental("N");
			   						}
			   						if (memberProvider.getMaternity() != null && memberProvider.getMaternity().intValue() == 1){
			   							element.setMaternity("Y");
			   						}
			   						else {
			   							element.setMaternity("N");
			   						}
			   						if (memberProvider.getOptical() != null && memberProvider.getOptical().intValue() == 1){
			   							element.setOptical("Y");
			   						}
			   						else {
			   							element.setOptical("N");
			   						}
			   						if (memberProvider.getMcuLab() != null && memberProvider.getMcuLab().intValue() == 1){
			   							element.setLab("Y");
			   						}
			   						else {
			   							element.setLab("N");
			   						}
			   						if (memberProvider.getPpk1() != null && memberProvider.getPpk1().intValue() == 1){
			   							element.setPpk1("Y");
			   						}
			   						else {
			   							element.setPpk1("N");
			   						}
			   						if (memberProvider.getPpk2() != null && memberProvider.getPpk2().intValue() == 1){
			   							element.setPpk2("Y");
			   						}
			   						else {
			   							element.setPpk2("N");
			   						}
			   						if (memberProvider.getPpk3() != null && memberProvider.getPpk3().intValue() == 1){
			   							element.setPpk3("Y");
			   						}
			   						else {
			   							element.setPpk3("N");
			   						}
			   					}
			   				}
			   			}
					}					
				}
				else {
					Criteria c = providerDao.getCriteria();
					DaoSupportUtil.setLikeParam(likeParam, likeValue, c);
					DaoSupportUtil.setEqParam(eqParam, eqValue, c);					
					DaoSupportUtil.setLimit(index,offset, c);
					
					result = c.list();
					
					for (Iterator iter = result.iterator(); iter.hasNext();) {
						Provider element = (Provider) iter.next();
			   			DaoSupportUtil.lazyInit(required,element);
			   			
						
						element.setInpatient("Y");
						element.setOutpatient("Y");
						element.setDental("Y");
						element.setMaternity("Y");
						element.setOptical("Y");
						element.setLab("Y");
						element.setPpk1("Y");
						element.setPpk2("Y");   						
						element.setPpk3("Y");
   						
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
	public int getTotalMemberProvider(String[] likeParam, Object[] likeValue,
			String[] eqParam, Object[] eqValue, Integer memberId)
			throws Exception {
		int result = 0;
		
		try {
			
			String[] required = {"Member.CurrentPolicyId"};
			Member member = memberService.get(memberId,required);

			
			
			if (member != null){
			
			
				Policy policy = member.getCurrentPolicyId();
				int allocation  = 0;
				if (policy.getProviderAllocationType() != null){
					allocation = policy.getProviderAllocationType();
				}
								
			
				if (allocation == Policy.PROVIDER_MEMBER_USAGE_TYPE){

					DetachedCriteria dc = DetachedCriteria.forClass(MemberProvider.class);
					dc.setProjection(Property.forName("providerId"));
					dc.add(Property.forName("memberId.memberId").eq(memberId));
					Criteria c = providerDao.getCriteria();
					
					DaoSupportUtil.setLikeParam(likeParam, likeValue, c);
					DaoSupportUtil.setEqParam(eqParam, eqValue, c);
					DaoSupportUtil.setIn("providerId", dc, c);
					
					
					result = DaoSupportUtil.getTotal(c);
				}
				else if (allocation == Policy.PROVIDER_GROUP_USAGE_TYPE){
					
					DetachedCriteria dc = DetachedCriteria.forClass(PolicyProvider.class);
					dc.setProjection(Property.forName("providerId"));
					dc.add(Property.forName("policyId.policyId").eq(policy.getPolicyId()));
					Criteria c = providerDao.getCriteria();
					
					DaoSupportUtil.setLikeParam(likeParam, likeValue, c);
					DaoSupportUtil.setEqParam(eqParam, eqValue, c);
					DaoSupportUtil.setIn("providerId", dc, c);
					
					result = DaoSupportUtil.getTotal(c);
				}
				else if (allocation == Policy.PROVIDER_CLIENT_USAGE_TYPE){
					DetachedCriteria dc = DetachedCriteria.forClass(ClientProvider.class);
					dc.setProjection(Property.forName("providerId"));
					dc.add(Property.forName("clientId.clientId").eq(policy.getClientId().getClientId()));
					Criteria c = providerDao.getCriteria();
					
					DaoSupportUtil.setLikeParam(likeParam, likeValue, c);
					DaoSupportUtil.setEqParam(eqParam, eqValue, c);
					DaoSupportUtil.setIn("providerId", dc, c);
					
					result = DaoSupportUtil.getTotal(c);
				}
				else {
					Criteria c = providerDao.getCriteria();
					DaoSupportUtil.setLikeParam(likeParam, likeValue, c);
					DaoSupportUtil.setEqParam(eqParam, eqValue, c);
					
					
					result = DaoSupportUtil.getTotal(c);
				}
			}			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public Collection<Provider> searchContactPersonByName (String[] likeColumns, Object[] likeParams, String[] Name, Object[] oName ) throws Exception{

		Criteria c = providerDao.getCriteria();
		DaoSupportUtil.setEqParam(likeColumns, likeParams, c);
		DaoSupportUtil.setLikeParam(Name, oName, c);
		List list = c.list();
		
		return list;

	}
	@Override
	public boolean blockProvider(Integer providerId, String reason,
			ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		
		try {
			Provider provider = get(providerId);
			
			if (provider != null){
			
				SubscriptionStatus status = new SubscriptionStatus();
				status.setStatusId(SubscriptionStatus.BLOCKED);
				
				provider.setStatusId(status);
				
				
				update(provider,actionUser);
				
				ProviderHistory history = new ProviderHistory();
				history.setProviderId(provider);
				history.setActionTime(new java.sql.Timestamp(System.currentTimeMillis()));
				history.setDescription(reason);
				history.setActionType("BLOCK PROVIDER");
				history.setStatus(SubscriptionStatus.BLOCKED);
				
				providerHistoryService.create(history, actionUser);
				
				result = true;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	@Override
	public boolean unBlockProvider(Integer providerId, String reason,
			ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		try {
			Provider provider = get(providerId);
			
			if (provider != null){
			
				SubscriptionStatus status = new SubscriptionStatus();
				status.setStatusId(SubscriptionStatus.ACTIVE);
				
				provider.setStatusId(status);
				
				update(provider,actionUser);
				
				ProviderHistory history = new ProviderHistory();
				history.setProviderId(provider);
				history.setActionTime(new java.sql.Timestamp(System.currentTimeMillis()));
				history.setDescription(reason);
				history.setActionType("UNBLOCK PROVIDER");
				history.setStatus(SubscriptionStatus.ACTIVE);
				
				providerHistoryService.create(history, actionUser);
				
				result = true;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
}
