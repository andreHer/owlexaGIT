<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%
	//		// theme
	String theme = (String) request.getParameter("theme");
	if (theme == null || theme.trim().equals("")) {
		theme = (String) request.getSession().getAttribute("theme");
	}
	if (theme == null) {
		theme = "style.css";
	}
	// 	end
%>
<%
	String breadcrumb = (String)request.getAttribute("breadcrumb") == null ? "" : (String)request.getAttribute("breadcrumb");	
	User user = (User) session.getAttribute("theUser");	
	String menuUrl = user == null ? "" : user.getRoleId().getMenuTemplateUrl();
	
	String sidebarUrl = user == null ? "" : user.getRoleId().getSidebarTemplateUrl();;
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD html 4.01 Transitional//EN">
<%@page import="com.ametis.cms.datamodel.User"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" media="all"
			href="css/calendar-win2k-cold-1.css" />
		<link rel="stylesheet" type="text/css" media="all"
			href="css/style.css" />

		<script src="scripts/cookie.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="css/tabcontent.css" />
		<script type="text/javascript" src="scripts/tabcontent.js">
			/***********************************************
			* Tab Content script- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
			* This notice MUST stay intact for legal use
			* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
			***********************************************/
		</script>
		<link rel="stylesheet" type="text/css"
			href="css/reset-fonts-grids.css" />
		
		<!-- CSS for Menu -->
		<link rel="stylesheet" type="text/css" href="css/menu.css" />


		<!-- Page-specific styles -->

		<!-- Namespace source file -->
		<!-- calendar stylesheet -->
		<link rel="stylesheet" type="text/css" media="all"
			href="css/calendar-blue.css" title="win2k-cold-1" />

		<!-- main calendar program -->
		<script type="text/javascript" src="scripts/calendar.js"></script>

		<!-- language for the calendar -->
		<script type="text/javascript" src="scripts/calendar-en.js"></script>

		<!-- the following script defines the Calendar.setup helper function, which makes
       adding a calendar a matter of 1 or 2 lines of code. -->
		<script type="text/javascript" src="scripts/calendar-setup.js"></script>
		<!-- Lama End-->
		
		
	<link rel="stylesheet" type="text/css" href="themes/green/css/superfish.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.10.custom.css" />
		
	<!-- 	
	<script type="text/javascript" src="scripts/jquery-1.2.6.min.js"> </script>		
	-->
	<!-- modified by aju on 20150402, update jQuery version -->
	<script type="text/javascript" src="scripts/jquery/jquery-1.11.2.js"> </script>	
	<script type="text/javascript" src="scripts/superfish.js"></script>
	
	<!-- modified by aju on 20150402, add jQuery UI latest version -->
	<script type="text/javascript" src="scripts/jquery/plugin/jquery-ui-1.11.4.custom.js"> </script>		
	
	<!-- Owlexa Style -->	
	<link rel="stylesheet" href="css/owlexa/styles.menu.css">
	<script src="scripts/owlexa/script.menu.js"></script>
	<link rel="stylesheet" href="css/owlexa/styles.menu.show.hide.css">
	<script src="scripts/owlexa/jquery.show.hide.menu.js"></script>
	<link rel="stylesheet" href="css/owlexa/style.sidebar.css">
	<script src="scripts/jquery/plugin/jquery.cookie.js"></script>

		<title><c:out value="${systemConfig.company}" /> - Healthcare Management System</title>
		<link href="css/navigation.css" rel="stylesheet" type="text/css" />

	</head>
	<body >
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td colspan="3">
						<jsp:include page="<%=menuUrl%>" />
					</td>
				</tr>
				<tr>
					<td class="welcomeBar" height="50px" id="welcomeBar" >&nbsp;</td>
				</tr>
				<tr >
					<td class="welcomeBar" style="position:fixed;z-index:9998;"  width="100%">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tbody>
								<tr height="20" >
									<td class="welcome" width="100%">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										Welcome								
										<c:choose>
										    <c:when test="${empty theMember}">
										       <c:out value="${theUser.username}" />
										    </c:when>
										    <c:otherwise>
										        <c:out value="${theMember.firstName}" /> <c:out value="${theMember.lastName}" />
										    </c:otherwise>
										</c:choose>
										| Navigation > &nbsp;&nbsp; <%=breadcrumb %>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tbody>
								<tr>
									<td class="welcomeBar" height="31px" id="welcomeBar" >&nbsp;</td>
								</tr>
								<tr>						
									<td class="leftColumn" height="600" valign="top" width="10">
										<img src="images/blank_002.gif" alt="" height="1" width="10">
									</td>

									<c:if test="${theUser.roleId.sidebarTemplateUrl eq ''}">
										<td class="leftColumnMain" valign="top" width="100%">
											<decorator:body />
										</td>
									</c:if>
									<c:if test="${theUser.roleId.sidebarTemplateUrl ne ''}">
										<td class="leftColumnMain" valign="top" width="95%">
											<decorator:body />
										</td>
										
										<td class="leftColumn"  height="600" valign="top" width="1">
										</td>
										<td class="rightColumn"  valign="top" width="5%">
											<jsp:include page="<%=sidebarUrl%>" />
										</td>
									</c:if>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="footer" align="center">
						<c:out value="${systemConfig.company}" /> &copy; 2014.
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>

