
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%//		// theme
			String theme = (String) request.getParameter("theme");
			if (theme == null || theme.trim().equals("")) {
				theme = (String) request.getSession().getAttribute("theme");
			}
			if (theme == null) {
				theme = "style.css";
			}
			// 	end

		%>


<!DOCTYPE html PUBLIC "-//W3C//DTD html 4.01 Transitional//EN">
<html>
	<head>


		<script type="text/javascript" src="scripts/sugar_3.js"></script>
		<script type="text/javascript" src="scripts/cookie.js"></script>
		<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css" />
		<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
	
		
<script src="scripts/prototype.js" type="text/javascript"></script>
  <script src="scripts/scriptaculous.js" type="text/javascript"></script>
  	<link rel="stylesheet" type="text/css" href="css/tabcontent.css" />
<script type="text/javascript" src="scripts/tabcontent.js">
/***********************************************
* Tab Content script- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/
</script>
<link rel="stylesheet" type="text/css" href="css/reset-fonts-grids.css" />
        <!-- CSS for Menu -->

        <link rel="stylesheet" type="text/css" href="css/menu.css" />
 
 
        <!-- Page-specific styles -->

        <!-- Namespace source file -->
<!-- calendar stylesheet -->
		<link rel="stylesheet" type="text/css" media="all" href="css/calendar-blue.css" title="win2k-cold-1" />

		<!-- main calendar program -->
		<script type="text/javascript" src="scripts/calendar.js"></script>

		<!-- language for the calendar -->
		<script type="text/javascript" src="scripts/calendar-en.js"></script>

		<!-- the following script defines the Calendar.setup helper function, which makes
       adding a calendar a matter of 1 or 2 lines of code. -->
		<script type="text/javascript" src="scripts/calendar-setup.js"></script>
		<!-- Lama End-->
		
		
        <script type="text/javascript" src="scripts/yahoo.js"></script>

        <!-- Dependency source files -->

        <script type="text/javascript" src="scripts/event.js"></script>
        <script type="text/javascript" src="scripts/dom.js"></script>

            
        <script type="text/javascript" src="scripts/animation.js"></script>
        
        <!-- Container source file -->
        <script type="text/javascript" src="scripts/container_core.js"></script>

        <!-- Menu source file -->
        <script type="text/javascript" src="scripts/menu-yahoo.js"></script>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>GESA Assistance - Healthcare Management System</title>


		<link href="css/navigation.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="scripts/menu.js"></script>
		<script language="javascript" src="scripts/cookie_002.js"></script>

	</head>
	<body >
	
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>

				<tr>
					<td class="welcomeBar">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tbody>

							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tbody>
								<tr>
									<td rowspan="3" style="width: 10px;" valign="top">
										<img style="" id="HideHandle" name="HideHandle" src="images/hide.gif" alt="" onclick='hideLeftCol("leftCol");closeMenus();' onMouseOver="showSubMenu('leftCol')">
									</td>
									<td class="leftCol" rowspan="3" style="width: 160px;" valign="top">
										<div style="display: inline;" id="leftCol">
											<table border="0" cellpadding="0" cellspacing="0" width="160">
												<tbody>
													<tr>
														<td style="padding-right: 10px;" valign="top">
															<table border="0" cellpadding="0" cellspacing="0" width="100%" >
																<tbody valign="top">

																</tbody>
															</table>
			


															<img src="images/blank.gif" alt="" border="0" height="1" width="160">
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</td>
									<td class="leftColumn" height="600" valign="top" width="10">
										<img src="images/blank_002.gif" alt="" height="1" width="10">
									</td>
									<td class="leftColumnMain" valign="top" width="100%">
										
										<decorator:body />
										
										
										<!-- crmprint -->
									


									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="footer" align="center">

					</td>
				</tr>
			</tbody>
		</table>

		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td align="center"></td>
				</tr>
				<tr>
					<td class="copyRight" align="center">
						<br>
						© 2004-2006 <a href="http://ametis.co.id" target="_blank" class="copyRightLink">Ametis Technology Ltd.</a> All Rights Reserved.
						<br>
						<br>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>

