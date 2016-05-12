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
							<li><a href="provider?navigation=searchedcalertdefault" title="" class="active">Provider EDC Alert Default </a> <span class="pendinglist-notif" id="providerEdcAlertNotifyId">0</span></li>
						</ul>
						<ul class="pendinglist">
							<li><a href="provider?navigation=searchedcalertcustom" title="" class="active">Provider EDC Alert Customize </a> <span class="pendinglist-notif" id="providerEdcAlertCustomNotifyId">0</span></li>
						</ul>
						<ul class="pendinglist">
							<li><a href="provider?navigation=searchrollpaperalertdefault" title="" class="active">Provider Roll Paper Alert Default </a> <span class="pendinglist-notif" id="providerRollPaperAlertDefaultNotifyId">0</span></li>
						</ul>
						<ul class="pendinglist">
							<li><a href="provider?navigation=searchrollpaperalertcustom" title="" class="active">Provider Roll Paper Alert Customize </a> <span class="pendinglist-notif" id="providerRollPaperAlertCustomNotifyId">0</span></li>
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
			refreshProviderEdcAlertDefault(); //done
			refreshProviderEdcAlertCustom();
			refreshProviderRollPaperAlertDefault();
			refreshProviderRollPaperAlertCustom();
		}		
		function refreshProviderEdcAlertDefault(){
			$.get("edcterminal?navigation=jsontotaledcalert",
				function(data) {					
					var label = document.getElementById("providerEdcAlertNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);				
				});
		}
		function refreshProviderEdcAlertCustom(){
			$.get("provider?navigation=jsontotalprovideredcalertcustom",
				function(data) {					
					var label = document.getElementById("providerEdcAlertCustomNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);				
				});
		}
		function refreshProviderRollPaperAlertDefault(){
			$.get("provider?navigation=jsontotalrollpaperalertdefault",
				function(data) {					
					var label = document.getElementById("providerRollPaperAlertDefaultNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);				
				});
		}
		function refreshProviderRollPaperAlertCustom(){
			$.get("provider?navigation=jsontotalrollpaperalertcustom",
				function(data) {					
					var label = document.getElementById("providerRollPaperAlertCustomNotifyId");					
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
		
</script>