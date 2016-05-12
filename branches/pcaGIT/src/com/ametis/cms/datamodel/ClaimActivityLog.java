
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="claim_activity_log")
public class ClaimActivityLog implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- claim_activity_log.id --------- 
 schema        = null
 tableName     = claim_activity_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 15
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long id;
			
	/**data for the column :
	
 --------- claim_activity_log.activity_time --------- 
 schema        = null
 tableName     = claim_activity_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp activityTime;
			
	/**data for the column :
	
 --------- claim_activity_log.username --------- 
 schema        = null
 tableName     = claim_activity_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
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
	private String username;
			
	/**data for the column :
	
 --------- claim_activity_log.claim_id --------- 
 schema        = null
 tableName     = claim_activity_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
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
	private Integer claimId;
			
	/**data for the column :
	
 --------- claim_activity_log.action_type --------- 
 schema        = null
 tableName     = claim_activity_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String actionType;
			
	/**data for the column :
	
 --------- claim_activity_log.before_action_claim_data --------- 
 schema        = null
 tableName     = claim_activity_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String beforeActionClaimData;
			
	/**data for the column :
	
 --------- claim_activity_log.after_action_claim_data --------- 
 schema        = null
 tableName     = claim_activity_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String afterActionClaimData;
			
	/**data for the column :
	
 --------- claim_activity_log.ip_address --------- 
 schema        = null
 tableName     = claim_activity_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
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
	private String ipAddress;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getId(){
		return id;
	}
	public void setId(java.lang.Long value){
		id = value;
	}
			// PK GETTER SETTER END

						@Column(name="activity_time")
	public java.sql.Timestamp getActivityTime(){
		return activityTime;
	}
	public void setActivityTime(java.sql.Timestamp value){
		activityTime = value;
	}
				@Column(name="username")
	public java.lang.String getUsername(){
		return username;
	}
	public void setUsername(java.lang.String value){
		username = value;
	}
				@Column(name="claim_id")
	public java.lang.Integer getClaimId(){
		return claimId;
	}
	public void setClaimId(java.lang.Integer value){
		claimId = value;
	}
				@Column(name="action_type")
	public java.lang.String getActionType(){
		return actionType;
	}
	public void setActionType(java.lang.String value){
		actionType = value;
	}
				@Column(name="before_action_claim_data")
	public java.lang.String getBeforeActionClaimData(){
		return beforeActionClaimData;
	}
	public void setBeforeActionClaimData(java.lang.String value){
		beforeActionClaimData = value;
	}
				@Column(name="after_action_claim_data")
	public java.lang.String getAfterActionClaimData(){
		return afterActionClaimData;
	}
	public void setAfterActionClaimData(java.lang.String value){
		afterActionClaimData = value;
	}
				@Column(name="ip_address")
	public java.lang.String getIpAddress(){
		return ipAddress;
	}
	public void setIpAddress(java.lang.String value){
		ipAddress = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}