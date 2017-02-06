package cn.wwah.common.image;

/**
 * Created by Jixiang_Li on 2017/2/6.
 */

import android.graphics.BitmapRegionDecoder;

import java.io.IOException;

public interface BitmapDecoderFactory {
    BitmapRegionDecoder made() throws IOException;
}