package com.yingjunyu.getinfo.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.baidu.apistore.sdk.ApiStoreSDK;
import com.yingjunyu.getinfo.db.WeatherDB;
import com.yingjunyu.getinfo.db.CityWeather;
import com.yingjunyu.getinfo.api.WeatherAsynctaskSDK;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;

import com.yingjunyu.getinfo.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ui_impFragment extends Fragment {

    //weather_detail.xml
    private TextView txt_weather_detail_city;              //城市（可选择）
    private TextView txt_weather_detail_date;              //日期（可选择）
    private TextView txt_weather_detail_station;           //天气状况（如：晴天）
    private TextView txt_weather_detail_temperature;       //温度
    private TextView txt_weather_detail_windDir;           //风向
    private TextView txt_weather_detail_windSc;            //风力
    private TextView txt_weather_detail_week;              //星期（如：周五）
    private TextView txt_weather_detail_pm;                //PM2.5
    private TextView txt_weather_detail_airquality;        //空气质量
    private ImageView image_weather_detail_icon;           //天气状况图片

    //weather_day.xml
    //weather_day01
    private TextView txt_weather_day_week01;
    private TextView txt_weather_day_date01;
    private TextView txt_weather_day_temperature01;
    private TextView txt_weather_day_station01;
    private TextView txt_weather_day_windDir01;
    private TextView txt_weather_day_windSc01;
    private ImageView image_weather_day_icon01;

    //weather_day02
    private TextView txt_weather_day_week02;
    private TextView txt_weather_day_date02;
    private TextView txt_weather_day_temperature02;
    private TextView txt_weather_day_station02;
    private TextView txt_weather_day_windDir02;
    private TextView txt_weather_day_windSc02;
    private ImageView image_weather_day_icon02;

    //weather_day03
    private TextView txt_weather_day_week03;
    private TextView txt_weather_day_date03;
    private TextView txt_weather_day_temperature03;
    private TextView txt_weather_day_station03;
    private TextView txt_weather_day_windDir03;
    private TextView txt_weather_day_windSc03;
    private ImageView image_weather_day_icon03;

    //查询天气辅助工具类
    private WeatherAsynctaskSDK wasdk;
    //天气列表
    private List<CityWeather> listWeather  = new ArrayList<CityWeather>();

    //WeatherDB
    private WeatherDB weatherDB;

    //默认选择城市
    private String citySelected = "wuhan";

    private SwipeRefreshLayout mSwipeLayout;

    public ui_impFragment() {
        //ApiStoreSDK.init(getContext(),"7f95bf87342d58243e5a5ce0bfda6b1b");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ui_imp, container, false);
        //weather_detail.xml
        txt_weather_detail_city = (TextView) view.findViewById(R.id.txt_weather_detail_city);
        txt_weather_detail_date = (TextView) view.findViewById(R.id.txt_weather_detail_date);
        image_weather_detail_icon = (ImageView) view.findViewById(R.id.image_weather_detail_icon);
        txt_weather_detail_pm = (TextView) view.findViewById(R.id.txt_weather_detail_pm);
        txt_weather_detail_station = (TextView) view.findViewById(R.id.txt_weather_detail_station);
        txt_weather_detail_temperature = (TextView) view.findViewById(R.id.txt_weather_detail_temperature);
        txt_weather_detail_week = (TextView) view.findViewById(R.id.txt_weather_detail_week);
        txt_weather_detail_windDir = (TextView) view.findViewById(R.id.txt_weather_detail_windDir);
        txt_weather_detail_windSc = (TextView) view.findViewById(R.id.txt_weather_detail_windSc);
        txt_weather_detail_airquality = (TextView) view.findViewById(R.id.txt_weather_detail_airquality);

        //txt_weather_detail_date.setOnClickListener(getActivity());
        //txt_weather_detail_city.setOnClickListener(this);

        //weather_day.xml
        //weather_day_01
        LinearLayout layoutWeather01 = (LinearLayout) view.findViewById(R.id.weather_day01);
        txt_weather_day_date01 = (TextView) layoutWeather01.findViewById(R.id.txt_weather_day_date);
        txt_weather_day_week01 = (TextView) layoutWeather01.findViewById(R.id.txt_weather_day_week);
        image_weather_day_icon01 = (ImageView) layoutWeather01.findViewById(R.id.image_weather_day_icon);
        txt_weather_day_temperature01 = (TextView) layoutWeather01.findViewById(R.id.txt_weather_day_tempature);
        txt_weather_day_week01 = (TextView) layoutWeather01.findViewById(R.id.txt_weather_day_week);
        txt_weather_day_windDir01 = (TextView) layoutWeather01.findViewById(R.id.txt_weather_day_windDir);
        txt_weather_day_windSc01 = (TextView) layoutWeather01.findViewById(R.id.txt_weather_day_windSc);
        txt_weather_day_station01 = (TextView) layoutWeather01.findViewById(R.id.txt_weather_day_station);

        //weather_day02
        LinearLayout layoutWeather02 = (LinearLayout) view.findViewById(R.id.weather_day02);
        txt_weather_day_date02 = (TextView) layoutWeather02.findViewById(R.id.txt_weather_day_date);
        txt_weather_day_week02 = (TextView) layoutWeather02.findViewById(R.id.txt_weather_day_week);
        image_weather_day_icon02 = (ImageView) layoutWeather02.findViewById(R.id.image_weather_day_icon);
        txt_weather_day_temperature02 = (TextView) layoutWeather02.findViewById(R.id.txt_weather_day_tempature);
        txt_weather_day_week02 = (TextView) layoutWeather02.findViewById(R.id.txt_weather_day_week);
        txt_weather_day_windDir02 = (TextView) layoutWeather02.findViewById(R.id.txt_weather_day_windDir);
        txt_weather_day_windSc02 = (TextView) layoutWeather02.findViewById(R.id.txt_weather_day_windSc);
        txt_weather_day_station02 = (TextView) layoutWeather02.findViewById(R.id.txt_weather_day_station);

        //weather_day03
        LinearLayout layoutWeather03 = (LinearLayout) view.findViewById(R.id.weather_day03);
        txt_weather_day_date03 = (TextView) layoutWeather03.findViewById(R.id.txt_weather_day_date);
        txt_weather_day_week03 = (TextView) layoutWeather03.findViewById(R.id.txt_weather_day_week);
        image_weather_day_icon03 = (ImageView) layoutWeather03.findViewById(R.id.image_weather_day_icon);
        txt_weather_day_temperature03 = (TextView) layoutWeather03.findViewById(R.id.txt_weather_day_tempature);
        txt_weather_day_week03 = (TextView) layoutWeather03.findViewById(R.id.txt_weather_day_week);
        txt_weather_day_windDir03 = (TextView) layoutWeather03.findViewById(R.id.txt_weather_day_windDir);
        txt_weather_day_windSc03 = (TextView) layoutWeather03.findViewById(R.id.txt_weather_day_windSc);
        txt_weather_day_station03 = (TextView) layoutWeather03.findViewById(R.id.txt_weather_day_station);

        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swip_weather);
        mSwipeLayout.setColorSchemeResources(R.color.swipe_color_1,
                R.color.swipe_color_2, R.color.swipe_color_3, R.color.swipe_color_4);
        mSwipeLayout.setSize(SwipeRefreshLayout.LARGE);;
        mSwipeLayout.setProgressBackgroundColor(R.color.swipe_background_color);

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWeatherInfo();
                mHandler.sendEmptyMessageDelayed(1,2000);
            }
        });

        //getWeatherInfo();
        return view;
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mSwipeLayout.setRefreshing(false);
                    //adapter.notifyDataSetChanged();
                    //swipeRefreshLayout.setEnabled(false);
                    break;
                default:
                    break;
            }
        }

    };
    /**
     * 显示天气信息
     */
    private void initWeatherInfo() {

        //// TODO: 2016/4/4

        if (listWeather.size() > 0) {

            //保证每次都是最新数据
            int count = listWeather.size();
            //weatherToday
            CityWeather weatherToday = listWeather.get(count-4);
            txt_weather_detail_date.setText(weatherToday.getDate());
            txt_weather_detail_week.setText(weatherToday.getWeek());
            txt_weather_detail_station.setText(weatherToday.getStation());
            txt_weather_detail_temperature.setText(weatherToday.getTemperature() + "℃");
            txt_weather_detail_windDir.setText(weatherToday.getWindDir());
            txt_weather_detail_windSc.setText(weatherToday.getWindSc() + "级");
            txt_weather_detail_pm.setText(weatherToday.getPM());
            txt_weather_detail_city.setText("武汉");

            CityWeather weatherForest01 = listWeather.get(count-3);
            txt_weather_day_date01.setText(weatherForest01.getDate());
            txt_weather_day_week01.setText(weatherForest01.getWeek());
            txt_weather_day_station01.setText(weatherForest01.getStation());
            txt_weather_day_temperature01.setText(weatherForest01.getTemperature() + "℃");
            txt_weather_day_windDir01.setText(weatherForest01.getWindDir());
            txt_weather_day_windSc01.setText(weatherForest01.getWindSc() + "级");

            CityWeather weatherForest02 = listWeather.get(count-2);
            txt_weather_day_date02.setText(weatherForest02.getDate());
            txt_weather_day_week02.setText(weatherForest02.getWeek());
            txt_weather_day_station02.setText(weatherForest02.getStation());
            txt_weather_day_temperature02.setText(weatherForest02.getTemperature() + "℃");
            txt_weather_day_windDir02.setText(weatherForest02.getWindDir());
            txt_weather_day_windSc02.setText(weatherForest02.getWindSc() + "级");

            CityWeather weatherForest03 = listWeather.get(count-1);
            txt_weather_day_date03.setText(weatherForest03.getDate());
            txt_weather_day_week03.setText(weatherForest03.getWeek());
            txt_weather_day_station03.setText(weatherForest03.getStation());
            txt_weather_day_temperature03.setText(weatherForest03.getTemperature() + "℃");
            txt_weather_day_windDir03.setText(weatherForest03.getWindDir());
            txt_weather_day_windSc03.setText(weatherForest03.getWindSc() + "级");
        } else {
            //Toast.makeText(this, "空空如也。。。。。", 1).show();
            Toast.makeText(getContext(),"空空如也......",Toast.LENGTH_LONG).show();
        }
    }

    public void getWeatherInfo() {
//        ApiStoreSDK.init(getContext(),"7f95bf87342d58243e5a5ce0bfda6b1b");
        weatherDB = WeatherDB.getInstance(getContext());
        wasdk = new WeatherAsynctaskSDK(weatherDB);

        listWeather = wasdk.requestHttp(citySelected);

        int size = listWeather.size();
        Log.i("yingjunyu:信息通", "WeatherActivity中获取的天气列表长度为：" + size);

        //显示天气信息
        initWeatherInfo();
    }
}
