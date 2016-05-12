<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<tr>
    <td colspan="2" class="subMenuBar2" width="100%">
    
    	<div id="header">
			<div id="menu-container">
			    <div id="openCloseIdentifier"></div>
							

		        <div id="cssmenu">
		            <ul>
		            	<li >				 
							<img src="images/owlexa/owlexa_letter_logo.png" alt="Owlexa Healthcare"  border="0" height="50" width="53">				
						</li>
		                <li id="mnuMainDashboard">
		                    <a href="dashboard?navigation=pusat">Dashboard</a>
		                </li>
		                <li id="mnuMainClaim" class="has-sub">
		                    <a href="#">Claim</a>
		                    <ul>
								<li>
		                            <a href="claimreceiving">Claim Receive</a>
		                        </li>
		                        <li>
		                            <a href="batchclaim-form">Register Claim</a>
		                        </li>
		                        <li >
		                            <a href="batchclaim?status=1&navigation=gosearch">Claim Input</a>
		                        </li>
		                        <li>
		                            <a href="batchclaim?status=12&navigation=gosearch">Claim Verification</a>
		                        </li>
		                        <li>
		                            <a href="batchclaim?status=5&navigation=gosearch">Claim Payment</a>
		                        </li>
		                        <li>
		                            <a href="payment?status=1&navigation=gosearch">Claim Payment Approval</a>
		                        </li>
		                        <li>
		                            <a href="claim">Claim Monitor</a>
		                        </li>
		                        <li>
		                            <a href="dashboard?navigation=dashboardclaimstat">Claim Dashboard</a>
		                        </li>
								
		
		                    </ul>
		                </li>
		           
		                <li class="has-sub">
		                    <a href="#">Membership</a>
		                    <ul>                        
		                        <li>
		                            <a href="membergroup">Search Group</a>
		                        </li>
		                        <li>
		                            <a href="member">Search Member</a>
		                        </li>
		                       
		                        <li>
		                            <a href="client">Search Client</a>
		                        </li>
		
		                    </ul>
		                </li>
		
		                <li class="has-sub">
		                    <a href="#">Search Engine</a>
		                    <ul>
		                        <li >
		                            <a href="batchclaim">Search Batch Claim</a>
		                        </li>
		                        <li>
		                            <a href="claim">Search Claim</a>
		                        </li>
		                        <li>
		                            <a href="payment">Search Payment</a>
		                        </li>                       
								<li>
		                            <a href="policy">Search Policy</a>
		                        </li>
		                        
		                    </ul>
		                </li>
		                
		                <li class="has-sub" >
		                    <a href="#">Provider Relation</a>
		                    <ul>                       
		                        <li>
		                            <a href="provider">Search Provider</a>
		                        </li>
		                         <li>
		                            <a href="providerclaim-form">Register Provider</a>
		                        </li>
		                    </ul>
		                </li>
		                <li >
		                    <a href="diagnosis">Diagnosis Reference</a>
		                </li>                
		              	 <li >
		                    <a href="product">Product & Benefit</a>
		                </li>
		                <li  class="has-sub" >
		                    <a href="#">Report</a>
		                    <ul>
		                        <li >
		                            <a href="claimreport">Claim Statistic</a>
		                        </li>
		                        <li>
		                            <a href="claimreport?navigation=track">Claim Tracking</a>
		                        </li>
		                        <li>
		                            <a href="claimreport?navigation=claimreconcile">Claim Reconcile</a>
		                        </li>
		                        <li>
		                            <a href="claimreport?navigation=activeclaimreport">Claim Active</a>
		                        </li>
		                        <li>
		                            <a href="claimreport?navigation=claimagingreport">Claim Aging</a>
		                        </li>
		                        <li>
		                            <a href="claimreport?navigation=tracksla">SLA Tracking</a>
		                        </li>
		                        <li>
		                            <a href="groupclaimreport">Group Statistic</a>
		                        </li>
		                        <li>
		                            <a href="claim?navigation=searchsla">Claim SLA Monitor</a>
		                        </li>
		                        <li>
					                <a href="claimreport?navigation=topxreport">Top X Report</a>
					            </li>
		                        <li>
		                            <a href="reportrequest">Request Report</a>
		                        </li>
		                         <li>
		                            <a href="paymentmonitor?navigation=reportmonitor">Payment Monitor</a>
		                        </li>
		                        <li>
		                            <a href="diagnosis?navigation=potentialhealthreport">Potential Health Problem</a>
				                </li>
		                    </ul>
		                </li>                
		                <li>
		                    <a href="user?navigation=logout">Logout</a>
		                </li>
		
						<li>
		                	<img src="images/owlexa/Lintasarta.png" alt="Aplikanusa Lintasarta"  border="0"    height="40" width="97">
		                </li>
		
		            </ul>
		        </div>
		        
			<!-- End Menu Container -->
			</div>
			<div id="handle" class="handle-container">
			    <div id="openCloseButton" class="handle-button-item">
			    	<span><img src="images/owlexa/arrow_up.png"/>Menu</span>
			    </div>
			</div>
		</div>
		        
    </td>
</tr>