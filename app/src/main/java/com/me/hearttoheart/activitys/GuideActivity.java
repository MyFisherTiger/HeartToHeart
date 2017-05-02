package com.me.hearttoheart.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hyphenate.chat.EMClient;
import com.me.hearttoheart.LauncherActivity;
import com.me.hearttoheart.R;
import com.me.hearttoheart.im.controller.activity.ComuniteActivity;
import com.me.hearttoheart.im.controller.activity.LoginAcitivity;
import com.me.hearttoheart.im.model.Model;
import com.me.hearttoheart.im.model.bean.UserInfo;
import com.me.hearttoheart.utils.CacheUtil;
import com.me.hearttoheart.utils.DensityUtil;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private LinearLayout ll_point_group;
    private ViewPager viewPager;
    private Button btn_start_main;
    private ArrayList<ImageView> imageViews;
    private ImageView red_point;
    private int LEFT_MAX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        btn_start_main = (Button) findViewById(R.id.btn_start_main);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
        red_point=(ImageView) findViewById(R.id.iv_red_point);
        int[] ids = new int[]{
                R.drawable.guide_bg1,
                R.drawable.guide_bg2,
                R.drawable.guide_bg3
        };
        imageViews = new ArrayList<ImageView>();
        for (int i = 0; i < ids.length; i++) {
            //this在最开始持有的上下文
            ImageView imageView = new ImageView(this);
            //使用setBackgroudResource,而不用setBackground
            imageView.setBackgroundResource(ids[i]);
            imageViews.add(imageView);
            //创建点
            ImageView point=new ImageView(this);
            point.setBackgroundResource(R.drawable.point_narmal);
            //params参数单位是像素，注意配适
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            if(i!=0){
                //进行适配
                params.leftMargin= DensityUtil.px2dip(GuideActivity.this,10);
            }
            point.setLayoutParams(params);
            ll_point_group.addView(point);
        }
        //设置viewpager的适配器
        viewPager.setAdapter(new MyPagerAdapter());
        //根据视图的生命周期，视图在执行onDraw,OnLayout方法前，早已经测量过，试图的宽、高、边距等都已获得
        red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyGlobalLayoutlistener());
        //为viewpager绑定监听器
        viewPager.setOnPageChangeListener(new MyOnPagerChangelistener());
        //为开始按钮绑定监听器
        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.保存进入过GuideActivity的状态数据
                CacheUtil.setState(GuideActivity.this, LauncherActivity.START_MAIN,true);
                //2.跳转到登录面
                Intent intent=new Intent(GuideActivity.this,LoginAcitivity.class);
                startActivity(intent);
                //3.关闭引导页面
                finish();

            }
        });
    }

    //创建viewpager配适器
    private class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView=imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    //创建视图树监听器
    private class MyGlobalLayoutlistener implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            //不止执行一次
            red_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            LEFT_MAX=ll_point_group.getChildAt(1).getLeft()-ll_point_group.getChildAt(0).getLeft();

        }
    }

    //创建viewpager监听器
    private class MyOnPagerChangelistener implements ViewPager.OnPageChangeListener {
        /**
         *当页面滚动回调这个方法
         * @param position 当前界面滑动位置
         * @param positionOffset 当前前界面滑动位置占屏幕百分比
         * @param positionOffsetPixels 当前已移动像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int distance=(int)((positionOffset+position)*LEFT_MAX);
            RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)red_point.getLayoutParams();
            params.leftMargin=distance;
            red_point.setLayoutParams(params);

        }

        /**
         *当页面被选中时回调的方法
         * @param position 选择的对应页面
         */
        @Override
        public void onPageSelected(int position) {
            if(position==imageViews.size()-1){
                btn_start_main.setVisibility(View.VISIBLE);
            }
            else{
                btn_start_main.setVisibility(View.GONE);
            }

        }

        /**
         *当viewpager页面状态发生改变时回调
         * @param state 有三种状态：静止，拖动，惯性
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
