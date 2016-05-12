
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="problem_category")
public class ProblemCategory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- problem_category.problem_category_id --------- 
 schema        = null
 tableName     = problem_category
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 3
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer problemCategoryId;
			
	/**data for the column :
	
 --------- problem_category.problem_category_name --------- 
 schema        = null
 tableName     = problem_category
 foreignCol    = 
 foreignTab    = 
 catalog       = media
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
	private String problemCategoryName;
			
	/**data for the column :
	
 --------- problem_category.description --------- 
 schema        = null
 tableName     = problem_category
 foreignCol    = 
 foreignTab    = 
 catalog       = media
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

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="problem_category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getProblemCategoryId(){
		return problemCategoryId;
	}
	public void setProblemCategoryId(java.lang.Integer value){
		problemCategoryId = value;
	}
			// PK GETTER SETTER END

						@Column(name="problem_category_name")
	public java.lang.String getProblemCategoryName(){
		return problemCategoryName;
	}
	public void setProblemCategoryName(java.lang.String value){
		problemCategoryName = value;
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

	
		
	//exported key end



}