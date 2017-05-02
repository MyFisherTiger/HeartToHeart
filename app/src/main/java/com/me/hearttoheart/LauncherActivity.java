package com.me.hearttoheart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import com.hyphenate.chat.EMClient;
import com.me.hearttoheart.activitys.GuideActivity;
import com.me.hearttoheart.activitys.MainActivity;
import com.me.hearttoheart.im.controller.activity.LoginAcitivity;
import com.me.hearttoheart.im.model.Model;
import com.me.hearttoheart.im.model.bean.UserInfo;
import com.me.hearttoheart.utils.CacheUtil;

public class LauncherActivity extends Activity {

    public static final String START_MAIN = "start_main";
    private View rl_root;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            // 如果当前activity已经退出，那么我就不处理handler中的消息
            if(isFinishing()) {
                return;
            }

            // 判断进入主页面还是登录页面
            toMainOrLogin();
        }
    };

    // 判断进入主页面还是登录页面
    private void toMainOrLogin() {
//        new Thread(){
//            public void run(){
//
//            }
//        }.start();

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                // 判断当前账号是否已经登录过
                if(EMClient.getInstance().isLoggedInBefore()) {// 登录过

                    // 获取到当前登录用户的信息
                    UserInfo account = Model.getInstance().getUserAccountDao().getAccountByHxId(EMClient.getInstance().getCurrentUser());

                    if(account == null) {
                        // 跳转到登录页面
                        Intent intent = new Intent(LauncherActivity.this, LoginAcitivity.class);
                        startActivity(intent);
                    }else {
                        // 登录成功后的方法
                        Model.getInstance().loginSuccess(account);

                        // 跳转到主页面
                        Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }else {// 没登录过
                    // 跳转到登录页面
                    Intent intent = new Intent(LauncherActivity.this, LoginAcitivity.class);
                    startActivity(intent);
                }
                // 结束当前页面
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setMyAnimation();
    }
    //初始化界面
    public void initView(){
        setContentView(R.layout.activity_launcher);
        rl_root=(View)findViewById(R.id.rl_launcher_root);
    }
    //渐变动画，放缩动画，旋转动画
    public void setMyAnimation(){
        //渐变
        AlphaAnimation aa=new AlphaAnimation(0,1);
        aa.setDuration(500);//持续播放时间
        aa.setFillAfter(true);
        //放缩
        ScaleAnimation sa=new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,
                ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(500);
        sa.setFillAfter(true);
        //旋转
        RotateAnimation ra=new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,
                RotateAnimation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(500);
        ra.setFillAfter(true);
        //添加这三种动画特效，无先后顺序，同时播放
        AnimationSet as=new AnimationSet(false);
        as.addAnimation(aa);
        as.addAnimation(sa);
        as.addAnimation(ra);
        //这里设置的时间可覆盖之前设置的，以这里的为准
        as.setDuration(2000);
        rl_root.startAnimation(as);
        //采用匿名内部类设置动画播放监听器
        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boolean isStartMain= CacheUtil.getState(LauncherActivity.this, START_MAIN);
                if(isStartMain==true){
                    // 发送消息，跳转到判断进入主页面还是登录页面的逻辑
                    // 发送2s钟的延时消息
                    handler.sendMessageDelayed(Message.obtain(),2000);

                }
                else{
                    //进入引导页面
                    Intent intent=new Intent(LauncherActivity.this, GuideActivity.class);
                    startActivity(intent);
                    finish();

                }


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁消息
        handler.removeCallbacksAndMessages(null);
    }

}
