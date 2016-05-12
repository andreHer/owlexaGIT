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
		            	
		                <li class="has-sub" >
		                    <a href="#">Claim</a>
		                    <ul>
		                        <li>
		                            <a href="batchclaim?status=5&navigation=gosearch">Claim Payment</a>
		                        </li>
		                        <li>
		                            <a href="payment?status=1&navigation=gosearch">Claim Payment Approval</a>
		                        </li>
		                        <li>
		                            <a href="dataimportstage-form?tipe=14">Upload Claim Payment</a>
		                        </li>
		                    </ul>
		                </li>
		                <li >
		                    <a href="payment">Payment History</a>
		                </li>
		                <li >
		                    <a href="paymentbatch">Payment Batch Request</a>                    
		                </li>
		                <li >
		                    <a href="paymentinstallment?navigation=searchbatching">Payment Batch</a>                    
		                </li>
		                <li >
		                    <a href="fund">Floating Fund</a>                    
		                </li>
		                <li >
		                    <a href="excesscharge">Excess Charge</a>                    
		                </li>
		                <li >
		                    <a href="invoice">Billing</a>                    
		                </li>
		                <li >
		                    <a href="client?navigation=trackclientfund">Track Client Fund</a>                
		                </li>
		                <li class="has-sub" >
		                    <a href="#">Invoice</a>
		                    <ul>
		                        <li>
		                            <a href="invoice-form">Register Invoice</a>
		                        </li>
		                        <li>
		                            <a href="invoice">Invoice</a>
		                        </li>
		                    </ul>
		                </li>
		                 <li class="has-sub">
		                    <a href="#">Report</a>
		                    <ul>
		                        <li class="has-sub">
				                    <a href="#">Claim Report</a>
				                    <ul>
				                    	    <li>
					                            <a href="claimreport">Claim Statistic</a>
					                        </li>
					                        <li>
					                            <a href="claimreport?navigation=track">Claim Tracking</a>
					                        </li>
					                        <li>
					                            <a href="claimreport?navigation=tracksla">SLA Tracking</a>
					                        </li>
					                        <li>
					                            <a href="groupclaimreport">Group Statistic</a>
					                        </li>
					                        <li>
					                            <a href="claimreport?navigation=topxreport">Top X Report</a>
					                        </li>
					                        <li>
					                            <a href="claim?navigation=searchsla">Claim SLA Monitor</a>
					                        </li>					                                                   
					                        <li>
					                            <a href="rejectedclaim?navigation=searchissued">Rejected Claim Report</a>
					                        </li>
					                        <li>
					                            <a href="claimreport?navigation=searchtransaction">Daily Transaction</a>
					                        </li>      
				                    </ul>
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