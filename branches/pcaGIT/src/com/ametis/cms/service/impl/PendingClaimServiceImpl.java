package com.ametis.cms.service.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.joda.time.DateTime;

import com.ametis.cms.dao.PendingClaimDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.MessageTemplate;
import com.ametis.cms.datamodel.Notification;
import com.ametis.cms.datamodel.NumberCounter;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.PendingCategory;
import com.ametis.cms.datamodel.PendingClaim;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClaimHistoryService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.NotificationService;
import com.ametis.cms.service.NumberCounterService;
import com.ametis.cms.service.PendingClaimService;
import com.ametis.cms.util.StringUtil;
import com.ametis.cms.util.dao.DaoSupportUtil;

// imports+ 

// imports- 

/**
 * PendingClaim is a servlet controller for pending_claim Table. All you have to
 * do is to convert necessary data field to the named parameter
 */
public class PendingClaimServiceImpl implements PendingClaimService

// extends+

// extends-

{
	private ClaimService claimService;

	private PendingClaimDao pendingClaimDao;

	private ActivityLogService activityLogService;
	private NumberCounterService numberCounterService;
	private ConfigurationService configurationService;
	private ClientService clientService;
	private ClaimHistoryService claimHistoryService;
	
	private BatchClaimService batchClaimService;
	private NotificationService notificationService;
	
	
	
	
	public ClaimHistoryService getClaimHistoryService() {
		return claimHistoryService;
	}
	public void setClaimHistoryService(ClaimHistoryService claimHistoryService) {
		this.claimHistoryService = claimHistoryService;
	}
	public ClientService getClientService() {
		return clientService;
	}
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
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
	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}
	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public void setPendingClaimDao(PendingClaimDao object) {
		this.pendingClaimDao = object;
	}

	public PendingClaimDao getPendingClaimDao() {
		return this.pendingClaimDao;
	}

	private String generatePendingCounterNumber (Claim claim){
		
		String result = "";
		
		try {
			
			int res = 0;
			Configuration configuration = configurationService.getClientConfiguration(claim.getMemberId().getClientId().getClientId());
			if (configuration == null){
				configuration = configurationService.getSystemConfiguration();
			}
			
			res = configuration.getClaimPendingNumber();

			

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
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	/*
	 * Method create (PendingClaim object) berfungsi untuk melakukan penambahan
	 * sebuah object kedalam database @param object adalah sebuah object yang
	 * ingin diubah @return object hasil kreasi,lengkap dengan assigned primary
	 * key, exception jika gagal
	 */
	public PendingClaim create(PendingClaim object, ActionUser actionUser) throws Exception {

		// object.setCreatedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		// ----
		PendingClaim result = null;
		
		

		if (object != null) {

			String[] required = {"Claim.DiagnosisId","Claim.MemberId","Claim.MemberId.ClientId"};
				
			Claim claim = claimService.get(object.getClaimId().getClaimId(),required);

			CaseStatus caseStatus = new CaseStatus();
			caseStatus.setCaseStatusId(Claim.CLAIM_PENDING);
			claim.setClaimStatus(caseStatus);

			DateTime dateTime = new DateTime();
			Configuration configuration = configurationService.getClientConfiguration(claim.getMemberId().getClientId().getClientId());
			
			if (configuration == null){
				configuration = configurationService.getSystemConfiguration();
			}
			int month = dateTime.getMonthOfYear();
			String monthStr = "";
			if (month < 10){
				monthStr = "0" + month;
			}
			else {
				monthStr = month+"";
			}
			
			String counter = generatePendingCounterNumber(claim);
			
			String pendingNumber = configuration.getClaimPendingNumberTemplate();

			pendingNumber = pendingNumber.replace("${counter}", counter);
			pendingNumber = pendingNumber.replace("${month}", monthStr);
			pendingNumber = pendingNumber.replace("${year}", dateTime.getYear()+"");
			
			
			
			PendingCategory pendingCategory = object.getPendingCategory();
			
			if (pendingCategory != null ){
				
				if (pendingCategory.getPendingCategoryId().intValue() == PendingCategory.PENDING_DOCUMENT){
					String tmp = claim.getClaimRemarks();
					
					claim.setClaimRemarks(StringUtil.getStringValue(tmp,"")  + "\n" + object.getRemarks());
					
				}
				else if (pendingCategory.getPendingCategoryId().intValue() == PendingCategory.PENDING_INVESTIGATION){
					String tmp = claim.getMedicalRemarks();
					claim.setMedicalRemarks(StringUtil.getStringValue(tmp,"")  + "\n" + object.getRemarks());
				}
				
				
				//Begin Riyan, Notif Email Pending claim
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
								
							
							notificationService.createPending(notification, actionUser, object,claim,theBatch);
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
								
								
								notificationService.createPending(notification, actionUser, object,claim,theBatch);
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
									
									
									notificationService.createPending(notification, actionUser, object,claim,theBatch);
									}
					
							}
					}
				}
				
				
				
				//end
			}
			
			
			object.setDeletedStatus(Integer.valueOf(0));

			claimService.update(claim, actionUser);
			
			claimService.recordHistory(claim, "PENDING CLAIM", object.getRemarks(), actionUser);
			if (actionUser != null){
				User user = actionUser.getUser();
			if (user != null) {
				object.setCreatedBy(user.getUsername());
			}
			}

			object.setPendingNumber(pendingNumber);
			result = pendingClaimDao.create(object);
			
			configuration.setClaimPendingNumber(configuration.getClaimPendingNumber()+1);
			configurationService.update(configuration, actionUser);
		}

		return result;
	}

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
	/*
	 * Method update (PendingClaim object) berfungsi untuk melakukan perubahan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin diubah @return object hasil update,
	 * exception jika gagal
	 */
	public PendingClaim update(PendingClaim object, ActionUser actionUser) throws Exception {

		// object.setUpdatedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object
				.setUpdatedTime(new java.sql.Timestamp(System
						.currentTimeMillis()));

		if (actionUser != null){
			User user = actionUser.getUser();
		if (user != null) {

			object.setUpdatedBy(user.getUsername());
		}
		}

		pendingClaimDao.update(object);
		return object;
	}

	/*
	 * Method delete (Object pkey) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param pkey adalah
	 * sebuah object yang merepresentasikan primary key dari tabel yang
	 * bersangkutan. Object tersebut dapat dalam bentuk single ID maupun
	 * composite ID @return no return value karena objeknya sendiri sudah
	 * dihapus - just for consistency. Again, exception if failure occured
	 * WARNING ! Invalid value for the returned object, better not use it again
	 * in any place
	 */
	public PendingClaim trash(java.io.Serializable pkey) throws Exception {
		PendingClaim object = pendingClaimDao.get(pkey);
		pendingClaimDao.delete(object);
		return object;
	}

	/*
	 * Method delete (PendingClaim object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public PendingClaim delete(java.io.Serializable pkey, ActionUser actionUser)
			throws Exception {
		PendingClaim object = pendingClaimDao.get(pkey);

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

		if (actionUser != null){
			User user = actionUser.getUser();
		if (user != null) {
			object.setDeletedBy(user.getUsername());
		}
		}

		pendingClaimDao.update(object);
		return object;
	}

	/*
	 * Method delete (PendingClaim object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public PendingClaim delete(PendingClaim object, ActionUser actionUser) throws Exception {

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

		if (actionUser != null){
			User user = actionUser.getUser();
		if (user != null) {
			object.setDeletedBy(user.getUsername());
		}
		}

		pendingClaimDao.update(object);
		return object;
	}

	// -- get section - carefull !

	/*
	 * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	 * sebuah object yang terdapat didalam database @param pkey adalah sebuah
	 * object yang merepresentasikan primary key dari tabel yang bersangkutan.
	 * Object tersebut dapat dalam bentuk single ID maupun composite ID @return
	 * Object yang dihasilkan dari proses retrieval, apabila object tidak
	 * ditemukan maka method akan mengembalikan nilai "NULL"
	 */
	public PendingClaim get(java.io.Serializable pkey) throws Exception {
		PendingClaim object = null;
		object = pendingClaimDao.get(pkey);
		return object;
	}

	/*
	 * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	 * sebuah object yang terdapat didalam database @param pkey adalah sebuah
	 * object yang merepresentasikan primary key dari tabel yang bersangkutan.
	 * Object tersebut dapat dalam bentuk single ID maupun composite ID @param
	 * required adalah array dari field-field yang dibutuhkan dari hibernate
	 * object @return Object yang dihasilkan dari proses retrieval, apabila
	 * object tidak ditemukan maka method akan mengembalikan nilai "NULL"
	 */

	public PendingClaim get(java.io.Serializable pkey, String[] required)
			throws Exception {
		PendingClaim object = null;
		object = pendingClaimDao.get(pkey);
		DaoSupportUtil.lazyInit(required, object);
		return object;
	}

	// -- get section end here

	// SEARCH SECTION - PALING RUMIT !!
	// * -> plain
	// *b -> with columnOrder

	// -- 1
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, int index, int offset)
			throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] required,
			int index, int offset) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			PendingClaim element = (PendingClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder[], String[] required, int index, int offset)
			throws Exception {
		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			PendingClaim element = (PendingClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder, String[] required, int index, int offset)
			throws Exception {
		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			PendingClaim element = (PendingClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	// -- 1b

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset)
			throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String[] required, int index,
			int offset) throws Exception {
		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			PendingClaim element = (PendingClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], String[] required, int index, int offset)
			throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			PendingClaim element = (PendingClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc, String columnOrder,
			String[] required, int index, int offset) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			PendingClaim element = (PendingClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	// -- 2 , between

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, int index, int offset)
			throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, String[] required,
			int index, int offset) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			PendingClaim element = (PendingClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder[], String[] required, int index, int offset)
			throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			PendingClaim element = (PendingClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder, String[] required, int index, int offset)
			throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			PendingClaim element = (PendingClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- 2b
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, int index, int offset)
			throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, String[] required,
			int index, int offset) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			PendingClaim element = (PendingClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder[], String[] required, int index, int offset)
			throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			PendingClaim element = (PendingClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder, String[] required, int index, int offset)
			throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			PendingClaim element = (PendingClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- search end

	// -- get total

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// ------------------------------------------------------------------
	public int getTotal() throws Exception {
		Criteria c = pendingClaimDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}

	// -- get total end

	// ---------------------------------------------------
	public Collection getAll(String[] required) throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			PendingClaim element = (PendingClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection getAll() throws Exception {

		Criteria c = pendingClaimDao.getCriteria();
		List list = c.list();
		return list;
	}

	// -------------------------------------------------

	// unique Result

	public PendingClaim searchUnique(String eqColumns, Object eqParams,
			String[] required) throws Exception {
		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		PendingClaim obj = (PendingClaim) c.uniqueResult();
		DaoSupportUtil.lazyInit(required, obj);
		return obj;
	}

	public PendingClaim searchUnique(String eqColumns, Object eqParams)
			throws Exception {
		Criteria c = pendingClaimDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		PendingClaim obj = (PendingClaim) c.uniqueResult();
		return obj;
	}

	// -----------------------------------------------

	/*
	 * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
	 * @return criteria
	 */
	public Criteria getCriteria() throws Exception {
		Criteria c = pendingClaimDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = pendingClaimDao.getDetachedCriteria();
		return dc;
	}
	public PendingClaim getPendingClaim(Integer claimId) throws Exception {
		// TODO Auto-generated method stub
		
		Criteria c = pendingClaimDao.getCriteria();
		String[] param = {"claimId.claimId"};
		Object[] value = {claimId};
		DaoSupportUtil.setEqParam(param,value, c);
		List<PendingClaim> list = c.list();
		
		PendingClaim result = null;
		
		if (list != null){
			Iterator<PendingClaim> pendingList = list.iterator();
			result = pendingList.next();
		}
		
		return result;
		
	}

	// class+

	// class-

}
