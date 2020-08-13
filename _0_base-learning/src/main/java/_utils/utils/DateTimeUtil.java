package _utils.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期处理辅助类
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author gaohj
 * @version 1.0
 */
public class DateTimeUtil {
//	private static Log log = Log.getLog(DateTimeUtil.class);

  public static final String LONGTIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

  public static final String LONGTIME_PATTERN2 = "yyyyMMddHHmmss";

  public static final String MEDIATIME_PATTERN = "yyyy-MM-dd";

  public static final String MEDIATIME_PATTERN2 = "yyyyMMdd";

  public static final String MEDIATIME_YEARANDMONEY = "yyyyMM";
  public static final String MEDIATIME_YEARANDMONEY1 = "yyyy-MM";

  public static final String SHORTTIME_PATTERN = "HH:mm:SS";

  private static SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");

  public static String getSignTime() {
    Calendar ca = Calendar.getInstance();
    SimpleDateFormat sp1 = new SimpleDateFormat(MEDIATIME_PATTERN2);
    return sp1.format(ca.getTime());
  }

  public static String getNowString() {
    Calendar ca = Calendar.getInstance();
    SimpleDateFormat sp1 = new SimpleDateFormat(MEDIATIME_PATTERN);
    return sp1.format(ca.getTime());
  }


  public static String getNowFullString() {
    Calendar ca = Calendar.getInstance();
    SimpleDateFormat sp1 = new SimpleDateFormat(LONGTIME_PATTERN);
    return sp1.format(ca.getTime());
  }

  /**
   * @param @param
   *     startTime  10:18
   * @param @param
   *     endTime   23:13
   * @param @return
   *
   * @return int
   *
   * @Title: getMins
   * @Description:获取两个时间差的分钟
   */
  public static int getMins(String startTime, String endTime) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int result = 0;
    try {
      Date d1 = df.parse("2004-03-26 " + startTime + ":00");
      Date d2 = df.parse("2004-03-26 " + endTime + ":00");
      result = (int) ((d2.getTime() - d1.getTime()) / 1000 / 60);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }

  public static int getTwoSecondTimesMins(String startTime, String endTime) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int result = 0;
    try {
      Date d1 = df.parse("2004-03-26 " + startTime);
      Date d2 = df.parse("2004-03-26 " + endTime);
      result = (int) ((d2.getTime() - d1.getTime()) / 1000 / 60);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }

  public static int isLtTime(String startTime, String endTime) {
    int i = Integer.parseInt(startTime.replaceAll(":", "").replaceAll("", ""));
    int y = Integer.parseInt(endTime.replaceAll(":", "").replaceAll("", ""));
    if (i > y) {
      return 1;
    } else if (i == y) {
      return 0;
    } else {
      return -1;
    }
  }

  public static boolean isDateTime(String dt) {
    SimpleDateFormat sdf = new SimpleDateFormat();
    try {
      sdf.parse(dt);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 得到指定日期的字符串格式：YYYY-MM-DD HH:mm:SS.ms
   *
   * @param dt
   *     指定的日期
   *
   * @return
   */
  public static String getFullTime(Calendar dt) {
    return formatCalendar(dt, LONGTIME_PATTERN);
  }

  /**
   * 得到当前日期的字符串格式：YYYY-MM-DD HH:mm:SS.ms
   *
   * @return
   */
  public static String getFullTime() {
    return getFullTime(Calendar.getInstance());
  }

  /**
   * 得到当前日期的字符串格式：YYYY-MM-DD HH:mm:SS.ms
   *
   * @return
   */
  public static String getDateTime(String dateTimePattern) {
    return formatCalendar(Calendar.getInstance(), dateTimePattern);
  }

  /**
   * 得到短日期格式：HH:mm:SS.ms
   *
   * @param datetime
   *     日期
   *
   * @return
   */
  public static String getShortTime(Calendar datetime) {
    return formatCalendar(datetime, SHORTTIME_PATTERN);
  }


  /**
   * 比较两个日期的大小
   *
   * @param nowDate
   * @param lastDate
   *
   * @return 1 大于 0 相等 -1 小于
   */
  public static int comparativeTime(Date nowDate, Date lastDate)

  {
    SimpleDateFormat sdf = new SimpleDateFormat(LONGTIME_PATTERN2);
    String nowTime = sdf.format(nowDate);
    String lastTime = sdf.format(lastDate);
    try {
      nowDate = sdf.parse(nowTime);
      lastDate = sdf.parse(lastTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    if (nowDate.equals(lastDate)) {
      return 0;
    }
    if (nowDate.after(lastDate)) {
      return 1;
    }
    if (nowDate.before(lastDate)) {
      return -1;
    }
    return 0;
  }

  public static int comparativeDate(Date nowDate, Date lastDate)

  {
    SimpleDateFormat sdf = new SimpleDateFormat(MEDIATIME_PATTERN);
    String nowTime = sdf.format(nowDate);
    String lastTime = sdf.format(lastDate);
    try {
      nowDate = sdf.parse(nowTime);
      lastDate = sdf.parse(lastTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    if (nowDate.equals(lastDate)) {
      return 0;
    }
    if (nowDate.after(lastDate)) {
      return 1;
    }
    if (nowDate.before(lastDate)) {
      return -1;
    }
    return 0;
  }


  /**
   * 得到当前日期短日期格式：HH:mm:SS.ms
   *
   * @return
   */
  public static String getShortTime() {
    return getShortTime(Calendar.getInstance());
  }

  /**
   * 得到日期格式：YYYY-MM-DD
   *
   * @param datetime
   *     日期
   *
   * @return
   */
  public static String getMediumTime(Calendar datetime) {
    return formatCalendar(datetime, MEDIATIME_PATTERN);
  }

  /**
   * 得到当前日期的年月日格式：YYYY-MM-DD
   *
   * @return
   */
  public static String getMediumTime() {
    return getMediumTime(Calendar.getInstance());
  }

  /**
   * 比较两个日期的早晚，日期格式为：yyyy-MM-dd
   *
   * @param date1
   *     日期1
   * @param date2
   *     日期2
   *
   * @return 如果日期1晚于日期2，则返回大于0；如果日期1等日期2，则返回0；如果日期1早于日期2，则返回小于0
   */
  public static int compareDate(String date1, String date2) {
    Date dt1 = toDate(date1);
    Date dt2 = toDate(date2);
    return dt1.compareTo(dt2);
  }

  /**
   * 得到当前年份
   *
   * @return
   */
  public static int getCurrentYear() {
    Calendar cal = Calendar.getInstance();
    return cal.get(Calendar.YEAR);
  }

  /**
   * 得到当前月份
   *
   * @return
   */
  public static int getCurrentMonth() {
    Calendar cal = Calendar.getInstance();
    return cal.get(Calendar.MONTH) + 1;
  }

  /**
   * 得到当前日
   *
   * @return
   */
  public static int getCurrentDay() {
    Calendar cal = Calendar.getInstance();
    return cal.get(Calendar.DAY_OF_MONTH);
  }

  /**
   * 格式化给定时间
   *
   * @param cal
   *     给定时间
   * @param pattern
   *     要格式化的模式
   *
   * @return 格式化后的字符串
   */
  public static String formatCalendar(Calendar cal, String pattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    return sdf.format(cal.getTime());
  }

  /**
   * 将字符串(yyy-MM-dd)转化为日期
   *
   * @param strDate
   *     待转化的日期字符串
   *
   * @return 日期对象, 如果字符串格式非法，则返回null
   *
   * @throws
   */
  public static Date toDate(String strDate) {
    SimpleDateFormat sdf = new SimpleDateFormat(MEDIATIME_PATTERN);
    try {
      return sdf.parse(strDate);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

    public static Date toDate(String strDate, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
  /**
   * 判断time是否在from，to之内
   *
   * @param time
   *     指定日期
   * @param from
   *     开始日期
   * @param to
   *     结束日期
   *
   * @return
   */
  public static boolean belongCalendar(Date time, Date from, Date to) {
    Calendar date = Calendar.getInstance();
    date.setTime(time);

    Calendar after = Calendar.getInstance();
    after.setTime(from);

    Calendar before = Calendar.getInstance();
    before.setTime(to);

    if ((date.after(after) || date.equals(after) )&& (date.before(before) || date.equals(before))) {
      return true;
    } else {
      return false;
    }
  }

  public static Date getMonFirstDay(Integer add) {
    Calendar cal = Calendar.getInstance();
    Date now = new Date();
    cal.setTime(now);
    cal.add(Calendar.MONTH, add);
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    String resultTime = year + "-" + month + "-01";
    Date date = null;
    try {
      date = sp.parse(resultTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  // 本月最后一天日期
  public static Date getThisMonLastDay() {
    Calendar cal = Calendar.getInstance();
    Date now = new Date();
    cal.setTime(now);
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    String resultTime = year + "-" + month + "-" + day;
    Date date = null;
    try {
      date = sp.parse(resultTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }


  /**
   * 获得两个日期的 天数集合
   */
  public static List<String> getDay(Date start, Date end) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    List<String> list = new ArrayList<String>();
    try {
      Date date = start;
      Calendar cd = Calendar.getInstance();
      while (date.getTime() <= end.getTime()) {
        list.add(sdf.format(date));
        cd.setTime(date);
        // 增加一天
        cd.add(Calendar.DATE, 1);
        date = cd.getTime();
      }
    } catch (Exception e) {
    }
    return list;
  }


  /**
   * *年*月最后一天日期
   * @param year
   * @param month
   * @return
   */
  public static Integer getMonLastDay(Integer year, Integer month) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, month);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    cal.add(Calendar.DAY_OF_MONTH, -1);
    return cal.get(Calendar.DAY_OF_MONTH);
  }

  /**
   * 某月第一天日期
   * @param year
   * @param month
   * @return
   */
  public static Date getMonFirstDay(Integer year, Integer month) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, month - 1);
    String resultTime = year + "-" + month + "-01";
    Date date = null;
    try {
      date = sp.parse(resultTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  /**
   * 将字符串(yyyy-MM-dd HH:mm:ss)转化为日期
   *
   * @param strDate
   *     待转化的日期字符串
   *
   * @return 日期对象, 如果字符串格式非法，则返回null
   *
   * @throws
   */
  public static Date toDateTime(String strDate) {
    SimpleDateFormat sdf = new SimpleDateFormat(LONGTIME_PATTERN);
    try {
      return sdf.parse(strDate);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 将字符串(yyy-MM-dd)转化为日期
   *
   * @param strDate
   *     待转化的日期字符串
   *
   * @return 日期对象, 如果字符串格式非法，则返回null
   *
   * @throws
   */
  public static Calendar toCalendar(String strDate) {
    Date dt = toDate(strDate);
    Calendar cal = Calendar.getInstance();
    cal.setTime(dt);
    return cal;
  }


  /**
   * 根据 日期（时间）字符串 解析日期（时间）
   *
   * @param strTime
   *     字符串形式的日期（时间），格式为：
   *     <p/>
   *     （1）yyyyMMdd （2） yyyy-MM-dd （3）yyyyMMddHHmmss （4） yyyy-MM-dd
   *     HH:mm:ss
   *
   * @return Date 如果解析成功，则返回Date类型的对象，否则为 null
   */
  private static Date parseDate(final String strTime) {
    if (strTime == null || strTime.trim().length() < 6) // 日期时间的最小长度必须大于等于6
    {
//			log.error("DateTimeUtil.parseDate()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");

      return null;
    }

    try {
      if (strTime.indexOf('-') >= 0) // yyyy-MM-dd HH:mm:ss
      {
        if (strTime.length() > 10) {
          SimpleDateFormat timeFormat2 = new SimpleDateFormat(LONGTIME_PATTERN);
          return timeFormat2.parse(strTime);
        } else {
          SimpleDateFormat dateFormat2 = new SimpleDateFormat(MEDIATIME_PATTERN);
          return dateFormat2.parse(strTime);
        }
      } else
      // yyyyMMddHHmmss
      {
        if (strTime.length() > 8) {
          SimpleDateFormat timeFormat1 = new SimpleDateFormat(LONGTIME_PATTERN2);
          return timeFormat1.parse(strTime);
        } else {
          SimpleDateFormat dateFormat1 = new SimpleDateFormat(MEDIATIME_PATTERN2);
          return dateFormat1.parse(strTime);
        }
      }
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 将日期转化为指定格式的字符串
   *
   * @param date
   *     日期
   * @param format
   *     格式字符串
   *
   * @return 指定格式的日期字符串
   */
  public static String formatDate(Date date, String format) {
    if (null == date) {
      return null;
    }
    SimpleDateFormat sdFromat = new SimpleDateFormat(format);
    return sdFromat.format(date);
  }

  /**
   * 日期转换为字符串 短日期
   *
   * @param date
   *
   * @return
   */
  public static String dateToString(Date date) {
    if (date != null) {
      SimpleDateFormat dateFormat2 = new SimpleDateFormat(MEDIATIME_PATTERN);
      return dateFormat2.format(date);
    } else {
      return null;
    }
  }

  /**
   * 日期转换为字符串 长日期
   *
   * @param date
   *
   * @return
   */
  public static String dateTimeToString(Date date) {
    if (date != null) {
      SimpleDateFormat timeFormat2 = new SimpleDateFormat(LONGTIME_PATTERN);
      return timeFormat2.format(date);
    } else {
      return null;
    }
  }

  /**
   * 获取指定日期以后（以前）一定年数的日期
   *
   * @param strTime
   *     字符串形式的日期，格式可以为：（1）yyyyMMdd （2） yyyy-MM-dd
   * @param count
   *     要增加（减少）的年数，是负数表示向前的年数
   *
   * @return String 格式与输入的日期格式相同
   */
  public static String dateAddYear(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.dateAddYear()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.YEAR, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd
    {
      SimpleDateFormat dateFormat2 = new SimpleDateFormat(MEDIATIME_PATTERN);
      return dateFormat2.format(dtDst);
    } else
    // //yyyyMMdd
    {
      SimpleDateFormat dateFormat1 = new SimpleDateFormat(MEDIATIME_PATTERN2);
      return dateFormat1.format(dtDst);
    }
  }

  /**
   * 获取指定日期以后（以前）一定月份数的日期
   *
   * @param strTime
   *     字符串形式的日期，格式可以为：（1）yyyyMMdd （2） yyyy-MM-dd
   * @param count
   *     要增加（减少）的月份数，是负数表示向前的月份数
   *
   * @return String 格式与输入的日期格式相同
   */
  public static String dateAddMonth(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.dateAddMonth()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.MONTH, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd
    {
      SimpleDateFormat dateFormat2 = new SimpleDateFormat(MEDIATIME_PATTERN);
      return dateFormat2.format(dtDst);
    } else
    // //yyyyMMdd
    {
      SimpleDateFormat dateFormat1 = new SimpleDateFormat(MEDIATIME_PATTERN2);
      return dateFormat1.format(dtDst);
    }
  }

  /**
   * 获取指定日期以后（以前）n个星期的日期
   *
   * @param strTime
   *     字符串形式的日期，格式可以为：（1）yyyyMMdd （2） yyyy-MM-dd
   * @param count
   *     要增加（减少）的星期数，是负数表示向前的星期数
   *
   * @return String 格式与输入的日期格式相同
   */
  public static String dateAddWeek(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.dateAddWeek()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.WEEK_OF_MONTH, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd
    {
      SimpleDateFormat dateFormat2 = new SimpleDateFormat(MEDIATIME_PATTERN);
      return dateFormat2.format(dtDst);
    } else
    // //yyyyMMdd
    {
      SimpleDateFormat dateFormat1 = new SimpleDateFormat(MEDIATIME_PATTERN2);
      return dateFormat1.format(dtDst);
    }
  }

  /**
   * 获取指定日期以后（以前）一定天数的日期
   *
   * @param strTime
   *     字符串形式的日期，格式可以为：（1）yyyyMMdd （2） yyyy-MM-dd
   * @param count
   *     要增加（减少）的天数，是负数表示向前的天数
   *
   * @return String 格式与输入的日期格式相同
   */
  public static String dateAddDay(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.dateAddDay()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.DAY_OF_MONTH, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd
    {
      SimpleDateFormat dateFormat2 = new SimpleDateFormat(MEDIATIME_PATTERN);
      return dateFormat2.format(dtDst);
    } else
    // //yyyyMMdd
    {
      SimpleDateFormat dateFormat1 = new SimpleDateFormat(MEDIATIME_PATTERN2);
      return dateFormat1.format(dtDst);
    }
  }


  public static Date dateAddDay(Date time, int count) {
    if (time == null)  return null;

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(time);

    calendar.add(Calendar.DATE, count);
    Date dtDst = calendar.getTime();
    return dtDst;
  }

  public static Date getDateOfZero(Date current){
    Calendar cal = Calendar.getInstance();
    cal.setTime(current);
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    return cal.getTime();
  }
  public static Date getDateOfZeroGip(Date current){
    Calendar cal = Calendar.getInstance();
    cal.setTime(current);
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
    return cal.getTime();
  }

  /**
   * 获取指定时间以后（以前）一定小时数的时间
   *
   * @param strTime
   *     字符串形式的时间，格式可以为：（1）yyyyMMddhhmmss （2） yyyy-MM-dd hh:mm:ss
   * @param count
   *     要增加（减少）的小时数，是负数表示向前的小时数
   *
   * @return String 格式与输入的时间格式相同
   */
  public static String timeAddHour(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.timeAddHour()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.HOUR_OF_DAY, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd hh:mm:ss
    {
      SimpleDateFormat timeFormat2 = new SimpleDateFormat(LONGTIME_PATTERN);
      return timeFormat2.format(dtDst);
    } else
    // //yyyyMMddhhmmss
    {
      SimpleDateFormat timeFormat1 = new SimpleDateFormat(LONGTIME_PATTERN2);
      return timeFormat1.format(dtDst);
    }
  }


  /**
   * 获取指定时间以后（以前）一定分钟数的时间
   *
   * @param strTime
   *     字符串形式的时间，格式可以为：（1）yyyyMMddhhmmss （2） yyyy-MM-dd hh:mm:ss
   * @param count
   *     要增加（减少）的分钟数，是负数表示向前的分钟数
   *
   * @return String 格式与输入的时间格式相同
   */
  public static String timeAddMinute(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.timeAddMinute()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.MINUTE, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd hh:mm:ss
    {
      SimpleDateFormat timeFormat2 = new SimpleDateFormat(LONGTIME_PATTERN);
      return timeFormat2.format(dtDst);
    } else
    // //yyyyMMddhhmmss
    {
      SimpleDateFormat timeFormat1 = new SimpleDateFormat(LONGTIME_PATTERN2);
      return timeFormat1.format(dtDst);
    }
  }


  /**
   * 获取指定时间以后（以前）一定秒数的时间
   *
   * @param strTime
   *     字符串形式的时间，格式可以为：（1）yyyyMMddhhmmss （2） yyyy-MM-dd hh:mm:ss
   * @param count
   *     要增加（减少）的秒数，是负数表示向前的秒数
   *
   * @return String 格式与输入的时间格式相同
   */
  public static String timeAddSecond(String strTime, int count) {
    if (strTime == null) {
//			log.error("DateTimeUtil.timeAddSecond()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
      return null;
    }

    Calendar calendar = Calendar.getInstance();

    Date dtSrc = parseDate(strTime);
    calendar.setTime(dtSrc);

    calendar.add(Calendar.SECOND, count);
    Date dtDst = calendar.getTime();

    if (strTime.indexOf('-') >= 0) // yyyy-MM-dd hh:mm:ss
    {
      SimpleDateFormat timeFormat2 = new SimpleDateFormat(LONGTIME_PATTERN);
      return timeFormat2.format(dtDst);
    } else
    // //yyyyMMddhhmmss
    {
      SimpleDateFormat timeFormat1 = new SimpleDateFormat(LONGTIME_PATTERN2);
      return timeFormat1.format(dtDst);
    }
  }

  /**
   * 将字符串形式的日期时间格式成显示形式
   *
   * @param strTime
   *     字符串形式的日期（时间），格式为：
   *     <p/>
   *     （1）yyyyMMdd （2）yyyyMMddHHmmss
   *
   * @return String
   */
  public static String displayDateTimeCN(String strTime) {
    String retString = strTime;
    try {
      if (strTime == null) {
        retString = "";
      } else if (strTime.length() == 14) {
        retString =
            strTime.substring(0, 4) + "年" + strTime.substring(4, 6) + "月" + strTime.substring(6, 8)
                + "日 " + strTime.substring(8, 10) + "时" + strTime.substring(10, 12) + "分" + strTime
                .substring(12, 14) + "秒";
      } else if (strTime.length() == 8) {
        retString =
            strTime.substring(0, 4) + "年" + strTime.substring(4, 6) + "月" + strTime.substring(6, 8)
                + "日";
      } else if (strTime.length() == 6) {
        retString = strTime.substring(0, 4) + "年" + strTime.substring(4, 6) + "月";
      } else if (strTime.length() <= 4) {
        retString = strTime.substring(0, 4) + "年";
      }
    } catch (Exception e) {
//			log.error("DateTimeUtil.displayDateTimeCN()日期时间字符串 [" + strTime + "] 不符合系统格式要求！");
    }
    return retString;
  }

  /**
   * @param @param
   *     startTime  10:18
   * @param @param
   *     endTime   23:13
   * @param @return
   *
   * @return int
   *
   * @Title: getMins
   * @Description:获取两个时间差的分钟
   */
  public static int getSecond(String startTime, String endTime) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int result = 0;
    try {
      Date d1 = df.parse("2004-03-26 " + startTime + ":00");
      Date d2 = df.parse("2004-03-26 " + endTime + ":00");
      result = (int) ((d2.getTime() - d1.getTime()) / 1000);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }

  /**
   * @param @param
   *     startTime
   * @param @param
   *     x
   * @param @return
   *
   * @return String
   *
   * @Title: addDateMinut
   * @Description:增加多少分钟
   */
  public static String addDateMinut(String startTime, int x) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
    SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");// 24小时制
    Date date = null;
    try {
      date = format.parse("2014-03-26 " + startTime + ":00");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    if (date == null) {
      return "";
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    // 24小时制
    cal.add(Calendar.MINUTE, x);
    date = cal.getTime();
    cal = null;
    return format1.format(date);
  }

  public static String getStringNowShortTime() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 24小时制
    String time = format.format(new Date());
    return time + " 00:00:00";
  }

  public static int CompareTwoString(String a, String b) {
    DateFormat df = new SimpleDateFormat("HH:mm:ss");
    try {
      if (a.equals(b)) {
        return 0;
      }
      Date aa = df.parse(a);
      Date bb = df.parse(b);
      if (aa.getTime() < bb.getTime()) {
        return 1;
      } else {
        return -1;
      }
    } catch (ParseException e) {
      e.printStackTrace();
      return -2;
    }
  }


  public static String differMins(Date inTime) {
    int minutes = (int) ((System.currentTimeMillis() - inTime.getTime()) / (1000 * 60));
    if (minutes < 60) {
      return minutes + "分钟";
    } else {
      int hour = minutes / 60;
      int othermins = minutes - hour * 60;
      if (othermins == 0) {
        return hour + "小时";
      } else {
        return hour + "小时" + othermins + "分钟";
      }
    }
  }


  public static String differMins(Date inTime, Date outTime) {
    if (inTime == null || outTime == null) {
      return " ";
    }
    int minutes = (int) ((outTime.getTime() - inTime.getTime()) / (1000 * 60));
    if (minutes < 60) {
      return minutes + "分钟";
    } else {
      int hour = minutes / 60;
      int othermins = minutes - hour * 60;
      if (othermins == 0) {
        return hour + "小时";
      } else {
        return hour + "小时" + othermins + "分钟";
      }
    }
  }


  public static String differMins(String inTime, String outTime) {
    if (org.apache.commons.lang3.StringUtils.isBlank(inTime) || StringUtils.isBlank(outTime)) {
      return "空";
    }
    Date inDate = DateTimeUtil.parseDate(inTime);
    Date outDate = DateTimeUtil.parseDate(outTime);
    if (inTime == null || outTime == null) {
      return "空";
    }
    int minutes = (int) ((outDate.getTime() - inDate.getTime()) / (1000 * 60));
    if (minutes < 60) {
      return minutes + "分钟";
    } else {
      int hour = minutes / 60;
      int othermins = minutes - hour * 60;
      if (othermins == 0) {
        return hour + "小时";
      } else {
        return hour + "小时" + othermins + "分钟";
      }
    }
  }

  public static String differMins(int minutes) {
    if (minutes < 60) {
      return minutes + "分钟";
    } else {
      int hour = minutes / 60;
      int othermins = minutes - hour * 60;
      if (othermins == 0) {
        return hour + "小时";
      } else {
        return hour + "小时" + othermins + "分钟";
      }
    }
  }

  public static String[] getYesterDayWeek() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String[] a = new String[7];
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -1);
    for (int i = 0; i < 7; i++) {
      a[i] = sdf.format(calendar.getTime());
      calendar.add(Calendar.DATE, -1);
    }
    return a;
  }


  /**
   * 分钟转天时分
   *
   * @param min
   *
   * @return day hour min
   */
  public static String minConvertDayHourMin(Double min) {
    String html = "0分";
    if (min != null) {
      Double m = (Double) min;
      String format;
      Object[] array;
      Integer days = (int) (m / (60 * 24));
      Integer hours = (int) (m / (60) - days * 24);
      Integer minutes = (int) (m - hours * 60 - days * 24 * 60);
      if (days > 0) {
        format = "%1$,d天%2$,d时%3$,d分";
        array = new Object[]{days, hours, minutes};
      } else if (hours > 0) {
        format = "%1$,d时%2$,d分";
        array = new Object[]{hours, minutes};
      } else {
        format = "%1$,d分";
        array = new Object[]{minutes};
      }
      html = String.format(format, array);
    }

    return html;
  }

  /**
   * 天时分转分钟
   *
   * @param day
   * @param hour
   * @param min
   *
   * @return min
   */
  public static int dayHourMinConvertMin(int day, int hour, int min) {
    int days = day * 24 * 60;
    int hours = hour * 60;
    return days + hours + min;
  }

  public static String[] getDayWeek() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String[] a = new String[7];
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -6);
    for (int i = 0; i < 7; i++) {
      a[i] = sdf.format(calendar.getTime());
      calendar.add(Calendar.DATE, 1);
    }
    return a;
  }

//	public static void main(String[] args) {
//		Date lastMonth=DateTimeUtil.getMonFirstDay(-1);
//		lastMonth=DateTimeUtil.dateAddMonth(lastMonth,-3);
//		Date yesterdayMonth=DateTimeUtil.toDate(DateTimeUtil.dateAddDay(DateTimeUtil.getNowString(),-1));
//		yesterdayMonth=DateTimeUtil.dateAddMonth(yesterdayMonth, -3);
//		System.out.println(lastMonth.toLocaleString());
//		System.out.println(yesterdayMonth.toLocaleString());
////		Date now=DateTimeUtil.toDate(DateTimeUtil.getNowString());
////		Date yesterday=DateTimeUtil.toDate(DateTimeUtil.dateAddDay(DateTimeUtil.getNowString(),-1));
////		System.out.println(now.toLocaleString());
////		System.out.println(yesterday.toLocaleString());
////		String[] s=getDayWeek();
////		for(int i=0;i<s.length;i++){
////		}
////
////		Date date = getMonFirstDay(-1);
////        System.out.println(date.toLocaleString());
//	}

  public static Date dateAddMonth(Date lastMonth, int i) {
    Calendar calendar = Calendar.getInstance();

    calendar.setTime(lastMonth);

    calendar.add(Calendar.MONTH, i);
    return calendar.getTime();
  }

  public static int compare_date(String DATE1, String DATE2) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date dt1 = df.parse(DATE1);
      Date dt2 = df.parse(DATE2);
      if (dt1.getTime() > dt2.getTime()) {
        System.out.println("dt1 在dt2前");
        return 1;
      } else if (dt1.getTime() < dt2.getTime()) {
        System.out.println("dt1在dt2后");
        return -1;
      } else {
        return 0;
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return 0;
  }
 
	/*public static void main(String[] args) {
		Calendar cal=Calendar.getInstance();
//System.out.println(Calendar.DATE);//5
		cal.add(Calendar.DATE,-1);
		Date time=cal.getTime();
		System.out.println(new SimpleDateFormat("yyyyMM").format(time));
	}*/
 

  public static boolean isSameDate(Date date1, Date date2) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(date1);

    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(date2);

    boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
        .get(Calendar.YEAR);
    boolean isSameMonth = isSameYear
        && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
    boolean isSameDate = isSameMonth
        && cal1.get(Calendar.DAY_OF_MONTH) == cal2
        .get(Calendar.DAY_OF_MONTH);

    return isSameDate;
  }


  /*
  * 将 LocalDateTime 格式转换成 date 格式  长时间格式
  *
  * */
/*  public static Date localDateTimeToDate( LocalDateTime localDateTime){
      ZoneId zoneId = ZoneId.systemDefault();
      ZonedDateTime zdt = localDateTime.atZone(zoneId);
      Date date = Date.from(zdt.toInstant());
      return date;
  }*/

  /*
  * 将 LocalDateTime 格式的时间 转换成 yyyy-MM-dd HH:mm:ss 格式的字符串
  * */
  /*  public static String localDateTimeToDateString( LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        SimpleDateFormat sdf = new SimpleDateFormat(LONGTIME_PATTERN);// 24小时制
        String format = sdf.format(date);
        return format;
    }*/

    /*
    * 将 date 转换成 LocalDateTime 格式
    * */
    public static LocalDateTime dateToLocalDateTime (Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(LONGTIME_PATTERN);
        DateTimeFormatter df = DateTimeFormatter.ofPattern(LONGTIME_PATTERN);
        LocalDateTime parse = LocalDateTime.parse(sdf.format(date), df);
        return parse;
    }

    /*
    * 比较 两个LocalDateTime 时间的 大小
    *   l1 > l2  == 1
    *   l1 < l2 == -1
    *   l1 = l2 == 0
    * */
    public static int compareLDT(LocalDateTime l1,LocalDateTime l2){
        return l1.isAfter(l2) ? 1 : (l1.isBefore(l2) ? -1 : 0);
    }




  /*<获取分钟数>_12302_2019-11-11_*/
  public static Long calcMinutes(String inTime){
    try{
      SimpleDateFormat df = new SimpleDateFormat(LONGTIME_PATTERN);
      Date outTime = new Date();
      Date startTime = df.parse(inTime);
      long l = outTime.getTime() - startTime.getTime();
      return (l/1000/60);
    }catch (Exception e){
      //Ignore ...
    }
    return 0l;
  }

  public static void main(String[] args) throws ParseException {
    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
    String a = "2017-02-28";
    Date expiryEndDate = sim.parse(a);

    Calendar cd = Calendar.getInstance();
    System.out.println(sim.format(cd.getTime()));
    System.out.println(expiryEndDate.getYear());
    System.out.println(expiryEndDate.getYear()+1900);
    cd.set(expiryEndDate.getYear() + 1900, expiryEndDate.getMonth(), expiryEndDate.getDate());
    System.out.println(sim.format(cd.getTime()));
    cd.add(Calendar.DATE, 1);
    System.out.println(sim.format(cd.getTime()));
    cd.add(Calendar.MONTH, 1);
    cd.add(Calendar.DATE, -1);
    Date newExpiryEndDate = cd.getTime();

    System.out.println(sim.format(newExpiryEndDate));
  }
}
