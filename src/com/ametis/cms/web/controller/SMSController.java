package com.ametis.cms.web.controller;

import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberClausulService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.ProductBenefitService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.util.WebUtil;

public class SMSController implements Controller{

	private MemberBenefitService memberBenefitService;
	private MemberService memberService;
	private MemberClausulService memberClausulService;
	private ProductService productService;
	private ProductBenefitService productBenefitService;
	private MemberProductService memberProductService;
	private ClaimService claimService;
	
	
	private String username;
	private String password;
	
	private String smsGatewayURL;
	private String smsGatewayPort;
	
	
	private URL smsSenderURL;
	
	
	
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public ClaimService getClaimService() {
		return claimService;
	}
	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}
	public String getSmsGatewayPort() {
		return smsGatewayPort;
	}
	public void setSmsGatewayPort(String smsGatewayPort) {
		this.smsGatewayPort = smsGatewayPort;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSmsGatewayURL() {
		return smsGatewayURL;
	}
	public void setSmsGatewayURL(String smsGatewayURL) {
		this.smsGatewayURL = smsGatewayURL;
	}
	public URL getSmsSenderURL() {
		return smsSenderURL;
	}
	public void setSmsSenderURL(URL smsSenderURL) {
		this.smsSenderURL = smsSenderURL;
	}
	
	
	public MemberProductService getMemberProductService() {
		return memberProductService;
	}
	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}
	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}
	public void setMemberBenefitService(MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public MemberClausulService getMemberClausulService() {
		return memberClausulService;
	}
	public void setMemberClausulService(MemberClausulService memberClausulService) {
		this.memberClausulService = memberClausulService;
	}
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public ProductBenefitService getProductBenefitService() {
		return productBenefitService;
	}
	public void setProductBenefitService(ProductBenefitService productBenefitService) {
		this.productBenefitService = productBenefitService;
	}
	private ModelAndView generateRawatInapReply (HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = null;
		
		try {
			String isi = WebUtil.getParameterString(request, "isi", "");
			String phone = WebUtil.getParameterString(request, "sender", "");
			
			Member member = authorize(isi, phone);
		}
		catch (Exception e){
			
		}
		return result;
	}
	
	private ModelAndView generateRawatJalanReply (HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = null;
		
		try {
			String isi = WebUtil.getParameterString(request, "isi", "");
			String phone = WebUtil.getParameterString(request, "sender", "");
			
			Member member = authorize(isi, phone);
			
			if (member != null){
				String[] required = {"Member.memberProducts","Member.memberBenefits"};
				member = memberService.get(member.getMemberId(),required);
				
				MemberProduct memberProduct =	memberProductService.getMemberActiveProduct(member.getMemberId(), CaseCategory.OUTPATIENT);
				
				String msg = "";
			
				//Collection<MemberBenefit> memberBenefits = memberBenefitService.get
				
				String nomorID = phone.substring(3);
				nomorID = "0"+nomorID;
				
				NumberFormat nf = NumberFormat.getInstance();
				
				
				String message = URLEncoder.encode("Halo "+member.getFirstName() + " Annual Limit : " + nf.format(member.getCustomerLimit())  + " Usage : " + nf.format(member.getCurrentBenefitUsage()) + " Actual : " + nf.format(member.getActualCustomerLimit()));
				String theLocation = "http://"+smsGatewayURL+":"+smsGatewayPort+"/cgi-bin/sendsms?username="+username;
					theLocation += "&password=" + password +"&from=081111111&to="+nomorID+"&text="+message;
				
					try {
						System.out.println("LOCATION : " + theLocation);
						
						URL url = new URL(theLocation);
						Object res = url.getContent();
						
						System.out.println("RESPONSE : " + res);
						
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	private ModelAndView generateMaternityReply (HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = null;
		
		String nomorID = "";
		
		try {
			String isi = WebUtil.getParameterString(request, "isi", "");
			String phone = WebUtil.getParameterString(request, "sender", "");
			
			Member member = authorize(isi, phone);
			
			if (member != null){
				String[] required = {"Member.memberProducts","Member.memberBenefits"};
				member = memberService.get(member.getMemberId(),required);
				
				MemberProduct memberProduct =	memberProductService.getMemberActiveProduct(member.getMemberId(), CaseCategory.OUTPATIENT);
				
				String msg = "";
			
				//Collection<MemberBenefit> memberBenefits = memberBenefitService.get
				
				nomorID = phone.substring(3);
				nomorID = "0"+nomorID;
				
				NumberFormat nf = NumberFormat.getInstance();
				
				
				String message = URLEncoder.encode("Halo "+member.getFirstName() + " Annual Limit : " + nf.format(member.getCustomerLimit())  + " Usage : " + nf.format(member.getCurrentBenefitUsage()) + " Actual : " + nf.format(member.getActualCustomerLimit()));
				String theLocation = "http://"+smsGatewayURL+":"+smsGatewayPort+"/cgi-bin/sendsms?username="+username;
					theLocation += "&password=" + password +"&from=081111111&to="+nomorID+"&text="+message;
				
					try {
						System.out.println("LOCATION : " + theLocation);
						
						URL url = new URL(theLocation);
						Object res = url.getContent();
						
						System.out.println("RESPONSE : " + res);
						
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	
		}
		catch (Exception e){
			String message = URLEncoder.encode("Data yang anda inginkan tidak tersedia");
			String theLocation = "http://"+smsGatewayURL+":"+smsGatewayPort+"/cgi-bin/sendsms?username="+username;
				theLocation += "&password=" + password +"&from=081111111&to="+nomorID+"&text="+message;
			
				try {
					System.out.println("LOCATION : " + theLocation);
					
					URL url = new URL(theLocation);
					Object resp = url.getContent();
					
					System.out.println("RESPONSE : " + resp);
					
					
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		return result;
	}
	private ModelAndView generateDentalReply (HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = null;
		
		try {
			String isi = WebUtil.getParameterString(request, "isi", "");
			String phone = WebUtil.getParameterString(request, "sender", "");
			
			Member member = authorize(isi, phone);
			
			if (member != null){
				String[] required = {"Member.memberProducts","Member.memberBenefits"};
				member = memberService.get(member.getMemberId(),required);
				
				MemberProduct memberProduct =	memberProductService.getMemberActiveProduct(member.getMemberId(), CaseCategory.OUTPATIENT);
				
				String msg = "";
			
				//Collection<MemberBenefit> memberBenefits = memberBenefitService.get
				
				String nomorID = phone.substring(3);
				nomorID = "0"+nomorID;
				
				NumberFormat nf = NumberFormat.getInstance();
				
				
				String message = URLEncoder.encode("Halo "+member.getFirstName() + " Annual Limit : " + nf.format(member.getCustomerLimit())  + " Usage : " + nf.format(member.getCurrentBenefitUsage()) + " Actual : " + nf.format(member.getActualCustomerLimit()));
				String theLocation = "http://"+smsGatewayURL+":"+smsGatewayPort+"/cgi-bin/sendsms?username="+username;
					theLocation += "&password=" + password +"&from=081111111&to="+nomorID+"&text="+message;
				
					try {
						System.out.println("LOCATION : " + theLocation);
						
						URL url = new URL(theLocation);
						Object res = url.getContent();
						
						System.out.println("RESPONSE : " + res);
						
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	
		}
		catch (Exception e){
			
		}
		return result;
	}
	private ModelAndView generateClaimReply (HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = null;
		
		try {
			String isi = WebUtil.getParameterString(request, "isi", "");
			String phone = WebUtil.getParameterString(request, "sender", "");
			
			Member member = authorize(isi, phone);
			
			boolean isAuthorized = true;
			
			if (member != null){
			
				String[] eqParam = {"memberId.memberId"};
				String[] likeParam = null;
				Object[] eqValue = {member.getMemberId()};
				Object[] likeValue = null;
				
				String messageBefore = "";
				
				int totalClaim = claimService.getTotal(likeParam,likeValue,eqParam,eqValue);
				
				String msg = "";
			
				if (totalClaim < 1){
					messageBefore = "Tidak ada klaim atas nama Anda.";
				}
				else {
					Collection<Claim> claims = claimService.search(likeParam,likeValue ,eqParam,eqValue,false,"admissionDate", 0,2);
					Iterator<Claim> claimIterator = claims.iterator();
					int idx = 0;
					
					while (claimIterator.hasNext()){
						Claim claim = claimIterator.next();
						
						if (claim != null){
							String admission = "";
							String paymentDate = "";
							String jumlah = "";
							
							NumberFormat nf = NumberFormat.getInstance();
							jumlah = nf.format(claim.getClaimChargeValue());
							DateFormat df = DateFormat.getDateInstance();
							
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
							admission = sdf.format(claim.getBatchClaimId().getBatchClaimDate());
							
							
							
							if (claim.getClaimStatus().getCaseStatusId().equals(Claim.CLAIM_PAID)){
							
								paymentDate = sdf.format(claim.getPaymentConfirmDate());
								
								if (idx == 0){
									messageBefore += "Kuitansi anda yg kami terima tgl "+admission+" Rp. "+jumlah+" sdh dibayar tgl "+paymentDate+". ";
								}
								else {
									messageBefore += "Tgl "+admission+" Rp. "+jumlah+" sdh dibayar tgl "+paymentDate+". ";
								}

							}
							else {
								if (idx == 0){
									messageBefore += "Kuitansi anda yg kami terima tgl "+admission+" Rp. "+jumlah+" sdg dalam proses. ";
								}
								else {
									messageBefore += "Tgl "+admission+" Rp. "+jumlah+" sdg dalam proses. ";
								}

							}
						}
						idx += 1;
					}
				}
				messageBefore += "Info lanjut hub HRD prshn Anda.";
				//Collection<MemberBenefit> memberBenefits = memberBenefitService.get
				
				String nomorID = phone.substring(3);
				nomorID = "0"+nomorID;
				
							
				
				String message = URLEncoder.encode(messageBefore);
				System.out.println(message);
				
				String theLocation = "http://"+smsGatewayURL+":"+smsGatewayPort+"/cgi-bin/sendsms?username="+username;
					theLocation += "&password=" + password +"&from=081111111&to="+nomorID+"&text="+message;
				
					try {
						System.out.println("LOCATION : " + theLocation);
						
						URL url = new URL(theLocation);
						Object res = url.getContent();
						
						System.out.println("RESPONSE : " + res);
						
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	private ModelAndView freeTextSMS (HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = null;
		
		try {
			
			String isi = WebUtil.getParameterString(request, "isi", "");
			String phone = WebUtil.getParameterString(request, "sender", "");
			
			String destination = WebUtil.getParameterString(request, "destination", "");
		
			StringTokenizer token = new StringTokenizer(destination,",");
			
			if (token != null && token.countTokens() > 0){
				
				String msg = "";
			
				
//				String nomorID = phone.substring(3);
//				nomorID = "0"+nomorID;
//				
//				NumberFormat nf = NumberFormat.getInstance();
				
				
				while (token.hasMoreTokens()){
				
					String currentNumber = token.nextToken();
					
					String theLocation = "http://"+smsGatewayURL+":"+smsGatewayPort+"/cgi-bin/sendsms?username="+username;
						theLocation += "&password=" + password +"&from=081111111&to="+currentNumber+"&text="+URLEncoder.encode(isi);
					
						try {
							System.out.println("LOCATION : " + theLocation);
							
							URL url = new URL(theLocation);
							Object res = url.getContent();
							
							System.out.println("RESPONSE : " + res);
							
							
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	private ModelAndView generateOpticalReply (HttpServletRequest request, HttpServletResponse response){
		ModelAndView result =null;
		
		try {
			String isi = WebUtil.getParameterString(request, "isi", "");
			String phone = WebUtil.getParameterString(request, "sender", "");
			
			Member member = authorize(isi, phone);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	private ModelAndView generateLainLainReply (HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = null;
		
		try {
			String isi = WebUtil.getParameterString(request, "isi", "");
			String phone = WebUtil.getParameterString(request, "sender", "");
			
			Member member = authorize(isi, phone);
			
			if (member != null){
				
			}
		}
		catch (Exception e){
			
		}
		
		return result;
	}
	
	private Member authorize (String incomingMessage, String phone){
		

		Member member = null;
		if (incomingMessage!= null){
			StringTokenizer token = new StringTokenizer(incomingMessage);
			String tipe = token.nextToken();
			String customerNumber = "";
			String bday = "";
			
			
			
			if (token.hasMoreTokens()){
				customerNumber = token.nextToken();
			}
			/*
			if (token.hasMoreTokens()){
				bday = token.nextToken();
			}*/
			
			if (customerNumber != null ){
				try {
					//member = memberService.getMember(customerNumber, bday);
					member = memberService.getMember(customerNumber);
				}
				catch (Exception e){
					
				}
			}
		}
		
		String nomorID = phone.substring(3);
		nomorID = "0"+nomorID;
		
		if (member == null){
			String message = URLEncoder.encode("Data yang anda inginkan tidak tersedia");
			String theLocation = "http://"+smsGatewayURL+":"+smsGatewayPort+"/cgi-bin/sendsms?username="+username;
				theLocation += "&password=" + password +"&from=081111111&to="+nomorID+"&text="+message;
			
				try {
					System.out.println("LOCATION : " + theLocation);
					
					URL url = new URL(theLocation);
					Object response = url.getContent();
					
					System.out.println("RESPONSE : " + response);
					
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return member;
	}
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String navigation = WebUtil.getParameterString(request, "jenis", "");
		System.out.println("DAPET SMS DENGAN JENIS : " + navigation);
		ModelAndView result = null;
		
		String message = WebUtil.getParameterString(request, "isi", "");
		String phone = WebUtil.getParameterString(request, "sender", "");
		
		System.out.println(message);
		
		
		
		if (navigation.equalsIgnoreCase("RO")){
			result = generateOpticalReply(request, response);
		}
		else if (navigation.equalsIgnoreCase("RJ")){
			result = generateRawatJalanReply(request, response);
		}
		else if (navigation.equalsIgnoreCase("RM")){
			result = generateMaternityReply(request, response);
		}
		else if (navigation.equalsIgnoreCase("RI")){
			result = generateRawatInapReply(request, response);
		}
		else if (navigation.equalsIgnoreCase("RD")){
			result = generateDentalReply(request, response);
		}
		else if (navigation.equalsIgnoreCase("PLL")){
			result = generateLainLainReply(request, response);
		}
		else if (navigation.equalsIgnoreCase("sms")){
			result = new ModelAndView("sms");
		}
		else if (navigation.equalsIgnoreCase("free")){
			result = freeTextSMS(request, response);
		}
		else if (navigation.equalsIgnoreCase("CLAIM")){
			result = generateClaimReply(request, response);
		}
		else {
			
			 authorize(message, phone);
			 result = new ModelAndView();
		}
		
		return result;
	}
	

}
