package com.ametis.cms.util;

public class EmailUtil {
	private static final String SENDER_EMAIL_ADDRESS = "notification@lintasarta.healthcare.co.id";
	  private static final String EMAIL = "febayvero@gmail.com";
	  private static final String EMAIL_PASSWORD = "";
	  private static final String HOST = "localhost";
	  private static final String EMAIL_SUBJECT = "Upload Process Notification";
	  private static final String EMAIL_TEXT = "Terima kasih Anda telah menggunakan fasilitas Owlexa Healthcare. " +
	  		"\n Berikut dilampirkan Enrollment yang telah Anda lakukan. \n\n Semoga Informasi ini bermanfaat bagi Anda. \n Terima Kasih. \n\n Hormat Kami, \n\n" +
	  		" Owlexa Healthcare \n";
	  private static final String EMAIL_TEXT_CLAIM = " Terima kasih Anda telah menggunakan fasilitas Owlexa Healthcare. " +
	  		"\n Berikut dilampirkan Transaksi yang telah Anda lakukan. \n\n Semoga Informasi ini bermanfaat bagi Anda. \n Terima Kasih. \n\n Hormat Kami, \n\n" +
	  		" Owlexa Healthcare \n";
	  private static final String EMAIL_SUBJECT_CLAIM = "CLAIM TRANSACTION NOTIFICATION";

	  public static String getSenderEmailAddress() {
	    return SENDER_EMAIL_ADDRESS;
	  }

	  public static String getHost() {
	    return HOST;
	  }

	  public static String getSubject() {
	    return EMAIL_SUBJECT;
	  }

	  public static String getEmail() {
		return EMAIL;
	}

	public static String getEmailPassword() {
		return EMAIL_PASSWORD;
	}

	public static String getEmailText() {
	    return EMAIL_TEXT;    
	  }

	public static String getEmailTextClaim() {
		return EMAIL_TEXT_CLAIM;
	}

	public static String getEmailSubjectClaim() {
		return EMAIL_SUBJECT_CLAIM;
	}
}
