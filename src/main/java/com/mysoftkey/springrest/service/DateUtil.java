/**
 * 
 */
package com.mysoftkey.springrest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

/**
 * This utility class is used to manipulation for Date stuff in java.
 * 
 * @author ranjeet jha
 *
 */
public class DateUtil {

 private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

 // format of date used in this project.
 public static final String DATE_FORMAT_DD_MMM_YY_DEFAULT = "dd-MMM-yy";
 public static final String DATE_FORMAT_DD_MMM_YY_HH_MM_SS = "dd-MMM-yy hh.mm.ss";
 public static final String DATE_FORMAT_DD_MMM_YY_HH_MM_SS_ = "dd-MMM-yy HH:mm:ss";
 public static final String DATE_FORMAT_DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy hh:mm:ss";
 public static final String DATE_FORMAT_DD_MMMM_YYYY = "dd MMM yyy";

 public static final String DATE_FORMAT_DD_MM_YYYY = "dd-MM-yyyy";

 public static final String DATE_FORMAT_MM_DD_YYYY_FOR_SQL = "MM-dd-yyyy";
 public static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";

 public static final String DATE_FORMAT_DD_MM_YYYY_SLASH = "dd/MM/yyyy";

 public static final String DATE_FORMAT_DD_MM_YY_SLASH = "dd/MM/yy";

 public static final String DATE_FORMAT_EE_MMM_dd_hh_mm_ss_z_yyyy = "EEE MMM dd hh:mm:ss z yyyy";

 public static final String DATE_FORMAT_yyyy_MM_dd_T_HH_mm_ss_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'";

 // public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS =
 // "yyyy-MM-dd hh.mm.ss";
 public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

 public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

 public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

 public static final String DATE_FORMAT_YYYY_MM_DD_hh_MM_SS_MILLI = "yyyy-MM-dd HH:mm:ss.S"; // 2014-11-28
 // 15:52:51.680

 public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_MILLI = "yyyy-MM-dd HH:mm:ss.sss";

 public static final String DATE_FORMAT_DD_MMM_YYYY_HH_MM_SS = "dd MMM yyyy HH:mm:ss";

 public static final String DATE_FORMAT_YYYYMMDD_HHMMSS = "yyyyMMdd_hhmmss";

 public static final String DATE_FORMAT_DDMMYYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";

 public static final String DATE_FORMAT_MMDDYYYY_HHMMSS_AMPM = "MM/dd/yyyy hh:mm:ss a";

 /**
  * This method is used get the date by adding year in
  * 
  * @param date
  * @param year
  * @return
  */
 public static Date getDateByAddingYear(Date date, int year) {
  Date uDate = null;
  try {
   Calendar cal = Calendar.getInstance();
   cal.add(Calendar.YEAR, year);
   uDate = cal.getTime();
  } catch (Exception e) {

  }
  return uDate;
 }

 /**
  * This method is used get the date by adding year in
  * 
  * @param date
  * @param year
  * @return
  */
 public static Date getDateByAddingMonths(Date date, int months) {
  Date uDate = null;
  try {
   Calendar cal = Calendar.getInstance();
   cal.setTime(date);
   cal.add(Calendar.MONTH, months);
   uDate = cal.getTime();
  } catch (Exception e) {
   logger.warn(e.getMessage());
  }
  return uDate;
 }

 /**
  * This method is used get the date by adding year in
  * 
  * @param date
  * @param year
  * @return
  */
 public static Date getDateByAddingDay(Date date, int days) {
  Date uDate = null;
  try {
   Calendar cal = Calendar.getInstance();
   cal.setTime(date);
   cal.add(Calendar.DATE, days);
   uDate = cal.getTime();
  } catch (Exception e) {
   logger.warn(e.getMessage());
  }
  return uDate;
 }

 /**
  * This method is used get formated string date.
  * 
  * @param date
  * @param format
  * @return
  */
 public static String parseDate(Date date, String format) {
  SimpleDateFormat dateFormat = null;
  String sDate = null;
  try {
   if (format != null) {
    dateFormat = new SimpleDateFormat(format);
   } else {
    dateFormat = new SimpleDateFormat(DATE_FORMAT_DD_MMM_YY_DEFAULT);
   }
   sDate = dateFormat.format(date);
  } catch (Exception e) {
   logger.warn(e.getMessage());
  }
  return sDate;
 }

 /**
  * This method is used to parse string date to {@link Date}.
  * 
  * @param dateStr
  *         e.g. "Tue Jan 01 05:30:00 IST 1985";
  * @param format
  *         e.g. "EEE MMM dd hh:mm:ss z yyyy";
  * @return
  */
 public static Date getStringToDate(String dateStr, String format) {
  Date d = null;
  SimpleDateFormat dateFormat = null;
  try {
   if (dateStr != null && format != null) {
    // String format = "EEE MMM dd hh:mm:ss z yyyy"; // value e.g.
    // "Tue Jan 01 05:30:00 IST 1985";
    dateFormat = new SimpleDateFormat(format);
    d = dateFormat.parse(dateStr);
   }

  } catch (Exception e) {
   logger.info("error in parsing dateStr [" + dateStr + "] , format [ " + format + " ] , msg: " + e.getMessage());
  } finally {
   dateFormat = null;
  }
  return d;
 }

 /**
  * This method is used to return a .net service compatible date string like
  * format "/Date(1420187098000)/"
  * 
  * @param date
  * @return
  */
 public static String getNetServiceCompatibleDate(Date date) {
  String formatedDate = null;
  try {
   formatedDate = "/Date(" + date.getTime() + ")/"; // "\/Date(1420187098000)\/"
  } catch (Exception e) {
   logger.error("error in parsing the date time msg:" + e.getMessage());
  }

  return formatedDate;
 }

 public static long getTimeTakenInMin(long startTime, long endTime) {
  long timeTaken = 0;
  try {
   timeTaken = (endTime - startTime) / 1000;
   // timeTaken = timeTaken / 60;
  } catch (Exception e) {

  }
  return timeTaken;
 }

 public static long getTimeTakenInSec(long startTime, long endTime) {
  long timeTaken = 0;
  try {
   timeTaken = (endTime - startTime) / 1000;
  } catch (Exception e) {

  }
  return timeTaken;
 }

 public static String getFormatedDate(SimpleDateFormat sdf, Date d) {
  try {
   return sdf.format(d);
  } catch (Exception e) {
   logger.error("exception occured while formating date, msg : " + e.getMessage());
  }
  return null;
 }

 public static String addDays(Object dateObject, int daysToAdd) {
  return addDays(dateObject, daysToAdd, DateUtil.DATE_FORMAT_YYYY_MM_DD);
 }

 public static String addDays(Object dateObject, int daysToAdd, String dateFormat) {
  String newDateString = "";
  if (dateObject != null) {
   Calendar cal = Calendar.getInstance();
   Date inputdate = DateUtil.getStringToDate(String.valueOf(dateObject), dateFormat);
   cal.setTime(inputdate);
   cal.add(Calendar.DATE, daysToAdd);
   inputdate = cal.getTime();
   newDateString = DateUtil.parseDate(inputdate, DateUtil.DATE_FORMAT_YYYY_MM_DD);
  }
  return newDateString;
 }

 public static Date addDays(Date date, int daysToAdd) {
  Date newDate = null;
  if (date != null) {
   Calendar cal = Calendar.getInstance();
   cal.setTime(date);
   cal.add(Calendar.DATE, daysToAdd);
   newDate = cal.getTime();
  }
  return newDate;
 }

 public static Date addDaysInCurrentDate(int daysToAdd) {
  Calendar cal = Calendar.getInstance();
  cal.add(Calendar.DATE, daysToAdd);
  return cal.getTime();
 }

 public static Date addMinute(Date date, int min) {
  Date newDate = null;
  if (date != null) {
   Calendar cal = Calendar.getInstance();
   cal.setTime(date);
   cal.add(Calendar.MINUTE, min);
   newDate = cal.getTime();
  }
  return newDate;
 }

 public static boolean isThisDateValid(String dateToValidate, String dateFromat) {

  if (dateToValidate == null) {
   return false;
  }

  SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
  sdf.setLenient(false);

  try {

   // if not valid, it will throw ParseException
   Date date = sdf.parse(dateToValidate);

  } catch (ParseException e) {
   return false;
  }

  return true;
 }

 public static int getCurrentYear() {

  int year = Calendar.getInstance().get(Calendar.YEAR);
  return year;
 }

 public static int getCurrentMonth() {

  int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
  return month;
 }

 public static int getDifferenceDate(Date d2, Date d1) {
  int day = 0;
  if (null != d2 && null != d1) {
   day = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
  }
  return day;
 }

}
