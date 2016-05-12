
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
							<li><a href="batchclaim?navigation=gosearch&status=5" title="" class="active">Closed Batch Claim </a> <span class="pendinglist-notif" id="closedBatchNotifyId">0</span></li>						
							<li><a href="payment?navigation=searchpending" title="">Pending Payment</a> <span class="pendinglist-notif" id="pendingPaymentNotifyId">0</span></li>
							<li><a href="payment?navigation=searchdelivered" title="">Dispositioned Payment</a> <span class="pendinglist-notif" id="dispositionPaymentNotifyId">0</span></li>
							
							<li><a href="client?navigation=searchrequiredfund" title="">Fund Top Up Request</a> <span class="pendinglist-notif" id="fundTopUpNotifyId">0</span></li>
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
			refreshClosedBatch(); //done
			refreshPendingPayment();
			refreshDispositionedPayment();
			refreshFundTopUpClient();
		}
		function refreshFundTopUpClient(){
			$.get("client?navigation=jsontotalclientfund",
				function(data) {					
					var label = document.getElementById("fundTopUpNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);
				});
		}		
		function refreshDispositionedPayment(){
			$.get("payment?navigation=jsonsentpayment",
				function(data) {					
					var label = document.getElementById("dispositionPaymentNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);
				});
		}
		function refreshClosedBatch(){
			$.get("batchclaim?navigation=jsonclosedbatchclaim",
				function(data) {					
					var label = document.getElementById("closedBatchNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);
				});
		}
		function refreshPendingPayment(){
			$.get("payment?navigation=jsontotalpending",
				function(data) {					
					var label = document.getElementById("pendingPaymentNotifyId");					
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