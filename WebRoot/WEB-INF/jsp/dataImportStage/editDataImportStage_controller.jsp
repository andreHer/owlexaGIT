
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>




<form action="dataimportstage" method="POST"  enctype="multipart/form-data">
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
            <tr>
                <td style="padding-bottom: 2px;">
                    <input type="hidden" name="notyet" value="">
                    <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
                    <input title="Cancel [Alt+C]" accesskey="C"  class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="submit">         
                </td>
                <td align="right"></td>
            </tr>
        </tbody>
    </table>

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


                        <input type="hidden" name="id" value="<c:out value="${id}" />" >
                            

                        <input type="hidden" name="navigation" value="upload">


                        <tr>
                            <td class="dataLabel"> </td>				


                            <td class="dataField">

                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>

                        <tr>
                            <td class="dataLabel"> Import Number : </td>				


                            <td class="dataField">
                                    <input type="text" size="35" name="importNumber" value="<c:out value="${importNumber}" />" />                                    
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>


                        




                        <tr>
                            <td class="dataLabel"> Import Date : </td>				


                            <td class="dataField">
                                
                                    <input type="text" size="15" name="importDate" id="importDate" value="<c:out value="${importDate}" />" maxlength="10">
                                    <img src="images/jscalendar.gif" alt="Enter Date" id="importDate_trigger" align="absmiddle" height="20" width="20">
                                    <script type="text/javascript">
                                        Calendar.setup({
                                            inputField     :    "importDate",     // id of the input field
                                            ifFormat       :    "%Y-%m-%d",      // format of the input field
                                            button         :    "importDate_trigger",  // trigger for the calendar (button ID)
                                            align          :    "Tl",           // alignment (defaults to "Bl")
                                            singleClick    :    true
                                        });
                                    </script>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>

<tr>
                            <td class="dataLabel"> Import Template : </td>				


                            <td class="dataField">
                                
                                    <select name="template">
                                        <c:forEach items="${templates}" var="template">
                                            <option value="<c:out value="${template.id}" />"><c:out value="${template.templateName}" /></option>
                                            
                                        </c:forEach>
                                    </select>
                                
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>





                        <tr>
                            <td class="dataLabel"> Import  File : </td>				


                            <td class="dataField">
                                <input type="file" name="filenya" />

                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>

                        <tr>
                            <td class="dataLabel"> Description : </td>				


                            <td class="dataField">
                                    <textarea rows="6" cols="40" name="description"><c:out value="${description}" /></textarea>
                                    
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>














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
                <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="submit">         
            </td>
            <td align="right"></td>
        </tr>
    </tbody>
</table>
</form>

<script language="javascript">
    function simpan (){

        document.form1.method = "POST";
        document.form1.action = "dataimportstage";
        document.form1.submit();
    }
    function kembali (){
        document.form1.navigation.value = "back";
        document.form1.action = "dataimportstage";
        document.form1.submit();
    }
    // forreign affairs
    // forreign affairs end
</script>