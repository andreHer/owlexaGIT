package com.ametis.cms.util.parser.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;

public class NextCardNumberValidator {
	
	public static String isValid (String input,String clientCode,String policyNumber,String actionType, MemberService memberService, PolicyService policyService){
		String result = "";
		
		if (input != null && !input.trim().equalsIgnoreCase("")){
			try {
				Member member = memberService.getMemberByCardNumber(input);
				
				if (member == null){
				
					if (clientCode != null && policyNumber != null){
					
						Policy policy = policyService.getActivePolicyByNumber(policyNumber, clientCode);
						
						if (policy != null){
							char preFixChar = input.charAt(0);
							String yearCode = input.substring(4,6);
							String subClientCode = input.substring(6,9);
							DateTime effectiveDate = new DateTime(policy.getEffectiveDate().getTime());
							String leftYear = effectiveDate.getYear() % 1000 + "";
							
							if (subClientCode.equalsIgnoreCase(clientCode)){
							
								if (policy.getPolicyType().intValue() == Policy.INDEMNITY_POLICY){
									if (preFixChar == '1'){
										if (leftYear.equalsIgnoreCase(yearCode)){
											result = "OK";
										}
										else {
											result = "Kode tahun pada swipe card (" +yearCode + ") tidak sesuai dengan tahun effective  " + leftYear;
										}
									}
									else {
										result = "Prefix Swipe Card Number INDEMNITY harus dimulai dengan angka 1";
									}
								}
								else if (policy.getPolicyType().intValue() == Policy.MANAGED_CARE_POLICY){
									if (preFixChar == '2'){
										if (leftYear.equalsIgnoreCase(yearCode)){
											result = "OK";
										}
										else {
											result = "Kode tahun pada swipe card (" +yearCode + ") tidak sesuai dengan tahun effective  " + leftYear;
										}
									}
									else {
										result = "Prefix Swipe Card Number MANAGED CARE harus dimulai dengan angka 2";
									}
								}
							}
							else {
								result = "Client Code pada Swipe Card ("+subClientCode+") tidak sesuai dengan alokasi Client";
							}
						}					
					}
					else {
						result = "Jika Field Swipe Card Number diisi, Mohon Isi Client Code dan Policy Number ";
					}
				}
				else if (member != null && actionType.equalsIgnoreCase("ADDITION")){
					result = "Peserta dengan Nomor Swipe Card " + input + " Telah Terdaftar di Database";
				}
			}
			catch (Exception e){
				result = e.getMessage();
				e.printStackTrace();
			}
		}
		else {
			result = "OK";
		}
		
		return result;
	}

}
