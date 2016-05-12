<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>


<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>
<script type="text/javascript" src="scripts/owlexa.function.js"></script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Member Group </h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>	

<form action="membergroup-form" method="POST" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
<spring:bind path="memberGroupForm.memberGroupId">
								<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
								<div id="fredcaption">
									<c:out value="${status.errorMessage}" />
								</div>
							</spring:bind>

<%String alert = (String) request.getAttribute("alert");
			if (alert != null && !alert.trim().equals("")) {%>
							<div id="warning">
								<c:out value="${alert}"></c:out>
							</div>
							<%}%>
	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td class="dataLabel"></td>
				<td class="dataField"></td>
				<td class="dataLabel"></td>
				<td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel">Group Name *:</td>
				<td class="dataField">
					<spring:bind path="memberGroupForm.groupName">
						<input type="text" size="35" id="memberGroupName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
				<td class="dataLabel">Client *:</td>
				<td class="dataField">
					<spring:bind path="memberGroupForm.clientName">
						<input type="text"  id="clientName" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />						
					</spring:bind>
					<spring:bind path="memberGroupForm.clientId">
						<input type="hidden" id="clientId" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
			</tr>
			<tr>
				<td class="dataLabel">Business Category *:</td>
				<td class="dataField">
					<spring:bind path="memberGroupForm.businessCategoryId">
						<select name="<c:out value="${status.expression}" />" >
							<option value="">-- SELECT ONE --</option>
							<c:forEach items="${businessCategories}" var="businessCategory">							
								<option value="${businessCategory.businessCategoryId}" <c:if test="${businessCategory.businessCategoryId eq status.value}">selected</c:if>><c:out value="${businessCategory.businessCategoryName}" /></option>
							</c:forEach>
						</select>
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
				<td class="dataLabel">Group Type *:</td>
				<td class="dataField">
					<spring:bind path="memberGroupForm.tipe">
						<select name="<c:out value="${status.expression}" />">
							<option value="G" <c:if test="${status.value eq 'G' }">selected</c:if>>GROUP ACCOUNT</option>
							<option value="I" <c:if test="${status.value eq 'I' }">selected</c:if>>INDIVIDUAL ACCOUNT</option>
							
						</select>
					</spring:bind>
				</td>
			</tr>
			<tr>
				<td class="dataLabel"></td>
				<td class="dataField"></td>
				<td class="dataLabel">&nbsp;</td>
				<td class="dataField"></td>
			</tr>
						<tr>
						
						<td class="dataLabel">
								Country :
							</td>


							<td class="dataField">
								<spring:bind path="memberGroupForm.country">
									<input type="text" size="35" id="countryName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
									<input type="hidden" id="countryId" name="countryId" value="" />
								</spring:bind>
							</td>
							
							<td class="dataLabel">
								Telephone :
							</td>


							<td class="dataField">
								<spring:bind path="memberGroupForm.telephone">
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
								</spring:bind>
							</td>
						</tr>
						<tr>
							<td class="dataLabel">
								Province :
							</td>


							<td class="dataField">
								<spring:bind path="memberGroupForm.province">
									<input type="text" size="35" id="provinceName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
									<input type="hidden" id="provinceId" name="provinceId" value="" />
								</spring:bind>
							</td>
							<td class="dataLabel">
								Faximile :
							</td>

							<td class="dataField">
								<spring:bind path="memberGroupForm.faximile">
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
								City :
							</td>


							<td class="dataField">
								<spring:bind path="memberGroupForm.city">
									<input type="text" size="35" id="cityName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
									<input type="hidden" id="cityId" name="cityId" value="" />
								</spring:bind>
							</td>

							<td class="dataLabel">
								Website :
							</td>


							<td class="dataField">
								<spring:bind path="memberGroupForm.website">
									<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
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
								<spring:bind path="memberGroupForm.postalCode">
									<input type="text"  onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
							<td class="dataLabel">
								Email :
							</td>


							<td class="dataField">
								<spring:bind path="memberGroupForm.email">
									<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						</tr>




						<tr>
							<td class="dataLabel">
								&nbsp;
							</td>


							<td class="dataField">
								&nbsp;
							</td>
							<td class="dataLabel"></td>
							<td class="dataField"></td>
						</tr>





						<tr>
							<td class="dataLabel">
								Bank Account *:
							</td>


							<td class="dataField">
								<spring:bind path="memberGroupForm.bankAccount">
									<input type="text" onkeypress="return isNumberKey(this,event)" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
							<td class="dataLabel">
								Bank *:
							</td>


							<td class="dataField">
								<spring:bind path="memberGroupForm.bank">
									<input type="text" size="35" id="bankName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						</tr>





						<tr>
							<td class="dataLabel">
								Bank Account Name *:
							</td>


							<td class="dataField">
								<spring:bind path="memberGroupForm.bankAccountName">
									<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
							<td class="dataLabel">
								Bank Branch:
							</td>


							<td class="dataField">
								<spring:bind path="memberGroupForm.bankBranch">
									<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						</tr>







						<tr>
							<td class="dataLabel">
								Address :
							</td>


							<td class="dataField" colspan="3">
								<spring:bind path="memberGroupForm.address">
									<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>

						</tr>
						<tr>
							<td class="dataLabel">
								
							</td>


							<td class="dataField">
								
							</td>
							<td class="dataLabel">
							
							</td>
							<td class="dataField">
								
								
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
					<input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>

</form>

<script language="javascript">
var temp=false;
$(document).ready(function(){
    
    
   
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
        $("#cityId").val(value.id);
         $("#cityCode").val(value.number);
      	var a=$("#cityCode").val();
        var b=a.substring(1);
     //   alert("test"+b);
        $("#telephone").val($("#countryCode").val()+"-"+b+"-");
        $("#faximile").val($("#countryCode").val()+"-"+b+"-");
        temp=true;
        
    });
   
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
        $("#countryId").val(value.id);
        
        $("#countryCode").val(value.number);
        $("#telephone").val($("#countryCode").val()+"-");
        
        $("#faximile").val($("#countryCode").val()+"-");
        temp=false;
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
        $("#provinceId").val(value.id);
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
 });
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "membergroup-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "membergroup";
		document.form1.submit();
	}
	function cancel (){
		<c:if test="${navigation eq 'update'}">
		document.form1.navigation.value = "detail";
		</c:if>
		document.form1.action = "membergroup";
		document.form1.submit();
	}

				// forreign affairs end
</script>
