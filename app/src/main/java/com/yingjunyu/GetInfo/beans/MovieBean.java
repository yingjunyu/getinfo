package com.yingjunyu.GetInfo.beans;

import java.io.Serializable;

/**
 * Description :
 * Author : yingjunyu
 * Email  : yingjunyu@163.com
 * Blog   : https://github.com/yingjunyu
 * Date   : 15/12/22
 */
public class MovieBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title; //电影标题
    private String movieurl; //电影播放地址
    private String moviecontent; //电影简介

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovieurl() {
        return movieurl;
    }

    public void setMovieurl(String movieurl) {
        this.movieurl = movieurl;
    }

    public String getMoviecontent() {
        return moviecontent;
    }

    public void setMoviecontent(String moviecontent) {
        this.moviecontent = moviecontent;
    }
}
