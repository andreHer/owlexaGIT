<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<html>
	<head>
		<link href="css/mobile/stylesheets.css" rel="stylesheet" type="text/css" />
		<link href="css/mobile/mystyles.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="row-fluid">
			<div class="span5">
				<div class="block">				
					<div class="data-fluid">
						<table cellpadding="0" cellspacing="0" width="100%" class="table">
							<thead>
								<tr>
									<th width="25%" style="background-color: #E9E9E9;">Case Number</th>
									<th width="25%" style="background-color: #E9E9E9;">Member</th>
									<th width="25%" style="background-color: #E9E9E9;">Date</th>
									<th width="25%" style="background-color: #E9E9E9;">Status</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${caseList}" var="mycase">
									<tr>
										<td><a href="mobile?navigation=detailcase&caseId=<c:out value="${mycase.caseId}" />"><c:out value="${mycase.caseNumber}" /></a></td>
										<td><c:out value="${mycase.memberId.firstName}" /></td>
										<td><c:out value="${mycase.caseStartTime}" /></td>
										<td><span class="label label-success">checked in</span></td>
									</tr>		
								</c:forEach>	
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
