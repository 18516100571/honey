package com.honeybeeapp.bean;

import java.io.Serializable;

/**
 * Created by yyszsq on 2018/3/1.
 * 货物实体类
 */

public class GoodsInfo  {
    private String name;
    private String qishu;
    private String time;
    private String history;
    private String zoushi;
    private String zhuanjia;
    private String zhongjiang;



    public GoodsInfo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQishu() {
        return qishu;
    }

    public void setQishu(String qishu) {
        this.qishu = qishu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getZoushi() {
        return zoushi;
    }

    public void setZoushi(String zoushi) {
        this.zoushi = zoushi;
    }

    public String getZhuanjia() {
        return zhuanjia;
    }

    public void setZhuanjia(String zhuanjia) {
        this.zhuanjia = zhuanjia;
    }

    public String getZhongjiang() {
        return zhongjiang;
    }

    public void setZhongjiang(String zhongjiang) {
        this.zhongjiang = zhongjiang;
    }
}
