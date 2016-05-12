
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
							<li><a href="claim?navigation=searchopenedc" title="">Open EDC Claim</a> <span class="pendinglist-notif" id="openEdcClaimNotifyId">0</span></li>
							<li><a href="claim?navigation=searchopencase" title="">Open Case Claim</a> <span class="pendinglist-notif" id="openCaseClaimNotifyId">0</span></li>		
							<li><a href="claim?navigation=searchopen" title="">Inputted Claim</a> <span class="pendinglist-notif" id="inputClaimNotifyId">0</span></li>
							<li><a href="claim?navigation=searchopencob" title="">Open COB Claim</a> <span class="pendinglist-notif" id="inputCOBClaimNotifyId">0</span></li>
							<li><a href="claim?navigation=searchverify" title="">Verified Claim</a> <span class="pendinglist-notif" id="verifiedClaimNotifyId">0</span></li>		
							<li><a href="batchclaim?navigation=searchopen" title="">Open Batch Claim</a> <span class="pendinglist-notif" id="openBatchNotifyId">0</span></li>
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
			refreshOpenBatch(); //done
			refreshOpenEDCClaim();
			refreshOpenCaseClaim();
			refreshInputtedClaim();
			refreshVerifiedClaim();
			//Add by aju on 20150805, for COB :D
			refreshInputtedCOBClaim()
		}	
		function refreshInputtedClaim(){
			$.get("batchclaim?navigation=jsontotalopen",
				function(data) {					
					var label = document.getElementById("inputClaimNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);
				});
		}
		
		//Add by aju on 20150805, for COB
		function refreshInputtedCOBClaim(){
			$.get("claim?navigation=jsontotalopencob",
				function(data) {					
					var label = document.getElementById("inputCOBClaimNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);
				});
		}
		
		function refreshVerifiedClaim(){
			$.get("batchclaim?navigation=jsontotalopen",
				function(data) {					
					var label = document.getElementById("verifiedClaimNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);
				});
		}	
		function refreshOpenBatch(){
			$.get("batchclaim?navigation=jsontotalopen",
				function(data) {					
					var label = document.getElementById("openBatchNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);
				});
		}
		function refreshOpenEDCClaim(){
			$.get("claim?navigation=jsontotalopenedc",
				function(data) {					
					var label = document.getElementById("openEdcClaimNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);
				});
		}
		function refreshOpenCaseClaim(){
			$.get("claim?navigation=jsontotalcaseclaim",
				function(data) {					
					var label = document.getElementById("openCaseClaimNotifyId");					
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