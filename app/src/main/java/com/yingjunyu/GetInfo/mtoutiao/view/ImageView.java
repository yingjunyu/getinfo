package com.yingjunyu.GetInfo.mtoutiao.view;

import com.yingjunyu.GetInfo.beans.ImageBean;

import java.util.List;

/**
 * Description :
 * Author : yingjunyu
 * Email  : yingjunyu@163.com
 * Blog   : https://github.com/yingjunyu
 * Date   : 15/12/22
 */
public interface ImageView {
    void addImages(List<ImageBean> list);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg();
}
