
package com.ametis.cms.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;

public class WebUtil {
	public static String getAttributeString (HttpServletRequest request, String paramName, String defaultVal){
		return request.getAttribute(paramName) == null ? defaultVal : request.getAttribute(paramName).toString();
	}
	public static Integer getAttributeInteger (HttpServletRequest request, String paramName, int defaultVal){
		String res = getAttributeString(request,paramName,""+defaultVal);

		if (StringUtils.isNumeric(res)&&!res.trim().equals("")){
			return new Integer(res);
		}else{
			return Integer.valueOf(0);
		}
	}
	public static Long getAttributeLong (HttpServletRequest request, String paramName, int defaultVal){
		String res = getAttributeString(request,paramName,""+defaultVal);

		if (StringUtils.isNumeric(res)&&!res.trim().equals("")){
			return new Long(res);
		}else{
			return Long.valueOf(0);
		}
	}

	public static Calendar getNextBusinessDay (Calendar cal, int numBusinessDays){
		
		
		int numNonBusinessDays = 0;

		for(int i = 0; i < numBusinessDays; i++) {
		    cal.add(Calendar.DATE, 1);

		    /*
		       It's a Canadian/American custom to get the Monday (sometimes Friday) off
		       when a holiday falls on a weekend.
		    */

		    if(cal.get(Calendar.DAY_OF_WEEK) == 1 ||
		       cal.get(Calendar.DAY_OF_WEEK) == 7) {
		      numNonBusinessDays++;
		    }
		  }

		  if(numNonBusinessDays > 0) {
		    cal.add(Calendar.DATE, numNonBusinessDays);
		  }

		  return cal;
		
	}
	
	public static String getParameterString (HttpServletRequest request, String paramName, String defaultVal){
		//Modified by  aju on 20150220, for trimming whitespaces fufufu >:)
		//return request.getParameter(paramName) == null ? defaultVal : request.getParameter(paramName);
		String tmpString = request.getParameter(paramName) == null ? defaultVal : request.getParameter(paramName);
		
		tmpString = tmpString.replaceAll("^\\s+", "");
		tmpString = tmpString.replaceAll("\\s+$","");
		
		return tmpString;
	}
	
	public static String[] getParameterStringArray (HttpServletRequest request, String paramName){
		return request.getParameter(paramName) == null ? new String[]{} : request.getParameterValues(paramName);
	}
	public static Integer getParameterInteger (HttpServletRequest request, String paramName){

		Integer result = null;

		String res = getParameterString(request, paramName, "");

//		remove by Aulia R. 3-7-2015, karena nilai -1 tidak terbaca di StringUtils.isNumeric()
//		if (StringUtils.isNumeric(res)&&!res.trim().equals("")){
//			result = new Integer(res);
//		}
		if (NumberUtils.isNumber(res)&&!res.trim().equals("")){
			result = new Integer(res);
		}

		return result;
	}
	public static Double getParameterDouble (HttpServletRequest request, String paramName){

		Double result = null;

		String res = getParameterString(request, paramName, "");

		if (!res.trim().equals("")){
			result = new Double(res);
		}

		return result;
	}
	public static Long getParameterLong (HttpServletRequest request, String paramName){

		Long result = null;

		String res = getParameterString(request, paramName, "");

		if (StringUtils.isNumeric(res)&&!res.trim().equals("")){
			result = new Long(res);
		}

		return result;
	}

	/*
	public static PaginationUtil setPaginationProperties (int offset, int index,int total){


		int last_idx = total / offset ;

		int next_idx = last_idx;
		int prev_idx = 0;

		if (index != 0){
			prev_idx = index - 1;
		}

		if (index < last_idx){
			next_idx = index + 1;
		}

		int lower_bound = offset * index + 1;
		int upper_bound = offset * (index+1);

		PaginationUtil paginationUtil = new PaginationUtil();
		paginationUtil.setLastIndex(new Integer( last_idx));
		paginationUtil.setLowerBound(new Integer(lower_bound));
		paginationUtil.setNext(new Integer(next_idx));
		paginationUtil.setPrevious(new Integer( prev_idx));
		paginationUtil.setUpperBound(new Integer(upper_bound));
		paginationUtil.setTotalData(new Integer(total));





	/*	model.addObject("page_index", new Integer(index));
		model.addObject("total_data", new Integer(total));
		model.addObject("lower_bound", new Integer (lower_bound));
		model.addObject("upper_bound", new Integer(upper_bound));

		model.addObject("previous_index", new Integer (prev_idx));
		model.addObject("next_index", new Integer(next_idx));
		model.addObject("start_index", Integer.valueOf(0));
		model.addObject("last_index", new Integer(last_idx));*/

/*
		return paginationUtil;
	}
*/

	public static Timestamp getParameterTimeStamp(HttpServletRequest request, String dateName,String timeName){
		Timestamp result = null;
		String date = getParameterString(request, dateName, "1970-01-01");
		String time = getParameterString(request, timeName, "00:00");
		result = TimeUtils.parseTimestamp(date,time);
		return result;
	}
	public static Date getParameterDate(HttpServletRequest request, String dateName){
		Date result = null;
		String date = getParameterString(request, dateName, "1970-01-01");
		result = TimeUtils.parseDate(date);
		return result;
	}

}