package com.yingjunyu.getinfo.api;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;

/**
 * Created by yingjunyu on 2016/4/27.
 */
public class NewsAsynctaskSDK {
    String httpUrl = "http://apis.baidu.com/songshuxiansheng/news/news";
    String httpArg = "";
    //String jsonResult = request(httpUrl, httpArg);

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

    public JSONArray getNewsJSON(){
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
            Log.i("yingjunyu:信息通", "新闻获取返回错误：" + e.toString());
        }
        return jsonArray;
    }
}


//System.out.println(jsonResult);
