
package com.ametis.cms.service.impl;


import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.joda.time.DateTime;

import com.ametis.cms.dao.RejectedClaimDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.CostContainment;
import com.ametis.cms.datamodel.CostContainmentType;
import com.ametis.cms.datamodel.Fund;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MessageTemplate;
import com.ametis.cms.datamodel.Notification;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.RejectedClaim;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClaimHistoryService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.CostContainmentService;
import com.ametis.cms.service.NotificationService;
import com.ametis.cms.service.RejectedClaimService;
import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 


/**
 * RejectedClaim is a servlet controller for rejected_claim Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class RejectedClaimServiceImpl implements RejectedClaimService

// extends+ 

// extends- 

{
	
	private RejectedClaimDao  rejectedClaimDao;
	private ClaimService claimService;
	private CostContainmentService costContainmentService;
	private ClaimHistoryService claimHistoryService;

private ActivityLogService activityLogService;
	private ConfigurationService configurationService;
	
	
	private ClientService clientService;
	private BatchClaimService batchClaimService;
	public BatchClaimService getBatchClaimService() {
		return batchClaimService;
	}
	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}
	public NotificationService getNotificationService() {
		return notificationService;
	}
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	private NotificationService notificationService;
	
	
	
	public ClaimHistoryService getClaimHistoryService() {
		return claimHistoryService;
	}
	public void setClaimHistoryService(ClaimHistoryService claimHistoryService) {
		this.claimHistoryService = claimHistoryService;
	}
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}
	public CostContainmentService getCostContainmentService() {
		return costContainmentService;
	}
	public void setCostContainmentService(
			CostContainmentService costContainmentService) {
		this.costContainmentService = costContainmentService;
	}
	public ClaimService getClaimService() {
		return claimService;
	}
	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}
	public void setRejectedClaimDao (RejectedClaimDao object){
		this.rejectedClaimDao = object;
	}
	public RejectedClaimDao getRejectedClaimDao (){
		return this.rejectedClaimDao;
	}
	
	public ClientService getClientService() {
		return clientService;
	}
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	/*
	* Method create (RejectedClaim object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public RejectedClaim create (RejectedClaim object,ActionUser actionUser) throws Exception {

		int numberCounter = 0;
		
		String[] required = {"Claim.MemberId","Claim.MemberId.ClientId,Claim.MemberId.CurrentPolicyId"};
		
		Claim claim = claimService.get(object.getClaimId().getClaimId(),required);
		
		Configuration configuration = configurationService.getClientConfiguration(claim.getMemberId().getClientId().getClientId());
		 
		 if (configuration == null){
			 configuration = configurationService.getSystemConfiguration();
		 }
		 
		 Integer isUsingSequence = configuration.getIsUsingSequenceNumber();
			
			if (isUsingSequence != null && isUsingSequence.intValue() == 1){
				String seqSQL = configuration.getClaimRejectSeqName();
				
				Session session = rejectedClaimDao.getRejectionSession();
				
				if (session != null){
					SQLQuery q = session.createSQLQuery(seqSQL);
					
					List<Object> list = q.list();
					
					if (list != null && !list.isEmpty()){
						Iterator<Object> iterator = list.iterator();
						
						if (iterator.hasNext()){
							Object nextval = iterator.next();
							
							if (nextval != null){
								BigInteger val = (BigInteger) nextval;
								numberCounter = val.intValue();
							}
						}
					}
				}
			}
			else {
				numberCounter = configuration.getClaimRejectionNumber();
			}
		
		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setClaimNumber(claim.getClaimNumber());
		object.setMemberId(claim.getMemberId());
		

		// ----
		RejectedClaim result = null; 	
	
		if (object != null){
			
		
			object.setDeletedStatus(Integer.valueOf(0));
			if (actionUser != null){
				
				User user = actionUser.getUser();		
				if (user != null){
					object.setCreatedBy(user.getUsername());
				}
			}
			
			DateTime dateTime = new DateTime();
			

			int month = dateTime.getMonthOfYear();
			String monthStr = "";
			if (month < 10) {
				monthStr = "0"+month;
			}
			else {
				monthStr = month+"";
			}
			
			String number = configuration.getClaimRejectionNumberTemplate();
			String counter = generateCounterNumber(numberCounter);

			number = number.replace("${counter}", counter);
			number = number.replace("${month}", monthStr);
			number = number.replace("${year}", dateTime.getYear()+"");
			
			object.setRejectionNumber(number);
			
			result = rejectedClaimDao.create (object);
			
			if (claim != null){
				
				claim.setRejectionRemarks(object.getDescription());
								
				claimService.reject(claim,actionUser);
				
				CostContainment costContainment = new CostContainment();
				costContainment.setClaimId(claim);
				costContainment.setCostContainmentValue(claim.getClaimChargeValue());
				
				CostContainmentType costContainmentType = new CostContainmentType();
				costContainmentType.setCostContainmentTypeId(Integer.valueOf(CostContainmentType.CLAIM_REJECTION));
				
				costContainment.setCostContainmentType(costContainmentType);
				costContainment.setClientId(claim.getMemberId().getClientId());
				
				costContainmentService.create(costContainment,actionUser);
				
				
				//Riyan Distianda:kirim email
				
				
				String[] required2 = {"BatchClaim.MemberId","BatchClaim.ProviderId","BatchClaim.MemberGroupId"};
				
				BatchClaim theBatch= batchClaimService.get(claim.getBatchClaimId().getBatchClaimId(),required2);
				
//				config default
//				Configuration configuration2 = configurationService.getClientConfiguration(theBatch.getClientId().getClientId());
				
				
				Configuration configuration2 = configurationService.getSystemConfiguration();
				
				if (configuration2 != null && configuration2.getIsUsingClientNotification() != null){ 
					if (configuration2.getIsUsingClientNotification().intValue() ==1 ){
if (theBatch.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER){
							
							String destination = "";
							MessageTemplate template = new MessageTemplate();
							
							Notification notification = new Notification();
							
						
							if (theBatch.getMemberId() != null && theBatch.getMemberId().getEmail() != null ){
								destination = theBatch.getMemberId().getEmail();
								template.setId(MessageTemplate.MEMBER_CLAIM_PAID_EMAIL );
								notification.setMemberId(theBatch.getMemberId());	
							}
							
							
							notification.setBatchClaimId(theBatch);
							notification.setTemplateId(template);
							notification.setDestination(destination);
							notification.setStatus(0);
							notification.setMessageType(Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL);
							notification.setSender("system-daemon");
							
							if(notification.getDestination()!=null){
								
							
							notificationService.createReject(notification, actionUser, object,claim,theBatch);
							}
						}
						
							if (theBatch.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER_GROUP){
							
							String destination = "";
							MessageTemplate template = new MessageTemplate();
							
							Notification notification = new Notification();
							
						
							if (theBatch.getMemberGroupId() != null && theBatch.getMemberGroupId().getEmail() != null){
								destination = theBatch.getMemberGroupId().getEmail();
								template.setId(MessageTemplate.MEMBER_CLAIM_PAID_EMAIL );
								notification.setMemberId(theBatch.getMemberId());	
							}
							
							
							notification.setBatchClaimId(theBatch);
							notification.setTemplateId(template);
							notification.setDestination(destination);
							notification.setStatus(0);
							notification.setMessageType(Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL);
							notification.setSender("system-daemon");
							
							if(notification.getDestination()!=null){
								
								
								notificationService.createReject(notification, actionUser, object,claim,theBatch);
								}
				
						}
							if (theBatch.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.PROVIDER){
								
								String destination = "";
								MessageTemplate template = new MessageTemplate();
								
								Notification notification = new Notification();
								
							
								if (theBatch.getProviderId() != null && theBatch.getProviderId().getEmail() != null){
									destination = theBatch.getProviderId().getEmail();
									template.setId(MessageTemplate.MEMBER_CLAIM_PAID_EMAIL );
									notification.setMemberId(theBatch.getMemberId());	
								}
								
								
								notification.setBatchClaimId(theBatch);
								notification.setTemplateId(template);
								notification.setDestination(destination);
								notification.setStatus(0);
								notification.setMessageType(Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL);
								notification.setSender("system-daemon");
								
								if(notification.getDestination()!=null){
									
									
									notificationService.createReject(notification, actionUser, object,claim,theBatch);
									}
					
							}
					}
				}
			
			
				
				
				
				
			}
	

			configuration.setClaimRejectionNumber(configuration.getClaimRejectionNumber()+1);
			configurationService.update(configuration, actionUser);
				
							
				
			}
			return result;
		}
	private String generateCounterNumber (int res){
		
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
	/*
	* Method update (RejectedClaim object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public RejectedClaim update (RejectedClaim object,ActionUser actionUser) throws Exception{
		
		
//		object.setUpdatedDate (new java.sql.Date (System.currentTimeMillis()));

				
				
	if (actionUser != null){
				
				
		User user = actionUser.getUser();
		if (user != null){	
				object.setUpdatedTime (new java.sql.Timestamp (System.currentTimeMillis()));
					
					
			
						
			
	
			
			
			object.setUpdatedBy(user.getUsername());
		}
	}	
				
				
		
 		rejectedClaimDao.update (object);
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
	public RejectedClaim trash (java.io.Serializable pkey) throws Exception {
		RejectedClaim object = rejectedClaimDao.get (pkey);
		rejectedClaimDao.delete (object);
		return object;
	}

	/*
	* Method delete (RejectedClaim object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public RejectedClaim delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception{
		RejectedClaim object = rejectedClaimDao.get (pkey);


		
		
		object.setDeletedTime(new java.sql.Timestamp (System.currentTimeMillis()));

		
		
		
		
		object.setDeletedStatus(new Integer(1));

		


		
if (actionUser != null){
			
			User user = actionUser.getUser();			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
}
		
		rejectedClaimDao.update (object);
		return object;
	}


	/*
	* Method delete (RejectedClaim object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public RejectedClaim delete (RejectedClaim object,ActionUser actionUser) throws Exception{
		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		

		
		
		object.setDeletedStatus(new Integer(1));

		

		
if (actionUser != null){
			
			User user = actionUser.getUser();			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
}
		
		rejectedClaimDao.update (object);
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
	public RejectedClaim get (java.io.Serializable pkey) throws Exception{
		RejectedClaim object = null;
		object = rejectedClaimDao.get(pkey);
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

	public RejectedClaim get (java.io.Serializable pkey, String[] required) throws Exception{
	    RejectedClaim object = null;
	    object = rejectedClaimDao.get(pkey);
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

		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedClaim element = (RejectedClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = rejectedClaimDao.getCriteria();
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
		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedClaim element = (RejectedClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = rejectedClaimDao.getCriteria();
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
		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedClaim element = (RejectedClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = rejectedClaimDao.getCriteria();
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
		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedClaim element = (RejectedClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = rejectedClaimDao.getCriteria();
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

		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedClaim element = (RejectedClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = rejectedClaimDao.getCriteria();
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

		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedClaim element = (RejectedClaim) iter.next();
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

		Criteria c = rejectedClaimDao.getCriteria();
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

		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedClaim element = (RejectedClaim) iter.next();
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

		Criteria c = rejectedClaimDao.getCriteria();
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

		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedClaim element = (RejectedClaim) iter.next();
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

		Criteria c = rejectedClaimDao.getCriteria();
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

		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedClaim element = (RejectedClaim) iter.next();
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

		Criteria c = rejectedClaimDao.getCriteria();
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

		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedClaim element = (RejectedClaim) iter.next();
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

		Criteria c = rejectedClaimDao.getCriteria();
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

		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedClaim element = (RejectedClaim) iter.next();
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

		Criteria c = rejectedClaimDao.getCriteria();
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

		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedClaim element = (RejectedClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = rejectedClaimDao.getCriteria();
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

		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = rejectedClaimDao.getCriteria();
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

		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = rejectedClaimDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = rejectedClaimDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedClaim element = (RejectedClaim) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = rejectedClaimDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public RejectedClaim searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		RejectedClaim obj = (RejectedClaim) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public RejectedClaim searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = rejectedClaimDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		RejectedClaim obj = (RejectedClaim) c.uniqueResult();
		return obj;
	}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = rejectedClaimDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = rejectedClaimDao.getDetachedCriteria();
		return dc;
	}




// class+ 

// class- 

}
