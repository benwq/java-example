package utils;

import jodd.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
* @ClassName: DateTool
* @Description: 日期处理工具类
* @author ben
* @date 2015-11-21 下午5:49:00
 */
public class DateTool {

	public static Date getNow(){
		return new Date();
	}
	
	public static String getNowString(String reg){
		if(StringUtil.isBlank(reg)) reg="yyyy-MM-dd HH:mm:ss";
		return new SimpleDateFormat(reg).format(new Date());
	}
    
	/**
     *@方法名称:toDate
     *@方法描述:将日期转换为需要的格式，例如yyyy-MM-dd HH:mm:ss
     *@return 日期型
     */
	public static Date toDate(String date,String reg){
		try {
			return new SimpleDateFormat(reg).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *@方法名称:toString
	 *@方法描述:  将日期转换为需要的格式，例如yyyy-MM-dd HH:mm:ss
	 *@return 字符串
	 */
	public static String toString(Date date,String reg){
		if(date == null) return "";
		return new SimpleDateFormat(reg).format(date);
	}
	
	public static Date getStringDateByDay(int dayCount){
        Calendar calendar  = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, dayCount);
        return calendar.getTime();
	}
	
	/***
	 * 获取某天的日期 ,昨天 dayCount=-1；当天 dayCount=0; 明天dayCount=1
	 * @return
	 */
	public static String getStringDateByDay(int dayCount,String reg){
		SimpleDateFormat sdf = new SimpleDateFormat(reg);
        return sdf.format(getStringDateByDay(dayCount));
	}
	
	public static Date getDateByMonth(int monthCount){
		Calendar calendar  = Calendar.getInstance();
		calendar.add(Calendar.MONTH, monthCount);
		return calendar.getTime();
	}
	
	/**
	 * 获取相隔几个月 ,上个月 monthCount=-1；当前月 monthCount=0; 下一个月monthCount=1
	 * @return
	 */
	public static String getStringDateByMonth(int monthCount,String reg){
		SimpleDateFormat sdf = new SimpleDateFormat(reg);
		return sdf.format(getDateByMonth(monthCount));
	}
	
	public static int getSecondByDate(Date date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		Calendar ce = Calendar.getInstance();
		Long time = ce.getTimeInMillis() - ca.getTimeInMillis();
		return (int) (time/1000);
	}
	
	public static Date addMinutes(Date date,int minutes){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MINUTE, minutes);
		return ca.getTime();
	}
	
	public static Date addDay(Date date,int count){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DAY_OF_YEAR, count);
		return ca.getTime();
	}
	
	public static Date getNearest5minuteDate(){
		Date now = getNow();
		String str = toString(now,"mm");
		Integer minute = Integer.parseInt(str);
		int less = 5-minute%5;
		Date returnDate = addMinutes(now,less);
		String returnDateStr = toString(returnDate,"yyyy-MM-dd HH:mm");
		returnDate = toDate(returnDateStr,"yyyy-MM-dd HH:mm");
		return returnDate;
	}
	
	public static Date getTodayEnd(){
		String str = getNowString("yyyy-MM-dd");
		return toDate(str+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
	}
	
	public static boolean isToday(Date date){
		if(date==null)	return false;
		String str = getNowString("yyyyMMdd");
		String strDate = toString(date, "yyyyMMdd");
		return str.equals(strDate);
	}
}
