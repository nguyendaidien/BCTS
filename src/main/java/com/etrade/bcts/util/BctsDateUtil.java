/**
 *
 */
package com.etrade.bcts.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This util class is handling the date related operation.
 *
 * @author weixiang
 * @since 5.0.0
 */
public class BctsDateUtil 
{ 
	static final Logger LOG = LoggerFactory.getLogger(BctsDateUtil.class);

    public static final String UI_DATEFORMAT = "dd/MM/yyyy";

    public static final String TRW_DATETIME_NO_TIMEZONE = "yyyyMMddHHmmss";

    /**
     * Truncate the unnecessary date element from the dateTime based on the given format. EG. If the given format is 'yyyyMMddHHmmss' and the given dateTime is '20110930163059SST', the returned result
     * will be '20110930163059'
     *
     * @param format
     * @param dateTime
     * @return
     */
    public static String truncateDate(String format, String dateTime)
    {
        if (dateTime == null)
        {
            throw new IllegalArgumentException("The given date time can not be null!");
        }

        /*if (format == null)
        {
            throw new IllegalArgumentException("The given format can not be null!");
        }*/

        if (dateTime.length() < format.length())
        {
            throw new IllegalArgumentException("The given dateTime=" + dateTime + " must be at least same length as the given format=" + format);
        }

        if (dateTime.length() > format.length())
        {
            // remove the time zone (zzz) from the date
            dateTime = dateTime.substring(0, format.length());
        }

        return dateTime;
    }

    public static String getDateTime(String format)
    {
        return getDateTime(Calendar.getInstance(), format);
    }

    public static String getDateTime(Calendar date, String format)
    {
        String formattedDate = null;

        long time = date.getTimeInMillis();

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        try
        {
            formattedDate = dateFormat.format(Long.valueOf(time));
        }
        catch (IllegalArgumentException e)
        {
            LOG.error("getDateTime: ", e);
        }
        return formattedDate;
    }

    /**
     * Get Date from String based on the provided format
     * @param dateStr
     * @param format
     * @return
     */
    public static Date getDate(String dateStr, String format)
    {
        final String methodName = "getDate: ";
        Date result = null;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            result = dateFormat.parse(dateStr);
        }
        catch (ParseException e)
        {
            LOG.error(methodName + "{}", dateStr, e);
        }
        return result;
    }

    public static void main(String[] args) {
		System.out.println(getDate("20111006142534","dd/MM/yyyy hh24:mm"));
	}
    
    /**
     * Get SQL Date from String based on the provided format
     * @param dateStr
     * @param format
     * @return
     */
    public static java.sql.Date getSQLDate(String dateStr, String format)
    {
        Date date = getDate(dateStr, format);

        java.sql.Date result = new java.sql.Date(date.getTime());

        return result;
    }

    /**
     * Get Start of Date up to Milliseconds
     * @param dateStr
     * @param dateFormat
     * @return
     */
    public static Date getStartOfDay(String dateStr, String dateFormat)
    {
        Date date = getDate(dateStr, dateFormat);

        date = getStartOfDay(date);

        return date;
    }

    /**
     * Get End of Date up to Milliseconds
     * @param dateStr
     * @param dateFormat
     * @return
     */
    public static Date getEndOfDay(String dateStr, String dateFormat)
    {
        Date date = getDate(dateStr, dateFormat);

        date = getEndOfDay(date);

        return date;
    }

    /**
     * Get Start of Date up to Milliseconds
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * Get End of Date up to Milliseconds
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }

    /**
     * This method returns the Date String according to the specified format
     */
    public static String getDateString(Date date, String format)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * Returns the java.sql.Date object
     */
    public static java.sql.Timestamp getTimestampSQL(Date date)
    {
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

        return timestamp;
    }

    /**
     * Simple method to check if time limit in seconds reached
     * @param startTimeMillis
     * @param durationSeconds
     * @return
     */
    public static final boolean exceedTimeSeconds(long startTimeMillis, long durationSeconds)
    {
        boolean exceededTime = false;

        long elapsedTimeSeconds = TimeUnit.SECONDS.convert(System.currentTimeMillis() - startTimeMillis, TimeUnit.MILLISECONDS);

        if (elapsedTimeSeconds > durationSeconds)
        {
            exceededTime = true;
        }
        return exceededTime;
    }

    /**
     * Simple method to check if time limit in minutes reached
     * @param startTimeMillis
     * @param durationSeconds
     * @return
     */
    public static final boolean exceedTimeMinutes(long startTimeMillis, long durationMinutes)
    {
        boolean exceededTime = false;

        long elapsedTimeMinutes = TimeUnit.MINUTES.convert(System.currentTimeMillis() - startTimeMillis, TimeUnit.MILLISECONDS);

        if (elapsedTimeMinutes > durationMinutes)
        {
            exceededTime = true;
        }
        return exceededTime;
    }

    /**
     * Get days difference between current time and previous time
     * @param previous
     * @return
     */
    public static long getDaysDifference(long previous)
    {
        long difference = TimeUnit.DAYS.convert(System.currentTimeMillis() - previous, TimeUnit.MILLISECONDS);

        return difference;
    }

    /**
     * Check if its last day of month
     * @return
     */
    public static boolean isLastDayOfMonth(Calendar instance)
    {
        boolean isLastDayOfMonth = false;

        isLastDayOfMonth = instance.get(Calendar.DATE) == instance.getActualMaximum(Calendar.DATE);

        return isLastDayOfMonth;
    }

    /**
     * Check if the instance is on the current month
     * @return
     */
    public static boolean isCurrentMonth(Calendar instance)
    {
        boolean isCurrentMonth = false;

        Calendar today = Calendar.getInstance();

        if (instance.get(Calendar.MONTH) == today.get(Calendar.MONTH))
        {
            isCurrentMonth = true;
        }
        return isCurrentMonth;
    }

    /**
     * Check if the instance is on the current month
     * @return
     */
    public static boolean isCurrentMonth(Date date)
    {
        boolean isCurrentMonth = false;

        Calendar instance = Calendar.getInstance();

        instance.setTime(date);

        isCurrentMonth = isCurrentMonth(instance);

        return isCurrentMonth;
    }

    /**
     * Checks if today is after TargetDate
     * e.g.
     * Target Date: 01/07/2017
     *
     * If today is 30/06/2017 --> Today is before Target date
     * If today is 15/07/2017 --> Today is after Target date
     *
     * @param targetDate
     * @return
     */
    public static boolean isAfterTargetDate(Date targetDate)
    {
        boolean isAfterTargetDate = false;

        Calendar now = Calendar.getInstance();

        Calendar targetDateCal = Calendar.getInstance();

        targetDateCal.setTime(targetDate); //set as targetDate

        if (targetDateCal.compareTo(now) <= 0) //means the target date has passed
        {
            isAfterTargetDate = true;
        }
        return isAfterTargetDate;
    }

    /* public static void main(String args[]){ LOGGER.info(TrwDateUtil.getDateTime("yyyyMMddssss")); } */
}
