
package com.ametis.cms.datamodel;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="investigation_category")
public class InvestigationCategory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- investigation_category.investigation_category_id --------- 
 schema        = null
 tableName     = investigation_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 5
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer investigationCategoryId;
			
	/**data for the column :
	
 --------- investigation_category.investigation_category_name --------- 
 schema        = null
 tableName     = investigation_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String investigationCategoryName;
			
	/**data for the column :
	
 --------- investigation_category.description --------- 
 schema        = null
 tableName     = investigation_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** Investigation
	data for the exported class :
	
 --------- investigation.investigation_category --------- 
 schema        = null
 tableName     = investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = 
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 3
 size          = 5
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = investigation_category.investigation_category_id

=========================================



	 */
	private Set <Investigation> investigations = new HashSet(0);
	private String printOutTemplate;
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="investigation_category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getInvestigationCategoryId(){
		return investigationCategoryId;
	}
	public void setInvestigationCategoryId(java.lang.Integer value){
		investigationCategoryId = value;
	}
			// PK GETTER SETTER END

						@Column(name="investigation_category_name")
	public java.lang.String getInvestigationCategoryName(){
		return investigationCategoryName;
	}
	public void setInvestigationCategoryName(java.lang.String value){
		investigationCategoryName = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
			/** Investigation */
	@OneToMany(mappedBy="investigationCategory")
	public Set<Investigation> getInvestigations(){
		return this.investigations;
	}

	/** Investigation */
	public void setInvestigations(Set<Investigation> obj){
		this.investigations = obj;
	}
	@Column(name="print_out_template")
	public String getPrintOutTemplate() {
		return printOutTemplate;
	}
	public void setPrintOutTemplate(String printOutTemplate) {
		this.printOutTemplate = printOutTemplate;
	}
			
	
	//exported key end



}