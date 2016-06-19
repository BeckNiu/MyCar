package com.niuben.mycar.Activitys;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.niuben.mycar.Bean.MyCarBean;
import com.niuben.mycar.R;

/**
 * Created by niuben on 2016/5/12.
 */
public class CarShowActivity extends BaseActivity {
    private TextView tv_carType,tv_carTime,tv_ting;
    private ImageView iv_back;
    private MyCarBean myCarBean;
    private Intent intent;
    @Override
    protected void setContent() {
        setContentView(R.layout.activity_show);
        intent=getIntent();
        myCarBean= (MyCarBean) intent.getSerializableExtra("bean");
    }

    @Override
    protected void findView() {
        tv_carType= (TextView) findViewById(R.id.tv_carType);
        tv_carTime= (TextView) findViewById(R.id.tv_carTime);
        tv_ting= (TextView) findViewById(R.id.tv_ting);
        iv_back= (ImageView) findViewById(R.id.iv_back);

    }

    @Override
    protected void initView() {
        tv_carType.getBackground().setAlpha(80);
        tv_carTime.getBackground().setAlpha(80);
        tv_ting.getBackground().setAlpha(80);
        tv_carType.setText(myCarBean.getCar_type());
        tv_carTime.setText(myCarBean.getTime());
        tv_ting.setText(myCarBean.getTing());
    }

    @Override
    protected void setListener() {

        iv_back.setOnClickListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setData() {

    }

    @Override
    public void onClick(View v) {

                finish();
    }

}


