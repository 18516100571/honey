package com.honeybeeapp.utils;


import android.text.format.DateFormat;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Avazu Holding on 2017/11/23.
 * 日志打印类
 * 敏感信息使用d打印
 */

public class L {

    //	private static Boolean SWITCH_HIGH = true, SWITCH_LOW = true; // 日志文件总开关
//	private static Boolean LOG_WRITE_TO_FILE = true;// 日志写入文件开关
    public 	static final String TAG 			= "avazu";
    public 	static final String LOG_FILENAME 	= "_logcat.txt";// 本类输出的日志文件名称
    private static final String FORMAT_LOG 		= "yyyy-MM-dd HH:mm:ss";
    public  static final String FORMAT_LOG_FILE = "yyyy-MM-dd";
    private static final int 	LOG_SAVE_DAYS 	= 2;// sd卡中日志文件的最多保存天数
    public  static final char	LOG_TYPE_D		= 'd';
    public  static final char	LOG_TYPE_I		= 'i';
    public  static final char	LOG_TYPE_W		= 'w';
    public  static final char	LOG_TYPE_E		= 'e';
    public  static final char	LOG_TYPE_V		= 'v';
    //	private static char			mLogType 		= LOG_TYPE_V;// 输入日志类型，w代表只输出告警信息等，v代表输出所有信息
    private static boolean		mWriteToFile	= true;// 是否写入文件中
    private static boolean		mIsDebug		= true;// 是否为DEBUG模式，为false时只输出ERROR级别信息(其他信息不输出)



    /**
     * 初始化Logcat日志，默认输出全部信息并写入文件
     * @param isDebug true-输出全部信息，false-时只输出ERROR级别信息(其他信息不输出)
     * @param writeToFile true-写入文件，false-不写入文件
     */
    public static void initLogcatAndWriteToFile(boolean isDebug, boolean writeToFile) {
        mIsDebug = isDebug;
        mWriteToFile = writeToFile;
    }

    public static void d(Object msg) {// 调试信息
        log(TAG, msg.toString(), LOG_TYPE_D);
    }

    public static void d(String tag, Object msg) {// 调试信息
        log(tag, msg.toString(), LOG_TYPE_D);
    }

    public static void i(Object msg) {//
        log(TAG, msg.toString(), LOG_TYPE_I);
    }

    public static void i(String tag, Object msg) {//
        log(tag, msg.toString(), LOG_TYPE_I);
    }

    public static void w(Object msg) { // 警告信息
        log(TAG, msg.toString(), LOG_TYPE_W);
    }

    public static void w(String tag, Object msg) { // 警告信息
        log(tag, msg.toString(), LOG_TYPE_W);
    }

    public static void w(Throwable tr) { //
        Log.w(TAG, "---------------------------------------------------", tr);
    }

    public static void w(String tag, Throwable tr) { //
        Log.w(tag, "---------------------------------------------------", tr);
    }

    public static void e(Object msg) { // 错误信息
        log(TAG, msg.toString(), LOG_TYPE_E);
    }

    public static void e(String tag, Object msg) { // 错误信息
        log(tag, msg.toString(), LOG_TYPE_E);
    }

    public static void e(Throwable tr) { //
        Log.e(TAG, "---------------------------------------------------", tr);
    }

    public static void e(String tag, Throwable tr) { //
        Log.e(tag, "---------------------------------------------------", tr);
    }

    public static void v(Object msg) {
        log(TAG, msg.toString(), LOG_TYPE_V);
    }

    public static void v(String tag, Object msg) {
        log(tag, msg.toString(), LOG_TYPE_V);
    }

    /**
     * 根据tag, msg和等级，输出日志
     *
     * @param tag
     * @param msg
     * @param level
     * @return void
     * @since v 1.0
     */
    private static void log(String tag, String msg, char level) {
        msg = buildMessage(msg);
        if (mIsDebug) {
            switch (level) {
                case LOG_TYPE_D:
                    Log.d(tag, msg);
                    break;
                case LOG_TYPE_I:
                    Log.i(tag, msg);
                    break;
                case LOG_TYPE_W:
                    Log.w(tag, msg);
                    break;
                case LOG_TYPE_E:
                    Log.e(tag, msg);
                    break;
                case LOG_TYPE_V:
                    Log.v(tag, msg);
                    break;
            }
            if (mWriteToFile) {
                writeLogToFile(String.valueOf(level), tag, msg);
            }
        } else {
            switch (level) {
                case LOG_TYPE_E:
                    Log.e(tag, msg);
                    break;
            }
            if (mWriteToFile) {
                writeLogToFile(String.valueOf(level), tag, msg);
            }
        }
    }



    /**
     * 打开日志文件并写入日志
     *
     * @return
     * **/
    private static void writeLogToFile(String mylogtype, String tag, String text) {// 新建或打开日志文件
        Date nowtime = new Date();
        String needWriteFiel = DateFormat.format(FORMAT_LOG_FILE, nowtime).toString();
        String needWriteMessage = "<" + DateFormat.format(FORMAT_LOG, nowtime) + "> [" + mylogtype + "." + tag + "] " + text;
        File file = new File(F.getLogcatFile().getAbsolutePath(), needWriteFiel + LOG_FILENAME);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter filerWriter = new FileWriter(file, true);// 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(needWriteMessage.replace("\n", "\r\n"));
            bufWriter.write("\r\n\r\n");// windows中换行只认\r\n
            bufWriter.flush();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对日志内容加工成 [pid号]类名，方法名，行数，内容
     *
     * @param msg
     * @return
     */
    private static String buildMessage(String msg) {
        Thread thread = Thread.currentThread();
        StackTraceElement stl = thread.getStackTrace()[5];
        String className = stl.getClassName();
        className = className.substring(className.lastIndexOf(".") + 1);
        return String.format(Locale.US, "[%d] %s.%s(Line:%d)  %s", thread.getId(), className, stl.getMethodName(), stl.getLineNumber(), msg);
    }

    /**
     * 删除制定的日志文件
     * */
    public static void delFile() {// 删除日志文件
        String[] strs = getDates();
        File[] files = new File(F.getLogcatFile().getPath()).listFiles();
        boolean flat = false;
        for (File file : files) {
            flat = false;
            for (String str : strs) {
                if (!file.getAbsolutePath().contains(str)) {
                    flat = true;
                }else{
                    flat = false;//只要含有一个就说明不需要删除了
                    break;
                }
            }
            if(flat && file.exists()){
                file.delete();
            }
        }

    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件名
     * */
    private static String[] getDates() {
        String[] strs = new String[LOG_SAVE_DAYS];
        Date nowtime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowtime);
        for (int i = 0; i < LOG_SAVE_DAYS; i++) {
            now.set(Calendar.DATE, now.get(Calendar.DATE) - i);
            strs[i] = DateFormat.format(FORMAT_LOG_FILE, now.getTime()).toString();
        }
        return strs;
    }





    /**
     * sleep thread
     *
     * @param time
     *            time
     */
    public static void sleep(long time) {
        if (time <= 0)
            return;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }
    }

    public static boolean ismIsDebug() {
        return mIsDebug;
    }

}
