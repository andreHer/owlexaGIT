/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ametis.cms.util;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.MemberProductService;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

/**
 *
 * @author adhit
 */
public class ReportFormatter {
    
    public static final int FULL_CSV_REPORT = 1;
    public static final int TEXT_HEADER_REPORT = 2;
    public static final int TEXT_DETAIL_REPORT = 3;
    public static final int EXCEL_REPORT = 4;

    public static String formatDetailIPMatDownload (Collection<ClaimItem> claimItemList){
        String result = "";
              NumberFormat nf = NumberFormat.getInstance(Locale.US);
              
              
        try {
            for (ClaimItem claimItem : claimItemList) {
                
                String claimItemValue = claimItem.getClaimItemValue() == null ? "" : nf.format(claimItem.getClaimItemValue().doubleValue());
                String preApproveRemainBenefit = claimItem.getPreApproveRemainingBenefit() == null ? "" : nf.format(claimItem.getPreApproveRemainingBenefit().doubleValue());
                String claimApprovedValue = claimItem.getClaimItemApprovedValue() == null ? "" : nf.format(claimItem.getClaimItemApprovedValue().doubleValue());
                String claimExcessValue = claimItem.getExcessValue() == null ? "" :  nf.format(claimItem.getExcessValue().doubleValue());
                
                
                result += "\""+claimItem.getClaimId().getClaimNumber()+"\",\""+claimItem.getItemId().getItemCode()+"\"";
                result += ",\""+claimItem.getItemId().getItemName()+"\",\""+TimeUtils.getTotalDay(claimItem.getClaimId().getDischargeDate(), claimItem.getClaimId().getAdmissionDate())+"\",";
                result += "\""+claimItemValue+"\",\""+preApproveRemainBenefit+"\",\"";
                result += claimApprovedValue+"\",\""+claimExcessValue+"\",\""+claimItem.getBenefitCheckRemarks() == null ? "" : claimItem.getBenefitCheckRemarks().replace("\n", "") +"\"\n";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
        
    }
    public static String formatHeaderIPMatDownload (Collection<Claim> claimList){
        String result = "";
        try {
            
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat yearFormat = new SimpleDateFormat("yyyy");
            NumberFormat nf = NumberFormat.getInstance(Locale.US);
            
            for (Claim claim : claimList) {
                
                java.sql.Date approvedTime = new java.sql.Date(claim.getApprovedTime() == null ? 0L : claim.getApprovedTime().getTime());
                java.sql.Date inputTime = new java.sql.Date(claim.getCreatedTime()  == null ? 0L : claim.getCreatedTime().getTime());
                java.sql.Date paymentDate = new java.sql.Date(claim.getPaidTime() == null ? 0L : claim.getPaidTime().getTime());
                
                
                int approveStat = 0;
                int paidStat = 0;
                int klaimStat = 0;
                
                String claimChargeValue = claim.getClaimChargeValue() == null ? "" : nf.format(claim.getClaimChargeValue());
                String claimApprovedValue = claim.getClaimApprovedValue() == null ? "" : nf.format(claim.getClaimApprovedValue());
                String claimExcessValue = claim.getClaimExcessValue() == null ? "" : nf.format(claim.getClaimExcessValue());
                
                result += "\""+claim.getClaimNumber().trim()+"\",\""+claim.getMemberPolicyNumber()+"\"";
                result += ",\""+claim.getMemberName()+"\",\""+claim.getMemberNumber()+"\",";
                result += "\""+df.format(claim.getAdmissionDate())+"\",\""+df.format(claim.getDischargeDate())+"\",\"";
                result += claimChargeValue+"\",\""+claimApprovedValue+"\",\""+claimExcessValue+"\"";
                result += ",\""+claim.getDiagnosis1Code()+"\",\""+claim.getProviderName()+"\",\""+claim.getBatchClaimId().getBatchClaimNumber();
                result += "\",\"\",\""+df.format(approvedTime)+"\",\""+df.format(inputTime)+"\",\"adjust to confirm\",\"";
                result += df.format(paymentDate) + "\",\""+approveStat+"\",\""+paidStat+"\",\""+klaimStat+"\",\"";
                result += claim.getCreatedBy()+"\",\""+claim.getCreatedTime()+"\",\""+claim.getMemberId().getParentMember().getFirstName()+"\",\"";
                result += claim.getBankAccount()+"\",\""+claim.getMemberOtherNumber()+"\",\""+claim.getBank()+"\",\"";
                result += claim.getPlan() + "\",\"no-->perluconfirm\",\""+claim.getProviderId().getProviderCode()+"\",\"";
                result += yearFormat.format(claim.getMemberId().getEffectiveDate())+"\",\"nopolisawal\",\""+claim.getBatchClaimId().getInvoiceNumber()+"\"\n";
                
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
        
    }
    public static String formatHeaderRJDownload (Collection<ClaimItem> claimList){
        String result = "";
        try {
            
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat yearFormat = new SimpleDateFormat("yyyy");
            NumberFormat nf = NumberFormat.getInstance(Locale.US);
                
            for (ClaimItem claimitem : claimList) {
                
                
                java.sql.Date approvedTime = new java.sql.Date(claimitem.getClaimId().getApprovedTime() == null ? 0: claimitem.getClaimId().getApprovedTime().getTime());
                java.sql.Date inputTime = new java.sql.Date(claimitem.getClaimId().getCreatedTime().getTime());
                java.sql.Date paymentDate = new java.sql.Date(claimitem.getClaimId().getPaidTime() == null ? 0L :  claimitem.getClaimId().getPaidTime().getTime());
                java.sql.Date paymentConfirm = new java.sql.Date(claimitem.getClaimId().getPaymentConfirmDate() == null ? 0L: claimitem.getClaimId().getPaymentConfirmDate().getTime());
                
                String claimItemValue = claimitem.getClaimItemValue() == null ? "" : nf.format(claimitem.getClaimItemValue().doubleValue());
                String preApproveRemainBenefit = claimitem.getPreApproveRemainingBenefit() == null ? "" : nf.format(claimitem.getPreApproveRemainingBenefit().doubleValue());
                String claimApprovedValue = claimitem.getClaimItemApprovedValue() == null ? "" : nf.format(claimitem.getClaimItemApprovedValue().doubleValue());
                String claimExcessValue = claimitem.getExcessValue() == null ? "" :  nf.format(claimitem.getExcessValue().doubleValue());
     
                
                
                result += "\""+claimitem.getClaimId().getClaimNumber()+"\",\""+claimitem.getClaimId().getMemberPolicyNumber()+"\"";
                result += ",\""+claimitem.getClaimId().getMemberNumber()+"\",\""+claimitem.getClaimId().getMemberName()+"\",";
                result += "\""+df.format(claimitem.getClaimId().getAdmissionDate())+"\",\""+claimitem.getClaimId().getPlan()+"\",\"";
                result += "\""+claimitem.getItemCode()+"\",\"";
                result += claimItemValue+"\",\""+claimApprovedValue+"\",\""+claimExcessValue +"\"";
                result += ",\""+claimApprovedValue+"\",\""+claimitem.getBenefitCheckRemarks() == null ? "" : claimitem.getBenefitCheckRemarks().replace("\n", "")+"\",\""+claimitem.getClaimId().getDiagnosis1Code();
                result += "\",\""+claimitem.getClaimId().getDiagnoseName()+"\",\""+claimitem.getClaimId().getBatchClaimId().getBatchClaimNumber()+"\",";
                result += "\""+df.format(approvedTime)+"\",\"\",\"\",\"\",\"\",\"\",\""+claimitem.getClaimId().getMemberId().getParentMember().getFirstName()+"\",\"";
                result += df.format(inputTime) +"\",\""+df.format(paymentDate) +"\",\"\",\"\",\""+df.format(paymentConfirm) +"\",\"";
                result += claimitem.getClaimId().getBankAccount()+"\",\"\",\""+claimitem.getClaimId().getBank()+"\",\"\",\"\",\"\"";
                result += "\""+claimitem.getClaimId().getBatchClaimId().getInvoiceNumber()+"\"\n";
                
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
        
    }
    public static String formatCSVComplete(Collection<Claim> collection, 
            ClaimItemService claimItemService, MemberProductService memberProductService) throws Exception{
        String data = "";

        Double claimChargeValue = 0.0;
        Double claimPaidValue = 0.0;
        Double claimExcessValue = 0.0;

        int idx = 1;
        data = "No|Policy Holder|Claim Number| CDV Number| Name of Member|Member ID| Relation| Sex| Date of Birth";
        data += "|Plan|Class|Inception Date|Maturity Date| Date of Claim Event|Discharge Date| Date of Claim Accepted";
        data += "|CDV Date| Date of Claim Paid|Facility| Facility Area| Claim Type| Limit Benefit| Remaining Benefit| Length of Stay";
        data += "|Action Type|Diagnose| Payment Type|Charge|Paid|Declined|Excess|Charge| Paid| Declined| Excess| Currency| Declined Reason| Bank| Bank Account";
        data += "\n\n";
        if (collection != null) {
            Iterator<Claim> iterator = collection.iterator();

            Collection<ClaimItem> claimItems = null;

            if (iterator != null) {
                Claim claim = null;

                while (iterator.hasNext()) {

                    claim = iterator.next();

                    if (claim != null) {

                    	String[] required = {"ClaimItem.ItemId"};
                        claimItems = claimItemService.search(null, null,
                                "claimId.claimId", claim.getClaimId(),required, 0,
                                100);

                        if (claim.getClaimChargeValue() != null) {
                            claimChargeValue += claim.getClaimChargeValue();
                        }

                        if (claim.getClaimPaidValue() != null) {
                            claimPaidValue += claim.getClaimPaidValue();
                        } else {
                            claimPaidValue += 0;
                        }

                        if (claim.getClaimExcessValue() != null) {
                            claimExcessValue += claim.getClaimExcessValue();
                        } else {
                            claimExcessValue += 0;
                        }

                        Member member = claim.getMemberId();

                        String provider = "";
                        String areaProvider = "";

                        if (claim.getProviderId() != null) {
                            provider = claim.getProviderId().getProviderName();

                            areaProvider = claim.getProviderId().getCity();
                        } else {
                            provider = claim.getProviderName();
                        }

                        String limit = "";
                        String remaining = "0";
                        String claimDeclinedValue = "0";
                        String excessClaim = "0";
                        String paidValue = "0";
                        String annualLimit = "";
                        String kelas = "";

                        String cdvNumber = "";
                        String cdvDate = "";
                        String paymentConfirmedDate = "";
                        String bank = "";
                        String bankAccount = "";
                        String payeeName = "";
                        String claimTypeStr = "";

                        if (claim.getPaymentId() != null) {
                            cdvNumber = claim.getPaymentId().getPaymentNumber();

                            if (claim.getPaymentId().getPaymentTime() != null) {
                                cdvDate = claim.getPaymentId().getPaymentTime().toString();
                            }

                            if (claim.getPaymentId().getPaymentConfirmDate() != null) {
                                paymentConfirmedDate = claim.getPaymentId().getPaymentConfirmDate().toString();
                            }
                            bank = claim.getPaymentId().getBankName();
                            bankAccount = claim.getPaymentId().getAccountNumber();
                            payeeName = claim.getPaymentId().getPayeeName();
                        }

                        if (member.getCustomerLimit() != null) {
                            limit = BigDecimal.valueOf(member.getCustomerLimit().doubleValue()).toPlainString();
                        }

                        if (member.getActualCustomerLimit() != null) {
                            double tmpremaining = claim.getRemainingMemberLimit() == null ? 0
                                    : claim.getRemainingMemberLimit().doubleValue();
                            
                            remaining = BigDecimal.valueOf(tmpremaining).toPlainString();
                            
                        }

                        if (claim.getClaimApprovedValue() != null) {
                            paidValue = BigDecimal.valueOf(claim.getClaimApprovedValue().doubleValue()).toPlainString();
                        }

                        MemberProduct memberProduct = memberProductService.getMemberActiveProduct(member.getMemberId(), claim.getCaseCategoryId().getCaseCategoryId());

                        if (memberProduct != null) {
                            Product product = memberProduct.getProductId();

                            if (product != null) {
                                kelas = product.getServiceClass();

                                if (product.getAnnualLimitValue() != null) {
                                    limit = BigDecimal.valueOf(product.getAnnualLimitValue()).toPlainString()+"";
                                }

                            }

                        }

                        if (claim.getBatchClaimId() != null){
	                        if (claim.getBatchClaimId().getBatchClaimType() != null) {
	                            claimTypeStr = claim.getBatchClaimId().getBatchClaimType().getClaimTypeCode();
	                        }
                        }
                        else {
                        	claimTypeStr = "C/EDC";
                        }
                        String batchDate = "";
                        
                        if (claim.getBatchClaimId() != null){
                        	batchDate = claim.getBatchClaimId().getBatchClaimDate() == null ? "" : claim.getBatchClaimId().getBatchClaimDate().toString();
                        }

                        String declinedReason = "";

                        data += idx + "|"
                                + member.getMemberGroupId().getGroupName()
                                + "|" + claim.getClaimNumber() + "|"
                                + cdvNumber + "|\"" + member.getFirstName();
                        data += "\"|" + member.getCustomerPolicyNumber()
                                + "|" + member.getRelationship() + "|";
                        data += member.getGender()
                                + "|"
                                + member.getBirthday()
                                + "|"
                                + claim.getCaseCategoryId().getCaseCategoryCode();
                        data += "|" + kelas + "|"
                                + member.getEffectiveDate() + "|"
                                + member.getExpireDate();
                        data += "|"
                                + claim.getAdmissionDate()
                                + "|"
                                + claim.getDischargeDate()
                                + "|"
                                + batchDate + "|"
                                + cdvDate + "|" + paymentConfirmedDate;
                        data += "|\""
                                + provider
                                + "\"|\""
                                + areaProvider
                                + "\"|"
                                + claim.getCaseCategoryId().getCaseCategoryCode() + "|"
                                + limit;
                        data += "|" + remaining + "|||"
                                + claim.getDiagnosis1Code() + "|"
                                + claimTypeStr;
                        data += "|||||" + claim.getClaimChargeValue() + "|"
                                + paidValue + "|" + claimDeclinedValue
                                + "|" + excessClaim + "||\""
                                + declinedReason + "\"|\"" + bank + "\"|\""
                                + bankAccount + "\"" + "|\"" + payeeName
                                + "\"";

                        declinedReason = "";
                        // delimiter untuk new line
                        data += "\n";
                        cdvNumber = "";
                        cdvDate = "";
                        paymentConfirmedDate = "";
                        idx += 1;
                        if (claimItems != null) {
                            Iterator<ClaimItem> ciIterator = claimItems.iterator();

                            ClaimItem ci = null;

                            String declined = "0.0";
                            String excess = "0.0";
                            String reason = "";
                            

                            if (ciIterator != null) {

                                String groupName = "";
                                int ciIndex = 0;

                                while (ciIterator.hasNext()) {
                                    ci = ciIterator.next();
                                    String claimItemApprovedValue = "0.0";

                                    if (ci.getClaimItemApprovedValue() != null) {
                                        double tmpdeclined = (ci.getClaimItemValue()
                                                - ci.getClaimItemApprovedValue());
                                        declined = BigDecimal.valueOf(tmpdeclined).toPlainString();
                                    } else {
                                    	
                                    	declined = BigDecimal.valueOf(ci.getClaimItemValue()).toPlainString();
                                        
                                    }
                                    if (ci.getMedicalRemarks() != null
                                            && !ci.getMedicalRemarks().equalsIgnoreCase("")) {
                                        reason = ci.getMedicalRemarks();
                                    }

                                    if (ci.getBenefitCheckRemarks() != null
                                            && !ci.getBenefitCheckRemarks().equals("")) {
                                        reason = ci.getBenefitCheckRemarks();
                                    }

                                    if (ciIndex == 0) {
                                        groupName = member.getMemberGroupId().getGroupName();
                                    } else {
                                        groupName = "";
                                    }

                                    if (ci.getClaimItemApprovedValue() != null) {
                                        claimItemApprovedValue = BigDecimal.valueOf(ci.getClaimItemApprovedValue().doubleValue()).toPlainString();
                                    }
                                    
                                    
                                    ciIndex += 1;

                            

                                    data += "|";
                                    data += member.getMemberGroupId().getGroupName()+ "|"+ claim.getClaimNumber()+"|"+cdvNumber+"|"+member.getFirstName()+"|"+member.getCustomerPolicyNumber()+"|";
                                    data += member.getRelationship()+"|"+member.getGender()+"|"+member.getBirthday()+"|"+claim.getCaseCategoryId().getCaseCategoryCode()+"|";
                                    data += kelas+"|"+member.getEffectiveDate()+"|"+member.getExpireDate()+"|"+claim.getAdmissionDate()+"|"+claim.getDischargeDate()+"|";
                                    data += batchDate +"|"+cdvDate+"|"+paymentConfirmedDate+"|\""+provider+"\"|\""+areaProvider+"\"|"+claim.getCaseCategoryId().getCaseCategoryCode()+"|";
                                    data += limit+"|"+remaining+"|"+"|";
                                    data += ci.getItemId().getItemName()
                                            + "|||"
                                            + ci.getClaimItemValue();
                                    data += "|" + claimItemApprovedValue
                                            + "|" + declined + "|" + excess;
                                    data += "|||||\"" + reason + "\"||\n";

                                    reason = "";
                                }
                                data += "\n";
                            }
                        }
                    }
                }
            }
        }


        return data;
    }
    public static String formatCSVTransferComplete(Collection<Claim> collection, 
            ClaimItemService claimItemService, MemberProductService memberProductService) throws Exception{
        String data = "";

        Double claimChargeValue = 0.0;
        Double claimPaidValue = 0.0;
        Double claimExcessValue = 0.0;

        int idx = 0;
        data = "No|Policy Number|Claim Number | Claim Status | Claim Process Status | Employee ID |Member ID ";
        data += " | Member Name |Plan| Product Code | Claim Type | Admission Date|Discharge Date| Diag Code | Diag Desc | Diag 2 Code | Diag 2 Desc";
        data += " | Diag 3 Code | Diag 3 Desc | Provider Code | Provider Name | ";
        data += " Provider Code Rimbursement | Provider Name Reimbursement |Benefit Code | Benefit Desc | Qty |Charge| Approved |Declined |Excess Paid | Excess Not Paid | Refund | Paid To Prov | Provider Inv No | Provider Inv Date| Submission Date | Received Date | Remarks ";
        data += "\n";
        if (collection != null) {
            Iterator<Claim> iterator = collection.iterator();

            Collection<ClaimItem> claimItems = null;

            if (iterator != null) {
                Claim claim = null;

                while (iterator.hasNext()) {

                    claim = iterator.next();

                    if (claim != null) {

                    	String[] required = {"ClaimItem.ItemId"};
                        claimItems = claimItemService.search(null, null,
                                "claimId.claimId", claim.getClaimId(),required, 0,
                                100);

                        if (claim.getClaimChargeValue() != null) {
                            claimChargeValue += claim.getClaimChargeValue();
                        }

                        if (claim.getClaimPaidValue() != null) {
                            claimPaidValue += claim.getClaimPaidValue();
                        } else {
                            claimPaidValue += 0;
                        }

                        if (claim.getClaimExcessValue() != null) {
                            claimExcessValue += claim.getClaimExcessValue();
                        } else {
                            claimExcessValue += 0;
                        }

                        Member member = claim.getMemberId();

                        String provider = "";
                        String providerCode = "";
                        String reimburseProviderCode = "";
                        String reimburseProvider = "";
                        
                        String invoiceNo = "";
                        String invoiceDate = "";
                        String submissionDate = "";
                        String receivedDate = "";

                        
                        if (claim.getProviderId() != null) {
                        	if (claim.getClaimTypeId().getClaimTypeId().intValue() == ClaimType.CASHLESS){
	                            provider = claim.getProviderId().getProviderName();
	
	                            providerCode = claim.getProviderId().getProviderCode();
                        	}
                        	else {
                        		reimburseProvider = claim.getProviderId().getProviderName();
                        		
                        		reimburseProviderCode = claim.getProviderId().getProviderCode();
                        	}
                        } else {
                        	reimburseProvider = claim.getProviderName();
                        }

                        String limit = "";
                        String remaining = "0";
                        String claimDeclinedValue = "0";
                        String excessClaim = "0";
                        String paidValue = "0";
                        String annualLimit = "";
                        String kelas = "";

                        String cdvNumber = "";
                        String cdvDate = "";
                        String paymentConfirmedDate = "";
                        String bank = "";
                        String bankAccount = "";
                        String payeeName = "";
                        String claimTypeStr = "";
                        
                        String diagCode = "";
                        String diagDesc = "";
                        
                        if (claim.getDiagnosisId() != null){
                        	diagCode = claim.getDiagnosis1Code();
                        	diagDesc = claim.getDiagnoseName();
                        }
                        
                        String diag2Code = "";
                        String diag2Desc = "";
                        
                        if (claim.getDiagnosis2Id() != null){
                        	diag2Code = claim.getDiagnosis2Code();
                        }
                        
                        String diag3Code = "";
                        String diag3Desc = "";
                        
                        if (claim.getDiagnosis3Id() != null){
                        	diag3Code = claim.getDiagnosis3Code();
                        }

                        if (claim.getPaymentId() != null) {
                            cdvNumber = claim.getPaymentId().getPaymentNumber();

                            if (claim.getPaymentId().getPaymentTime() != null) {
                                cdvDate = claim.getPaymentId().getPaymentTime().toString();
                                submissionDate = claim.getPaymentId().getPaymentTime().toString();
                            }

                            if (claim.getPaymentId().getPaymentConfirmDate() != null) {
                                paymentConfirmedDate = claim.getPaymentId().getPaymentConfirmDate().toString();
                            }
                            bank = claim.getPaymentId().getBankName();
                            bankAccount = claim.getPaymentId().getAccountNumber();
                            payeeName = claim.getPaymentId().getPayeeName();
                        }

                        if (member.getCustomerLimit() != null) {
                            limit = BigDecimal.valueOf(member.getCustomerLimit().doubleValue()).toPlainString();
                        }

                        if (member.getActualCustomerLimit() != null) {
                            double tmpremaining = claim.getRemainingMemberLimit() == null ? 0
                                    : claim.getRemainingMemberLimit().doubleValue();
                            
                            remaining = BigDecimal.valueOf(tmpremaining).toPlainString();
                            
                        }

                        if (claim.getClaimApprovedValue() != null) {
                            paidValue = BigDecimal.valueOf(claim.getClaimApprovedValue().doubleValue()).toPlainString();
                        }

                        MemberProduct memberProduct = memberProductService.getMemberActiveProduct(member.getMemberId(), claim.getCaseCategoryId().getCaseCategoryId());

                        if (memberProduct != null) {
                            Product product = memberProduct.getProductId();

                            if (product != null) {
                                kelas = product.getServiceClass();

                                if (product.getAnnualLimitValue() != null) {
                                    limit = BigDecimal.valueOf(product.getAnnualLimitValue()).toPlainString()+"";
                                }

                            }

                        }

                        String cStatus = "-";
                        String claimProcessStatus = "-";
                        String batchDate = "";
                        
                        if (claim.getClaimStatus() != null){
                        	int claimStatus = claim.getClaimStatus().getCaseStatusId();
                        	
                        	if (claimStatus == 1){
                        		cStatus = "OPEN";
                        	}
                        	else if (claimStatus == 2){
                        		cStatus = "PENDING DOCUMENT";
                        	}
                        	else if (claimStatus == 3){
                        		cStatus = "VERIFIED";
                        	}
                        	else if (claimStatus == 4){
                        		cStatus = "REJECTED";
                        	}
                        	else if (claimStatus == 5){
                        		cStatus = "CLOSED";
                        	}
                        	else if (claimStatus == 6){
                        		cStatus = "PAID";
                        	}
                        	else if (claimStatus == 7){
                        		cStatus = "PENDING INVESTIGATION";
                        	}
                        	else if (claimStatus == 8){
                        		cStatus = "CHECKED";
                        	}
                        	else if (claimStatus == 9){
                        		cStatus = "APPROVED";
                        	}else if (claimStatus == 10){
                        		cStatus = "PENDING";
                        	}else if (claimStatus == 13){
                        		cStatus = "CDV ISSUED";
                        	}
                        	else if (claimStatus == -1){
                        		cStatus = "VOID";
                        	}
                        }
                        if (claim.getSecondaryStatus() != null){
                        	int claimStatus = claim.getClaimStatus().getCaseStatusId();
                        	
                        	if (claimStatus == 1){
                        		claimProcessStatus = "OPEN";
                        	}
                        	else if (claimStatus == 2){
                        		claimProcessStatus = "PENDING DOCUMENT";
                        	}
                        	else if (claimStatus == 3){
                        		claimProcessStatus = "VERIFIED";
                        	}
                        	else if (claimStatus == 4){
                        		claimProcessStatus = "REJECTED";
                        	}
                        	else if (claimStatus == 5){
                        		claimProcessStatus = "CLOSED";
                        	}
                        	else if (claimStatus == 6){
                        		claimProcessStatus = "PAID";
                        	}
                        	else if (claimStatus == 7){
                        		claimProcessStatus = "PENDING INVESTIGATION";
                        	}
                        	else if (claimStatus == 8){
                        		claimProcessStatus = "CHECKED";
                        	}
                        	else if (claimStatus == 9){
                        		claimProcessStatus = "APPROVED";
                        	}else if (claimStatus == 10){
                        		claimProcessStatus = "PENDING";
                        	}else if (claimStatus == 13){
                        		claimProcessStatus = "CDV ISSUED";
                        	}
                        	else if (claimStatus == -1){
                        		claimProcessStatus = "VOID";
                        	}
                        	
                        }
                        if (claim.getBatchClaimId() != null){
	                        if (claim.getClaimTypeId() != null) {
	                            claimTypeStr = claim.getClaimTypeId().getClaimTypeCode();
	                        }
	                        batchDate = claim.getBatchClaimId().getBatchClaimDate() == null ? "" : claim.getBatchClaimId().getBatchClaimDate().toString();
	                        invoiceNo = claim.getBatchClaimId().getInvoiceNumber();
	                        invoiceDate = claim.getBatchClaimId().getInvoiceDate() == null ? "" : claim.getBatchClaimId().getInvoiceDate().toString();
                        }
                        else {
                        	claimTypeStr = "C/EDC";
                        }
                        

                        String declinedReason = "";
                        

                        /**
                        data += idx + "|"
                                + member.getCurrentPolicyNumber()
                                + "|" + claim.getClaimNumber() + "|"
                                + cStatus + "|" + claimProcessStatus ;
                        data += "|" + member.getParentNumber()
                                + "|" + member.getCustomerPolicyNumber() + "|";
                        data += claim.getCaseCategoryId().getCaseCategoryCode()
                                + "|"+claimTypeStr+" | "
                                + claim.getAdmissionDate()
                                + "|"
                                + claim.getDischargeDate();
                        data += "|" + diagCode + "|"
                                + diagDesc + "|"
                                + diag2Code;
                        data += "|"
                                + diag2Desc
                                + "|"
                                + diag3Code
                                + "|"
                                + diag3Desc ;
                        data += "|"
                                + providerCode
                                + "|"
                                + provider
                                + "|"
                                + reimburseProviderCode + "|"
                                + reimburseProvider;
                        data += "|  |  |  |  |  |  |  |  | | |";
                        data +=  invoiceNo + "|" + invoiceDate + "|"+ submissionDate +"|" + batchDate + "|\"" + declinedReason + "\""; 

                        declinedReason = "";
                        */
                        // delimiter untuk new line
                        
                        //data += "\n";
                        
                        /**
                         * jadi tidak menggunakan claim header .. langsung per detail saja
                         */
                        
                        cdvNumber = "";
                        cdvDate = "";
                        paymentConfirmedDate = "";
                        
                        idx += 1;
                        if (claimItems != null) {
                            Iterator<ClaimItem> ciIterator = claimItems.iterator();

                            ClaimItem ci = null;

                            if (ciIterator != null) {

                                String groupName = "";
                                int ciIndex = 0;

                                while (ciIterator.hasNext()) {
                                    ci = ciIterator.next();
                                    String claimItemApprovedValue = "0.0";
                                    String itemAmount = "0.0";
                                    String declined = "0.0";
                                    String excessNotPaid = "0.0";
                                    String reason = "";
                                    String prePaidExcess = "0.0";
                                    String refund = "0.0";
                                    String paidToProvider = "0.0";
                                    String claimItemValue = "0.0";
                                    
                                    
                                    String benefitCode = "";
                                    String benefitDesc = "";
                                    
                                    if (ci.getItemId() != null) { 
                                    	// default code, jika client punya mapping maka digunakan client mapping
                                    	benefitCode = ci.getItemId().getItemCode();
                                    	benefitDesc = ci.getItemId().getItemName();
                                    }

                                    if (ci.getClaimItemApprovedValue() != null) {
                                        double tmpdeclined = (ci.getClaimItemValue()
                                                - ci.getClaimItemApprovedValue());
                                        declined = BigDecimal.valueOf(tmpdeclined).toPlainString();
                                    } else {
                                    	
                                    	declined = BigDecimal.valueOf(ci.getClaimItemValue()).toPlainString();
                                        
                                    }
                                    if (ci.getMedicalRemarks() != null
                                            && !ci.getMedicalRemarks().equalsIgnoreCase("")) {
                                        reason = ci.getMedicalRemarks();
                                    }

                                    if (ci.getBenefitCheckRemarks() != null
                                            && !ci.getBenefitCheckRemarks().equals("")) {
                                        reason = ci.getBenefitCheckRemarks();
                                    }

                                    reason = reason == null ? "" : reason.replace("\n", ";");
                                    reason = reason == null ? "" : reason.replace("|", ";");
                                    
                                    if (ciIndex == 0) {
                                        groupName = member.getGroupName();
                                    } else {
                                        groupName = "";
                                    }

                                    if (ci.getClaimItemAmount() != null){
                                    	itemAmount = Converter.getMoney(ci.getClaimItemAmount());
                                    }
                                    if (ci.getClaimItemApprovedValue() != null) {
                                        claimItemApprovedValue = BigDecimal.valueOf(ci.getClaimItemApprovedValue().doubleValue()).toPlainString();
                                    }
                                    
                                    if (ci.getClaimItemValue() != null){
                                    	claimItemValue = BigDecimal.valueOf(ci.getClaimItemValue()).toPlainString();
                                    }
                                    if (ci.getPrePaidExcess() != null) {
                                    	prePaidExcess = BigDecimal.valueOf(ci.getPrePaidExcess()).toPlainString();
                                    }
                                    
                                    ciIndex += 1;

                                    data += idx + "|"
		                                    + member.getCurrentPolicyNumber()
		                                    + "|" + claim.getClaimNumber() + "|"
		                                    + cStatus + "|" + claimProcessStatus ;
		                            data += "|" + member.getParentNumber()
		                                    + "|" + member.getCustomerPolicyNumber() +"|" + member.getFirstName()+ "|";
		                            data += claim.getCaseCategoryId().getCaseCategoryCode() + "|" + claim.getProductClass()
		                                    + "| "+claimTypeStr+" | " + claim.getAdmissionDate() + "| "  + claim.getDischargeDate();
		                            data += "|" +diagCode+  "|" + diagDesc + "|" + diag2Code;
		                            data += "| " +diag2Desc+ " | "+diag3Code+" | " +diag3Desc+ "| ";
		                            data += providerCode + "| " +provider+ "| " +reimburseProviderCode + "| " +reimburseProvider+ "| " ;
		                            data +=  benefitCode + "|" + benefitDesc + "|"+ itemAmount +"|" + claimItemValue  +"|" 
		                            	 + claimItemApprovedValue +  "|"  + declined +  "|" + prePaidExcess
		                                 + "|"  +  excessNotPaid+  "|" + refund;
		                            data += " | "+paidToProvider+" | "+invoiceNo+" | "+invoiceDate+"| "+submissionDate+"| "+batchDate+"|\"" + reason + "\""; 

		                            data += "\n";
		                            
                                    reason = "";
                                }
                         
                            }
                        }
                    }
                }
            }
        }


        return data;
    }
}
