
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
							<li><a href="case?navigation=searchopen" title="">New Case</a> <span class="pendinglist-notif" id="openCaseNotifyId">0</span></li>
							<li><a href="case?navigation=searchmonitor" title="">Monitor Case</a> <span class="pendinglist-notif" id="monitorCaseNotifyId">0</span></li>
							<li><a href="case?navigation=searchexpire" title="">Expire Case</a> <span class="pendinglist-notif" id="expireCaseNotifyId">0</span></li>
							<li><a href="case?navigation=searchclosed" title="">Closed Case</a> <span class="pendinglist-notif" id="closedCaseNotifyId">0</span></li>
							<li><a href="case?navigation=mycaseinvestigation" title="" class="active">Investigation Case </a> <span class="pendinglist-notif" id="investigationCaseNotifyId">0</span></li>
							<li><a href="case?navigation=searchopencob" title="">COB Case</a> <span class="pendinglist-notif" id="openCOBCaseNotifyId">0</span></li>
						</ul>
						<span class="pendinglist-title">Approval</span>
						<ul class="pendinglist">		
							<li><a href="caseprocedure?navigation=searchapprove" title="">Procedure Approval</a> <span class="pendinglist-notif" id="procedureNotifyId">0</span></li>
							<li><a href="casemedicine?navigation=searchapprove" title="">Medicine Approval</a> <span class="pendinglist-notif" id="medicineNotifyId">0</span></li>
						</ul>
						<span class="pendinglist-title">EDC Transaction</span>
						<ul class="pendinglist">		
							<li><a href="case?navigation=searchedcopen" title="">EDC Check In</a> <span class="pendinglist-notif" id="checkinNotifyId">0</span></li>		
						</ul>
						<span class="pendinglist-title">Follow Up Error Log</span>
						<ul class="pendinglist">		
							<li><a href="firstcall?navigation=searchfollowupmember" title="" class="active">Follow Up Member Log </a> <span class="pendinglist-notif" id="followUpMemberCallNotifyId">0</span></li>
							<li><a href="firstcall?navigation=searchfollowupcase" title="" class="active">Follow Up Case Log </a> <span class="pendinglist-notif" id="followUpCaseCallNotifyId">0</span></li>
							<li><a href="firstcall?navigation=searchfollowupclaim" title="" class="active">Follow Up Claim Log </a> <span class="pendinglist-notif" id="followUpClaimCallNotifyId">0</span></li>
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
			
			refreshOpenCase();
			refreshOpenCOBCase();
			refreshMonitorCase();
			refreshClosingCase();
			refreshExpireCase();
			refreshInvestigationCase();
			refreshProcedureApproval();		
			refreshMedicineApproval();
			refreshEDCMonitor();
			refreshFollowUpMemberCall();
			refreshFollowUpCaseCall();
			refreshFollowUpClaimCall();
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
			$.get("case?navigation=jsontotalclosingcase",
				function(data) {					
					var label = document.getElementById("closedCaseNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);					
				});
		}
		function refreshInvestigationCase(){
			$.get("case?navigation=jsontotalcaseinvestigation",
				function(data) {					
					var label = document.getElementById("investigationCaseNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);					
				});
		}
		function refreshProcedureApproval(){
			$.get("caseprocedure?navigation=jsontotalapprove",
				function(data) {					
					var label = document.getElementById("procedureNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);					
				});
		}
		function refreshMedicineApproval(){
			$.get("casemedicine?navigation=jsontotalapprove",
				function(data) {					
					var label = document.getElementById("medicineNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);					
				});
		}
		function refreshFollowUpCaseCall(){
			$.get("firstcall?navigation=jsontotalfollowupcase",
				function(data) {					
					var label = document.getElementById("followUpCaseCallNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);				
				});
		}	
		function refreshFollowUpMemberCall(){
			$.get("firstcall?navigation=jsontotalfollowupmember",
				function(data) {					
					var label = document.getElementById("followUpMemberCallNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);				
				});
		}	
		function refreshFollowUpClaimCall(){
			$.get("firstcall?navigation=jsontotalfollowupclaim",
				function(data) {					
					var label = document.getElementById("followUpClaimCallNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);				
				});
		}	
</script>