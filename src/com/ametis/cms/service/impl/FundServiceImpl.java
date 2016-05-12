package com.ametis.cms.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.joda.time.DateTime;

import com.ametis.cms.dao.FundDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.ExcessCharge;
import com.ametis.cms.datamodel.Fund;
import com.ametis.cms.datamodel.FundCategory;
import com.ametis.cms.datamodel.Invoice;
import com.ametis.cms.datamodel.Outstanding;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyCoverageFund;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.ExcessChargeService;
import com.ametis.cms.service.FundService;
import com.ametis.cms.service.InvoiceService;
import com.ametis.cms.service.NumberCounterService;
import com.ametis.cms.service.OutstandingService;
import com.ametis.cms.service.PolicyCoverageFundService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderCapitationFundService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.util.dao.DaoSupportUtil;

// imports+ 

// imports- 

/**
 * Fund is a servlet controller for fund Table. All you have to do is to convert
 * necessary data field to the named parameter
 */
public class FundServiceImpl implements FundService

// extends+

// extends-

{

	private FundDao fundDao;

	private ClientService clientService;
	private ExcessChargeService excessChargeService;
	private NumberCounterService numberCounterService;	
	
	private OutstandingService outstandingService;
	private ActivityLogService activityLogService;
	private ConfigurationService configurationService;
	private InvoiceService invoiceService;
	private ProviderCapitationFundService providerCapitationFundService;
	private ProviderService providerService;
	private PolicyService policyService;
	private PolicyCoverageFundService policyCoverageFundService;
	
	
	
	public PolicyService getPolicyService() {
		return policyService;
	}
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}
	public PolicyCoverageFundService getPolicyCoverageFundService() {
		return policyCoverageFundService;
	}
	public void setPolicyCoverageFundService(
			PolicyCoverageFundService policyCoverageFundService) {
		this.policyCoverageFundService = policyCoverageFundService;
	}
	public ProviderCapitationFundService getProviderCapitationFundService() {
		return providerCapitationFundService;
	}
	public void setProviderCapitationFundService(
			ProviderCapitationFundService providerCapitationFundService) {
		this.providerCapitationFundService = providerCapitationFundService;
	}
	public ProviderService getProviderService() {
		return providerService;
	}
	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}
	public InvoiceService getInvoiceService() {
		return invoiceService;
	}
	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
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
	public OutstandingService getOutstandingService() {
		return outstandingService;
	}

	public void setOutstandingService(OutstandingService outstandingService) {
		this.outstandingService = outstandingService;
	}

	public ExcessChargeService getExcessChargeService() {
		return excessChargeService;
	}

	public void setExcessChargeService(ExcessChargeService excessChargeService) {
		this.excessChargeService = excessChargeService;
	}

	public void setFundDao(FundDao object) {
		this.fundDao = object;
	}

	public FundDao getFundDao() {
		return this.fundDao;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	/*
	 * Method create (Fund object) berfungsi untuk melakukan penambahan sebuah
	 * object kedalam database @param object adalah sebuah object yang ingin
	 * diubah @return object hasil kreasi,lengkap dengan assigned primary key,
	 * exception jika gagal
	 */
	public Fund create(Fund object, ActionUser actionUser) throws Exception {

		 object.setCreatedTime(new Timestamp (System.currentTimeMillis()));

		 int number = 0;

		 Configuration configuration = configurationService.getClientConfiguration(object.getClientId().getClientId());
		 
		 if (configuration == null){
			 configuration = configurationService.getSystemConfiguration();
		 }
		 Integer isUsingSequence = configuration.getIsUsingSequenceNumber();
			
			if (isUsingSequence != null && isUsingSequence.intValue() == 1){
				String seqSQL = configuration.getFundNumberSeqName();
				
				Session session = fundDao.getFundSession();
				if (session != null){
					SQLQuery q = session.createSQLQuery(seqSQL);
					
					List<Object> list = q.list();
					
					if (list != null && !list.isEmpty()){
						Iterator<Object> iterator = list.iterator();
						
						if (iterator.hasNext()){
							Object nextval = iterator.next();
							
							if (nextval != null){
								BigInteger bignumber = (BigInteger) nextval;
								if (bignumber != null){
									number = bignumber.intValue();
								}
							}
						}
					}
				}
			}
			else {
				number = configuration.getFundNumber();
			}
		 

		object.setDeletedStatus(Integer.valueOf(0));

		if (actionUser != null){
			
			User user = actionUser.getUser();
		
			if (user != null) {
				object.setCreatedBy(user.getUsername());
			}
		}
		String fundCode = "";
		
		
		String counter = generateFundCounterNumber(number);
		
		String month = "";
		
		DateTime dateTime = new DateTime();
		
		if (dateTime.getMonthOfYear() < 10){
			month += "0"+dateTime.getMonthOfYear();
		}
		else {
			month += dateTime.getMonthOfYear();
		}
		String fundType = "";
		
		FundCategory fundCategory = object.getFundType();
		
		if (fundCategory != null){
			if (fundCategory.getFundCategoryId().intValue() == Fund.EXCESS_CHARGE_PAYMENT) {
				fundType += "EX";
				object.setOutstanding(null);
				
				ExcessCharge excess = excessChargeService.get(object.getExcessCharge().getExcessChargeId());
				
				if (excess != null){
					double currentOutstanding = excess.getOutstanding();
					double currentPaid = excess.getExcessPaidValue();
					
					excess.setExcessPaidValue(object.getFundValue().doubleValue() +currentPaid);
					excess.setOutstanding(currentOutstanding-object.getFundValue().doubleValue());
					
					currentOutstanding = excess.getOutstanding();
					
					if (currentOutstanding == 0){
						PaymentStatus status = new PaymentStatus();
						status.setPaymentStatusId(PaymentStatus.PAYMENT_PAID);
						excess.setExcessChargeStatus(status);
					}
					else if (currentOutstanding > 0){
						PaymentStatus status = new PaymentStatus();
						status.setPaymentStatusId(PaymentStatus.PAYMENT_PARTIAL_PAID);
						excess.setExcessChargeStatus(status);
					}
					
					excessChargeService.update(excess, actionUser);
					
					Invoice invoice = excess.getInvoiceId();
					
					if (invoice != null){
						double currentInvoiceOutstanding = invoice.getOutstanding();
						double currentInvoicePaidValue = invoice.getPaid();
						
						double payment = object.getFundValue().doubleValue();
						invoice.setOutstanding(currentInvoiceOutstanding-payment);
						invoice.setPaid(currentInvoicePaidValue+payment);
						if (currentInvoiceOutstanding - payment == 0){
							PaymentStatus status = new PaymentStatus();
							status.setPaymentStatusId(PaymentStatus.PAYMENT_PAID);
							invoice.setInvoiceStatus(status);
						}
						
						invoiceService.update(invoice, actionUser);						
					}					
				}				
			}
			else if (fundCategory.getFundCategoryId().intValue() == Fund.OUTSTANDING_PAYMENT) {
				fundType += "OUT";
				object.setExcessCharge(null);
			}
			else if (fundCategory.getFundCategoryId().intValue() == Fund.CLIENT_FLOATING_FUND) {
				fundType += "FF";
				object.setOutstanding(null);
				object.setExcessCharge(null);
			}
			else if (fundCategory.getFundCategoryId().intValue() == Fund.PROVIDER_CAPITATION_PAYMENT) {
				fundType += "CAP";
				object.setOutstanding(null);
				object.setExcessCharge(null);
			}
			
		}
		
		fundCode = configuration.getFundNumberTemplate();

		fundCode = fundCode.replace("${counter}", counter);
		fundCode = fundCode.replace("${month}", month);
		fundCode = fundCode.replace("${year}", dateTime.getYear()+"");
		fundCode = fundCode.replace("${fundType}", fundType);
		
		object.setFundCode(fundCode);
		
		Fund result = fundDao.create(object);

		configuration.setFundNumber(configuration.getFundNumber()+1);
		configurationService.update(configuration, actionUser);

		return result;
	}

	/*
	 * Method update (Fund object) berfungsi untuk melakukan perubahan terhadap
	 * sebuah object yang terdapat didalam database @param object adalah sebuah
	 * object yang ingin diubah @return object hasil update, exception jika
	 * gagal
	 */
	public Fund update(Fund object, ActionUser actionUser) throws Exception {

		object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		/*
		 * Gue tambahin mekanisme NULL value checking just in case user nya null
		 */
		if (actionUser != null){
			User user = actionUser.getUser();
			if (user != null) {
				object.setModifiedBy(user.getUsername());
			}
		}

		fundDao.update(object);
		return object;
	}
	
	private String generateFundCounterNumber (int res){
		
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
	 * Method delete (Object pkey) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param pkey adalah
	 * sebuah object yang merepresentasikan primary key dari tabel yang
	 * bersangkutan. Object tersebut dapat dalam bentuk single ID maupun
	 * composite ID @return no return value karena objeknya sendiri sudah
	 * dihapus - just for consistency. Again, exception if failure occured
	 * WARNING ! Invalid value for the returned object, better not use it again
	 * in any place
	 */
	public Fund trash(java.io.Serializable pkey) throws Exception {
		Fund object = fundDao.get(pkey);
		fundDao.delete(object);
		return object;
	}

	/*
	 * Method delete (Fund object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public Fund delete(java.io.Serializable pkey, ActionUser actionUser) throws Exception {
		Fund object = fundDao.get(pkey);

		 object.setDeletedTime (new java.sql.Timestamp
		 (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

if (actionUser != null){
			
			User user = actionUser.getUser();
		if (user != null) {
			object.setDeletedBy(user.getUsername());
		}
}

		fundDao.update(object);
		return object;
	}

	/*
	 * Method delete (Fund object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public Fund delete(Fund object, ActionUser actionUser) throws Exception {

		 object.setDeletedTime (new Timestamp
		 (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));
if (actionUser != null){
			
			User user = actionUser.getUser();
		if (user != null) {
			object.setDeletedBy(user.getUsername());
		}
}

		fundDao.update(object);
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
	public Fund get(java.io.Serializable pkey) throws Exception {
		Fund object = null;
		object = fundDao.get(pkey);
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

	public Fund get(java.io.Serializable pkey, String[] required)
			throws Exception {
		Fund object = null;
		object = fundDao.get(pkey);
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

		Criteria c = fundDao.getCriteria();
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

		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Fund element = (Fund) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = fundDao.getCriteria();
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
		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Fund element = (Fund) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = fundDao.getCriteria();
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
		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Fund element = (Fund) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	// -- 1b

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset)
			throws Exception {

		Criteria c = fundDao.getCriteria();
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
		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Fund element = (Fund) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = fundDao.getCriteria();
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

		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Fund element = (Fund) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception {

		Criteria c = fundDao.getCriteria();
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

		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Fund element = (Fund) iter.next();
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

		Criteria c = fundDao.getCriteria();
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

		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Fund element = (Fund) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = fundDao.getCriteria();
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

		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Fund element = (Fund) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = fundDao.getCriteria();
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

		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Fund element = (Fund) iter.next();
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

		Criteria c = fundDao.getCriteria();
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

		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Fund element = (Fund) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = fundDao.getCriteria();
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

		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Fund element = (Fund) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = fundDao.getCriteria();
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

		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Fund element = (Fund) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- search end

	// -- get total

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams) throws Exception {

		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2) throws Exception {

		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams) throws Exception {

		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2) throws Exception {

		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// ------------------------------------------------------------------
	public int getTotal() throws Exception {
		Criteria c = fundDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}

	// -- get total end

	// ---------------------------------------------------
	public Collection getAll(String[] required) throws Exception {

		Criteria c = fundDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Fund element = (Fund) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection getAll() throws Exception {

		Criteria c = fundDao.getCriteria();
		List list = c.list();
		return list;
	}

	// -------------------------------------------------

	// unique Result

	public Fund searchUnique(String eqColumns, Object eqParams,
			String[] required) throws Exception {
		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Fund obj = (Fund) c.uniqueResult();
		DaoSupportUtil.lazyInit(required, obj);
		return obj;
	}

	public Fund searchUnique(String eqColumns, Object eqParams)
			throws Exception {
		Criteria c = fundDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Fund obj = (Fund) c.uniqueResult();
		return obj;
	}

	// -----------------------------------------------

	/*
	 * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
	 * @return criteria
	 */
	public Criteria getCriteria() throws Exception {
		Criteria c = fundDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = fundDao.getDetachedCriteria();
		return dc;
	}
	public NumberCounterService getNumberCounterService() {
		return numberCounterService;
	}
	public void setNumberCounterService(NumberCounterService numberCounterService) {
		this.numberCounterService = numberCounterService;
	}
	public Fund approve(Fund object, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		
		Fund result = null;
		
		if (object != null) {
			
			
			FundCategory fundCategory = object.getFundType();			

			if (fundCategory != null) {
			
				if (fundCategory.getFundCategoryId().intValue() == Fund.EXCESS_CHARGE_PAYMENT) {
				
					ExcessCharge excess = object.getExcessCharge();
					if (excess != null) {
						
						PaymentStatus status = new PaymentStatus();
						status.setPaymentStatusId(PaymentStatus.PAYMENT_PAID);
						excess.setExcessChargeStatus(status);

						excessChargeService.update(excess, actionUser);
						
					}
				} else if (fundCategory.getFundCategoryId().intValue() == Fund.OUTSTANDING_PAYMENT) {

					Outstanding outstanding = object.getOutstanding();

					if (outstanding != null) {
	
						PaymentStatus status = new PaymentStatus();
						status.setPaymentStatusId(PaymentStatus.PAYMENT_PAID);

						outstanding.setOutstandingStatus(status);

						outstandingService.update(outstanding, actionUser);
						
					}

				}
				else if (fundCategory.getFundCategoryId().intValue() == Fund.CLIENT_FLOATING_FUND){
					Client client = clientService.get(object.getClientId().getClientId());
					if (client != null) {

						Double currentValue = client.getClientFundValue() == null ? 0 : client.getClientFundValue();
						client.setClientFundValue(currentValue + object.getFundValue());
						clientService.update(client, actionUser);
					}
				}
				else if (fundCategory.getFundCategoryId().intValue() == Fund.POLICY_FLOATING_FUND){
					Policy policy = policyService.get(object.getPolicyId().getPolicyId());
					if (policy != null) {

						double currentValue = policy.getCurrentPolicyFund() == null ? 0 : policy.getCurrentPolicyFund();
						double currentTotal = policy.getTotalPolicyFund() == null ? 0.0 : policy.getTotalPolicyFund();
						double blockPercentage = policy.getBlockFundPercentage() == null ? 0.0 : policy.getBlockFundPercentage();
						
						policy.setCurrentPolicyFund(currentValue + object.getFundValue());
						policy.setTotalPolicyFund(currentTotal + object.getFundValue());
						
						policyService.update(policy, actionUser);
					}
				}
				else if (fundCategory.getFundCategoryId().intValue() == Fund.POLICY_EXCESS_FUND){
					Policy policy = policyService.get(object.getPolicyId().getPolicyId());
					if (policy != null) {

						double currentValue = policy.getCurrentExcessFund() == null ? 0 : policy.getCurrentExcessFund();
						double currentTotal = policy.getTotalExcessFund() == null ? 0.0 : policy.getTotalExcessFund();
						
						
						policy.setCurrentExcessFund(currentValue + object.getFundValue());
						policy.setTotalExcessFund(currentTotal + object.getFundValue());
						policyService.update(policy, actionUser);
					}
				}
				else if (fundCategory.getFundCategoryId().intValue() == Fund.POLICY_COVERAGE_FUND){
					PolicyCoverageFund policy = policyCoverageFundService.get(object.getPolicyCoverageFundId().getPolicyCoverageFundId());
					if (policy != null) {

						double currentValue = policy.getCurrentFundValue() == null ? 0 : policy.getCurrentFundValue();
						double currentTotal = policy.getTotalAsoValue() == null ? 0.0 : policy.getTotalAsoValue();
						
						
						policy.setCurrentFundValue(currentValue + object.getFundValue());
						policy.setTotalAsoValue(currentTotal + object.getFundValue());
						
						policyCoverageFundService.update(policy, actionUser);
					}
				}
				else if (fundCategory.getFundCategoryId().intValue() == Fund.POLICY_COVERAGE_EXCESS_FUND){
					PolicyCoverageFund policy = policyCoverageFundService.get(object.getPolicyCoverageFundId().getPolicyCoverageFundId());
					if (policy != null) {

						double currentValue = policy.getExcessFundValue() == null ? 0 : policy.getExcessFundValue();
						double currentTotal = policy.getTotalExcessValue() == null ? 0.0 : policy.getTotalExcessValue();
						
						policy.setExcessFundValue(currentValue + object.getFundValue());
						policy.setTotalExcessValue(currentTotal + object.getFundValue());
						
						policyCoverageFundService.update(policy, actionUser);
					}
				}
			}
		}

		PaymentStatus status = new PaymentStatus();
		status.setPaymentStatusId(PaymentStatus.PAYMENT_APPROVED);
		object.setFundStatus(status);
		
		if (actionUser != null){
			
			object.setApprovedBy(actionUser.getUser().getUsername());
			
		}
		result = update(object,actionUser);
		
		return result; 
	}

	// class+

	// class-

}
