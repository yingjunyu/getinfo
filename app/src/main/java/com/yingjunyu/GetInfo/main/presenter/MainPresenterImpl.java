package com.yingjunyu.GetInfo.main.presenter;

import com.yingjunyu.GetInfo.main.view.MainView;
import com.yingjunyu.GetInfo.R;
/**
 * Description :
 * Author : yingjunyu
 * Email  : yingjunyu@163.com
 * Blog   : https://github.com/yingjunyu
 * Date   : 16/08/01
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    public MainPresenterImpl(MainView mainView) {
        this.mMainView = mainView;
    }

    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.navigation_item_news:
                mMainView.switch2News();
                break;
            case R.id.navigation_item_images:
                mMainView.switch2Images();
                break;
            case R.id.navigation_item_weather:
                mMainView.switch2Weather();
                break;
            case R.id.navigation_item_about:
                mMainView.switch2About();
                break;
            case R.id.navigation_item_movie:
                mMainView.switch2Movie();;
                break;
            default:
                mMainView.switch2News();
                break;
        }
    }
}
