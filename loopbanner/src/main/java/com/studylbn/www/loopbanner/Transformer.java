package com.studylbn.www.loopbanner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.studylbn.www.loopbanner.transformer.AccordionTransformer;
import com.studylbn.www.loopbanner.transformer.BackgroundToForegroundTransformer;
import com.studylbn.www.loopbanner.transformer.CubeInTransformer;
import com.studylbn.www.loopbanner.transformer.CubeOutTransformer;
import com.studylbn.www.loopbanner.transformer.DefaultTransformer;
import com.studylbn.www.loopbanner.transformer.DepthPageTransformer;
import com.studylbn.www.loopbanner.transformer.FlipHorizontalTransformer;
import com.studylbn.www.loopbanner.transformer.FlipVerticalTransformer;
import com.studylbn.www.loopbanner.transformer.ForegroundToBackgroundTransformer;
import com.studylbn.www.loopbanner.transformer.RotateDownTransformer;
import com.studylbn.www.loopbanner.transformer.RotateUpTransformer;
import com.studylbn.www.loopbanner.transformer.ScaleInOutTransformer;
import com.studylbn.www.loopbanner.transformer.StackTransformer;
import com.studylbn.www.loopbanner.transformer.TabletTransformer;
import com.studylbn.www.loopbanner.transformer.ZoomInTransformer;
import com.studylbn.www.loopbanner.transformer.ZoomOutSlideTransformer;
import com.studylbn.www.loopbanner.transformer.ZoomOutTranformer;


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
