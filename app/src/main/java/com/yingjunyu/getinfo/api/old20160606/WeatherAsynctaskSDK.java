package com.yingjunyu.getinfo.api.old20160606;

import android.util.Log;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;

import com.yingjunyu.getinfo.db.old20160606.CityWeather;
import com.yingjunyu.getinfo.db.old20160606.WeatherDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by yingjunyu on 2016/4/14.
 */
public class WeatherAsynctaskSDK {

    private String httpUrl = "http://apis.baidu.com/heweather/weather/free";

    private WeatherDB weatherDB;

    public WeatherAsynctaskSDK(WeatherDB weatherDB) {
        this.weatherDB = weatherDB;
    }

    /**
     *
     * @param city       请求的城市
     * @return        天气情况的list结合
     */
    public List<CityWeather> requestHttp(String city) {

        //参数
        Parameters para = new Parameters();
        para.put("city", city);
        Log.i("yingjunyu:信息通", "城市："+city);

        /**
         * 接口的参数含义如下
         ApiStoreSDK.execute(
         String url, 						// 接口地址
         String method, 					// 接口方法
         Parameters params, 			// 接口参数
         ApiCallBack callBack) {		// 接口回调方法
         其中方法为”get”或”post”，推荐使用sdk提供的变量ApiStoreSDK.GET和ApiStoreSDK.POST.
         回调方法在UI线程中执行，可以直接修改UI。
         onError 调用时，statusCode参数值对应如下：
         -1 没有检测到当前网络；
         -3 没有进行初始化;
         -4 传参错误
         其他数值是http状态码，或服务器响应的errNum，请查阅响应字符串responseString

         */
        ApiStoreSDK.execute(httpUrl, ApiStoreSDK.GET, para, new ApiCallBack() {
            @Override
            public void onSuccess(int status, String responseString) {
                Log.i("yingjunyu:信息通", "onSuccess");

                //解析数据，获得天气信息，并保存
                jsonResult(responseString);

            }

            @Override
            public void onComplete() {
                Log.i("yingjunyu:信息通", "onComplete");
            }

            @Override
            public void onError(int status, String responseString, Exception e) {
                Log.i("yingjunyu:信息通", "onError, status: " + status);
                Log.i("yingjunyu:信息通", "errMsg: " + (e == null ? "" : e.getMessage()));
            }

        });

        List<CityWeather> list = weatherDB.loadCityWeather();
        Log.i("yingjunyu:信息通", "list的长度为：" + list.size());

        return list;
    }

    /**
     * 解析返回的json数据
     * @param responseString
     * @return
     */
    private void jsonResult(String responseString) {

        CityWeather weatherToday = new CityWeather();
        try {

            JSONObject jsonObject = new JSONObject(responseString);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather data service 3.0");
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);

            String isOk = jsonObject1.getString("status");

            Log.i("yingjunyu:信息通", "http返回值：" + isOk);

            if (isOk.equals("ok")) {

                //今日天气状况
                weatherToday = todayWeather(jsonObject1);
                //将数据保存到数据表中
                weatherDB.saveCityWeather(weatherToday);

                //未来三天的天气状况
                JSONArray jsonArray1 = jsonObject1.getJSONArray("daily_forecast");
                for (int i = 1; i < 4; i++) {
                    JSONObject jsonObject2 = (JSONObject) jsonArray1.get(i);
                    CityWeather cityWeather = forestWeather(jsonObject2);

                    //将数据保存到数据表中
                    weatherDB.saveCityWeather(cityWeather);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取今天的天气状况
     * @param jsonObject1
     * @return
     * @throws JSONException
     */
    private CityWeather todayWeather(JSONObject jsonObject1) {

        CityWeather weatherToday = new CityWeather();
        try {
            {//天气状况基本信息

                JSONObject jsonBasic = jsonObject1.getJSONObject("basic");

                //城市名称
                String city_name = jsonBasic.getString("city");
                //城市id
                String city_id = jsonBasic.getString("id");

                //更新时间
                JSONObject jsonDate = jsonBasic.getJSONObject("update");
                //当地时间
                String date = jsonDate.getString("loc");

                //周几
                String week = WeekFind(date);

                //将信息保存在CityWeather实例中
                weatherToday.setCityName(city_name);
                weatherToday.setCityID(city_id);
                weatherToday.setDate(date);
                weatherToday.setWeek(week);
            }
            {//实况天气

                JSONObject jsonNow = jsonObject1.getJSONObject("now");

                //天气状况
                JSONObject jsonStation = jsonNow.getJSONObject("cond");
                //天气状况代码
                String sta_code = jsonStation.getString("code");
                //天气状况描述
                String station = jsonStation.getString("txt");

                //温度
                String tempature = jsonNow.getString("tmp");

                //风力风向
                JSONObject jsonWind = jsonNow.getJSONObject("wind");
                //风向
                String windDir = jsonWind.getString("dir");
                //风力
                String windSc = jsonWind.getString("sc");

                weatherToday.setStation(station);
                weatherToday.setStationCode(sta_code);
                weatherToday.setTemperature(tempature);
                weatherToday.setWindDir(windDir);
                weatherToday.setWindSc(windSc);
            }
            {//空气质量，仅限国内部分城市

                JSONObject jsonAir = jsonObject1.getJSONObject("aqi");
                JSONObject jsonCity = jsonAir.getJSONObject("city");
                //PM2.5 1小时平均值(ug/m³)
                String pm = jsonCity.getString("pm25");
                //空气质量类别
                String airQ = jsonCity.getString("qlty");

                weatherToday.setPM(pm);
                weatherToday.setAirQlty(airQ);
                weatherToday.setIsToday(true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherToday;
    }

    /**
     * 获取未来三天的天气状况
     * @param jsonObject2
     * @return
     */
    public CityWeather forestWeather(JSONObject jsonObject2) {

        CityWeather weatherForest = new CityWeather();

        try {

            //日期
            String date = jsonObject2.getString("date");

            //周几
            String week = WeekFind(date);

            //天气状况（这里主要取白天）
            JSONObject jsonStation = jsonObject2.getJSONObject("cond");
            String sta_code = jsonStation.getString("code_d");
            String sta_txt = jsonStation.getString("txt_d");

            //温度
            JSONObject jsonTmp = jsonObject2.getJSONObject("tmp");
            String tmpMax = jsonTmp.getString("max");
            String tmpMin = jsonTmp.getString("min");

            //风力风向
            JSONObject jsonWind = jsonObject2.getJSONObject("wind");
            //风力
            String windSc = jsonWind.getString("sc");
            //风向
            String windDir = jsonWind.getString("dir");

            weatherForest.setIsToday(false);
            weatherForest.setDate(date);
            weatherForest.setWeek(week);
            weatherForest.setStation(sta_txt);
            weatherForest.setStationCode(sta_code);
            weatherForest.setTemperature(tmpMin + "~" + tmpMax);
            weatherForest.setWindSc(windSc);
            weatherForest.setWindDir(windDir);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherForest;
    }

    /**
     * 找出对应的周几
     * @param dateTime
     * @return
     */
    public String WeekFind(String dateTime) {

        String week = "";

        dateTime = dateTime.substring(0,10);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Calendar c = Calendar.getInstance();

        try {

            c.setTime(format.parse(dateTime));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case 1:                                  //周日
                Log.i("yingjunyu:信息通", "今天是周日");
                week = "周日";
                break;
            case 2:                                  //周一
                Log.i("yingjunyu:信息通", "今天是周一" );
                week = "周一";
                break;
            case 3:                                  //周二
                Log.i("yingjunyu:信息通", "今天是周二" );
                week = "周二";
                break;
            case 4:                                  //周三
                Log.i("yingjunyu:信息通", "今天是周三" );
                week = "周三";
                break;
            case 5:                                  //周四
                Log.i("yingjunyu:信息通", "今天是周四" );
                week = "周四";
                break;
            case 6:                                  //周五
                Log.i("yingjunyu:信息通", "今天是周五" );
                week = "周五";
                break;
            case 7:                                  //周六
                Log.i("yingjunyu:信息通", "今天是周六" );
                week = "周六";
                break;
        }
        return week;
    }
}
