package com.me.hearttoheart.pager;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.me.hearttoheart.R;

/**
 * Created by Administrator on 2017/4/5.
 */
public class HelpPager extends BasePager{


    public HelpPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        //设置标题
        pager_title.setText("寻求帮助");
        //(联网或直接)请求数据，创建视图
        View pager_help=View.inflate(context, R.layout.pager_help,null);


        //将数据绑定到fl_content中
        fl_content.addView(pager_help);
    }
}
