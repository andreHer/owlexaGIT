
package com.ametis.cms.util.servlet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TableRenderingServlet {

	public static Map seti18N(HttpServletRequest request,HttpServletResponse response){
		// language
		String lang = (String)request.getParameter("lang");
		if(lang==null||lang.trim().equals("")){
			lang = (String)request.getSession().getAttribute("lang");
		}else{
			request.getSession().setAttribute("lang",lang);
		}
		// theme
		String theme = (String)request.getParameter("theme");
		if(theme==null||theme.trim().equals("")){
			theme = (String)request.getSession().getAttribute("theme");
		}else{
			request.getSession().setAttribute("theme",theme);
		}
		// 	end

		Map mp = new HashMap();
		if(lang!=null&&lang.equals("en")){
			mp.put("CREATE","Create");
			mp.put("SEARCH","Search");
			mp.put("BASED_ON","Based On");
			mp.put("LIST_OF","List of");
			mp.put("PAGE","Page");
			mp.put("FROM","From");
		}else{
			mp.put("CREATE","Bikin");
			mp.put("SEARCH","Cari");
			mp.put("BASED_ON","Berdasarkan");
			mp.put("LIST_OF","Daftar Tabel");
			mp.put("PAGE","Halaman");
			mp.put("FROM","Dari");
		}
		return mp;


	}
}
