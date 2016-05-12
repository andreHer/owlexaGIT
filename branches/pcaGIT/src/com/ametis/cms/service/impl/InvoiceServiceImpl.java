
package com.ametis.cms.service.impl;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Months;

import com.ametis.cms.dao.InvoiceDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.ClientContract;
import com.ametis.cms.datamodel.ClientContractItem;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.ExcessCharge;
import com.ametis.cms.datamodel.Invoice;
import com.ametis.cms.datamodel.InvoiceItem;
import com.ametis.cms.datamodel.InvoiceItemMember;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberImport;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClientContractItemService;
import com.ametis.cms.service.ClientContractService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.ExcessChargeService;
import com.ametis.cms.service.InvoiceItemMemberService;
import com.ametis.cms.service.InvoiceItemService;
import com.ametis.cms.service.InvoiceService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberImportService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyBillingRateService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.util.dao.DaoSupportUtil;

// imports+ 

// imports- 

/**
 * Invoice is a servlet controller for invoice Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class InvoiceServiceImpl implements InvoiceService

// extends+

// extends-

{

	private InvoiceDao invoiceDao;
	private ActivityLogService activityLogService;

	private ExcessChargeService excessChargeService;
	private ConfigurationService configurationService;
	private MemberGroupService memberGroupService;

	private ClientService clientService;
	private ClientContractService clientContractService;
	private ClientContractItemService clientContractItemService;
	private PolicyBillingRateService policyBillingRateService;
	private ClaimService claimService;
	private CaseService caseService;
	private ItemService itemService;
	private InvoiceItemService invoiceItemService;

	private MemberImportService memberImportService;
	private MemberService memberService;
	private PolicyService policyService;
	private InvoiceItemMemberService invoiceItemMemberService;
	
	

	public InvoiceItemMemberService getInvoiceItemMemberService() {
		return invoiceItemMemberService;
	}

	public void setInvoiceItemMemberService(InvoiceItemMemberService invoiceItemMemberService) {
		this.invoiceItemMemberService = invoiceItemMemberService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public PolicyBillingRateService getPolicyBillingRateService() {
		return policyBillingRateService;
	}

	public void setPolicyBillingRateService(PolicyBillingRateService policyBillingRateService) {
		this.policyBillingRateService = policyBillingRateService;
	}

	public InvoiceItemService getInvoiceItemService() {
		return invoiceItemService;
	}

	public void setInvoiceItemService(InvoiceItemService invoiceItemService) {
		this.invoiceItemService = invoiceItemService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ClientContractService getClientContractService() {
		return clientContractService;
	}

	public void setClientContractService(ClientContractService clientContractService) {
		this.clientContractService = clientContractService;
	}

	public ClientContractItemService getClientContractItemService() {
		return clientContractItemService;
	}

	public void setClientContractItemService(ClientContractItemService clientContractItemService) {
		this.clientContractItemService = clientContractItemService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}

	public MemberImportService getMemberImportService() {
		return memberImportService;
	}

	public void setMemberImportService(MemberImportService memberImportService) {
		this.memberImportService = memberImportService;
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

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public ExcessChargeService getExcessChargeService() {
		return excessChargeService;
	}

	public void setExcessChargeService(ExcessChargeService excessChargeService) {
		this.excessChargeService = excessChargeService;
	}

	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}

	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}

	public void setInvoiceDao(InvoiceDao object) {
		this.invoiceDao = object;
	}

	public InvoiceDao getInvoiceDao() {
		return this.invoiceDao;
	}

	/*
	 * Method create (Invoice object) berfungsi untuk melakukan penambahan
	 * sebuah object kedalam database
	 * 
	 * @param object adalah sebuah object yang ingin diubah
	 * 
	 * @return object hasil kreasi,lengkap dengan assigned primary key,
	 * exception jika gagal
	 */
	public Invoice create(Invoice object, ActionUser actionUser) throws Exception {

		MemberGroup group = memberGroupService.get(object.getMemberGroupId().getMemberGroupId());
		Configuration configuration = configurationService.getClientConfiguration(group.getClientId().getClientId());

		int res = 0;
		if (configuration == null) {
			configuration = configurationService.getSystemConfiguration();
		}

		Integer isUsingSequence = configuration.getIsUsingSequenceNumber();

		if (isUsingSequence != null && isUsingSequence.intValue() == 1) {
			String seqSQL = configuration.getInvoiceNumberSeqName();

			Session session = invoiceDao.getInvoiceSession();
			if (session != null) {
				SQLQuery q = session.createSQLQuery(seqSQL);

				List<Object> list = q.list();

				if (list != null && !list.isEmpty()) {
					Iterator<Object> iterator = list.iterator();

					if (iterator.hasNext()) {
						Object nextval = iterator.next();

						if (nextval != null) {

							BigInteger num = (BigInteger) nextval;
							if (num != null) {
								res = num.intValue();
							}
						}
					}
				}
			}
		} else {
			res = configuration.getInvoiceNumber();
		}

		object.setDeletedStatus(Integer.valueOf(0));

		object.setInvoiceStatus(new PaymentStatus(PaymentStatus.PAYMENT_OPEN));

		if (actionUser != null) {

			User user = actionUser.getUser();
			if (user != null) {
				object.setCreatedBy(user.getUsername());
			}
		}

		String invoiceNumber = configuration.getInvoiceNumberTemplate();

		String counter = "";
		if (res > 0 && res < 10) {
			counter = "0000" + res;
		} else if (res >= 10 && res < 100) {
			counter = "000" + res;
		} else if (res >= 100 && res < 1000) {
			counter = "00" + res;
		} else if (res >= 1000 && res < 10000) {
			counter = "0" + res;
		} else if (res >= 10000) {
			counter = "" + res;
		}
		DateTime dateTime = new DateTime();
		String month = "";

		if (dateTime.getMonthOfYear() < 10) {
			month += "0" + dateTime.getMonthOfYear();
		} else {
			month += "" + dateTime.getMonthOfYear();
		}

		invoiceNumber = invoiceNumber.replace("${counter}", counter);
		invoiceNumber = invoiceNumber.replace("${month}", month);
		invoiceNumber = invoiceNumber.replace("${year}", dateTime.getYear() + "");
		object.setInvoiceNumber(invoiceNumber);
		object.setOutstanding(object.getInvoiceValue());
		object.setPaid(0.0);
		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));

		Invoice result = invoiceDao.create(object);
		return result;
	}

	/*
	 * Method update (Invoice object) berfungsi untuk melakukan perubahan
	 * terhadap sebuah object yang terdapat didalam database
	 * 
	 * @param object adalah sebuah object yang ingin diubah
	 * 
	 * @return object hasil update, exception jika gagal
	 */
	public Invoice update(Invoice object, ActionUser actionUser) throws Exception {
		object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		if (actionUser != null) {

			User user = actionUser.getUser();
			if (user != null) {
				object.setModifiedBy(user.getUsername());
			}
		}

		invoiceDao.update(object);
		return object;
	}

	/*
	 * Method delete (Object pkey) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database
	 * 
	 * @param pkey adalah sebuah object yang merepresentasikan primary key dari
	 * tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID
	 * maupun composite ID
	 * 
	 * @return no return value karena objeknya sendiri sudah dihapus - just for
	 * consistency. Again, exception if failure occured WARNING ! Invalid value
	 * for the returned object, better not use it again in any place
	 */
	public Invoice trash(java.io.Serializable pkey) throws Exception {
		Invoice object = invoiceDao.get(pkey);
		invoiceDao.delete(object);
		return object;
	}

	/*
	 * Method delete (Invoice object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database
	 * 
	 * @param object adalah sebuah object yang ingin dihapus, isi dari object
	 * tersebut cukup dengan mengisi field-field primary key
	 * 
	 * @return updated object, exception if failed
	 */

	public Invoice delete(java.io.Serializable pkey, ActionUser actionUser) throws Exception {
		Invoice object = invoiceDao.get(pkey);

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

		if (actionUser != null) {

			User user = actionUser.getUser();
			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}
		}

		invoiceDao.update(object);
		return object;
	}

	/*
	 * Method delete (Invoice object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database
	 * 
	 * @param object adalah sebuah object yang ingin dihapus, isi dari object
	 * tersebut cukup dengan mengisi field-field primary key
	 * 
	 * @return updated object, exception if failed
	 */

	public Invoice delete(Invoice object, ActionUser actionUser) throws Exception {

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

		if (actionUser != null) {

			User user = actionUser.getUser();
			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}
		}

		invoiceDao.update(object);
		return object;
	}

	// -- get section - carefull !

	/*
	 * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	 * sebuah object yang terdapat didalam database
	 * 
	 * @param pkey adalah sebuah object yang merepresentasikan primary key dari
	 * tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID
	 * maupun composite ID
	 * 
	 * @return Object yang dihasilkan dari proses retrieval, apabila object
	 * tidak ditemukan maka method akan mengembalikan nilai "NULL"
	 */
	public Invoice get(java.io.Serializable pkey) throws Exception {
		Invoice object = null;
		object = invoiceDao.get(pkey);
		return object;
	}
	/*
	 * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	 * sebuah object yang terdapat didalam database
	 * 
	 * @param pkey adalah sebuah object yang merepresentasikan primary key dari
	 * tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID
	 * maupun composite ID
	 * 
	 * @param required adalah array dari field-field yang dibutuhkan dari
	 * hibernate object
	 * 
	 * @return Object yang dihasilkan dari proses retrieval, apabila object
	 * tidak ditemukan maka method akan mengembalikan nilai "NULL"
	 */

	public Invoice get(java.io.Serializable pkey, String[] required) throws Exception {
		Invoice object = null;
		object = invoiceDao.get(pkey);
		DaoSupportUtil.lazyInit(required, object);
		return object;
	}
	// -- get section end here

	// SEARCH SECTION - PALING RUMIT !!
	// * -> plain
	// *b -> with columnOrder

	// -- 1
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] required, int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Invoice element = (Invoice) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder[], int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder[], String[] required, int index, int offset) throws Exception {
		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Invoice element = (Invoice) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder, int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder, String[] required, int index, int offset) throws Exception {
		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Invoice element = (Invoice) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	// --req end

	// -- 1b

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, int index,
			int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String[] required, int index, int offset) throws Exception {
		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Invoice element = (Invoice) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	// --req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], String[] required, int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Invoice element = (Invoice) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}
	// req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder, String[] required, int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Invoice element = (Invoice) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	// -- 2 , between

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, String[] required, int index, int offset)
					throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Invoice element = (Invoice) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder[],
			int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder[],
			String[] required, int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Invoice element = (Invoice) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder,
			int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder,
			String[] required, int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Invoice element = (Invoice) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- 2b
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, String[] required, int index, int offset)
					throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Invoice element = (Invoice) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder[], int index,
			int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder[],
			String[] required, int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Invoice element = (Invoice) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder, int index,
			int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder,
			String[] required, int index, int offset) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Invoice element = (Invoice) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	// -- search end

	// -- get total

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams)
			throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String likeColumns, Object likeParams, String eqColumns, Object eqParams) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// ------------------------------------------------------------------
	public int getTotal() throws Exception {
		Criteria c = invoiceDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
	// -- get total end

	// ---------------------------------------------------
	public Collection getAll(String[] required) throws Exception {

		Criteria c = invoiceDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Invoice element = (Invoice) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection getAll() throws Exception {

		Criteria c = invoiceDao.getCriteria();
		List list = c.list();
		return list;
	}
	// -------------------------------------------------

	// unique Result

	public Invoice searchUnique(String eqColumns, Object eqParams, String[] required) throws Exception {
		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Invoice obj = (Invoice) c.uniqueResult();
		DaoSupportUtil.lazyInit(required, obj);
		return obj;
	}

	public Invoice searchUnique(String eqColumns, Object eqParams) throws Exception {
		Criteria c = invoiceDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Invoice obj = (Invoice) c.uniqueResult();
		return obj;
	}

	// -----------------------------------------------

	/*
	 * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
	 * 
	 * @return criteria
	 */
	public Criteria getCriteria() throws Exception {
		Criteria c = invoiceDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = invoiceDao.getDetachedCriteria();
		return dc;
	}

	public boolean confirmInvoiceAssignment(Integer pkey, String[] excessIds, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		boolean result = false;
		try {
			if (pkey != null && excessIds != null) {
				Invoice invoice = get(pkey);

				double totalInvoice = 0.0;
				for (int i = 0; i < excessIds.length; i++) {

					ExcessCharge excess = excessChargeService.get(Integer.valueOf(excessIds[i]));

					if (excess != null) {
						excess.setInvoiceId(invoice);
						excess.setExcessLetterSent(Integer.valueOf(1));
						totalInvoice += excess.getExcessChargeValue().doubleValue();

						excessChargeService.update(excess, actionUser);
					}
				}

				invoice.setInvoiceValue(totalInvoice);

				update(invoice, actionUser);
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalPendingInvoice() throws Exception {
		// TODO Auto-generated method stub

		int result = 0;
		try {
			Session session = invoiceDao.getInvoiceSession();

			if (session != null) {
				String query = "SELECT count(e) FROM Invoice e WHERE e.invoiceStatus.paymentStatusId = :status "
						+ " AND e.deletedStatus = 0";

				org.hibernate.Query q = session.createQuery(query);
				q.setInteger("status", PaymentStatus.PAYMENT_OPEN);

				Number num = (Number) q.uniqueResult();
				result = num.intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Invoice generateClientContractInvoice(Integer clientContractId, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		Invoice result = null;

		try {

			ClientContract activeContract = clientContractService.get(clientContractId);

			if (activeContract != null) {
				
				boolean isUsingVolumeLevel = false;
				
				if (activeContract.getIsUsingVolumeLevel() != null){
					if (activeContract.getIsUsingVolumeLevel().intValue() == 1){
						isUsingVolumeLevel = true;
					}
				}
				Configuration configuration = configurationService.getSystemConfiguration();
				int res = 0;

				String[] eqParam = { "deletedStatus", "clientContractId.clientContractId", "invoiceStatus.paymentStatusId" };
				Object[] eqValue = { 0, activeContract.getClientContractId(), PaymentStatus.PAYMENT_DRAFT };

				Invoice object = null;

				Collection<Invoice> draftInvList = search(null, null, eqParam, eqValue, 0, 1);
				if (draftInvList != null) {
					Iterator<Invoice> iterator = draftInvList.iterator();

					if (iterator.hasNext()) {
						object = iterator.next();
					}
				}

				if (object == null) {
					Integer isUsingSequence = configuration.getIsUsingSequenceNumber();

					if (isUsingSequence != null && isUsingSequence.intValue() == 1) {
						String seqSQL = configuration.getInvoiceNumberSeqName();

						Session session = invoiceDao.getInvoiceSession();
						if (session != null) {
							SQLQuery q = session.createSQLQuery(seqSQL);

							List<Object> list = q.list();

							if (list != null && !list.isEmpty()) {
								Iterator<Object> iterator = list.iterator();

								if (iterator.hasNext()) {
									Object nextval = iterator.next();

									if (nextval != null) {

										BigInteger num = (BigInteger) nextval;
										if (num != null) {
											res = num.intValue();
										}
									}
								}
							}
						}
					} else {
						res = configuration.getInvoiceNumber();
					}

					object.setDeletedStatus(Integer.valueOf(0));
					object.setInvoiceStatus(new PaymentStatus(PaymentStatus.PAYMENT_DRAFT));

					if (actionUser != null) {

						User user = actionUser.getUser();
						if (user != null) {
							object.setCreatedBy(user.getUsername());
						}
					}

					String invoiceNumber = configuration.getInvoiceNumberTemplate();

					String counter = "";
					if (res > 0 && res < 10) {
						counter = "0000" + res;
					} else if (res >= 10 && res < 100) {
						counter = "000" + res;
					} else if (res >= 100 && res < 1000) {
						counter = "00" + res;
					} else if (res >= 1000 && res < 10000) {
						counter = "0" + res;
					} else if (res >= 10000) {
						counter = "" + res;
					}
					DateTime dateTime = new DateTime();
					String monthStr = "";

					if (dateTime.getMonthOfYear() < 10) {
						monthStr += "0" + dateTime.getMonthOfYear();
					} else {
						monthStr += "" + dateTime.getMonthOfYear();
					}

					invoiceNumber = invoiceNumber.replace("${counter}", counter);
					invoiceNumber = invoiceNumber.replace("${month}", monthStr);
					invoiceNumber = invoiceNumber.replace("${year}", dateTime.getYear() + "");

					object.setInvoiceNumber(invoiceNumber);
					object.setOutstanding(object.getInvoiceValue());
					object.setPaid(0.0);
					object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));

					result = invoiceDao.create(object);
				}

				

				Collection<ClientContractItem> ccItemList = clientContractItemService
						.getContractItemList(activeContract.getClientContractId());

				double outstanding = 0.0;

				for (Iterator iterator = ccItemList.iterator(); iterator.hasNext();) {

					ClientContractItem clientContractItem = (ClientContractItem) iterator.next();

					if (clientContractItem != null && clientContractItem.getContractUnitType() != null) {
						String[] eqPolicyParam = {"clientId.clientId","deletedStatus","productType.productTypeId","policyStatus.statusId"};
						Object[] eqPolicyValue = {activeContract.getClientId().getClientId(),0,activeContract.getProductTypeId().getProductTypeId(),SubscriptionStatus.ACTIVE};
						
						int totalActivePolicy = policyService.getTotal(null,null,eqPolicyParam,eqPolicyValue);
						
						Collection<Policy> clientActivePolicyList = policyService.search(null,null,eqPolicyParam,eqPolicyValue,0,totalActivePolicy);

						for (Iterator iteratorPolicy = clientActivePolicyList.iterator(); iteratorPolicy.hasNext();) {

							Policy policy = (Policy) iteratorPolicy.next();

							if (policy != null) {

								DateTime policyEffectiveDate = new DateTime(policy.getEffectiveDate().getTime());
								DateTime policyExpireDate = new DateTime(policy.getExpireDate().getTime());
								
								
								DateTime currentDate = new DateTime();
								
								int currentMonth = currentDate.getMonthOfYear();
								int currentYear = currentDate.getYear();
								int currentDay = currentDate.getDayOfMonth();
								
								int policyDuration = Months.monthsBetween(policyEffectiveDate, policyExpireDate).getMonths();
								
								
								boolean hasPreviousDraftItem = false;
								InvoiceItem invoiceItem = null;

								String[] invItemParamEq = { "deletedStatus", "invoiceId.invoiceId",
										"invoiceItemStatus.paymentStatusId", "itemId.itemId", "policyId.policyId" };
								Object[] invItemValEq = { 0, object.getInvoiceId(), PaymentStatus.PAYMENT_DRAFT,
										clientContractItem.getItemId().getItemId(), policy.getPolicyId() };

								
								Collection<InvoiceItem> previousInvItemList = invoiceItemService.search(null, null,
										invItemParamEq, invItemValEq, 0, 1);

								if (previousInvItemList != null && !previousInvItemList.isEmpty()) {
									Iterator<InvoiceItem> iteratorInvItem = previousInvItemList.iterator();

									if (iteratorInvItem.hasNext()) {
										invoiceItem = iteratorInvItem.next();
									}

								} else {
									invoiceItem = new InvoiceItem();
								}
								invoiceItem.setInvoiceId(result);
								invoiceItem.setCreatedBy(actionUser.getUser().getUsername());
								invoiceItem.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
								invoiceItem.setDeletedStatus(0);
								invoiceItem.setItemId(clientContractItem.getItemId());
								invoiceItem.setItemUnitChargeValue(clientContractItem.getItemPrice());
								
								if (!hasPreviousDraftItem) {
									invoiceItemService.create(invoiceItem, actionUser);
								} else {
									invoiceItemService.update(invoiceItem, actionUser);
								}
								
								double amount = 0.0;								
								boolean isSubjectForProrate = false;
								
								Collection<MemberImport> movementList = null;
								Collection<Claim> claimMovementList = null;
								Collection<Member> regularPeriodeMembershipList = null;
								int maxVolume = 0;
								
								if (isUsingVolumeLevel){
									maxVolume = clientContractItem.getVolumeEnd();
								}
								String[] eqActivePolicyMemberParam = {"policyId.policyId","status","deletedStatus"};
								Object[] eqActivePolicyMemberValue = {policy.getPolicyId(),SubscriptionStatus.ACTIVE,Integer.valueOf(0)};
								
								String[] eqActiveClientMemberParam = {"policyId.productType.productTypeId","status","deletedStatus"};
								Object[] eqActiveClientMemberValue = {activeContract.getProductTypeId().getProductTypeId(),SubscriptionStatus.ACTIVE,0};
								
								int totalActivePolicyMember = memberService.getTotal(null,null,eqActivePolicyMemberParam,eqActivePolicyMemberValue);
								int totalActiveClientMember = memberService.getTotal(null,null,eqActiveClientMemberParam,eqActiveClientMemberValue);
								
								int totalUnbilledMember = 0;
								
								int totalUnbilledClaim = 0;
								int totalBilledClaim = 0;

								if ( clientContractItem
										.getContractUnitType().intValue() == ClientContractItem.PER_MEMBER) {
									
									if (activeContract.getMembershipPeriode() != null){
										if (activeContract.getMembershipPeriode().intValue() == ClientContract.ANNUALY){
											
											if (policyDuration <= 12){
												/**
												 * Group Based Membership and it supposed to be enrollment based
												 */
			
												// check pro-rate and volume 
												
												if (isUsingVolumeLevel){
			
													
													if (clientContractItem.getItemId().getMemberMovementType() != null) {												
									
														movementList = memberImportService.getPolicyUnbilledMovement(
																policy.getPolicyId(),
																clientContractItem.getItemId().getMemberMovementType());
				
														if (movementList != null) {
															amount = movementList.size();
														}
													}
													
												}
												else {
													if (clientContractItem.getItemId().getMemberMovementType() != null) {												
														movementList = memberImportService.getPolicyUnbilledMovement(
																policy.getPolicyId(),
																clientContractItem.getItemId().getMemberMovementType());
				
														if (movementList != null) {
															amount = movementList.size();
														}
													}
												}
												// 2 case, jika individual / enrollment
												
	
												if (movementList != null) {
													for (Iterator memberMovementIterator = movementList
															.iterator(); memberMovementIterator.hasNext();) {
	
														MemberImport memberImport = (MemberImport) memberMovementIterator.next();
	
														if (memberImport != null) {
															double billingRate = 0.0;
															
															if (activeContract.getProrateType() != null){
																int prorateType = activeContract.getProrateType().intValue();
																
																DateTime effectiveDate = new DateTime(memberImport.getEffectiveDate().getTime());
																DateTime expireDate= new DateTime(memberImport.getExpireDate().getTime());
	
																Months diffMonth = Months.monthsBetween(effectiveDate, expireDate);
																int totalDiffMonth = diffMonth.getMonths();
																		
																
																if (prorateType == ClientContract.PRORATE_NONE){
																	billingRate = clientContractItem.getItemPrice();
																}
																else if  (prorateType == ClientContract.PRORATE_MONTHLY){
																	billingRate = (totalDiffMonth)/12.0 * clientContractItem.getItemPrice();
																}
																else if  (prorateType == ClientContract.PRORATE_QUARTERLY){
																	billingRate = (totalDiffMonth)/4.0 * clientContractItem.getItemPrice();
																}
																else if  (prorateType == ClientContract.PRORATE_SEMESTER){
																	if (totalDiffMonth > 6){
																		billingRate = clientContractItem.getItemPrice();
																	}
																	else {
																		billingRate = (totalDiffMonth)/12.0 * clientContractItem.getItemPrice();
																	}
																}
															}
															else {
																billingRate = clientContractItem.getItemPrice();
															}
															
															memberImport.setInvoiceItemId(invoiceItem);
															memberImport.setIsBilled(1);
															memberImport.setBillingRate(billingRate);
	
															memberImportService.update(memberImport, actionUser);
														}
													}
												}
											}
											else {
												// Long Term / Multiple Year Membership
												
												String[] eqParamToBill = {"latestMonthMembershipBilled","latestYearMembershipBilled",
														"deletedStatus","status"};
												Object[] eqValueToBill = {};

												
											}
										}
										else if (activeContract.getMembershipPeriode().intValue() == ClientContract.MONTHLY){
											// check is already billed --> InvoiceItemMember
											String[] eqParamToBill = {"latestMonthMembershipBilled","latestYearMembershipBilled",
													"deletedStatus","status","policyId.policyId"};
											
											Object[] eqValueToBill = {};
											Calendar calendar = Calendar.getInstance();
											
											Collection<Member> memberList = null;
											
											double billingRate = 0.0;
											int totalToFetch = 0;
											
											if (isUsingVolumeLevel){
												if (totalActiveClientMember <= maxVolume){
													amount = totalActiveClientMember;
												}
												else {
													amount = 0.0;
												}
											}
											else {
												amount = totalActiveClientMember;
											}
											
											memberList = memberService.search(null,null,eqParamToBill,eqValueToBill,0,totalToFetch);
											
											if (memberList != null){
												for (Iterator iterator2 = memberList.iterator(); iterator2.hasNext();) {
													
													Member member = (Member) iterator2.next();
													
													if (member != null){
														InvoiceItemMember invoiceItemMember = new InvoiceItemMember();
														invoiceItemMember.setMemberId(member);
														invoiceItemMember.setBillingYear(currentYear);
														invoiceItemMember.setBillingMonth(currentMonth);
														invoiceItemMember.setInvoiceItemId(invoiceItem);
														invoiceItemMember.setStatus(0);
														invoiceItemMember.setBillingRate(billingRate);
														invoiceItemMember.setMembershipPeriode(activeContract.getMembershipPeriode());
														
														
														invoiceItemMemberService.create(invoiceItemMember, actionUser);
														
														member.setLatestMonthMembershipBilled(currentMonth);
														member.setLatestYearMembershipBilled(currentYear);
														
														memberService.update(member, actionUser);
														
														
													}
												}
											}											
											
										}
										else if (activeContract.getMembershipPeriode().intValue() == ClientContract.SEMESTER){

											// check is already billed --> InvoiceItemMember
											String[] eqParamToBill = {"latestMonthMembershipBilled","latestYearMembershipBilled",
													"deletedStatus","status","policyId.policyId"};
											
											Object[] eqValueToBill = {};
											Calendar calendar = Calendar.getInstance();
											
											Collection<Member> memberList = null;
											
											double billingRate = 0.0;
											int totalToFetch = 0;
											
											if (isUsingVolumeLevel){
												if (totalActiveClientMember <= maxVolume){
													amount = totalActiveClientMember;
												}
												else {
													amount = 0.0;
												}
											}
											else {
												amount = totalActiveClientMember;
											}
											
											memberList = memberService.search(null,null,eqParamToBill,eqValueToBill,0,totalToFetch);
											
											if (memberList != null){
												for (Iterator iterator2 = memberList.iterator(); iterator2.hasNext();) {
													
													Member member = (Member) iterator2.next();
													
													if (member != null){
														InvoiceItemMember invoiceItemMember = new InvoiceItemMember();
														invoiceItemMember.setMemberId(member);
														invoiceItemMember.setBillingYear(currentYear);
														invoiceItemMember.setBillingMonth(currentMonth);
														invoiceItemMember.setInvoiceItemId(invoiceItem);
														invoiceItemMember.setStatus(0);
														invoiceItemMember.setBillingRate(billingRate);
														invoiceItemMember.setMembershipPeriode(activeContract.getMembershipPeriode());
														
														
														invoiceItemMemberService.create(invoiceItemMember, actionUser);
														
														member.setLatestMonthMembershipBilled(currentMonth);
														member.setLatestYearMembershipBilled(currentYear);
														
														memberService.update(member, actionUser);
														
														
													}
												}
											}
										}
										else if (activeContract.getMembershipPeriode().intValue() == ClientContract.QUARTERLY){

											// check is already billed --> InvoiceItemMember
											String[] eqParamToBill = {"latestMonthMembershipBilled","latestYearMembershipBilled",
													"deletedStatus","status","policyId.policyId"};
											
											Object[] eqValueToBill = {};
											Calendar calendar = Calendar.getInstance();
											
											Collection<Member> memberList = null;
											
											double billingRate = 0.0;
											int totalToFetch = 0;
											
											if (isUsingVolumeLevel){
												if (totalActiveClientMember <= maxVolume){
													amount = totalActiveClientMember;
												}
												else {
													amount = 0.0;
												}
											}
											else {
												amount = totalActiveClientMember;
											}
											
											memberList = memberService.search(null,null,eqParamToBill,eqValueToBill,0,totalToFetch);
											
											if (memberList != null){
												for (Iterator iterator2 = memberList.iterator(); iterator2.hasNext();) {
													
													Member member = (Member) iterator2.next();
													
													if (member != null){
														InvoiceItemMember invoiceItemMember = new InvoiceItemMember();
														invoiceItemMember.setMemberId(member);
														invoiceItemMember.setBillingYear(currentYear);
														invoiceItemMember.setBillingMonth(currentMonth);
														invoiceItemMember.setInvoiceItemId(invoiceItem);
														invoiceItemMember.setStatus(0);
														invoiceItemMember.setBillingRate(billingRate);
														invoiceItemMember.setMembershipPeriode(activeContract.getMembershipPeriode());
														
														
														invoiceItemMemberService.create(invoiceItemMember, actionUser);
														
														member.setLatestMonthMembershipBilled(currentMonth);
														member.setLatestYearMembershipBilled(currentYear);
														
														memberService.update(member, actionUser);
														
														
													}
												}
											}
										}
									}
								} 
								else if (clientContractItem.getContractUnitType().intValue() == ClientContractItem.PER_MONTHLY_FIXED_FEE){
									amount = 1.0;
								}
								else if (clientContractItem.getContractUnitType().intValue() == ClientContractItem.PER_ANNUM_FIXED_FEE){
									
								}
								else if (clientContractItem.getContractUnitType().intValue() == ClientContractItem.PER_QUARTER_FIXED_FEE){
									
								}
								else if (clientContractItem.getContractUnitType().intValue() == ClientContractItem.PER_SEMESTER_FIXED_FEE){
									
								}
								else if (clientContractItem
										.getContractUnitType().intValue() == ClientContractItem.PER_CLAIM) {

									Integer caseCategoryId = null;
									Integer claimType = null;
									Integer claimStatus = clientContractItem.getClaimStatusProcess();
								
									

									if (clientContractItem.getCaseCategoryId() != null) {
										caseCategoryId = clientContractItem.getCaseCategoryId().getCaseCategoryId();
									}
									if (clientContractItem.getClaimType() != null && (clientContractItem.getClaimType()
											.intValue() == ClientContractItem.REIMBURSEMENT
											|| clientContractItem.getClaimType()
													.intValue() == ClientContractItem.CASHLESS)) {
										claimType = clientContractItem.getClaimType();
									}

									claimMovementList = claimService.getUnbilledPolicyClaim(policy.getPolicyId(),
											claimStatus, claimType, caseCategoryId);

									if (claimMovementList != null) {
										totalUnbilledClaim = claimMovementList.size();
									
										
										if (!isUsingVolumeLevel){
											amount = totalUnbilledClaim;
										}
										else {
											if (caseCategoryId == null){
												
												String[] eqParamClaimInv = {"invoiceItemId.invoiceItemId","deletedStatus"};
												Object[] eqValueClaimInv = {invoiceItem.getInvoiceItemId(),Integer.valueOf(0)};
												
												String[] eqParamClaimBilled = {"isBilled","deletedStatus"};
												Object[] eqValueClaimBilled = {1,0};
												

												int totalBilled = claimService.getTotal(null,null,eqParamClaimBilled,eqValueClaimBilled);
												int totalBilledCurrentInv = claimService.getTotal(null,null,eqParamClaimInv,eqValueClaimInv);
												
												
												String[] eqParamTotalClaim = {"policyId.policyId","claimStatus.caseStatusId","deletedStatus"};
												Object[] eqValueTotalClaim = {policy.getPolicyId(),claimStatus,0};
												
												int totalClaim = claimService.getTotal(null,null,eqParamTotalClaim,eqValueTotalClaim);
												
												if (totalClaim < maxVolume ){
													
												}
												
											}
											else {
												
												// per productType
												String[] eqParamClaimInv = {"invoiceItemId.invoiceItemId","deletedStatus","caseCategoryId.caseCategoryId"};
												Object[] eqValueClaimInv = {invoiceItem.getInvoiceItemId(),Integer.valueOf(0),caseCategoryId};
												
												String[] eqParamClaimBilled = {"isBilled","deletedStatus","caseCategoryId.caseCategoryId","policyId.productType.productTypeId"};
												Object[] eqValueClaimBilled = {1,0,caseCategoryId,activeContract.getProductTypeId().getProductTypeId()};
												

												int totalBilled = claimService.getTotal(null,null,eqParamClaimBilled,eqValueClaimBilled);
												int totalBilledCurrentInv = claimService.getTotal(null,null,eqParamClaimInv,eqValueClaimInv);
												
												String[] eqParamClaimToBill = {"isBilled","deletedStatus","caseCategoryId.caseCategoryId","policyId.policyId","claimStatus.caseStatusId"};
												Object[] eqValueClaimToBill = {0,0,caseCategoryId,policy.getPolicyId(),claimStatus};
												
												String[] eqParamTotalClaim = {"policyId.policyId","claimStatus.caseStatusId",
														"deletedStatus","caseCategoryId.caseCategoryId"};
												Object[] eqValueTotalClaim = {policy.getPolicyId(),claimStatus,0,caseCategoryId};
												
												int totalClaim = claimService.getTotal(null,null,eqParamTotalClaim,eqValueTotalClaim);
											
												if (totalBilled < maxVolume ){
													
													int totalToFetch = maxVolume - totalBilled;
													
													Collection<Claim> claimList = claimService.search(null,null,eqParamClaimToBill,eqValueClaimToBill,0,totalToFetch);
													
													for (Iterator iterator2 = claimList.iterator(); iterator2.hasNext();) {
														
														Claim claim = (Claim) iterator2.next();
														claim.setBillingRate(clientContractItem.getItemPrice());
														claim.setIsBilled(1);
														claim.setInvoiceItemId(invoiceItem);

														claimService.update(claim, actionUser);
													}													
												}
											}											
										}
									}									
								}

								if (amount > 0.0) {



									/**
									if (claimMovementList != null) {
										for (Iterator<Claim> claimIterator = claimMovementList.iterator(); claimIterator
												.hasNext();) {

											Claim claimMovement = (Claim) claimIterator.next();

											if (claimMovement != null) {
												claimMovement.setInvoiceItemId(invoiceItem);
												claimMovement.setBillingRate(invoiceItem.getInvoiceItemValue());

												claimService.update(claimMovement, actionUser);
											}
										}
									}*/
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean finalizeInvoice(Integer invoiceId, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		Invoice invoice = get(invoiceId);
		boolean result = false;

		if (invoice != null) {
			ClientContract contract = invoice.getContractId();

			if (contract.getBillingCutOffDate() != null) {

				DateTime currentDate = new DateTime();
				if (currentDate.getDayOfMonth() == contract.getBillingCutOffDate().intValue()) {

					invoice.setInvoiceStatus(new PaymentStatus(PaymentStatus.PAYMENT_OPEN));
					invoice.setInvoiceDate(new java.sql.Date(System.currentTimeMillis()));
					int dueLength = contract.getInvoiceDueLength() == null ? 14 : contract.getInvoiceDueLength();

					DateTime nextDueDate = currentDate.plusDays(dueLength);
					invoice.setInvoiceDueDate(new java.sql.Date(nextDueDate.getMillis()));

					update(invoice, actionUser);

					Collection<InvoiceItem> itemList = invoiceItemService.getItemList(invoiceId);

					for (Iterator iterator = itemList.iterator(); iterator.hasNext();) {
						InvoiceItem invoiceItem = (InvoiceItem) iterator.next();

						if (invoiceItem != null) {
							invoiceItem.setOutstanding(invoice.getInvoiceValue());
							invoiceItem.setInvoiceItemPaid(0.0);
							invoiceItem.setInvoiceItemStatus(new PaymentStatus(PaymentStatus.PAYMENT_OPEN));

							invoiceItemService.update(invoiceItem, actionUser);
						}
					}
				}
			}
		}
		return result;
	}

	@Override
	public Invoice generateClientInvoice(Integer clientId, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
