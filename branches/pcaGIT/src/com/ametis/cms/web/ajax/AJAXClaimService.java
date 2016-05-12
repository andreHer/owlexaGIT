package com.ametis.cms.web.ajax;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.StringTokenizer;

import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClaimReceivingService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.ProviderService;

public class AJAXClaimService {
	private ClaimService claimService;
	private ClaimReceivingService claimReceivingService;
	BatchClaimService batchClaimService;
	ProviderService providerService;
	PaymentService paymentService;
	Payment tmp;
	
	private MemberProductService memberProductService;
	private ConfigurationService configurationService;
	private MemberService memberService;
	
	
	

	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public ClaimReceivingService getClaimReceivingService() {
		return claimReceivingService;
	}

	public void setClaimReceivingService(
			ClaimReceivingService claimReceivingService) {
		this.claimReceivingService = claimReceivingService;
	}

	public BatchClaimService getBatchClaimService() {
		return batchClaimService;
	}

	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	public String getRoom (String ccId, String admDate, String memberId, String patientId){
		String result = "";
		
		if (ccId != null && admDate != null && memberId != null ){
			if (ccId.equalsIgnoreCase(CaseCategory.INPATIENT+"")){
				
			}
		}
		
		return result;
		
	}
	public String calculatePayment(String claimList, String batchClaimId)
			throws Exception {
		String result = "";

		if (batchClaimId != null) {

			String[] required = { "BatchClaim.MemberId",
					"BatchClaim.ProviderId", "BatchClaim.MemberGroupId" };
			BatchClaim batchClaim = batchClaimService.get(
					Integer.parseInt(batchClaimId), required);

			if (batchClaim.getProviderId() != null) {

				Provider provider = providerService.get(batchClaim
						.getProviderId().getProviderId());
				
				

				if (provider != null) {
					System.out.println("provider = " + provider);

					double discountValue = 0.0;

					try {
						double paidValue = 0.0;

						StringTokenizer tokenizer = new StringTokenizer(
								claimList, ",");

						while (tokenizer.hasMoreTokens()) {
							String claimId = tokenizer.nextToken();

							if (claimId != null) {
								Claim claim = claimService.get(Integer
										.valueOf(claimId));

								if (claim != null) {

									if (claim.getPaidToProvider() != null) {
										paidValue += claim.getPaidToProvider();
									} else {
										if (claim.getClaimTypeId()
												.getClaimTypeId().intValue() == ClaimType.CASHLESS) {
											paidValue += claim
													.getClaimChargeValue();
										} else {
											paidValue += claim
													.getClaimApprovedValue();
										}
									}
								}
							}
						}

						// converting discount percentage into discount value
						if (provider.getDiscount() != null) {

							// getting percentage value
							double percentage = provider.getDiscount()
									.doubleValue();
							if (percentage <= 100 && percentage >= 0) {
								discountValue = paidValue * percentage / 100;
							}
						}

						// getting materai value from batchClaim

						BigDecimal bigDec;
						if (batchClaim.getBiayaMaterai() != null
								&& paidValue != 0) {
							Double materai = batchClaim.getBiayaMaterai();
							// calculate total confirmed payment value

							bigDec = new BigDecimal(paidValue - discountValue
									+ materai);

						} else {

							bigDec = new BigDecimal(paidValue - discountValue);
						}

//						BigDecimal bigDec = new BigDecimal(paidValue);
//						System.out.println("liat paid value :" + paidValue);
						DecimalFormat df = new DecimalFormat("#");
						result = String.format(String.valueOf(df.format(bigDec)));
//						result = bigDec.toPlainString();
						
					}

					catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {

				try {
					double paidValue = 0.0;

					StringTokenizer tokenizer = new StringTokenizer(claimList,
							",");

					while (tokenizer.hasMoreTokens()) {
						String claimId = tokenizer.nextToken();

						if (claimId != null) {
							Claim claim = claimService.get(Integer
									.valueOf(claimId));

							if (claim != null) {

								if (claim.getPaidToProvider() != null) {
									paidValue += claim.getPaidToProvider();
								} else {
									if (claim.getClaimTypeId().getClaimTypeId()
											.intValue() == ClaimType.CASHLESS) {
										paidValue += claim
												.getClaimChargeValue();
									} else {
										paidValue += claim
												.getClaimApprovedValue();
									}
								}
							}
						}
					}
					
					BigDecimal bigDec = new BigDecimal(paidValue);
					System.out.println("liat paid value :" + paidValue);
					DecimalFormat df = new DecimalFormat("#");
					result = String.format(String.valueOf(df.format(paidValue)));

				}

				catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		return result;

	}
}
