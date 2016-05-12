
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="external_raw_detail_data")
public class ExternalRawDetailData implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- external_raw_detail_data.id --------- 
 schema        = null
 tableName     = external_raw_detail_data
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 15
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer id;
			
	/**data for the column :
	
 --------- external_raw_detail_data.data --------- 
 schema        = null
 tableName     = external_raw_detail_data
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String data;
			
	/**data for the column :
	
 --------- external_raw_detail_data.is_migrated --------- 
 schema        = null
 tableName     = external_raw_detail_data
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer isMigrated;
			
	/**data for the column :
	
 --------- external_raw_detail_data.template_id --------- 
 schema        = null
 tableName     = external_raw_detail_data
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private ExportImportTemplate templateId;
        private Integer importSessionId;
		
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

						@Column(name="data")
	public java.lang.String getData(){
		return data;
	}
	public void setData(java.lang.String value){
		data = value;
	}
				@Column(name="is_migrated")
	public java.lang.Integer getIsMigrated(){
		return isMigrated;
	}
	public void setIsMigrated(java.lang.Integer value){
		isMigrated = value;
	}
        @ManyToOne
				@JoinColumn(name="template_id")
	public ExportImportTemplate getTemplateId(){
		return templateId;
	}
	public void setTemplateId(ExportImportTemplate value){
		templateId = value;
	}
		
        
        
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end

        @Column(name="import_session_id")
    public Integer getImportSessionId() {
        return importSessionId;
    }

    public void setImportSessionId(Integer importSessionId) {
        this.importSessionId = importSessionId;
    }



}