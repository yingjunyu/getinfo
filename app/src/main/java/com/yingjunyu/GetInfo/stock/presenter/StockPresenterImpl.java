package com.yingjunyu.GetInfo.stock.presenter;

import android.content.Context;
import android.util.Log;

import com.yingjunyu.GetInfo.beans.StockBean;
import com.yingjunyu.GetInfo.utils.ToolsUtil;
import com.yingjunyu.GetInfo.stock.model.StockModel;
import com.yingjunyu.GetInfo.stock.model.StockModelImpl;
import com.yingjunyu.GetInfo.stock.view.StockView;

import java.util.List;

/**
 * Description :
 * Author : yingjunyu
 * Email  : yingjunyu@163.com
 * Blog   : https://github.com/yingjunyu
 * Date   : 2015/12/22
 */
public class StockPresenterImpl implements StockPresenter{

    private StockView mStockView;
    private StockModel mStockModel;
    private Context mContext;

    public StockPresenterImpl(Context context, StockView stockView) {
        this.mContext = context;
        this.mStockView = stockView;
        mStockModel = new StockModelImpl();
    }

    @Override
    public void loadStockData() {
        mStockView.showProgress();

        if (!ToolsUtil.isNetworkAvailable(mContext)) {
            mStockView.hideProgress();
            mStockView.showErrorToast("无网络连接");
            return;
        }

        getStockInfo(mStockModel.loadStokinfo());
    }

        /*
        StockModelImpl.LoadStockListener listener = new StockModelImpl.LoadStockListener() {
            @Override
            public void onSuccess(List<StockBean> list) {
                //定位成功，获取定位城市天气预报
                //mStockView.setCity(cityName);
                mStockModel.loadStokinfo();
            }

            @Override
            public void onFailure(String msg, Exception e) {
                mStockView.showErrorToast("获取股票信息失败");
                //mStockView.setCity("深圳");
                //mWeatherModel.loadWeatherData("深圳", StockPresenterImpl.this);
            }
        };
        //获取定位信息
        //mWeatherModel.loadLocation(mContext, listener);
        mStockModel.loadStokinfo();
    }

    @Override
    public void onSuccess(List<StockBean> list) {
        if(list != null && list.size() > 0) {
            StockBean todayStock = list.remove(0);
            mStockView.setSname(todayStock.getSname());
            mStockView.setCurdot(todayStock.getCurdot());
            mStockView.setCurprice(todayStock.getCurprice());
            mStockView.setRate(todayStock.getRate());
            //mWeatherView.setWeatherImage(todayWeather.getImageRes());
        }
        mStockView.setStockData(list);
        mStockView.hideProgress();
        mStockView.showStockLayout();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mStockView.hideProgress();
        mStockView.showErrorToast("获取股票信息失败");
    }
    */

    public void getStockInfo(List<StockBean> list){
        try{
            if(list != null && list.size() > 0) {
                StockBean todayStock = list.remove(0);
                mStockView.setSname(todayStock.getSname());
                mStockView.setCurdot(todayStock.getCurdot());
                mStockView.setCurprice(todayStock.getCurprice());
                mStockView.setRate(todayStock.getRate());
                //mWeatherView.setWeatherImage(todayWeather.getImageRes());
            }
            mStockView.setStockData(list);
            mStockView.hideProgress();
            mStockView.showStockLayout();
        }catch(Exception e){
            Log.d("getStockInfo","error:" + e.toString());
        }
    }
}
