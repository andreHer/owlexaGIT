
package com.ametis.cms.util.interceptor;


import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.ametis.cms.datamodel.RoleAction;
import com.ametis.cms.datamodel.User;



// imports+ 

// imports- 

public class DebugInterceptor implements MethodInterceptor

// extends+ 

// extends- 
{
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method methodInstance = invocation.getMethod();
		String methodeName = methodInstance.getName();
		Class classInstance = methodInstance.getDeclaringClass();
		String className = classInstance.getSimpleName();
		int index = className.lastIndexOf("Service");
		String actionName = className.substring(0,index);
		Object rval=null;
		if(methodeName.startsWith("get")|| methodeName.startsWith("search")){
			rval = invocation.proceed();
		}else if(methodeName.startsWith("create")|| methodeName.startsWith("delete")||methodeName.startsWith("update")){
			Object[] arguments = invocation.getArguments();
			User user = (User)arguments[1];
			
			if (user != null){
				Set roleAct = user.getRoleId().getRoleActions();
				for (Iterator iter = roleAct.iterator(); iter.hasNext();) {
					RoleAction element = (RoleAction) iter.next();
					if (element.getActionId().getActionName().equalsIgnoreCase(actionName)
						&& ((element.getHapus().intValue()==1) && methodeName.startsWith("delete"))) {
						rval = invocation.proceed();
						System.out.println("delete "+arguments[0]+" oleh "+user.getEmail());
						break;
					}else if (element.getActionId().getActionName().equalsIgnoreCase(actionName)
						&& ((element.getTambah().intValue()==1) && methodeName.startsWith("create"))) {
						rval = invocation.proceed();
						System.out.println("create "+arguments[0]+" oleh "+user.getEmail());
						break;
					}else if (element.getActionId().getActionName().equalsIgnoreCase(actionName)
						&& ((element.getUbah().intValue()==1) && methodeName.startsWith("update"))) {
						rval = invocation.proceed();
						System.out.println("update "+arguments[0]+" oleh "+user.getEmail());
						break;
					}
				}
			}
			Class returnType = methodInstance.getReturnType();
			if(rval==null){
				System.out.println("Ga punya akses!");
				if(returnType.getSimpleName().equals("boolean")){
					rval = new Boolean(false);
				}else{
					rval = returnType.newInstance();
				}
			}
		}
		return rval;
	}
// class+ 

// class- 
}
