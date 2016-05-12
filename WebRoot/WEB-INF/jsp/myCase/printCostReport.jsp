<%@page import="java.util.Collection"%>
<%@page import="com.ametis.cms.datamodel.Claim"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<head>
	<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
</head>

<%
String rowclass = "";
int i=0;

%>

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>    
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="text-align: right; border: 0px solid #000; font-size: 10px;"></td>
	
	    </tr>
	
		
			
	</tbody>
</table>
<h3 style="border-bottom: 1px; color: #000; solid #000; font-size: 17px; font-weight: bold;text-align: center">FILE TIDAK ADA/FORMAT LAPORAN BELUM ADA</h3>
<h3 style="border-bottom: 1px; color: #000; solid #000; font-size: 17px; font-weight: bold;text-align: center">SILAHKAN HUBUNGI IT</h3>
<h3 style="font-size: 16px; color: #000000; font-weight: bold; text-align: center">LAPORAN PERHITUNGAN BIAYA PERAWATAN</h3>

<br />
<br />
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">TO&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px ; font-size: 12px; color: #000;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px ; font-size: 10px;"></td>
	
	    </tr>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">UP &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px;"></td>
	     
	    </tr>		
		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Faximile &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000; "> </td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000;  font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px; color: #000;"></td>
	     
	    </tr>
	 
	</tbody>
</table>
<br />

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Nama&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px ; font-size: 12px; color: #000;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px ; font-size: 10px;"></td>
	
	    </tr>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">No Guarantee Letter &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px;"></td>
	     
	    </tr>		
		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Nomor Peserta &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000; "></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000;  font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px; color: #000;"></td>
	     
	    </tr>
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Perusahaan / Group &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000;  font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px; color: #000;"></td>
	     
	    </tr>	
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Tanggal Perawatan &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000;"> s/d </td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000;  font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px; color: #000;"></td>
	     
	    </tr>
   	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Plan &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000;  font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px; color: #000;"></td>
	     
	    </tr>		
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Room And Board &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000;  font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px; color: #000;"></td>
	     
	    </tr>			
	    			
	</tbody>
</table>

<br />
<table  cellspacing="0" cellpadding="0" >
	<tbody>
	
	<tr height="20">
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%" style="text-align: center; border: 1px solid #000; font-size: 10px;">
			No. &nbsp;</td>	
		<td scope="col" class="listViewThS1"  width="10%" style="text-align: center; border: 1px solid #000;font-size: 10px;">
			Benefit &nbsp;</td>	
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%" style="text-align: center; border: 1px solid #000;font-size: 10px;">
			Charges&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%" style="text-align: center; border: 1px solid #000;font-size: 10px;">
			Approved&nbsp;</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%" style="text-align: center; border: 1px solid #000;font-size: 10px;">
			Excess &nbsp;</td>	
		<td scope="col" class="listViewThS1" width="25%" style="text-align: center; border: 1px solid #000;font-size: 10px;">
			Remarks&nbsp;</td>

		
	</tr>


	 <tr height="20">
			<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" height="9" style="border-left: 1px solid #000000;font-size: 10px;"> 
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; text-align:left;font-size: 10px;">
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;  text-align:right;font-size: 10px;">
			</td>			   		   		   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;  text-align:right;font-size: 10px;">
			</td>		   		   		   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;  text-align:right;font-size: 10px;">
			</td>			   		   		   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000; text-align:left;font-size: 10px;">
				&nbsp;
			</td>			   		   		
			
    	</tr>
	
	
	<tr height="20">
		<td scope="col" class="listViewThS1" colspan=2 width="3%" style="text-align: center; border: 1px solid #000; font-size: 10px;">
			TOTAL</td>	
		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%" style="text-align: right; border: 1px solid #000;font-size: 10px;">
			&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%" style="text-align: right; border: 1px solid #000;font-size: 10px;">
			&nbsp;</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%" style="text-align: right; border: 1px solid #000;font-size: 10px;">
			&nbsp;</td>	
		<td scope="col" class="listViewThS1" width="25%" style="text-align: center; border: 1px solid #000;font-size: 10px;">
			&nbsp;</td>

		
	</tr>
	</table>

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Diagnosa Akhir : &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px ; font-size: 12px; color: #000;"> - </td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px ; font-size: 10px;"></td>
	
	    </tr>	
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Catatan : &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px ; font-size: 12px; color: #000;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px ; font-size: 10px;"></td>
	
	    </tr>				
	    			
	</tbody>
</table>
<br />
<h4 style="font-size: 14px; color: #000000; font-weight: bold; text-align: left">ESTIMASI INI BERSIFAT SEMENTARA APABILA ADA BIAYA BELUM DITAGIH AKAN DITAGIH KEMUDIAN</h4>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
		<tr>
	      <td colspan=2 class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;"> &nbsp;</td>		  
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px ; font-size: 10px;"></td>	
	    </tr>    			
	</tbody>
</table>
<br />
<br />
<br />
<br />

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
		<tr>
	      <td colspan=2 class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">&nbsp;</td>		  
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px ; font-size: 10px;"></td>	
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px ; font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px ; font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px ; font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px ; font-size: 10px;"></td>
	    </tr>  
	    <tr>
	      <td colspan=2 class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; text-decoration: underline; color: #000; font-size: 12px;"></td>		  
	      <td class="tabDetailViewDF" valign="top" width="5%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="5%" style="border: 0px ; font-size: 10px;"></td>	
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px ; text-decoration: underline; color: #000; font-size: 12px;">Pihak Rumah Sakit</td>
	      <td class="tabDetailViewDF" valign="top" width="8%" style="border: 0px ; font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="5%" style="border: 0px ; font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px ; text-decoration: underline; color: #000; font-size: 12px;">Pasien / Keluarga</td>
	      
	    </tr>    			
	</tbody>
</table>
