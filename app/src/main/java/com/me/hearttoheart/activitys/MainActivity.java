package com.me.hearttoheart.activitys;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.me.hearttoheart.R;
import com.me.hearttoheart.fragment.ContentFragment;
import com.me.hearttoheart.fragment.LeftmenuFragment;
import com.me.hearttoheart.utils.DensityUtil;
import com.me.hearttoheart.utils.LogUtils;

public class MainActivity extends SlidingFragmentActivity {

    public static final String MAIN_CONTENT_TAG = "main_content_tag";
    public static final String LEFTMENU_TAG = "leftmenu_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.D("logutil打印日志");
        initView();


    }

    private void initView() {
        //获取slideingmenu
        SlidingMenu slidingMenu = getSlidingMenu();
        //设置主页面
        setContentView(R.layout.activity_main);
        //设置左侧菜单
        setBehindContentView(R.layout.activity_leftmenu);
        //设置显示模式:左侧菜单+主页面
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置滑动模式:全屏可滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置主页面占用比例
        slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this, 100));
        initFragment();
    }

    private void initFragment() {
        //1.得到fragmentmanager
        FragmentManager fm=getSupportFragmentManager();
        //2.开启事务
        FragmentTransaction ft=fm.beginTransaction();
        //3.替换
        ft.replace(R.id.fl_main_content,new ContentFragment(), MAIN_CONTENT_TAG);//替换主页
        ft.replace(R.id.fl_leftmenu,new LeftmenuFragment(), LEFTMENU_TAG);//替换左侧菜单
        //4.提交
        ft.commit();



    }


}
