package cn.wwah.common.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import cn.wwah.common.banner.transformer.AccordionTransformer;
import cn.wwah.common.banner.transformer.BackgroundToForegroundTransformer;
import cn.wwah.common.banner.transformer.CubeInTransformer;
import cn.wwah.common.banner.transformer.CubeOutTransformer;
import cn.wwah.common.banner.transformer.DefaultTransformer;
import cn.wwah.common.banner.transformer.DepthPageTransformer;
import cn.wwah.common.banner.transformer.FlipHorizontalTransformer;
import cn.wwah.common.banner.transformer.FlipVerticalTransformer;
import cn.wwah.common.banner.transformer.ForegroundToBackgroundTransformer;
import cn.wwah.common.banner.transformer.RotateDownTransformer;
import cn.wwah.common.banner.transformer.RotateUpTransformer;
import cn.wwah.common.banner.transformer.ScaleInOutTransformer;
import cn.wwah.common.banner.transformer.StackTransformer;
import cn.wwah.common.banner.transformer.TabletTransformer;
import cn.wwah.common.banner.transformer.ZoomInTransformer;
import cn.wwah.common.banner.transformer.ZoomOutSlideTransformer;
import cn.wwah.common.banner.transformer.ZoomOutTranformer;

public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
