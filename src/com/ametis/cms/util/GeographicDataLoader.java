package com.ametis.cms.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.StringTokenizer;

public class GeographicDataLoader {

	private Connection conn;
	private File file;
	public static void main (String[] args){
		String fileName = args[0];
		
		if (fileName != null){
			GeographicDataLoader loader = new GeographicDataLoader(fileName);
			loader.load();
		}
	}
	public GeographicDataLoader(String fileName){
		
		try{
			this.file = new File(fileName);
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/medikaplaza","root","root");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public void load(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			
			String queryRegion = "SELECT ref_region_id FROM ref_region WHERE region_code = ?";
			PreparedStatement psRegion = conn.prepareStatement(queryRegion);
			
			String qInserRegion = "INSERT INTO ref_region (region_name,region_code,deleted_status) VALUES (?,?,?)";
			PreparedStatement psInsertRegion = conn.prepareStatement(qInserRegion);
			
			String queryProvince = "SELECT id FROM ref_province WHERE province_code = ? AND region_id = ?";
			PreparedStatement psQProvince = conn.prepareStatement(queryProvince);
			
			String qInsertProvince = "INSERT INTO ref_province (province_name,province_code,deleted_status,region_id) VALUES (?,?,?,?)";
			PreparedStatement psInsertProvince = conn.prepareStatement(qInsertProvince);
			
			String queryCity = "SELECT id FROM ref_city WHERE city_code = ? AND province_id = ?";
			PreparedStatement psQCity = conn.prepareStatement(queryCity);
			
			String qInsertCity = "INSERT INTO ref_city (city_name,city_code,deleted_status,province_id) VALUES (?,?,?,?)";
			PreparedStatement psInsertCity = conn.prepareStatement(qInsertCity);
			
			
			
			while((line= reader.readLine()) != null){
				StringTokenizer tokenizer = new StringTokenizer(line, "|");
				
				String regionCode = tokenizer.nextToken().trim();
				String regionName = tokenizer.nextToken().trim();
				String provinceCode = tokenizer.nextToken().trim();
				String provinceName = tokenizer.nextToken().trim();
				String cityCode = tokenizer.nextToken().trim();
				String cityName = tokenizer.nextToken().trim();
				String cityAllCode = tokenizer.nextToken().trim();
				
				
				psRegion.clearParameters();
				
				psRegion.setString(1, regionCode);
				ResultSet rsRegion = psRegion.executeQuery();
				
				if (rsRegion.next()){
					int regionId = rsRegion.getInt("ref_region_id");
					
					psQProvince.clearParameters();
					psQProvince.setString(1, regionCode+provinceCode);
					psQProvince.setInt(2, regionId);
					
					ResultSet rsProvince = psQProvince.executeQuery();
					
					if (rsProvince.next()){
						int provinceId = rsProvince.getInt("id");
						
						psQCity.clearParameters();
						psQCity.setString(1, cityAllCode);
						psQCity.setInt(2,provinceId);
						
						ResultSet rsCity = psQCity.executeQuery();
						
						if (!rsCity.next()){
							psInsertCity.clearParameters();
							psInsertCity.setString(1, cityName);
							psInsertCity.setString(2, cityAllCode);
							psInsertCity.setInt(3, 0);
							psInsertCity.setInt(4, provinceId);
							
							psInsertCity.executeUpdate();
						}
					}
					else {
						psInsertProvince.clearParameters();
						psInsertProvince.setString(1,provinceName);
						psInsertProvince.setString(2, regionCode+provinceCode);
						psInsertProvince.setInt(3, 0);
						psInsertProvince.setInt(4, regionId);
						
						psInsertProvince.executeUpdate();
						
						psQProvince.clearParameters();
						psQProvince.setString(1, regionCode+provinceCode);
						psQProvince.setInt(2, regionId);
						
						ResultSet rsProvince2 = psQProvince.executeQuery();
						
						if (rsProvince2.next()){
							int provinceId = rsProvince2.getInt("id");
							
							psInsertCity.clearParameters();
							psInsertCity.setString(1, cityName);
							psInsertCity.setString(2, cityAllCode);
							psInsertCity.setInt(3, 0);
							psInsertCity.setInt(4, provinceId);
							
							psInsertCity.executeUpdate();
						}
					}
				}
				else {
					psInsertRegion.clearParameters();
					psInsertRegion.setString(1, regionName);
					psInsertRegion.setString(2, regionCode);
					psInsertRegion.setString(3, "0");
					
					psInsertRegion.executeUpdate();
					
					psRegion.clearParameters();
				
					psRegion.setString(1, regionCode);
					ResultSet rsRegion2 = psRegion.executeQuery();
					
					if (rsRegion2.next()){
						int regionId = rsRegion2.getInt("ref_region_id");
						
						psInsertProvince.clearParameters();
						psInsertProvince.setString(1,provinceName);
						psInsertProvince.setString(2, regionCode+provinceCode);
						psInsertProvince.setInt(3, 0);
						psInsertProvince.setInt(4, regionId);
						
						psInsertProvince.executeUpdate();
						
						psQProvince.clearParameters();
						psQProvince.setString(1, regionCode+provinceCode);
						psQProvince.setInt(2, regionId);
						
						ResultSet rsProvince = psQProvince.executeQuery();
						
						if (rsProvince.next()){
							int provinceId = rsProvince.getInt("id");
							
							psInsertCity.clearParameters();
							psInsertCity.setString(1, cityName);
							psInsertCity.setString(2, cityAllCode);
							psInsertCity.setInt(3, 0);
							psInsertCity.setInt(4, provinceId);
							
							psInsertCity.executeUpdate();
							
						}
					}
				}		
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
