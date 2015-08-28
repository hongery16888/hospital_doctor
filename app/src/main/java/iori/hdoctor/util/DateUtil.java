package iori.hdoctor.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.text.format.Time;

import iori.hdoctor.modules.common.BaseManager;

public class DateUtil {
	/**
	 * 日期转换成字符串
	 *
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date, String outFormat) {

		SimpleDateFormat format = new SimpleDateFormat(outFormat);// "yyyy MM-dd E HH:mm a"
		String str = format.format(date);
		return str;
	}

	/**
	 * 比较起始日期大小
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean compareDate(String startDate, String endDate) {
		if (java.sql.Date.valueOf(
				getDates(startDate, "yyyy/MM/dd", "yyyy-MM-dd")).after(
				java.sql.Date.valueOf(getDates(endDate, "yyyy/MM/dd",
						"yyyy-MM-dd")))) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 字符串转换成日期
	 *
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str, String inFormat) {

		SimpleDateFormat format = new SimpleDateFormat(inFormat);// "yyyy-MM-dd HH:mm:ss"
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static boolean LoginFlag(){
		long startTime = StrToDate("2015-08-21", "yyyy-MM-dd").getTime();
		long dayTime = 86400000;
		System.out.println("startTime : " + (System.currentTimeMillis() - startTime) + "    nowTime : " + dayTime*30);
		if ((System.currentTimeMillis() - startTime) > dayTime*30){
			return false;
		}else
			return true;
	}

	/**
	 * 把传进的字符串日期转化成相应的格式的日期字符串
	 *
	 * @param date
	 * @return
	 */
	public static String getDates(String date, String inFormat, String outFormat) {
		return DateToStr(StrToDate(date, inFormat), outFormat);
	}

	public static String long2Date(Long date){
		DateFormat format = new SimpleDateFormat("MM/dd  HH:mm:ss");
		return format.format(new Date(date)) ;
	}

	public static String long2Date(Long date,  String outFormat){
		DateFormat format = new SimpleDateFormat(outFormat);
		return format.format(new Date(date)) ;
	}

	public static String long2Date(String date, String outFormat){
		DateFormat format = new SimpleDateFormat(outFormat);
		return format.format(new Date(Long.parseLong(date))) ;
	}

	public static String getToday(){
		Time time = new Time();
		time.setToNow();
		return time.year + "-" + (time.month + 1 < 10 ? "0" + (time.month + 1) : time.month +1 ) + "-" + (time.monthDay < 10 ? "0" + time.monthDay : time.monthDay);
	}

	public static String getFindToday(){
		Time time = new Time();
		time.setToNow();
		return time.year + "/" + (time.month + 1 < 10 ? "0" + (time.month + 1) : time.month +1 ) + "/" + (time.monthDay < 10 ? "0" + time.monthDay : time.monthDay);
	}

	public static String getLastday(int i){
		Time time = new Time();
		time.setToNow();
		int monthDay = (time.monthDay - i > 0 ? time.monthDay - i : getMonthDay(time.month + 1));
		int month = (monthDay > time.monthDay ? (time.month  > 0 ? time.month  : 12) : time.month + 1);
		int year = (month > time.month + 1 ? time.year - 1 : time.year);
		return year + "-" + (month < 10? "0" + month : month) + "-" + (monthDay < 10 ? "0" + monthDay : monthDay);
	}

	public static int getMonthDay(int month){
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month ==12)
			return 31;
		else if(month == 2)
			return 28;
		else
			return 30;
	}

	public static boolean isToday(String date){
		if(null != date && !"".equals(date) && !"null".equals(date)){
			Time time = new Time();
			time.setToNow();
			String[] fdate = date.split(" ");
			String[] dates = fdate[0].split("-");
			if(Integer.parseInt(dates[0]) == time.year && Integer.parseInt(dates[1]) == (time.month+1) && Integer.parseInt(dates[2]) == time.monthDay){
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据用户生日计算年龄
	 */
	public static int getAgeByBirthday(Date birthday) {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age;
	}

	public static ArrayList<String> getAllDayTime(){
		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < 24; i++){
			list.add(i + ":00");
		}

		return  list;
	}
}
