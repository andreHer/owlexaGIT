		<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
		<META content="Owlexa Healthcare" name=description>
		<META content="Insurances, healthcare and more..." name=keywords>
		<META content="Insurances, healthcare and more..." name=key-phrases>
		<META content="Insurances, healthcare and more..." name=category>
		<META content=www.owlexa.co.id name=owlexa>
		<style type="text/css">
			@font-face {
			   font-family: DinLight;
			   src: url(css/fonts/FF_DIN_Light.otf) format("opentype");
			}
			
			@font-face {
			   font-family: DinRegular;
			   src: url(css/fonts/FF_DIN_Regular.otf) format("opentype");
			}
			
			@font-face {
			   font-family: DinBold;
			   src: url(css/fonts/FF_DIN_Bold.otf) format("opentype");
			}
			
			body{
				font-family:DinLight,Verdana;
				font-size:12px;
			}
			
			.letterTitle {
				font-family: DinBold, Arial, Helvetica, sans-serif;
				font-size: 24px;
				color: rgb(109,110,112);
			}
			
			.letterTitle2 {
				font-family: Calibri;
				font-size: 20px;
				color: white;
				font-weight: bold;
			}
			.footerOwlexa {
				font-family: Calibri;
				font-size: 11px;
				color: white;
				font-weight: bold;
			}
			
			<!--
			@font-face {
			   font-family: DinLight;
			   src: url(css/fonts/FF_DIN_Light.otf) format("opentype");
			}

			@font-face {
			   font-family: DinRegular;
			   src: url(css/fonts/FF_DIN_Regular.otf) format("opentype");
			}

			@font-face {
			   font-family: DinBold;
			   src: url(css/fonts/FF_DIN_Bold.otf) format("opentype");
			}

			body{
				font-family:DinLight,Verdana;
				font-size:12px;
			}

			.letterTitle {
				font-family: DinBold, Arial, Helvetica, sans-serif;
				font-size: 24px;
				color: rgb(109,110,112);
			}

			.style1 {
				font-family: DinLight,Geneva, Arial, Helvetica, sans-serif;
				font-size: 18px;
				font-weight: bold;
				color: #6f6f6f;
			}
			.HeaderName {font-family: DinLight,Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-weight: bold; color:#6f6f6f;}
			.HeaderName2 {font-family: Calibri; font-size: 10px; font-weight: bold; color:#000000;}
			.HeaderName3 {font-family: Calibri; font-size: 13px; font-weight: bold; color:#FFFFFF;}
			.HeaderName4 {font-family: Calibri; font-size: 13px; font-weight: bold; color:#FFFFFF;}
			.Details {font-family: DinLight,Verdana, Arial, Helvetica, sans-serif; font-size: 10px; color: #6f6f6f; }
			.Details2 {font-family: Calibri; font-size: 10px; color: #000000; }
			.DetailsRed {font-family: DinLight,Verdana, Arial, Helvetica, sans-serif; font-size: 10px; color: #FF0000; }
			.style43 {font-family: DinLight,Verdana, Arial, Helvetica, sans-serif; font-size: 11px; font-weight: bold; color: #FFFFFF; }
			.AssessStyleHeader {
				font-size: 9;
				color: #FFFF00;
			}
			.AssessStyleD {font-family:DinLight,Verdana ; font-size: 9px; color: #305CB8; }
			.BOwlexa {
				font-family: DinLight,Verdana, Arial, Helvetica, sans-serif;
				font-size: 12px;
				font-weight: bold;
				color: #6f6f6f;
			}
			.BOwlexa2 {
				font-family: Calibri;
				font-size: 10px;
				color: #000000;
			}
			.BOwlexa3 {
				font-family: Calibri;
				font-size: 10px;
				font-weight: bold;
				color: #000000;
			}
			.BOwlexa4 {
				font-family: Calibri;
				font-size: 12px;
				font-weight: bold;
				color: #FFFFFF;
			}
			.BOwlexa5 {
				font-family: Calibri;
				font-size: 11px;
				font-weight: bold;
				color: #000000;
			}
			.style55 {
				font-size: 9px;
				font-family: DinLight,Verdana;
				color: #6f6f6f;
			}
			.style58 {
				font-size: 11px;
				font-weight: bold;
			}
			.style59 {color: #6f6f6f}
			.style60 {font-family: DinLight,Verdana; font-size: 8px;}
			.style72 {font-family: DinLight,Geneva, Arial, Helvetica, sans-serif; font-size: 24px; font-weight: bold; color: #6f6f6f; }
			.style73 {font-family: DinLight,Verdana}
			.style75 {font-family: DinLight,Geneva, Arial, Helvetica, sans-serif; font-size: 14px; font-weight: bold; color: #6f6f6f; }
			
			.inputuserstyle{
		    	background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:13px DinLight;
		    	line-height:5px;
		    }
		    .inputuserstyle2{
		    	background-color: transparent; color: #000000; border:0px; rgb(0,0,0);font:10px Calibri;
		    }
		    
		    .inputuserstyle3{
		    	background-color: transparent; color: #000000; border:0px; rgb(0,0,0);font:11px Calibri;
		    }
			
			-->
			
		</style>
	</head>
	<BODY link=#000000 vLink=#000000 aLink=#000000 leftMargin=0 bottomMargin=0 marginwidth="0" topmargin="0">
		<DIV align=center>
			<center>
			<table width=775 height=91 border="0" cellPadding=0 cellSpacing=0 id=AutoNumber3 style="BORDER-COLLAPSE: collapse">
				<tr>
					<c:if test="${not empty myCase.memberId.clientId.clientCode }">
						<td align="left">
							<img src="images/owlexa/insurance_<c:out value="${myCase.memberId.clientId.clientCode}" />.png" width="130" height="70"
								onerror="this.style='display:none';">
						</td>
					</c:if>
					<td align="right">
						<table>
							<tr>
								<td align="right" style="font-family: Calibri;font-size: 10">
									<br>
									PT. Aplikanusa Lintasarta<br>
									Menara Thamrin Lt. 18<br>
									Jl. MH Thamrin Kav.3 Jakarta 10250<br>
									Email : cc.owlexa@lintasarta.co.id<br>
									Website : www.lintasarta.net
								</td>
								<td>
									<img src="images/owlexa/owlexa_letter_logo.png" width="80" height="72">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<hr style="margin-left: 2em; margin-right: 2em; color : #00B8CE;border-width: 3px;" noshade="noshade">
		</DIV>