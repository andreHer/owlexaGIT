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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Sarana Solusi Amanah - Healthcare Management System</title>
	<link rel="stylesheet" type="text/css" media="all" href="themes/green/css/reset.css" />
	<link rel="stylesheet" type="text/css" media="all" href="themes/green/style.css" />
	<link rel="stylesheet" type="text/css" media="all" href="themes/green/css/960.css" />
	<link rel="stylesheet" type="text/css" href="themes/green/css/superfish.css" media="screen" />

		
	<script type="text/javascript" src="themes/green/js/jquery-1.2.6.min.js"> </script>		
	<script type="text/javascript" src="themes/green/js/superfish.js"></script>


		<script type="text/javascript">

		// initialise plugins
		jQuery(function(){
			jQuery('ul.sf-menu').superfish();
		});

		</script>
		

<!--[if IE 6]>
<script type='text/javascript' src='js/DD_belatedPNG.js'></script>
<script>DD_belatedPNG.fix('img, #navigation, #header, .bottom-coaninter, .sidebar-top, .sidebar-bot, .right-column, h3,.tm-bg, img.tn, #footer');</script>
<link rel="stylesheet" type="text/css" href="css/ie6.css" media="screen" />
<![endif]-->



</head>
<body>
<!--start main container-->
<div class="container_12">
	
	<div class="grid_12">
		<div id="header">
			<div id="logo"></div>
			<div id="call"></div>
		</div>
	</div>
	<!--end header-->
	
	<div class="grid_12">
		<div id="navigation">
			
		<ul class="sf-menu">
		
			<li  class="activelink"><a href="index.html">Dashboard</a></li>
			<li>
				<a href="portfolio.html">Claim</a>
				<ul>
					<li>
						<a href="batchclaim-form">Register Claim</a>
					</li>
					<li class="current">
						<a href="batchclaim?status=1&navigation=gosearch">Claim Input</a>						
					</li>
					
					<li>
						<a href="batchclaim?status=12&navigation=gosearch">Claim Verification</a>
						
					</li>
					
					<li class="current">
						<a href="#">Claim Search</a>
						<ul>
							<li class="current"><a href="#">Search Batch Claim</a></li>
							<li><a href="#">Web 2.0 Logos</a></li>
							<li><a href="#">Internet Logos</a></li>
							<li><a href="#">Business Logos</a></li>
							
						</ul>
					</li>
					
				</ul>
			</li>
			<li>
				<a href="about-us.html">Call Center</a>
			</li>
			
			
			<li>
				<a href="contact-us.html">Membership</a>
			</li>
			
			<li>
				<a href="services.html">Report</a>
			</li>
			
				
		</ul>
		
		</div>
	</div>
	<!--end navigation-->
	<div class="clear">&nbsp;</div>

	<br />

	<!--end middle container-->
		
	<div class="clear">&nbsp;</div>
	
	<div class="grid_12">	
											
										<decorator:body />
	
	</div>
	<!--end bottom container(grid_12)-->
	
	<!-- start footer container-->
	<div class="grid_12">
		<div id="footer">
			<p> &copy; www.yourdomain.com, all rights reserved.</p>
		</div>
	</div>
	<!--end footer container-->
</div>
<!--end main container-->
	
</body>
</html>