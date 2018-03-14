package com.honeybeeapp.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;

import com.honeybeeapp.bean.GoodsInfo;

/**
 * @version 1.0
 * @Author SunnyCoffee
 * @Date 2014-1-28
 * @Desc 工具类
 */

public class Utils {

    /**
     * 获取当前时间
     */
    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }

    public static String getCurrentTime() {
        return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
    }

    /**
     * 获取当月的第一天
     */
    @SuppressLint("SimpleDateFormat")
    public String getFirstDay_Month(int type) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        if (type == 1) {
            df = new SimpleDateFormat("yyyy年MM月dd日");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date theDate = calendar.getTime();
        return df.format(theDate);
    }

    /**
     * 获取当前日期
     */
    @SuppressLint("SimpleDateFormat")
    public String getCurrentDay_Month(int type) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        if (type == 1) {
            df = new SimpleDateFormat("yyyy年MM月dd日");
        }
        return df.format(new Date());
    }

    public static List getDateList() {
        List<GoodsInfo> list = new ArrayList<GoodsInfo>();
        GoodsInfo goods1 = new GoodsInfo();
        goods1.setName("双色球");
        goods1.setQishu("18028期");
        goods1.setTime("2018-03-13 星期二");
        goods1.setHistory("历史开奖");
        goods1.setZoushi("走势图");
        goods1.setZhuanjia("专家预测");
        goods1.setZhongjiang("中奖查询");
        list.add(goods1);
        GoodsInfo goods2 = new GoodsInfo();
        goods2.setName("七星彩");
        goods2.setQishu("18028期");
        goods2.setTime("2018-03-13 星期二");
        goods2.setHistory("历史开奖");
        goods2.setZoushi("走势图");
        goods2.setZhuanjia("本期热议");
        goods2.setZhongjiang("");
        list.add(goods2);
        GoodsInfo goods3 = new GoodsInfo();
        goods3.setName("超级大乐透");
        goods3.setQishu("18028期");
        goods3.setTime("2018-03-12 星期一");
        goods3.setHistory("历史开奖");
        goods3.setZoushi("走势图");
        goods3.setZhuanjia("专家预测");
        goods3.setZhongjiang("中奖查询");
        list.add(goods3);
        GoodsInfo goods4 = new GoodsInfo();
        goods4.setName("福彩3D");
        goods4.setQishu("2018064期");
        goods4.setTime("2018-03-12 星期一");
        goods4.setHistory("历史开奖");
        goods4.setZoushi("走势图");
        goods4.setZhuanjia("专家预测");
        goods4.setZhongjiang("中奖查询");
        list.add(goods4);
        GoodsInfo goods5 = new GoodsInfo();
        goods5.setName("排列3");
        goods5.setQishu("18064期");
        goods5.setTime("2018-03-12 星期一");
        goods5.setHistory("历史开奖");
        goods5.setZoushi("走势图");
        goods5.setZhuanjia("专家预测");
        goods5.setZhongjiang("");
        list.add(goods5);
        GoodsInfo goods6 = new GoodsInfo();
        goods6.setName("排列5");
        goods6.setQishu("18064期");
        goods6.setTime("2018-03-12 星期一");
        goods6.setHistory("历史开奖");
        goods6.setZoushi("走势图");
        goods6.setZhuanjia("专家预测");
        goods6.setZhongjiang("");
        list.add(goods6);
        GoodsInfo goods7 = new GoodsInfo();
        goods7.setName("七乐彩");
        goods7.setQishu("18028期");
        goods7.setTime("2018-03-12 星期一");
        goods7.setHistory("历史开奖");
        goods7.setZoushi("走势图");
        goods7.setZhuanjia("");
        goods7.setZhongjiang("");
        list.add(goods7);

        return list;
    }
}
