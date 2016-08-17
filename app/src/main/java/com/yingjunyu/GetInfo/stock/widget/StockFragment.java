package com.yingjunyu.GetInfo.stock.widget;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yingjunyu.GetInfo.R;
import com.yingjunyu.GetInfo.beans.StockBean;
import com.yingjunyu.GetInfo.stock.presenter.StockPresenter;
import com.yingjunyu.GetInfo.stock.presenter.StockPresenterImpl;
import com.yingjunyu.GetInfo.stock.view.StockView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Author : yingjunyu
 * Email  : yingjunyu@163.com
 * Blog   : https://github.com/yingjunyu
 * Date   : 15/12/21
 */
public class StockFragment extends Fragment implements StockView {

    private StockPresenter mStockPresenter;
    private TextView mName;
    //private ImageView mTodayWeatherImage;
    private TextView mCurdot;
    private TextView mCurprice;
    private TextView mRate;
    //private TextView mCityTV;
    private ProgressBar mProgressBar;
    private LinearLayout mStockLayout;
    private LinearLayout mStockContentLayout;
    private FrameLayout mRootLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStockPresenter = new StockPresenterImpl(getActivity().getApplication(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, null);
        mName = (TextView) view.findViewById(R.id.today);
        //mTodayWeatherImage = (ImageView) view.findViewById(R.id.weatherImage);
        mCurdot = (TextView) view.findViewById(R.id.weatherTemp);
        mCurprice = (TextView) view.findViewById(R.id.wind);
        mRate = (TextView) view.findViewById(R.id.weather);
        //mCityTV = (TextView)view.findViewById(R.id.city);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        mStockLayout = (LinearLayout) view.findViewById(R.id.weather_layout);
        mStockContentLayout = (LinearLayout) view.findViewById(R.id.weather_content);
        mRootLayout = (FrameLayout) view.findViewById(R.id.root_layout);
        mStockPresenter.loadStockData();
        return view;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showStockLayout() {
        mStockLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setSname(String sname) {
        mName.setText(sname);
    }

    @Override
    public void setCurdot(String curdot) { mCurdot.setText(curdot); }

    @Override
    public void setCurprice(String curprice) {
        mCurprice.setText(curprice);
    }

    @Override
    public void setRate(String rate) {
        mRate.setText(rate);
    }


    /*
    @Override
    public void setWeather(String weather) {
        mTodayWeatherTV.setText(weather);
    }

    @Override
    public void setWeatherImage(int res) {
        mTodayWeatherImage.setImageResource(res);
    }
    */

    @Override
    public void setStockData(List<StockBean> lists) {
        List<View> adapterList = new ArrayList<View>();
        for (StockBean stockBean : lists) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_weather, null, false);
            TextView mName = (TextView) view.findViewById(R.id.date);
            //ImageView todayWeatherImage = (ImageView) view.findViewById(R.id.weatherImage);
            TextView mCurdot = (TextView) view.findViewById(R.id.weatherTemp);
            TextView mCurprice = (TextView) view.findViewById(R.id.wind);
            TextView mRate = (TextView) view.findViewById(R.id.weather);

            mName.setText(stockBean.getSname());
            mCurdot.setText(stockBean.getCurdot());
            mCurprice.setText(stockBean.getCurprice());
            mRate.setText(stockBean.getRate());
            //todayWeatherImage.setImageResource(weatherBean.getImageRes());
            //mWeatherContentLayout.addView(view);
            adapterList.add(view);
        }
    }

    @Override
    public void showErrorToast(String msg) {
        Snackbar.make(getActivity().findViewById(R.id.drawer_layout), msg, Snackbar.LENGTH_SHORT).show();
    }
}
