
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="provider_service")
public class ProviderService implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	public static final int NO_DISCOUNT = 0;
	public static final int DISCOUNT_GENERAL = 1;
	public static final int DISCOUNT_PER_COVERAGE = 2;
	
	/**data for the column :
	
 --------- provider_service.provider_service_id --------- 
 schema        = null
 tableName     = provider_service
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private Integer providerServiceId;
								
	// foreign affairs
	
			/** CaseCategory
	data for the foreign class :
	
 --------- case_category.case_category_id --------- 
 schema        = null
 tableName     = case_category
 foreignCol    = case_category_id
 foreignTab    = provider_service
 catalog       = wanaartha
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
	private CaseCategory caseCategoryId;
				/** Provider
	data for the foreign class :
	
 --------- provider.provider_id --------- 
 schema        = null
 tableName     = provider
 foreignCol    = provider_id
 foreignTab    = provider_service
 catalog       = wanaartha
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
	private Provider providerId;
	private Double discount;
	private String createdBy;
	private String modifiedBy;
	private String deletedBy;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private Integer deletedStatus;
	private Timestamp deletedTime;
	private Integer discountType;
			
	private Client clientId;
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="provider_service_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getProviderServiceId(){
		return providerServiceId;
	}
	public void setProviderServiceId(java.lang.Integer value){
		providerServiceId = value;
	}
			// PK GETTER SETTER END

										
	// foreign affairs
	
			/** CaseCategory */
	@ManyToOne
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId(){
		return this.caseCategoryId;
	}

	/** CaseCategory */
	public void setCaseCategoryId(CaseCategory obj){
		this.caseCategoryId = obj;
	}
				/** Provider */
	@ManyToOne
	@JoinColumn(name="provider_id")
	public Provider getProviderId(){
		return this.providerId;
	}

	/** Provider */
	public void setProviderId(Provider obj){
		this.providerId = obj;
	}
	@Column(name="discount")
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	@Column(name="created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name="modified_by")
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Column(name="deleted_by")
	public String getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}
	@Column(name="created_time")
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	@Column(name="modified_time")
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	@Column(name="deleted_status")
	public Integer getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(Integer deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	@Column(name="deleted_time")
	public Timestamp getDeletedTime() {
		return deletedTime;
	}
	public void setDeletedTime(Timestamp deletedTime) {
		this.deletedTime = deletedTime;
	}
	@Column(name="discount_type")
	public Integer getDiscountType() {
		return discountType;
	}
	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}
	
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId() {
		return clientId;
	}
	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}
			
	



}