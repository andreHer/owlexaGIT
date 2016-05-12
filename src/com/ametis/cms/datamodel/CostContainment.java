
package com.ametis.cms.datamodel;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="cost_containment")
public class CostContainment implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	

	//Fields
		
	/**data for the column :
	
 --------- cost_containment.cost_containment_id --------- 
 schema        = null
 tableName     = cost_containment
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
	private Integer costContainmentId;
			
	/**data for the column :
	
 --------- cost_containment.case_id --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Case caseId;
						
	/**data for the column :
	
 --------- cost_containment.measurement_unit_id --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer measurementUnitId;
			
	/**data for the column :
	
 --------- cost_containment.description --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- cost_containment.initial_item_cost --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double initialItemCost;
			
	/**data for the column :
	
 --------- cost_containment.total_substitution_value --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalSubstitutionValue;
			
	/**data for the column :
	
 --------- cost_containment.total_item --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalItem;
			
	/**data for the column :
	
 --------- cost_containment.created_time --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- cost_containment.created_by --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- cost_containment.deleted_time --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- cost_containment.deleted_by --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- cost_containment.modified_time --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- cost_containment.modified_by --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- cost_containment.deleted_status --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** Item
	data for the foreign class :
	
 --------- item.item_id --------- 
 schema        = null
 tableName     = item
 foreignCol    = item_id
 foreignTab    = cost_containment
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
	private Item itemId;
	
	private Claim claimId;
	
	private CostContainmentType costContainmentType;
	
	private Client clientId;
	
	private Double costContainmentValue;
	
	private java.sql.Date costContainmentDate;
	
	private Member memberId;
	
	private Item substitutionItemId;
			
	// -- foreign affairs end

	// -- exported key

	
			/** SubstitutionItem
	data for the exported class :
	
 --------- substitution_item.cost_containment_id --------- 
 schema        = null
 tableName     = substitution_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = cost_containment.cost_containment_id

=========================================



	 */
	private Set <SubstitutionItem> substitutionItems = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="cost_containment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getCostContainmentId(){
		return costContainmentId;
	}
	public void setCostContainmentId(java.lang.Integer value){
		costContainmentId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="case_id")
	public Case getCaseId(){
		return caseId;
	}
	public void setCaseId(Case value){
		caseId = value;
	}
	@Column(name="measurement_unit_id")
	public java.lang.Integer getMeasurementUnitId(){
		return measurementUnitId;
	}
	public void setMeasurementUnitId(java.lang.Integer value){
		measurementUnitId = value;
	}
	@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="initial_item_cost")
	public java.lang.Double getInitialItemCost(){
		return initialItemCost;
	}
	public void setInitialItemCost(java.lang.Double value){
		initialItemCost = value;
	}
				@Column(name="total_substitution_value")
	public java.lang.Double getTotalSubstitutionValue(){
		return totalSubstitutionValue;
	}
	public void setTotalSubstitutionValue(java.lang.Double value){
		totalSubstitutionValue = value;
	}
				@Column(name="total_item")
	public java.lang.Double getTotalItem(){
		return totalItem;
	}
	public void setTotalItem(java.lang.Double value){
		totalItem = value;
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
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
		
	// foreign affairs
	
			/** Item */
	@ManyToOne
	@JoinColumn(name="item_id")
	public Item getItemId(){
		return this.itemId;
	}

	/** Item */
	public void setItemId(Item obj){
		this.itemId = obj;
	}
	
	@ManyToOne
	@JoinColumn(name="claim_id")
	public Claim getClaimId() {
		return claimId;
	}
	public void setClaimId(Claim claimId) {
		this.claimId = claimId;
	}
	@ManyToOne
	@JoinColumn(name="cost_containment_type")
	public CostContainmentType getCostContainmentType() {
		return costContainmentType;
	}
	public void setCostContainmentType(CostContainmentType costContainmentType) {
		this.costContainmentType = costContainmentType;
	}
	// foreign affairs end

	
// exported key

	
			/** SubstitutionItem */
	@OneToMany(mappedBy="costContainmentId")
	public Set<SubstitutionItem> getSubstitutionItems(){
		return this.substitutionItems;
	}

	/** SubstitutionItem */
	public void setSubstitutionItems(Set<SubstitutionItem> obj){
		this.substitutionItems = obj;
	}
	
	@Column(name="cost_containment_value")
	public Double getCostContainmentValue() {
		return costContainmentValue;
	}
	public void setCostContainmentValue(Double costContainmentValue) {
		this.costContainmentValue = costContainmentValue;
	}
	@Column(name="cost_containment_date")
	public java.sql.Date getCostContainmentDate() {
		return costContainmentDate;
	}
	public void setCostContainmentDate(java.sql.Date costContainmentDate) {
		this.costContainmentDate = costContainmentDate;
	}
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId() {
		return clientId;
	}
	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}
	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId() {
		return memberId;
	}
	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}
	@ManyToOne
	@JoinColumn(name="substitution_item_id")
	public Item getSubstitutionItemId() {
		return substitutionItemId;
	}
	public void setSubstitutionItemId(Item substitutionItemId) {
		this.substitutionItemId = substitutionItemId;
	}
	
	
			
	//exported key end



}