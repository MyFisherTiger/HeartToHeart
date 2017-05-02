package com.me.hearttoheart.utils;

import android.content.Context;

/**
 * Created by Administrator on 2017/4/3.
 * dip与px相互转换工具类
 */
public class DensityUtil {
    /**
     * 根据手机分辨率将dip单位转换为px单位
     * @param context 上下文
     * @param dpValue 相对像素dip
     * @return
     */
    public static int dip2px(Context context,float dpValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

    /**
     *根据手机分辨率将px单位转换为dip单位
     * @param context 上下文
     * @param pxValue 像素
     * @return
     */
    public static int px2dip(Context context,float pxValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue*scale+0.5f);
    }
}
