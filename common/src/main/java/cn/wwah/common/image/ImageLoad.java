package cn.wwah.common.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import cn.wwah.common.R;

/**
 * 图片加载工具
 * author：jeasinlee on 2016/6/14 14:02
 */
public class ImageLoad {
    Context mContext;
    static ImageLoad mImageLoad;
    private static RequestManager requestManager;

    private ImageLoad(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 获取单列
     *
     * @param mContext
     * @return
     */
    public static ImageLoad getImageLoad(Context mContext) {
        if (mImageLoad == null) {
            mImageLoad = new ImageLoad(mContext);
            requestManager = Glide.with(mContext);
        }
        return mImageLoad;
    }

    /**
     * 加载图片
     *
     * @param url       图片地址
     * @param imageView 图片
     */
    public void loadImg(String url, ImageView imageView) {
        requestManager.load(url).error(R.drawable.pic_default).placeholder(R.drawable.loding).into(imageView);
    }

    /**
     * 加载图片
     *
     * @param error       错误图片
     * @param placeholder 占位图片
     * @param url         图片地址
     * @param imageView   图片
     */
    public void loadImg(int error, int placeholder, String url, ImageView imageView) {
        requestManager.load(url).error(R.drawable.pic_default).placeholder(R.drawable.loding).into(imageView);
    }

}
