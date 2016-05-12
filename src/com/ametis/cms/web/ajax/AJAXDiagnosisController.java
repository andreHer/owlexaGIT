package com.ametis.cms.web.ajax;

import java.util.Collection;
import java.util.Iterator;

import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.service.DiagnosisService;

public class AJAXDiagnosisController {
	private DiagnosisService diagnosisService;

	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}

	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}
	
	
	public String[] searchDiagnosisByCode (String searchtext){
		String[] res = null;
		
		try {
			String[] vLikeP = {"diagnosisCode"};
			Object[] vLikeQ = {searchtext};
			Collection result = diagnosisService.search(vLikeP,vLikeQ,null,null,true,"diagnosisCode",0,6);
			
			if (result != null){
				Iterator iterator = result.iterator();
				
				if (iterator != null){
					String resCode = "<ul>";
					String resName = "<ul>";
					Diagnosis diag = null;
					
					while (iterator.hasNext()){
						diag = (Diagnosis) iterator.next();
						
						resCode += "<li>"+diag.getDiagnosisCode()+"</li>";
						resName += "<li>"+diag.getDiagnosisName()+"</li>";
					}
					resCode += "</ul>";
					resName += "</ul>";
				
					res = new String[2];
					res[0] = resCode;
					res[1] = resName;
					System.out.print(res);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return res;
	}
	public String searchDiagnosisByName (String searchtext){
	String res = null;
		
		try {
			String[] vLikeP = {"diagnosisName"};
			Object[] vLikeQ = {searchtext};
			Collection result = diagnosisService.search(vLikeP,vLikeQ,null,null,true,"diagnosisCode",0,6);
			
			if (result != null){
				Iterator iterator = result.iterator();
				
				if (iterator != null){
					res = "<ul>";
					Diagnosis diag = null;
					
					while (iterator.hasNext()){
						diag = (Diagnosis) iterator.next();
						
						res += "<li>"+diag.getDiagnosisCode()+"</li>";
						res += "<li>"+diag.getDiagnosisName()+"</li>";
					}
					res += "</ul>";
					
					System.out.print(res);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return res;
	}
}
