package com.me.hearttoheart.pager;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.me.hearttoheart.R;
import com.me.hearttoheart.activitys.MainActivity;
import com.me.hearttoheart.im.controller.activity.ComuniteActivity;

/**
 * Created by Administrator on 2017/4/5.
 */
public class ComunitePager extends BasePager{


    public ComunitePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        //设置标题
        pager_title.setText("小社区");
        //联网请求数据，创建视图
        View pager_comunite=View.inflate(context, R.layout.pager_comunite,null);
        //初始化监听器
        initListener(pager_comunite);
        //将数据绑定到fl_content中
        fl_content.addView(pager_comunite);
    }
    public void initListener(View view){
        //初始化控件
        LinearLayout pager_comunite_l1=(LinearLayout) view.findViewById(R.id.pager_comunite_l1);
        LinearLayout pager_comunite_l2=(LinearLayout) view.findViewById(R.id.pager_comunite_l2);
        LinearLayout pager_comunite_l3=(LinearLayout) view.findViewById(R.id.pager_comunite_l3);
        LinearLayout pager_comunite_l4=(LinearLayout) view.findViewById(R.id.pager_comunite_l4);
        //社区活动
        pager_comunite_l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"社区活动",Toast.LENGTH_SHORT).show();

            }
        });
        //发起活动
        pager_comunite_l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"发起活动",Toast.LENGTH_SHORT).show();

            }
        });
        //社区交流
        pager_comunite_l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"社区交流",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, ComuniteActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        //社区擂台
        pager_comunite_l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"社区擂台",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
