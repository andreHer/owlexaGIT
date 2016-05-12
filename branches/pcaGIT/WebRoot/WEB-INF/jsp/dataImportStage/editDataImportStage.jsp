<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
    <% if (request.getParameter("tipe").equals("1")) { %>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Upload Claim</h3></td>
    <% } %>
    <% if (request.getParameter("tipe").equals("2")) { %>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Upload Claim Detail</h3></td>
    <% } %>
    <% if (request.getParameter("tipe").equals("3")) { %>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Upload Member</h3></td>
    <% } %>
    <% if (request.getParameter("tipe").equals("4")) { %>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Upload Provider</h3></td>
    <% } %>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<%
       String alert = (String) request.getAttribute("alert");
       if (alert != null && !alert.trim().equals("")) {%>
        
	<div id="warning">
    	<c:out value="${alert}"></c:out>
    </div>
<%}%>

<form action="dataimportstage-form" method="POST"  name="form1" id="form_layout" enctype="multipart/form-data">
	<input type="hidden" name="navigation" value="">
    <table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
            <tr>
                <td>
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tbody>

                            

                        
                        <spring:bind path="dataImportStageForm.tipe">
                            <input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
                            <div id="fredcaption">
                                <c:out value="${status.errorMessage}" />
                            </div>
                        </spring:bind>

                        <spring:bind path="dataImportStageForm.id">
                            <input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
                            <div id="fredcaption">
                                <c:out value="${status.errorMessage}" />
                            </div>
                        </spring:bind>


                        <tr>
                            <td class="dataLabel"> </td>				


                            <td class="dataField">

                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>

                        <tr>
                            <td class="dataLabel"> Client Name : </td>				


                            <td class="dataField">
                            	<spring:bind path="dataImportStageForm.clientId">
                                    <input type="hidden" id="clientId" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
                                    <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>
                                <spring:bind path="dataImportStageForm.clientName">
                                    <input type="text" size="35" id="clientName" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
                                    <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>
                        
						<tr>
                            <td class="dataLabel"> Receive Date : </td>				


                            <td class="dataField">
                                <spring:bind path="dataImportStageForm.receiveDate">
                                    <input type="text" size="15" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
                                    <img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
                                    <script type="text/javascript">
                                        Calendar.setup({
                                            inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
                                            ifFormat       :    "%Y-%m-%d",      // format of the input field
                                            button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
                                            align          :    "Tl",           // alignment (defaults to "Bl")
                                            singleClick    :    true
                                        });
                                    </script>
                                    <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>
                        <!-- 
                        <tr>
                            <td class="dataLabel"> Import Number : </td>				


                            <td class="dataField">
                                <spring:bind path="dataImportStageForm.importNumber">
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
                            <td class="dataLabel"> Import Date : </td>				


                            <td class="dataField">
                                <spring:bind path="dataImportStageForm.importDate">
                                    <input type="text" size="15" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
                                    <img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
                                    <script type="text/javascript">
                                        Calendar.setup({
                                            inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
                                            ifFormat       :    "%Y-%m-%d",      // format of the input field
                                            button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
                                            align          :    "Tl",           // alignment (defaults to "Bl")
                                            singleClick    :    true
                                        });
                                    </script>
                                    <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>
 -->
			<!-- 			
						<tr>
                            <td class="dataLabel"> Import Template : </td>				


                            <td class="dataField">
                                <spring:bind path="dataImportStageForm.template">
                                    <select name="<c:out value="${status.expression}" />">
                                        <c:forEach items="${templates}" var="template">
                                            <option value="<c:out value="${template.id}" />" <c:if test="${status.value eq template.id}">selected</c:if>><c:out value="${template.templateName}" /></option>
                                            
                                        </c:forEach>
                                    </select>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>

 -->

                        <tr>
                            <td class="dataLabel"> Import Raw File : </td>				


                            <td class="dataField">
                                <spring:bind path="dataImportStageForm.file">
                                    <input type="file" name="file" >

                                    <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>
    					<!--                     
                        <tr>
                            <td class="dataLabel"> File Format : </td>
                            <td class="dataField">
                                <spring:bind path="dataImportStageForm.fileFormat">
									<select name="<c:out value="${status.expression}" />">
										<option value="csv" <c:if test="${status.value eq 'csv' }">selected</c:if>>CSV</option>
										<option value="xls" <c:if test="${status.value eq 'xls' }">selected</c:if>>Excel File</option>
									</select>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>
						<tr>
                            <td class="dataLabel"> Date Format : </td>
                            <td class="dataField">
                                <spring:bind path="dataImportStageForm.datePattern">
									<select name="<c:out value="${status.expression}" />">
										<option value="yyyy-MM-dd" <c:if test="${status.value eq 'yyyy-MM-dd' }">selected</c:if>>yyyy-MM-dd</option>
										<option value="dd/MM/yyyy" <c:if test="${status.value eq 'dd/MM/yyyy' }">selected</c:if>>dd/MM/yyyy</option>
									</select>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>
                        <tr>
                            <td class="dataLabel"> First Line Header : </td>
                            <td class="dataField">
                                <spring:bind path="dataImportStageForm.firstLineHeader">
									<select name="<c:out value="${status.expression}" />">
										<option value="1" <c:if test="${status.value eq '1' }">selected</c:if>>Yes</option>
										<option value="0" <c:if test="${status.value eq '0' }">selected</c:if>>No</option>
									</select>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>
                        

                        <tr>
                            <td class="dataLabel"> Description : </td>				


                            <td class="dataField">
                                <spring:bind path="dataImportStageForm.description">
                                    <textarea rows="6" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
                                    <div id="fredcaption">
                                        <c:out value="${status.errorMessage}" />
                                    </div>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>

						-->	












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
    function simpan (){

        document.form1.method = "POST";
        document.form1.action = "dataimportstage-form";
        document.form1.submit();
    }
    function cancel (){
        document.form1.navigation.value = "back";
        document.form1.action = "dataimportstage";
        document.form1.submit();
    }
    // forreign affairs
    // forreign affairs end
</script>