
package com.ametis.cms.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="invoice_item")
public class InvoiceItem implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- invoice_item.invoice_item_id --------- 
 schema        = null
 tableName     = invoice_item
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
	private Integer invoiceItemId;
									
	/**data for the column :
	
 --------- invoice_item.invoice_item_value --------- 
 schema        = null
 tableName     = invoice_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double invoiceItemValue;
			
	/**data for the column :
	
 --------- invoice_item.item_amount --------- 
 schema        = null
 tableName     = invoice_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Double itemAmount;
	private Double itemUnitChargeValue;
			
	/**data for the column :
	
 --------- invoice_item.created_time --------- 
 schema        = null
 tableName     = invoice_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- invoice_item.created_by --------- 
 schema        = null
 tableName     = invoice_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- invoice_item.deleted_time --------- 
 schema        = null
 tableName     = invoice_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- invoice_item.deleted_by --------- 
 schema        = null
 tableName     = invoice_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- invoice_item.modified_time --------- 
 schema        = null
 tableName     = invoice_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- invoice_item.modified_by --------- 
 schema        = null
 tableName     = invoice_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- invoice_item.deleted_status --------- 
 schema        = null
 tableName     = invoice_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 11
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
 foreignTab    = invoice_item
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
				/** Invoice
	data for the foreign class :
	
 --------- invoice.invoice_id --------- 
 schema        = null
 tableName     = invoice
 foreignCol    = invoice_id
 foreignTab    = invoice_item
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
	private Invoice invoiceId;
			
	private MemberGroup memberGroupId;
	private Policy policyId;
	private ClientContractItem clientContractItemId;
	private PaymentStatus invoiceItemStatus;
	private Double invoiceItemPaid;
	private Double outstanding;
	private String invoiceItemAttachmentURL;

	// PK GETTER SETTER
			@Id
	@Column(name="invoice_item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getInvoiceItemId(){
		return invoiceItemId;
	}
	public void setInvoiceItemId(java.lang.Integer value){
		invoiceItemId = value;
	}
			// PK GETTER SETTER END

												@Column(name="invoice_item_value")
	public java.lang.Double getInvoiceItemValue(){
		return invoiceItemValue;
	}
	public void setInvoiceItemValue(java.lang.Double value){
		invoiceItemValue = value;
	}
				@Column(name="item_amount")
	public java.lang.Double getItemAmount(){
		return itemAmount;
	}
	public void setItemAmount(java.lang.Double value){
		itemAmount = value;
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
				/** Invoice */
	@ManyToOne
	@JoinColumn(name="invoice_id")
	public Invoice getInvoiceId(){
		return this.invoiceId;
	}

	/** Invoice */
	public void setInvoiceId(Invoice obj){
		this.invoiceId = obj;
	}
	@Column(name="item_unit_charge_value")
	public Double getItemUnitChargeValue() {
		return itemUnitChargeValue;
	}
	public void setItemUnitChargeValue(Double itemUnitChargeValue) {
		this.itemUnitChargeValue = itemUnitChargeValue;
	}
	@ManyToOne
	@JoinColumn(name="member_group_id")
	public MemberGroup getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(MemberGroup memberGroupId) {
		this.memberGroupId = memberGroupId;
	}
	@ManyToOne
	@JoinColumn(name="policy_id")
	public Policy getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Policy policyId) {
		this.policyId = policyId;
	}
	@ManyToOne
	@JoinColumn(name="client_contract_item_id")
	public ClientContractItem getClientContractItemId() {
		return clientContractItemId;
	}
	public void setClientContractItemId(ClientContractItem clientContractItemId) {
		this.clientContractItemId = clientContractItemId;
	}
	@ManyToOne
	@JoinColumn(name="invoice_item_status")
	public PaymentStatus getInvoiceItemStatus() {
		return invoiceItemStatus;
	}
	public void setInvoiceItemStatus(PaymentStatus invoiceItemStatus) {
		this.invoiceItemStatus = invoiceItemStatus;
	}
	@Column(name="invoice_item_paid")
	public Double getInvoiceItemPaid() {
		return invoiceItemPaid;
	}
	public void setInvoiceItemPaid(Double invoiceItemPaid) {
		this.invoiceItemPaid = invoiceItemPaid;
	}
	@Column(name="outstanding")
	public Double getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(Double outstanding) {
		this.outstanding = outstanding;
	}
	@Column(name="invoice_item_attachment_url",length=2500)
	public String getInvoiceItemAttachmentURL() {
		return invoiceItemAttachmentURL;
	}
	public void setInvoiceItemAttachmentURL(String invoiceItemAttachmentURL) {
		this.invoiceItemAttachmentURL = invoiceItemAttachmentURL;
	}
			
	
	


}