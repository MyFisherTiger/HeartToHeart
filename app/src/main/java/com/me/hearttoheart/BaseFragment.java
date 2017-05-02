package com.me.hearttoheart;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/4/4.
 */
public abstract class BaseFragment extends Fragment {

    public Context context;

    /**
     * fragment被创建时回调
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    /**
     * 创建视图时回调
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }


    /**
     * activity创建后回调
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //提供子类实现自己的视图,一定要实现
    public abstract View initView();

    //空方法,建议子类实现，如果子页面没有数据，联网请求数据，实现数据与视图的绑定
    public void initData() {

    }

}
