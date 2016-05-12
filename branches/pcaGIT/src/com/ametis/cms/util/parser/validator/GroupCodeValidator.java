package com.ametis.cms.util.parser.validator;

import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.service.MemberGroupService;

public class GroupCodeValidator {

	public static String isValid(String input, MemberGroupService memberGroupService){
		String result = "Group Code merupakan field wajib, mohon diisi dengan kode group yang terdaftar. Contoh: G0001";
		
		try {
			if (input != null){
				if (!input.trim().equalsIgnoreCase("")){
					
					if (input.trim().length() > 50){
						result = "Panjang maksimal field Group Code adalah 50 karakter";
					}
					else {
						if (memberGroupService != null){
							MemberGroup memberGroup = memberGroupService.getMemberGroupByCode(input.trim());
							
							if (memberGroup != null){
								result = "OK";
							}
							else {
								result = "Group dengan kode tersebut tidak terdaftar di database";
							}
						}
						else {
							result = "Null Pointer Exception";
						}
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
}
