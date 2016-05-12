<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<head>


	<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.10.custom.css" />
    <script type="text/javascript" src="scripts/jquery-1.2.6.min.js"> </script>		
        
	<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/reset-fonts-grids.css" />
        <!-- CSS for Menu -->
        <link rel="stylesheet" type="text/css" href="css/menu.css" />
		<link href="css/navigation.css" rel="stylesheet" type="text/css" />
</head>
<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

 <!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->

<!-- Search Container Start -->
<form>
	<input type="hidden" id="providerLat" value="<c:out value="${provider.latitude}" />" name="providerLat" />
	<input type="hidden" id="providerLong" value="<c:out value="${provider.longitude}" />" name="providerLong" />
	<input type="hidden" id="providerName" value="<c:out value="${provider.providerName}" />" name="providerName" />

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDPcMLYYvZw6GugiFfjMBcMZytMg6gJAUg&sensor=false" type="text/javascript"></script>
	<script src="scripts/maps/showProviderMap.js" type="text/javascript"></script>

	<div id="mapCanvas" class="mapCanvas"></div>
</form>


<!-- Table Container Stop -->
<script language="Javascript">
<%
%>
function pilih (idx,nama){
	window.opener.setProvider (idx,nama);
	window.close();
}
</script>
