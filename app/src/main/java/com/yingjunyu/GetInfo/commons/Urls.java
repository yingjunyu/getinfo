package com.yingjunyu.GetInfo.commons;

/**
 * Description : 接口API的URL
 * Author : yingjunyu
 * Email  : yingjunyu@163.com
 * Blog   : https://github.com/yingjunyu
 * Date   : 16/08/01
 */
public class Urls {

    //http://c.m.163.com/nc/article/headline/T1348647909107/0-5.html  头条

    public static final int PAZE_SIZE = 20;

    public static final String HOST = "http://c.m.163.com/";
    public static final String END_URL = "-" + PAZE_SIZE + ".html";
    public static final String END_DETAIL_URL = "/full.html";
    // 头条
    public static final String TOP_URL = HOST + "nc/article/headline/";
    public static final String TOP_ID = "T1348647909107";
    // 新闻详情
    public static final String NEW_DETAIL = HOST + "nc/article/";

    public static final String COMMON_URL = HOST + "nc/article/list/";

    // 汽车
    public static final String CAR_ID = "T1348654060988";
    // 笑话
    public static final String JOKE_ID = "T1350383429665";
    // nba
    public static final String NBA_ID = "T1348649145984";

    // 图片
    public static final String IMAGES_URL = "http://api.laifudao.com/open/tupian.json";

    // 天气预报url
    public static final String WEATHER = "http://wthrcdn.etouch.cn/weather_mini?city=";

    //百度定位
    public static final String INTERFACE_LOCATION = "http://api.map.baidu.com/geocoder";

    //新浪新闻url
    public static final String MSINA_URL = "http://sina.cn/?from=sinacom";

    //股票信息网址，百度api
    public static final String STOCK_URL = "http://apis.baidu.com/apistore/stockservice/hkstock?" +
            "apikey=c52f95571f53d3b2ef238c44ed211db6" +
            "stockid=00001&list=1";

}
