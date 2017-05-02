package com.me.hearttoheart.pager;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.me.hearttoheart.been.OperaBeen;
import com.me.hearttoheart.utils.Constant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/4/5.
 */
public class OperaPager extends BasePager{


    public OperaPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        //设置标题
        pager_title.setText("情感剧场");
        //联网请求数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setText("情感主页");
        textView.setTextSize(25);
        getDataFromNet();

        //将数据绑定到fl_content中
        fl_content.addView(textView);
    }

    private void getDataFromNet() {
        RequestParams requestParams=new RequestParams(Constant.OPERA_URL);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("联网请求成功", "onSuccess: ");
                OperaBeen operaBeen=processData(result);
                String title=operaBeen.getData().get(0).getChildren().get(1).getTitle();
                Log.d(title, "onSuccess: ");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("失败", "onError: ");

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("取消", "onCancelled: ");

            }

            @Override
            public void onFinished() {
                Log.d("完成", "onFinished: ");

            }
        });
    }

    private OperaBeen processData(String json) {
        Gson gson=new Gson();
        OperaBeen operaBeen=gson.fromJson(json, OperaBeen.class);
        return operaBeen;
    }
}
