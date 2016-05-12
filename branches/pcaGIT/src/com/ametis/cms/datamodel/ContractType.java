
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="contract_type")
public class ContractType implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- contract_type.contract_type_id --------- 
 schema        = null
 tableName     = contract_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Integer contractTypeId;
	private String contractTypeName;

	private String contractTypeCode;
			
	/**data for the column :
	
 --------- contract_type.contract_charge_type --------- 
 schema        = null
 tableName     = contract_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer contractChargeType;
			
	/**data for the column :
	
 --------- contract_type.contract_charge_per_member --------- 
 schema        = null
 tableName     = contract_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double contractChargePerMember;
	private Double contractChargePerClaim;
	
			
	/**data for the column :
	
 --------- contract_type.created_time --------- 
 schema        = null
 tableName     = contract_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- contract_type.created_by --------- 
 schema        = null
 tableName     = contract_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- contract_type.modified_time --------- 
 schema        = null
 tableName     = contract_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- contract_type.modified_by --------- 
 schema        = null
 tableName     = contract_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- contract_type.deleted_time --------- 
 schema        = null
 tableName     = contract_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 10
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- contract_type.deleted_by --------- 
 schema        = null
 tableName     = contract_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- contract_type.deleted_status --------- 
 schema        = null
 tableName     = contract_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** ClientContract
	data for the exported class :
	
 --------- client_contract.contract_type_id --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 5
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = contract_type.contract_type_id

=========================================



	 */
	private Set <ClientContract> clientContracts = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="contract_type_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getContractTypeId(){
		return contractTypeId;
	}
	public void setContractTypeId(java.lang.Integer value){
		contractTypeId = value;
	}
			// PK GETTER SETTER END

						@Column(name="contract_type_name")
	public java.lang.String getContractTypeName(){
		return contractTypeName;
	}
	public void setContractTypeName(java.lang.String value){
		contractTypeName = value;
	}
				@Column(name="contract_type_code")
	public java.lang.String getContractTypeCode(){
		return contractTypeCode;
	}
	public void setContractTypeCode(java.lang.String value){
		contractTypeCode = value;
	}
				@Column(name="contract_charge_type")
	public java.lang.Integer getContractChargeType(){
		return contractChargeType;
	}
	public void setContractChargeType(java.lang.Integer value){
		contractChargeType = value;
	}
				@Column(name="contract_charge_per_member")
	public java.lang.Double getContractChargePerMember(){
		return contractChargePerMember;
	}
	public void setContractChargePerMember(java.lang.Double value){
		contractChargePerMember = value;
	}
				@Column(name="created_time")
	public java.sql.Timestamp getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp value){
		createdTime = value;
	}
				@Column(name="created_by")
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
	}
				@Column(name="modified_time")
	public java.sql.Timestamp getModifiedTime(){
		return modifiedTime;
	}
	public void setModifiedTime(java.sql.Timestamp value){
		modifiedTime = value;
	}
				@Column(name="modified_by")
	public java.lang.String getModifiedBy(){
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String value){
		modifiedBy = value;
	}
				@Column(name="deleted_time")
	public java.sql.Timestamp getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Timestamp value){
		deletedTime = value;
	}
				@Column(name="deleted_by")
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
			/** ClientContract */
	@OneToMany(mappedBy="contractTypeId")
	public Set<ClientContract> getClientContracts(){
		return this.clientContracts;
	}

	@Column(name="contract_charge_per_claim")
	public Double getContractChargePerClaim() {
				return contractChargePerClaim;
			}
			public void setContractChargePerClaim(Double contractChargePerClaim) {
				this.contractChargePerClaim = contractChargePerClaim;
			}
	/** ClientContract */
	public void setClientContracts(Set<ClientContract> obj){
		this.clientContracts = obj;
	}
			
	//exported key end



}