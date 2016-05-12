
package com.ametis.cms.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.Days;

import com.ametis.cms.dao.MemberDao;
import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ActivityLog;
import com.ametis.cms.datamodel.BenefitUsageType;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberClausul;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberIdAndPolicyId;
import com.ametis.cms.datamodel.MemberLimitLayer;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyBenefit;
import com.ametis.cms.datamodel.PolicyDiagnosisExclusion;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductBenefit;
import com.ametis.cms.datamodel.ProductClausul;
import com.ametis.cms.datamodel.ProductLayerLimit;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.TreatmentLocation;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BenefitService;
import com.ametis.cms.service.BillingItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClausulService;
import com.ametis.cms.service.ClientContractItemService;
import com.ametis.cms.service.ClientContractService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberClausulService;
import com.ametis.cms.service.MemberElectronicCardService;
import com.ametis.cms.service.MemberImportService;
import com.ametis.cms.service.MemberLimitLayerService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.MemberSuspendHistoryService;
import com.ametis.cms.service.PolicyBenefitService;
import com.ametis.cms.service.PolicyBillingRateService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProductBenefitService;
import com.ametis.cms.service.ProductClausulService;
import com.ametis.cms.service.ProductLayerLimitService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.util.dao.DaoSupportUtil;

// imports+ 

// imports- 

/**
 * Member is a servlet controller for member Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao;
	private ActivityLogService activityLogService;
	private MemberProductService memberProductService;
	private MemberBenefitService memberBenefitService;
	private MemberLimitLayerService memberLimitLayerService;
	private ProductLayerLimitService productLayerLimitService;
	private MemberSuspendHistoryService memberSuspendHistoryService;
	private MemberElectronicCardService memberElectronicCardService;

	private MemberElectronicCardService memberCardService;
	private MemberClausulService memberClausulService;
	private ProductService productService;
	private ClausulService clausulService;
	private BenefitService benefitService;

	private ProductBenefitService productBenefitService;
	private PolicyBenefitService policyBenefitService;
	private ProductClausulService productClausulService;
	private ClaimService claimService;
	private PolicyService policyService;
	private MemberImportService memberImportService;

	private ClientContractService clientContractService;
	private ClientContractItemService clientContractItemService;
	private BillingItemService billingItemService;
	private PolicyBillingRateService policyBillingRateService;

	public ClientContractItemService getClientContractItemService() {
		return clientContractItemService;
	}

	public void setClientContractItemService(ClientContractItemService clientContractItemService) {
		this.clientContractItemService = clientContractItemService;
	}

	public ClientContractService getClientContractService() {
		return clientContractService;
	}

	public void setClientContractService(ClientContractService clientContractService) {
		this.clientContractService = clientContractService;
	}

	public BillingItemService getBillingItemService() {
		return billingItemService;
	}

	public void setBillingItemService(BillingItemService billingItemService) {
		this.billingItemService = billingItemService;
	}

	public PolicyBillingRateService getPolicyBillingRateService() {
		return policyBillingRateService;
	}

	public void setPolicyBillingRateService(PolicyBillingRateService policyBillingRateService) {
		this.policyBillingRateService = policyBillingRateService;
	}

	public MemberElectronicCardService getMemberElectronicCardService() {
		return memberElectronicCardService;
	}

	public void setMemberElectronicCardService(MemberElectronicCardService memberElectronicCardService) {
		this.memberElectronicCardService = memberElectronicCardService;
	}

	public MemberImportService getMemberImportService() {
		return memberImportService;
	}

	public void setMemberImportService(MemberImportService memberImportService) {
		this.memberImportService = memberImportService;
	}

	public MemberSuspendHistoryService getMemberSuspendHistoryService() {
		return memberSuspendHistoryService;
	}

	public void setMemberSuspendHistoryService(MemberSuspendHistoryService memberSuspendHistoryService) {
		this.memberSuspendHistoryService = memberSuspendHistoryService;
	}

	public MemberLimitLayerService getMemberLimitLayerService() {
		return memberLimitLayerService;
	}

	public void setMemberLimitLayerService(MemberLimitLayerService memberLimitLayerService) {
		this.memberLimitLayerService = memberLimitLayerService;
	}

	public ProductLayerLimitService getProductLayerLimitService() {
		return productLayerLimitService;
	}

	public void setProductLayerLimitService(ProductLayerLimitService productLayerLimitService) {
		this.productLayerLimitService = productLayerLimitService;
	}

	public PolicyBenefitService getPolicyBenefitService() {
		return policyBenefitService;
	}

	public void setPolicyBenefitService(PolicyBenefitService policyBenefitService) {
		this.policyBenefitService = policyBenefitService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public MemberElectronicCardService getMemberCardService() {
		return memberCardService;
	}

	public void setMemberCardService(MemberElectronicCardService memberCardService) {
		this.memberCardService = memberCardService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public MemberClausulService getMemberClausulService() {
		return memberClausulService;
	}

	public void setMemberClausulService(MemberClausulService memberClausulService) {
		this.memberClausulService = memberClausulService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public ClausulService getClausulService() {
		return clausulService;
	}

	public void setClausulService(ClausulService clausulService) {
		this.clausulService = clausulService;
	}

	public BenefitService getBenefitService() {
		return benefitService;
	}

	public void setBenefitService(BenefitService benefitService) {
		this.benefitService = benefitService;
	}

	public ProductBenefitService getProductBenefitService() {
		return productBenefitService;
	}

	public void setProductBenefitService(ProductBenefitService productBenefitService) {
		this.productBenefitService = productBenefitService;
	}

	public ProductClausulService getProductClausulService() {
		return productClausulService;
	}

	public void setProductClausulService(ProductClausulService productClausulService) {
		this.productClausulService = productClausulService;
	}

	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}

	public void setMemberBenefitService(MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}

	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}

	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}

	public void setMemberDao(MemberDao object) {
		this.memberDao = object;
	}

	public MemberDao getMemberDao() {
		return this.memberDao;
	}

	public Collection<Member> searchDependent(Integer parentId, int index, int offset) throws Exception {
		Collection<Member> result = null;

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Collection<Member> searchMemberByName(String keyword, String category, int index, int offset)
			throws Exception {
		Collection<Member> result = null;

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Collection<Member> searchMemberByClient(String keyword, String category, Integer clientId, int index,
			int offset) throws Exception {
		Collection<Member> result = null;

		return result;
	}

	public Collection<Member> searchMemberByClientAndGroup(String keyword, String category, Integer clientId,
			Integer groupId, int index, int offset) throws Exception {
		Collection<Member> result = null;

		return result;
	}

	public Member getMember(String memberNumber, String bday) throws Exception {
		Member member = null;

		try {
			member = searchUnique("customerPolicyNumber", memberNumber);

			if (member != null && (bday != null && bday.length() == 6)) {
				String dd = bday.charAt(0) + "" + bday.charAt(1) + "";
				String mm = bday.charAt(2) + "" + bday.charAt(3) + "";
				String yy = bday.charAt(4) + "" + bday.charAt(5) + "";
				String yyyy = "";

				try {
					int yyInt = Integer.parseInt(yy);

					if (yyInt < 30) {
						yyyy = "20" + yy;
					} else {
						yyyy = "19" + yy;
					}
				} catch (Exception e) {
					yyyy = "1970";
				}

				String date = yyyy + "-" + mm + "-" + dd;
				System.out.println("BIRTH DATE : " + date);

				System.out.println("ADA MEMBER NAMANYA : " + member.getFirstName());
				if (member.getBirthday().toString().equals(date)) {
					return member;
				} else {
					return null;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;
	}

	public Collection<Object> activateAllPendingMember(Integer memberGroupId, ActionUser actionUser) throws Exception {

		Collection<Object> result = null;
		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m.memberId FROM Member m WHERE m.deletedStatus = 0 AND m.memberGroupId.memberGroupId = :gid "
					+ "AND m.status = -1 ";

			Query q = session.createQuery(query);

			q.setInteger("gid", memberGroupId);

			result = q.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Collection<Object> activateAllPolicyMember(Integer policyId, ActionUser actionUser) throws Exception {

		Collection<Object> result = null;
		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m.memberId FROM Member m WHERE m.deletedStatus = 0 AND m.currentPolicyId.policyId = :gid "
					+ "AND m.status = -1 ";

			Query q = session.createQuery(query);

			q.setInteger("gid", policyId);

			result = q.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * case --> pindah polis, ganti nomor peserta, ganti status relationship
	 * (mutasi principal/dependent)
	 * 
	 */
	public ActionResult activateMutation(Integer memberId, String newPolicyNumber, String additionalInfo,
			Date effectiveDate, Date expireDate, ActionUser actionUser) throws Exception {
		ActionResult actionResult = new ActionResult();

		try {
			Member member = get(memberId);

			if (member != null) {
				Policy currentActivePolicy = member.getCurrentPolicyId();

				if (member.getCurrentPolicyNumber().equalsIgnoreCase(newPolicyNumber)) {

					// ada 3 kemungkinan --> ganti nomor peserta dan pernikahan
					// sehingga jadi principal dan spouse
					// atau principal jadi dependant

					// check apakah family plan untuk per product atau bukan

					// new number | new principal number

					StringTokenizer addInfoToken = new StringTokenizer(additionalInfo, "|");

					int totalToken = addInfoToken.countTokens();

					if (totalToken == 2) {
						String newMemberNumber = addInfoToken.nextToken();
						String newPrincipalNumber = addInfoToken.nextToken();

						if (!newPrincipalNumber.equalsIgnoreCase(member.getCustomerPolicyNumber())) {

							Member newPrincipalMember = getMemberByNumberAndPolicy(newPrincipalNumber,
									currentActivePolicy.getPolicyId());

							if (newPrincipalMember != null) {
								member.setParentMember(newPrincipalMember);
								member.setCustomerNumber(newMemberNumber);
								member.setCustomerPolicyNumber(newMemberNumber);
								member.setParentNumber(newPrincipalNumber);
								member.setParentName(newPrincipalMember.getFirstName());
							}
						} else {

						}
					}
				} else {

					/**
					 * untuk mutasi ke polis yang berbeda ada beberapa kasus
					 * yaitu bisa naik plan atau tetep dengan plan yang sama
					 * 
					 * untuk kedua skenario tersebut, member_product harus
					 * dibuat yang baru dan claim yang ada tetep di link yang
					 * member_product
					 */

					Policy newPolicy = policyService.getActivePolicyByNumber(newPolicyNumber,
							member.getClientId().getClientId());

					if (newPolicy != null) {
						Collection<MemberProduct> currentProductList = memberProductService
								.getMemberActiveProduct(memberId);

					}

					MemberGroup group = member.getMemberGroupId();

					String tipe = group.getTipe() == null ? "G" : group.getTipe();

					SubscriptionStatus status = new SubscriptionStatus();
					status.setStatusId(SubscriptionStatus.ACTIVE);

					if (member != null && member.getStatus() == SubscriptionStatus.PENDING_MUTATION) {

						if (member.getCurrentCardNumber() != null && member.getNextCardNumber() != null) {
							if (!member.getCurrentCardNumber().trim()
									.equalsIgnoreCase(member.getNextCardNumber().trim())) {
								member.setCurrentCardNumber(member.getNextCardNumber());
							}
						}
						member.setExpireDate(member.getNextExpireDate());
						member.setResignedDate(member.getNextExpireDate());
						member.setCurrentPolicyId(newPolicy);
						member.setCurrentPolicyNumber(newPolicyNumber);
						member.setMemberGroupId(newPolicy.getMemberGroupId());

						member.setStatus(SubscriptionStatus.ACTIVE);
						String productCode = member.getCurrentProductCode();

						StringTokenizer token = new StringTokenizer(productCode);

						Product product = null;

						double proratedRatio = 1.0;

						boolean isPrincipal = false;
						boolean isDependent = false;
						if (member != null && member.getCustomerPolicyNumber() != null
								&& member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())) {
							isPrincipal = true;
						}

						if (member != null && member.getCustomerPolicyNumber() != null
								&& !member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())) {
							isDependent = true;
						}

						while (token.hasMoreTokens()) {

							productCode = token.nextToken();

							if (member.getCurrentPolicyId() != null) {
								product = productService.getActiveProduct(productCode, member.getCurrentPolicyId().getPolicyId());

							} else {
								if (tipe.equalsIgnoreCase("I")) {
									product = productService.getActiveProduct(productCode);
								}
							}

							double multiplier = 1.0;

							if (product != null) {

								if (product.getSalaryMultiplier() != null) {
									multiplier = product.getSalaryMultiplier().doubleValue();
								}

								MemberProduct memberProduct = new MemberProduct();

								memberProduct.setCurrentMemberNumber(member.getNextCustomerNumber());
								memberProduct.setCurrentCardNumber(member.getNextCardNumber());
								memberProduct.setCurrentPolicyNumber(member.getNextPolicyNumber());
								memberProduct.setCurrentPolicyId(member.getCurrentPolicyId());

								memberProduct.setIsProductActive(1);
								memberProduct.setProductClassRate(product.getProductClassRate());
								memberProduct.setMemberId(member);
								memberProduct.setProductId(product);
								memberProduct.setRegisterDate(member.getNextEffectiveDate());
								memberProduct.setExpireDate(member.getNextExpireDate());
								memberProduct.setDisabilityLength(product.getDisabilityLength());
								memberProduct.setBenefitUsage(0.0);

								memberProduct.setMemberProductStatus(status);

								proratedRatio= getProrateRatio(member, product);

								if (product.getEdcEnabled() != null) {

									if (product.getEdcEnabled().intValue() == 1) {
										memberProduct.setEDCEnabled(true);
										memberProduct
												.setLimitBenefitPublishedOnEdc(product.getLimitBenefitShowedOnEdc());
									} else if (product.getEdcEnabled().intValue() == 0) {
										memberProduct.setEDCEnabled(false);
										memberProduct.setLimitBenefitPublishedOnEdc(0);
									}
								}

								if (product.getAnnualLimitValue().intValue() > 0) {
									if (product.getIsUsingSalary() != null
											&& product.getIsUsingSalary().intValue() == 1) {
										Member parent = member.getParentMember();
										Double salary = parent.getCurrentSalary();

										memberProduct.setAnnualBenefit(salary * multiplier); 
										memberProduct.setActualBenefitLimit(salary * proratedRatio * multiplier);
									} else {
										memberProduct
												.setActualBenefitLimit(product.getAnnualLimitValue() * proratedRatio);
										memberProduct.setAnnualBenefit(product.getAnnualLimitValue()); 
									}
								} else {
									if (product.getIsUsingSalary() != null
											&& product.getIsUsingSalary().intValue() == 1) {
										Member parent = member.getParentMember();
										if (parent != null) {
											Double salary = parent.getCurrentSalary();
											memberProduct.setAnnualBenefit(salary * multiplier); 
											memberProduct.setActualBenefitLimit(salary * proratedRatio * multiplier);
										} else {
											if (member.getParentNumber()
													.equalsIgnoreCase(member.getCustomerPolicyNumber())) {
												Double salary = member.getCurrentSalary();
												memberProduct.setAnnualBenefit(salary * multiplier); 
												memberProduct
														.setActualBenefitLimit(salary * proratedRatio * multiplier);
											}
										}
									} else {
										memberProduct.setActualBenefitLimit(Double.valueOf(-1));
										memberProduct.setAnnualBenefit(Double.valueOf(-1));
									}
								}

								if (product.getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY
										|| product.getProductType().getProductTypeId()
												.intValue() == ProductType.FAMILY) {

									Member parent = member.getParentMember();

									if (parent != null
											&& parent.getMemberId().intValue() != member.getMemberId().intValue()) {
										MemberProduct familyProduct = null;
										
										familyProduct = getActiveFamilyProduct(product, parent);
										
										if (familyProduct != null) {
											memberProduct.setFamilyProductId(familyProduct);
										}
									}
								}

								if (product.getSharedProductId() != null) {
									MemberProduct sharedProduct = getSharedProduct(member, product);									
									if (sharedProduct != null) {
										memberProduct.setSecondaryCoverageId(sharedProduct);
									}
								}

								memberProductService.create(memberProduct, actionUser);

								Collection<ProductBenefit> benefits = getProductBenefitList(product);

								Collection<ProductClausul> clausuls = product.getProductClausuls();

								System.out.println(" total benefit : " + benefits.size());

								if (benefits != null && benefits.size() > 0) {
									Iterator<ProductBenefit> iterator = benefits.iterator();

									if (iterator != null) {
										MemberBenefit memberBenefit = null;
										ProductBenefit productBenefit = null;

										while (iterator.hasNext()) {
											productBenefit = iterator.next();

											if (productBenefit != null && !isProductBenefitExist(member.getMemberId(),
													productBenefit, memberProduct)) {

												memberBenefit = new MemberBenefit();
												memberBenefit.setBenefitType(MemberBenefit.MEMBER_PRODUCT_BENEFIT);
												memberBenefit.setMemberProductId(memberProduct);
												memberBenefit.setMemberId(member);

									
												initializeMemberBenefitUsage(memberBenefit);

												synchronizeBenefit(actionUser, isPrincipal, isDependent, productBenefit,
														memberBenefit);

												memberBenefit.setEffectiveDate(member.getNextEffectiveDate());
												memberBenefit.setExpireDate(member.getNextExpireDate());

												if (productBenefit.getIsAsCharge() != null
														&& productBenefit.getIsAsCharge().booleanValue()) {
													memberBenefit.setCurrentBenefitPosition(-1.0);
												} else {
													if (productBenefit.getBenefitUsageType() != null && productBenefit
															.getBenefitUsageType().getBenefitUsageTypeId()
															.intValue() == BenefitUsageType.ANNUAL) {
														
														memberBenefit.setCurrentBenefitPosition(
																productBenefit.getBenefitLimit());
														
													}
												}

												memberBenefit.setMemberBenefitStatus(SubscriptionStatus.ACTIVE);

												if (productBenefit.getSharedBenefitId() != null) {
													MemberBenefit sharedBenefit = getSharedBenefit(member, productBenefit);

													if (sharedBenefit != null) {
														memberBenefit.setSharedBenefitId(sharedBenefit);
													}
												}
												memberBenefitService.create(memberBenefit, actionUser);
											}
										}
									}
								}
								if (clausuls != null && clausuls.size() > 0) {
									Iterator<ProductClausul> iterator = clausuls.iterator();
									deactivateMemberClausul(actionUser, member);

									if (iterator != null) {
										populateMemberClausul(actionUser, member, iterator, memberProduct);
									}
								}

								update(member, actionUser);
								actionResult.setActionCode("ACTIVATEMUTATION");
								actionResult.setResult(true);
								actionResult.setAdditionalMessage("Sukses Melakukan Aktifasi Mutasi Member");
							} else {
								actionResult.setActionCode("ACTIVATEMUTATION");
								actionResult.setResult(false);
								actionResult.setAdditionalMessage("Product Asuransi Tidak Terdefinisikan");
							}
						}
						// multiple insurance product
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actionResult;
	}

	private MemberProduct getActiveFamilyProduct(Product product, Member parent)
			throws Exception {
		
		/**
		 * BUGFIX 0000034 [Benefit] Product Synchronize
		 * Duplicates Patch : Sort By memberProductId DESC
		 */
		MemberProduct familyProduct = null;
		
		String[] eqParam = { "deletedStatus", "memberId.memberId",
				"productId.productId","memberProductStatus.statusId" };
		Object[] eqValue = { 0, parent.getMemberId(), product.getProductId(),SubscriptionStatus.ACTIVE };

		Collection<MemberProduct> familyProductList = memberProductService.search(null,
				null, eqParam, eqValue,false,"memberProductId", 0, 1);

		if (familyProductList != null && familyProductList.size() > 0) {
			Iterator<MemberProduct> iteratorProduct = familyProductList.iterator();

			if (iteratorProduct.hasNext()) {
				familyProduct = iteratorProduct.next();

				
			}
		}
		return familyProduct;
	}

	private MemberProduct getSharedProduct(Member member, Product product)
			throws Exception {
		MemberProduct sharedProduct = null;
		
		String[] eqParam = { "deletedStatus", "memberId.memberId", "productId.productId" };
		Object[] eqValue = { 0, member.getMemberId(),
				product.getSharedProductId().getProductId() };

		Collection<MemberProduct> sharedProductList = memberProductService.search(null,
				null, eqParam, eqValue, 0, 1);

		if (sharedProductList != null && sharedProductList.size() > 0) {
			Iterator<MemberProduct> iteratorProduct = sharedProductList.iterator();

			if (iteratorProduct.hasNext()) {
				sharedProduct = iteratorProduct.next();											
			}
		}
		return sharedProduct;
	}

	public ActionResult activateUpgrade(Integer memberId, ActionUser actionUser) throws Exception {
		ActionResult actionResult = new ActionResult();

		Member member = get(memberId);
		SubscriptionStatus status = new SubscriptionStatus();
		status.setStatusId(SubscriptionStatus.ACTIVE);

		if (member != null && member.getStatus() == SubscriptionStatus.PENDING_UPGRADE) {

			member.setStatus(SubscriptionStatus.ACTIVE);

			String productCode = member.getCurrentProductCode();

			StringTokenizer token = new StringTokenizer(productCode);

			Product product = null;

			boolean isPrincipal = false;
			boolean isDependent = false;

			boolean isParentActive = true;

			if (member != null && member.getCustomerPolicyNumber() != null
					&& member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())) {
				isPrincipal = true;
			}

			if (member != null && member.getCustomerPolicyNumber() != null
					&& !member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())) {
				isDependent = true;
			}

			if (isParentActive) {
				double proratedRatio = 1.0;

				while (token.hasMoreTokens()) {

					productCode = token.nextToken();
					product = productService.getActiveProduct(productCode, member.getCurrentPolicyId().getPolicyId());

					if (product != null && !isProductExist(member.getMemberId(), product)) {
						double previousUsage = 0.0;
						double previousbenefitExcessed = 0.0;
						double previousExGratia = 0.0;
						double previousFamilyUsage = 0.0;

						MemberProduct memberProductPrev = memberProductService.getMemberActiveProduct(memberId,
								product.getCaseCategory().getCaseCategoryId());

						if (memberProductPrev != null) {
							ProductType previousProductType = memberProductPrev.getProductId().getProductType();
							
							if (previousProductType.getProductTypeId().intValue() == ProductType.GROUP
									|| previousProductType.getProductTypeId().intValue() == ProductType.INDIVIDUAL) {
								
								if (memberProductPrev != null && memberProductPrev.getBenefitUsage() != null) {
									previousUsage = memberProductPrev.getBenefitUsage().doubleValue();
								}
								if (memberProductPrev.getBenefitExcessed() != null) {
									previousbenefitExcessed = memberProductPrev.getBenefitExcessed().doubleValue();
								}
								if (memberProductPrev.getBenefitExGratia() != null) {
									previousExGratia = memberProductPrev.getBenefitExGratia().doubleValue();
								}
							} else if (previousProductType.getProductTypeId().intValue() == ProductType.GROUP_FAMILY
									|| previousProductType.getProductTypeId().intValue() == ProductType.FAMILY) {

								if (member.getParentMember().getMemberId().intValue() == member.getMemberId().intValue()) {
									previousFamilyUsage = memberProductService.getTotalFamilyLimitUsage(memberProductPrev.getMemberProductId());
								}
								if (memberProductPrev != null && memberProductPrev.getBenefitUsage() != null) {
									previousUsage = memberProductPrev.getBenefitUsage().doubleValue();
								}
								if (memberProductPrev.getBenefitExcessed() != null) {
									previousbenefitExcessed = memberProductPrev.getBenefitExcessed().doubleValue();
								}
								if (memberProductPrev.getBenefitExGratia() != null) {
									previousExGratia = memberProductPrev.getBenefitExGratia().doubleValue();
								}
							}
						}

						proratedRatio = getProrateRatio(member, product);
						
						MemberProduct memberProduct = new MemberProduct();
						memberProduct.setCurrentMemberNumber(member.getCustomerPolicyNumber());
						memberProduct.setCurrentCardNumber(member.getCurrentCardNumber());
						memberProduct.setCurrentPolicyNumber(member.getCurrentPolicyNumber());

						if (member.getCurrentPolicyId() != null) {
							memberProduct.setCurrentPolicyId(member.getCurrentPolicyId());
						}

						memberProduct.setIsProductActive(1);
						memberProduct.setProductClassRate(product.getProductClassRate());
						memberProduct.setMemberId(member);
						memberProduct.setProductId(product);
						memberProduct.setRegisterDate(member.getProductUpgradeEffectiveDate());
						memberProduct.setExpireDate(member.getExpireDate());
						memberProduct.setDisabilityLength(product.getDisabilityLength());
						memberProduct.setProductCurrencyId(product.getProductCurrencyId());
						memberProduct.setIsProrateLimit(product.getIsUsingProrateLimit());

						memberProduct.setMemberProductStatus(status);


						if (product.getEdcEnabled() != null) {

							if (product.getEdcEnabled().intValue() == 1) {
								memberProduct.setEDCEnabled(true);
								memberProduct.setLimitBenefitPublishedOnEdc(product.getLimitBenefitShowedOnEdc());
							} else if (product.getEdcEnabled().intValue() == 0) {
								memberProduct.setEDCEnabled(false);
								memberProduct.setLimitBenefitPublishedOnEdc(0);
							}
						}

						if (product != null && !isProductExist(member.getMemberId(), product)) {
							if (product.getAnnualLimitValue().doubleValue() > 0) {

								memberProduct.setBenefitUsage(previousUsage);
								memberProduct.setBenefitExcessed(previousbenefitExcessed);
								memberProduct.setBenefitExGratia(previousExGratia);

								double percentage = previousUsage / product.getAnnualLimitValue().doubleValue() * 100;

								memberProduct.setBenefitUsagePercentage(percentage);

								if (product.getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY
										|| product.getProductType().getProductTypeId()
												.intValue() == ProductType.FAMILY) {

									if (member.getParentMember().getMemberId().intValue() == member.getMemberId()
											.intValue()) {
										memberProduct.setActualBenefitLimit(
												(product.getAnnualLimitValue() * proratedRatio) - previousFamilyUsage);
									}
								} else {
									memberProduct.setActualBenefitLimit(
											(product.getAnnualLimitValue() * proratedRatio) - previousUsage);
								}

								memberProduct.setAnnualBenefit(product.getAnnualLimitValue() * proratedRatio);

							} else {
								memberProduct.setBenefitUsage(previousUsage);
								memberProduct.setBenefitExcessed(previousbenefitExcessed);
								memberProduct.setBenefitExGratia(previousExGratia);
								memberProduct.setActualBenefitLimit(Double.valueOf(-1));
								memberProduct.setAnnualBenefit(Double.valueOf(-1));
							}
						}
						if (product.getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY
								|| product.getProductType().getProductTypeId().intValue() == ProductType.FAMILY) {
							// check if

							Member parent = member.getParentMember();

							if (parent != null && parent.getMemberId().intValue() != member.getMemberId().intValue()) {
								MemberProduct familyProduct =getActiveFamilyProduct(product, parent);
								
								if (familyProduct != null) {
									memberProduct.setFamilyProductId(familyProduct);
								}
							}
							else {
								memberProduct.setFamilyProductId(memberProduct);
							}
						}

						if (product.getSharedProductId() != null) {
							MemberProduct sharedProduct = getActiveSharedProduct(member, product);
							
							if (sharedProduct != null) {
								memberProduct.setSecondaryCoverageId(sharedProduct);
							}
						}

						MemberProduct newMP = memberProductService.create(memberProduct, actionUser);

						if (newMP != null) {
							Collection<ProductLayerLimit> layerProductList = getProductLayerLimit(product);
							if (layerProductList != null) {
								for (ProductLayerLimit productLayerLimit : layerProductList) {
									if (productLayerLimit != null) {
										MemberLimitLayer limitLayer = createMemberLayerLimit(actionUser, member,product, newMP, productLayerLimit);
										if (limitLayer != null) {
											Collection<ProductBenefit> layerBenefitList = getProductLayerBenefitList(productLayerLimit);
											Iterator<ProductBenefit> iteratorLayer = layerBenefitList.iterator();
											populateMemberBenefit(actionUser, member, iteratorLayer, newMP,limitLayer,status);
										}
									}
								}
							}
						}

						SubscriptionStatus terminatedStatus = new SubscriptionStatus();
						terminatedStatus.setStatusId(SubscriptionStatus.UPGRADED);
						Date effectiveDateUpgrade = new Date(member.getProductUpgradeEffectiveDate().getTime() - 1);

						if (memberProductPrev != null) {
							memberProductPrev.setMemberProductStatus(terminatedStatus);
							memberProductPrev.setExpireDate(effectiveDateUpgrade);
							memberProductPrev.setIsProductActive(0);

							memberProductService.update(memberProductPrev, actionUser);
						}

						Collection<ProductBenefit> benefits = getMainProductBenefitList(product);
						Collection<ProductClausul> clausuls = product.getProductClausuls();

						if (benefits != null && benefits.size() > 0) {
							Iterator<ProductBenefit> iterator = benefits.iterator();

							if (iterator != null) {
								MemberBenefit memberBenefit = null;
								ProductBenefit productBenefit = null;

								while (iterator.hasNext()) {
									double previousMBenefitUsage = 0.0;
									double previousMBenefitExcessed = 0.0;
									double previousMBenefitExGratia = 0.0;
									double previousMBenefitCashless = 0.0;
									double previousMBenefitReimburse = 0.0;
									double cashlessPercentage = 0.0;
									double reimbursePercentage = 0.0;
									double benefitPercentage = 0.0;
									double prevOccurance = 0.0;

									productBenefit = iterator.next();
									Collection<MemberBenefit> previousBenefitList = null;

									if (productBenefit.getItemCategoryId().getIsSurgeryItem() != null
											&& productBenefit.getItemCategoryId().getIsSurgeryItem().intValue() == 1) {

										previousBenefitList = getPreviousActiveSurgeryBenefitList(member, product, memberProductPrev, productBenefit);
									
										
									} else {
										if (memberProductPrev != null) {
											previousBenefitList = getPreviousActiveBenefitList(member, product, memberProductPrev, productBenefit);
										}
									}

									if (previousBenefitList != null && !previousBenefitList.isEmpty()) {
										Iterator<MemberBenefit> iteratorBenefit = previousBenefitList.iterator();
										if (iteratorBenefit != null && iteratorBenefit.hasNext()) {
											MemberBenefit prevBenefit = iteratorBenefit.next();

											if (prevBenefit != null) {
												if (prevBenefit.getBenefitUsage() != null) {
													previousMBenefitUsage = prevBenefit.getBenefitUsage().doubleValue();
												}
												if (prevBenefit.getBenefitExcessed() != null) {
													previousMBenefitExcessed = prevBenefit.getBenefitExcessed().doubleValue();
												}
												if (prevBenefit.getBenefitExGratia() != null) {
													previousMBenefitExGratia = prevBenefit.getBenefitExGratia().doubleValue();
												}
												if (prevBenefit.getCashlessBenefitUsage() != null) {
													previousMBenefitCashless = prevBenefit.getCashlessPercentage().doubleValue();
												}
												if (prevBenefit.getReimbursementBenefitUsage() != null) {
													previousMBenefitReimburse = prevBenefit
															.getReimbursementBenefitUsage().doubleValue();
												}
												if (prevBenefit.getBenefitOccuranceUsage() != null) {
													prevOccurance = prevBenefit.getBenefitOccuranceUsage().doubleValue();
												}

												prevBenefit.setMemberBenefitStatus(SubscriptionStatus.UPGRADED);
												prevBenefit.setDeletedStatus(0);
												// prevBenefit.setExpireDate(member.getProductUpgradeEffectiveDate());
												prevBenefit.setExpireDate(effectiveDateUpgrade);
												memberBenefitService.update(prevBenefit, actionUser);
											}
										}
									}

									if (productBenefit != null && !isProductBenefitExist(member.getMemberId(),
											productBenefit, memberProduct)) {

										memberBenefit = new MemberBenefit();

										memberBenefit.setBenefitType(MemberBenefit.MEMBER_PRODUCT_BENEFIT);
										memberBenefit.setMemberProductId(memberProduct);
										memberBenefit.setItemCategoryId(productBenefit.getItemCategoryId());
										memberBenefit.setMemberId(member);

										memberBenefit.setBenefitUsage(previousMBenefitUsage);
										memberBenefit.setBenefitExcessed(previousMBenefitExcessed);
										memberBenefit.setBenefitExGratia(previousMBenefitExGratia);
										memberBenefit.setCashlessBenefitUsage(previousMBenefitCashless);
										memberBenefit.setReimbursementBenefitUsage(previousMBenefitReimburse);
										memberBenefit.setMemberBenefitStatus(SubscriptionStatus.ACTIVE);
										
										memberBenefit.setEffectiveDate(effectiveDateUpgrade);
										memberBenefit.setExpireDate(member.getExpireDate());

										synchronizeBenefit(actionUser, isPrincipal, isDependent, productBenefit,memberBenefit);

										if (productBenefit.getSharedBenefitId() != null) {
											MemberBenefit sharedBenefit = getSharedBenefit(member, productBenefit);

											if (sharedBenefit != null) {
												memberBenefit.setSharedBenefitId(sharedBenefit);
											}
										}
										memberBenefitService.create(memberBenefit, actionUser);
									}
								}

								// edit member benefit yang lama biar gak ngikut
								Collection<MemberBenefit> previousBenefitList2 = null;
								if (memberProductPrev != null) {

									String[] eqParamBenefit2 = { "memberId.memberId",
											"memberProductId.memberProductId" };

									Object[] eqValueBenefit2 = { member.getMemberId(),
											memberProductPrev.getMemberProductId() };

									previousBenefitList2 = memberBenefitService.search(null, null, eqParamBenefit2,
											eqValueBenefit2);

									if (previousBenefitList2.size() > 0) {
										Iterator<MemberBenefit> iteratorBenefit2 = previousBenefitList2.iterator();
										while (iteratorBenefit2.hasNext()) {
											if (iteratorBenefit2 != null && iteratorBenefit2.hasNext()) {
												MemberBenefit prevBenefit2 = iteratorBenefit2.next();

												prevBenefit2.setMemberBenefitStatus(SubscriptionStatus.UPGRADED);
												prevBenefit2.setDeletedStatus(0);
												// prevBenefit2.setExpireDate(member.getProductUpgradeEffectiveDate());
												prevBenefit2.setExpireDate(effectiveDateUpgrade);
												memberBenefitService.update(prevBenefit2, actionUser);

											}
										}
									}
								}
							}
						}
						if (clausuls != null && clausuls.size() > 0) {
							Iterator<ProductClausul> iterator = clausuls.iterator();
							deactivateMemberClausul(actionUser, member);

							if (iterator != null) {
								populateMemberClausul(actionUser, member, iterator, memberProduct);
							}
						}

						update(member, actionUser);
						actionResult.setActionCode("ACTIVATEMEMBER");
						actionResult.setResult(true);
						actionResult.setAdditionalMessage("Sukses Melakukan Aktifasi Member");
					} else {
						actionResult.setActionCode("ACTIVATEMEMBER");
						actionResult.setResult(false);
						actionResult.setAdditionalMessage("Product Asuransi Tidak Terdefinisikan");
					}
				}
			} else {
				actionResult.setActionCode("ACTIVATEMEMBER");
				actionResult.setResult(false);
				actionResult.setAdditionalMessage("Product Asuransi Tidak Terdefinisikan");
			}
			// multiple insurance product
		}
		return actionResult;
	}

	private MemberLimitLayer createMemberLayerLimit(ActionUser actionUser, Member member, Product product,
			MemberProduct memberProduct, ProductLayerLimit productLayerLimit) throws Exception {
		MemberLimitLayer memberLayer = new MemberLimitLayer();
		memberLayer.setMemberId(member);
		memberLayer.setMemberProductId(memberProduct);
		memberLayer.setProductId(product);
		memberLayer.setProductLimitLayerId(productLayerLimit);
		memberLayer.setActualBenefitLimit(productLayerLimit.getAnnualLimit());
		memberLayer.setAnnualLimit(productLayerLimit.getAnnualLimit());
		memberLayer.setCoSharePercentage(productLayerLimit.getCoSharePercentage());
		memberLayer.setCoShareAmount(productLayerLimit.getCoShareAmount());
		memberLayer.setStatus(SubscriptionStatus.ACTIVE);
		memberLayer.setDeletedStatus(0);
		memberLayer.setLayer(productLayerLimit.getLayerLevel());

		if (productLayerLimit.getDiagnosisId() != null) {
			memberLayer.setDiagnosisId(productLayerLimit.getDiagnosisId());
		}
		if (productLayerLimit.getDiagnosisSetId() != null) {
			memberLayer.setDiagnosisSetId(productLayerLimit.getDiagnosisSetId());
		}
		memberLayer.setDeductible(productLayerLimit.getDeductible());
		
		/**
		 * check family plan limit
		 */
		
		if (product.getProductType().getProductTypeId().intValue() == ProductType.FAMILY 
				|| product.getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY){
			
			if (member.getParentMember().getMemberId().intValue() == member.getMemberId().intValue()){
				memberLayer.setFamilyLimitLayerId(memberLayer);
			}
			else {
				MemberLimitLayer familyLayerLimit = memberLimitLayerService.getMemberLimitLayer(memberProduct.getFamilyProductId().getMemberProductId(), productLayerLimit.getLayerLevel(), SubscriptionStatus.ACTIVE);
			
				if (familyLayerLimit != null){
					memberLayer.setFamilyLimitLayerId(familyLayerLimit);
				}
			}
		}

		MemberLimitLayer limitLayer = memberLimitLayerService.create(memberLayer,actionUser);
		return limitLayer;
	}

	private Collection<ProductLayerLimit> getProductLayerLimit(Product product) throws Exception {
		String[] eqLayerParam = { "productId.productId", "deletedStatus" };
		Object[] eqLayerValue = { product.getProductId(), 0 };

		int totalLayer = productLayerLimitService.getTotal(null, null, eqLayerParam, eqLayerValue);
		Collection<ProductLayerLimit> layerProductList = productLayerLimitService.search(null, null,
				eqLayerParam, eqLayerValue, 0, totalLayer);
		return layerProductList;
	}

	private MemberProduct getActiveSharedProduct(Member member, Product product)
			throws Exception {
		
		MemberProduct sharedProduct = null;
		
		/**
		 * BUGFIX 0000034 [Benefit] Product Synchronize
		 * Duplicates Patch : Sort By memberProductId DESC
		 */

		
		String[] eqParam = { "deletedStatus", "memberId.memberId", "productId.productId",
				"memberProductStatus.statusId" };
		Object[] eqValue = { 0, member.getMemberId(), product.getSharedProductId().getProductId(),
				SubscriptionStatus.ACTIVE };

		Collection<MemberProduct> sharedProductList = memberProductService.search(null, null,
				eqParam, eqValue,false,"memberProductId", 0, 1);

		if (sharedProductList != null && sharedProductList.size() > 0) {
			Iterator<MemberProduct> iteratorProduct = sharedProductList.iterator();

			if (iteratorProduct.hasNext()) {
				sharedProduct = iteratorProduct.next();

				
			}
		}
		return sharedProduct;
	}

	public ActionResult activateSynchronize(Integer memberId, ActionUser actionUser) throws Exception {
		ActionResult actionResult = new ActionResult();

		Member member = get(memberId);
		SubscriptionStatus status = new SubscriptionStatus();
		status.setStatusId(SubscriptionStatus.ACTIVE);

		if (member != null && member.getStatus() == SubscriptionStatus.ACTIVE) {

			member.setStatus(SubscriptionStatus.ACTIVE);

			String productCode = member.getCurrentProductCode();
			
			StringTokenizer token  = new StringTokenizer(productCode);			
			System.out.println("productCode : "+productCode);

			Product product = null;

			boolean isPrincipal = false;
			boolean isDependent = false;
			if (member != null && member.getCustomerPolicyNumber() != null
					&& member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())) {
				isPrincipal = true;
			}

			if (member != null && member.getCustomerPolicyNumber() != null
					&& !member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())) {
				isDependent = true;
			}

			double proratedRatio = 1.0;


			while (token.hasMoreTokens()) {

				productCode = token.nextToken();
				product = productService.getActiveProduct(productCode, member.getCurrentPolicyId().getPolicyId());

	
				
				if (product != null && product.getDoSynchronize() != null && product.getDoSynchronize().intValue() == 1){
					// 

					System.out.println("SYNCHRONIZE FOR " + productCode);
					double previousUsage = 0.0;
					double previousbenefitExcessed = 0.0;
					double previousExGratia = 0.0;

					MemberProduct memberProduct = memberProductService.getMemberActiveProduct(memberId,
							product.getCaseCategory().getCaseCategoryId());

					memberProduct.setIsProductActive(1);
					memberProduct.setProductClassRate(product.getProductClassRate());
					memberProduct.setMemberId(member);

					memberProduct.setCurrentMemberNumber(member.getCustomerPolicyNumber());
					memberProduct.setCurrentCardNumber(member.getCurrentCardNumber());
					memberProduct.setCurrentPolicyNumber(member.getCurrentPolicyNumber());

					memberProduct.setProductId(product);

					memberProduct.setDisabilityLength(product.getDisabilityLength());
					memberProduct.setProductCurrencyId(product.getProductCurrencyId());
					memberProduct.setIsProrateLimit(product.getIsUsingProrateLimit());

					memberProduct.setMemberProductStatus(status);


					proratedRatio = getProrateRatio(member, product);

					if (product.getEdcEnabled() != null) {

						if (product.getEdcEnabled().intValue() == 1) {
							memberProduct.setEDCEnabled(true);
							memberProduct.setLimitBenefitPublishedOnEdc(product.getLimitBenefitShowedOnEdc());
						} else if (product.getEdcEnabled().intValue() == 0) {
							memberProduct.setEDCEnabled(false);
							memberProduct.setLimitBenefitPublishedOnEdc(0);
						}
					}

					if (product.getAnnualLimitValue().intValue() > 0) {

						memberProduct.setBenefitUsage(previousUsage);
						memberProduct.setBenefitExcessed(previousbenefitExcessed);
						memberProduct.setBenefitExGratia(previousExGratia);

						double percentage = previousUsage / product.getAnnualLimitValue().doubleValue() * 100;

						memberProduct.setBenefitUsagePercentage(percentage);
						memberProduct
								.setActualBenefitLimit((product.getAnnualLimitValue() * proratedRatio) - previousUsage);
						memberProduct.setAnnualBenefit(product.getAnnualLimitValue() * proratedRatio);

					} else {
						memberProduct.setBenefitUsage(previousUsage);
						memberProduct.setBenefitExcessed(previousbenefitExcessed);
						memberProduct.setBenefitExGratia(previousExGratia);
						memberProduct.setActualBenefitLimit(Double.valueOf(-1));
						memberProduct.setAnnualBenefit(Double.valueOf(-1));
					}

					if (product.getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY
							|| product.getProductType().getProductTypeId().intValue() == ProductType.FAMILY) {

						Member parent = member.getParentMember();

						if (parent != null && parent.getMemberId().intValue() != member.getMemberId().intValue()) {
							MemberProduct familyProduct = getActiveFamilyProduct(product, parent);
							if (familyProduct != null) {
								memberProduct.setFamilyProductId(familyProduct);
							}
						}
					}

					if (product.getSharedProductId() != null) {						
						MemberProduct sharedProduct = getActiveSharedProduct(member, product);						
						if (sharedProduct != null) {
							memberProduct.setSecondaryCoverageId(sharedProduct);
						}
					}

					/**
					 * 0000034: [Benefit] Product Synchronize Duplicates
					 * 
					 * FIX: Change the Create Revision to direct update to latest benefit
					 */

					memberProductService.update(memberProduct, actionUser);

					
					Collection<ProductBenefit> benefits = getMainProductBenefitList(product);

					Collection<ProductClausul> clausuls = product.getProductClausuls();

					if (benefits != null && benefits.size() > 0) {
						Iterator<ProductBenefit> iterator = benefits.iterator();

						if (iterator != null) {

							ProductBenefit productBenefit = null;

							while (iterator.hasNext()) {
								double previousMBenefitUsage = 0.0;
								double previousMBenefitExcessed = 0.0;
								double previousMBenefitExGratia = 0.0;
								double previousMBenefitCashless = 0.0;
								double previousMBenefitReimburse = 0.0;
								double cashlessPercentage = 0.0;
								double reimbursePercentage = 0.0;
								double benefitPercentage = 0.0;
								double prevOccurance = 0.0;

								productBenefit = iterator.next();
								Collection<MemberBenefit> previousBenefitList = null;

								if (productBenefit.getItemCategoryId().getIsSurgeryItem() != null
										&& productBenefit.getItemCategoryId().getIsSurgeryItem().intValue() == 1) {
								
									previousBenefitList = getPreviousActiveSurgeryBenefitList(member, product,
											memberProduct, productBenefit);
								} else {
									previousBenefitList = getPreviousActiveBenefitList(member, product, memberProduct,
											productBenefit);
								}

								/**
								 * 0000034: [Benefit] Product Synchronize
								 * Duplicates
								 * 
								 * UPDATING REVISION WITHOUT HISTORICAL EVIDENCE
								 * REQUEST PAK FIRMAN 4 FEB 2016 - 11:31
								 */
								if (previousBenefitList != null && !previousBenefitList.isEmpty()) {
									Iterator<MemberBenefit> iteratorBenefit = previousBenefitList.iterator();
									if (iteratorBenefit != null && iteratorBenefit.hasNext()) {
										MemberBenefit memberBenefit = iteratorBenefit.next();

										if (memberBenefit != null) {

											memberBenefit.setBenefitType(MemberBenefit.MEMBER_PRODUCT_BENEFIT);
											memberBenefit.setMemberProductId(memberProduct);
											memberBenefit.setItemCategoryId(productBenefit.getItemCategoryId());
											memberBenefit.setMemberId(member);
											
											if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == BenefitUsageType.ANNUAL){
												previousMBenefitUsage = memberBenefit.getBenefitUsage() == null ? 0.0 : memberBenefit.getBenefitUsage();
												benefitPercentage = previousMBenefitUsage / memberBenefit.getBenefitLimit();
														 
												memberBenefit.setBenefitUsagePercentage(benefitPercentage);
											}
											
											memberBenefit.setMemberBenefitStatus(SubscriptionStatus.ACTIVE);

											synchronizeBenefit(actionUser, isPrincipal, isDependent, productBenefit,
													memberBenefit);

											if (productBenefit.getSharedBenefitId() != null) {
												MemberBenefit sharedBenefit = getSharedBenefit(member, productBenefit);
												
												if (sharedBenefit != null) {
													memberBenefit.setSharedBenefitId(sharedBenefit);
												}
											}
											memberBenefitService.update(memberBenefit, actionUser);
										}
									}
								} else {
									/**
									 * TODO: Previous Benefit Not Found populate
									 * new product benefit
									 */
									MemberBenefit memberBenefit = new MemberBenefit();
									memberBenefit.setBenefitType(MemberBenefit.MEMBER_PRODUCT_BENEFIT);
									memberBenefit.setMemberProductId(memberProduct);
									memberBenefit.setItemCategoryId(productBenefit.getItemCategoryId());
									memberBenefit.setMemberId(member);
									memberBenefit.setEffectiveDate(memberProduct.getRegisterDate());
									memberBenefit.setExpireDate(memberProduct.getExpireDate());
									
									initializeMemberBenefitUsage(memberBenefit);
									
									memberBenefit.setMemberBenefitStatus(SubscriptionStatus.ACTIVE);

									synchronizeBenefit(actionUser, isPrincipal, isDependent, productBenefit,memberBenefit);

									if (productBenefit.getSharedBenefitId() != null) {
										MemberBenefit sharedBenefit = null;

										sharedBenefit = getSharedBenefit(member, productBenefit);
										
										if (sharedBenefit != null) {
											memberBenefit.setSharedBenefitId(sharedBenefit);
										}
									}
									memberBenefitService.create(memberBenefit, actionUser);
								}
							}
						}
					}

					Collection<ProductLayerLimit> productLayerList = productLayerLimitService.getProductLayerList(product.getProductId());
									
					for (Iterator iterator = productLayerList.iterator(); iterator.hasNext();) {
						ProductLayerLimit productLayerLimit = (ProductLayerLimit) iterator.next();
						
						if (productLayerLimit != null){
							
							Collection<ProductBenefit> productBenefitLayerList = productBenefitService.getBenefitLayer(productLayerLimit.getProductLayerLimitId());
							
							MemberLimitLayer memberLimitLayer = memberLimitLayerService.getMemberLimitLayer(memberProduct.getMemberProductId(), productLayerLimit.getLayerLevel(),SubscriptionStatus.ACTIVE);
							
							if (productBenefitLayerList != null && memberLimitLayer != null){
								for (Iterator iteratorLayerBenefit = productBenefitLayerList.iterator(); iteratorLayerBenefit.hasNext();) {
									ProductBenefit layerBenefit = (ProductBenefit) iteratorLayerBenefit.next();
									
									if (layerBenefit != null){
						
										//TODO: Synchronize

										Collection<MemberBenefit> previousLayerBenefitList = null;

										if (layerBenefit.getItemCategoryId().getIsSurgeryItem() != null
												&& layerBenefit.getItemCategoryId().getIsSurgeryItem().intValue() == 1) {
										
											previousLayerBenefitList = getPreviousActiveLayerSurgeryBenefitList(member, product,
													memberProduct, layerBenefit,memberLimitLayer.getMemberLimitLayerId());
										} else {
											previousLayerBenefitList = getPreviousActiveLayerBenefitList(member, product, memberProduct,
													layerBenefit,memberLimitLayer.getMemberLimitLayerId());
										}
										
										if (previousLayerBenefitList != null){
											if (previousLayerBenefitList != null && !previousLayerBenefitList.isEmpty()) {
												Iterator<MemberBenefit> iteratorBenefit = previousLayerBenefitList.iterator();
												if (iteratorBenefit != null && iteratorBenefit.hasNext()) {
													MemberBenefit memberBenefit = iteratorBenefit.next();

													if (memberBenefit != null) {

														memberBenefit.setBenefitType(MemberBenefit.MEMBER_PRODUCT_BENEFIT);
														memberBenefit.setMemberProductId(memberProduct);
														memberBenefit.setItemCategoryId(layerBenefit.getItemCategoryId());
														memberBenefit.setMemberId(member);
														
														if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == BenefitUsageType.ANNUAL){
															double previousMBenefitUsage = memberBenefit.getBenefitUsage() == null ? 0.0 : memberBenefit.getBenefitUsage();
															double benefitPercentage = previousMBenefitUsage / memberBenefit.getBenefitLimit();
																	 
															memberBenefit.setBenefitUsagePercentage(benefitPercentage);
														}
											
														memberBenefit.setMemberBenefitStatus(SubscriptionStatus.ACTIVE);

														synchronizeBenefit(actionUser, isPrincipal, isDependent, layerBenefit,
																memberBenefit);

														if (layerBenefit.getSharedBenefitId() != null) {
															MemberBenefit sharedBenefit = getSharedBenefit(member, layerBenefit);
															
															if (sharedBenefit != null) {
																memberBenefit.setSharedBenefitId(sharedBenefit);
															}
														}
														memberBenefitService.update(memberBenefit, actionUser);
													}
												}
											} else {
												/**
												 * TODO: Previous Benefit Not Found populate
												 * new product benefit
												 */
												MemberBenefit memberBenefit = new MemberBenefit();
												memberBenefit.setBenefitType(MemberBenefit.MEMBER_PRODUCT_BENEFIT);
												memberBenefit.setMemberProductId(memberProduct);
												memberBenefit.setItemCategoryId(layerBenefit.getItemCategoryId());
												memberBenefit.setMemberId(member);
												memberBenefit.setMemberLimitLayerId(memberLimitLayer);
												
												initializeMemberBenefitUsage(memberBenefit);
												
												memberBenefit.setMemberBenefitStatus(SubscriptionStatus.ACTIVE);

												synchronizeBenefit(actionUser, isPrincipal, isDependent, layerBenefit,memberBenefit);

												if (layerBenefit.getSharedBenefitId() != null) {
													MemberBenefit sharedBenefit = null;

													sharedBenefit = getSharedBenefit(member, layerBenefit);
													
													if (sharedBenefit != null) {
														memberBenefit.setSharedBenefitId(sharedBenefit);
													}
												}
												memberBenefitService.create(memberBenefit, actionUser);
											}			
										}
									}
								}
							}
						}						
					}
					
					if (clausuls != null && clausuls.size() > 0) {
						Iterator<ProductClausul> iterator = clausuls.iterator();

						deactivateMemberClausul(actionUser, memberProduct, member);

						if (iterator != null) {
							populateMemberClausul(actionUser, member, iterator, memberProduct);
						}
					}

					update(member, actionUser);
					actionResult.setActionCode("ACTIVATEMEMBER");
					actionResult.setResult(true);
					actionResult.setAdditionalMessage("Sukses Melakukan Aktifasi Member");
				} else {
					actionResult.setActionCode("ACTIVATEMEMBER");
					actionResult.setResult(false);
					actionResult.setAdditionalMessage("Product Asuransi Tidak Terdefinisikan");
				}
				System.out.println("===========================END While DEBUG===================================");
			}
			// multiple insurance product
		}
		return actionResult;
	}

	private Collection<MemberBenefit> getPreviousActiveBenefitList(Member member, Product product,
			MemberProduct memberProduct, ProductBenefit productBenefit) throws Exception {
		
		Collection<MemberBenefit> result = new Vector<MemberBenefit>();
		
		
		String q = "SELECT p FROM MemberBenefit p WHERE p.memberId.memberId = :memberId AND p.deletedStatus = 0"
				+ " AND itemCategoryId.itemCategoryId = :itemCategoryId"
				+ " AND p.memberProductId.memberProductId = :memberProductId "
				+ " AND p.memberBenefitStatus = :status AND p.memberLimitLayerId IS NULL ORDER BY memberBenefitId DESC ";
		
		Session session = memberDao.getMemberSession();
		
		Query query = session.createQuery(q);
		query.setInteger("memberId", member.getMemberId());
		query.setInteger("itemCategoryId", productBenefit.getItemCategoryId().getItemCategoryId());
		query.setInteger("memberProductId", memberProduct.getMemberProductId());
		query.setInteger("status", SubscriptionStatus.ACTIVE);
		query.setMaxResults(1);
		
		List<ProductBenefit> list = query.list();
		
		if (list != null && !list.isEmpty()){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
				
				
				result.add(memberBenefit);
			}
		}
		
		return result;
	}

	private Collection<MemberBenefit> getPreviousActiveSurgeryBenefitList(Member member, Product product,
			MemberProduct memberProduct, ProductBenefit productBenefit) throws Exception {

		Collection<MemberBenefit> result = new Vector<MemberBenefit>();
		
		if (memberProduct != null){
			if (productBenefit.getTreatmentLevel() != null){
				String q = "SELECT p FROM MemberBenefit p WHERE p.memberId.memberId = :memberId AND p.deletedStatus = 0"
						+ " AND itemCategoryId.itemCategoryId = :itemCategoryId"
						+ " AND p.memberProductId.memberProductId = :memberProductId "
						+ " AND p.memberBenefitStatus = :status"
						+ " AND p.treatmentLevel = :level AND p.memberLimitLayerId IS NULL ORDER BY memberBenefitId DESC ";
				
				Session session = memberDao.getMemberSession();
				
				Query query = session.createQuery(q);
				query.setInteger("memberId", member.getMemberId());
				query.setInteger("itemCategoryId", productBenefit.getItemCategoryId().getItemCategoryId());
				query.setInteger("memberProductId", memberProduct.getMemberProductId());
				query.setInteger("status", SubscriptionStatus.ACTIVE);
				query.setInteger("level", productBenefit.getTreatmentLevel());
				query.setMaxResults(1);
				
				List<ProductBenefit> list = query.list();
				
				if (list != null && !list.isEmpty()){
					for (Iterator iterator = list.iterator(); iterator.hasNext();) {
						MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
						
						
						result.add(memberBenefit);
					}
				}
			}
			else {
				String q = "SELECT p FROM MemberBenefit p WHERE p.memberId.memberId = :memberId AND p.deletedStatus = 0"
						+ " AND itemCategoryId.itemCategoryId = :itemCategoryId"
						+ " AND p.memberProductId.memberProductId = :memberProductId "
						+ " AND p.memberBenefitStatus = :status"
						+ " AND p.memberLimitLayerId IS NULL ORDER BY memberBenefitId DESC ";
				
				Session session = memberDao.getMemberSession();
				
				Query query = session.createQuery(q);
				query.setInteger("memberId", member.getMemberId());
				query.setInteger("itemCategoryId", productBenefit.getItemCategoryId().getItemCategoryId());
				query.setInteger("memberProductId", memberProduct.getMemberProductId());
				query.setInteger("status", SubscriptionStatus.ACTIVE);				
				query.setMaxResults(1);
				
				List<ProductBenefit> list = query.list();
				
				if (list != null && !list.isEmpty()){
					for (Iterator iterator = list.iterator(); iterator.hasNext();) {
						MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
						
						
						result.add(memberBenefit);
					}
				}
			}
		}
		
		return result;
	}

	private Collection<MemberBenefit> getPreviousActiveLayerBenefitList(Member member, Product product,
			MemberProduct memberProduct, ProductBenefit productBenefit,Integer layer) throws Exception {
		Collection<MemberBenefit> previousBenefitList;
		String[] eqParamBenefit = { "memberId.memberId", 
				"itemCategoryId.itemCategoryId", "memberProductId.memberProductId",
				"memberBenefitStatus", "deletedStatus" ,"memberLimitLayerId.memberLimitLayerId"};

		Object[] eqValueBenefit = { member.getMemberId(),
				
				productBenefit.getItemCategoryId().getItemCategoryId(),
				memberProduct.getMemberProductId(), SubscriptionStatus.ACTIVE, 0 ,layer};

		
		previousBenefitList = memberBenefitService.search(null, null, eqParamBenefit,
				eqValueBenefit, false, "memberBenefitId", 0, 1);
		return previousBenefitList;
	}

	private Collection<MemberBenefit> getPreviousActiveLayerSurgeryBenefitList(Member member, Product product,
			MemberProduct memberProduct, ProductBenefit productBenefit,Integer layer) throws Exception {
		Collection<MemberBenefit> previousBenefitList;
		String[] eqParamBenefit = { "memberId.memberId", 
				"itemCategoryId.itemCategoryId", "memberProductId.memberProductId",
				"treatmentLevel", "memberBenefitStatus", "deletedStatus","memberLimitLayerId.memberLimitLayerId" };

		Object[] eqValueBenefit = { member.getMemberId(),
				productBenefit.getItemCategoryId().getItemCategoryId(),
				memberProduct.getMemberProductId(), productBenefit.getTreatmentLevel(),
				SubscriptionStatus.ACTIVE, 0 ,layer};

		previousBenefitList = memberBenefitService.search(null, null, eqParamBenefit,
				eqValueBenefit, false, "memberBenefitId", 0, 1);
		return previousBenefitList;
	}
	private MemberBenefit getSharedBenefit(Member member, ProductBenefit productBenefit)
			throws Exception {
		MemberBenefit sharedBenefit = null;
		
		
		String[] eqParam = { "deletedStatus", "memberId.memberId",
				"itemCategoryId.itemCategoryId", "productCaseCategoryId.caseCategoryId",
				"productId.productId" };

		Object[] eqValue = { 0, member.getMemberId(),
				productBenefit.getSharedBenefitId().getItemCategoryId()
						.getItemCategoryId(),
				productBenefit.getCaseCategoryId().getCaseCategoryId(),
				productBenefit.getProductId().getProductId() };

		Collection<MemberBenefit> benefit = memberBenefitService.search(null, null,
				eqParam, eqValue,false,"memberBenefitId", 0, 1);

		if (benefit != null) {
			Iterator<MemberBenefit> benefitIterator = benefit.iterator();

			if (benefitIterator.hasNext()) {
				sharedBenefit = benefitIterator.next();																							
			}
		}
		return sharedBenefit;
	}

	private void initializeMemberBenefitUsage(MemberBenefit memberBenefit) {
		memberBenefit.setCashlessBenefitUsage(0.0);
		memberBenefit.setReimbursementBenefitUsage(0.0);
		memberBenefit.setCashlessUsagePercentage(0.0);
		memberBenefit.setReimbursementUsagePercentage(0.0);
		memberBenefit.setBenefitUsagePercentage(0.0);
		memberBenefit.setBenefitOccuranceUsage(0.0);
		memberBenefit.setBenefitUsage(0.0);
		memberBenefit.setBenefitExcessed(0.0);
		memberBenefit.setBenefitExGratia(0.0);

	}

	private void synchronizeBenefit(ActionUser actionUser, boolean isPrincipal, boolean isDependent,
			ProductBenefit productBenefit, MemberBenefit memberBenefit) {
		
		memberBenefit.setProductId(productBenefit.getProductId());

		memberBenefit.setBenefitCalculationMethod(productBenefit.getBenefitUsageType());
		memberBenefit.setTreatmentLevel(productBenefit.getTreatmentLevel());

		memberBenefit.setTreatmentLocation(productBenefit.getTreatmentLocation());
		memberBenefit.setCaseCategoryId(productBenefit.getCaseCategoryId());
		memberBenefit.setItemCategoryId(productBenefit.getItemCategoryId());
		memberBenefit.setProductCaseCategoryId(productBenefit.getProductId().getCaseCategory());
		memberBenefit.setDivertBenefit(productBenefit.getDivertBenefit());
		memberBenefit.setDeductibleLimit(productBenefit.getDeductibleLimit());
		memberBenefit.setIsAsCharge(productBenefit.getIsAsCharge());
		
		if (productBenefit.getBenefitUsageType().getBenefitUsageTypeId().intValue() == BenefitUsageType.ANNUAL){
			System.out.println("ANNUAL MemberBenefit Id = " + memberBenefit.getMemberBenefitId());
		}

		memberBenefit.setReimbursementAsCharge(productBenefit.getReimburseAsCharge());
		memberBenefit.setReimbursementBenefitLimit(productBenefit.getReimbursementBenefitLimit());

		memberBenefit.setIsSMMBenefit(productBenefit.getIsSMMBenefit());
		memberBenefit.setSmmLimit(productBenefit.getSmmLimit());
		memberBenefit.setEffectiveSMMDay(productBenefit.getEffectiveSMMDay());

		if (productBenefit.getIsEdcEnabled() != null) {
			if (productBenefit.getIsEdcEnabled().intValue() == 1) {
				memberBenefit.setIsEDCEnabled(true);
				memberBenefit.setBenefitLimitPublishedOnEdc(productBenefit.getBenefitShowedOnEdc());
			} else if (productBenefit.getIsEdcEnabled().intValue() == 0) {
				memberBenefit.setIsEDCEnabled(false);
				memberBenefit.setBenefitLimitPublishedOnEdc(0);
			}
		}

		memberBenefit.setIsDeductPlanLimit(productBenefit.getIsDeductPlanLimit());
		memberBenefit.setMaxOccurancePerCase(productBenefit.getMaxOccurancePerCase());
		memberBenefit.setMaxBenefitPerCase(productBenefit.getMaxBenefitPerCase());

		if (productBenefit.getMaxOccurance() != null) {
			memberBenefit.setMaxBenefitOccurance(productBenefit.getMaxOccurance().doubleValue());
		}
		memberBenefit.setBenefitUpgradable(productBenefit.isBenefitUpgradable());
		memberBenefit.setBenefitUpgradeLimit(productBenefit.getBenefitUpgradeLimit());
		memberBenefit.setBenefitLimit(productBenefit.getBenefitLimit());

		memberBenefit.setIsCostSharing(productBenefit.isCostSharing());
		memberBenefit.setCostSharingMethod(productBenefit.getCostSharingMethod());

		if (isPrincipal) {
			memberBenefit.setCostSharingPercentage(productBenefit.getCostSharingPercentage());
			memberBenefit.setCostSharingAmount(productBenefit.getCostSharingAmount());
		}
		if (isDependent) {
			memberBenefit.setCostSharingPercentage(productBenefit.getDependentCoSharePercentage());
			memberBenefit.setCostSharingAmount(productBenefit.getDependentCoShareAmount());
		}

		String info = memberBenefit.getInformation() == null ? " " : memberBenefit.getInformation();
		String prodInfo = productBenefit.getInformation() == null ? " " : productBenefit.getInformation();
		memberBenefit.setInformation(info + " [SYNC] " + prodInfo);
		memberBenefit.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		memberBenefit.setDeletedStatus(0);
		
		
		if (productBenefit.getReimbursementPercentage() != null){
			if (productBenefit.getProductId().getReimbursementPercentage() != null){
				if (productBenefit.getReimbursementPercentage().equals(productBenefit.getProductId().getReimbursementPercentage())){
					memberBenefit.setReimbursementPercentage(productBenefit.getReimbursementPercentage());
				}
				else {
					memberBenefit.setReimbursementPercentage(productBenefit.getProductId().getReimbursementPercentage());
				}
			}
			else {
				memberBenefit.setReimbursementPercentage(productBenefit.getReimbursementPercentage());
			}
		}
		else {
			memberBenefit.setReimbursementPercentage(productBenefit.getProductId().getReimbursementPercentage());
		}
		
		if (productBenefit.getCashlessPercentage() != null){
			if (productBenefit.getProductId().getCashlessPercentage() != null){
				if (productBenefit.getCashlessPercentage().equals(productBenefit.getProductId().getCashlessPercentage())){
					memberBenefit.setCashlessPercentage(productBenefit.getCashlessPercentage());
				}
				else {
					memberBenefit.setCashlessPercentage(productBenefit.getProductId().getCashlessPercentage());
				}
			}
			else {
				memberBenefit.setCashlessPercentage(productBenefit.getCashlessPercentage());
			}
		}
		else {
			memberBenefit.setCashlessPercentage(productBenefit.getProductId().getCashlessPercentage());
		}

		if (productBenefit.getIsAsCharge() != null && productBenefit.getIsAsCharge().booleanValue()) {
			memberBenefit.setCurrentBenefitPosition(-1.0);
		} else {
			if (productBenefit.getBenefitUsageType() != null && productBenefit.getBenefitUsageType()
					.getBenefitUsageTypeId().intValue() == BenefitUsageType.ANNUAL) {
				if (memberBenefit.getBenefitUsage() != null) {
					memberBenefit.setCurrentBenefitPosition(
							productBenefit.getBenefitLimit() - memberBenefit.getBenefitUsage());
				} else {
					memberBenefit.setCurrentBenefitPosition(productBenefit.getBenefitLimit());
				}

				if (memberBenefit.getReimbursementBenefitUsage() != null) {
					double usagePercentage = memberBenefit.getReimbursementBenefitUsage()
							/ productBenefit.getBenefitLimit().doubleValue() * 100;
					memberBenefit.setReimbursementUsagePercentage(usagePercentage);
				}
				if (memberBenefit.getCashlessBenefitUsage() != null) {
					double usagePercentage = memberBenefit.getCashlessBenefitUsage()
							/ productBenefit.getBenefitLimit().doubleValue() * 100;
					memberBenefit.setCashlessUsagePercentage(usagePercentage);
				}
				if (memberBenefit.getBenefitUsage() != null) {
					double usagePercentage = memberBenefit.getBenefitUsage()
							/ productBenefit.getBenefitLimit().doubleValue() * 100;
					memberBenefit.setBenefitUsagePercentage(usagePercentage);
				}
			}
		}
		if (actionUser != null) {
			if (actionUser.getUser() != null) {
				memberBenefit.setCreatedBy(actionUser.getUser().getUsername());
			}
		}
	}

	/*
	 * Method create (Member object) berfungsi untuk melakukan penambahan sebuah
	 * object kedalam database
	 * 
	 * @param object adalah sebuah object yang ingin diubah
	 * 
	 * @return object hasil kreasi,lengkap dengan assigned primary key,
	 * exception jika gagal
	 */
	public ActionResult activate(Integer memberId, ActionUser actionUser) throws Exception {

		ActionResult actionResult = new ActionResult();

		String[] required = { "Member.MemberGroupId", "Member.ClientId", "Member.RelationshipId",
				"Member.ParentMember" };
		Member member = get(memberId, required);

		/**
		 * parent number diberikan oleh input sehingga tidak perlu melakukan
		 * lookup berdasarkan LIKE
		 */
		if (member != null && member.getStatus() != SubscriptionStatus.ACTIVE) {

			Client client = member.getClientId();
			MemberGroup group = member.getMemberGroupId();

			String tipe = "G";
			String paymentMethod = member.getPaymentPeriodeMethod() == null ? "A" : member.getPaymentPeriodeMethod();

			if (client != null) {
				member.setClientName(client.getClientName());
			}
			if (group != null) {
				member.setGroupName(group.getGroupName());
				tipe = group.getTipe() == null ? "G" : group.getTipe();
			}

			Policy policy = null;
			if (member.getCurrentPolicyId() == null) {
				if (member.getCurrentPolicyNumber() != null) {
					policy = policyService.getActivePolicyByNumber(member.getCurrentPolicyNumber());
					if (policy != null) {
						member.setCurrentPolicyId(policy);
						member.setClientId(policy.getClientId());
						member.setMemberGroupId(policy.getMemberGroupId());
						member.setClientName(policy.getClientId().getClientName());
						member.setGroupName(policy.getMemberGroupId().getGroupName());
					}
				}
			}
			Date nextProductExpireDate = member.getExpireDate();

			if (tipe.equalsIgnoreCase("I")) {
				if (paymentMethod.equalsIgnoreCase("A")) {
					DateTime dt = new DateTime(member.getEffectiveDate());
					DateTime nextPolicyDate = dt.plusYears(1);
					member.setLatestPolicyPaymentDate(new java.sql.Date(nextPolicyDate.getMillis()));
					nextProductExpireDate = new java.sql.Date(nextPolicyDate.getMillis());
				} else if (paymentMethod.equalsIgnoreCase("M")) {
					DateTime dt = new DateTime(member.getEffectiveDate());
					DateTime nextPolicyDate = dt.plusMonths(1);
					member.setLatestPolicyPaymentDate(new java.sql.Date(nextPolicyDate.getMillis()));
					nextProductExpireDate = new java.sql.Date(nextPolicyDate.getMillis());
				} else if (paymentMethod.equalsIgnoreCase("SM")) {
					DateTime dt = new DateTime(member.getEffectiveDate());
					DateTime nextPolicyDate = dt.plusMonths(6);
					member.setLatestPolicyPaymentDate(new java.sql.Date(nextPolicyDate.getMillis()));
					nextProductExpireDate = new java.sql.Date(nextPolicyDate.getMillis());
				} else if (paymentMethod.equalsIgnoreCase("Q")) {
					DateTime dt = new DateTime(member.getEffectiveDate());
					DateTime nextPolicyDate = dt.plusMonths(4);
					member.setLatestPolicyPaymentDate(new java.sql.Date(nextPolicyDate.getMillis()));
					nextProductExpireDate = new java.sql.Date(nextPolicyDate.getMillis());
				}
			}

			SubscriptionStatus status = new SubscriptionStatus();
			status.setStatusId(SubscriptionStatus.ACTIVE);
			
			member.setStatus(SubscriptionStatus.ACTIVE);
			member.setResignedDate(member.getExpireDate());

			String productCode = member.getCurrentProductCode();

			String customerPolicyNumber = member.getCustomerPolicyNumber();
			String parentNumber = member.getParentNumber();

			Member parent = null;
			boolean isParentActive = true;
			boolean isActivateSuccess = false;

			if (!customerPolicyNumber.equalsIgnoreCase(parentNumber)) {
				if (member.getMemberGroupId() != null && member.getClientId() != null) {
					parent = getParentMember(parentNumber, member.getMemberGroupId().getMemberGroupId(),
							member.getClientId().getClientId());
				}
			} else {
				parent = member;
			}

			if (parent != null) {
				member.setParentMember(parent);
				member.setParentName(parent.getFirstName());
				member.setParentNumber(parent.getCustomerPolicyNumber());

				if (parent.getStatus().intValue() != SubscriptionStatus.ACTIVE
						&& member.getMemberId().intValue() != parent.getMemberId().intValue()) {
					isParentActive = false;
				}
			}

			StringTokenizer token = new StringTokenizer(productCode);

			if (token.countTokens() == 1) {

				Product product = null;

				if (member.getCurrentPolicyId() != null) {
					product = productService.getActiveProduct(productCode, member.getCurrentPolicyId().getPolicyId());
				} else {
					if (tipe.equalsIgnoreCase("I")) {
						product = productService.getActiveProduct(productCode, member.getCurrentPolicyId().getPolicyId());
					}
				}

				/**
				 * ada kasus dimana family plan, dan employee/principal
				 * melakukan change plan karena ada anak/dependent baru untuk
				 * hal tersebut, dibutuhkan pemeriksaan apakah status principal
				 * sudah ACTIVE atau masih pending CHANGE PLAN
				 */
				if (product != null && isParentActive) {
					MemberProduct populatedProduct = populateMemberProduct(actionUser, member,member.getEffectiveDate(),nextProductExpireDate, status, product);

					Collection<ProductBenefit> benefits = getProductBenefitList(product);
					Collection<ProductClausul> clausuls = product.getProductClausuls();

					if (populatedProduct != null && benefits != null && benefits.size() > 0) {
						Iterator<ProductBenefit> iterator = benefits.iterator();
						populateMemberBenefit(actionUser, member, iterator, populatedProduct, null,status);
					}
					if (populatedProduct != null && clausuls != null && clausuls.size() > 0) {
						Iterator<ProductClausul> iterator = clausuls.iterator();

						if (iterator != null) {
							populateMemberClausul(actionUser, member, iterator, populatedProduct);
						}
					}
					
					if (populatedProduct != null){

						update(member, actionUser);
	
						actionResult.setActionCode("ACTIVATEMEMBER");
						actionResult.setResult(true);
						actionResult.setAdditionalMessage("Sukses Melakukan Aktifasi Member");
						isActivateSuccess = true;
					}
					else {
						System.out.println("GAGAL MELAKUKAN AKTIFASI PESERTA DENGAN MEMBERID = " + memberId);
					}

				} else {
					actionResult.setActionCode("ACTIVATEMEMBER");
					actionResult.setResult(false);
					actionResult.setAdditionalMessage("Product Asuransi Tidak Terdefinisikan");

				}
			} else {
				Product product = null;

				while (token.hasMoreTokens()) {

					productCode = token.nextToken();

					if (member.getCurrentPolicyId() != null) {

						product = productService.getActiveProduct(productCode, member.getCurrentPolicyId().getPolicyId());
					} else {
						if (tipe.equalsIgnoreCase("I")) {
							product = productService.getActiveProduct(productCode, member.getCurrentPolicyId().getPolicyId());
						}
					}

					if (product != null && isParentActive) {

						MemberProduct populatedProduct = populateMemberProduct(actionUser, member,member.getEffectiveDate(),nextProductExpireDate, status, product);

						Collection<ProductBenefit> benefits = getMainProductBenefitList(product);

						Collection<ProductClausul> clausuls = product.getProductClausuls();

						if (populatedProduct != null && benefits != null && benefits.size() > 0) {

							Iterator<ProductBenefit> iterator = benefits.iterator();

							populateMemberBenefit(actionUser, member, iterator, populatedProduct, null,status);
						}
						if (populatedProduct!= null && clausuls != null && clausuls.size() > 0) {
							Iterator<ProductClausul> iterator = clausuls.iterator();

							if (iterator != null) {
								populateMemberClausul(actionUser, member, iterator, populatedProduct);
							}
						}

						if (populatedProduct != null){
							update(member, actionUser);
	
							actionResult.setActionCode("ACTIVATEMEMBER");
							actionResult.setResult(true);
							actionResult.setAdditionalMessage("Sukses Melakukan Aktifasi Member");
	
							isActivateSuccess = true;
						}
						else {
							isActivateSuccess = false;
							System.out.println("GAGAL MELAKUKAN AKTIFASI MEMBER ID = " + memberId);
						}

					} else {
						actionResult.setActionCode("ACTIVATEMEMBER");
						actionResult.setResult(false);
						actionResult.setAdditionalMessage("Product Asuransi Tidak Terdefinisikan");

					}
				}
				// multiple insurance product
			}
			if (policy != null && isActivateSuccess) {

				Collection<PolicyBenefit> policyBenefitList = getPolicyBenefitList(policy);

				if (policyBenefitList != null && policyBenefitList.size() > 0) {
					Iterator<PolicyBenefit> iteratorPolicyBenefit = policyBenefitList.iterator();
					populatePolicyBenefit(actionUser, member, iteratorPolicyBenefit);
				}
			}

			if (member.getCurrentCardNumber() != null && !member.getCurrentCardNumber().equalsIgnoreCase("")
					&& isActivateSuccess) {

				String[] eqCardParam = { "deletedStatus", "memberId.memberId", "cardNumber" };
				Object[] eqCardValue = { Integer.valueOf(0), member.getMemberId(), member.getCurrentCardNumber() };

				int totalCard = memberCardService.getTotal(null, null, eqCardParam, eqCardValue);

				if (totalCard == 0) {

					MemberElectronicCard card = new MemberElectronicCard();

					card.setMemberId(member);
					card.setMemberNumber(member.getCustomerPolicyNumber());
					card.setCardStatus(1);
					card.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
					if (actionUser != null && actionUser.getUser() != null) {
						card.setCreatedBy(actionUser.getUser().getUsername());
					}
					card.setDeletedStatus(0);
					card.setCardNumber(member.getCurrentCardNumber());
					card.setRegistrationDate(new java.sql.Date(System.currentTimeMillis()));

					card.setExpireDate(member.getExpireDate());
					card.setEffectiveDate(member.getEffectiveDate());

					if (member.getCurrentPolicyId() != null) {

						card.setPolicyId(member.getCurrentPolicyId());
						card.setCardType(member.getCurrentPolicyId().getCardTypeId());

						memberCardService.create(card, actionUser);
					}
				}
			}
		}
		return actionResult;
	}

	private Collection<PolicyBenefit> getPolicyBenefitList(Policy policy) throws Exception {
		String[] eqPolicyBenefitParam = { "policyId.policyId", "deletedStatus" };
		Object[] eqPolicyBenefitValue = { policy.getPolicyId(), Integer.valueOf(0) };

		String[] requiredBenefit = { "PolicyBenefit.ItemCategoryId" };
		int totalPolicyBenefit = policyBenefitService.getTotal(null, null, eqPolicyBenefitParam,
				eqPolicyBenefitValue);

		Collection<PolicyBenefit> policyBenefitList = policyBenefitService.search(null, null,
				eqPolicyBenefitParam, eqPolicyBenefitValue, requiredBenefit, 0, totalPolicyBenefit);
		return policyBenefitList;
	}

	/*
	 * Method activateRenewal berfungsi untuk mengaktifkan proses permohonan
	 * renewal suatu member Seluruh Product Benefit dan Product Clausul yang
	 * baru akan disalin ke tabel member_benefit dan member_clausul
	 * 
	 * @param object adalah sebuah object yang ingin diubah
	 * 
	 * @return object hasil kreasi,lengkap dengan assigned primary key,
	 * exception jika gagal
	 */
	public ActionResult activateRenewal(Integer memberId, ActionUser actionUser) throws Exception {

		ActionResult actionResult = new ActionResult();

		String[] required = { "Member.CurrentPolicyId" };

		Member member = get(memberId, required);
		MemberGroup group = member.getMemberGroupId();

		String tipe = group.getTipe() == null ? "G" : group.getTipe();

		SubscriptionStatus status = new SubscriptionStatus();
		status.setStatusId(SubscriptionStatus.ACTIVE);

		if (member != null && member.getStatus() == SubscriptionStatus.PENDING_RENEWAL) {

			if (member.getCurrentCardNumber() != null && member.getNextCardNumber() != null) {
				if (!member.getCurrentCardNumber().trim().equalsIgnoreCase(member.getNextCardNumber().trim())) {
					member.setCurrentCardNumber(member.getNextCardNumber());
				}
			}
			member.setExpireDate(member.getNextExpireDate());
			member.setResignedDate(member.getNextExpireDate());
			member.setStatus(SubscriptionStatus.ACTIVE);
			member.setRenewalDate(new java.sql.Date(System.currentTimeMillis()));

			String productCode = member.getCurrentProductCode();
			StringTokenizer token = new StringTokenizer(productCode);

			Product product = null;

			double proratedRatio = 1.0;

			boolean isPrincipal = false;
			boolean isDependent = false;
			if (member != null && member.getCustomerPolicyNumber() != null
					&& member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())) {
				isPrincipal = true;
			}

			if (member != null && member.getCustomerPolicyNumber() != null
					&& !member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())) {
				isDependent = true;
			}

			while (token.hasMoreTokens()) {

				productCode = token.nextToken();

				if (member.getCurrentPolicyId() != null) {
					product = productService.getActiveProduct(productCode, member.getCurrentPolicyId().getPolicyId());					
				} else {
					if (tipe.equalsIgnoreCase("I")) {
						product = productService.getActiveProduct(productCode);
					}
				}


				if (product != null) {

					SubscriptionStatus mpStatus = new SubscriptionStatus();

					if (member.getNextEffectiveDate().before(new java.sql.Date(System.currentTimeMillis()))) {
						mpStatus.setStatusId(SubscriptionStatus.ACTIVE);
					} else {
						mpStatus.setStatusId(SubscriptionStatus.ADVANCED_RENEWAL);
					}
					
					
					MemberProduct memberProduct = populateMemberProduct(actionUser, member,member.getNextEffectiveDate(),member.getNextExpireDate(), mpStatus, product);
					
					Collection<ProductBenefit> benefits = getMainProductBenefitList(product);

					Collection<ProductClausul> clausuls = product.getProductClausuls();

					if (memberProduct != null && benefits != null && benefits.size() > 0) {
						Iterator<ProductBenefit> iterator = benefits.iterator();

						System.out.println("BEFORE POPULATE MEMBER BENEFIT AFTER POPULATE MEMBER PRODUCT ---> " + memberProduct + " MP ID = " + memberProduct.getMemberProductId());
						populateMemberBenefit(actionUser, member, iterator, memberProduct, null,mpStatus);
					}
					
					if (memberProduct != null && clausuls != null && clausuls.size() > 0) {
						Iterator<ProductClausul> iterator = clausuls.iterator();
						deactivateMemberClausul(actionUser, member);

						if (iterator != null) {
							populateMemberClausul(actionUser, member, iterator, memberProduct);
						}
					}

					if (memberProduct != null){
						update(member, actionUser);
						actionResult.setActionCode("ACTIVATEMEMBER");
						actionResult.setResult(true);
						actionResult.setAdditionalMessage("Sukses Melakukan Aktifasi Member");
	
						if (member.getParentMember() != null
								&& member.getParentMember().getMemberId().intValue() == member.getMemberId().intValue()) {
							// check apabila dependent nya tidak menggunakan
							// enrollment renew maka di set pending renew
	
							Collection<Member> dependentList = getDependentList(member.getMemberId());
	
							if (dependentList != null) {
								for (Iterator iterator = dependentList.iterator(); iterator.hasNext();) {
	
									Member memberDependent = (Member) iterator.next();
	
									if (memberDependent != null && memberDependent.getMemberId().intValue() != member
											.getMemberId().intValue()) {
	
										if (memberDependent.getStatus().intValue() == SubscriptionStatus.TERMINATED
												|| memberDependent.getStatus().intValue() == SubscriptionStatus.ACTIVE) {
	
											if (memberDependent.getExpireDate().equals(memberDependent.getResignedDate())) {
	
												memberDependent.setNextEffectiveDate(member.getNextEffectiveDate());
												memberDependent.setNextExpireDate(member.getNextExpireDate());
												memberDependent.setNextPolicyNumber(member.getNextPolicyNumber());
												memberDependent.setStatus(SubscriptionStatus.PENDING_RENEWAL);
	
												update(memberDependent, actionUser);
											}
										}
									}
								}
							}
						}
					}
					else {
						System.out.println("GAGAL MELAKUKAN ACTIVATE RENEWAL DIKARENAKAN MEMBER PRODUCT NULL UNTUK MEMBER ID = " + memberId);
					}
				} else {
					actionResult.setActionCode("ACTIVATEMEMBER");
					actionResult.setResult(false);
					actionResult.setAdditionalMessage("Product Asuransi Tidak Terdefinisikan");
				}
			}
			// multiple insurance product
		}
		return actionResult;
	}

	private Collection<ProductBenefit> getProductBenefitList(Product product) throws Exception {
		String[] eqParamBen = { "deletedStatus", "productId.productId" };
		Object[] eqValueBen = { 0, product.getProductId() };

		int totalBenefit = productBenefitService.getTotal(null, null, eqParamBen, eqValueBen);
		String[] requiredBenefit = { "ProductBenefit.ItemCategoryId" };

		Collection<ProductBenefit> benefits = productBenefitService.search(null, null, eqParamBen,
				eqValueBen, true, "sharedBenefitId", requiredBenefit, 0, totalBenefit);
		return benefits;
	}

	private Collection<ProductBenefit> getMainProductBenefitList(Product product) throws Exception {
		
		Collection<ProductBenefit> result = new Vector<ProductBenefit>();
		
		String[] requiredBenefit = { "ProductBenefit.ItemCategoryId" };
		
		String q = "SELECT p FROM ProductBenefit p WHERE p.productId.productId = :productId AND p.deletedStatus = 0"
				+ " AND p.productLayerId IS NULL ORDER BY sharedBenefitId ASC ";
		
		Session session = memberDao.getMemberSession();
		
		Query query = session.createQuery(q);
		query.setInteger("productId", product.getProductId());
		
		List<ProductBenefit> list = query.list();
		
		if (list != null && !list.isEmpty()){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ProductBenefit productBenefit = (ProductBenefit) iterator.next();
				
				DaoSupportUtil.lazyInit(requiredBenefit, productBenefit);
				
				result.add(productBenefit);
			}
		}
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ametis.cms.service.MemberService#getParentMember(java.lang.String,
	 * java.lang.Integer, java.lang.Integer)
	 * 
	 * @param parentNumber merupakan nomor kepesertaan employee yang aktif dalam
	 * suatu polis
	 * 
	 * @param memberGroupId merupakan referensi ID ke tabel memberGroup
	 * 
	 * @param clientId merupakan referensi ID ke tabel Client
	 */
	public Member getParentMember(String parentNumber, Integer memberGroupId, Integer clientId) throws Exception {
		Member member = null;

		if (parentNumber != null) {

			System.out.println("SEARCH KEY : " + parentNumber);

			String[] paramEq = { "customerPolicyNumber", "memberGroupId.memberGroupId", "clientId.clientId",
					"deletedStatus" };
			Object[] valueEq = { parentNumber, memberGroupId, clientId, Integer.valueOf(0) };

			Collection<Member> memberCollection = search(null, null, paramEq, valueEq, 0, 1);

			if (memberCollection != null && memberCollection.size() > 0) {
				Iterator<Member> memberIterator = memberCollection.iterator();
				if (memberIterator.hasNext()) {
					member = memberIterator.next();
				}
			}
		}

		return member;
	}

	private MemberProduct populateMemberProduct(ActionUser actionUser, Member member,Date effectiveDate,Date expireDate, 
			SubscriptionStatus status,	Product product) throws Exception {

		MemberProduct memberProduct = new MemberProduct();
		memberProduct.setCurrentMemberNumber(member.getCustomerPolicyNumber());
		memberProduct.setCurrentCardNumber(member.getCurrentCardNumber());
		memberProduct.setCurrentPolicyNumber(member.getCurrentPolicyNumber());

		memberProduct.setIsProductActive(1);
		memberProduct.setMemberId(member);
		memberProduct.setProductId(product);
		memberProduct.setRenewalDate(member.getRenewalDate());
		memberProduct.setRegisterDate(effectiveDate);
		memberProduct.setExpireDate(expireDate);
		memberProduct.setExcessPaymentType(product.getExcessPaymentType());
		memberProduct.setDisabilityLength(product.getDisabilityLength());
		
		memberProduct.setIsProrateLimit(product.getIsUsingProrateLimit());
		
		memberProduct.setMemberProductStatus(status);
		memberProduct.setProductCurrencyId(product.getProductCurrencyId());
		memberProduct.setCurrentPolicyId(member.getCurrentPolicyId());

		if (memberProduct.getCurrentPolicyId() != null
				&& memberProduct.getCurrentPolicyId().getReimburseMaxReceiveDay() != null) {
			DateTime expireTime = new DateTime(memberProduct.getExpireDate().getTime());

			DateTime gracePeriode = expireTime.plusDays(memberProduct.getCurrentPolicyId().getReimburseMaxReceiveDay());

			memberProduct.setGracePeriodeDate(new java.sql.Date(gracePeriode.getMillis()));
		}

		if (product.getEdcEnabled() != null) {

			if (product.getEdcEnabled().intValue() == 1) {
				memberProduct.setEDCEnabled(true);
				memberProduct.setLimitBenefitPublishedOnEdc(product.getLimitBenefitShowedOnEdc());
			} else if (product.getEdcEnabled().intValue() == 0) {
				memberProduct.setEDCEnabled(false);
				memberProduct.setLimitBenefitPublishedOnEdc(0);
			}
		}

		double proratedRatio = 1.0;
		double multiplier = 1.0;

		if (product.getSalaryMultiplier() != null) {
			multiplier = product.getSalaryMultiplier().doubleValue();
		}

		if (product != null && !isProductExist(member.getMemberId(), product)) {

			proratedRatio = getProrateRatio(member, product);

			if (product.getAnnualLimitValue().intValue() > 0) {
				if (product.getIsUsingSalary() != null && product.getIsUsingSalary().intValue() == 1) {
					Member parent = member.getParentMember();
					Double salary = parent.getCurrentSalary();

					if (salary != null) {
						memberProduct.setAnnualBenefit(salary * multiplier); 
						memberProduct.setActualBenefitLimit(salary * proratedRatio * multiplier);
					}
				} else {
					memberProduct.setActualBenefitLimit(product.getAnnualLimitValue() * proratedRatio);
					memberProduct.setAnnualBenefit(product.getAnnualLimitValue()); 
				}
			} else {
				if (product.getIsUsingSalary() != null && product.getIsUsingSalary().intValue() == 1) {

					Member parent = member.getParentMember();

					if (parent != null) {
						Double salary = parent.getCurrentSalary();
						if (salary != null) {
							memberProduct.setAnnualBenefit(salary * multiplier);
							memberProduct.setActualBenefitLimit(salary * proratedRatio * multiplier);
						}
					} else {
						if (member.getParentNumber().equalsIgnoreCase(member.getCustomerPolicyNumber())) {
							Double salary = member.getCurrentSalary();
							if (salary != null) {
								memberProduct.setAnnualBenefit(salary * multiplier); 
								memberProduct.setActualBenefitLimit(salary * proratedRatio * multiplier);
							}
						}
					}
				} else {

					memberProduct.setActualBenefitLimit(Double.valueOf(-1));
					memberProduct.setAnnualBenefit(Double.valueOf(-1));
				}
			}
			memberProduct.setBenefitUsage(Double.valueOf(0));
			memberProduct.setAnnualPremium(Double.valueOf(-1));

			if (product.getProductClassRate() != null) {
				memberProduct.setProductClassRate(product.getProductClassRate());
			}

			if (product.getSharedProductId() != null) {

				MemberProduct sharedProduct = getActiveSharedProduct(member, product);
				
				if (sharedProduct != null) {
					memberProduct.setSecondaryCoverageId(sharedProduct);
				}
			}

			if (product.getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY
					|| product.getProductType().getProductTypeId().intValue() == ProductType.FAMILY) {
				// check if

				Member parent = member.getParentMember();

				if (parent != null && parent.getMemberId().intValue() != member.getMemberId().intValue()) {
					MemberProduct familyProduct = getActiveFamilyProduct(product, parent);

					if (familyProduct != null) {
						memberProduct.setFamilyProductId(familyProduct);
						memberProduct.setActualBenefitLimit(familyProduct.getActualBenefitLimit());
						memberProduct.setAnnualBenefit(familyProduct.getAnnualBenefit());
					}
				}
				else {
					memberProduct.setFamilyProductId(memberProduct);
					memberProduct.setActualBenefitLimit(memberProduct.getActualBenefitLimit());
					memberProduct.setAnnualBenefit(memberProduct.getAnnualBenefit());
				}
			}

			memberProduct = memberProductService.create(memberProduct, actionUser);

			if (memberProduct != null) {
				Collection<ProductLayerLimit> layerProductList = getProductLayerLimit(product);

				if (layerProductList != null) {
					for (ProductLayerLimit productLayerLimit : layerProductList) {

						if (productLayerLimit != null) {

							MemberLimitLayer memberLayer = new MemberLimitLayer();
							memberLayer.setMemberId(member);
							memberLayer.setMemberProductId(memberProduct);
							memberLayer.setProductId(product);
							memberLayer.setProductLimitLayerId(productLayerLimit);
							memberLayer.setActualBenefitLimit(productLayerLimit.getAnnualLimit());
							memberLayer.setAnnualLimit(productLayerLimit.getAnnualLimit());
							memberLayer.setCoSharePercentage(productLayerLimit.getCoSharePercentage());
							memberLayer.setCoShareAmount(productLayerLimit.getCoShareAmount());
							memberLayer.setStatus(1);
							memberLayer.setDeletedStatus(0);
							memberLayer.setLayer(productLayerLimit.getLayerLevel());

							if (product.getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY
									|| product.getProductType().getProductTypeId().intValue() == ProductType.FAMILY) {
								// check if

								Member parent = member.getParentMember();

								if (parent != null && parent.getMemberId().intValue() != member.getMemberId().intValue()) {
								
									if (memberProduct.getFamilyProductId() != null){
										MemberLimitLayer familyLayerLimit = memberLimitLayerService.getMemberLimitLayer(memberProduct.getFamilyProductId().getMemberProductId(), 
												productLayerLimit.getLayerLevel(), SubscriptionStatus.ACTIVE);
	
										if (familyLayerLimit != null){
											memberLayer.setFamilyLimitLayerId(familyLayerLimit);
										}
									}
								}
								else {
									memberLayer.setFamilyLimitLayerId(memberLayer);
								}
							}

							
							if (productLayerLimit.getDiagnosisId() != null) {
								memberLayer.setDiagnosisId(productLayerLimit.getDiagnosisId());
							}
							if (productLayerLimit.getDiagnosisSetId() != null) {
								memberLayer.setDiagnosisSetId(productLayerLimit.getDiagnosisSetId());
							}
							memberLayer.setDeductible(productLayerLimit.getDeductible());

							memberLimitLayerService.create(memberLayer, actionUser);

							if (memberLayer != null) {
								Collection<ProductBenefit> layerBenefitList = getProductLayerBenefitList(
										productLayerLimit);

								Iterator<ProductBenefit> iteratorLayer = layerBenefitList.iterator();

								populateMemberBenefit(actionUser, member, iteratorLayer, memberProduct, memberLayer,status);

							}
						}
					}
				}
			}
		}
		else {
			System.out.println("POPULATE MEMBER PRODUCT FAILED BECAUSE PRODUCT IS ALREADY EXIST! ");
			memberProduct = null;
		}
		return memberProduct;
	}

	private Collection<ProductBenefit> getProductLayerBenefitList(ProductLayerLimit productLayerLimit)
			throws Exception {
		String[] eqParamLayerBenefit = { "deletedStatus",
				"productLayerId.productLayerLimitId" };

		Object[] eqValueLayerBenefit = { 0, productLayerLimit.getProductLayerLimitId() };

		int totalLayerBenefit = productBenefitService.getTotal(null, null, eqParamLayerBenefit,
				eqValueLayerBenefit);
		Collection<ProductBenefit> layerBenefitList = productBenefitService.search(null, null,
				eqParamLayerBenefit, eqValueLayerBenefit, 0, totalLayerBenefit);
		return layerBenefitList;
	}

	public void populateUpdateMemberProduct(ActionUser actionUser, Integer memberId) throws Exception {

		String[] required = { "Member.MemberGroupId", "Member.ClientId", "Member.RelationshipId",
				"Member.ParentMember" };
		Member member = get(memberId, required);

		// Collection<MemberProduct> memberProduct =
		// memberProductService.getMemberActiveProduct(memberId);

		MemberProduct memberProduct = new MemberProduct();

		String productCode = member.getCurrentProductCode();

		StringTokenizer token = new StringTokenizer(productCode);

		Product product = null;

		Collection<MemberProduct> familyProductList = null;

		if (token.countTokens() == 1) {

			if (member.getCurrentPolicyId() != null) {
				product = productService.getActiveProduct(productCode, member.getCurrentPolicyId().getPolicyId());
			} else {
				product = productService.getActiveProduct(productCode);
			}
		} else {

			while (token.hasMoreTokens()) {

				productCode = token.nextToken();

				if (member.getCurrentPolicyId() != null) {

					product = productService.getActiveProduct(productCode, member.getCurrentPolicyId().getPolicyId());
				} else {
					product = productService.getActiveProduct(productCode);
				}
			}
		}

		if (product.getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY
				|| product.getProductType().getProductTypeId().intValue() == ProductType.FAMILY) {
			// check if

			Member parent = member.getParentMember();

			if (parent != null && parent.getMemberId().intValue() != member.getMemberId().intValue()) {
				if (familyProductList == null) {
					MemberProduct familyProduct = getActiveFamilyProduct(product, parent);
					System.out.println("Member update = " + member.getMemberId());

					if (familyProduct != null) {
						memberProduct.setFamilyProductId(familyProduct);
						memberProduct.setActualBenefitLimit(familyProduct.getActualBenefitLimit());
						memberProduct.setAnnualBenefit(familyProduct.getAnnualBenefit());
						memberProduct = memberProductService.update(memberProduct, actionUser);
					}
				}
			}
		}
	}

	private void populateMemberBenefit(ActionUser actionUser, Member member, Iterator<ProductBenefit> iterator,
			MemberProduct memberProduct, MemberLimitLayer layerId,SubscriptionStatus status) throws Exception {
		if (iterator != null) {

			MemberBenefit memberBenefit = null;
			ProductBenefit productBenefit = null;

			double prorateRatio = 1.0;

			boolean isPrincipal = false;
			boolean isDependent = false;
			if (member != null && member.getCustomerPolicyNumber() != null
					&& member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())) {
				isPrincipal = true;
			}

			if (member != null && member.getCustomerPolicyNumber() != null
					&& !member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())) {
				isDependent = true;
			}

			while (iterator.hasNext()) {

				productBenefit = iterator.next();

				if (productBenefit != null && productBenefit.getDeletedStatus().intValue() == 0
						&& !isProductBenefitExist(member.getMemberId(), productBenefit, memberProduct)) {

					prorateRatio = getProrateRatio(member, productBenefit.getProductId());

					memberBenefit = new MemberBenefit();

					memberBenefit.setBenefitType(MemberBenefit.MEMBER_PRODUCT_BENEFIT);
					memberBenefit.setMemberProductId(memberProduct);
					if (productBenefit.getItemCategoryId() != null) {
						memberBenefit.setItemCategoryId(productBenefit.getItemCategoryId());
					}
					if (productBenefit.getDiagnosisId() != null) {
						memberBenefit.setDiagnosisId(productBenefit.getDiagnosisId());
					}
					memberBenefit.setMemberId(member);
					

					initializeMemberBenefitUsage(memberBenefit);
					if (layerId != null){
						memberBenefit.setMemberLimitLayerId(layerId);
					}
					
					if (memberProduct.getProductId().getProductType().getProductTypeId().intValue() == ProductType.FAMILY ||
							memberProduct.getProductId().getProductType().getProductTypeId().intValue() == ProductType.GROUP_FAMILY){
					
						if (layerId != null && layerId.getFamilyLimitLayerId() != null){
							
							if (!member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())){
								String[] eqParam = {"memberProductId.memberProductId",
										"itemCategoryId.itemCategoryId","deletedStatus",
										"memberBenefitStatus","memberLimitLayerId.memberLimitLayerId"};
								
								Object[] eqValue = {memberProduct.getFamilyProductId().getMemberProductId(),
										productBenefit.getItemCategoryId().getItemCategoryId(),0,
										SubscriptionStatus.ACTIVE,layerId.getFamilyLimitLayerId().getMemberLimitLayerId()};
								
								Collection<MemberBenefit> layerMBList = memberBenefitService.search(null,null,eqParam,eqValue,0,1);
								
								if (layerMBList != null){
									Iterator<MemberBenefit> iteratorLayerMbenefit = layerMBList.iterator();
									
									if (iteratorLayerMbenefit.hasNext()){
										MemberBenefit layerMemberBenefit = iteratorLayerMbenefit.next();
										
										memberBenefit.setFamilyBenefitId(layerMemberBenefit);
										
									}
								}
							}
						}
						else {
							
							if (!member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())){
								if (memberProduct.getFamilyProductId() != null){
									String[] eqParam = {"memberProductId.memberProductId","itemCategoryId.itemCategoryId",
											"deletedStatus","memberBenefitStatus"};
									
									Object[] eqValue = {memberProduct.getFamilyProductId().getMemberProductId(),productBenefit.getItemCategoryId().getItemCategoryId(),
											0,SubscriptionStatus.ACTIVE};
									
									int total = memberBenefitService.getTotal(null,null,eqParam,eqValue);
								
									System.out.println("GETTING FAMILY MEMBER BENEFIT ITEM FOR :  " + productBenefit.getItemCategoryId().getItemCategoryId() + " MAIN PROD ID = " + memberProduct.getFamilyProductId().getMemberProductId() +  " TOTAL = " + total);
									
									
									Collection<MemberBenefit> mainMBList = memberBenefitService.search(null,null,eqParam,eqValue,0,total);
									
									if (mainMBList != null){
										Iterator<MemberBenefit> iteratorMainMbenefit = mainMBList.iterator();
										
										while (iteratorMainMbenefit.hasNext()){
											MemberBenefit mainMemberBenefit = iteratorMainMbenefit.next();
											
											if (mainMemberBenefit.getMemberLimitLayerId() == null){
												System.out.println("DONE FOR ASSIGNMENT ITEM ID:  " + productBenefit.getItemCategoryId().getItemCategoryId() + " MAIN BENEFIT ID = " + mainMemberBenefit.getMemberBenefitId());
												memberBenefit.setFamilyBenefitId(mainMemberBenefit);											
											}			
											else {
												System.out.println("MEMBER LAYER ID = " + mainMemberBenefit.getMemberLimitLayerId());
											}
										}
									}
								}
								else {
									String warning = "FAMILY PLAN DARI PRINCIPAL TIDAK DITEMUKAN, MOHON PERIKSA KESESUAIAN current_product_code DARI PRINCIPAL dan DEPENDENT MEMBER ID = " + member.getMemberId() + " AND PARENT NUMBER = " + member.getParentNumber();
									System.out.println(warning);

									String currentInfo = memberBenefit.getInformation() == null ? " " : memberBenefit.getInformation();
									memberBenefit.setInformation(currentInfo + warning);
									
								}
							}
						}
					}
						 
					memberBenefit.setEffectiveDate(memberProduct.getRegisterDate());
					memberBenefit.setExpireDate(memberProduct.getExpireDate());

					synchronizeBenefit(actionUser, isPrincipal, isDependent, productBenefit, memberBenefit);
					memberBenefit.setMemberBenefitStatus(SubscriptionStatus.ACTIVE);

					if (productBenefit.getIsAsCharge() != null && productBenefit.getIsAsCharge().booleanValue()) {
						memberBenefit.setCurrentBenefitPosition(-1.0);
					} else {
						if (productBenefit.getBenefitUsageType() != null && productBenefit.getBenefitUsageType()
								.getBenefitUsageTypeId().intValue() == BenefitUsageType.ANNUAL) {
							memberBenefit.setCurrentBenefitPosition(productBenefit.getBenefitLimit() * prorateRatio);
						}
					}

					if (productBenefit.getSharedBenefitId() != null) {
						MemberBenefit sharedBenefit = getSharedBenefit(member, productBenefit);
						
						if (sharedBenefit != null) {
							memberBenefit.setSharedBenefitId(sharedBenefit);
						}

					}

					if (layerId != null) {
						if (productBenefit.getProductLayerId().getProductLayerLimitId().intValue() == layerId
								.getProductLimitLayerId().getProductLayerLimitId().intValue()) {
							System.out.println("MEMBER LAYER BENEFIT IS NOT NULL --> "
									+ productBenefit.getProductLayerId().getProductLayerLimitId());
							memberBenefitService.create(memberBenefit, actionUser);
						}
					} else {
						if (productBenefit.getProductLayerId() == null) {
							System.out.println("MEMBER LAYER BENEFIT IS NULL");
							memberBenefitService.create(memberBenefit, actionUser);
						}
					}

					// memberBenefit.setBenefitId(productBenefit.get)
					// memberBenefits.add(memberBenefit);
				}
			}
		}
	}

	private double getProrateRatio(Member member, Product product) {
		double prorateRatio = 1.0;
		
		if (product.getIsUsingProrateLimit() != null) {
			if (product.getIsUsingProrateLimit().intValue() == 1) {
				DateTime start = new DateTime(member.getEffectiveDate().getTime());
				DateTime end = new DateTime(member.getExpireDate().getTime());

				int totalDays = Days.daysBetween(start, end).getDays() + 1;

				if (totalDays < 365) {
					prorateRatio = (double) totalDays / 365;
				}
			}
		}
		return prorateRatio;
	}

	private void populatePolicyBenefit(ActionUser actionUser, Member member, Iterator<PolicyBenefit> iterator)
			throws Exception {
		if (iterator != null) {

			MemberBenefit memberBenefit = null;
			PolicyBenefit policyBenefit = null;

			double prorateRatio = 1.0;

			boolean isPrincipal = false;
			boolean isDependent = false;
			if (member != null && member.getCustomerPolicyNumber() != null
					&& member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())) {
				isPrincipal = true;
			}

			if (member != null && member.getCustomerPolicyNumber() != null
					&& !member.getCustomerPolicyNumber().equalsIgnoreCase(member.getParentNumber())) {
				isDependent = true;
			}

			while (iterator.hasNext()) {

				policyBenefit = iterator.next();

				if (policyBenefit != null && policyBenefit.getDeletedStatus().intValue() == 0
						&& !isPolicyBenefitExist(member.getMemberId(), policyBenefit)) {

					memberBenefit = new MemberBenefit();
					memberBenefit.setBenefitType(MemberBenefit.MEMBER_POLICY_BENEFIT);

					memberBenefit.setMemberId(member);
					memberBenefit.setPolicyBenefitId(policyBenefit);

					memberBenefit.setBenefitCalculationMethod(policyBenefit.getBenefitUsageType());

					if (policyBenefit.getDiagnosisId() != null) {
						memberBenefit.setDiagnosisId(policyBenefit.getDiagnosisId());
					}
					if (policyBenefit.getDiagnosisSetId() != null) {
						memberBenefit.setDiagnosisSetId(policyBenefit.getDiagnosisSetId());
					}
					if (policyBenefit.getProcedureId() != null) {
						memberBenefit.setProcedureId(policyBenefit.getProcedureId());
					}
					if (policyBenefit.getItemCategoryId() != null) {
						memberBenefit.setItemCategoryId(policyBenefit.getItemCategoryId());
					}

					if (policyBenefit.getTreatmentLocation() != null) {
						TreatmentLocation location = new TreatmentLocation();
						location.setLocationId(policyBenefit.getTreatmentLocation());
						memberBenefit.setTreatmentLocation(location);
					}
					memberBenefit.setCaseCategoryId(policyBenefit.getCaseCategoryId());
					memberBenefit.setProductCaseCategoryId(policyBenefit.getCaseCategoryId());

					initializeMemberBenefitUsage(memberBenefit);
					
					memberBenefit.setDeductibleLimit(policyBenefit.getDeductibleLimit());

					if (policyBenefit.getMaxOccurancePerMember() != null) {
						memberBenefit.setMaxBenefitOccurance(policyBenefit.getMaxOccurancePerMember());
					}

					if (policyBenefit.getBenefitUsageType() != null) {
						if (policyBenefit.getBenefitUsageType().getBenefitUsageTypeId()
								.intValue() == BenefitUsageType.ANNUAL) {
							memberBenefit.setBenefitLimit(policyBenefit.getBenefitLimit() * prorateRatio);
							memberBenefit.setReimbursementBenefitLimit(
									policyBenefit.getReimbursementLimit() * prorateRatio);
						} else {
							memberBenefit.setBenefitLimit(policyBenefit.getBenefitLimit());
							memberBenefit.setReimbursementBenefitLimit(policyBenefit.getReimbursementLimit());
						}
					}

					memberBenefit.setCostSharingPercentage(policyBenefit.getCostSharingPercentage());
					memberBenefit.setCostSharingAmount(policyBenefit.getCostSharingAmount());

					memberBenefit.setInformation(policyBenefit.getDescription());
					
					memberBenefit.setEffectiveDate(member.getEffectiveDate());
					memberBenefit.setExpireDate(member.getExpireDate());
					memberBenefit.setCashlessPercentage(policyBenefit.getCashlessPercentage());
					memberBenefit.setReimbursementPercentage(policyBenefit.getReimbursementPercentage());


					memberBenefitService.create(memberBenefit, actionUser);

				}
			}
		}
	}

	private void deactivateMemberClausul(ActionUser actionUser, MemberProduct memberProduct, Member member)
			throws Exception {

		try {

			if (member != null) {
				Collection<MemberClausul> clausulList = memberClausulService.getMemberActiveClausul(
						member.getMemberId(), memberProduct.getProductId().getCaseCategory().getCaseCategoryId());

				Iterator<MemberClausul> iterator = clausulList.iterator();

				while (iterator.hasNext()) {

					MemberClausul memberClausul = iterator.next();

					if (memberClausul != null) {

						memberClausul.setDeletedStatus(1);
						memberClausul.setDeletedTime(new Timestamp(System.currentTimeMillis()));

						if (actionUser != null) {
							memberClausul.setDeletedBy(actionUser.getUser().getUsername());
						}
						memberClausulService.delete(memberClausul, actionUser);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deactivateMemberClausul(ActionUser actionUser, Member member) throws Exception {

		try {

			if (member != null) {
				Collection<MemberClausul> clausulList = memberClausulService
						.getMemberActiveClausul(member.getMemberId());
				Iterator<MemberClausul> iterator = clausulList.iterator();

				while (iterator.hasNext()) {

					MemberClausul memberClausul = iterator.next();

					if (memberClausul != null) {

						memberClausul.setDeletedStatus(1);
						memberClausul.setDeletedTime(new Timestamp(System.currentTimeMillis()));

						if (actionUser != null) {
							memberClausul.setDeletedBy(actionUser.getUser().getUsername());
						}
						memberClausulService.delete(memberClausul, actionUser);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deactivateMemberProduct(ActionUser actionUser, Member member, Date pastExpireDate) throws Exception {

		try {

			Collection<MemberProduct> memberProductList = memberProductService
					.getMemberActiveProduct(member.getMemberId());

			if (memberProductList != null) {
				Iterator<MemberProduct> iterator = memberProductList.iterator();

				while (iterator.hasNext()) {
					MemberProduct product = iterator.next();
					if (product != null
							&& product.getMemberProductStatus().getStatusId().intValue() == SubscriptionStatus.ACTIVE) {
						SubscriptionStatus status = new SubscriptionStatus();
						status.setStatusId(SubscriptionStatus.TERMINATED);
						product.setMemberProductStatus(status);
						product.setDeletedStatus(Integer.valueOf(1));
						product.setExpireDate(pastExpireDate);
						memberProductService.update(product, actionUser);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deactivateMemberBenefit(ActionUser actionUser, Member member, Date expireDate) throws Exception {

		try {

			Collection<MemberBenefit> memberBenefitList = memberBenefitService
					.getMemberBenefitList(member.getMemberId());
			Iterator<MemberBenefit> iterator = memberBenefitList.iterator();

			while (iterator.hasNext()) {

				MemberBenefit memberBenefit = iterator.next();

				if (memberBenefit != null
						&& memberBenefit.getMemberBenefitStatus().intValue() == SubscriptionStatus.ACTIVE) {

					memberBenefit.setExpireDate(expireDate);
					memberBenefit.setMemberBenefitStatus(SubscriptionStatus.TERMINATED);
					memberBenefit.setDeletedStatus(Integer.valueOf(1));
					memberBenefit.setDeletedTime(new Timestamp(System.currentTimeMillis()));
					memberBenefitService.update(memberBenefit, actionUser);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void populateMemberClausul(ActionUser actionUser, Member member, Iterator<ProductClausul> iterator,
			MemberProduct memberProduct) throws Exception {
		MemberClausul memberClausul = null;
		ProductClausul productClausul = null;

		while (iterator.hasNext()) {

			productClausul = iterator.next();

			if (productClausul != null) {
				memberClausul = new MemberClausul();
				memberClausul.setMemberProductId(memberProduct);
				memberClausul.setBenefitAmount(productClausul.getBenefitAmount());
				memberClausul.setBenefitParameter(productClausul.getBenefitParameter());
				memberClausul.setBenefitReductionPercentage(productClausul.getBenefitReductionPercentage());
				memberClausul.setCaseCategoryId(memberProduct.getProductId().getCaseCategory());
				memberClausul.setDecision(productClausul.getDecision());
				memberClausul.setDiagnosisId(productClausul.getDiagnosisId());
				memberClausul.setParameterUmur(productClausul.getParameterUmur());
				memberClausul.setReductionSubject(productClausul.getReductionSubject());
				memberClausul.setReductionType(productClausul.getReductionType());
				memberClausul.setTreatmentUpgradeType(productClausul.getTreatmentUpgradeType());
				memberClausul.setUmur(productClausul.getUmur());

				memberClausul.setClausulId(productClausul.getClausulId());
				memberClausul.setMemberId(member);
				memberClausul.setDeletedStatus(0);
				memberClausul.setCreatedTime(new Timestamp(System.currentTimeMillis()));

				if (actionUser != null) {
					memberClausul.setCreatedBy(actionUser.getUser().getUsername());
				}

				memberClausulService.create(memberClausul, actionUser);

			}
		}
	}

	public Member create(Member object, ActionUser actionUser) throws Exception {

		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(Integer.valueOf(0));

		if (actionUser != null) {
			User user = actionUser.getUser();

			if (user != null) {
				object.setCreatedBy(user.getUsername());
			}
		}

		if (object != null) {

			object.setStatus(SubscriptionStatus.PENDING);
		}
		if (object != null && object.getMemberGroupId() != null) {
			if (object.getMemberGroupId().getTipe() != null) {
				object.setTipe(object.getMemberGroupId().getTipe());
			}
		}

		System.out.println("================= DEBUGING =====================");
		System.out.println("= Name : " + object.getFirstName());
		System.out.println("= Number : " + object.getCustomerNumber());
		System.out.println("= Parent : " + object.getParentMember());
		System.out.println("= Client : " + object.getClientId());
		System.out.println("= Group : " + object.getMemberGroupId());
		System.out.println("= Status : " + object.getStatus());
		System.out.println("= Relationship : " + object.getRelationshipId());
		System.out.println("= Member Type : " + object.getMemberType());
		System.out.println("================= DEBUGING =====================");

		Member result = memberDao.create(object);

		if (result != null) {
			if (result.getCurrentCardNumber() != null || !result.getCurrentCardNumber().equalsIgnoreCase("")) {

				String[] eqCardParam = { "deletedStatus", "memberId.memberId", "cardNumber" };
				Object[] eqCardValue = { Integer.valueOf(0), result.getMemberId(), result.getCurrentCardNumber() };

				int totalCard = memberCardService.getTotal(null, null, eqCardParam, eqCardValue);

				if (totalCard == 0) {
					MemberElectronicCard card = new MemberElectronicCard();
					card.setMemberId(result);
					card.setMemberNumber(result.getCustomerPolicyNumber());
					card.setCardStatus(1);
					card.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
					if (actionUser != null && actionUser.getUser() != null) {
						card.setCreatedBy(actionUser.getUser().getUsername());
					}
					card.setDeletedStatus(0);
					card.setCardNumber(result.getCurrentCardNumber());
					card.setRegistrationDate(new java.sql.Date(System.currentTimeMillis()));

					card.setExpireDate(result.getExpireDate());
					card.setEffectiveDate(result.getEffectiveDate());

					if (result.getMemberGroupId().getCurrentActivePolicyId() != null) {
						card.setPolicyId(result.getMemberGroupId().getCurrentActivePolicyId());
						card.setCardType(result.getMemberGroupId().getCurrentActivePolicyId().getCardTypeId());

						memberCardService.create(card, actionUser);
					}
				}

			}
		}
		return result;
	}

	/*
	 * Method update (Member object) berfungsi untuk melakukan perubahan
	 * terhadap sebuah object yang terdapat didalam database
	 * 
	 * @param object adalah sebuah object yang ingin diubah
	 * 
	 * @return object hasil update, exception jika gagal
	 */
	public Member update(Member object, ActionUser actionUser) throws Exception {
		object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));

		if (actionUser != null) {
			User user = actionUser.getUser();

			if (user != null) {
				object.setModifiedBy(user.getUsername());
			}
		}
		memberDao.update(object);
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
	public Member trash(java.io.Serializable pkey) throws Exception {
		Member object = memberDao.get(pkey);
		memberDao.delete(object);
		return object;
	}

	/*
	 * Method delete (Member object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database
	 * 
	 * @param object adalah sebuah object yang ingin dihapus, isi dari object
	 * tersebut cukup dengan mengisi field-field primary key
	 * 
	 * @return updated object, exception if failed
	 */

	public Member delete(java.io.Serializable pkey, ActionUser actionUser) throws Exception {
		Member object = memberDao.get(pkey);

		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(new Integer(1));

		if (actionUser != null) {
			User user = actionUser.getUser();

			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}
		}

		memberDao.update(object);
		return object;
	}

	/*
	 * Method delete (Member object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database
	 * 
	 * @param object adalah sebuah object yang ingin dihapus, isi dari object
	 * tersebut cukup dengan mengisi field-field primary key
	 * 
	 * @return updated object, exception if failed
	 */

	public Member delete(Member object, ActionUser actionUser) throws Exception {

		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(new Integer(1));

		if (actionUser != null) {
			User user = actionUser.getUser();

			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}
		}

		memberDao.update(object);
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
	public Member get(java.io.Serializable pkey) throws Exception {
		Member object = null;
		object = memberDao.get(pkey);
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

	public Member get(java.io.Serializable pkey, String[] required) throws Exception {
		Member object = null;
		object = memberDao.get(pkey);
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

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] required, int index, int offset) throws Exception {

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();

		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	// --req end

	// start search order by
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] required, String columnOrder, boolean order, int index, int offset) throws Exception {

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(order, columnOrder, c);
		List list = c.list();

		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	// end search order by

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder[], int index, int offset) throws Exception {

		Criteria c = memberDao.getCriteria();
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
		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder, int index, int offset) throws Exception {

		Criteria c = memberDao.getCriteria();
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
		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	// --req end

	// -- 1b

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, int index,
			int offset) throws Exception {

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String[] required, int index, int offset) throws Exception {
		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	// --req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = memberDao.getCriteria();
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

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}
	// req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = memberDao.getCriteria();
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

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	// -- 2 , between

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, int index, int offset) throws Exception {

		Criteria c = memberDao.getCriteria();
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

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder[],
			int index, int offset) throws Exception {

		Criteria c = memberDao.getCriteria();
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

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder,
			int index, int offset) throws Exception {

		Criteria c = memberDao.getCriteria();
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

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, int index, int offset) throws Exception {

		Criteria c = memberDao.getCriteria();
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

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder[], int index,
			int offset) throws Exception {

		Criteria c = memberDao.getCriteria();
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

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder, int index,
			int offset) throws Exception {

		Criteria c = memberDao.getCriteria();
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

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	// -- search end

	// -- get total

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams)
			throws Exception {

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2) throws Exception {

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String likeColumns, Object likeParams, String eqColumns, Object eqParams) throws Exception {

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2) throws Exception {

		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// ------------------------------------------------------------------
	public int getTotal() throws Exception {
		Criteria c = memberDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
	// -- get total end

	// ---------------------------------------------------
	public Collection getAll(String[] required) throws Exception {

		Criteria c = memberDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection getAll() throws Exception {

		Criteria c = memberDao.getCriteria();
		List list = c.list();
		return list;
	}
	// -------------------------------------------------

	// unique Result

	public Member searchUnique(String eqColumns, Object eqParams, String[] required) throws Exception {
		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Member obj = (Member) c.uniqueResult();
		DaoSupportUtil.lazyInit(required, obj);
		return obj;
	}

	public Member searchUnique(String eqColumns, Object eqParams) throws Exception {
		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Member obj = (Member) c.uniqueResult();
		return obj;
	}
	
	public Member searchUnique (String[] eqColumns, Object[] eqParams,int index,int offset)
			throws Exception{
			Criteria c = memberDao.getCriteria();
			DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
			DaoSupportUtil.setLimit(index, offset, c);
			Member obj = (Member) c.uniqueResult();
			return obj;
		}

	// -----------------------------------------------

	/*
	 * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
	 * 
	 * @return criteria
	 */
	public Criteria getCriteria() throws Exception {
		Criteria c = memberDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = memberDao.getDetachedCriteria();
		return dc;
	}

	public Member create(Member object, String productCode, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		object.setDeletedStatus(Integer.valueOf(0));

		object.setCurrentProductCode(productCode);

		if (actionUser != null) {
			User user = actionUser.getUser();

			if (user != null) {
				object.setCreatedBy(user.getUsername());
			}
		}

		if (object != null) {

			object.setStatus(SubscriptionStatus.PENDING);
		}

		Member result = memberDao.create(object);
		return result;

	}

	public Collection<Member> getMemberDependent(Integer memberId) throws Exception {
		Collection<Member> result = null;

		String[] eqParam = { "parentMember.memberId" };
		Object[] eqValue = { memberId };

		int total = getTotal(null, null, eqParam, eqValue);
		result = search(null, null, eqParam, eqValue, 0, total);
		return result;
	}

	public ActionResult terminate(Integer memberId, Date terminationDate, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		String[] required = { "Member.MemberGroupId", "Member.ClientId", "Member.RelationshipId", "Member.ParentMember",
				"Member.CurrentPolicyId" };

		// Member member = get(memberId);

		Member member = get(memberId, required);

		ActionResult result = new ActionResult();
		result.setActionCode("TERMINATEMEMBER");
		result.setResult(false);

		if (member != null && member.getStatus().intValue() == SubscriptionStatus.ACTIVE) {
			Date expireDate = member.getExpireDate();

			DateTime dtExpireDate = new DateTime(expireDate.getTime());

			Integer statusId = 0;
			if (dtExpireDate.isBefore(terminationDate.getTime())) {

				statusId = SubscriptionStatus.TERMINATED;
				member.setStatus(SubscriptionStatus.TERMINATED);
			} else {

				statusId = SubscriptionStatus.RESIGNED;
				member.setStatus(SubscriptionStatus.RESIGNED);
				member.setResignedDate(terminationDate);

			}
			// String currentInformation = member.get
			update(member, actionUser);

			if (member.getParentMember().getMemberId().intValue() == member.getMemberId().intValue()) {
				Collection<Member> dependentList = getMemberDependent(memberId);

				Iterator<Member> iterator = dependentList.iterator();

				String[] productEqP = { "memberId.memberId", "memberProductStatus.statusId", "deletedStatus" };
				String[] benefitEqP = { "memberId.memberId", "memberBenefitStatus", "deletedStatus" };
				String[] clausulEqP = { "memberId.memberId", "memberClausulStatus", "deletedStatus" };
				while (iterator.hasNext()) {
					Member dependent = iterator.next();

					if (dependent != null) {
						dependent.setStatus(statusId);
						dependent.setResignedDate(terminationDate);

						update(dependent, actionUser);

						Object[] productEqQ = { dependent.getMemberId(), Integer.valueOf(1), Integer.valueOf(0) };
						Object[] benefitEqQ = { dependent.getMemberId(), Integer.valueOf(1), Integer.valueOf(0) };
						Object[] clausulEqQ = { dependent.getMemberId(), Integer.valueOf(1), Integer.valueOf(0) };

						int totalBenefit = memberBenefitService.getTotal(null, null, benefitEqP, benefitEqQ);
						int totalProduct = memberProductService.getTotal(null, null, productEqP, productEqQ);
						int totalClausul = memberClausulService.getTotal(null, null, clausulEqP, clausulEqQ);

						Collection<MemberProduct> productList = memberProductService.search(null, null, productEqP,
								productEqQ, 0, totalProduct);

						Collection<MemberBenefit> benefitList = memberBenefitService.search(null, null, benefitEqP,
								benefitEqQ, 0, totalBenefit);
						Collection<MemberClausul> clausulList = memberClausulService.search(null, null, clausulEqP,
								clausulEqQ, 0, totalClausul);

						for (Iterator iterator2 = productList.iterator(); iterator2.hasNext();) {

							MemberProduct memberProduct = (MemberProduct) iterator2.next();

							if (memberProduct != null) {
								SubscriptionStatus mpStatus = new SubscriptionStatus();
								mpStatus.setStatusId(SubscriptionStatus.TERMINATED);

								memberProduct.setMemberProductStatus(mpStatus);
								memberProduct.setExpireDate(terminationDate);

								memberProductService.update(memberProduct, actionUser);

							}

						}

						for (Iterator iterator2 = benefitList.iterator(); iterator2.hasNext();) {

							MemberBenefit memberBenefit = (MemberBenefit) iterator2.next();

							if (memberBenefit != null) {
								memberBenefit.setMemberBenefitStatus(SubscriptionStatus.TERMINATED);
								memberBenefit.setExpireDate(terminationDate);

								memberBenefitService.update(memberBenefit, actionUser);
							}

						}

						for (Iterator iterator2 = clausulList.iterator(); iterator2.hasNext();) {
							MemberClausul memberClausul = (MemberClausul) iterator2.next();

							if (memberClausul != null) {
								memberClausul.setMemberClausulStatus(SubscriptionStatus.TERMINATED);

								memberClausulService.update(memberClausul, actionUser);

							}

						}

					}
				}
			}
			result.setResultObject(member);
			result.setResult(true);
			result.setAdditionalMessage("Sukses melakukan pemberhentian member");
		}

		return result;
	}

	public Member getMember(String memberNumber) throws Exception {
		// TODO Auto-generated method stub
		Member member = searchUnique("customerPolicyNumber", memberNumber);

		return member;
	}

	public Collection<Object[]> getAllMember(Integer status) throws Exception {
		// TODO Auto-generated method stub

		Session session = memberDao.getMemberSession();

		SQLQuery query = session.createSQLQuery(
				"SELECT first_name,customer_policy_number,effective_date,expire_date,current_product_code FROM member");
		Collection<Object[]> result = query.list();

		System.out.println("DONE QUERY ALL MEMBER");

		return result;
	}

	public ActionResult terminate(String memberNumbers, Date terminateDate, ActionUser actionUser) throws Exception {
		//

		Member member = getMember(memberNumbers);

		ActionResult result = new ActionResult();

		if (member != null) {
			result = terminate(member.getMemberId(), terminateDate, actionUser);
		}
		return result;
	}

	public Member getMember(String memberNumber, Integer clientId, Integer memberGroupId) throws Exception {
		// TODO Auto-generated method stub

		String[] params = { "customerPolicyNumber", "clientId.clientId", "memberGroupId.memberGroupId" };
		Object[] value = { memberNumber, clientId, memberGroupId };

		Collection<Member> members = search(null, null, params, value, 0, 1);

		Member member = null;

		if (members != null) {
			Iterator<Member> memberIterator = members.iterator();

			if (memberIterator.hasNext()) {
				member = memberIterator.next();
			}
		}

		return member;
	}

	private boolean isProductBenefitExist(Integer memberId, ProductBenefit productBenefit,
			MemberProduct memberProduct) {
		boolean result = false;

		try {
			int memberProductId = memberProduct == null ? -1 : memberProduct.getMemberProductId();
			
			System.out.println("IS PRODUCT BENEFIT EXIST ===> MEMBER PRODUCT ID = " + memberProductId );
			String[] params = { "caseCategoryId.caseCategoryId", "deletedStatus", "productId.productId",
					"itemCategoryId.itemCategoryId", "benefitCalculationMethod.benefitUsageTypeId",
					"memberBenefitStatus", "memberId.memberId", "providerContract", "treatmentLocation.locationId",
					"treatmentLevel", "memberProductId.memberProductId" };

			Integer providerContract = productBenefit.getProviderContract();
			Integer location = productBenefit.getTreatmentLocation() == null ? null
					: productBenefit.getTreatmentLocation().getLocationId();

			Object[] value = { productBenefit.getCaseCategoryId().getCaseCategoryId(), Integer.valueOf(0),
					productBenefit.getProductId().getProductId(),
					productBenefit.getItemCategoryId().getItemCategoryId(),
					productBenefit.getBenefitUsageType().getBenefitUsageTypeId(), Integer.valueOf(1), memberId,
					providerContract, location, productBenefit.getTreatmentLevel(),
					memberProduct.getMemberProductId() };

			int total = memberBenefitService.getTotal(null, null, params, value);

			if (total > 0) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private boolean isPolicyBenefitExist(Integer memberId, PolicyBenefit policyBenefit) {
		boolean result = false;

		try {

			Integer providerContractId = policyBenefit.getProviderContract();
			boolean providerContract = false;

			if (providerContractId != null) {
				if (providerContractId.intValue() == 1) {
					providerContract = true;
				}
			}
			Integer location = policyBenefit.getTreatmentLocation() == null ? null
					: policyBenefit.getTreatmentLocation();

			if (policyBenefit.getItemCategoryId() != null) {
				String[] params = { "caseCategoryId.caseCategoryId", "deletedStatus", "itemCategoryId.itemCategoryId",
						"benefitCalculationMethod.benefitUsageTypeId", "memberBenefitStatus", "memberId.memberId",
						"providerContract", "treatmentLocation.locationId" };

				Object[] value = { policyBenefit.getCaseCategoryId().getCaseCategoryId(), Integer.valueOf(0),
						policyBenefit.getItemCategoryId().getItemCategoryId(), policyBenefit.getBenefitType(),
						Integer.valueOf(1), memberId, providerContract, location };

				int total = memberBenefitService.getTotal(null, null, params, value);

				if (total > 0) {
					result = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private boolean isProductExist(Integer memberId, Product productId) {
		boolean result = false;

		try {
			String[] params = { "deletedStatus", "productId.productId", "isProductActive",
					"memberProductStatus.statusId", "memberId.memberId" };

			Object[] value = { Integer.valueOf(0), productId.getProductId(), Integer.valueOf(1),
					SubscriptionStatus.ACTIVE, memberId };

			int total = memberProductService.getTotal(null, null, params, value);

			if (total > 0) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int[] getYearlyBranchMemberGrowth(int year, Integer clientId) throws Exception {

		int[] result = new int[12];

		try {

			for (int i = 0; i < result.length; i++) {
				result[i] = 0;
			}
			Session session = memberDao.getMemberSession();

			if (session != null) {
				String queryString = "SELECT month(m.effectiveDate),count(m.memberId) "
						+ "FROM Member m WHERE year(m.effectiveDate) = :year AND m.deletedStatus = 0 "
						+ " AND m.clientId = :clientId GROUP BY month(m.effectiveDate)";

				Query query = session.createQuery(queryString);
				query.setInteger("year", year);
				query.setInteger("clientId", clientId);

				List<Object[]> hasil = query.list();

				if (hasil != null) {
					Iterator<Object[]> iterator = hasil.iterator();

					while (iterator.hasNext()) {
						Object[] tmp = iterator.next();

						if (tmp != null) {
							int month = Integer.valueOf(tmp[0].toString());
							int amount = Integer.valueOf(tmp[1].toString());

							result[month - 1] = amount;
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double[] getYearlyBranchMemberPremiumGrowth(int year, Integer clientId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] getYearlyMemberGrowth(int year) throws Exception {
		// TODO Auto-generated method stub
		int[] result = new int[12];

		try {

			for (int i = 0; i < result.length; i++) {
				result[i] = 0;
			}
			Session session = memberDao.getMemberSession();

			if (session != null) {
				String queryString = "SELECT month(m.effectiveDate),count(m.memberId) "
						+ "FROM Member m WHERE year(m.effectiveDate) = :year AND m.deletedStatus = 0 "
						+ "GROUP BY month(m.effectiveDate)";

				Query query = session.createQuery(queryString);
				query.setInteger("year", year);

				List<Object[]> hasil = query.list();

				if (hasil != null) {
					Iterator<Object[]> iterator = hasil.iterator();

					while (iterator.hasNext()) {
						Object[] tmp = iterator.next();

						if (tmp != null) {
							int month = Integer.valueOf(tmp[0].toString());
							int amount = Integer.valueOf(tmp[1].toString());

							result[month - 1] = amount;
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double[] getYearlyMemberPremiumGrowth(int year) throws Exception {
		double[] result = new double[12];

		try {

			for (int i = 0; i < result.length; i++) {
				result[i] = 0;
			}
			Session session = memberDao.getMemberSession();

			if (session != null) {
				String queryString = "SELECT month(m.effectiveDate),sum(m.currentAnnualPremium) "
						+ "FROM Member m WHERE year(m.effectiveDate) = :year AND m.deletedStatus = 0 "
						+ "GROUP BY month(m.effectiveDate)";

				Query query = session.createQuery(queryString);
				query.setInteger("year", year);

				List<Object[]> hasil = query.list();

				if (hasil != null) {
					Iterator<Object[]> iterator = hasil.iterator();

					while (iterator.hasNext()) {
						Object[] tmp = iterator.next();

						if (tmp != null) {
							int month = Integer.valueOf(tmp[0].toString());
							double amount = Double.valueOf(tmp[1].toString());

							result[month - 1] = amount;
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Collection<Member> getDependentList(Integer memberId) throws Exception {
		// TODO Auto-generated method stub
		Collection<Member> result = new Vector<Member>();

		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m FROM Member m WHERE m.parentMember.memberId = ? ";

			Query q = session.createQuery(query);
			q.setInteger(0, memberId);

			result = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Collection<Member> getParentList(Integer memberGroupId) throws Exception {
		// TODO Auto-generated method stub
		Collection<Member> result = new Vector<Member>();

		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m FROM Member m WHERE m.parentMember.memberId = m.memberId "
					+ " AND m.memberGroupId.memberGroupId = ? ";

			Query q = session.createQuery(query);
			q.setInteger(0, memberGroupId);

			result = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Collection<Member> getParentListByAlphabet(Integer memberGroupId, String alphabet) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Collection<Member> result = new Vector<Member>();

		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m FROM Member m WHERE m.parentMember.memberId = m.memberId AND m.memberGroupId.memberGroupId = ? AND m.firstName LIKE ?";

			Query q = session.createQuery(query);
			q.setInteger(0, memberGroupId);
			q.setString(1, alphabet + "%");

			result = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Collection<Member> getMemberList(Integer memberGroupId) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Collection<Member> result = new Vector<Member>();

		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m FROM Member m WHERE m.memberGroupId.memberGroupId = ? AND m.deletedStatus = 0 ORDER BY m.firstName ASC";

			Query q = session.createQuery(query);
			q.setInteger(0, memberGroupId);

			result = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Collection<Member> searchMember(String qText) throws Exception {
		// TODO Auto-generated method stub
		Collection<Member> result = new Vector<Member>();

		try {

			Criteria c = memberDao.getCriteria();
			c.add(Restrictions.or(Restrictions.ilike("firstName", qText, MatchMode.ANYWHERE),
					Restrictions.ilike("customerPolicyNumber", qText, MatchMode.ANYWHERE)));
			c.add(Property.forName("deletedStatus").eq(0));
			c.setMaxResults(10);

			result = c.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Collection<Member> searchMemberAndClient(String qText, Integer clientId) throws Exception {
		Collection<Member> result = new Vector<Member>();

		try {

			Criteria c = memberDao.getCriteria();
			c.add(Restrictions.or(Restrictions.ilike("firstName", qText, MatchMode.ANYWHERE),
					Restrictions.ilike("customerPolicyNumber", qText, MatchMode.ANYWHERE)));
			c.add(Property.forName("deletedStatus").eq(0));
			c.add(Property.forName("clientId.clientId").eq(clientId));
			c.setMaxResults(10);

			List list = c.list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Collection<Member> searchMemberClientPolicy(String firstName, Integer clientId, Integer memberGroupId,
			Integer currentId) throws Exception {
		// TODO Auto-generated method stub
		Collection<Member> result = null;
		try {
			Session session = memberDao.getMemberSession();

			String query = " SELECT m " + " FROM Member m " +
					// " inner join m.clientId " +
					// " inner join m.memberGroupId " +
					// " inner join m.currentPolicyId " +
			" WHERE " + " m.clientId.clientId = :clientid and " + " m.memberGroupId.memberGroupId = :memberGroupId and "
					+ " m.currentPolicyId.policyId = :currentPolicyId and " + " m.deletedStatus = 0 " +
					// " and m.clientId.deletedStatus = 0 " +
					// " and m.memberGroupId.deletedStatus = 0 " +
					// " and m.currentPolicyId.deletedStatus = 0 " +
			" and m.firstName LIKE upper(:firstName) ";

			Query q = session.createQuery(query);

			q.setInteger("clientid", clientId);
			q.setInteger("memberGroupId", memberGroupId);
			q.setInteger("currentPolicyId", currentId);
			q.setString("firstName", "%" + firstName + "%");

			result = q.list();

			if (result != null) {
				//
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Collection<Member> searchMemberAndGroup(String qText, Integer groupId) throws Exception {
		Collection<Member> result = new Vector<Member>();

		try {

			Criteria c = memberDao.getCriteria();
			c.add(Restrictions.or(Restrictions.ilike("firstName", qText, MatchMode.ANYWHERE),
					Restrictions.ilike("customerPolicyNumber", qText, MatchMode.ANYWHERE)));
			c.add(Property.forName("deletedStatus").eq(0));
			c.add(Property.forName("memberGroupId.memberGroupId").eq(groupId));
			c.setMaxResults(10);

			result = c.list();

			String[] required = { "Member.ClientId", "Member.MemberGroupId", "Member.ParentMember" };

			for (Iterator iter = result.iterator(); iter.hasNext();) {
				Member element = (Member) iter.next();
				DaoSupportUtil.lazyInit(required, element);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ActionResult authorizeMemberCard(Integer memberId, String cardNumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Member getMemberByCardNumber(String memberNumber, String cardNumber) throws Exception {
		// TODO Auto-generated method stub
		Member result = null;

		try {

			String[] eqParam = { "customerPolicyNumber", "currentCardNumber", "deletedStatus" };
			Object[] eqValue = { memberNumber, cardNumber, Integer.valueOf(0) };

			Collection<Member> memberList = search(null, null, eqParam, eqValue, 0, 1);

			if (memberList != null) {
				Iterator<Member> memberIterator = memberList.iterator();

				if (memberIterator.hasNext()) {
					result = memberIterator.next();
				}
			}

			/**
			 * Session session = memberDao.getMemberSession(); String query =
			 * "SELECT m FROM Member m WHERE m.customerPolicyNumber = :number "
			 * + "AND m.currentCardNumber = :cardNo AND m.deletedStatus = 0 ";
			 * 
			 * Query q = session.createQuery(query); q.setString("number",
			 * memberNumber); q.setString("cardNo", cardNumber);
			 * q.setMaxResults(1);
			 * 
			 * result = (Member)q.uniqueResult();
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Member getMemberByCardNumber(String cardNumber) throws Exception {
		// TODO Auto-generated method stub
		Member result = null;

		try {

			String[] required = { "Member.MemberGroupId", "Member.ClientId", "Member.MemberGroupId.Status",
					"Member.CurrentPolicyId", "Member.ParentMember" };

			String[] eqParam = { "currentCardNumber", "deletedStatus" };

			Object[] eqValue = { cardNumber, Integer.valueOf(0) };

			Collection<Member> memberList = search(null, null, eqParam, eqValue, required, 0, 1);

			if (memberList != null) {
				Iterator<Member> memberIterator = memberList.iterator();

				if (memberIterator.hasNext()) {
					result = memberIterator.next();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int[] getYearlyGroupMemberGrowth(int year, Integer memberGroupId) throws Exception {

		int[] result = new int[12];

		try {

			for (int i = 0; i < result.length; i++) {
				result[i] = 0;
			}
			Session session = memberDao.getMemberSession();

			if (session != null) {
				String queryString = "SELECT month(m.effectiveDate),count(m.memberId) "
						+ "FROM Member m WHERE year(m.effectiveDate) = :year AND m.deletedStatus = 0 "
						+ " AND m.memberGroupId.memberGroupId = :groupId GROUP BY month(m.effectiveDate)";

				Query query = session.createQuery(queryString);
				query.setInteger("year", year);
				query.setInteger("groupId", memberGroupId);

				List<Object[]> hasil = query.list();

				if (hasil != null) {
					Iterator<Object[]> iterator = hasil.iterator();

					while (iterator.hasNext()) {
						Object[] tmp = iterator.next();

						if (tmp != null) {
							int month = Integer.valueOf(tmp[0].toString());
							int amount = Integer.valueOf(tmp[1].toString());

							result[month - 1] = amount;
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
	public ActionResult block(Integer memberId, String reason, Date startDate, Date endDate, ActionUser actionUser)
			throws Exception {

		Member member = get(memberId);
		ActionResult result = new ActionResult();
		result.setActionCode("BLOCKMEMBER");
		result.setResult(false);

		if (member != null && member.getStatus().intValue() == SubscriptionStatus.ACTIVE) {
			member.setStatus(SubscriptionStatus.BLOCKED);
			member.setSuspendStartDate(startDate);
			member.setSuspendEndDate(endDate);

			update(member, actionUser);

			String username = "";

			if (actionUser != null && actionUser.getUser() != null) {
				username = actionUser.getUser().getUsername();
			}
			ActivityLog log = new ActivityLog();
			log.setAction("BLOCKMEMBER");
			log.setDescription("BLOCK MEMBER " + member.getCustomerPolicyNumber() + " " + member.getFirstName()
					+ " reason : " + reason);
			log.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			log.setIpAddress(actionUser.getIpAddress());
			log.setMemberId(member);
			log.setUsername(username);

			activityLogService.create(log);

			if (member.getParentMember().getMemberId().intValue() == member.getMemberId().intValue()) {
				Collection<Member> dependentList = getMemberDependent(memberId);

				Iterator<Member> iterator = dependentList.iterator();

				while (iterator.hasNext()) {
					Member dependent = iterator.next();

					if (dependent != null) {
						dependent.setStatus(SubscriptionStatus.BLOCKED);
						dependent.setSuspendStartDate(startDate);
						dependent.setSuspendEndDate(endDate);
						update(dependent, actionUser);

						log = new ActivityLog();
						log.setAction("BLOCKMEMBER");
						log.setDescription("BLOCK MEMBER " + dependent.getCustomerPolicyNumber() + " "
								+ dependent.getFirstName() + " reason : " + reason);
						log.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
						log.setIpAddress(actionUser.getIpAddress());
						activityLogService.create(log);
					}
				}
			}
			result.setResultObject(member);
			result.setResult(true);
			result.setAdditionalMessage("Sukses melakukan block member");
		}

		return result;
	}

	@Override
	public ActionResult unblock(Integer memberId, String reason, ActionUser actionUser) throws Exception {
		Member member = get(memberId);
		ActionResult result = new ActionResult();
		result.setActionCode("UNBLOCKMEMBER");
		result.setResult(false);

		if (member != null && member.getStatus().intValue() == SubscriptionStatus.BLOCKED) {

			member.setSuspendEndDate(null);
			member.setSuspendStartDate(null);
			member.setStatus(SubscriptionStatus.ACTIVE);

			update(member, actionUser);

			String username = "";

			if (actionUser != null && actionUser.getUser() != null) {
				username = actionUser.getUser().getUsername();
			}

			ActivityLog log = new ActivityLog();
			log.setAction("UNBLOCKMEMBER");
			log.setDescription("UNBLOCK MEMBER " + member.getCustomerPolicyNumber() + " " + member.getFirstName()
					+ " reason : " + reason);
			log.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			log.setIpAddress(actionUser.getIpAddress());
			log.setMemberId(member);
			log.setUsername(username);
			activityLogService.create(log);

			if (member.getParentMember().getMemberId().intValue() == member.getMemberId().intValue()) {
				Collection<Member> dependentList = getMemberDependent(memberId);

				Iterator<Member> iterator = dependentList.iterator();

				while (iterator.hasNext()) {
					Member dependent = iterator.next();

					if (dependent != null) {
						dependent.setSuspendEndDate(null);
						dependent.setSuspendStartDate(null);
						dependent.setStatus(SubscriptionStatus.ACTIVE);

						update(dependent, actionUser);

						log = new ActivityLog();
						log.setAction("UNBLOCKMEMBER");
						log.setDescription("UNBLOCK MEMBER " + dependent.getCustomerPolicyNumber() + " "
								+ dependent.getFirstName() + " reason : " + reason);
						log.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
						log.setIpAddress(actionUser.getIpAddress());
						activityLogService.create(log);
					}
				}
			}
			result.setResultObject(member);
			result.setResult(true);
			result.setAdditionalMessage("Sukses melakukan unblock member");
		}

		return result;
	}

	@Override
	public Collection<Object> getAllPendingEmployee() throws Exception {

		Collection<Object> result = null;
		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m.memberId FROM Member m WHERE m.deletedStatus = 0 AND m.relationship = 'EMPLOYEE' AND m.status = -1 ";

			Query q = session.createQuery(query);

			result = q.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Object> getAllPendingMember() throws Exception {
		Collection<Object> result = null;
		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m.memberId FROM Member m WHERE m.deletedStatus = 0 AND m.status = -1 ";

			Query q = session.createQuery(query);

			result = q.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Object> getAllPendingUpgradeEmployee() throws Exception {
		// TODO Auto-generated method stub
		Collection<Object> result = null;
		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m.memberId FROM Member m WHERE m.deletedStatus = 0 AND m.relationship = 'EMPLOYEE' "
					+ "AND m.status = -3 ";

			Query q = session.createQuery(query);

			result = q.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Object> getAllPendingUpgradeMember() throws Exception {
		Collection<Object> result = null;
		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m.memberId FROM Member m WHERE m.deletedStatus = 0 AND m.status = -3 ";

			Query q = session.createQuery(query);

			result = q.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Object> getAllPendingRenewEmployee() throws Exception {
		// TODO Auto-generated method stub
		Collection<Object> result = null;
		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m.memberId FROM Member m WHERE m.deletedStatus = 0 AND m.relationship = 'EMPLOYEE' AND m.status = -4 ";

			Query q = session.createQuery(query);

			result = q.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Object> getAllPendingRenewMember() throws Exception {

		Collection<Object> result = null;
		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m.memberId FROM Member m WHERE m.deletedStatus = 0 AND m.status = -4 ";

			Query q = session.createQuery(query);

			result = q.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Integer> getAutoRenewalNomineeList() throws Exception {
		// TODO Auto-generated method stub
		Collection<Integer> result = new Vector<Integer>();

		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m.memberId FROM Member m WHERE m.deletedStatus = 0 AND "
					+ " ( m.status = 1 OR m.status = 2 ) " + "AND m.memberGroupId.tipe = :tipe AND"
					+ " m.expireDate < :currentDate AND m.policyExpireDate > :currentDate";

			Query q = session.createQuery(query);
			q.setString("tipe", "I");
			q.setDate("currentDate", new java.sql.Date(System.currentTimeMillis()));

			result = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean activateSuspendRelease(ActionUser user) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;

		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT m.memberId FROM Member m WHERE m.deletedStatus = 0 AND m.status = -2  AND"
					+ " m.suspendEndDate < :currentDate ";

			Query q = session.createQuery(query);
			q.setDate("currentDate", new java.sql.Date(System.currentTimeMillis()));

			List<Object> suspendList = q.list();

			if (suspendList != null) {
				for (Iterator iterator = suspendList.iterator(); iterator.hasNext();) {

					Object object = (Object) iterator.next();
					if (object instanceof Integer) {
						Member member = get((Integer) object);

						if (member != null) {
							member.setStatus(SubscriptionStatus.ACTIVE);
							update(member, user);

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Member getMemberByNumberAndPolicy(String memberNumber, Integer policyId) {
		Member result = null;

		try {
			String[] eqParam = { "deletedStatus", "customerPolicyNumber", "currentPolicyId.policyId" };
			Object[] eqValue = { 0, memberNumber, policyId };

			Collection<Member> memberList = search(null, null, eqParam, eqValue, 0, 1);

			if (memberList != null) {
				Iterator<Member> iterator = memberList.iterator();

				if (iterator.hasNext()) {
					result = iterator.next();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Object> getAllPendingSynchronizeEmployee(Integer policyId) throws Exception {
		// TODO Auto-generated method stub
		Collection<Object> result = null;
		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT DISTINCT(m.memberId) FROM MemberProduct m "
					+ "WHERE m.deletedStatus = 0 AND m.memberId.relationship = 'EMPLOYEE' "
					+ "AND m.memberProductStatus = 1 AND "
					+ "m.memberId.currentPolicyId.policyId = :policyId AND m.productId.productId IN "
					+ " (SELECT p.productId FROM Product p WHERE p.policyId = :policyId AND p.doSynchronize = 1 "
					+ " AND p.deletedStatus = 0) ";

			Query q = session.createQuery(query);
			q.setInteger("policyId", policyId);

			result = q.list();
			if (result != null) {
				System.out.println("SUCCESS RETRIEVE PENDING SYNC FOR " + result.size() + " records");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Object> getAllPendingSynchronizeMember(Integer policyId) throws Exception {
		// TODO Auto-generated method stub
		Collection<Object> result = null;
		try {
			Session session = memberDao.getMemberSession();

			String query = "SELECT DISTINCT(m.memberId) FROM MemberProduct m "
					+ "WHERE m.deletedStatus = 0 AND m.memberId.relationship <> 'EMPLOYEE' "
					+ "AND m.memberProductStatus = 1 AND "
					+ "m.memberId.currentPolicyId.policyId = :policyId AND m.productId.productId IN "
					+ " (SELECT p.productId FROM Product p WHERE p.policyId = :policyId AND p.doSynchronize = 1 AND p.deletedStatus = 0) ";

			Query q = session.createQuery(query);
			q.setInteger("policyId", policyId);

			result = q.list();

			if (result != null) {
				System.out.println("SUCCESS RETRIEVE PENDING SYNC DEPENDENT FOR " + result.size() + " records");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int getTotalRegisteredMember(Integer clientId, Date start, Date end) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;

		try {
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			vEqP.add("deletedStatus");
			vEqQ.add(0);

			if (clientId != null) {
				vEqP.add("clientId");
				vEqQ.add(clientId);
			}

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			if (start != null && end != null) {
				result = getTotal(null, null, sEqP, sEqQ, "effectiveDate", start, end);
			} else {
				result = getTotal(null, null, sEqP, sEqQ);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Collection<Member> searchMemberByDobClientGroup(String[] eqColumns, String[] eqFirstName,
			Object[] oFirstName, Object[] eqParams, String[] required) throws Exception {
		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLikeParam(eqFirstName, oFirstName, c);

		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection<Member> searchMemberByDob(String[] eqColumns, Object[] eqParams, String[] required)
			throws Exception {
		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);

		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection<Member> searchMemberList(String[] eqColumns, Object[] eqParams, Object[] eqParams2,
			String[] required) throws Exception {
		Criteria c = memberDao.getCriteria();
		DaoSupportUtil.setBetweenParam(eqColumns, eqParams, eqParams2, c);

		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Member element = (Member) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	@Override
	public String synchronizeSuspendReactive() throws Exception {
		// TODO Auto-generated method stub
		String result = "";

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalCurrentPolicyMember(Integer policyId) {
		int result = 0;

		try {
			Session session = memberDao.getMemberSession();
			String q = "SELECT count(a) FROM Member a WHERE a.status = :statusActive OR a.status = :statusPendingCP "
					+ "OR a.status = :statusPendingSync";

			Query query = session.createQuery(q);
			query.setInteger("statusActive", SubscriptionStatus.ACTIVE);
			query.setInteger("statusPendingCP", SubscriptionStatus.PENDING_UPGRADE);
			query.setInteger("statusPendingSync", SubscriptionStatus.REVISION);

			List<Object> res = query.list();

			if (res != null) {
				Iterator<Object> iterator = res.iterator();

				if (iterator.hasNext()) {
					Object tmpResult = iterator.next();

					if (tmpResult instanceof Integer) {
						result = (Integer) tmpResult;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Member getMemberByPolicyNumber(String memberNumber, String policyNumber) throws Exception {
		// TODO Auto-generated method stub

		Member result = null;

		if (memberNumber != null && policyNumber != null) {
			String[] eqParam = { "customerPolicyNumber", "currentPolicyNumber", "deletedStatus" };
			Object[] eqValue = { memberNumber, policyNumber, Integer.valueOf(0) };

			Collection<Member> memberList = search(null, null, eqParam, eqValue, 0, 1);

			if (memberList != null && memberList.size() > 0) {
				Iterator<Member> iterator = memberList.iterator();

				if (iterator.hasNext()) {
					result = iterator.next();
				}
			}
		}

		return result;
	}

	@Override
	public boolean updateExpiredMember() throws Exception {
		// TODO Auto-generated method stub
		boolean result = true;

		Session session = memberDao.getMemberSession();

		String qSELECT = "SELECT m FROM Member m WHERE m.expireDate < :exp AND m.status = :stat";
		Query querySelect = session.createQuery(qSELECT);

		querySelect.setDate("exp", new java.sql.Date(System.currentTimeMillis()));
		querySelect.setInteger("stat", SubscriptionStatus.ACTIVE);

		List<Member> list = querySelect.list();
		ActionUser actionUser = new ActionUser();
		User user = new User();
		user.setUsername("system-daemon");
		actionUser.setUser(user);

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {

			Member member = (Member) iterator.next();

			if (member.getResignedDate() != null && member.getResignedDate().after(member.getExpireDate())) {
				member.setResignedDate(member.getExpireDate());
			}

			member.setStatus(SubscriptionStatus.EXPIRED);

			update(member, actionUser);

			Collection<MemberProduct> mpList = memberProductService.getMemberActiveProduct(member.getMemberId());

			SubscriptionStatus terminate = new SubscriptionStatus();
			terminate.setStatusId(SubscriptionStatus.EXPIRED);

			if (mpList != null) {
				for (Iterator iterator2 = mpList.iterator(); iterator2.hasNext();) {

					MemberProduct memberProduct = (MemberProduct) iterator2.next();

					memberProduct.setMemberProductStatus(terminate);

					memberProductService.update(memberProduct, actionUser);

				}
			}

			Collection<MemberElectronicCard> cardList = memberElectronicCardService
					.getCardNumberByMember(member.getMemberId());

			for (Iterator iterator2 = cardList.iterator(); iterator2.hasNext();) {

				MemberElectronicCard memberElectronicCard = (MemberElectronicCard) iterator2.next();

				if (memberElectronicCard != null
						&& memberElectronicCard.getCardStatus().intValue() == MemberElectronicCard.CARD_ACTIVE) {
					memberElectronicCard.setCardStatus(MemberElectronicCard.CARD_EXPIRED);

					memberElectronicCardService.update(memberElectronicCard, actionUser);
				}
			}
		}

		memberProductService.updateMemberExpiredProduct(actionUser);

		return result;
	}

	@Override
	public void activateAndUpdate(ActionUser actionUser, Integer memberId) throws Exception {
		// TODO Auto-generated method stub

		activate(memberId, actionUser);
		populateUpdateMemberProduct(actionUser, memberId);

	}

	@Override
	public boolean activateAdvanceRenewal(ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		boolean result = true;

		Session session = memberDao.getMemberSession();

		String qSELECT = "SELECT m FROM MemberProduct m WHERE m.registerDate <= :effectiveDate AND m.memberProductStatus.statusId = :stat AND m.deletedStatus = 0";
		Query querySelect = session.createQuery(qSELECT);

		querySelect.setDate("effectiveDate", new java.sql.Date(System.currentTimeMillis()));
		querySelect.setInteger("stat", SubscriptionStatus.ADVANCED_RENEWAL);

		List<MemberProduct> list = querySelect.list();

		SubscriptionStatus activeStatus = new SubscriptionStatus();
		activeStatus.setStatusId(SubscriptionStatus.ACTIVE);

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {

			MemberProduct memberProduct = (MemberProduct) iterator.next();

			memberProduct.setMemberProductStatus(activeStatus);

			memberProductService.update(memberProduct, actionUser);

		}

		return result;
	}

	@Override
	public String generateCardNumber(String string, String clientCode, Logger logger, ArrayList<String> errorList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Member getEmployee(String customerNumber,String policyNumber, String clientCode) throws Exception {
		// TODO Auto-generated method stub
		
		Member result = null;
		
		String[] eqParam = {"customerNumber","currentPolicyNumber","clientId.clientCode","relationship","deletedStatus"};
		Object[] eqValue = {customerNumber,policyNumber,clientCode,"EMPLOYEE",0};
		
		Collection<Member> memberList = search(null,null,eqParam,eqValue,0,1);
		
		if (memberList != null){
			Iterator<Member> iterator = memberList.iterator();
			
			if (iterator.hasNext()){
				result = iterator.next();
			}
		}
		
		return result;
	}

}
