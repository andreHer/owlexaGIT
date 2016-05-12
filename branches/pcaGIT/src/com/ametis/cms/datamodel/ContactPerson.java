
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="contact_person")
public class ContactPerson implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- contact_person.contact_person_id --------- 
 schema        = null
 tableName     = contact_person
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
	private Integer contactPersonId;
			
	/**data for the column :
	
 --------- contact_person.name --------- 
 schema        = null
 tableName     = contact_person
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 2
 size          = 250
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String name;
			
	/**data for the column :
	
 --------- contact_person.telephone --------- 
 schema        = null
 tableName     = contact_person
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String telephone;
			
	/**data for the column :
	
 --------- contact_person.handphone --------- 
 schema        = null
 tableName     = contact_person
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String handphone;
			
	/**data for the column :
	
 --------- contact_person.email --------- 
 schema        = null
 tableName     = contact_person
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String email;
			
	/**data for the column :
	
 --------- contact_person.department --------- 
 schema        = null
 tableName     = contact_person
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
	private String department;
			
	/**data for the column :
	
 --------- contact_person.job_position --------- 
 schema        = null
 tableName     = contact_person
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String jobPosition;
			
	/**data for the column :
	
 --------- contact_person.created_time --------- 
 schema        = null
 tableName     = contact_person
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- contact_person.created_by --------- 
 schema        = null
 tableName     = contact_person
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String createdBy;
			
	/**data for the column :
	
 --------- contact_person.modified_time --------- 
 schema        = null
 tableName     = contact_person
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- contact_person.modified_by --------- 
 schema        = null
 tableName     = contact_person
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- contact_person.deleted_time --------- 
 schema        = null
 tableName     = contact_person
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 12
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- contact_person.deleted_by --------- 
 schema        = null
 tableName     = contact_person
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- contact_person.deleted_status --------- 
 schema        = null
 tableName     = contact_person
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
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer paymentOfficer;
	
	/**data for the column :
	
 --------- contact_person.payment_officer --------- 
 schema        = null
 tableName     = contact_person
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
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
											
	// foreign affairs
	
			/** Provider
	data for the foreign class :
	
 --------- provider.provider_id --------- 
 schema        = null
 tableName     = provider
 foreignCol    = provider_id
 foreignTab    = contact_person
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
	private Provider providerId;
				/** MemberGroup
	data for the foreign class :
	
 --------- member_group.member_group_id --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = member_group_id
 foreignTab    = contact_person
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
	private MemberGroup memberGroupId;
				/** Client
	data for the foreign class :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = client_id
 foreignTab    = contact_person
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
	private Client clientId;
        private Broker brokerId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="contact_person_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getContactPersonId(){
		return contactPersonId;
	}
	public void setContactPersonId(java.lang.Integer value){
		contactPersonId = value;
	}
			// PK GETTER SETTER END

						@Column(name="name")
	public java.lang.String getName(){
		return name;
	}
	public void setName(java.lang.String value){
		name = value;
	}
				@Column(name="telephone")
	public java.lang.String getTelephone(){
		return telephone;
	}
	public void setTelephone(java.lang.String value){
		telephone = value;
	}
				@Column(name="handphone")
	public java.lang.String getHandphone(){
		return handphone;
	}
	public void setHandphone(java.lang.String value){
		handphone = value;
	}
				@Column(name="email")
	public java.lang.String getEmail(){
		return email;
	}
	public void setEmail(java.lang.String value){
		email = value;
	}
				@Column(name="department")
	public java.lang.String getDepartment(){
		return department;
	}
	public void setDepartment(java.lang.String value){
		department = value;
	}
				@Column(name="job_position")
	public java.lang.String getJobPosition(){
		return jobPosition;
	}
	public void setJobPosition(java.lang.String value){
		jobPosition = value;
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
	
	@Column(name="payment_officer")
	public Integer getPaymentOfficer() {
		return paymentOfficer;
	}
	public void setPaymentOfficer(Integer paymentOfficer) {
		this.paymentOfficer = paymentOfficer;
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
				/** MemberGroup */
	@ManyToOne
	@JoinColumn(name="member_group_id")
	public MemberGroup getMemberGroupId(){
		return this.memberGroupId;
	}

	/** MemberGroup */
	public void setMemberGroupId(MemberGroup obj){
		this.memberGroupId = obj;
	}
				/** Client */
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId(){
		return this.clientId;
	}

	/** Client */
	public void setClientId(Client obj){
		this.clientId = obj;
	}
			
	// foreign affairs end

// exported key

        
	
		
	//exported key end

        @ManyToOne
        @JoinColumn(name="broker_id")
    public Broker getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Broker brokerId) {
        this.brokerId = brokerId;
    }



}