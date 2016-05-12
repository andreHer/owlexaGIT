<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td valign="top">
				<div id="handle-sidebar" class="handle-container-sidebar vertical-text-sidebar">
				    <div id="openCloseButton-sidebar" class="handle-button-item-sidebar">
				    	<span><img src="images/owlexa/arrow_up.png"/>Sidebar</span>
				    </div>
				</div>
			</td>
			<td>
				<div id="sidebar-container">
					<div id="openCloseIdentifier-sidebar"></div>
					
					<div class="sidebar" style="margin-left:-50px;">
						<span class="pendinglist-title">Notifications</span>
						<ul class="pendinglist">
							<li><a href="firstcall?navigation=searchopen" title="" class="active">Open Call </a> <span class="pendinglist-notif" id="openCallNotifyId">0</span></li>
							<li><a href="case?navigation=searchopen" title="">New Case</a> <span class="pendinglist-notif" id="openCaseNotifyId">0</span></li>
							<li><a href="case?navigation=searchmonitor" title="">Monitor Case</a> <span class="pendinglist-notif" id="monitorCaseNotifyId">0</span></li>
							<li><a href="case?navigation=searchexpire" title="">Expire Case</a> <span class="pendinglist-notif" id="expireCaseNotifyId">0</span></li>
							<li><a href="case?navigation=searchclosed" title="">Closed Case</a> <span class="pendinglist-notif" id="closedCaseNotifyId">0</span></li>
							<li><a href="case?navigation=searchopencob" title="">COB Case</a> <span class="pendinglist-notif" id="openCOBCaseNotifyId">0</span></li>
						</ul>
						<span class="pendinglist-title">EDC Transaction</span>
						<ul class="pendinglist">		
							<li><a href="case?navigation=searchedcopen" title="">EDC Check In</a> <span class="pendinglist-notif" id="checkinNotifyId">0</span></li>		
						</ul>
					</div>
					
				<!-- End Sidebar container -->
				</div>
			</td>
		</tr>
	</tbody>
</table>	



<script type="text/javascript">
		
		refreshData();		
		setInterval("refreshData()",50000);
		
		function refreshData(){
			refreshOpenCall(); //done
			refreshOpenCase();
			refreshMonitorCase();
			refreshClosingCase();
			refreshExpireCase();	
			refreshEDCMonitor();
			refreshOpenCOBCase();
			
		}
		function refreshEDCMonitor(){
			$.get("case?navigation=jsontotaledccase",
				function(data) {					
					var label = document.getElementById("checkinNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);					
				});
		}
		function assignLabel(label,data,hasil){						
			if (hasil == '0'){
				label.style.display = "none";						
			}
			else {
				if (!isNaN(hasil)){
					label.style.display = "";
					label.innerHTML = data;
				}
				else {
					label.style.display = "none";
				}
			}
		}
		
		function refreshOpenCall(){
			$.get("firstcall?navigation=jsontotalopencall",
				function(data) {					
					var label = document.getElementById("openCallNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);				
				});
		}
		function refreshOpenCase(){
			$.get("case?navigation=jsontotalnewcase",
				function(data) {					
					var label = document.getElementById("openCaseNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);					
				});
		}
		
		/*
		Add by aju on 20150804, for COB :D		
		*/
		function refreshOpenCOBCase(){
			$.get("case?navigation=jsontotalnewcobcase",
				function(data) {					
					var label = document.getElementById("openCOBCaseNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);					
				});
		}
		
		function refreshMonitorCase(){
			$.get("case?navigation=jsontotalopencase",
				function(data) {					
					var label = document.getElementById("monitorCaseNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);					
				});
		}
		function refreshExpireCase(){
			$.get("case?navigation=jsontotalexpirecase",
				function(data) {					
					var label = document.getElementById("expireCaseNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);					
				});
		}
		function refreshClosingCase(){
			$.get("firstcall?navigation=jsontotalclosingcase",
				function(data) {					
					var label = document.getElementById("closedCaseNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);					
				});
		}
		
		
		
</script>