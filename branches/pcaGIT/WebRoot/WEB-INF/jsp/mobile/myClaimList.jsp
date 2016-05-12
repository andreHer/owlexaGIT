<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<html>
	<head>
		
	    <link href="css/mobile/stylesheets.css" rel="stylesheet" type="text/css" />	    
		<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>
		
		<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>
		
		
		<script type='text/javascript' src='dwr/interface/AJAXCaseService.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
	</head>
	<body>
	
		<div class="row-fluid">
			<div class="span6">
				<div class="block">				
					<div class="data-fluid">
						<table cellpadding="0" cellspacing="0" width="100%" class="table">
							<thead>
								<tr>
									<th width="25%">Claim Number</th>
									<th width="25%">Provider</th>
									<th width="25%">Date</th>
									<th width="25%">Charge</th>
									<th width="25%">Approved</th>
									<th width="25%">Status</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><a href="#">dmitry@domain.com</a></td>
									<td>Dmitry Ivaniuk</td>
									<td>DT-SV35582</td>
									<td><span class="label label-success">Developer</span></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
