
package com.ametis.cms.service.impl;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.joda.time.DateTime;

import com.ametis.cms.dao.FirstCallDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.FirstCall;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.FirstCallService;
import com.ametis.cms.service.NumberCounterService;
import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 


/**
 * FirstCall is a servlet controller for first_call Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class FirstCallServiceImpl implements FirstCallService

// extends+ 

// extends- 

{
	
	private FirstCallDao  firstCallDao;
	private ActivityLogService activityLogService;
	private ClientService clientService;
	private NumberCounterService numberCounterService;
	private ConfigurationService configurationService;
	
	
	
	
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	public NumberCounterService getNumberCounterService() {
		return numberCounterService;
	}
	public void setNumberCounterService(NumberCounterService numberCounterService) {
		this.numberCounterService = numberCounterService;
	}
	public ClientService getClientService() {
		return clientService;
	}
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}
	public void setFirstCallDao (FirstCallDao object){
		this.firstCallDao = object;
	}
	public FirstCallDao getFirstCallDao (){
		return this.firstCallDao;
	}
	/*
	* Method create (FirstCall object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	private String generateCallCounterNumber (int res){
		
		String result = "";
		
		
		
		
		if (res > 0 && res < 10){
			result = "0000"+res;
		}
		else if (res >= 10 && res < 100){
			result = "000"+res;
		}
		else if (res >= 100 && res < 1000){
			result = "00"+res;
		}
		else if (res >= 1000 && res < 10000){
			result = "0"+res;
		}
		else if (res >= 10000){
			result = ""+res;
		}
			
		
		return result;
	}
	public FirstCall create (FirstCall object,ActionUser actionUser) throws Exception {
			
			
			
			object.setDeletedStatus(Integer.valueOf(0));

			DateTime dateTime = new DateTime();
			int number = 0;
			
			
			Configuration configuration = configurationService.getSystemConfiguration();
			
			 Integer isUsingSequence = configuration.getIsUsingSequenceNumber();
				
				if (isUsingSequence != null && isUsingSequence.intValue() == 1){
					String seqSQL = configuration.getCallNumberSeqName();
					
					Session session = firstCallDao.getCallSession();
					if (session != null){
						SQLQuery q = session.createSQLQuery(seqSQL);
						
						List<Object> list = q.list();
						
						if (list != null && !list.isEmpty()){
							Iterator<Object> iterator = list.iterator();
							
							if (iterator.hasNext()){
								Object nextval = iterator.next();
								
								if (nextval != null){
									
									
									BigInteger num = (BigInteger) nextval;
									if (num != null){
										number = num.intValue();
									}
								}
							}
						}
					}
				}
				else {
					number = configuration.getCallNumber();
				}
			 

			String counter = generateCallCounterNumber(number);
			
			String month = "";
			
			if (dateTime.getMonthOfYear() < 10){
				month += "0"+dateTime.getMonthOfYear();
			}
			else {
				month += dateTime.getMonthOfYear();
			}
			
			if (actionUser != null){
				
				User user = actionUser.getUser();					
				if (user != null){
					object.setCreatedBy(user.getUsername());
				}
			}
			
			String description = object.getDescription();
			String remarks = object.getRemarks();
			
			if (description != null){
				//description = description.replaceAll("(\r\n|\n)", "<br />");
				object.setDescription(description);
			}
			if (remarks != null){
				//remarks = remarks.replaceAll("(\r\n|\n)", "<br />");
				object.setRemarks(remarks);
			}
			
			Configuration config = 	configurationService.getSystemConfiguration();
			
			String callNumber = config.getCallNumberTemplate();
			callNumber = callNumber.replace("${counter}", counter);
			callNumber = callNumber.replace("${month}",month);
			callNumber = callNumber.replace("${year}", dateTime.getYear()+"");
			
			 
			object.setCallNumber(callNumber);
            
            config.setCallNumber(Integer.valueOf(counter) + 1);
            configurationService.update(config, null);
            
			object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));

			FirstCall result = firstCallDao.create (object);
			return result;
		}
	/*
	* Method update (FirstCall object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public FirstCall update (FirstCall object,ActionUser actionUser) throws Exception{
		
		
//		object.setUpdatedDate (new java.sql.Date (System.currentTimeMillis()));

				
				
		
					
				
				
		
					object.setModifiedTime (new java.sql.Timestamp (System.currentTimeMillis()));
		

			String description = object.getDescription();
			String remarks = object.getRemarks();
			
			if (description != null){
				//description = description.replaceAll("(\r\n|\n)", "<br />");
				object.setDescription(description);
			}
			if (remarks != null){
				//remarks = remarks.replaceAll("(\r\n|\n)", "<br />");
				object.setRemarks(remarks);
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
		
 		firstCallDao.update (object);
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
	public FirstCall trash (java.io.Serializable pkey) throws Exception {
		FirstCall object = firstCallDao.get (pkey);
		firstCallDao.delete (object);
		return object;
	}

	/*
	* Method delete (FirstCall object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public FirstCall delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception{
		FirstCall object = firstCallDao.get (pkey);


		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		
		
		
		object.setDeletedStatus(new Integer(1));

		


if (actionUser != null){
			
			User user = actionUser.getUser();	
			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
}

		
		firstCallDao.update (object);
		return object;
	}


	/*
	* Method delete (FirstCall object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public FirstCall delete (FirstCall object,ActionUser actionUser) throws Exception{
		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		

		
		
		object.setDeletedStatus(new Integer(1));

		

if (actionUser != null){
			
			User user = actionUser.getUser();	
			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
}

		
		firstCallDao.update (object);
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
	public FirstCall get (java.io.Serializable pkey) throws Exception{
		FirstCall object = null;
		object = firstCallDao.get(pkey);
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

	public FirstCall get (java.io.Serializable pkey, String[] required) throws Exception{
	    FirstCall object = null;
	    object = firstCallDao.get(pkey);
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

		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FirstCall element = (FirstCall) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = firstCallDao.getCriteria();
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
		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FirstCall element = (FirstCall) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = firstCallDao.getCriteria();
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
		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FirstCall element = (FirstCall) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = firstCallDao.getCriteria();
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
		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FirstCall element = (FirstCall) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = firstCallDao.getCriteria();
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

		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FirstCall element = (FirstCall) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = firstCallDao.getCriteria();
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

		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FirstCall element = (FirstCall) iter.next();
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

		Criteria c = firstCallDao.getCriteria();
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

		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FirstCall element = (FirstCall) iter.next();
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

		Criteria c = firstCallDao.getCriteria();
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

		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FirstCall element = (FirstCall) iter.next();
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

		Criteria c = firstCallDao.getCriteria();
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

		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FirstCall element = (FirstCall) iter.next();
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

		Criteria c = firstCallDao.getCriteria();
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

		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FirstCall element = (FirstCall) iter.next();
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

		Criteria c = firstCallDao.getCriteria();
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

		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FirstCall element = (FirstCall) iter.next();
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

		Criteria c = firstCallDao.getCriteria();
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

		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FirstCall element = (FirstCall) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = firstCallDao.getCriteria();
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

		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = firstCallDao.getCriteria();
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

		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = firstCallDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = firstCallDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FirstCall element = (FirstCall) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = firstCallDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public FirstCall searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		FirstCall obj = (FirstCall) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public FirstCall searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = firstCallDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		FirstCall obj = (FirstCall) c.uniqueResult();
		return obj;
	}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = firstCallDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = firstCallDao.getDetachedCriteria();
		return dc;
	}
	
	public int getTotalFollowUpNeededCall() throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Session session = firstCallDao.getCallSession();
			
			if (session != null){
				
				String querySQL = "SELECT count(call_id) FROM first_call WHERE status = :status AND need_followup = :followup " +
						"AND deleted_status = 0";
				
				/**
				String query = "SELECT count(c) FROM FirstCall c WHERE c.status.caseStatusId = :status AND " +
						"c.followup = :followup AND c.deletedStatus = 0";
						*/
				
				org.hibernate.SQLQuery q = session.createSQLQuery(querySQL);
				q.setInteger("status",CaseStatus.CASE_OPEN);
				q.setString("followup", "Y");
				Number num = (Number) q.uniqueResult();
				result = num.intValue();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int getTotalFollowUpNeededCall(int userId) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Session session = firstCallDao.getCallSession();
			
			if (session != null){
				String sqlQuery = "SELECT count(call_id) FROM first_call WHERE status = :status AND" +
						" need_followup = :followup AND handledBy = :handled";
				
				/**String query = "SELECT count(c) FROM FirstCall c WHERE c.status.caseStatusId = :status AND" +
				" c.followup = :followup AND c.handledBy.userId = :handled";*/
				
				org.hibernate.SQLQuery q = session.createSQLQuery(sqlQuery);
				q.setInteger("status",CaseStatus.CASE_OPEN);
				q.setString("followup", "Y");
				q.setInteger("handled", userId);
				Number num = (Number) q.uniqueResult();
				result = num.intValue();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int getTotalFollowUpErrorLogByLogType(Integer callLogType) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Session session = firstCallDao.getCallSession();
			
			if (session != null){
				
				String querySQL = "SELECT count(call_id) FROM first_call WHERE call_log_type = :callLogType AND " +
						"need_followup = 'Y' AND status = " +CaseStatus.CASE_OPEN +
						" AND deleted_status = 0";
				
				org.hibernate.SQLQuery q = session.createSQLQuery(querySQL);
				q.setInteger("callLogType", callLogType);
				Number num = (Number) q.uniqueResult();
				result = num.intValue();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int getTotalMemberErrorLog(Integer memberId, Integer callLogType) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Session session = firstCallDao.getCallSession();
			
			if (session != null){
				
				String querySQL = "SELECT count(call_id) FROM first_call WHERE customer_id = :memberId AND call_log_type = :callLogType AND " +
						"priority = 1 " +
						"AND deleted_status = 0";
				
				org.hibernate.SQLQuery q = session.createSQLQuery(querySQL);
				q.setInteger("memberId", memberId);
				q.setInteger("callLogType", callLogType);
				Number num = (Number) q.uniqueResult();
				result = num.intValue();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int getTotalCaseErrorLog(Integer caseId, Integer callLogType) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Session session = firstCallDao.getCallSession();
			
			if (session != null){
				
				String querySQL = "SELECT count(call_id) FROM first_call WHERE case_id = :caseId AND call_log_type = :callLogType AND " +
						"priority = 1 " +
						"AND deleted_status = 0";
				
				org.hibernate.SQLQuery q = session.createSQLQuery(querySQL);
				q.setInteger("caseId", caseId);
				q.setInteger("callLogType", callLogType);
				Number num = (Number) q.uniqueResult();
				result = num.intValue();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int getTotalClaimErrorLog(Integer claimId, Integer callLogType) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Session session = firstCallDao.getCallSession();
			
			if (session != null){
				
				String querySQL = "SELECT count(call_id) FROM first_call WHERE claim_id = :claimId AND call_log_type = :callLogType AND " +
						"priority = 1 " +
						"AND deleted_status = 0";
				
				org.hibernate.SQLQuery q = session.createSQLQuery(querySQL);
				q.setInteger("claimId", claimId);
				q.setInteger("callLogType", callLogType);
				Number num = (Number) q.uniqueResult();
				result = num.intValue();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	public boolean close(Serializable id, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		
		try {
			FirstCall call = get(id);
			if (call != null){
				call.setStatus(new CaseStatus(CaseStatus.CASE_CLOSED));
				call.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
				call.setModifiedBy(actionUser.getUser().getUsername());
				firstCallDao.update(call);
				result = true;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


// class+ 

// class- 

}
