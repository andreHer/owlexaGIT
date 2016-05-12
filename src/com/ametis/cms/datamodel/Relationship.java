
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
@Table(name="relationship")
public class Relationship implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static final int SPOUSE = 1;
	public static final int CHILD = 2;
	public static final int EMPLOYEE = 3;
	
	public static final int PARENT = 4;
	public static final int GRANDPARENT = 5;
	public static final int OTHER_RELATIVES = 6;
	public static final int OTHER = 7;
	public static final int WIFE = 8;
	public static final int DAUGHTER  = 9;
	public static final int SON = 10;

	//Fields
		
	/**data for the column :
	
 --------- relationship.relationship_id --------- 
 schema        = null
 tableName     = relationship
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
	private Integer relationshipId;
			
	/**data for the column :
	
 --------- relationship.relationship_name --------- 
 schema        = null
 tableName     = relationship
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
	private String relationshipName;
			
	/**data for the column :
	
 --------- relationship.description --------- 
 schema        = null
 tableName     = relationship
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
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** Member
	data for the exported class :
	
 --------- member.relationship_id --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 21
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = relationship.relationship_id

=========================================



	 */
	private Set <Member> members = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="relationship_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getRelationshipId(){
		return relationshipId;
	}
	public void setRelationshipId(java.lang.Integer value){
		relationshipId = value;
	}
			// PK GETTER SETTER END

						@Column(name="relationship_name")
	public java.lang.String getRelationshipName(){
		return relationshipName;
	}
	public void setRelationshipName(java.lang.String value){
		relationshipName = value;
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

	
			/** Member */
	@OneToMany(mappedBy="relationshipId")
	public Set<Member> getMembers(){
		return this.members;
	}

	/** Member */
	public void setMembers(Set<Member> obj){
		this.members = obj;
	}
			
	//exported key end



}