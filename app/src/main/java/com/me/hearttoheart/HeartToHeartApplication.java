package com.me.hearttoheart;

import android.app.Application;
import android.content.Context;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;
import com.me.hearttoheart.im.model.Model;
import com.me.hearttoheart.utils.LogUtils;

import org.xutils.common.util.LogUtil;
import org.xutils.x;

/**
 * Created by Administrator on 2017/4/5.
 * 代表整个软件
 */
public class HeartToHeartApplication extends Application{
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.setDebug(true);
        x.Ext.init(this);

        // 初始化EaseUI
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);// 设置需要同意后才能接受邀请
        options.setAutoAcceptGroupInvitation(false);// 设置需要同意后才能接受群邀请

        EaseUI.getInstance().init(this,options);

        // 初始化数据模型层类
        Model.getInstance().init(this);

        // 初始化全局上下文对象
        mContext = this;
    }
    // 获取全局上下文对象
    public static Context getGlobalApplication(){
        return mContext;
    }
}
