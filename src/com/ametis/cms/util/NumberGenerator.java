package com.ametis.cms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ametis.cms.datamodel.CaseCategory;

public class NumberGenerator {
	private Connection connection;
	
	public String generateInpatientNumber(int year, int month){
	String result = "";
		
		String query = "SELECT count(*) as result FROM tb_case where case_start_time LIKE '%?%' AND case_category_id = ?";
		
		try {
			String monthStr = "";
			if (month < 10){
				monthStr = "0"+month; 
			}
			else {
				monthStr = ""+month;
			}
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,year+"-"+monthStr);
			ps.setInt(2, CaseCategory.INPATIENT);
			
			ResultSet rs = ps.executeQuery();
			int total = 0;
			
			if (rs.first()){
				total = rs.getInt("result") + 1;				
			}
			else {
				total = 1;
			}
			
			result = year+"/"+monthStr+"/INP/"+"/"+formatNumber(total+"",4);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		return result ;
	}
	private String formatNumber (String number, int digit){
		String result = "";
		
		if (number != null){
			int len = number.length();
			
			if (len < digit){
				int diff = digit - len;
				String padding = "";
				
				for (int i = 0; i < diff; i++){
					padding += "0";
				}
				
				result = padding+number;			
			}
			else {
				return number;
			}
		}		
		return result;
	}
	public String generateBatchNumber(int year, int month, String clientCode){
		
		String result = "";
		
		String query = "SELECT count(*) as result FROM batch_claim where batch_claim_date LIKE '%?%'";
		
		try {
			String monthStr = "";
			if (month < 10){
				monthStr = "0"+month; 
			}
			else {
				monthStr = ""+month;
			}
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,year+"-"+monthStr);
			
			ResultSet rs = ps.executeQuery();
			int total = 1;
			
			if (rs.first()){
				total = rs.getInt("result") + 1;				
			}
			
			result = year+"/"+monthStr+"/BATCH/"+clientCode+"/"+formatNumber(total+"",4);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		return result ;
		
	}
	public String generateFirstCallNumber(int year, int month){
	String result = "";
		
		String query = "SELECT count(*) as result FROM first_call where call_start_time LIKE '%?%'";
		
		try {
			String monthStr = "";
			if (month < 10){
				monthStr = "0"+month; 
			}
			else {
				monthStr = ""+month;
			}
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,year+"-"+monthStr);
			
			ResultSet rs = ps.executeQuery();
			int total = 1;
			
			if (rs.first()){
				total = rs.getInt("result") + 1;				
			}
			
			result = year+"/"+monthStr+"/FC/"+formatNumber(total+"",5);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		return result ;
	}
	public String generateDentalNumber (int year, int month){
		String result = "";
		
		String query = "SELECT count(*) as result FROM tb_case where case_start_time LIKE '%?%' AND case_category_id = ?";
		
		try {
			String monthStr = "";
			if (month < 10){
				monthStr = "0"+month; 
			}
			else {
				monthStr = ""+month;
			}
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,year+"-"+monthStr);
			ps.setInt(2, CaseCategory.DENTAL);
			
			ResultSet rs = ps.executeQuery();
			int total = 0;
			
			if (rs.first()){
				total = rs.getInt("result") + 1;				
			}
			else {
				total = 1;
			}
			
			result = year+"/"+monthStr+"/DENT/"+"/"+formatNumber(total+"",4);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		return result ;
	}
	public String generateMaternityNumber (int year, int month){
	String result = "";
		
		String query = "SELECT count(*) as result FROM tb_case where case_start_time LIKE '%?%' AND case_category_id = ?";
		
		try {
			String monthStr = "";
			if (month < 10){
				monthStr = "0"+month; 
			}
			else {
				monthStr = ""+month;
			}
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,year+"-"+monthStr);
			ps.setInt(2, CaseCategory.MATERNITY);
			
			ResultSet rs = ps.executeQuery();
			int total = 0;
			
			if (rs.first()){
				total = rs.getInt("result") + 1;				
			}
			else {
				total = 1;
			}
			
			result = year+"/"+monthStr+"/MAT/"+"/"+formatNumber(total+"",4);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		return result ;
	}
	public String generateOutpatientNumber (int year, int month){
	String result = "";
		
		String query = "SELECT count(*) as result FROM tb_case where case_start_time LIKE '%?%' AND case_category_id = ?";
		
		try {
			String monthStr = "";
			if (month < 10){
				monthStr = "0"+month; 
			}
			else {
				monthStr = ""+month;
			}
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,year+"-"+monthStr);
			ps.setInt(2, CaseCategory.OUTPATIENT);
			
			ResultSet rs = ps.executeQuery();
			int total = 0;
			
			if (rs.first()){
				total = rs.getInt("result") + 1;				
			}
			else {
				total = 1;
			}
			
			result = year+"/"+monthStr+"/OUT/"+"/"+formatNumber(total+"",4);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		return result ;
	}
	public String generateOpticalNumber (int year, int month){
	String result = "";
		
		String query = "SELECT count(*) as result FROM tb_case where case_start_time LIKE '%?%' AND case_category_id = ?";
		
		try {
			String monthStr = "";
			if (month < 10){
				monthStr = "0"+month; 
			}
			else {
				monthStr = ""+month;
			}
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,year+"-"+monthStr);
			ps.setInt(2, CaseCategory.OPTICAL);
			
			ResultSet rs = ps.executeQuery();
			int total = 0;
			
			if (rs.first()){
				total = rs.getInt("result") + 1;				
			}
			else {
				total = 1;
			}
			
			result = year+"/"+monthStr+"/OPT/"+"/"+formatNumber(total+"",4);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		return result ;
	}
	public String generateClaimPaymentNumber (int year, int month){
	String result = "";
		
		String query = "SELECT count(*) as result FROM payment where payment_time LIKE '%?%' ";
		
		try {
			String monthStr = "";
			if (month < 10){
				monthStr = "0"+month; 
			}
			else {
				monthStr = ""+month;
			}
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,year+"-"+monthStr);
			
			
			ResultSet rs = ps.executeQuery();
			int total = 0;
			
			if (rs.first()){
				total = rs.getInt("result") + 1;				
			}
			else {
				total = 1;
			}
			
			result = year+"/"+monthStr+"/PAYMENT/"+"/"+formatNumber(total+"",4);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		return result ;
	}
	public String generateClaimNumber (int year, int month){
		
		String result = "";
		
		String query = "SELECT count(*) as result FROM tb_case where case_start_time LIKE '%?%' AND case_category_id = ?";
		
		try {
			String monthStr = "";
			if (month < 10){
				monthStr = "0"+month; 
			}
			else {
				monthStr = ""+month;
			}
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,year+"-"+monthStr);
			ps.setInt(2, CaseCategory.DENTAL);
			
			ResultSet rs = ps.executeQuery();
			int total = 0;
			
			if (rs.first()){
				total = rs.getInt("result") + 1;				
			}
			else {
				total = 1;
			}
			
			result = year+"/"+monthStr+"/DENT/"+"/"+formatNumber(total+"",4);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		return result ;
	}
}
