package com.yingjunyu.GetInfo.stock;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonObject;
import com.yingjunyu.GetInfo.R;
import com.yingjunyu.GetInfo.beans.StockBean;
import com.yingjunyu.GetInfo.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Author : yingjunyu
 * Email  : yingjunyu@163.com
 * Blog   : https://github.com/yingjunyu
 * Date   : 2015/12/22
 */
public class StockJsonUtils {
    /**
     * 获取天气信息
     * @param json
     * @return
     */
    public static List<StockBean> getStockInfo(String json) {
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        LogUtils.d("stockinfo", json);
        List<StockBean> list = new ArrayList<StockBean>();
        /*StockBean sbean = new StockBean();
        sbean.setSname("test");
        sbean.setCurprice("1");
        sbean.setCurdot("2");
        sbean.setRate("3");
        list.add(sbean);*/
        if(TextUtils.isEmpty(json)){
            return list;
        }
        try {
            jsonObject = new JSONObject(json);
            String errNum = jsonObject.getString("errNum");
            Log.i("yingjunyu:信息通", "errNum：" + errNum);
            if (errNum.equals("0")) {
                Log.i("yingjunyu:信息通", "本次获取内容：" + json);
                /*jsonArray = jsonObject.getJSONObject("retData").getJSONObject("market").getJSONArray("shanghai");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        StockBean stockBean = getStockBeanFromJson(jsonArray);
                        list.add(stockBean);
                    }*/
                list.add(getStockBeanFromJson(jsonObject
                        .getJSONObject("retData")
                        .getJSONObject("market")
                        .getJSONObject("shanghai")));
            }
        }catch(Exception e){
            Log.i("yingjunyu:信息通", "新闻获取返回错误：" + e.toString());
        }
        return list;
    }

    private static StockBean getStockBeanFromJson(JSONObject jsb) {

        /*
        try {
            String sname = jsonArray.get(0).toString();
            String curdot = jsonArray.get(1).toString();
            String curprice = jsonArray.get(2).toString();
            String rate = jsonArray.get(3).toString();

            StockBean stockBean = new StockBean();

            stockBean.setSname(sname);
            stockBean.setCurdot(curdot);
            stockBean.setCurprice(curprice);
            stockBean.setRate(rate);
            return stockBean;
        }catch(Exception e){
            return null;
        }
        */
        try{
            String sname = jsb.getString("name");
            String curdot = jsb.getString("curdot");
            String curprice = jsb.getString("curprice");
            String rate = jsb.getString("rate"+"%");

            StockBean stockBean = new StockBean();

            stockBean.setSname(sname);
            stockBean.setCurdot(curdot);
            stockBean.setCurprice(curprice);
            stockBean.setRate(rate);
            return stockBean;
        }catch(Exception e){
            return null;
        }

    }
}
