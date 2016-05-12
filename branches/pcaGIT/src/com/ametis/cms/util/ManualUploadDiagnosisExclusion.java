package com.ametis.cms.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.StringTokenizer;

public class ManualUploadDiagnosisExclusion {
	Connection connection;
	
	public static void main(String[] args){
		ManualUploadDiagnosisExclusion exclusion = new ManualUploadDiagnosisExclusion();
		exclusion.loadData();	
		//exclusion.loadDataExclusion();
	}
	public ManualUploadDiagnosisExclusion(){
		try {
			
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://10.245.245.8/healthcare","healthcare","h34lth-lint4s!");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void loadData(){
		try {
			FileReader instream = new FileReader("/home/adhit/Documents/INTERMEDIA/LINTASARTA/policy-exclusion/CAR_PEC_365.csv");
			BufferedReader reader = new BufferedReader(instream);
			
			String line = "";
			
			String qDiagnosis = "SELECT diagnosis_id FROM diagnosis WHERE diagnosis_code = ? AND deleted_status = 0";
			String qPolicy = "SELECT policy_id FROM policy WHERE policy_number = ? AND deleted_status = 0";
			String qInsert = "INSERT INTO policy_diagnosis_exclusion (deleted_status,created_time,created_by"
					+ ",diagnosis_id,policy_id,pre_existing_days_duration) VALUES (?,?,?,?,?,?) ";
			
			
			
			PreparedStatement psDiagnosis = connection.prepareStatement(qDiagnosis);
			PreparedStatement psPolicy = connection.prepareStatement(qPolicy);
			PreparedStatement psInsert = connection.prepareStatement(qInsert);
			
			while((line = reader.readLine()) != null){
				
				psDiagnosis.clearParameters();
				psPolicy.clearParameters();
				
				
				StringTokenizer tokenizer = new StringTokenizer(line,"|");
				String policyNumber = "";
				String diagnosisCode = "";
				
				String diagSetId = "";
				String preExDate = "";
				String preExDuration = "";
				String ageParam = "";
				String actionType = "";
				String ageConstraint = "";
				
				if (tokenizer.hasMoreTokens()){
					policyNumber = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					diagnosisCode = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					diagSetId = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					preExDate  = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					preExDuration  = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					ageParam = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					ageConstraint = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					actionType = tokenizer.nextToken();
				}
				
				if (actionType.equalsIgnoreCase("EXCLUDE")){
					
					psDiagnosis.setString(1, diagnosisCode.trim());
					

					Integer diagnosisId = null;
					Integer policyId = null;
					
					ResultSet rsDiagnosis = psDiagnosis.executeQuery();
					
					if (rsDiagnosis != null && rsDiagnosis.next()){
						diagnosisId = rsDiagnosis.getInt("diagnosis_id");
					}
					
					psPolicy.setString(1, policyNumber.trim());
					
					ResultSet rsPolicy = psPolicy.executeQuery();
					
					if (rsPolicy != null && rsPolicy.next()){
						policyId = rsPolicy.getInt("policy_id");
						 
					}

					
					boolean isExist = isExist(policyId, diagnosisId);
					
					if (!isExist){
						psInsert.clearParameters();
						psInsert.setInt(1,0);
						psInsert.setTimestamp(2,new Timestamp(System.currentTimeMillis()));
						psInsert.setString(3,"backdoor");
						psInsert.setInt(4,diagnosisId);
						psInsert.setInt(5,policyId);
						psInsert.setInt(6,Integer.valueOf(preExDuration.trim()));
						
						//System.out.println("INSERT INTO : POLICY ID = " + policyId + " DIAGNOSIS ID = " + diagnosisId + " Duration(s) : " + preExDuration);;
						psInsert.executeUpdate();
						
					}
					else {
						System.out.println("EXIST diagnosis_id = " + diagnosisId  + " Code = " + diagnosisCode + " policy d = " + policyId);
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public void loadDataExclusion(){
		try {
			
			FileReader instream = new FileReader("/home/adhit/Documents/INTERMEDIA/LINTASARTA/policy-exclusion/CAR_Exclusion-2.csv");
			BufferedReader reader = new BufferedReader(instream);
			
			String line = "";
			
			String qDiagnosis = "SELECT diagnosis_id FROM diagnosis WHERE diagnosis_code = ? AND deleted_status = 0";
			String qPolicy = "SELECT policy_id FROM policy WHERE policy_number = ? AND deleted_status = 0";
			String qInsert = "INSERT INTO policy_diagnosis_exclusion (deleted_status,created_time,created_by"
					+ ",diagnosis_id,policy_id) VALUES (?,?,?,?,?	) ";
			
			
			
			PreparedStatement psDiagnosis = connection.prepareStatement(qDiagnosis);
			PreparedStatement psPolicy = connection.prepareStatement(qPolicy);
			PreparedStatement psInsert = connection.prepareStatement(qInsert);
			
			while((line = reader.readLine()) != null){
				
				psDiagnosis.clearParameters();
				psPolicy.clearParameters();
				
				
				StringTokenizer tokenizer = new StringTokenizer(line,"|");
				String policyNumber = "";
				String diagnosisCode = "";
				
				String diagSetId = "";
				String preExDate = "";
				String preExDuration = "";
				String ageParam = "";
				String actionType = "";
				String ageConstraint = "";
				
				if (tokenizer.hasMoreTokens()){
					policyNumber = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					diagnosisCode = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					diagSetId = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					preExDate  = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					preExDuration  = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					ageParam = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					ageConstraint = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()){
					actionType = tokenizer.nextToken();
				}
				
				if (actionType.equalsIgnoreCase("EXCLUDE")){
					
					psDiagnosis.setString(1, diagnosisCode.trim());
					

					Integer diagnosisId = null;
					Integer policyId = null;
					
					ResultSet rsDiagnosis = psDiagnosis.executeQuery();
					
					if (rsDiagnosis != null && rsDiagnosis.next()){
						diagnosisId = rsDiagnosis.getInt("diagnosis_id");
					}
					
					psPolicy.setString(1, policyNumber.trim());
					
					ResultSet rsPolicy = psPolicy.executeQuery();
					
					if (rsPolicy != null && rsPolicy.next()){
						policyId = rsPolicy.getInt("policy_id");
						 
					}

					
					boolean isExist = isExist(policyId, diagnosisId);
					
					if (!isExist){
						psInsert.clearParameters();
						psInsert.setInt(1,0);
						psInsert.setTimestamp(2,new Timestamp(System.currentTimeMillis()));
						psInsert.setString(3,"backdoor");
						psInsert.setInt(4,diagnosisId);
						psInsert.setInt(5,policyId);
						
						
						System.out.println("INSERT INTO : POLICY ID = " + policyId + " DIAGNOSIS ID = " + diagnosisId + " Duration(s) : " + preExDuration);;
						psInsert.executeUpdate();
						
					}
					else {
						System.out.println("EXIST");
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	private boolean executeUpload (Integer policyId, Integer diagnosisId){
		boolean result = false;
		
		try {
		
		//	String q = "IS"
			
			result = true;
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	 
	
	private boolean isExist(Integer policyId, Integer diagnosisId){
		
		boolean result = false;
		try {
				
			
			if (policyId != null && diagnosisId != null){
				String q = "SELECT count(id) as result FROM policy_diagnosis_exclusion WHERE diagnosis_id = ? AND policy_id = ?  AND deleted_status = 0";
				PreparedStatement psCheck = connection.prepareStatement(q);
				psCheck.setInt(1, diagnosisId);
				psCheck.setInt(2, policyId);
				
				ResultSet rs = psCheck.executeQuery();
				
				if (rs.next()){
					Integer res = rs.getInt("result");
					
					if (res != null && res.intValue() > 0)
					{
						result = true;
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
