package com.me.hearttoheart.pager;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.me.hearttoheart.R;
import com.me.hearttoheart.activitys.MainActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2017/4/5.
 */
public class BasePager {
    public final Context context;//MainActivity
    //显示标题
    //@ViewInject(R.id.pager_title)
    public TextView pager_title;
    //点击弹出侧滑菜单
    //@ViewInject(R.id.pager_menu)
    public ImageButton pager_menu;
    //加载各个子页面
    //@ViewInject(R.id.fl_content)
    public FrameLayout fl_content;
    //各个不同的页面
    public View rootView;
    public BasePager(Context context) {
        this.context=context;

        rootView=initView();
    }

    //初始化公共视图及被重写后初始化子类视图
    private View initView() {
        View view=View.inflate(context, R.layout.pager_base,null);
        //效率上不好，其实不建议使用注入方式初始化
        //x.view().inject(this,view);
        pager_title=(TextView) view.findViewById(R.id.pager_title);
        pager_menu=(ImageButton)view.findViewById(R.id.pager_menu);
        fl_content=(FrameLayout) view.findViewById(R.id.fl_content);
        pager_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity=(MainActivity)context;
                mainActivity.getSlidingMenu().showMenu();
            }
        });
        return view;
    }
    //建议子类重写该方法，联网请求并初始化绑定数据
    public void initData(){

    }
}
