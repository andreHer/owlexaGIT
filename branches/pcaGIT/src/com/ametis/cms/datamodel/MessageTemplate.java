
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="message_template")
public class MessageTemplate implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int MEMBER_BATCH_RECEIVED_EMAIL = 1;
	public static final int MEMBER_CDV_ISSUED_EMAIL = 2;
	public static final int MEMBER_CLAIM_PAID_EMAIL = 3;
	
	public static final int POLICY_FUND_WARNING_EMAIL = 4;
	public static final int POLICY_FUND_BLOCK_EMAIL = 5;
	
	public static final int POLICY_EXCESS_WARNING_EMAIL = 6;
	public static final int POLICY_EXCESS_BLOCK_EMAIL = 7;
	
	public static final int POLICY_FUND_COV_WARNING_EMAIL = 8;
	public static final int POLICY_FUND_COV_BLOCK_EMAIL = 9;
	
	public static final int POLICY_EXCESS_COV_WARNING_EMAIL = 10;
	public static final int POLICY_EXCESS_COV_BLOCK_EMAIL = 11;

	//Fields
		
	/**data for the column :
	
 --------- message_template.id --------- 
 schema        = null
 tableName     = message_template
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private Integer id;
			
	/**data for the column :
	
 --------- message_template.template_name --------- 
 schema        = null
 tableName     = message_template
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String templateName;
			
	/**data for the column :
	
 --------- message_template.template_url --------- 
 schema        = null
 tableName     = message_template
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String templateUrl;
			
	/**data for the column :
	
 --------- message_template.description --------- 
 schema        = null
 tableName     = message_template
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
	private String subject;
		
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

						@Column(name="template_name")
	public java.lang.String getTemplateName(){
		return templateName;
	}
	public void setTemplateName(java.lang.String value){
		templateName = value;
	}
				@Column(name="template_url")
	public java.lang.String getTemplateUrl(){
		return templateUrl;
	}
	public void setTemplateUrl(java.lang.String value){
		templateUrl = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
	@Column(name="subject")
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
		
	

}