package com.yingjunyu.GetInfo.mtoutiao.presenter;

import com.yingjunyu.GetInfo.beans.ImageBean;
import com.yingjunyu.GetInfo.images.model.ImageModel;
import com.yingjunyu.GetInfo.images.model.ImageModelImpl;
import com.yingjunyu.GetInfo.images.view.ImageView;

import java.util.List;

/**
 * Description :
 * Author : yingjunyu
 * Email  : yingjunyu@163.com
 * Blog   : https://github.com/yingjunyu
 * Date   : 15/12/22
 */
public class ImagePresenterImpl implements ImagePresenter, ImageModelImpl.OnLoadImageListListener {

    private ImageModel mImageModel;
    private ImageView mImageView;

    public ImagePresenterImpl(ImageView imageView) {
        this.mImageModel = new ImageModelImpl();
        this.mImageView = imageView;
    }

    @Override
    public void loadImageList() {
        mImageView.showProgress();
        mImageModel.loadImageList(this);
    }

    @Override
    public void onSuccess(List<ImageBean> list) {
        mImageView.addImages(list);
        mImageView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mImageView.hideProgress();
        mImageView.showLoadFailMsg();
    }
}
