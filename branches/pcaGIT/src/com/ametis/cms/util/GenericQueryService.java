package com.ametis.cms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GenericQueryService {

	public static int getDiagnosisId(String code, Connection conn){
		int result = -1;
		
		try {
			String query = "SELECT diagnosis_id FROM diagnosis WHERE diagnosis_code = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, code);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				result = rs.getInt("diagnosis_id");
			}
			rs.close();
			ps.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public static int getProviderId(String name, Connection conn){
		int result = -1;
		
		try {
			String query = "SELECT provider_id FROM provider WHERE provider_name = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				result = rs.getInt("provider_id");
			}
			rs.close();
			ps.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public static int getPolicyId(String policyNumber, Connection conn){
		int result = -1;
		
		try {
			String query = "SELECT policy_id FROM policy WHERE policy_number = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, policyNumber);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				result = rs.getInt("policy_id");
			}
			rs.close();
			ps.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public static int getProcedureId(String code, Connection conn){
		int result = -1;
		
		try {
			String query = "SELECT procedure_id FROM medical_procedure WHERE procedure_code = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, code);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				result = rs.getInt("procedure_id");
			}
			rs.close();
			ps.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public static int getMemberId(String memberNumber, Connection conn){
		int result = -1;
		
		try {
			String query = "SELECT member_id FROM member WHERE customer_policy_number = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, memberNumber);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				result = rs.getInt("member_id");
			}
			rs.close();
			ps.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
