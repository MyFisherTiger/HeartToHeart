package com.me.hearttoheart.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.hearttoheart.BaseFragment;
import com.me.hearttoheart.R;
import com.me.hearttoheart.utils.DensityUtil;

/**
 * Created by Administrator on 2017/4/4.
 * 右侧菜单
 */
public class LeftmenuFragment extends BaseFragment{

    @Override
    public View initView() {
        View leftmenu=View.inflate(context, R.layout.activity_leftmenu,null);
        return leftmenu;
    }

    @Override
    public void initData() {
        super.initData();

    }
}
