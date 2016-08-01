package com.yingjunyu.GetInfo.images.model;

/**
 * Description :
 * Author : yingjunyu
 * Email  : yingjunyu@163.com
 * Blog   : https://github.com/yingjunyu
 * Date   : 15/12/22
 */
public interface ImageModel {
    void loadImageList(ImageModelImpl.OnLoadImageListListener listener);
}
