package com.ametis.cms.web.ajax;

import java.util.Collection;
import java.util.Iterator;

import com.ametis.cms.datamodel.User;
import com.ametis.cms.datamodel.Role;
import com.ametis.cms.service.RoleService;

public class AJAXRoleService {
	private RoleService roleService;

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(
			RoleService roleService) {
		this.roleService = roleService;
	}

	public String getRole (int userType){
		String result = null;
		
		try {
			Collection<Role> tmp = null;
			if (userType == User.MEMBER_GROUP){
				tmp = roleService.getMemberGroupRole();
			}
//			else if (userType == User.TPA){
//				tmp = roleService.getTPARole();
//			}
//			else if (userType == User.CLIENT){
//				tmp = roleService.getClientRole();
//			}
//			else if (userType == User.PROVIDER){
//				tmp = roleService.getProviderRole();
//			}
//			else if (userType == User.BROKER){
//				tmp = roleService.getBrokerRole();
//			}
//			else if (userType == User.MANAGEMENT){
//				tmp = roleService.getManagementRole();
//			}
//			else if (userType == User.MEMBER){
//				tmp = roleService.getMemberRole();
//			}
			else {
				tmp = roleService.getAll();
			}
			
			if (tmp != null ){
				Iterator<Role> iterator = tmp.iterator();
				result = "";
				
				Role role = null;
				
				while (iterator.hasNext()){
					role = iterator.next();
					
					result += "<option value='"+role.getRoleId()+"'>"+role.getRoleName()+"</option>";
				}
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
