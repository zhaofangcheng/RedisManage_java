package com.smart.handler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 时间工具类，保存各种时间工具方法
 *
 */
public class DateUtils {

	public static final String DATE_FORMAT = "MM/dd/yyyy";

	public static final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm";

	public static final String ORA_DATE_FORMAT = "yyyyMMdd";

	public static final String ORA_DATE_TIME_FORMAT = "yyyyMMddHHmm";

	public static final String ORA_DATE_TIMES_FORMAT = "yyyy-MM-dd HH:mm:ss,SSS";

	public static final String ORA_DATES_FORMAT = "yyyy-MM-dd";
	public static final String ORA_DATE_TIMES2_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String ORA_DATE_TIMES3_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String ORA_TIME2_FORMAT = "HH:mm:ss";

	private static final int dayArray[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30,
			31, 30, 31 };

	/**
	 * 类型转换日期
	 *
	 * @param dateString
	 *            日期格式
	 * @param partten
	 *            格式模板(如：yyyy-MM-dd)
	 * @return StringToDate 类型转换后的日期
	 */
	public synchronized static Date StringToDate(String dateString,
			String partten) {
		SimpleDateFormat df = new SimpleDateFormat(partten);
		Date date = null;
		try {
			date = df.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 日期类型转换成字符型
	 *
	 * @param date
	 *            Date类型的给定日期
	 * @param partten
	 *            格式模板(如：yyyy-MM-dd)
	 * @return 类型转换后的字符型日期
	 */
	public synchronized static String toString(Date date, String partten) {
		if (date == null) {
			return "";
		}else {
			SimpleDateFormat df = new SimpleDateFormat(partten);
			return df.format(date);
		}
	}

	/**
	 * 给定字符串日期按模板转换成Calendar类型
	 *
	 * @param dateString
	 *            字符串形式的给定日期
	 * @param partten
	 *            格式模板(如：yyyy-MM-dd)
	 * @return 类型转换后的Calendar对象
	 */
	public synchronized static Calendar parseCalendarFormat(String dateString,
			String pattern) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(pattern);
		Calendar cal = null;
		simpledateformat.applyPattern(pattern);
		try {
			simpledateformat.parse(dateString);
			cal = simpledateformat.getCalendar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cal;
	}

	/**
	 * Calendar类型转换成字符型
	 *
	 * @param cal
	 *            Calendar日期类型
	 * @param partten
	 *            格式模板(如：yyyy-MM-dd)
	 * @return 类型转换后的字符型日期
	 */
	public synchronized static String getDateMilliFormat(Calendar cal,
			String pattern) {
		return toString(cal.getTime(), pattern);
	}

	/**
	 * 取得Calendar实例
	 *
	 * @return Calendar类型实例
	 */
	public synchronized static Calendar getCalendar() {
		return GregorianCalendar.getInstance();
	}

	/**
	 * 取得当前年的某一个月的最后一天
	 *
	 * @param month
	 *            数字类型的月份值
	 * @return 参数月份的最后一天
	 */
	public synchronized static int getLastDayOfMonth(int month) {
		if (month < 1 || month > 12) {
			return -1;
		}

		int retn = 0;

		if (month == 2) {
			if (isLeapYear()) {
				retn = 29;
			} else {
				retn = dayArray[month - 1];
			}
		} else {
			retn = dayArray[month - 1];
		}
		return retn;
	}

	/**
	 * 取得当某年的某一个月的最后一天
	 *
	 * @param year
	 *            数字类型的年份值
	 * @param month
	 *            数字类型的月份值
	 * @return 参数月份的最后一天
	 */
	public synchronized static int getLastDayOfMonth(int year, int month) {
		if (month < 1 || month > 12) {
			return -1;
		}

		int retn = 0;

		if (month == 2) {
			if (isLeapYear(year)) {
				retn = 29;
			} else {
				retn = dayArray[month - 1];
			}
		} else {
			retn = dayArray[month - 1];
		}
		return retn;
	}

	/**
	 * 取得某日期所在月的最后一天
	 *
	 * @param date
	 *            Date类型的某日期
	 * @return 最后一天的日期型结果
	 */
	public synchronized static Date getLastDayOfMonth(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(2)) {
		case 0: // '\0'
			gc.set(5, 31);
			break;

		case 1: // '\001'
			gc.set(5, 28);
			break;

		case 2: // '\002'
			gc.set(5, 31);
			break;

		case 3: // '\003'
			gc.set(5, 30);
			break;

		case 4: // '\004'
			gc.set(5, 31);
			break;

		case 5: // '\005'
			gc.set(5, 30);
			break;

		case 6: // '\006'
			gc.set(5, 31);
			break;

		case 7: // '\007'
			gc.set(5, 31);
			break;

		case 8: // '\b'
			gc.set(5, 30);
			break;

		case 9: // '\t'
			gc.set(5, 31);
			break;

		case 10: // '\n'
			gc.set(5, 30);
			break;

		case 11: // '\013'
			gc.set(5, 31);
			break;
		}

		if (gc.get(2) == 1 && isLeapYear(gc.get(1))) {
			gc.set(5, 29);
		}
		return gc.getTime();
	}

	/**
	 * 取得某日期所在月的最后一天
	 *
	 * @param date
	 *            Calendar类型的某日期
	 * @return 最后一天的日期型结果
	 */
	public synchronized static Calendar getLastDayOfMonth(Calendar gc) {
		switch (gc.get(2)) {
		case 0: // '\0'
			gc.set(5, 31);
			break;

		case 1: // '\001'
			gc.set(5, 28);
			break;

		case 2: // '\002'
			gc.set(5, 31);
			break;

		case 3: // '\003'
			gc.set(5, 30);
			break;

		case 4: // '\004'
			gc.set(5, 31);
			break;

		case 5: // '\005'
			gc.set(5, 30);
			break;

		case 6: // '\006'
			gc.set(5, 31);
			break;

		case 7: // '\007'
			gc.set(5, 31);
			break;

		case 8: // '\b'
			gc.set(5, 30);
			break;

		case 9: // '\t'
			gc.set(5, 31);
			break;

		case 10: // '\n'
			gc.set(5, 30);
			break;

		case 11: // '\013'
			gc.set(5, 31);
			break;
		}
		if (gc.get(2) == 1 && isLeapYear(gc.get(1))) {
			gc.set(5, 29);
		}
		return gc;
	}

	/**
	 * 取得某日期所在周的最后一天
	 *
	 * @param date
	 *            Date类型日期参数
	 * @return 所在周的最后一天日期
	 */
	public synchronized static Date getLastDayOfWeek(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(7)) {
		case 1: // '\001'
			gc.add(5, 6);
			break;

		case 2: // '\002'
			gc.add(5, 5);
			break;

		case 3: // '\003'
			gc.add(5, 4);
			break;

		case 4: // '\004'
			gc.add(5, 3);
			break;

		case 5: // '\005'
			gc.add(5, 2);
			break;

		case 6: // '\006'
			gc.add(5, 1);
			break;

		case 7: // '\007'
			gc.add(5, 0);
			break;
		}
		return gc.getTime();
	}

	/**
	 * 取得某日期所在周的第一天
	 *
	 * @param date
	 *            Date类型日期参数
	 * @return 当周的第一天日期
	 */
	public synchronized static Date getFirstDayOfWeek(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(7)) {
		case 1: // '\001'
			gc.add(5, 0);
			break;

		case 2: // '\002'
			gc.add(5, -1);
			break;

		case 3: // '\003'
			gc.add(5, -2);
			break;

		case 4: // '\004'
			gc.add(5, -3);
			break;

		case 5: // '\005'
			gc.add(5, -4);
			break;

		case 6: // '\006'
			gc.add(5, -5);
			break;

		case 7: // '\007'
			gc.add(5, -6);
			break;
		}
		return gc.getTime();
	}

	/**
	 * 当前年是否闰年
	 *
	 * @return 返回true为润年，否则不是
	 */
	public synchronized static boolean isLeapYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(1);
		return isLeapYear(year);
	}

	/**
	 * 给定年是否闰年
	 *
	 * @param 整数类型的年份
	 * @return 返回true为润年，否则不是
	 */
	public synchronized static boolean isLeapYear(int year) {
		if (year % 400 == 0) {
			return true;
		}
		if (year % 4 == 0) {
			return year % 100 != 0;
		} else {
			return false;
		}
	}

	/**
	 * 给定日期所在年是否闰年
	 *
	 * @param Date
	 *            类型的日期
	 * @return 返回true为润年，否则不是
	 */
	public synchronized static boolean isLeapYear(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		int year = gc.get(1);
		return isLeapYear(year);
	}

	/**
	 * 给定日期所在年是否闰年
	 *
	 * @param Calendar
	 *            类型的日期
	 * @return 返回true为润年，否则不是
	 */
	public synchronized static boolean isLeapYear(Calendar gc) {
		int year = gc.get(1);
		return isLeapYear(year);
	}

	/**
	 * 取得当前日期简体汉字的星期
	 *
	 * @return 取得中文版本的星期,例如:星期三
	 */
	public synchronized static String getWeek() {
		SimpleDateFormat format = new SimpleDateFormat("E");
		Date date = new Date();
		String time = format.format(date);
		return time;
	}

	/**
	 * 取得给定日期简体汉字的星期
	 *
	 * @param date
	 *            Date 类型的日期
	 * @return 取得中文版本的星期,例如:星期三
	 */
	public synchronized static String getWeek(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("E");
		String time = format.format(date);
		return time;
	}

	/**
	 * 取得给定日期年份
	 *
	 * @param date
	 *            Date 类型的日期
	 * @return 整数类型的年(如:2008)
	 */
	public synchronized static int getYear(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(1);
	}

	/**
	 * 取得给定日期月份
	 *
	 * @param date
	 *            Date 类型的日期
	 * @return 整数类型的月
	 */
	public synchronized static int getMonth(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(2) + 1;
	}

	/**
	 * 取得给定日期是日
	 *
	 * @param date
	 *            Date 类型的日期
	 * @return 整数类型的日
	 */
	public synchronized static int getDay(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(5);
	}

	/**
	 * 取得给定日期是日
	 *
	 * @param date
	 *            Date 类型的日期
	 * @return 整数类型的日
	 */
	public synchronized static int getHour(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(11);
	}

	/**
	 * 取得给定日期的分钟
	 *
	 * @param date
	 *            Date 类型的日期
	 * @return 整数类型的分钟
	 */
	public synchronized static int getMinute(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(12);
	}

	/**
	 * 取得给定日期的秒
	 *
	 * @param date
	 *            Date 类型的日期
	 * @return 整数类型的秒
	 */
	public synchronized static int getSecond(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(13);
	}

	/**
	 * 取得给定日期的下一天日期
	 *
	 * @param date
	 *            Date 类型的日期
	 * @return 下一天的日期
	 */
	public synchronized static Date getNextDay(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(5, 1);
		return gc.getTime();
	}

	/**
	 * 取得给定日期的一个月后的日期
	 *
	 * @param date
	 *            Date 类型的日期
	 * @return 给定日期的一个月后的日期
	 */
	public synchronized static Date getNextMonth(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(2, 1);
		return gc.getTime();
	}

	/**
	 * 取得给定日期的一个星期后的日期
	 *
	 * @param date
	 *            Date 类型的日期
	 * @return 给定日期的一个星期后的日期
	 */
	public synchronized static Date getNextWeek(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(5, 7);
		return gc.getTime();
	}

	/**
	 * 取得给定日期的所在月的第一天
	 *
	 * @param date
	 *            Date 类型的日期
	 * @return 给定日期的所在月的第一天
	 */
	public synchronized static Date getFirstDayOfMonth(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.set(5, 1);
		return gc.getTime();
	}

	/**
	 * 取得两个给定日期之差的天数
	 *
	 * @param lowerLimitDate
	 *            Date 类型的前日期
	 * @param upperLimitDate
	 *            Date 类型的后日期
	 * @return 整数类型的两个日期之差的天数
	 * @exception java.lang.IllegalArgumentException
	 *                如果前日期大于后日期，抛出java.lang.IllegalArgumentException异常
	 */
	public synchronized static int getDayInRange(Date lowerLimitDate,
			Date upperLimitDate) {
		long upperTime, lowerTime;
		upperTime = upperLimitDate.getTime();
		lowerTime = lowerLimitDate.getTime();

		if (upperTime < lowerTime) {
			new java.lang.IllegalArgumentException();
		}

		Long result = new Long((upperTime - lowerTime) / (1000 * 60 * 60 * 24));
		return result.intValue();
	}

	/**
	 * 比较两个日期
	 *
	 * @param lowerLimitDate
	 *            给定比较日期1
	 * @param upperLimitDate
	 *            给定比较日期2
	 * @return 如果 给定比较日期1大于给定比较日期2返回true，否则返回false
	 * @exception java.lang.IllegalArgumentException
	 *                如果给定参数为空，抛出java.lang.IllegalArgumentException异常
	 */
	public synchronized static boolean checkOverLimited(Date beginLimitDate,
			Date endLimitDate) {
		if (beginLimitDate == null || endLimitDate == null)
			new java.lang.IllegalArgumentException();

		if (((Date)beginLimitDate).compareTo((Date)endLimitDate) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
     * 将标准日期字符串转换成日期时间对象
     */
    public static Date stringToDateTime(String dateString){   		
    	
    	if(dateString.trim().length() <=10 ){
    		
    		//构建带当前时间的日期字符串
    		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    		String time = df.format(new Date());
    		dateString = dateString.trim() + " " + time;    		 		
    	}   	
    	    	  	
    	return (Date)java.sql.Timestamp.valueOf(dateString.trim()); 
    }
    
    public static String formatfulld(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date != null) {
            return df.format(date);
        } else {
            return "";
        }
    }
	/**
	 * 计算两个 calendar days 之间的天数
	 *
	 * @param begin
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @return 使用SimpleDateFormat，规定好格式，parser出错即为非法
	 *         使用DateToStr或者StrToDate格式化，返回的永远是大于零的数字
	 */
	public synchronized static int getDaysBetween(Calendar begin, Calendar end) {
		if (begin.after(end)) {
			Calendar swap = begin;
			begin = end;
			end = swap;
		}

		int days = end.get(Calendar.DAY_OF_YEAR)
				- begin.get(Calendar.DAY_OF_YEAR);
		int y2 = end.get(Calendar.YEAR);

		if (begin.get(Calendar.YEAR) != y2) {
			begin = (Calendar) begin.clone();
			do {
				days += begin.getActualMaximum(Calendar.DAY_OF_YEAR);
				begin.add(Calendar.YEAR, 1);
			} while (begin.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 计算给定日期与给定天数的计算结果（N天后，或N天前）
	 *
	 * @param date
	 *            给定的日期类型
	 * @param count
	 *            整数天，如果为负数；代表day天前。如果为正数：代表day天后
	 * @return Date型计算后的日期对象
	 */
	public synchronized static Date modDay(Date date, int day) {
		try {
			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			cd.add(Calendar.DATE, day);
			return cd.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 计算给定日期与给定小时数的计算结果（N小时后，或N小时前）
	 *
	 * @param date
	 *            给定的日期类型
	 * @param count
	 *            整数小时，如果为负数；代表time小时前。如果为正数：代表time小时后
	 * @return Date型计算后的日期对象
	 */
	public synchronized static Date modHour(Date date, int time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, time);
		return cal.getTime();
	}
	/**
	 *
	* <p>方法名称: formatDate|描述: </p>
	* @param formatDate
	* @param format_str
	* @return
	 */
	public static Date formatDate(Date formatDate,String format_str){
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(format_str);
		try{
			date = format.parse(format.format(formatDate));
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	/**
	 *
	* <p>方法名称: 返回xxx年xx月xx日|描述: </p>
	* @param formatDate
	* @param format_str
	* modified by zhaofangcheng
	* @return
	 */
	public static String formatChineseDate(Date formatDate){
		String str="";
		SimpleDateFormat formatYear = new SimpleDateFormat("yyyy年MM月dd日");
		try{
			str =formatYear.format(formatDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * <p>方法名称: dtuGetCurDatTimStr|描述:获得当前日期字符串格式</p>
	 * @param
	 * @return String
	 */
	public static String dtuGetCurDatTimStr()throws Exception{
		String str="";
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
			Calendar cd=Calendar.getInstance();
			str=sdf.format(cd.getTime());
		}catch(Exception e){
			throw new Exception("【错误：获得当前日期字符串格式服用异常！】");
		}
		return str;
	}

	/**
	 * <p>方法名称: dtuGetDatTimFmt|描述:格式化提供的日期</p>
	 * @param
	 * @return String
	 */
	public static String dtuGetDatTimFmt(Date date){
		String dt="";
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			dt=sdf.format(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return dt;
	}

	/**
	 * <p>方法名称: dtuGetDatTimFmt|描述:将字符串转换成日期</p>
	 */
	public static Date parseDatStr2Date(String datStr,String pattern)throws java.text.ParseException{
		if(datStr==null){
			return null;
		}
		pattern=pattern==null||pattern.equals("")?"yyyy-MM-dd":pattern;
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		return sdf.parse(datStr);
	}

	/**
	 * <p>方法名称: getCurrentDayEndDate|描述:获得本日的结束日期(yyyy-MM-dd 23:59:59) </p>
	 * @param endDate
	 * @return
	 */
	public static Date getCurrentDayEndDate(Date endDate){
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try{
			endDate = dateFormat2.parse(dateFormat1.format(endDate)
					+ " 23:59:59");
		}catch (Exception e){
		}
		return endDate;
	}

	/**
	 * <p>方法名称: getCurrentDayStartDate|描述: 获得本日的开始日期(yyyy-MM-dd 00:00:00)</p>
	 * @param startDate
	 * @return
	 */
	public static Date getCurrentDayStartDate(Date startDate){
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try{
			startDate = dateFormat2.parse(dateFormat1.format(startDate)
					+ " 00:00:00");
		}catch (Exception e){
		}
		return startDate;
	}

	/**
     * 获取指定月份的最后一天
     * @param theDataStr
     * @return
     */
    public static String LastDateOfMonth(String theDataStr){
    	Date theDate = java.sql.Date.valueOf(theDataStr);
    	Calendar c= new GregorianCalendar();
    	c.setTime(theDate);
    	c.set(Calendar.DAY_OF_MONTH,1);
    	c.add(Calendar.MONTH,1);
    	c.add(Calendar.DAY_OF_MONTH,-1);
    	return (new java.sql.Date(c.getTime().getTime())).toString();
    }
	/**
	 * 获得指定格式的当前日期字符串
	 */
    public static String getDatStrAsStyle(String part)throws Exception{
		String str="";
		if(part==null||part.equals("")){
			part="yyyy-MM-dd";
		}
		try{
			SimpleDateFormat sdf=new SimpleDateFormat(part);
			Calendar cd=Calendar.getInstance();
			str=sdf.format(cd.getTime());
		}catch(Exception e){
			throw new Exception("【错误：获得当前日期字符串格式服用异常！】");
		}
		return str;
	}

    /**
     *
    * <p>方法名称: getCurrDate|描述:获取当前日期 </p>
    * @return
     */
  public static Date getCurrDate(){
      Date date = null;
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      try{
       date = format.parse(format.format(new Date()));
      }catch(Exception e){
       e.printStackTrace();
      }
      return date;
     }
  
  /**
  *
 * <p>方法名称: getCurrDate|描述:获取当前日期 字符串</p>
 * @return
  */
public static String getCurrDateStr(String fmt){
   String date = null;
   SimpleDateFormat format = new SimpleDateFormat(fmt);
   try{
    date = format.format(new Date());
   }catch(Exception e){
    e.printStackTrace();
   }
   return date;
  }

  /**
	 * 日期解析
	 *
	 * @param source 日期字符
	 * @param format 解析格式，如果为空，使用系统默认格式解析
	 * @return 日期
	 */
	public static Date parse(String source, String format) {
		if (source == null) {
			return null;
		}

		DateFormat df = null;
		if (format != null) {
			df = new SimpleDateFormat(format);
		} else {
			df = DateFormat.getDateInstance(DateFormat.DEFAULT);
		}
		try {
			return df.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
	
	public static Date getYesterday(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat sdf = new SimpleDateFormat(ORA_DATES_FORMAT);
		return StringToDate(sdf.format(cal.getTime()), ORA_DATES_FORMAT);
	}
	
	// 日期转化为大小写
	public static String dataToUpper(Date date) {
	        Calendar ca = Calendar.getInstance();  
	        ca.setTime(date);  
	        int year = ca.get(Calendar.YEAR);  
	        int month = ca.get(Calendar.MONTH) + 1;  
	        int day = ca.get(Calendar.DAY_OF_MONTH);
	        return numToUpper(year) + "年" + monthToUppder(month) + "月" + dayToUppder(day) + "日";
	}
	 
	 // 将数字转化为大写
	 public static String numToUpper(int num) {
	        //String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
	        String u[] = {"零","一","二","三","四","五","六","七","八","九"};
	        char[] str = String.valueOf(num).toCharArray();
	        String rstr = "";
	        for (int i = 0; i < str.length; i++) {
	                rstr = rstr + u[Integer.parseInt(str[i] + "")];
	         }
	        return rstr;
	 }
	 
	 // 月转化为大写
	 public static String monthToUppder(int month) {
	          if(month < 10) {
	                  return numToUpper(month);       
	          } else if(month == 10){
	                  return "十";
	          } else {
	                  return "十" + numToUpper(month - 10);
	          }
	 }
	 
	 // 日转化为大写
	 public static String dayToUppder(int day) {
	          if(day < 20) {
	                   return monthToUppder(day);
	          } else {
	                   char[] str = String.valueOf(day).toCharArray();
	                   if(str[1] == '0') {
	                            return numToUpper(Integer.parseInt(str[0] + "")) + "十";
	                   }else {
	                            return numToUpper(Integer.parseInt(str[0] + "")) + "十" + numToUpper(Integer.parseInt(str[1] + ""));
	                   }
	        }
	}
	 /**
	 * 对当前时间进行操作
	 * @param date 当前时间
	 * @param delayTime + 代表未来时间，-代表已逝时间
	 * @param zone 代表哪一位，2->月，3->星期，5->日，10->小时，12->分钟，13->秒
	 * @return
	 * @author zhaofangcheng
	 */
	public static Date customDate(Date date,int zone,int delayTime){
	   GregorianCalendar cal = new GregorianCalendar();  
       cal.setTime(date);  
       cal.add(zone,delayTime);  
       return cal.getTime(); 
	}
	/**
	 * 获取今天从零分开始
	 * @return
	 */
	public static Date currentDate(){
		String d=getCurrDateStr("yyyy-MM-dd")+" 00:00:01"; 
		return StringToDate(d,"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 获取今天从零分开始
	 * @return
	 */
	public static Date currentDateEnd(){
		String d=getCurrDateStr("yyyy-MM-dd")+" 23:59:59"; 
		return StringToDate(d,"yyyy-MM-dd HH:mm:ss");
	}
}
