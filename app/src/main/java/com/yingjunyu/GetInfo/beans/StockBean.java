package com.yingjunyu.GetInfo.beans;

/**
 * Created by yingjunyu on 2016/8/11.
 */
public class StockBean {

    private String sname; //指数名称
    private String curdot; //当前指数
    private String curprice; //涨跌指数
    private String rate; //涨跌百分比

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getCurdot() {
        return curdot;
    }

    public void setCurdot(String curdot) {
        this.curdot = curdot;
    }

    public String getCurprice() {
        return curprice;
    }

    public void setCurprice(String curprice) {
        this.curprice = curprice;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
