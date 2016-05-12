
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
	

		<!-- main calendar program -->
		<script type="text/javascript" src="scripts/calendar.js"></script>

		
        <!-- Menu source file -->
       
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Aplikanusa Lintasarta - Healthcare Management System</title>


		<link href="css/navigation.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="scripts/menu.js"></script>
		<script language="javascript" src="scripts/cookie_002.js"></script>

	</head>
	<body  onmouseout="closeMenus();">
	
		<div id="HideMenu" class="subDmenu">
			<table onMouseOver="hiliteItem(this,'no');" border="0" cellpadding="0" cellspacing="0" width="160">

				<tbody>



					<tr>
						<td>
							<table class="subMenuTable" border="0" cellpadding="0" cellspacing="0" width="100%">
								<tbody>


								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>

		</div>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td colspan="3">
						<input name="action" value="UnifiedSearch" type="hidden">
						<input value="Home" name="module" type="hidden">
						<input value="false" name="search_form" type="hidden">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tbody>
								<tr style="background: rgb(255, 255, 255) none repeat scroll 0%; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;" height="74">
									<td style="background-image: url(images/kiri.jpg); background-position: left center; background-repeat: no-repeat;" nowrap="nowrap" width="90%">
										<img src="images/gesa.jpg" alt="Company Logo" style="margin-left: 15px;" border="0" height="60" width="60">
									</td>
									<td style="padding-right: 10px; vertical-align: bottom; background-image: url(images/axaheader.jpg);" align="right" nowrap="nowrap" width="524">
										<br>
										<div style="padding-bottom: 4px;" class="search">
											&nbsp;
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2" class="subMenuBar2" nowrap="nowrap" width="100%">

										<table border="0" cellpadding="0" cellspacing="0" width="100%">
											<tbody>
												<tr>
													<td class="subMenu2" align="right" width="746">
														<a href="" class="subMenu2Link">Administrator</a>&nbsp;| <a href="" class="subMenu2Link">Membership</a>&nbsp;|&nbsp;<a href="" class="subMenu2Link"> Customer Service </a>&nbsp;|&nbsp;<a href="" class="subMenu2Link"> Claim Management </a>&nbsp;|&nbsp;<a
															href="" class="subMenu2Link">Finance</a>&nbsp;|&nbsp;<a href="" class="subMenu2Link">Case Management </a>&nbsp;|
														
													</td>
													<td width="71">
														<img src="images/myAreaLeft.gif" alt="" border="0">
													</td>
													<td width="161" nowrap="nowrap" class="myArea">
														&nbsp;|&nbsp; <a href="" class="myAreaLink">Logout</a>&nbsp;|&nbsp;

													</td>
												</tr>
											</tbody>
										</table>

									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td class="welcomeBar">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tbody>
								<tr height="20">
									<td class="welcome" width="160">
										Welcome admin
									</td>
									<td class="lastView" nowrap="nowrap">
										<b>Navigation > </b>&nbsp;&nbsp;
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
																	<page:applyDecorator page="menuListServlet" name="menu-kiri"/>
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

