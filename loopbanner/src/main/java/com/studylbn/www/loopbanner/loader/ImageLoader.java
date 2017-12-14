package com.studylbn.www.loopbanner.loader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by LiuBin on 2017/12/14 10:53.
 */

public abstract class ImageLoader implements ImageLoaderInterface<ImageView> {

    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }

}
