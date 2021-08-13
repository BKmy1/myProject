package com.my.tool;

import java.util.Locale;

/**
 * 国际化Internationalization
 * @author admin
 *
 */
public class Internationalization {
	private static Locale loc=Locale.getDefault();
	 private static String url="content";
	 public static Locale getInternationalization() {
	  return loc;
	 }
	 public static String getUrl() {
	  return url;
	 }
}
