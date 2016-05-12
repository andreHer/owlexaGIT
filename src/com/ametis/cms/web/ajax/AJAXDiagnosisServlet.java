package com.ametis.cms.web.ajax;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.util.WebUtil;

public class AJAXDiagnosisServlet implements Controller{
	private DiagnosisService diagnosisService;

	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}

	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}
	
	private String generateXMLResult (Collection collection){
		String result = "<response>";
		
		if (collection != null){
			
			Iterator iterator = collection.iterator();
			Diagnosis diagnosis = null;
			String sub = "";
			
			while (iterator.hasNext()){
				
				diagnosis = (Diagnosis) iterator.next();
				
				sub = "<diagnosis>";
				sub += "<id>"+diagnosis.getDiagnosisId().toString()+"</id>";
				sub += "<code>"+diagnosis.getDiagnosisCode()+"</code>";
				sub += "<name>"+diagnosis.getDiagnosisName()+"</name>";				
				sub += "</diagnosis>";
				
				result += sub;
			}
		}
		
		result += "</response>";
		return result;
	}
	private void writeToClient (String xml, HttpServletResponse response){
		try {
			
			if (response != null){
				PrintWriter out = response.getWriter();
				System.out.println(xml);
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				
				out.write(xml);
				out.close();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	
		String navigation = WebUtil.getParameterString(request,"navigation","");
		String keyword = WebUtil.getParameterString(request,"keyword","");
		
		String result = "";
		Collection list = null;
		String[] vLikeP = null;
		Object[] vLikeQ = {keyword};
		
		
		
		if (navigation.equalsIgnoreCase("searchbycode")){
			vLikeP = new String[]{"diagnosisCode"};
			try {
				list = diagnosisService.search(vLikeP,vLikeQ,null,null,true,"diagnosisCode",0,10);
			}
			catch (Exception e){
				
			}
		}
		else if (navigation.equalsIgnoreCase("searchbyname")){
			vLikeP = new String[]{"diagnosisName"};
			try {
				list = diagnosisService.search(vLikeP,vLikeQ,null,null,true,"diagnosisName",0,10);
			}
			catch (Exception e){
				
			}
		}
		
		result = generateXMLResult(list);
		System.out.println("GENERATED XML : " + result);
		writeToClient(result,response);
		
		return null;
	}
}