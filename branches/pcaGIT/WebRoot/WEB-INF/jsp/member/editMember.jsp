<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>
<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>

<script type="text/javascript" src="scripts/owlexa.function.js"></script>

<form action="member-form" method="POST" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">

	<%String alert = (String) request.getAttribute("alert");
			if (alert != null && !alert.trim().equals("")) {%>
							<div id="warning">
								<c:out value="${alert}"></c:out>
							</div>
							<%}%>
							
		<spring:bind path="memberForm.memberId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>
	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
						<tr>
							<td class="dataLabel"></td>
							<td class="dataField"></td>
							<td class="dataLabel"></td>
							<td class="dataField"></td>
						</tr>
						 
						<tr>							
							<td class="dataLabel">
								Client :
							</td>
								<td class="dataField">
									<spring:bind path="memberForm.clientId">
										<input type="hidden" id="clientId" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
									</spring:bind>
									<spring:bind path="memberForm.clientName">
										<input type="text" id="clientName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35"/>										
									</spring:bind>
								</td>
							
								<td class="dataLabel">
									Member Group :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.memberGroupId">
										<input type="hidden" id="memberGroupId" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />										
									</spring:bind>
									<spring:bind path="memberForm.groupName">
										<input type="text" id="memberGroupName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size=35/>										
									</spring:bind>
								</td>
								
								

							</tr>
							
							
							<tr>							
								<td class="dataLabel"></td>
								<td class="dataField"></td>							
								<td class="dataLabel">
									Policy Number :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.currentPolicyId">
										<input type="hidden" id="currentPolicyId" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />										
									</spring:bind>
									<spring:bind path="memberForm.currentPolicyNumber">
										<input type="text" id="currentPolicyNumber" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size=35/>										
									</spring:bind>
								</td>
								
								

							</tr>
							<tr>
							
								<td class="dataLabel">
									Member Name :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.firstName">
										<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
											
										</div>
									</spring:bind>
								</td>
								
								
								
								<td class="dataLabel">
									Birthday :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.birthday">
										<input type="text" size="25" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="30">
										<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
										<script type="text/javascript">
					    					Calendar.setup({
					        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
					        					ifFormat       :    "%Y-%m-%d",      // format of the input field
					        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
					        					align          :    "Bl",           // alignment (defaults to "Bl")
					        					singleClick    :    true
					    					});
									 	</script>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							
								
							</tr>






							<tr>
								<td class="dataLabel">
									Customer Number :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.customerPolicyNumber">
										<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								
								
								
								
								<td class="dataLabel">
									Join Date :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.joinDate">
										<input type="text" size="25" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="30">
										<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
										<script type="text/javascript">
					    					Calendar.setup({
					        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
					        					ifFormat       :    "%Y-%m-%d",      // format of the input field
					        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
					        					align          :    "Bl",           // alignment (defaults to "Bl")
					        					singleClick    :    true
					    					});
									 	</script>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								
							</tr>







							<tr>								
								<td class="dataLabel">
								
									Insurance Product :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.currentProductCode">
										<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" /> <input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupProduct()">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								
								
								
								<td class="dataLabel">
									Effective Date :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.effectiveDate">
										<input type="text" size="25" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="30">
										<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
										<script type="text/javascript">
					    					Calendar.setup({
					        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
					        					ifFormat       :    "%Y-%m-%d",      // format of the input field
					        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
					        					align          :    "Bl",           // alignment (defaults to "Bl")
					        					singleClick    :    true
					    					});
									 	</script>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								
							</tr>

							<tr>
								<td class="dataLabel">								
									EDC Card Number :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.memberEDCNumber">
										<input type="text" size="35" onkeypress="return isNumberKey(this,event);" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" /> 
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
						<td class="dataLabel">								
									Expire Date :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.expireDate">
										<input type="text" size="25" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="30">
										<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
										<script type="text/javascript">
					    					Calendar.setup({
					        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
					        					ifFormat       :    "%Y-%m-%d",      // format of the input field
					        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
					        					align          :    "Bl",           // alignment (defaults to "Bl")
					        					singleClick    :    true
					    					});
									 	</script>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>




							<tr>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>								
							</tr>
							<tr>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>




						









							<tr>
								<td class="dataLabel">
									Country :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.country">
										<input type="text" size="35" id="countryName" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
										<input type="hidden" id="countryId" name="countryId" value="" />
									
									</spring:bind>
								</td>
							
								
								
								<td class="dataLabel">
									Mobile Phone :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.mobilePhone">
										<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							
							</tr>









							<tr>
								<td class="dataLabel">
									Province :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.province">
										<input type="text" size="35" id="provinceName" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									<input type="hidden" id="provinceId" name="provinceId" value="" />
								
									</spring:bind>
								</td>
																
								
								
								<td class="dataLabel">
									Telephone :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.telephone">
									
									<input type="hidden" id="countryCode" 
											name=""
											value="" />
										<input type="hidden" id="cityCode" 
											name=""
											value="" />
										<input type="text" id="telephone" onkeypress="return isBackspace(this,event,$('#countryCode').val(),$('#cityCode').val(),temp)"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
										</div>
									</spring:bind>
								</td>
									
							</tr>






							<tr>
																
								<td class="dataLabel">
									City :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.city">
										<input type="text" size="35" id="cityName" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									<input type="hidden" id="cityId" name="cityId" value="" />
								
									</spring:bind>
								</td>
							
							
								
								
								
								<td class="dataLabel">
									Faximile :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.faximile">
										<input type="hidden" id="countryCode" 
											name=""
											value="" />
										<input type="hidden" id="cityCode" 
											name=""
											value="" />
										<input type="text" id="faximile" onkeypress="return isBackspace(this,event,$('#countryCode').val(),$('#cityCode').val(),temp)"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								
							</tr>
							
							
							<tr>
							<td class="dataLabel">
									Postal Code :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.postalCode">
										<input type="text" size="35" onkeypress="return isNumberKey(this,event);" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							
								
								
								
								<td class="dataLabel">
									Email :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.email">
										<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								
							</tr>
								
							<tr>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
							
							<tr>
								
								
								
								<td class="dataLabel">
									Policy Agent :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.policyAgent">
										<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>





							<tr>
								<td class="dataLabel">
									Bank Account No  :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.bankAccount">
										<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								
								
								
									
								<td class="dataLabel">
									Department :
								</td>


								<td class="dataField">
									<spring:bind path="memberForm.department">
										<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								
							</tr>


								<tr>
								<td class="dataLabel">
									Bank Account Name :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.bankAccountName">
										<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								
								
								
								
									
								<td class="dataLabel">
									Job Position :
								</td>


								<td class="dataField">
									<spring:bind path="memberForm.jobPosition">
										<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								
							</tr>
							



							<tr>
								<td class="dataLabel">
									Bank :
								</td>
								<td class="dataField">
									<spring:bind path="memberForm.bank">
										<input type="text" size="35" id="bankName" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>




							<tr>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>	
							</tr>
							<tr>
								<td class="dataLabel">
									Address :
								</td>
								<td class="dataField" colspan=3>
									<spring:bind path="memberForm.address">
										<textarea cols="50" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
						</tbody>						
					</table>					
				</td>
			</tr>			
		</tbody>			
	</table>
	
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-top: 2px;">
					<input type="hidden" name="notyet" value="">
					<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>

</form>

<script language="javascript">
var temp=false;
$(document).ready(function(){
    
    
    $("#clientName").autocomplete("client?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#clientId").val(value.id);
    });
    
    $("#memberGroupName").autocomplete("membergroup?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.number +" - </font>" +    "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#memberGroupId").val(value.id);        
    });
    
    $("#currentPolicyNumber").autocomplete("policy?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.number +" - </font>" +    "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#currentPolicyId").val(value.id);        
    });
    $("#bankName").autocomplete("bank?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
    });
    
    $("#countryName").autocomplete("refcountry?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        //include the countryId for filtering
        //alert(value.toSource());
        $("#countryId").val(value.id);
        
        $("#countryCode").val(value.number);
        $("#telephone").val($("#countryCode").val()+"-");
        
        $("#faximile").val($("#countryCode").val()+"-");
        temp=false;
        //alert(value.id);
    });
    
    $("#provinceName").autocomplete("refprovince?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.name +"</font>" ;
        },
        extraParams: {
       		countryId: function() { return $("#countryId").val(); }
   		}
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        //alert(value.toSource());
        $("#provinceId").val(value.id);
    });
    
    $("#cityName").autocomplete("refcity?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.name +"</font>" ;
        },
        extraParams: {
       		provinceId: function() { return $("#provinceId").val(); }
   		}
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        //alert(value.toSource());
        $("#cityId").val(value.id);
         $("#cityCode").val(value.number);
      	var a=$("#cityCode").val();
        var b=a.substring(1);
     //   alert("test"+b);
        $("#telephone").val($("#countryCode").val()+"-"+b+"-");
        
        $("#faximile").val($("#countryCode").val()+"-"+b+"-");
        temp=true;
    });
    
});

	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "member-form";
		document.form1.submit();
	}
	function kembali (){
		<c:if test="${navigation eq 'update'}">
		document.form1.navigation.value = "detail";
		</c:if>
		<c:if test="${navigation eq 'tambah'}">
		document.form1.navigation.value = "back";
		</c:if>
		document.form1.action = "member";
		document.form1.submit();
	}

	function lookupProduct(){
		window.open ("product?navigation=lookup&url=member-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function appendProduct (productCode){
		var currentProduct = document.form1.currentProductCode.value;
		
		currentProduct += " " + productCode;
		
		document.form1.currentProductCode.value = currentProduct;
	}
	
</script>
