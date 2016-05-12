package com.ametis.cms.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.dao.NotificationDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.MessageTemplate;
import com.ametis.cms.datamodel.Notification;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.PendingCategory;
import com.ametis.cms.datamodel.PendingClaim;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyCoverageFund;
import com.ametis.cms.datamodel.RejectCategory;
import com.ametis.cms.datamodel.RejectedClaim;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MessageTemplateService;
import com.ametis.cms.service.NotificationService;
import com.ametis.cms.service.PendingCategoryService;
import com.ametis.cms.service.RejectCategoryService;
import com.ametis.cms.util.Converter;
import com.ametis.cms.util.dao.DaoSupportUtil;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

// imports+ 

// imports- 

/**
 * Notification is a servlet controller for notification Table. All you have to
 * do is to convert necessary data field to the named parameter
 */
public class NotificationServiceImpl implements NotificationService

// extends+

// extends-
{

	private NotificationDao notificationDao;
	private MessageTemplateService templateService;
	private ConfigurationService configurationService;
	private RejectCategoryService rejectCategoryService;
	private PendingCategoryService pendingCategoryService;
	private MemberProductService memberProductService;




	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public PendingCategoryService getPendingCategoryService() {
		return pendingCategoryService;
	}

	public void setPendingCategoryService(
			PendingCategoryService pendingCategoryService) {
		this.pendingCategoryService = pendingCategoryService;
	}

	public RejectCategoryService getRejectCategoryService() {
		return rejectCategoryService;
	}

	public void setRejectCategoryService(RejectCategoryService rejectCategoryService) {
		this.rejectCategoryService = rejectCategoryService;
	}


	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;



	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public MessageTemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(MessageTemplateService templateService) {
		this.templateService = templateService;
	}

	public void setNotificationDao(NotificationDao object) {
		this.notificationDao = object;
	}

	public NotificationDao getNotificationDao() {
		return this.notificationDao;
	}

	private String getMemberClaimContent(BatchClaim batchClaim, MessageTemplate template){
		String result = "";
		try {
			if (template != null && template.getTemplateUrl() != null){

				Properties p = new Properties();
				p.setProperty( "resource.loader", "class" );
				p.setProperty( "class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
				p.setProperty( "resource.loader", "file" );
				p.setProperty( "file.resource.loader.path", "" );

				VelocityEngine ve = new VelocityEngine();
				ve.init(p);

				Template t = ve.getTemplate(template.getTemplateUrl());
				VelocityContext ctx = new VelocityContext();
				ctx.put("name", batchClaim.getMemberId().getFirstName());
				ctx.put("totalClaim", batchClaim.getTotalClaim());
				ctx.put("totalAmount", Converter.getMoney(batchClaim.getBatchClaimInitialValue()));
				ctx.put("receivedDate", batchClaim.getBatchClaimDate());

				if (batchClaim.getBatchClaimFinalValue() != null){
					ctx.put("paidAmount", Converter.getMoney(batchClaim.getBatchClaimFinalValue()) );
				}
				if (batchClaim.getPaymentDate() != null){
					ctx.put("paymentDate", batchClaim.getPaymentDate());
				}

				StringWriter writer = new StringWriter();
				t.merge(ctx, writer);

				result = writer.toString();

			}

		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	private String getFundWarningContent(Policy policy,PolicyCoverageFund fund, MessageTemplate template){
		String result = "";
		try {
			if (template != null && template.getTemplateUrl() != null){

				Properties p = new Properties();
				p.setProperty( "resource.loader", "class" );
				p.setProperty( "class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
				p.setProperty( "resource.loader", "file" );
				p.setProperty( "file.resource.loader.path", "" );



				VelocityEngine ve = new VelocityEngine();
				ve.init(p);

				Template t = ve.getTemplate(template.getTemplateUrl());

				String blockLimit = ""; 
				String warningLimit = "";
				String caseCategoryCode = "";

				if (fund != null){
					blockLimit = formatDecimal(fund.getMinimumAsoValue());
					warningLimit =  formatDecimal(fund.getWarningAsoValue());
					caseCategoryCode = fund.getCaseCategoryId().getCaseCategoryName();
				}
				else {
					blockLimit = formatDecimal(policy.getFundBlockingLimit());
					warningLimit =  formatDecimal(policy.getMinimumPolicyFund());
				}

				VelocityContext ctx = new VelocityContext();
				ctx.put("policyNumber",policy.getPolicyNumber());
				ctx.put("fundWarningLimit", warningLimit);
				ctx.put("fundBlockLimit", blockLimit);
				ctx.put("groupCode", policy.getGroupCode());
				ctx.put("groupName", policy.getMemberGroupId().getGroupName());
				ctx.put("caseCategory", caseCategoryCode);


				StringWriter writer = new StringWriter();
				t.merge(ctx, writer);

				result = writer.toString();

			}

		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String formatDecimal(double value) {
		DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
		DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

		formatRp.setCurrencySymbol("Rp. ");
		formatRp.setMonetaryDecimalSeparator(',');
		formatRp.setGroupingSeparator('.');

		kursIndonesia.setDecimalFormatSymbols(formatRp);
		String result = kursIndonesia.format(value);
		return result;
	}
	private String getFundBlockContent(Policy policy,PolicyCoverageFund fund, MessageTemplate template){
		String result = "";
		try {
			if (template != null && template.getTemplateUrl() != null){

				Properties p = new Properties();
				p.setProperty( "resource.loader", "class" );
				p.setProperty( "class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
				p.setProperty( "resource.loader", "file" );
				p.setProperty( "file.resource.loader.path", "" );

				VelocityEngine ve = new VelocityEngine();
				ve.init(p);

				Template t = ve.getTemplate(template.getTemplateUrl());
				VelocityContext ctx = new VelocityContext();

				String blockLimit = "";
				String caseCategoryCode = "";

				if (fund != null){
					blockLimit = formatDecimal(fund.getMinimumAsoValue());
					caseCategoryCode = fund.getCaseCategoryId().getCaseCategoryName();
				}
				else {
					blockLimit = formatDecimal(policy.getFundBlockingLimit());
				}


				ctx.put("policyNumber",policy.getPolicyNumber());
				ctx.put("fundBlockLimit", blockLimit);
				ctx.put("groupCode", policy.getGroupCode());
				ctx.put("groupName", policy.getMemberGroupId().getGroupName());
				ctx.put("caseCategory", caseCategoryCode);



				StringWriter writer = new StringWriter();
				t.merge(ctx, writer);

				result = writer.toString();

			}

		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}


	/*
	 * Method create (Notification object) berfungsi untuk melakukan penambahan
	 * sebuah object kedalam database @param object adalah sebuah object yang
	 * ingin diubah @return object hasil kreasi,lengkap dengan assigned primary
	 * key, exception jika gagal
	 */
	public Notification create(Notification object, ActionUser user)
			throws Exception {

		object
		.setCreatedTime(new java.sql.Timestamp(System
				.currentTimeMillis()));

		if (user != null && user.getUser() != null) {
			object.setCreatedBy(user.getUser().getUsername());
		}

		/*if (object.getTemplateId() != null && object.getTemplateId().getId() != null){
			System.out.println("GETTING MESSSSSSAGE TEMPLATE : " + object.getTemplateId().getId());
			MessageTemplate template = templateService.get(object.getTemplateId().getId());

			String content = "";
			String from = "";
			String subject = "";

			Configuration config = configurationService.getSystemConfiguration();

			if (config != null){
				from = config.getEmailNotifier();
			}



			if (template != null && object.getMessageType().intValue() == Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL){
				content = getMemberClaimContent(object.getBatchClaimId(), template);
			}
			if (template != null && object.getMessageType().intValue() == Notification.ASO_FUND_BLOCK_NOTIFICATION_EMAIL){
				content = getFundBlockContent(object.getPolicyId(),object.getPolicyCoverageFund(), template);
				subject = "[BLOCK] Blocked ASO Fund Notification";
			}
			if (template != null && object.getMessageType().intValue() == Notification.ASO_FUND_WARNING_NOTIFICATION_EMAIL){
				content = getFundWarningContent(object.getPolicyId(),object.getPolicyCoverageFund(), template);
				subject = "[WARNING] Minimum ASO Fund Notification";
			}
			if (template != null && object.getMessageType().intValue() == Notification.EXCESS_FUND_BLOCK_NOTIFICATION_EMAIL){
				content = getFundBlockContent(object.getPolicyId(),object.getPolicyCoverageFund(), template);
				subject = "[BLOCK] Blocked ASO Excess Deposit Notification";
			}
			if (template != null && object.getMessageType().intValue() == Notification.EXCESS_FUND_WARNING_NOTIFICATION_EMAIL){
				content = getFundWarningContent(object.getPolicyId(),object.getPolicyCoverageFund(), template);
				subject = "[WARNING] Minimum ASO Excess Deposit Notification";
			}

			object.setContent(content);



			String host = config.getEmailServer();
			int port = 25;

			if (config.getEmailPort() != null && !config.getEmailPort().equalsIgnoreCase("")){
				port = Integer.valueOf(config.getEmailPort());
			}

			System.out.println("Sending email using : " + host + " port : " + port +  " to : " + object.getDestination());

			if (object.getDestination() != null && !object.getDestination().equalsIgnoreCase("")){
				Email  email = new SimpleEmail();
				email.setHostName(host);
				email.setSmtpPort(port);
				email.setMsg(content);


				email.setFrom(from);
				email.setTLS(true);

				email.setSubject(subject);
				email.addTo(object.getDestination());			
				email.send();
			}


		}*/

		Notification result = notificationDao.create(object);

		return result;
	}

	//Riyan D, Create email notif PAID
	/*
	 * Method create (Notification object) berfungsi untuk melakukan penambahan
	 * sebuah object kedalam database @param object adalah sebuah object yang
	 * ingin diubah @return object hasil kreasi,lengkap dengan assigned primary
	 * key, exception jika gagal
	 */
	public Notification createPAID(Notification object, ActionUser user,BatchClaim data,Collection<Claim> claimList,Payment payment)
			throws Exception {

		object
		.setCreatedTime(new java.sql.Timestamp(System
				.currentTimeMillis()));

		if (user != null && user.getUser() != null) {
			object.setCreatedBy(user.getUser().getUsername());
		}

		if (object.getTemplateId() != null && object.getTemplateId().getId() != null){
			System.out.println("GETTING MESSSSSSAGE TEMPLATE : " + object.getTemplateId().getId());
			MessageTemplate template = templateService.get(object.getTemplateId().getId());

			String content = "";
			String from = "";
			String subject = "Subject";
			String message = "";
			String host ="";
			String bcc="";
			String cc="";
			String pass="";

			Configuration config = configurationService.getSystemConfiguration();

			if (config != null){
				from = config.getEmailNotifier();
				host = config.getEmailServer();
				bcc = config.getEmailBccFinance();
				pass= config.getEmailPassword();
				cc= config.getEmailBccClaim();
			}



			//Begin Riyan Convert PDF



			try {


				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				Rectangle two = new Rectangle(700,400);
				Document document = new Document(PageSize.A4_LANDSCAPE.rotate());
				PdfWriter.getInstance(document, baos);
				ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
				document.open();
				addMetaData(document);
				//				      rejectPDF(document,data,claim);
				if (data.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.PROVIDER){
					PAIDPROVIDERPDF(document,data,claimList,payment);
				}
				else{
					PAIDPDF(document,data,claimList);
				}


				//

				//				      document.left(100f);
				//				      document.right(150f);
				//				      addContent(document);
				document.close();

				//email
				baos.flush();
				// Step1

				System.out.println("\n 1st ===> setup Mail Server Properties..");
				mailServerProperties = System.getProperties();
				mailServerProperties.put("mail.smtp.port", config.getEmailPort());//587
				mailServerProperties.put("mail.smtp.auth", "true");
				mailServerProperties.put("mail.smtp.starttls.enable", "false");
				mailServerProperties.put("mail.smtp.ssl.enable", "false");
				mailServerProperties.put("smtpd_recipient_restrictions" ,"true" );
				System.out.println("Mail Server Properties have been setup successfully..");

				// Step2
				System.out.println("\n\n 2nd ===> get Mail Session..");
				getMailSession = Session.getDefaultInstance(mailServerProperties, null);
				generateMailMessage = new MimeMessage(getMailSession);
				generateMailMessage.setFrom( new InternetAddress(from));
				
				//email member
				if (data.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER){
					for(Claim claim : claimList){
						generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(claim.getMemberId().getEmail()));
					}
				}
				else{
					generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(object.getDestination()));
				}
				if(bcc!=null){
					generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
				}
				if(cc!=null){
					generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
				}

				//email2
				//				   		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("test2@crunchify.com"));
				generateMailMessage.setSubject("PAID "+data.getBatchClaimNumber());


				//kirim message
				MimeBodyPart pesan 	= new MimeBodyPart();
				pesan.setText("Dengan hormat, \r\n" +
						"Bersama surat ini kami informasikan bahwa klaim "+data.getBatchClaimNumber()+" yang diajukan telah selesai diproses.\r\n berikut rinciannya kami kirimkan dalam file attachment. \r\n\r\n" +
						"Hormat kami, \r\n" +
						"Owlexa Healthcare \r\n" +
						"Surat ini diterbitkan secara otomatis, tanda tangan petugas tidak diperlukan");


				//Kirim Attacment
				BodyPart messageBodyPart = new MimeBodyPart(bais);
				Multipart multipart = new MimeMultipart();
				messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(baos.toByteArray(),"application/pdf")));
				messageBodyPart.setFileName("PAID"+data.getBatchClaimNumber()+".pdf");
				String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
				generateMailMessage.setText(emailBody, "text/html");

				multipart.addBodyPart(messageBodyPart);
				multipart.addBodyPart(pesan);
				generateMailMessage.setContent(multipart);

				System.out.println("Mail Session has been created successfully..");

				// Step3
				System.out.println("\n\n 3rd ===> Get Session and Send mail");
				Transport transport = getMailSession.getTransport("smtp");
				//				   		transport.connect("localhost", "system@owlexa.com", "");
				//					   		transport.connect("10.24.19.82", "system@owlexa.com", "");
				transport.connect(host, from, pass);
				transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
				transport.close();


			} catch (Exception e) {
				e.printStackTrace();
			}

			//			end





		}

		Notification result = notificationDao.create(object);

		return result;
	}

	public Notification createCDV(Notification object, ActionUser user,BatchClaim data,Collection<Claim> claimList)
			throws Exception {

		object
		.setCreatedTime(new java.sql.Timestamp(System
				.currentTimeMillis()));

		if (user != null && user.getUser() != null) {
			object.setCreatedBy(user.getUser().getUsername());
		}

		if (object.getTemplateId() != null && object.getTemplateId().getId() != null){
			System.out.println("GETTING MESSSSSSAGE TEMPLATE : " + object.getTemplateId().getId());
			MessageTemplate template = templateService.get(object.getTemplateId().getId());

			String content = "";
			String from = "";
			String subject = "Subject";
			String message = "";
			String host ="";
			String bcc="";
			String cc="";
			String pass="";

			Configuration config = configurationService.getSystemConfiguration();

			if (config != null){
				from = config.getEmailNotifier();
				host = config.getEmailServer();
				bcc = config.getEmailBccFinance();
				pass= config.getEmailPassword();
				cc= config.getEmailBccClaim();
			}


			//Begin Riyan Convert PDF



			try {


				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				Rectangle two = new Rectangle(700,400);
				Document document = new Document(PageSize.A4_LANDSCAPE.rotate());
				PdfWriter.getInstance(document, baos);
				ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
				document.open();
				addMetaData(document);
				//				      rejectPDF(document,data,claim);
				CDVIssuedPDF(document,data,claimList);

				//

				//				      document.left(100f);
				//				      document.right(150f);
				//				      addContent(document);
				document.close();

				//email
				baos.flush();
				// Step1

				System.out.println("\n 1st ===> setup Mail Server Properties..");
				mailServerProperties = System.getProperties();
				mailServerProperties.put("mail.smtp.port", config.getEmailPort());//587
				mailServerProperties.put("mail.smtp.auth", "true");
				mailServerProperties.put("mail.smtp.starttls.enable", "false");
				mailServerProperties.put("mail.smtp.ssl.enable", "false");
				mailServerProperties.put("smtpd_recipient_restrictions" ,"true" );
				System.out.println("Mail Server Properties have been setup successfully..");

				// Step2
				System.out.println("\n\n 2nd ===> get Mail Session..");
				getMailSession = Session.getDefaultInstance(mailServerProperties, null);
				generateMailMessage = new MimeMessage(getMailSession);
				generateMailMessage.setFrom( new InternetAddress(from));
				//email member
				if (data.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER){
					for(Claim claim : claimList){
						generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(claim.getMemberId().getEmail()));
					}
				}
				else{
					generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(object.getDestination()));
				}				if(bcc!=null){
					generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
				}
				if(cc!=null){
					generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
				}

				//email2
				//				   		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("test2@crunchify.com"));
				generateMailMessage.setSubject("CDV Issued"+data.getBatchClaimNumber());

				//kirim message
				MimeBodyPart pesan 	= new MimeBodyPart();
				pesan.setText("Dengan hormat, \r\n" +
						"Bersama surat ini kami informasikan bahwa klaim "+data.getBatchClaimNumber()+" yang diajukan telah selesai diproses.\r\n berikut rinciannya kami kirimkan dalam file attachment. \r\n\r\n" +
						"Hormat kami, \r\n" +
						"Owlexa Healthcare \r\n" +
						"Surat ini diterbitkan secara otomatis, tanda tangan petugas tidak diperlukan");

				//Kirim Attacment
				BodyPart messageBodyPart = new MimeBodyPart(bais);
				Multipart multipart = new MimeMultipart();
				messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(baos.toByteArray(),"application/pdf")));
				messageBodyPart.setFileName("CDVIssued "+data.getBatchClaimNumber()+".pdf");
				String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
				generateMailMessage.setText(emailBody, "text/html");

				multipart.addBodyPart(messageBodyPart);
				multipart.addBodyPart(pesan);
				generateMailMessage.setContent(multipart);

				System.out.println("Mail Session has been created successfully..");

				// Step3
				System.out.println("\n\n 3rd ===> Get Session and Send mail");
				Transport transport = getMailSession.getTransport("smtp");
				//				   		transport.connect("localhost", "system@owlexa.com", "");
				//					   		transport.connect("10.24.19.82", "system@owlexa.com", "");
				transport.connect(host, from, pass);
				transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
				transport.close();


			} catch (Exception e) {
				e.printStackTrace();
			}

			//			end





		}

		Notification result = notificationDao.create(object);

		return result;
	}


	//Riyan, Notif Email Reject
	/*
	 * Method create (Notification object) berfungsi untuk melakukan penambahan
	 * sebuah object kedalam database @param object adalah sebuah object yang
	 * ingin diubah @return object hasil kreasi,lengkap dengan assigned primary
	 * key, exception jika gagal
	 */
	public Notification createReject(Notification object, ActionUser user,RejectedClaim data, Claim claim,BatchClaim bc)
			throws Exception {

		object
		.setCreatedTime(new java.sql.Timestamp(System
				.currentTimeMillis()));

		if (user != null && user.getUser() != null) {
			object.setCreatedBy(user.getUser().getUsername());
		}

		if (object.getTemplateId() != null && object.getTemplateId().getId() != null){
			System.out.println("GETTING MESSSSSSAGE TEMPLATE : " + object.getTemplateId().getId());
			MessageTemplate template = templateService.get(object.getTemplateId().getId());

			String content = "";
			String from = "";
			String subject = "Subject";
			String message = "";
			String host ="";
			String bcc="";
			String cc="";
			String pass="";

			Configuration config = configurationService.getSystemConfiguration();

			if (config != null){
				from = config.getEmailNotifier();
				host = config.getEmailServer();
				bcc = config.getEmailBccFinance();
				pass= config.getEmailPassword();
				cc= config.getEmailBccClaim();
			}

			//Begin Riyan Convert PDF



			try {


				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				Rectangle two = new Rectangle(700,400);
				Document document = new Document(PageSize.A4_LANDSCAPE.rotate());
				PdfWriter.getInstance(document, baos);
				ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
				document.open();
				addMetaData(document);
				rejectPDF(document,data,claim,bc);

				//

				//				      document.left(100f);
				//				      document.right(150f);
				//				      addContent(document);
				document.close();

				//email
				baos.flush();
				// Step1

				System.out.println("\n 1st ===> setup Mail Server Properties..");
				mailServerProperties = System.getProperties();
				mailServerProperties.put("mail.smtp.port", config.getEmailPort());//587
				mailServerProperties.put("mail.smtp.auth", "true");
				mailServerProperties.put("mail.smtp.starttls.enable", "false");
				mailServerProperties.put("mail.smtp.ssl.enable", "false");
				mailServerProperties.put("smtpd_recipient_restrictions" ,"true" );
				System.out.println("Mail Server Properties have been setup successfully..");

				// Step2
				System.out.println("\n\n 2nd ===> get Mail Session..");
				getMailSession = Session.getDefaultInstance(mailServerProperties, null);
				generateMailMessage = new MimeMessage(getMailSession);
				generateMailMessage.setFrom( new InternetAddress(from));
				if(bcc!=null){
					generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
				}
				if(cc!=null){
					generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
				}

				generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(object.getDestination()));
				//email2
				//				   		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("test2@crunchify.com"));
				generateMailMessage.setSubject(data.getRejectionSubject());

				//kirim message
				MimeBodyPart pesan 	= new MimeBodyPart();
				pesan.setText("Dengan hormat, \r\n" +
						"Bersama surat ini kami informasikan bahwa klaim "+data.getClaimNumber()+" yang diajukan telah selesai diproses.\r\n berikut rinciannya kami kirimkan dalam file attachment. \r\n\r\n" +
						"Hormat kami, \r\n" +
						"Owlexa Healthcare \r\n" +
						"Surat ini diterbitkan secara otomatis, tanda tangan petugas tidak diperlukan");

				//Kirim Attacment
				BodyPart messageBodyPart = new MimeBodyPart(bais);
				Multipart multipart = new MimeMultipart();
				messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(baos.toByteArray(),"application/pdf")));
				messageBodyPart.setFileName("RejectedClaim"+claim.getClaimNumber()+".pdf");
				String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
				//					   		messageBodyPart.setText("test text");

				multipart.addBodyPart(messageBodyPart);
				multipart.addBodyPart(pesan);

				generateMailMessage.setContent(multipart);
				generateMailMessage.setDescription("test description");
				//					   		String pesan = "test email";
				//					   		generateMailMessage.setText(pesan, "text/html; charset=utf-8");

				System.out.println("Mail Session has been created successfully..");

				// Step3
				System.out.println("\n\n 3rd ===> Get Session and Send mail");
				Transport transport = getMailSession.getTransport("smtp");
				//				   		transport.connect("localhost", "system@owlexa.com", "");
				//					   		transport.connect("10.24.19.82", "system@owlexa.com", "");
				transport.connect(host, from, pass);
				transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
				transport.close();


			} catch (Exception e) {
				e.printStackTrace();
			}

			//			end





		}

		Notification result = notificationDao.create(object);

		return result;
	}



	//Riyan, Notif Email Pending
	/*
	 * Method create (Notification object) berfungsi untuk melakukan penambahan
	 * sebuah object kedalam database @param object adalah sebuah object yang
	 * ingin diubah @return object hasil kreasi,lengkap dengan assigned primary
	 * key, exception jika gagal
	 */
	public Notification createPending(Notification object, ActionUser user,PendingClaim data,Claim claim,BatchClaim bc)
			throws Exception {

		object
		.setCreatedTime(new java.sql.Timestamp(System
				.currentTimeMillis()));

		if (user != null && user.getUser() != null) {
			object.setCreatedBy(user.getUser().getUsername());
		}

		if (object.getTemplateId() != null && object.getTemplateId().getId() != null){
			System.out.println("GETTING MESSSSSSAGE TEMPLATE : " + object.getTemplateId().getId());
			MessageTemplate template = templateService.get(object.getTemplateId().getId());

			String content = "";
			String from = "";
			String subject = data.getPendingSubject();
			String message = data.getRemarks();
			SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
			String tanggal=df.format(data.getPendingDueDate());
			String host="";
			String bcc="";
			String cc="";
			String pass="";


			Configuration config = configurationService.getSystemConfiguration();

			if (config != null){
				from = config.getEmailNotifier();
				host = config.getEmailServer();
				bcc = config.getEmailBccClaim();
				cc= config.getEmailBccClaim();
				pass=config.getEmailPassword();
			}


			//				
			//				if (template != null && object.getMessageType().intValue() == Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL){
			//					content = getMemberClaimContent(object.getBatchClaimId(), template);
			//				}
			//				if (template != null && object.getMessageType().intValue() == Notification.ASO_FUND_BLOCK_NOTIFICATION_EMAIL){
			//					content = getFundBlockContent(object.getPolicyId(),object.getPolicyCoverageFund(), template);
			//					subject = "[BLOCK] Blocked ASO Fund Notification";
			//				}
			//				if (template != null && object.getMessageType().intValue() == Notification.ASO_FUND_WARNING_NOTIFICATION_EMAIL){
			//					content = getFundWarningContent(object.getPolicyId(),object.getPolicyCoverageFund(), template);
			//					subject = "[WARNING] Minimum ASO Fund Notification";
			//				}
			//				if (template != null && object.getMessageType().intValue() == Notification.EXCESS_FUND_BLOCK_NOTIFICATION_EMAIL){
			//					content = getFundBlockContent(object.getPolicyId(),object.getPolicyCoverageFund(), template);
			//					subject = "[BLOCK] Blocked ASO Excess Deposit Notification";
			//				}
			//				if (template != null && object.getMessageType().intValue() == Notification.EXCESS_FUND_WARNING_NOTIFICATION_EMAIL){
			//					content = getFundWarningContent(object.getPolicyId(),object.getPolicyCoverageFund(), template);
			//					subject = "[WARNING] Minimum ASO Excess Deposit Notification";
			//				}

			//Kirim Email PDF
			try {


				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				Document document = new Document(PageSize.A4_LANDSCAPE.rotate());
				PdfWriter.getInstance(document, baos);
				ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
				document.open();
				addMetaData(document);
				pendingPDF(document,data,claim,bc);

				//

				//				      document.left(100f);
				//				      document.right(150f);
				//				      addContent(document);
				document.close();

				//email
				baos.flush();
				// Step1

				System.out.println("\n 1st ===> setup Mail Server Properties..");
				mailServerProperties = System.getProperties();
				mailServerProperties.put("mail.smtp.port", config.getEmailPort());//587
				mailServerProperties.put("mail.smtp.auth", "true");
				mailServerProperties.put("mail.smtp.starttls.enable", "false");
				mailServerProperties.put("mail.smtp.ssl.enable", "false");
				mailServerProperties.put("smtpd_recipient_restrictions" ,"true" );
				System.out.println("Mail Server Properties have been setup successfully..");

				// Step2
				System.out.println("\n\n 2nd ===> get Mail Session..");
				getMailSession = Session.getDefaultInstance(mailServerProperties, null);
				generateMailMessage = new MimeMessage(getMailSession);
				generateMailMessage.setFrom( new InternetAddress(from));
				generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(object.getDestination()));
				if(bcc!=null){
					generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
				}
				if(cc!=null){
					generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
				}

				//email2
				//				   		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("test2@crunchify.com"));
				generateMailMessage.setSubject(data.getPendingSubject());

				//kirim message
				MimeBodyPart pesan 	= new MimeBodyPart();
				pesan.setText("Dengan hormat, \r\n" +
						"Bersama surat ini kami informasikan bahwa klaim "+claim.getClaimNumber()+" yang diajukan telah selesai diproses.\r\n berikut rinciannya kami kirimkan dalam file attachment. \r\n\r\n" +
						"Hormat kami, \r\n" +
						"Owlexa Healthcare \r\n" +
						"Surat ini diterbitkan secara otomatis, tanda tangan petugas tidak diperlukan");

				//Kirim Attacment
				BodyPart messageBodyPart = new MimeBodyPart(bais);
				Multipart multipart = new MimeMultipart();
				messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(baos.toByteArray(),"application/pdf")));
				messageBodyPart.setFileName("PendingClaim"+claim.getClaimNumber()+".pdf");
				String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
				generateMailMessage.setText(emailBody, "text/html");

				multipart.addBodyPart(messageBodyPart);
				multipart.addBodyPart(pesan);
				generateMailMessage.setContent(multipart);

				System.out.println("Mail Session has been created successfully..");

				// Step3
				System.out.println("\n\n 3rd ===> Get Session and Send mail");
				Transport transport = getMailSession.getTransport("smtp");
				//					   		transport.connect("10.24.19.82", "system@owlexa.com", "");
				transport.connect(host, from, pass);
				transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
				transport.close();


			} catch (Exception e) {
				e.printStackTrace();
			}

			//End


		}

		Notification result = notificationDao.create(object);

		return result;
	}



	/*
	 * Method update (Notification object) berfungsi untuk melakukan perubahan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin diubah @return object hasil update,
	 * exception jika gagal
	 */
	public Notification update(Notification object, ActionUser user)
			throws Exception {

		// object.setUpdatedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		notificationDao.update(object);
		return object;
	}

	/*
	 * Method delete (Object pkey) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param pkey adalah
	 * sebuah object yang merepresentasikan primary key dari tabel yang
	 * bersangkutan. Object tersebut dapat dalam bentuk single ID maupun
	 * composite ID @return no return value karena objeknya sendiri sudah
	 * dihapus - just for consistency. Again, exception if failure occured
	 * WARNING ! Invalid value for the returned object, better not use it again
	 * in any place
	 */
	public Notification trash(java.io.Serializable pkey) throws Exception {
		Notification object = notificationDao.get(pkey);
		notificationDao.delete(object);
		return object;
	}

	/*
	 * Method delete (Notification object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public Notification delete(java.io.Serializable pkey, ActionUser user)
			throws Exception {
		Notification object = notificationDao.get(pkey);

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		notificationDao.update(object);
		return object;
	}

	/*
	 * Method delete (Notification object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public Notification delete(Notification object, ActionUser user)
			throws Exception {

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		notificationDao.update(object);
		return object;
	}


	// -- get section - carefull !

	/*
	 * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	 * sebuah object yang terdapat didalam database @param pkey adalah sebuah
	 * object yang merepresentasikan primary key dari tabel yang bersangkutan.
	 * Object tersebut dapat dalam bentuk single ID maupun composite ID @return
	 * Object yang dihasilkan dari proses retrieval, apabila object tidak
	 * ditemukan maka method akan mengembalikan nilai "NULL"
	 */
	public Notification get(java.io.Serializable pkey) throws Exception {
		Notification object = null;
		object = notificationDao.get(pkey);
		return object;
	}

	/*
	 * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	 * sebuah object yang terdapat didalam database @param pkey adalah sebuah
	 * object yang merepresentasikan primary key dari tabel yang bersangkutan.
	 * Object tersebut dapat dalam bentuk single ID maupun composite ID @param
	 * required adalah array dari field-field yang dibutuhkan dari hibernate
	 * object @return Object yang dihasilkan dari proses retrieval, apabila
	 * object tidak ditemukan maka method akan mengembalikan nilai "NULL"
	 */

	public Notification get(java.io.Serializable pkey, String[] required)
			throws Exception {
		Notification object = null;
		object = notificationDao.get(pkey);
		DaoSupportUtil.lazyInit(required, object);
		return object;
	}

	// -- get section end here

	// SEARCH SECTION - PALING RUMIT !!
	// * -> plain
	// *b -> with columnOrder

	// -- 1
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, int index, int offset)
					throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] required,
			int index, int offset) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Notification element = (Notification) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder[], String[] required, int index, int offset)
					throws Exception {
		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Notification element = (Notification) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder, String[] required, int index, int offset)
					throws Exception {
		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Notification element = (Notification) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	// -- 1b

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset)
					throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String[] required, int index,
			int offset) throws Exception {
		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Notification element = (Notification) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], String[] required, int index, int offset)
					throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Notification element = (Notification) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc, String columnOrder,
			String[] required, int index, int offset) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Notification element = (Notification) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	// -- 2 , between

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, int index, int offset)
					throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, String[] required,
			int index, int offset) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Notification element = (Notification) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder[], String[] required, int index, int offset)
					throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Notification element = (Notification) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder, String[] required, int index, int offset)
					throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Notification element = (Notification) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- 2b
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, int index, int offset)
					throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, String[] required,
			int index, int offset) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Notification element = (Notification) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder[], String[] required, int index, int offset)
					throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Notification element = (Notification) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder, String[] required, int index, int offset)
					throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Notification element = (Notification) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- search end

	// -- get total

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2) throws Exception {

		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
		.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// ------------------------------------------------------------------
	public int getTotal() throws Exception {
		Criteria c = notificationDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}

	// -- get total end

	// ---------------------------------------------------
	public Collection getAll(String[] required) throws Exception {

		Criteria c = notificationDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Notification element = (Notification) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection getAll() throws Exception {

		Criteria c = notificationDao.getCriteria();
		List list = c.list();
		return list;
	}

	// -------------------------------------------------

	// unique Result

	public Notification searchUnique(String eqColumns, Object eqParams,
			String[] required) throws Exception {
		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Notification obj = (Notification) c.uniqueResult();
		DaoSupportUtil.lazyInit(required, obj);
		return obj;
	}

	public Notification searchUnique(String eqColumns, Object eqParams)
			throws Exception {
		Criteria c = notificationDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Notification obj = (Notification) c.uniqueResult();
		return obj;
	}

	// -----------------------------------------------

	/*
	 * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
	 * @return criteria
	 */
	public Criteria getCriteria() throws Exception {
		Criteria c = notificationDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = notificationDao.getDetachedCriteria();
		return dc;
	}


	//Riyan Create PDF

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font arial = new Font(Font.FontFamily.TIMES_ROMAN, 10,
			Font.NORMAL	 );
	private static Font arialSmall = new Font(Font.FontFamily.TIMES_ROMAN, 8,
			Font.NORMAL	 );
	private static Font arialHeader = new Font(Font.FontFamily.TIMES_ROMAN, 10,
			Font.BOLD	 );
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);

	private static void addMetaData(Document document) {
		document.addTitle("My first PDF");
		document.addSubject("Using iText");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("Lars Vogel");
		document.addCreator("Lars Vogel");
	}

	private void PAIDPDF(Document document,BatchClaim data,Collection<Claim> claimList)
			throws Exception {




		Paragraph preface = new Paragraph();
		Paragraph preface2 = new Paragraph();
		// We add one empty line
		//		    addEmptyLine(preface, 1);
		//		    // Lets write a big header
		//		    preface.add(new Paragraph("Rejected Claim", catFont));

		addEmptyLine(preface, 1);
		if (data.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER){
			preface.add(new Paragraph("Kepada Yth. \r\n " +data.getMemberId().getFirstName() ,
					arial));

			String polis = data.getMemberId().getCurrentPolicyId().getPolicyNumber()!=null?data.getMemberId().getCurrentPolicyId().getPolicyNumber():"";
			String nomorKartu = data.getMemberId().getCurrentCardNumber()!=null?data.getMemberId().getCurrentCardNumber():"";
			String pasien = data.getMemberId().getFirstName()!=null?data.getMemberId().getFirstName():"";
			String karyawan = data.getMemberId().getParentName()!=null?data.getMemberId().getParentName():"";
			String departement = data.getMemberId().getDepartment()!=null?data.getMemberId().getDepartment():"";

			addEmptyLine(preface, 1);
			preface.add(new Paragraph("No Polis		: "+polis +"\r\n" +
					"Departement	: "+ departement+"\r\n" +
					"Nomor Peserta/Nomor Kartu	:"+nomorKartu +"\r\n" +
					"Nama Pasien	:"+pasien+ "\r\n" +
					"Nama Karyawan	: "+karyawan,
					arial));
		}
		if (data.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER_GROUP){
			preface.add(new Paragraph("Kepada Yth. \r\n " +data.getMemberGroupId().getGroupName() ,
					arial));
		}
		if (data.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.PROVIDER){
			preface.add(new Paragraph("Kepada Yth. \r\n " +data.getProviderId().getProviderName() ,
					arial));
		}



		/*RejectCategory rc;
			    rc = rejectCategoryService.get(data.getRejectionCategory().getRejectCategoryId());
					addEmptyLine(preface, 1);

					preface.add(new Paragraph("No Claim		: "+claim.getClaimNumber() +"\r\n" +
				    		"Rejection Category 	: "+rc.getRejectCategoryName() +"\r\n" +
				    		"Description	:  "+data.getDescription(),
				        arial));*/


		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Dengan Hormat, \r\n" +
				"Bersama surat ini kami informasikan bahwa klaim yang diajukan telah selesai diproses dengan rincian sebagai berikut  : ",
				arial));

		// add a table
		//		   createTable(subCatPart);

		addEmptyLine(preface, 2);
		PdfPTable table = new PdfPTable(12);
		table.setWidthPercentage(100);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Nama RS/ Klinik Rekanan",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Nomor Klaim",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Tanggal Berobat",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Deskripsi Manfaat",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Status",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Sisa Limit",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Total Biaya",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Biaya Disetujui",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Biaya Tidak Disetujui",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Selisih Sudah Dibayar",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Selisih Belum Dibayar",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Remarks",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		for(Claim claim : claimList){

			Date tanggal= claim.getAdmissionDate();
			DateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
			String tanggalBerobat= dateFormat.format(tanggal);


			MemberProduct mp=memberProductService.getMemberActiveProduct(claim.getMemberId().getMemberId(), claim.getCaseCategoryId().getCaseCategoryId());

			//isi tabel


			if(claim.getProviderName()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(claim.getProviderName(), arialSmall));
			}

			if (claim.getClaimNumber()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(claim.getClaimNumber(), arialSmall));
			}
			if(tanggalBerobat==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(tanggalBerobat, arialSmall));	
			}

			table.addCell("");
			table.addCell(new Phrase("PAID", arialSmall));
			if(claim.getRemainingMemberLimit()!=null){

				if(claim.getRemainingMemberLimit() == -1){
					table.addCell("AS CHARGE");
				}
				else{
					table.addCell(new Phrase(Double.toString(claim.getRemainingMemberLimit()),arialSmall));
				}

			}else{
				table.addCell(new Phrase("0",arialSmall));
			}
			/* if(mp.getActualBenefitLimit()==null){
			    	table.addCell("");
			    }else{
			    	table.addCell(new Phrase(Double.toString(mp.getActualBenefitLimit()),arial));
			    }*/
			if(claim.getClaimChargeValue()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(String.valueOf(claim.getClaimChargeValue()), arialSmall) );	
			}
			if(claim.getClaimApprovedValue()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(Double.toString(claim.getClaimApprovedValue()), arialSmall) );	
			}
			if(claim.getClaimExcessValue()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(Double.toString(claim.getClaimExcessValue()), arialSmall) );	
			}
			if(claim.getPrePaidExcess()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(Double.toString(claim.getPrePaidExcess()), arialSmall) );	
			}

			table.addCell("");
			table.addCell("");


			//		    table.addCell("2.2");
			//		    table.addCell("2.3");
			//		    

		}
		preface.add(table);

		addEmptyLine(preface, 2);
		preface.add(new Paragraph("Hormat Kami, \r\n\r\n" +
				"Surat ini diterbitkan secara otomatis, tanda tangan petugas tidak diperlukan \r\n\r\n" +
				"Keterangan : \r\n" +
				"REJECTED			: Klaim ditolak. \r\n" +
				"PENDING			: Klaim di pending. \r\n" +
				"CDV ISSUED			: Klaim siap dibayar / transfer. \r\n" +
				"PAID				: Klaim sudah dibayar / transfer.",
				arial));

		document.add(preface);

		// Start a new page
		document.newPage();
	}

	private void PAIDPROVIDERPDF(Document document,BatchClaim data,Collection<Claim> claimList,Payment payment)
			throws Exception {




		Paragraph preface = new Paragraph();
		Paragraph preface2 = new Paragraph();
		// We add one empty line
		//		    addEmptyLine(preface, 1);
		//		    // Lets write a big header
		//		    preface.add(new Paragraph("Rejected Claim", catFont));

		addEmptyLine(preface, 1);
		if (data.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER){
			preface.add(new Paragraph("Kepada Yth. \r\n " +data.getMemberId().getFirstName() ,
					arial));

			String polis = data.getMemberId().getCurrentPolicyId().getPolicyNumber()!=null?data.getMemberId().getCurrentPolicyId().getPolicyNumber():"";
			String nomorKartu = data.getMemberId().getCurrentCardNumber()!=null?data.getMemberId().getCurrentCardNumber():"";
			String pasien = data.getMemberId().getFirstName()!=null?data.getMemberId().getFirstName():"";
			String karyawan = data.getMemberId().getParentName()!=null?data.getMemberId().getParentName():"";
			String departement = data.getMemberId().getDepartment()!=null?data.getMemberId().getDepartment():"";

			addEmptyLine(preface, 1);
			preface.add(new Paragraph("No Polis		: "+polis +"\r\n" +
					"Departement	: "+ departement+"\r\n" +
					"Nomor Peserta/Nomor Kartu	:"+nomorKartu +"\r\n" +
					"Nama Pasien	:"+pasien+ "\r\n" +
					"Nama Karyawan	: "+karyawan,
					arial));
		}
		if (data.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER_GROUP){
			preface.add(new Paragraph("Kepada Yth. \r\n " +data.getMemberGroupId().getGroupName() ,
					arial));
		}
		if (data.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.PROVIDER){
			preface.add(new Paragraph("Kepada Yth. \r\n " +data.getProviderId().getProviderName() ,
					arial));
		}



		/*RejectCategory rc;
			    rc = rejectCategoryService.get(data.getRejectionCategory().getRejectCategoryId());
					addEmptyLine(preface, 1);

					preface.add(new Paragraph("No Claim		: "+claim.getClaimNumber() +"\r\n" +
				    		"Rejection Category 	: "+rc.getRejectCategoryName() +"\r\n" +
				    		"Description	:  "+data.getDescription(),
				        arial));*/


		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Dengan Hormat, \r\n" +
				"Bersama surat ini kami informasikan bahwa pembayaran klaim yang ditagihkan telah selesai diproses dengan rincian sebagai berikut  : ",
				arial));

		// add a table
		//		   createTable(subCatPart);

		addEmptyLine(preface, 2);
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Nama Perusahaan/ Asuransi",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Nomor Invoice",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Tanggal Invoice",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Nomor Transfer (Voucher) ",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Tanggal Pembayaran",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Total Invoice",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Total Dibayarkan",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Total Belum Dibayarkan",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Remarks",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);



		for(Claim claim : claimList){

			Date tanggal= data.getInvoiceDate();
			DateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
			String tanggalInvoice= dateFormat.format(tanggal);

			Date tanggalPayment= payment.getPaymentConfirmDate();
			DateFormat dateFormatPay= new SimpleDateFormat("dd-MM-yyyy");
			String tanggalPay= dateFormat.format(tanggalPayment);

			MemberProduct mp=memberProductService.getMemberActiveProduct(claim.getMemberId().getMemberId(), claim.getCaseCategoryId().getCaseCategoryId());

			//isi tabel


			if(claim.getProviderName()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(claim.getProviderName(), arialSmall));
			}

			if (data.getInvoiceNumber()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(data.getInvoiceNumber(), arialSmall));
			}
			if(tanggalInvoice==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(tanggalInvoice, arialSmall));	
			}
			if(payment.getPaymentNumber()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(payment.getPaymentNumber(), arialSmall));	
			}

			if(tanggalPay==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(tanggalPay, arialSmall));	
			}
			if(payment.getPaymentValue()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(String.valueOf(payment.getPaymentValue()), arialSmall));	
			}
			if(payment.getPaymentValue()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(Double.toString(payment.getPaymentValue()), arialSmall));	
			}


			if(claim.getPrePaidExcess()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(Double.toString(claim.getPrePaidExcess()), arialSmall) );	
			}

			table.addCell("");


			//		    table.addCell("2.2");
			//		    table.addCell("2.3");
			//		    

		}
		preface.add(table);

		addEmptyLine(preface, 2);
		preface.add(new Paragraph("Untuk bukti pembayaran dan details klaim dapat dilihat melalui link berikut : http://www.owlexa.com \r\n\r\n Hormat Kami, \r\n\r\n" +
				"Surat ini diterbitkan secara otomatis, tanda tangan petugas tidak diperlukan \r\n\r\n" +
				"Keterangan : \r\n" +
				"REJECTED			: Klaim ditolak. \r\n" +
				"PENDING			: Klaim di pending. \r\n" +
				"CDV ISSUED			: Klaim siap dibayar / transfer. \r\n" +
				"PAID				: Klaim sudah dibayar / transfer.",
				arial));

		document.add(preface);

		// Start a new page
		document.newPage();
	}


	private void CDVIssuedPDF(Document document,BatchClaim data,Collection<Claim> claimList)
			throws Exception {




		Paragraph preface = new Paragraph();
		Paragraph preface2 = new Paragraph();
		// We add one empty line
		//		    addEmptyLine(preface, 1);
		//		    // Lets write a big header
		//		    preface.add(new Paragraph("Rejected Claim", catFont));

		addEmptyLine(preface, 1);
		if (data.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER){
			preface.add(new Paragraph("Kepada Yth. \r\n " +data.getMemberId().getFirstName() ,
					arial));

			String polis = data.getMemberId().getCurrentPolicyId().getPolicyNumber()!=null?data.getMemberId().getCurrentPolicyId().getPolicyNumber():"";
			String nomorKartu = data.getMemberId().getCurrentCardNumber()!=null?data.getMemberId().getCurrentCardNumber():"";
			String pasien = data.getMemberId().getFirstName()!=null?data.getMemberId().getFirstName():"";
			String karyawan = data.getMemberId().getParentName()!=null?data.getMemberId().getParentName():"";
			String departement = data.getMemberId().getDepartment()!=null?data.getMemberId().getDepartment():"";


			addEmptyLine(preface, 1);
			preface.add(new Paragraph("No Polis		: "+polis +"\r\n" +
					"Departement	: "+departement +"\r\n" +
					"Nomor Peserta/Nomor Kartu	:"+nomorKartu +"\r\n" +
					"Nama Pasien	:"+pasien+ "\r\n" +
					"Nama Karyawan	: "+karyawan,
					arial));
		}
		if (data.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER_GROUP){
			preface.add(new Paragraph("Kepada Yth. \r\n " +data.getMemberGroupId().getGroupName() ,
					arial));
		}
		if (data.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.PROVIDER){
			preface.add(new Paragraph("Kepada Yth. \r\n " +data.getProviderId().getProviderName() ,
					arial));
		}




		/*RejectCategory rc;
			    rc = rejectCategoryService.get(data.getRejectionCategory().getRejectCategoryId());
					addEmptyLine(preface, 1);

					preface.add(new Paragraph("No Claim		: "+claim.getClaimNumber() +"\r\n" +
				    		"Rejection Category 	: "+rc.getRejectCategoryName() +"\r\n" +
				    		"Description	:  "+data.getDescription(),
				        arial));*/


		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Dengan Hormat, \r\n" +
				"Bersama surat ini kami informasikan bahwa klaim yang diajukan telah selesai diproses dengan rincian sebagai berikut  : ",
				arial));

		// add a table
		//		   createTable(subCatPart);

		addEmptyLine(preface, 2);
		PdfPTable table = new PdfPTable(12);
		table.setWidthPercentage(100);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Nama RS/ Klinik Rekanan",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Nomor Klaim",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Tanggal Berobat",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Deskripsi Manfaat",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Status",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Sisa Limit",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Total Biaya",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Biaya Disetujui",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Biaya Tidak Disetujui",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Selisih Sudah Dibayar",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Selisih Belum Dibayar",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Remarks",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		for(Claim claim : claimList){

			Date tanggal= claim.getAdmissionDate();
			DateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
			String tanggalBerobat= dateFormat.format(tanggal);


			MemberProduct mp=memberProductService.getMemberActiveProduct(claim.getMemberId().getMemberId(), claim.getCaseCategoryId().getCaseCategoryId());

			//isi tabel


			if(claim.getProviderName()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(claim.getProviderName(), arialSmall));
			}

			if (claim.getClaimNumber()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(claim.getClaimNumber(), arialSmall));
			}
			if(tanggalBerobat==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(tanggalBerobat, arialSmall));	
			}

			table.addCell("");
			table.addCell(new Phrase("CDV ISSUED", arialSmall));
			if(claim.getRemainingMemberLimit()!=null){

				if(claim.getRemainingMemberLimit() == -1){
					table.addCell("AS CHARGE");
				}
				else{
					table.addCell(new Phrase(Double.toString(claim.getRemainingMemberLimit()),arialSmall));
				}

			}else{
				table.addCell(new Phrase("0",arialSmall));
			}
			/* if(mp.getActualBenefitLimit()==null){
			    	table.addCell("");
			    }else{
			    	table.addCell(new Phrase(Double.toString(mp.getActualBenefitLimit()),arial));
			    }*/
			if(claim.getClaimChargeValue()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(String.valueOf(claim.getClaimChargeValue()), arialSmall) );	
			}
			if(claim.getClaimApprovedValue()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(Double.toString(claim.getClaimApprovedValue()), arialSmall) );	
			}
			if(claim.getClaimExcessValue()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(Double.toString(claim.getClaimExcessValue()), arialSmall) );	
			}
			if(claim.getPrePaidExcess()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(Double.toString(claim.getPrePaidExcess()), arialSmall) );	
			}

			table.addCell("");
			table.addCell("");


			//		    table.addCell("2.2");
			//		    table.addCell("2.3");
			//		    

		}
		preface.add(table);

		addEmptyLine(preface, 2);
		preface.add(new Paragraph("Hormat Kami, \r\n\r\n" +
				"Surat ini diterbitkan secara otomatis, tanda tangan petugas tidak diperlukan \r\n\r\n" +
				"Keterangan : \r\n" +
				"REJECTED			: Klaim ditolak. \r\n" +
				"PENDING			: Klaim di pending. \r\n" +
				"CDV ISSUED			: Klaim siap dibayar / transfer. \r\n" +
				"PAID				: Klaim sudah dibayar / transfer.",
				arial));

		document.add(preface);

		// Start a new page
		document.newPage();
	}

	private void pendingPDF(Document document,PendingClaim data,Claim claim,BatchClaim bc)
			throws Exception {
		Paragraph preface = new Paragraph();
		Paragraph preface2 = new Paragraph();
		// We add one empty line
		//		    addEmptyLine(preface, 1);
		//		    // Lets write a big header
		//		    preface.add(new Paragraph("Rejected Claim", catFont));

		addEmptyLine(preface, 1);
		if (bc.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER){
			preface.add(new Paragraph("Kepada Yth. \r\n " +claim.getMemberId().getFirstName() ,
					arial));
		}
		if (bc.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER_GROUP){
			preface.add(new Paragraph("Kepada Yth. \r\n " +bc.getMemberGroupId().getGroupName() ,
					arial));
		}
		if (bc.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.PROVIDER){
			preface.add(new Paragraph("Kepada Yth. \r\n " +bc.getProviderId().getProviderName() ,
					arial));
		}
		addEmptyLine(preface, 1);
		preface.add(new Paragraph("No Polis		: "+claim.getMemberId().getCurrentPolicyId().getPolicyNumber()!=null?claim.getMemberId().getCurrentPolicyId().getPolicyNumber():"" +"\r\n" +
				"Departement	:  OWLEXA - FINANCE AND ADMINISTRATION",
				arial));


		PendingCategory pc;
		pc = pendingCategoryService.get(data.getPendingCategory().getPendingCategoryId());
		addEmptyLine(preface, 1);

		preface.add(new Paragraph("No Claim		: "+claim.getClaimNumber() +"\r\n" +
				"Pending Category 	: "+pc.getPendingCategoryName() +"\r\n" +

				    				"Pending Due Date	: "+data.getPendingDueDate(),
				    				arial));


		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Dengan Hormat, \r\n" +
				"Bersama surat ini kami informasikan bahwa klaim yang diajukan telah selesai diproses dengan rincian sebagai berikut  : ",
				arial));

		// add a table
		//		   createTable(subCatPart);

		addEmptyLine(preface, 2);
		PdfPTable table = new PdfPTable(12);
		table.setWidthPercentage(100);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Nama RS/ Klinik Rekanan",arialHeader));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Nomor Klaim",arialHeader));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Tanggal Berobat",arialHeader));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Deskripsi Manfaat",arialHeader));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Status",arialHeader));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Sisa Limit",arial));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Total Biaya",arialHeader));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Biaya Disetujui",arialHeader));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Biaya Tidak Disetujui",arialHeader));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Selisih Sudah Dibayar",arialHeader));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Selisih Belum Dibayar",arialHeader));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Remarks",arialHeader));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		Date tanggal= claim.getAdmissionDate();
		DateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
		String tanggalBerobat= dateFormat.format(tanggal);


		MemberProduct mp=memberProductService.getMemberActiveProduct(claim.getMemberId().getMemberId(), claim.getCaseCategoryId().getCaseCategoryId());

		//isi tabel
		if(claim.getProviderName()==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(claim.getProviderName(), arialSmall));
		}

		if (claim.getClaimNumber()==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(claim.getClaimNumber(), arialSmall));
		}
		if(tanggalBerobat==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(tanggalBerobat, arialSmall));	
		}

		table.addCell("");
		table.addCell(new Phrase("PENDING", arialSmall));
		if(mp!=null){


			if(mp.getActualBenefitLimit()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(Double.toString(mp.getActualBenefitLimit()),arialSmall));
			}
		}
		else{
			table.addCell("");
		}
		if(claim.getClaimChargeValue()==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(Double.toString(claim.getClaimChargeValue()), arialSmall) );	
		}
		if(claim.getClaimApprovedValue()==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(Double.toString(claim.getClaimApprovedValue()), arialSmall) );	
		}
		if(claim.getClaimExcessValue()==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(Double.toString(claim.getClaimExcessValue()), arialSmall) );	
		}
		if(claim.getPrePaidExcess()==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(Double.toString(claim.getPrePaidExcess()), arialSmall) );	
		}

		table.addCell("");
		table.addCell("");
		//		    
		preface.add(table);


		addEmptyLine(preface, 2);
		preface.add(new Paragraph("Hormat Kami, \r\n" +
				"Surat ini diterbitkan secara otomatis, tanda tangan petugas tidak diperlukan \r\n\r\n" +
				"Keterangan : \r\n" +
				"REJECTED			: Klaim ditolak. \r\n" +
				"PENDING			: Klaim di pending. \r\n" +
				"CDV ISSUED			: Klaim siap dibayar / transfer. \r\n" +
				"PAID				: Klaim sudah dibayar / transfer.",
				arial));

		document.add(preface);

		// Start a new page
		document.newPage();
	}


	//Riyan, Create PDF CDV ISSUED

	private void rejectPDF(Document document,RejectedClaim data,Claim claim,BatchClaim bc)
			throws Exception {
		Paragraph preface = new Paragraph();
		Paragraph preface2 = new Paragraph();
		// We add one empty line
		//		    addEmptyLine(preface, 1);
		//		    // Lets write a big header
		//		    preface.add(new Paragraph("Rejected Claim", catFont));

		addEmptyLine(preface, 1);
		if (bc.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER){
			preface.add(new Paragraph("Kepada Yth. \r\n " +data.getMemberId().getFirstName() ,
					arial));
		}
		if (bc.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER_GROUP){
			preface.add(new Paragraph("Kepada Yth. \r\n " +bc.getMemberGroupId().getGroupName() ,
					arial));
		}
		if (bc.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.PROVIDER){
			preface.add(new Paragraph("Kepada Yth. \r\n " +bc.getProviderId().getProviderName() ,
					arial));
		}


		RejectCategory rc;
		rc = rejectCategoryService.get(data.getRejectionCategory().getRejectCategoryId());
		addEmptyLine(preface, 1);

		preface.add(new Paragraph("No Claim		: "+claim.getClaimNumber() +"\r\n" +
				"Rejection Category 	: "+rc.getRejectCategoryName() +"\r\n" +
				"Description	:  "+data.getDescription(),
				arial));


		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Dengan Hormat, \r\n" +
				"Bersama surat ini kami informasikan bahwa klaim yang diajukan telah selesai diproses dengan rincian sebagai berikut  : ",
				arial));

		// add a table
		//		   createTable(subCatPart);

		addEmptyLine(preface, 2);
		PdfPTable table = new PdfPTable(12);
		table.setWidthPercentage(100);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Nama RS/ Klinik Rekanan",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Nomor Klaim",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Tanggal Berobat",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Deskripsi Manfaat",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Status",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Sisa Limit",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Total Biaya",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Biaya Disetujui",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Biaya Tidak Disetujui",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Selisih Sudah Dibayar",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Selisih Belum Dibayar",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Remarks",arialHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		Date tanggal= claim.getAdmissionDate();
		DateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
		String tanggalBerobat= dateFormat.format(tanggal);


		MemberProduct mp=memberProductService.getMemberActiveProduct(claim.getMemberId().getMemberId(), claim.getCaseCategoryId().getCaseCategoryId());

		//isi tabel
		if(claim.getProviderName()==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(claim.getProviderName(), arialSmall));
		}

		if (data.getClaimNumber()==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(data.getClaimNumber(), arialSmall));
		}
		if(tanggalBerobat==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(tanggalBerobat, arialSmall));	
		}

		table.addCell("");
		table.addCell(new Phrase("REJECT", arialSmall));

		if(mp!=null){


			if(mp.getActualBenefitLimit()==null){
				table.addCell("");
			}else{
				table.addCell(new Phrase(Double.toString(mp.getActualBenefitLimit()),arialSmall));
			}
		}
		else{
			table.addCell("");

		}
		if(claim.getClaimChargeValue()==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(Double.toString(claim.getClaimChargeValue()), arialSmall) );	
		}
		if(claim.getClaimApprovedValue()==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(Double.toString(claim.getClaimApprovedValue()), arialSmall) );	
		}
		if(claim.getClaimExcessValue()==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(Double.toString(claim.getClaimExcessValue()), arialSmall) );	
		}
		if(claim.getPrePaidExcess()==null){
			table.addCell("");
		}else{
			table.addCell(new Phrase(Double.toString(claim.getPrePaidExcess()), arialSmall) );	
		}

		table.addCell("");
		table.addCell("");


		//		    table.addCell("2.2");
		//		    table.addCell("2.3");
		//		    
		preface.add(table);


		addEmptyLine(preface, 2);
		preface.add(new Paragraph("Hormat Kami, \r\n\r\n" +
				"Surat ini diterbitkan secara otomatis, tanda tangan petugas tidak diperlukan \r\n\r\n" +
				"Keterangan : \r\n" +
				"REJECTED			: Klaim ditolak. \r\n" +
				"PENDING			: Klaim di pending. \r\n" +
				"CDV ISSUED			: Klaim siap dibayar / transfer. \r\n" +
				"PAID				: Klaim sudah dibayar / transfer.",
				arial));

		document.add(preface);

		// Start a new page
		document.newPage();
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private static void addContent(Document document) throws DocumentException {
		Anchor anchor = new Anchor("First Chapter", catFont);
		anchor.setName("First Chapter");

		// Second parameter is the number of the chapter
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("Subcategory 1", subFont);
		Section subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Hello"));

		subPara = new Paragraph("Subcategory 2", subFont);
		subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Paragraph 1"));
		subCatPart.add(new Paragraph("Paragraph 2"));
		subCatPart.add(new Paragraph("Paragraph 3"));

		// add a list
		createList(subCatPart);
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 5);
		subCatPart.add(paragraph);

		// add a table
		createTable(subCatPart);

		// now add all this to the document
		document.add(catPart);

		// Next section
		anchor = new Anchor("Second Chapter", catFont);
		anchor.setName("Second Chapter");

		// Second parameter is the number of the chapter
		catPart = new Chapter(new Paragraph(anchor), 1);

		subPara = new Paragraph("Subcategory", subFont);
		subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("This is a very important message"));

		// now add all this to the document
		document.add(catPart);

	}

	private static void createTable(Section subCatPart)
			throws BadElementException {
		PdfPTable table = new PdfPTable(3);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header 2"));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header 3"));
		//		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		table.addCell("1.0");
		table.addCell("1.1");
		table.addCell("1.2");
		table.addCell("2.1");
		table.addCell("2.2");
		table.addCell("2.3");

		subCatPart.add(table);

	}

	private static void createList(Section subCatPart) {
		com.itextpdf.text.List list = new com.itextpdf.text.List(true, false, 10);
		list.add(new ListItem("First point"));
		list.add(new ListItem("Second point"));
		list.add(new ListItem("Third point"));
		subCatPart.add(list);
	}



	// class+

	// class-
}
