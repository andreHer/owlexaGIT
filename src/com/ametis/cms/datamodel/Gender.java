
package com.ametis.cms.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="gender")
public class Gender implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- gender.gender_id --------- 
 schema        = null
 tableName     = gender
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer genderId;
			
	/**data for the column :
	
 --------- gender.gender_name --------- 
 schema        = null
 tableName     = gender
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String genderName;
			
	/**data for the column :
	
 --------- gender.description --------- 
 schema        = null
 tableName     = gender
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String description;
	private String genderCode;
	private Double riskCoefficient;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="gender_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getGenderId(){
		return genderId;
	}
	public void setGenderId(java.lang.Integer value){
		genderId = value;
	}
			// PK GETTER SETTER END

						@Column(name="gender_name")
	public java.lang.String getGenderName(){
		return genderName;
	}
	public void setGenderName(java.lang.String value){
		genderName = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
	@Column(name="gender_code")
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	@Column(name="risk_coefficient")
	public Double getRiskCoefficient() {
		return riskCoefficient;
	}
	public void setRiskCoefficient(Double riskCoefficient) {
		this.riskCoefficient = riskCoefficient;
	}
	
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}