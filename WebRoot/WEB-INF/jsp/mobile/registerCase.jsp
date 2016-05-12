<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<html>
	<head>	
	    
	    <link href="css/mobile/stylesheets.css" rel="stylesheet" type="text/css" />	    
		<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>
		
		<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>
		
		
		<script type='text/javascript' src='dwr/interface/AJAXCaseService.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
	</head>
	<body>
		<form name="form1" method="POST" action="mobile">
		
			<input type="hidden" name="navigation" value="doregistercase" />
			<input type="hidden" name="memberId" value="<c:out value="${memberId}" />" />
			<input type="hidden" name="login" value="<c:out value="${login}" />" />
			<input type="hidden" name="password" value="<c:out value="${password}" />" />
			<div class="row-fluid">
	            <div class="span6">
	                <div class="block">                                   
	                    <div class="data-fluid">                        
	                        <div class="row-form">
	                            <div class="span3">Name / Member No. :</div>
	                            <div class="span5"><input type="text" value="<c:out value="${member.firstName }" /> / <c:out value="${member.customerPolicyNumber }" />" readonly="readonly"/></div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Company:</div>
	                            <div class="span5"><input type="text" value="<c:out value="${member.memberGroupId.groupName }" />" readonly="readonly"/></div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Status / Expire Date:</div>
	                            <div class="span5"><input type="text" value="<c:out value="${status}" /> / <c:out value="${expireDate}" />" readonly="readonly"/></div>
	                        </div>      
	                    </div>
	                </div>
	            </div>            
	        </div>
	        <div class="row-fluid">
	            <div class="span6">
	                <div class="block">                                   
	                    <div class="data-fluid">                        
	                        <table cellpadding="0" cellspacing="0" width="100%" class="table">
								<thead>
									<tr>
										<th width="2%" style="background-color: #EEDDDD;">No</th>
										<th width="25%" style="background-color: #EEDDDD;">Benefit Name</th>
										<th width="70%" style="background-color: #EEDDDD;">Description</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${benefitList}" var="benefit" varStatus="index">
										<tr>
											<td><c:out value="${index.index+1}" />.</td>
											<td><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></td>
											<td><c:out value="${benefit.information}" /></td>
										</tr>		
									</c:forEach>	
								</tbody>
							</table>   
	                    </div>
	                </div>
	            </div>            
	        </div>
	        <c:if test="${theUser.userType eq 4 and (theUser.providerId.providerCategoryId.providerCategoryId eq 2 or theUser.providerId.providerCategoryId.providerCategoryId eq 1) }">
	        <div class="row-fluid">
	            <div class="span6">
	                <div class="block">                                   
	                    <div class="data-fluid">
	                        <div class="row-form">
	                            <div class="span3">Poliklinik :</div>
	                            <div class="span5">
	                            	<select name="poliklinikId">
	                            		<option value=""> -- PILIH SALAH SATU -- </option>
	                            		<c:forEach items="${poliList}" var="poli">
	                            			<option value="<c:out value="${poli.poliklinikId }" />"><c:out value="${poli.poliklinikName}" /></option>
	                            		</c:forEach>
	                            	</select>
	                            </div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Doctor :</div>
	                            <div class="span5">
	                            	<select name="doctorId">
	                            		<option value=""> -- PILIH SALAH SATU -- </option>
	                            		<c:forEach items="${doctorList }" var="doctor">
	                            			<option value=""><c:out value="${doctor}" /></option>
	                            		</c:forEach>
	                            	</select>
	                            </div>
	                        </div>      
	                    </div>
	                </div>
	            </div>            
	        </div>
	        </c:if>
	        <div class="row-fluid">
	            <div class="span6">                
					<div class="row-form">
	                	<button class="btn btn-primary" type="button" onclick="javascript:registerCase();">Save</button>
	                	<button class="btn btn-danger" type="button" onclick="javascript:detailMember();">Cancel</button>
	                </div>
	            </div>	            
	        </div>
        </form>
     </body>
</html>     
