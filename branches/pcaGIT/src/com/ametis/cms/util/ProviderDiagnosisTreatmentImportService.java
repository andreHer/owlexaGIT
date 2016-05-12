package com.ametis.cms.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

public class ProviderDiagnosisTreatmentImportService {

	private Connection conn;
	
	public static void main (String[] args){
		ProviderDiagnosisTreatmentImportService service = new ProviderDiagnosisTreatmentImportService();
		service.execute();
	}
	public ProviderDiagnosisTreatmentImportService(){
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/healthcare","root","root");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public void execute(){
		try {
		
			
			String sql = "INSERT INTO provider_diagnosis_treatment (provider_id,diagnosis_id,treatment_class_id," +
					"treatment_risk_id,weight,alos,age,treatment_rate,deleted_status) VALUES (?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			Vector<String[]> fileLoad = this.loadFile("");
			
			if (fileLoad != null){
				
				for (Iterator iterator = fileLoad.iterator(); iterator.hasNext();) {
					
					ps.clearParameters();					
					
					String[] line = (String[]) iterator.next();
					
					if (line != null){
						String diagnoseCode = line[0];
						int diagnoseId = GenericQueryService.getDiagnosisId(diagnoseCode, conn);
						int providerId = 123; // --> INI DIUBAH SESUAI DENGAN KEBUTUHAN
						Integer treatmentClass = null; // INI DIUBAH SESUAI DENGAN PARAMETER KEBUTUHAN
						Integer treatmentRisk = null;
					
						Double weight = null; // DIUBAH JADI LINE ke X jika ada
						Double age = null; // DIUBAH JADI LINE ke X jika ada
						Double alos = null; // DIUBAH JADI LINE ke X jika ada
						Double price = null; // DIUBAH JADI LINE ke x Jika ada
						
						ps.setInt(1, providerId);
						ps.setInt(2, diagnoseId);
						ps.setInt(3, treatmentClass);
						ps.setInt(4, treatmentRisk);
						ps.setDouble(5, weight);
						ps.setDouble(6, alos);
						ps.setDouble(7, age);
						ps.setDouble(8, price);
						ps.setInt(9, 0);
						
						ps.executeUpdate();
						
						
					}
					
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public Vector<String[]> loadFile(String file){
		Vector<String[]> result = new Vector<String[]>();
		
		try {
			
			FileReader reader = new FileReader(file);
			BufferedReader buffReader = new BufferedReader(reader);
			String line = "";
			
			while ((line = buffReader.readLine()) != null) {
            	String rawData = line;
                while (rawData.contains("||")){
                   	rawData = rawData.replace("||", "| |");
                }
                StringTokenizer tokenizer = new StringTokenizer(rawData,"|");
                int totalToken = tokenizer.countTokens();
                String[] data = new String[totalToken];
                for (int i = 0; i < totalToken; i++){
                	data[i] = tokenizer.nextToken();
                }
                result.add(data);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
}
