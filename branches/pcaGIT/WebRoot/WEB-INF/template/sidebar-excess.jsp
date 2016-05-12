
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
							<li><a href="excesscharge?navigation=searchopen" title="" class="active">Unpaid Excess </a> <span class="pendinglist-notif" id="openExcessNotifyId">0</span></li>
							<li><a href="excesscharge?navigation=searchremind" title="">Reminder Excess</a> <span class="pendinglist-notif" id="excessReminderNotifyId">0</span></li>
							<li><a href="invoice?navigation=searchopen" title="">Pending Invoice</a> <span class="pendinglist-notif" id="invoiceOpenNotifyId">0</span></li>
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
			refreshOpenExcess(); //done
			refreshOpenReminder();		
			refreshOpenInvoice();
		}		
		function refreshOpenExcess(){
			$.get("excesscharge?navigation=jsontotalopenexcess",
				function(data) {					
					var label = document.getElementById("openExcessNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);
				});
		}
		function refreshOpenReminder(){
			$.get("excesscharge?navigation=jsontotalreminder",
				function(data) {					
					var label = document.getElementById("excessReminderNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);					
				});
		}
		function refreshOpenInvoice(){
			$.get("invoice?navigation=jsontotalopen",
				function(data) {					
					var label = document.getElementById("invoiceOpenNotifyId");					
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