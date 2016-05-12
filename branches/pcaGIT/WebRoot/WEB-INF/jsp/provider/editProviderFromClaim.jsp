<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<!-- <link rel="stylesheet" type="text/css" href="css/autocomplete.css"> -->
<link rel="stylesheet" type="text/css" href="css/button.css">

<!-- <link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/> -->

<!-- <script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script> -->
<!-- <script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script> -->

<script type="text/javascript" src="scripts/owlexa.function.js"></script>	

	<style>
		.ui-autocomplete-loading {
		  background: white url("images/owlexa/ui-anim_basic_16x16.gif") right center no-repeat;
		}
	</style>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Provider </h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="provider-form" method="POST" name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="${navigation}">
	<input type="hidden" name="memberId" value="<c:out value="${memberId}" />" />

	

	<spring:bind path="providerForm.providerId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />"><c:out value="${status.value} "/>
	</spring:bind>

	<%
		String alert = (String) request.getAttribute("alert");
		if (alert != null && !alert.trim().equals("")) {
	%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%
		}
	%>

	<table class="tabForm" border="0" cellpadding="0" cellspacing="0"
		width="100%">
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
									Provider Name * :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.providerName">
										<input size="35" type="text"
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
									Provider Category * :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.providerCategoryId">
										<select name="<c:out value="${status.expression}"/>">
											<c:forEach items="${providerCategory}" var="category">
												<option
													value="<c:out value="${category.providerCategoryId}" />"
													<c:if test="${category.providerCategoryId eq status.value}">selected</c:if>>
													<c:out value="${category.providerCategoryName}" />
												</option>
											</c:forEach>
										</select>

										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>
							<tr>
							<td class="dataLabel">
									Country * :
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.country">
										<input size="35" type="text" id="countryName"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
										<input type="hidden" id="countryId" name="countryId" value="" />
									</spring:bind>
								</td>
								</tr>
								
								<tr>
								<td class="dataLabel">
									Province * :
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.province">
										<input size="35" type="text" id="provinceName"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									<input type="hidden" id="provinceId" name="provinceId" value="" />
								</td>
								</tr>								
								<tr>
								<td class="dataLabel">
									City * :
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.city">
										<input size="35" type="text" id="cityName"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									<input type="hidden" id="cityId" name="cityId" value="" />
								</td>
								</tr>
								<tr>
								<td class="dataLabel">
									Address :
								</td>

								<td class="dataField" >
									<spring:bind path="providerForm.address">
										<textarea cols="50" rows="8" class="inputbox"
											name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
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
				</td>
			</tr>
		</tbody>
	</table>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-top: 2px;">
					<input type="hidden" name="notyet" value="">
					<input title="Save [Alt+Shift+S]" accesskey="S"	class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"	onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C"	class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"	onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>
</form>
<div id="mapDialog" style="display:none">
	<div class="mapCanvas" style="height:100%"></div>
</div>

<script type="text/javascript">
var temp=false;
$(document).ready(function(){
    
    /* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
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
    */
    
    $("#parentProviderName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "provider?navigation=lookupjson",
				dataType: "json",
				data: {q: request.term},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {
				$(this).parents("dd").find(".error_message").hide();        
				//alert("parentProviderName : " + ui.item.label + "\nparentProviderId : " + ui.item.id);
	    		$("#parentProviderId").val(ui.item.id);
	    		
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
    
    /* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
    $("#parentProviderName").autocomplete("provider?navigation=lookupjson", {
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
	    $("#parentProviderId").val(value.id);
    });
    */
    
    $("#countryName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "refcountry?navigation=lookupjson",
				dataType: "json",
				data: {q: request.term},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {
				$(this).parents("dd").find(".error_message").hide();
		        //include the countryId for filtering
		        //alert("CountryName : " + ui.item.label + "\nCountryID : " + ui.item.id + "\nCountryCode : " + ui.item.number);
		        $("#countryId").val(ui.item.id);
		        $("#countryCode").val(ui.item.number);
		        $("#telephone").val($("#countryCode").val()+"-");
		        
		        $("#faximile").val($("#countryCode").val()+"-");
		        temp=false;
		        //alert(value.id);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
    
    /* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
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
    */
    
    $("#provinceName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "refprovince?navigation=lookupjson",
				dataType: "json",
				data: {
					q: request.term,
					countryId : $("#countryId").val()
				},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {
				$(this).parents("dd").find(".error_message").hide();
		        //alert("ProvinceName : " + ui.item.label + "\nProvinceID : " + ui.item.id + "\nProvinceCode : " + ui.item.number);
		        $("#provinceId").val(ui.item.id);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
    
    /* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
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
    */
    
    $("#cityName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "refcity?navigation=lookupjson",
				dataType: "json",
				data: {
					q: request.term,
					provinceId : $("#provinceId").val()
				},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {		        
		        $(this).parents("dd").find(".error_message").hide();
		      	//alert("CityName : " + ui.item.label + "\nCityID : " + ui.item.id + "\nCityCode : " + ui.item.number);
		        $("#cityId").val(ui.item.id);
		        $("#cityCode").val(ui.item.number);
		      	var a=$("#cityCode").val();
		        var b=a.substring(1);
		     //   alert("test"+b);
		        $("#telephone").val($("#countryCode").val()+"-"+b+"-");
		        
		        $("#faximile").val($("#countryCode").val()+"-"+b+"-");
		        temp=true;
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
    
    /* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
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
      // alert(value.toSource());
        $("#cityId").val(value.id);
        $("#cityCode").val(value.number);
      	var a=$("#cityCode").val();
        var b=a.substring(1);
     //   alert("test"+b);
        $("#telephone").val($("#countryCode").val()+"-"+b+"-");
        
        $("#faximile").val($("#countryCode").val()+"-"+b+"-");
        temp=true;
    });
    */
    
//       var temp = $("#alertEdcProv").val();
// 		if($("#alertEdcProv").val() != null || temp.length > 0)
// 			alert($("#alertEdcProv").val());
 });
 
	function simpan (){
		document.form1.method = "POST";
		document.form1.action = "providerclaim-form";
		document.form1.submit();
	}
	
	function simpanProviderClaim (){

		document.form1.method = "POST";
		document.form1.action = "providerclaim-form";
		document.form1.submit();
	}
	function cancel(){
		<c:choose>
			<c:when test="${navigation eq 'update'}">
					document.form1.action = "provider";
					document.form1.navigation.value = "detail";		
			</c:when>
			<c:when test="${navigation eq 'listpolicy'}">
					document.form1.action = "provider";
					document.form1.policyId.value = <c:out value="${policyId}" />				
					document.form1.navigation.value = "listpolicy";		
			</c:when>
			<c:when test="${navigation eq 'tambahprovider'}">
					document.form1.action = "provider";
					document.form1.navigation.value = "listmember";		
					document.form1.memberId.value = <c:out value="${memberId}" />
			</c:when>
			<c:otherwise>
					document.form1.action = "provider";
					document.form1.memberId.value = <c:out value="${policyId}" />				
					document.form1.navigation.value = "listpolicy";		
			</c:otherwise>
		</c:choose>
		document.form1.submit();
		
		
	}

</script>
