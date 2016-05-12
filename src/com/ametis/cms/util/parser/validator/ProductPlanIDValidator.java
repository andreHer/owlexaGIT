package com.ametis.cms.util.parser.validator;

import java.util.StringTokenizer;

import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProductService;

public class ProductPlanIDValidator {

	public static String isValid(String input, String policyNumber,String clientCode, ProductService productService,PolicyService policyService) {
		String result = "";

		Policy policy = null;
		
		if (policyNumber != null && clientCode != null){
			try {
				policy = policyService.getActivePolicyByNumber(policyNumber, clientCode);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		if (input != null && !input.equalsIgnoreCase("")) {
			if (input.trim().length() > 200) {
				result = "Jumlah Maksimal Field Plan ID adalah 200 Karakter.";
			} else {
				StringTokenizer tokenizer = new StringTokenizer(input);
				
				if (tokenizer.countTokens() > 1){
					result = "OK";
					while (tokenizer.hasMoreTokens()){
						String tokenPlan = tokenizer.nextToken();
						
						try {
							Product product = productService.getActiveProduct(tokenPlan, policy.getPolicyId());
							if (product == null){
								result = "Plan ID belum terdaftar di database";
								break;
							}
						}
						catch (Exception e){
							e.printStackTrace();
						}
					}
				}
				else {					
					try {
						if (policy != null){
							Product product = productService.getActiveProduct(input,policy.getPolicyId());
							
							if (product != null){
								result = "OK";
							}
							else {
								result = "Plan ID belum terdaftar di dalam database";
							}
						}
						else {
							result = "Policy tidak ditemukan di dalam database";
						}
					}
					catch (Exception e){
						e.printStackTrace();
					}
				}
			}

		} else {
			result = "Product/Plan ID tidak boleh kosong";
		}

		return result;
	}

}
