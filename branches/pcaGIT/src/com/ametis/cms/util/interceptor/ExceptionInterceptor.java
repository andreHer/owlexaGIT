
package com.ametis.cms.util.interceptor;


import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;



// imports+ 

// imports- 


public class ExceptionInterceptor implements ThrowsAdvice

// extends+ 

// extends- 
{
	public void afterThrowing(Method method,Object obj[],Object target,Throwable th){
		System.out.println(method+" -- "+target+" --- "+th);
		if (obj != null){
			for (int i = 0; i < obj.length; i++) {
				System.out.println(obj[i]);
			}
		}
	}	
// class+ 

// class- 
}
