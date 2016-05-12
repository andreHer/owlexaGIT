
package com.ametis.cms.datamodel;


import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="member_electronic_card")
public class MemberElectronicCard implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int SHOW_CARD = 1;
	public static final int SWIPE_CARD = 2;
	public static final int SMART_CARD = 3;
	
	public static final int CARD_ACTIVE = 1;
	public static final int CARD_PENDING = -1;
	public static final int CARD_BLOCKED = 2;
	public static final int CARD_EXPIRED = 3;

	//Fields
		
	/**data for the column :
	
 --------- member_electronic_card.id --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Integer id;
			
	/**data for the column :
	
 --------- member_electronic_card.member_id --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Member memberId;
			
	/**data for the column :
	
 --------- member_electronic_card.card_number --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String cardNumber;
			
	/**data for the column :
	
 --------- member_electronic_card.registration_date --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date registrationDate;
			
	/**data for the column :
	
 --------- member_electronic_card.policy_id --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Policy policyId;
			
	/**data for the column :
	
 --------- member_electronic_card.member_number --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String memberNumber;
			
	/**data for the column :
	
 --------- member_electronic_card.created_time --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- member_electronic_card.created_by --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- member_electronic_card.modified_time --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- member_electronic_card.modified_by --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- member_electronic_card.deleted_time --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
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
	
 --------- member_electronic_card.deleted_by --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- member_electronic_card.deleted_status --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- member_electronic_card.card_status --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer cardStatus;
			
	/**data for the column :
	
 --------- member_electronic_card.description --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- member_electronic_card.replaced_card_id --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private MemberElectronicCard replacedCardId;
			
	/**data for the column :
	
 --------- member_electronic_card.card_type --------- 
 schema        = null
 tableName     = member_electronic_card
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private CardType cardType;
	private Date effectiveDate;
	private Date expireDate;
	
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getId(){
		return id;
	}
	public void setId(java.lang.Integer value){
		id = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId(){
		return memberId;
	}
	public void setMemberId(Member value){
		memberId = value;
	}
				@Column(name="card_number")
	public java.lang.String getCardNumber(){
		return cardNumber;
	}
	public void setCardNumber(java.lang.String value){
		cardNumber = value;
	}
				@Column(name="registration_date")
	public java.sql.Date getRegistrationDate(){
		return registrationDate;
	}
	public void setRegistrationDate(java.sql.Date value){
		registrationDate = value;
	}
	@ManyToOne
	@JoinColumn(name="policy_id")
	public Policy getPolicyId(){
		return policyId;
	}
	public void setPolicyId(Policy value){
		policyId = value;
	}
				@Column(name="member_number")
	public java.lang.String getMemberNumber(){
		return memberNumber;
	}
	public void setMemberNumber(java.lang.String value){
		memberNumber = value;
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
				@Column(name="card_status")
	public java.lang.Integer getCardStatus(){
		return cardStatus;
	}
	public void setCardStatus(java.lang.Integer value){
		cardStatus = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
	@ManyToOne
	@JoinColumn(name="replaced_card_id")
	public MemberElectronicCard getReplacedCardId(){
		return replacedCardId;
	}
	public void setReplacedCardId(MemberElectronicCard value){
		replacedCardId = value;
	}
	@ManyToOne
	@JoinColumn(name="card_type")
	public CardType getCardType(){
		return cardType;
	}
	public void setCardType(CardType value){
		cardType = value;
	}
	@Column(name="effective_date")
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	@Column(name="expire_date")
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
		
	


}