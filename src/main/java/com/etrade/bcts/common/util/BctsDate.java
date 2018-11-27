package com.etrade.bcts.common.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BctsDate implements Serializable{
	static final Logger LOGGER = LoggerFactory.getLogger(BctsDate.class);
	/** 
     * 
     */ 
    private static final long serialVersionUID = 7472810438929142913L;
    
    
	public static final String XML_DATEFORMAT = "yyyyMMdd";
	public static final String XML_DATETIMEFORMAT = XML_DATEFORMAT + "HHmm";
	// public static final String XML_DATETIMEFORMAT_TN41 =
	// "yyyyMMddHHmmsszzz"; //TWX 20110923
	public static final String XML_DATETIMEFORMAT_TN41 = "yyyyMMddHHmmss"; // TWX 20111003 we // will ignore the time  zone as the underlying DB  table does  not support  yet!

    public static final String UI_DATEFORMAT = "dd/MM/yyyy";
    public static final String UI_DATETIMEFORMAT = UI_DATEFORMAT + " HH:mm";
    public static final String UI_DATETIMEFORMAT2 = UI_DATETIMEFORMAT + ":ss";
    public static final String UI_DATEFORMAT2 = "dd MMM yyyy";
    public static final String SQL_DATEFORMAT = "yyyyMMdd";
    public static final String SQL_DATETIMEFORMAT = "yyyyMMddHH24mi";
    public static final long MILLISECS_PER_MINUTE = 60 * 1000;
    public static final long MILLISECS_PER_HOUR = 60 * MILLISECS_PER_MINUTE;
    public static final long MILLISECS_PER_DAY = 24 * MILLISECS_PER_HOUR;

    private Date dateObj;
    private String dateFormat;

    /**
     * Date format is yyyyMMdd
     * 
     * @param date
     */
    public BctsDate()
    {
        dateObj = new Date();
    }

    /**
     * Date format is yyyyMMdd
     * 
     * @param date
     */
    public BctsDate(String date)
    {
        dateObj = getDateFromString(date, XML_DATEFORMAT);
    }

    /**
     * Date format is yyyyMMdd Time format is HHmm
     * 
     * @param date
     * @param time
     */
    public BctsDate(String date, String time)
    {
        dateObj = getDateFromString(date + time, XML_DATETIMEFORMAT);
    }

    /**
     * Date format is yyyyMMdd Time format is HHmm
     * 
     * @param date
     * @param time
     */
    public BctsDate(String date, String time, String format)
    {
        if (date != null && time != null)
        {
            dateObj = getDateFromString(date + time, format);
        }
        else
            if (time == null)
            {
                dateObj = getDateFromString(date, format);
            }
    }

    /**
     * Constructs a TrwDate using the specified format to parse the information
     * of the given datetime.
     *
     * @param datetime
     * @param format
     * @param b
     *            carries no influence to setting the date and time information
     *            using the specified format.
     */
    public BctsDate(String datetime, String format, boolean b)
    {
        this.dateFormat = format;

        if (datetime != null && datetime.length() > 0)
        {
            try
            {
                dateObj = new SimpleDateFormat(format).parse(datetime);
            }
            catch (NumberFormatException | ParseException x)
            {
                dateObj = null;
                LOGGER.error(x.toString() + " : ", x);
            }
        }
        else
        {
            dateObj = null;
        }
    }

    /**
     * Sets the Calendar time from the Timestamp object
     * 
     * @param timestamp
     */
    public BctsDate(Timestamp timestamp)
    {
        dateObj = new Date(timestamp.getTime());
    }

    /**
     * Constructs a TrwDate from a util date object
     * 
     * @param date
     */
    public BctsDate(Date date)
    {
        dateObj = date;
    }

    /**
     * Constructs a TrwDate from a sql date object
     * 
     * @param date
     */
    public BctsDate(java.sql.Date date)
    {
        dateObj = new Date(date.getTime());
    }

    // ////////////////////////////////////////////////////////////////////////////////////////////
    // Set methods
    // ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Sets java.util.Date object
     */
    public void setTrwDate(Date date)
    {
        this.dateObj = date;
    }

    /**
     * Sets the Date object based on timeMillis
     */
    public void setTrwDate(long timeMillis)
    {
        this.dateObj = new Date(timeMillis);
    }

    // ////////////////////////////////////////////////////////////////////////////////////////////
    // Get Date methods
    // ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Returns the java.util.Date object
     */
    public Date getTrwDate()
    {
        return this.dateObj;
    }

    /**
     * Returns the java.sql.Date object
     */
    public java.sql.Date getTrwDateSQL()
    {
        java.sql.Date sqlDate = new java.sql.Date(dateObj.getTime());

        return sqlDate;
    }

    public String getDateFormat()
    {
        return dateFormat;
    }

    /**
     * Returns the java.sql.Date object
     */
    public java.sql.Timestamp getTimestampSQL()
    {
        java.sql.Timestamp timestamp = new java.sql.Timestamp(dateObj.getTime());

        return timestamp;
    }

    /**
     * Returns the java.sql.Date object
     */
    public String getTrwDateString(String format)
    {
        return getDateString(dateObj, format);
    }

    /**
     * Returns the java.sql.Date object
     */
    public String getUIDateString()
    {
        return getDateString(dateObj, UI_DATEFORMAT);
    }

    /**
     * Returns the java.sql.Date object
     */
    public String getUIDateTimeString()
    {
        return getDateString(dateObj, UI_DATETIMEFORMAT);
    }

    /**
     * Returns the java.sql.Date object
     */
    public String getXMLDateString()
    {
        return getDateString(dateObj, XML_DATEFORMAT);
    }

    /**
     * Returns the java.sql.Date object
     */
    public String getXMLDateTimeString()
    {
        return getDateString(dateObj, XML_DATETIMEFORMAT);
    }

    // //////////////////////////////////////////////////////////////////////////
    // Helper methods
    // //////////////////////////////////////////////////////////////////////////

    /**
     * This method returns the Date String according to the specified format
     */
    public static String getDateString(Date date, String format)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String getDateString(Timestamp date, String format)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * This method returns the Date according to the specified format
     */
    public static Date getDateFromString(String dateStr, String format)
    {
        Date result = null;

        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            result = dateFormat.parse(dateStr);
        }
        catch (NumberFormatException | ParseException e)		//modified
        {
            LOGGER.error(e.toString() + " : ", e);
        }

        return result;
    }

    /**
     * This method returns the Timestamp according to the specified format
     */
    public static java.sql.Timestamp getTimestampFromString(String dateStr, String format)
    {
        Date date = null;
        java.sql.Timestamp result = null;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);

            result = new java.sql.Timestamp(date.getTime());
        }
        catch (NumberFormatException | ParseException e)		//modified
        {
            LOGGER.error(e.toString() + " : ", e);
        }
        return result;
    }

    /**
     * Convert from 1 pattern to another pattern
     */
    public static String convertDateString(String dateStr, String srcPattern, String dstPattern)
    {
        String result = null;
        Date srcDate = null;

        if (dateStr != null)
        {
            srcDate = getDateFromString(dateStr, srcPattern);
            result = getDateString(srcDate, dstPattern);
        }
        return result;
    }

    public static long diffDayPeriods(Date startDate, Date endDate)
    {
        Calendar start = new GregorianCalendar();
        start.setTime(startDate);
        Calendar end = new GregorianCalendar();
        end.setTime(endDate);
        long endL = end.getTimeInMillis() + end.getTimeZone().getOffset(end.getTimeInMillis());
        long startL = start.getTimeInMillis() + start.getTimeZone().getOffset(start.getTimeInMillis());
        return (endL - startL) / MILLISECS_PER_DAY;
    }

    public static Date addDay(Date srcDate, int dayAmount)
    {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(srcDate);
        cal.add(Calendar.DAY_OF_MONTH, dayAmount);
        return cal.getTime();
    }

    /**
     * Convert date string to Date. Accepted date formats are:
     * <ul>
     * <li>UK: dd/MM/yyyy</li>
     * <li>ISO 8601: yyyy-MM-dd</li>
     * </ul>
     * 
     * @param dateStr
     *            date string
     * @return date
     * @throws ParseException
     */
    public static Date parse(String dateStr) throws ParseException
    {
        try
        {
            SimpleDateFormat ukDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return ukDateFormat.parse(dateStr);
        }
        catch (ParseException e)
        {
            LOGGER.warn("Date is not in UK format: dd/MM/yyyy", e);
            try
            {
                SimpleDateFormat iso8601DateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return iso8601DateFormat.parse(dateStr);
            }
            catch (ParseException e1)
            {
                LOGGER.error("Unknown date string format: [" + dateStr + "].", e1);
                throw new ParseException("Unknown date string format: [" + dateStr + "]. It must be in ISO-8601 format (yyyy-MM-dd) or UK format (dd/MM/yyyy)", e1.getErrorOffset());
            }
        }
    }

    public static Date getLastDateOfMonth()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }
    
    
    /**
     * To get the firstDate Of the Month
     **/
    public static Date getFirstDateOfMonth()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        cal.set(Calendar.DAY_OF_MONTH, 1);
        
        return cal.getTime();
    }
    
    /**
     * To get the firstDate Of the Week
     **/
    public static Date getFirstDateOfWeek()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        
        return cal.getTime();
    }
}
