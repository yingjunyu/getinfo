package com.yingjunyu.GetInfo.stock.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.os.StrictMode;

import com.yingjunyu.GetInfo.beans.StockBean;
import com.yingjunyu.GetInfo.commons.Urls;
import com.yingjunyu.GetInfo.utils.LogUtils;
import com.yingjunyu.GetInfo.utils.OkHttpUtils;
import com.yingjunyu.GetInfo.stock.StockJsonUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description :
 * Author : yingjunyu
 * Email  : yingjunyu@163.com
 * Blog   : https://github.com/yingjunyu
 * Date   : 2015/12/22
 */
public class StockModelImpl implements StockModel {

    private static final String TAG = "StockModelImpl";

    private String httpUrl = "http://apis.baidu.com/apistore/stockservice/hkstock";
    private String httpArg = "stockid=00168&list=1";

    @Override
    public List<StockBean> loadStokinfo(){
        //用okhttp实现，暂时未成功，改用其他原始办法
        /*
        try {
            String url = Urls.STOCK_URL;
            Log.d("net111", url);
            //OkHttpUtils.post(url, OkHttpUtils.ResultCallback<String> callback);
            OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>() {
                @Override
                public void onSuccess(String response) {
                    List<StockBean> lists = StockJsonUtils.getStockInfo(response);
                    listener.onSuccess(lists);
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure("load stock data failure.", e);
                }
            };
            OkHttpUtils.get(url, callback);
        } catch (Exception e) {
            LogUtils.e(TAG, "some error.", e);
        }
        */
        return StockJsonUtils.getStockInfo(request(httpUrl, httpArg));
    }

    /*public interface LoadStockListener {
        void onSuccess(List<StockBean> list);
        void onFailure(String msg, Exception e);
    }
    */

    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "c52f95571f53d3b2ef238c44ed211db6");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
    public JSONArray getStockJSON(){
        String jsonResult = request(httpUrl, httpArg);
        JSONArray jsonArray = null;
        //JSONArray jsonArray1 = null;
        try{
            JSONObject jsonObject = new JSONObject(jsonResult);
            String errNum = jsonObject.getString("errNum");
            Log.i("yingjunyu:信息通", "errNum：" + errNum);
            if(errNum.equals("0")){
                Log.i("yingjunyu:信息通", "本次获取内容：" + jsonResult);
                jsonArray = jsonObject.getJSONArray("retData");
            }else{
                jsonArray = null;
            }
        }catch (Exception e){
            Log.i("yingjunyu:信息通", "股票信息获取返回错误：" + e.toString());
        }
        return jsonArray;
    }
    */
}
