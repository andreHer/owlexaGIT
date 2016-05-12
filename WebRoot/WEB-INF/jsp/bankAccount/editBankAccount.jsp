<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<!-- <link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/> -->
<!-- <script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script> -->
<!-- <script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script> -->

	<style>
		.ui-autocomplete-loading {
		  background: white url("images/owlexa/ui-anim_basic_16x16.gif") right center no-repeat;
		}
	</style>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Create Bank Account</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="bankaccount-form" method="POST"  name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="${navigation}">
	<input type="hidden" name="providerId" value="${providerId}">
	<input type="hidden" name="memberGroupId" value="${memberGroupId}">
	<input type="hidden" name="clientId" value="${clientId}">
	
    <spring:bind path="bankAccountForm.tipe">
        <input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
    </spring:bind>
    <spring:bind path="bankAccountForm.refId">
        <input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
    </spring:bind>
    <spring:bind path="bankAccountForm.previousURL">
        <input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
    </spring:bind>

    <table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
            <tr>
                <td>
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tbody>

                        <%
                            String alert = (String) request.getAttribute("alert");
                            if (alert != null && !alert.trim().equals("")) {%>
                        <div id="warning">
                            <c:out value="${alert}"></c:out>
                            </div>
                        <%}%>


                        <spring:bind path="bankAccountForm.id">
                            <input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
                        </spring:bind>

                        



                        <tr>
                            <td class="dataLabel"> </td>				


                            <td class="dataField">
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>


                        <tr>
                            <td class="dataLabel">Bank </td>		
                            <td class="dataField">
                            	<%--
                            	<spring:bind path="bankAccountForm.bankId">
                            		<select name="${status.expression}">
                            			<option value="">--- SELECT BANK ---</option>
                            			
                            			<c:forEach items="${bankList}" var="bank">
                            				<option value="<c:out value="${bank.bankId }" />" <c:if test="${status.value eq bank.bankId }">selected</c:if> ><c:out value="${bank.bankName}" /></option>
                            			</c:forEach>
                            		</select>
                                    <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>
                            	 --%>
                                <spring:bind path="bankAccountForm.bankName">
                                	<input type="text" id="bankLookup" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="40" />
                                     
                                </spring:bind>
                                <spring:bind path="bankAccountForm.bankId">
                                    <input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
                                	 <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>


                        <tr>
                            <td class="dataLabel"> Account Number: </td>				


                            <td class="dataField">
                                <spring:bind path="bankAccountForm.account">
                                    <input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="40" />

                                    <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>





						<spring:bind path="bankAccountForm.bank">
                                    <input type="hidden" name="<c:out value="${status.expression}"/>" id="bank" value="<c:out value="${status.value}" />" size="40" />

                                    <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>

                        







                        <tr>
                            <td class="dataLabel"> Bank Branch : </td>				


                            <td class="dataField">
                                <spring:bind path="bankAccountForm.bankBranch">
                                    <input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="40" />

                                    <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>













                        <tr>
                            <td class="dataLabel"> Account Name : </td>				


                            <td class="dataField">
                                <spring:bind path="bankAccountForm.accountName">
                                    <input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="40" />

                                    <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>
                        
						<tr>
                            <td class="dataLabel"> Currency Name : </td>				


                            <td class="dataField">
                                <spring:bind path="bankAccountForm.currencyId">
                                    <select name="<c:out value="${status.expression }" />">
                                    	<option value="">-- SELEC ONE --</option>
                                    	<c:forEach items="${currencyList}" var="currency">                                    	
                                    		<option value="<c:out value="${currency.currencyId}" />" <c:if test="${status.value eq currency.currencyId}">selected</c:if>><c:out value="${currency.currencyName}" /></option>
                                    	</c:forEach>
                                    </select>

                                    <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>
                        <c:if test="${bankAccountForm.tipe eq 'client'}">
						<tr>
                            <td class="dataLabel"> Is Source : </td>
                            <td class="dataField">
                                <spring:bind path="bankAccountForm.paymentSource">
                                    <select name="<c:out value="${status.expression}" />">                                    	
                                    	<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>NO</option>
                                    	<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>YES</option>
                                    	
                                    </select>

                                    <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>
						</c:if>



                        <tr>
                            <td class="dataLabel"> </td>				


                            <td class="dataField">
                            </td>
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
                <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
                <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
            </td>
            <td align="right"></td>
        </tr>
    </tbody>
</table>
</form>

<script language="javascript">
	$(document).ready(function(){
		//alert("xxx");
		
		$("#bankLookup").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "bank?navigation=lookupjson",
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
		        $("#bankId").val(ui.item.id);
		        $("#bank").val(ui.item.label);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
		
		/* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
		$("#bankLookup").autocomplete("bank?navigation=lookupjson", {
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
	        $("#bankId").val(value.id);
	        $("#bank").val(value.name);
	    });
	    */
	    
	    
	});
	
	
	function simpan (){

        document.form1.method = "POST";
        document.form1.action = "bankaccount-form";
   		//document.form1.providerId.value = <c:out value="${providerId}" />;
   		<c:choose>
			<c:when test="${bankAccountForm.tipe eq 'provider'}">
				document.form1.providerId.value = <c:out value="${providerId}" />;
				document.form1.navigation.value = "listprovider";
			</c:when>
			<c:when test="${bankAccountForm.tipe eq 'client'}">
				document.form1.clientId.value = <c:out value="${clientId}" />;
				document.form1.navigation.value = "listclient";
			</c:when>
			<c:when test="${bankAccountForm.tipe eq 'group'}">
				document.form1.memberGroupId.value = <c:out value="${memberGroupId}" />;
				document.form1.navigation.value = "listgroup";
			</c:when>
		</c:choose>
        document.form1.submit();
    }
    
    function cancel(){
		<c:choose>
			<c:when test="${bankAccountForm.tipe eq 'provider'}">
				<c:choose>
					<c:when test="${navigation eq 'listprovider'}">
						document.form1.navigation.value = "listprovider";	
						document.form1.action = "bankaccount";
						document.form1.providerId.value = <c:out value="${providerId}" />;
					</c:when>
				</c:choose>
			</c:when>
			<c:when test="${bankAccountForm.tipe eq 'client'}">
				<c:choose>
					<c:when test="${navigation eq 'listclient'}">
						document.form1.navigation.value = "listclient";	
						document.form1.action = "bankaccount";
						document.form1.clientId.value = <c:out value="${clientId}" />;
					</c:when>
				</c:choose>			
			</c:when>
			<c:when test="${bankAccountForm.tipe eq 'group'}">
				<c:choose>
					<c:when test="${navigation eq 'listgroup'}">
						document.form1.navigation.value = "listgroup";	
						document.form1.action = "bankaccount";
						document.form1.memberGroupId.value = <c:out value="${memberGroupId}" />;
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
					document.form1.action = "bankaccount";
					document.form1.navigation.value = "listprovider";	
					document.form1.providerId.value = <c:out value="${providerId}" />;
			</c:otherwise>
		</c:choose>
		document.form1.submit();
    }
    
	
</script>