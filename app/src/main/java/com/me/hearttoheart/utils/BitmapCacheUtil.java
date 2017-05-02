package com.me.hearttoheart.utils;

import android.graphics.Bitmap;
import android.os.Handler;

/**
 * Created by Administrator on 2017/4/21.
 */
public class BitmapCacheUtil {
    /**
     * 网络缓存工具类
     */
    private NetCacheUtil netCacheUtil;

    /**
     * 本地缓存工具类
     */

    private LocalCacheUtil localCacheUtil;

    /**
     内存缓存工具类
     */
    private MemoryCacheUtil memoryCacheUtil;

    public BitmapCacheUtil(Handler handler) {
        memoryCacheUtil = new MemoryCacheUtil();
        localCacheUtil = new LocalCacheUtil(memoryCacheUtil);
        netCacheUtil = new NetCacheUtil(handler,localCacheUtil,memoryCacheUtil);

    }


    /**
     * 三级缓存设计步骤：
     *   * 从内存中取图片
     *   * 从本地文件中取图片
     *        向内存中保持一份
     *   * 请求网络图片，获取图片，显示到控件上,Hanlder,postion
     *      * 向内存存一份
     *      * 向本地文件中存一份
     *
     * @param imageUrl
     * @param position
     * @return
     */
    public Bitmap getBitmap(String imageUrl, int position) {
        //1.从内存中取图片
        if (memoryCacheUtil != null) {
            Bitmap bitmap = memoryCacheUtil.getBitmapFromUrl(imageUrl);
            if (bitmap != null) {
                LogUtils.E("内存加载图片成功=="+position);
                return bitmap;
            }
        }

        //2.从本地文件中取图片
        if (localCacheUtil != null) {
            Bitmap bitmap = localCacheUtil.getBitmapFromUrl(imageUrl);
            if (bitmap != null) {
                LogUtils.E("本地加载图片成功=="+position);
                return bitmap;
            }
        }

        //3.请求网络图片
        netCacheUtil.getBitmapFomNet(imageUrl, position);
        return null;
    }
}
