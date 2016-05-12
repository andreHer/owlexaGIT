package com.ametis.cms.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

public class ProviderProcedureTreatmentImportService {
private Connection conn;
	
	public static void main (String[] args){
		ProviderProcedureTreatmentImportService service = new ProviderProcedureTreatmentImportService();
		service.execute();
	}
	public ProviderProcedureTreatmentImportService(){
	
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
		
			
			String sql = "INSERT INTO provider_type_procedure (provider_type_id,procedure_id,treatment_class_id," +
					"treatment_risk_id,alos,treatment_rate,deleted_status,description) VALUES (?,?,?,?,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			Vector<String[]> fileLoad = this.loadFile("D:\\insurance\\DIAGNOSIS & PROSEDUR\\ICD09\\RAWAT JALAN\\DAFTAR TARIF PER PROSEDUR RS UMUM & KHUSUS KELAS C & D.csv");
			
			
			if (fileLoad != null){
				
				for (Iterator iterator = fileLoad.iterator(); iterator.hasNext();) {
					
					ps.clearParameters();					
					
					String[] line = (String[]) iterator.next();
					
					if (line != null){
						String procedureCode = line[0];
						int procedureId = GenericQueryService.getProcedureId(procedureCode, conn);
						int providerId = 4; // --> INI DIUBAH SESUAI DENGAN KEBUTUHAN
						String treatmentClass = null; // INI DIUBAH SESUAI DENGAN PARAMETER KEBUTUHAN
						String treatmentRisk = null;
					
						String alos = "-1";
						String price = "-1";
						
						if (line.length > 1  ){
							alos = line[1]; // DIUBAH JADI LINE ke X jika ada
						}
						if (line.length > 2){
							price = line[2]; // DIUBAH JADI LINE ke x Jika ada
						}
						
						if (price != null){
							price = price.replace(",", "");
							price = price.replace(".", "");
							
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
						ps.setInt(2, procedureId);
						ps.setString(3, treatmentClass);
						ps.setString(4, treatmentRisk);
						ps.setString(5, alos);
						ps.setString(6, price);
						ps.setInt(7, 0);
						ps.setString(8, procedureCode);
						System.out.println(procedureCode + " price :  " + price + " alos : " + alos );
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
