package com.ametis.cms.util.parser.validator;

import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;

public class NextGroupCodeValidator {
	public static String isValid (String input,MemberGroupService memberGroupService){
		String result = "";

		if(input !=null && !input.equalsIgnoreCase("")){


			if (input.trim().length() > 10){
				result = "Jumlah Maksimal Karakter Next Group Code adalah 10.";
				

			}
			else {
				
				try {
					MemberGroup group=memberGroupService.getMemberGroupByCode(input);
					if(group!=null){
						result="OK";
					}
					else{
						result="Group Code tidak ada di tabel member group ";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		else {
			result = "Next Group Code tidak boleh kosong";
		}

		return result;
	}

}
