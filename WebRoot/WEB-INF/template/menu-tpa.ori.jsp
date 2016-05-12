<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<tr>
    <td colspan="2" class="subMenuBar2" width="100%">

        <div id="navigation">
            <ul class="sf-menu">
                <li class="activelink">
                    <a href="dashboard?navigation=pusat">Dashboard</a>
                </li>

                <li>
                    <a href="#">Claim</a>
                    <ul>
						<li>
                            <a href="claimreceiving">Claim Receive</a>
                        </li>
                        <li>
                            <a href="batchclaim-form">Register Claim</a>
                        </li>
                        <li class="current">
                            <a href="batchclaim?status=1&navigation=gosearch">Claim
                                Input</a>
                        </li>

                        <li>
                            <a href="batchclaim?status=12&navigation=gosearch">Claim
                                Verification</a>

                        </li>

                        <li>
                            <a href="batchclaim?status=5&navigation=gosearch">Claim
                                Payment</a>

                        </li>
                        <li>
                            <a href="payment?status=1&navigation=gosearch">Claim
                                Payment Approval</a>

                        </li>

                        <li>
                            <a href="claim">Claim
                                Monitor</a>

                        </li>

                        <li>
                            <a href="dashboard?navigation=dashboardclaimstat">Claim
                                Monitor Dashboard</a>

                        </li>
                    </ul>
                </li>
                <!-- 
                <li>
                    <a href="firstcall-form?navigation=freecall">Call Center</a>
                </li>
				 -->
			   <li>
                    <a href="case?navigation=searchmonitor">Case Monitoring</a>
                </li>
                <li>
                    <a href="#">Membership</a>
                    <ul>
                        <li class="current">
                            <a href="membergroup-form">Register Group</a>
                        </li>
                        <li>
                            <a href="membergroup">Search Group</a>
                        </li>
                        <li>
                            <a href="member">Search Member</a>
                        </li>
                        <li>
                            <a href="client-form">Register Client</a>
                        </li>
                        <li>
                            <a href="client">Search Client</a>
                        </li>
                        <li>
                            <a href="policy">Search Policy</a>
                        </li>

                    </ul>
                </li>
                
                <c:if test="${(theUser.roleId.roleId eq 0) or (theUser.roleId.roleId eq 7) or (theUser.roleId.roleId eq 8)
                or (theUser.roleId.roleId eq 9) or (theUser.roleId.roleId eq 10)}">
                <li >
                    <a href="#">Finance</a>
                    <ul>
                        <li class="current">
                            <a href="fund">Floating Fund</a>
                        </li>
                        <li>
                            <a href="excesscharge">Excess Charge</a>
                        </li>
                        <!-- 
                        <li>
                                <a href="costcontainment">Cost Containment</a>
                        </li>
                        -->
                    </ul>
                </li>
                </c:if>
                

                <li>
                    <a href="#">Search Engine</a>
                    <ul>
                        <li class="current">
                            <a href="batchclaim">Search Batch Claim</a>
                        </li>
                        <li>
                            <a href="claim">Search Claim</a>
                        </li>
                        <li>
                            <a href="payment">Search Payment</a>
                        </li>
                        <li>
                            <a href="excesscharge">Search Excess</a>
                        </li>
                        <li>
                            <a href="firstcall">Search Call</a>
                        </li>
						
                        
                    </ul>
                </li>
                
                <li >
                    <a href="#">Provider Relation</a>
                    <ul>
                        <li class="current">
                            <a href="provider-form">Register Provider</a>
                        </li>
                        <li>
                            <a href="provider">Search Provider</a>
                        </li>
                        <li>
                            <a href="provider?navigation=searchcapitation">Provider Capitation</a>
                        </li>
                        <li>
                            <a href="providerset">Provider Set Profile</a>
                        </li>
						<li>
                            <a href="provider?navigation=showmap">Provider Mapping</a>
                        </li>
					
                    </ul>
                </li>
                
                <c:if test="${theUser.roleId.roleId eq 0 }">
                <li >
                    <a href="#">Product & Benefit</a>
                    <ul>
                        <li class="current">
                            <a href="product-form">Register Product</a>
                        </li>
                        <li>
                            <a href="product">Search Product</a>
                        </li>



                    </ul>
                </li>
				</c:if>
                <li>
                    <a href="#">Report</a>
                    <ul>
                        <li class="current">
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
                            <a href="claim?navigation=searchsla">Claim SLA Monitor</a>
                        </li>
                        <li>
                            <a href="case?navigation=searchissued">Issued GL Report</a>
                        </li>
                        <li>
                            <a href="rejectedcase?navigation=searchissued">Rejected Case Report</a>
                        </li>                            
                        <li>
                            <a href="rejectedclaim?navigation=searchissued">Rejected Claim Report</a>
                        </li>     

                    </ul>
                </li>
                <c:if test="${theUser.roleId.roleId eq 0 }">
                <li>
                    <a href="#">Administration</a>
                    <ul>
                        <li class="current">
                            <a href="user-form">Register User</a>
                        </li>
                        <li>
                            <a href="user">Search User</a>
                        </li>
                        
                        <li>
                            <a href="#">Medical Item</a>
                            <ul>
                            	<li>
                            		<a href="diagnosis">Diagnosis</a>
                            	</li>
                            	<li>
                            		<a href="diagnosisset">Diagnosis Set</a>
                            	</li>
                            	<li>
                            		<a href="medicine">Medicine</a>
                            	</li>
                            	<li>
                            		<a href="procedure">Medical Procedure</a>
                            	</li>
                            	<li>
                            		<a href="itemcategory">Service Category</a>
                            	</li>
                            	<li>
                            		<a href="item">Service Facilities</a>
                            	</li>
                            	<li>
                            		<a href="casecategory">Service Coverage</a>
                            	</li>
                            </ul>
                        </li>
                        
                        
                        <li>
                            <a href="bank-form">Register Bank</a>
                        </li>
                        <li>
                            <a href="bank">Search Bank</a>
                        </li>
                        <li>
                            <a href="exchangerate">Exchange Rate</a>
                        </li>

                        <li>
                            <a href="dataimportstage">Data Import Session</a>
                        </li>
                        <li>
                            <a href="exportimporttemplate">Export Import Template</a>
                        </li>
                        <li>
                            <a href="#">Upload Data</a>
                            <ul>
								<li >
		                            <a href="dataimportstage-form?tipe=1">Upload Claim</a>
		                        </li>
		                        <li >
		                            <a href="dataimportstage-form?tipe=2">Upload Claim Detail</a>
		                        </li>
								 <li >
		                            <a href="dataimportstage-form?tipe=4">Upload Provider</a>
		                        </li>
		                        <li >
		                            <a href="dataimportstage-form?tipe=3">Upload Member</a>
		                        </li>
		                        <li >
		                            <a href="memberprovider-upload">Upload Member Provider</a>
		                        </li>
		                        <li >
		                            <a href="clientprovider-upload">Upload Client Provider</a>
		                        </li>
		                        <li >
		                            <a href="membergroupprovider-upload">Upload Group Provider</a>
		                        </li>
		                        <li >
		                            <a href="policyprovider-upload">Upload Policy Provider</a>
		                        </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#">Security & Audit</a>
                            <ul>
								
                            	<li>
                            		<a href="activitylog">Activity Log</a>
                            	</li>
                            	<li>
                            		<a href="edctransactionlog">EDC Activity Log</a>
                            	</li>								 
		                        <li>
		                            <a href="role">Setting Role</a>
		                        </li>		                        																			
		                        <li>
		                            <a href="action">Access Code</a>
		                        </li>
		                        
                            </ul>
                        </li>

                    </ul>
                </li>
                </c:if>
                <li>
                    <a href="user?navigation=logout">Logout</a>
                </li>


            </ul>
        </div>
    </td>
</tr>