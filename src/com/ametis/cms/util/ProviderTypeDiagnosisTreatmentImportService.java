package com.ametis.cms.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

public class ProviderTypeDiagnosisTreatmentImportService {
	private Connection conn;
	
	public static void main (String[] args){
		ProviderTypeDiagnosisTreatmentImportService service = new ProviderTypeDiagnosisTreatmentImportService();
		service.execute();
	}
	public ProviderTypeDiagnosisTreatmentImportService(){
	
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
		
			
			String sql = "INSERT INTO provider_type_diagnosis_treatment (provider_type_id,diagnosis_id,treatment_class_id," +
					"treatment_risk_id,weight,alos,age,treatment_rate,deleted_status,description) VALUES (?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			Vector<String[]> fileLoad = this.loadFile("D:\\insurance\\DIAGNOSIS & PROSEDUR\\ICD10\\RAWAT JALAN\\DAFTAR TARIF PER DIAGNOSA RS UMUM & KHUSUS KELAS C & D.csv");
			
			
			if (fileLoad != null){
				
				for (Iterator iterator = fileLoad.iterator(); iterator.hasNext();) {
					
					ps.clearParameters();					
					
					String[] line = (String[]) iterator.next();
					
					if (line != null){
						String diagnoseCode = line[0];
						int diagnoseId = GenericQueryService.getDiagnosisId(diagnoseCode, conn);
						int providerId = 4; // --> INI DIUBAH SESUAI DENGAN KEBUTUHAN
						String treatmentClass = null; // INI DIUBAH SESUAI DENGAN PARAMETER KEBUTUHAN
						String treatmentRisk = null;
					
						String age = line[1]; // DIUBAH JADI LINE ke X jika ada
						String weight = line[2]; // DIUBAH JADI LINE ke X jika ada
						String alos = "-1";
						String price = "-1";
						
						if (line.length > 3  ){
							alos = line[3]; // DIUBAH JADI LINE ke X jika ada
						}
						if (line.length > 4){
							price = line[4]; // DIUBAH JADI LINE ke x Jika ada
						}
						
						if (price != null){
							price = price.replace(",", "");
							price = price.replace(".", "");
							
						}
						if (age != null ){
							age = age.trim();
							if (age.equals("-") || age.equals("")){
								age = "-1";
							}
						}
						if (weight != null){
							weight = weight.trim();
							if (weight.equals("-") || weight.equals("")){
								weight = "-1";
							}
						}
						if (alos != null ){
							alos = alos.trim();
							alos = alos.replace(",", ".");
							if (alos.equals("-") || alos.equals("")){
								alos = "-1";
							}
						}
						if (price != null ){
							price = price.trim();
							if (price.equals("-") || price.equals("")){
								price = "-1";
							}
						}
						ps.setInt(1, providerId);
						ps.setInt(2, diagnoseId);
						ps.setString(3, treatmentClass);
						ps.setString(4, treatmentRisk);
						ps.setString(5, weight);
						ps.setString(6, alos);
						ps.setString(7, age);
						ps.setString(8, price);
						ps.setInt(9, 0);
						ps.setString(10, diagnoseCode);
						System.out.println(diagnoseCode + " price :  " + price + " alos : " + alos + " weight : " + weight + " age : " + age);
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
