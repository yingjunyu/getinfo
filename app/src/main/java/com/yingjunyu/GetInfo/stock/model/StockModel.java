package com.yingjunyu.GetInfo.stock.model;

import android.content.Context;

import com.yingjunyu.GetInfo.beans.StockBean;

import java.util.List;

/**
 * Description :
 * Author : yingjunyu
 * Email  : yingjunyu@163.com
 * Blog   : https://github.com/yingjunyu
 * Date   : 2015/12/22
 */
public interface StockModel {

    //void loadWeatherData(String cityName, StockModelImpl.LoadWeatherListener listener);

    //void loadLocation(Context context, StockModelImpl.LoadLocationListener listener);

    List<StockBean> loadStokinfo();

}
