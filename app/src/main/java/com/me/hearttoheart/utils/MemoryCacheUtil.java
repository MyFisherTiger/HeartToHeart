package com.me.hearttoheart.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by Administrator on 2017/4/21.
 */
public class MemoryCacheUtil {

    /**
     * 集合
     * LruCache api12之后引入
     * 最近最少使用缓存算法
     */
    private LruCache<String,Bitmap> lruCache;

    public MemoryCacheUtil(){
        //使用了系统分配给应用程序的八分之一内存来作为缓存大小,对于小图特别有效
        int maxSize = (int) (Runtime.getRuntime().maxMemory()/1024/8);
        lruCache = new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
//                return super.sizeOf(key, value);
                return (value.getRowBytes() * value.getHeight())/1024;
            }
        };
    }

    /**
     * 根据url从内存中获取图片
     * @param imageUrl
     * @return
     */
    public Bitmap getBitmapFromUrl(String imageUrl) {
        return lruCache.get(imageUrl);
    }

    /**
     * 根据url保存图片到lruCache集合中
     * @param imageUrl 图片路径
     * @param bitmap 图片
     */
    public void putBitmap(String imageUrl, Bitmap bitmap) {
        lruCache.put(imageUrl,bitmap);
    }
}
