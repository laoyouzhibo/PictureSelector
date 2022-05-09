package com.luck.picture.lib.listener;

import com.luck.picture.lib.entity.LocalMedia;
import java.util.List;

/**
 * date:2022/5/9   time: 5:24 下午
 * author zy
 * Have a nice day :)
 **/
public interface OnPictureCompressListener {
    void onSuccess(List<LocalMedia> result);
    void onFailed(Throwable t);
}
