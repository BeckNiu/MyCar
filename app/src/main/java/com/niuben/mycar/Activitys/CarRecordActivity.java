package com.niuben.mycar.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.niuben.mycar.Bean.MyCarBean;
import com.niuben.mycar.R;

/**
 * Created by niuben on 2016/5/16.
 */
public class CarRecordActivity extends Activity implements View.OnClickListener {

    private EditText et_car_type,et_car_time,et_car_address,et_car_ting;
    private ImageView iv_back;
    private TextView tv_sure;
    private int userId;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add);
        intent=getIntent();
        userId=intent.getIntExtra("userId", -1);
        initView();
    }

    private void initView() {
        et_car_type= (EditText) findViewById(R.id.et_car_type);
        et_car_time= (EditText) findViewById(R.id.et_car_time);
        et_car_address= (EditText) findViewById(R.id.et_car_address);
        et_car_ting= (EditText) findViewById(R.id.et_car_ting);
        tv_sure= (TextView) findViewById(R.id.tv_sure);
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sure:
                if(TextUtils.isEmpty(et_car_type.getText().toString().trim())&TextUtils.isEmpty(et_car_time.getText().toString().trim())&TextUtils.isEmpty(et_car_address.getText().toString().trim())&TextUtils.isEmpty(et_car_ting.getText().toString().trim())){
                    Toast.makeText(this, "请您先补全信息", Toast.LENGTH_LONG).show();
                }
                else {
                    MyCarBean mycarbean=new MyCarBean();
                    mycarbean.setCar_type(et_car_type.getText().toString().trim());
                    mycarbean.setTime(et_car_time.getText().toString().trim());
                    mycarbean.setAddress(et_car_address.getText().toString().trim());
                    mycarbean.setTing(et_car_ting.getText().toString().trim());
                    mycarbean.setUserId(userId);
                    mycarbean.save();
                    Toast.makeText(this,"添加成功",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.putExtra("bean", mycarbean);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
        }


    }
}
