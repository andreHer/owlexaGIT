
package com.ametis.cms.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.StringTokenizer;
import org.joda.time.DateTime;

public class TimeUtils {
	public static void main (String[] args){
		Calendar res = parseTime("2006-05-18","20:00");

	}
	public static Timestamp getTimestamp (java.util.Date date , String hour, String minute){
		Timestamp result = null;
		
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);		
			
			cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
			cal.set(Calendar.MINUTE, Integer.valueOf(minute));
			
			result = new Timestamp (cal.getTimeInMillis());
		}
		catch (Exception e){
			
		}
		
		return result;
	}
        public static int getTotalDay (Date end, Date start){
            
            return (int)( (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24));
        }

	public static Date parseDate (String date){
		Date res = null;

		try {
			if (date != null ){
				StringTokenizer token = new StringTokenizer(date, "-");
				Calendar res1 = Calendar.getInstance();

				int year = Integer.parseInt(token.nextToken());
				int month = Integer.parseInt(token.nextToken());
				int day = Integer.parseInt(token.nextToken());



				res1.set(Calendar.YEAR, year);
				res1.set(Calendar.MONTH, month-1);
				res1.set(Calendar.DAY_OF_MONTH, day);

				res = new Date(res1.getTimeInMillis());

			}
		}
		catch (Exception e){

		}



		return res;
	}
	public static Timestamp  parseTimestamp (String date, String time){
		Timestamp result = null;

		try {
			if (date != null && time != null){
				Calendar cal = parseTime (date, time);

				result = new Timestamp(cal.getTimeInMillis());
			}
		}
		catch (Exception e) {

		}
		return result;
	}
	public static Calendar  parseTime (String date, String time){

		Calendar res = null;

		try {
			if (date != null && time != null){
				StringTokenizer token = new StringTokenizer(date, "-");
				res = Calendar.getInstance();
				int year = Integer.parseInt(token.nextToken());
				int month = Integer.parseInt(token.nextToken());
				int day = Integer.parseInt(token.nextToken());

				StringTokenizer timeToken = new StringTokenizer (time, ":");
				int hour = Integer.parseInt(timeToken.nextToken());
				int minute = Integer.parseInt(timeToken.nextToken());

				res.set(Calendar.YEAR, year);
				res.set(Calendar.MONTH, month);
				res.set(Calendar.DAY_OF_MONTH, day);
				res.set(Calendar.HOUR_OF_DAY, hour);
				res.set(Calendar.MINUTE, minute);



			}
		}
		catch (Exception e){

		}



		return res;
	}
}
