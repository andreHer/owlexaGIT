package com.ametis.cms.util;

/**
 * User: ZaQ <zaki.rahman@gmail.com>
 */
public class LoggingUtil {
  private static final String LOG_DIRECTORY = "/var/log/healthcare/";
	/*private static final String LOG_DIRECTORY = "D:/LINTASARTA/BackUp/task/logParser/";*/
  public static String getLogDirectory() {
    return LOG_DIRECTORY;
  }
}
