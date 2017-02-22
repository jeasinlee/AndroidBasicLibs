package cn.wwah.common.image;

/**
 * Created by Jixiang_Li on 2017/2/6.
 */

import android.graphics.BitmapRegionDecoder;

import java.io.IOException;
import java.io.InputStream;

public class InputstreamBitmapDecoderFactory implements BitmapDecoderFactory {
    private InputStream inputStream;

    public InputstreamBitmapDecoderFactory(InputStream inputStream) {
        super();
        this.inputStream = inputStream;
    }

    @Override
    public BitmapRegionDecoder made() throws IOException {
        return BitmapRegionDecoder.newInstance(inputStream, false);
    }

}
