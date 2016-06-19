package com.niuben.mycar.Activitys;

import android.os.Bundle;
import android.view.View;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by niuben on 2016/4/22.
 */
public abstract class BaseActivity extends AutoLayoutActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加布局
        setContent();
        //查找控件
        findView();
        //设置控件
        initView();
        //设置监听
        setListener();
        //获取数据
        getData();
        //处理数据
        setData();

    }
    protected abstract void setContent();
    protected abstract void findView();
    protected abstract void initView();
    protected abstract void setListener();
    protected abstract void getData();
    protected abstract void setData();
}
