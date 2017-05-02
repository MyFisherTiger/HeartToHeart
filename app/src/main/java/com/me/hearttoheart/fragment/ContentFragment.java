package com.me.hearttoheart.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.me.hearttoheart.BaseFragment;
import com.me.hearttoheart.R;
import com.me.hearttoheart.pager.BasePager;
import com.me.hearttoheart.pager.ComunitePager;
import com.me.hearttoheart.pager.HelpPager;
import com.me.hearttoheart.pager.MonthPager;
import com.me.hearttoheart.pager.OperaPager;
import com.me.hearttoheart.utils.DensityUtil;
import com.me.hearttoheart.utils.LogUtils;

import org.w3c.dom.Text;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/4.
 * 主界面
 */
public class ContentFragment extends BaseFragment{
    @ViewInject(R.id.ll_viewpager)
    private ViewPager viewPager;
    @ViewInject(R.id.bottom_tag)
    private RadioGroup radioGroup;
    private ArrayList<BasePager> basePagers;
    @Override
    public View initView() {
        View view=View.inflate(context, R.layout.fragment_main_content,null);
        //viewPager=(ViewPager)view.findViewById(R.id.ll_viewpager);
        //radioGroup=(RadioGroup)view.findViewById(R.id.bottom_tag);
        //注入视图，是该view与contentFragment类关联
        x.view().inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        basePagers=new ArrayList<>();
        basePagers.add(new MonthPager(context));
        basePagers.add(new HelpPager(context));
        basePagers.add(new OperaPager(context));
        basePagers.add(new ComunitePager(context));
        viewPager.setAdapter(new MyPagerAdapter());
        radioGroup.setOnCheckedChangeListener(new MyOnCheckListener());
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return basePagers.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager=basePagers.get(position);
            View rootView=basePager.rootView;
            //为了还用不着的延时加载，不在这全部初始化
            //basePager.initData();
            container.addView(rootView);
            return rootView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);

        }

    }

    private class MyOnCheckListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_month:
                    viewPager.setCurrentItem(0, false);
                    break;
                case R.id.rb_help:
                    viewPager.setCurrentItem(1,false);
                    break;
                case R.id.rb_opera:
                    viewPager.setCurrentItem(2,false);
                    break;
                case R.id.rb_comunite:
                    viewPager.setCurrentItem(3,false);
                    break;
                default:break;
            }

        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            BasePager basePager=basePagers.get(position);
            basePager.initData();


        }
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            switch (position){
                case 0:
                    radioGroup.check(R.id.rb_month);
                    break;
                case 1:
                    radioGroup.check(R.id.rb_help);
                    break;
                case 2:
                    radioGroup.check(R.id.rb_opera);
                    break;
                case 3:
                    radioGroup.check(R.id.rb_comunite);
                    break;
                default:break;
            }
        }



        @Override
        public void onPageScrollStateChanged(int state) {


        }
    }
}
