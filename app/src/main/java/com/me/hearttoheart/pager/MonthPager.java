package com.me.hearttoheart.pager;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/5.
 */
public class MonthPager extends BasePager {
    public MonthPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        //设置标题
        pager_title.setText("心情");
        //联网请求数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setText("心情主页");
        textView.setTextSize(25);

        //将数据绑定到fl_content中
        fl_content.addView(textView);
    }
}
