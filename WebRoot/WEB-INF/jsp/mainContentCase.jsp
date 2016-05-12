<ul id="maintab" class="shadetabs">

	<li class="selected"><a
		href="case?navigation=detail&caseId=<c:out value="${myCase.caseId}" />"
		rel="tcontent1">Member Case </a>
	</li>
	<c:if test="${theUser.userType eq 2}">
		<li><a
			href="caseevent?navigation=list&caseId=<c:out value="${myCase.caseId}" />"
			rel="tcontent2">Monitoring </a>
		</li>
		<li><a
			href="caseitem?navigation=list&caseId=<c:out value="${myCase.caseId}" />"
			rel="tcontent3">Treatment Item </a>
		</li>
		<li><a
			href="costcontainment?navigation=list&caseId=<c:out value="${myCase.caseId}" />"
			rel="tcontent4">Cost Containment </a>
		</li>
		<li><a
			href="caseconversation?navigation=list&caseId=<c:out value="${myCase.caseId}" />"
			rel="tcontent5">Conversation </a>
		</li>
		<li><a
			href="caseinvestigation?navigation=list&caseId=<c:out value="${myCase.caseId}" />"
			rel="tcontent6">Investigation </a>
		</li>
	</c:if>
	<li><a
		href="casemedicine?navigation=list&caseId=<c:out value="${myCase.caseId}" />"
		rel="tcontent7">Medicine </a>
	</li>
	<li><a
		href="caseprocedure?navigation=list&caseId=<c:out value="${myCase.caseId}" />"
		rel="tcontent8">Procedure </a>
	</li>
	<li><a
		href="document?navigation=listcase&caseId=<c:out value="${myCase.caseId}" />"
		rel="tcontent9">Document </a>
	</li>
	<li><a
		href="casehistory?navigation=listcase&caseId=<c:out value="${myCase.caseId}" />"
		rel="tcontent9">History </a>
	</li>

</ul>
<br />
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0"
	width="100%">
	<tbody>
		<tr>
			<td colspan="20" style="padding: 0px;">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<td style="text-align: left;" class="tabDetailViewDL">&nbsp;

							</td>
							<td class="tabDetailViewDL" align="right" nowrap="nowrap"><c:if
									test="${theUser.userType ne 4}">
									<a
										href="case?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>&caseCategoryId=<c:out value="${caseCategoryId}"/>"
										class="tabDetailViewDFLink" style="font-weight: normal;">
										<img src="images/back-arrow.png" title="Return to List" /> </a>
								</c:if> <c:if test="${theUser.userType eq 4}">
									<%
										String sess = session.getAttribute("nav").toString();
									%>
									<%
										if (sess.equals("2")) {
									%>
									<a href="case?navigation=searchcase"
										class="tabDetailViewDFLink" style="font-weight: normal;">
										<img src="images/back-arrow.png" title="Return to List" /> </a>
									<%
										} else {
									%>
									<a href="case?navigation=searchopen"
										class="tabDetailViewDFLink" style="font-weight: normal;">
										<img src="images/back-arrow.png" title="Return to List" /> </a>
									<%
										}
									%>
								</c:if></td>
						</tr>
					</tbody>
				</table></td>
		</tr>

		<tr>
			<td class="tabDetailViewDL" valign="top" width="15%">Case Number
				:&nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="20%"><c:out
					value="${myCase.caseNumber}" />
			</td>
			<td class="tabDetailViewDL" valign="top" width="15%">Case
				Category :&nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="20%"><c:out
					value="${myCase.caseCategoryId.caseCategoryName}" />
			</td>
			<td class="tabDetailViewDL" valign="top" width="15%">Diagnosis 1
				:</td>
			<td class="tabDetailViewDF" valign="top" width="20%"><c:out
					value="${myCase.diagnosis1Id.diagnosisCode}" /> - <c:out
					value="${myCase.diagnosis1Id.description}" /> <c:if
					test="${diagnosis1Status eq 'disability'}">
					<b>(RECURRING DIAGNOSIS)</b>
				</c:if>&nbsp;&nbsp;<c:if test="${diag1exstat eq 'exclusion'}">
					<b>(DIAGNOSIS EXCLUSION)</b>
				</c:if>
			</td>
		</tr>


		<tr>
			<td class="tabDetailViewDL" valign="top" width="15%">Member
				Number :&nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="20%">
				<!-- modified by aju on 20150925, as Pak Firman request, for Provider User -->
				<%-- 
	      	<a class="listViewTdLinkS1" href="javascript:popupMember(<c:out value="${myCase.memberId.memberId}" />)"><c:out value="${myCase.memberId.customerPolicyNumber}" /></a>
	      	 --%> <c:if test="${theUser.userType eq 4}">
					<a class="listViewTdLinkS1" href="#"><c:out
							value="${myCase.memberId.customerPolicyNumber}" />
					</a>
				</c:if> 
				<c:if test="${theUser.userType ne 4}">
					<a
						href="member?navigation=detail&memberId=<c:out value="${claim.memberId.memberId}" />"
						class="linkDetail"><c:out value="${claim.memberId.firstName}" />
						<c:out value="${claim.memberId.lastName}" />
					</a>
				</c:if></td>
			<td class="tabDetailViewDL" valign="top" width="15%">Case Status
				:&nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="20%"><b> <a
					class="linkDetail"
					href="casehistory?navigation=list&caseId=<c:out value="${myCase.caseId}" />"><c:out
							value="${myCase.caseStatusId.caseStatusName}" />
				</a> <c:if test="${myCase.caseStatusId.caseStatusId eq 17 }">
						<b> ( <c:out value="${myCase.caseReferalNumber }" /> ) </b>
					</c:if> </b></td>
			<td class="tabDetailViewDL" valign="top" width="15%">Diagnosis 2
				:</td>
			<td class="tabDetailViewDF" valign="top" width="20%"><c:out
					value="${myCase.diagnosis2Id.diagnosisCode}" /> - <c:out
					value="${myCase.diagnosis2Id.description}" />&nbsp;&nbsp;<c:if
					test="${diag2exstat eq 'exclusion'}">
					<b>(DIAGNOSIS EXCLUSION)</b>
				</c:if>
			</td>

		</tr>

		<tr>
			<td class="tabDetailViewDL" valign="top" width="15%">Member Name
				:&nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="20%"><c:out
					value="${myCase.memberId.firstName}" /> &nbsp; <c:out
					value="${myCase.memberId.lastName}" />
			</td>
			<td class="tabDetailViewDL" valign="top" width="15%">Provider
				:&nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="20%"><c:out
					value="${myCase.providerId.providerName}" />
			</td>
			<td class="tabDetailViewDL" valign="top" width="15%">Diagnosis 3
				:</td>
			<td class="tabDetailViewDF" valign="top" width="20%"><c:out
					value="${myCase.diagnosis3Id.diagnosisCode}" /> - <c:out
					value="${myCase.diagnosis3Id.description}" />&nbsp;&nbsp;<c:if
					test="${diag3exstat eq 'exclusion'}">
					<b>(DIAGNOSIS EXCLUSION)</b>
				</c:if>
			</td>

		</tr>

		<tr>
			<td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="20%"></td>
			<td class="tabDetailViewDL" valign="top" width="15%"></td>
			<td class="tabDetailViewDF" valign="top" width="20%"></td>

			<td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="20%"></td>
		</tr>
		<c:if test="${myCase.caseCategoryId.caseCategoryId eq 1}">
			<tr>
				<td class="tabDetailViewDL" valign="top" width="15%">Physician
					:&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="20%"><c:out
						value="${myCase.physician}" />
				</td>
				<td class="tabDetailViewDL" valign="top" width="15%">Room
					Benefit :&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="20%"><c:out
						value="${myCase.roomAndBoard}" />
				</td>
				<td class="tabDetailViewDL" valign="top" width="15%">Case Start
					Time :&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="20%"><c:out
						value="${myCase.caseStartTime}" />
				</td>
			</tr>
			<tr>
				<td class="tabDetailViewDL" valign="top" width="15%">Status
					:&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="20%"><c:if
						test="${myCase.roomAndBoardStatus eq 1}">SESUAI BENEFIT</c:if> <c:if
						test="${myCase.roomAndBoardStatus eq 2}">NAIK PERMINTAAN SENDIRI</c:if>
					<c:if test="${myCase.roomAndBoardStatus eq 3}">KAMAR PENUH</c:if> <c:if
						test="${myCase.roomAndBoardStatus eq 4}">KAMAR TIDAK TERSEDIA</c:if>
					<c:if test="${myCase.roomAndBoardStatus eq 5}">REKOMENDASI DOKTER</c:if>
				</td>
				<td class="tabDetailViewDL" valign="top" width="15%">Room
					Usage:&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="20%"><c:out
						value="${myCase.roomAndBoardUsage}" />
				</td>
				<td class="tabDetailViewDL" valign="top" width="15%">Case End
					Time :&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="20%"><c:out
						value="${myCase.caseEndTime}" />
				</td>
			</tr>
			<tr>
				<td class="tabDetailViewDL" valign="top" width="15%">Card
					Number :&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="20%"><c:out
						value="${myCase.memberId.currentCardNumber}" /></td>
				<td class="tabDetailViewDL" valign="top" width="15%">Excess
					Charges:&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="20%"><fmt:formatNumber>
						<c:out value="${myCase.caseExcessValue}" />
					</fmt:formatNumber>
				</td>
				<td class="tabDetailViewDL" valign="top" width="15%">Length Of
					Stay :&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="20%"><c:out
						value="${myCase.longOfStay}" /> &nbsp; hari</td>
			</tr>
			<c:if test="${myCase.claimId ne null}">
				<tr>
					<td class="tabDetailViewDL" valign="top" width="15%"><c:if
							test="${myCase.claimId ne null}">Claim Reference :&nbsp;</c:if>
					</td>
					<td class="tabDetailViewDF" valign="top" width="20%"><c:if
							test="${myCase.claimId ne null}">
							<a class="listViewTdLinkS1"
								href="claim?navigation=detail&claimId=<c:out value="${myCase.claimId.claimId}" />"><c:out
									value="${myCase.claimId.claimNumber}" />
							</a>
						</c:if>
					</td>
					<td class="tabDetailViewDL" valign="top" width="15%"><c:if
							test="${myCase.claimId ne null}">Claim Charge :&nbsp;</c:if>
					</td>
					<td class="tabDetailViewDF" valign="top" width="20%"><c:if
							test="${myCase.claimId ne null}">
							<fmt:formatNumber>
								<c:out value="${myCase.claimId.claimChargeValue}" />
							</fmt:formatNumber>
						</c:if>
					</td>
					<td class="tabDetailViewDL" valign="top" width="15%"><c:if
							test="${myCase.claimId ne null}">Claim Approved :&nbsp;</c:if>
					</td>
					<td class="tabDetailViewDF" valign="top" width="20%"><c:if
							test="${myCase.claimId ne null}">
							<fmt:formatNumber>
								<c:out value="${myCase.claimId.claimApprovedValue}" />
							</fmt:formatNumber>
						</c:if>
					</td>


				</tr>
			</c:if>

			<tr>
				<td class="tabDetailViewDL" valign="top" width="15%">Case
					Handler :&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="20%"><c:out
						value="${myCase.caseHandler}" />
				</td>
				<td class="tabDetailViewDL" valign="top" width="15%">Average
					LoS :&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="20%"><c:out
						value="${inaCbgRef.alos}" /> &nbsp; hari</td>

				<td class="tabDetailViewDL" valign="top" width="15%">Costing
					Reference :&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="20%"><fmt:formatNumber>
						<c:out value="${inaCbgRef.treatmentRate}" />
					</fmt:formatNumber>
				</td>
			</tr>




		</c:if>

		<tr>
			<td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="20%"></td>
			<td class="tabDetailViewDL" valign="top" width="15%"></td>
			<td class="tabDetailViewDF" valign="top" width="20%"></td>
			<td class="tabDetailViewDL" valign="top" width="15%"></td>
			<td class="tabDetailViewDF" valign="top" width="20%"></td>

		</tr>

		<c:if test="${myCase.caseCategoryId.caseCategoryId eq 1}">
			<tr>
				<td class="tabDetailViewDL" valign="top" width="15%">Anamnesa
					:&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="35%" colspan="3"><c:out
						value="${myCase.anamnesa}" />
				</td>
				<td class="tabDetailViewDL" valign="top" width="15%">Cost
					Containment :&nbsp;</td>
				<td class="tabDetailViewDF" valign="top" width="20%"><fmt:formatNumber>
						<c:out value="${myCase.costContainmentValue}" />
					</fmt:formatNumber>
				</td>
			</tr>

		</c:if>

		<tr>
			<td class="tabDetailViewDL" valign="top" width="15%">Description
				:&nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="35%" colspan="3">
				<c:choose>
					<c:when test="${(myCase.caseStatusId.caseStatusId eq 2) or 
									(myCase.caseStatusId.caseStatusId eq 4) or 
									(myCase.caseStatusId.caseStatusId eq 7) or 
									(myCase.caseStatusId.caseStatusId eq 10) or 
									(myCase.caseStatusId.caseStatusId eq 22)}">
						<c:out value="${myCase.assignmentNote}" />
					</c:when>
					<c:otherwise>
						<c:out value="${myCase.description}" />
					</c:otherwise>
				</c:choose>
				
			</td>
			<td class="tabDetailViewDL" valign="top" width="15%"></td>
			<td class="tabDetailViewDF" valign="top" width="20%"></td>
		</tr>
		<tr>
			<td class="tabDetailViewDL" valign="top" width="15%">Created
				Time :&nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="20%"><c:out
					value="${myCase.createdTime}" /> &nbsp; - &nbsp; <c:out
					value="${myCase.createdBy}" />
			</td>
			<td class="tabDetailViewDL" valign="top" width="15%">Modified
				Time :&nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="20%"><c:out
					value="${myCase.modifiedTime}" /> &nbsp; - &nbsp; <c:out
					value="${myCase.modifiedBy}" />
			</td>
			<td class="tabDetailViewDL" valign="top" width="15%">EDC
				Terminal : &nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="20%"></td>
		</tr>

		<tr>
			<td colspan="20" style="padding: 0px;">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<td style="text-align: left;" class="tabDetailViewDL">&nbsp;

							</td>
							<!-- 					<td class="tabDetailViewDL" align="right" nowrap="nowrap"> -->
							<!-- 						<a href="case?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>&caseCategoryId=<c:out value="${caseCategoryId}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					 -->
							<!-- 					</td> -->
						</tr>
					</tbody>
				</table></td>
		</tr>
	</tbody>
</table>