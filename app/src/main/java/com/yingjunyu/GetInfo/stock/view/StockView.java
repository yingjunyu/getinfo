package com.yingjunyu.GetInfo.stock.view;

import com.yingjunyu.GetInfo.beans.StockBean;

import java.util.List;

/**
 * Description :
 * Author : yingjunyu
 * Email  : yingjunyu@163.com
 * Blog   : https://github.com/yingjunyu
 * Date   : 2015/12/22
 */
public interface StockView {

    void showProgress();
    void hideProgress();
    void showStockLayout();

    void setSname(String sname);
    void setCurdot(String curdot);
    void setCurprice(String curprice);
    void setRate(String rate);

    void setStockData(List<StockBean> lists);

    void showErrorToast(String msg);


}
