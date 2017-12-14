package com.studylbn.www.loopbanner.loader;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

/**
 * Created by LiuBin on 2017/12/14 10:47.
 */

public interface ImageLoaderInterface<T extends View> extends Serializable {
    void displayImage(Context context, Object path, T imageView);

    T createImageView(Context context);
}
