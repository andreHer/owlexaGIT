<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html dir="ltr" xmlns="http://www.w3.org/1999/xhtml" lang="en"><head>

<%
String alert = (String) request.getAttribute("alert");
 %>

	<title>Healthcare Management System - Log In</title>
	<head>
	<link rel="stylesheet" id="login" href="css/login.css" type="text/css" media="all" />
<link rel="stylesheet" id="colors-fresh" href="css/colors-fresh.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/wpcom.css" type="text/css" />

	<!-- Add by aju on 20150504, latest jQuery -->
	<script type="text/javascript" src="scripts/jquery/jquery-1.11.2.js"> </script>
	<script src="scripts/jquery/plugin/jquery.cookie.js"></script>	

</head>
<body class="login">

<div id="login">

<br />
<center><h2>
<%if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
</h2></center>
<br />
<center><img src="images/logo/owlexa.png" /></center>
<br />
<br />
Selamat,
<br/>
akun aplikasi mobile Owlexa Healthcare telah berhasil diaktifkan.
<br/>
<br/>
Silahkan login melalui aplikasi mobile Owlexa Healthcare pada smartphone anda.
<br/>
atau
<br/>
Login ke Owlexa Healthcare versi web di <a href="user?navigation=login">sini</a>.
<br/>
<br/>
Hormat kami,
<br/>
<b>Owlexa Healthcare</b>
<br />
</div>

<script type="text/javascript">
	//Add by aju on 20150504, for resetting menu and sidebar state
	$(document).ready(function(){
		$.cookie("menuState","opened");
		$.cookie("sidebarState","opened");
	});
</script>


</body></html>