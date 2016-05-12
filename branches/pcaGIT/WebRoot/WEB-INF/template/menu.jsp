<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>


<tr>
	<td class="tabTableTop" height="1" width="100%">
		<div class="yui-b">

			<div id="productsandservices" class="yuimenu">
				<div class="bd">
					<ul class="first-of-type">
					
						<c:if test="${theUser.userType eq 2}">
							<li class="yuimenuitem">
								<a href="provider">Provider Relation</a>
							</li>
							<li class="yuimenuitem">
								<a href="client">Client Relation</a>
							</li>
							<li class="yuimenuitem">
								<a href="member">Customer Management</a>
							</li>
							<li class="yuimenuitem">
								<a href="member">Call Management</a>
							</li>
							<li class="yuimenuitem">
								<a href="claim">Claim Management</a>
							</li>
							<li class="yuimenuitem">
								<a href="case">Case Management</a>
							</li>
							<li class="yuimenuitem">
								<a href="payment">Finance</a>
							</li>
							<li class="yuimenuitem">
								<a href="payment">Report</a>
							</li>
						</c:if>
						<c:if test="${theUser.userType eq 1 }">
							<li class="yuimenuitem">
								<a href="claimreport?navigation=track">Claim Report</a>
							</li>
							<li class="yuimenuitem">
								<a href="groupclaimreport">Claim Report</a>
							</li>
							<li class="yuimenuitem">
								<a href="excesscharge">Excess Charge Report</a>
							</li>
						</c:if>
						<c:if test="${theUser.userType eq 5 }">
							<li class="yuimenuitem">
								<a href="claimreport?navigation=track">Dashboard</a>
							</li>
						
							<li class="yuimenuitem">
								<a href="claimreport?navigation=track">Claim Report</a>
							</li>
										
							<li class="yuimenuitem">
								<a href="groupclaimreport">Claim Report</a>
							</li>
							<li class="yuimenuitem">
								<a href="excesscharge">Excess Charge Report</a>
							</li>
						</c:if>
						<c:if test="${theUser.userType eq 3 }">
							<li class="yuimenuitem">
								<a href="claimreport?navigation=track">Dashboard</a>
							</li>
						
							<li class="yuimenuitem">
								<a href="claimreport?navigation=track">Claim Report</a>
							</li>
							<li class="yuimenuitem">
								<a href="groupclaimreport">Claim Report</a>
							</li>
							<li class="yuimenuitem">
								<a href="excesscharge">Excess Charge Report</a>
							</li>
						</c:if>
						<c:if test="${theUser.userType eq 4 }">
							<li class="yuimenuitem">
								<a href="batchclaimreport">Invoice Report</a>
							</li>
						
						</c:if>
						
						
						
					</ul>
				</div>
			</div>

		</div>
	</td>
</tr>


