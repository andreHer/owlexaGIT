<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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

<!DOCTYPE html>
<html>
	<head>


		<script type="text/javascript" src="scripts/sugar_3.js"></script>
		<script type="text/javascript" src="scripts/cookie.js"></script>
		<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css" />
		<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
	
		
<script src="scripts/prototype.js" type="text/javascript"></script>
  <script src="scripts/scriptaculous.js" type="text/javascript"></script>
  <script src="scripts/cookie.js" type="text/javascript"></script>
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
		 <script type="text/javascript">

            YAHOO.example.onMenuReady = function(p_oEvent) {
                
                // "beforerender" event handler for the menu

                function onMenuBeforeRender(p_sType, p_sArgs, p_oMenu) {

                    var oSubmenuData = {
                    
                        "provider": [ 
                        
                            { text: "Create Provider", url: "provider-form" },
                            { text: "Search Provider", url: "provider" },
                           	{ text: "Provider Report", submenu: { id: "providerreport", itemdata: [        
                                    { text: "Client Provider", url:"provider" },
                                    { text: "Terminated Provider", url:"provider?status=3&navigation=gosearch" },
                                    { text: "Registered Provider",  url:"provider?status=1&navigation=gosearch" },
        
                                ] }
                            
                            },                                                
                        ],
		    
                        "client": [
    
                            { text: "Create Client", url: "client-form" },
                            { text: "Search Client", url: "client" },
                            { text: "Client Report", submenu: { id: "clientreport", itemdata: [        
                                    { text: "Terminated Client", url:"client?status=4&navigation=gosearch" },
                                    { text: "Registered Client",  url:"client?status=1&navigation=gosearch" },
        
                                ] }
                            
                            },                          
    
                        ],
                      
                        "customer": [
    
                            { text: "Search Member Group", url: "membergroup" },
                            { text: "Create Member Group", url: "membergroup-form" },
                            { text: "Search Member", url: "member" },
                            { text: "Search Member Account", url: "member?navigation=account" },
                            { text: "Cautious Member Benefit", url: "member?navigation=criticalbenefit" },
                            { text: "Create Member", url: "member-form" },
                            { text: "Member Group Report", submenu: { id: "mgreport", itemdata: [        
                                    { text: "Terminated Group", url:"membergroup?status=2&navigation=gosearch" },
                                    { text: "Registered Group",  url:"membergroup?status=1&navigation=gosearch" },
                                    { text: "Resigned Group",  url:"membergroup?status=3&navigation=gosearch" },                                                                 
        
                                ] }
                            
                            },        
                            { text: "Member Report", submenu: { id: "memberreport", itemdata: [        
                                    { text: "Terminated Member", url:"member?status=2&navigation=gosearch" },
                                    { text: "Registered Member",  url:"member?status=1&navigation=gosearch" },
                                    { text: "Resigned Member",  url:"member?status=3&navigation=gosearch" },        
                                ] }
                            
                            },                
                        
                        ],
		                           
                        "call": [
    
                            { text: "Search First Call", url: "firstcall" },
                            { text: "Create First Call", url: "firstcall-form" },
                            { text: "First Call Report", submenu: { id: "callreport", itemdata: [        
                                    { text: "Information Call", url:"firstcall?callType=4&navigation=gosearch" },
                                    { text: "Complain Call",  url:"firstcall?callType=1&navigation=gosearch" },
                                    { text: "Case Call",  url:"firstcall?callType=1&navigation=gosearch" },                                
        
                                ] }
                            
                            },         
                        
                        ],
                        
                        "case": [
    
                            { text: "Search Case", url: "case" },
                            { text: "Monitor Case", url: "case?status=9&caseCategoryId=1&navigation=gosearch" },
                            { text: "Case Report", submenu: { id: "casereport", itemdata: [        
                                    { text: "Case Monitoring Report", url:"case" },
                                    { text: "Case Investigation Report",  url:"case" },
        
                                ] }
                            
                            },         
                        
                        ],
         
                         "claim": [
    
                           	{ text: "Batch Claim", submenu: { id: "bc", itemdata: [        
                                    { text: "Create Batch Claim", url:"batchclaim-form" },
                                    { text: "Search Batch Claim",  url:"batchclaim" },
									{ text: "Completed Batch Claim",  url:"batchclaim?status=12&navigation=gosearch" },                                    
									{ text: "Verified Batch Claim",  url:"batchclaim?status=12&navigation=gosearch" },									
									{ text: "Checked Batch Claim",  url:"batchclaim?status=12&navigation=gosearch" },
									{ text: "Unregistered Batch Claim",  url:"batchclaim?status=14&navigation=gosearch" },									
        
                                ] }
                            
                            },      
                           	{ text: "Claim", submenu: { id: "cl", itemdata: [        
                                       { text: "Search Claim", url: "claim" },
                           			   { text: "Verify Claim", url: "claim?status=11&navigation=gosearch" },                            
			                           { text: "Check Claim", url: "claim?status=3&navigation=gosearch" },    
            			               { text: "Pending Claim", url: "claim?status=10&navigation=gosearch" },         
            			               { text: "Paid Claim", url: "claim?status=6&navigation=gosearch" },                     			               
                                ] }
                            
                            },                                                                                   
                            { text: "Batch Claim Report", submenu: { id: "bcreport", itemdata: [        
                                    { text: "Batch Claim Report", url:"batchclaim" },
                                    { text: "Pending Batch Report",  url:"batchclaim" },
									{ text: "Paid Batch Claim",  url:"batchclaim?status=6&navigation=gosearch" },        
                                ] }
                            
                            },
                    		        { text: "Outstanding Claim", submenu: { id: "outstandingclaim", itemdata: [        
                                    { text: "Submitted Outstanding Claim", url:"outstandingclaim?status=1&navigation=gosearch" },
                                    { text: "Processed Outstanding Claim",  url:"outstandingclaim?status=2&navigation=gosearch" },
                                                                                
                                ] }
                            
                            },                         
                                     
                            { text: "Claim Report", submenu: { id: "creport", itemdata: [        
                                    { text: "Claim Report", url:"claim" },
                                    { text: "Pending Claim Report",  url:"claim" },
                                    { text: "Claim Excess Report",  url:"claim?navigation=claimExcessReport" },
        
                                ] }
                            
                            },
                            { text: "Claim Statistic", submenu: { id: "cstatistic", itemdata: [        
                                    { text: "Claim Statistic", url:"claimreport" }, 
                                    { text: "Claim Tracking", url:"claimreport?navigation=track" }, 
                                    { text: "SLA Tracking", url:"claimreport?navigation=tracksla" },
                                    { text: "Group Claim Statistic", url:"groupclaimreport" },
                                          
                                ] }
                            
                            },
                            { text: "Batch Claim Statistic", submenu: { id: "bcstat", itemdata: [        
                                    { text: "Batch Claim Statistic", url:"batchclaimreport" },
                                          
                                ] }
                            
                            },  
                            { text: "Claim SLA Monitor",  url:"claim?navigation=searchsla" 
                                
                            
                            },                      
                        
                        ],
                        "finance": [
    					                                                                              
                            { text: "Excess Charges", submenu: { id: "excess", itemdata: [        
                                    { text: "Search Open Excess Charge", url:"excesscharge?status=1&navigation=gosearch" },
                                    { text: "Paid Excess Charge",  url:"excesscharge?status=2&navigation=gosearch" },
                                    { text: "Bad Excess Charge",  url:"excesscharge?status=4&navigation=gosearch" },                                            
                                ] }
                            
                            },         
                            { text: "Payment", submenu: { id: "payment", itemdata: [        
                                    { text: "Search Closed Batch Claim", url:"batchclaim?status=12&navigation=gosearch" },
                                    { text: "Claim Payment Report",  url:"payment" },
        
                                ] }
                            
                            },  
                             { text: "Payment Batch", submenu: { id: "paymentbatch", itemdata: [        
                                    { text: "Search Payment Batch", url:"paymentbatch?navigation=gosearch" },
                                    { text: "Search Sent Payment",  url:"payment?navigation=searchbatching" },
        
                                ] }
                            
                            },          
                             { text: "Cost Containment Report", submenu: { id: "ccreport", itemdata: [        
                                    { text: "Search Cost Containtment", url:"costcontainment" },
                                    { text: "Cost Containtment Report",  url:"costcontainment" },
        
                                ] }
                            
                            },
                            { text: "Floating Fund", submenu: { id: "ffund", itemdata: [        
                                    { text: "Search Floating Fund", url:"fund" },
                                    { text: "Add Floating Fund",  url:"fund-form" },
        
                                ] }
                            
                            },      
                        
                        ],
                        "report": [
    					                                                                              
                            { text: "Claim", submenu: { id: "claimReport", itemdata: [        
                                    { text: "Claim Statistic", url:"claimreport" },
                                    { text: "Claim Tracking", url:"claimreport?navigation=track" }, 
                                    { text: "Claim SLA Tracking",  url:"claimreport?navigation=tracksla" },
                                    { text: "Claim Group Report",  url:"groupclaimreport" },
                                    { text: "Member Claim Report",  url:"claimreport?navigation=memberClaim" },            
									{ text: "Group Utilization Report",  url:"groupclaimutilreport" },
									{ text: "Provider Utilization Report",  url:"providerclaimutilreport" },                                                                        
                                ] }
                            
                            },         
                            { text: "Membership", submenu: { id: "memberReport", itemdata: [        
                                    { text: "Pending Member", url:"batchclaim?status=12&navigation=gosearch" },
                                    { text: "Terminated Member",  url:"member?navigation=searchterminated" },
        
                                ] }
                            
                            },         
                             { text: "Call Center", submenu: { id: "callReport", itemdata: [        
                                    { text: "Report Call Center", url:"firstcall?navigation=report" },                                           
                                ] }
                            
                            },
                            { text: "Finance", submenu: { id: "financeReport", itemdata: [        
                                    { text: "Reimbursement Claim", url:"payment?navigation=claimreport" },
                                    { text: "Excess Collection",  url:"excesscharge?navigation=searchpaid" },
        
                                ] }
                            
                            },      
                        
                        ],
                    		            
                    };

					
                    this.getItem(0).cfg.setProperty("submenu", { id:"provider", itemdata: oSubmenuData["provider"] });
                    this.getItem(1).cfg.setProperty("submenu", { id:"client", itemdata: oSubmenuData["client"] });
                    
                    this.getItem(2).cfg.setProperty("submenu", { id:"customer", itemdata: oSubmenuData["customer"] });
                    this.getItem(3).cfg.setProperty("submenu", { id:"call", itemdata: oSubmenuData["call"] });
                    this.getItem(4).cfg.setProperty("submenu", { id:"claim", itemdata: oSubmenuData["claim"] });
                    this.getItem(5).cfg.setProperty("submenu", { id:"case", itemdata: oSubmenuData["case"] });
                    this.getItem(6).cfg.setProperty("submenu", { id:"finance", itemdata: oSubmenuData["finance"] });                                        
					this.getItem(7).cfg.setProperty("submenu", { id:"report", itemdata: oSubmenuData["report"] });
                }              


                // Instantiate and render the menu

                var oMenu = new YAHOO.widget.Menu(
                                    "productsandservices", 
                                    {
                                        position:"static", 
                                        hidedelay:750, 
                                        lazyload:true, 
                                        effect:{ 
                                            effect:YAHOO.widget.ContainerEffect.FADE,
                                            duration:0.25
                                        } 
                                    }
                            );


                // Subscribe to the "beforerender" event

                oMenu.beforeRenderEvent.subscribe(onMenuBeforeRender, oMenu, true);


                // Render the menu

                oMenu.render();
                
            }


            // Initialize and render the menu when it is available in the DOM

            YAHOO.util.Event.onContentReady("productsandservices", YAHOO.example.onMenuReady);

function hideLeftCol(id){

	if(this.document.getElementById( id).style.display=='none'){
		this.document.getElementById( id).style.display='inline';

		Set_Cookie('showLeftCol','true',30,'/','','');
		var show = Get_Cookie('showLeftCol');
		document['HideHandle'].src = 'images/hide.gif';		
	}else{
		this.document.getElementById(  id).style.display='none';

		Set_Cookie('showLeftCol','false',30,'/','','');
		var show = Get_Cookie('showLeftCol');
		document['HideHandle'].src = 'images/show.gif';	

	}
}

function showSubMenu(id){
	if(this.document.getElementById( id).style.display=='none'){
		tbButtonMouseOver('HideHandle',120,'',10);
	}
}




        </script>   
		
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
										<img src="images/<c:out value="${systemConfig.companyCode}" />.png" alt="Company Logo" style="margin-left: 15px;" border="0" height="60" width="130">
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
													<td class="subMenu2" align="left" width="746">
													
													<c:if test="${(theUser.roleId.roleId eq 0) and (theUser.userType eq 2)}">
														<a href="user" class="subMenu2Link">User Management</a>&nbsp;| <a href="role" class="subMenu2Link">Role Management</a>&nbsp;|&nbsp;<a href="diagnosis" class="subMenu2Link"> Diagnosis </a>&nbsp;|&nbsp;<a href="activitylog" class="subMenu2Link"> Activity Audit </a>&nbsp;|&nbsp;<a
															href="product" class="subMenu2Link">Product Management</a>&nbsp;|&nbsp;<a href="item" class="subMenu2Link">Item Management </a>&nbsp;|
													</c:if>
													<c:if test="${theUser.userType eq 1}">
														<a href="claim" class="subMenu2Link">Claim Progess Report</a>&nbsp;| <a href="excesscharge" class="subMenu2Link">Excess Report</a>&nbsp;|&nbsp;<a href="member" class="subMenu2Link"> Member Report </a>&nbsp;|&nbsp;
													</c:if>
													<c:if test="${theUser.userType eq 3}">
														<a href="claim" class="subMenu2Link">Claim Progess Report</a>&nbsp;| <a href="excesscharge" class="subMenu2Link">Excess Report</a>&nbsp;|&nbsp;<a href="member" class="subMenu2Link"> Member Report </a>&nbsp;|&nbsp;<a href="groupclaimutilreport" class="subMenu2Link"> Claim Utilization Report </a>&nbsp;|&nbsp;
													</c:if>
													<c:if test="${theUser.userType eq 5}">
														<a href="groupclaimutilreport" class="subMenu2Link">Rekapitulasi </a>&nbsp;| <a href="claim" class="subMenu2Link">Claim Progess Report</a>&nbsp;| <a href="excesscharge" class="subMenu2Link">Excess Report</a>&nbsp;|&nbsp;<a href="member" class="subMenu2Link"> Member Report </a>&nbsp;|&nbsp;
													</c:if>
													<c:if test="${theUser.userType eq 4}">
														<a href="batchclaim" class="subMenu2Link">Invoice Report</a>&nbsp;|&nbsp;
													</c:if>
													<c:if test="${theUser.userType eq 10}">
														<a href="payment?navigation=searchconfirm" class="subMenu2Link">Payment Confirmation</a>&nbsp;|&nbsp;<a href="excesscharge?navigation=searchpending" class="subMenu2Link">Excess Payment Confirmation</a>&nbsp;|&nbsp;<a href="excesscharge?navigation=searchpending" class="subMenu2Link">Excess Report </a>&nbsp;|&nbsp;<a href="invoice?navigation=searchpending" class="subMenu2Link">Outstanding Invoice </a>&nbsp;|&nbsp;<a href="paymentbatch" class="subMenu2Link">Payment Batch </a>
													</c:if>
													<c:if test="${theUser.userType eq 6}">
														<a href="dashboard?navigation=pusat" class="subMenu2Link">Dashboard</a>&nbsp;| <a href="groupclaimutilreport" class="subMenu2Link">Claim Utilization Report</a>&nbsp;|&nbsp;
													
													</c:if>
													</td>
													
													<td width="61">
														<img src="images/myAreaLeft.gif" alt="" border="0">
													</td>
													<td width="171" nowrap="nowrap" class="myArea">
														|&nbsp;&nbsp;<a href="user?navigation=chmypass" class="myAreaLink">Change Password</a>&nbsp;&nbsp;|&nbsp; <a href="user?navigation=logout" class="myAreaLink">Logout</a>&nbsp;|&nbsp;

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
										Welcome <c:out value="${theUser.username}" />
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
									<c:if test="${theUser.userType eq 2}">
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
									</c:if>
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
						
					</td>
				</tr>
			</tbody>
		</table>
	</body>
	
	<script language="Javascript">
	if (!Get_Cookie('showLeftCol')) {
		Set_Cookie('showLeftCol','true',30,'/','','');
	}
	var show = Get_Cookie('showLeftCol');

	if (show == 'true') {
		this.document.getElementById('leftCol').style.display='inline';
		document['HideHandle'].src = 'images/hide.gif';
	} else {
		this.document.getElementById('leftCol').style.display='none';
		document['HideHandle'].src = 'images/show.gif';	
		
	}
	</script>
	
</html>

