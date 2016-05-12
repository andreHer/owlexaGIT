<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>



<div class="accordion" id="accordion1">
	<div class="accordion-group">
		<div class="accordion-heading blockhead">
			<a class="accordion-toggle">
				Pending Request
			</a>
		</div>
		<div id="collapseOne" class="accordion-body collapse in">
			<div class="accordion-inner blockarea">
				<ul class="pendinglist">
					<li><a href="firstcall?navigation=searchopen" title="" class="active">Open Call </a> <span class="pendinglist-notif" id="openCallNotifyId">0</span></li>			
					
					<li><a href="excesscharge?navigation=searchpending" title="">Pending Excess </a> <span class="pendinglist-notif" id="excessNotifyId">0</span> </li>			
					<li><a href="excesscharge?navigation=searchreminder" title="">Pending Excess Reminder </a> <span class="pendinglist-notif" id="exReminderNotifyId">0</span></li>
					
					<li><a href="case?navigation=searchopen" title="">Open Case</a> <span class="pendinglist-notif" id="openCaseNotifyId">0</span></li>
					<li><a href="case?navigation=searchmonitor" title="">Monitor Case</a> <span class="pendinglist-notif" id="monitorCaseNotifyId">0</span></li>
					<li><a href="case?navigation=searchclosed" title="">Closed Case</a> <span class="pendinglist-notif" id="closedCaseNotifyId">0</span></li>
					
					<li><a href="client?navigation=searchfundwarning" title="">Fund Warning</a> <span class="pendinglist-notif" id="fundWarningNotifyId">0</span></li>
					
					<li><a href="claim?navigation=searchopenedc" title="">Open EDC Claim</a> <span class="pendinglist-notif" id="openEdcClaimNotifyId">0</span></li>
					
					<li><a href="claim?navigation=searchopen" title="">Inputted Claim</a> <span class="pendinglist-notif" id="inputClaimNotifyId">0</span></li>
					<li><a href="claim?navigation=searchverify" title="">Verified Claim</a> <span class="pendinglist-notif" id="verifiedClaimNotifyId">0</span></li>
					
					<li><a href="batchclaim?navigation=searchopen" title="">Open Batch Claim</a> <span class="pendinglist-notif" id="openBatchNotifyId">0</span></li>
					<li><a href="batchclaim?navigation=searchclose" title="">Closed Batch Claim</a> <span class="pendinglist-notif" id="closedBatchNotifyId">0</span></li>
					
					<li><a href="payment?navigation=searchopen" title="">Open Payment</a> <span class="pendinglist-notif" id="openPaymentNotifyId">0</span></li>						
					
					
				</ul>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
		
		refreshData();		
		setInterval("refreshData()",5000);
		
		function refreshData(){
			refreshOpenCall(); //done
			
			refreshOpenEDCClaim(); //done
			refreshOpenPayment(); // done
			refreshOpenExcess();
			
			refreshRemindExcess();
			refreshClientFund();
			
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
			$.get("firstcall?navigation=jsontotal",
				function(data) {					
					var label = document.getElementById("openCallNotifyId");					
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
		
		function refreshOpenPayment(){
			$.get("payment?navigation=jsontotalopenpayment",
				function(data) {					
					var label = document.getElementById("openPaymentNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);
					
				});
		}
		function refreshOpenExcess(){
			$.get(
			"excesscharge?navigation=jsontotalopenexcess",
				function(data) {					
					var label = document.getElementById("excessNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);
					
				});
		}
		function refreshRemindExcess(){
			$.get(
			"excesscharge?navigation=jsontotalremindexcess",
				function(data) {					
					var label = document.getElementById("exReminderNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);
				});
		}
		function refreshClientFund(){
			$.get(
			"client?navigation=jsontotalclientfund",
				function(data) {					
					var label = document.getElementById("exReminderNotifyId");					
					var hasil = $.trim(data);
					
					assignLabel(label,data,hasil);
				});
		}		
		
</script>