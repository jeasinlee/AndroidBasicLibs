package cn.wwah.common.banner.loader;

import android.content.Context;
import android.widget.ImageView;


public abstract class BannerImageLoader implements ImageLoaderInterface<ImageView> {

    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }

}
